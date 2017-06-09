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


public class TestTastDetailActivity {

    private String mTime;
    private String mDate;
    private String mWeekday;
    private int mCategoryImageSourceId;
    private int mOnOffImageSourceId;
    private int mInOutImageSourceId;
    private int mPoolingImagesourceId;
    private int mLocationImageSourceId;
    private String mLocation;

    public TestTastDetailActivity(String time, String date,String weekday, int categoryImageSourceId, int onOffImageSourceId, int inOutImageSourceId, int poolingImageSourceId, int locationImageSourceId, String location) {
        mTime = time;
        mDate = date;
        mWeekday = weekday;
        mCategoryImageSourceId = categoryImageSourceId;
        mOnOffImageSourceId = onOffImageSourceId;
        mInOutImageSourceId = inOutImageSourceId;
        mPoolingImagesourceId = poolingImageSourceId;
        mLocationImageSourceId = locationImageSourceId;
        mLocation = location;
    }


    /**
     * Get the default translation of the word.
     */
    public String getmTimen() {
        return mTime;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmWeekday() {
        return mWeekday;
    }

    public int getCategoryImageSourceId() {
        return mCategoryImageSourceId;
    }

    public int getmOnOffImageSourceId() {
        return mOnOffImageSourceId;
    }

    public int getmInOutImageSourceId() {
        return mInOutImageSourceId;
    }

    public int getmPoolingImagesourceId() {
        return mPoolingImagesourceId;
    }

    public int getmLocationImageSourceId() {
        return mLocationImageSourceId;
    }

    public String getmLocation() {
        return mLocation;
    }




}