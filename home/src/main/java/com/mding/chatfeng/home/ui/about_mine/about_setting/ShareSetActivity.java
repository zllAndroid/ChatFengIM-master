package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataSetAbout;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;

public class ShareSetActivity extends BaseActivity {
    TextView includeTopTvTitle;
    TextView includeTopTvRight;
    LinearLayout includeTopLin;
    RadioButton radioBtnOne;
    RadioButton radioBtnTwo;
    RadioButton radioBtnThr;
    RadioGroup shareBtngrop;

//    @BindView(R.id.share_switch_accept)
//    SwitchButton shareSwitchAccept;
//    @BindView(R.id.share_switch_nouse)
//    SwitchButton shareSwitchNouse;
//    @BindView(R.id.share_switch_no)
//    SwitchButton shareSwitchNo;

    String mShare= "1";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_share;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.card_sharing_title));
//        includeTopTvTitle.setText("名片分享");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 接接口
//                sendWeb( SplitWeb.getSplitWeb().permissionSetOne("1",mShare));
            }
        });
        radioBtnOne.setChecked(true);
//        radiogroup本身有监听的方法可以直接设置监听，这个监听需要一个回调接口OnCheckedChangeListener，这个接口里面的回调方法给我们返回了两个参数其中int型的参数就是当前你选中的RadioButton的ID
        shareBtngrop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkId就是当前选中的RadioButton
                if (checkedId == R.id.radioBtn_one) {
                    mShare = "1";
//                        ToastUtil.show("允许分享");

                } else if (checkedId == R.id.radioBtn_two) {
                    mShare = "2";
//                        ToastUtil.show("需要验证");

                } else if (checkedId == R.id.radioBtn_thr) {
                    mShare = "0";
//                        ToastUtil.show("禁止被分享");

                }
//                switch (checkedId){
//                    case R.id.radioBtn_one:
//                        mShare= "1";
////                        ToastUtil.show("允许分享");
//                        break;
//                    case R.id.radioBtn_two:
//                        mShare= "2";
////                        ToastUtil.show("需要验证");
//                        break;
//                    case R.id.radioBtn_thr:
//                        mShare= "0";
////                        ToastUtil.show("禁止被分享");
//                        break;
//                }
            }
        });
        // TODO 接接口
//        sendWeb( SplitWeb.getSplitWeb().getPermissStatu("1"));
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        includeTopTvRight = getView(R.id.inclu_tv_right);
        includeTopLin = getView(R.id.include_top_lin_back);
        radioBtnOne = getView(R.id.radioBtn_one);
        radioBtnTwo = getView(R.id.radioBtn_two);
        radioBtnThr = getView(R.id.radioBtn_thr);
        shareBtngrop = getView(R.id.share_radioGroup);

        addOnClickListeners();
    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);

        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "getPermissStatu":
//                DataSetAbout dataSetAbout = JSON.parseObject(responseText, DataSetAbout.class);
//                DataSetAbout.RecordBean record = dataSetAbout.getRecord();
//                if (record!=null)
//                {
//                    String is_share = record.getIsShare();
//                    if (!StrUtils.isEmpty(is_share))
//                    switch (is_share)
//                    {
//                        case "1":
//                            radioBtnOne.setChecked(true);
//                            break;
//                        case "2":
//                            radioBtnTwo.setChecked(true);
//                            break;
//                        case "0":
//                            radioBtnThr.setChecked(true);
//                            break;
//                    }
////                    String is_qrcode_show = record.getIs_qrcode_show();
////                    mSwitchCount.setChecked(is_sno_show.equals("1"));
////                    mSwitcherCode.setChecked(is_qrcode_show.equals("1"));
//                }
                break;
            case "permissionSet":
//                DialogUtils.showDialogOne("名片分享设置成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
                ToastUtil.show(getResources().getString(R.string.card_sharing_save_succeed));
//                ToastUtil.show("名片分享设置成功！");
                AppManager.getAppManager().finishActivity();
                break;
        }
    }

}
