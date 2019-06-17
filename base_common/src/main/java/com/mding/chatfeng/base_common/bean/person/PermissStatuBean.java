package com.mding.chatfeng.base_common.bean.person;

/**
 * wdh
 * 查询个人状态接口
 */
public class PermissStatuBean {


    /**
     * code : 200
     * msg : 成功
     * method : getPermissStatu
     * record : {"isShare":"0","isMsgRemind":"0","isVideoRemind":"1","isVoiceRemind":"0","isQrcodeShow":"0","isSnoShow":"0"}
     * api_key : 20180903
     * sign : BE04562FE706642B26CB1300C3D4DFFB
     * timestamp : 1538986180
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
         * isShare : 0
         * isMsgRemind : 0
         * isVideoRemind : 1
         * isVoiceRemind : 0
         * isQrcodeShow : 0
         * isSnoShow : 0
         */

        private String isShare;
        private String isMsgRemind;
        private String isVideoRemind;
        private String isVoiceRemind;
        private String isQrcodeShow;
        private String isSnoShow;

        public String getIsShare() {
            return isShare;
        }

        public void setIsShare(String isShare) {
            this.isShare = isShare;
        }

        public String getIsMsgRemind() {
            return isMsgRemind;
        }

        public void setIsMsgRemind(String isMsgRemind) {
            this.isMsgRemind = isMsgRemind;
        }

        public String getIsVideoRemind() {
            return isVideoRemind;
        }

        public void setIsVideoRemind(String isVideoRemind) {
            this.isVideoRemind = isVideoRemind;
        }

        public String getIsVoiceRemind() {
            return isVoiceRemind;
        }

        public void setIsVoiceRemind(String isVoiceRemind) {
            this.isVoiceRemind = isVoiceRemind;
        }

        public String getIsQrcodeShow() {
            return isQrcodeShow;
        }

        public void setIsQrcodeShow(String isQrcodeShow) {
            this.isQrcodeShow = isQrcodeShow;
        }

        public String getIsSnoShow() {
            return isSnoShow;
        }

        public void setIsSnoShow(String isSnoShow) {
            this.isSnoShow = isSnoShow;
        }
    }
}
