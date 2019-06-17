package com.mding.chatfeng.base_common.components;

import com.billy.cc.core.component.CC;
import com.mding.chatfeng.base_common.components.base.BaseUtils;
import com.mding.chatfeng.base_common.request.requsetkeys.SearchInfoBean;

public class ComsApi extends BaseUtils {
    /**
     * 获取集群ip接口
     * @return
     */
    public CC.Builder getClusterIp(){
        String ctnName=login().controllersName;
        SearchInfoBean mtnBean=login().getClusterIp;
        Object[] objs=null;
        return  bases(ctnName,mtnBean,objs);
    }

    /**
     * 心跳包
     * @return
     */
    public String heart(){
        String ctnName=person().controllersName;
        SearchInfoBean mtnBean=person().heart;
        Object[] obj=null;
        return setApiCore(ctnName,mtnBean,obj);
    }


    /**
     * 执行公共控制器方法,与其他不同的是，需要指定控制器名称
     * @param ctnName
     * @param mtnBean
     * @param dataBean
     * @return
     */
    public CC.Builder doCommon(String ctnName, SearchInfoBean mtnBean, Object dataBean){
        return  basesCommon(ctnName,mtnBean,dataBean);
    }
    //只返回请求数据和格式
    public String doCommonStr(String ctnName,SearchInfoBean mtnBean,Object dataBean){
        return setApiCore(ctnName,mtnBean,dataBean);
    }



    /**
     * 执行登入控制器公共方法
     * @param dataBean 请求的参数实体集合
     * @return CC.Builder
     */
    public CC.Builder doLoginMtn(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=login().controllersName;
        return  bases(ctnName,mtnBean,dataBean);
    }
    //只返回请求数据和格式
    public String doLoginMtnStr(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=login().controllersName;
        return setApiCore(ctnName,mtnBean,dataBean);
    }




    /**
     * 执行个人控制器公共方法
     * @param dataBean
     * @return
     */
    public CC.Builder doPersonMtn(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=person().controllersName;
        return  bases(ctnName,mtnBean,dataBean);
    }
    //只返回请求数据和格式
    public String doPersonMtnStr(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=person().controllersName;
        return setApiCore(ctnName,mtnBean,dataBean);
    }




    /**
     * 执行联系人控制器公共方法
     * @param dataBean
     * @return
     */
    public CC.Builder doContactMtn(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=contact().controllersName;
        return  bases(ctnName,mtnBean,dataBean);
    }
    //只返回请求数据和格式
    public String doContactMtnStr(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=contact().controllersName;
        return setApiCore(ctnName,mtnBean,dataBean);
    }





    /**
     * 执行消息推送控制器公共方法
     * @param dataBean
     * @return
     */
    public CC.Builder doAmqpMtn(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=amqp().controllersName;
        return  bases(ctnName,mtnBean,dataBean);
    }
    //只返回请求数据和格式
    public String doAmqpMtnStr(SearchInfoBean mtnBean,Object dataBean){
        String ctnName=amqp().controllersName;
        return setApiCore(ctnName,mtnBean,dataBean);
    }

}
