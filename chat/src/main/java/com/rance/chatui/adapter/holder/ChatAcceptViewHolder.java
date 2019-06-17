package com.rance.chatui.adapter.holder;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.base_common.components.model.DataJieShou;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.TimeUtil;
import com.mding.chatfeng.base_common.utils.aboututils.MyLog;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.chat.R;
import com.rance.chatui.ui.ChatAdapter;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.GifTextView;


//接收
public class ChatAcceptViewHolder extends BaseViewHolder<DataJieShou.RecordBean> {

    TextView chatItemDate;
    ImageView chatItemHeader;
    GifTextView chatItemContentText;
    ImageView chatItemContentImage;
    ImageView chatItemVoice;
    LinearLayout chatItemLayoutContent;
    TextView chatItemVoiceTime;
    private ChatAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;

    Context context;
    protected boolean isScrolling = false;
//    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
//    RealmFriendUserHelper realmFriendUserHelper;
//    RealmFriendRelationHelper friendHelper;
    public ChatAcceptViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler,boolean isScrolling ,Context context) {
        super(parent, R.layout.item_chat_accept);
//        ButterKnife.bind(this,itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        this.isScrolling = isScrolling;
        this.context = context;
//        if (realmMsgInfoTotalHelper ==null)
//            realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(getContext());
//        if (friendHelper==null)
//            friendHelper = new RealmFriendRelationHelper(getContext());
//        if (realmFriendUserHelper==null)
//            realmFriendUserHelper = new RealmFriendUserHelper(getContext());
    }
    @Override
    public void setData(final DataJieShou.RecordBean data) {
        chatItemDate= itemView.findViewById(R.id.chat_item_date);
        chatItemHeader= itemView.findViewById(R.id.chat_item_header);
        chatItemContentText= itemView.findViewById(R.id.chat_item_content_text);
        chatItemContentImage= itemView.findViewById(R.id.chat_item_content_image);
        chatItemVoice= itemView.findViewById(R.id.chat_item_voice);
        chatItemLayoutContent= itemView.findViewById(R.id.chat_item_layout_content);
        chatItemVoiceTime= itemView.findViewById(R.id.chat_item_voice_time);

        if (StrUtils.isEmpty(data.getRequestTime()))
        {
            chatItemDate.setVisibility(View.GONE);
        }else
        {
            chatItemDate.setText(TimeUtil.formatDisplayTime(data.getRequestTime(),null));
            chatItemDate.setVisibility(View.VISIBLE);
        }

//        if (!StrUtils.isEmpty(data.getFriendsId())) {
//            String imgPath = realmFriendUserHelper.queryLinkFriendReturnImgPath(data.getFriendsId());
//            ImageUtils.useBase64(getContext(),chatItemHeader,imgPath);
//        }
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(), Constants.CHAT_ITEM_TYPE_LEFT,data.getFriendsId());
            }
        });
        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickListener.onConClick(view,getAdapterPosition(),data.getMessage());
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
                case Constants.CHAT_PICTURE:
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemLayoutContent.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.VISIBLE);
                    // TODO 显示接收的图片
                    String message = data.getMessage();
                    final String[] split = message.split("_");
                    ImageUtils.useBase64ToBitmap(getContext(),chatItemContentImage,split[1]);
                    MyLog.e("ChatSendViewHolder","------------------------ChatAccept-------------------------"+split[1]);
//                    Glide.with(getContext()).load(data.getMessage()).into(chatItemContentImage);
                    chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onImageClick( getDataPosition(), split[1]);
                        }
                    });
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
    }
}
