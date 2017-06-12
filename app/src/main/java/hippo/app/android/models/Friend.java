package hippo.app.android.models;

/**
 * Created by Daniel on 12/6/17.
 */

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


// [START task_class]
@IgnoreExtraProperties
public class Friend {

    public String uid;
    public String username;
    public String surname;
    public String givenname;


    public Friend() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Friend(String uid, String username, String surname, String givenname) {
        this.uid = uid;
        this.username = username;
        this.surname = surname;
        this.givenname = givenname;
    }
    // [START task_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("username", username);
        result.put("surname", surname);
        result.put("givenname", givenname);

        return result;
    }

}
