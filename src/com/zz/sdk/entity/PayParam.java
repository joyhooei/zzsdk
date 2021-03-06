package com.zz.sdk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.zz.sdk.ZZSDKConfig;
import com.zz.sdk.util.DebugFlags;
import com.zz.sdk.util.PaymentYDMMUtil;

/**
 * @Description: 支付参数
 * @author roger
 */

public class PayParam implements Serializable, JsonParseInterface {
	private static final long serialVersionUID = 5L;
	public String loginName;

	public String gameRole;

	public String serverId;
	public String amount;
	public String projectId;
	public String requestId;
	public String channelId;
	public String cardNo;// 易宝充值卡号码
	public String cardPassword; // 易宝充值卡密码
	public String type; // 易宝充值类型
	public String callBackInfo;
	public String part;// 请求路径
	public String smsActionType; // 短信请求类型 1获取通道 2提交订单
	public String smsImsi;
	public String cardAmount;

	/**
	 * [20131018] 充值方式
	 * <nl>
	 * <li>0或不传走原来方式；
	 * <li>1充卓越币
	 * </nl>
	 */
	public String way;

	/** 附加参数，直接使用 */
	public HashMap<String, String> attachParam;

	@Override
	public String toString() {
		return "Charge [gameRole=" + gameRole + ", serverId=" + serverId
				+ ", amount=" + amount + ", projectId=" + projectId
				+ ", cardNo=" + cardNo + ", cardPassword=" + cardPassword
				+ ", callBackInfo=" + callBackInfo + "]";
	}

