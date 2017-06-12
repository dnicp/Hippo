package hippo.app.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class CategoryActivity extends BaseActivity {

    private ImageView car;
    private ImageView events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

        car = (ImageView)findViewById(R.id.cat_car);
        events = (ImageView)findViewById(R.id.cat_events);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, NewTaskActivity.class));
                passOnCategory =  "car";
                finish();
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, NewTaskActivity.class));
                passOnCategory =  "events";
                finish();
            }
        });
    }

}
