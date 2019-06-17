package com.mding.chatfeng.base_common.net;

import android.database.Observable;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.mding.chatfeng.base_common.net.socket.DoReciveSocket;
import com.mding.chatfeng.base_common.net.socket.protocol.MsgDataBean;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.bean.BaseBean;
import com.mding.chatfeng.base_common.net.socket.DoSendSocket;
import com.mding.chatfeng.base_common.request.Api;
import com.mding.chatfeng.base_common.AppConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 待完成，还要封装文件上传、下载
 */
public abstract class NetManager {

    protected abstract void successData(String result);
    protected abstract void failData(BaseBean mBaseBean);
    protected abstract void  replaceApi();

    public NetManager(final CC cc) {
        if (cc.getParamItem(cc.getComponentName()).equals("1"))
        {
            AppConfig.logs("此链接是http协议");
            doPostMethon(cc);
        } else if (cc.getParamItem(cc.getComponentName()).equals("2")||cc.getParamItem(cc.getComponentName()).equals("3")){
            AppConfig.logs("此链接不是http协议,是socket长链接协议");
            doSocketMethon(cc);
        }
    }

    private void doSocketMethon(final CC cc){
    new DoReciveSocket() {
        @Override
        public void connectSuccess(String result) {

//            mManager.send(new MsgDataBean(cc.getParamItem(cc.getActionName()).toString()));
        }

        @Override
        public void succeesBacks(String result) {
            ctn=cc.getComponentName();
            mtn=cc.getActionName();
            replaceApi();
//            successData(result);


            //根据返回的方法名进行过滤回执
           BaseBean mBaseBean= new GsonParamConverter().json2Object(result ,BaseBean.class);


           //此处判断消息类型是推送消息还是请求返回消息
            //如果是返回类型，则
                 //此处判断返回消息是否是当前调用对象的请求名，是的话返回给调用者
            //如果是推送类型，则
                //根据动态组件，发给相应的UI绑定组件
//            AppConfig.logs(cc.getParamItem(cc.getActionName()).toString());


            if(mBaseBean.getMtn().equals(mtn))
            {
                CC.sendCCResult(cc.getCallId(), CCResult.success(cc.getActionName(),result));
                AppConfig.logs(result+"--DoReciveSocket");
            }
     AppConfig.logs("________"+mtn+"-              -DoReciveSocket"+"++++++++++"+mBaseBean.getMtn());

        }
    };

    }

   public String ctn,mtn;
    private void doPostMethon(final CC cc){

        String url=AppConfig.httpProtocolIp;
      if (cc.getParamItem("url")!=null)
          url=cc.getParamItem("url");

        Retrofit  retrofit = new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=retrofit.create(Api.class);
        //利用抽象方法替换不同的API请求
        ctn=cc.getComponentName();
        mtn=cc.getActionName();
        replaceApi();
        String postData=cc.getParamItem(cc.getActionName()).toString();
        Call<ResponseBody>  call=api.request(AppConfig.version,ctn,mtn,postData);


        AppConfig.logs("请求链接："+ call.request().url());
        AppConfig.logs("请求数据："+ postData);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //网络请求成功
                try {
                    String result=response.body().string();
                    AppConfig.logs(cc.getComponentName()+"/"+cc.getActionName()+"->数据获取成功:"+result);
                //进一步筛选业务数据是否返回成功
                    BaseBean mBaseBean= new GsonParamConverter().json2Object(result ,BaseBean.class);
                    switch (mBaseBean.getCode()){
                        case 9999:
                            //错误则返回提示信息
                            failData(mBaseBean);
                            break;
                            //正确则返回数据
                            default:successData(result);
                            break;
                    }

                    CC.sendCCResult(cc.getCallId(), CCResult.success(cc.getActionName(),result));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //网络请求失败
                CC.sendCCResult(cc.getCallId(), CCResult.errorUnsupportedActionName());
            }
        });
    }




}