	@Override
	public JSONObject buildJson() {
		try {
			JSONObject json = new JSONObject();
			json.put("loginName", loginName);
			json.put("gameRole", gameRole);
			json.put("serverId", serverId);
			json.put("amount", amount);
			json.put("projectId", projectId);
			json.put("requestId", requestId);
			json.put("channelId", channelId);
			json.put("cardNo", cardNo);
			json.put("cardPassword", cardPassword);
			json.put("type", type);
			json.put("callBackInfo", callBackInfo);
			json.put("smsActionType", smsActionType);
			json.put("smsImsi", smsImsi);
			json.put("cardAmount", cardAmount);
			return json;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void parseJson(JSONObject json) {
		if (json == null)
			return;
		try {
			loginName = json.isNull("loginName") ? null : json
					.getString("loginName");
			gameRole = json.isNull("gameRole") ? null : json
					.getString("gameRole");
			serverId = json.isNull("serverId") ? null : json
					.getString("serverId");
			amount = json.isNull("amount") ? null : json.getString("amount");
			projectId = json.isNull("projectId") ? "-1" : json
					.getString("projectId");
			requestId = json.isNull("requestId") ? "-1" : json
					.getString("requestId");
			channelId = json.isNull("channelId") ? "-1" : json
					.getString("channelId");
			cardNo = json.isNull("cardNo") ? null : json.getString("cardNo");
			cardPassword = json.isNull("cardPassword") ? null : json
					.getString("cardPassword");
			type = json.isNull("type") ? null : json.getString("type");
			callBackInfo = json.isNull("callBackInfo") ? null : json
					.getString("callBackInfo");
			smsActionType = json.isNull("smsActionType") ? null : json
					.getString("smsActionType");
			smsImsi = json.isNull("smsImsi") ? null : json.getString("smsImsi");
			cardAmount = json.isNull("cardAmount") ? null :json.getString("cardAmount");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getShortName() {
		return "c";
	}

	// :%s/\t.*$//g
	// :sort
	// :%s/^\(.*\)\(\n\1\)\+$/\1/
	// :%s/\(.*\)/public static final String K_\U\1 = "\l\1";/g
	public static final String K_AMOUNT = "amount";
	public static final String K_CARDNO = "cardNo";
	public static final String K_CARDPWD = "cardPwd";
	public static final String K_GAMEROLE = "gameRole";
	public static final String K_IMSI = "imsi";
	public static final String K_LOGINNAME = "loginName";
	public static final String K_PROJECTID = "projectId";
	public static final String K_REQUESTID = "requestId";
	public static final String K_SERVERID = "serverId";
	public static final String K_TYPE = "type";
	public static final String K_CARD_NO = "cardNo";
	public static final String K_CARD_PWD = "cardPwd";
	public static final String K_WAY = "way";
	public static final String K_CARDACOUNT ="cardAmount";

	public ArrayList<BasicNameValuePair> getChargeParameters(int payType) {
		ArrayList<BasicNameValuePair> listParames = new ArrayList<BasicNameValuePair>();
		switch (payType) {
		case PayChannel.PAY_TYPE_ALIPAY:
		case PayChannel.PAY_TYPE_UNMPAY:
		case PayChannel.PAY_TYPE_EX_DEZF:
		case PayChannel.PAY_TYPE_TENPAY:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_GAMEROLE, gameRole));
			listParames.add(new BasicNameValuePair(K_SERVERID, serverId));
			listParames.add(new BasicNameValuePair(K_PROJECTID, projectId));
			listParames.add(new BasicNameValuePair(K_AMOUNT, amount));
			listParames.add(new BasicNameValuePair(K_REQUESTID, requestId));
			break;

		case PayChannel.PAY_TYPE_YEEPAY_LT:
		case PayChannel.PAY_TYPE_YEEPAY_YD:
		case PayChannel.PAY_TYPE_YEEPAY_DX:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_GAMEROLE, gameRole));
			listParames.add(new BasicNameValuePair(K_SERVERID, serverId));
			listParames.add(new BasicNameValuePair(K_PROJECTID, projectId));
			listParames.add(new BasicNameValuePair(K_TYPE, type));
			listParames.add(new BasicNameValuePair(K_AMOUNT, amount));
			listParames.add(new BasicNameValuePair(K_REQUESTID, requestId));
			listParames.add(new BasicNameValuePair(K_CARD_NO, cardNo));
			listParames.add(new BasicNameValuePair(K_CARD_PWD, cardPassword));
			listParames.add(new BasicNameValuePair(K_CARDACOUNT, cardAmount));
			break;

		case PayChannel.PAY_TYPE_KKFUNPAY:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_GAMEROLE, gameRole));
			listParames.add(new BasicNameValuePair(K_SERVERID, serverId));
			listParames.add(new BasicNameValuePair(K_PROJECTID, projectId));
			listParames.add(new BasicNameValuePair(K_REQUESTID, requestId));
			listParames.add(new BasicNameValuePair(K_IMSI, smsImsi));
			if (DebugFlags.DEBUG) {
				listParames.add(new BasicNameValuePair("ip", DebugFlags.DEF_DEBUG_IP));
			}
			listParames.add(new BasicNameValuePair(K_AMOUNT, amount));
			break;
		case PayChannel.PAY_TYPE_KKFUNPAY_NEW_FMM:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_IMSI, smsImsi));
			listParames.add(new BasicNameValuePair(K_PROJECTID, projectId));
			if (DebugFlags.DEBUG) {
				listParames.add(new BasicNameValuePair("ip", DebugFlags.DEF_DEBUG_IP));
			}
			// ua : 手机型号，如android
			// serviceType
			// spCode
			// command
			// price
			// ip
			break;
		case PayChannel.PAY_TYPE_KKFUNPAY_EX:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_IMSI, smsImsi));
			break;
		case PayChannel.PAY_TYPE_ZZCOIN:
			listParames.add(new BasicNameValuePair(K_LOGINNAME, loginName));
			listParames.add(new BasicNameValuePair(K_GAMEROLE, gameRole));
			listParames.add(new BasicNameValuePair(K_SERVERID, serverId));
			listParames.add(new BasicNameValuePair(K_PROJECTID, projectId));
			listParames.add(new BasicNameValuePair(K_AMOUNT, amount));
			listParames.add(new BasicNameValuePair(K_REQUESTID, requestId));
			break;
		}
		switch (payType) {
		case PayChannel.PAY_TYPE_ALIPAY:
			part = "pali.lg";
			break;
		case PayChannel.PAY_TYPE_UNMPAY:
		case PayChannel.PAY_TYPE_EX_DEZF:
			part = "pupmp.lg";
			break;
		case PayChannel.PAY_TYPE_TENPAY:
			part = "pten.lg";
			break;
		case PayChannel.PAY_TYPE_YEEPAY_LT:
		case PayChannel.PAY_TYPE_YEEPAY_YD:
		case PayChannel.PAY_TYPE_YEEPAY_DX:
			part = "pyee.lg";
			break;
		case PayChannel.PAY_TYPE_KKFUNPAY:
			if (ZZSDKConfig.SUPPORT_YDMM) {
				if (PaymentYDMMUtil.isValid()) {
					part = "pmm.lg";
					break;
				}
			}
			part = "pkkfun0.lg"; // "pkkfun.lg";
			break;
		case PayChannel.PAY_TYPE_KKFUNPAY_NEW_FMM:
			part = "pkfmm0.lg";
			break;
		case PayChannel.PAY_TYPE_KKFUNPAY_EX:
			part = "pkkfunnt0.lg"; //"pkkfunnt.lg";
			break;
		case PayChannel.PAY_TYPE_ZZCOIN:
			part = "pzy.lg";
			break;
		}

		if (attachParam != null) {
			Set<Entry<String, String>> s = attachParam.entrySet();
			for (Entry<String, String> e : s) {
				listParames
						.add(new BasicNameValuePair(e.getKey(), e.getValue()));
			}
		}

		if (way != null) {
			listParames.add(new BasicNameValuePair(K_WAY, way));
		}

		return listParames;
	}
}
