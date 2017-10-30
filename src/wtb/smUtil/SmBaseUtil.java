package wtb.smUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.org.apache.xpath.internal.operations.Bool;

import read.core.service.ReadHonorRecordService;
import read.core.service.ReadNoticesService;
import read.core.service.ReadRegionService;
import read.core.service.ReadStudentStatService;
import read.core.service.ReadStudentsService;
import read.core.service.ReadVoteService;
import read.core.service.ReadWeChatBindService;
import read.core.service.ReadWeMoneyRecordService;
import sun.misc.BASE64Decoder;
import sun.net.util.IPAddressUtil;

import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import wtb.core.controller.NoticesController;
import wtb.core.model.AccessActive;
import wtb.core.model.ClickList;
import wtb.core.model.HonorRecord;
import wtb.core.model.Notices;
import wtb.core.model.Region;
import wtb.core.model.StudentStat;
import wtb.core.model.Students;
import wtb.core.model.Video;
import wtb.core.model.Vote;
import wtb.core.model.WeChatBind;
import wtb.core.model.WeChatInfo;
import wtb.core.model.WeChatUser;
import wtb.core.service.AccessActiveService;
import wtb.core.service.ClickListService;
import wtb.core.service.NoticesService;
import wtb.core.service.RegionService;
import wtb.core.service.StudentsService;
import wtb.core.service.VoteService;
import wtb.core.service.WeChatInfoServices;
import wtb.core.service.WeChatUserService;
import wtb.sessions.MySessionContext;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;



public class SmBaseUtil {
	private static SmBaseUtil instance;
	private static int size = 20;
	private static int page = 1;
	
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    public static byte[] GenerateImage(String imgStr) throws IOException {// 对字节数组字符串进行Base64解码并生成图片

		String[] b = imgStr.split(",");
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(b[1]);
		return bytes;
	}
    
