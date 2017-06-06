package hippo.app.android.viewholder;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hippo.app.android.R;
import hippo.app.android.models.Category;


public class CategoryViewAdatper extends ArrayAdapter<Category> {

    public CategoryViewAdatper (Context context, ArrayList<Category> categories){
        super(context,0,categories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_category, parent, false);
        }

        // Get the {@link category} object located at this position in the list
        Category currentCategory = getItem(position);

        TextView categoryTextView = (TextView) gridItemView.findViewById(R.id.categoryText);

        categoryTextView.setText(currentCategory.getCategoryName());



        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView categoryImageView = (ImageView) gridItemView.findViewById(R.id.categoryImage);
        // Check if an image is provided for this word or not
        if (currentCategory.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            categoryImageView.setImageResource(currentCategory.getImageResourceId());
            // Make sure the view is visible
            categoryImageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            categoryImageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = gridItemView.findViewById(R.id.text_container);


        // Return the whole list item layout
        return gridItemView;
    }

}