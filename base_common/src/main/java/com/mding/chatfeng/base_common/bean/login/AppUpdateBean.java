package com.mding.chatfeng.base_common.bean.login;


import com.mding.chatfeng.base_common.bean.BaseBean;

/**
 * 版本检测
 * wdh
 */
public class AppUpdateBean extends BaseBean {

    /**
     * record : {"device":"android","force":"1","newVsCode":"1","nowVsCode":"1","time":"2018-01-20","update":"1","url":"","content":"更新更新","vsName":"1.0.0"}
     * api_key : 20180903
     * sign : 1FA4CF78E373F84D74CB93E1968F663A
     * timestamp : 1548382851
     * only : 1
     */

    private RecordBean record;
    private String api_key;
    private String sign;
    private int timestamp;
    private int only;

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

    public int getOnly() {
        return only;
    }

    public void setOnly(int only) {
        this.only = only;
    }

    public static class RecordBean {
        /**
         * device : android
         * force : 1
         * newVsCode : 1
         * nowVsCode : 1
         * time : 2018-01-20
         * update : 1
         * url :
         * content : 更新更新
         * vsName : 1.0.0
         */

        private String device;
        private String force;
        private String newVsCode;
        private String nowVsCode;
        private String time;
        private String update;
        private String url;
        private String content;
        private String vsName;

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getForce() {
            return force;
        }

        public void setForce(String force) {
            this.force = force;
        }

        public String getNewVsCode() {
            return newVsCode;
        }

        public void setNewVsCode(String newVsCode) {
            this.newVsCode = newVsCode;
        }

        public String getNowVsCode() {
            return nowVsCode;
        }

        public void setNowVsCode(String nowVsCode) {
            this.nowVsCode = nowVsCode;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVsName() {
            return vsName;
        }

        public void setVsName(String vsName) {
            this.vsName = vsName;
        }
    }
}
