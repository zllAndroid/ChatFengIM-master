package com.mding.chatfeng.base_common.components.model;

public class DataSetHeader {

    /**
     * code : 200
     * msg : 成功
     * method : setHeadImg
     * record : {"headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141056790163.png","nickName":"123"}
     * api_key : 20180903
     * sign : 30CFF2C7B5C4F6F38077E31CBDEC77DA
     * timestamp : 1540274083
     */

    private int code;
    private String msg;
    private String method;
    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class RecordBean {
        /**
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154141056790163.png
         * nickName : 123
         */

        private String headImg;
        private String nickName;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
