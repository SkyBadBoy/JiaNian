package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import wtb.core.model.Integration;
import wtb.core.model.Notices;
import wtb.core.model.Students;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

@Controller
@RequestMapping("Ranking")
public class RankingController extends BaseController {

	@RequestMapping(value = "/RankingList", method = RequestMethod.GET)
	public ModelAndView RankingList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "RankingList");
	}

	/**
	 * 学生发稿
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getStudentRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getHotActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		List<Notices> notices = ReadNoticesService.getNoticesRankingList(checkParammap);
		int count = ReadNoticesService.getNoticesRankingCount(checkParammap);
		for (int i = 0; i < notices.size(); i++) {
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("ID", notices.get(i).getPublishUser());
			List<Students> students = ReadStudentsService.getStudentsList(checkParammap);
			if (students.size() > 0) {
				notices.get(i).setName(students.get(0).getName());
			} else {
				notices.remove(notices.get(i));
			}
		}
		responseMap.put("Data", notices);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * 学校发稿
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getNoticesSchoolRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticesSchoolRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		List<Notices> notices = ReadNoticesService.getNoticesSchoolRankingList(checkParammap);
		int count = ReadNoticesService.getNoticesSchoolRankingCount(checkParammap);
		responseMap.put("Data", notices);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}
	@RequestMapping(value = "/phoneSchoolRanking", method = RequestMethod.GET)
	public ModelAndView phoneSchoolRanking(@ModelAttribute("StudentsForm") Students Students, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String type=req.getParameter("type");
		if(type==null || type.isEmpty()){
			type="0";
		}
		if(type.equals("3")){
			model.addAttribute("title", "今日投稿排行榜");
		}else if(type.equals("1")){
			model.addAttribute("title", "本周投稿排行榜");
			checkParammap.putAll(getWeekDay());
			model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
		}else if(type.equals("2")){
			model.addAttribute("title", "本月投稿排行榜");
		}else if(type.equals("0")){
			model.addAttribute("title", "总投稿排行榜");
		}
		model.addAttribute("type", type);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneSchoolRanking");

	}
	/**
	 * 学校发稿
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/phonegetNoticesSchoolRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phonegetNoticesSchoolRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String type = request.getParameter("type");
		if (type == null || type.isEmpty()) {
			type = "1";
		}
		if (type.equals("1")) {
			checkParammap.putAll(getWeekDay());
		} else if (type.equals("2")) {
			Calendar cal = Calendar.getInstance();
			checkParammap.put("Month", cal.get(Calendar.MONTH) + 1);
			checkParammap.put("Year", cal.get(Calendar.YEAR));
		} else if(type.equals("3")){
			checkParammap.put("Today", 1);
		}else if(type.equals("5")){
			String voteid = request.getParameter("voteid");
			if(voteid!=null && !voteid.isEmpty() && !voteid.equals("0")){
				checkParammap.put("VoteID", voteid);
			}
		}
		
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Notices> Notices = ReadNoticesService.getNoticesSchoolRankingList(checkParammap);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Notices.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", Notices.get(i).getAreaID());
			jsonObject.put("pkid", String.valueOf(Notices.get(i).getAreaID()));
			jsonObject.put("title", Notices.get(i).getSRegion().getName());
			jsonObject.put("readCount", Notices.get(i).getCount());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("AreaID", Notices.get(i).getAreaID());
			param.put("Rand",SmBaseUtil.getRandomString(10));
			List<WeChatPublic>chatPublics=ReadWeChatPublicService.getWeChatPublicList(param);
			if(chatPublics.size()<=0){
				WeChatPublic weChatPublic=new WeChatPublic();
				weChatPublic.setID(SmBaseUtil.CreateNewID());
				weChatPublic.setWeChat(Notices.get(i).getSRegion().getCode());
				weChatPublic.setCompany(Notices.get(i).getSRegion().getName());
				weChatPublic.setAreaID(Notices.get(i).getSRegion().getID());
				weChatPublic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weChatPublic.setBind(1);
				WeChatPublicService.addWeChatPublic(weChatPublic);
				chatPublics.add(weChatPublic);
			}
			if(chatPublics.size()>0){
				if(chatPublics.get(0).getCompany()!=null){
					jsonObject.put("author", chatPublics.get(0).getCompany());
					jsonObject.put("title", chatPublics.get(0).getCompany());
				}else{
					jsonObject.put("author","");
					jsonObject.put("title", "");
				}
				if(chatPublics.get(0).getContent()!=null && !chatPublics.get(0).getContent().isEmpty()){
					jsonObject.put("school", chatPublics.get(0).getContent());
				}else{
					jsonObject.put("school", "");
				}
				if(chatPublics.get(0).getLogo()!=null &&  chatPublics.get(0).getLogo().getUrl()!=null){
					jsonObject.put("imageUrl", chatPublics.get(0).getLogo().getUrl().split(",")[0]);
				}else{
					jsonObject.put("imageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.SchoolDefaultImageUrl);
				}
			}else{
				jsonObject.put("imageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.SchoolDefaultImageUrl);
			}
			jsonObject.put("level", Notices.get(i).getLevel());
			
			jsonArray.add(jsonObject);
		}
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		return responseMap;
	}
	
	
	/**
	 * 暑假微新闻大赛投票排行
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/phoneWXWDSRanking", method = RequestMethod.GET)
	public ModelAndView phoneWXWDSRanking2(HttpServletResponse response,HttpServletRequest req, HttpSession session, Model model) throws IOException {
		String type=req.getParameter("type");
		String uid=req.getParameter("uid");
		String vid=req.getParameter("vid");
		if(!SmBaseUtil.CheckIsNull(vid)){
			vid = "879990030779944960";
		}
		if(!SmBaseUtil.CheckIsNull(uid)){
			uid = "0";
		}
		Students student = (Students) req.getSession().getAttribute("StudentName");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 1);
		checkParammap.put("start", 0);
		checkParammap.put("VoteID", vid);
		if(student!=null){
			checkParammap.put("UsersID", student.getID());
		}else{
			checkParammap.put("UsersID", uid);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ID", uid);
			List<Students> students = ReadStudentsService.getStudentsList(param) ;
			if(students.size()>0){
				student = students.get(0);
			}
		}
		List<Notices> readStudents=ReadNoticesService.getNoticesVoteRankingList(checkParammap);
		if(readStudents.size()>0){
			model.addAttribute("Student", student);
			model.addAttribute("level", readStudents.get(0).getLevel());
			model.addAttribute("count", readStudents.get(0).getCount());
			model.addAttribute("notices", readStudents.get(0));
		}else{
			model.addAttribute("Student", student);
			model.addAttribute("level", "未入榜");
			model.addAttribute("count",0);
		}

		model.addAttribute("title", "2017暑假微新闻大赛排行榜");
		if(!SmBaseUtil.CheckIsNull(type)){
			model.addAttribute("type", true);
		}else{
			model.addAttribute("type", false);
		}
		
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneWXWDSRanking2");

	}
	
	/**
	 * 暑假微新闻大赛投票排行
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getphoneWXWDSRanking", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getphoneWXWDSRanking(HttpServletResponse response,HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String vid=req.getParameter("vid");
		if(!SmBaseUtil.CheckIsNull(vid)){
			vid = "879990030779944960";
		}
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("VoteID", vid);
		checkParammap.putAll(SmBaseUtil.AddPageParam(req));
		List<Notices> Notices = ReadNoticesService.getNoticesVoteRankingList(checkParammap);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Notices.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", Notices.get(i).getPKID());
			jsonObject.put("title", Notices.get(i).getTitle());
			jsonObject.put("readCount", Notices.get(i).getCount());
			jsonObject.put("author", Notices.get(i).getAuthor());
			jsonObject.put("uid", Notices.get(i).getPublishUser());
			if (Notices.get(i).getSRegion() != null && Notices.get(i).getSRegion().getName() != null && !Notices.get(i).getSRegion().getName().isEmpty()) {
				jsonObject.put("school", Notices.get(i).getSRegion().getName());
			} else {
				if(Notices.get(i).getStudent() != null && Notices.get(i).getStudent().getSchool()!= null && !Notices.get(i).getStudent().getSchool().isEmpty()){
					jsonObject.put("school", Notices.get(i).getStudent().getSchool());
				}else{
					jsonObject.put("school", "");
				}
				
			}
			if(Notices.get(i).getStudent().getImageUrl()!=null){
				jsonObject.put("userImageUrl", Notices.get(i).getStudent().getImageUrl().split(",")[0]);
			}else{
				jsonObject.put("userImageUrl", SmBaseUtil.getProjectBaseUrl(req)+SmBaseGlobal.UserDefaultImageUrl);
			}
			jsonObject.put("level", Notices.get(i).getLevel());
			jsonArray.add(jsonObject);
		}
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		return responseMap;

	}
	
	@RequestMapping(value = "/phoneStudentsRanking", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("StudentsForm") Students Students, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		String type=req.getParameter("type");
		Students student = (Students) req.getSession().getAttribute("StudentName");
		if(type==null || type.isEmpty() || type.equals("0") ){
			type="0";
			if(student!=null){
				Map<String, Object> checkParammap = new HashMap<String, Object>();
				checkParammap.put("limit", 1);
				checkParammap.put("start", 0);
				checkParammap.put("ID", student.getID());
				List<Students> readStudents=ReadStudentsService.getStudentsAllNoticesList(checkParammap);
				if(readStudents.size()>0){
					model.addAttribute("Student", readStudents.get(0));
					if( readStudents.get(0).getNoticeCount()==0){
						model.addAttribute("level", "未入榜");
					}else{
						model.addAttribute("level", readStudents.get(0).getOrder());
					}
					model.addAttribute("count", readStudents.get(0).getNoticeCount());
				}else{
					model.addAttribute("Student", student);
					model.addAttribute("level", "未入榜");
					model.addAttribute("count",0);
				}
				
			}
		}
		if(!type.equals("0")){
			if(student!=null){
				Map<String, Object> checkParammap = new HashMap<String, Object>();
				checkParammap.put("limit", 1);
				checkParammap.put("start", 0);
				if (type.equals("1")) {
					checkParammap.putAll(getWeekDay());
					model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
				} else if (type.equals("2")) {
					Calendar cal = Calendar.getInstance();
					checkParammap.put("Month", cal.get(Calendar.MONTH) + 1);
					checkParammap.put("Year", cal.get(Calendar.YEAR));
				} else if(type.equals("3")){
					checkParammap.put("Today", 1);
				}else if(type.equals("4")){
					if(student!=null){
						checkParammap.put("AreaID", student.getAreaID());
						checkParammap.putAll(getLastWeekDay());
						model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
					}
				}
				checkParammap.put("RankUsersID", student.getID());
				List<Notices> Notices = ReadNoticesService.getNoticesRankingList(checkParammap);
				if(Notices.size()>0){
					model.addAttribute("Student", Notices.get(0).getStudent());
					if(Notices.get(0).getCount()>0){
						model.addAttribute("level", Notices.get(0).getLevel());
					}else{
						model.addAttribute("level", "未入榜");
					}
					model.addAttribute("count", Notices.get(0).getCount());
				}else{
					model.addAttribute("Student", student);
					model.addAttribute("level", "未入榜");
					model.addAttribute("count",0);
				}
			}
		}
		if(type.equals("3")){
			model.addAttribute("title", "今日投稿排行榜");
		}else if(type.equals("1")){
			model.addAttribute("title", "本周投稿排行榜");
		}else if(type.equals("2")){
			model.addAttribute("title", "本月投稿排行榜");
		}else if(type.equals("0")){
			model.addAttribute("title", "总投稿排行榜");
		}else if(type.equals("4")){
			model.addAttribute("title", "上周投稿排行榜");
			if(student!=null && student.getSchool()!=null && !student.getSchool().isEmpty()){
				model.addAttribute("school", student.getSchool());
			}
		}
		model.addAttribute("type", type);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneStudentRanking");

	}

	/**
	 * 个人发稿
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/phonegetNoticesStudentRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phonegetNoticesStudentRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String type = request.getParameter("type");
		if (type == null || type.isEmpty()) {
			type = "1";
		}
		if (type.equals("1")) {
			checkParammap.putAll(getWeekDay());
		} else if (type.equals("2")) {
			Calendar cal = Calendar.getInstance();
			checkParammap.put("Month", cal.get(Calendar.MONTH) + 1);
			checkParammap.put("Year", cal.get(Calendar.YEAR));
		} else if(type.equals("3")){
			checkParammap.put("Today", 1);
		}else if(type.equals("4")){
			Students user = (Students) request.getSession().getAttribute("StudentName");
			checkParammap.putAll(getLastWeekDay());
			if(user!=null){
				checkParammap.put("AreaID", user.getAreaID());
			}
		}else {
			if(type.equals("5")){
				String voteid = request.getParameter("voteid");
				if(voteid!=null && !voteid.isEmpty() && !voteid.equals("0")){
					checkParammap.put("VoteID", voteid);
				}
			}
			checkParammap.put("orderNoticeCount", 1);
			
		}
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Notices> Notices=new ArrayList<Notices>();
		if(!type.equals("0")){
			 Notices = ReadNoticesService.getNoticesRankingList(checkParammap);
		}else{
			List<Students> readStudents=ReadStudentsService.getStudentsAllNoticesList(checkParammap);
			if(readStudents.size()>0){
				for (Students stu :readStudents) {
					Notices noticestemp=new Notices();
					noticestemp.setCount((int)stu.getNoticeCount());
					noticestemp.setPublishUser(String.valueOf(stu.getID()));
					noticestemp.setStudent(stu);
					noticestemp.setLevel(stu.getOrder());
					Notices.add(noticestemp);
				}
			}
		}
		
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Notices.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", Notices.get(i).getPublishUser());
			jsonObject.put("readCount", Notices.get(i).getCount());
			if(Notices.get(i).getStudent().getName()!=null){
				jsonObject.put("author", Notices.get(i).getStudent().getName());
				jsonObject.put("title", Notices.get(i).getStudent().getName());
			}else{
				jsonObject.put("author", "");
				jsonObject.put("title", "");
			}
			if(Notices.get(i).getStudent().getSchool()!=null){
				jsonObject.put("school",Notices.get(i).getStudent().getSchool());
			}else{
				jsonObject.put("school", "");
			}
			if(Notices.get(i).getStudent().getImageUrl()!=null){
				jsonObject.put("imageUrl", Notices.get(i).getStudent().getImageUrl().split(",")[0]);
			}else{
				jsonObject.put("imageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			}
			
			jsonObject.put("level", Notices.get(i).getLevel());
			
			jsonArray.add(jsonObject);
		}
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		return responseMap;
	}
	
	/**
	 * 新闻阅读
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getNoticeReadRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticeReadRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		checkParammap.put("ClickCountOrder", 1);
		List<Notices> Notices = ReadNoticesService.getReadNoticesList(checkParammap);
		int count = ReadNoticesService.getNoticesCount(checkParammap);
		responseMap.put("Data", Notices);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	@RequestMapping(value = "/phoneReadRanking", method = RequestMethod.GET)
	public ModelAndView phoneReadRanking(@ModelAttribute("StudentsForm") Students Students, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		Students student = (Students) req.getSession().getAttribute("StudentName");
		String type=req.getParameter("type");
		if(type==null || type.isEmpty()){
			type="0";
		}
		if(type.equals("3")){
			model.addAttribute("title", "今日阅读排行榜");
		}else if(type.equals("1")){
			model.addAttribute("title", "本周阅读排行榜");
			checkParammap.putAll(getWeekDay());
			model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
		}else if(type.equals("2")){
			model.addAttribute("title", "本月阅读排行榜");
		}else if(type.equals("0")){
			model.addAttribute("title", "总阅读排行榜");
		}else if(type.equals("4")){
			model.addAttribute("title", "上周阅读排行榜");
			if(student!=null){
				model.addAttribute("school", student.getSchool());
				checkParammap.putAll(getLastWeekDay());
				model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
			}
			//checkParammap.putAll(getLastWeekDay());
			//model.addAttribute("StatisticsRange","统计周期（"+String.valueOf(checkParammap.get("startTime")).substring(0,10)+"到"+String.valueOf(checkParammap.get("endTime")).substring(0,10)+"）");
		}
		model.addAttribute("type", type);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneReadRanking");

	}
	
	@RequestMapping(value = "/phoneKindRanking", method = RequestMethod.GET)
	public ModelAndView phoneKindRanking(@ModelAttribute("StudentsForm") Students Students, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		model.addAttribute("title", "微新闻社本周之最");
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneKindRanking");

	}
	/**
	 * 新闻阅读
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getphoneNoticeReadRankingJson", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> getphoneNoticeReadRankingJson(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		String submitType = request.getParameter("submitType");
		if (submitType == null || submitType.isEmpty()) {
			responseMap.put("Message", "submitType不能为空");
			responseMap.put("Status", false);
			return responseMap;
		}
		String type = request.getParameter("type");
		if (type == null || type.isEmpty()) {
			type = "1";
		}
		if (type.equals("1")) {
			checkParammap.putAll(getWeekDay());
		} else if (type.equals("2")) {
			Calendar cal = Calendar.getInstance();
			checkParammap.put("Month", cal.get(Calendar.MONTH) + 1);
			checkParammap.put("Year", cal.get(Calendar.YEAR));
		} else if(type.equals("3")){
			checkParammap.put("Today", 1);
		}else if(type.equals("4")){
			checkParammap.putAll(getLastWeekDay());
			if(user!=null){
				checkParammap.put("AreaID", user.getAreaID());
			}
		}else if(type.equals("5")){
			String voteid = request.getParameter("voteid");
			if(voteid!=null && !voteid.isEmpty() && !voteid.equals("0")){
				checkParammap.put("VoteID", voteid);
			}
		}
		
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.put("ClickCountOrder", 1);
		List<Notices> Notices = ReadNoticesService.getNoticesReadRankingList(checkParammap);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Notices.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", Notices.get(i).getPKID());
			jsonObject.put("title", Notices.get(i).getTitle());
			jsonObject.put("readCount", Notices.get(i).getCount());
			jsonObject.put("author", Notices.get(i).getAuthor());
			jsonObject.put("uid", Notices.get(i).getPublishUser());
			if (Notices.get(i).getSRegion() != null && Notices.get(i).getSRegion().getName() != null && !Notices.get(i).getSRegion().getName().isEmpty()) {
				jsonObject.put("school", Notices.get(i).getSRegion().getName());
			} else {
				if(Notices.get(i).getStudent() != null && Notices.get(i).getStudent().getSchool()!= null && !Notices.get(i).getStudent().getSchool().isEmpty()){
					jsonObject.put("school", Notices.get(i).getStudent().getSchool());
				}else{
					jsonObject.put("school", "");
				}
				
			}
			if(Notices.get(i).getStudent().getImageUrl()!=null){
				jsonObject.put("userImageUrl", Notices.get(i).getStudent().getImageUrl().split(",")[0]);
			}else{
				jsonObject.put("userImageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			}
			jsonObject.put("level", Notices.get(i).getLevel());
			jsonArray.add(jsonObject);
		}
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		return responseMap;
	}
	
	
	/**
	 * 微社之最
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getphoneRankingKing", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	Map<String, Object> getphoneRankingKing(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String pageNumber=request.getParameter("pageNumber");
		if(!pageNumber.equals("1")){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Data", new JSONArray());
			responseMap.put("Status", true);
			return responseMap;
		}
		//这里的NoticesType表示 1 为学生 2为新闻
		JSONArray jsonArray = new JSONArray();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("ClickCountOrder", 1);
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		List<Notices> Notices = ReadNoticesService.getReadNoticesList(checkParammap);//阅读之最
		if(Notices.size()>0){
			Notices.get(0).setLevel("阅读之最");
			Notices.get(0).setType(2);
			Notices.get(0).setCount((int)Notices.get(0).getClickCount());
			jsonArray.add(setRankKindData(Notices.get(0),request));
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("LikeOrder", 1);
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		Notices = ReadNoticesService.getReadNoticesList(checkParammap);//点赞之最
		if(Notices.size()>0){
			Notices.get(0).setLevel("点赞之最");
			Notices.get(0).setType(2);
			Notices.get(0).setCount((int)Notices.get(0).getLike());
			jsonArray.add(setRankKindData(Notices.get(0),request));
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("CommentOrder", 1);
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		Notices = ReadNoticesService.getReadNoticesList(checkParammap);//评论之最
		if(Notices.size()>0){
			Notices.get(0).setLevel("评论之最");
			Notices.get(0).setType(2);
			Notices.get(0).setCount((int)Notices.get(0).getCommentCount());
			jsonArray.add(setRankKindData(Notices.get(0),request));
		}
		
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("Num", 2);
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		List<Integration> integrations=ReadIntegrationService.getNoticesIntegrationRankingList(checkParammap);//分享之最
		if(integrations.size()>0){
			Notices notices2=ReadNoticesService.getNoticesByID(integrations.get(0).getStudentID());
			if(notices2!=null){
				notices2.setCount(integrations.get(0).getNum());
				notices2.setLevel("分享之最");
				notices2.setType(2);
				jsonArray.add(setRankKindData(notices2,request));
			}
			
		}
		
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		List<WeMoneyRecord> weMoneyRecords=ReadWeMoneyRecordService.getWeMoneyRecordRankingList(checkParammap);//赞赏之最
		if(weMoneyRecords.size()>0){
			Notices notices2=ReadNoticesService.getNoticesByID(weMoneyRecords.get(0).getBelong());
			if(notices2!=null){
				notices2.setCount(Integer.parseInt(weMoneyRecords.get(0).getCount()));
				notices2.setLevel("赞赏之最");
				notices2.setType(2);
				jsonArray.add(setRankKindData(notices2,request));
			}
			
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		weMoneyRecords=ReadWeMoneyRecordService.getWeMoneyRewardRankingList(checkParammap);//打赏之最
		if(weMoneyRecords.size()>0){
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", weMoneyRecords.get(0).getBelong());
			List<Students> students=ReadStudentsService.getStudentsList(responseMap);
			Notices notices2=new Notices();
			if(students.size()>0){
				notices2.setCount(Integer.parseInt(String.valueOf(weMoneyRecords.get(0).getCount())));
				notices2.setPKID(String.valueOf(students.get(0).getID()));
				notices2.setTitle(students.get(0).getName());
				notices2.setAuthor(students.get(0).getName());
				notices2.setPublishUser(String.valueOf(students.get(0).getID()));
				notices2.setSRegion(students.get(0).getArea());
				notices2.setStudent(students.get(0));
				notices2.setLevel("打赏之最");
				notices2.setType(1);
			}
			jsonArray.add(setRankKindData(notices2,request));
			
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		Notices = ReadNoticesService.getNoticesRankingList(checkParammap);//发稿之最
		if(Notices.size()>0){
			Notices.get(0).setPKID(String.valueOf(Notices.get(0).getPublishUser()));
			Notices.get(0).setLevel("发稿之最");
			Notices.get(0).setType(1);
			Notices.get(0).setAuthor(Notices.get(0).getStudent().getName());
			Notices.get(0).setTitle(Notices.get(0).getStudent().getName());
			Notices.get(0).setSRegion(Notices.get(0).getStudent().getArea());
			jsonArray.add(setRankKindData(Notices.get(0),request));
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		checkParammap.put("Count", 2);
		Notices = ReadNoticesService.getReadNoticesList(checkParammap);//人气之最
		if(Notices.size()>0){
			Notices.get(0).setLevel("人气之最");
			Notices.get(0).setType(2);
			jsonArray.add(setRankKindData(Notices.get(0),request));
		}
		
		checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(getWeekDay());
		checkParammap.put("start", 0);
		checkParammap.put("limit", 1);
		Notices = ReadNoticesService.getNoticesChiefKindRankingList(checkParammap);//社长之最
		if(Notices.size()>0){
			Notices notices2=new Notices();
			notices2.setCount(Integer.parseInt(String.valueOf(Notices.get(0).getCount())));
			notices2.setPKID(String.valueOf(Notices.get(0).getPublishUser()));
			notices2.setTitle(Notices.get(0).getStudent().getName());
			notices2.setAuthor(Notices.get(0).getStudent().getName());
			notices2.setPublishUser(Notices.get(0).getPublishUser());
			notices2.setSRegion(Notices.get(0).getSRegion());
			notices2.setStudent(Notices.get(0).getStudent());
			notices2.setLevel("社长之最");
			notices2.setType(1);
			jsonArray.add(setRankKindData(notices2,request));
		}
		for(int i=0;i<jsonArray.size();i++){
			if(jsonArray.getJSONObject(i)==null){
				jsonArray.remove(i);
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		return responseMap;
	}
	public JSONObject setRankKindData(Notices Notices,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", Notices.getPKID());
		jsonObject.put("title", Notices.getTitle());
		jsonObject.put("readCount", Notices.getCount());
		jsonObject.put("author", Notices.getAuthor());
		jsonObject.put("uid", Notices.getPublishUser());
		if (Notices.getSRegion() != null && Notices.getSRegion().getName() != null && !Notices.getSRegion().getName().isEmpty()) {
			if(Notices.getType()==2){
				jsonObject.put("school", Notices.getAuthor() +"  "+Notices.getSRegion().getName());
			}else{
				
				String sex=Notices.getStudent().getSex();
				if(sex==null||sex.isEmpty()){
					sex = "0";
				}
				jsonObject.put("sex", sex);
				jsonObject.put("school", Notices.getSRegion().getName());
			}
		} else {
			jsonObject.put("school", "");
		}
		if(Notices.getStudent().getImageUrl()!=null){
			jsonObject.put("imageUrl", Notices.getStudent().getImageUrl().split(",")[0]);
		}else{
			jsonObject.put("imageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
		}
		
		jsonObject.put("type", Notices.getType());
		jsonObject.put("level", Notices.getLevel());
		return jsonObject;
	}
	
	/**
	 * 新闻阅读
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getphoneNoticeReadRanking", produces = "text/html;charset=UTF-8", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String getphoneNoticeReadRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String submitType = request.getParameter("submitType");
		if (submitType == null || submitType.isEmpty()) {
			responseMap.put("Message", "submitType不能为空");
			responseMap.put("Status", false);
			return responseMap.toString();
		}
		String type = request.getParameter("type");
		if (type == null || type.isEmpty()) {
			type = "1";
		}
		if (type.equals("1")) {
			checkParammap.putAll(getWeekDay());
		} else if (type.equals("2")) {
			Calendar cal = Calendar.getInstance();
			checkParammap.put("Month", cal.get(Calendar.MONTH) + 1);
			checkParammap.put("Year", cal.get(Calendar.YEAR));
		} else if(type.equals("3")){
			checkParammap.put("Today", 1);
		}
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.put("ClickCountOrder", 1);
		List<Notices> Notices = ReadNoticesService.getReadNoticesList(checkParammap);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < Notices.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("title", Notices.get(i).getTitle());
			jsonObject.put("readCount", Notices.get(i).getCount());
			jsonObject.put("author", Notices.get(i).getAuthor());
			jsonObject.put("uid", Notices.get(i).getPublishUser());
			if (Notices.get(i).getSRegion() != null && Notices.get(i).getSRegion().getName() != null && !Notices.get(i).getSRegion().getName().isEmpty()) {
				jsonObject.put("school", Notices.get(i).getSRegion().getName());
			} else {
				jsonObject.put("school", "");
			}
			if(Notices.get(i).getImage()!=null && Notices.get(i).getImage().getUrl()!=null && !Notices.get(i).getImage().getUrl().isEmpty()){
				jsonObject.put("imageUrl", Notices.get(i).getImage().getUrl().split(",")[0]);
			}else{
				jsonObject.put("imageUrl", "#");
			}
			jsonArray.add(jsonObject);
		}
		responseMap.put("Data", jsonArray);
		responseMap.put("Status", true);
		JSONObject json = new JSONObject();
		json.putAll(responseMap);
		return json.toString();
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

	/**
	 * 新闻阅读
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getNoticeLikeRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticeLikeRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		// checkParammap.put("Hot", 1);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		checkParammap.put("LikeOrder", 1);
		List<Notices> Notices = ReadNoticesService.getReadNoticesList(checkParammap);
		int count = ReadNoticesService.getNoticesCount(checkParammap);
		responseMap.put("Data", Notices);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * 新闻赞赏
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getNoticeAppreciateRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticeClickCountRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		// checkParammap.put("Hot", 1);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		List<WeMoneyRecord> WeMoneyRecords = ReadWeMoneyRecordService.getWeMoneyRecordRankingList(checkParammap);
		int count = ReadWeMoneyRecordService.getWeMoneyRecordCount(checkParammap);
		responseMap.put("Data", WeMoneyRecords);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * 学生积分
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getIntegrationRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getIntegrationRankingList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		// checkParammap.put("Hot", 1);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		checkParammap.put("orderStudents", 1);
		// checkParammap.put("startTime", "2016-11-18 00:00:00");
		// checkParammap.put("endTime", "2016-11-18 23:59:59");
		List<Integration> Students = ReadIntegrationService.getIntegrationRecordRankingList(checkParammap);
		int count = ReadIntegrationService.getIntegrationRecordRankingCount(checkParammap);

		responseMap.put("Data", Students);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * 学生微米
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getWeMoneyRankingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeMoneyList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		// checkParammap.put("Hot", 1);
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.putAll(TimeQuery(request));
		checkParammap.put("orderWeMoney", 1);
		// checkParammap.put("startTime", "2016-11-18 00:00:00");
		// checkParammap.put("endTime", "2016-11-18 23:59:59");
		List<WeMoneyRecord> WeMoneys = ReadWeMoneyRecordService.getWeMoneyRecordSumRankingList(checkParammap);
		int count = ReadWeMoneyRecordService.getWeMoneyRecordRankingCount(checkParammap);
		int index=0;
		for (WeMoneyRecord weMoneyRecord : WeMoneys) {
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("ID", weMoneyRecord.getNotices().getPublishUser());
			List<Students> stu=ReadStudentsService.getStudentsList(checkParammap);
			if(stu.size()>0){
				weMoneyRecord.setFromUser(stu.get(0));
				WeMoneys.set(index++, weMoneyRecord);
				
			}
		}
		responseMap.put("Data", WeMoneys);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	public static Map<String, Object> TimeQuery(HttpServletRequest request) {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String AreaID = request.getParameter("AreaID");
		if (startTime == null || startTime.isEmpty() || startTime.equals("undefined") || endTime == null || endTime.isEmpty() || endTime.equals("undefined")) {
			checkParammap.put("startTime", null);
			checkParammap.put("endTime", null);
		} else {
			checkParammap.put("startTime", startTime + " 00:00:00");
			checkParammap.put("endTime", endTime + " 23:59:59");
		}
		if(AreaID!=null && !AreaID.isEmpty() && !AreaID.equals("0")){
			checkParammap.put("AreaID", AreaID);
			
		}
		return checkParammap;
	}
}
