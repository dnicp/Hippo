package hippo.app.android;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hippo.app.android.models.Friend;


public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView surnameView;
    public TextView givennameView;
    public ImageView greentickView;





    public UserViewHolder(View itemView) {
        super(itemView);

        surnameView = (TextView) itemView.findViewById(R.id.friend_surname);
        givennameView = (TextView) itemView.findViewById(R.id.friend_givenname);
        greentickView = (ImageView) itemView.findViewById(R.id.green_tick);



    }

    public void bindToTask(Friend friend, View.OnClickListener inviteClickListener) {
        surnameView.setText(friend.surname);
        givennameView.setText(friend.givenname);


        surnameView.setOnClickListener(inviteClickListener);
        givennameView.setOnClickListener(inviteClickListener);

    }

}