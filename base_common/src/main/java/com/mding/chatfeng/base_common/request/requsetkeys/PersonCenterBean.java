package com.mding.chatfeng.base_common.request.requsetkeys;

import com.google.gson.annotations.SerializedName;

public class PersonCenterBean {
    /**
     * controllers_name : PersonCenter
     * personalCenter : {"method_name":"personalCenter","method_type":"2"}
     * upNickName : {"method_name":"upNickName","method_type":"2"}
     * upHeadImg : {"method_name":"upHeadImg","method_type":"2"}
     * upUserSno : {"method_name":"upUserSno","method_type":"2"}
     * upPersonSign : {"method_name":"upPersonSign","method_type":"2"}
     * upPassWord : {"method_name":"upPassWord","method_type":"2"}
     * upPassWordSms : {"method_name":"upPassWordSms","method_type":"2"}
     * getPermissStatu : {"method_name":"getPermissStatu","method_type":"2"}
     * permissionSet : {"method_name":"permissionSet","method_type":"2"}
     * blackList : {"method_name":"blackList","method_type":"2"}
     * removeBlack : {"method_name":"removeBlack","method_type":"2"}
     * replaceMobileOld : {"method_name":"replaceMobileOld","method_type":"2"}
     * replaceMobileNew : {"method_name":"replaceMobileNew","method_type":"2"}
     * bindUid : {"method_name":"bindUid","method_type":"2"}
     * coroutineUid : {"method_name":"coroutineUid","method_type":"2"}
     * updateFriendSend : {"method_name":"updateFriendSend","method_type":"2"}
     * kickUid : {"method_name":"kickUid","method_type":"2"}
     * heart : {"method_name":"heart","method_type":"2"}
     */

    @SerializedName("controllers_name")
    public String controllersName;
    @SerializedName("personalCenter")
    public SearchInfoBean personalCenter;
    @SerializedName("upNickName")
    public SearchInfoBean upNickName;
    @SerializedName("upHeadImg")
    public SearchInfoBean upHeadImg;
    @SerializedName("upUserSno")
    public SearchInfoBean upUserSno;
    @SerializedName("upPersonSign")
    public SearchInfoBean upPersonSign;
    @SerializedName("upPassWord")
    public SearchInfoBean upPassWord;
    @SerializedName("upPassWordSms")
    public SearchInfoBean upPassWordSms;
    @SerializedName("getPermissStatu")
    public SearchInfoBean getPermissStatu;
    @SerializedName("permissionSet")
    public SearchInfoBean permissionSet;
    @SerializedName("blackList")
    public SearchInfoBean blackList;
    @SerializedName("removeBlack")
    public SearchInfoBean removeBlack;
    @SerializedName("replaceMobileOld")
    public SearchInfoBean replaceMobileOld;
    @SerializedName("replaceMobileNew")
    public SearchInfoBean replaceMobileNew;
    @SerializedName("bindUid")
    public SearchInfoBean bindUid;
    @SerializedName("coroutineUid")
    public SearchInfoBean coroutineUid;
    @SerializedName("updateFriendSend")
    public SearchInfoBean updateFriendSend;
    @SerializedName("kickUid")
    public SearchInfoBean kickUid;
    @SerializedName("heart")
    public SearchInfoBean heart;
}
