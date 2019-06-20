package com.rance.chatui.about_chat;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.chat.R;


public class ShowFullImgActivity extends BaseActivity {
    ImageView fullImage;
    LinearLayout fullLay;
    private Drawable mBackground;

    @Override
    protected void initBaseView() {
        super.initBaseView();

        initUI();
        mBackground = new ColorDrawable(getResources().getColor(R.color.black_trans));
        Intent intent = getIntent();
        if (intent != null) {
            String image = intent.getStringExtra(AppConfig.IMAGE_BASE64);
            ImageUtils.useBase64Origin(ShowFullImgActivity.this, fullImage, image);
        }

    }

    private void initUI() {
        fullImage=   getView(R.id.full_image);
        fullLay=   getView(R.id.full_lay);
    }

    @Override
    protected boolean isGones() {
        return false;
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_show_full_img;
    }

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
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.full_image:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity(ShowFullImgActivity.this);
                        overridePendingTransition(0, 0);
                    }
                });
                break;
            case R.id.full_lay:
                activityExitAnim(new Runnable() {
                    @Override
                    public void run() {
                        AppManager.getAppManager().finishActivity(ShowFullImgActivity.this);
                        overridePendingTransition(0, 0);
                    }
                });
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void activityExitAnim(Runnable runnable) {
        fullImage.setPivotX(0);
        fullImage.setPivotY(0);
        fullImage.animate().scaleX(1).scaleY(1).translationX(0).translationY(0).
                withEndAction(runnable).
                setDuration(100).setInterpolator(new DecelerateInterpolator()).start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mBackground, "alpha", 180, 180);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setDuration(500);
        objectAnimator.start();
    }
}
