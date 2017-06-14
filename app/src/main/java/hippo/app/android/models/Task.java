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
    public String category;
    public String description;
    public String location;
    public String date;
    public String time;
    public int poolingCount;
    public int minPooling;

    public Map<String, Boolean> poolings = new HashMap<>();


    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String uid, String author, String category, String description, String location, String date, String time, int minPooling) {
        this.uid = uid;
        this.author = author;
        this.category = category;
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
        result.put("category", category);
        result.put("description", description);
        result.put("poolingCount", poolingCount);
        result.put("minPooling", minPooling);
        result.put("poolings", poolings);
        result.put("location", location);
        result.put("date", date);
        result.put("time", time);


        return result;
    }
    // [END task_to_map]

}
// [END task_class]