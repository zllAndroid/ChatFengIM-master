package com.mding.chatfeng.base_common.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 项目：ChatFengIM-master
 * 文件描述：
 * 作者：zll
 * 创建时间：2019/6/13 0013
 * 修改人：
 * 更改时间：
 * 修改备注：
 */
public class EndView {
    private static Activity activity;
    // 运用了单例模式中的饿汉式
    private static final EndView endView = new EndView();

    public static EndView getEndView(Activity activitys) {
        setActivity(activitys);
        return endView;
    }

    private <T extends View> T getView(int id) {
        View mview = activity.findViewById(id);
        // activity.getWindow();

        return (T) mview;
    }

    // textview
    public EndView setTextView(int id, String txt) {
        TextView textView = getView(id);
        textView.setText(txt);
        return this;
    }

    // 图片
    public EndView setImgView(int id, int img) {
        ImageView imgs = getView(id);
        imgs.setImageResource(img);
        return this;
    }

    // 获取activity
    private static void setActivity(Activity activity) {
        EndView.activity = activity;
    }
}

