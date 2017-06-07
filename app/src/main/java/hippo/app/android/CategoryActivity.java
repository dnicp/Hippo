package hippo.app.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class CategoryActivity extends BaseActivity {

    private ImageView car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        car = (ImageView)findViewById(R.id.cat_car);

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, NewTaskActivity.class));
            }
        });
    }

}
