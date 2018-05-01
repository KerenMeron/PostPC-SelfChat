package com.example.android.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements ChatAdaptor.OnClickListener {

    private ArrayList<MessageItem> messages;
    private ChatAdaptor adaptor;
    private RecyclerView myRecyclerView;
    MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // recycler view defined in activity layout
        myRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // changes in content do not affect view size
        // this line improves performance
        myRecyclerView.setHasFixedSize(true);

        // initialize messages list
        messages = new ArrayList<MessageItem>();

        // create and attach adaptor
        adaptor = new ChatAdaptor(messages, this);
        myRecyclerView.setAdapter(adaptor);

        // set the layout manager to arrange the items
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    /**
     * Handle sending messages in chat, process and display in messages.
     */
    public void sendMessage(View view){
        EditText editText = (EditText) findViewById(R.id.edit_msg);
        String text = editText.getText().toString();

        // add a new message and notify adaptor
        int position = messages.size();
        messages.add(position, new MessageItem(text));
        adaptor.notifyItemInserted(position);

        // scroll to last item added
        myRecyclerView.scrollToPosition(position);

        // clear edit text area for next message
        editText.setText("");
    }

    /**
     * Make keyboard disappear when user clicks on screen
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        View v = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(event);

        if (v instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            w.getLocationOnScreen(scrcoords);
            float x = event.getRawX() + w.getLeft() - scrcoords[0];
            float y = event.getRawY() + w.getTop() - scrcoords[1];

            if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom()) ) {

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
            }
        }
        return ret;
    }

    @Override
    public void onClick(MessageItem message){
        System.out.println("ChatActivity:: onClick");
        Toast.makeText(getBaseContext(), " On click func from ChatActivity", Toast.LENGTH_SHORT).show();
        messageFragment = new MessageFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_chat_frame, messageFragment).commit();
    }

    public void removeMessage(View view){
        if (messageFragment != null){
            messageFragment.removeMessage();
        }
    }

    public void shareMessage(View view){
        if (messageFragment != null){
            messageFragment.shareMessage();
        }
    }
}
