package com.mding.chatfeng.home.ui.about_contacts.about_contacts_adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mding.chatfeng.base_common.components.model.DataAddQunDetails;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.home.R;

import java.util.List;

public class GroupMemberAdapter extends BaseQuickAdapter<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean,BaseViewHolder> {

    List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> searchCityList;
    Context context;
    boolean isShowName;
    public GroupMemberAdapter(Context context, List<DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean> searchCityList,boolean isShowName)
    {
        super(R.layout.item_group_data_member,searchCityList);
        this.context = context;
        this.searchCityList=searchCityList;
        this.isShowName=isShowName;
    }
    @Override
    protected void convert(BaseViewHolder helper, DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item)
    {

    }

    @Override
    public void onBindViewHolder(BaseViewHolder helper, int positions) {
        super.onBindViewHolder(helper, positions);
//        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = searchCityList.get(positions);
//        Glide.with(context).load(item.getHead_img())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .crossFade(1000)
//                .error(R.drawable.first_head_nor)
//                .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
//        if (isShowName) {
//            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
//        } else {
//            helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
//        }
//        helper.setText(R.id.item_tv_group_member_name, item.getNick_name());
        ImageView mImg = helper.getView(R.id.item_iv_group_member_head);
        DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = searchCityList.get(positions);
        ImageUtils.useBase64(context,mImg,item.getHeadImg());
            if (isShowName) {
                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
            }
            helper.setText(R.id.item_tv_group_member_name, item.getNickName());
           if ((searchCityList.size()-1)==positions)
           {
               ImageUtils.useerror(context,(ImageView) helper.getView(R.id.item_iv_group_member_head),R.drawable.more_and_more);
               if (isShowName) {
                   helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
               } else {
                   helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
               }
               helper.setText(R.id.item_tv_group_member_name, "更多");
           }

//        if (positions<9) {
//            DataAddQunDetails.RecordBean.GroupDetailInfoBean.GroupUserInfoBean item = searchCityList.get(positions);
//            Glide.with(context).load(item.getHead_img())
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .crossFade(1000)
//                    .error(R.drawable.first_head_nor)
//                    .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
//            if (isShowName) {
//                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
//            } else {
//                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
//            }
//            helper.setText(R.id.item_tv_group_member_name, item.getNick_name());
//        }else if (positions==9)
//        {
//            Glide.with(context).load(R.drawable.more_and_more)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .crossFade(1000)
//                    .error(R.drawable.more_and_more)
//                    .into((ImageView) helper.getView(R.id.item_iv_group_member_head));
//            if (isShowName) {
//                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.VISIBLE);
//            } else {
//                helper.getView(R.id.item_tv_group_member_name).setVisibility(View.GONE);
//            }
//            helper.setText(R.id.item_tv_group_member_name, "更多");
//        }
    }
}
