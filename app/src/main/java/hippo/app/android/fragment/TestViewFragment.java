package hippo.app.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hippo.app.android.R;
import hippo.app.android.TestTaskDetailActivity;
import hippo.app.android.viewholder_or_adapters.TestTaskAdapter;

/**
 * Created by Daniel on 8/6/17.
 */

public class TestViewFragment extends android.support.v4.app.Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_listview_item_list, container, false);


        // Create a list of words
        final ArrayList<TestTaskDetailActivity> tasks = new ArrayList<>();

        tasks.add(new TestTaskDetailActivity("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in, R.drawable.ic_pooling,"3", R.drawable.ic_location,"Ultimo"));
        tasks.add(new TestTaskDetailActivity("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in, R.drawable.ic_pooling,"3", R.drawable.ic_location,"Ultimo"));

        TestTaskAdapter adapter = new TestTaskAdapter(, tasks);
        ListView listView = (ListView) getView().findViewById(R.id.list);
        listView.setAdapter(adapter);



    }
}
