package com.mding.chatfeng.home.about_broadcastreceiver;

public class MessageEvent {
    private String message;

    public MessageEvent(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