    public static String format(Date date) {
    	
		
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

	    private static long toSeconds(long date) {
	        return date / 1000L;
	    }
	
	    private static long toMinutes(long date) {
	        return toSeconds(date) / 60L;
	    }
	
	    private static long toHours(long date) {
	        return toMinutes(date) / 60L;
	    }
	
	    private static long toDays(long date) {
	        return toHours(date) / 24L;
	    }
	
	    private static long toMonths(long date) {
	        return toDays(date) / 30L;
	    }
	
	    private static long toYears(long date) {
	        return toMonths(date) / 365L;
	    }
	    
	    
		public static String getRandomString(int length) {
			String base = "abcdefghijklmnopqrstuvwxyz0123456789";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			return sb.toString();
		}
	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数个敿
	 * @return
	 */
	public String getCheckNu(int count) {
		StringBuffer sb = new StringBuffer();
		try {

			String str = "123456789";
			Random r = new Random();
			for (int i = 0; i < count; i++) {
				int num = r.nextInt(str.length());
				sb.append(str.charAt(num));
				str = str.replace((str.charAt(num) + ""), "");
			}
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getCheckNu", e);
		}
		return sb.toString();
	}

	public static SmBaseUtil getInstance() {
		try {

			if (instance == null) {
				instance = new SmBaseUtil();
			}
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getInstance", e);
		}
		return instance;
	}

	/**
	 * 生成动态姓名
	 * 
	 * @param srcName
	 */
	public static String getRandOtherName(String srcName) {
		StringBuffer otherName = new StringBuffer();
		String nameChild = "";
		int Namelen = new Random().nextInt(2) + 4;
		for (int i = 0; i < Namelen; i++) {
			int charChild = new Random().nextInt(srcName.length());
			int charChildend = charChild + 1;
			if (charChildend >= srcName.length()) {
				charChildend = charChild;
				charChild = charChild - 1;
			}
			nameChild = srcName.substring(charChild, charChildend);
			otherName.append(nameChild);
		}
		try {
			return new String(otherName.toString().getBytes(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getRandOtherName", e);

			e.printStackTrace();
			return "";
		}
	}

	
	/**
	 * 发邮件
	 * @return
	 * @throws MessagingException 
	 */
	public void sendMail(String mail,String message) 
	{
		 String configpath = getClass().getResource("/").getFile().replace("classes/", "")+"config.xml";  
		List<String> configList=SmBaseUtil.initServersList(configpath);
	    // 创建Properties 类用于记录邮箱的一些属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", configList.get(4));
        //端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
        props.put("mail.smtp.port", configList.get(5));
        // 此处填写你的账号
        props.put("mail.user", configList.get(6));
        // 此处的密码就是前面说的16位STMP口令
        props.put("mail.password", configList.get(7));

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        
        MailSSLSocketFactory sf = null;
        try {
             sf = new MailSSLSocketFactory();
             sf.setTrustAllHosts(true);
         } catch (GeneralSecurityException e1) {
             e1.printStackTrace();
         }
         props.put("mail.smtp.ssl.enable", "true");
         props.put("mail.smtp.ssl.socketFactory", sf);
 
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message1 = new MimeMessage(mailSession);
        // 设置发件人
      

        // 最后当然就是发送邮件啦
        try {
        	  InternetAddress form = new InternetAddress(
                      props.getProperty("mail.user"));
              message1.setFrom(form);

              // 设置收件人的邮箱
              InternetAddress to = new InternetAddress(mail);
              message1.setRecipient(RecipientType.TO, to);

              // 设置邮件标题
              message1.setSubject("微新闻社报警");

              // 设置邮件的内容体re
              String path = getClass().getResource("/").getFile().replace("WEB-INF/classes/", "")+"include/mailTemp.html";  
              String newMessage= readfile(path);
              newMessage=newMessage.replace("${content}", message);
              message1.setContent(newMessage, "text/html;charset=UTF-8");
			Transport.send(message1);
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.sendMail", e);
		}
		
	}
	public static String readfile(String filePath){
        File file = new File(filePath);  
        InputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
        StringBuffer buffer = new StringBuffer();  
        byte[] bytes = new byte[1024];
        try {
            for(int n ; (n = input.read(bytes))!=-1 ; ){  
                buffer.append(new String(bytes,0,n,"utf-8"));  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(buffer);
        return buffer.toString();  
    }
	/*
	 * 获取前台参数
	 * 
	 * @param http请求
	 *            request
	 * @return
	 */
	public Map<String, Object> getParamsMap(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Enumeration<String> pNames = request.getParameterNames();
		try {
			Boolean ispage=false;
			while (pNames.hasMoreElements()) {
				String pName = pNames.nextElement();
				if (pName.equals("page")) {
					if (request.getParameter(pName) != null && Integer.valueOf(request.getParameter(pName)) > 0) {
						page = Integer.valueOf(request.getParameter(pName));
						ispage=true;
					}
				} else if (pName.equals("size")) {
					if (request.getParameter(pName) != null && Integer.valueOf(request.getParameter(pName)) > 0) {
						size = Integer.valueOf(request.getParameter(pName));
						ispage=true;
					}
				} else {
					String pParam=request.getParameter(pName);
					if(pParam!=null && !pParam.isEmpty()){
						paramMap.put(pName, URLDecoderString(pParam));
					}
				}
			}
			if(ispage){
				int start = page * size - size;
				paramMap.put("start", start);
				paramMap.put("limit", size);
			}
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getParamsMap", e);
		}
		return paramMap;
	}

	/**
	 * 返回客户端信息错误信息
	 * 
	 * @param request
	 * @return
	 */
	public Map<String, Object> getresponseMap(int status) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Msg = "";
		try {
			if (status == 0) {
				Msg = "session不存在";
			} else if (status == -1) {
				Msg = "ID不能为空";
			} else if (status == -2) {
				Msg = "记录已存在";
			} else if (status == -3) {
				Msg = "网络异常,请稍后再试!";
			}
			responseMap.put("status", status);
			responseMap.put("msg", Msg);
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getresponseMap", e);
		}
		return responseMap;
	}

	
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			ErrorUtil.HandleError(null, "wtb.smUtil.MD5", e);
			return null;
		}
	}

	public static File rename(File file) {
		try {
			String body = "";
			String ext = "";
			Date date = new Date();
			int pot = file.getName().lastIndexOf(".");// 取得文件名和后缀分割点
			if (pot != -1) {// 说明后缀存在
				body = date.getTime() + "";// 采用时间的形式命名
				ext = file.getName().substring(pot);// 截取后缀名
			} else {
				body = (new Date()).getTime() + "";
				ext = "";
			}
			String newName = body + ext;
			file = new File(file.getParent(), newName);// 对文件进行重命名
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.rename", e);
		}
		return file;

	}

	// 获取ip地址
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		try {
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getIpAddr", e);
		}
		if(ip!=null && ip.length()>100){
			ip=ip.split(",")[0];
		}
		return ip;
	}

	

	public static int getClickInfo(HttpServletRequest req, String EntryID,ClickListService clickListService) {
		
		int result=0;
		Students stu=(Students)req.getSession().getAttribute("StudentName");
		String ip = "";
		if(stu!=null){
			ip = String.valueOf(stu.getID());
		}else{
			ip = getIpAddr(req);
		}
		
		Map<String, Object> responseMap =new HashMap<String, Object>();
		try {
			responseMap.put("IPAddress", ip);
			responseMap.put("Type", 1);
			responseMap.put("BeLongID", EntryID);
			int count=clickListService.CheckExistByIdAddress(responseMap);
			if(count<=0){
				String macip = MacAddress.getMacAddress(ip);
				ClickList clicklist=new ClickList();
				clicklist.setBeLongID(Long.parseLong(EntryID));
				clicklist.setCreateTime(new Date());
				clicklist.setID(CreateNewID());
				clicklist.setIPAddress(ip);
				clicklist.setMacAddress(macip);
				clicklist.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				clicklist.setType(1);
				try{
					result=clickListService.addClickList(clicklist);
				}catch (Exception e) {
					result=clickListService.updateClickList(clicklist);
				}
				
			}
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getClickInfo", e);
		}
			
		return result;

	}
	/**
	 * 添加分页参数信息
	 * @param request
	 * @return
	 */
	public static Map<String, Object> AddPageParam(HttpServletRequest request){
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String pageSize=request.getParameter("pageSize");
		String pageNumber=request.getParameter("pageNumber");
		String Submit=request.getParameter("submitType");
		if(pageSize==null || pageSize.isEmpty()){
			pageSize="10";
		}
		if(pageNumber==null){
			pageNumber="0";
		}
		if(!isNumeric(pageNumber)){
			pageNumber="0";
		}
		
		if (pageSize != null && !pageSize.isEmpty()) {
			checkParammap.put("limit", pageSize);
		}else{
			checkParammap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
			int start=0;
			if(Submit!=null && Submit.equals("phone")){
				start=Integer.parseInt(pageNumber)*Integer.parseInt(pageSize)-Integer.parseInt(pageSize);
			}else{
				start=Integer.parseInt(pageNumber);
			}
			if(start<0){
				start=0;
			}
			checkParammap.put("start", start);
			
		}else{
			checkParammap.put("start", 0);
		}
		return checkParammap;
	}
	
	/**
	 * 添加手机分页参数信息
	 * @param pageSize 页数量
	 * @param pageNumber 页
	 * @param request
	 * @return
	 */
	public static Map<String, Object> AddPhonePageParam(String pageSize,String pageNumber){
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if(pageSize==null || pageSize.isEmpty()){
			pageSize="10";
		}
		if(pageNumber==null){
			pageNumber="0";
		}
		if (pageSize != null && !pageSize.isEmpty()) {
			checkParammap.put("limit", pageSize);
		}else{
			checkParammap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
				checkParammap.put("start", Integer.parseInt(pageNumber)*Integer.parseInt(pageSize)-Integer.parseInt(pageSize));
		}else{
			checkParammap.put("start", 0);
		}
		return checkParammap;
	}
	
	
	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c)
			throws UnsupportedEncodingException {
		// 如果字节数大于1，是汉字
		if((c >= 0x0391 && c <= 0xFFE5)){ //中文字符
			return true;
		}
		return false;
				
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String substring(String orignal, int count)
			throws UnsupportedEncodingException {
		// 原始字符不为null，也不是空字符串
		if (orignal != null && !"".equals(orignal) ) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes());
			int byteLen=orignal.getBytes().length;
			// 要截取的字节数大于0，且小于原始字符串的字节数
			if ( count < byteLen) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						count-=2;
					}
				}
				return buff.toString()+"...";
			}
		}
		return orignal;
	}
