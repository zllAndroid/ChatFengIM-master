package com.mding.chatfeng.base_common.net.socket.protocol;

import android.util.Log;

import com.mding.chatfeng.base_common.request.body.UserBean;
import com.mding.chatfeng.base_common.request.create;

/**
 * Created by xuhao on 2017/5/22.
 */

public class HandShakeBean extends ProtocolSend {
String userId,token;
    public HandShakeBean() {
        if(false)
        {
            //A用户
            userId="f8da-5b32-3a5";
            token="B04B071F-29F1-C16B-5F48-32DFF2EC9041";
        }else
        {
            userId="5d58-6c6e-dec";
            token="7F5B9108-F23F-9A20-9C12-91993947C76D";
        }

/*        com.alibaba.fastjson.JSONObject params = new com.alibaba.fastjson.JSONObject();

        com.alibaba.fastjson.JSONObject parameters = new com.alibaba.fastjson.JSONObject();
        com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();*/
        try {

    /*        parameters.put("userId",userId);
            parameters.put("token",token);
            objects.add(parameters);

            params.put("ctn", "PersonCenter");
            params.put("mtn", "bindUid");
            params.put("version", "3.0.0");
            params.put("data", objects);*/

            content = create.getComsApi().doPersonMtnStr(create.person().bindUid,new UserBean("5d58-6c6e-dec","7F5B9108-F23F-9A20-9C12-91993947C76D"));
//          content = create.getRequestSocket().bindUid(new UserBean("5d58-6c6e-dec","7F5B9108-F23F-9A20-9C12-91993947C76D"));
//            Log.d("xf绑定",content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


