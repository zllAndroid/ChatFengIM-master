package com.mding.chatfeng.base_common.request.requsetkeys;

import com.google.gson.annotations.SerializedName;

public class LoginBean {
    /**
     * controllers_name : Login
     * register : {"method_name":"register","method_type":"1"}
     * groupSend : {"method_name":"groupSend","method_type":"2"}
     * groupReceive : {"method_name":"groupReceive","method_type":"2"}
     * loginIn : {"method_name":"loginIn","method_type":"1"}
     * sendForceLogin : {"method_name":"sendForceLogin","method_type":"2"}
     * setHeadImg : {"method_name":"setHeadImg","method_type":"1"}
     * smsLogin : {"method_name":"smsLogin","method_type":"1"}
     * getSmsCode : {"method_name":"getSmsCode","method_type":"1"}
     * appUpdate : {"method_name":"appUpdate","method_type":"1"}
     * addrPort : {"method_name":"addrPort","method_type":"1"}
     * getClusterIp : {"method_name":"getClusterIp","method_type":"1"}
     * contactsList : {"method_name":"contactsList","method_type":"1"}
     * routeList : {"method_name":"routeList","method_type":"1"}
     * routeListTo : {"method_name":"routeListTo","method_type":"1"}
     */

    @SerializedName("controllers_name")
    public String controllersName;
    @SerializedName("register")
    public SearchInfoBean register;
    @SerializedName("groupSend")
    public SearchInfoBean groupSend;
    @SerializedName("groupReceive")
    public SearchInfoBean groupReceive;
    @SerializedName("loginIn")
    public SearchInfoBean loginIn;
    @SerializedName("sendForceLogin")
    public SearchInfoBean sendForceLogin;
    @SerializedName("setHeadImg")
    public SearchInfoBean setHeadImg;
    @SerializedName("smsLogin")
    public SearchInfoBean smsLogin;
    @SerializedName("getSmsCode")
    public SearchInfoBean getSmsCode;
    @SerializedName("appUpdate")
    public SearchInfoBean appUpdate;
    @SerializedName("addrPort")
    public SearchInfoBean addrPort;
    @SerializedName("getClusterIp")
    public SearchInfoBean getClusterIp;
    @SerializedName("contactsList")
    public SearchInfoBean contactsList;
    @SerializedName("routeList")
    public SearchInfoBean routeList;
    @SerializedName("routeListTo")
    public SearchInfoBean routeListTo;
}
