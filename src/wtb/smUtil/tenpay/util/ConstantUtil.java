package wtb.smUtil.tenpay.util;

public class ConstantUtil {
	/**
	 * 微信支付需要的信息
	 */
	//初始化
	public static String APP_ID = "wx374010a8213a12d0";//微信开发平台应用id
	public static String APP_SECRET = "7b6071747ab062ae9beb39ece4844361";//应用对应的凭证
	public static String PARTNER = "1367071302";//财付通商户号
	public static String PARTNER_KEY = "WuTuoBangAppKeyQueTingKangMobile";//商户号对应的密钥

	/**
	 * 微信提现信息
	 */
	public static String WXPay_TansfersUrl="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	public static String WXPay_APPID="wxe2c7726175dcc617";//校播

	/**
	 * 百度APIStore需要的信息 实名认证接口

	 */
	public static String BDAPI_AppKey="699d0ddeaf053182b29df74d7eb0aa76";
	public static String BDAPI_Url="http://apis.baidu.com/chazhao/idcard/idcard";//身份证实名认证
	public static String BDAPI_BankAppKey="399385e38546cecf76a3429f69ad7ad9";//银行卡实名认证
	public static String BDAPI_BankUrl="http://apis.baidu.com/datatiny/cardinfo/cardinfo";//银行卡实名认证

	/**
	 * 百度推送
	 */
	public static String BDPush_ApiKey = "gMy75cA2ulPxAaIKIoIPTvuz";
	public static String BDPush_SecretKey = "hPUfTGoIwKxoaDhSsGHZj4G6puz1qKHw";

	/**
	 * 阿里百川需要的信息
	 */
	public static String BCALI_APIKey = "23387502";
	public static String BCALI_SecretKey = "c92174216bd1a310b87d60a738e7eb4a";
	public static String BCALI_Url="http://gw.api.taobao.com/router/rest";

	/**
	 * ping ++
	 */
	public static String PingPay_App_ID="app_irHqrT4yDqbTPi1y";
	public static String PingPay_SecretKey = "sk_live_Der5a5ePGujHbzPWPSXLKiT8";//"sk_test_DGGGWD9mj54GOuHuv9zHyHG4";
	public static String PingPay_PrivateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
			"MIICXAIBAAKBgQCs6Gx2vfTAlucRj25wxkZcpSDH8SVS7pfcFf6BNjhRP4fIzYeS\n" +
			"qk5vi0yO7kB82kReFsuCbg0qS76f47UdzDboLX1fhnT6NnJxUr8XeDObKAMCrYZV\n" +
			"njFRW4Vrir4WDqQkLGYcKvhWlOny4kcn8zD4sJk+Yy7/j/dE2hYjMwE1JwIDAQAB\n" +
			"AoGAekqo7BLN2KjiJPldE0Yo3b2swb08uLsnjPHBX4IJsu1MDCnRiVoKv7m2HCFG\n" +
			"WwfE8wcMSOOtTAzmdg+HOdwFuGbGxVPRHJA8YE7KYpALTG62UFApKekFZkcjtQGZ\n" +
			"sGKQI3xTMKD8CmSMY9tYAeidhQLlJfY1ye7D7r7K5otelIECQQDfzGHqRWc+d57E\n" +
			"HifO2JXMNxbzrAtl/7KxWfdae5V0rcSUs+odd3xGKun369/e9HG5oabZuud1YTdq\n" +
			"NOV/GErLAkEAxcl9a0h912lHUSeDFsQb7OFmQXGyIJqD/zZYJZMVFEKDOV8PCUe0\n" +
			"ZRDRBk2wLBlIIh9li/OE29tvPpsi5JJnlQJAUXikBJbzeOgvIhIgR8rM7aT4dAij\n" +
			"taQaNzobNBNdWzaPI9tDVSrUUO76cciqnQ28AOn6RbUm4bJcbAuJqHT9dQJAcrru\n" +
			"hc0HMm1fcTP6VrpreH1HU68gJMl8yA4+E42+LIJonF8H2do+SmH23jEygGIp9aFA\n" +
			"xm38I4ymeyqCkxRaAQJBAJ2vUoxznukqrXAOQ/OlIGcV1QL+MSpZAu5kfctw8SZ4\n" +
			"Zmpx5pu/n96rs+NoKPRchdctW5seKedEW3iiAs1pMjM=\n" +
			"-----END RSA PRIVATE KEY-----";

	/**
	 * AliPay 所需要的信息
	 */
	public static String AliPay_NotifyUrl="http://115.29.185.211/WuTuoBang/Order/Interface/V1/AliPayWebhooks";
	public static  String AliPay_PayAccount="13396810625";
	public static String  AliPay_PayAccountName="阙廷康";


}
