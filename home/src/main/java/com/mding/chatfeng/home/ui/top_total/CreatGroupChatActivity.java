package com.mding.chatfeng.home.ui.top_total;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mding.chatfeng.base_common.components.base.BaseActivity;
import com.mding.chatfeng.base_common.components.model.DataCreatGroupChat;
import com.mding.chatfeng.base_common.components.model.DataCreatGroupResult;
import com.mding.chatfeng.base_common.utils.GsonParamConverter;
import com.mding.chatfeng.base_common.utils.HeadFileUtils;
import com.mding.chatfeng.base_common.utils.about_custom.LetterBar;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.about_main_utils.ImageUtils;
import com.mding.chatfeng.base_common.utils.aboutsystem.AppManager;
import com.mding.chatfeng.base_common.utils.aboututils.ToastUtil;
import com.mding.chatfeng.home.R;
import com.mding.chatfeng.home.ui.about_contacts.about_contacts_adapter.CreatGroupChatAdapter;
import com.mding.chatfeng.home.ui.about_msg.CusJumpGroupChatData;
import com.mding.chatfeng.home.ui.about_msg.PhotoPopWindow;
import com.mding.chatfeng.home.ui.adapter.CreatGroupSeachAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//位置：创建群聊
public class CreatGroupChatActivity extends BaseActivity {
    TextView includeTopTvTitle;
    TextView includeTopTvRight;
    LinearLayout includeTopLin;
    TextView mTvAbc;

    LetterBar mLetterBar;
    EditText seachEdInput;
    EditText mEdGroupName;
    ImageView seachIvClose;
    ImageView seachIvFind;
    ImageView creatIvHead;
    RecyclerView seachRecyc;
    LinearLayout seachLinList;

    //    未搜索到东西
    TextView seachTvNoSearch;
    RelativeLayout seachLinNoSearch;
    ExpandableListView mExList;
    LinearLayout mLinMain;
    LinearLayout mLinTop;
    TextView creatChatTvYixuanze;


