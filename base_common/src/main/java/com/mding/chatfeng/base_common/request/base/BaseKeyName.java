package com.mding.chatfeng.base_common.request.base;

/**
 * API键名
 * wdh
 */
public class BaseKeyName {
    //请求的版本键名
    private final String version="version";
    //请求的控制器键名
    private final String ctn="ctn";
    //请求的方法键名
    private final String mtn="mtn";
    //请求的数据体键名
    private final String data="data";
    //post数据键名
    private final String postData="postData";

    public String getVersion() {
        return version;
    }

    public String dataKey() {
        return data;
    }

    public String mtnKey() {
        return mtn;
    }

    public String ctnKey() {
        return ctn;
    }

    public String postData() {
        return postData;
    }

}
