package com.mding.chatfeng.base_common.components.base;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCUtil;
import com.mding.chatfeng.base_common.components.common.IC_Common;
import com.mding.chatfeng.base_common.components.common.Vcommon;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.request.base.RequestCtrlBase;
import com.mding.chatfeng.base_common.request.requsetkeys.ContactBean;
import com.mding.chatfeng.base_common.request.requsetkeys.KeysRoot;
import com.mding.chatfeng.base_common.request.requsetkeys.LoginBean;
import com.mding.chatfeng.base_common.request.requsetkeys.MyAMQPTaskBean;
import com.mding.chatfeng.base_common.request.requsetkeys.PersonCenterBean;
import com.mding.chatfeng.base_common.request.requsetkeys.SearchInfoBean;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseUtils extends RequestCtrlBase {

    private ArrayList<Object> beanList=new ArrayList<>();

    protected KeysRoot mKeys= create.mKeys;

    public PersonCenterBean person(){
        return mKeys.record.PersonCenter;
    }
    public LoginBean login(){
        return mKeys.record.Login;
    }
    public ContactBean contact(){
        return mKeys.record.Contact;
    }
    public MyAMQPTaskBean amqp(){
        return mKeys.record.MyAMQPTask;
    }


    public  CC.Builder bases(String ctnName, SearchInfoBean mtnBean, Object... dataBean){
        return  CC.obtainBuilder(ctnName).setActionName(mtnBean.methodName).addParam(ctnName,mtnBean.methodType).addParam(mtnBean.methodName,setApiCore(ctnName,mtnBean,dataBean)).addParam(ctnName+mtnBean.methodName,ctnName.concat("@")+mtnBean.methodName);
    }


    public  CC.Builder basesCommon(String ctnName, SearchInfoBean mtnBean, Object... dataBean){
        String ICName=IC_Common.class.getSimpleName();
        String IAName=Vcommon.class.getSimpleName();
        return  CC.obtainBuilder(ICName).setActionName(IAName).addParam(ICName,mtnBean.methodType).addParam(IAName,setApiCore(ctnName,mtnBean,dataBean)).addParam(ICName+IAName,ctnName.concat("@")+mtnBean.methodName);
    }

    protected String setApiCore(String ctnName, SearchInfoBean mtnBean, Object... dataBean){
        //最终的请求数据格式
        String requestResult="";

        //根据协议类型，包装不同的数据请求格式
        switch (mtnBean.methodType){
            case "1":
                // 1: http  get/post
                requestResult= makeShortLink(ctnName,mtnBean,dataBean);
                break;
            case "2":
            case "3":
                // 2:ws  3:sock

                requestResult= makeLongLink(ctnName,mtnBean,dataBean);
                break;
            default:break;
        }
//        AppConfig.logs("====================="+ CCUtil.convertToJson(maps).toString());
        return requestResult;
    }

    protected String makeShortLink(String ctnName,SearchInfoBean mtnBean,Object... dataBean){

//        request = baseUrl.concat("/").concat(ctnName).concat("/").concat(mtnBean.methodName).concat("@");

/*        maps =initCtn();
        Object[] dataBeans=dataBean;
        //设置数据体
        if(dataBeans!=null)
        {
//            maps.put(getVersion(),version);
            beanList.clear();
            for(Object obj:dataBean)
                beanList.add(obj);
            putDatas(beanList);
        }

        //设置post数据键名
        final HashMap<String,Object> urlDataMap=new HashMap<>();
        urlDataMap.put(postData(),maps);

        return CCUtil.convertToJson(urlDataMap).toString();*/
        //设置头部、控制器和方法
        maps =setCtns(ctnName);
        putMtns(mtnBean.methodName);

        Object[] dataBeans=dataBean;
        //设置数据体
        if(dataBeans!=null)
        {
            maps.put(getVersion(),version);
            beanList.clear();
            for(Object obj:dataBean)
                beanList.add(obj);
            putDatas(beanList);
        }
        //设置post数据键名
  /*      final HashMap<String,Object> urlDataMap=new HashMap<>();
        urlDataMap.put(postData(),maps);*/
        return CCUtil.convertToJson(maps).toString();
    }


    /**
     * 设置长链接的数据请求格式
     * @param ctnName
     * @param mtnBean
     * @param dataBean
     * @return
     */
    protected String makeLongLink(String ctnName,SearchInfoBean mtnBean,Object... dataBean){
        //设置头部、控制器和方法
        maps =setCtns(ctnName);
        putMtns(mtnBean.methodName);

        Object[] dataBeans=dataBean;
        //设置数据体
        if(dataBeans!=null)
        {
            maps.put(getVersion(),version);
            beanList.clear();
            for(Object obj:dataBean)
                beanList.add(obj);
            putDatas(beanList);
        }
        return CCUtil.convertToJson(maps).toString();
    }


}