/**
 * 检测验证码是否正确
 * @param session
 * @param CurrentAuthCode
 * @return
 */
	public static String CheckAuthCode(HttpSession session,String CurrentAuthCode){
		String authCode=null;
		if(session.getAttribute("AuthenCode")==null){
			return "验证码已失效，请重新获取";
		}else{
			authCode=(String)session.getAttribute("AuthenCode");
			if(!CurrentAuthCode.equals(authCode)){
				return "验证码不正确，请重新输入验证码！";
			}
		}
		return null;
	}
	
	
	
	
	
	// 将汉字转换为全拼  
    public static String getPingYin(String src) {  
  
        char[] t1 = null;  
        t1 = src.toCharArray();  
        String[] t2 = new String[t1.length];  
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();  
          
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);  
        String t4 = "";  
        int t0 = t1.length;  
        try {  
            for (int i = 0; i < t0; i++) {  
                // 判断是否为汉字字符  
                if (java.lang.Character.toString(t1[i]).matches(  
                        "[\\u4E00-\\u9FA5]+")) {  
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);  
                    t4 += t2[0];  
                } else  
                    t4 += java.lang.Character.toString(t1[i]);  
            }  
            // System.out.println(t4);  
            return t4;  
        } catch (BadHanyuPinyinOutputFormatCombination e1) {  
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getPingYin", e1);
        }  
        return t4;  
    }  
  
    // 返回中文的首字母  
    public static String getPinYinHeadChar(String str) {  
  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    }  
    /**
     * 获取本项目的根URL 如 www.whohelp.cc/WebContentName/
     * @param request
     * @return
     */
    public static String getProjectBaseUrl(HttpServletRequest request){
    	String basePath=request.getScheme() + "://"
    			+ request.getServerName() + ":" + request.getServerPort()
    			+ request.getContextPath() + "/";
    
    	return basePath;
    }
    public static String getProjectBaseUrlNoProject(HttpServletRequest request){
    	String basePath=request.getScheme() + "://"
    			+ request.getServerName() + ":" + request.getServerPort()+ "/";
    
    	return basePath;
    }
	
    /**
     * 将xbUser对象转换为学生类
     * @param xbuser
     * @param stu
     * @param regionService
     * @return
     */
    public static Students parseXBUserForStudent(WeChatUser xbuser,Students stu,RegionService regionService){
		Map<String, Object> checkParammap=new HashMap<String, Object>();
		if(stu==null){
			stu=new Students();
		}
		if(xbuser.getNickName()!=null){
			stu.setName(xbuser.getNickName());
			stu.setOpenID(xbuser.getOpenID());
			stu.setUnionID(xbuser.getUnionID());
			stu.setSex(xbuser.getSex());
			stu.setImageUrl(xbuser.getHeadImgUrl());
			stu.setCreateTime(SmBaseGlobal.sdfDate.format(new Date()));
			stu.setID(SmBaseUtil.CreateNewID());
			stu.setLevel("0");
			stu.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			if(stu.getSchool()!=null && !stu.getSchool().isEmpty()){
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Name", stu.getSchool());
				List<Region> regions=regionService.getRegionList(checkParammap);
				if(regions.size()>0){
					stu.setAreaID(regions.get(0).getID());
					stu.setArea(regions.get(0));
				}
			}
		}
		return stu;
	}
    
  
    /**
     * 获取当前项目的url,用户外链的跳转
     * @param req
     * @return
     */
    public static String getCurrentWebUrl(HttpServletRequest req){
    	String ContextPath = req.getContextPath();
    	if(ContextPath.length()>1&& ContextPath.substring(0,1).equals("/")){
    		ContextPath = ContextPath.substring(1,ContextPath.length());
    	}
    	String basePath = WeChatAPIURL(req)+ContextPath;
    	return basePath;
    }
    /**
     * 用户登录的状态 snsapi_userinfo表示 手工授权登录 snsapi_base 表示自动授权登录
     * @param api
     * @return 返回登录成功后的url
     */
    public static String getWeChatLoginUrl(String api,String returlUrl,HttpServletRequest req){
    	String islogin="";
    	returlUrl=returlUrl.replaceAll("&code=", "&del_code=");
		try {
			islogin = WeChatAPIURL(req)+"WeChatAPI/directUrl.jsp?type=setp1&&api="+ api +"&&aid="+URLEncoder.encode(returlUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getWeChatLoginUrl", e);
		}
		return islogin;
    }
	/**
	 * 创建一个session对象
	 * @param SessionName
	 * @param obj
	 * @param req
	 * @param session
	 */
    public static void CreateSession(String SessionName,Object obj,HttpServletRequest req, HttpSession session,HttpServletResponse resp){
		session = req.getSession();
		MySessionContext myc= MySessionContext.getInstance();  
		myc.DelSession(session);
		session.removeAttribute("StudentName");
		myc.AddSession(session);
		if(obj!=null){
			session.setAttribute("StudentName", obj);
			Cookie cookie = new Cookie("StudentSID", session.getId());
			cookie.setMaxAge(3600*24*7);
			resp.addCookie(cookie);
		}
    }
    public static Cookie getCookies(HttpServletRequest resp,String CookieName){
    	Cookie Result=null;
    	Cookie[] cookies = resp.getCookies();//这样便可以获取一个cookie数组
    	if(cookies!=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(CookieName)){
					Result= cookie;
				}
			}
    	}
		return Result;
    }
    public static Object getSessionValueForCookie(Object student,HttpServletRequest req,String SessionName,HttpServletResponse resp){
    	try{
	    	if(student==null){
	    		Cookie cookie=SmBaseUtil.getCookies(req,"StudentSID");
	    		if(cookie!=null){
	    			String SID=cookie.getValue();
	    			if(SID!=null && !SID.isEmpty()){
	    				MySessionContext myc= MySessionContext.getInstance();
	    				HttpSession session = myc.getSession(SID);
	    				if(session!=null && session.getId()!=null && !session.getId().isEmpty()){
	    					student=session.getAttribute(SessionName);
	    					CreateSession("StudentName", student, req, session,resp);
	    				}
	    			}
	    		}
	    	}
	    	return student;
    	}catch(IllegalStateException e){
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.getSessionValueForCookie", e);
    		return student;
    	}
    }
    @SuppressWarnings("finally")
   	public static JSONObject SendGetRequestURL(String url){
       	String result="";
       	URL realUrl;
       	JSONObject ErrorMessage=new JSONObject();
   		try {
   			realUrl = new URL(url);
   		
       	  // 打开和URL之间的连接
           URLConnection connection = realUrl.openConnection();
           // 设置通用的请求属性
           connection.setRequestProperty("accept", "*/*");
           connection.setRequestProperty("connection", "Keep-Alive");
           connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
           //connection.set
           connection.connect();
           System.err.print("connect");
           // 定义 BufferedReader输入流来读取URL的响应
           BufferedReader in = new BufferedReader(new InputStreamReader(
                   connection.getInputStream()));
           String line;
           System.err.print("readLine");
           while ((line = in.readLine()) != null) {
               result += line;
           }
           System.err.print("readLineed");
           System.err.print(result);
           if(result.contains("{") || result.contains("}")){
        	   ErrorMessage=SmBaseUtil.PaseJsonToJsonObject(result);
           }
           //	ErrorMessage=JSONObject.fromObject(result);
            if(ErrorMessage==null){
            	ErrorMessage=new JSONObject();
            }
           	ErrorMessage.accumulate("Data", result);
           	System.err.print(ErrorMessage.toString());
           	
   		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.SendGetRequestURL", e);
   		}finally{
   			return ErrorMessage;
   		}
       }
    /**
	 * 初始化多服务器更新索引的接口列表
	 * */
	public static List<String> initServersList(ServletContext event) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<String> lsStr=new ArrayList<String>();
		if(event==null){
			return lsStr;
		}
		try {
			String path=event.getRealPath("/WEB-INF/config.xml");
			path.replaceAll("/", File.separator);
			FileInputStream   inputStream=new FileInputStream (path);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputStream);
			// Element root = doc.getDocumentElement();
			NodeList serverslist = doc.getElementsByTagName("item");
			if (serverslist != null) {
				for (int i = 0; i < serverslist.getLength(); i++) {
					String updateIndexUrl = doc
							.getElementsByTagName("value").item(i)
							.getFirstChild().getNodeValue();
					
					lsStr.add(updateIndexUrl);
				}
			}
			
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.initServersList", e);
		}
		return lsStr;
	}
	
	public static List<String> initServersList(String path) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<String> lsStr=new ArrayList<String>();
		
		try {
			FileInputStream   inputStream=new FileInputStream (path);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(inputStream);
			// Element root = doc.getDocumentElement();
			NodeList serverslist = doc.getElementsByTagName("item");
			if (serverslist != null) {
				for (int i = 0; i < serverslist.getLength(); i++) {
					String updateIndexUrl = doc
							.getElementsByTagName("value").item(i)
							.getFirstChild().getNodeValue();
					
					lsStr.add(updateIndexUrl);
				}
			}
			
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.initServersList", e);
		}
		return lsStr;
	}
	public static long CreateNewID(){
    	return new IdWorker(1, new Random().nextInt(31)).nextId();
    }
	public static JSONObject PaseJsonToJsonObject(String json){
		JSONObject jsonObj=null;
		if(json!=null && !json.isEmpty()){
			try{
				if(!json.contains("-")){
					json=json.replace('-', '%');
				}
				json=URLDecoderString(json);
				if(json.endsWith("]")){
					json+="]}";
				}
				if(!json.endsWith("}")){
					json+="\"}";
				}
				if(!json.contains("nickname\":\"")){
					json=json.replaceFirst("nickname\"", "nickname\":\"");
				}
				if(!json.contains(",\"sex\"")){
					json=json.replaceFirst("sex\"", ",\"sex\"");
				}
				if(!json.contains("\",\"sex\"")){
					json=json.replaceFirst(",\"sex\"", "\",\"sex\"");
				}
				if(!json.contains(",\"}")){
					json=json.replaceFirst(",\"}", "}");
				}
	    		jsonObj=JSONObject.fromObject(json);
	    	}catch (Exception e) {
	    		System.err.println(json);
	    		ErrorUtil.HandleError(null, "wtb.smUtil.PaseJsonToJsonObject", e);
			}
		}
		return jsonObj;
	}
	
	
	public static WeChatUser PaseJsonToWeChatObj(JSONObject jsonObj,HttpServletRequest request,WeChatUserService WeChatUserService){
		WeChatUser weChatUser=new WeChatUser();
		
		weChatUser.setID(SmBaseUtil.CreateNewID());
		weChatUser.setOpenID(jsonObj.getString("openid"));
		if(jsonObj.containsKey("unionid")){
			weChatUser.setUnionID(jsonObj.getString("unionid"));
		}
		weChatUser.setNickName(jsonObj.getString("nickname"));
		weChatUser.setSex(jsonObj.getString("sex"));
		weChatUser.setLanguage(jsonObj.getString("language"));
		weChatUser.setCity(jsonObj.getString("city"));
		weChatUser.setProvince(jsonObj.getString("province"));
		weChatUser.setCountry(jsonObj.getString("country"));
		weChatUser.setHeadImgUrl(jsonObj.getString("headimgurl"));
		weChatUser.setIpAddress(SmBaseUtil.getIpAddr(request));
		if (jsonObj.containsKey("privilege")) {
			weChatUser.setPrivilege(jsonObj.getString("privilege"));
		}
		if (jsonObj.containsKey("subscribe")) {
			weChatUser.setFlag(Integer.parseInt(jsonObj.getString("subscribe")));
		}
		WeChatUserService.addWeChatUser(weChatUser);
		return weChatUser;
	}
	
	public static String  URLDecoderString(String string) {
		String newString="";
		if(string==null){
			string="";
		}
		try {
			newString = URLDecoder.decode(string, "utf-8");
		} catch (Exception e) {
			string = string.replaceAll("%", "%25");
			try {
				newString = URLDecoder.decode(string, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				ErrorUtil.HandleError(null, "wtb.smUtil.URLDecoderString", e);
				newString= string;	
			}
		}
		return newString;
	}
	
	/**
	 * 返回带A标签的ImgHtml代码
	 * @return
	 */
	public static String getClickImageHtml(String url){
		if(!SmBaseUtil.CheckIsNull(url)){
			url = "";
		}
		String[] strs=url.split(",");
		String a_url="";
		if(strs.length>=3){
			a_url=strs[0];
		}else{
			a_url=strs[0];
		}
		String html = url;
		if(!url.contains("<a href")){
			html="<a href='"+ a_url +"' title='图片' data-gallery=''><img alt='image' style='width:70px;height:70px;' onerror='nofind()' src='"+ a_url +"'></a>";
		}
		return html;
	}
	
	/**
	 * 获取微信的个人信息
	 * @param openid
	 * @return
	 */
	public static String getWeChat_token(String openid,HttpServletRequest request){
		String WeChat_token=WeChatAPIURL(request)+ "WeChatAPI/wechat.do?getToken=true";
		JSONObject jsobject=SendGetRequestURL(WeChat_token);
		String access_key="";
		if(jsobject.containsKey("Data")){
			access_key=jsobject.getString("Data");
		}
		if(!access_key.isEmpty()){
			String WeChatAPI_UsrInfo="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ access_key +"&openid="+ openid +"&lang=zh_CN";
			JSONObject jsuserinfo=SendGetRequestURL(WeChatAPI_UsrInfo);
			if(jsuserinfo.containsKey("Data")){	
				return jsuserinfo.getString("Data");
			}
		}
		return null;
	}
	public static boolean isNumeric(String str){ 
	   if(str==null || str.isEmpty())return false;
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
	}

	public static int compareDate(Date d1,Date d2){
    if (d1.getTime() > d2.getTime()) {
        System.out.println("dt1 在dt2前");
        return 1;
    } else if (d1.getTime() < d2.getTime()) {
        System.out.println("dt1在dt2后");
        return -1;
    } else {//相等
        return 0;
    }
	}
	public static boolean compareDateForNow(Date voteStart,Date voteEnd){
		Date noticeTime=new Date();
		if (noticeTime.getTime()>voteStart.getTime()&&noticeTime.getTime()<voteEnd.getTime()) {
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean compareDateDual(Date voteStart,Date voteEnd,Date noticeTime){
		if (noticeTime.getTime()>voteStart.getTime()&&noticeTime.getTime()<voteEnd.getTime()) {
			return true;
		}else{
			return false;
		}
	}
	/*
	 * mac地址取不出来  暂时换成Ip地址
	 */
	public static String getLocalMac(HttpServletRequest request) throws SocketException, UnknownHostException {
	return	SmBaseUtil.getIpAddr(request);
	//获取网卡，获取地址
	/*InetAddress ia=InetAddress.getLocalHost();
	byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
	//System.out.println("mac数组长度："+mac.length);
	StringBuffer sb = new StringBuffer("");
	for(int i=0; i<mac.length; i++) {
		if(i!=0) {
			sb.append("-");
		}
		//字节转换为整数
		int temp = mac[i]&0xff;
		String str = Integer.toHexString(temp);
		//System.out.println("每8位:"+str);
		if(str.length()==1) {
			sb.append("0"+str);
		}else {
			sb.append(str);
		}
	}
		return sb.toString().toUpperCase();*/
	}
	public static String Random() {
		return getRandomString(8);
	}
	
	public static String getPositionIcon(HttpServletRequest request, Students student) {
		String baseUrl=getProjectBaseUrl(request);
		String iconUrl="";
		if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.Chief.getid()){
			iconUrl=baseUrl+SmBaseGlobal.Chief;
		}else if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.DeputyChief.getid()){
			iconUrl=baseUrl+SmBaseGlobal.DeputyChief;
		}else if(student!=null && student.getChief()==SmBaseGlobal.ChiefType.Editorial.getid()){
			iconUrl=baseUrl+SmBaseGlobal.Editorial;
		}else if(student!=null && student.getOfficial()==1){
			iconUrl=baseUrl+SmBaseGlobal.Normal;
		}else if(student!=null && student.getPhone()!=null && !student.getPhone().isEmpty()){
			iconUrl=baseUrl+SmBaseGlobal.UnNormal;
		}
		return iconUrl;
	}
	public static List<String> getHonourIcon(HttpServletRequest request, Students student,ReadHonorRecordService ReadHonorRecordService ) {
		String baseUrl=getProjectBaseUrl(request);
		List<String> urls=new ArrayList<String>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("CustomerTime", SmBaseGlobal.sdfDateTime.format(new Date()));
		responseMap.put("StudentID", student.getID());
		List<HonorRecord>honorRecords=ReadHonorRecordService.getHonorRecordList(responseMap);
		String iconUrl="";
		int Write=0,Spread=0,Excellent=0;
		
		for (HonorRecord honorRecord : honorRecords) {
			iconUrl="";
			if(honorRecord.getType()==SmBaseGlobal.HonerType.Write.getid() && Write==0){
				iconUrl=baseUrl+"images/weixinwenshe/xzdr.png";
				Write=1;
			}else if(honorRecord.getType()==SmBaseGlobal.HonerType.Spread.getid() && Spread==0){
				iconUrl=baseUrl+"images/weixinwenshe/cbdr.png";
				Spread=1;
			}else if(honorRecord.getType()==SmBaseGlobal.HonerType.Excellent.getid() && Excellent==0){
				iconUrl=baseUrl+"images/weixinwenshe/xwdr.png";
				Excellent=1;
			}
			if(!iconUrl.isEmpty()){
				urls.add(iconUrl);
			}
		}
		return urls;
		
	}
	public static String getLevelIcon(HttpServletRequest request, Students student,ReadStudentStatService ReadStudentStatService) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String baseUrl=getProjectBaseUrl(request);
		String iconUrl=baseUrl+"img/star0.png";
		int DiamondLevel=Integer.parseInt(student.getDiamondLevel());
		if(student!=null ){
			if(DiamondLevel>0){
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MONTH, -1);
				responseMap = new HashMap<String, Object>();
				responseMap.put("StatMonth", calendar.get(Calendar.MONTH) + 1);
				responseMap.put("StatYear", calendar.get(Calendar.YEAR));
				responseMap.put("Sina", SmBaseUtil.Random());
				responseMap.put("StudentID", student.getID());
				List<StudentStat> result=ReadStudentStatService.getStudentStatList(responseMap);
				int Glamour=0;
				if(result.size()>0){
					Glamour = Integer.parseInt(result.get(0).getGlamour());
				}
				if(DiamondLevel==1){
					if( Glamour >=200){
						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
					}else{
						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
					}
				}
				if(DiamondLevel==2){
					if( Glamour >=300){
						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
					}else{
						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
					}
				}
				if(DiamondLevel==3){
					if( Glamour >=500){
						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
					}else{
						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
					}
				}
				if(DiamondLevel==4){
					if( Glamour >=1000){
						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
					}else{
						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
					}
				}
				if(DiamondLevel==5){
					if( Glamour >=5000){
						iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
					}else{
						iconUrl=baseUrl+"images/weixinwenshe/d-starb"+ DiamondLevel +".png";
					}
				}
				iconUrl=baseUrl+"images/weixinwenshe/d-star"+ DiamondLevel +".png";
			}else{
				iconUrl=baseUrl+"img/star"+ ((student.getLevel()==null || student.getLevel().isEmpty())?"0":student.getLevel()) +".png";
			}
		}
		
		return iconUrl;
	}
	
	public static String getUserImageUrl(Students user,HttpServletRequest request){
		String baseUrl=getProjectBaseUrl(request);
		String iconUrl=baseUrl+SmBaseGlobal.UserDefaultImageUrl;
		if(user!=null){
			if(user.getImageUrl()!=null && !user.getImageUrl().isEmpty()){
				iconUrl=user.getImageUrl().split(",")[0];
			}else{
				if(user.getImage()!=null && user.getImage().getUrl()!=null && !user.getImage().getUrl().isEmpty()){
					iconUrl=user.getImage().getUrl().split(",")[0];
				}
			}
		}
		return iconUrl;
	}
	/**
	 * 获得本周第一天或者最后一天
	 * 0第一天
	 * 1最后一天
	 * @param First
	 * @return
	 */
	public static String getWeek(int i){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int d = 0;
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			d = -6;
		}else{
			d = 2-cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		String data=null;
		if(i==0){
			data=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}else{
			cal.add(Calendar.DAY_OF_WEEK, 6);
			data=new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}
		return data;
	}

	
    /**
     * 当月第一天
     * @return
     */
	public static String getFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return str.toString();

    }
    
    /**
     * 当月最后一天
     * @return
     */
    public static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }
	/**
	 * 必须传入的是学校的ID 
	 * @param AreaID 
	 * @param ReadRegionService
	 * @return
	 */
	public static String Address(String AreaID,ReadRegionService ReadRegionService){
		String ShenRegionStr="";
		String ShiRegionStr="";
		String QuRegionStr="";
		List<Region> myRegion=ReadRegionService.getRegionByIDList(AreaID);//自己的名字
		if(myRegion.size()>0){
			List<Region> QuRegion=ReadRegionService.getRegionByIDList(myRegion.get(0).getParentID());//上级，代表区
			if(QuRegion.size()>0){
				QuRegionStr = QuRegion.get(0).getName();
				List<Region> ShiRegion=ReadRegionService.getRegionByIDList(QuRegion.get(0).getParentID());//上级，代表市
				if(ShiRegion.size()>0 ){
					ShiRegionStr = ShiRegion.get(0).getName();
					List<Region> ShenRegion=ReadRegionService.getRegionByIDList(ShiRegion.get(0).getParentID());//上级，代表省
					if(ShenRegion.size()>0){
						ShenRegionStr = ShenRegion.get(0).getName();
					}
				}
			}
		}
		return ShenRegionStr+ShiRegionStr+QuRegionStr;
	}

	public static List<Students> getStudentByOpenID(String openid,String unionid,ReadStudentsService ReadStudentsService,ReadWeChatBindService ReadWeChatBindService){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("OpenID", openid);
		responseMap.put("Sina", Random());
		List<Students> stus =new ArrayList<Students>();
		List<WeChatBind>binds= ReadWeChatBindService.getWeChatBindList(responseMap);
		if(binds.size()<=0 && unionid!=null && !unionid.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("UnionID", unionid);
			responseMap.put("Sina", Random());
			binds= ReadWeChatBindService.getWeChatBindList(responseMap);
		}
		if(binds.size()>0){
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", binds.get(0).getStudentID());
			responseMap.put("Sina", Random());
			stus = ReadStudentsService.getStudentsList(responseMap);
		}
		if(stus.size()<=0){
			if(openid!=null && !openid.isEmpty()){
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", openid);
				responseMap.put("Sina", Random());
				stus = ReadStudentsService.getStudentsList(responseMap);
			}
			if(stus.size()<=0 && unionid!=null && !unionid.isEmpty()){
				responseMap = new HashMap<String, Object>();
				responseMap.put("UnionID", unionid);
				responseMap.put("Sina", Random());
				stus = ReadStudentsService.getStudentsList(responseMap);
			}
		}
		
		return stus;
		
	}
	

	public static Map<String, String> getWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
		
		
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		
		if(weekday==1){
			cal.add(Calendar.WEEK_OF_YEAR, -1);
		}
		map.put("startTime", df.format(cal.getTime()) + " 00:00:00");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		
	
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		
		map.put("endTime", df.format(cal.getTime()) + " 23:59:59");

		return map;
	}
	public static Map<String, String> getLastWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK);
		/*if(weekday==0){
			weekday=7;
		}
		
		Calendar cal = Calendar.getInstance();
		 int n = -1;
		 if(weekday==1){
			 n=-2;
		 }
		cal.add(Calendar.DATE, n*7);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); // 获取本周一的日期
		cal.add(Calendar.DATE, -7+(weekday-6));
		map.put("startTime", df.format(cal.getTime()) + " 00:00:00");
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.DATE, 6);
		map.put("endTime", df.format(cal.getTime()) + " 23:59:59");*/
