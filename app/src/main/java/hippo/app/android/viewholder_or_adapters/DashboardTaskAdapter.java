package hippo.app.android.viewholder_or_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hippo.app.android.R;
import hippo.app.android.DashboardTaskDetail;

/**
 * Created by Daniel on 9/6/17.
 */

public class DashboardTaskAdapter extends ArrayAdapter<DashboardTaskDetail> {


    public DashboardTaskAdapter(Context context, ArrayList<DashboardTaskDetail> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.dashboard_item_task, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        DashboardTaskDetail currentTask = getItem(position);

        // get views

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.task_time);
        timeTextView.setText(currentTask.getmTime());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.task_date);
        dateTextView.setText(currentTask.getmDate());

        TextView weekdayTextView = (TextView) listItemView.findViewById(R.id.task_weekday);
        weekdayTextView.setText(currentTask.getmWeekday());

        ImageView categoryView = (ImageView) listItemView.findViewById(R.id.task_category);
        categoryView.setImageResource(currentTask.getmCategoryImageSourceId());

        ImageView onoffView = (ImageView) listItemView.findViewById(R.id.task_onoff);
        onoffView.setImageResource(currentTask.getmOnOffImageSourceId());

        ImageView inoutView = (ImageView) listItemView.findViewById(R.id.task_inout);
        inoutView.setImageResource(currentTask.getmInOutImageSourceId());

        TextView poolingView = (TextView) listItemView.findViewById(R.id.task_minPooling);
        poolingView.setText(currentTask.getmPooling());

        TextView tasklocationView = (TextView) listItemView.findViewById(R.id.task_location);
        tasklocationView.setText(currentTask.getmLocation());


        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
