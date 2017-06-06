package hippo.app.android;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import hippo.app.android.models.Category;
import hippo.app.android.viewholder.CategoryViewAdatper;

public class categoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);

        // Create a list of categories
        final ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category(R.drawable.ic_car, "Car"));
        categories.add(new Category(R.drawable.ic_add_button,"button"));


        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        CategoryViewAdatper adapter = new CategoryViewAdatper(this, categories);


        GridView gridView = (GridView) findViewById(R.id.catetoryList);

        gridView.setAdapter(adapter);

    }
}
