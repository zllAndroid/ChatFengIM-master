package com.rance.chatui.ui;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mding.chatfeng.base_common.components.model.DataJieShou;
import com.rance.chatui.adapter.holder.ChatAcceptViewHolder;
import com.rance.chatui.adapter.holder.ChatSendViewHolder;
import com.rance.chatui.util.Constants;

public class ChatAdapter extends RecyclerArrayAdapter<DataJieShou.RecordBean> {

    private onItemClickListener onItemClickListener;
    public Handler handler;
    public Context context;

    public ChatAdapter(Context context) {
        super(context);
        handler = new Handler();
        this.context=context;
    }
    protected boolean isScrolling = false;
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }
    public void deleteWho(int position) {
        getAllData().remove(position);
    }
    public void notifyAdapter(int position) {
//        getAllData().remove(position);
        notifyItemChanged(position);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.CHAT_ITEM_TYPE_LEFT:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler,isScrolling,context);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatSendViewHolder(parent, onItemClickListener, handler,isScrolling);
                break;
            default:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler,isScrolling,context);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getViewType(int position) {
        return getAllData().get(position).getType();
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onHeaderClick(int position, int type, String friendId);
        void onConClick(View v, int position, String conText);

        void onImageClick(int position, String imgHttp);

        void onVoiceClick(ImageView imageView, int position);
        void onAddFriendClick(boolean isCancleClick, int position);
    }
}
