package hippo.app.android;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import hippo.app.android.models.Friend;


/**
 * Created by Daniel on 12/6/17.
 */

public class allFriendsActivity extends BaseActivity

{

    public static final String EXTRA_FRIEND_KEY = "task_key";
    private static final String TAG = "allFriendsActivity";
    private String mFriendsTaskKey;

    private DatabaseReference mFriendReference;

    private TextView mFriendSurname;
    private TextView mFriendGivenname;

    private FirebaseRecyclerAdapter<Friend, hippo.app.android.FriendViewHolder> mAdapter;
    private RecyclerView mFriendRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allfriends);

//        // Get task key from intent
//        mFriendsTaskKey = getIntent().getStringExtra(EXTRA_FRIEND_KEY);
//        if (mFriendsTaskKey == null) {
//            throw new IllegalArgumentException("Must pass FRIENDS_TASK_KEY");
//        }

        // Initialize Database
        mFriendReference = FirebaseDatabase.getInstance().getReference();

        // Initialize Views
        mFriendSurname = (TextView) findViewById(R.id.friend_surname);
        mFriendGivenname = (TextView) findViewById(R.id.friend_givenname);

        // Recycleview stuff

        mFriendRecycler = (RecyclerView) findViewById(R.id.allfriends);

        //set layout manager
        mFriendRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Set up FirebaseRecyclerAdapter with the Query
        Query friendQuery = getQuery(mFriendReference);
        mAdapter = new FirebaseRecyclerAdapter<Friend, hippo.app.android.FriendViewHolder>(Friend.class, R.layout.allfriends,
                hippo.app.android.FriendViewHolder.class, friendQuery) {

            // using viewholder to insert FB data in the form of task_dashboard to the container
            @Override
            protected void populateViewHolder(final hippo.app.android.FriendViewHolder viewHolder, final Friend model, final int position) {
                final DatabaseReference friendRef = getRef(position);

//                // Set click listener for the whole friend view
//                final String taskKey = friendRef.getKey();
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Launch FriendDetailActivity
//                        Intent intent = new Intent(allFriendsActivity.this, addFriendsActivity.class);
//        intent.putExtra(addFriendsActivity.EXTRA_FRIEND_KEY, taskKey);
//                        startActivity(intent);
//                    }
//                });

            }
        };


        mFriendRecycler.setAdapter(mAdapter);

    }

    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_tasks_query]
        // Last 100 tasks, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query friendQuery = databaseReference.child("user-friends").limitToFirst(100);
        // [END recent_tasks_query]

        return friendQuery;
    }
}
