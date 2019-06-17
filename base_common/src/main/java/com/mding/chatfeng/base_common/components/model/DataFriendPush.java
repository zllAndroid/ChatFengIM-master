package com.mding.chatfeng.base_common.components.model;

import java.util.List;

public class DataFriendPush {


    /**
     * code : 200
     * msg : 成功
     * method : addFriendPush
     * record : {"messageList":[{"userId":"49a4-9b2-68","friendUserId":"2644b163-b898-ba84-a7be-69cb62f214c7","remark":"dasd","nickName":"小强子","headImg":"http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png"}]}
     * api_key : 20180903
     * sign : FDEAFBDD6BB5C8D0F3787CC0285C143A
     * timestamp : 1542339695
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
        private List<MessageListBean> messageList;

        public List<MessageListBean> getMessageList() {
            return messageList;
        }

        public void setMessageList(List<MessageListBean> messageList) {
            this.messageList = messageList;
        }

        public static class MessageListBean {
            /**
             * userId : 49a4-9b2-68
             * friendUserId : 2644b163-b898-ba84-a7be-69cb62f214c7
             * remark : dasd
             * nickName : 小强子
             * headImg : http://doubleq.oss-cn-beijing.aliyuncs.com/logo/154113866221225.png
             */

            private String userId;
            private String friendUserId;
            private String remark;
            private String nickName;
            private String headImg;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getFriendUserId() {
                return friendUserId;
            }

            public void setFriendUserId(String friendUserId) {
                this.friendUserId = friendUserId;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }
        }
    }
}
