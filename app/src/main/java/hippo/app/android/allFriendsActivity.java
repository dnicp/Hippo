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
    private String mFriendKey;

    private DatabaseReference mFriendReference = FirebaseDatabase.getInstance().getReference();

    private TextView mFriendSurname;
    private TextView mFriendGivenname;
    private TextView mSampleSurname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allfriends);
// Initialize DB
        FirebaseRecyclerAdapter<Friend, hippo.app.android.FriendViewHolder> mAdapter;
        RecyclerView mFriendRecycler = (RecyclerView) findViewById(R.id.friends_list);
        mFriendReference = FirebaseDatabase.getInstance().getReference();

        // Initialize Views
        mFriendSurname = (TextView) findViewById(R.id.friend_surname);
        mFriendGivenname = (TextView) findViewById(R.id.friend_givenname);
        mSampleSurname = (TextView) findViewById(R.id.sample_surname);

        // Recycleview stuff

        mFriendRecycler = (RecyclerView) findViewById(R.id.friends_list);

        //set layout manager
        mFriendRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Set up FirebaseRecyclerAdapter with the Query
        // Test Query
        Query friendQuery = getQuery(mFriendReference);


        // Recycler stuff
        mFriendRecycler.setHasFixedSize(true);
        mFriendRecycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new FirebaseRecyclerAdapter<Friend, hippo.app.android.FriendViewHolder>(Friend.class, R.layout.friend_detail, hippo.app.android.FriendViewHolder.class, friendQuery)

        {
            public void populateViewHolder(hippo.app.android.FriendViewHolder friendViewHolder,
                                           Friend friend,
                                           int position) {
                friendViewHolder.surnameView.setText(friend.surname);
                friendViewHolder.givennameView.setText(friend.givenname);
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
