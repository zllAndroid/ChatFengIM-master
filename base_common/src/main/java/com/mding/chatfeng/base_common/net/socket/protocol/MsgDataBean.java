package com.mding.chatfeng.base_common.net.socket.protocol;


/**
 * Created by Tony on 2017/10/24.
 */

public class MsgDataBean extends ProtocolSend {
    String token;
    String userId;
    String friendsId;
    public MsgDataBean(String contents) {
        if(false)
        {
            //A发给B
             token= "B04B071F-29F1-C16B-5F48-32DFF2EC9041";
             userId="f8da-5b32-3a5";
             friendsId="5d58-6c6e-dec";
        }else {
            //B发给A
            userId="5d58-6c6e-dec";
            token="D7CE0F88-4C23-10D1-6BD3-6E677CF95AC7";
             friendsId="f8da-5b32-3a5";
        }


  /*      com.alibaba.fastjson.JSONObject params = new com.alibaba.fastjson.JSONObject();

        com.alibaba.fastjson.JSONObject parameters = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();*/
        try {

       /*     parameters.put("userId", userId);
            parameters.put("friendsId", friendsId);
            parameters.put("message", "4232");
            parameters.put("messageType", "1");
            parameters.put("requestTime", "121312131");
            parameters.put("token", token);
            objects.add(parameters);

            params.put("ctn", "MyAMQPTask");
            params.put("mtn", "sendPrivateChat");
            params.put("version", "3.0.0");
            params.put("data", objects);

            content = params.toJSONString();*/
            content=contents;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}