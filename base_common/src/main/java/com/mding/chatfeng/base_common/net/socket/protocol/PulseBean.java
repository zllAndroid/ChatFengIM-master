package com.mding.chatfeng.base_common.net.socket.protocol;


import android.util.Log;

import com.mding.chatfeng.base_common.request.create;
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class PulseBean implements IPulseSendable {
    private String str = "";

    public PulseBean() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ctn", "PersonCenter");
            jsonObject.put("mtn", "heart");
            str = create.getComsApi().heart();
//            Log.d("xf心跳",str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] parse() {
        byte[] body = str.getBytes(Charset.defaultCharset());
        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(4+body.length);
        bb.put(body);
        return bb.array();
    }
}