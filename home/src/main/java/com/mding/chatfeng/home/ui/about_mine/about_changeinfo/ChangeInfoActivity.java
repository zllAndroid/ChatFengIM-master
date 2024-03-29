package com.mding.chatfeng.home.ui.about_mine.about_changeinfo;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.base.BaseApplication;
import com.mding.chatfeng.base_common.components.model.DataMyZiliao;
import com.mding.chatfeng.base_common.components.model.DataSetHeadResult;
import com.mding.chatfeng.base_common.components.model.HeadImgInfo;
import com.mding.chatfeng.base_common.components.model.PersonInfo;
import com.mding.chatfeng.base_common.request.body.PersonUserBody;
import com.mding.chatfeng.base_common.request.create;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.HeadFileUtils;
import com.mding.chatfeng.base_common.utils.about_key.AppAllKey;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.MyJsonUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.NoDoubleClickUtils;
import com.mding.chatfeng.base_common.utils.aboututils.SPUtils;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.fragment.PersonalFragment;
import com.mding.chatfeng.home.ui.about_contacts.ChangeInfoWindow;
import com.mding.chatfeng.home.ui.about_msg.PhotoPopWindow;
import com.mding.chatfeng.home.ui.top_total.ClipImgActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;

//我的资料页面   在个人中心点击进入此
public class ChangeInfoActivity extends BaseActivity implements ChangeInfoWindow.OnAddContantClickListener {

