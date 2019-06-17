//package com.mding.chatfeng.start;
//
//
//import android.Manifest;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.view.View;
//import android.widget.TextView;
//
//import com.billy.cc.core.component.CC;
//import com.billy.cc.core.component.CCResult;
//import com.billy.cc.core.component.IComponentCallback;
//import com.mding.chatfeng.base_common.AppConfig;
//import com.mding.chatfeng.base_common.bean.login.GetClusterIp;
//import com.mding.chatfeng.base_common.components.base.BaseActivity;
//import com.mding.chatfeng.base_common.components.base.model.DataLogin;
//import com.mding.chatfeng.base_common.request.body.LoginInBody;
//import com.mding.chatfeng.base_common.request.create;
//import com.mding.chatfeng.base_common.utils.GsonParamConverter;
//import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
//import com.mding.chatfeng.base_common.utils.about_key.AppAllKey;
//import com.mding.chatfeng.base_common.utils.aboututils.ACache;
//import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import butterknife.BindView;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.OnShowRationale;
//import permissions.dispatcher.PermissionRequest;
//import permissions.dispatcher.RuntimePermissions;
//import site.gemus.openingstartanimation.OpeningStartAnimation;
//
//@RuntimePermissions
//public class AppStartActivity22 extends BaseActivity {
//
//    @BindView(R.id.tv_start)
//    TextView tvStart;
//    @Override
//    public int getLayoutView() {
//        return R.layout.activity_start;
//    }
//
//    Timer timer = null;
//    OpeningStartAnimation openingStartAnimation;
//    @Override
//    protected void initBaseView() {
//        super.initBaseView();
//        initGreate();
//        addOnClickListeners(R.id.tv_start);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//        {
////            StartActivityPermissionsDispatcher.needWithCheck(this);
//
//
//        }
//        else
//            need();
//    }
//    //    private WindowService.MyBinder mybinder;
//    void need() {
//        if (timer == null) {
//            timer = new Timer();
//            timer.schedule(task, 1500);
//        }
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (timer != null)
//            timer.cancel();
//    }
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////                showFloatingWindow();
//            }
//            initCaChe();
//        }
//    };
//
////    @Override
////    public void initStateBar() {
////        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
////    }
//
//    @Override
//    public boolean isSupportSwipeBack() {
//        return false;
//    }
//    @Override
//    public boolean isGonesStatus() {
//        return true;
//    }
//
//    @Override
//    protected boolean isTopBack() {
//        return false;
//    }
//
//    private ACache mCache;
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_start);
////        ButterKnife.bind(this);
////        initGreate();
////
////
////    }
//
//    private void initGreate() {
//        //初始化控制器和方法字典
//        create.init(this);
//        //通过集群初始化动态IP
//        create.getComsApi().getClusterIp().build()
//                .callAsync(new IComponentCallback() {
//                    @Override
//                    public void onResult(CC loginCC, CCResult result) {
//                        AppConfig.logs(result.isSuccess() + "调用成败：" + result);
//                        if (result.isSuccess()) {
//                            GetClusterIp mGetClusterIp = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), GetClusterIp.class);
//                            AppConfig.httpProtocolIp = mGetClusterIp.getRecord().getHttpProtocolIp();
//                            AppConfig.wsProtocolIp_1 = mGetClusterIp.getRecord().getWsProtocolIp_1();
//                            AppConfig.skProtocolIp_1 = mGetClusterIp.getRecord().getSkProtocolIp_1();
////                            AppConfig.skProtocolPort=Integer.valueOf(mGetClusterIp.getRecord().getSkProtocolIp_1().split(":")[1]);
//                            //此处可能存本地化
//
//                            //异步回调成功后执行跳转组件
//                            //此处测试
////                            testAmqp();
////                            tesCommon();
//                            testHttp();
//                        } else {
//                            AppConfig.logs("配置初始化失败，网络数据获取失败");
//                            //此处可能取本地化
//                        }
//                    }
//                });
//    }
//
//    /**
//     * doLoginMtn 控制器方法
//     * create.login().register  具体方法
//     * LoginInBody  请求的数据体
//     * 注意：单个组件不能同时调用，需要第一次结束后才能发起第二次重复调用，如需同时调用，需要在组件Action名加上时间戳，如果是同步调用则无需考虑
//     *
//     * @wdh
//     */
//    private void testHttp() {
//        create.getComsApi().doCommon(create.login().controllersName, create.login().loginIn, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
//                .callAsync(new IComponentCallback() {
//                    @Override
//                    public void onResult(CC loginCC, CCResult result) {
////                        LoginInBean mLoginInBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), LoginInBean.class);
////                        AppConfig.logs("调用成败：" + result.isSuccess() + "  ---解析数据：" + mLoginInBean.getMsg());
//                        AppConfig.logs("调用成败：" + result.isSuccess());
//                    }
//                });
//    }
//
//
//    /**
//     * 测试socket组件
//     */
//    private void testAmqp() {
//        create.getComsApi().doAmqpMtn(create.amqp().sendPrivateChat, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
//                .callAsync(new IComponentCallback() {
//                    @Override
//                    public void onResult(CC loginCC, CCResult result) {
////                        LoginInBean mLoginInBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), LoginInBean.class);
//
//                        AppConfig.logs("调用成败：" + result);
//                    }
//                });
//    }
//
//
//    /**
//     * 测试socket组件
//     */
//    private void tesCommon() {
//        create.mComsApi.doCommon(create.amqp().controllersName, create.amqp().sendPrivateChat, new LoginInBody("18150960006", "e5e94876560ab8220f43cbd52854e80a")).build()
//                /*create.mComsApi.doCommon(create.login().controllersName,create.login().register, new LoginInBody("123123", "5123123", "12312", "13123", "13")).build()*/
//                .callAsync(new IComponentCallback() {
//                    @Override
//                    public void onResult(CC loginCC, CCResult result) {
////                        LoginInBean mLoginInBean = new GsonParamConverter().json2Object(result.getDataItem(loginCC.getActionName()).toString(), LoginInBean.class);
//
//                        AppConfig.logs("调用成败：" + result);
//                    }
//                });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId()==R.id.tv_start)
//            CC.obtainBuilder("jump")
//                    .setActionName("jumpLogin")
//                    .build()
//                    .callAsyncCallbackOnMainThread(new IComponentCallback() {
//                        @Override
//                        public void onResult(CC cc, CCResult result) {
//                            //此onResult在主线程中运行
//                        }
//                    });
//    }
//
//    private void initCaChe() {
////        MyJsonUtils.initBeforeLogin(AppStartActivity.this);
//        if (mCache==null)
//            mCache = ACache.get(this);
//        if (mCache!=null){
//            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY);
//            if (!StrUtils.isEmpty(asString))
//            {
//                DataLogin dataLogin = new GsonParamConverter().json2Object(asString, DataLogin.class);
////                DataLogin dataLogin = JSON.parseObject(asString, DataLogin.class);
//                DataLogin.RecordBean record = dataLogin.getRecord();
////                DataLogin.RecordBean dataLogin = JSON.parseObject(asString, DataLogin.RecordBean.class);
//                if (record!=null) {
//                    String asFriend = mCache.getAsString(AppAllKey.FRIEND_DATA);
//
////                    if (StrUtils.isEmpty(asFriend))
////                    {
////                        IntentUtils.JumpFinishTo(AppStartActivity.this, LoadLinkManActivity.class);
////                    }else {
////                        IntentUtils.JumpFinishTo(AppStartActivity.this, MainActivity.class);
////                    }
////                    init(BaseApplication.getAppContext());
////                    overridePendingTransition(0, 0);
//                    return;
//                }
//            }
//        }
////        IntentUtils.JumpFinishTo(AppStartActivity.this,LoginActivity.class);
////        overridePendingTransition(0,0);
//    }
//    //
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        StartActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//
//
//    }
//    PermissionRequest requests =null;
//    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onshow(final PermissionRequest request) {
//        requests=request;
//        DialogUtils.showDialogOne("信任是美好的开始，\n请授权相机等权限，\n让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
//            @Override
//            public void onClickSure() {
//                request.proceed();
//            }
//        });
//    }
//    //拒绝
//    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onper() {
//        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
//            @Override
//            public void onClickSure() {
//                if (requests!=null)
//                    requests.proceed();
//                else {
////                        AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
//                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                    intent.setData(Uri.parse("package:" + getPackageName()));
//                    intent.addCategory(Intent.CATEGORY_DEFAULT);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivityForResult(intent,0);
////                    startActivity(intent);
//                }
//            }
//        });
//    }
//    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
//    void onnesver() {
////        ToastUtil.show("OnNeverAskAgain");
//        DialogUtils.showDialogOne("应用需要使用相机等权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
//            @Override
//            public void onClickSure() {
//                if (requests!=null)
//                    requests.proceed();
//                else {
////                    AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
//                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                    intent.setData(Uri.parse("package:" + getPackageName()));
//                    intent.addCategory(Intent.CATEGORY_DEFAULT);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivityForResult(intent,0);
////                    startActivity(intent);
//                }
//            }
//        });
//    }
//}
