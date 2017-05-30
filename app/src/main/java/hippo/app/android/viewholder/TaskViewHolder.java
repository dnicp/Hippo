package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.models.Task;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView descriptionView;
    public TextView locationView;
    public TextView poolingView;

    public TaskViewHolder(View itemView) {
        super(itemView);

        authorView = (TextView) itemView.findViewById(R.id.task_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.task_num_stars);
        descriptionView = (TextView) itemView.findViewById(R.id.task_des);
        locationView = (TextView) itemView.findViewById(R.id.task_loc);
        poolingView = (TextView) itemView.findViewById(R.id.task_pooling);
    }

    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        authorView.setText(task.author);
        numStarsView.setText(String.valueOf(task.starCount));
        starView.setOnClickListener(starClickListener);
        descriptionView.setText(task.description);
        locationView.setText(task.location);
        poolingView.setText(task.pooling);


    }
}
