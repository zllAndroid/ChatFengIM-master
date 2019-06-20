package com.mding.chatfeng.home.ui.about_mine.about_changeinfo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
//import com.mding.chatfeng.base_common.utils.about_main_utils.ZXingUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.home.R;

/**
 *  我的二维码
 *
 *   不需要网络请求，纯数据展示（1、传值显示  2、缓存显示）
 */
public class MyAccountActivity extends BaseActivity {
    TextView includeTopTvTitle;
    ImageView qrcodeIvHead;
    TextView qrcodeTvName;
    TextView qrcodeTvSao;
    ImageView qrcodeIvQrcode;
    LinearLayout includeTopLinBackground;
    public  static final String TITAL_NAME = "tital_name";
    ACache aCache;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_mine_ziliao;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
//        Intent intent = getIntent();
//        if (intent!=null)
//        {
////        TODO    接收传值过来显示
//            PersonData personData = (PersonData)intent.getSerializableExtra(TITAL_NAME);
//            if (personData!=null)
//            {
//                qrcodeTvSao.setText(personData.getScanTital());
//                qrcodeTvName.setText(personData.getName());
//                String headImg = personData.getHeadImg();
//                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
//                String string = personData.getQrCode();
//                createQrCodeImg(string);
//                includeTopTvTitle.setText(personData.getTital());
//                return;
//            }
//        }
////      TODO  显示自个人资料的缓存数据
//        aCache = ACache.get(this);
//        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
//        if (!StrUtils.isEmpty(json))
//        {
//            DataMyZiliao.RecordBean recordBean = JSON.parseObject(json, DataMyZiliao.RecordBean.class);
//            if (recordBean!=null) {
//                String headImg = recordBean.getHeadImg();
//                String qrCodeString = recordBean.getQrcode();
//                createQrCodeImg(qrCodeString);
//                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
//                String nickName = recordBean.getNickName();
//                qrcodeTvName.setText(nickName);
//            }
//        }
        includeTopTvTitle.setText(getResources().getString(R.string.qrcode_title_mine));
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        qrcodeIvHead = getView(R.id.qrcode_iv_head);
        qrcodeTvName = getView(R.id.qrcode_tv_name);
        qrcodeTvSao = getView(R.id.qrcode_tv_sao);
        qrcodeIvQrcode = getView(R.id.qrcode_iv_qrcode);
        includeTopLinBackground = getView(R.id.include_top_lin_background);

        addOnClickListeners();
    }

    //    不需要网络请求，纯数据展示（1、传值显示  2、缓存显示）
//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        if (HelpUtils.backMethod(responseText).equals("personalCenter")) {
//            DataMyZiliao dataMyZiliao = JSON.parseObject(responseText, DataMyZiliao.class);
//            DataMyZiliao.RecordBean record = dataMyZiliao.getRecord();
//            if (record != null) {
//                String s = MyJsonUtils.toChangeJson(record);
//                aCache.put(AppAllKey.PPERSON_iNFO,s);
//                qrcodeTvName.setText(record.getNickName());
//                String headImg = record.getHeadImg();
//                ImageUtils.useBase64(MyAccountActivity.this, qrcodeIvHead, headImg);
//                createQrCodeImg(record.getQrcode());
//            }
//        }
//    }

//    private void createQrCodeImg(String qrcode) {
//        Bitmap bitmap = ZXingUtils.createQRImage(qrcode, 300, 300);
//        Drawable drawable = new BitmapDrawable(bitmap);
//        qrcodeIvQrcode.setBackground(drawable);
//    }

}