    TextView includeTopTvTitle;
    ImageView includeTopIvBack;
    ImageView changeinfoIvHead;
    ImageView changeinfoIvWrite;
    TextView changeinfoTvName;
    TextView changeinfoTvCount;
    TextView changeinfoTvSign;
    LinearLayout mLinMain;
    LinearLayout includeTopLinBackground;
    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    private String imageBase64;
    public static String NICK_NAME = "nickName";
    ACache aCache;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_changeinfo;
    }

    @Override
    protected boolean isTopBack() {
        return true;
    }

    @Override
    protected boolean isGones() {
        return true;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopIvBack.setVisibility(View.VISIBLE);
        includeTopTvTitle.setText(getResources().getString(R.string.my_info_title));
//        includeTopTvTitle.setText("我的资料");
        includeTopLinBackground.setBackgroundColor(getResources().getColor(R.color.app_theme));
        if (aCache==null){
            aCache =  ACache.get(this);
        }
        String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
        if (!StrUtils.isEmpty(json)){
            try {
                initUI(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {

//            请求接口
//            sendWeb(SplitWeb.getSplitWeb().personalCenter());
            create.getComsApi().doCommon(create.person().controllersName,create.person().personalCenter,new PersonUserBody("","")).build().callAsync(new IComponentCallback() {
                @Override
                public void onResult(CC cc, CCResult result) {
                    String  dataItem = result.getDataItem(cc.getActionName());

                }
            });
        }
    }

    private void initViewUI() {
        includeTopTvTitle=getView(R.id.include_top_tv_title);
        includeTopIvBack=getView(R.id.include_top_iv_back);
        changeinfoIvHead=getView(R.id.include_top_iv_back);
        changeinfoIvWrite=getView(R.id.changeinfo_iv_count);
        changeinfoTvName=getView(R.id.changeinfo_tv_name);
        changeinfoTvCount=getView(R.id.changeinfo_tv_count);
        changeinfoTvSign=getView(R.id.changeinfo_tv_sign);
        mLinMain=getView(R.id.changeinfo_top_lin);
        includeTopLinBackground=getView(R.id.include_top_lin_background);
        addOnClickListeners(R.id.changeinfo_re_head, R.id.changeinfo_tv_name, R.id.changeinfo_lin_sign, R.id.changeinfo_lin_count,R.id.changeinfo_iv_qrcode);
    }
    String signatureText;
    private void initUI(String json) {
        DataMyZiliao.RecordBean recordBean = new GsonParamConverter().json2Object(json, DataMyZiliao.RecordBean.class);
        if (recordBean!=null) {

            ImageUtils.useBase64(ChangeInfoActivity.this,changeinfoIvHead,recordBean.getHeadImg());

            changeinfoTvName.setText(recordBean.getNickName());
            changeinfoTvCount.setText(recordBean.getWxSno());

            signatureText = StrUtils.isEmpty(recordBean.getPersonaSignature()) ? getResources().getString(R.string.personal_no_set) : recordBean.getPersonaSignature();
            changeinfoTvSign.setText(signatureText);
            String up_sno_num = recordBean.getUpSnoNum();
            if (up_sno_num.equals("1")) {
                changeinfoIvWrite.setVisibility(View.VISIBLE);
            } else {
                changeinfoIvWrite.setVisibility(View.GONE);
            }
        }
    }
    //0 修改昵称   1 修改帐号 2 修改个签
    String isChangeName = "0";
    private PhotoPopWindow photoPopWindow = null;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
        if (i == R.id.changeinfo_re_head) {
            if (photoPopWindow == null)
                photoPopWindow = new PhotoPopWindow(ChangeInfoActivity.this, MyClick);
            photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);

        } else if (i == R.id.changeinfo_tv_name) {
            if (NoDoubleClickUtils.isDoubleClick())
                doChangeName();

//               TODO 点击二维码跳转
        } else if (i == R.id.changeinfo_iv_qrcode) {
            //                Log.e("qrCode","----------------------------------userId = "+SplitWeb.getSplitWeb().getUserId());
//                IntentUtils.JumpToHaveOne(MyAccountActivity.class,"userId",SplitWeb.getSplitWeb().getUserId());
                IntentUtils.JumpTo(MyAccountActivity.class);

        } else if (i == R.id.changeinfo_lin_sign) {
            if (NoDoubleClickUtils.isDoubleClick())
                doChangeSign();

        } else if (i == R.id.changeinfo_lin_count) {
            int visibility = changeinfoIvWrite.getVisibility();
            if (visibility != View.GONE) {
                if (NoDoubleClickUtils.isDoubleClick())
                    doChangeCount();
            }
        }
    }
    //修改个签
    private void doChangeSign() {
        isChangeName = "2";
        ChangeInfoWindow changeInfoWindowSign;
        String string = BaseApplication.getAppContext().getResources().getString(R.string.personal_no_set);
        if (signatureText!=null&&signatureText.equals(string)){
            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, getResources().getString(R.string.my_info_modify_signature), "");
//            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, "修改个性签名", "");
        }
        else {
            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, getResources().getString(R.string.my_info_modify_signature), changeinfoTvSign.getText().toString().trim());
//            changeInfoWindowSign = new ChangeInfoWindow(ChangeInfoActivity.this, "修改个性签名", changeinfoTvSign.getText().toString().trim());
        }
        changeInfoWindowSign.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowSign.setOnAddpopClickListener(this);
    }

    //修改帐号
    private void doChangeCount() {
        isChangeName = "1";
        ChangeInfoWindow changeInfoWindowCount = new ChangeInfoWindow(ChangeInfoActivity.this, getResources().getString(R.string.my_info_modify_account), changeinfoTvCount.getText().toString().trim());
//        ChangeInfoWindow changeInfoWindowCount = new ChangeInfoWindow(ChangeInfoActivity.this, "修改帐号", changeinfoTvCount.getText().toString().trim());
        changeInfoWindowCount.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindowCount.setOnAddpopClickListener(this);
    }

    //修改昵称
    private void doChangeName() {
        isChangeName = "0";
        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(ChangeInfoActivity.this, getResources().getString(R.string.my_info_modify_nick_name), changeinfoTvName.getText().toString().trim());
//        ChangeInfoWindow changeInfoWindow = new ChangeInfoWindow(ChangeInfoActivity.this, "修改昵称", changeinfoTvName.getText().toString().trim());
        changeInfoWindow.showAtLocation(mLinMain, Gravity.CENTER, 0, 0);
        changeInfoWindow.setOnAddpopClickListener(this);
    }
    //为弹出窗口实现监听类
    public View.OnClickListener MyClick = new View.OnClickListener() {
        public void onClick(View v) {
            photoPopWindow.dismiss();
            int i = v.getId();
            if (i == R.id.btn_open_cramre) {
                destoryImage();
                getPicturesFile();
            } else if (i == R.id.btn_open_xaingce) {//	相册
                //权限判断
                if (ContextCompat.checkSelfPermission(ChangeInfoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    goToAlbum();
                }
                photoPopWindow.dismiss();

            } else {
            }
        }
    };

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }
    /**
     * 打开截图界面
     */
    public void goToClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImgActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToAlbum();
            }
        }
    }

    DataMyZiliao.RecordBean record;
    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "personalCenter":
                DataMyZiliao dataMyZiliao =new GsonParamConverter().json2Object(responseText, DataMyZiliao.class);
                record = dataMyZiliao.getRecord();
                if (record != null) {
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                    String up_sno_num = record.getUpSnoNum();
                    if (up_sno_num.equals("1")) {
                        changeinfoIvWrite.setVisibility(View.VISIBLE);
                    } else {
                        changeinfoIvWrite.setVisibility(View.GONE);
                    }
                    changeinfoTvName.setText(record.getNickName());
                    changeinfoTvCount.setText(record.getWxSno());
                    if (StrUtils.isEmpty(record.getPersonaSignature())) {
                        changeinfoTvSign.setHint(getResources().getString(R.string.personal_no_set));
                    } else {
                        changeinfoTvSign.setText(record.getPersonaSignature());
                    }
                }
