package wtb.smUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import wtb.core.model.WeChatInfo;
import wtb.core.service.WeChatInfoServices;

@Component
public class WeChatMenuUtil {
	@Autowired
	private WeChatInfoServices weChatInfoServices;
	private static WeChatMenuUtil weChatMenuUtil;
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static DefaultHttpClient httpclient;
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
	}
	
	
	public void setWeChatMenuUtil(WeChatMenuUtil weChatMenuUtil) {
		this.weChatMenuUtil = weChatMenuUtil;
	}

	@PostConstruct
	public void Init() {
		weChatMenuUtil = this;
		weChatMenuUtil.weChatInfoServices = this.weChatInfoServices;
		
	}
/*
	 * 获取AccessToken
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * 先注释掉，太危险啦
	 * @throws java.text.ParseException 
	 */
//	public static String getAccessToken() throws ClientProtocolException, IOException {
////		HttpGet get = HttpClientConnectionManager.getGetMethod("http://www.whohelp.cc/HBWeChatAPI/wechat.do?getToken=true");
////		HttpResponse response = httpclient.execute(get);
////		String AccessToken = EntityUtils.toString(response.getEntity(), "utf-8");
//		String AccessToken="lnfIHmJbwOy3JgHJGeHmKhHrf04SuaJ1ywWCYc2s5BLf2OqBrlkVRgfh7fT9lKAq2F9Cm58Ol2NAlSfLRaL9Fib22WY7Fu5g8eKjZ-InWIZO1QiojNSc-x9nzgJb6WLEKThAGAUAJ";
//		return AccessToken;
//	}
//	
	@SuppressWarnings("deprecation")
	public static String getAccessToken(String appid,String appsecret) throws ParseException, IOException, java.text.ParseException{
		
		Map<String, Object> map=new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("AppID", appid);
		List<WeChatInfo> mChatInfos=weChatMenuUtil.weChatInfoServices.getWeChatInfo(map);
		if (mChatInfos.size() > 0) {
			long currentTime = System.currentTimeMillis() - 115 * 60 * 1000;// 115分钟改变一次，不取120是有原因的就怕，刚刚2小时，差一秒就报错啦
			Date date = new Date(currentTime);
			Date date2 = sdf.parse(mChatInfos.get(0).getATTime());
			if (date.getTime() <= date2.getTime()) {
				return mChatInfos.get(0).getAccessToken();
			} else {
				String url = null;
				JSONObject jsonObject = null;
				String access_token = null ;
				try {
					url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
					jsonObject = doGetStr(url);
					if(jsonObject.getString("access_token") != null)
					{
						access_token= jsonObject.getString("access_token");
						WeChatInfo weChatInfo = mChatInfos.get(0);
						weChatInfo.setAccessToken(access_token);
						weChatInfo.setATTime(sdf.format(new Date()));
						weChatMenuUtil.weChatInfoServices.updateWeChatInfo(weChatInfo);
						return access_token;
					}
				} catch (Exception e) {
					
					ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.getAccessToken", e);
				}
				
				
				return null;
			}
		} else {
			return null;
		}
	}

	
	/**
	 *获取已存在的菜单
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	public static String getMenuInfo(String accessToken) throws Exception {
		HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		try {
			if(JSONObject.fromObject(jsonStr).get("errcode")!=null)
			{
				return "-1";
			};
		} catch (Exception e) {
			ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.getMenuInfo", e);
		}
		return jsonStr;
	}
	
	/**
	 * 删除
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
   public static String deleteMenuInfo(String accessToken) throws Exception {    
		JSONObject demoJson =null;
		try{
		HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
		HttpResponse response = httpclient.execute(get);
		String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
		 demoJson = JSONObject.fromObject(jsonStr);
		} catch (Exception e) {
			ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.deleteMenuInfo", e);
		}
        return demoJson.getString("errmsg");    
    }    
   
   /**
    * 创建
    * @param params
    * @param accessToken
    * @return
    * @throws Exception
    */
   public static String createMenu2(String params, String accessToken) throws Exception {    
		JSONObject demoJson=null;
		try{
       HttpPost httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);    
       httpost.setEntity(new StringEntity(params, "UTF-8"));    
       HttpResponse response = httpclient.execute(httpost);    
       String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");    
		 demoJson = JSONObject.fromObject(jsonStr);
	} catch (Exception e) {
		ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.createMenu2", e);
	}
		return accessToken;
   }
   
	
   
	
	/**
	 * 有用的
	 * @param url
	 * @param menu
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static int createMenu(String accessToken,String jsonMenu) throws ParseException, IOException{
		int result = 0;
		try{
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
		JSONObject jsonObject = doPostStr(url, jsonMenu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
	} catch (Exception e) {
		ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.createMenu", e);
	}
		return result;
	}

	public static JSONObject doPostStr(String url, String menu) throws ParseException, IOException {
		JSONObject jsonObject = null;
		try{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		
		httpost.setEntity(new StringEntity(menu, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
	} catch (Exception e) {
		ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.doPostStr", e);
	}
		return jsonObject;
	}

	public static JSONObject doGetStr(String url) throws ParseException, IOException {
		JSONObject jsonObject = null;
		try{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
	} catch (Exception e) {
		ErrorUtil.HandleError(null,"wtb.smUtil.WeChatMenuUtil.doGetStr", e);
	}
		return jsonObject;
	}
	
	
}
