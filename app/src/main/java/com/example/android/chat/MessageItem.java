package com.example.android.chat;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/***
 * MessageItem class represents a message sent in Chat.
 * Contains the text message and time it was sent.
 */
public class MessageItem {

    private String message;
    private Calendar calendar;
    private String name;

    public MessageItem(String msg){
        name = "Anonymous:";
        message = msg;
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public String getTimeStamp(){
        calendar.setTime(new Date());
        String hours = Integer.toString(calendar.get(calendar.HOUR_OF_DAY));
        String minutes = Integer.toString(calendar.get(calendar.MINUTE));
        return hours + ":" + minutes;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

}
