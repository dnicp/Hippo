package hippo.app.android.viewholder_or_adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.R;
import hippo.app.android.models.Task;


public class TestTaskViewHolder extends RecyclerView.ViewHolder {

    public TextView timeView;
    public TextView dateView;
    public TextView weekdayView;
    public ImageView categoryView;
    public ImageView onOffView;
    public ImageView inOutView;
    public ImageView poolingView;
    public ImageView locationImageView;
    public TextView locationView;


    public TestTaskViewHolder(View itemView) {
        super(itemView);

        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        weekdayView = (TextView) itemView.findViewById(R.id.task_weekday);
        categoryView = (ImageView) itemView.findViewById(R.id.task_category);
        onOffView = (ImageView) itemView.findViewById(R.id.task_onoff);
        inOutView = (ImageView) itemView.findViewById(R.id.task_inout);
        poolingView = (ImageView) itemView.findViewById(R.id.task_pooling);
        locationImageView = (ImageView) itemView.findViewById(R.id.task_location_image);
        locationView = (TextView) itemView.findViewById(R.id.task_location);
    }

    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        weekdayView = (TextView) itemView.findViewById(R.id.task_weekday);
        categoryView = (ImageView) itemView.findViewById(R.id.task_category);
        onOffView = (ImageView) itemView.findViewById(R.id.task_onoff);
        inOutView = (ImageView) itemView.findViewById(R.id.task_inout);
        poolingView = (ImageView) itemView.findViewById(R.id.task_pooling);
        locationImageView = (ImageView) itemView.findViewById(R.id.task_location_image);
        locationView = (TextView) itemView.findViewById(R.id.task_location);

    }
}
