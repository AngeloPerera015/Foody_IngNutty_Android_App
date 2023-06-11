package com.f_in.foodyingnutty.Models;

import androidx.annotation.NonNull;
//this is to create the user messages and return to the message adapter
public class Message {
    //declare variables
    String message;
    String name;
    String key;
    public Message() {}
    public Message(String message, String name) {
        this.message = message;
        this.name = name;
        this.key = key;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    //return the user message, name, and a key
    @NonNull
    @Override
    public String toString() {
        return "Message{"+
                "message-'"+message + '\''+
                ", name-'" + name + '\'' +
                ", key-'" + key + '\'' +
                '}';
    }
}