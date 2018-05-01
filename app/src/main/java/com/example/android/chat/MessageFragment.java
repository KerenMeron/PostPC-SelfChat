package com.example.android.chat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MessageFragment extends Fragment {

    MessageRemovedListener listener;
    MessageItem messageItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        View layout = view.findViewById(R.id.fragment_message_layout);
        TextView textView = (TextView) view.findViewById(R.id.fragment_message_text);
        layout.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_green_light));
        textView.setText("I am a fragment");
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof MessageRemovedListener){
            this.listener = (MessageRemovedListener) context;
        }
    }

    public static MessageFragment newInstance(MessageItem message) {

        Bundle args = new Bundle();
        args.putString("message newInstance", message.toString());
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void removeMessage(){
        if (this.listener != null && messageItem != null){
            this.listener.onMessageRemoved(messageItem);
        }
    }

    public void shareMessage() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, messageItem.getMessage());
        startActivity(shareIntent);
    }


    public interface MessageRemovedListener{
        void onMessageRemoved(MessageItem message);
    }
}
