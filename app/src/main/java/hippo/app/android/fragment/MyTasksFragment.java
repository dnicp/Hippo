package hippo.app.android.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyTasksFragment extends TaskListFragment {

    public MyTasksFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // All my tasks
        return databaseReference.child("user-tasks")
                .child(getUid());
    }
}