    private void initUIView() {
        includeTopTvTitle= getView(R.id.include_top_tv_title);
         includeTopTvRight=getView(R.id.inclu_tv_right);

        includeTopLin=getView(R.id.include_top_lin_back);

        mTvAbc=getView(R.id.tv_abc);
        mLetterBar=getView(R.id.group_team_letter);
        seachEdInput=getView(R.id.seach_ed_input);
        mEdGroupName=getView(R.id.creat_chat_ed_groupname);
        seachIvClose=getView(R.id.seach_iv_close);
        seachIvFind=getView(R.id.seach_iv_find);
        creatIvHead=getView(R.id.creat_chat_tv_head);
        seachRecyc=getView(R.id.seach_recyc);
        seachLinList=getView(R.id.seach_lin_list);


        //    未搜索到东西
        seachTvNoSearch=getView(R.id.seach_tv_noSearch);
        seachLinNoSearch=getView(R.id.seach_lin_noSearch);
        mExList=getView(R.id.creat_exlist_friend);
        mLinMain=getView(R.id.group_chat_lin_main);
        mLinTop=getView(R.id.creat_chat_lin_top);
        creatChatTvYixuanze=getView(R.id.creat_chat_tv_yixuanze);

    }
    private Runnable runnable;
    String mShare = "1";
    //请求相册
    private static final int REQUEST_PICK = 401;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 402;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 403;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 404;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

//    LinearLayoutManager linearLayoutManager;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_creat_group_chat;
    }

    @Override
    protected void initBaseView() {
        super.initBaseView();

        initUIView();
        includeTopTvTitle.setText(getResources().getString(R.string.create_group_title));
        includeTopLin.setBackgroundColor(getResources().getColor(R.color.app_theme));
        includeTopTvRight.setVisibility(View.VISIBLE);
        includeTopTvRight.setText(getResources().getString(R.string.tv_sure));
//        includeTopTvRight.setText("确定");

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setNestedScrollingEnabled(false);
//        linearLayoutManager = new LinearLayoutManager(CreatGroupChatActivity.this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
        seachRecyc.setLayoutManager(new LinearLayoutManager(CreatGroupChatActivity.this));
//        mRecyclerView.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(GroupTeamActivity.this));
//        sendWeb( SplitWeb.blackList());
        initGroup();
    }



    private void initGroup() {
        initUI();
        initABC2();
//        initHttp();
//        TODO 网络请求
//        sendWeb(SplitWeb.getSplitWeb().getGroupWebInfo());
    }

    CreatGroupSeachAdapter mSeachAdapter;
    private ArrayList<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> searchCityList = new ArrayList<>();

    private void initUI() {
        mSeachAdapter = new CreatGroupSeachAdapter(CreatGroupChatActivity.this, searchCityList);
        seachRecyc.setAdapter(mSeachAdapter);
        initListenSearch();

        //设置EditText文本监听事件
        seachEdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //获取用户输入文本
                String putStr = seachEdInput.getText().toString();
                //搜索的Listview显示
                seachLinList.setVisibility(View.VISIBLE);
                searchCityList.clear();
                for (int a = 0; a < mFriendList.size(); a++) {
                    List<DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean> group_list = mFriendList.get(a).getGroupList();
                    for (int b = 0; b < group_list.size(); b++) {
                        String nick_name = group_list.get(b).getNickName();
                        if (nick_name.contains(putStr)) {
                            searchCityList.add(group_list.get(b));
                        }
                    }
                }
                //为搜索布局的显示隐藏
                if (searchCityList.size() == 0) {
                    seachLinNoSearch.setVisibility(View.VISIBLE);
                } else {
                    seachLinNoSearch.setVisibility(View.GONE);
                }

                //输入框为空时，返回城市列表
                if ("".equals(putStr)) {
                    searchCityList.clear();
                    seachLinList.setVisibility(View.GONE);
                    seachLinNoSearch.setVisibility(View.GONE);
                }
                //RecyclerView列表进行批量UI数据更新
//                mSeachAdapter.notifyItemRangeInserted(0,searchCityList.size());
//                // scrollToPosition（0）作用是把列表移动到顶端
//                seachRecyc.scrollToPosition(0);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (creatGroupChatAdapter!=null)
                        {
                            if (mList!=null&&mList.size()!=0)
                                mSeachAdapter.setChoose(mList);
                        }
                        mSeachAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void initListenSearch() {
//        List<String> checkSearch = mSeachAdapter.getCheckString();
//        if (creatGroupChatAdapter!=null) {
//            List<String> checkString1 = creatGroupChatAdapter.getCheckString();
//        }
//        mSeachAdapter.setCheckedChangeListener(new CreatGroupSeachAdapter.OnMyCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(String friendId, boolean isChecked) {
//                if (isChecked)
//                {
//                }
//            }
//        });
    }

    List<String> ABCList = new ArrayList<>();

    public void initABC2() {
        ABCList.clear();
//        ABCList.add("");
//        ABCList.add("⇧");
        for (char i = 'A'; i <= 'Z'; i++) {
            ABCList.add(i + "");
        }
        runnable = new Runnable() {
            public void run() {
                mTvAbc.setVisibility(View.INVISIBLE);
            }
        };
        mLetterBar.setonTouchLetterListener(new LetterBar.onTouchLetterListener() {
            @Override
            public void onTouchDown(String letter) {
                if (mTvAbc!=null) {
                    mTvAbc.removeCallbacks(runnable);
                    mTvAbc.setVisibility(View.VISIBLE);
                    mTvAbc.setText(letter);
                }
                if (letter.equals("⇧")) {
                    mExList.setSelection(0);
//                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
//                    mRecyclerView.scrollToPosition(0);
//                    mRecyclerView.setSelection(0);
                    return;
                }
                for (int i = 0; i < mFriendList.size(); i++) {
                    //获取所有城市的首字母
                    String firstLetter = getFirstABC
                            (mFriendList.get(i).getGroupName());
                    if (letter.equals(firstLetter)) {
                        mExList.setSelectedGroup(i);
                        break;
                    }
                }
            }
            @Override
            public void onTouchUp() {
                if (mTvAbc!=null)
                    mTvAbc.postDelayed(runnable, 1000);
            }
        });
    }
    public String getFirstABC(String pinyin) {
        String upperCase = pinyin.substring(0, 1).toUpperCase();
        return upperCase;
    }
    //    大列表选中的数据
    List<String> mList = new ArrayList<>();
    List<DataCreatGroupChat.RecordBean.FriendListBean> mFriendList = new ArrayList<>();
    DataCreatGroupResult.RecordBean record1;

    @Override
    public void receiveResultMsg(String responseText) {
        super.receiveResultMsg(responseText);
        String method = HelpUtils.backMethod(responseText);
        switch (method) {
            case "getGroupWebInfo":
                initGroupInfo(responseText);
                break;
//            case "groupSend":
//                if (record1!=null)
//                {
//
//                }
//                break;
//                创建群成功
            case "createdUserGroup":
//                在application处理
                DataCreatGroupResult dataCreatGroupResult = new GsonParamConverter().json2Object(responseText, DataCreatGroupResult.class);
                record1 = dataCreatGroupResult.getRecord();
                if (record1 != null) {
                    CusJumpGroupChatData cusJumpGroupChatData = new CusJumpGroupChatData();
                    cusJumpGroupChatData.setGroupId(record1.getGroupOfId());
                    cusJumpGroupChatData.setGroupName(record1.getGroupNickName());
//                    TODO 跳转群聊

//                    IntentUtils.JumpToHaveObj(ChatGroupActivity.class, Constants.KEY_FRIEND_HEADER, cusJumpGroupChatData);
                    AppManager.getAppManager().finishActivity(CreatGroupChatActivity.this);
                }
                break;
        }
    }

    private void initGroupInfo(String responseText) {
        DataCreatGroupChat dataCreatGroupChat = new GsonParamConverter().json2Object(responseText, DataCreatGroupChat.class);
        DataCreatGroupChat.RecordBean record = dataCreatGroupChat.getRecord();
        if (record != null) {
            List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list = record.getFriendList();
            mFriendList.clear();
            mFriendList.addAll(friend_list);
            if (friend_list.size() > 0)
                initAdapter(friend_list);
        }
    }

    CreatGroupChatAdapter creatGroupChatAdapter = null;

    private void initAdapter(final List<DataCreatGroupChat.RecordBean.FriendListBean> friend_list) {
        creatGroupChatAdapter = new CreatGroupChatAdapter(this, friend_list);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mExList.setAdapter(creatGroupChatAdapter);
        mExList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //设置点击不关闭（不收回）
                v.setClickable(true);
                return true;
            }
        });
        for (int i = 0; i < creatGroupChatAdapter.getGroupCount(); i++) {
            mExList.expandGroup(i);
        }
        creatGroupChatAdapter.setOnMyLinChangeListener(new CreatGroupChatAdapter.OnMyLinChangeListener() {
            @Override
            public void onCheckedChanged(String friendId, boolean isChecked) {
                if (isChecked)
                {
                    if (!mList.contains(friendId))
                        mList.add(friendId);
                }else
                {
                    if (mList.contains(friendId))
                        mList.remove(friendId);
                }
                creatChatTvYixuanze.setText("已选择"+mList.size()+"人");
                if (mList.size()>0) {
                    creatChatTvYixuanze.setVisibility(View.VISIBLE);
                    creatChatTvYixuanze.setTextColor(getResources().getColor(R.color.app_theme));

                }
                else {
                    creatChatTvYixuanze.setVisibility(View.INVISIBLE);
                    creatChatTvYixuanze.setTextColor(getResources().getColor(R.color.gray999));
                }
                List<String> checkString = creatGroupChatAdapter.getCheckString();
                Log.e("checkChat","friendId="+friendId+isChecked+"++++"+mList.toString()+"---"+checkString.toString());
            }
        });
