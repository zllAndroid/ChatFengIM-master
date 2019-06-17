package com.mding.chatfeng.base_common.bean.login;

import com.mding.chatfeng.base_common.bean.BaseBean;

public class GetClusterIp extends BaseBean {

    /**
     * record : {"swooleServer":"www.xm6leefun.cn","httpProtocolIp":"http://www.xm6leefun.cn:5052","wsProtocolIp_1":"ws://www.xm6leefun.cn:5056","skProtocolIp_1":"www.xm6leefun.cn:5055","skProtocolIp_2":"www.xm6leefun.cn:9091"}
     * push_type : 1
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
         * swooleServer : www.xm6leefun.cn
         * httpProtocolIp : http://www.xm6leefun.cn:5052
         * wsProtocolIp_1 : ws://www.xm6leefun.cn:5056
         * skProtocolIp_1 : www.xm6leefun.cn:5055
         * skProtocolIp_2 : www.xm6leefun.cn:9091
         */

        private String swooleServer;
        private String httpProtocolIp;
        private String wsProtocolIp_1;
        private String skProtocolIp_1;
        private String skProtocolIp_2;

        public String getSwooleServer() {
            return swooleServer;
        }

        public void setSwooleServer(String swooleServer) {
            this.swooleServer = swooleServer;
        }

        public String getHttpProtocolIp() {
            return httpProtocolIp;
        }

        public void setHttpProtocolIp(String httpProtocolIp) {
            this.httpProtocolIp = httpProtocolIp;
        }

        public String getWsProtocolIp_1() {
            return wsProtocolIp_1;
        }

        public void setWsProtocolIp_1(String wsProtocolIp_1) {
            this.wsProtocolIp_1 = wsProtocolIp_1;
        }

        public String getSkProtocolIp_1() {
            return skProtocolIp_1;
        }

        public void setSkProtocolIp_1(String skProtocolIp_1) {
            this.skProtocolIp_1 = skProtocolIp_1;
        }

        public String getSkProtocolIp_2() {
            return skProtocolIp_2;
        }

        public void setSkProtocolIp_2(String skProtocolIp_2) {
            this.skProtocolIp_2 = skProtocolIp_2;
        }
    }
}
