package hippo.app.android.main_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

import hippo.app.android.R;
import hippo.app.android.TaskDetailActivity;
import hippo.app.android.TaskViewHolder;
import hippo.app.android.models.Task;

public class DashboardFragment extends Fragment {

    public DashboardFragment() {}


    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_tasks_query]
        // Last 100 tasks, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query tasksQuery = databaseReference.child("tasks");
        // [END recent_tasks_query]

        return tasksQuery;
    }

    private static final String TAG = "RecentTaskFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<Task, TaskViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.dashboard_layout, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query taskQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(Task.class, R.layout.task_dashboard,
                TaskViewHolder.class, taskQuery) {

            // using viewholder to insert FB data in the form of task_dashboard to the container
            @Override
            protected void populateViewHolder(final TaskViewHolder viewHolder, final Task model, final int position) {
                final DatabaseReference taskRef = getRef(position);

                // do something here to show lightson/off starts

                int vStartCount = model.starCount;
                int vMinPooling = model.minPooling;
                if(vStartCount >= vMinPooling){
                    viewHolder.lightsOnOffView.setImageResource(R.drawable.ic_lightson);
                } else {
                    viewHolder.lightsOnOffView.setImageResource(R.drawable.ic_lightsoff);
                }

                // do something here to show lightson/off finish


                final String taskKey = taskRef.getKey();

                // Set click listener for the whole task view
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch TaskDetailActivity
                        Intent intent = new Intent(getActivity(), TaskDetailActivity.class);

                        // sending bundle to TaskDetailActivity
                        intent.putExtra(TaskDetailActivity.EXTRA_TASK_KEY, taskKey);
                        startActivity(intent);
                    }
                });

                // Determine if the current user has liked this task and set UI accordingly
                if (model.stars.containsKey(getUid())) {
                    viewHolder.starView.setImageResource(R.drawable.ic_in);
                } else {
                    viewHolder.starView.setImageResource(R.drawable.ic_notin);
                }

                // Bind Task to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToTask(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {
                        // Need to write to both places the task is stored
                        DatabaseReference globalTaskRef = mDatabase.child("tasks").child(taskRef.getKey());
                        DatabaseReference userTaskRef = mDatabase.child("user-tasks").child(model.uid).child(taskRef.getKey());

                        // Run two transactions
                        onStarClicked(globalTaskRef);
                        onStarClicked(userTaskRef);
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    // [START task_stars_transaction]
    private void onStarClicked(DatabaseReference taskRef) {
        taskRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Task p = mutableData.getValue(Task.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                if (p.stars.containsKey(getUid())) {
                    // Unstar the task and remove self from stars
                    p.starCount = p.starCount - 1;
                    p.stars.remove(getUid());
                } else {
                    // Star the task and add self to stars
                    p.starCount = p.starCount + 1;
                    p.stars.put(getUid(), true);
                }

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "taskTransaction:onComplete:" + databaseError);
            }
        });
    }
    // [END task_stars_transaction]

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
