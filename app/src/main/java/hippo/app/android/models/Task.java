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
    public int poolingmin;
    public int participants;
    public boolean lightson;
    public boolean authorinout;
    public String date;
    public String weekday;
    public String time;
    public String location;
    public int starCount = 0;

    public Map<String, Boolean> stars = new HashMap<>();

    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String uid, String author, String category,String description, int poolingmin, int participants, boolean lightson, boolean authorinout, String date, String weekday, String time, String location) {
        this.uid = uid;
        this.author = author;
        this.category = category;
        this.description = description;
        this.poolingmin = poolingmin;
        this.participants = participants;
        this.lightson = lightson;
        this.authorinout = authorinout;
        this.date = date;
        this.weekday = weekday;
        this.time =  time;
        this.location = location;

    }

    // [START task_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("description", description);
        result.put("poolingmin", poolingmin);
        result.put("participants", participants);
        result.put("lightson", lightson);
        result.put("authorinout", authorinout);
        result.put("date", date);
        result.put("weekday", weekday);
        result.put("time", time);
        result.put("location", location);
        result.put("stars", stars);
        result.put("starCount", starCount);

        return result;
    }
    // [END task_to_map]

}
// [END task_class]