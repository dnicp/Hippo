package hippo.app.android.viewholder;

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
    public ImageView onoffView;
    public ImageView inoutView;
    public ImageView poolingView;
    public ImageView locationimageView;
    public TextView locationView;


    public TestTaskViewHolder(View itemView) {
        super(itemView);

        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        weekdayView = (TextView) itemView.findViewById(R.id.task_weekday);
        categoryView = (ImageView) itemView.findViewById(R.id.task_category);
        onoffView = (ImageView) itemView.findViewById(R.id.task_onoff);
        inoutView = (ImageView) itemView.findViewById(R.id.task_inout);
        poolingView = (ImageView) itemView.findViewById(R.id.task_pooling);
        locationimageView = (ImageView) itemView.findViewById(R.id.task_location_image);
        locationView = (TextView) itemView.findViewById(R.id.task_location);
    }

    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        weekdayView = (TextView) itemView.findViewById(R.id.task_weekday);
        categoryView = (ImageView) itemView.findViewById(R.id.task_category);
        onoffView = (ImageView) itemView.findViewById(R.id.task_onoff);
        inoutView = (ImageView) itemView.findViewById(R.id.task_inout);
        poolingView = (ImageView) itemView.findViewById(R.id.task_pooling);
        locationimageView = (ImageView) itemView.findViewById(R.id.task_location_image);
        locationView = (TextView) itemView.findViewById(R.id.task_location);

    }
}
