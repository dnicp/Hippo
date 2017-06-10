package hippo.app.android.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START task_class]
@IgnoreExtraProperties
public class Task {

    public String uid;
    public String author;
    public String description;
    public String location;
    public String date;
    public String time;
    // to implement
    public String weekday;
    // to implement
    public int starCount;
    public int minPooling;

    public Map<String, Boolean> stars = new HashMap<>();


    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String uid, String author, String description, String location, String date, String time, int minPooling) {
        this.uid = uid;
        this.author = author;
        this.description = description;
        this.location = location;
        this.date = date;
        this.time = time;
        this.minPooling = minPooling;

    }

    // [START task_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("description", description);
        result.put("starCount", starCount);
        result.put("minPooling", minPooling);
        result.put("stars", stars);
        result.put("location", location);
        result.put("date", date);
        result.put("time", time);


        return result;
    }
    // [END task_to_map]

}
// [END task_class]