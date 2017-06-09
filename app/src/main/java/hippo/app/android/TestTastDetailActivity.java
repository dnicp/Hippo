/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hippo.app.android;


import android.widget.ImageView;
import android.widget.TextView;

public class TestTastDetailActivity {

    private String mTime;
    private String mDate;
    private String mWeekday;
    private int mCategoryImageSourceId = NO_IMAGE_PROVIDED;
    private int mOnOffImageSourceId = NO_IMAGE_PROVIDED;
    private int mInOutImageSourceId = NO_IMAGE_PROVIDED;
    private int mPoolingImagesourceId = NO_IMAGE_PROVIDED;
    private int mLocationImageSourceId = NO_IMAGE_PROVIDED;
    private String mLocation;
    private static final int NO_IMAGE_PROVIDED = -1;

    public TestTastDetailActivity(String time, String date,String weekday, int categoryImageSourceId, int onOffImageSourceId, int inOutImageSourceId, int poolingImageSourceId, int LocationImageSourceId, String location) {
        mTime = time;
        mDate = date;
        mWeekday = weekday;
        mCategoryImageSourceId = categoryImageSourceId;
        mOnOffImageSourceId = onOffImageSourceId;
        mInOutImageSourceId = inOutImageSourceId;
        mPoolingImagesourceId = poolingImagesourceId;
        mLocationImageSourceId = locationImageSourceId;
        mLocation = location;
    }


    /**
     * Get the default translation of the word.
     */
    public String gettimen() {
        return mTime;
    }

    public String getdate() {
        return mDate;
    }

    public String getweekday() {
        return mWeekday;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}