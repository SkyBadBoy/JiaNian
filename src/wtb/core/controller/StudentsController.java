package wtb.core.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import read.core.data.ReadCompetitionApplyMapper;
import read.core.service.ReadLotteryStatuService;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;

import wtb.core.json.ExtractUIForUsers;
import wtb.core.model.ActivityPart;
import wtb.core.model.Advert;
import wtb.core.model.BaseInfo;
import wtb.core.model.ChangePassWord;
import wtb.core.model.CompetitionApply;
import wtb.core.model.HonorRecord;
import wtb.core.model.KeyWord;
import wtb.core.model.LotteryDemo;
import wtb.core.model.Notices;
import wtb.core.model.NoticesTemp;
import wtb.core.model.PayRecord;
import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.SignRecord;
import wtb.core.model.SinaBallot;
import wtb.core.model.Students;
import wtb.core.model.StudentsLog;
import wtb.core.model.Users;
import wtb.core.model.Video;
import wtb.core.model.WeChatBind;
import wtb.core.model.WeChatUser;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.core.model.LotteryRecord;
import wtb.core.model.LotteryStatu;
import wtb.core.model.Prizes;
import wtb.core.model.RedPacket;
import wtb.core.model.Rolls;
import wtb.core.service.StudentsService;
import wtb.core.service.WeMoneyService;
import wtb.smUtil.BaseUtil;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SessionUtils;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseGlobal.CheckStatus;
import wtb.smUtil.SmBaseGlobal.KeywordType;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.VerifyCode;
import wtb.smUtil.WeekUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Students")
public class StudentsController extends BaseController {

	
	private static String Register = SmBaseGlobal.XBLoginOrRegister;
	SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/phoneRegister", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("StudentsForm") Students Students, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, null);
		
		Students user = (Students) req.getSession().getAttribute("StudentName");
		if(user==null || user.getPhone()==null || user.getPhone().isEmpty() ){
			String uid=req.getParameter("uid");
			if(uid!=null && !uid.isEmpty()){
				session.setAttribute("RegisterRecommendUserID", uid);
			}
			model.addAttribute("type", 3);
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "AuthenticationUser");
		}else{
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("Phone", user.getPhone());
			List<Students> xbuser = ReadStudentsService.getStudentsList(responseMap);
			if (xbuser.size() > 0 && xbuser.get(0).getParentName()!=null &&  !xbuser.get(0).getParentName().isEmpty()) {
				String returnURL=(String)req.getSession().getAttribute("returnURL");
				if(returnURL!=null && !returnURL.isEmpty()){
					response.sendRedirect(returnURL);
				}else{
					if (xbuser.get(0).getAreaID()!=null&& !xbuser.get(0).getAreaID().isEmpty() && !xbuser.get(0).getAreaID().equals("0")
							&&user.getParentName() != null && !user.getParentName().isEmpty()) {
						response.sendRedirect(SmBaseUtil.getProjectBaseUrl(req)+"Students/phoneSinaIndex");
					}
				}
				
				
			}
			
		}
		
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phonelogin");
	}

	@RequestMapping(value = "/phoneRegister", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phonewenewsStudentRegister( HttpServletRequest req, HttpSession session, Model model,HttpServletResponse response) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String UnitAreaID=req.getParameter("UnitAreaID");
		String school=req.getParameter("school");
		String parentName=req.getParameter("parentName");
		String name=req.getParameter("name");
		String grade=req.getParameter("grade");
		String habit=req.getParameter("habit");
		String email=req.getParameter("email");
		String uid=req.getParameter("uid");
		if(uid==null || uid.isEmpty()){
			uid="0";
		}
		responseMap.put("ID", Long.parseLong(uid));
		List<Students> Students= ReadStudentsService.getStudentsList(responseMap);
		if(Students.size()<=0){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", false);
			responseMap.put("Message", "微信验证失败,请刷新页面后重试!");
			return responseMap;
		}
		if(parentName==null || parentName.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", false);
			responseMap.put("Message", "请填写家长姓名!");
			return responseMap;
		}
		if(name==null || name.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", false);
			responseMap.put("Message", "请填写孩子姓名!");
			return responseMap;
		}
		if(UnitAreaID==null || UnitAreaID.isEmpty() || UnitAreaID.equals("0")){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", false);
			responseMap.put("Message", "请选择您的学校!");
			return responseMap;
		}
		Students.get(0).setAreaID(UnitAreaID);
		Students.get(0).setParentName(parentName);
		Students.get(0).setName(name);
		Students.get(0).setGrade(grade);
		Students.get(0).setHabit(habit);
		Students.get(0).setEmail(email);
		Students.get(0).setSchool(school);
		Region region=new Region();
		region.setID(UnitAreaID);
		region.setName(school);
		Students.get(0).setArea(region);
		UpdateWeMoney(Students.get(0),StudentsService,req,response,session,ReadWeMoneyService,WeMoneyService);
		/* 
		 * 获取邀请人的Id 给邀请人增加微米
		 * */
		String registerUserID=(String)req.getSession().getAttribute("RegisterRecommendUserID");
		if(registerUserID!=null && !registerUserID.isEmpty() && !registerUserID.equals("null")){
			Map<String, Object> respmap = new HashMap<String, Object>();
			respmap.put("UserID", Long.parseLong(registerUserID));
			List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyList(respmap);
			if (weMoney.size() > 0) {
				weMoney.get(0).setWeMoney(weMoney.get(0).getWeMoney()+100);//邀请注册+100微米 上不封顶
				WeMoneyService.updateWeMoney(weMoney.get(0));
				WeMoneyRecord weMoneyRecordFromUser = new WeMoneyRecord();
				weMoneyRecordFromUser.setID(SmBaseUtil.CreateNewID());
				weMoneyRecordFromUser.setBelong(Long.parseLong(registerUserID));
				weMoneyRecordFromUser.setFromUserID(Students.get(0).getID());
				weMoneyRecordFromUser.setUserID(Long.parseLong(registerUserID));
				weMoneyRecordFromUser.setReson("邀请好友奖励");
				weMoneyRecordFromUser.setType(WeMoneyType.Consume.getid());
				weMoneyRecordFromUser.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weMoneyRecordFromUser.setWeMoney(100d);
				WeMoneyRecordService.addWeMoneyRecord(weMoneyRecordFromUser);
			}
			
			
		}
		String returnURL=(String)req.getSession().getAttribute("returnURL");
		if(returnURL!=null && !returnURL.isEmpty()){
			session.setAttribute("isNewStudent", "1");//表示新用户
			responseMap.put("returnURL", returnURL);
		}else{
			responseMap.put("zhuye", true);
		}
		responseMap.put("Status", true);
		return responseMap;
	}
