package hippo.app.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hippo.app.android.R;
import hippo.app.android.TestTaskDetail;
import hippo.app.android.viewholder_or_adapters.TestTaskAdapter;

/**
 * Created by Daniel on 8/6/17.
 */

public class TestViewFragment extends Fragment{


    public TestViewFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.test_listview_item_list, container, false);


        // Create a list of words
        final ArrayList<TestTaskDetail> tasks = new ArrayList<TestTaskDetail>();

        tasks.add(new TestTaskDetail("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in, R.drawable.ic_pooling,"3", R.drawable.ic_location,"Ultimo"));
        tasks.add(new TestTaskDetail("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in, R.drawable.ic_pooling,"3", R.drawable.ic_location,"Ultimo"));

        TestTaskAdapter adapter = new TestTaskAdapter(getActivity(), tasks);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

return (rootView);

    }
}
