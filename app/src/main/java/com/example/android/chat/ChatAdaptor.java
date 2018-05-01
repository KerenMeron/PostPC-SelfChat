package com.example.android.chat;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Adaptor Class between ChatActivity and MessageItems.
 */
public class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.ViewHolder> {

    private List<MessageItem> myMessages;
    private ChatAdaptor.OnClickListener listener;
    private Context context;

    // Constructor
    public ChatAdaptor(List<MessageItem> msgList, OnClickListener listener){
        this.myMessages = msgList;
        this.listener = listener;
    }


    @Override
    public ChatAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.setOnClickListener(listener);
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

    /**
     * Remove given message from messages list.
     * @param message
     */
    public void removeItem(MessageItem message){
        for (int i = 0; i < myMessages.size(); i++){
            if (message.equals(myMessages.get(i))){
                myMessages.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    // Returns the total amount of items in messages list
    @Override
    public int getItemCount(){
        return this.myMessages.size();
    }


    // ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView msg;
        public TextView timeStamp;
        public TextView name;
        OnClickListener listener;

        public ViewHolder(View itemView){
            super(itemView);
            msg = (TextView) itemView.findViewById(R.id.msg);
            timeStamp = (TextView) itemView.findViewById(R.id.time_stamp);
            name = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view){
                    System.out.println("ViewHolder:: onLongClick");
                    System.out.println(ChatAdaptor.this.listener);
                    if (ChatAdaptor.this.listener != null) {
                        ChatAdaptor.this.listener.onClick(myMessages.get(getAdapterPosition()));
                        return true;
                    }
                    return false;
                }
            });
        }
        void setOnClickListener(OnClickListener listener){
            System.out.println("ViewHolder:: setOnClickListener");
            System.out.println(listener);
            this.listener = listener;
        }
    }

    interface OnClickListener {
        void onClick(MessageItem message);
    }

}