//                }
                break;
            case "upNickName"://修改昵称成功
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NAME, contant);

//                SplitWeb.getSplitWeb().NICK_NAME = contant;


                String json = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json)) {
                    DataMyZiliao.RecordBean record = new GsonParamConverter().json2Object(json, DataMyZiliao.RecordBean.class);
                    record.setNickName(contant);
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                changeinfoTvName.setText(contant);
                PersonInfo personInfo = new PersonInfo();
                personInfo.setNickName(contant);
                EventBus.getDefault().post(personInfo);
                break;
            case "upPersonSign":
                PersonalFragment.isChange = true;
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_SIGN, contant);
//                SplitWeb.getSplitWeb().PERSON_SIGN = contant;


                String json2 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json2)) {
                    DataMyZiliao.RecordBean record = new GsonParamConverter().json2Object(json2, DataMyZiliao.RecordBean.class);
                    record.setPersonaSignature(contant);
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                if (StrUtils.isEmpty(contant)) {
                    changeinfoTvSign.setText(getResources().getString(R.string.personal_no_set));
                }
                else {
                    changeinfoTvSign.setText(contant);
                }

                PersonInfo personInfo2 = new PersonInfo();
                personInfo2.setSign(contant);
                EventBus.getDefault().post(personInfo2);
                break;
            case "upUserSno":
                SPUtils.put(ChangeInfoActivity.this, AppConfig.TYPE_NO, contant);
//                SplitWeb.getSplitWeb().WX_SNO = contant;
                changeinfoIvWrite.setVisibility(View.GONE);
                String json3 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                if (!StrUtils.isEmpty(json3)) {
                    DataMyZiliao.RecordBean record =new GsonParamConverter().json2Object(json3, DataMyZiliao.RecordBean.class);
                    record.setWxSno(contant);
                    record.setUpSnoNum("0");
                    String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                    aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                }
                changeinfoTvCount.setText(contant);
                break;
            case "upHeadImg":
                PersonalFragment.isChangeHead = true;
                DataSetHeadResult dataSetHeadResult =new GsonParamConverter().json2Object(responseText, DataSetHeadResult.class);
                if (dataSetHeadResult != null) {
                    String headImg = dataSetHeadResult.getRecord().getHeadImg();
//                    String substring = headImg.substring(22);
                    if (!StrUtils.isEmpty(headImg)){
                        String s = headImg.contains("_")?"yes" : "no";
                        MyLog.i("imageBase64","------------changeInfoActivity--------------"+s);
//                        SplitWeb.getSplitWeb().USER_HEADER = headImg;
                        aCache.put(AppConfig.IMAGE_BASE64, headImg);
                        aCache.put(AppAllKey.User_HEAD_URL, headImg);
                        HeadImgInfo headImgInfo = new HeadImgInfo();
                        headImgInfo.setHeadImgBase64(headImg);
                        EventBus.getDefault().post(headImgInfo);
//                        EventBus.getDefault().postSticky(headImgInfo);


                        String json4 = aCache.getAsString(AppAllKey.PPERSON_iNFO);
                        if (!StrUtils.isEmpty(json4)) {
                            DataMyZiliao.RecordBean record =new GsonParamConverter().json2Object(json4, DataMyZiliao.RecordBean.class);
                            record.setHeadImg(headImg);
                            String jsonString = MyJsonUtils.toChangeJson(record);//将java对象转换为json对象
                            aCache.put(AppAllKey.PPERSON_iNFO, jsonString);
                        }else {
                            String sNo = (String) SPUtils.get(ChangeInfoActivity.this, AppConfig.TYPE_NO, "");
                            String qrCode = aCache.getAsString(AppConfig.QR_CODE);
                            String nickName = aCache.getAsString(AppAllKey.User_NI_NAME);
                            DataMyZiliao.RecordBean recordBean = new DataMyZiliao.RecordBean();
                            recordBean.setHeadImg(headImg);
                            recordBean.setNickName(nickName);
                            recordBean.setWxSno(sNo);
                            recordBean.setQrcode(qrCode);
                            String mData = MyJsonUtils.toChangeJson(recordBean);
                            aCache.put(AppAllKey.PPERSON_iNFO,mData);
                        }
                    }
                }
                break;
        }
    }

    private Bitmap photo;

    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;

    private File mPhotoFile;
