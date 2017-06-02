package hippo.app.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import hippo.app.android.fragment.DatePickerFragment;
import hippo.app.android.fragment.TimePickerFragment;
import hippo.app.android.models.Task;
import hippo.app.android.models.User;


public class NewTaskActivity extends hippo.app.android.BaseActivity {

    
    private static final String TAG = "NewTaskActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText mTaskDes;
    private EditText mTaskLoc;
    private RadioGroup mPoolingGroup;
    private RadioButton mPoolingRadioButton;
    private String mPoolingValue;
    private FloatingActionButton mSubmitButton;
    private Button mTimeButton;
    private Button mDateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        mTaskDes = (EditText) findViewById(R.id.task_des);
        mTaskLoc = (EditText) findViewById(R.id.task_loc);

        // start of radio stuff
        mPoolingGroup = (RadioGroup) findViewById(R.id.radioPooling);

        mPoolingValue = "something";

        // end of radio stuff

        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_task);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTask();
            }
        });

       mTimeButton = (Button) findViewById(R.id.showTimePicker);
        mDateButton = (Button) findViewById(R.id.showDatePicker);

// time and date picker activity
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newTimeFragment = new TimePickerFragment();
                newTimeFragment.show(getSupportFragmentManager(), "timePicker");

            }
        });

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newDateFragment = new DatePickerFragment();
                newDateFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });
    }

    private void submitTask() {
        final String description = mTaskDes.getText().toString();
        final String location = mTaskLoc.getText().toString();


        // not happy with this part radio group start
        int selectedId = mPoolingGroup.getCheckedRadioButtonId();
        mPoolingRadioButton = (RadioButton) findViewById(selectedId);
        final String pooling = mPoolingRadioButton.getTag().toString();

        // something going on with radio group finish


        // Description is required
        if (TextUtils.isEmpty(description)) {
            mTaskDes.setError(REQUIRED);
            return;
        }

        // Location is required
        if (TextUtils.isEmpty(location)) {
            mTaskLoc.setError(REQUIRED);
            return;
        }

        // Pooling info is required
        if (TextUtils.isEmpty(pooling)) {
            mTaskLoc.setError(REQUIRED);
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
                            writeNewPost(userId, user.username, description, location,pooling);
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
    }

    // what's going on here?
    private void setEditingEnabled(boolean enabled) {
        mTaskDes.setEnabled(enabled);
        mTaskLoc.setEnabled(enabled);
        mPoolingGroup.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String userId, String username, String description, String location, String pooling) {
        // Create new task at /user-tasks/$userid/$taskid and at
        // /tasks/$taskid simultaneously
        String key = mDatabase.child("tasks").push().getKey();
        Task task = new Task(userId, username, description, location, pooling);
        Map<String, Object> postValues = task.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/tasks/" + key, postValues);
        childUpdates.put("/user-tasks/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}