//周六到周五的统计方式
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		 int n = -1;
		 if(weekday==1){
			 n=-2;
		 }
		  cal.add(Calendar.DATE, n*7);
		  cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
	
		map.put("startTime", df.format(cal.getTime()) + " 00:00:00");
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		
	
			cal.add(Calendar.WEEK_OF_YEAR, 1);
		
		map.put("endTime", df.format(cal.getTime()) + " 23:59:59");

		
		
		return map;
	}
	public static Notices getPCWeNewsData(HttpServletRequest req,Notices notices,read.core.service.ReadLikeRecordService ReadLikeRecordService,wtb.core.service.VoteRecordsService VoteRecordsService) throws SocketException, UnknownHostException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Students user = (Students) req.getSession().getAttribute("StudentName"); 
		String userid=SmBaseUtil.getIpAddr(req);;
		if(user!=null){
			userid=String.valueOf(user.getID());
		}
		if(notices!=null){
			map=new HashMap<>();
			map.put("UserID", userid);
			map.put("NoticesID",String.valueOf(notices.getID()));
			int islikeflag=ReadLikeRecordService.getLikeRecordCount(map);
			if (islikeflag>0) {
				//点赞接口0喜欢     1取消    所以这边刚刚要相反
				notices.setIsLike(islikeflag);
			}
			if(user!=null){
				if(user.getUnionID()!=null){
					map=new HashMap<>();
					map.put("NoticeID", notices.getID());
					map.put("UnionID", user.getUnionID());
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						notices.setIsVote(1);
					}else{
						notices.setIsVote(0);
					}
				}else{
					map=new HashMap<>();
					map.put("NoticeID", notices.getID());
					map.put("UnionID",SmBaseUtil.getLocalMac(req));
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						notices.setIsVote(1);
					}else{
						notices.setIsVote(0);
					}
				}
			}else{
				notices.setIsVote(0);
			}
		}
		
		
		return notices;
	}
	
	public static Video getPCVideoWeNewsData(HttpServletRequest req,Video video,read.core.service.ReadLikeRecordService ReadLikeRecordService,wtb.core.service.VoteRecordsService VoteRecordsService) throws SocketException, UnknownHostException, ParseException {
		Map<String, Object> map = new HashMap<String, Object>();
		Students user = (Students) req.getSession().getAttribute("StudentName"); 
		String userid=SmBaseUtil.getIpAddr(req);;
		if(user!=null){
			userid=String.valueOf(user.getID());
		}
		if(video!=null){
			map=new HashMap<>();
			map.put("UserID", userid);
			map.put("NoticesID",String.valueOf(video.getID()));
			int islikeflag=ReadLikeRecordService.getLikeRecordCount(map);
			if (islikeflag>0) {
				//点赞接口0喜欢     1取消    所以这边刚刚要相反
				video.setIsLike(islikeflag);
			}
			if(user!=null){
				if(user.getUnionID()!=null){
					map=new HashMap<>();
					map.put("NoticeID", video.getID());
					map.put("UnionID", user.getUnionID());
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						video.setIsVote(1);
					}else{
						video.setIsVote(0);
					}
				}else{
					map=new HashMap<>();
					map.put("NoticeID", video.getID());
					map.put("UnionID",SmBaseUtil.getLocalMac(req));
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						video.setIsVote(1);
					}else{
						video.setIsVote(0);
					}
				}
			}else{
				video.setIsVote(0);
			}
		}
		
		
		return video;
	}
	public static String huanhang(String myString) {
		Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
		Matcher m = CRLF.matcher(myString);
		String tempm = "";
		if (m.find()) {
			if (tempm.isEmpty()) {
				tempm = "<p>";
			} else {
				if (tempm.equals("<p>")) {
					tempm = "</p><p>";
				} else {
					tempm = "<p>";
				}
			}
			myString = m.replaceAll(tempm);
		}
		Pattern CRLFnbsp = Pattern.compile("(\40)");
		Matcher mnbsp = CRLFnbsp.matcher(myString);
		if (mnbsp.find()) {
			myString = mnbsp.replaceAll("&nbsp");
		}
		return myString;
	}
	/*
	 * 发送生日祝福短信
	 */
	public static void SendBirthDayblessing(List<Students> stus,StudentsService StudentsService,ServletContext arg0 ){
		for (Students students : stus) {
			if(students.getPhone()!=null && !students.getPhone().isEmpty() && students.getPhone().length()>0){
				System.err.println("发送给"+students.getPhone()+"一条生日短信");
				boolean result=false;
				result = VerifyCode.sharedInstance().sendBirthDayMessage(students.getPhone(),arg0);
				if(result){
					StudentsService.UupdateBirthDayRemind(students.getID());
				}
			}
		}
	}
	/**
	 * 长时间未使用平台的用户短信推广通知
	 * @param stus
	 */
	public static void SendLongTimeUnUseNotices(List<Students> stus,StudentsService StudentsService,ServletContext arg0 ){
		for (Students students : stus) {
			if(students.getPhone()!=null && !students.getPhone().isEmpty() && students.getPhone().length()>0){
				boolean result=false;
				result = VerifyCode.sharedInstance().sendLongTimeUnUseMessage(students.getPhone(),arg0);
				System.err.println("发送给"+students.getPhone()+"一条长时间未使用推广短信");
				if(result){
					StudentsService.UpSendCount(students.getID());
				}
			}
		}
	}
	/**
	 * 投稿成功短信
	 * @param stus
	 * @param StudentsService
	 */
	public static void SendNoticesPublisMessage(String phone,ServletContext arg0){
		System.err.println("发送给"+phone+"投稿成功的短信");
		VerifyCode.sharedInstance().sendNoticesPublisMessage(phone,arg0);
			
	}
	public static void sendActiveApplyMessage(String phone,String name,String title,ServletContext arg0){
		System.err.println("发送给"+phone+"报名活动成功的短信");
		VerifyCode.sharedInstance().sendActiveApplyMessage(phone,name,title,arg0);
			
	}
	

	/**
	 * 部分需要特殊放行的一些URL 通过这里去放行,不再检测是否登录,同时进行访问量的统计
	 * @param requestUrl
	 * @param arg0
	 * @return
	 */
	public static String CheckIsPass(String requestUrl,HttpServletRequest arg0,String returnURL,ReadNoticesService NoticesService,String pid){
		String WeNews="";
		String submitType=arg0.getParameter("submitType");
		if(requestUrl.split("/").length==3){
			WeNews= requestUrl.split("/")[1];//接口的话取出来是这个"WeNews"
		}
		if(requestUrl.equals("/Product/phoneweChatPordDetail")){
			if(WeixinUtil.isWeXin(arg0,requestUrl)){
				try{
					if(pid!=null && !pid.isEmpty()){
				
						Notices notices=NoticesService.getNoticesByID(Long.parseLong(pid));
						if(notices!=null){
							return String.valueOf(true);
						}
					}
				}catch (Exception e) {
					// TODO: handle exception
				}
				
	    	}else{
				return String.valueOf(true);
			}
		}
    	if(requestUrl.equals("/Activity/phoneSinaActivityDetail")){
    		String ispre = arg0.getParameter("_ispre_");
       		if(ispre!=null && ispre.equals("1")){
       			return String.valueOf(true);
       		}
    	}
    	if(requestUrl.equals("/Activity/phoneSinaActivityDetail")){
    		String ispre = arg0.getParameter("_ispre_");
       		if(ispre!=null && ispre.equals("1")){
       			return String.valueOf(true);
       		}
    	}
    	/**
    	 * 微新闻社PC端限制
    	 */
    	if(WeNews.equals("WeNews") || (submitType!=null && submitType.equals("pc"))){
    		return String.valueOf(true);
    	}
    	if(!WeixinUtil.isWeXin(arg0,requestUrl) && (submitType!=null && submitType.equals("mweb"))){
    		return String.valueOf(true);
    	}
    	if(WeNews.equals("Competition") && requestUrl.contains("Details")){
    		return String.valueOf(true);
    	}
    	if(!WeixinUtil.isWeXin(arg0,requestUrl)){
    		if(!requestUrl.contains("submitType=mweb") && requestUrl.contains("/Activity/") && requestUrl.contains("Apply")){
    			return returnURL+"&submitType=mweb";
    		}
        		
    	}
    	if(WeixinUtil.isWeXin(arg0,requestUrl) && returnURL.contains("submitType=mweb")){
    		return returnURL.replace("&submitType=mweb", "");
        		
    	}
    	return String.valueOf(false);
 
	}
	
	public static void addAccessActivity(final HttpServletRequest arg0 ,final String requestUrl,final AccessActiveService AccessActiveService){
		try{
		Thread t = new Thread() {
			public void run() {
				//增加访问量
				try{
					Students student=null;
					if(arg0.getSession()!=null && arg0.getSession().getAttribute("StudentName")!=null){
						try{
							student=(Students)arg0.getSession().getAttribute("StudentName");
						}catch (Exception e) {
							
						}
					}
					String type=arg0.getParameter("type");
					if(type==null || type.isEmpty()){
						type="1";
					}
					String userid="0";
					String ipaddress=getIpAddr(arg0);
					if(ipaddress==null){
						ipaddress="";
					}
					if(ipaddress.length()>50){
						ipaddress=ipaddress.substring(0,50);
					}
					
						if(student!=null){
							userid = String.valueOf(student.getID());
						}else{
							userid=ipaddress;
						}
						AccessActive accessActive=new AccessActive();
						accessActive.setID(String.valueOf(SmBaseUtil.CreateNewID()));
						accessActive.setAccessUrl(requestUrl);
						accessActive.setIPAddress(ipaddress);
						accessActive.setUserID(userid);
						accessActive.setType(Integer.parseInt(type));//1表示微新闻社
						accessActive.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						System.err.println(ipaddress);
						if(ipaddress!=null && !ipaddress.isEmpty() && !internalIp(ipaddress)){
							JSONObject obj=NetUtil.doGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ipaddress);
							if(obj!=null && obj.containsKey("data") && !obj.getString("data").isEmpty() && obj.getString("data").contains("region")){
								JSONObject data=obj.getJSONObject("data");
								if(data.containsKey("region") && data.containsKey("city")){
									accessActive.setAccessArea(data.getString("region")+data.getString("city"));
								}
							}
						}else{
							accessActive.setAccessArea("内网IP");
						}
						if(AccessActiveService!=null){
							try{
								AccessActiveService.addAccessActive(accessActive);
							}catch (Exception e) {
								AccessActiveService.updateAccessActive(accessActive);
							}
						}
				
					
				}catch (Exception e) {
					try{
						AccessActive accessActive=new AccessActive();
						accessActive.setID(String.valueOf(SmBaseUtil.CreateNewID()));
						accessActive.setAccessUrl(requestUrl);
						accessActive.setIPAddress("");
						accessActive.setUserID("0");
						accessActive.setType(1);//1表示微新闻社
						accessActive.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						accessActive.setAccessArea("无法识别");
						if(AccessActiveService!=null){
							AccessActiveService.addAccessActive(accessActive);
						}
					}catch (Exception e1) {
						//ErrorUtil.HandleError(null, "wtb.smUtil.SmBaseUtil.addAccessActivity", e1);
					}
					
				}
			
		}};
		t.start();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 判断是否需要微信登录
	 * @return
	 */
	public static boolean CheckIsWeChatLogin(String requestUrl,HttpServletRequest arg0){
		if(WeixinUtil.isWeXin(arg0,requestUrl)){
			if(requestUrl.equals("/Students/phoneRegister")){
				if(arg0.getParameter("returnURL")!=null){
					arg0.getSession().setAttribute("returnURL", arg0.getParameter("returnURL"));
				}
				return true;
			}else if(requestUrl.contains("Competition") && requestUrl.contains("Details")){
	    		return true;
	    	}else if(requestUrl.contains("Product/phoneweChatPordDetail")){
	    		return true;
	    	}
			
			
		}
		return false;
	}
	public static boolean internalIp(String ip) {
		if(ip==null || ip.isEmpty() || ip.equals("127.0.0.1")) {
			return true;
		}
	    byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
	    return internalIp(addr);
	}


	public static boolean internalIp(byte[] addr) {
		if(addr==null || addr.length>1){
			return false;
		}
	    final byte b0 = addr[0];
	    final byte b1 = addr[1];
	    //10.x.x.x/8
	    final byte SECTION_1 = 0x0A;
	    //172.16.x.x/12
	    final byte SECTION_2 = (byte) 0xAC;
	    final byte SECTION_3 = (byte) 0x10;
	    final byte SECTION_4 = (byte) 0x1F;
	    //192.168.x.x/16
	    final byte SECTION_5 = (byte) 0xC0;
	    final byte SECTION_6 = (byte) 0xA8;
	    switch (b0) {
	        case SECTION_1:
	            return true;
	        case SECTION_2:
	            if (b1 >= SECTION_3 && b1 <= SECTION_4) {
	                return true;
	            }
	        case SECTION_5:
	            switch (b1) {
	                case SECTION_6:
	                    return true;
	            }
	        default:
	            return false;

	    }
	}
	public static WeChatInfo getDefaultWeChatInfo(WeChatInfoServices WeChatInfoServices ,String appid){
		WeChatInfo weChatInfo=WeChatInfoServices.getDefWeChat();
		Map<String , Object> map=new HashMap<String, Object>();
		if(appid!=null && !appid.isEmpty() && !appid.equals("null")){
			map = new HashMap<String, Object>();
			map.put("AppID", appid);
			List<WeChatInfo> chatInfos=WeChatInfoServices.getWeChatInfo(map);
			if(chatInfos.size()>0){
				weChatInfo=chatInfos.get(0);
			}
		}
		if (weChatInfo==null) {
			weChatInfo=WeChatInfoServices.getWeightWeChat();
		} 
		return weChatInfo;
	}
	public static int getCurrentMonthLastDay()  
	{  
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    return maxDate;  
	}  
	
	/**
	 * 判断是否是周末
	 * @return
	 */
	public static Boolean isWeedEnd(){
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(new Date());
	 if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
	 {
	  return true;
	 }
	    return false;
	 }
	
	public static void AddAutoRewardWeMoneyForRule(WeMoneyClassify classify,wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.WeMoneyService WeMoneyService 
			,read.core.service.ReadWeMoneyService ReadWeMoneyService ,read.core.service.ReadVoteService ReadVoteService,read.core.service.ReadCommentService ReadCommentService,Notices noticesObj,
			wtb.core.service.NoticesService NoticesService,Students user,read.core.service.ReadWeMoneyRecordService ReadWeMoneyRecordService){
		NoticesController notices=new NoticesController();
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("Classify", classify.getid());
		map.put("Belong", noticesObj.getID());
		map.put("Type", SmBaseGlobal.WeMoneyType.AutoReward.getid());
		map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		map.put("Sina", SmBaseUtil.Random());
		int baseWeMoney=0;
		int rewardLevel=0;
		if(classify.getid()==WeMoneyClassify.Comment.getid()){
			int UserCount = ReadCommentService.getCommentUserCount(noticesObj.getID());
			//不同的人评论，超过10条评论即可开始打赏，打赏5微米 ，每过10条前基数上奖励5微米
			if(UserCount>=10){
				rewardLevel = (UserCount-(UserCount%10))/10;
				map.put("RewardLevel", rewardLevel);
				int modNum=ReadWeMoneyRecordService.getWeMoneyRecordCount(map);
				if(modNum==0){
					baseWeMoney = (UserCount/10)*SmBaseGlobal.BaseRewardWeMoney;
				}
			}
		}else if(classify.getid()==WeMoneyClassify.Read.getid()){
			if(noticesObj.getClickCount()>=100){
				rewardLevel = (noticesObj.getClickCount()-(noticesObj.getClickCount()%10))/100;
				map.put("RewardLevel", rewardLevel);
				int modNum=ReadWeMoneyRecordService.getWeMoneyRecordCount(map);
				if(modNum==0){
					baseWeMoney = (noticesObj.getClickCount()/100)*SmBaseGlobal.BaseRewardWeMoney;
				}
			}
		}else if(classify.getid()==WeMoneyClassify.Like.getid()){
				rewardLevel =  (int)(noticesObj.getLike()-(noticesObj.getLike()%50l))/10;
				map.put("RewardLevel", rewardLevel);
				int modNum=ReadWeMoneyRecordService.getWeMoneyRecordCount(map);
				if(modNum==0){
					baseWeMoney = (int)(noticesObj.getLike()/50l)*SmBaseGlobal.BaseRewardWeMoney;
				}
		}else if(classify.getid()==WeMoneyClassify.Vote.getid()){
			rewardLevel =  (int)(noticesObj.getVoteCount()-(noticesObj.getVoteCount()%50l))/10;
			map.put("RewardLevel", rewardLevel);
			int modNum=ReadWeMoneyRecordService.getWeMoneyRecordCount(map);
			if(modNum==0){
				baseWeMoney = (int)(noticesObj.getLike()/50l)*SmBaseGlobal.BaseRewardWeMoney;
			}
		}
		if(baseWeMoney>0){
			notices.addAutoRewardWeMoney(noticesObj,user.getID(),(long)baseWeMoney/3,WeMoneyRecordService,WeMoneyService,ReadWeMoneyService,ReadVoteService,classify,rewardLevel);
			if(classify.getid()==WeMoneyClassify.Comment.getid()){
				NoticesService.UpDateCommentCount(noticesObj.getID());
			}
			
		}
		/* 活动文章规则 */
		if(noticesObj.getVoteID()>0){
			 map=new HashMap<String, Object>();
			 map.put("ID", noticesObj.getVoteID());
			 map.put("IsEffect", noticesObj.getVoteID());//只有在活动期间才可以
			 List<Vote> lsvote=ReadVoteService.getVoteList(map);
			 if(lsvote.size()>0){
				 
			 }
			
		}
	}
	public static String StripHT(String strHtml) {  
	     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签    
	        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符    
	        return txtcontent;  
	   }  
	  
	public static int Random4() {  
		long randomNum = System.currentTimeMillis();  
		int randomNumber = (int) randomNum%(9999-1000)+1000;
		return randomNumber;  
	}  
	
	public static  String WeChatAPIURL(HttpServletRequest request){
		String basePath=request.getScheme() + "://"
    			+ "www.whohelp.cc/" ;//+ ":" + request.getServerPort();
    
    	return basePath;
	}
	
	public static String getSystemInfo(){
		Properties p=System.getProperties();
		return p.getProperty("os.name", "linux ")+p.getProperty("os.version", "");
	}
	
	public static boolean CheckIsNull(String str){
		boolean flag=true;
		if (str==null||str.isEmpty()||str.equals("null")||str.equals("undefined")) {
			flag=false;
		}
		return flag;
	}
}
