package com.mding.chatfeng.base_common.net.socket;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.net.socket.protocol.HandShakeBean;
import com.mding.chatfeng.base_common.net.socket.protocol.ProtocolReceive;
import com.mding.chatfeng.base_common.net.socket.protocol.PulseBean;
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.socket.client.impl.client.action.ActionDispatcher;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.client.sdk.client.connection.NoneReconnect;

import java.nio.charset.Charset;
import java.util.Arrays;

public abstract class BaseSocket {
    public abstract void connectSuccess(String result);
    public abstract void succeesBack(String result);
    private ConnectionInfo mInfo;
    protected IConnectionManager mManager;
    private SocketActionAdapter adapter = new SocketActionAdapter() {

        @Override
        public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
            AppConfig.logs("连接成功(Connecting Successful)");
            mManager.send(new HandShakeBean());
            mManager.getPulseManager().setPulseSendable(new PulseBean());

        }

        @Override
        public void onSocketDisconnection(ConnectionInfo info, String action, Exception e) {
            if (e != null) {
                if (e instanceof RedirectException) {
                    AppConfig.logs("正在重定向连接(Redirect Connecting)...");
                    mManager.switchConnectionInfo(((RedirectException) e).redirectInfo);
                    mManager.connect();

                } else {
                    AppConfig.logs("异常断开(Disconnected with exception):" + e.getMessage());

                }
            } else {
                AppConfig.logs("正常断开(Disconnect Manually)");
            }


        }

        @Override
        public void onSocketConnectionFailed(ConnectionInfo info, String action, Exception e) {
            AppConfig.logs("连接失败(Connecting Failed)");
        }

        @Override
        public void onSocketReadResponse(ConnectionInfo info, String action, OriginalData data) {

            AppConfig.logs("有数据返回");

            String str = new String(data.getBodyBytes(), Charset.forName("utf-8"));
            JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
            //------------------------Test
            succeesBack(jsonObject.toString());
            String mtn = jsonObject.get("mtn").getAsString();

            if (mtn.equals("bindUid")) {
                //绑定成功
                String handshake = jsonObject.get("msg").getAsString();
                AppConfig.logs("握手成功! 握手信息(Handshake Success):" + handshake + ". 开始心跳(Start Heartbeat)..");
                connectSuccess(str);
            } else if (mtn.equals("cdx")) {//切换,重定向.(暂时无法演示,如有疑问请咨询github)
                String ip = jsonObject.get("data").getAsString().split(":")[0];
                int port = Integer.parseInt(jsonObject.get("data").getAsString().split(":")[1]);
                ConnectionInfo redirectInfo = new ConnectionInfo(ip, port);
                redirectInfo.setBackupInfo(mInfo.getBackupInfo());
                mManager.getReconnectionManager().addIgnoreException(RedirectException.class);
                mManager.disconnect(new RedirectException(redirectInfo));
            } else if (mtn.equals("heart")) {//心跳
                AppConfig.logs("收到心跳,喂狗成功(Heartbeat Received,Feed the Dog)");
                mManager.getPulseManager().feed();
            } else if (mtn.equals("receivePrivateChat")) {
                //------------------------
                succeesBack(jsonObject.toString());
                AppConfig.logs("收到一条消息:"+jsonObject.toString());
            } else {
                AppConfig.logs("未收到心跳"+str);
            }
        }

        @Override
        public void onSocketWriteResponse(ConnectionInfo info, String action, ISendable data) {
            byte[] bytes = data.parse();

            bytes = Arrays.copyOfRange(bytes, 4, bytes.length);

            String str = new String(bytes, Charset.forName("utf-8"));

            JsonElement mJsonParserss=  new JsonParser().parse(str);


        /*    if (mJsonParserss.isJsonArray())
            { */
            JsonObject jsonObject = mJsonParserss.getAsJsonObject();

            String mtn = jsonObject.get("mtn").getAsString();
            switch (mtn) {
                case "bindUid": {
                    String handshake = jsonObject.get("mtn").getAsString();
                    AppConfig.logs("发送握手绑定数据成功，启动心跳:" + handshake);
                    mManager.getPulseManager().pulse();
                    break;
                }
                default:
                    AppConfig.logs(str);
            }
         /*   }else {
                logSend(str);
            }*/

        }

        @Override
        public void onPulseSend(ConnectionInfo info, IPulseSendable data) {
            byte[] bytes = data.parse();
            bytes = Arrays.copyOfRange(bytes, 4, bytes.length);
            String str = new String(bytes, Charset.forName("utf-8"));
            JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
            AppConfig.logs("onPulseSend:"+jsonObject.toString());
            String method = jsonObject.get("mtn").getAsString();
            if (method.equals("heart")) {
//                mManager.getPulseManager().trigger();
                AppConfig.logs("发送心跳包(Heartbeat Sending)");
            }
        }
    };



    public BaseSocket(String ip,int port) {
        mInfo = new ConnectionInfo(ip,port);
//        final Handler handler = new Handler(Looper.getMainLooper());
        OkSocketOptions.Builder builder = new ProtocolReceive().ProtocolByReceive();
        builder.setReconnectionManager(new NoneReconnect());
//      builder.setReconnectionManager(OkSocketOptions.getDefault().getReconnectionManager());
/*        builder.setCallbackThreadModeToken(new OkSocketOptions.ThreadModeToken() {
            @Override
            public void handleCallbackEvent(ActionDispatcher.ActionRunnable runnable) {
                handler.post(runnable);
            }
        });*/
        mManager = OkSocket.open(mInfo).option(builder.build());
        //设置心跳频率
        OkSocketOptions okOptions = new OkSocketOptions.Builder(mManager.getOption())
                .setPulseFrequency(Long.parseLong(AppConfig.setPulseFrequency))
                .build();
        mManager.option(okOptions);

        mManager.registerReceiver(adapter);

        mManager.connect();
    }

    public void onDestroy() {
        if (mManager != null) {
            mManager.disconnect();
            mManager.unRegisterReceiver(adapter);
        }
    }
}