//        mExList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
//                DataCreatGroupChat.RecordBean.FriendListBean.GroupListBean groupListBean = friend_list.get(groupPosition).getGroupList().get(childPosition);
////                String userId = mFriendList.get(groupPosition).getGroupList().get(childPosition).getUserId();
////                IntentUtils.JumpToHaveOne(FriendDataActivity.class,"id",userId);
//                Log.e("checkChat","groupListBean="+groupListBean.getNickName());
//
//                return false;
//            }
//        });
        creatGroupChatAdapter.notifyDataSetChanged();
    }

    private PhotoPopWindow photoPopWindow = null;

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i1 = view.getId();
        if (i1 == R.id.seach_iv_close) {
            seachEdInput.setText("");

//                点击头像
        } else if (i1 == R.id.creat_chat_tv_head) {
            if (photoPopWindow == null)
                photoPopWindow = new PhotoPopWindow(CreatGroupChatActivity.this, MyClick);
            photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);

//                点击确定
        } else if (i1 == R.id.inclu_tv_right) {
            List<String> checkString = creatGroupChatAdapter.getCheckString();
            if (checkString.size() > 0) {
//                    String check[]= new String[checkData.size()];
                String checkChat = "";
                for (int i = 0; i < checkString.size(); i++) {
                    if (i == 0) {
                        checkChat += checkString.get(i);
                    } else {
                        checkChat += "," + checkString.get(i);
                    }
//                        check[i]=checkData.get(i).getWx_sno();
                }
                Log.e("checkChat", "checkChat=" + checkChat);
//                    String replace =Arrays.toString(check).replace("[", "").replace("]", "").replace(" ","");
                String trim = mEdGroupName.getText().toString().trim();
//                TODO 创建群聊
//                sendWebHaveDialog(SplitWeb.getSplitWeb().createdUserGroup(checkChat, trim, imageBase64)
//                        , "创建中...", "群聊创建成功");
//                    sendWeb(SplitWeb.createdUserGroup(checkChat,"zll",""));
            } else {
                ToastUtil.show("请选择群聊成员");
            }


        }

    }

