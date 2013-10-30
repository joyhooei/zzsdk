package com.zz.sdk.layout;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zz.sdk.ParamChain;
import com.zz.sdk.ParamChain.KeyGlobal;
import com.zz.sdk.ParamChain.KeyUser;
import com.zz.sdk.entity.result.BaseResult;
import com.zz.sdk.entity.result.ResultBalance;
import com.zz.sdk.util.ConnectionUtil;
import com.zz.sdk.util.DebugFlags;
import com.zz.sdk.util.Logger;
import com.zz.sdk.util.ResConstants.CCImg;
import com.zz.sdk.util.ResConstants.Config.ZZDimen;
import com.zz.sdk.util.ResConstants.Config.ZZFontColor;
import com.zz.sdk.util.ResConstants.Config.ZZFontSize;
import com.zz.sdk.util.ResConstants.ZZStr;

/**
 * 充值中心的模板界面。 功能
 * <ul>
 * <li>余额
 * <ul>
 * <li>显示
 * <li>查询
 * </ul>
 * <li>订单
 * <li>帮助
 * <ul>
 * 
 * @author nxliao
 * 
 */
abstract class CCBaseLayout extends BaseLayout {

	static final boolean DEBUG_UI = false; // DebugFlags.DEBUG;

	static enum IDC implements IIDC {

		/** 页眉， {@link FrameLayout} */
		ACT_HEADER,

		/** 余额 */
		BT_BALANCE,

		/** 余额描述文本 */
		TV_BALANCE,

		/** 余额刷新等待进度条 */
		PB_BALANCE,

		/** 页脚， {@link FrameLayout} */
		ACT_FOOTER,

		/** 帮助按钮 */
		BT_HELP, //

		_MAX_;

		protected final static int __start__ = BaseLayout.IDC._MAX_.id();

		public final int id() {
			return ordinal() + __start__;
		}

		/** 从 id 反查，如果失败则返回 {@link #_MAX_} */
		public final static IDC fromID(int id) {
			id -= __start__;
			if (id >= 0 && id < _MAX_.ordinal()) {
				return values()[id];
			}
			return _MAX_;
		}
	}

	/** 余额 */
	private Double mCoinBalance;
	private ITaskCallBack mCoinbalanceCallback;

	public CCBaseLayout(Context context, ParamChain env) {
		super(context, env);
	}

	@Override
	protected void onInitEnv(Context ctx, ParamChain env) {
		mCoinBalance = env.getParent(KeyUser.class.getName()).getOwned(
				KeyUser.K_COIN_BALANCE, Double.class);
		mCoinbalanceCallback = new ITaskCallBack() {
			@Override
			public void onResult(AsyncTask<?, ?, ?> task, Object token,
					BaseResult result) {
				onUpdateBalanceResult(result);
			}
		};
	}

	private CharSequence getHelpTitle() {
		// return null == Application.topicTitle ? null : Html
		// .fromHtml(Application.topicTitle);
		String title = getEnv().get(KeyGlobal.K_HELP_TITLE, String.class);
		if (title != null)
			return Html.fromHtml(title);
		return null;
	}

	private CharSequence getHelpTopic() {
		String topic;
		// topic = Application.topicDes;
		topic = getEnv().get(KeyGlobal.K_HELP_TOPIC, String.class);
		if (topic != null) {
			return Html.fromHtml(ToDBC(topic));
		}
		return null;
	}

	/** 展示帮助说明内容 */
	protected void showPopup_Help() {
		Context ctx = mContext;
		LinearLayout ll = new LinearLayout(ctx);
		ll.setOrientation(VERTICAL);
		ll.setLayoutParams(new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
				Gravity.BOTTOM));
		ll.setBackgroundDrawable(CCImg.BACKGROUND.getDrawble(ctx));
		ll.setPadding(ZZDimen.dip2px(48), ZZDimen.dip2px(5),
				ZZDimen.dip2px(48), ZZDimen.dip2px(24));

