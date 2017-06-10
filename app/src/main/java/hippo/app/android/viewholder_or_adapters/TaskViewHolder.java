package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.models.Task;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView locationView;
    public TextView timeView;
    public TextView dateView;

    public TaskViewHolder(View itemView) {
        super(itemView);

        authorView = (TextView) itemView.findViewById(R.id.task_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.task_num_stars);
        locationView = (TextView) itemView.findViewById(R.id.task_location);
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);

    }

    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        authorView.setText(task.author);
        numStarsView.setText(String.valueOf(task.starCount));
        starView.setOnClickListener(starClickListener);
        locationView.setText(task.location);
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        dateView = (TextView) itemView.findViewById(R.id.task_date);



    }
}
