package com.mding.chatfeng.base_common.request.base;

import com.mding.chatfeng.base_common.AppConfig;

import java.util.HashMap;

/**
 * wdh
 *  请求接口的头部键名设置
 */
public class RequestCtrlBase extends AppConfig {
    private HashMap<String, Object> map = new HashMap<String, Object>();
    protected HashMap<String, Object> maps;

    /**
     * 初始化请求接口头部，定义版本号等
     * Socket
     * @return
     */
    protected HashMap<String, Object>  initCtn(){
        map.clear();
        return map;
    }

    /**
     * 设置控制器名
     * Socket
     * @return
     */
    protected HashMap<String, Object> setCtns(String ctnName){
        HashMap<String, Object> map = initCtn();
        map.put(ctnKey(),ctnName);
        return  map;
    }
    /**
     * 设置控制器中的方法名
     * Socket
     * @return
     */
    protected void putMtns(String mtnName){
        if(maps!=null)
            maps.put(mtnKey(),mtnName);

    }

    /**
     * API中的请求数据体
     * Socket
     * @return
     */
    protected void putDatas(Object obj){
        if(maps!=null)
            maps.put(dataKey(),obj);
    }




}