//    @OnClick({R.id.seach_iv_close,  R.id.inclu_tv_right, R.id.creat_chat_tv_head})
//    public void onViewClicked(View view) {
//        int i1 = view.getId();
//        if (i1 == R.id.seach_iv_close) {
//            seachEdInput.setText("");
//
////                点击头像
//        } else if (i1 == R.id.creat_chat_tv_head) {
//            if (photoPopWindow == null)
//                photoPopWindow = new PhotoPopWindow(CreatGroupChatActivity.this, MyClick);
//            photoPopWindow.showAtLocation(mLinMain, Gravity.NO_GRAVITY, 0, 0);
//
////                点击确定
//        } else if (i1 == R.id.inclu_tv_right) {
//            List<String> checkString = creatGroupChatAdapter.getCheckString();
//            if (checkString.size() > 0) {
////                    String check[]= new String[checkData.size()];
//                String checkChat = "";
//                for (int i = 0; i < checkString.size(); i++) {
//                    if (i == 0) {
//                        checkChat += checkString.get(i);
//                    } else {
//                        checkChat += "," + checkString.get(i);
//                    }
////                        check[i]=checkData.get(i).getWx_sno();
//                }
//                Log.e("checkChat", "checkChat=" + checkChat);
////                    String replace =Arrays.toString(check).replace("[", "").replace("]", "").replace(" ","");
//                String trim = mEdGroupName.getText().toString().trim();
//                sendWebHaveDialog(SplitWeb.getSplitWeb().createdUserGroup(checkChat, trim, imageBase64)
//                        , "创建中...", "群聊创建成功");
////                    sendWeb(SplitWeb.createdUserGroup(checkChat,"zll",""));
//            } else {
//                ToastUtil.show("请选择群聊成员");
//            }
//
//
//        }
//    }
    //为弹出窗口实现监听类
    public View.OnClickListener MyClick = new View.OnClickListener() {
        public void onClick(View v) {
            photoPopWindow.dismiss();
            int i1 = v.getId();
            if (i1 == R.id.btn_open_cramre) {
                destoryImage();
                getPicturesFile();

            } else if (i1 == R.id.btn_open_xaingce) {//	相册
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

            } else {
            }
        }
    };
    private Bitmap photo;

    private void destoryImage() {
        if (photo != null) {
            photo.recycle();
            photo = null;
        }
    }

    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;

    private File mPhotoFile;
    File save;

    String imageBase64 = "";
    boolean isBackKey = false;

    /**
     * 监听Back键按下事件,方法1:
     * 注意:
     * super.onBackPressed()会自动调用finish()方法,关闭
     * 当前Activity.
     * 若要屏蔽Back键盘,注释该行代码即可
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackKey = true;
        System.out.println("按下了back键   onBackPressed()");
    }
    /**
     * 调用相机以及相册的回调 获取的数据
     */
    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        //		相机
        if (requestCode == CAMERA_RESULT) {
//        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (mPhotoFile != null && mPhotoFile.exists()) {
                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                bitmapOptions.inJustDecodeBounds = true;
                Bitmap bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                bitmapOptions.inJustDecodeBounds = false;
                int be = (int) (bitmapOptions.outHeight / (float) 200);
                if (be <= 0)
                    be = 1;
                bitmapOptions.inSampleSize = be;
                bitmap = BitmapFactory.decodeFile(mPhotoFile.getPath(), bitmapOptions);
                if(resultCode == Activity.RESULT_CANCELED) {
                    return;
                }
                if (isBackKey){
                    isBackKey = false;
                    return;
                }
                if (bitmap==null)
                {
                    ToastUtil.show("不支持的图片，请重新选择");
                    return;
                }
                goToClipActivity(Uri.fromFile(mPhotoFile));
//                save = ImageUtils.saveBitmap(CreatGroupChatActivity.this, bitmap);
////                final Map<String, File> files = new HashMap<String, File>();
////                files.put("file", save);
//////                UpLoadIdCard(requestCode,files,CAMERA_RESULT_Btn1);
////                BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
//////                pdIvHead.setBackgroundResource(0);
//                imageBase64 = ImageUtils.GetStringByImageView(bitmap);
//                Glide.with(this).load(save)
//                        .bitmapTransform(new CropCircleTransformation(CreatGroupChatActivity.this))
//                        .into(creatIvHead);
////                Glide.with(ChangeInfoActivity.this).load(drawable.getBitmap()).;
////                changeinfoIvHead.setImageBitmap(drawable.getBitmap());
////                SendDataImg(files);
////                sendWeb(SplitWeb.upHeadImg(ImageUtil.GetStringByImageView(bitmap)));
            }
        }

        //		相册
        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            File saveBitmap = null;
