package com.mding.chatfeng.base_common.utils.about_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mding.chatfeng.base_common.R;


/**
 * Created by Administrator on 2017/10/20 0020.
 */

public  class CustomDialog extends Dialog {
    private TextView mMessageTv;
    private TextView mProgressTv;
    private Button mPositiveBtn;
    private Button mNegativeBtn;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;
//    private View mButtonDividerView;
private boolean isProgress;
    private String message;
    private String positiveButtonText;
    private String negativeButtonText;
    private OnClickListener positiveButtonClickListener;
    private OnClickListener negativeButtonClickListener;
    public CustomDialog(Context context) {
        super(context);
    }

//    public CustomDialog(Context context, int theme) {
//        super(context, theme);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom,null);
        mMessageTv = (TextView) findViewById(R.id.tv_dialog_message);
        mProgressTv = (TextView) findViewById(R.id.tv_dialog_message_progress);
        mPositiveBtn = (Button) findViewById(R.id.btn_dialog_positive);
        mNegativeBtn = (Button)findViewById(R.id.btn_dialog_negative);
        mProgressBar = findViewById(R.id.progressbar_dialog_update);
        mLinearLayout = findViewById(R.id.dialog_lin_progress);
//        mButtonDividerView = findViewById(R.id.view_dialog_button_divider);

        if (message != null) {
            mMessageTv.setText(message);
            mProgressTv.setText(message);
        }
        if (isProgress){
            mLinearLayout.setVisibility(View.VISIBLE);
            mMessageTv.setVisibility(View.GONE);
        }else {
            mLinearLayout.setVisibility(View.GONE);
            mMessageTv.setVisibility(View.VISIBLE);
        }
        if (positiveButtonText != null) {
            mPositiveBtn.setText(positiveButtonText);
            if (positiveButtonClickListener != null) {
                mPositiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveButtonClickListener.onClick(CustomDialog.this, Dialog.BUTTON_POSITIVE);
                    }
                });
            }
        } else {
            mPositiveBtn.setVisibility(View.GONE);
//            mButtonDividerView.setVisibility(View.GONE);
        }

        if (negativeButtonText != null) {
            mNegativeBtn.setText(negativeButtonText);
            if (negativeButtonClickListener != null) {
                mNegativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(CustomDialog.this, Dialog.BUTTON_NEGATIVE);
                    }
                });
            }
        } else {
            mNegativeBtn.setVisibility(View.GONE);
//            mButtonDividerView.setVisibility(View.GONE);
        }

    }

    private void setMessage(String msg) {
        message = msg;
    }

    private void setPositiveButtonText(String text) {
        positiveButtonText = text;
    }

    private void setNegativeButtonText(String text) {
        negativeButtonText = text;
    }

    private void setOnNegativeListener(OnClickListener listener) {
        negativeButtonClickListener = listener;
    }

    private void setOnPositiveListener(OnClickListener listener) {
        positiveButtonClickListener = listener;
    }

    private void setProgressBar(boolean isNeed) {
        isProgress=isNeed;

    }

    public static class Builder {
        private Context context;
        private String message;
        private boolean isSetProgress;
        private String positiveButtonText;
        private String negativeButtonText;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setProgressBar(boolean isSetProgress) {
            this.isSetProgress = isSetProgress;
            return this;
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int message) {
            this.message = context.getString(message);
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            return setPositiveButton(context.getString(positiveButtonText), listener);
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            return setNegativeButton(context.getString(negativeButtonText), listener);
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            CustomDialog dialog = new CustomDialog(context);

            if (dialog.isShowing() && null != dialog){
                dialog.dismiss();
            }
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
            dialog.setMessage(message);
            dialog.setProgressBar(isSetProgress);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setOnNegativeListener(negativeButtonClickListener);
            dialog.setOnPositiveListener(positiveButtonClickListener);
            return dialog;
        }
    }
}
