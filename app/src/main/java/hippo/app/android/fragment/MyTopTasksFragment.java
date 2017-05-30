package hippo.app.android.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyTopTasksFragment extends TaskListFragment {

    public MyTopTasksFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START my_top_tasks_query]
        // My top tasks by number of stars
        String myUserId = getUid();
        Query myTopTasksQuery = databaseReference.child("user-tasks").child(myUserId)
                .orderByChild("starCount");
        // [END my_top_tasks_query]

        return myTopTasksQuery;
    }
}
