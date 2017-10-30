package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.smUtil.NetUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.WeNewsUtills;
import wtb.smUtil.WeixinUtil;


/**
 * 所有PC端页面请求都从这边过
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/WeNews")
public class WeNewsUserController extends BaseController {
	
	/**个人中心 用户信息 
	 * @throws IOException */
	@RequestMapping(value = "/UserLogin", method = RequestMethod.GET)
	public String UserLogin(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String returnUrl=request.getParameter("returnUrl");
		if(returnUrl!=null && !returnUrl.isEmpty()){
			request.getSession().setAttribute("returnUrl", returnUrl);
		}
			
		String code=request.getParameter("code");
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");   
		String returnURL=SmBaseUtil.getCurrentWebUrl(request)+ requestUrl+((request.getQueryString()!=null)?"?"+request.getQueryString():"");
		String url="https://open.weixin.qq.com/connect/qrconnect?appid="+WeNewsUtills.WENEWSAPPID+"&redirect_uri="+returnURL+"&response_type=code&scope=snsapi_login#wechat_redirect";
		if(code==null){
			return "redirect:"+url;
		}else{
			String Acess_token="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WeNewsUtills.WENEWSAPPID+"&secret="+WeNewsUtills.WENEWSAPPSECEET+"&code="+code+"&grant_type=authorization_code";
    		JSONObject obj=NetUtil.doGet(Acess_token);
    		//{"access_token":"zHiyUWVTk6JjCj5DnTf8nN2r1ZfC9scjCKTCpIU0pfj3UKOTXKA9PMv6_mQ2O-I1Xaq9J49DiP9W12Eo2RNMq00A3IjeUHbK5mt1h0IGhwE","expires_in":7200,"refresh_token":"KqlWCQiogNhyneh5vbHXMNo-LOzcxF6zK-az8qLLpoo5CqMGkK1E0iWhTPBrDiT6QZgBkVYdhfZ5CBJqc59bz-HY3lsMJXCwVFVUz1OerqM","openid":"oHXsRv9c4JfG5BIzKCT2FTnbRsp8","scope":"snsapi_login","unionid":"oD7arwH9x7r4Y8xsh3lSUV7Z72-g"}
    		if(obj!=null && obj.containsKey("openid")){
        		String OpenID=obj.getString("openid");
        		if(obj.containsKey("unionid")){
        			OpenID=obj.getString("unionid");
        		}
        		String access_token=obj.getString("access_token");
				String getUserInfo="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+OpenID;
				JSONObject UserInfo=NetUtil.doGet(getUserInfo);
				Students StudentFlag= WeixinUtil.verifyWeChatUser(UserInfo, ReadStudentsService, StudentsService, request,response, WeChatUserService, RegionService, ReadWeChatBindService);
				if (StudentFlag==null) {
					return "redirect:"+url;//用户更新失败的
				}
	    		Students student=(Students)request.getSession().getAttribute("StudentName");
	    		if(student!=null && student.getPhone()!=null && !student.getPhone().isEmpty() && (StudentFlag.getPhone()==null ||StudentFlag.getPhone().isEmpty())){
	    			StudentFlag.setPhone(student.getPhone());
	    		}
				if(StudentFlag.getPhone()!=null && !StudentFlag.getPhone().isEmpty() ){
					StudentsController controller=new StudentsController();
					controller.UpdateWeMoney(StudentFlag,StudentsService,request,response,request.getSession(),ReadWeMoneyService,WeMoneyService);
				}else{
					returnUrl=(String)request.getSession().getAttribute("returnUrl");
					if(returnUrl==null || returnUrl.isEmpty() ){
						returnUrl = SmBaseUtil.getProjectBaseUrl(request)+"WeNews/Register";
					}else{
						returnUrl = SmBaseUtil.getProjectBaseUrlNoProject(request)+ URLDecoder.decode(returnUrl);
						request.getSession().removeAttribute("returnUrl");
					}
				}
    		}
		}
		if(returnUrl!=null && !returnUrl.isEmpty()){
			response.sendRedirect(returnUrl);
		}else{
			response.sendRedirect(SmBaseUtil.getProjectBaseUrl(request)+"WeNewsHome");
		}		
		return  null;
	}
	
	
	

	@RequestMapping(value = "/BindUser", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeNewStudentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> returnMap=new HashMap<>();
		String phone=request.getParameter("Phone");
		String code=request.getParameter("Code");
		String password=request.getParameter("PassWord");
		
		if(phone==null){
			returnMap.put("status", false);
			returnMap.put("message", "手机号码为空");
			return	returnMap;
		}
		
		if(code==null){
			returnMap.put("status", false);
			returnMap.put("message", "验证码为空");
			return	returnMap;
		}
		
		if(password==null){
			returnMap.put("status", false);
			returnMap.put("message", "密码为空");
			return	returnMap;
		}
		
		String AuthenPhone=(String) request.getSession().getAttribute("AuthenPhone");
		String AuthenCode=(String) request.getSession().getAttribute("AuthenCode");
		if (AuthenPhone!=null && AuthenCode!=null && AuthenPhone.equals(phone)&&AuthenCode.equals(code)) {
			Students students=(Students)request.getSession().getAttribute("StudentName");
			boolean isnew=false;
			if(students==null){
				students=new Students();
				long id=SmBaseUtil.CreateNewID();
				students.setID(id);
				students.setImageUrl(SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
				students.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				isnew=true;
			}
			returnMap=new HashMap<>();
			returnMap.put("Phone", phone);
			List<Students> stus=ReadStudentsService.getStudentsList(returnMap);
			if(stus.size()>1 && (isnew || (stus.get(0).getOpenID()!=null && stus.get(0).getOpenID().equals(students.getOpenID())))){
				returnMap=new HashMap<>();
				returnMap.put("status", false);
				returnMap.put("message", "该手机号码已经被注册");
				return returnMap;
			}
			if(stus.size()>1 && students.getOpenID()!=null && !students.getOpenID().isEmpty()){
				stus.get(0).setOpenID(students.getOpenID());
				stus.get(0).setUnionID(students.getUnionID());
				stus.get(0).setPhone(phone);
				stus.get(0).setPassWord(SmBaseUtil.MD5(password));
				
				/* 查询是否已经能够存在同样的记录如果存在先删除新生成的微信用,然后在更新旧的OpenID */
				Map<String, Object> responseMap = new HashMap<String, Object>();
				responseMap.put("ID", students.getID());
				responseMap.put("Sina", SmBaseUtil.Random());
				List<Students> checkisExist = ReadStudentsService.getStudentsList(responseMap);
				if (checkisExist.size() > 0) {
					responseMap.put("OpenID", students.getOpenID());
					StudentsService.deleteStudents(responseMap);
				}
				students = stus.get(0);
				isnew = false;
			}else{
				students.setPhone(phone);
				students.setPassWord(SmBaseUtil.MD5(password));
			}
			
		
			if(isnew){
				StudentsService.addStudents(students);
				NoticesController notices=new NoticesController();
				notices.getDefaultWeMoneyInfo(students.getID(), WeMoneyService, ReadWeMoneyService);
			}else{
				StudentsService.updateStudents(students);
			}
			SmBaseUtil.CreateSession("StudentName", students, request, session, response);
			returnMap=new HashMap<>();
			returnMap.put("status", true);
			returnMap.put("parentName", students.getParentName());
			returnMap.put("message", "用户创建成功");
			
		}else{
			returnMap=new HashMap<>();
			returnMap.put("status", false);
			returnMap.put("message", "验证码错误");
		}
		return returnMap;
	}
	
	
	@RequestMapping(value = "/EditPWD", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> EditPWD(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> returnMap=new HashMap<>();
		String phone=request.getParameter("Phone");
		String code=request.getParameter("Code");
		String password=request.getParameter("PassWord");
		
		if(phone==null){
			returnMap.put("status", false);
			returnMap.put("message", "手机号码为空");
			return	returnMap;
		}
		
		if(code==null){
			returnMap.put("status", false);
			returnMap.put("message", "验证码为空");
			return	returnMap;
		}
		
		if(password==null){
			returnMap.put("status", false);
			returnMap.put("message", "密码为空");
			return	returnMap;
		}
		
		String AuthenPhone=(String) request.getSession().getAttribute("AuthenPhone");
		String AuthenCode=(String) request.getSession().getAttribute("AuthenCode");
		if (AuthenPhone!=null && AuthenCode!=null &&  AuthenPhone.equals(phone)&&AuthenCode.equals(code)) {
			Map<String, Object> queryMap=new HashMap<>();
			queryMap.put("TPhone", phone);
			List<Students> students=ReadStudentsService.getStudentsList(queryMap);
			if(students.size()>0){
				students.get(0).setPassWord(SmBaseUtil.MD5(password));
				queryMap=new HashMap<>();
				StudentsService.ChangePassword(students.get(0));
				returnMap.put("status", true);
				returnMap.put("message", "设置成功");
			}else{
				returnMap.put("status", false);
				returnMap.put("message", "用户不存在");
			}
		}else{
			returnMap.put("status", false);
			returnMap.put("message", "验证码错误");
		}
		return returnMap;
	}
	@RequestMapping(value = "/PhoneLogin", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> PhoneLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> returnMap=new HashMap<>();
		Map<String, Object> queryMap=new HashMap<>();
		String phone=request.getParameter("Phone");
		String password=request.getParameter("password");
		
		if(phone==null){
			returnMap.put("status", false);
			returnMap.put("message", "手机号码为空");
			return	returnMap;
		}
		
		if(password==null){
			returnMap.put("status", false);
			returnMap.put("message", "密码为空");
			return	returnMap;
		}
		
		queryMap.put("TPhone", phone);
		queryMap.put("PassWord", SmBaseUtil.MD5(password));
		List<Students> students=ReadStudentsService.getStudentsList(queryMap);
		if (students.size()>0) {
			returnMap.put("status", true);
			returnMap.put("message", "登录成功");
			request.getSession().setAttribute("StudentName", students.get(0));//说明该学生存在
		}else{
			returnMap.put("status", false);
			returnMap.put("message", "手机号或者密码有误");
		}
		return returnMap;
	}
	
	@RequestMapping(value = "/PhoneCodeLogin", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> PhoneCodeLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> returnMap=new HashMap<>();
		Map<String, Object> queryMap=new HashMap<>();
		String phone=request.getParameter("Phone");
		String code=request.getParameter("code");
		
		if(phone==null){
			returnMap.put("status", false);
			returnMap.put("message", "手机号码为空");
			return	returnMap;
		}
		
		if(code==null){
			returnMap.put("status", false);
			returnMap.put("message", "验证码为空");
			return	returnMap;
		}
		
		String AuthenPhone=(String) request.getSession().getAttribute("AuthenPhone");
		String AuthenCode=(String) request.getSession().getAttribute("AuthenCode");
		if (AuthenPhone!=null && AuthenPhone.equals(phone)&&AuthenCode!=null && AuthenCode.equals(code)) {
				queryMap=new HashMap<>();
				queryMap.put("Phone", phone);
				List<Students> students=ReadStudentsService.getStudentsList(queryMap);
				if (students.size()>0) {
					if (students.get(0).getPhone()==null) {
						students.get(0).setPhone(phone);
						StudentsService.updateStudents(students.get(0));
					}
					returnMap.put("status", true);
					returnMap.put("message", "登录成功");
					request.getSession().setAttribute("StudentName", students.get(0));//说明该学生存在
				}else{
					Students studentss=new Students();
					studentss.setPhone(phone);
					request.getSession().setAttribute("StudentName", studentss);//说明该学生存在
					returnMap.put("status", true);
					returnMap.put("message", "验证成功");
				}
			
		}else{
			returnMap.put("status", false);
			returnMap.put("message", "手机号码或验证码错误");
		}
		return returnMap;
	}
	
	
	@RequestMapping(value = "/UserEdits", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> UserEdits(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> returnMap=new HashMap<>();
		Map<String, Object> queryMap=new HashMap<>();
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if(user==null){
			returnMap.put("Status", false);
			returnMap.put("Message", "长时间未操作，请重新登录！");
			return returnMap;
		}
	
		String Name=request.getParameter("Name");
		String Age=request.getParameter("Age");
		String Sex=request.getParameter("Sex");
		String Phone=request.getParameter("Phone");
		String ParentName=request.getParameter("ParentName");
		String Habit=request.getParameter("Habit");
		String Email=request.getParameter("Email");
		String Grade=request.getParameter("Grade");
		String AreaID=request.getParameter("AreaID");
		String imageID=request.getParameter("ImageID");
		String ImageUrl=request.getParameter("ImageUrl");
		if (Name!=null) {
			user.setName(Name);
		}
		if (Age!=null && !Age.isEmpty()) {
			user.setAge(SmBaseUtil.URLDecoderString(Age));
			try
			{
				Date date = sdfShort.parse(user.getAge());
			} catch (ParseException e){
				returnMap.put("Status", false);
				returnMap.put("Message", "生日格式不正确，正确格式如:2007-11-01");
				return returnMap;
			}
		}
		if (Email!=null) {
			user.setEmail(Email);
		}
		if (Habit!=null) {
			user.setHabit(Habit);
		}
		if (ParentName!=null) {
			user.setParentName(ParentName);
		}
		if (Sex!=null) {
			user.setSex(Sex);
		}
		if (Phone!=null) {
			user.setPhone(Phone);
		}
		
		if (Grade!=null) {
			user.setGrade(Grade);
		}
		if(AreaID==null || AreaID.isEmpty()){
			AreaID = "0";
		}
		if (AreaID!=null) {
			user.setAreaID(AreaID);

			List<Region> regions= ReadRegionService.getRegionByIDList(AreaID);
			if(regions.size()>0){
				user.setSchool(regions.get(0).getName());
				user.setArea(regions.get(0));
			}
			
		}
		if(imageID!=null && !imageID.isEmpty() && !imageID.equals("0") && !imageID.equals("null")){
			user.setImageID(Long.parseLong(imageID));
			if(ImageUrl!=null && !ImageUrl.isEmpty()){
				user.setImageUrl(ImageUrl);
			}
		}
		int i=StudentsService.updateStudents(user);
		if(user.getPhone()!=null && !user.getPhone().isEmpty() ){
			StudentsController controller=new StudentsController();
			user=controller.UpdateWeMoney(user,StudentsService,request,response,request.getSession(),ReadWeMoneyService,WeMoneyService);
		}
		returnMap=new HashMap<>();
		if (user!=null) {
			
			returnMap.put("Status", true);
			returnMap.put("Message", "保存成功");
			SmBaseUtil.CreateSession("StudentName", user, request, session, response);
		}else{
			returnMap.put("Status", false);
			returnMap.put("Message", "保存失败");
		}
		return returnMap;
	}
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public ModelAndView loginOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		
		Users user = null;
		try{
			if(session!=null){
				session.setAttribute("StudentName", user);
				session.removeAttribute("StudentName");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
			Cookie ck = new Cookie("JSESSIONID", System.currentTimeMillis() + "");
			ck.setMaxAge(-3600);
			response.addCookie(ck);
			String returnUrl=request.getParameter("returnUrl");
			
			if(returnUrl==null || returnUrl.isEmpty() ){
				returnUrl = SmBaseUtil.getProjectBaseUrl(request)+"WeNewsHome";
			}else{
				if(returnUrl.length()>1 && returnUrl.substring(0,1).equals("/")){
					returnUrl = returnUrl.substring(1,returnUrl.length());
				}
				returnUrl = SmBaseUtil.getProjectBaseUrlNoProject(request)+URLDecoder.decode(returnUrl);
			}
			response.sendRedirect(returnUrl);
		
		return new ModelAndView(SmBaseGlobal.WeNewsPCViewPath + "WeNewsHome");
	}

	
	

}
