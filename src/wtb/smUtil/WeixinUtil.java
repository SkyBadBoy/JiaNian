package wtb.smUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;

import wtb.core.model.Students;
import wtb.core.model.WeChatUser;

import net.sf.json.JSONObject;

/**
 * 微信工具类
 * @author Stephen
 *
 */
public class WeixinUtil {

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String UPLOAD_URL2 = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String UPLOAD_URL21 = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";
	
	private static final String UPLOAD_URL3 = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN&&type=TYPE";
	
	
	//互办
	public	static String APPID="wxeb5043ba75e89f51";
	public static String AppSecret="cc3bab4c29cbc2ec967e565fcac7a0e9";
	 
	//校波
	
	public	static String APPIDX="wxab5e4ee5a20faf3f";
	public static String AppSecretX="806fd24f9ef88f0080193337c9af6b59";
	/**
	 * 文件上传
	 * {"type":"thumb","thumb_media_id":"ntqXheNfViRi35LMnv1qTHr0kldGhYIOusFIUAXQC9HDeU_1HIlKlrKlFWfYfv9b","created_at":1471853575}
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		String mediaId =null;
		try{
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			//throw new IOException("文件不存在");
			return "-1"; //文件不存在
		}else
		{
			String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
			
			URL urlObj = new URL(url);
			//连接
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

			con.setRequestMethod("POST"); 
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); 

			//设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");

			//设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes("utf-8");

			//获得输出流
			OutputStream out = new DataOutputStream(con.getOutputStream());
			//输出表头
			out.write(head);

			//文件正文部分
			//把文件已流文件的方式 推入到url中
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			//结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

			out.write(foot);

			out.flush();
			out.close();

			StringBuffer buffer = new StringBuffer();
			BufferedReader reader = null;
			String result = null;
			try {
				//定义BufferedReader输入流来读取URL的响应
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				if (result == null) {
					result = buffer.toString();
				}
			} catch (IOException e) {
				e.printStackTrace();
				ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.upload", e);
			} finally {
				if (reader != null) {
					reader.close();
				}
			}

			JSONObject jsonObj = JSONObject.fromObject(result);
			System.out.println(jsonObj);
			String typeName = "media_id";
			if(!"image".equals(type)){
				typeName = type + "_media_id";
			}
			mediaId = jsonObj.getString(typeName);
			System.out.println(mediaId);
			return mediaId;
		}

		 } catch (Exception e) {
				ErrorUtil.HandleError(null, "wtb.smUtil.WeixinUtil.upload", e);
				return null;
			}
	}
	
	public static String uploadimg(String filePath,String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL21.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.WeixinUtil.uploadimg", e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		String mediaId = jsonObj.getString(typeName);
		System.out.println(mediaId);
	 
		return mediaId;
	}
	
	/**
	 * 用于获得URL
	 * 
	 * {
    "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
}
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String uploadURL(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		JSONObject jsonObj=null;
		try{
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		String url = UPLOAD_URL2.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				System.out.println(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.uploadURL", e);
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		 jsonObj = JSONObject.fromObject(result);
	 } catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.WeixinUtil.uploadURL", e);
		}
		return jsonObj.toString();
	}
	

	
	
	public static int GroupSend(String accessToken,String type,String content,String media_id) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	//	{ "filter":{ "is_to_all":true }, "text":{ "content":"CONTENT" }, "msgtype":"text"}
		String errcode =null;
		try{
		String json = null;
		if (content!=null&&!content.isEmpty()) {
			content=new String(content.getBytes("UTF-8"), "ISO8859_1"); 
			json="{\"filter\":{ \"is_to_all\":true }, \""+type+"\":{ \"content\":\""+content+"\" }, \"msgtype\":\""+type+"\"}";
		}
		if (media_id!=null&&!media_id.isEmpty()) {
			json="{\"filter\":{\"is_to_all\":true},\""+type+"\":{\"media_id\":\""+media_id+"\"},\"msgtype\":\""+type+"\"}";
			//json="{ \"touser\":[\"oVQ3Xv8XJvP9UN5wEO5Ug05SbYZQ\"],\"mpnews\":{\"media_id\":\""+media_id+"\"},\"msgtype\":\""+type+"\"}";
		}
		String url = UPLOAD_URL3.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		con.connect();
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(json);
        out.flush();
        out.close(); 
        StringBuffer buffer = new StringBuffer();
        String result = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
            buffer.append(line);
        }
        if (result==null) {
        	result = buffer.toString();
		}
        reader.close();
        con.disconnect();
        JSONObject jsonObj = JSONObject.fromObject(result);
		 errcode = jsonObj.getString("errcode");
		
	 } catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.WeixinUtil.GroupSend", e);
		}
		return Integer.parseInt(errcode);
	}
	
	/**
	 *  图文消息
	 * @param accessToken 
	 * @param media_id 缩略图值
	 * @param author 作者
	 * @param title 标题
	 * @param content_source_url 连接
	 * @param content 内容
	 * @param digest 详情
	 * @throws IOException
	 *    	"type":"news",
   			"media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ",
   			"created_at":1391857799
	 */
	public static String uploadPermanentMedia(String accessToken,net.sf.json.JSONArray jsonArray) throws IOException {
		
		String tuwenmedia_id=null;
		try{
		String json="{\"articles\":"+jsonArray.toString()+"}";
		      	System.out.println(json);
			    String urll="https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
				String url = urll.replace("ACCESS_TOKEN", accessToken);
				URL urlObj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
				con.setRequestMethod("POST"); 
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				con.setRequestProperty("Connection", "Keep-Alive");
				con.setRequestProperty("Charset", "UTF-8");
				con.connect();
		        DataOutputStream out = new DataOutputStream(con.getOutputStream());
		        out.writeBytes(json);
		        out.flush();
		        out.close(); 
		        StringBuffer buffer = new StringBuffer();
		        String result = null;
		        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String line;
		        while ((line = reader.readLine()) != null){
		            System.out.println(line);
		            buffer.append(line);
		        }
		        if (result==null) {
		        	result = buffer.toString();
				}
		        reader.close();
		        con.disconnect();
		        JSONObject jsonObj = JSONObject.fromObject(result);
				 tuwenmedia_id = jsonObj.getString("media_id");
		  } catch (Exception e) {
				ErrorUtil.HandleError(null, "wtb.smUtil.WeixinUtil.uploadPermanentMedia", e);
			}
				return tuwenmedia_id;
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, ClientProtocolException, IOException {
		//upload("C:/Users/Mj/Desktop/ali.jpg", WeChatMenuUtil.getAccessToken(), "thumb");
		//uploadPermanentMedia(WeChatMenuUtil.getAccessToken(), "EcHzuYtDWa6iRUxXWKoFhOakDd2cQCPttJiR7ZdlEHu1Y9YeNPTG4Bwl7wirqgJM", "马健", "abc", "acc", "bcc", "cdd");
		//npO77BEZ5Bxf_klqV2JcIOB29mXruTIWQPA_lBqrxXiwLScJM5PljE7MHObBSbdL
		//GroupSend(WeChatMenuUtil.getAccessToken(), "mpnews", null, "x4SGLNSJYzKnqFitP6DXCOnPNx_6e8L4T6d_UeUk7XL66rv1G5qHCWEcVRIMlBlP");
		uploadURL("C:/Users/Mj/Desktop/ali.jpg", "TgKcuz-lCdHXQKLrcUdcSDBcBtVNbNEZFMjUSa7H-R0obB4cBovTeXNW7GtLgwnQRqlsctqO8i8HKXrw2bSpVNtpC4Sqt2l6ic37E624qB2yabR7gMoid6UiuYy2M_VVCCTgAIAIIZ","image");
	}
	
	public static boolean isWeXin(HttpServletRequest request,String requestUrl){
		if(request!=null && request.getHeader("user-agent")!=null){
			String ua = request.getHeader("user-agent")
					.toLowerCase();
			if (ua.indexOf("micromessenger") > 0 && requestUrl.contains("phone")) {// 是微信浏览器
				return  true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public static String weburl(HttpServletRequest request){
		return  "http://" + request.getServerName() //服务器地址
        + ":" 
        + request.getServerPort()           //端口号
        + request.getContextPath()      //项目名称
        + request.getServletPath()      //请求页面或其他地址
        + "?" + (request.getQueryString()); //参数
	}
	
    public static Students verifyWeChatUser(JSONObject obj,read.core.service.ReadStudentsService ReadStudentsService,wtb.core.service.StudentsService StudentsService,
    		HttpServletRequest arg0,HttpServletResponse arg1,wtb.core.service.WeChatUserService WeChatUserService ,wtb.core.service.RegionService RegionService,read.core.service.ReadWeChatBindService ReadWeChatBindService){
    	if(obj!=null && (obj.containsKey("unionid") || obj.containsKey("openid"))){
    		String unionid="";
    		String openid="";
    		if(obj.containsKey("unionid")){
    			unionid=obj.getString("unionid");
    		}
    		if(obj.containsKey("openid")){
    			openid=obj.getString("openid");
    		}
    		List<Students> students= new ArrayList<Students>();
    		students=SmBaseUtil.getStudentByOpenID(openid,unionid, ReadStudentsService, ReadWeChatBindService);
    		if (students.size()>0) {
    			if (students.get(0).getUnionID()==""||students.get(0).getUnionID()==null || students.get(0).getOpenID()==""||students.get(0).getOpenID()==null) {
    				students.get(0).setUnionID(obj.getString("unionid"));
    				students.get(0).setOpenID(obj.getString("openid"));
    				try{
    					StudentsService.updateStudents(students.get(0));
    				}catch (Exception e) {
						e.printStackTrace();
						 Map<String, Object> responseMap = new HashMap<String, Object>();
						 if((unionid!=null && !unionid.isEmpty()) || (openid!=null && !openid.isEmpty())){
							if(unionid!=null && !unionid.isEmpty()){
		    					responseMap.put("UnionID", unionid);
		    				}else{
		    					responseMap.put("OpenID", openid);
		    				}
							StudentsService.deleteStudents(responseMap);
							try{
								StudentsService.updateStudents(students.get(0));
							}catch (Exception e1) {
							}
						 }
						
						
					}
    				
				}
    			if(obj.containsKey("openid")){
    				students.get(0).setTempOpenID(obj.getString("openid"));
        		}
        		
    			SmBaseUtil.CreateSession("StudentName", students.get(0), arg0, arg0.getSession(), arg1);
    			return students.get(0);
			}else{
				try{
	        		JSONObject userInfo=obj;
	        		if((!userInfo.containsKey("subscribe")||(userInfo.containsKey("subscribe") && userInfo.getString("subscribe").equals("0"))) && obj.containsKey("access_token")){
	    				String WeChatAPI_UsrInfo="https://api.weixin.qq.com/sns/userinfo?access_token="+ obj.getString("access_token") +"&openid="+ obj.getString("openid") +"&lang=zh_CN";
	    				userInfo=SmBaseUtil.SendGetRequestURL(WeChatAPI_UsrInfo);
	    			}
	        		if(userInfo!=null && userInfo.containsKey("openid") && userInfo.containsKey("nickname")){
	        			WeChatUser wechatUser= SmBaseUtil.PaseJsonToWeChatObj(userInfo, arg0, WeChatUserService);
	        			Students stu=SmBaseUtil.parseXBUserForStudent(wechatUser, null, RegionService);
	        			try{
	        				StudentsService.addStudents(stu);
	        			}catch (Exception e) {
	        				StudentsService.updateStudents(stu);
						}
	        			if(userInfo.containsKey("openid")){
	        				stu.setTempOpenID(userInfo.getString("openid"));
	            		}
	        			SmBaseUtil.CreateSession("StudentName", stu, arg0, arg0.getSession(), arg1);
	        			return stu;
	        		}
				}catch (Exception e) {
					e.printStackTrace();
					ErrorUtil.HandleError(null, "wtb.core.UserAuthenticationController.preHandle", e);
					return null;
				}
				return null;
				
			}
		}
    	return null;
    }
	
	
	
}
