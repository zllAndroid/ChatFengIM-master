package com.mding.chatfeng.base_common.bean.person;

import com.mding.chatfeng.base_common.bean.BaseBean;

/**
 * wdh
 * 我的资料
 */
public class PersonalCenterBean extends BaseBean {


    /**
     * record : {"headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153829787075008.png","mobile":"18065283783","nickName":"android","personaSignature":"我是很个性的签名","qrcode":"http://picture.beybow.com/default/user_qrcod/usercode_384e84283becd1fc9adc3366d9e17cd0.png","upSnoNum":"1","wxSno":"b6cb0d083b77b4d0aac1249789080","verificationMD5":"45345hsgsgsgsga353"}
     * api_key : 20180903
     * sign : A2E2F57800074C4B688D0EF3871AEC37
     * timestamp : 1538984929
     */

    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;

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
         * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153829787075008.png
         * mobile : 18065283783
         * nickName : android
         * personaSignature : 我是很个性的签名
         * qrcode : http://picture.beybow.com/default/user_qrcod/usercode_384e84283becd1fc9adc3366d9e17cd0.png
         * upSnoNum : 1
         * wxSno : b6cb0d083b77b4d0aac1249789080
         * verificationMD5 : 45345hsgsgsgsga353
         */

        private String headImg;
        private String mobile;
        private String nickName;
        private String personaSignature;
        private String qrcode;
        private String upSnoNum;
        private String wxSno;
        private String verificationMD5;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPersonaSignature() {
            return personaSignature;
        }

        public void setPersonaSignature(String personaSignature) {
            this.personaSignature = personaSignature;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getUpSnoNum() {
            return upSnoNum;
        }

        public void setUpSnoNum(String upSnoNum) {
            this.upSnoNum = upSnoNum;
        }

        public String getWxSno() {
            return wxSno;
        }

        public void setWxSno(String wxSno) {
            this.wxSno = wxSno;
        }

        public String getVerificationMD5() {
            return verificationMD5;
        }

        public void setVerificationMD5(String verificationMD5) {
            this.verificationMD5 = verificationMD5;
        }
    }
}
