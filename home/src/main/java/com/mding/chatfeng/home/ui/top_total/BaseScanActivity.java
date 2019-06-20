//package com.mding.chatfeng.home.ui.top_total;
//
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.SurfaceView;
//import android.widget.Toast;
//
//import com.google.zxing.Result;
//import com.google.zxing.client.android.AutoScannerView;
//import com.google.zxing.client.android.BaseCaptureActivity;
//import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
//import com.mding.chatfeng.home.R;
//
///**
// * 模仿微信的扫描界面
// */
//public class BaseScanActivity extends  BaseCaptureActivity {
//
//    private static final String TAG = BaseScanActivity.class.getSimpleName();
//
//    private SurfaceView surfaceView;
//    private AutoScannerView autoScannerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        AppManager.getAppManager().addActivity(this);
//        setContentView(R.layout.activity_scan);
//        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
//        autoScannerView = (AutoScannerView) findViewById(R.id.autoscanner_view);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        autoScannerView.setCameraManager(cameraManager);
//    }
//
//
//
//    @Override
//    public SurfaceView getSurfaceView() {
//        return (surfaceView == null) ? (SurfaceView) findViewById(R.id.preview_view) : surfaceView;
//    }
//
//    @Override
//    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
//        Log.i(TAG, "dealDecode ~~~~~ " + rawResult.getText() + " " + barcode + " " + scaleFactor);
//        playBeepSoundAndVibrate(true, false);
//        Toast.makeText(this, rawResult.getText(), Toast.LENGTH_LONG).show();
////        对此次扫描结果不满意可以调用
////        reScan();
//    }
//}
