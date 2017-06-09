package hippo.app.android.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import hippo.app.android.R;
import hippo.app.android.DashboardTaskDetail;
import hippo.app.android.viewholder_or_adapters.DashboardTaskAdapter;

/**
 * Created by Daniel on 8/6/17.
 */

public class DashboardViewFragment extends Fragment{


    public DashboardViewFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard_listview_container, container, false);


        // Create a list of words
        final ArrayList<DashboardTaskDetail> tasks = new ArrayList<DashboardTaskDetail>();

        tasks.add(new DashboardTaskDetail("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in,"3","Ultimo"));
        tasks.add(new DashboardTaskDetail("9:00", "04 April","Wednesday",R.drawable.ic_car,R.drawable.ic_lightson,R.drawable.ic_in,"3","Ultimo"));

        DashboardTaskAdapter adapter = new DashboardTaskAdapter(getActivity(), tasks);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

return (rootView);

    }
}
