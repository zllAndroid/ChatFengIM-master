package com.mding.chatfeng.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.HandlerThread;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mding.chatfeng.base_common.R;
import com.mding.chatfeng.base_common.utils.about_custom.about_top_bar.FragmentTopBarLayout;
import com.mding.chatfeng.base_common.utils.about_key.AppAllKey;
import com.mding.chatfeng.base_common.utils.about_main_utils.HelpUtils;
import com.mding.chatfeng.base_common.utils.aboututils.ACache;
import com.mding.chatfeng.home.about_broadcastreceiver.MessageEvent;
import com.mding.chatfeng.home.fragment.top_pop.ConfirmPopWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 公共基类
 *   zll
 * @Time 2017-11-01
 */
public class BaseFragment extends Fragment  implements View.OnClickListener{

	public ACache mFragCache;
	String simpleName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (mFragCache==null)
			mFragCache = ACache.get(getActivity());
		simpleName = getClass().getSimpleName();
		EventBus.getDefault().register(this);
	}
	public HandlerThread myHandlerThread ;
//	public Handler handler ;
	View view =null;
//	boolean mIsPrepare = false;		//视图还没准备好
//	boolean mIsVisible= false;		//不可见
//	boolean mIsFirstLoad = true;	//第一次加载
	@SuppressLint("HandlerLeak")
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		if (view == null)
			view = inflater.inflate(setFragmentLayout(), container, false);

		initBaseUI(view);
		initTopBarEvent();
		return view;
	}
	public final <E extends View> E getView (int id) {
		try {
			return (E) view.findViewById(id);
		} catch (ClassCastException ex) {
			throw ex;
		}
	}
	public final void  addOnClickListeners (@IdRes int... ids) {
		try {
            for (@IdRes int id : ids)
                getView(id).setOnClickListener(this);
		} catch (ClassCastException ex) {
			throw ex;
		}
	}

    //	protected void addOnClickListeners(@IdRes int... ids) {
//		if (ids != null) {
//			for (@IdRes int id : ids) {
//				view.findViewById(id).setOnClickListener(this);
//			}
//		}
//	}
	protected void initBaseUI(View view) {

	}
	ConfirmPopWindow confirmPopWindow=null;


	private void initTopBarEvent() {
//		FragmentTopBarLayout mTopBar = view.findViewById(R.id.fg_top_bar);
		if (setFragmentTopBarLayout()!=null) {
			FragmentTopBarLayout mTopBar=setFragmentTopBarLayout();
			mTopBar.setTitle(setFragmentTital());
			mTopBar.setOnRightClick(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					if (confirmPopWindow == null)
						confirmPopWindow = new ConfirmPopWindow(getActivity());
					confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
				}
			});
			mTopBar.setOnSearchClick(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					searchClickEvent();
				}
			});
		}
//
//		mTopBar.setTopLinBackground(setTopBarBackground());
	}

	protected View getViewLayout() {
		return view;
	}
	protected int setTopBarBackground() {
		return 0;
	}
	protected int setFragmentLayout() {
		return com.mding.chatfeng.home.R.layout.fragment_msg;
	}

	protected void searchClickEvent() {
//		IntentUtils.JumpTo(LoadLinkManActivity.class);

	}

	protected FragmentTopBarLayout setFragmentTopBarLayout() {
		return getView(com.mding.chatfeng.home.R.id.fg_top_bar);
	}
	protected String setFragmentTital() {
		return "123";
	}

//	protected void sendWeb(String text) {
//		BaseApplication.getApp().sendData(text);
//	}
	@Override
	public void onStart() {
		super.onStart();

//		BaseFragment bs=new MsgFragment();
//		bs.
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//				View viewById = getActivity().findViewById(R.id.main_view_top);
//			// 设置状态栏高度
//			int statusBarHeight = WindowBugDeal.getStatusBarHeight(getActivity());
//			//这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
//			LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)viewById.getLayoutParams();
//			//获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
////        layout.setMargins(0,-statusBarHeight,0,0);
//			layout.height=statusBarHeight;
//			//设置button的新位置属性,left，top，right，bottom
//			viewById.setLayoutParams(layout);
//				if (viewById!=null)
//				viewById.setVisibility(View.VISIBLE);
////				else
////					viewById.setVisibility(View.GONE);
//		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		if (view != null) {
//			((ViewGroup) view.getParent()).removeView(view);
//		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		//释放资源
		try {
			myHandlerThread.quit() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventBus.getDefault().unregister(this);
	}

	protected String GetsimpleName() {
		return getClass().getSimpleName().toString();
	}
	protected boolean FragUseCaChe() {
		return false;
	}
//	protected void onFragmentHandleMessage(Message msg) {
//	}
	//订阅方法，当接收到事件的时候，会调用该方法
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(MessageEvent messageEvent){
		String isSucess = HelpUtils.HttpIsSucess(messageEvent.getMessage());
//		Log.e("onEvent","fragment"+messageEvent.getMessage());
		if (isSucess.equals(AppAllKey.CODE_OK))
			receiveResultMsg(messageEvent.getMessage());
//        textView.setText(messageEvent.getMessage());
	}
	public void receiveResultMsg(String responseText) {

	}


	@Override
	public void onClick(View view) {

	}
//	@Override
//	public void onNetSuccess(String msg) {
//		Message message = new Message();
//		if (!StrUtils.isEmpty(msg)&&!msg.equals(AppAllKey.NO_RESULT)) {
//			message.what = AppAllKey.HANDLE_MSG_SUCCESS;
//			if (FragUseCaChe())
//				mFragCache.put(simpleName, msg);
//		}
//		else
//			message.what = AppAllKey.HANDLE_MSG_FAILED;
//		message.obj = msg;
//		mHandlers.sendMessage(message);
//	}
}
