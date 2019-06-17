package com.mding.chatfeng.start;


import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.bean.login.GetClusterIp;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.request.body.LoginInBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.about_immersive.StatusBarUtil;
import com.mding.chatfeng.base_common.utils.about_key.AppAllKey;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.MyJsonUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.base_common.utils.aboututils.SPUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;

import java.util.Timer;
import java.util.TimerTask;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import site.gemus.openingstartanimation.OpeningStartAnimation;

@RuntimePermissions
public class AppStartActivity extends BaseActivity {

    Timer timer = null;
    OpeningStartAnimation openingStartAnimation;
    @Override
    protected void initBaseView() {
        super.initBaseView();
        initGreate();
//        addOnClickListeners(R.id.tv_start);
        initJumpMain();
//        initJumpLogin();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            AppStartActivityPermissionsDispatcher.needWithCheck(this);
//            need();
        else
            need();
    }
    private void initGreate() {
        //初始化控制器和方法字典
        create.init(this);
        //通过集群初始化动态IP
        create.getComsApi().getClusterIp().build()
                .callAsync(new IComponentCallback() {
                    @Override
                    public void onResult(CC loginCC, CCResult result) {
                        AppConfig.logs(result.isSuccess() + "调用成败：" + result);
                        if (result.isSuccess()) {
                            GetClusterIp mGetClusterIp = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), GetClusterIp.class);
                            AppConfig.httpProtocolIp = mGetClusterIp.getRecord().getHttpProtocolIp();
                            AppConfig.wsProtocolIp_1 = mGetClusterIp.getRecord().getWsProtocolIp_1();
                            AppConfig.skProtocolIp_1 = mGetClusterIp.getRecord().getSkProtocolIp_1();
//                            AppConfig.skProtocolPort=Integer.valueOf(mGetClusterIp.getRecord().getSkProtocolIp_1().split(":")[1]);
                            //此处可能存本地化

                            //异步回调成功后执行跳转组件
                            //此处测试
//                            testAmqp();
//                            tesCommon();
//                            testHttp();

                        } else {
                            AppConfig.logs("配置初始化失败，网络数据获取失败");
                            //此处可能取本地化
                        }
                    }
                });
    }

    private void initJumpLogin() {
        CC.obtainBuilder("jump")
                .setActionName("jumpLogin").addParam("zll","this is data")
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行
                    }
                });
    }
    private void initJumpMain() {
        CC.obtainBuilder("StartCallMain")
                .setActionName("jumpMain").addParam("zll","this is data")
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行
                    }
                });
    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_start;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                showFloatingWindow();
            }
            initCaChe();
        }
    };


    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
    @Override
    public boolean isGonesStatus() {
        return true;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();

    }
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE
            ,Manifest.permission.WRITE_EXTERNAL_STORAGE
    })
    void need() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(task, 1500);
        }
    }
    private ACache mCache;
    private void initCaChe() {
        if (mCache==null)
            mCache = ACache.get(this);
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
            if (!StrUtils.isEmpty(asString))
            {}
        }
//        IntentUtils.JumpFinishTo(AppStartActivity.this,LoginActivity.class);
//        overridePendingTransition(0,0);
    }
    //
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppStartActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    PermissionRequest requests =null;
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onshow(final permissions.dispatcher.PermissionRequest request) {
        requests=request;
        DialogUtils.showDialogOne("信任是美好的开始，\n请授权相机等权限，\n让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                request.proceed();
            }
        });
    }
    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onper() {
        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
                    initStratSet();
                }
            }
        });
    }

    private void initStratSet() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                    intent.setData(Uri.parse("package:" + getPackageName()));
        intent.setData(Uri.parse("package:" + "com.mding.chatfeng.start"));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        AppStartActivity.this.startActivityForResult(intent,0);
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onnesver() {
        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
                    initStratSet();
                }
            }
        });
    }
}
