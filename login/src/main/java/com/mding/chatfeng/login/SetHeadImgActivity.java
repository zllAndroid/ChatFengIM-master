package com.mding.chatfeng.login;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_img_clip.ClipImgActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_popwindow.PhotoPopWindow;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.login.utils.StringCode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目：ChatFengIM-master
 * 文件描述：注册后，首次登录，跳转至的设置头像昵称界面
 * 作者：ljj
 * 创建时间：2019/6/12
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class SetHeadImgActivity extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_set_head_img);
//
//    }

    TextView topTvTitle;
    TextView topTvRight;
    EditText firstAddEdName;
    LinearLayout topLinBack;
    LinearLayout topLinRight;
    ImageView firstAddIvHeadImg;
    LinearLayout mLinMain;

    String userId;
    String headImg;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_head_img;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        topTvTitle = findViewById(R.id.include_top_tv_title);
        topTvRight = findViewById(R.id.include_top_tv_right);
        topLinBack = findViewById(R.id.include_top_lin_newback);
        topLinRight = findViewById(R.id.include_top_lin_right_text);
        firstAddEdName = findViewById(R.id.first_ed_name);
        firstAddIvHeadImg = findViewById(R.id.first_iv_head);
        mLinMain = findViewById(R.id.first_lin_main);

        topTvTitle.setText(getResources().getString(R.string.first_add_title));
        topLinRight.setVisibility(View.VISIBLE);
        topLinBack.setVisibility(View.INVISIBLE);

        addOnClickListeners(R.id.first_btn_sure
                , R.id.include_top_tv_right
                , R.id.first_re_head
        );

        Intent intent = getIntent();
        if (intent != null){
            userId = intent.getStringExtra(StringCode.USER_ID);
        }
    }
    private PhotoPopWindow photoPopWindow = null;
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.first_btn_sure) {//TODO   请求注册设置头像昵称接口
            clickSure();

        } else if (i == R.id.include_top_tv_right) {//跳转到Home组件
            jump2Home();

        } else if (i == R.id.first_re_head) {
            if (photoPopWindow == null)
                photoPopWindow = new PhotoPopWindow(SetHeadImgActivity.this, MyClick);
            photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);

//            case R.id.first_btn_sure:
//                //TODO   请求注册设置头像昵称接口
//                clickSure();
//            break;
//
//            case R.id.include_top_tv_right:
//                //跳转到Home组件
//                jump2Home();
//            break;
//
//            case R.id.first_re_head:
//                if (photoPopWindow == null)
//                    photoPopWindow = new PhotoPopWindow(SetHeadImgActivity.this, MyClick);
//                photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
//            break;
        }