//                saveBitmap = ImageUtil.saveFile(bitmap);
            if (isBackKey){
                isBackKey = false;
                return;
            }
            if (bitmap==null)
            {
                ToastUtil.show("不支持的图片，请重新选择");
                return;
            }
            save = ImageUtils.saveBitmap(CreatGroupChatActivity.this, bitmap);
            final Map<String, File> files = new HashMap<String, File>();
            files.put("file", save);
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            c.close();
//            Log.e(AppConstant.TAG,saveBitmap+"这个是图片的地址"+files);
//            SendDataImg(files);
//            mTvChange.setText("");
//            changeinfoIvHead.setImageBitmap(bitmap);

            goToClipActivity(selectedImage);
//            imageBase64 = ImageUtils.GetStringByImageView(bitmap);
//            Glide.with(this).load(save)
//                    .bitmapTransform(new CropCircleTransformation(CreatGroupChatActivity.this))
//                    .into(creatIvHead);
//            sendWeb(SplitWeb.upHeadImg(save));
//            sendWeb(SplitWeb.upHeadImg("123"));
//            sendWeb(SplitWeb.upHeadImg(ImageUtil.GetStringByImageView(bitmap)));
        }
        if (requestCode == REQUEST_CROP_PHOTO && null != data){
            final Uri uri = data.getData();
            if (uri == null) {
                return;
            }
            String cropImagePath = HeadFileUtils.getRealFilePathFromUri(getApplicationContext(), uri);
            // 高清头像
            Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
            String originBase64 = ImageUtils.Bitmap2StrByBase64(bitMap);
            //TODO 压缩头像
            Bitmap bm = ImageUtils.imageZoom(bitMap);
            String compressBase64 = ImageUtils.Bitmap2StrByBase64(bm);
            String totalImage = compressBase64 + "_" + originBase64;
            ImageUtils.useBase64(CreatGroupChatActivity.this, creatIvHead, compressBase64);
            imageBase64 = totalImage;
        }
    }
    /**
     * 跳转到相册
     */
    private void goToAlbum() {
        Log.d("==image==", "*****************打开图库********************");
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }
    /**
     * 打开截图界面
     */
    public void goToClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImgActivity.class);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
    /**
     * 外部存储权限申请返回
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                goToCamera();
                destoryImage();
                getPicturesFile();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                goToAlbum();
            }
        }
    }


    String mTmpPath;

    /**
     * 7.0 拍照权限
     * 我是直接提取成一个方法了
     */
    public void getPicturesFile() {
        mPhotoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/chat_image/" + System.currentTimeMillis() + ".jpg");
        try {
            mPhotoFile.getParentFile().mkdirs();
            mPhotoFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTmpPath = mPhotoFile.getAbsolutePath();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        //判断一下当前的系统版本，然后在分配权限
        if (Build.VERSION.SDK_INT >= 24) {
            //Android 7.0权限申请
            ContentValues contentValues = new ContentValues(1);
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CreatGroupChatActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_RESULT);
            }
            contentValues.put(MediaStore.Images.Media.DATA, mTmpPath);
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_RESULT);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTmpPath)));
            startActivityForResult(intent, CAMERA_RESULT);
        }
    }
}
