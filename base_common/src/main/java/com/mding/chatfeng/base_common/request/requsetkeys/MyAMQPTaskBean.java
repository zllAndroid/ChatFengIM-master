package com.mding.chatfeng.base_common.request.requsetkeys;

import com.google.gson.annotations.SerializedName;

public class MyAMQPTaskBean {
    /**
     * controllers_name : MyAMQPTask
     * sendPrivateChat : {"method_name":"sendPrivateChat","method_type":"2"}
     * sendGroupChat : {"method_name":"sendGroupChat","method_type":"2"}
     * pullPrivateChat : {"method_name":"pullPrivateChat","method_type":"2"}
     * pullGroupChat : {"method_name":"pullGroupChat","method_type":"2"}
     * messageConfirmReceipt : {"method_name":"messageConfirmReceipt","method_type":"2"}
     * messageGroupConfirmReceipt : {"method_name":"messageGroupConfirmReceipt","method_type":"2"}
     * pullMergeChat : {"method_name":"pullMergeChat","method_type":"1"}
     * getQueryRepetition : {"method_name":"getQueryRepetition","method_type":"2"}
     */

    @SerializedName("controllers_name")
    public String controllersName;
    @SerializedName("sendPrivateChat")
    public SearchInfoBean sendPrivateChat;
    @SerializedName("sendGroupChat")
    public SearchInfoBean sendGroupChat;
    @SerializedName("pullPrivateChat")
    public SearchInfoBean pullPrivateChat;
    @SerializedName("pullGroupChat")
    public SearchInfoBean pullGroupChat;
    @SerializedName("messageConfirmReceipt")
    public SearchInfoBean messageConfirmReceipt;
    @SerializedName("messageGroupConfirmReceipt")
    public SearchInfoBean messageGroupConfirmReceipt;
    @SerializedName("pullMergeChat")
    public SearchInfoBean pullMergeChat;
    @SerializedName("getQueryRepetition")
    public SearchInfoBean getQueryRepetition;
}
