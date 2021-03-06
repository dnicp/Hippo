package hippo.app.android.fragment;

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
import hippo.app.android.models.Task;
import hippo.app.android.TaskViewHolder;

public abstract class TaskListFragment extends Fragment {

    private static final String TAG = "TaskListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<Task, TaskViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public TaskListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_tasks, container, false);

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
        mAdapter = new FirebaseRecyclerAdapter<Task, TaskViewHolder>(Task.class, R.layout.item_task,
                TaskViewHolder.class, taskQuery) {
            @Override
            protected void populateViewHolder(final TaskViewHolder viewHolder, final Task model, final int position) {
                final DatabaseReference taskRef = getRef(position);

                // Set click listener for the whole task view
                final String taskKey = taskRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch TaskDetailActivity
                        Intent intent = new Intent(getActivity(), TaskDetailActivity.class);
                        intent.putExtra(TaskDetailActivity.EXTRA_TASK_KEY, taskKey);
                        startActivity(intent);
                    }
                });

                // Determine if the current user has liked this task and set UI accordingly
                if (model.stars.containsKey(getUid())) {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
                } else {
                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
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

    public abstract Query getQuery(DatabaseReference databaseReference);

}