//    File save;
boolean isBackKey = false;
    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackKey = true;
        System.out.println("按下了back键   onBackPressed()");
    }

    /**
     * 调用相机以及相册的回调 获取的数据
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        //		相机
        if (requestCode == CAMERA_RESULT) {
//        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                bitmapOptions.inJustDecodeBounds = false;
                int be = (int) (bitmapOptions.outHeight / (float) 200);
                if (be <= 0)
                    be = 1;
                bitmapOptions.inSampleSize = be;

                bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                if(resultCode == Activity.RESULT_CANCELED) {
                    return;
                }
                if (isBackKey){
                    isBackKey = false;
                    return;
                }
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                goToClipActivity(Uri.fromFile(mPhotoFile));
//                Bitmap bm = ImageUtils.getBitmapCompress(mPhotoFile.getPath());
//                String s1 = ImageUtils.Bitmap2StrByBase64(bm);
////                save = ImageUtils.saveBitmap(ChangeInfoActivity.this, bm);
////                sendWeb(SplitWeb.getSplitWeb().upHeadImg(ImageUtils.GetStringByImageView(bm)));
//                sendWeb(SplitWeb.getSplitWeb().upHeadImg(s1));
            }
        }
        //		相册
        if (requestCode == REQUEST_PICK && null != data) {
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (isBackKey){
                isBackKey = false;
                return;
            }
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
//            save = ImageUtils.saveBitmap(ChangeInfoActivity.this, bitmap);
//            final Map<String, File> files = new HashMap<String, File>();
//            files.put("file", save);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            c.close();
            goToClipActivity(selectedImage);
        }
        if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = HeadFileUtils.getRealFilePathFromUri(getApplicationContext(), uri);
            // 高清头像
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            String OriginBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap,400));
            //TODO 压缩头像
//            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap));
            String totalBase64 = OriginBase64 + "_" + compressBase64;
            ImageUtils.useBase64(ChangeInfoActivity.this, changeinfoIvHead, compressBase64);
            //TODO 提交头像
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(totalBase64));

        }
    }

    String mTmpPath;

    /**
     * 7.0 拍照权限
     * 我是直接提取成一个方法了
     */
    public void getPicturesFile() {
        mPhotoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chat_image/" + System.currentTimeMillis() + ".jpg");
        try {
            mPhotoFile.getParentFile().mkdirs();
            mPhotoFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTmpPath = mPhotoFile.getAbsolutePath();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        //判断一下当前的系统版本，然后在分配权限
        if (Build.VERSION.SDK_INT >= 24) {
            //Android 7.0权限申请
            ContentValues contentValues = new ContentValues(1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ChangeInfoActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
            }
            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_RESULT);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
            startActivityForResult(intent, CAMERA_RESULT);
        }
    }

    String contant = null;

    //修改签名等的回调按钮
    @Override
    public void onSure(String contant) {
        this.contant = contant;

        //TODO 提交三个请求
//        switch (isChangeName) {
//            case "0"://昵称
//                sendWeb(SplitWeb.getSplitWeb().upNickName(contant));
//                break;
//            case "1"://帐号
//                sendWeb(SplitWeb.getSplitWeb().upUserSno(contant));
//                break;
//            case "2"://签名
//                sendWeb(SplitWeb.getSplitWeb().upPersonSign(contant));
//                break;
//        }
    }

    @Override()
    public void onCancle() {

    }


}
