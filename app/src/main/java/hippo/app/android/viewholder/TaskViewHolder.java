package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.models.Task;

import static hippo.app.android.R.drawable.ic_lightsoff;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView authorView;
    public ImageView starView;
    public TextView numPoolingView;
    public TextView locationView;
    public TextView timeView;
    public TextView dateView;
    public ImageView lightsOnOffView;



    public TaskViewHolder(View itemView) {
        super(itemView);

        authorView = (TextView) itemView.findViewById(R.id.task_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numPoolingView = (TextView) itemView.findViewById(R.id.task_num_stars);
        locationView = (TextView) itemView.findViewById(R.id.task_location_comment);
        timeView = (TextView) itemView.findViewById(R.id.task_time_comment);
        dateView = (TextView) itemView.findViewById(R.id.task_date_comment);
        lightsOnOffView = (ImageView) itemView.findViewById(R.id.task_onoff);


    }

    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        authorView.setText(task.author);
        numPoolingView.setText(String.valueOf(task.starCount));
        timeView.setText(task.time);
        dateView.setText(task.date);
        locationView.setText(task.location);
        starView.setOnClickListener(starClickListener);
        lightsOnOffView.setImageResource(ic_lightsoff);

    }
}