public Students UpdateWeMoney(Students stu,StudentsService StudentsService,HttpServletRequest req,HttpServletResponse response,HttpSession session,
		read.core.service.ReadWeMoneyService ReadWeMoneyService,wtb.core.service.WeMoneyService WeMoneyService) {
	Map<String, Object> responseMap = new HashMap<String, Object>();
	if(stu.getID()==0){
		stu.setID(SmBaseUtil.CreateNewID());
		stu.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		if(stu.getAge()!=null && (stu.getAge().isEmpty() || stu.getAge().equals("0000-00-00 00:00:00"))){
			stu.setAge(null);
		}
		StudentsService.addStudents(stu);
	}else{
		if(stu.getOpenID()!=null && !stu.getOpenID().isEmpty()){
			StudentsService.updateStudentsForOpenID(stu);
		}else{
			StudentsService.updateStudents(stu);
		}
	}
	
	
	SmBaseUtil.CreateSession("StudentName", stu, req, session, response);
	responseMap = new HashMap<String, Object>();
	responseMap.put("UserID", stu.getID());
	responseMap.put("Sina", SmBaseUtil.Random());
	List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyByIDList(responseMap);
	if (weMoney.size() > 0) {
		if (weMoney.get(0).getUserID() != stu.getID()) {
			weMoney.get(0).setUserID(stu.getID());
			WeMoneyService.updateWeMoney(weMoney.get(0));
		}
	}else{
		WeMoney weMoneyTemp = new WeMoney();
		weMoneyTemp.setID(SmBaseUtil.CreateNewID());
		weMoneyTemp.setUserID(stu.getID());
		weMoneyTemp.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		weMoneyTemp.setWeMoney(SmBaseGlobal.DefaultWeMoney);// 新用户奖!"0微米
		WeMoneyService.addWeMoney(weMoneyTemp);
	}
	return stu;
		
}
	@RequestMapping(value = "/phoneSinaRule", method = RequestMethod.GET)
	public ModelAndView phoneSinaRule(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		String basePath = SmBaseUtil.getProjectBaseUrl(request);
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String imageurl = "";
		if(user==null){
			String studentID=request.getParameter("StudentID");
			Map<String, Object> map=new HashMap<>();
			map.put("ID", studentID);
			map.put("Rand", SmBaseUtil.Random());
			List<Students> students=ReadStudentsService.getStudentsList(map);
			if (students.size()>0) {
				user=students.get(0);
			}else{
				user=new Students();
			}
			
			if (studentID!=null) {
				model.addAttribute("isApp", true);
			}else{
				model.addAttribute("isApp", false);
			}
		}
		if (user.getImageUrl() != null) {
			imageurl = user.getImageUrl();
		} else {
			imageurl = basePath + SmBaseGlobal.UserDefaultImageUrl;
		}
		
		
		
		
		model.addAttribute("imageurl", imageurl);
		model.addAttribute("Student", user);

		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaRule");

	}

	@RequestMapping(value = "/phoneSinaReWard", method = RequestMethod.GET)
	public ModelAndView phoneSinaReWard(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException,
			ParseException {
		Students myself = (Students) request.getSession().getAttribute("StudentName");
		String sid = request.getParameter("uid");
		String pid = request.getParameter("pid");
		if(sid==null || (sid!=null && sid.isEmpty()) || !SmBaseUtil.isNumeric(sid)){
			sid="0";
		}
		String basePath =SmBaseUtil.getProjectBaseUrl(request);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ID", sid);
		List<Students> students = ReadStudentsService.getStudentsList(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", sid);
		responseMap.put("Sina", SmBaseUtil.Random());
		List<WeMoney> weMoneys = ReadWeMoneyService.getWeMoneyByIDList(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", sid);
		responseMap.put("Belong", pid);
		responseMap.put("Reward", SmBaseGlobal.WeMoneyType.Reward.getid());
		responseMap.put("limit", 10);
		responseMap.put("start", 0);
		List<WeMoneyRecord> weMoneyRecords = ReadWeMoneyRecordService.getWeMoneyRecordList(responseMap);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
		for (int i = 0; i < weMoneyRecords.size(); i++) {
			Date date = format.parse(weMoneyRecords.get(i).getCreateTime());
			weMoneyRecords.get(i).setFormtTime(SmBaseUtil.format(date));
		}
		long shanSum = ReadWeMoneyRecordService.getWeMoneyRecordSum(responseMap);
		String imageurl = "";
		if (students.size() <= 0) {
			students.add(new Students());
		}
		if (students.size() > 0 ) {
			if (students.get(0).getImageUrl() != null) {
				imageurl = students.get(0).getImageUrl().split(",")[0];
			} else {
				imageurl = basePath + SmBaseGlobal.UserDefaultImageUrl;
			}
		}
		NoticesController notices=new NoticesController();
		long myselfId=(myself==null)?-1:myself.getID();
		List<WeMoney> MtweMoneys = notices.getDefaultWeMoneyInfo(myselfId, WeMoneyService, ReadWeMoneyService);
		model.addAttribute("imageurl", imageurl);
		model.addAttribute("Student", students.get(0));
		model.addAttribute("WeMoney", weMoneys.get(0).getWeMoney());
		model.addAttribute("MyWeMoney", MtweMoneys.get(0).getWeMoney());

		model.addAttribute("shanSum", shanSum);
		model.addAttribute("NewID", pid);
		model.addAttribute("WeMoneyRecord", weMoneyRecords);
		model.addAttribute("WeMoneyRecordCount", weMoneyRecords.size());
		model.addAttribute("isMe", Long.parseLong(sid) == myselfId ? 1 : -1);

		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaReWard");

	}
	
	
	@RequestMapping(value = "/phoneSinaChanceReWard", method = RequestMethod.GET)
	public ModelAndView phoneSinaChanceReWard(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException,
			ParseException {
		 Map<String, Object> result = new HashMap<String, Object>();
		Students myself = SessionUtils.getStudentSession(request);
		result.put("UserID", myself.getID());
		result.put("Rand", SmBaseUtil.Random());
		
	       LotteryStatu lotterystatus =readLotteryStatuService.QueryMessage(result);
			if (lotterystatus == null) {
				lotterystatus = new LotteryStatu();
				lotterystatus.setLotteryId(SmBaseUtil.CreateNewID());
				lotterystatus.setLotteryUserid(myself.getID());
				lotterystatus.setLotteryResiduecount(0);
				lotterystatus.setLotteryTotalcount(0);
				lotteryStatuService.AddNewMessage(lotterystatus);
			}
			model.addAttribute("lotterystatus", lotterystatus);
			result = new HashMap<String, Object>();
			result.put("start", 0);
			result.put("size", 4);
			List<LotteryRecord> LRS =readLotteryRecordService.QueryRecordAll(result);
			List<ExtractUIForUsers> users = new ArrayList<ExtractUIForUsers>();
    		for (int i = 0; i < LRS.size(); i++) {
				ExtractUIForUsers user = new ExtractUIForUsers();
				if(LRS.get(i).getStudent()!=null){
					user.setID(LRS.get(i).getRecordUserid());
					user.setName(LRS.get(i).getStudent().getName());
					user.setPhone(LRS.get(i).getStudent().getPhone());
					user.setImageUrl(LRS.get(i).getStudent().getImageUrl());
					user.setPrizeCreatetime(SmBaseUtil.format(sdf.parse(LRS.get(i).getRecordCreatetime())));
					 Map<String, Object> map=new HashMap<>();
					map.put("ID", LRS.get(i).getRecordType().intValue());
					map.put("Rand", SmBaseUtil.Random());
					Prizes prize = readPrizesService.QueryPrize(map);
					user.setPrizeResiduecount(Integer.parseInt(LRS.get(i).getRecordRedundancy()));
					user.setPrizeRolltitle(prize.getPrizeRollremark());
					users.add(user);
				}
			}
    		model.addAttribute("LRS", users);
    		result = new HashMap<String, Object>();
			result.put("Status", 28);
			List<Pictures> pic =ReadPicturesService.getPicturesList(result);
			if(pic.size()>0){
				model.addAttribute("LRSUrl", pic.get(0).getUrl().split(",")[0]);
			}else{
				model.addAttribute("LRSUrl", "http://wenews.oss-cn-hangzhou.aliyuncs.com/Images/2017-09-27/a197a711-2fc6-49bc-a8dd-52a7f9c69775_800.PNG");
			}
			
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaChanceReWard");

	}

	@RequestMapping(value = "/phoneSinaMyNews", method = RequestMethod.GET)
	public ModelAndView phoneSinaMyNews(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		Students user = (Students) request.getSession().getAttribute("StudentName");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (user != null) {
			responseMap.put("UsersID", user.getID());
			responseMap.put("start", 0);
			responseMap.put("limit", 10);
			model.addAttribute("sid", user.getID());
			model.addAttribute("AreaId", user.getID());
			//responseMap.put("NotEdit", true);//不要编辑状态的文件;
			List<Notices> mList = ReadNoticesService.getReadNoticesList(responseMap);
			model.addAttribute("data", mList);
		} else {
			model.addAttribute("data", null);
			if(user==null){
				response.sendRedirect(SmBaseGlobal.XBLoginOrRegister);
				return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaMyNews");
			}
		}
		
		try{
			Map<String , Object> map=new HashMap<>();
			map.put("ID", user.getID());
			map.put("status", SmBaseGlobal.CheckStatus.Effective.getid());
			List<NoticesTemp> noticesTemps=NoticesTempService.getNoticesList(map);
			if (noticesTemps.size()>0) {
				model.addAttribute("IsCaoGao", true);
				if (noticesTemps.get(0).getImageUrl()!=null&&noticesTemps.get(0).getImageUrl()!="") {
					model.addAttribute("ImgTemp", noticesTemps.get(0).getImageUrl().split(",")[0]);
				}
				if (noticesTemps.get(0).getTitle()!=null&&noticesTemps.get(0).getTitle()!="") {
					model.addAttribute("TitleTemp", noticesTemps.get(0).getTitle());
				}
			}else{
				model.addAttribute("IsCaoGao", false);
			}
		}catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.core.controller.StudentsController.phoneSinaMyNews", e);
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaMyNews");

	}

	@RequestMapping(value = "/phoneSinaReWardList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneSinaReWardList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException,
			ParseException {
		Map<String, Object> result = new HashMap<String, Object>();
		String sid = request.getParameter("uid");
		String pid = request.getParameter("pid");
		String page = request.getParameter("_page_");
		if (page == null || page.isEmpty()) {
			page = "2";
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", sid);
		responseMap.put("Belong", pid);
		responseMap.put("Reward", 2);
		responseMap.putAll(SmBaseUtil.AddPhonePageParam(null,page));
		List<WeMoneyRecord> weMoneyRecords = ReadWeMoneyRecordService.getWeMoneyRecordList(responseMap);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
		for (int i = 0; i < weMoneyRecords.size(); i++) {
			Date date = format.parse(weMoneyRecords.get(i).getCreateTime());
			weMoneyRecords.get(i).setFormtTime(SmBaseUtil.format(date));
		}
		result.put("data", weMoneyRecords);
		result.put("Status", 1);
		result.put("_page_", Integer.parseInt(page));
		return result;

	}

	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value = "/phoneSinaToReWard", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneSinaToReWard(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> respmap = new HashMap<String, Object>();
		String sid = request.getParameter("uid");
		String nid = request.getParameter("nid");
		String fid = request.getParameter("fid");
		String Message = "打赏失败,请尝试重新打开该页!";
		String money = request.getParameter("money");
		if (sid == null || sid.isEmpty()) {
			result.put("Status", false);
			result.put("Message", Message);
			return result;
		}
		if (nid == null || nid.isEmpty()) {
			result.put("Status", false);
			result.put("Message", Message);
			return result;
		}
		if (fid == null || fid.isEmpty()) {
			result.put("Status", false);
			result.put("Message", Message);
			return result;
		}
		if (money == null || money.isEmpty()) {
			result.put("Status", false);
			result.put("Message", "微米不能为空");
			return result;
		}
		try {

			respmap = new HashMap<String, Object>();
			respmap.put("UserID", Long.parseLong(fid));
			List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyList(respmap);
			if (weMoney.size() > 0) {
				if (weMoney.get(0).getWeMoney() - Long.parseLong(money) < 0) {
					result.put("Status", false);
					result.put("Message", "微米数量不足");
					return result;
				}
				weMoney.get(0).setWeMoney(weMoney.get(0).getWeMoney() - Long.parseLong(money));
				WeMoneyService.updateWeMoney(weMoney.get(0));
			}
			WeMoneyRecord weMoneyRecordFromUser = new WeMoneyRecord();
			weMoneyRecordFromUser.setID(SmBaseUtil.CreateNewID());
			weMoneyRecordFromUser.setBelong(Long.parseLong(nid));
			weMoneyRecordFromUser.setFromUserID(Long.parseLong(sid));
			weMoneyRecordFromUser.setUserID(Long.parseLong(fid));
			weMoneyRecordFromUser.setReson("被赞赏");
			weMoneyRecordFromUser.setType(WeMoneyType.Consume.getid());
			weMoneyRecordFromUser.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			weMoneyRecordFromUser.setWeMoney(Double.parseDouble(money));
			WeMoneyRecordService.addWeMoneyRecord(weMoneyRecordFromUser);

			WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
			weMoneyRecord.setID(SmBaseUtil.CreateNewID());
			weMoneyRecord.setBelong(Long.parseLong(nid));
			weMoneyRecord.setFromUserID(Long.parseLong(fid));
			weMoneyRecord.setUserID(Long.parseLong(sid));
			weMoneyRecord.setReson("赞赏");
			weMoneyRecord.setType(WeMoneyType.Reward.getid());
			weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			weMoneyRecord.setWeMoney(Double.parseDouble(money));
			weMoneyRecord.setFromUser((Students) request.getSession().getAttribute("StudentName"));
			WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
			int lastDayUserSum=0;
			respmap = new HashMap<String, Object>();
			respmap.put("FromUserID", Long.parseLong(fid));
			respmap.put("Type", SmBaseGlobal.WeMoneyType.Reward.getid());
			respmap.put("Today", 1);
			respmap.put("Sina", SmBaseUtil.Random());
			
			int RewardCount=ReadWeMoneyRecordService.getWeMoneyRecordCount(respmap);
			if(RewardCount<=1){
				int lastDayUserCount=ReadWeMoneyRecordService.getWeMoneyRecordUserCount(Long.parseLong(fid));
				if(lastDayUserCount>=3){
					lastDayUserSum=ReadWeMoneyRecordService.getWeMoneyRecordUserSum(Long.parseLong(fid));
					if(lastDayUserSum>50){
						lastDayUserSum=50;
					}
					/*
					 * 每天打赏超过三次，且打赏的微米数量超过50，则在第二天首次打赏时会返还昨天打赏的微米数，最多返还50微米，
						若第一天打赏超过三次，打赏微米超过50，第二天未继续打赏，则第三天打赏的时候微米不再返还
					 */
					if(lastDayUserSum>0){
						WeMoneyRecord weMoneyRecordReward = new WeMoneyRecord();
						weMoneyRecordReward.setID(SmBaseUtil.CreateNewID());
						weMoneyRecordReward.setBelong(Long.parseLong(fid));
						weMoneyRecordReward.setFromUserID((long)0);
						weMoneyRecordReward.setUserID(Long.parseLong(fid));
						weMoneyRecordReward.setReson("系统返还");
						weMoneyRecordReward.setType(WeMoneyType.Added.getid());
						weMoneyRecordReward.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						weMoneyRecordReward.setWeMoney((double)lastDayUserSum);
						WeMoneyRecordService.addWeMoneyRecord(weMoneyRecordReward);
						respmap = new HashMap<String, Object>();
						respmap.put("UserID", Long.parseLong(fid));
						List<WeMoney> weMoneyTemp = ReadWeMoneyService.getWeMoneyList(respmap);
						if (weMoneyTemp.size() > 0) {
							weMoneyTemp.get(0).setWeMoney(weMoneyTemp.get(0).getWeMoney() + lastDayUserSum);
							WeMoneyService.updateWeMoney(weMoneyTemp.get(0));
						}
					}
					
				}
			}
			
			respmap = new HashMap<String, Object>();
			respmap.put("UserID", Long.parseLong(sid));
			weMoney = ReadWeMoneyService.getWeMoneyList(respmap);
			if (weMoney.size() > 0) {
				weMoney.get(0).setWeMoney(weMoney.get(0).getWeMoney() + Long.parseLong(money));
				WeMoneyService.updateWeMoney(weMoney.get(0));
			}
			result.put("Status", true);
			result.put("BackWeMoney", lastDayUserSum);
			result.put("Message", JSON.toJSON(weMoneyRecord));
			result.put("Formamt", SmBaseUtil.format(new Date()));
		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			result.put("Status", false);
			result.put("Message", "赞赏失败,请稍后再试");
		}

		return result;
	}

	@RequestMapping(value = "/phoneFindPassword", method = RequestMethod.GET)
	public ModelAndView ModifyMyInfo(@ModelAttribute("ChangePassWordForm") ChangePassWord users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneFindPassword");
	}

	@RequestMapping(value = "/phoneFindPassword", method = RequestMethod.POST)
	public ModelAndView changepasswordResult(@ModelAttribute("ChangePassWordForm") ChangePassWord users, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("AuthCode") String AuthCode, BindingResult result, Model model, HttpSession session) throws Exception {

		String Message = SmBaseUtil.CheckAuthCode(session, AuthCode);
		if (Message != null) {
			result.rejectValue("UserName", "misFormat", Message);
		} else {
			String NewPassword = users.getNewPassWord();
			String Confirm_NewPassword = users.getConfirm_NewPassWord();

			if (!NewPassword.equals(Confirm_NewPassword)) {
				result.rejectValue("Confirm_NewPassword", "misFormat", "两次密码不一致");
			}

//			Map<String, Object> checkParammap = new HashMap<String, Object>();
//			String MD5NewPassword = SmBaseUtil.MD5(NewPassword);
//			checkParammap.put("LoginName", users.getUserName());
//			checkParammap.put("NewPassWord", MD5NewPassword);
//			int proResult = StudentsService.ChangePassword(checkParammap);
//			if (proResult > 0) {
//				model.addAttribute("content", request.getContextPath() + "/Students/phonelogin");
//				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneDealInfoSuccess");
//
//			} else {
//				result.rejectValue("LoginName", "misFormat", "保存失败,请重试");
//			}
		}
		if (result.hasErrors()) {
			model.addAttribute("StudentsForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空!"
		}
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneFindPassword");
	}

	public String ParseUserToJsonString(Students user, WeMoneyService WeMoneyService) {
		if(user==null){
			user=new Students();
		}
		JsonObject json = new JsonObject();
		if (user.getName() == null) {
			json.addProperty("nickname", "");
		} else {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			if(user.getOpenID()!=null && !user.getOpenID().isEmpty()){
				responseMap.put("OpenID", user.getOpenID());
				List<WeChatUser> wechatuser = ReadWeChatUserService.getWeChatUserList(responseMap);
				if (wechatuser.size() > 0) {
					json.addProperty("nickname", wechatuser.get(0).getNickName());
				} else {
					json.addProperty("nickname", user.getName());
				}
			}else {
				json.addProperty("nickname", user.getName());
			}
		}
		if (user.getSex() == null) {
			json.addProperty("sex", "");
		} else {
			json.addProperty("sex", user.getSex());
		}
		json.addProperty("id", String.valueOf(user.getID()));
		json.addProperty("level", user.getLevel());
		if (user.getArea() != null) {
			json.addProperty("school", user.getArea().getName());
		} else {
			json.addProperty("school", user.getSchool());
		}
		if (user.getImageUrl() != null) {
			json.addProperty("headimgurl", user.getImageUrl());
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", user.getID());
		List<WeMoney> weList = ReadWeMoneyService.getWeMoneyList(responseMap);
		if (weList.size() <= 0) {
			WeMoney weMoney = new WeMoney();
			weMoney.setUserID(user.getID());
			weMoney.setWeMoney(SmBaseGlobal.DefaultWeMoney);
			weMoney.setStatus(CheckStatus.Effective.getid());
			weMoney.setID(SmBaseUtil.CreateNewID());
			weList.add(weMoney);
		}
		json.addProperty("integration", String.valueOf(weList.get(0).getWeMoney()));
		json.addProperty("phone", user.getPhone());
		if (user.getOpenID() == null) {
			json.addProperty("openid", "");
		} else {
			json.addProperty("openid", user.getOpenID());
		}
		if (user.getAge() == null) {
			json.addProperty("age", "");
		} else {
			json.addProperty("age", user.getAge());
		}
		return json.toString();
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/phonelogin", method = { RequestMethod.POST, RequestMethod.GET })
	/**/
	public ModelAndView phonewenewslogin(@ModelAttribute("StudentsForm") Students Students,HttpServletResponse response, HttpSession session, Model model, HttpServletRequest req) throws IOException {
		String islogin="";
		System.err.println("进入phonelogin");
		try{
		islogin = CheckLogin(req, session, response);
		}catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		try{
		if (islogin==null || islogin.isEmpty()) {
			String returnURL = req.getParameter("url");
			if (returnURL == null ) {
				if(req.getParameter("returnURL")!=null){
					returnURL = req.getParameter("returnURL");
				}else{
					returnURL="";
				}
			}
			returnURL = SmBaseUtil.getCurrentWebUrl(req) + "/Students/phonelogin?url=" + URLEncoder.encode(returnURL);
			response.sendRedirect(SmBaseUtil.getWeChatLoginUrl("snsapi_userinfo", returnURL,req));
		} else {
			Students user = (Students) req.getSession().getAttribute("StudentName");
			if (user != null) {
				if (islogin.contains("?")) {
					islogin = islogin + "&user_info=" + URLEncoder.encode(ParseUserToJsonString(user, WeMoneyService));
				} else {
					islogin = islogin + "?user_info=" + URLEncoder.encode(ParseUserToJsonString(user, WeMoneyService));
				}
			} else {
				String userInfo = req.getParameter("userInfo");
				if (userInfo == null || userInfo.isEmpty()) {
					userInfo = req.getParameter("user_info");
				}
				JSONObject jsonObj = null;

				if (userInfo != null) {

					userInfo = SmBaseUtil.URLDecoderString(userInfo);
					jsonObj = SmBaseUtil.PaseJsonToJsonObject(userInfo);
					if(jsonObj!=null){
						jsonObj.put("integration", 0);
						if (islogin.contains("?")) {
							islogin = islogin + "&user_info=" + SmBaseUtil.URLDecoderString(JSON.toJSONString(jsonObj));
						} else {
							islogin = islogin + "?user_info=" + SmBaseUtil.URLDecoderString(JSON.toJSONString(jsonObj));
						}
					}

				}
			}
			response.sendRedirect(islogin);
		}
		}catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		System.err.println("离开phonelogin");
		model.addAttribute("StudentsForm", new Students());
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
	}

	@RequestMapping(value = "/phoneConsumptionIntegration", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody
	String phoneConsumptionIntegration(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		try {
			String uid = request.getParameter("uid");
			String openid = request.getParameter("openid");
			if ((uid == null || uid.isEmpty()) && (openid == null || openid.isEmpty())) {
				responseMap.put("status", "false");
				responseMap.put("message", "uid或者openid不能为空");
				json.putAll(responseMap);
				return json.toString();
			}
			String num = request.getParameter("num");
			String reason = request.getParameter("reason");
			if (num == null || num.isEmpty()) {
				responseMap.put("status", "false");
				responseMap.put("message", "消费的微米不能为空");
				json.putAll(responseMap);
				return json.toString();
			}

			if (reason == null || reason.isEmpty()) {
				responseMap.put("status", "false");
				responseMap.put("message", "消费的原因不能为空");
				json.putAll(responseMap);
				return json.toString();
			}
			List<Students> stu=new ArrayList<Students>();
			responseMap = new HashMap<String, Object>();
			if (uid != null && !uid.isEmpty()) {
				responseMap.put("ID", uid);
				stu = ReadStudentsService.getStudentsList(responseMap);
			} else if (openid != null && !openid.isEmpty()) {
				responseMap.put("OpenID", openid);
				stu = ReadStudentsService.getStudentsList(responseMap);
			}
			
			responseMap = new HashMap<String, Object>();
			if (stu.size() > 0) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("UserID", stu.get(0).getID());
				List<WeMoney> weMoneys = ReadWeMoneyService.getWeMoneyList(responseMap);
				WeMoney weMoney = new WeMoney();
				if (weMoneys.size() > 0) {
					weMoney = weMoneys.get(0);
					weMoney.setWeMoney(weMoney.getWeMoney() - Long.parseLong(num));
				} else {

					weMoney.setUserID(stu.get(0).getID());
					weMoney.setWeMoney(SmBaseGlobal.DefaultWeMoney - Long.parseLong(num));
					weMoney.setStatus(1);
					weMoney.setID(SmBaseUtil.CreateNewID());
					WeMoneyService.addWeMoney(weMoney);
				}
				if (weMoney.getWeMoney() < 0) {
					responseMap.put("status", "false");
					responseMap.put("message", "微米不足");
					json.putAll(responseMap);
					return json.toString();
				}
				Long InID = SmBaseUtil.CreateNewID();
				WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
				weMoneyRecord.setID(InID);
				weMoneyRecord.setBelong(stu.get(0).getID());
				weMoneyRecord.setFromUserID((long) 0);
				weMoneyRecord.setReson(reason);
				weMoneyRecord.setUserID(stu.get(0).getID());
				weMoneyRecord.setType(WeMoneyType.Consume.getid());
				weMoneyRecord.setStatus(CheckStatus.Effective.getid());
				weMoneyRecord.setWeMoney(Double.parseDouble(num));
				WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
				int result = WeMoneyService.updateWeMoney(weMoney);

				responseMap = new HashMap<String, Object>();
				if (result > 0) {
					responseMap.put("status", "true");
					responseMap.put("Message", "处理成功!");
				} else {
					responseMap.put("status", "false");
					responseMap.put("Message", "处理失败!");
				}

			} else {
				responseMap.put("status", "false");
				responseMap.put("message", "该用户不存在");
			}

		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", "false");
			responseMap.put("Message", "处理失败,请输入正确的参数!");
		}
		json.putAll(responseMap);
		return json.toString();
	}

	@RequestMapping(value = "/phoneSignUp", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneSignUp(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		try {
			String uid = request.getParameter("uid");
			if (uid == null || uid.isEmpty()) {
				responseMap.put("status", "false");
				responseMap.put("message", "签到失败，请刷新页面后重新尝试");
				return responseMap;
			}
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", uid);
			List<Students> stu = ReadStudentsService.getStudentsList(responseMap);
			Calendar cal = Calendar.getInstance();
			responseMap = new HashMap<String, Object>();
			SignRecord signrecord = new SignRecord();
			signrecord.setCreateTime(new Date());
			signrecord.setDay(cal.get(Calendar.DATE));
			signrecord.setMonth(cal.get(Calendar.MONTH) + 1);
			signrecord.setYear(cal.get(Calendar.YEAR));
			signrecord.setUserID(uid);
			signrecord.setID(SmBaseUtil.CreateNewID());
			SignRecordService.addSignRecord(signrecord);
			int num = 0;
			int lastCount = 0;
			if (stu.size() > 0) {
				List<SignRecord> SignRecordList = getWeekDay(ReadSignRecordService, uid);
				int count = 0;
				int index = 0;
				boolean isBreakSign = false;
				for (SignRecord signRecord : SignRecordList) {
					if (index < 7) {
						if (signRecord.getWeek() > 0 && signRecord.getWeek() <= 7) {

							if (signRecord.getStatus() != -1) {
								count++;
								if (isBreakSign) {
									lastCount = count - 1;
								} else {
									if (count >= lastCount) {
										lastCount = count;
									}
								}
							} else {
								count = 0;
								isBreakSign = true;
							}
						}
					}
					index++;
				}
				lastCount++;
				if (lastCount == 1) {
					num = 1;
				} else if (lastCount == 2) {
					num = 2;
				} else if (lastCount == 3) {
					num = 3;
				} else if (lastCount == 4) {
					num = 4;
				} else if (lastCount == 5) {
					num = 5;
				} else if (lastCount == 6) {
					num = 5;
				} else if (lastCount >= 7) {
					num = 5;
				}
				stu.get(0).setIntegration(stu.get(0).getIntegration() + num);
				int result = StudentsService.updateLevelStudents(stu.get(0));
				signrecord.setMemo(String.valueOf(num));
				SignRecordService.updateSignRecord(signrecord);
				// 更新积分

				String title = "成功签到";
				NoticesController notic = new NoticesController();
				Notices notices=new Notices();
				notices.setID(stu.get(0).getID());
				notic.addLevel(stu.get(0).getID(), title, notices, num, IntegrationService,
						StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
				//addLevel(stu.get(0), title, Long.parseLong(uid), num, IntegrationService, StudentsService, WeMoneyService,ReadWeMoneyService);

				responseMap = new HashMap<String, Object>();
				if (result > 0) {
					SmBaseUtil.CreateSession("StudentName", stu.get(0), request, session, response);
					responseMap.put("status", "true");
					responseMap.put("Data", num);
				} else {
					responseMap.put("status", "false");
					responseMap.put("Message", "处理失败!");
				}

			} else {
				responseMap.put("status", "false");
				responseMap.put("message", "该用户不存在");
			}

		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", "false");
			responseMap.put("Message", "处理失败,请输入正确的参数!");
		}
		return responseMap;
	}

	/**
	 * @param SignRecordService
	 * @param uid
	 * @return
	 */
	public static List<SignRecord> getWeekDay(read.core.service.ReadSignRecordService SignRecordService, String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		Calendar currcal = Calendar.getInstance();
		SignRecord currsignRecord = new SignRecord();
		List<SignRecord> SignRecordList = new ArrayList<SignRecord>();
		// 外国人计算是礼拜天是第一!"我们这边如果当天是礼拜天 需要日期上减去一!"

		for (int j = 0; j < 7; j++) {
			map = new HashMap<String, Object>();
			map.put("UserID", uid);
			map.put("Day", WeekUtil.getNowWeek(j));
			map.put("Month", cal.get(Calendar.MONTH) + 1);
			map.put("Year", cal.get(Calendar.YEAR));
			List<SignRecord> tempList = SignRecordService.getSignRecordList(map);
			int week = j + 1;

			SignRecord signRecord = new SignRecord();
			if (tempList.size() > 0) {
				tempList.get(0).setWeek(week);
				signRecord = tempList.get(0);
				SignRecordList.add(signRecord);
			} else {
				signRecord.setStatus(-1);
				signRecord.setWeek(week);
				signRecord.setDay(Integer.valueOf(WeekUtil.getNowWeek(j)));
				signRecord.setMonth(cal.get(Calendar.MONTH) + 1);
				signRecord.setYear(cal.get(Calendar.YEAR));
				SignRecordList.add(signRecord);
			}
			if (currcal.get(Calendar.DATE) == signRecord.getDay() && (cal.get(Calendar.MONTH) + 1) == signRecord.getMonth()
					&& currcal.get(Calendar.YEAR) == signRecord.getYear()) {
				currsignRecord = signRecord;

			}
		}
		SignRecordList.add(currsignRecord);
		return SignRecordList;
	}

	@RequestMapping(value = "/phoneGetUserInfo", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody
	String phoneGetUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		String uid = request.getParameter("uid");
		String openid = request.getParameter("openid");
		if ((uid == null || uid.isEmpty()) && (openid == null || openid.isEmpty())) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", "false");
			responseMap.put("message", "uid或者openid不能为空");
			json.putAll(responseMap);
			return json.toString();
		} else {
			List<Students> stu =new ArrayList<Students>();
			responseMap = new HashMap<String, Object>();
			if (uid != null && !uid.isEmpty()) {
				responseMap.put("ID", uid);
				stu = ReadStudentsService.getStudentsList(responseMap);
			} else if (openid != null && !openid.isEmpty()) {
				responseMap.put("OpenID", openid);
				stu = ReadStudentsService.getStudentsList(responseMap);
			}
			
			int count = stu.size();
			String userjson = "";
			if (count <= 0) {
				if ((uid != null && (openid == null || openid.isEmpty())) || (openid != null && (uid == null || uid.isEmpty()))) {
					responseMap = new HashMap<String, Object>();
					responseMap.put("status", "false");
					responseMap.put("message", "该用户不存在");
					json.putAll(responseMap);
					return json.toString();
				}
				responseMap = new HashMap<String, Object>();
				responseMap.put("OpenID", openid);
				List<WeChatUser> wechatuser = ReadWeChatUserService.getWeChatUserList(responseMap);
				count = wechatuser.size();
				if (count > 0) {
					Students student = new Students();
					student.setName(wechatuser.get(0).getNickName());
					student.setSex(wechatuser.get(0).getSex());
					student.setID(wechatuser.get(0).getID());
					student.setSchool(wechatuser.get(0).getProvince() + wechatuser.get(0).getCity());
					student.setImageUrl(wechatuser.get(0).getHeadImgUrl());
					student.setIntegration(0);
					student.setPhone("");
					student.setOpenID(wechatuser.get(0).getOpenID());
					student.setAge("");
					student.setLevel("0");
					userjson = ParseUserToJsonString(student, WeMoneyService);
				}
			} else {
				userjson = ParseUserToJsonString(stu.get(0), WeMoneyService);
			}
			responseMap = new HashMap<String, Object>();
			if (count > 0) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("status", "true");
				responseMap.put("data", JSON.parse(userjson));
			} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("status", "false");
				responseMap.put("message", "该用户不存在");

			}
		}

		json.putAll(responseMap);
		return json.toString();
	}
	@RequestMapping(value = "/phoneSinaIndex", method = RequestMethod.GET)
	public ModelAndView phonewenewsindex(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {
		Students user = (Students) request.getSession().getAttribute("StudentName");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		System.err.println("进入phoneSinaIndex");
		//src_param:只是一个判断，用于看看这个链接是不是从app的这个业务出来的，userid是用于判断是这儿出来的绑定这个用户
		if(request.getParameter("src_param")!=null && request.getParameter("src_param").equals("appshare") && 
				request.getParameter("userid")!=null){
			String UserID=request.getParameter("userid");
			if(user!=null){
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", UserID);
				List<Students> stus = ReadStudentsService.getStudentsList(responseMap);
				if(stus.size()>0){
					stus.get(0).setOpenID(user.getOpenID());
					StudentsService.updateStudents(stus.get(0));
				}
			}
		}
		if (user == null) {
			response.sendRedirect(Register);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
		}
		try{
		if (user.getPhone() == null || user.getPhone().isEmpty()) {
			response.sendRedirect(Register);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
				
		}
		if (user.getParentName() == null || user.getParentName().isEmpty()) {
			response.sendRedirect(Register);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
				
		}
		if (user.getAreaID() == null || user.getAreaID().isEmpty()) {
			response.sendRedirect(Register);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
				
		}
		
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.UserCenterBanner.getid());
		List<Advert> Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("Adverts_Url", Adverts.get(0).getImageurl());
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ID", user.getID());
		List<Students> stus = ReadStudentsService.getStudentsList(responseMap);
		if (stus.size() > 0) {
			user = stus.get(0);
			SmBaseUtil.CreateSession("StudentName", user, request, session, response);
		}else{
			response.sendRedirect(Register);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
		}
		
			Map<String, Object> map = new HashMap<>();
			map.put("UserID", user.getID());
			List<WeMoney> weMoneys = ReadWeMoneyService.getWeMoneyByIDList(map);
			if (weMoneys.size() > 0) {
				model.addAttribute("weMoney", weMoneys.get(0).getWeMoney());
			} else {
				model.addAttribute("weMoney", 0);
			}
			
			//2017 2 22增加了发稿了字段，更新发稿量
			if (user.getNoticeCount()==0) {//如果用户的发稿量为0的时候，则可能是投稿量还没有更新上去，或者用户本来就没有投稿
				Map<String, Object> NoticeMap=new HashMap<>();
				NoticeMap.put("UsersID", user.getID());
				int count=ReadNoticesService.getNoticesCount(NoticeMap);
				if (count>0) {//大于0说明自己的有发表过文章
					user.setNoticeCount(count);
					StudentsService.updateStudents(user);
					SmBaseUtil.CreateSession("StudentName", user, request, session, response);
				}
			}
			
			
			model.addAttribute("Student", user);
			model.addAttribute("AreaId", user.getAreaID());
			model.addAttribute("SID", user.getID());
			model.addAttribute("LevelIcon", SmBaseUtil.getLevelIcon(request,user,ReadStudentStatService));
			/* 统计个人的勋章数量  */
			//新闻达人
			map = new HashMap<>();
			map.put("StudentID", user.getID());
			map.put("Type", SmBaseGlobal.HonerType.Excellent.getid());
			int honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("ExcellentCount", honorRecords);
			map.put("EndTime", 1);
			honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("ExcellentIsEnd", honorRecords);
			
			//传播达人
			map = new HashMap<>();
			map.put("StudentID", user.getID());
			map.put("Type", SmBaseGlobal.HonerType.Spread.getid());
			honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("SpreadCount", honorRecords);
			map.put("EndTime", 1);
			honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("SpreadIsEnd", honorRecords);
			
			
			//新闻达人
			map = new HashMap<>();
			map.put("StudentID", user.getID());
			map.put("Type", SmBaseGlobal.HonerType.Write.getid());
			honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("WriteCount", honorRecords);
			map.put("EndTime", 1);
			honorRecords=ReadHonorRecordService.getHonorRecordCount(map);
			model.addAttribute("WriteIsEnd", honorRecords);
			
		}catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		System.err.println("离开phoneSinaIndex");
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaIndex");
	}
	@RequestMapping(value = "/phoneGetSignInfo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneGetSignInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap=new HashMap<>();
		String uid=request.getParameter("uid");
		if (uid!=null) {
			List<SignRecord> SignRecordList = getWeekDay(ReadSignRecordService, String.valueOf(uid));
			if (SignRecordList.size() > 7) {
				responseMap.put("CurrSignRecord", SignRecordList.get(7));
			}
			responseMap.put("SignRecordList", SignRecordList);
			responseMap.put("status", true);
		} else {
			responseMap.put("status", false);
		}
		return responseMap;
	}

	private String CheckLogin(HttpServletRequest request, HttpSession session, HttpServletResponse resp) {
		String islogin = "";
		try{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		
		Students stu = (Students) request.getSession().getAttribute("StudentName");
		String basePath = SmBaseUtil.getCurrentWebUrl(request);
		String userInfo = request.getParameter("userInfo");
		if (userInfo == null || userInfo.isEmpty()) {
			userInfo = request.getParameter("user_info");
		}
		String uid = request.getParameter("uid");
		String returnURL = request.getParameter("url");
		int isOutUrl = 0;
		if (returnURL == null) {
			returnURL = request.getParameter("returnURL");
		} else {
			isOutUrl = 1;
		}
		if (returnURL == null) {

			if (stu != null || uid==null) {
				returnURL = "/Students/phoneSinaIndex";
			} else {
				returnURL = "/Students/phonelogin?uid=" + uid;
			}
		}
		if (!returnURL.contains("http://")) {
			returnURL = basePath + returnURL;
		}
		if (SmBaseGlobal.IOpen == true) {
			if (uid != null || userInfo != null) {
				JSONObject jsonObj = null;

				if (userInfo != null) {
					userInfo = SmBaseUtil.URLDecoderString(userInfo);
					jsonObj = SmBaseUtil.PaseJsonToJsonObject(userInfo);
				}
				List<Students> user = new ArrayList<Students>();
				if (uid != null) {
					responseMap.put("ID", uid);
					user = ReadStudentsService.getStudentsList(responseMap);
				} else {
					if(jsonObj.containsKey("openid") &&  jsonObj.getString("openid")!=null && !jsonObj.getString("openid").isEmpty()){
						responseMap.put("OpenID", jsonObj.getString("openid"));
						user = ReadStudentsService.getStudentsList(responseMap);
					}
				}
				
				if (user.size() <= 0) {
					if (isOutUrl == 1 && returnURL != null && !returnURL.isEmpty()) {
						return returnURL;
					}
					islogin = SmBaseGlobal.XBLoginOrRegister;
					return islogin;
				}
				List<Students> studs2 = new ArrayList<Students>();
				// 微信登录,进行微信绑定
				if (jsonObj == null && ((user.get(0).getOpenID() == null || user.get(0).getOpenID().isEmpty()) && (userInfo == null || userInfo.isEmpty()))) {
					if (stu == null) {
						return SmBaseUtil.getWeChatLoginUrl("snsapi_userinfo", returnURL,request);
					}
				}
					// 绑定微信!"
				//	user.set(0, BindWeChat(userInfo, String.valueOf(user.get(0).getID()), user.get(0)));
				if (userInfo != null && jsonObj != null) {
					if (jsonObj.containsKey("openid")) {
						String unionid="";
						if(jsonObj.containsKey("unionid")){
							unionid = jsonObj.getString("unionid");
						}
						studs2=SmBaseUtil.getStudentByOpenID(jsonObj.getString("openid"),unionid, ReadStudentsService, ReadWeChatBindService);
					}
				}
				
				if (studs2.size() <= 0) {
					try{
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", stu.getID());
						responseMap.put("Sina",SmBaseUtil.Random());
						studs2 = ReadStudentsService.getStudentsList(responseMap);
						if(studs2.size()<=0){
							StudentsService.addStudents(stu);
							responseMap = new HashMap<String, Object>();
							responseMap.put("UserID", stu.getID());
							List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyByIDList(responseMap);
							if (weMoney.size() > 0) {
								if (weMoney.get(0).getUserID() != stu.getID()) {
									weMoney.get(0).setUserID(stu.getID());
									WeMoneyService.updateWeMoney(weMoney.get(0));
								}
							}else{
								WeMoney weMoneyTemp = new WeMoney();
								weMoneyTemp.setID(SmBaseUtil.CreateNewID());
								weMoneyTemp.setUserID(stu.getID());
								weMoneyTemp.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
								weMoneyTemp.setWeMoney(SmBaseGlobal.DefaultWeMoney);// 新用户奖!"0微米
								WeMoneyService.addWeMoney(weMoneyTemp);
							}
						}
					}catch (Exception e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
				} else {
					try {
						
						try{
								if(stu==null && studs2.size()>0){
									stu=studs2.get(0);
								}
								StudentsService.updateStudentsForOpenID(stu);
								responseMap = new HashMap<String, Object>();
								responseMap.put("UserID", stu.getID());
								List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyByIDList(responseMap);
								if (weMoney.size() > 0) {
									if (weMoney.get(0).getUserID() != stu.getID()) {
										weMoney.get(0).setUserID(stu.getID());
										WeMoneyService.updateWeMoney(weMoney.get(0));
									}
								}
							
						}catch (Exception e) {
							e.printStackTrace();
							//islogin = request.getContextPath() + "/include/mobileuserlimit.html";
							//return islogin;
							//SmBaseUtil.CreateSession("StudentName", null, request, session, resp);
						}
						

					} catch (Exception e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
				}
				SmBaseUtil.CreateSession("StudentName", stu, request, session, resp);
				if (returnURL == null || returnURL.isEmpty()) {
					islogin = request.getContextPath() + "/Students/phoneSinaIndex";
				} else {
					islogin = returnURL;
				}
			}else{
				islogin=returnURL;
			}
		} else {

			if (uid != null) {
				responseMap.put("ID", uid);

				List<Students> user = ReadStudentsService.getStudentsList(responseMap);
				SmBaseUtil.CreateSession("StudentName", user.get(0), request, session, resp);
				islogin = request.getContextPath() + "/Students/phoneSinaIndex";
			}else{
				islogin=returnURL;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		return islogin;
	}

	@RequestMapping(value = "/phoneSinaUserInfo", method = RequestMethod.GET)
	public ModelAndView phonewenewsuserinfo(@ModelAttribute("StudentsForm") Students student, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = req.getParameter("pid");
		responseMap.put("ID", pid);
		List<Students> user = ReadStudentsService.getStudentsList(responseMap);
		if (user.size() > 0) {
			model.addAttribute("Student", user.get(0));
			model.addAttribute("Type", 3);
			model.addAttribute("AreaID", user.get(0).getAreaID());
			responseMap = new HashMap<String, Object>();
			responseMap.put("UsersID", user.get(0).getID());// 进行中的活动
			responseMap.put("limit", 10);
			responseMap.put("start", 0);
			List<Notices> lswe = ReadNoticesService.getReadNoticesList(responseMap);
			model.addAttribute("data", lswe);
			String imageurl = "";
			String basePath = SmBaseUtil.getProjectBaseUrl(req);
			if (user.get(0).getImageUrl() != null) {
				imageurl = user.get(0).getImageUrl().split(",")[0];
			} else {
				imageurl = basePath + SmBaseGlobal.UserDefaultImageUrl;// "img/tx_default.jpg";
			}
			
			model.addAttribute("LevelIcon", SmBaseUtil.getLevelIcon(req,user.get(0),ReadStudentStatService));
			model.addAttribute("PositionIcon", SmBaseUtil.getPositionIcon(req,user.get(0)));
			model.addAttribute("HonourIcon", SmBaseUtil.getHonourIcon(req,user.get(0),ReadHonorRecordService));
			model.addAttribute("title", user.get(0).getName()+"的个人空间");
			model.addAttribute("userImageurl", imageurl);
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaUserInfo");
	}

	@RequestMapping(value = "/phoneSinaUserInfoEdit", method = RequestMethod.GET)
	public ModelAndView phoneSinaUserInfoEdit(@ModelAttribute("StudentsForm") Students student, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) throws IOException {

		model = RegionController.getRegionParams(req, ReadRegionService, model, session, null);
		String AreaId = req.getParameter("AreaId");
		Students user = (Students) req.getSession().getAttribute("StudentName");

		if (user != null) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", user.getID());
			responseMap.put("Sina",SmBaseUtil.Random());
			List<Students> lsstu = ReadStudentsService.getStudentsList(responseMap);
			if(lsstu.size()<=0){
				response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
				return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaUserInfoEdit");
				
			}
			if(lsstu.size()>0){
				user = lsstu.get(0);
				if (user.getAge() != null && user.getAge().length() > 0 && !user.getAge().equals("0000-00-00 00:00:00")) {
					user.setAge(user.getAge().substring(0, 10));
				}
			}
			List<String> lists=new ArrayList<String>();
			responseMap = new HashMap<String, Object>();
			List<WeChatUser> chatUsers = null;
			if(user.getOpenID()!=null && !user.getOpenID().isEmpty()){
				responseMap.put("OpenID", user.getOpenID());
				chatUsers = ReadWeChatUserService.getWeChatUserList(responseMap);
				if(chatUsers.size()>0){
					lists.add(chatUsers.get(0).getHeadImgUrl());
				}
			}
			
			responseMap = new HashMap<String, Object>();
			responseMap.put("StudentID", user.getID());
			List<WeChatBind> binds = ReadWeChatBindService.getWeChatBindList(responseMap);
			for(WeChatBind bind :binds){
				if(bind.getOpenID()!=null && !bind.getOpenID().isEmpty()){
					responseMap = new HashMap<String, Object>();
					responseMap.put("OpenID", bind.getOpenID());
					chatUsers = ReadWeChatUserService.getWeChatUserList(responseMap);
					if(chatUsers.size()>0){
						lists.add(chatUsers.get(0).getHeadImgUrl());
					}
				}
				
			}
			model.addAttribute("WeChatImageUrlCount", lists.size());
			model.addAttribute("WeChatImageUrl", lists);
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", user.getAreaID());
			List<Region> region = ReadRegionService.getRegionByIDList(user.getAreaID());
			if (region.size() > 0) {
				user.setSchool(region.get(0).getName());
			}

			model.addAttribute("StudentsForm", user);
		}
		model.addAttribute("Areaid", AreaId);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaUserInfoEdit");
	}

	@RequestMapping(value = "/phoneSinaUserInfoEdit", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phoneuserinfoedit(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String ImageID = req.getParameter("ImageID");
		String StudentName = req.getParameter("StudentName");
		String StudentAge = req.getParameter("StudentAge");
		String StudentSex = req.getParameter("StudentSex");
		String StudentParentName = req.getParameter("StudentParentName");
		String StudentHabit = req.getParameter("StudentHabit");
		String StudentEmail = req.getParameter("StudentEmail");
		String StudentGrade = req.getParameter("StudentGrade");
		Students student = (Students) req.getSession().getAttribute("StudentName");
		boolean isChangeName=false;
		if (StudentAge != null && !StudentAge.isEmpty() && !StudentAge.equals("null") ) {
			if(StudentAge.equals("0000-00-00 00:00:00")){
				StudentAge=null;
			}
			student.setAge(StudentAge);
		}
		if (StudentName != null && !StudentName.isEmpty() && !StudentName.equals("null")) {
			//如果名字相同说明修改过名字，后面执行新闻的作者批量修改
			if(!student.getName().equals(StudentName)){
				isChangeName=true;
			}
			student.setName(StudentName);
		}
		if (StudentSex != null && !StudentSex.isEmpty() && !StudentSex.equals("null")) {
			student.setSex(StudentSex);
		}
		if (StudentParentName != null && !StudentParentName.isEmpty() && !StudentParentName.equals("null")) {
			student.setParentName(StudentParentName);
		}
		if (StudentHabit != null && !StudentHabit.isEmpty() && !StudentHabit.equals("null")) {
			student.setHabit(StudentHabit);
		}
		if (StudentEmail != null && !StudentEmail.isEmpty() && !StudentEmail.equals("null")) {
			student.setEmail(StudentEmail);
		}
		if (StudentGrade != null && !StudentGrade.isEmpty() && !StudentGrade.equals("null")) {
			student.setGrade(StudentGrade);
		}
		if (ImageID != null && !ImageID.isEmpty() && ImageID != "0" && !ImageID.equals("null")) {
			student.setImageID(Long.parseLong(ImageID));
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", ImageID);
			List<Pictures> lspic = ReadPicturesService.getPictureEffList(responseMap);
			if (lspic.size() > 0) {
				student.setImage(lspic.get(0));
				student.setImageUrl(lspic.get(0).getUrl().split(",")[0]);
			}
		}

		/*responseMap = new HashMap<String, Object>();
		responseMap.put("UID", student.getID());
		List<XBUser> xbuser = XBUserService.getXBUserList(responseMap);
		if (xbuser.size() > 0) {
			XBUserService.updateXBUser(SmBaseUtil.StudentParseXBUser(student, xbuser.get(0), XBSChoolService,ReadRegionService));
		}*/
		int proResult = 0;
		try{
			proResult = StudentsService.updateStudents(student);
		}catch (Exception e) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "false");
			responseMap.put("Message", "保存失败,请检测是否包含表情字符目前暂不支持表情或者特殊字符!");
			return responseMap;
		}
		if (proResult > 0) {
			if(isChangeName){
				responseMap = new HashMap<String, Object>();
				responseMap.put("PublishUser", student.getID());
				responseMap.put("Author", student.getName());
				NoticesService.UpdateAuthorForStudents(responseMap);
			}
			SmBaseUtil.CreateSession("StudentName", student, req, session, response);
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Message", "保存成功");
			return responseMap;
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", "false");
		responseMap.put("Message", "保存失败,请稍后再试!");
		return responseMap;
	}

	
	@RequestMapping(value = "/phoneInputPayInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phoneInputPayInfo(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String pkid = req.getParameter("pkid");
		String buid = req.getParameter("buid");
		String IntParamA = req.getParameter("IntParamA");
		String StringParamF = req.getParameter("StringParamF");
		String StringParamC = req.getParameter("StringParamC");
		String StringParamD = req.getParameter("StringParamD");
		String StringParamE = req.getParameter("StringParamE");
		
		
		
		Students student = (Students) req.getSession().getAttribute("StudentName");
		responseMap = new HashMap<String, Object>();
		responseMap.put("StudentID", student.getID());
		responseMap.put("Type", pkid);
		responseMap.put("Sina", SmBaseUtil.Random());
		responseMap.put("Status", SmBaseGlobal.CheckStatus.Apply.getid());
		List<CompetitionApply> resultCount=ReadCompetitionApplyService.getCompetitionList(responseMap);
		CompetitionApply apply=new CompetitionApply();
		apply.setID(SmBaseUtil.CreateNewID());
		if(resultCount.size()>0){
			apply=resultCount.get(0);
		}
		apply.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Apply.getid()));
		apply.setType(Long.parseLong(pkid));
		if(address!=null && !address.isEmpty()){
			apply.setBigStringParamA(address);
		}
		
		if(buid!=null && !buid.equals("0") && !buid.equals(student.getPKID())){
			apply.setStringParamA(buid);
		}else{
			apply.setStringParamA("0");
		}
		
		apply.setStudentID(String.valueOf(student.getID()));
		apply.setName(name);
		apply.setPhone(phone);
		if(IntParamA!=null && !IntParamA.isEmpty()){
			apply.setIntParamA(Integer.parseInt(IntParamA));
		}
		
		if(StringParamC!=null && !StringParamC.isEmpty()){
			apply.setStringParamC(StringParamC);
		}
		if(StringParamD!=null && !StringParamD.isEmpty()){
			apply.setStringParamD(StringParamD);
		}
		if(StringParamE!=null && !StringParamE.isEmpty()){
			apply.setStringParamE(StringParamE);
		}
		
		
		if(StringParamF!=null && !StringParamF.isEmpty()){//组团号
			responseMap = new HashMap<String, Object>();
			responseMap.put("Type", pkid);
			responseMap.put("Sina", SmBaseUtil.Random());
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			responseMap.put("StringParamF", StringParamF);
			int applyCount=ReadCompetitionApplyService.getCompetitionCount(responseMap);
			if(applyCount<=0){
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status", "false");
				responseMap.put("Message", "当前组团号不存在!");
				return responseMap;
			}else if(applyCount>=10){
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status", "false");
				responseMap.put("Message", "当前团号已经组满,无法加入!");
				return responseMap;
			}
			apply.setStringParamF(StringParamF);
		}
		int proResult = 0;
		if(resultCount.size()>0){
			proResult = CompetitionApplyService.updateCompetition(apply);
			proResult=1;
		}else{
			proResult = CompetitionApplyService.addCompetition(apply);
			
		}
		
		if (proResult > 0) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("CID", String.valueOf(apply.getID()));
			responseMap.put("Message", "保存成功");
			return responseMap;
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", "false");
		responseMap.put("Message", "保存失败,请稍后再试!");
		return responseMap;
	}
	@RequestMapping(value = "/phoneuserinfoedit_temp", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phoneuserinfoedit_temp(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String StudentName = req.getParameter("StudentName");
		String StudentPhone = req.getParameter("StudentPhone");
		Students student = (Students) req.getSession().getAttribute("StudentName");
		boolean isChangeName=false;
		
		if (StudentName != null && !StudentName.isEmpty() && !StudentName.equals("null")) {
			student.setName(StudentName);
		}
		if (StudentPhone != null && !StudentPhone.isEmpty() && !StudentPhone.equals("null")) {
			student.setPhone(StudentPhone);
		}
		int proResult = StudentsService.updateStudents(student);
		if (proResult > 0) {
			SmBaseUtil.CreateSession("StudentName", student, req, session, response);
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Message", "保存成功");
			return responseMap;
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", "false");
		responseMap.put("Message", "保存失败,请稍后再试!");
		return responseMap;
	}
	
	
	@RequestMapping(value = "/phoneCheckIsFull", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phoneCheckIsFull(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String StringParamC = req.getParameter("StringParamC");
		String StringParamD = req.getParameter("StringParamD");
		String StringParamE = req.getParameter("StringParamE");
		responseMap.put("StringParamC", StringParamC);
		responseMap.put("StringParamD", StringParamD);
		responseMap.put("StringParamE", StringParamE);
		int proResult = ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (proResult > 0) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Count", proResult);
			return responseMap;
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", "false");
		responseMap.put("Message", "保存失败,请稍后再试!");
		return responseMap;
	}
	
	@RequestMapping(value = "/StudentsList", method = RequestMethod.GET)
	public ModelAndView StudentsList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {

		String company = request.getParameter("AreaID");
		model.addAttribute("AreaID", company);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "StudentsList");
	}

	/**
	 * 改变学生的当前状!"表示是否可用,禁用则无法登!"
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> ChangeStudentsState(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String sid = request.getParameter("sid");
		String state = request.getParameter("Status");
		int result = 0;

		if (sid != null && !sid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(sid));
			responseMap.put("ModifyTime", new Date());
			if (state.equals("1")) {
				result = PicturesService.enabledPictures(responseMap);
			}
			if (state.equals("0")) {
				result = PicturesService.deletePictures(responseMap);
			}
		}
		String ErrorMessage = "";
		if (result != 1) {
			ErrorMessage = "处理发生错误,请稍后重试!";
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", result);
		responseMap.put("ErrorMessage", ErrorMessage);
		return responseMap;
	}

	@RequestMapping(value = "/isExistUser", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> isExistUser(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String test = request.getParameter("testState");
		if (test != null && !test.isEmpty()) {
			if (test.equals("1") || test.equals("0") || test.equals("-1")) {
				responseMap.put("Status", test);
			} else {
				responseMap.put("ErrorMessage", "输入的参数值不正确");
			}
		} else {
			responseMap.put("Status", -1);
		}
		return responseMap;
	}

	@RequestMapping(value = "/getStudentsList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getStudentsList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		String state = request.getParameter("Status");
		String AreaID = request.getParameter("AreaID");
		String check = request.getParameter("check");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		if (AreaID != null && !AreaID.isEmpty() && Long.parseLong(AreaID) > 0) {
			checkParammap.put("AreaID", AreaID);
		}
		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			responseMap.put("BelongAreaID", user.getID());
		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
			responseMap.put("AreaID", user.getAreaID());
		}
		if (state != null && !state.isEmpty() && Long.parseLong(state) > 0) {
			checkParammap.put("Status", state);
		}
		if (check != null && !check.isEmpty()) {
			check = SmBaseUtil.URLDecoderString(check);
			checkParammap.put("Check", check);

		}
		System.out.println("Check" + check);
		List<Students> lswe = ReadStudentsService.getStudentsList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {

			String produrl = SmBaseUtil.getProjectBaseUrl(request);
			if (lswe.get(i).getImageUrl() == null || lswe.get(i).getImageUrl().isEmpty()) {
				produrl = produrl + SmBaseGlobal.UserDefaultImageUrl;
			} else {
				produrl = lswe.get(i).getImageUrl();
			}
			String url = SmBaseUtil.getClickImageHtml(produrl);
			lswe.get(i).setImageUrl(url);
			if (Integer.parseInt(lswe.get(i).getSex()) == 1) {
				lswe.get(i).setSex("男");
			} else {
				lswe.get(i).setSex("女");
			}
		}

		int WeChatCount = ReadStudentsService.getStudentsCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", WeChatCount);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/SaveScanInfo", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> SaveScanInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String Data = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			Data += line;
		}
		if (Data != null && !Data.isEmpty()) {
			// Data=new String(Data.getBytes("ISO-8859-1"),"UTF-8");
			JSONObject jsonObj = SmBaseUtil.PaseJsonToJsonObject(Data);
			String title="点击链接给他投一票吧!";
			if(jsonObj.containsKey("title")){
				title=jsonObj.getString("title");
			}
			checkParammap.put("EssayID", jsonObj.getString("id"));
			List<SinaBallot> lsina = SinaBallotService.getSinaBallotList(checkParammap);
			SinaBallot ballot = new SinaBallot();
			if (lsina.size() > 0) {
				ballot = lsina.get(0);
				ballot.setTitle(title);
				SinaBallotService.updateSinaBallot(ballot);

			} else {
				ballot.setCreateTime(new Date());
				ballot.setEssayID(Long.parseLong(jsonObj.getString("id")));
				ballot.setID(new IdWorker(1, 0).nextId());
				ballot.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				ballot.setTitle(title);
				SinaBallotService.addSinaBallot(ballot);
			}
			checkParammap.put("Status", 1);
		} else {
			checkParammap.put("ErrorMessage", "Post请求输入不能为空");
		}
		return checkParammap;
	}

	@RequestMapping(value = "/getScanInfo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getScanInfo(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String Data = request.getParameter("aid");
		String type = request.getParameter("type");

		if (Data != null && !Data.isEmpty()) {
			
			checkParammap.put("ID", Data);
			String title="点击链接给他投一票吧!";
			if(type!=null && type.equals("1")){
				List<Video> lsina = ReadVideoService.getVideoList(checkParammap);
				if(lsina.size()>0){
					title = lsina.get(0).getTitle();
				}
			}else{
				List<Notices> lsina = ReadNoticesService.getReadNoticesList(checkParammap);
				if(lsina.size()>0){
					title = lsina.get(0).getTitle();
				}
			}
			checkParammap.put("title", title);
			
		} else {
			checkParammap.put("title", "未找到数据");
		}

		return checkParammap;
	}

	/**
	 * 跨域预请!"返回许可的表头信!"
	 * 
	 * @param resp
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/AddWeChatUser", method = RequestMethod.OPTIONS)
	public @ResponseBody
	String AddWeChatUser(HttpServletResponse resp, HttpServletRequest request, HttpSession session, Model model) throws IOException {
		String backUrl = request.getParameter("backUrl");
		if (backUrl.contains("www.wutuo.help")) {
			resp.addHeader("Access-Control-Allow-Origin", "http://www.wutuo.help");
		} else if (backUrl.contains("wutuo.help")) {
			resp.addHeader("Access-Control-Allow-Origin", "http://wutuo.help");
		} else if (backUrl.contains("wenews.top")) {
			resp.addHeader("Access-Control-Allow-Origin", "http://wenews.top");
		} else if (backUrl.contains("www.whohelp.cc")) {
			resp.addHeader("Access-Control-Allow-Origin", "http://www.whohelp.cc");
		}
		resp.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		resp.setHeader("Access-Control-Allow-Headers", "POWERED-BY-MENGXIANHUI,X_FILENAME,Content-Type");
		resp.setHeader("Access-Control-Max-Age", "30000");

		return "";
	}

	@RequestMapping(value = "/AddWeChatUser", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> AddWeChatUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		Map<String, Object> mapResult = new HashMap<>();
		String Data = request.getParameter("userinfo");
		String type = request.getParameter("type");
		response.addHeader("Access-Control-Allow-Origin", "http://wenews.top");

		response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
		response.setHeader("Access-Control-Allow-Headers", "POWERED-BY-MENGXIANHUI,X_FILENAME,Content-Type");
		response.setHeader("Access-Control-Max-Age", "30000");
		JSONObject jsonObj = null;

		int loginCount = 0;
		if (Data != null) {
			try {
				jsonObj = SmBaseUtil.PaseJsonToJsonObject(Data);
				if (jsonObj!=null && !jsonObj.containsKey("subscribe")) {
					String userinfo = SmBaseUtil.getWeChat_token(jsonObj.getString("openid"),request);
					if (userinfo != null && !userinfo.isEmpty() && !userinfo.contains("errcode")) {
						JSONObject jsuserinfo = SmBaseUtil.PaseJsonToJsonObject(userinfo);
						if (jsuserinfo!=null && jsuserinfo.containsKey("subscribe") && jsuserinfo.getString("subscribe").equals("1")) {
							jsonObj = jsuserinfo;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}
			int lsina=0;
			int lsmyina=0;
			WeChatUser weChatUser = new WeChatUser();
			
			if (jsonObj!=null && jsonObj.containsKey("nickname")) {
				String nikename = jsonObj.getString("nickname");
				int isActivity = 0;
				Map<String, Object> map = new HashMap<>();
				map.put(KeyWord.attributeKeyWordTypeID, KeywordType.BlackChar.getCode());
				List<KeyWord> keyWords = ReadKeyWordService.queryKeyWord(map);
				for (KeyWord item : keyWords) {
					if (nikename.contains(item.getKeyWord()) || nikename.equals(item.getKeyWord()) ) {
						isActivity = 1;
						mapResult.put("Message", "您登录过于频繁,请稍后再试");
						break;
					}
				}
				if(isActivity==0){
					weChatUser.setOpenID(jsonObj.getString("openid"));
					weChatUser.setNickName(jsonObj.getString("nickname"));
					weChatUser.setSex(jsonObj.getString("sex"));
					weChatUser.setLanguage(jsonObj.getString("language"));
					weChatUser.setCity(jsonObj.getString("city"));
					weChatUser.setProvince(jsonObj.getString("province"));
					weChatUser.setCountry(jsonObj.getString("country"));
					weChatUser.setHeadImgUrl(jsonObj.getString("headimgurl"));
					weChatUser.setIpAddress(SmBaseUtil.getIpAddr(request));
					if (type != null && !type.isEmpty()) {
						weChatUser.setWeChat(type);
					} else {
						weChatUser.setWeChat("xiaobo");
					}
	
					if (jsonObj.containsKey("privilege")) {
						weChatUser.setPrivilege(jsonObj.getString("privilege"));
					}
					if (jsonObj.containsKey("subscribe")) {
						weChatUser.setFlag(Integer.parseInt(jsonObj.getString("subscribe")));
					}
					try{
						/*checkParammap = new HashMap<String, Object>();
						checkParammap.put("OpenID", weChatUser.getOpenID());
						lsina = XbWeChatUserService.getWeChatUserEffCount(checkParammap);
						if (lsina <= 0) {
							weChatUser.setID(SmBaseUtil.CreateNewID());
							XbWeChatUserService.addWeChatUser(weChatUser);
						} else {
							XbWeChatUserService.updateWeChatUser(weChatUser);
						}*/
						checkParammap = new HashMap<String, Object>();
						checkParammap.put("OpenID", weChatUser.getOpenID());
						lsmyina = ReadWeChatUserService.getWeChatUserEffCount(checkParammap);
						if (lsmyina <= 0) {
							weChatUser.setID(SmBaseUtil.CreateNewID());
							weChatUser.setCreateTime(new Date());
							WeChatUserService.addWeChatUser(weChatUser);
						} else {
							WeChatUserService.updateWeChatUser(weChatUser);
						}
					}catch (Exception e) {
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
				}
				Map<String, Object> mapTest = new HashMap<>();
				
				mapTest.put("NickName", nikename);
				// 登录间隔
				mapTest.put("isLoginIntervals", 1);
				loginCount = ReadWeChatUserService.getWeChatUserEffCount(mapTest);
				if (loginCount > 10) {// 杜绝刷票
					mapResult.put("Message", "您登录过于频繁,请稍后再试");
				}
				mapResult.put("Status", loginCount);
			}
		} else {
			mapResult.put("Message", "Post请求输入不能为空");
		}
		return mapResult;
	}

	@RequestMapping(value = "/unsubscribe", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> unsubscribe(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String Data = request.getParameter("openid");
		WeChatUser wechat = new WeChatUser();
		
		if(Data!=null && !Data.isEmpty()){
			//checkParammap.put("OpenID", Data);
			//List<WeChatUser>ls= ReadWeChatUserService.getWeChatUserList(checkParammap);
			//if(ls!=null && ls.size()>0){
			//	ls.get(0).setFlag(0);
			//	wechat.setOpenID(Data);
				//wechat.setFlag(0);
				//WeChatUserService.updateWeChatUser(wechat);
			//}
		}
		//XbWeChatUserService.UnSubscribeXBUser(wechat);
		return checkParammap;
	}

	/**
	 * 绑定微信!"
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public Students BindWeChat(String userInfo, String uid, Students xbUser) {

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if (userInfo != null && uid != null && !uid.isEmpty()) {
			userInfo = SmBaseUtil.URLDecoderString(userInfo);
			JSONObject jsonObj = SmBaseUtil.PaseJsonToJsonObject(userInfo);
			if (jsonObj!=null && jsonObj.containsKey("openid")) {
				String openid = jsonObj.getString("openid");
				checkParammap = new HashMap<String, Object>();
				int xbusers = 0;
				if(openid!=null && !openid.isEmpty()){
					checkParammap.put("OpenID", openid);
					xbusers = ReadStudentsService.getStudentsCount(checkParammap);
				}
				if (xbusers < 1) {
					xbUser = updateXBUserWeChat(xbUser, openid, StudentsService);
				}

			}

		}
		return xbUser;
	}

	public Students updateXBUserWeChat(Students xbUser, String openid, StudentsService StudentsService) {

		if (xbUser != null && (xbUser.getOpenID()==null || xbUser.getOpenID().isEmpty())) {
			xbUser.setOpenID(openid);
			StudentsService.updateStudents(xbUser);
		}
		return xbUser;
	}


	/**
	 * 学生日志列表!"
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/StudentsLogList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView RegionList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "StudentsLogList");
	}

	@RequestMapping(value = "/getStudentsLogList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getStudentsLogList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String StudentsName = request.getParameter("SName");

		if (StudentsName != null) {
			StudentsName = SmBaseUtil.URLDecoderString(StudentsName);
		}
		System.out.println("" + StudentsName);
		checkParammap.put("StudentsName", StudentsName);
		checkParammap.put("Type", 1);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<StudentsLog> mList = ReadStudentsLogService.getStudentsLogList(checkParammap);
		int count = ReadStudentsLogService.getStudentsLogCount(checkParammap);

		responseMap.put("data", mList);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	@RequestMapping(value = "/deleteStudentsLog", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteStudentsLog(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Map<String, Object> responseMap = null;
		String logid = request.getParameter("logid");
		String[] logids = logid.split(",");
		int result = 0;

		for (String id : logids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				System.out.println(id + "");
				responseMap.put("ID", Long.parseLong(id));
				StudentsLogService.deleteStudentsLog(responseMap);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	/**
	 * 学校入驻
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/SchoolSettledList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView SchoolSettledList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		model.addAttribute("id", request.getParameter("id"));
		return new ModelAndView(SmBaseGlobal.WebViewPath + "SchoolSettledList");
	}

	/**
	 * 获取学校入驻日志列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getSchoolSettledList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSchoolSettledList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String StudentsName = request.getParameter("SName");

		if (StudentsName != null) {
			StudentsName = SmBaseUtil.URLDecoderString(StudentsName);
			checkParammap.put("SchoolName", StudentsName);
		}
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.put("Type", 2);
		List<StudentsLog> mList = ReadStudentsLogService.getStudentsLogList(checkParammap);
		int count = ReadStudentsLogService.getStudentsLogCount(checkParammap);

		responseMap.put("data", mList);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * StudentsUserList学生用户列表
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/StudentsUserList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView StudentsUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", SmBaseGlobal.SettingType.Honor.getid());
		List<BaseInfo> baseInfos=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("Data", baseInfos);
		model.addAttribute("Count", baseInfos.size());
		return new ModelAndView(SmBaseGlobal.WebViewPath + "StudentsUserList");
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
	@RequestMapping(value = "/getStudentsUserList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getStudentsUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			checkParammap.put("BelongAreaID", user.getID());
		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
			checkParammap.put("AreaID", user.getAreaID());
		}
		SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
		checkParammap.putAll(amBaseUtil.getParamsMap(request));
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Students> lswe = ReadStudentsService.getStudentsList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String produrl = SmBaseUtil.getProjectBaseUrl(request);
			if (lswe.get(i).getImageUrl() == null) {
				produrl = produrl + SmBaseGlobal.UserDefaultImageUrl;
			} else {
				produrl = lswe.get(i).getImageUrl();
			}
			String url = SmBaseUtil.getClickImageHtml(produrl);
			lswe.get(i).setImageUrl(url);
		}

		int WeChatCount = ReadStudentsService.getStudentsCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", WeChatCount);
		responseMap.put("Status", 1);
		return responseMap;
	}

	/**
	 * 获取学生新闻列表!"
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/StudentsNewsList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView StudentsNewsList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		model.addAttribute("id", request.getParameter("id"));
		return new ModelAndView(SmBaseGlobal.WebViewPath + "StudentsNewsList");
	}
	@RequestMapping(value = "/phoneUpdatePhone", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView updatePhone(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		model.addAttribute("ttype", 1);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "AuthenticationUser");
	}
	@RequestMapping(value = "/phonehelp", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phonehelp(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phonehelp");
	}
	@RequestMapping(value = "/phoneUpdatePhoneResult", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAuthCode(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String Phone = request.getParameter("Phone");
		String Code = request.getParameter("Code");
		String auth = request.getParameter("auth");
		String type = request.getParameter("type");
		String checkNum = (String)session.getAttribute("AuthenCode");
		String AuthenPhone = (String)session.getAttribute("AuthenPhone");
		String message="";
		if(Phone==null){
			Phone="";
		}
		if(Code==null){
			Code="";
		}
		int resultFlag=0;
		if(checkNum!=null && AuthenPhone!=null &&   AuthenPhone.equals(Phone) && checkNum.equals(Code)){
			if(auth!=null && !auth.isEmpty() && auth.length()>0){
				result.put("Status", -1);
				session.setAttribute("auth", 1);
				return result;
			}
			Students user = (Students) session.getAttribute("StudentName");
			Map<String, Object> responseMap = new HashMap<String, Object>();
			if (user != null) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("Phone", Phone);
				responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				List<Students> stucount = ReadStudentsService.getStudentsList(responseMap);
				if (stucount.size() > 0 && user.getOpenID()!=null && !user.getOpenID().isEmpty() ) {
					WeChatBind bind=new WeChatBind();
					bind.setID(SmBaseUtil.CreateNewID());
					bind.setOpenID(user.getOpenID());
					bind.setUnionID(user.getUnionID());
					bind.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					bind.setStudentID(stucount.get(0).getID());
					WeChatBindService.addWeChatBind(bind);
					if(user!=null && user.getOpenID()!=null && (stucount.get(0).getOpenID()==null || stucount.get(0).getUnionID()==null)){
						stucount.get(0).setOpenID(user.getOpenID());
						stucount.get(0).setUnionID(user.getUnionID());
						if(stucount.get(0).getImageUrl()==null || (stucount.get(0).getImageUrl()!=null && 
								stucount.get(0).getImageUrl().isEmpty() ||stucount.get(0).getImageUrl().contains("default") )){
							stucount.get(0).setImageUrl(user.getImageUrl());
						}
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", user.getID());
						StudentsService.deleteStudents(responseMap);
						StudentsService.updateStudents(stucount.get(0));
					}
					SmBaseUtil.CreateSession("StudentName", stucount.get(0), request, session, response);
					//message="该手机号码已经被绑定,如有疑问请联系客服!";
				}else{
					responseMap = new HashMap<String, Object>();
					responseMap.put("ID", user.getID());
					List<Students> lsstu = ReadStudentsService.getStudentsList(responseMap);
					if(lsstu.size()>0){
						lsstu.get(0).setPhone(Phone);
						resultFlag=StudentsService.updateStudents(lsstu.get(0));
						if(resultFlag>0){
							SmBaseUtil.CreateSession("StudentName", lsstu.get(0), request, session, response);
						}
					}
				}
			}else{
				responseMap = new HashMap<String, Object>();
				responseMap.put("Phone", Phone);
				responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				List<Students> stucount = ReadStudentsService.getStudentsList(responseMap);
				Students Students=new Students();
				if(stucount.size()<=0){
					
					Students.setID(SmBaseUtil.CreateNewID());
					Students.setName(Phone);
					Students.setPhone(Phone);
					Students.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					resultFlag=StudentsService.addStudents(Students);
				}else{
					Students = stucount.get(0);
		
					resultFlag=1;
				}
				if(resultFlag>0){
					SmBaseUtil.CreateSession("StudentName", Students, request, session, response);
				}
			}
			
		}else{
			resultFlag=-1;
		}
		if(type!=null && type.equals("3")){//用于手机号码注册的验证跳转
			resultFlag=-2;
		}
		if(message!=null && !message.isEmpty()){
			result.put("Message", message);
		}
		result.put("Status", resultFlag);
		return result;
	}
	/**
	 * 获取一个学生新闻列!"
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getStudentsNewsList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticesList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		if (title != null) {
			title = SmBaseUtil.URLDecoderString(title);
			System.out.println(title);
			checkParammap.put("Title", title);
		}
		checkParammap.put("UsersID", id);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Notices> lswe = ReadNoticesService.getReadNoticesList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String produrl = SmBaseUtil.getProjectBaseUrl(request);
			String baseUrl=SmBaseUtil.getProjectBaseUrl(request);
			if (lswe.get(i).getImage() == null || lswe.get(i).getImage().getUrl() == null ||lswe.get(i).getImage().getUrl().isEmpty() ) {
				produrl = baseUrl + SmBaseGlobal.ErrorPicture;
				lswe.get(i).setImage(new ProdPictures());
			} else {
				produrl = lswe.get(i).getImage().getUrl().split(",")[0];
			}
			String url = SmBaseUtil.getClickImageHtml(produrl);
			lswe.get(i).getImage().setPUrl(url);
		}
		int count = ReadNoticesService.getNoticesCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	@RequestMapping(value = "/addIntegration", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addIntegration(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String num = request.getParameter("num");
		if(!SmBaseUtil.isNumeric(num)){
			num="0";
		}
		if(!SmBaseUtil.isNumeric(id)){
			id="0";
		}
		if (title != null) {
			title = SmBaseUtil.URLDecoderString(title);
			System.out.println(title);
		}
		checkParammap.put("ID", id);
		List<Students> stu = ReadStudentsService.getStudentsList(checkParammap);
		int result = 0;
		if (stu.size() > 0) {
			stu.get(0).setIntegration(stu.get(0).getIntegration() + Long.parseLong(num));
			result = StudentsService.updateLevelStudents(stu.get(0));
		}
		if (result == 1) {
			NoticesController notic = new NoticesController();
			Notices notices=new Notices();
			notices.setID(stu.get(0).getID());
			notic.addLevel(stu.get(0).getID(), title, notices, Integer.parseInt(num), IntegrationService,
					StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
			responseMap.put("Status", true);
		} else {
			responseMap.put("Status", false);
		}

		return responseMap;
	}

	
	@RequestMapping(value = "/changeOfficial", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> changeOfficial(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String id = request.getParameter("id");
		String type = request.getParameter("type");
		if(id==null || id.isEmpty()){
			id="0";
		}
		int result = 0;
		if(type!=null && type.equals("1")){
			result=StudentsService.UpdateOfficial(Long.parseLong(id));
		}else{
			result=StudentsService.CancelOfficial(Long.parseLong(id));
		}
		if (result >= 1) {
			responseMap.put("Status", true);
		} else {
			responseMap.put("Status", false);
		}

		return responseMap;
	}
	
	@RequestMapping(value = "/changeChief", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> changeChief(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		if(id==null || id.isEmpty()){
			id="0";
		}
		if(type==null || type.isEmpty()){
			type="0";
		}
		int result = 0;
		param.put("ID", id);
		int wemoney=0;
		String title="";
		if(type!=null && type.equals(String.valueOf(SmBaseGlobal.ChiefType.Chief.getid()))){
			param.put("Chief", SmBaseGlobal.ChiefType.Chief.getid());
			wemoney=300;//社长奖励300
			title="晋升社长";
		}else if(type!=null && type.equals(String.valueOf(SmBaseGlobal.ChiefType.DeputyChief.getid()))){
			param.put("Chief", SmBaseGlobal.ChiefType.DeputyChief.getid());
			wemoney=200;//副社长奖励200
			title="晋升副社长";
		}else if(type!=null && type.equals(String.valueOf(SmBaseGlobal.ChiefType.Editorial.getid()))){
			param.put("Chief", SmBaseGlobal.ChiefType.Editorial.getid());
			wemoney=100;//编委奖励100
			title="晋升编委";
		}else{
			param.put("Chief", SmBaseGlobal.ChiefType.Normal.getid());
			wemoney=0;//普通的没有
		}
		result=StudentsService.UpdateChief(param);
		if (result >= 1) {
			if(wemoney>0){
				StudentsService.UpdateOfficial(Long.parseLong(id));
				//更新微米
				param = new HashMap<String, Object>();
				param.put("UserID", id);
				List<WeMoney> stu = ReadWeMoneyService.getWeMoneyList(param);
				if (stu.size() > 0) {
					stu.get(0).setWeMoney(stu.get(0).getWeMoney() + wemoney);
					result = WeMoneyService.updateWeMoney(stu.get(0));
					WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
					weMoneyRecord.setBelong(Long.parseLong(id));
					weMoneyRecord.setReson(title);
					weMoneyRecord.setWeMoney((double)wemoney);
					weMoneyRecord.setFromUserID((long) 0);
					weMoneyRecord.setUserID(Long.parseLong(id));
					weMoneyRecord.setType(WeMoneyType.Other.getid());
					weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					weMoneyRecord.setID(SmBaseUtil.CreateNewID());
					WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
				}
			}
			responseMap.put("Status", true);
		} else {
			responseMap.put("Status", false);
		}
		return responseMap;
	}
	
	@RequestMapping(value = "/addWeMoney", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addWeMoney(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String num = request.getParameter("num");
		if (title != null) {
			title = SmBaseUtil.URLDecoderString(title);
			System.out.println(title);
		}
		if(num==null || num.isEmpty()){
			num = "0";
		}
		checkParammap.put("UserID", id);
		List<WeMoney> stu = ReadWeMoneyService.getWeMoneyList(checkParammap);
		int result = 0;
		if (stu.size() > 0) {
			stu.get(0).setWeMoney(stu.get(0).getWeMoney() + Double.parseDouble(num));
			result = WeMoneyService.updateWeMoney(stu.get(0));
		}
		if (result == 1) {
			WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
			weMoneyRecord.setBelong(Long.parseLong(id));
			weMoneyRecord.setReson(title);
			weMoneyRecord.setWeMoney(Double.parseDouble(num));
			weMoneyRecord.setFromUserID((long) 0);
			weMoneyRecord.setUserID(Long.parseLong(id));
			weMoneyRecord.setType(WeMoneyType.Other.getid());
			weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			weMoneyRecord.setID(SmBaseUtil.CreateNewID());
			WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
			responseMap.put("Status", true);
		} else {
			responseMap.put("Status", false);
		}
		return responseMap;
	}

	@RequestMapping(value = "/relatedActivity", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> relatedActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String id = request.getParameter("id");// 用户ID
		String title = request.getParameter("title");
		// title = java.net.URLDecoder.decode(title, "UTF-8");
		String aid = request.getParameter("aid");// 活动ID
		responseMap.put("StudentID", id);
		responseMap.put("ActivityID", aid);
		List<ActivityPart> ActivityPart = ActivityPartService.getActivityPartPartList(responseMap);
		ActivityPart activitypart;
		int flag = 0;
		if (ActivityPart.size() > 0) {
			activitypart = ActivityPart.get(0);
			if (activitypart.getContent() == 0) {
				activitypart.setContent(1);
				activitypart.setActivityID(Long.parseLong(aid));
				ActivityPartService.updateActivityPart(activitypart);
				flag = 1;
			} else {
				flag = 0;
			}
		} else {
			Long APID = new IdWorker(1, 0).nextId();
			activitypart = new ActivityPart();
			activitypart.setActivityID(Long.parseLong(aid));
			activitypart.setID(APID);
			activitypart.setModifyTime(new Date());
			activitypart.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			activitypart.setStudentID(Long.parseLong(id));
			activitypart.setContent(1);
			ActivityPartService.addActivityPart(activitypart);
			flag = 1;
		}
		responseMap = new HashMap<String, Object>();
		if (flag == 1) {
			responseMap.put("Status", true);
			responseMap.put("Content", "增加积分成功");
			NoticesController notic = new NoticesController();
			Notices notices=new Notices();
			notices.setID(Long.parseLong(aid));
			notic.addLevel(Long.parseLong(id), "参加活动【" + SmBaseUtil.URLDecoderString(title) + "】", notices, 10, IntegrationService,
					StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
		} else {
			responseMap.put("Status", false);
			responseMap.put("Content", "增加积分失败或该学生已经参加活动");
		}
		return responseMap;
	}
	
	@RequestMapping(value = "/addResources", method = RequestMethod.GET)
	public ModelAndView addResources(@ModelAttribute("fileForm") Users users, BindingResult result,HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "addResources");
	}
	/**
	 * 小编分享注册送微米
	 * @param request
	 * @param response
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/phoneShare", method = RequestMethod.GET)
	public ModelAndView phoneShare(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {
		String uid=request.getParameter("uid");
		Students user = (Students) session.getAttribute("StudentName");
		if(uid!=null && !uid.isEmpty()){
			if(user==null || (user!=null && user.getID()!=Long.parseLong(uid))){
				Map<String, Object> responseMap = new HashMap<String, Object>();
				responseMap.put("ID", uid);
				List<Students> ls=  ReadStudentsService.getStudentsList(responseMap);
				if(ls.size()>0){
					model.addAttribute("name", ls.get(0).getName());
					model.addAttribute("uid", ls.get(0).getID());
				}
			}
		}else{
			
			if(user!=null && user.getPhone()!=null && user.getParentName()!=null){
				response.sendRedirect(request.getContextPath() + "/Students/phoneShare?uid="+user.getID()  + "&sina="+SmBaseUtil.Random());
			}
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneShare");
	}
	
	@RequestMapping(value = "/phoneAppDownLoad", method = RequestMethod.GET)
	public ModelAndView phoneAppDownLoad(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws Exception {
		
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonelogin");
	}
	
	@RequestMapping(value = "/importStudents", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> importStudents(MultipartFile file, HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		String message = "";// 为了解决提示乱码 1=“设置成功” 2=“未知图片，请上传正确格式图片” 3=“图片未找到”
							// 4=“服务器异常”
		if (file != null) {
			String path = null;
			String type = null;
			String fileName = file.getOriginalFilename();
			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
			
			if (type != null) {
				// "GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())
				// 去除校验
				if ("xls".equals(type)||"xlsx".equals(type)) {
					String realPath = request.getSession().getServletContext().getRealPath("/");
					path = realPath + "upload/" + user.getID() +"_" +SmBaseUtil.CreateNewID()+"_"+fileName;
					System.out.println(path);
					List<Students>list=new ArrayList<Students>();
					file.transferTo(new File(path));
					 try {
		                  Workbook wookbook = null;
		                  try {
		                	  wookbook = new XSSFWorkbook(path);
		                  } catch (Exception ex) {
		                	  wookbook = new HSSFWorkbook(new FileInputStream(path));
		                  }
		                  //获取到Excel文件中的所有行数­
		                  org.apache.poi.ss.usermodel.Sheet sheet =  wookbook.getSheetAt(0);
		                  int rows = sheet.getPhysicalNumberOfRows();
		                  //遍历行­
		                  for (int i = 1; i < rows; i++) {
		                        // 读取左上端单元格­
		                        Row row = sheet.getRow(i);
		                        // 行不为空­
		                        if (row != null) {
		                              //获取到Excel文件中的所有的列­
		                              int cells = row.getLastCellNum();
		                              List<String> value = new ArrayList<String>();     
		                              
		                              //遍历列­
		                              for (int j = 0; j < cells; j++) {
		                                    //获取到列的值­
		                                    Cell cell = row.getCell(j);
		                                    if (cell != null) {
		                                    	if(cell.getCellType()==0 &&  HSSFDateUtil.isCellDateFormatted(cell)){
		                                    		value.add(sdf.format(cell.getDateCellValue()));
		                                    	}else{
		                                          switch (cell.getCellType()) {
		                                                case Cell.CELL_TYPE_FORMULA:
		                                                break;
		                                                case Cell.CELL_TYPE_NUMERIC:
		                                                	DecimalFormat df = new DecimalFormat("########");
		                                                	value.add(df.format(cell.getNumericCellValue()));   
		                                                break;  
		                                                case Cell.CELL_TYPE_STRING:
		                                                      value.add(cell.getStringCellValue());
		                                                break;
		                                                default:
		                                                      value.add("");
		                                                break;
		                                          	}
			                                  }
		                              }else{
		                            	  value.add("");
		                              }
		                          }
		                            /*  if(value.size()>1){
		                              VerifyCode.sharedInstance().sendActiveApplyMessageResultForYJ(value.get(0), value.get(1), request.getServletContext());
		                              }*/
		                           //   /* 
		                       if(value.size()>0){
		                        	if(value.get(0)==null || value.get(0).isEmpty()){
		                        		message+="第"+ i +"行姓名为空值；";
		                        		continue;
		                        	}
		                        	if(value.get(4)==null || value.get(4).isEmpty()){
		                        		message+="第"+ i +"行手机号码为空值；";
		                        		continue;
		                        	}
		                        	if(value.get(2)!=null && !value.get(2).equals("-") && !value.get(2).isEmpty()){
		                        		try{
		                        			Date date=sdf.parse(value.get(2).replace("/", "-"));
		                        		}catch (Exception e) {
		                        			message+="第"+ i +"行出生日期填写错误；";
			                        		continue;
										}
		
		                        	}
		                        	 if(user!=null && user.getLevel()!=SmBaseGlobal.LevelStatus.ParsonManage.getid() &&
		                        			 value.size()>7 && (value.get(7)==null || value.get(7).isEmpty())){
		                        		 message+="第"+ i +"行学校不能为空";
			                        	 continue;
		                        	 }
		                        	 if(value.size()<6){
		                        		 message = "模版格式不正确,请上传正确的模版文件,并按照实例的要求填写";
		                        		 responseMap.put("message", message);
		                             	responseMap.put("status", false);
		                            
		                             	return responseMap;
		                        	 }
			                        // 将数据插入到mysql数据库中­
			                        Students student = new Students();
			                        student.setID(SmBaseUtil.CreateNewID());
			                        student.setName(value.get(0));
			                        if(value.get(1)!=null && value.get(1).equals("男")){
			                        	student.setSex("1");
			                        }else if(value.get(1)!=null && value.get(1).equals("女")){
			                        	student.setSex("2");
			                        }else{
			                        	student.setSex("0");
			                        }
			                        if(value.get(2)!=null && !value.get(2).equals("-") && !value.get(2).isEmpty() && !value.get(2).equals("0000-00-00 00:00:00")){
			                        	student.setAge(value.get(2));
			                        }else{
			                        	student.setAge(null);
			                        }
			                        
			                        student.setGrade(value.get(3));
			                        student.setPhone(value.get(4));
			                        student.setParentName(value.get(5));
			                        student.setEmail(value.get(6));
			                        if(user!=null && user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid()){
			                        	student.setAreaID(String.valueOf(user.getAreaID()));
		                        		responseMap = new HashMap<String, Object>();
		                        		responseMap.put("ID", user.getAreaID());
		                        		List<Region> regions=ReadRegionService.getRegionList(responseMap);
		                        		if(regions.size()>0){
		                        			student.setSchool(regions.get(0).getName());
		                        		}
			                        	
			                        }else{
			                        	
			                        	if(value.size()>=7 && value.get(7)!=null && !value.get(7).isEmpty()){
			                        		responseMap = new HashMap<String, Object>();
			                        		responseMap.put("Name", value.get(7));
			                        		List<Region> regions=ReadRegionService.getRegionList(responseMap);
			                        		if(regions.size()>0){
			                        			student.setAreaID(regions.get(0).getID());
			                        			student.setSchool(regions.get(0).getName());
			                        		}
			                        	}
			                        }
			                        student.setImageUrl(SmBaseUtil.getUserImageUrl(null, request));
			                        student.setPassWord(SmBaseUtil.MD5("123456"));//初始密码
			                        student.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			                        
			                        list.add(student);
			                        
		                        }  // */
		                  }
		             }
		            if(message.isEmpty()&& list.size()>0){
		            	int index=1;
		            	for (Students student : list) {
		            		responseMap = new HashMap<String, Object>();
		            		responseMap.put("Phone", student.getPhone());
		            		responseMap.put("Sina", SmBaseUtil.Random());
		            		int result=ReadStudentsService.getStudentsCount(responseMap);
		            		
		            		if(result<=0){
		            			try{
			            			StudentsService.addStudents(student);
			                        NoticesController notices=new NoticesController();
			                		notices.getDefaultWeMoneyInfo(student.getID(), WeMoneyService, ReadWeMoneyService);
		            			}catch (Exception e) {
		            				 message+="第"+ index +"行【"+student.getName()+"】导入失败,请检测格式是否正确；";
		                        	 continue;
								}
		            		}
		            		index++;
		            		
						}
		            }
		            wookbook=null;
		      } catch (FileNotFoundException e) {
		            e.printStackTrace();
		            message = "请上传正确的模版文件";
		      } catch (IOException e) {
		            e.printStackTrace();
		            message = "请上传正确的模版文件";
		      }
				}else{
					message = "请上传正确的模版文件";//格式不正确
				}
			} else {
				message = "请上传正确的模版文件";
			}
		} else {
			message = "请上传正确的模版文件";
		}
		if(!message.isEmpty()){
        	responseMap.put("message", message);
        	responseMap.put("status", false);
        }else{
        	responseMap.put("status", true);
        }
		return responseMap;

	}
	@RequestMapping(value = "/makeMoney", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	Map<String, Object> makeMoney(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> map=new HashMap<>();
		String id=request.getParameter("id");
		String money=request.getParameter("money");
		String reason=request.getParameter("reason");
		String message="";
		if (!SmBaseUtil.CheckIsNull(id)) {
			message+="学生编号为空";
		}
		if (!SmBaseUtil.CheckIsNull(money)) {
			message+=" 金额不能为空";
		}
		if (!SmBaseUtil.CheckIsNull(reason)) {
			message+=" 原因不能为空";
		}
		System.err.println("id"+id+"~money"+money+"~reason"+reason);
		if (message!="") {
			map.put("status", false);
			map.put("message", message);
		}else{
			map=new HashMap<>();
			map.put("ID", id);
			map.put("Sina", SmBaseUtil.Random());
			List<Students> students=ReadStudentsService.getStudentsList(map);
			if(students.size()>0 && (students.get(0).getOpenID()==null || students.get(0).getOpenID().isEmpty())){
				map=new HashMap<>();
				map.put("StudentID", id);
				map.put("Sina", SmBaseUtil.Random());
				List<WeChatBind> wechat=ReadWeChatBindService.getWeChatBindList(map);
				if(wechat.size()>0){
					map=new HashMap<>();
					map.put("OpenID", wechat.get(0).getOpenID());
					map.put("Sina", SmBaseUtil.Random());
					students=ReadStudentsService.getStudentsList(map);
					if(students.size()>0){
						id = String.valueOf(students.get(0).getID());
					}
				}
			}
			
			
			WeMoneyController buid=new WeMoneyController();
			Map<String, Object> result = buid.withdrawMethod(id,Float.parseFloat(money),request,response,WeChatInfoServices,ReadStudentsService,null);
			if (result.get("Status").toString().equals("true")) {
				PayRecord payRecord=new PayRecord();
				payRecord.setID(SmBaseUtil.CreateNewID());
				payRecord.setOrderID(String.valueOf(SmBaseUtil.CreateNewID()));
				payRecord.setPayMethod("企业付款");
				payRecord.setPayReason(reason);
				payRecord.setType(1);
				payRecord.setUserID(id);
				payRecord.setVersion("1");
				payRecord.setBeLongID(id);
				payRecord.setMoney(String.valueOf(money));
				payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				PayRecordService.addPayRecord(payRecord);
				map.put("status", true);
				map.put("message", result.get("Message"));
			}else{
				map.put("status", false);
				map.put("message", result.get("Message"));
			}
		}
		return map;
	}
}