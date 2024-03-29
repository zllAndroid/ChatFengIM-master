package com.rance.chatui.util;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.rance.chatui.adapter.EmotionGridViewAdapter;

/**
 * 项目：DoubleQ
 * 文件描述：点击表情全局监听管理类
 * 作者：zll
 */

public class GlobalOnItemClickManagerUtils {

    private static GlobalOnItemClickManagerUtils instance;
    private EditText mEditText;//输入框
    private static Context mContext;

    public static GlobalOnItemClickManagerUtils getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (GlobalOnItemClickManagerUtils.class) {
                if (instance == null) {
                    instance = new GlobalOnItemClickManagerUtils();
                }
            }
        }
        return instance;
    }

    public void attachToEditText(EditText editText) {
        mEditText = editText;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object itemAdapter = parent.getAdapter();

                if (itemAdapter instanceof EmotionGridViewAdapter) {
                    // 点击的是表情
                    EmotionGridViewAdapter emotionGvAdapter = (EmotionGridViewAdapter) itemAdapter;

                    if (position == emotionGvAdapter.getCount() - 1) {
                        // 如果点击了最后一个回退按钮,则调用删除键事件
                        mEditText.dispatchKeyEvent(new KeyEvent(
                                KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                    } else {
                        // 如果点击了表情,则添加到输入框中
                        String emotionName = emotionGvAdapter.getItem(position);

                        // 获取当前光标位置,在指定位置上添加表情图片文本
                        int curPosition = mEditText.getSelectionStart();
                        StringBuilder sb = new StringBuilder(mEditText.getText().toString());
                        sb.insert(curPosition, emotionName);

                        // 特殊文字处理,将表情等转换一下
                        mEditText.setText(Utils.getEmotionContent(mContext, mEditText, sb.toString()));

                        // 将光标设置到新增完表情的右侧
                        mEditText.setSelection(curPosition + emotionName.length());
                    }

                }
            }
        };
    }

}
