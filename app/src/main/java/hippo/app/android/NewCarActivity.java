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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import hippo.app.android.fragment.DatePickerFragment;
import hippo.app.android.fragment.TimePickerFragment;
import hippo.app.android.models.Task;
import hippo.app.android.models.User;

import static android.R.attr.category;
import static android.R.attr.description;


public class NewCarActivity extends hippo.app.android.BaseActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private static final String TAG = "NewCarActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]


    // floatingActionButton
    private FloatingActionButton mSubmitButton;

    // regular variables

    public TextView mCategory;
    public EditText mDescription;
    public EditText mPoolingmin;
    public ImageView mDatepicker;
    public ImageView mTimepikcer;
    public TextView mDate;
    public TextView mTime;
    public TextView mLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCategory = (TextView) findViewById(R.id.task_cat);
        mDescription = (EditText) findViewById(R.id.task_des);
        mPoolingmin = (EditText) findViewById(R.id.task_poolingmin);
        mTimepikcer = (ImageView) findViewById(R.id.showTimePicker);
        mDatepicker = (ImageView) findViewById(R.id.showDatePicker);
        mLocation = (EditText) findViewById(R.id.task_loc);
        mDate = (TextView) findViewById(R.id.task_date);
        mTime = (TextView) findViewById(R.id.task_time);

        // autofill category
        mCategory.setText("Category: Shared Drive");

        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_task);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                submitTask();
            }
        });



// time and date picker activity
        mTimepikcer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newTimeFragment = new TimePickerFragment();
                newTimeFragment.show(getSupportFragmentManager(), "timePicker");

            }
        });

        mDatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });

        // set the capture of date and time
        public void onDateSet(DatePicker view, int year, int month, int day) {
            String date = day + "/" + month + "/" + year;
            mDate.setText(date);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = hourOfDay + ":" + minute;
            mTime.setText(time);
        }

    }

    private void submitTask() {
        final String category = "car";
        final String description = mDescription.getText().toString();
        final String poolingmin = mPoolingmin.getText().toString();
        final String date = mDate.getText().toString();
        final String time = mTime.getText().toString();
        final String location = mLocation.getText().toString();

        // below variables are to be calculated
        final int participants = 0;
        final boolean lightson = false;
        final boolean authorinout = false;
        final String weekday = "Monday";


        // Description is required
        if (TextUtils.isEmpty(description)) {
            mDescription.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(location)) {
            mPoolingmin.setError(REQUIRED);
            return;
        }


        if (TextUtils.isEmpty(location)) {
            mDate.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(date)) {
            mTime.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(time)) {
            mLocation.setError(REQUIRED);
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
                            Toast.makeText(NewCarActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username,category, description,poolingmin,participants,lightson, authorinout, date, weekday, time, location);
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

    // what's going on here?
    private void setEditingEnabled(boolean enabled) {
        mDescription.setEnabled(enabled);
        mPoolingmin.setEnabled(enabled);
        mDate.setEnabled(enabled);
        mTime.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String userId, String user.username, String category,String description, int poolingmin, int participants, boolean lightson, boolean authorinout, String date, String weekday, String time, String location) {
        // Create new task at /user-tasks/$userid/$taskid and at
        // /tasks/$taskid simultaneously
        String key = mDatabase.child("tasks").push().getKey();
        Task task = new Task(userId,user.username,category, description,poolingmin,participants,lightson, authorinout, date, weekday, time, location);
        Map<String, Object> postValues = task.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/tasks/" + key, postValues);
        childUpdates.put("/user-tasks/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}