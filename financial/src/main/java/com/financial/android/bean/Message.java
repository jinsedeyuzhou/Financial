package com.financial.android.bean;

/**
 * 消息记录
 * Created by wyy on 2016/1/19.
 */
public class Message {

    private String message;
    private long time;

    public Message(String message, long time) {
        this.message = message;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
