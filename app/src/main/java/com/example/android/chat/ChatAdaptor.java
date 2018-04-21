package com.example.android.chat;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Adaptor Class between ChatActivity and MessageItems.
 */
public class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.ViewHolder> {

    // Container for messages in chat
    private List<MessageItem> myMessages;

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView msg;
        public TextView timeStamp;
        public TextView name;

        public ViewHolder(View itemView){
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.msg);
            timeStamp = (TextView) itemView.findViewById(R.id.time_stamp);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    // Constructor
    public ChatAdaptor(List<MessageItem> msgList){
        this.myMessages = msgList;
    }

    @Override
    public ChatAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatAdaptor.ViewHolder viewHolder, int position){

        // extract message item based on position
        MessageItem msgItem = this.myMessages.get(position);

        // set attributes to the view holder according to the MessageItem
        TextView msgView = viewHolder.msg;
        msgView.setText(msgItem.getMessage());
        TextView timeStampView = viewHolder.timeStamp;
        timeStampView.setText(msgItem.getTimeStamp());
        TextView nameView = viewHolder.name;
        nameView.setText(msgItem.getName());
    }

    // Returns the total amount of items in messages list
    @Override
    public int getItemCount(){
        return this.myMessages.size();
    }

}
