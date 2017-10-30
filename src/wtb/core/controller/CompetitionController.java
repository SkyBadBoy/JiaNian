package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage.Req;

import wtb.core.model.ActivityPart;
import wtb.core.model.BaseInfo;
import wtb.core.model.CompetitionApply;
import wtb.core.model.MoneyRecord;
import wtb.core.model.ProdPictures;
import wtb.core.model.Activity;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.WeChatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.core.service.MoneyRecordService;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.UnifiedorderServlet;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Competition")
public class CompetitionController extends BaseController {



	@RequestMapping(value = "/CompetitionCampList", method = RequestMethod.GET)
	public ModelAndView CompetitionCampList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "CompetitionCampList");
	}
	@RequestMapping(value = "/CompetitionList", method = RequestMethod.GET)
	public ModelAndView CompetitionList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "CompetitionList");
	}
	
	@RequestMapping(value = "/SummitMeetingList", method = RequestMethod.GET)
	public ModelAndView SummitMeetingList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "SummitMeetingList");
	}
	
	@RequestMapping(value = "/getCompetitionCampList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCompetitionCampList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String searchText = request.getParameter("searchText");
		if (searchText != null && !searchText.isEmpty()) {
			searchText = SmBaseUtil.URLDecoderString(searchText);
			System.out.println("搜索的内容：" + searchText);
		}
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		
		if (searchText != null && !searchText.isEmpty()) {
			checkParammap.put("check", searchText);
		}
	
		List<wtb.core.model.Competition> lswe = ReadCompetitionService.getCompetitionList(checkParammap);

		int Prodcount = ReadCompetitionService.getCompetitionCount(checkParammap);
		
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	@RequestMapping(value = "/getCompetitionList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCompetitionList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String state = request.getParameter("state");
		String type = request.getParameter("type");
		String searchText = request.getParameter("searchText");
		if (searchText != null && !searchText.isEmpty()) {
			searchText = SmBaseUtil.URLDecoderString(searchText);
			System.out.println("搜索的内容：" + searchText);
		}
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		if (state != null && !state.isEmpty()) {
			checkParammap.put("Status", state);
		} else {
			checkParammap.put("Status", 1);
		}
		if (type != null && !type.isEmpty()) {
			checkParammap.put("Type", type);
		} else {
			checkParammap.put("Type", 1);
		}
		if (searchText != null && !searchText.isEmpty()) {
			checkParammap.put("check", searchText);
		}
	
		List<CompetitionApply> lswe = ReadCompetitionApplyService.getCompetitionList(checkParammap);
		

			Map<String, Object> param = new HashMap<String, Object>();
			for (int i=0;i<lswe.size();i++) {
				CompetitionApply competitionApply = lswe.get(i);
				if(competitionApply.getStringParamA()!=null && !competitionApply.getStringParamA().isEmpty()){
					param = new HashMap<String, Object>();
					param.put("ID", competitionApply.getStringParamA());
					List<Students>list=ReadStudentsService.getStudentsList(param);
					if(list.size()>0){
						competitionApply.setStringParamA(list.get(0).getName());
					}
				}
				if(competitionApply.getStringParamC()!=null && !competitionApply.getStringParamC().isEmpty()){
					param = new HashMap<String, Object>();
					param.put("ID", competitionApply.getStringParamC());
					List<BaseInfo>list=ReadBaseInfoService.getBaseInfoListByID(param);
					if(list.size()>0){
						competitionApply.setStringParamC(list.get(0).getName());
					}
				}
				if(competitionApply.getStringParamD()!=null && competitionApply.getStringParamD().equals("1")){
					competitionApply.setStringParamD("需要");
				}else{
					competitionApply.setStringParamD("不需要");
				}
				
				lswe.set(i, competitionApply);
			}
		
		int Prodcount = ReadCompetitionApplyService.getCompetitionCount(checkParammap);
		
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	
	
	
	
	@RequestMapping(value = "/submitCompetition", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> submitCompetition(HttpServletRequest request,Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Name=request.getParameter("Name");
		String phone=request.getParameter("Phone");
		String teacher=request.getParameter("Teacher");
		String Type=request.getParameter("Type");
		if (Name==null || Name.isEmpty()) {
			responseMap.put("message", "姓名不能为空");
			responseMap.put("status", "-1");
			return responseMap;
		}
		if (teacher==null || teacher.isEmpty()) {
			responseMap.put("message", "父母或者监护人不能为空");
			responseMap.put("status", "-1");
			return responseMap;
		}
		if (phone==null || phone.isEmpty()) {
			responseMap.put("message", "联系方式不能为空");
			responseMap.put("status", "-1");
			return responseMap;
		}else if(!SmBaseUtil.isNumeric(phone)){
			responseMap.put("message", "请输入正确的联系方式");
			responseMap.put("status", "-1");
			return responseMap;
		}
		Map<String, Object> map=new HashMap<>();
		map.put("Phone", phone);
		map.put("Type", Type);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(map);
		if (competitionAppliesFlag>0) {
			responseMap.put("message", "您已报名无需重复报名");
			responseMap.put("status", "-1");
			return responseMap;
		}
		
		CompetitionApply competitionApply=new CompetitionApply();
		competitionApply.setID(SmBaseUtil.CreateNewID());
		competitionApply.setName(Name);
		competitionApply.setTeacher(teacher);
		competitionApply.setPhone(phone);
		competitionApply=setSelfParam(competitionApply,request);
		Students user = (Students) request.getSession().getAttribute("StudentName");
		if (user!=null) {
			competitionApply.setStudentID(String.valueOf(user.getID()));
		}
		competitionApply.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Effective.getid()));
		int competitionFlag=CompetitionApplyService.addCompetition(competitionApply);
		if (competitionFlag>=1) {
			responseMap.put("message", "提交报名申请成功,请耐心等待!");
			responseMap.put("status", "1");
			return responseMap;
		}else{
			responseMap.put("message", "报名失败，请稍后重试!");
			responseMap.put("status", "-1");
			return responseMap;
		}
		
	}
	
	
	@RequestMapping(value = "/putCompetition", method = RequestMethod.POST)
	public String putFile(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request,Model model) throws IOException {
		String filePath="";
		if (file!=null && !file.isEmpty()) {
			String name=file.getOriginalFilename();
			String prefix=name.substring(name.lastIndexOf(".")+1);
			filePath=SmBaseGlobal.UploadAliYunFileService(file.getInputStream(), prefix,"File");
		}else{
			model.addAttribute("Error", "请上传附件");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		String school=request.getParameter("School");
		String phone=request.getParameter("Phone");
		String teacher=request.getParameter("Teacher");
		String Type=request.getParameter("Type");
		if (school!=null) {
			model.addAttribute("school", school);
		}else{
			model.addAttribute("Error", "学校为空");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		if (teacher!=null) {
			model.addAttribute("teacher", teacher);
		}else{
			model.addAttribute("Error", "教师为空");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		if (phone!=null && SmBaseUtil.isNumeric(phone)) {
			model.addAttribute("phone", phone);
		}else if(!SmBaseUtil.isNumeric(phone)){
			model.addAttribute("Error", "请输入正确的联系方式");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}else{
			model.addAttribute("Error", "手机为空");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		Map<String, Object> map=new HashMap<>();
		map.put("Phone", phone);
		map.put("Type", Type);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(map);
		if (competitionAppliesFlag>0) {
			model.addAttribute("Error", "您已报名无需重复报名");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		
		CompetitionApply competitionApply=new CompetitionApply();
		competitionApply.setID(SmBaseUtil.CreateNewID());
		competitionApply.setName(school);
		competitionApply.setTeacher(teacher);
		competitionApply.setPhone(phone);
		competitionApply.setAnnexURL(filePath);
		competitionApply=setSelfParam(competitionApply,request);
		Students user = (Students) request.getSession().getAttribute("StudentName");
		if (user!=null) {
			competitionApply.setStudentID(String.valueOf(user.getID()));
		}
		competitionApply.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Effective.getid()));
		int competitionFlag=CompetitionApplyService.addCompetition(competitionApply);
		if (competitionFlag==1) {
			model.addAttribute("Error", "");
			model.addAttribute("Success", "已经提交报名申请");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}else{
			model.addAttribute("Error", "报名失败，请重新报名");
			return SmBaseGlobal.WeNewsMobileViewPath+"phoneCompetition";
		}
		
	}
	/**
	 * 获取 并设置自定义字段的信息
	 * @param competitionApply
	 * @param request
	 * @return
	 */
	public CompetitionApply setSelfParam(CompetitionApply competitionApply ,HttpServletRequest request) {
		String StringParamA=request.getParameter("StringParamA");
		String StringParamB=request.getParameter("StringParamB");
		String StringParamC=request.getParameter("StringParamC");
		String StringParamD=request.getParameter("StringParamD");
		String StringParamE=request.getParameter("StringParamE");
		String StringParamF=request.getParameter("StringParamF");
		String StringParamG=request.getParameter("StringParamG");
		String StringParamH=request.getParameter("StringParamH");
		String StringParamI=request.getParameter("StringParamI");
		String StringParamJ=request.getParameter("StringParamJ");
		String StringParamK=request.getParameter("StringParamK");
		String IntParamA=request.getParameter("IntParamA");
		String IntParamB=request.getParameter("IntParamB");
		String IntParamC=request.getParameter("IntParamC");
		String IntParamD=request.getParameter("IntParamD");
		String IntParamE=request.getParameter("IntParamE");
		String DateParamA=request.getParameter("DateParamA");
		String DateParamB=request.getParameter("DateParamB");
		String DateParamC=request.getParameter("DateParamC");
		String BigIntParamA=request.getParameter("BigIntParamA");
		String BigIntParamB=request.getParameter("BigIntParamB");
		String BigIntParamC=request.getParameter("BigIntParamC");
		String Type=request.getParameter("Type");
		String BigStringParamA=request.getParameter("BigStringParamA");
		String BigStringParamB=request.getParameter("BigStringParamB");
		if(StringParamA!=null && !StringParamA.isEmpty()){
			competitionApply.setStringParamA(StringParamA);
		}
		if(StringParamB!=null && !StringParamB.isEmpty()){
			competitionApply.setStringParamB(StringParamB);
		}
		if(StringParamC!=null && !StringParamC.isEmpty()){
			competitionApply.setStringParamC(StringParamC);
		}
		if(StringParamD!=null && !StringParamD.isEmpty()){
			competitionApply.setStringParamD(StringParamD);
		}
		if(StringParamE!=null && !StringParamE.isEmpty()){
			competitionApply.setStringParamE(StringParamE);
		}
		if(StringParamF!=null && !StringParamF.isEmpty()){
			competitionApply.setStringParamF(StringParamF);
		}
		if(StringParamG!=null && !StringParamG.isEmpty()){
			competitionApply.setStringParamG(StringParamG);
		}
		if(StringParamH!=null && !StringParamH.isEmpty()){
			competitionApply.setStringParamH(StringParamH);
		}
		if(StringParamI!=null && !StringParamI.isEmpty()){
			competitionApply.setStringParamI(StringParamI);
		}
		if(StringParamJ!=null && !StringParamJ.isEmpty()){
			competitionApply.setStringParamJ(StringParamJ);
		}
		if(StringParamK!=null && !StringParamK.isEmpty()){
			competitionApply.setStringParamK(StringParamK);
		}
		if(IntParamA!=null && !IntParamA.isEmpty()){
			competitionApply.setIntParamA(Integer.parseInt(IntParamA));
		}
		if(IntParamB!=null && !IntParamB.isEmpty()){
			competitionApply.setIntParamB(Integer.parseInt(IntParamB));
		}
		if(IntParamC!=null && !IntParamC.isEmpty()){
			competitionApply.setIntParamC(Integer.parseInt(IntParamC));
		}
		if(IntParamD!=null && !IntParamD.isEmpty()){
			competitionApply.setIntParamD(Integer.parseInt(IntParamD));
		}
		if(IntParamE!=null && !IntParamE.isEmpty()){
			competitionApply.setIntParamE(Integer.parseInt(IntParamE));
		}
		if(DateParamA!=null && !DateParamA.isEmpty()){
			competitionApply.setDateParamA(DateParamA);
		}
		if(DateParamB!=null && !DateParamB.isEmpty()){
			competitionApply.setDateParamB(DateParamB);
		}
		if(DateParamC!=null && !DateParamC.isEmpty()){
			competitionApply.setDateParamC(DateParamC);
		}
		if(BigIntParamA!=null && !BigIntParamA.isEmpty()){
			competitionApply.setBigIntParamA(Long.parseLong(BigIntParamA));
		}
		if(BigIntParamB!=null && !BigIntParamB.isEmpty()){
			competitionApply.setBigIntParamB(Long.parseLong(BigIntParamB));
		}
		if(BigIntParamC!=null && !BigIntParamC.isEmpty()){
			competitionApply.setBigIntParamC(Long.parseLong(BigIntParamC));
		}
		
		if(Type!=null && !Type.isEmpty()){
			competitionApply.setType(Integer.parseInt(Type));
		}else{
			competitionApply.setType(1);
		}
		if(BigStringParamA!=null && !BigStringParamA.isEmpty()){
			competitionApply.setBigStringParamA(BigStringParamA);
		}
		if(BigStringParamB!=null && !BigStringParamB.isEmpty()){
			competitionApply.setBigStringParamB(BigStringParamB);
		}
		return competitionApply;
		
	}
	
	@RequestMapping(value = "/phoneCompetition", method = RequestMethod.GET)
	public String phoneCompetition(Model model)  {
		model.addAttribute("Error", "");
		model.addAttribute("Success", "");
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneCompetition";
	}
	
	@RequestMapping(value = "/Competition", method = RequestMethod.GET)
	public String Competition()  {

		return SmBaseGlobal.WebViewPath + "Competition";
	}
	
	@RequestMapping(value = "/phoneCompetitionDetails", method = RequestMethod.GET)
	public String Competitiondetails()  {
		
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneCompetitionDetails";
	}
	@RequestMapping(value = "/phoneCompetitionNoticesDetails", method = RequestMethod.GET)
	public String phoneCompetitionNoticesDetails()  {
		
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneCompetitionNoticesDetails";
	}
	@RequestMapping(value = "/phoneSummitMeetingDetails", method = RequestMethod.GET)
	public String phoneSummitMeetingDetails()  {
		
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummitMeetingDetails";
	}
	@RequestMapping(value = "/phoneSummitMeeting", method = RequestMethod.GET)
	public String phoneSummitMeeting(Model model)  {
		model.addAttribute("Error", "");
		model.addAttribute("Success", "");
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummitMeeting";
	}
	
	
	@RequestMapping(value = "/phonePutSummerCampApply", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> phonePutSummerCampApply(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
		Students user =(Students)request.getSession().getAttribute("StudentName");
		Map<String, Object> result=new HashMap<>();
		String buid=request.getParameter("buid");
		String type=request.getParameter("cid");
		String wemoney=request.getParameter("wemoney");//微米折扣数量
		if(wemoney==null || wemoney.isEmpty()){
			wemoney="0";
		}
		if(buid==null || buid.isEmpty()){
			buid = "0";
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("StudentID", user.getID());
		map.put("Type", type);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(map);
		if (competitionAppliesFlag>0) {
			 result=new HashMap<>();
			 result.put("Status", false);
			 result.put("Message", "您已报名无需重复报名");
			return result;
		}
		map = new HashMap<String, Object>();
		map.put("UserID", user.getID());
		List<WeMoney>weMoneys=ReadWeMoneyService.getWeMoneyByIDList(map);
		if(weMoneys.size()<=0 || weMoneys.size()>0 && weMoneys.get(0).getWeMoney()<Long.parseLong(wemoney)){
			 result=new HashMap<>();
			 result.put("Status", false);
			 result.put("Message", "微米不足");
			return result;
		}
		
		 result=new HashMap<>();
		 result.put("Status", true);
		 result.put("Message", "检测通过");
		return result;
		
	}
	}