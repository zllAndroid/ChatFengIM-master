package com.mding.chatfeng.base_common.request.body;

public class PrivateChatBean extends UserBean {
    private String friendsId;
    private String message;
    private String messageType;
    private String requestTime;



    public PrivateChatBean(String userId, String token) {
        super(userId, token);
    }


    public String getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(String friendsId) {
        this.friendsId = friendsId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }



}
