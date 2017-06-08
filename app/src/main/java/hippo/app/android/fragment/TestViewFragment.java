package hippo.app.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hippo.app.android.R;

/**
 * Created by Daniel on 8/6/17.
 */

public class TestViewFragment extends android.support.v4.app.Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment_all_tasks, container, false);
    }
}
