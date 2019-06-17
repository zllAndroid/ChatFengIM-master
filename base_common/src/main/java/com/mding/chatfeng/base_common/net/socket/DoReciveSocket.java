package com.mding.chatfeng.base_common.net.socket;

import com.mding.chatfeng.base_common.AppConfig;

/**
 * 只负责被动接收消息，如服务端的推送
 * 与UI动态组件互动
 */
public  abstract class DoReciveSocket extends BaseSocket{
    public abstract void succeesBacks(String result);

    @Override
    public void succeesBack(String result) {
        succeesBacks(result);
    }

    public DoReciveSocket() {
        super(AppConfig.skProtocolIp_1.split(":")[0],Integer.valueOf(AppConfig.skProtocolIp_1.split(":")[1]));

    }
}
