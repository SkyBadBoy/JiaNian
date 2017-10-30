package wtb.smUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Random;

import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONML;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import com.alibaba.fastjson.JSON;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import wtb.core.model.WeChatInfo;


public class UnifiedorderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	/**
	 * 微信服务号统一下单支付
	 * 
	 * @param out_trade_no
	 *            订单号
	 * @param body
	 *            标题
	 * @param openId
	 *            用户的openId
	 * @param money
	 *            支付金额
	 * @param ip
	 *            客户端ip
	 * @param request
	 *            HttpServletRequest
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject execute(String nonce_str, String timeStamp, String openId, String out_trade_no, String body, double money, String ip,HttpServletRequest req, JSONObject jso,WeChatInfo WeChatInfo,String attach) throws JSONException {
		
		
		String appid = WeChatInfo.getAppID();
		String mch_id = String.valueOf(WeChatInfo.getMchID());
		String api_key = WeChatInfo.getApiKey();
		String requestUrl = req.getRequestURI().replace(req.getContextPath(), "");   
		StringBuilder xml = new StringBuilder();
		//String notify_url = "http://test.com/payCallBack";// 支付回调地址
		String prepay_id = "", sign = "", charset = "UTF-8",mweb_url="";
		String notify_url=SmBaseUtil.getCurrentWebUrl(req)+"/WeMoney/payCallBack";
		String trade_type = req.getParameter("trade_type");
		String h5_info = "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \""+ requestUrl +"\",\"wap_name\": \"H5支付\"}} ";
		if(trade_type==null || trade_type.isEmpty()){
			trade_type="JSAPI";
		}
		if(attach!=null && !attach.isEmpty()){
			attach =attach.replaceAll("\"", "'");
		}
		try {
			String weixinMoney = new java.text.DecimalFormat("#").format(money * 100);// 微信是以分为单位的所以要乘以100
			xml.append("appid=").append(appid).append("&attach=").append(attach).append("&body=").append(new String(body.getBytes(charset),charset));// 处理中文
			xml.append("&mch_id=").append(mch_id).append("&nonce_str=").append(nonce_str);
			xml.append("&notify_url=").append(notify_url);
			if(trade_type.equals("JSAPI")){
				xml.append("&openid=").append(openId);
			}
			xml.append("&out_trade_no=").append(out_trade_no).append("&spbill_create_ip=").append(ip);
			xml.append("&total_fee=").append(weixinMoney);
			xml.append("&trade_type=").append(trade_type);
			if(trade_type.equals("MWEB")){
				xml.append("&trade_type=").append(h5_info);
			}
			xml.append("&key=").append(api_key);
			sign = MD5Purity(xml.toString());
			xml.delete(0, xml.length());
			xml.append("<xml>");
			xml.append("   <appid>").append(appid).append("</appid>");
			xml.append("   <attach>").append(attach).append("</attach>");
			xml.append("   <body>").append(body).append("</body>");
			xml.append("   <mch_id>").append(mch_id).append("</mch_id>");
			xml.append("   <nonce_str>").append(nonce_str).append("</nonce_str>");
			xml.append("   <notify_url>").append(notify_url).append("</notify_url>");
			if(trade_type.equals("JSAPI")){
				xml.append("   <openid>").append(openId).append("</openid>");
			}
			xml.append("   <out_trade_no>").append(out_trade_no).append("</out_trade_no>");
			xml.append("   <spbill_create_ip>").append(ip).append("</spbill_create_ip>");
			xml.append("   <total_fee>").append(weixinMoney).append("</total_fee>");
			xml.append("   <trade_type>").append(trade_type).append("</trade_type>");
			if(trade_type.equals("MWEB")){
				xml.append("   <scene_info>").append(h5_info).append("</scene_info>");
			}
			xml.append("   <sign>").append(sign).append("</sign>");
			xml.append("</xml>");
			
			System.err.println(xml);
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.mch.weixin.qq.com/pay/unifiedorder").openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/xml");
			conn.setRequestProperty("Charset", "UTF-8");
			OutputStream os = conn.getOutputStream();
			os.write(xml.toString().getBytes("UTF-8"));
			xml.delete(0, xml.length());
			os.close();
			int responseCode = conn.getResponseCode();
			InputStreamReader in = null;
			BufferedReader br = null;
			if (responseCode == 200) {
				in = new InputStreamReader(conn.getInputStream(), "utf-8");
				br = new BufferedReader(in);
				String retData = null;
				while ((retData = br.readLine()) != null)
						xml.append(retData);
				System.err.println(xml.toString());
					JSONArray childNodes = JSONML.toJSONObject(xml.toString()).getJSONArray("childNodes");
					int len = childNodes.length() - 1;
					for (int i = len; i > -1; i--) {
						org.activiti.engine.impl.util.json.JSONObject js = childNodes.getJSONObject(i);
						if (js.get("tagName").equals("prepay_id")) {
							prepay_id = js.getJSONArray("childNodes").getString(0);
							break;
						}
						if (js.get("tagName").equals("mweb_url")) {
							mweb_url = js.getJSONArray("childNodes").getString(0);
							break;
						}
					}
				
			}
			if (in != null)
				in.close();
			if (br != null)
				br.close();
			conn.disconnect();
			// ----------------------------------给h5返回的数据
		
			//nonce_str = getRandomString(32);
			jso.put("appId", appid);
			jso.put("nonceStr", nonce_str);
			jso.put("package", "prepay_id=" + prepay_id);
			jso.put("signType", "MD5");
			jso.put("timeStamp", timeStamp);
			jso.put("mweb_url", mweb_url);
			xml.delete(0, xml.length());
			xml.append("appId=").append(appid);
			xml.append("&nonceStr=").append(nonce_str);
			xml.append("&package=").append(jso.getString("package"));
			xml.append("&signType=").append(jso.getString("signType"));
			xml.append("&timeStamp=").append(timeStamp);
			xml.append("&key=").append(api_key);
			sign = MD5Purity(new String(xml.toString().getBytes(charset), charset));
			jso.put("paySign", sign);
			//返回的数据主要用在这地方 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6
			// ----------------------------------给h5返回的数据
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.execute", e);
		} catch (ParseException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.execute", e);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.execute", e);
		} catch (IOException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.execute", e);
		}
		return jso;
	}
	
	
	/**
	 * 微信退款接口
	 * 
	 * @param out_refund_no 退款订单 32为随机数
	 * @param out_trade_no out_trade_no 订单号
	 * @param money 退款数
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 */
	public static JSONObject Refund(String out_refund_no,String out_trade_no,Double money,WeChatInfo WeChatInfo) throws JSONException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, KeyManagementException, UnrecoverableKeyException {
		//退款开始
		String appid = WeChatInfo.getAppID();
		String mch_id = String.valueOf(WeChatInfo.getMchID());
		String api_key = WeChatInfo.getApiKey();
		
		JSONObject jso=new JSONObject();
		StringBuilder xml = new StringBuilder();
		String sign=null;
		String nonce_str=SmBaseUtil.getRandomString(32);
		//String out_refund_no=SmBaseUtil.CreateNewID()+"";//退款单号是随机数
		//String out_refund_no="uswo05ajyibx0yxxt0i7bxq9nnize1mm";//退款单号是随机数
		//appid mch_id nonce_str sign out_trade_no out_refund_no total_fee refund_fee op_user_id
		String refundMoney = new java.text.DecimalFormat("#").format(money * 100);//微信退款金额
		String ALLMoney = new java.text.DecimalFormat("#").format(money * 100);//微信退款原来金额
		xml.append("appid=").append(appid);
		xml.append("&mch_id=").append(mch_id);
		xml.append("&nonce_str=").append(nonce_str);
		xml.append("&op_user_id=").append(mch_id);//操作员-----默认为商户号
		xml.append("&out_refund_no=").append(out_refund_no);//退款单号
		xml.append("&out_trade_no=").append(out_trade_no);//订单号
		xml.append("&refund_fee=").append(refundMoney);//退款金额
		xml.append("&total_fee=").append(ALLMoney);//退款总金额
		xml.append("&key=").append(api_key);
		sign = MD5Purity(xml.toString());
		xml.delete(0, xml.length());
		xml.append("<xml>");
		xml.append("   <appid>").append(appid).append("</appid>");
		xml.append("   <mch_id>").append(mch_id).append("</mch_id>");
		xml.append("   <nonce_str>").append(nonce_str).append("</nonce_str>");
		xml.append("   <op_user_id>").append(mch_id).append("</op_user_id>");
		xml.append("   <out_refund_no>").append(out_refund_no).append("</out_refund_no>");
		xml.append("   <out_trade_no>").append(out_trade_no).append("</out_trade_no>");
		xml.append("   <refund_fee>").append(refundMoney).append("</refund_fee>");
		xml.append("   <total_fee>").append(ALLMoney).append("</total_fee>");
		xml.append("   <sign>").append(sign).append("</sign>");
		xml.append("</xml>");
		
		
	      KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	      String path="C:/Users/Administrator/Desktop/z/apiclient_cert.p12";
	      System.out.println(path);
	        FileInputStream instream = new FileInputStream(new File(path));
	        try {
	            keyStore.load(instream, mch_id.toCharArray());
	        } finally {
	            instream.close();
	        }

	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, mch_id.toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        @SuppressWarnings("deprecation")
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
	       String status="";
	        try {
	            HttpPost httpPost=new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
	            StringEntity  reqEntity  = new StringEntity(xml.toString());  
	            // 设置类型  
	            reqEntity.setContentType("application/x-www-form-urlencoded");  
	            httpPost.setEntity(reqEntity);  
	            CloseableHttpResponse response = httpclient.execute(httpPost);
	            try {
	                HttpEntity entity = response.getEntity();
	                
	                if (entity != null) {
	                    System.out.println("Response content length: " + entity.getContentLength());
	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"utf-8"));
	                    String text=null;
	                    xml.delete(0, xml.length());
	                	while ((text = bufferedReader.readLine()) != null)
							xml.append(text);
						JSONArray childNodes = JSONML.toJSONObject(xml.toString()).getJSONArray("childNodes");
						int len = childNodes.length() - 1;
						for (int i = len; i > -1; i--) {
							org.activiti.engine.impl.util.json.JSONObject js = childNodes.getJSONObject(i);
							if (js.get("tagName").equals("result_code")) {
								status = js.getJSONArray("childNodes").getString(0);
								break;
							}
						}
					
	                   
	                }
	                EntityUtils.consume(entity);
	            } finally {
	                response.close();
	            }
	        } finally {
	            httpclient.close();
	        }
		jso.put("status", status);
		return jso;
	}
	
	
	
	
	
	
	
	

	/**
	 * MD5
	 * 
	 * @param plainText
	 * @return
	 */
	public static String MD5Purity(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("utf-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			plainText = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.MD5Purity", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.MD5Purity", e);
		}
		return plainText.toUpperCase();
	}

	/**
	 * 生成一个随机字符串
	 * 
	 * @param length
	 *            表示生成字符串的长度
	 * @return
	 */
	private static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	
	
}
