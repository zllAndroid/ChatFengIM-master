package com.mding.chatfeng.base_common.bean.person;

import java.util.List;

/**
 * wdh
 * 屏蔽黑名单列表
 */
public class BlackListBean {

    /**
     * code : 200
     * msg : 成功
     * method : blackList
     * record : [{"userId":"2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe","headImg":"http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132","nickName":"海大胖"},{"userId":"cb16ce45-854c-f553-9c9a-2e57a6addca3","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/153820048090418.jpg","nickName":"kkk"}]
     * api_key : 20180903
     * sign : E4BE8682A5DB63D49FC08498499BB135
     * timestamp : 1538987950
     */

    private int code;
    private String msg;
    private String method;
    private String api_key;
    private String sign;
    private int timestamp;
    private List<RecordBean> record;

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

    public List<RecordBean> getRecord() {
        return record;
    }

    public void setRecord(List<RecordBean> record) {
        this.record = record;
    }

    public static class RecordBean {
        /**
         * userId : 2f9c14e8-ba94-8a7e-9cc9-4260e14f8ffe
         * headImg : http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLBTpm03G0z7G0uxs2P66uAtVFjQV7vM8OqeOZsBe1qnsED2cmpf5UF7jGPIVkOV5Q2EcCDBhV7ib9w/132
         * nickName : 海大胖
         */

        private String userId;
        private String headImg;
        private String nickName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

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
