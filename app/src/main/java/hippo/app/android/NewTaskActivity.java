package hippo.app.android;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import hippo.app.android.dialog_fragments.DatePickerFragment;
import hippo.app.android.dialog_fragments.TimePickerFragment;
import hippo.app.android.models.Task;
import hippo.app.android.models.User;


public class NewTaskActivity extends hippo.app.android.BaseActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private static final String TAG = "NewTaskActivity";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;

    private TextView mTaskCategory;
    private EditText mTaskDescription;
    private EditText mTaskLocation;
    private ImageView mTime;
    private ImageView mDate;
    private TextView mTaskTime;
    private TextView mTaskDate;
    private TextView mWeekday;
    private EditText mMinPooling;

    private FloatingActionButton mSubmitButton;


    // set the capture of date and time
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = day + "/" + month + "/" + year;
        mTaskDate.setText(date);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + ":" + minute;
        mTaskTime.setText(time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        mTaskCategory = (TextView) findViewById(R.id.task_category_comment);
        // inflate the category view with the value
        mTaskCategory.setText(passOnCategory);

        mTaskDescription = (EditText) findViewById(R.id.task_description_comment);
        mTaskLocation = (EditText) findViewById(R.id.task_location_comment);


        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_task);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTask();
            }
        });
        mTaskTime = (TextView) findViewById(R.id.task_time_comment);
        mTaskDate = (TextView) findViewById(R.id.task_date_comment);
        mMinPooling = (EditText) findViewById(R.id.task_poolingMin);

        mTime = (ImageView) findViewById(R.id.showTimePicker);
        mDate = (ImageView) findViewById(R.id.showDatePicker);

// time and date picker activity
        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newTimeFragment = new TimePickerFragment();
                newTimeFragment.show(getSupportFragmentManager(), "timePicker");

            }
        });

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });


    }

    private void submitTask() {
        final String category = passOnCategory;
        final String description = mTaskDescription.getText().toString();
        final String location = mTaskLocation.getText().toString();
        final String date = mTaskDate.getText().toString();
        final String time = mTaskTime.getText().toString();
        final int minPooling = Integer.parseInt(mMinPooling.getText().toString());

        // Description is required
        if (TextUtils.isEmpty(description)) {
            mTaskDescription.setError(REQUIRED);
            return;
        }

        // Location is required
        if (TextUtils.isEmpty(location)) {
            mTaskLocation.setError(REQUIRED);
            return;
        }

        // minPooling is required

        if (TextUtils.isEmpty(location)) {
            mMinPooling.setError(REQUIRED);
            return;
        }

        // Date is required
        if (TextUtils.isEmpty(date)) {
            mTaskDate.setError(REQUIRED);
            return;
        }
        // Time is required
        if (TextUtils.isEmpty(time)) {
            mTaskTime.setError(REQUIRED);
            return;
        }
        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(NewTaskActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username, category, description, location,date,time,minPooling);
                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    clearToMainactivity();
    }

    // function called to avoid double input?
    private void setEditingEnabled(boolean enabled) {
        mTaskDescription.setEnabled(enabled);
        mTaskLocation.setEnabled(enabled);
        mTaskDate.setEnabled(enabled);
        mTaskTime.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String userId, String username, String category, String description, String location, String date, String time, int minPooling) {
        // Create new task at /user-tasks/$userid/$taskid and at
        // /tasks/$taskid simultaneously
        String key = mDatabase.child("tasks").push().getKey();
        Task task = new Task(userId, username, category,description, location, date,time, minPooling);
        Map<String, Object> postValues = task.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/tasks/" + key, postValues);
        childUpdates.put("/user-tasks/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}