package com.mding.chatfeng.home.ui.about_msg.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.base_common.Constants;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.TimeUtil;
import com.mding.chatfeng.base_common.utils.aboututils.StrUtils;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_msg.about_swipe.BGASwipeItemLayout;

import java.util.List;

/**
 * 首页消息适配器
 */

public class MsgAdapter extends BaseQuickAdapter<CusHomeRealmData, BaseViewHolder> {
    Context context;
    public List<CusHomeRealmData> data;
    //    RealmFriendUserHelper realmFriendUserHelper;
//    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
//    RealmGroupHelper realmGroupHelper;
    public MsgAdapter(Context context, List<CusHomeRealmData> data, ItemTouchListener mItemTouchListener) {
        super(R.layout.item_home_message, data);
        this.data=data;
        this.context=context;
        this.mItemTouchListener=mItemTouchListener;
//        realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(context);
//        realmFriendUserHelper = new RealmFriendUserHelper(context);
//        realmGroupHelper = new RealmGroupHelper(context);
    }
    public void addData(CusHomeRealmData cusData) {
        data.add(0, cusData);
        notifyItemInserted(0);  //插入
    }
    public void removeData(int position) {
        data.remove(position);
        notifyItemRemoved(position);  //删除
    }
    public void notifyPosition(int position) {
        notifyItemRemoved(position);
    }
    public int getNumData() {
        int num=0;
        if (data!=null&&data.size()>0)
        {
            for (int i=0;i<data.size();i++)
            {
                CusHomeRealmData cusHomeRealmData = data.get(i);

                if (cusHomeRealmData!=null&&cusHomeRealmData.getMsgIsDisTurb()!=null&&!cusHomeRealmData.getMsgIsDisTurb().equals("2"))
                    num += cusHomeRealmData.getNum();
            }
        }
        return  num;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CusHomeRealmData item) {
//        CusDataLinkFriend linkFriend = realmMsgInfoTotalHelper.queryLinkFriend(item.getFriendId());
        helper.addOnClickListener(R.id.item_tv_click_ok);
        helper.addOnClickListener(R.id.item_msg_re);

        ImageView mIvHead = (ImageView) helper.getView(R.id.item_iv_head);
        ImageView mIvType = (ImageView) helper.getView(R.id.item_iv_group_type);
        TextView mTvNum = helper.getView(R.id.item_tv_num);
        String groupMemberName = "";
        try {
            if (!StrUtils.isEmpty(item.getFriendId())) {
//                String imgPath = realmMsgInfoTotalHelper.queryLinkFriendReturnImgPath(item.getFriendId());
                final int errorImg;
                String imgPath;
                String nickName;
//                获取头像和昵称
                if (item.getType().equals("1"))
                {
//                    imgPath = new RealmFriendUserHelper(context).queryLinkFriendReturnImgPath(item.getFriendId());
//                    errorImg=R.drawable.first_head_nor;
//                    nickName = new RealmFriendUserHelper(context).queryLinkFriendReturnname(item.getFriendId());//获取私聊好友名
                    mIvType.setVisibility(View.GONE);
                }else
                {
//                    imgPath =  new RealmGroupHelper(context).queryLinkFriendReturnImgPath(item.getFriendId());
//                    errorImg=R.drawable.qun_head;
//                    groupMemberName = new RealmFriendUserHelper(context).queryLinkFriendReturnname(item.getMemberId());
//                    nickName=  new RealmGroupHelper(context).queryLinkFriendReturnName(item.getFriendId());//获取群聊群名
                    mIvType.setVisibility(View.VISIBLE);
                }
//                 imgPath = realmMsgInfoTotalHelper.queryLinkFriendReturnImgPath(item.getFriendId());
//                ImageUtils.useBase64WithError(context,mIvHead,imgPath,errorImg);
//                helper.setText(R.id.item_tv_name,nickName);
            }
            String msg="";
            if (!StrUtils.isEmpty(item.getMessageType()))
                switch (item.getMessageType())
                {
                    case Constants.CHAT_PICTURE:
                        helper.setText(R.id.item_tv_msg,"[图片]");
                        break;
                    case Constants.CHAT_TEXT:
//                break;
                    default:
//                        TODO
                        /*if (!StrUtils.isEmpty(groupMemberName)&&item.getType().equals("2")&&!item.getMemberId().equals(SplitWeb.getSplitWeb().getUserId()))
                        {
                            msg= groupMemberName+"："+ item.getMsg();
                            helper.setText(R.id.item_tv_msg,msg);
                        }else
                        {
                            helper.setText(R.id.item_tv_msg,item.getMsg());
                        }*/
                        break;
                }
//            helper.setText(R.id.item_tv_msg,item.getMsg());

            helper.setText(R.id.item_tv_time, TimeUtil.formatDisplayTime(item.getTime(),null));
//          helper.setText(R.id.item_tv_time,item.getTime());
            int num =0;
            try {
                num =item.getNum();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (num==0) {
                mTvNum .setVisibility(View.INVISIBLE);
            }else
            {
                mTvNum .setVisibility(View.VISIBLE);
                if (item.getNum()>99) {
//                    mTvNum.setTextSize(context.getResources().getDimension(R.dimen.sp10));
                    helper.setText(R.id.item_tv_num,"99+");
                }else {
                    helper.setText(R.id.item_tv_num, String.valueOf(item.getNum()));
                }
            }

//        消息免打扰  的消息背景
            if(item.getMsgIsDisTurb()!=null)
                if (!item.getMsgIsDisTurb().equals("2"))
                    mTvNum.setBackgroundResource(R.drawable.linkman_news);
                else {
                    mTvNum.setBackgroundResource(R.drawable.news_disturb);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //添加数据
    public void delItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        //必须调用这行代码
        notifyItemRangeChanged(position, data.size());
    }
    String id;
    BGASwipeItemLayout swipeLayout;
    private ItemTouchListener mItemTouchListener;
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int positions) {
        super.onBindViewHolder(holder, positions);
        swipeLayout = (BGASwipeItemLayout) holder.getView(R.id.swipe_layout);
        final View lMenu = holder.getView(R.id.item_notice_del_menu);
        try {
//            //  删除后
//            if(swipeLayout.isOpened())
//            {
//                swipeLayout.close();
//            }

            click(swipeLayout, lMenu,positions,data.get(positions).getNickName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void click(final BGASwipeItemLayout swipeLayout, final  View view,final  int positions,String name) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemTouchListener.onLeftMenuClick(view,positions,id);
                    swipeLayout.close();
                }
            });
        }
    }

    public boolean getSwipeLayoutIsOpen() {
        return swipeLayout.isOpened();
    }
    public void colseBGASwipeItemLayout() {
        if (swipeLayout.isOpened())
            swipeLayout.close();
    }

    public  interface ItemTouchListener {
        void onLeftMenuClick(View view, int positions, String WaybillNum);
    }
}