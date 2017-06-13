package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import hippo.app.android.models.Friend;


public class FriendViewHolder extends RecyclerView.ViewHolder {

    public TextView surnameView;
    public TextView givennameView;



    public FriendViewHolder(View itemView) {
        super(itemView);

        surnameView = (TextView) itemView.findViewById(R.id.friend_surname);
        givennameView = (TextView) itemView.findViewById(R.id.friend_givenname);


    }

}