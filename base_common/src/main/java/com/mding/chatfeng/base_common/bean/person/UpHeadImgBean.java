package com.mding.chatfeng.base_common.bean.person;

import com.mding.chatfeng.base_common.bean.BaseBean;

/**
 * wdh
 * 修改头像
 */
public class UpHeadImgBean extends BaseBean {

    /**
     * record : {"headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153829787075008.png","modified":"16513515"}
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
         * modified : 16513515
         */

        private String headImg;
        private String modified;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }
    }
}
