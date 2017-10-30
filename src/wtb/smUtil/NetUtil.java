package wtb.smUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

import net.sf.json.JSONObject;

public class NetUtil {

	// name1=value1&name2=value2
	public static JSONObject doPostStr(String url, String param) throws ParseException, IOException {
		JSONObject jsonObject = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new StringEntity(param, "UTF-8"));
			HttpResponse response = client.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.doPostStr", e);
		}
		
		return jsonObject;
	}



	public static JSONObject doGetStr(String url) throws ParseException, IOException {
		JSONObject jsonObject = null;
		try {
			
			SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.doGetStr", e);
		}
		return jsonObject;
	}

	public static JSONObject doGet(String url) {
		String result = "";
		JSONObject json = null;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			String urlstring = url;
			URL realurl = new URL(urlstring);
			System.out.println("请求的服务器主机域名：" + realurl.getHost().toString());
			// 打开与此URL的连接
			URLConnection connection = realurl.openConnection();
			// 设置请求连接时间和读取数据时间
			connection.setConnectTimeout(7000);
			connection.setReadTimeout(17000);

			// 建立实际的连接
			connection.connect();
			// out = new PrintWriter(connection.getOutputStream());
			// // 发送参数
			// out.print(param);
			// // 输出流的缓冲
			// out.flush();
			// 读取获取的数据
	
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			json = JSONObject.fromObject(result);
		} catch (Exception e) {
			json = new JSONObject();
			json.put("data", result);
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			//ErrorUtil.HandleError(null, "wtb.smUtil.doGet", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				System.out.println("关闭请求流出现异常！" + e2);
				e2.printStackTrace();
				ErrorUtil.HandleError(null, "wtb.smUtil.doGet", e2);
			}
		}
		return json;

	}

	// 发送http的post请求
	public static JSONObject doPost(String url, String param) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		JSONObject jsonObject = null;
		try {
			URL realurl = new URL(url);
			System.out.println("请求的服务器主机域名：" + realurl.getHost().toString());
			// 打开与此URL的连接
			URLConnection connection = realurl.openConnection();
			// 设置请求连接时间和读取数据时间
			connection.setConnectTimeout(3000);
			connection.setReadTimeout(7000);

			// post请求的时候必须要设置的两个属性
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 获取URLconnextion对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送参数
			out.print(param);
			// 输出流的缓冲
			out.flush();
			// 读取获取的数据
			InputStreamReader InputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			in = new BufferedReader(InputStreamReader);
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.doPost", e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				System.out.println("关闭请求流出现异常！" + e2);
				e2.printStackTrace();
				ErrorUtil.HandleError(null, "wtb.smUtil.doPost", e2);
			}
		}
		return jsonObject;
	}
	
	
	public static com.alibaba.fastjson.JSONObject doPostFile(String url, String param,MultipartFile file) throws ParseException, IOException {
		com.alibaba.fastjson.JSONObject jsonObject = null;
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			String fileName = file.getOriginalFilename();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            if (param!=null) {
            	 builder.addTextBody("param", param);// 类似浏览器表单提交，对应input的name和value
			}
            HttpEntity entity = builder.build();
			httpost.setEntity(entity);
			HttpResponse response = httpclient.execute(httpost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSON.parseObject(result);
		
		return jsonObject;
	}

	
	

}
