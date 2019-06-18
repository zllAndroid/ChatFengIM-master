package com.mding.chatfeng.home.ui.about_mine.about_setting;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.home.R;

public class LaBlackActivity extends BaseActivity {
//    @BindView(R.id.include_top_tv_title)
    TextView includeTopTvTitle;
//    @BindView(R.id.inclu_tv_right)
    TextView includeTopTvRight;
//    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLin;
//    @BindView(R.id.black_recyc)
    RecyclerView mRecyclerView;

    String mShare= "1";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_black;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();
        initViewUI();

        includeTopTvTitle.setText(getResources().getString(R.string.block_list_title));
//        includeTopTvTitle.setText("屏蔽名单");
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(LaBlackActivity.this));
        // TODO  请求黑名单接口
//        sendWeb( SplitWeb.getSplitWeb().blackList());
    }

    private void initViewUI() {
        includeTopTvTitle = getView(R.id.include_top_tv_title);
        includeTopTvRight = getView(R.id.inclu_tv_right);
        includeTopLin = getView(R.id.include_top_lin_back);
        mRecyclerView = getView(R.id.black_recyc);

    }

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method)
        {
            case "blackList":
//                DataBlack dataBlack = JSON.parseObject(responseText, DataBlack.class);
//                List<DataBlack.RecordBean> record = dataBlack.getRecord();
//
//                if (record!=null)
//                {
//                    try {
//                        if (record.size()>0&&!StrUtils.isEmpty(record.get(0).getUserId()))
//                                initAdapter(record);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                break;
            case "removeBlack":
//                if (blackAdapter!=null)
//                {
//                    blackAdapter.delItem(positions);
//                }
//                ToastUtil.show("移除成功");
//                Log.e("position","点击了移除"+positions);
//                break;
        }
    }

    // TODO 处理黑名单 adapter
//    BlackAdapter  blackAdapter =null;
//    public int positions;
//    private void initAdapter( List<DataBlack.RecordBean> record) {
//        blackAdapter = new BlackAdapter(this,record);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        mRecyclerView.setAdapter(blackAdapter);
//        blackAdapter.notifyDataSetChanged();
//        blackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Log.e("position","点击了"+position);
//                positions=position;
////                DataBlack.RecordBean item = (DataBlack.RecordBean)adapter.getItem(position);
////                String user_id = item.getUserId();
////                if (!StrUtils.isEmpty(user_id))
//                // TODO  请求删除黑名单接口
//                    sendWeb(SplitWeb.getSplitWeb().removeBlack());
//            }
//        });
//
//    }
}
