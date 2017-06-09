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
package hippo.app.android.models;


public class DashboardTaskDetail {

    private String mTime;
    private String mDate;
    private String mWeekday;
    private int mCategoryImageSourceId;
    private int mOnOffImageSourceId;
    private int mInOutImageSourceId;
    private String mPooling;
    private String mLocation;

    public DashboardTaskDetail(String time, String date, String weekday, int categoryImageSourceId, int onOffImageSourceId, int inOutImageSourceId, String pooling, String location) {
        mTime = time;
        mDate = date;
        mWeekday = weekday;
        mCategoryImageSourceId = categoryImageSourceId;
        mOnOffImageSourceId = onOffImageSourceId;
        mInOutImageSourceId = inOutImageSourceId;
        mPooling = pooling;
        mLocation = location;
    }


    /**
     * Get the default translation of the word.
     */
    public String getmTime() {
        return mTime;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmWeekday() {
        return mWeekday;
    }

    public int getmCategoryImageSourceId() {
        return mCategoryImageSourceId;
    }

    public int getmOnOffImageSourceId() {
        return mOnOffImageSourceId;
    }

    public int getmInOutImageSourceId() {
        return mInOutImageSourceId;
    }

    public String getmPooling() {
        return mPooling;
    }

    public String getmLocation() {
        return mLocation;
    }




}