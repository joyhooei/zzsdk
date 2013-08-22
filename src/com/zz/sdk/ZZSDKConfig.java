package com.zz.sdk;

import com.zz.sdk.util.Constants;

/**
 * SDK 配置
 * 
 * @author nxliao
 * 
 */
public final class ZZSDKConfig {
	/** 使用360SDK */
	public final static boolean SUPPORT_360SDK = false;

	/** 支持豆趣的用户登录 */
	public final static boolean SUPPORT_DOUQU_LOGIN = true;

	/** 方向 */
	public final static int ORIENTATION = Constants.DIR_AUTO;

	/** 奇虎(360SDK)： 大话360测试 */
	public final static String QIHOO_PRODUCT_ID = "D1001";

	/* 版本信息 */

	/** 版本号，编译时更新，格式：1 */
	public final static int VERSION_CODE = 1;
	/** 版本名，编译时更新，格式：0.1.0 */
	public final static String VERSION_NAME = "1.0";
	/** 版本发布时间，编译时更新 ，格式：20130725 */
	public final static String VERSION_DATE = "20130726";
}
