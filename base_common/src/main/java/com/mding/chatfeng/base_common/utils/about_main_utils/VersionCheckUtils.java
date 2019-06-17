package com.mding.chatfeng.base_common.utils.about_main_utils;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.model.about_update.DataUpdate;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.about_dialog.DialogUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;

/**
 * Created by  on 2017/12/8 0008.
 */

public class VersionCheckUtils {
    static DataUpdate.RecordBean record = null;
    public static  void initUpdata(final String result, final  boolean isReturn) {
//        final int localVersion = HelpUtils.getLocalVersion(AppManager.getAppManager().currentActivity());
//                Log.e("result", "请求结果result----------==" + result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(AppConfig.CODE_OK)) {
//                    是否主动请求，无版本更新会弹出提示
                    if (isReturn)
                    {
                        initJson(result);
                    }else
                    {
                        initJsonReturn(result);
                    }
                }  else {
                    ToastUtil.show(sucess);
                }
    }
    public static void initJson(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
//                DataUpdate dataUpVersion = JSON.parseObject(msg, DataUpdate.class);
                DataUpdate dataUpVersion=  new GsonParamConverter().json2Object(msg,DataUpdate.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getUpdate();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("2"))
                    {
                        if (record.getForce().equals("1"))
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void initJsonReturn(String msg) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpdate dataUpVersion=  new GsonParamConverter().json2Object(msg,DataUpdate.class);
//                DataUpdate dataUpVersion = json2Object.parseObject(msg, DataUpdate.class);
                record = dataUpVersion.getRecord();
                if (record != null) {
                    String is_update = record.getUpdate();
                    if (!StrUtils.isEmpty(is_update))
                    if (is_update.equals("2"))
                    {
//                        是否强制更新
                        if (record.getForce().equals("1"))
                        {
                            try {
//                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, msg);
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), false, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else
                        {
                            try {
//                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true,msg);
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), record.getUrl(), true, record.getContent());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }else
                    {
//                        DialogUtils.showDialogOne(msg, new DialogUtils.OnClickSureListener() {
//                            @Override
//                            public void onClickSure() {
//                            }
//                        });
                        DialogUtils.showDialogOne("已经是最新版本", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {

                            }
                        });
//                       Tip.getDialog(AppManager.getAppManager().currentActivity(),"已经是最新版本");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
