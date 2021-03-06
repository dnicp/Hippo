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
    public String pooling;
    public int year,date,yearofmonth;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String uid, String author, String description, String location, String pooling) {
        this.uid = uid;
        this.author = author;
        this.description = description;
        this.location = location;
        this.pooling = pooling;
    }

    // [START task_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("description", description);
        result.put("starCount", starCount);
        result.put("stars", stars);
        result.put("location", location);
        result.put("pooling", pooling);


        return result;
    }
    // [END task_to_map]

}
// [END task_class]