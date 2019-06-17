package com.mding.chatfeng.home.about_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;

import org.greenrobot.eventbus.EventBus;


public class NetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
//            boolean isConnected = NetUtils.isWifi(context);
            boolean isConnected = HelpUtils.isNetworkConnected(context);
//
//  System.out.println("网络状态：" + isConnected);
//            System.out.println("wifi状态：" + NetUtils.isWifiConnected(context));
//            System.out.println("移动网络状态：" + NetUtils.isMobileConnected(context));
//            System.out.println("网络连接类型：" + NetUtils.getConnectedType(context));
            if (isConnected) {
//                Toast.makeText(context, "已经连接网络", Toast.LENGTH_LONG).show();
                EventBus.getDefault().post(new NetEvent(true));
//                boolean ping = HelpUtils.ping();
//                if (ping)
//                {
//                    EventBus.getDefault().post(new NetEvent(true));
//                }else
//                {
//                    EventBus.getDefault().post(new NetEvent(false));
//                }
            } else {
                EventBus.getDefault().post(new NetEvent(false));
//                Toast.makeText(context, "已经断开网络", Toast.LENGTH_LONG).show();
            }
        }
    }
}
