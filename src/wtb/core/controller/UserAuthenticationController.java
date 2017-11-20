package wtb.core.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import read.core.service.ReadStudentsService;
import read.core.service.ReadWeChatBindService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import net.sf.json.JSONObject;
import wtb.core.model.OnLine;
import wtb.core.model.Students;
import wtb.core.model.WeChatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatUser;
import wtb.core.service.AccessActiveService;
import wtb.core.service.NoticesService;
import wtb.core.service.OnLineService;
import wtb.core.service.RegionService;
import wtb.core.service.StudentsService;
import wtb.core.service.WeChatBindService;
import wtb.core.service.WeChatInfoServices;
import wtb.core.service.WeChatUserService;
import wtb.smUtil.BaseUtil;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.NetUtil;
import wtb.smUtil.SessionContext;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.WeixinUtil;
public class UserAuthenticationController implements HandlerInterceptor 
{

	/**
	 * 一个自定义的拦截器，实现：preHandle postHandle afterCompletion 三个接口
	 */
	
		private List<String> uncheckUrls; 
		private List<String> whiteList; 
		private List<String> phoneList;
		private List<String> wenewsList;
		@Autowired
		private StudentsService StudentsService;
		@Autowired
		private WeChatUserService WeChatUserService;
		@Autowired
		private RegionService RegionService;
		@Autowired
		private ReadStudentsService ReadStudentsService;
		@Autowired
		private WeChatInfoServices weChatInfoServices;
		@Autowired
		private ReadWeChatBindService ReadWeChatBindService;
		@Autowired
		private read.core.service.ReadNoticesService ReadNoticesService;
		public List<String> getWhiteList() {
			return whiteList;
		}

		public void setWhiteList(List<String> whiteList) {
			this.whiteList = whiteList;
		}

		public List<String> getPhoneList() {
			return phoneList;
		}

		public void setPhoneList(List<String> phoneList) {
			this.phoneList = phoneList;
		}

		public List<String> getWenewsList() {
			return wenewsList;
		}

		public void setWenewsList(List<String> wenewsList) {
			this.wenewsList = wenewsList;
		}

		@Override
		public void afterCompletion(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			
		}

		@Override
		public void postHandle(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2, ModelAndView arg3)
				throws Exception {
			
		}

