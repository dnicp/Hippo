package hippo.app.android.models;

/**
 * Created by Daniel on 6/6/17.
 */

public class Category {

    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private String mCategoryName;

    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    public Category(int ImageResourceId, String CategoryName) {

        mImageResourceId = ImageResourceId;
        mCategoryName = CategoryName;

    }


    public int getImageResourceId() {
        return mImageResourceId;

    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

}