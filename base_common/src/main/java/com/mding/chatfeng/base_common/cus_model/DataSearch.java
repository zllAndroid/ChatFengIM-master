package com.mding.chatfeng.base_common.cus_model;

import java.io.Serializable;

public class DataSearch implements Serializable{
    private String id;//friendsid
    private String sno;
    private String name;
    private String headImg;
//    好友还是群组类型
    private String type;
    private String qrcode;
    private String isRelation;
    private String sign;
    private String personaSignature;

    public String getPersonaSignature() {
        return personaSignature;
    }

    public void setPersonaSignature(String personaSignature) {
        this.personaSignature = personaSignature;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIsRelation() {
        return isRelation;
    }

    public void setIsRelation(String isRelation) {
        this.isRelation = isRelation;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
