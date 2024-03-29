package com.mding.chatfeng.home.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.billy.cc.core.component.IComponentCallback;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.HeadFileUtils;
import com.mding.chatfeng.home.R;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


public class ShowImgActivity extends BaseActivity  {
    TextView includeTopTvTitle;
    private ImageView clipViewLayout;
    //    private ClipViewLayout clipViewLayout;
    private TextView btnCancel;
    private TextView btnOk;

    String messageTypeImg = "2";
    public static String imgTotal;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_clip_image);
//        type = getIntent().getIntExtra("type", 1);
//        initView();
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_show_img;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        includeTopTvTitle.setText("大图预览");
        initView();
        clipViewLayout.setImageBitmap(bitmap);
    }


    /**
     * 初始化组件
     */
    public void initView() {
        includeTopTvTitle = findViewById(R.id.include_top_tv_title);
        clipViewLayout = findViewById(R.id.showImg_iv_full);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.bt_ok);
        //设置点击事件监听器
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

        //设置图片资源
        data = getIntent().getData();
//        clipViewLayout.setImageURI(getIntent().getData());
        bitmap = setImageSrc(data);
    }

    Uri data;

    @Override
    protected void onResume() {
        super.onResume();
//        //设置图片资源
//        data = getIntent().getData();
////        clipViewLayout.setImageURI(getIntent().getData());
//        bitmap = setImageSrc(data);

    }

    Bitmap bitmap;

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_cancel) {
            finish();

        } else if (i == R.id.bt_ok) {
            generateUriAndReturn();

        }
    }

    /**
     * 初始化图片
     */
    public Bitmap setImageSrc(final Uri uri) {
        //需要等到imageView绘制完毕再初始化原图
        ViewTreeObserver observer = clipViewLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
//                bitmap = initSrcPic(uri);
                clipViewLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        return initSrcPic(uri);
    }

    private Bitmap initSrcPic(Uri uri) {
        if (uri == null) {
            return null;
        }
        String path = HeadFileUtils.getRealFilePathFromUri(this, uri);
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        final Bitmap[] bitmap = {null};

        CC cc = CC.obtainBuilder("CCclipView")
                .setActionName("cclip").addParam("path", path)
                .build();
        CCResult call = cc.call();
        //这里decode出720*1280 左右的照片,防止OOM
        CC.obtainBuilder("CCclipView")
                .setActionName("cclip").addParam("path",path)
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行
                        Bitmap bitmap1 = result.getDataItem("bitmap");
                        bitmap[0] =bitmap1;
                    }
                });
//         bitmap =decodeSampledBitmap(path, 720, 1280);
        if (bitmap[0] == null) {
            return null;
        }
        final int[] rotation = new int[1];
        CC.obtainBuilder("CCclipView")
                .setActionName("ccrotation").addParam("path",path)
                .build()
                .callAsyncCallbackOnMainThread(new IComponentCallback() {
                    @Override
                    public void onResult(CC cc, CCResult result) {
                        //此onResult在主线程中运行
                         rotation[0] = result.getDataItem("rotation");
                    }
                });
        //竖屏拍照的照片，直接使用的话，会旋转90度，下面代码把角度旋转过来
//        int rotation = HeadFileUtils.getExifOrientation(path); //查询旋转角度
        Matrix m = new Matrix();
        m.setRotate(rotation[0]);
        bitmap[0] = Bitmap.createBitmap(bitmap[0], 0, 0, bitmap[0].getWidth(), bitmap[0].getHeight(), m, true);

        return bitmap[0];
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
////        调用返回剪切图
//        Drawable drawable = clipViewLayout.getDrawable();
//        Bitmap zoomedCropBitmap = ImageUtils.drawableToBitmap(drawable);

//        Bitmap zoomedCropBitmap = clipViewLayout.clip();
        if (bitmap == null) {
            Log.e("android", "bitmap == null");
            return;
        }
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), "cropped_" + System.currentTimeMillis() + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            Intent intent = new Intent();
            intent.setData(mSaveUri);
            setResult(RESULT_OK, intent);
            finish();
//            String cropImagePath = getRealFilePathFromUri(getApplicationContext(), mSaveUri);
//            // 原图
//            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
//            String imgNoZoom = ImageUtils.Bitmap2StrByBase64(bitMap);
//            // 压缩图
//            Bitmap bm = ImageUtils.imageZoom(bitMap);
//            String imgByZoom = ImageUtils.Bitmap2StrByBase64(bm);
//            // 发送图片（格式：压缩图片的base64_高清原图的base64）
//            imgTotal = imgByZoom + "_" + imgNoZoom;
//            String is_chat = SplitWeb.getSplitWeb().IS_CHAT;
//            String is_chat_group = SplitWeb.getSplitWeb().IS_CHAT_GROUP;
//            if (is_chat.equals("1")){
//                send(SplitWeb.getSplitWeb().privateSend(ChatActivity.FriendId, imgTotal, messageTypeImg, TimeUtil.getTime()));
//            }
//            else if (is_chat_group.equals("2")){
//                send(SplitWeb.getSplitWeb().groupSend(ChatGroupActivity.groupId, imgTotal, messageTypeImg, TimeUtil.getTime()));
//            }
//            finish();
        }
    }

//    @Override
//    public void receiveResultMsg(String responseText) {
//        super.receiveResultMsg(responseText);
//        String method = HelpUtils.backMethod(responseText);
//        switch (method) {
//            case Methon.PrivateSend:
//                DataJieShou dataJieShou = JSON.parseObject(responseText, DataJieShou.class);
//                DataJieShou.RecordBean recordBean = dataJieShou.getRecord();
//                ToastUtil.isDebugShow("发送图片成功");
//                break;
//            case Methon.GroupChatSend:
//                DataGroupChatResult dataGroupChatResult = JSON.parseObject(responseText, DataGroupChatResult.class);
//                DataGroupChatResult.RecordBean recordBean1 = dataGroupChatResult.getRecord();
//                ToastUtil.isDebugShow("发送群聊图片成功");
//                break;
//        }
//    }
}
