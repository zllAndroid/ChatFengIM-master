package com.mding.chatfeng.base_common.bean.login;

import com.mding.chatfeng.base_common.bean.BaseBean;

/**
 * 用户登录
 * wdh
 */
public class LoginInBean extends BaseBean {

    /**
     * code : 200
     * msg : 成功
     * method : loginIn
     * record : {"nickName":"杰克","userId":"cfa4-6337-6e4","mobile":"18150960007","wxSno":"8ef25d1f506e97bd456b5c1e4","userToken":"996343CB-1172-CC9B-A4F7-819ACA6C9FFA","isFirstLogin":2,"headImg":"dmCC","qrcode":"1_xm6leefun_cfa4-6337-6e4","personaSignature":"","modified":"1553932319","isUpdate":1,"serverIpWs":"ws://127.0.0.1:5053","upSnoNum":"1","serverIpHttp":"127.0.0.1:5052"}
     */


    private RecordBean record;



    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * nickName : 杰克
         * userId : cfa4-6337-6e4
         * mobile : 18150960007
         * wxSno : 8ef25d1f506e97bd456b5c1e4
         * userToken : 996343CB-1172-CC9B-A4F7-819ACA6C9FFA
         * isFirstLogin : 2
         * headImg : data:image/pnaW2xOVvyiwDAAAAElFTkSuQmCC
         * qrcode : 1_xm6leefun_cfa4-6337-6e4
         * personaSignature :
         * modified : 1553932319
         * isUpdate : 1
         * serverIpWs : ws://127.0.0.1:5053
         * upSnoNum : 1
         * serverIpHttp : 127.0.0.1:5052
         */

        private String nickName;
        private String userId;
        private String mobile;
        private String wxSno;
        private String userToken;
        private int isFirstLogin;
        private String headImg;
        private String qrcode;
        private String personaSignature;
        private String modified;
        private int isUpdate;
        private String serverIpWs;
        private String upSnoNum;
        private String serverIpHttp;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }

        public int getIsFirstLogin() {
            return isFirstLogin;
        }

        public void setIsFirstLogin(int isFirstLogin) {
            this.isFirstLogin = isFirstLogin;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getPersonaSignature() {
            return personaSignature;
        }

        public void setPersonaSignature(String personaSignature) {
            this.personaSignature = personaSignature;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public int getIsUpdate() {
            return isUpdate;
        }

        public void setIsUpdate(int isUpdate) {
            this.isUpdate = isUpdate;
        }

        public String getServerIpWs() {
            return serverIpWs;
        }

        public void setServerIpWs(String serverIpWs) {
            this.serverIpWs = serverIpWs;
        }

        public String getUpSnoNum() {
            return upSnoNum;
        }

        public void setUpSnoNum(String upSnoNum) {
            this.upSnoNum = upSnoNum;
        }

        public String getServerIpHttp() {
            return serverIpHttp;
        }

        public void setServerIpHttp(String serverIpHttp) {
            this.serverIpHttp = serverIpHttp;
        }
    }
}
