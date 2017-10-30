package wtb.smUtil.alipay.config;

import wtb.smUtil.SmBaseGlobal;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
	public static String partner = "2088521726498714";
	
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
	public static String seller_id = partner;
	
	//APPID
	public static String app_id = "2017041906822309";
	// MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
    public static String key = "fp5gejdx70obwybxkm2uvakpnee2b0p8";
	
    public static String ALIPAY_PUBLIC_KEY =  "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCck3AidMm9DcOcnGYUPTwmOilmW20r4Aa+NBOzcsGCvgY6Gnr0AHTBFTNS4TCAVI0QXH68ksDrkMHOY8qKafklT0d0IM0I3bkkrRQVi6RgRp0BIqQKF84CE3buD5jehB+fRoAmUQGQYOiu+uuSeZcDt2OxLw2MXHC4EDnkSTW4fuRjTrQL7K6L4RMROeT+CmHN6wia5CpdJZ0Od8HqyPPB6LE1Fgvj/kqHOp0gSwVUwsQj0YEbjdQ7Gmwyhrt7h9mb5+QzhvnybDYTynlIaQRslS0T0QWK1ZSoVa1RoyQ5mtabIGNlfEGRoNoP0efEDafmA50ytcLbjqNThrjXOyQ3AgMBAAECggEBAJPM+YUNCYJUg9pKKHHie+z56iqdEyjfiH3zWsWCBfkMt4QRiw7Zf1zkTlZmDAqXxbE0Hs6TGVlEhC33NTIDZxQ5ChItBF7iLerZFqRf9QKMpZh0wrRkVCrRhOqj/oHN5qvLORzySq+nSv4iDk1uSYdQjeKrr/ZXLHAToRJFqVhFrtQkeuFi3t4JVyVpEyYUoZChc24tVTwnGZPORuH8uHaAe5HWsuFHu6JMC5xZ3qBRT6NkCELnrwBM6QiC9YN+yWXItLjArcsRLvGTzaC7CY9OzD9i6ta5/UOZwZq1gG1xt4wLnQnl3Y2Lz8E4bZyfMuXK32k/ao69zAuKNeba9YECgYEA80YOhxnPqWMd3SMXCFbBGMj24MbZnNHp5uxtOAJYMkka5wEAVuWwTR/83H5lF9jFpAVkXnx343ifZGFYao1vc2qN26sM1PyOSHD4G4u3uKd281/ckUXOeS6gaqfNrunCbgM4e2txUcsmVRadL5W6FzrCXHmkwdBX1p07Kxfn7AkCgYEApMRROocPvnoARIjeEVOqDWAtpFZWvweOpggv1javerajzebPAjGsL51vax7yGRML7Gw1I8jUyD1Ikrxf4d6eFuu5MxEcmJSK9Ylixd3C9umZIQwd2/KCq9sS04uDccpkBjcHJxW9TtagzMHSGth/qazz1QQFTWQqVzekoglHHj8CgYB7Ti0a6UhKcQS4C4snHOHu8B3CPMgcJoHKHFgXpYiSpF9Qaz+CzBUaYnZGRIJ1Hdi/lEQVaD1UPNV9iaHJEDtHYuDYUNDGO+Tabvgy7hSHMcqm/RXNRHatEacZqEgmujFklZlRZVogvTmGs1mkvt193LibKsUJv9Jl43gUFZ/mEQKBgHWAb3EuuoKbHK/Db6KpqJ8rVMFatTg8WnV7Zyn8C76Wm1COZlhpNiMqwjeBdSN+msU7EdZ4NY6rbaL/Zd19Vf6Akq2haWRCoqqiFGeJU62kP4j6zBx74MZQUCe+2bl/nT91ZMtxzHR5yWjcXEq61KgbaDQkTue3JB7Y1rabqTUfAoGBAKOrc5gA9E4NDTJfDWIN0lNFYgfVr/N7FCBUixDCWg6iqSocOq0zgwcv+kwj8eTtzVxwUTjfzboFk/PQcPkO6OdC+B3VHMhznuY+aIy2utGThPVGN8L/KkZTwqcfR5LwkGnvMXcddQXIumsYTMKShckBmhiy6pmXrfMdwJaoNq+F";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = SmBaseGlobal.WeChatAPIURL +"WeNewsAgency/WeMoney/aliPayCallBack";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = SmBaseGlobal.WeChatAPIURL +"WeNewsAgency/WeMoney/alipayapiResult";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
		
	// 字符编码格式 目前支持utf-8
	public static String input_charset = "utf-8";
		
	// 支付类型 ，无需修改
	public static String payment_type = "1";
		
	// 调用的接口名，无需修改
	public static String service = "alipay.wap.create.direct.pay.by.user";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}

