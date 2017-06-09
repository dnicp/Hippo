package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.models.Task;


public class TaskViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryView;
    public EditText descriptionView;
    public EditText poolingminView;
    public ImageView datepickerView;
    public ImageView timepikcerView;
    public TextView dateView;
    public TextView timeView;
    public TextView locationView;

    public TaskViewHolder(View itemView) {
        super(itemView);

        categoryView = (TextView) itemView.findViewById(R.id.task_category);
        descriptionView = (EditText) itemView.findViewById(R.id.task_description);
        poolingminView = (EditText) itemView.findViewById(R.id.task_poolingmin);
        datepickerView = (ImageView) itemView.findViewById(R.id.showDatePicker);
        timepikcerView = (ImageView) itemView.findViewById(R.id.showTimePicker);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        locationView = (TextView) itemView.findViewById(R.id.task_location);

    }


    public void bindToTask(Task task, View.OnClickListener starClickListener) {
        categoryView = (TextView) itemView.findViewById(R.id.task_category);
        descriptionView = (EditText) itemView.findViewById(R.id.task_description);
        poolingminView = (EditText) itemView.findViewById(R.id.task_poolingmin);
        datepickerView = (ImageView) itemView.findViewById(R.id.showDatePicker);
        timepikcerView = (ImageView) itemView.findViewById(R.id.showTimePicker);
        dateView = (TextView) itemView.findViewById(R.id.task_date);
        timeView = (TextView) itemView.findViewById(R.id.task_time);
        locationView = (TextView) itemView.findViewById(R.id.task_location);


    }
}