		@SuppressWarnings("finally")
		@Override
		public boolean preHandle(HttpServletRequest arg0,
				HttpServletResponse arg1, Object arg2) throws Exception {
			try{
			String requestUrl = arg0.getRequestURI().replace(arg0.getContextPath(), "");   
			Map<String, Object> responseMap = new HashMap<String, Object>();
			String queryString=arg0.getQueryString();
			System.err.println(queryString);
			if(queryString==null){
				queryString="";
			}
			if (true) {
				return true;
			}
			queryString="?"+queryString;
			if (requestUrl.equals("//index")||requestUrl.equals("/index")||requestUrl.equals("index")) {
				return true;
			}
			System.err.println(SmBaseUtil.getCurrentWebUrl(arg0));
			System.err.println(queryString);
			System.err.println(requestUrl);
			String returnURL=SmBaseUtil.getCurrentWebUrl(arg0)+ requestUrl+queryString;
			String WID=arg0.getParameter("WID");
			String pid=arg0.getParameter("_pid_");
			String retUrl = arg0.getParameter("returnURL");
			if(retUrl!=null && !retUrl.isEmpty()){
				arg0.getSession().setAttribute("returnURL", retUrl);
			}
			/** 
			 * 例外放行
			 */
			String isPass=SmBaseUtil.CheckIsPass(requestUrl, arg0,returnURL,ReadNoticesService,pid);
			if(isPass.equals("true") || isPass.equals(true)){//如果返回的是
				return true;
			}else if(isPass.contains("http://")){
				arg1.sendRedirect(isPass);
				return false;
			}
			/*静态资源和手机页面允许放行*/
			System.err.println(requestUrl);
	        if(!uncheckUrls.contains(requestUrl) || SmBaseUtil.CheckIsWeChatLogin(requestUrl, arg0)){ 
	        	System.err.println("未通过URL放行");
	        	Object user=null;
	        	Object student=null;    
	        	WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(weChatInfoServices, WID);
	        	try{
	        		student=arg0.getSession().getAttribute("StudentName");
	        		user=arg0.getSession().getAttribute("UserName");
	        		if(student!=null){
		        		if(WID!=null && !WID.isEmpty()){
			        		((Students)student).setTempOpenID(null);
			        	}else{
			        		((Students)student).setTempOpenID(((Students)student).getOpenID());
			        	}
		        		System.err.println(((Students)student).getTempOpenID());
	        		}
	        	}catch (Exception e) {
	        		e.printStackTrace();
				}
	        	//-------------------------------------- 因为自己测试所以注释掉
	        	String code=arg0.getParameter("code");
	        	Boolean islogin=false;
	        	if (WeixinUtil.isWeXin(arg0,requestUrl) &&requestUrl.contains("phone")&&(student==null || ((Students)student).getTempOpenID()==null || ((Students)student).getTempOpenID().isEmpty()) ) {
	        		if (code==null) 
		        	{
		        		arg1.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+weChatInfo.getAppID()+"&redirect_uri="+URLEncoder.encode(returnURL, "utf-8")+"&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect");
	        			return false;
		        	}else{
		        		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+weChatInfo.getAppID()+"&secret="+weChatInfo.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
		        		JSONObject obj=NetUtil.doGet(url);
		        		System.err.println(obj);
		        		
		        		//用户说登录不上，我这边做测试  保留这块，以后可以做测试用
		        		//obj=JSONObject.fromObject("{\"openid\":\"o9tels17scdvo2mZQaWW-mCPXT-w\",\"unionid\":\"oD7arwNQARaX0gMvM8QOe0fese84\"}");
		        		 
		        		
		        		Students studen=WeixinUtil.verifyWeChatUser(obj, ReadStudentsService, StudentsService, arg0,arg1, WeChatUserService, RegionService, ReadWeChatBindService);
		        		if(studen!=null){
		        			islogin=true;
		        		}
		        	}
				}
	        	
	 	        if(!whiteList.contains(requestUrl)){
	 	        	Object student2=arg0.getSession().getAttribute("StudentName");
	 	        	if(student2!=null && !islogin){
	 	        		List<Students> students= new ArrayList<Students>();
	 	        		Students stu=(Students)student2;
	 	        		if((stu.getOpenID()!=null && !stu.getOpenID().isEmpty()) || (stu.getUnionID()!=null && !stu.getUnionID().isEmpty())){
	 	        			students=SmBaseUtil.getStudentByOpenID(stu.getOpenID(),stu.getUnionID(), ReadStudentsService, ReadWeChatBindService);
	 	        		}else{
	 	        			responseMap = new HashMap<String, Object>();
		 	   				responseMap.put("ID", stu.getID());
		 	   				responseMap.put("Sina", SmBaseUtil.Random());
		 	   				students = ReadStudentsService.getStudentsList(responseMap);
	 	        		}
		 	        	if(students.size()>0){
		 	        		System.err.println(((Students)student2).getTempOpenID());
		 	        		if(((Students)student2).getTempOpenID()!=null){
		 	        			students.get(0).setTempOpenID(((Students)student2).getTempOpenID());
		 	        		}else{
		 	        			students.get(0).setTempOpenID(students.get(0).getOpenID());
		 	        		}
		 	        		System.err.println(students.get(0).getTempOpenID());
		 	        		SmBaseUtil.CreateSession("StudentName", students.get(0), arg0, arg0.getSession(), arg1);
		 	        	}
	 	        	}
	 	        	
	 	        	if(requestUrl.contains("phone") && student2==null){
	 	        		arg1.sendRedirect(SmBaseUtil.getWeChatLoginUrl("snsapi_userinfo",SmBaseUtil.getCurrentWebUrl(arg0)+ "/Students/phonelogin?returnURL="+URLEncoder.encode(returnURL, "utf-8"),arg0));
	 	        		return false;
	 	        	}else if(!requestUrl.contains("phone") && user==null){
	 	        		arg1.sendRedirect(arg0.getContextPath()+"/Users/login");
	 	        		return false;
	 	        	}
	 	        	return true;
	 	        }else{
	 	        	return true;  
	 	        }
	        }else{
	        	System.err.println("go");
	        	return true;
	        }
			}catch (Exception e) {
				e.printStackTrace();
				ErrorUtil.HandleError(null, "wtb.core.UserAuthenticationController.preHandle", e);
				return true;
			}
			
		}

		public List<String> getUncheckUrls() {
			return uncheckUrls;
		}

		public void setUncheckUrls(List<String> uncheckUrls) {
			this.uncheckUrls = uncheckUrls;
		}}

