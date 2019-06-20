package com.rance.chatui.about_chat.chat_group.sub_group;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.chat.R;


public class AddGroupWayActivity extends BaseActivity {

    LinearLayout includeTopLin;
    ImageView includeTopIvBack;
    TextView includeTopTvTital;
    TextView includeTopTvRight;
    RadioButton radioBtnOne;
    RadioButton radioBtnTwo;
    RadioButton radioBtnThr;
    RadioGroup addRadioGroup;


    @Override
    protected int getLayoutView() {
        return R.layout.activity_add_group_way;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initUI();
        includeTopTvTital.setText("加群方式");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sendWeb( SplitWeb.getSplitWeb().permissionSetOne("1",mShare));
            }
        });
        radioBtnOne.setChecked(true);
//        radiogroup本身有监听的方法可以直接设置监听，这个监听需要一个回调接口OnCheckedChangeListener，这个接口里面的回调方法给我们返回了两个参数其中int型的参数就是当前你选中的RadioButton的ID
        addRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //checkId就是当前选中的RadioButton
                switch (checkedId){
                    case R.id.radioBtn_one:
//                        mShare= "1";
//                        ToastUtil.show("允许分享");
                        break;
                    case R.id.radioBtn_two:
//                        mShare= "2";
//                        ToastUtil.show("需要验证");
                        break;
                    case R.id.radioBtn_thr:
//                        mShare= "0";
//                        ToastUtil.show("禁止被分享");
                        break;
                }
            }
        });
//   TODO     sendWeb( SplitWeb.getSplitWeb().getPermissStatu("1"));
    }

    private void initUI() {
        LinearLayout includeTopLin=
        getView(R.id.include_top_lin_back);

        ImageView includeTopIvBack=
        getView(R.id.include_top_iv_back);
        TextView includeTopTvTital=
        getView(R.id.include_top_tv_title);
        TextView includeTopTvRight=
        getView(R.id.inclu_tv_right);
        RadioButton radioBtnOne=
        getView(R.id.radioBtn_one);
        RadioButton radioBtnTwo=
        getView(R.id.radioBtn_two);
        RadioButton radioBtnThr=
        getView(R.id.radioBtn_thr);
        RadioGroup addRadioGroup=
        getView(R.id.add_radioGroup);

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);

        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {

//            case "getPermissStatu":
//                DataSetAbout dataSetAbout = JSON.parseObject(responseText, DataSetAbout.class);
//                DataSetAbout.RecordBean record = dataSetAbout.getRecord();
//                if (record!=null)
//                {
//                    String is_share = record.getIsShare();
//                    if (!StrUtils.isEmpty(is_share))
//                        switch (is_share)
//                        {
//                            case "1":
//                                radioBtnOne.setChecked(true);
//                                break;
//                            case "2":
//                                radioBtnTwo.setChecked(true);
//                                break;
//                            case "0":
//                                radioBtnThr.setChecked(true);
//                                break;
//                        }
////                    String is_qrcode_show = record.getIs_qrcode_show();
////                    mSwitchCount.setChecked(is_sno_show.equals("1"));
////                    mSwitcherCode.setChecked(is_qrcode_show.equals("1"));
//                }
//                break;
//            case "permissionSet":
//                DialogUtils.showDialogOne("加群方式设置成功", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        AppManager.getAppManager().finishActivity();
//                    }
//                });
//                break;

        }
    }
}