		AnimationSet in = new AnimationSet(true);
		in.addAnimation(new AlphaAnimation(0f, 0.8f));
		in.addAnimation(new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
				Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1f,
				Animation.RELATIVE_TO_SELF, 0));
		in.setDuration(ANIMA_DUR_SHOW_POPUP_CHILD);
		ll.setAnimation(in);

		{
			TextView mTopicTitle;
			mTopicTitle = new TextView(ctx);
			ll.addView(mTopicTitle, new LayoutParams(LP_WW));
			mTopicTitle.setTextColor(0xffe7c5aa);
			mTopicTitle.setTextSize(16);
			mTopicTitle.setText(getHelpTitle());
		}
		{
			TextView mTopicDes;
			mTopicDes = new TextView(ctx);
			ll.addView(mTopicDes, new LayoutParams(LP_WW));
			mTopicDes.setTextSize(14);
			mTopicDes.setText(getHelpTopic());
		}
		showPopup(ll);
	}

	/** 服务器返回用户余额值 */
	private void onUpdateBalanceResult(BaseResult result) {
		if (isAlive()) {
			if (result instanceof ResultBalance && result.isSuccess()) {
				setCoinBalance(((ResultBalance) result).mZYCoin);
			}
			checkBalcanceState();
		}
	}

	/** 获取余额值，如果无记录则返回 0 */
	public double getCoinBalance() {
		return mCoinBalance == null ? 0 : mCoinBalance;
	}

	/** 设置余额值，如果数据有效(!=null)则更新到环境变量 */
	public void setCoinBalance(Double coinBalance) {
		if (coinBalance == null || coinBalance.equals(mCoinBalance)) {
			Logger.d("D: balance unchanged!");
			return;
		}
		getEnv().getParent(KeyUser.class.getName()).add(KeyUser.K_COIN_BALANCE,
				coinBalance);
		mCoinBalance = coinBalance;
		updateBalance();
	}

	/** 更新卓越币余额 */
	private void updateBalance() {
		updateBalance(getCoinBalance());
	}

	/** 更新卓越币余额 */
	protected void updateBalance(double count) {
		String str = String.format(ZZStr.CC_BALANCE_UNIT.str(),
				mRechargeFormat.format(count));
		set_child_text(IDC.TV_BALANCE, str);
	}

	/** 尝试打开余额刷新，如果启动了任务，则将显示等待 */
	private void tryUpdadteBalance() {
		String loginName = getEnv().get(KeyUser.K_LOGIN_NAME, String.class);
		if (loginName == null) {
			if (DEBUG) {
				showToast("D:need login");
			}
			Logger.d("need login");
			return;
		}
		BalanceTask.createAndStart(getConnectionUtil(), mCoinbalanceCallback,
				this, loginName);
		checkBalcanceState();
	}

	/** 检查余额刷新状态，如果有任务运行，则自动与之关联 */
	private void checkBalcanceState() {
		if (BalanceTask.isRunning()) {
			set_child_visibility(IDC.PB_BALANCE, VISIBLE);
			BalanceTask.bindCallback(mCoinbalanceCallback, this);
		} else {
			set_child_visibility(IDC.PB_BALANCE, GONE);
			setCoinBalance(getEnv().get(KeyUser.K_COIN_BALANCE, Double.class));
		}
	}

	@Override
	public void onClick(View v) {
		final int id = v.getId();
		IDC idc = IDC.fromID(id);
		switch (idc) {
		case BT_BALANCE: {
			tryUpdadteBalance();
		}
			break;
		case BT_HELP:
			showPopup_Help();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	@Override
	protected void initUI(Context ctx) {
		super.initUI(ctx);

		updateBalance();
	}

	/** 支付界面·主工作视图，页首：余额描述，页尾：帮助 */
	@Override
	protected View createView_subject(Context ctx) {
		// 主视图
		LinearLayout rv = new LinearLayout(ctx);
		rv.setPadding(ZZDimen.CC_ROOTVIEW_PADDING_LEFT.px(),
				ZZDimen.CC_ROOTVIEW_PADDING_TOP.px(),
				ZZDimen.CC_ROOTVIEW_PADDING_RIGHT.px(),
				ZZDimen.CC_ROOTVIEW_PADDING_BOTTOM.px());
		rv.setOrientation(LinearLayout.VERTICAL);
		rv.setBackgroundDrawable(CCImg.BACKGROUND.getDrawble(ctx));

		LinearLayout ll;
		TextView tv;

		// 余额描述
		{
			FrameLayout header = new FrameLayout(ctx);
			header.setId(IDC.ACT_HEADER.id());
			rv.addView(header, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));

			ll = new LinearLayout(ctx);
			header.addView(ll, new FrameLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			ll.setOrientation(HORIZONTAL);
			ll.setId(IDC.BT_BALANCE.id());
			ll.setOnClickListener(this);
			final int pv = ZZDimen.CC_SAPCE_PANEL_V.px();
			ll.setPadding(pv, pv, pv, pv / 4);
			if (DEBUG_UI) {
				ll.setBackgroundColor(0x80c06000);
			}

			tv = create_normal_label(ctx, ZZStr.CC_BALANCE_TITLE);
			ll.addView(tv, new LayoutParams(LP_WM));
			tv.setGravity(Gravity.CENTER);

			tv = create_normal_label(ctx, null);
			ll.addView(tv, new LayoutParams(LP_WM));
			tv.setId(IDC.TV_BALANCE.id());
			tv.setGravity(Gravity.CENTER);
			tv.setCompoundDrawablesWithIntrinsicBounds(null, null,
					CCImg.MONEY.getDrawble(ctx), null);
			ZZFontSize.CC_RECHARGE_BALANCE.apply(tv);
			if (DEBUG_UI) {
				tv.setBackgroundColor(0xc0c00000);
			}

			ProgressBar pb = new ProgressBar(ctx, null,
					android.R.attr.progressBarStyleSmallTitle);
			LayoutParams lp = new LayoutParams(LP_WM);
			lp.gravity = Gravity.CENTER_VERTICAL;
			ll.addView(pb, lp);
			pb.setId(IDC.PB_BALANCE.id());
			pb.setVisibility(GONE);
		}

		// 客户区
		{
			FrameLayout fl = new FrameLayout(ctx);
			fl.setId(BaseLayout.IDC.ACT_SUBJECT.id());
			rv.addView(fl, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT, 1.0f));
			if (DEBUG_UI) {
				fl.setBackgroundColor(0x803060c0);
			}
		}

		// 帮助区
		{
			FrameLayout footer = new FrameLayout(ctx);
			footer.setId(IDC.ACT_FOOTER.id());
			rv.addView(footer, new LayoutParams(LayoutParams.MATCH_PARENT,
					ZZDimen.dip2px(36)));

			ll = new LinearLayout(ctx);
			footer.addView(ll, new LayoutParams(LP_MM));
			ll.setOrientation(HORIZONTAL);
			ll.setId(IDC.BT_HELP.id());
			ll.setOnClickListener(this);
			if (DEBUG_UI) {
				ll.setBackgroundColor(0x80ff0000);
			}

			TextView tvHelp = new TextView(ctx);
			ll.addView(tvHelp, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT, 1.0f));
			tvHelp.setCompoundDrawablesWithIntrinsicBounds(
					CCImg.HELP.getDrawble(ctx), null, null, null);
			tvHelp.setText(ZZStr.CC_HELP_TITLE.str());
			tvHelp.setTextColor(ZZFontColor.CC_RECHARGE_HELP.color());
			tvHelp.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			tvHelp.setCompoundDrawablePadding(ZZDimen.dip2px(8));
			ZZFontSize.CC_RECHARGE_HELP.apply(tvHelp);
			if (DEBUG_UI) {
				tvHelp.setBackgroundColor(0x8000ff00);
			}

			TextView tvDesc = new TextView(ctx);
			ll.addView(tvDesc, new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT, 1.0f));
			tvDesc.setText(ZZStr.CC_HELP_TEL.str());
			tvDesc.setTextColor(ZZFontColor.CC_RECHARGE_HELP.color());
			tvDesc.setGravity(Gravity.CENTER_VERTICAL);
			ZZFontSize.CC_RECHARGE_HELP.apply(tvDesc);
			if (DEBUG_UI) {
				tvDesc.setBackgroundColor(0x800000ff);
			}
		}
		return rv;
	}

	@Override
	public boolean onEnter() {
		boolean ret = super.onEnter();
		if (ret) {
			if (mCoinBalance == null) {
				tryUpdadteBalance();
			} else {
				checkBalcanceState();
			}
		}
		return ret;
	}

	@Override
	protected void clean() {
		super.clean();

		BalanceTask.unBindCallback(mCoinbalanceCallback, this);
		mCoinbalanceCallback = null;
		mCoinBalance = null;
	}

	@Override
	public boolean onResume() {
		boolean ret = super.onResume();
		if (ret) {
			if (mCoinBalance == null) {
				tryUpdadteBalance();
			} else {
				checkBalcanceState();
			}
		}
		return ret;
	}

	/**
	 * 获取余额任务，这是一个单例任务，所有方法接口仅可在UI线程中调用。通过回调（单一）通知UI刷新。
	 */
	private static class BalanceTask extends
			AsyncTask<Object, Void, ResultBalance> {

		private static ResultBalance sLastBalance = null;
		private static BalanceTask sInstance = null;

		protected static boolean isRunning() {
			return sInstance != null;
		}

		protected static boolean bindCallback(ITaskCallBack callback,
				Object token) {
			if (sInstance != null) {
				sInstance.mCallback = callback;
				sInstance.mToken = token;
				return true;
			}
			return false;
		}

		protected static boolean unBindCallback(ITaskCallBack callback,
				Object token) {
			if (sInstance != null && sInstance.mCallback == callback
					&& sInstance.mToken == token) {
				sInstance.mCallback = null;
				sInstance.mToken = null;
				return true;
			}
			return false;
		}

		/**
		 * 创建并启动任务，
		 * <ul>
		 * <li>如果任务已经存在，则仅设置属性，并不启动。
		 * <li>如果上次结果未处理，则直接调用回调函数处理历史结果
		 * <li>否则，创建任务并启动
		 * </ul>
		 * 
		 * @param cu
		 * @param callback
		 * @param token
		 * @param loginName
		 * @return 是否有新任务启动
		 */
		protected static boolean createAndStart(ConnectionUtil cu,
				ITaskCallBack callback, Object token, String loginName) {
			if (sInstance != null) {
				sInstance.mCallback = callback;
				sInstance.mToken = token;
				if (DEBUG) {
					Logger.d("BalanceTask: sInstance!=null");
				}
				return false;
			} else if (sLastBalance != null) {
				ResultBalance balance = sLastBalance;
				sLastBalance = null;
				if (balance.isSuccess()) {
					callback.onResult(null, token, balance);
					if (DEBUG) {
						Logger.d("BalanceTask: sLastBalance!=null");
					}
					return false;
				}
			}
			sInstance = new BalanceTask();
			sInstance.execute(cu, loginName);
			sInstance.mCallback = callback;
			sInstance.mToken = token;
			if (DEBUG) {
				Logger.d("BalanceTask: created");
			}
			return true;
		}

		private ITaskCallBack mCallback;
		private Object mToken;

		@Override
		protected ResultBalance doInBackground(Object... params) {
			ConnectionUtil cu = (ConnectionUtil) params[0];
			String loginName = (String) params[1];
			if (DEBUG) {
				Logger.d("BalanceTask: run!");
			}
			if (DEBUG) {
				DebugFlags.debug_TrySleep(0, 4);
			}
			ResultBalance ret = cu.getBalance(loginName);
			return ret;
		}

		@Override
		protected void onPostExecute(ResultBalance result) {
			if (DEBUG) {
				Logger.d("BalanceTask: result = " + result);
			}
			sInstance = null;
			if (mCallback != null) {
				sLastBalance = null;
				mCallback.onResult(this, mToken, result);
			} else {
				sLastBalance = result;
			}
			mCallback = null;
			mToken = null;
		}
	}

}