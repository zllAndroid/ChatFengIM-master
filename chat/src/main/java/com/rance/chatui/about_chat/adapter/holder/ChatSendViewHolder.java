package com.rance.chatui.about_chat.adapter.holder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.base_common.AppConfig;
import com.mding.chatfeng.base_common.components.base.BaseApplication;
import com.mding.chatfeng.base_common.components.model.DataJieShou;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.TimeUtil;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.ui.ChatAdapter;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;



public class ChatSendViewHolder extends BaseViewHolder<DataJieShou.RecordBean> {

    TextView chatItemDate;
    ImageView chatItemHeader;
    GifTextView chatItemContentText;
           ImageView chatItemContentImage;
    ImageView chatItemFail;
    ProgressBar chatItemProgress;
    ImageView chatItemVoice;
    LinearLayout chatItemLayoutContent;
    TextView chatItemVoiceTime;
    private ChatAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;

    protected boolean isScrolling = false;
    public ChatSendViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler,boolean isScrolling) {
        super(parent, R.layout.item_chat_send);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        this.isScrolling = isScrolling;
    }
    @Override
    public void setData(final  DataJieShou.RecordBean data) {
//        chatItemDate.setText("2018-08-08");
        initUI();
        if (StrUtils.isEmpty(data.getRequestTime()))
        {
            chatItemDate.setVisibility(View.GONE);
        }else
        {
            chatItemDate.setText(TimeUtil.formatDisplayTime(data.getRequestTime(),null));
            chatItemDate.setVisibility(View.VISIBLE);
        }

        String asString = BaseApplication.getaCache().getAsString(AppConfig.IMAGE_BASE64);
        if (!StrUtils.isEmpty(asString))
        {
            ImageUtils.useBase64(getContext(),chatItemHeader,asString);
            chatItemContentImage.setVisibility(View.VISIBLE);
        }

        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_RIGHT,data.getUserId());
            }
        });

        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onConClick(v,getAdapterPosition(),data.getMessage());
                return false;
            }
        });
//        chatItemContentText.setTextIsSelectable(true);
        try {
            switch (data.getMessageType())
            {
                case Constants.CHAT_TEXT:
                    chatItemContentText.setTextColor(Color.BLACK);
//                    chatItemContentText.setTextIsSelectable(true);
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_NO_FRIEND:
                    if (data.getMessage().length()>=14) {
                        SpannableString xunChengJi = new SpannableString(data.getMessage());
                        xunChengJi.setSpan(new ForegroundColorSpan(Color.parseColor("#1edec8")), 11, 15, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        chatItemDate.setText(xunChengJi, null);
                    }else
                    {
                        chatItemDate.setText(data.getMessage(), null);
                    }
                    chatItemDate.setVisibility(View.VISIBLE);

                    chatItemDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onAddFriendClick(true,getAdapterPosition());
                        }
                    });
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemHeader.setVisibility(View.GONE);
                    chatItemLayoutContent.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
//                    图片
                case Constants.CHAT_PICTURE:
                    initImg(data);
                    break;
                case Constants.CHAT_EMOTION:
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_FILE:
                    chatItemVoice.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.VISIBLE);
                    chatItemContentImage.setVisibility(View.GONE);
                    chatItemVoiceTime.setText("文件");
                    //                chatItemVoiceTime.setText(Utils.formatTime(data.getVoiceTime()));
                    chatItemLayoutContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onVoiceClick(chatItemVoice, getDataPosition());
                        }
                    });
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (data.getSendState()) {
            case Constants.CHAT_ITEM_SENDING:
                chatItemProgress.setVisibility(View.VISIBLE);
                chatItemFail.setVisibility(View.GONE);
                break;
            case Constants.CHAT_ITEM_SEND_ERROR:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.VISIBLE);
                break;
            case Constants.CHAT_ITEM_SEND_SUCCESS:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.GONE);
                break;
        }
    }

    private void initImg(DataJieShou.RecordBean data) {
        chatItemVoice.setVisibility(View.GONE);
        chatItemLayoutContent.setVisibility(View.GONE);
        chatItemVoiceTime.setVisibility(View.GONE);
        chatItemContentText.setVisibility(View.GONE);
        chatItemContentImage.setVisibility(View.VISIBLE);
        // TODO 显示发送的图片
        String message = data.getMessage();
        final String[] split = message.split("_");
//        Glide.with(getContext()).load(bitmap).into(chatItemContentImage);
        ImageUtils.useBase64ToBitmap(getContext(),chatItemContentImage,split[1]);
        MyLog.e("ChatSendViewHolder","------------------------ChatSend------------split[0]-------------"+split[0]);
        MyLog.e("ChatSendViewHolder","------------------------ChatSend------------split[1]-------------"+split[1]);
        chatItemContentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onImageClick( getDataPosition(), split[1]);
            }
        });
    }
    private void initUI() {
        chatItemDate= $(R.id.chat_item_date);
        chatItemHeader= $(R.id.chat_item_header);
        chatItemContentText=  $(R.id.chat_item_content_text);
        chatItemContentImage=  $(R.id.chat_item_content_image);
        chatItemVoice= $(R.id.chat_item_voice);
        chatItemLayoutContent=  $(R.id.chat_item_layout_content);
        chatItemVoiceTime=  $(R.id.chat_item_voice_time);
        chatItemProgress=  $(R.id.chat_item_progress);
        chatItemFail=  $(R.id.chat_item_fail);
    }

}
