package hippo.app.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import hippo.app.android.models.Friend;
import hippo.app.android.models.User;

/**
 * Created by Daniel on 12/6/17.
 */

public class addContactActivity extends BaseActivity {

    private static final String TAG = "AddContactActivity";
    private static final String REQUIRED = "Required";

    private DatabaseReference mDatabase;

    EditText mSurname;
    EditText mGivenname;

    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);

        // initialize_database_ref
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // hook object to view
        mSurname = (EditText) findViewById(R.id.surname);
        mGivenname = (EditText) findViewById(R.id.givenname);

        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_contact);

        // set OnClickListener
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTask();
            }
        });

    }

    private void submitTask() {
        final String surname = mSurname.getText().toString();
        final String givenname = mGivenname.getText().toString();

        // Description is required
        if (TextUtils.isEmpty(surname)) {
            mSurname.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(givenname)) {
            mGivenname.setError(REQUIRED);
            return;
        }

        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // writing to firebase

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
                            Toast.makeText(addContactActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username, surname, givenname);
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

    private void setEditingEnabled(boolean enabled) {
        mSurname.setEnabled(enabled);
        mGivenname.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }

    // [START write_fan_out]
    private void writeNewPost(String userId, String username, String surname, String givenname) {
        // Create new task at /user-tasks/$userid/$taskid and at
        // /tasks/$taskid simultaneously
        String key = mDatabase.child("user_friends").push().getKey();
        Friend friend = new Friend(userId, username, surname,givenname);
        Map<String, Object> postValues = friend.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/user-friends/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]
}
