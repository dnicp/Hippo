package hippo.app.android.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class RecentTasksFragment extends TaskListFragment {

    public RecentTasksFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_tasks_query]
        // Last 100 tasks, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentTasksQuery = databaseReference.child("tasks")
                .limitToFirst(100);
        // [END recent_tasks_query]

        return recentTasksQuery;
    }
}
