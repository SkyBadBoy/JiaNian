package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import com.tencent.a.a.a.a.c;

import wtb.core.model.AccessActive;
import wtb.core.model.Combo;
import wtb.core.model.Comment;
import wtb.core.model.DisplayWeNewsPhoneData;
import wtb.core.model.Integration;
import wtb.core.model.JsonModel;
import wtb.core.model.Notices;
import wtb.core.model.OnlineCount;
import wtb.core.model.ProdPictures;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.smUtil.NetUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	
	@Autowired
	WeChatGroupController WeChatGroupController;
	@Autowired
	WeNewsController WeNewsController;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		//获取套餐详细表
		Map<String, Object> map=new HashMap<>();
		map.put("Rand", SmBaseUtil.Random());
		List<Combo> combos=ComboService.FindCombosByCondition(map);
		model.addAttribute("Combos", combos);
		//查询7条评论
		map=new HashMap<>();
		map.put("Rand", SmBaseUtil.Random());
		map.put("start", 0);
		map.put("limit", 4);
		map.put("Status", 1);
		List<Comment> comments=ReadCommentService.getCommentList(map);
		model.addAttribute("Comment1", comments);
		
		map=new HashMap<>();
		map.put("Rand", SmBaseUtil.Random());
		map.put("start", 4);
		map.put("limit", 7);
		map.put("Status", 1);
		comments=ReadCommentService.getCommentList(map);
		model.addAttribute("Comment2", comments);
		
		
		return new ModelAndView(SmBaseGlobal.JIANIANPC + "index");
	}
	

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException, SAXException, ParserConfigurationException, DocumentException {
		/*********轻松装************/
		//解析Url获取Document对象
        Document document = Jsoup.connect("http://xuexiao.pinwaiyi.com/").get();
        //获取网页源码文本内容
       // System.out.println(document.toString());
        //获取指定class的内容指定tag的元素
        Elements liElements1 = document.getElementsByClass("shi");
        List<Map<String, Object>> lscontent=new ArrayList<Map<String, Object>>();
        for (int k = 0; k < liElements1.size(); k++) {
        	 Map<String, Object> arealinfo=new HashMap<String, Object>();
        	 String areaname= liElements1.get(k).getElementsByTag("a").text();
        	 arealinfo.put("areaName", areaname);
        	 
        	 String url= liElements1.get(k).getElementsByTag("a").attr("href");
        	 Document document2 = Jsoup.connect(url).get();
        	 Elements liElements2 = document2.getElementsByClass("listtable").get(0).getElementsByClass("list");
        	 if(document2.getElementsByClass("page").size()>0){
        	 String page= document2.getElementsByClass("page").get(0).getElementsByTag("a").last().text().split("/")[1];
        	
        	 List<Map<String, Object>> schoollist=new ArrayList<Map<String, Object>>();
        	 Map<String, Object> schoolinfo=new HashMap<String, Object>();
        	 for (Element element : liElements2) {
        		 
				String schoolName=element.getElementsByClass("title").text();
				String saddress=element.getElementsByClass("other2").text().split(":")[2];
				String scontact=element.getElementsByClass("other2").text().split(":")[1].split(" ")[0];
				schoolinfo.put("schoolName", schoolName);
				schoolinfo.put("saddress", saddress);
				schoolinfo.put("scontact", scontact);
				schoollist.add(schoolinfo);
				 
			}
        	 for (int i = 2; i <Integer.parseInt(page); i++) {
        		  url= "http://xuexiao.pinwaiyi.com/hy/list.php?fid=178&page="+i;
            	  document2 = Jsoup.connect(url).get();
            	  liElements2 = document2.getElementsByClass("listtable").get(0).getElementsByClass("list");
            	 for (Element element : liElements2) {
            		schoolinfo=new HashMap<String, Object>();
    				String schoolName=element.getElementsByClass("title").text();
    				String saddress=element.getElementsByClass("other2").text().split(":")[2];
    				String scontact=element.getElementsByClass("other2").text().split(":")[1].split(" ")[0];
    				schoolinfo.put("schoolName", schoolName);
    				schoolinfo.put("saddress", saddress);
    				schoolinfo.put("scontact", scontact);
    				schoollist.add(schoolinfo);
    			}
            	 
             }
        	 arealinfo.put("schoollist", schoollist);
        	 }
        	 lscontent.add(arealinfo);
		}
       System.out.println("============================================================================");
       for (int i = 0; i < lscontent.size(); i++) {
    	   System.out.println("【"+lscontent.get(i).get("areaName")+"】");
    	   List<Map<String, Object>> schoollist = (List<Map<String, Object>>) lscontent.get(i).get("schoollist");
    	   if(schoollist!=null){
	    	   for (Map<String, Object> map : schoollist) {
	    		   System.out.println("学校名称："+map.get("schoolName"));
	    		   System.out.println("学校地址："+map.get("saddress"));
	    		   System.out.println("学校联系人："+map.get("scontact"));
	    	   }
    	   }
    	   
    		System.out.println("============================================================================");
    	   
		
	}
       System.out.println("over");
		return new ModelAndView("test");
	}


	@RequestMapping(value = "/WeNewsHome", method = RequestMethod.GET)
	public ModelAndView WeNewsHome(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws SocketException, UnknownHostException {
		/**广告位*/
		Map<String, Object> map = new HashMap<>();
		map.put("Type", 2);
		map.put("IsEnable", 1);
		List<ProdPictures> pictures = ReadProdPicturesService.getPictureList(map);//获取广告位
		model.addAttribute("ImageList", pictures);
		model.addAttribute("_page_",1);
		model.addAttribute("_isStu_", 0);
		model.addAttribute("_pc_", "PC");
		
		/**侧栏的状态*/
		model.addAttribute("flag", "Home");//用于侧栏的标记
		
		WeNewsController.WeNewsPCModel(model,req);
		
		return new ModelAndView(SmBaseGlobal.WeNewsPCViewPath + "WeNewsHome");
	}
	
	@RequestMapping(value = "/noticesdetail", method = RequestMethod.GET)
	public ModelAndView noticesdetail(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		String pid = req.getParameter("pid");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ID", pid);
		List<Notices> lswe = ReadNoticesService.getReadNoticesList(responseMap);
		if (lswe.size() > 0) {
			model.addAttribute("Notices", lswe.get(0));
		}

		return new ModelAndView(SmBaseGlobal.WebViewPath + "noticesdetail");
	}

	@RequestMapping(value = "/Parsonhome", method = RequestMethod.GET)
	public ModelAndView Parsonhome(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String pageSize = "8";
		String pageNumber = "0";

		if (pageSize != null && !pageSize.isEmpty()) {
			checkParammap.put("limit", pageSize);
		} else {
			checkParammap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
			checkParammap.put("start", pageNumber);
		} else {
			checkParammap.put("start", 0);
		}

		checkParammap.put("Status", 1);
		checkParammap.put("Type", 1);
		List<Notices> lswe = ReadNoticesService.getReadNoticesList(checkParammap);
		int Prodcount = ReadNoticesService.getNoticesCount(checkParammap);
		model.addAttribute("data", lswe);
		model.addAttribute("total", Prodcount);

		return new ModelAndView(SmBaseGlobal.BaseViewPath + "Parsonhome");

	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();

		// 获取今日新增
		params.put("Days", 0);
		params.put("Status", 1);
		int WeChatDayForDay = ReadWeChatPublicService.getWeChatByDaysList(params);
		int WeChatGroupDayForDay = ReadStudentsService.getStudentsByDaysList(params).getWeChatDayForDay();
		// 获取本月新增
		params = new HashMap<String, Object>();
		params.put("ThisMonth", 30);
		params.put("Status", 1);
		int WeChatGroupDayForMonth = ReadStudentsService.getStudentsByDaysList(params).getWeChatDayForDay();
		int WeChatDayForMonth = ReadWeChatPublicService.getWeChatByDaysList(params);

		params = new HashMap<String, Object>();
		params.put("Status", 1);
		int WeChatGroupDayCount = ReadStudentsService.getStudentsCount(params);
		int WeChatDayCount = ReadWeChatPublicService.getWeChatCount(params);
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		params = new HashMap<String, Object>();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH) + 1;
		int MaxDays = gc.getActualMaximum(Calendar.DATE);
		params.put("Year", year);
		params.put("Month", month);
		List<WeChatLastMonthStatInfo> lswclm = ReadWeChatPublicService.getWeChatLastMonthStat(params);
		List<WeChatLastMonthStatInfo> lswglm = ReadStudentsService.getStudentsMonthStat(params);
		int WeChatLastCountStat = 0;
		int WeChatGroupLastCountStat = 0;
		for (WeChatLastMonthStatInfo weChatLoatMonthStatInfo : lswglm) {
			WeChatGroupLastCountStat += weChatLoatMonthStatInfo.getCount();
		}
		for (WeChatLastMonthStatInfo weChatLoatMonthStatInfo : lswclm) {
			WeChatLastCountStat += weChatLoatMonthStatInfo.getCount();
		}
		
		// 微新闻编数
		params = new HashMap<>();
		params.put("Today", 1);
		int TodaySize = ReadNoticesService.getNoticesCount(params);// 获取今天的条数
		params = new HashMap<>();
		params.put("ToMonth", 1);
		int MonthSize = ReadNoticesService.getNoticesCount(params);// 获取本月的条数

		// 获取昨天
		params = new HashMap<>();
		params.put("Yesterday", 1);
		int YesterdaySize = ReadNoticesService.getNoticesCount(params);// 获取昨天的条数
		params = new HashMap<>();
		params.put("YesMonth", 1);
		int YesMonth = ReadNoticesService.getNoticesCount(params);// 获取上个月的条数

		float WeChatDayRisePercent = 0;
		float WeChatMonthRisePercent = 0;
		float GroupDayRisePercent = 0;
		float GroupMonthRisePercent = 0;
		float WeChatRiseLastPercent = 0;
		float GroupMonthRiseLastPercent = 0;

		if (WeChatDayCount > 0) {
			WeChatDayRisePercent = Float.parseFloat(df.format(((float) WeChatDayForDay / (float) WeChatDayCount) * 100));
			WeChatMonthRisePercent = Float.parseFloat(df.format(((float) WeChatDayForMonth / (float) WeChatDayCount) * 100));
			WeChatRiseLastPercent = Float.parseFloat(df.format(((float) WeChatLastCountStat / (float) WeChatDayCount) * 100));
		}
		if (WeChatGroupDayCount > 0) {
			GroupDayRisePercent = Float.parseFloat(df.format(((float) WeChatGroupDayForDay / (float) WeChatGroupDayCount) * 100));
			GroupMonthRisePercent = Float.parseFloat(df.format(((float) WeChatGroupDayForMonth / (float) WeChatGroupDayCount) * 100));
			GroupMonthRiseLastPercent = Float.parseFloat(df.format(((float) WeChatGroupLastCountStat / (float) WeChatGroupDayCount) * 100));
		}

		model.addAttribute("WeChatDayForDay", WeChatDayForDay);
		model.addAttribute("WeChatGroupDayForDay", WeChatGroupDayForDay);
		model.addAttribute("WeChatGroupDayForMonth", WeChatGroupDayForMonth);
		model.addAttribute("WeChatDayForMonth", WeChatDayForMonth);
		model.addAttribute("WeChatGroupDayCount", WeChatGroupDayCount);
		model.addAttribute("WeChatDayCount", WeChatDayCount);

		model.addAttribute("WeChatDayRisePercent", WeChatDayRisePercent);
		model.addAttribute("WeChatMonthRisePercent", WeChatMonthRisePercent);
		model.addAttribute("GroupDayRisePercent", GroupDayRisePercent);
		model.addAttribute("GroupMonthRisePercent", GroupMonthRisePercent);

		model.addAttribute("TodaySize", TodaySize);
		model.addAttribute("MonthSize", MonthSize);
		if (TodaySize > YesterdaySize) {
			model.addAttribute("YesterdaySize",
					Float.parseFloat(df.format(((float) (TodaySize - YesterdaySize) / (float) (YesterdaySize == 0 ? 1 : YesterdaySize)) * 100)));
			model.addAttribute("a", Float.parseFloat(df.format(((float) (TodaySize - YesterdaySize) / (float) (YesterdaySize == 0 ? 1 : YesterdaySize)))));
		} else {
			model.addAttribute("YesterdaySize",
					Float.parseFloat(df.format(((float) (YesterdaySize - TodaySize) / (float) (YesterdaySize == 0 ? 1 : YesterdaySize)) * 100)));
			model.addAttribute("a", Float.parseFloat(df.format(((float) (TodaySize - YesterdaySize) / (float) (YesterdaySize == 0 ? 1 : YesterdaySize)))));
		}

		if (MonthSize > YesMonth) {
			model.addAttribute("YesMonth", Float.parseFloat(df.format((float) (MonthSize - YesMonth) / (float) (YesMonth == 0 ? 1 : YesMonth) * 100)));
			model.addAttribute("b", Float.parseFloat(df.format((float) (MonthSize - YesMonth) / (float) (YesMonth == 0 ? 1 : YesMonth))));
		} else {
			model.addAttribute("YesMonth", Float.parseFloat(df.format((float) (YesMonth - MonthSize) / (float) (YesMonth == 0 ? 1 : YesMonth) * 100)));
			model.addAttribute("b", Float.parseFloat(df.format((float) (MonthSize - YesMonth) / (float) (YesMonth == 0 ? 1 : YesMonth))));
		}
		// if((float)(TodaySize-YesterdaySize)/(float)(YesterdaySize==0?1:YesterdaySize)>0)
		// {
		// model.addAttribute("YesMonth",
		// Float.parseFloat(df.format((float)MonthSize/(float)YesMonth)));
		// }
		// 上个月微信号总数
		model.addAttribute("WeChatGroupLastCountStat", WeChatGroupLastCountStat);
		// 上个月微盟总数
		model.addAttribute("WeChatLastCountStat", WeChatLastCountStat);
		// 上个月微信号增长%
		model.addAttribute("WeChatRiseLastPercent", WeChatRiseLastPercent);
		// 上个月微信盟增长%
		model.addAttribute("GroupMonthRiseLastPercent", GroupMonthRiseLastPercent);
		// 上个月微信号详细数据
		model.addAttribute("WeChatLastStat", getWeChatStatInfo(lswclm, MaxDays, year, month));
		// 上个月微盟详细数据
		model.addAttribute("WeGroupLastStat", getWeChatStatInfo(lswglm, MaxDays, year, month));

		return new ModelAndView(SmBaseGlobal.BaseViewPath + "home");

	}
	
	/*获取统计信息*/
	@RequestMapping(value = "/getAccessData", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAccessData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		/**
		 * 获取访问量和在线人数
		 */

		params = new HashMap<>();
		params.put("toDay", 1);
		params.put("Sina", SmBaseUtil.Random());
		params.put("Type", SmBaseGlobal.ProjectType.WeNewsService.getid());
		long todayAccessCount = ReadAccessActiveService.getAccessActiveCount(params);// 获取今天的条数
		params = new HashMap<>();
		params.put("CurrentOnline", 1);
		params.put("Sina", SmBaseUtil.Random());
		params.put("Type", SmBaseGlobal.ProjectType.WeNewsService.getid());
		List<OnlineCount> currentOnlineCount = ReadOnlineCountService.getOnlineCountList(params);// 获取本月的条数
		if(currentOnlineCount.size()>0 ){
			result.put("currentOnline", currentOnlineCount.get(0).getCount());
		}else{
			result.put("currentOnline", 0);
		}
		
		result.put("todayAccessCount", todayAccessCount);
		
		
		return result;
	}
	
	/*获取统计信息*/
	@RequestMapping(value = "/getAPPAccessData", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAPPAccessData(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		/**
		 * 获取APP访问量和在线人数
		 */

		params = new HashMap<>();
		params.put("toDay", 1);
		params.put("Sina", SmBaseUtil.Random());
		params.put("Type", SmBaseGlobal.ProjectType.WeNewsInteface.getid());
		long todayAPPAccessCount = ReadAccessActiveService.getAccessActiveCount(params);// 获取今天的条数
		params = new HashMap<>();
		params.put("CurrentOnline", 1);
		params.put("Sina", SmBaseUtil.Random());
		params.put("Type", SmBaseGlobal.ProjectType.WeNewsInteface.getid());
		List<OnlineCount> currentAPPOnlineCount = ReadOnlineCountService.getOnlineCountList(params);// 获取本月的条数
		if(currentAPPOnlineCount.size()>0 ){
			result.put("currentAPPOnline", currentAPPOnlineCount.get(0).getCount());
		}else{
			result.put("currentAPPOnline", 0);
		}
		result.put("todayAPPAccessCount", todayAPPAccessCount);
		params = new HashMap<>();
		params.put("toDay", 1);
		params.put("Sina", SmBaseUtil.Random());
		int toDayAPPOnlineCount = OnlineService.findOnLine(params);// 获取本月的条数
		result.put("toDayAPPOnlineCount", toDayAPPOnlineCount);
		return result;
	}
	
	/*获取统计信息*/
	@RequestMapping(value = "/getYesterdayWeNews", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getYesterdayWeNews(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		params = new HashMap<>();
		params.put("Today", 1);
		int YesterdayWeNews=ReadNoticesService.getSchoolActiveCount(params);
		result.put("YesterdayWeNews", YesterdayWeNews);
		return result;
	}
	
	/*获取统计信息*/
	@RequestMapping(value = "/getMonthWeNews", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getMonthWeNews(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
	
		params = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		params.put("Month", cal.get(Calendar.MONTH) + 1);
		params.put("Year", cal.get(Calendar.YEAR));
		int MonthWeNews=ReadNoticesService.getSchoolActiveCount(params);
		result.put("MonthWeNews", MonthWeNews);
		return result;
	}
	/*获取统计信息*/
	@RequestMapping(value = "/getYesterdayStudents", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getYesterdayStudents(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		params = new HashMap<>();
		params.put("TodayActive", 1);
		int YesterdayStudents=ReadStudentsService.getStudentsCount(params);
		result.put("YesterdayStudents", YesterdayStudents);
		return result;
	}
	/*获取统计信息*/
	@RequestMapping(value = "/getMonthStudents", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getMonthStudents(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		params = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		params.put("MonthActive", cal.get(Calendar.MONTH) + 1);
		params.put("YearActive", cal.get(Calendar.YEAR));
		params.put("isStu", 1);
		int MonthStudents=ReadStudentsService.getStudentsCount(params);
		
		result.put("MonthStudents", MonthStudents);

		
		return result;
	}
	/*获取统计信息*/
	@RequestMapping(value = "/getAllWeNews", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllWeNews(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		int AllWeNews=ReadWeChatPublicService.getWeChatCount(null);
		result.put("AllWeNews", AllWeNews);
		
		return result;
	}
	/*获取统计信息*/
	@RequestMapping(value = "/getAllStudent", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllStudent(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		params = new HashMap<>();
		params.put("isStu", 1);
		int AllStudent=ReadStudentsService.getStudentsCount(params);
		result.put("AllStudent", AllStudent);
		return result;
	}
	
	/**
	 * 学生昨天微米
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getWeMoneyNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeMoneyNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 10);
		checkParammap.put("start", 0);
		checkParammap.put("Yesterday", 1);
		List<WeMoneyRecord> WeMoneys = ReadWeMoneyRecordService.getWeMoneyRecordSumRankingList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<JsonModel> JsonModels = new ArrayList<JsonModel>();
		for (int i = 0; i < WeMoneys.size(); i++) {
			if(WeMoneys.get(i).getNotices()!=null){
				SchoolName.add(WeMoneys.get(i).getNotices().getTitle());
				JsonModel JsonModel=new JsonModel();
				JsonModel.setName(WeMoneys.get(i).getNotices().getTitle());
				JsonModel.setValue(Integer.parseInt(WeMoneys.get(i).getCount()));
				JsonModels.add(JsonModel);
			}
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		checkParammap.put("data", JsonModels);
		return checkParammap;
	}
	
	/**
	 * 学生所有微米
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllWeMoneyNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllWeMoneyNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 20);
		checkParammap.put("start", 0);
		checkParammap.put("orderWeMoney", 1);
		List<WeMoney> WeMoneys = ReadWeMoneyService.getWeMoneyList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		for (int i = 0; i < WeMoneys.size(); i++) {
			if(WeMoneys.get(i).getStudent()!=null){
				SchoolName.add(WeMoneys.get(i).getStudent().getName());
				JsonModels.add(Integer.parseInt(WeMoneys.get(i).getCount()));
			}
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		checkParammap.put("data", JsonModels);
		return checkParammap;
	}
	/**
	 * 昨天学生积分情况排行前10
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getIntegrationNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getIntegrationNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 10);
		checkParammap.put("start", 0);
		checkParammap.put("Yesterday", 1);
		List<Integration> Integrations = ReadIntegrationService.getIntegrationRecordRankingList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<JsonModel> JsonModels = new ArrayList<JsonModel>();
		for (int i = 0; i < Integrations.size(); i++) {
			if(Integrations.get(i).getStudent()!=null){
				SchoolName.add(Integrations.get(i).getStudent().getName());
				JsonModel JsonModel=new JsonModel();
				JsonModel.setName(Integrations.get(i).getStudent().getName());
				JsonModel.setValue(Integrations.get(i).getNum());
				JsonModels.add(JsonModel);
			}
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		checkParammap.put("data", JsonModels);
		return checkParammap;
	}
	/**
	 * 学生所有积分
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllIntegrationNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllIntegrationNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 20);
		checkParammap.put("start", 0);
		checkParammap.put("orderStudents", 1);
		List<Students> Students = ReadStudentsService.getStudentsList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		for (int i = 0; i < Students.size(); i++) {
			SchoolName.add(Students.get(i).getName());
			JsonModels.add(Students.get(i).getCount());
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		checkParammap.put("data", JsonModels);
		return checkParammap;
	}
	
	/**
	 * 学生所有赞赏量
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllRewardNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllRewardNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("start", 0);
		String type=request.getParameter("type");
		if(type!=null && type.equals("1")){
			checkParammap.put("Yesterday", 1);
			checkParammap.put("limit", 10);
		}else{
			checkParammap.put("limit", 20);
		}
		List<WeMoneyRecord> WeMoneyRecords = ReadWeMoneyRecordService.getWeMoneyRecordRankingList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		List<JsonModel> JsonModelList = new ArrayList<JsonModel>();
		for (int i = 0; i < WeMoneyRecords.size(); i++) {
			if(WeMoneyRecords.get(i).getNotices() !=null){
				SchoolName.add(WeMoneyRecords.get(i).getNotices().getTitle());
				if(type!=null && type.equals("1")){
					JsonModel JsonModel=new JsonModel();
					JsonModel.setName(WeMoneyRecords.get(i).getNotices().getTitle());
					JsonModel.setValue(Integer.parseInt(WeMoneyRecords.get(i).getCount()));
					JsonModelList.add(JsonModel);
				}else{
					JsonModels.add(Integer.parseInt(WeMoneyRecords.get(i).getCount()));
				}
			}
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		if(type!=null && type.equals("1")){
			checkParammap.put("data", JsonModelList);
		}else{
			checkParammap.put("data", JsonModels);
		}
		return checkParammap;
	}
	/**
	 * 学生所有投票量
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllStudentsNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getStudentsNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 20);
		checkParammap.put("start", 0);
		List<Notices> notices = ReadNoticesService.getNoticesRankingList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		for (int i = 0; i < notices.size(); i++) {
			if(notices.get(i).getPublisher()!=null){
				SchoolName.add(notices.get(i).getStudent().getName());
				JsonModels.add(notices.get(i).getCount());
			}
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("name", SchoolName);
		checkParammap.put("data", JsonModels);
		return checkParammap;
	}
	
	/*
	 * 学生昨天投稿量
	 */
	@RequestMapping(value = "/getStudentsNoticesCount", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getStudentsNoticesCount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		List<Notices> BingSize = ReadNoticesService.getStudentNoticesList(null);
		List<String> SchoolName = new ArrayList<String>();
		List<JsonModel> JsonModels = new ArrayList<JsonModel>();
		for (int i = 0; i < BingSize.size(); i++) {
			if(BingSize.get(i).getPublisher()!=null){
				SchoolName.add(BingSize.get(i).getStudent().getName());
				JsonModel JsonModel=new JsonModel();
				JsonModel.setName(BingSize.get(i).getSRegion().getName()+" - "+BingSize.get(i).getStudent().getName());
				JsonModel.setValue(BingSize.get(i).getCount());
				JsonModels.add(JsonModel);
			}
		}
		result.put("name", SchoolName);
		result.put("data", JsonModels);
		return result;
	}
	/**
	 * 昨日学校投稿前10
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getSchoolNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSchoolNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		List<Notices> BingSize = ReadNoticesService.getSchoolNoticesList(null);
		List<String> SchoolName = new ArrayList<String>();
		List<JsonModel> JsonModels = new ArrayList<JsonModel>();
		for (int i = 0; i < BingSize.size(); i++) {
			SchoolName.add(BingSize.get(i).getSRegion().getName());
			JsonModel JsonModel=new JsonModel();
			JsonModel.setName(BingSize.get(i).getSRegion().getName());
			JsonModel.setValue(BingSize.get(i).getCount());
			JsonModels.add(JsonModel);
		}
		result.put("name", SchoolName);
		result.put("data", JsonModels);
		return result;
	}
	/**
	 * 新闻阅读排行
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllReadNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllReadNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String type=request.getParameter("type");
		checkParammap.put("start", 0);
		checkParammap.put("ClickCountOrder", 1);
		if(type!=null && type.equals("1")){
			checkParammap.put("Yesterday", 1);
			checkParammap.put("limit", 10);
		}else{
			checkParammap.put("limit", 20);
		}
		List<Notices> NewReads = ReadNoticesService.getReadNoticesList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		List<JsonModel> JsonModelList = new ArrayList<JsonModel>();
		for (int i = 0; i < NewReads.size(); i++) {
			SchoolName.add(NewReads.get(i).getTitle());
			if(type!=null && type.equals("1")){
				JsonModel JsonModel=new JsonModel();
				JsonModel.setName(NewReads.get(i).getTitle());
				JsonModel.setValue(NewReads.get(i).getClickCount());
				JsonModelList.add(JsonModel);
			}else{
				JsonModels.add(NewReads.get(i).getClickCount());
			}
		}
		result.put("name", SchoolName);
		if(type!=null && type.equals("1")){
			result.put("data", JsonModelList);
		}else{
			result.put("data", JsonModels);
		}
		return result;
	}
	
	/**
	 * 新闻阅读排行
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllLikeNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllLikeNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String type=request.getParameter("type");
		checkParammap.put("start", 0);
		checkParammap.put("LikeOrder", 1);
		if(type!=null && type.equals("1")){
			checkParammap.put("Yesterday", 1);
			checkParammap.put("limit", 10);
		}else{
			checkParammap.put("limit", 20);
		}
		List<Notices> NewReads = ReadNoticesService.getReadNoticesList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Long> JsonModels = new ArrayList<Long>();
		List<JsonModel> JsonModelList = new ArrayList<JsonModel>();
		for (int i = 0; i < NewReads.size(); i++) {
			SchoolName.add(NewReads.get(i).getTitle());
			if(type!=null && type.equals("1")){
				JsonModel JsonModel=new JsonModel();
				JsonModel.setName(NewReads.get(i).getTitle());
				JsonModel.setValue(NewReads.get(i).getLike());
				JsonModelList.add(JsonModel);
			}else{
				JsonModels.add(NewReads.get(i).getLike());
			}
		}
		result.put("name", SchoolName);
		if(type!=null && type.equals("1")){
			result.put("data", JsonModelList);
		}else{
			result.put("data", JsonModels);
		}
		
		return result;
	}
	/**
	 * 学校总发稿排行
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAllSchoolNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAllSchoolNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("limit", 20);
		checkParammap.put("start", 0);
		List<Notices> BingSize = ReadNoticesService.getNoticesSchoolRankingList(checkParammap);
		List<String> SchoolName = new ArrayList<String>();
		List<Integer> JsonModels = new ArrayList<Integer>();
		for (int i = 0; i < BingSize.size(); i++) {
			SchoolName.add(BingSize.get(i).getSRegion().getName());
			JsonModels.add(BingSize.get(i).getCount());
		}
		result.put("name", SchoolName);
		result.put("data", JsonModels);
		return result;
	}
	/**
	 * 昨天地区投稿前10
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getAreaNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAreaNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		List<Notices> BingSize = ReadNoticesService.getAreaNoticesList(null);
		List<String> SchoolName = new ArrayList<String>();
		List<JsonModel> JsonModels = new ArrayList<JsonModel>();
		for (int i = 0; i < BingSize.size(); i++) {
			SchoolName.add(BingSize.get(i).getSRegion().getName());
			JsonModel JsonModel=new JsonModel();
			JsonModel.setName(BingSize.get(i).getSRegion().getName());
			JsonModel.setValue(BingSize.get(i).getCount());
			JsonModels.add(JsonModel);
		}
		result.put("name", SchoolName);
		result.put("data", JsonModels);
		return result;
	}
	
	
	@RequestMapping(value = "/getCurrentYearStudentsRegister", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentYearStudentsRegister(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		String type=request.getParameter("type");
		String atype=request.getParameter("atype");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		if(year==null || year.isEmpty() || year.equals("0") || !SmBaseUtil.isNumeric(year)){
			year=String.valueOf(cal.get(Calendar.YEAR));
		}
		param.put("isStu", 1);
		
		if(type!=null && type.equals("year")){
			if(month!=null && !month.isEmpty()){
				param.put("RegisterYear", year);
				param.put("RegisterMonth", month);
				List<WeChatLastMonthStatInfo> list=ReadStudentsService.getStudentsForDayStat(param);
				List<Long> days = new ArrayList<Long>();
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(WeChatLastMonthStatInfo notices:list){
						days.add((long)(notices.getCount()));
						daysTime.add(String.valueOf(notices.getDays())+"日");
					}	
					
				}
				
				result.put("daysTime", daysTime);
				result.put("name1", "访问量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月访问趋势");
			}else{
				param.put("GroupYear", 1);
				java.util.Date currTime = new java.util.Date();
				if(Integer.parseInt(year)!= (currTime.getYear()+1900)){
					param.put("RegisterYear", year);
				}
				List<WeChatLastMonthStatInfo> list=ReadStudentsService.getStudentsForDayStat(param);
				for(int y=2016;y<=currTime.getYear()+1900 ;y++){
					List<Long> days=new ArrayList<Long>();
					
					for (int i = 1; i <=12 ; i++){
						boolean isExist=false;
						long dayCount=0;
						if(list.size()>0){
							for(WeChatLastMonthStatInfo notices:list){
								if(notices.getMonth()==(i) && notices.getYear()==y){
									dayCount+=notices.getCount();
									isExist=true;
								}
							}	
						}
						
						if(!isExist){
							days.add(0l);
						}else{
							days.add(dayCount);
						}
					}
					result.put("name"+(y-2015), y+"年注册量");
					result.put("data"+(y-2015), days);
				}
				
				result.put("title", "访问趋势");
				result.put("dayCount", 12);
				result.put("LineCount", (currTime.getYear()+1900)-2015);
			}
			
		}else if(type!=null && type.equals("hour")){
			param.put("GroupHour", 1);
			param.put("GroupYear", 1);
			param.put("Today", 1);
			List<WeChatLastMonthStatInfo> list=ReadStudentsService.getStudentsForDayStat(param);
			List<Long> days = new ArrayList<Long>();

			List<String> daysTime = new ArrayList<String>();
			if(list.size()>0){
				for(WeChatLastMonthStatInfo notices:list){
					days.add((long)(notices.getCount()));
					daysTime.add(String.valueOf(notices.getHour())+"时");
				}	
			}
			
			result.put("daysTime", daysTime);
			result.put("name1", "访问量");
			result.put("data1", days);
			result.put("type", 1);
			result.put("dayCount", list.size());
			result.put("title", "今日访问趋势");
		}else if(type!=null && type.equals("month")){
			if( month!=null && !month.isEmpty()){
				param.put("RegisterYear", year);
				param.put("RegisterMonth", month);
				List<WeChatLastMonthStatInfo> list=ReadStudentsService.getStudentsForDayStat(param);
				List<Long> days = new ArrayList<Long>();
	
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(WeChatLastMonthStatInfo notices:list){
						days.add((long)(notices.getCount()));
						daysTime.add(String.valueOf(notices.getDays())+"日");
					}	
				}
				result.put("daysTime", daysTime);
				result.put("name1", "访问量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月访问趋势");
			}else{
				param.put("RegisterYear", year);
				List<WeChatLastMonthStatInfo> list=ReadStudentsService.getStudentsForDayStat(param);
				for(int m=0;m<12;m++){
					List<Integer> days=new ArrayList<Integer>();
					for (int i = 1; i <=31 ; i++){
						boolean isExist=false;
						if(list.size()>0){
							for(WeChatLastMonthStatInfo notices:list){
								if(notices.getMonth()==(m+1) && Integer.parseInt(notices.getDays())==i){
									days.add(notices.getCount());
									isExist=true;
									break;
								}
							}	
						}
						if(!isExist){
							days.add(0);
						}
					}
					result.put("name"+(m+1), (m+1)+"月注册数");
					result.put("data"+(m+1), days);
					
				}
				result.put("title", "注册趋势");
				result.put("dayCount", 31);
			}
		}
		// 获取这个月每天的数据getCurrentMonthLastDay()
		
		return result;
	}
	
	
	@RequestMapping(value = "/getCurrentYearAccessActive", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentYearAccessActive(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		String type=request.getParameter("type");
		String atype=request.getParameter("atype");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		if(year==null || year.isEmpty() || year.equals("0") || !SmBaseUtil.isNumeric(year)){
			year=String.valueOf(cal.get(Calendar.YEAR));
		}
		if(type!=null && type.equals("year")){
			if( month!=null && !month.isEmpty()){
				param.put("OnlineYear", year);
				param.put("OnlineMonth", month);
				param.put("Type", atype);
				List<AccessActive>list=ReadAccessActiveService.getDayActiveCountList(param);
				List<Long> days = new ArrayList<Long>();
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(AccessActive notices:list){
						days.add(notices.getCount());
						daysTime.add(String.valueOf(notices.getDay())+"日");
					}	
				}
				
				result.put("daysTime", daysTime);
				result.put("name1", "访问量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月访问趋势");
			}else{
				param.put("GroupYear", 1);
				java.util.Date currTime = new java.util.Date();
				if(Integer.parseInt(year)!= (currTime.getYear()+1900)){
					param.put("OnlineYear", year);
				}
				param.put("Type", atype);
				List<AccessActive>list=ReadAccessActiveService.getDayActiveCountList(param);
				for(int y=2016;y<=currTime.getYear()+1900 ;y++){
					List<Long> days=new ArrayList<Long>();
					for (int i = 1; i <=12 ; i++){
						boolean isExist=false;
						long dayCount=0;
						if(list.size()>0){
							for(AccessActive notices:list){
								if(notices.getMonth()==(i) && notices.getYear()==y){
									dayCount+=notices.getCount();
									isExist=true;
								}
							}	
						}
						
						if(!isExist){
							days.add(0l);
						}else{
							days.add(dayCount);
						}
						
					}
					result.put("name"+(y-2015), y+"年访问量");
					result.put("data"+(y-2015), days);
				}
				result.put("LineCount", (currTime.getYear()+1900)-2015);
				result.put("title", "访问趋势");
				result.put("dayCount", 12);
			}
		}else if(type!=null && type.equals("hour")){
			param.put("OnlineHour", 1);
			param.put("toDay", 1);
			param.put("Type", atype);
			List<AccessActive>list=ReadAccessActiveService.getDayActiveCountList(param);
			List<Long> days = new ArrayList<Long>();

			List<String> daysTime = new ArrayList<String>();
			if(list.size()>0){
				for(AccessActive notices:list){
					days.add(notices.getCount());
					daysTime.add(String.valueOf(notices.getHour())+"时");
				}	
			}
			
			result.put("daysTime", daysTime);
			result.put("name1", "访问量");
			result.put("data1", days);
			result.put("type", 1);
			result.put("dayCount", list.size());
			result.put("title", "今日访问趋势");
		}else if(type!=null && type.equals("month")){
			if( month!=null && !month.isEmpty()){
				param.put("OnlineYear", year);
				param.put("OnlineMonth", month);
				param.put("Type", atype);
				List<AccessActive>list=ReadAccessActiveService.getDayActiveCountList(param);
				List<Long> days = new ArrayList<Long>();
	
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(AccessActive notices:list){
						days.add(notices.getCount());
						daysTime.add(String.valueOf(notices.getDay())+"日");
					}	
				}
				
				result.put("daysTime", daysTime);
				result.put("name1", "访问量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月访问趋势");
			}else{
				param.put("OnlineYear", year);
				param.put("Type", atype);
				List<AccessActive>list=ReadAccessActiveService.getDayActiveCountList(param);
				for(int m=0;m<12;m++){
					List<Long> days=new ArrayList<Long>();
					for (int i = 1; i <=31 ; i++){
						boolean isExist=false;
						if(list.size()>0){
							for(AccessActive notices:list){
								if(notices.getMonth()==(m+1) && notices.getDay()==i){
									days.add(notices.getCount());
									isExist=true;
									break;
								}
							}	
						}
						if(!isExist){
							days.add(0l);
						}
					}
					result.put("name"+(m+1), year+"年"+(m+1)+"月访问量");
					result.put("data"+(m+1), days);
					
				}
				result.put("title", "访问趋势");
				result.put("dayCount", 31);
			}
		}
		// 获取这个月每天的数据getCurrentMonthLastDay()
		
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/getCurrentYearNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentYearNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		String uid=request.getParameter("uid");
		String areaID=request.getParameter("areaID");
		String type=request.getParameter("type");
		String year=request.getParameter("year");
		if(year==null || year.isEmpty() || year.equals("0") || !SmBaseUtil.isNumeric(year)){
			year=String.valueOf(cal.get(Calendar.YEAR));
		}
		param.put("Year", year);
		if(areaID!=null && !areaID.isEmpty()){
			param.put("AreaID", areaID);
		}
		if(uid!=null && !uid.isEmpty()){
			param.put("UsersID", uid);
		}
		if(type!=null && type.equals("year")){
			List<Notices>list=ReadNoticesService.getDayNoticesCountList(param);
			List<Integer> days = new ArrayList<Integer>();
			
			for(int month=0;month<12;month++){
				int daysCount=0;
				if(list.size()>0){
					for(Notices notices:list){
						if(notices.getMonth()==(month+1)){
							daysCount+=notices.getCount();
						}
					}	
				}
				days.add(daysCount);
			}

			result.put("name1", "投稿数");
			result.put("data1", days);
			result.put("dayCount", 12);
			result.put("title", year+"年投稿趋势");
		}else{
			List<Notices>list=ReadNoticesService.getDayNoticesCountList(param);
			for(int month=0;month<12;month++){
				List<Integer> days=new ArrayList<Integer>();
				for (int i = 1; i <=31 ; i++){
					boolean isExist=false;
					if(list.size()>0){
						for(Notices notices:list){
							if(notices.getMonth()==(month+1) && notices.getDay()==i){
								days.add(notices.getCount());
								isExist=true;
								break;
							}
						}	
					}
					if(!isExist){
						days.add(0);
					}
				}
				result.put("name"+(month+1), (month+1)+"月投稿数");
				result.put("data"+(month+1), days);
				
			}
			result.put("title", "投稿趋势");
			result.put("dayCount", 31);
		}
		// 获取这个月每天的数据getCurrentMonthLastDay()
		
		return result;
	}
	
	@RequestMapping(value = "/getCurrentMonthNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentMonthNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		String uid=request.getParameter("uid");
		String areaID=request.getParameter("areaID");
		int year=cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH)+1;
		int maxDate=SmBaseUtil.getCurrentMonthLastDay();
		param.put("Year", year);
		param.put("Month", month);
		if(areaID!=null && !areaID.isEmpty()){
			param.put("AreaID", areaID);
		}
		if(uid!=null && !uid.isEmpty()){
			param.put("UsersID", uid);
		}
		List<Notices>list=ReadNoticesService.getDayNoticesCountList(param);

			List<Integer> days=new ArrayList<Integer>();
			for (int i = 1; i <=maxDate ; i++){
				boolean isExist=false;
				if(list.size()>0){
					for(Notices notices:list){
						if(notices.getMonth()==(month) && notices.getDay()==i){
							days.add(notices.getCount());
							isExist=true;
							break;
						}
					}	
				}
				if(!isExist){
					days.add(0);
				}
			}

			result.put("name", (month)+"月");
			result.put("data", days);
			
		result.put("title", "投稿趋势");
		result.put("dayCount", maxDate);
		// 获取这个月每天的数据getCurrentMonthLastDay()
		
		return result;
	}
	
	/**
	 * 阙廷康  
	 * 获取当前在线人数的统计信息
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getCurrentOnlineStat", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentOnlineStat(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		String type=request.getParameter("type");
		String atype=request.getParameter("atype");
		if(type==null || type.isEmpty() || !SmBaseUtil.isNumeric(type)){
			type = "1";
		}
		if(type.equals("1")){
			param.put("OnlineLater", 15);
			
		}else if(type.equals("2")){
			param.put("OnlineLater", 60);
			
		}else if(type.equals("3")){
			param.put("OnlineToDay", 1);
		}
		param.put("Type", atype);
		param.put("Sina", SmBaseUtil.Random());
		List<OnlineCount> list=ReadOnlineCountService.getOnlineCountList(param);
		List<Long> days = new ArrayList<Long>();
		List<String> daysTime = new ArrayList<String>();
		if(list.size()>0){
			for(OnlineCount notices:list){
				days.add(notices.getCount());
				daysTime.add(notices.getCreateTime());
			}	
		}
		
		result.put("daysTime", daysTime);
		result.put("name1", "在线数");
		result.put("data1", days);
		result.put("dayCount", list.size());
		result.put("type", 1);
		result.put("title", "在线增长趋势");
		
		
		return result;
	}
	
	
	
	/**
	 * 统计支社的增长情况
	 * getCurrentWeChatPublic
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getCurrentWeChatPublic", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCurrentWeChatPublic(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		String type=request.getParameter("type");
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		if(year==null || year.isEmpty() || year.equals("0") || !SmBaseUtil.isNumeric(year)){
			year=String.valueOf(cal.get(Calendar.YEAR));
		}
		if(type!=null && type.equals("year")){
			if( month!=null && !month.isEmpty()){
				param.put("Year", year);
				param.put("Month", month);
				List<WeChatLastMonthStatInfo>list=ReadWeChatPublicService.getWeChatLastMonthStat(param);
				List<Long> days = new ArrayList<Long>();
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(WeChatLastMonthStatInfo notices:list){
						days.add((long)notices.getCount());
						daysTime.add(String.valueOf(notices.getDays())+"日");
					}	
				}
				
				result.put("daysTime", daysTime);
				result.put("name1", "新增量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月新增趋势");
			}else{
				param.put("GroupYear", 1);
				java.util.Date currTime = new java.util.Date();
				param.put("Year", year);
				
				List<WeChatLastMonthStatInfo>list=ReadWeChatPublicService.getWeChatLastMonthStat(param);
				for(int y=2016;y<=currTime.getYear()+1900 ;y++){
					List<Long> days=new ArrayList<Long>();
					for (int i = 1; i <=12 ; i++){
						boolean isExist=false;
						long dayCount=0;
						if(list.size()>0){
							for(WeChatLastMonthStatInfo notices:list){
								if(notices.getMonth()==(i) && notices.getYear()==y){
									dayCount+=notices.getCount();
									isExist=true;
								}
							}	
						}
						
						if(!isExist){
							days.add(0l);
						}else{
							days.add(dayCount);
						}
						
					}
					result.put("name"+(y-2015), y+"年新增量");
					result.put("data"+(y-2015), days);
				}
				result.put("LineCount", (currTime.getYear()+1900)-2015);
				result.put("title", "新增趋势");
				result.put("dayCount", 12);
			}
		}else if(type!=null && type.equals("month")){
			if( month!=null && !month.isEmpty()){
				param.put("Year", year);
				param.put("Month", month);
				List<WeChatLastMonthStatInfo>list=ReadWeChatPublicService.getWeChatLastMonthStat(param);
				List<Long> days = new ArrayList<Long>();
	
				List<String> daysTime = new ArrayList<String>();
				if(list.size()>0){
					for(WeChatLastMonthStatInfo notices:list){
						days.add((long)notices.getCount());
						daysTime.add(String.valueOf(notices.getDays())+"日");
					}	
				}
				
				result.put("daysTime", daysTime);
				result.put("name1", "新增量");
				result.put("data1", days);
				result.put("type", 1);
				result.put("dayCount", list.size());
				result.put("title", month+"月新增趋势");
			}else{
				param.put("Year", year);
				List<WeChatLastMonthStatInfo>list=ReadWeChatPublicService.getWeChatLastMonthStat(param);
				for(int m=0;m<12;m++){
					List<Long> days=new ArrayList<Long>();
					for (int i = 1; i <=31 ; i++){
						boolean isExist=false;
						if(list.size()>0){
							for(WeChatLastMonthStatInfo notices:list){
								if(notices.getMonth()==(m+1)  && Integer.parseInt(notices.getDays())==i){
									days.add((long)notices.getCount());
									isExist=true;
									break;
								}
							}	
						}
						if(!isExist){
							days.add(0l);
						}
					}
					result.put("name"+(m+1), year+"年"+(m+1)+"月新增量");
					result.put("data"+(m+1), days);
					
				}
				result.put("title", "新增趋势");
				result.put("dayCount", 31);
			}
		}
		// 获取这个月每天的数据getCurrentMonthLastDay()
		
		return result;
	}
	
	
	
	public String getWeChatStatInfo(List<WeChatLastMonthStatInfo> lswclm, int MaxDays, int year, int month) {
		String wcjsons = "";
		for (int i = 1; i <= MaxDays; i++) {
			String Value = "0";
			for (int day = 0; day < lswclm.size(); day++) {
				if (lswclm.get(day).getDays().equals(String.valueOf(i))) {
					Value = String.valueOf(lswclm.get(day).getCount());
				}
			}
			wcjsons += "; gd(" + String.valueOf(year) + ", " + String.valueOf(month) + ", " + String.valueOf(i) + "): " + Value + " ";
		}
		if (!wcjsons.isEmpty()) {
			wcjsons = wcjsons.substring(1);
		}
		return wcjsons;
		// [ gd(2012, 1, 1), 7 ]
	}

	@RequestMapping(value = "/phoneweNewsindex", method = RequestMethod.GET)
	public ModelAndView weNewsindex(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneweNewsindex");

	}

	/**
	 * 获得当月莫一天
	 */
	public int DataDay(int day,Calendar cal,String uid,String areaID) {
		if(cal==null){
			cal = Calendar.getInstance();
		}
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		// 当前月的第一天
		cal.set(GregorianCalendar.DAY_OF_MONTH, day);
		Date beginTime = cal.getTime();
		String beginTime1 = datef.format(beginTime) + " 00:00:00";
		String endTime1 = datef.format(beginTime) + " 23:59:59";

		Map<String, Object> params = new HashMap<>();
		params.put("SCreateTime", beginTime1);
		params.put("ECreateTime", endTime1);
		if(uid!=null && !uid.isEmpty()){
			params.put("UsersID", uid);
		}
		if(areaID!=null && !areaID.isEmpty()){
			params.put("AreaID", areaID);
		}
		
		return ReadNoticesService.getNoticesCount(params);
	}

	/**
	 * 获取本月天数
	 * 
	 * @return
	 */
	public int getCurrentMonthLastDay(Calendar cal) {
		Calendar a = Calendar.getInstance();
		if(cal!=null){
			a=cal;
		}
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

}