//        int v_Id = v.getId();
//        if (v_Id == R.id.first_btn_sure){
//            // 请求注册设置头像昵称接口
//            clickSure();
//        }
//        else if (v_Id == R.id.include_top_tv_right){
//            // 跳转到Home组件
//            jump2Home();
//        }
//        else if (v_Id == R.id.first_re_head){
//            // 设置头像
//
//        }

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
//                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    startActivityForResult(i, RESULT_LOAD_IMAGE);

                //权限判断
                if (ContextCompat.checkSelfPermission(SetHeadImgActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(SetHeadImgActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到相册
                    goToAlbum();
                }
                photoPopWindow.dismiss();

            }

//            switch (v.getId()) {
//                case R.id.btn_open_cramre:
//                    destoryImage();
//                    getPicturesFile();
//                    break;
//                case R.id.btn_open_xaingce:
//                    //	相册
////                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                    startActivityForResult(i, RESULT_LOAD_IMAGE);
//
//                    //权限判断
//                    if (ContextCompat.checkSelfPermission(SetHeadImgActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                            != PackageManager.PERMISSION_GRANTED) {
//                        //申请READ_EXTERNAL_STORAGE权限
//                        ActivityCompat.requestPermissions(SetHeadImgActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                READ_EXTERNAL_STORAGE_REQUEST_CODE);
//                    } else {
//                        //跳转到相册
//                        goToAlbum();
//                    }
//                    photoPopWindow.dismiss();
//                    break;
//                default:
//                    break;
//            }
        }
    };

    private int CAMERA_RESULT = 100;
    //请求相册
    private static final int REQUEST_PICK = 401;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 402;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 303;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 404;

    private Bitmap photo;
    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        Log.d("==image==", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 7.0 拍照权限
     * 我是直接提取成一个方法了
     */
    String mTmpPath;
    private File mPhotoFile;
    public void getPicturesFile(){
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
                ActivityCompat.requestPermissions(SetHeadImgActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
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

    boolean isBackKey = false;
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
                File saveBitmap = ImageUtils.saveBitmap(SetHeadImgActivity.this, bitmap);
                final Map<String, File> files = new HashMap<String, File>();
                files.put("file", saveBitmap);
//                UpLoadIdCard(requestCode,files,CAMERA_RESULT_Btn1);
                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
////                pdIvHead.setBackgroundResource(0);
//                Glide.with(this).load(saveBitmap)
//                        .bitmapTransform(new CropCircleTransformation(SetHeadImgActivity.this))
//                        .into(firstAddIvHeadImg);
////                Glide.with(ChangeInfoActivity.this).load(drawable.getBitmap()).;
////                changeinfoIvHead.setImageBitmap(drawable.getBitmap());
////                SendDataImg(files);
////                imageBase64=ImageUtils.GetStringByImageView(bitmap);
                goToClipActivity(Uri.fromFile(mPhotoFile));
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
//            File saveBitmap = null;
//                saveBitmap = ImageUtil.saveFile(bitmap);
            if (isBackKey){
                isBackKey = false;
                return;
            }
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
//            save = ImageUtils.saveBitmap(FirstAddHeaderActivity.this, bitmap);
//            final Map<String, File> files = new HashMap<String, File>();
//            files.put("file", save);
//            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//            Glide.with(this).load(save)
//                    .bitmapTransform(new CropCircleTransformation(FirstAddHeaderActivity.this))
//                   .into(firstIvHead);
//            //                Glide.with(this).load(saveBitmap)
////                        .bitmapTransform(new CropCircleTransformation(ChangeInfoActivity.this))
////                        .into(changeinfoIvHead);
////
////            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
////            SendDataImg(files);
////            mTvChange.setText("");
////            changeinfoIvHead.setImageBitmap(bitmap);
            c.close();
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(save));
//            imageBase64=ImageUtils.GetStringByImageView(bitmap);
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(ImageUtil.GetStringByImageView(bitmap)));
            goToClipActivity(selectedImage);
        }
        if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String s1 = ImageUtils.Bitmap2StrByBase64(bm);

            // 高清头像
            String OriginBase64 = ImageUtils.Bitmap2StrByBase64(ImageUtils.imageZoom(bitMap,400));
            //TODO 压缩头像
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(bm);
//            imageBase64 = OriginBase64 + "_" + compressBase64;

//            imageBase64 = s1;
            ImageUtils.useBase64(SetHeadImgActivity.this, firstAddIvHeadImg, s1);
//            sendWeb(SplitWeb.getSplitWeb().upHeadImg(s1));

        }
    }

    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        }
        else if (ContentResolver.SCHEME_FILE.equalsIgnoreCase(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equalsIgnoreCase(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
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
                // Permission Granted
//                goToCamera();
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                goToAlbum();
            }
        }
    }

    private void clickSure() {
//        String nickName = firstAddEdName.getText().toString().trim();
//        create.getComsApi().doCommon(create.login().controllersName, create.login().setHeadImg, new setHeadImgBody(userId, nickName, headImg)).build().callAsync(new IComponentCallback() {
//            @Override
//            public void onResult(CC cc, CCResult result) {
//                if (result.isSuccess()){
//                    //跳转到Home组件
//                    jump2Home();
//                }
//            }
//        });
    }

    public void jump2Home() {
        CC.obtainBuilder("HomeCom")
                .setActionName("MainActivity")
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行

                    }
                });
//        Toast.makeText(this,"123",Toast.LENGTH_SHORT).show();

    }
}
