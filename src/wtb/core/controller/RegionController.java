package wtb.core.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.StudentsLog;
import wtb.core.model.Users;
import wtb.core.model.WeChatBind;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatUser;
import wtb.core.service.RegionService;
import wtb.sessions.MySessionContext;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_region
 * @param <XBUserService>
 */
@Controller
@RequestMapping("/Region")
public class RegionController extends BaseController {

	/**
	 * 添加信息
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/addRegion", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> addRegion(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			String ParentID = request.getParameter("ParentID");
			String AreaCode = SmBaseUtil.URLDecoderString(request.getParameter("AreaCode"));
			String AreaName = SmBaseUtil.URLDecoderString(request.getParameter("AreaName"));


			String ID = request.getParameter("ID");
			String Message="";
			long UserID = 0;
			if (ID!=null &&!ID.isEmpty() && Long.parseLong(ID)>0) {
				UserID = Long.parseLong(ID);
			} else {
				UserID = new IdWorker(1, 0).nextId();
			}
			
			if(AreaName==null || AreaName.isEmpty()){
				Message+="区域名称不能为空；";
			}
			if(AreaCode==null || AreaCode.isEmpty()){
				Message += "区域编码不能为空；";
			}
			if(ParentID==null || ParentID.isEmpty()){
				Message+="父节点不能为空；";
			}
			
			List<Region> regionlist= RegionService.getRegionByIDList(ID);
			if(regionlist.size()>0){
				regionlist.get(0).setCode(AreaCode);
				regionlist.get(0).setName(AreaName);
				RegionService.updateRegion(regionlist.get(0));
				
			}else{
				if(!ParentID.isEmpty()){
					List<Region> parent= RegionService.getRegionByIDList(ParentID);
					int level=SmBaseGlobal.RegionType.School.getid();
					if(parent.size()>0){
						level=parent.get(0).getLevel()+1;
					}
					Region region=new Region();
					region.setID(String.valueOf(UserID));
					region.setParentID(ParentID);
					region.setName(AreaName);
					region.setName_En(SmBaseUtil.getPingYin(AreaName));
					region.setShortName_En(SmBaseUtil.getPinYinHeadChar(AreaName));
					region.setCode(AreaCode);
					region.setLevel(level);
					RegionService.addRegion(region);
					if(region.getLevel()==SmBaseGlobal.RegionType.School.getid()){
						WeChatPublic weChatPublic=new WeChatPublic();
						weChatPublic.setID(SmBaseUtil.CreateNewID());
						weChatPublic.setWeChat(region.getCode());
						weChatPublic.setCompany(region.getName());
						weChatPublic.setAreaID(region.getID());
						weChatPublic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						weChatPublic.setBind(1);
						WeChatPublicService.addWeChatPublic(weChatPublic);
					}
				}
			}
			
			if(!Message.isEmpty()){
				responseMap.put("Status", -1);
				responseMap.put("ErrorMsg", Message);
			}else{
				responseMap.put("Status", 1);
				responseMap.put("ErrorMsg", "添加成功");
			}

		    return responseMap;
	 }
	
	@RequestMapping(value = "/RegionList", method = RequestMethod.GET)
	public @ResponseBody ModelAndView RegionList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, null);

		return new ModelAndView(SmBaseGlobal.WebViewPath + "regionList");
	 }

	/**
	 * 获取信息列表
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value="/getRegionList",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getRegionList(HttpServletRequest request,   
            HttpServletResponse response,HttpSession session){
		Map<String, Object> responseMap = new HashMap<String, Object>();
				Map<String,Object> params = new HashMap<String,Object>();
				SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
				params = amBaseUtil.getParamsMap(request);
				if(params.containsKey("check") && !((String)params.get("check")).isEmpty()){
					params.remove("ParentID");
				}
				List<Region> region = ReadRegionService.getRegionList(params);
				int Count=ReadRegionService.getRegionCount(params);
				responseMap.put("total",Count);
				
			responseMap.put("Data", region);
			responseMap.put("Status", 1);
		    return responseMap;
	 }
	/**
	 * 获取信息列表
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value="/getParentRegionInfo",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getParentRegionInfo(HttpServletRequest request,   
            HttpServletResponse response,HttpSession session){
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		String ParentID = request.getParameter("parentID");
		params.put("ID", ParentID);
		
		List<Region> region = ReadRegionService.getRegionList(params);
		responseMap.put("Data", region);
		responseMap.put("Status", 1);
	    return responseMap;
	 }
	/**
	 * 获取学生用户
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getRegionLists", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getRegionLists(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String check = request.getParameter("check");
		if (check != null && !check.isEmpty()) {
			check = SmBaseUtil.URLDecoderString(check);
			checkParammap.put("check", check);
		}
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Region> lswe =ReadRegionService.getRegionList(checkParammap);
		int WeChatCount = ReadRegionService.getRegionCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", WeChatCount);
		responseMap.put("Status", 1);
		return responseMap;
	}
	
	/**
	 * 更新信息
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value="/updateRegion",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> updateRegion(HttpServletRequest request,   
            HttpServletResponse response){
			String session_id = request.getParameter("SessionID");
			MySessionContext myc= MySessionContext.getInstance();
			HttpSession session = myc.getSession(session_id);
			Map<String,Object> responseMap = new HashMap<String,Object>();
			if(session!=null){
				/** 获取行业类别记录,并更新 **/  
				String text = request.getParameter("data");
				Region region = JSON.parseObject(text, Region.class);
				int status = RegionService.updateRegion(region);
				responseMap.put("status",status);
				responseMap.put("data",region);

			}else{
				responseMap=amBaseUtil.getresponseMap(0);
			}
		    return responseMap;
	 }
	
	

	
	
	/**
	 * 删除记录
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value="/deleteRegion",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> deleteRegion(HttpServletRequest request,   
            HttpServletResponse response){
			Map<String,Object> responseMap = new HashMap<String,Object>();
			//if(session!=null){
				Map<String,Object> params = new HashMap<String,Object>();
				SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
				params = amBaseUtil.getParamsMap(request);
				if(params.get("ID")!=null&&params.get("ID").toString().length()>0){
					int status = RegionService.deleteRegion(params);
					responseMap.put("status",status);
					responseMap.put("data",params);
			} else {
				responseMap = amBaseUtil.getresponseMap(1);
			}
		return responseMap;
	 }
	/**
	 * 获取地区信息
	 */
	public static Model getRegionParams(HttpServletRequest request,read.core.service.ReadRegionService ReadRegionService,Model model, HttpSession session,String AreaID){
		Map<String,Object> responseMap=new HashMap<String,Object>();
		Users user = null;

			user = (Users) session.getAttribute("UserName");
			Students Students = (Students) session.getAttribute("StudentName");
			String ParaAreaID=request.getParameter("UnitAreaID");
			if(ParaAreaID!=null && !ParaAreaID.isEmpty() && !ParaAreaID.equals("null")){
				AreaID=ParaAreaID;
			}
			String ProvinceID=request.getParameter("ProvinceID");
			String CityID=request.getParameter("CityID");
			String CityAreaID=request.getParameter("AreaID");
			if(AreaID==null || AreaID.isEmpty() || AreaID.equals("0") || AreaID.equals("null")){
				if(user!=null && (user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid() || user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid())){
					AreaID=String.valueOf(user.getAreaID());
				}else if(Students!=null ){
					AreaID=String.valueOf(Students.getAreaID());
				}else{
					AreaID="0";
				}
			}
			if(ProvinceID==null || ProvinceID.isEmpty()){
				ProvinceID="0";
			}
			if(CityID==null || CityID.isEmpty()){
				CityID="0";
			}
		
			if(!AreaID.isEmpty() && !AreaID.equals("0")){
				responseMap = new HashMap<String, Object>();
				if(null==AreaID || "".equals(AreaID)){
					if(Students!=null && Students.getAreaID()!=null && !Students.getAreaID().isEmpty()){
						AreaID=Students.getAreaID();
					}else{
						AreaID="0";
					}
				}
				if(AreaID==null || AreaID.equals("null")){
					AreaID="0";
				}
				if(!AreaID.equals("0")){
					responseMap.put("ID", AreaID);
				}else{
					responseMap.put("Level", 4);
					responseMap.put("limit", 1);
					responseMap.put("start", 0);
				}
				
				List<Region> lswechat = ReadRegionService.getRegionList(responseMap);
				if(lswechat.size()>0){
					responseMap = new HashMap<String, Object>();
					responseMap.put("ID", lswechat.get(0).getParentID());
					 lswechat = ReadRegionService.getRegionList(responseMap);
					 if(lswechat.size()>0){
						 CityAreaID=String.valueOf(lswechat.get(0).getID());
						// 获取城市
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", lswechat.get(0).getParentID());
						List<Region> lsRegion = ReadRegionService.getRegionList(responseMap);
						if(lsRegion.size()>0){
							CityID=String.valueOf( lsRegion.get(0).getID());
							// 获取省
							responseMap = new HashMap<String, Object>();
							responseMap.put("ID", lsRegion.get(0).getParentID());
							 lsRegion = ReadRegionService.getRegionList(responseMap);
							 ProvinceID=String.valueOf(lsRegion.get(0).getID());
						}
					 }
				}
			}
			if(user!=null){
				model.addAttribute("AdminLevelParseon", user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid());
				model.addAttribute("AdminLevel", user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid());
				model.addAttribute("AdminLevelArea", user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid());
				model.addAttribute("AdminLevelTeacher", user.getLevel()==SmBaseGlobal.LevelStatus.TeacherManage.getid());
				model.addAttribute("AdminLevelStudent", user.getLevel()==SmBaseGlobal.LevelStatus.StudentManage.getid());
			}
			
			if(user!=null && user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid()){
				ProvinceID=CityID;
				CityID=CityAreaID;
				CityAreaID=AreaID;
				
			}
			model.addAttribute("UnitID",AreaID);
			model.addAttribute("ProvinceID", ProvinceID);
			model.addAttribute("CityID", CityID);
			model.addAttribute("CityAreaID", CityAreaID);
		return model;
	}
	@RequestMapping(value="/phoneBindRegion",method=RequestMethod.GET)
	public @ResponseBody ModelAndView bindRegionget(HttpServletRequest request,   
            HttpServletResponse response,Model model,HttpSession session){
		if(request.getParameter("type")!=null){
			String basePath =SmBaseUtil.getProjectBaseUrl(request);
			model.addAttribute("returnView", basePath+"Students/phoneSinaUserInfoEdit");
		}
		String AreaId=request.getParameter("_area_");
		if(AreaId!=null && !AreaId.equals("null")){
			model=getRegionParams(request,ReadRegionService,model,session,AreaId);
		}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath+"phoneBindRegion");
	 }
	/**
	 * 绑定地区(学校)
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value = "/phoneBindRegion", method = RequestMethod.POST)
	public @ResponseBody ModelAndView bindRegion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

			String UnitAreaID = request.getParameter("UnitAreaID");
			String returnurl = request.getParameter("returnView");
			Students user = (Students) request.getSession().getAttribute("StudentName");
			List<Region> region=ReadRegionService.getRegionByIDList(UnitAreaID);
			if (user != null && region.size()>0) {
				user.setAreaID(UnitAreaID);
				user.setSchool(region.get(0).getName());
				user.setArea(region.get(0));
				StudentsService.updateStudents(user);
				Map<String, Object> responseMap = new HashMap<String, Object>();
				/*responseMap.put("UID", user.getID());
				List<XBUser>xbuser= XBUserService.getXBUserList(responseMap);
				
				if(xbuser.size()>0){
					XBUserService.updateXBUser(SmBaseUtil.StudentParseXBUser(user,xbuser.get(0),XBSChoolService,ReadRegionService));
				}*/
				responseMap = new HashMap<String, Object>();
				responseMap.put("PublishUser", user.getID());
				responseMap.put("AreaID", UnitAreaID);
				NoticesService.UpdateAreaIDForStudents(responseMap);
				SmBaseUtil.CreateSession("StudentName", user, request, session,response);
				response.sendRedirect(returnurl);
			}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath+ "phoneWeNewsHome");
	 }
	/**
	 * 绑定学校2自动
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="/SchoolBind")
	public @ResponseBody void BindRegion(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		checkIsBind(request.getParameter("ID"), ReadRegionService, ReadWeChatPublicService,WeChatPublicService,StudentsLogService);
	}
	
	
	public void checkIsBind(String id,read.core.service.ReadRegionService ReadRegionService,
			read.core.service.ReadWeChatPublicService ReadWeChatPublicService,wtb.core.service.WeChatPublicService WeChatPublicService,wtb.core.service.StudentsLogService StudentsLogService){

		Map<String, Object> map=new HashMap<>();

		List<Region> Region=ReadRegionService.getRegionByIDList(id);
		System.out.println(Region.size());
		//4表示学校,只有标识为学校的才能变为支社
		if(Region.size()>0 && Region.get(0).getLevel()==SmBaseGlobal.RegionType.School.getid()){
			map=new HashMap<>();
			map.put("AreaID",id);
			map.put("Rand",SmBaseUtil.getRandomString(10));
			List<WeChatPublic> count=ReadWeChatPublicService.getWeChatPublicList(map);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long WCID=SmBaseUtil.CreateNewID();
			if(count.size()==0)
			{
				WeChatPublic weChatPublic=new WeChatPublic();
				weChatPublic.setID(WCID);
				weChatPublic.setWeChat(Region.get(0).getCode());
				weChatPublic.setCompany(Region.get(0).getName());
				weChatPublic.setAreaID(id);
				weChatPublic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weChatPublic.setBind(1);
				WeChatPublicService.addWeChatPublic(weChatPublic);
				StudentsLog log=new StudentsLog();
				log.setID(SmBaseUtil.CreateNewID());//学校入驻id
				log.setCreateTime(sdf.format(new Date()));//学校入驻时间
				log.setStudentsID(WCID);
				log.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());//状态
				log.setType("2");//类型
				StudentsLogService.addStudentsLog(log);
			}else{
				WCID=count.get(0).getID();
				count.get(0).setBind(1);
				count.get(0).setCompany(Region.get(0).getName());
				WeChatPublicService.updateWeChatPublic(count.get(0));
			}
		}
	}
	
	@RequestMapping(value="/SyncXBRegion",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> SyncXBRegion(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();

			String school = request.getParameter("schoolid");
			StudentsController stucontrol = new StudentsController();
			if (school != null && !school.isEmpty()) {
				//stucontrol.getXBAreaID(Long.parseLong(school), XBSChoolService, XBRegionService, XBCityService, ReadRegionService,WeChatPublicService,RegionService);
				checkParammap.put("Status", 1);
				checkParammap.put("Message", "success");
			}else {
				checkParammap.put("Status", -1);
				checkParammap.put("Message", "fail");
			}

		return checkParammap;
	 }
	
	@RequestMapping(value = "/phoneBindWeChatUser", method = RequestMethod.GET)
	public ModelAndView phoneSinaUserInfoEdit(HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) throws IOException {

		Students user = (Students) req.getSession().getAttribute("StudentName");
		String openid= req.getParameter("openid");
		String uid= req.getParameter("uid");
		String auth= null;
		if(session.getAttribute("auth")!=null){
			try{
				auth= (String)session.getAttribute("auth");
			}catch (Exception e) {
				auth= "1";
			}
		}
		if(openid==null || openid.isEmpty()){
			openid="";
		}
		if(uid==null || uid.isEmpty()){
			uid="0";
		}
		model.addAttribute("openid", openid);
		model.addAttribute("uid", uid);
		model.addAttribute("auth", auth);
		Map<String, Object> responseMap = new HashMap<String, Object>();
//		responseMap.put("OpenID", openid);
//		responseMap.put("isStu", 1);
//		responseMap.put("Sina",SmBaseUtil.Random());
//		List<Students> lsstu = ReadStudentsService.getStudentsList(responseMap);
//		if(lsstu.size()>0 && !lsstu.get(0).getOpenID().equals(openid)){
//			return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneDealInfoFail");
//		}
		if (user != null ) {//&&  user.getID()==Long.parseLong(uid) && user.getOpenID().equals(openid)
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", uid);
			responseMap.put("Sina",SmBaseUtil.Random());
			List<Students> lsstu = ReadStudentsService.getStudentsList(responseMap);
			if(lsstu.size()<=0){
				response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
				return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
				
			}
			
			List<WeChatUser> lists=new ArrayList<WeChatUser>();
			responseMap = new HashMap<String, Object>();
			responseMap.put("OpenID", openid);
			List<WeChatUser> chatUsers = ReadWeChatUserService.getWeChatUserList(responseMap);
			if(chatUsers.size()>0){
				lists.add(chatUsers.get(0));
			}
			responseMap = new HashMap<String, Object>();
			responseMap.put("StudentID", uid);
			responseMap.put("Sina", SmBaseUtil.Random());
			boolean isexist=false;
			List<WeChatBind> binds = ReadWeChatBindService.getWeChatBindList(responseMap);
			for(WeChatBind bind :binds){
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", bind.getOpenID());
				chatUsers = ReadWeChatUserService.getWeChatUserList(responseMap);
				if(chatUsers.size()>0){
					chatUsers.get(0).setPKID(bind.getPKID());
					lists.add(chatUsers.get(0));
				}
				if(bind.getOpenID().equals(user.getOpenID())){
					isexist=true;
				}
			}
			if(user!=null && !user.getOpenID().equals(openid) && !isexist){
				if(auth==null || auth.isEmpty() || !auth.equals("1")){
					model.addAttribute("phone", lsstu.get(0).getPhone());
					return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "AuthenticationUser");
				}
				WeChatBind bind=new WeChatBind();
				bind.setID(SmBaseUtil.CreateNewID());
				bind.setOpenID(user.getOpenID());
				bind.setUnionID(user.getUnionID());
				bind.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				bind.setStudentID(Long.parseLong(uid));
				WeChatBindService.addWeChatBind(bind);
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", bind.getOpenID());
				chatUsers = ReadWeChatUserService.getWeChatUserList(responseMap);
				if(chatUsers.size()>0){
					chatUsers.get(0).setPKID(bind.getPKID());
					lists.add(chatUsers.get(0));
					SmBaseUtil.CreateSession("StudentName", null, req, session, response);
				}
				session.setAttribute("auth", null);
			}
			model.addAttribute("WeChatImageUrlCount", lists.size());
			model.addAttribute("WeChatImageUrl", lists);
			
			model.addAttribute("StudentsForm", lsstu.get(0));
			
			String timeStamp = System.currentTimeMillis() + "";
			timeStamp = timeStamp.substring(0, 10);// 微信只要精确到秒
			String nonceStr =SmBaseUtil.getRandomString(32);
			
			// 微信分享内容
			// model.addAttribute("appId","wxeb5043ba75e89f51");
			model.addAttribute("timestamp", timeStamp);
			model.addAttribute("nonceStr", nonceStr);
		}else{
			response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
	}
	
	
	@RequestMapping(value = "/phoneBindWeChatUserMobile", method = RequestMethod.GET)
	public ModelAndView phoneBindWeChatUserMobile(HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) throws IOException {

		Students user = (Students) req.getSession().getAttribute("StudentName");
		String unionid= req.getParameter("unionid");
		String uid= req.getParameter("uid");
		
		if(unionid==null || unionid.isEmpty()){
			unionid="";
		}
		if(uid==null || uid.isEmpty()){
			uid="0";
		}
		model.addAttribute("openid", unionid);
		model.addAttribute("uid", uid);
		model.addAttribute("auth", 1);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (user != null ) {//&&  user.getID()==Long.parseLong(uid) && user.getOpenID().equals(openid)
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", uid);
			responseMap.put("Sina",SmBaseUtil.Random());
			List<Students> lsstu = ReadStudentsService.getStudentsList(responseMap);
			if(lsstu.size()<=0){
				response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
				return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
				
			}
			
			
			responseMap = new HashMap<String, Object>();
			responseMap.put("StudentID", uid);
			responseMap.put("Sina", SmBaseUtil.Random());
			boolean isexist=false;
			List<WeChatBind> binds = ReadWeChatBindService.getWeChatBindList(responseMap);
			for(WeChatBind bind :binds){
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", bind.getUnionID());
				
				if(bind.getOpenID().equals(user.getOpenID())){
					isexist=true;
				}
			}
			if(user!=null  && !isexist){
				
				WeChatBind bind=new WeChatBind();
				bind.setID(SmBaseUtil.CreateNewID());
				bind.setOpenID(user.getOpenID());
				bind.setUnionID(user.getUnionID());
				bind.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				bind.setStudentID(Long.parseLong(uid));
				WeChatBindService.addWeChatBind(bind);
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", bind.getOpenID());
				
				session.setAttribute("auth", null);
			}
			
			model.addAttribute("StudentsForm", lsstu.get(0));
			
			String timeStamp = System.currentTimeMillis() + "";
			timeStamp = timeStamp.substring(0, 10);// 微信只要精确到秒
			String nonceStr =SmBaseUtil.getRandomString(32);
			
			// 微信分享内容
			// model.addAttribute("appId","wxeb5043ba75e89f51");
			model.addAttribute("timestamp", timeStamp);
			model.addAttribute("nonceStr", nonceStr);
		}else{
			response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneBindWeChatUser");
	}
	@RequestMapping(value="/phoneUnBindWeChatUser",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> UnBindWeChatUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String pkid = request.getParameter("pkid");
		if(pkid==null || pkid.isEmpty()){
			pkid="0";
		}
		int result=WeChatBindService.deleteWeChatBind(Long.parseLong(pkid));
		if(result>0){
			SmBaseUtil.CreateSession("StudentName", null, request, session, response);
		}
		checkParammap.put("Status",result);
		return checkParammap;
	 }
	@RequestMapping(value = "/phoneDealInfoFail", method = RequestMethod.GET)
	public @ResponseBody ModelAndView phoneDealInfoFail(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, null);

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneDealInfoFail");
	 }
	
}