package com.mding.chatfeng.home.fragment.top_pop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.mding.chatfeng.base_common.utils.about_main_utils.IntentUtils;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.about_search.AddFriendActivity;
import com.mding.chatfeng.home.ui.top_total.CreatGroupChatActivity;


/**
 * 项目：DoubleQ
 * 文件描述：主界面右上角加号点击后的弹窗
 * 作者：zll
 */
public class ConfirmPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View ll_chat, ll_friend,ll_Scan;

    public ConfirmPopWindow(Context context) {
        super(context);
        this.context = context;
        initalize();
    }
    private void initalize() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_pop, null);
        ll_chat = view.findViewById(R.id.fp_tv_qunliao);//发起群聊
        ll_friend = view.findViewById(R.id.fp_tv_add);//添加好友
        ll_Scan = view.findViewById(R.id.fp_tv_scan);//扫一扫
        ll_chat.setOnClickListener(this);
        ll_Scan.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        setContentView(view);
        initWindow();
    }
    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        this.setWidth((int) (d.widthPixels * 0.35));
        this.setHeight(LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
//        黑色透明背景
//        backgroundAlpha((Activity) context, 0.8f);//0.0-1.0
//        设置为全透明背景，相当于无背景
        backgroundAlpha((Activity) context, 1f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
    }

    //设置添加屏幕的背景透明度
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
    public void showAtBottom(View view) {
        //弹窗位置设置
        showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 10);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.fp_tv_qunliao) {
            IntentUtils.JumpTo(CreatGroupChatActivity.class);
//                Toast.makeText(context, "发起群聊", Toast.LENGTH_SHORT).show();
            dismiss();

        } else if (i == R.id.fp_tv_add) {
            IntentUtils.JumpTo(AddFriendActivity.class);
            dismiss();

        } else if (i == R.id.fp_tv_scan) {
//            TODO 扫描
//            Intent intent = new Intent(context, ScanActivity.class);
//            context.startActivity(intent);
//            IntentUtils.JumpTo(ScanActivity.class);
//                IntentUtils.JumpTo(ActivityScanerCode.class);
            dismiss();

        } else {
        }
    }
}
