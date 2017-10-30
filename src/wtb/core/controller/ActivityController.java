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
import java.util.Properties;

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

import com.alibaba.fastjson.JSONObject;

import wtb.core.model.ActivityPart;
import wtb.core.model.ApplyList;
import wtb.core.model.BaseInfo;
import wtb.core.model.Competition;
import wtb.core.model.CompetitionApply;
import wtb.core.model.MoneyRecord;
import wtb.core.model.Notices;
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
import wtb.smUtil.SessionUtils;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.UnifiedorderServlet;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Activity")
public class ActivityController extends BaseController {

	@RequestMapping(value = "/addActivity", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("ActivityForm") Activity Activity, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Users user = null;
		int Status = 0;
		Long ProdID = new IdWorker(1, 0).nextId();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String isNew = req.getParameter("isNew");
		model.addAttribute("isNew", isNew);
		req.setAttribute("aid", ProdID);
		String PID = req.getParameter("pid");
		String WeChatID = req.getParameter("WeChatID");
		if (WeChatID != null) {
			model.addAttribute("WeChatID", WeChatID);
		}else{
			model.addAttribute("WeChatID", 0);
		}
		if (PID != null && !PID.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Activity> lsps = ReadActivityService.getActivityList(responseMap);
			if (lsps.size() > 0) {
				Activity = lsps.get(0);
				ProdPictures lsimg = Activity.getImage();
				if (lsimg != null) {
					model.addAttribute("Image", Activity.getImage().getUrl());
					System.out.println("Activity.getImage().getUrl()" + Activity.getImage().getUrl());
				} else {
					model.addAttribute("Image", "#");
				}
				model.addAttribute("aid", Activity.getID());
				model.addAttribute("ActivityEndTime", Activity.getEndTime().substring(0, 10));
				model.addAttribute("Content", Activity.getContent());
				model.addAttribute("WeChat", Activity.getWeChat().getCompany());
				model.addAttribute("WeChatID", Activity.getWeChat().getID());
				model.addAttribute("ImageID", Activity.getImageID());
				model.addAttribute("ActivityApplyMoney", Activity.getApplyMoney());
				model.addAttribute("ActivityApplyLimit", Activity.getApplyLimit());
				model.addAttribute("PayType", Activity.getPayType());
				Status = 1;
			} else {
				Status = 404;
				
			}
		}
		if (Status == 404 || Status == 0) {
			model.addAttribute("ImageID", 0);
		}

		model.addAttribute("ActivityForm", Activity);
		model.addAttribute("Status", Status);
		user = (Users) session.getAttribute("UserName");
		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid() && Activity.getID() > 0) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewActivity");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addActivity");
		}
	}

	@RequestMapping(value = "/phoneaddactivity", method = RequestMethod.GET)
	public ModelAndView phoneaddactivity(@ModelAttribute("ActivityForm") Activity Activity, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		String PID = req.getParameter("pid");
		String WeChatID = req.getParameter("WeChatID");
		if (WeChatID != null) {
			model.addAttribute("WeChatID", WeChatID);
		}
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Activity> lsps = ReadActivityService.getActivityList(responseMap);
			if (lsps.size() > 0) {
				Activity = lsps.get(0);
				ProdPictures lsimg = Activity.getImage();
				if (lsimg != null) {
					model.addAttribute("Image", Activity.getImage().getUrl());
				} else {
					model.addAttribute("Image", "#");
				}

				model.addAttribute("ActivityEndTime", Activity.getEndTime().substring(0, 10));
				model.addAttribute("Content", Activity.getContent());
				model.addAttribute("WeChat", Activity.getWeChat().getCompany());
				model.addAttribute("WeChatID", Activity.getWeChat().getID());
				model.addAttribute("ImageID", Activity.getImageID());
				Status = 1;
			} else {
				Status = 404;
			}
		}

		if (Status == 404 || Status == 0) {
			model.addAttribute("ImageID", 0);
		}

		model.addAttribute("ActivityForm", Activity);
		model.addAttribute("Status", Status);
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneaddactivity");
	}

	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("ActivityForm") Activity Activity, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {
		Integer proResult = 0;
		long ProdID = 0;
		Users user = null;
		String submittype = req.getParameter("submittype");
		proResult = 0;
		String isNews = req.getParameter("isNew");
		String imageIDs = req.getParameter("Images");
		String DelImages = req.getParameter("DelImages");
		String ImageID = req.getParameter("ImageID");

		ProdID = 0;
		if (Activity.getID() > 0 && isNews == null) {
			ProdID = Activity.getID();
		} else {
			ProdID = Long.parseLong(req.getParameter("aid"));
			Map<String, Object> map=new HashMap<>();
			map.put("ID", ProdID);
			List<Activity> activities=ReadActivityService.getActivityList(map);
			if(activities.size()<=0){
				isNews="1";
			}
		}
		String EndTime = null;

		model.addAttribute("aid", ProdID);
		EndTime = req.getParameter("ActivityEndTime");

		if (EndTime == null || EndTime.isEmpty()) {
			result.rejectValue("EndTime", "misFormat", "过期时间不能为空");
		} else {
			model.addAttribute("ActivityEndTime", EndTime);
		}
		String ApplyLimit=req.getParameter("ActivityApplyLimit");
		if (ApplyLimit == null || ApplyLimit.isEmpty()||ApplyLimit=="") {
			ApplyLimit="0";
		}
		model.addAttribute("ActivityApplyLimit", ApplyLimit);
		
		
		String paytype=req.getParameter("PayType");
		Activity.setPayType(Integer.parseInt(paytype));
		
		String ApplyMoney=req.getParameter("ActivityApplyMoney");
		if (ApplyMoney == null || ApplyMoney.isEmpty()||ApplyMoney=="") {
			ApplyMoney="0";
		}
		model.addAttribute("ActivityApplyMoney", ApplyMoney);
		
		model.addAttribute("Content", Activity.getContent());
		if (Activity.getTitle().isEmpty()) {
			result.rejectValue("Title", "misFormat", "活动标题不能为空");
		}
		if (Activity.getContent().isEmpty()) {
			result.rejectValue("Content", "misFormat", "活动简介不能为空");
		}
		if (isNews != null && !isNews.isEmpty() && (imageIDs == null || imageIDs.isEmpty())) {
			result.rejectValue("Image", "misFormat", "请选择至少一张封面图片");
		}
		if (imageIDs != null && imageIDs.length() > 0) {
			Activity = SavePicture(imageIDs, DelImages, Activity);
		}
		if (ImageID != null && !ImageID.isEmpty() && Long.parseLong(ImageID) > 0 && Activity.getImageID() <= 0) {
			Activity.setImageID(Long.parseLong(ImageID));
		}
		if (Activity.getWeChatID() > 0) {
			model.addAttribute("WeChatID", Activity.getWeChatID());
		}
		if (!result.hasErrors()) {
			/* 保存文件 begin */
			user = (Users) session.getAttribute("UserName");
			Activity.setStatus(1);
			Activity.setModifyTime(new Date());
			Activity.setApplyLimit(Integer.parseInt(ApplyLimit));
			Activity.setApplyMoney(Double.parseDouble(ApplyMoney));
			Activity.setEndTime(EndTime);
			if (isNews != null && !isNews.isEmpty()) {
				Activity.setID(ProdID);
				Activity.setWeChatID(user.getWeChatID());
				Activity.setCreateTime(new Date());
				proResult = ActivityService.addActivity(Activity);
			} else {
				if (Activity.getWeChatID() <= 0) {
					Activity.setWeChatID(user.getWeChatID());
				}
				proResult = ActivityService.updateActivity(Activity);
			}
			if (proResult > 0) {

				if (submittype != null && submittype.equals("phone")) {
					model.addAttribute("content", req.getContextPath() + "/Activity/phoneactivitylist?WeChatID=" + String.valueOf(Activity.getWeChatID()));
					return new ModelAndView(SmBaseGlobal.MobileViewPath + "modifysuccess");
				} else {
					model.addAttribute("content", req.getContextPath() + "/Activity/ActivityList?WeChatID=" + String.valueOf(Activity.getWeChatID()));
					return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
				}

			} else {
				Activity.setID(0);
				result.rejectValue("Title", "misFormat", "保存失败,请重试");
			}
		}
		if (result.hasErrors()) {
			model.addAttribute("ActivityForm", Activity);// 把accountVo对象返回到页面，这样不至于表单被清空了
			if (submittype != null && !submittype.isEmpty() && submittype.equals("phone")) {
				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneaddactivity");
			} else {
				return new ModelAndView(SmBaseGlobal.WebViewPath + "addActivity");
			}
		}
		if (submittype != null && submittype.equals("phone")) {
			return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneaddgood");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addActivity");
		}

	}

	@RequestMapping(value = "/ActivityList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Users user = null;
		String WeChatID = request.getParameter("WeChatID");
		user = (Users) session.getAttribute("UserName");
		if (WeChatID == null || WeChatID.isEmpty()) {
			WeChatID = String.valueOf(user.getWeChatID());
		}
		model.addAttribute("WeChatID", WeChatID);

		return new ModelAndView(SmBaseGlobal.WebViewPath + "ActivityList");
	}
	
	
	/**
	 * 退款
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws net.sf.json.JSONException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 */
	@RequestMapping(value = "/refundWeChat", method = RequestMethod.GET)
	public @ResponseBody Map<String , Object> refundWeChat(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, net.sf.json.JSONException, KeyManagementException, UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
		Map<String , Object> map=new HashMap<String, Object>();
		String aid=request.getParameter("aid");
		String sid=request.getParameter("sid");
		String applyid=request.getParameter("applyid");
		String paytype=request.getParameter("paytype");
		map.put("ActivityID", aid);
		map.put("StudentID", sid);
		map.put("Type", SmBaseGlobal.MoneyType.Pay.getid());
		List<MoneyRecord> moneyRecords=MoneyRecordService.getMoneyRecordList(map);
		System.out.println(Integer.parseInt(paytype)==SmBaseGlobal.PayType.WeMoney.getid());
		if (moneyRecords.size()>0) {
			String money=moneyRecords.get(0).getMoney();
			if (Integer.parseInt(paytype)==SmBaseGlobal.PayType.RMB.getid()) {
				final String out_refund_no=SmBaseUtil.getRandomString(32);
				String out_trade_no=moneyRecords.get(0).getPKID();//订单号也就是记录ID
				WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(WeChatInfoServices, null);
				net.sf.json.JSONObject jsonObject=UnifiedorderServlet.Refund(out_refund_no, out_trade_no, Double.parseDouble(money),weChatInfo);
				System.out.println(jsonObject);
				if (jsonObject.get("status").equals("SUCCESS")) {
					ActivityService.CanCelApplyActivity(Long.parseLong((aid)));//人数减去一个
					map=new HashMap<String, Object>();
					map.put("ID", applyid);
					ActivityPartService.deleteActivityPart(map);//在申请表中状态也去删掉
					moneyRecords.get(0).setRefundID(out_refund_no);//付款记录改为退款
					MoneyRecordService.refundMoneyRecord(moneyRecords.get(0));
					map=new HashMap<String, Object>();
					map.put("status", true);
					map.put("Message", "退款成功");
				}else{
					map=new HashMap<String, Object>();
					map.put("status", false);
					map.put("Message", "退款失败，订单错误");
				}
			}else if(Integer.parseInt(paytype)==SmBaseGlobal.PayType.WeMoney.getid()){
				map=new HashMap<String, Object>();
				map.put("UserID", sid);
				List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyByIDList(map);
				if (weMoneys.size()>0) {
					weMoneys.get(0).setWeMoney(weMoneys.get(0).getWeMoney()+Double.parseDouble(money));
					int weMoneyFlad=WeMoneyService.updateWeMoney(weMoneys.get(0));
					if (weMoneyFlad>0) {
						WeMoneyRecord weMoneyRecord=new WeMoneyRecord();
						weMoneyRecord.setID(SmBaseUtil.CreateNewID());
						weMoneyRecord.setWeMoney(Double.parseDouble(money));
						weMoneyRecord.setUserID(0);
						weMoneyRecord.setType(3);//消费微米
						weMoneyRecord.setBelong(Long.parseLong(aid));
						weMoneyRecord.setReson("活动退款");
						weMoneyRecord.setFromUserID(Long.parseLong(sid));
						weMoneyRecord.setStatus(1);
						WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
						
						ActivityService.CanCelApplyActivity(Long.parseLong((aid)));//人数减去一个
						map=new HashMap<String, Object>();
						map.put("ID", applyid);
						ActivityPartService.deleteActivityPart(map);//在申请表中状态也去删掉
						moneyRecords.get(0).setRefundID(aid);//付款记录改为退款
						MoneyRecordService.refundMoneyRecord(moneyRecords.get(0));
						map=new HashMap<String, Object>();
						map.put("status", true);
						map.put("Message", "退款成功");
					}
				}
				
			}else{
				map=new HashMap<String, Object>();
				map.put("status", false);
				map.put("Message", "退款失败，订单错误");
			}
		
			
			
		}else{
			map=new HashMap<String, Object>();
			map.put("Status", false);
			map.put("Message", "未发现订单");
		}
		return map;
	} 

	@RequestMapping(value = "/getActivityList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Users user = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		user = (Users) session.getAttribute("UserName");
		String state = request.getParameter("state");
		String WeChatID = request.getParameter("WeChatID");
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
		if (searchText != null && !searchText.isEmpty()) {
			checkParammap.put("Title", searchText);
		}
		if (user.getLevel() != SmBaseGlobal.LevelStatus.SuperManage.getid() && user.getLevel() != SmBaseGlobal.LevelStatus.Manage.getid()) {
			if(WeChatID==null || (WeChatID!=null && WeChatID.isEmpty())){
				WeChatID = "0";
			}
			checkParammap.put("WeChatID", Long.parseLong(WeChatID));
		}
		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			checkParammap.put("AreaID", user.getAreaID());
		}
		List<Activity> lswe = ReadActivityService.getActivityList(checkParammap);

		int Prodcount = ReadActivityService.getActivityCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/getHotActivityList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getHotActivityList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("Hot", 1);

		List<Activity> lswe = ReadActivityService.getActivityList(checkParammap);

		int Prodcount = ReadActivityService.getActivityCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	/**
	 * 确认学生参赛
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/confirmApply", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> confirmApply(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, UnsupportedEncodingException {
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String state = request.getParameter("state");
		String aid = request.getParameter("aid");
		String stuid = request.getParameter("stuids");
		String title = request.getParameter("title");
		String[] wids = WeChat.split(",");
		String[] stuids = stuid.split(",");
		int result = 0;
		int index = 0;
		Notices noticesObj=new Notices();
		noticesObj.setID(Long.parseLong(aid));
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				if (state.equals("1")) {
					responseMap.put("Content", 1);
					NoticesController notic = new NoticesController();
					notic.addLevel(Long.parseLong(stuids[index++]), "参加活动【" + SmBaseUtil.URLDecoderString(title) + "】", noticesObj, 10,
							IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
					ActivityPartService.confirmApplyActivityPart(responseMap);
				} else {
					responseMap.put("Content", 0);
					NoticesController notic = new NoticesController();
					notic.addLevel(Long.parseLong(stuids[index++]), "取消参加活动【" + SmBaseUtil.URLDecoderString(title) + "】", noticesObj, -10,
							IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
					ActivityPartService.confirmApplyActivityPart(responseMap);
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		responseMap.put("Type", state);
		return responseMap;
	}

	@RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int result = 0;
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String state = request.getParameter("state");
		String[] wids = WeChat.split(",");
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				if (state.equals("1")) {
					ActivityService.enabledActivity(responseMap);
				} else {
					ActivityService.deleteActivity(responseMap);
				}

				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);

		return responseMap;
	}

	@RequestMapping(value = "/setTopActivity", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> setTopActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String pid = request.getParameter("pid");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Hot", 1);
		ActivityService.CanCelTopLevevActivity();
		responseMap = new HashMap<String, Object>();
		if (!pid.isEmpty()) {
			responseMap.put("ID", Long.parseLong(pid));
			responseMap.put("ModifyTime", new Date());

			responseMap.put("Hot", 1);

			ActivityService.TopLevevActivity(responseMap);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/cancelTopActivity", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> cancelTopActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String pid = request.getParameter("pid");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("Hot", 1);
		ActivityService.CanCelTopLevevActivity();
		responseMap = new HashMap<String, Object>();
		if (!pid.isEmpty()) {
			responseMap.put("ID", Long.parseLong(pid));
			responseMap.put("ModifyTime", new Date());

			responseMap.put("Hot", 0);

			ActivityService.TopLevevActivity(responseMap);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/phoneactivitylist", method = RequestMethod.GET)
	public ModelAndView phoneactivitylist(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) throws IOException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Type = request.getParameter("Type");

		if (Type.equals("1")) {
			responseMap.putAll(MakeQueryParam(request, session));
			model.addAttribute("Title", "当前活动");

		} else if (Type.equals("2")) {
			responseMap.put("IsTimeOut", 1);
			responseMap.put("Status", 1);
			model.addAttribute("Title", "往期回顾");

		} else if (Type.equals("3")) {
			responseMap.putAll(MakeQueryParam(request, session));
			model.addAttribute("Title", "我的活动");
		}
		model.addAttribute("Type", Type);
		List<Activity> lswe = ReadActivityService.getActivityList(responseMap);
		model.addAttribute("data", lswe);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaActivityList");
	}

	@RequestMapping(value = "/getPhoneActivityList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPhoneActivityList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.putAll(MakeQueryParam(request, session));
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Activity> lswe = ReadActivityService.getActivityList(responseMap);

		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/phoneSinaActivityDetail", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phoneSinaActivityDetail(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
			throws UnsupportedEncodingException, ParseException, Exception {
		String aid = request.getParameter("aid");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		List<ProdPictures> lspicture = new ArrayList<ProdPictures>();// 照片列表
		String uid = request.getParameter("uid");
		String status = request.getParameter("status");

		checkParammap.put("ProductID", aid);
		lspicture = ReadProdPicturesService.getPictureList(checkParammap);
		model.addAttribute("ImageCount", lspicture.size());
		model.addAttribute("ImageList", lspicture);
		if (lspicture.size() > 0) {
			model.addAttribute("Image", lspicture.get(0).getUrl().split(",")[0]);
		} else {
			model.addAttribute("Image", "#");
		}
		if (status == null || status.isEmpty()) {
			model.addAttribute("status", 0);
		} else {
			model.addAttribute("status", status);
		}
		
		if (aid == null || aid.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaActivityDetail");
		}
		/* 正常业务流程 */
		checkParammap.put("ID", Long.parseLong(aid));
		List<Activity> lswe = ReadActivityService.getActivityList(checkParammap);
		if (lswe.size() > 0) {
			Students user = (Students) session.getAttribute("StudentName");
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("ActivityID", lswe.get(0).getPKID());
			//int Applycount = ActivityPartService.getActivityPartCount(checkParammap);
			if (user != null) {
				checkParammap.put("StudentID", user.getID());
			}
			int Prodcount = ActivityPartService.getActivityPartCount(checkParammap);
			model.addAttribute("Activity", lswe.get(0));
			Date end = sdf.parse(lswe.get(0).getEndTime());
			Date start = new Date();
			long t = (end.getTime() - start.getTime()) / (3600 * 24 * 1000);
			if (t > 0) {
				model.addAttribute("IsEnd", 0);// 未结束
			} else {
				model.addAttribute("IsEnd", 1);// 已结束
			}
			if (Prodcount > 0 && user != null) {
				model.addAttribute("IsApply", 1);// 已报名
			} else {
				model.addAttribute("IsApply", 0);// 未报名
			}
			int ApplyLimit=lswe.get(0).getApplyLimit();
			int ApplyCount=lswe.get(0).getApplyCount();
			model.addAttribute("ApplyLimit", ApplyLimit);
			model.addAttribute("ApplyCount", ApplyCount);
			if (ApplyLimit!=0&&ApplyCount>=ApplyLimit) {
				model.addAttribute("IsApplyLimit", false);//不能报名了
			}else{
				model.addAttribute("IsApplyLimit", true);//还可以报名
			}
			
			model.addAttribute("ApplyMoney", lswe.get(0).getApplyMoney());
			model.addAttribute("PayType", lswe.get(0).getPayType());
		}
		String timeStamp = System.currentTimeMillis() + "";
		timeStamp = timeStamp.substring(0, 10);// 微信只要精确到秒
		String nonceStr = SmBaseUtil.getRandomString(32);
		model.addAttribute("timestamp",timeStamp);
		model.addAttribute("nonceStr", nonceStr);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaActivityDetail");
	}

	@RequestMapping(value = "/phoneApplyActivity", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> PhoneApplyActivity(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user = (Students) session.getAttribute("StudentName");
		String aid = request.getParameter("aid");
		String wid = request.getParameter("WID");
		String type=request.getParameter("type");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		int xbuser = 0;
		if (user==null || user.getPhone() == null || user.getPhone().isEmpty()) {
			xbuser=0;
		}
		if (xbuser <= 0) {
			responseMap.put("Status", -2);
			responseMap.put("ErrorMessage", "请先注册成为新浪小编");
			responseMap.put("returnView", SmBaseGlobal.XBLoginOrRegister);
		} else {
			checkParammap = new HashMap<String, Object>();
			String Message = "";
			checkParammap.put("Status", 1);
			if (aid != null && !aid.isEmpty()) {
				checkParammap.put("ActivityID", aid);
			}
			checkParammap.put("StudentID", user.getID());

			int Prodcount = ActivityPartService.getActivityPartCount(checkParammap);
			if (Prodcount > 0) {
				Message = "您已经报名!";
			} else {
				if (Integer.parseInt(type)==1) {
					//增肌一条付费记录
					String pirce=request.getParameter("pirce");
					String orderID=request.getParameter("orderID");
					long id=0;
					if (orderID==null||orderID=="") {
						id=SmBaseUtil.CreateNewID();
						Map<String , Object> map=new HashMap<>();
						map.put("UserID", user.getID());
						List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyByIDList(map);
						if (weMoneys.size()>0) {
							double wemoney=weMoneys.get(0).getWeMoney()-Double.parseDouble(pirce);
							if(wemoney<0){
								Message = "微米不足,报名失败!";
								responseMap.put("ErrorMessage", Message);
								responseMap.put("Status", -1);
								return responseMap;
							}else{
								weMoneys.get(0).setWeMoney(weMoneys.get(0).getWeMoney()-Double.parseDouble(pirce));
								WeMoneyService.updateWeMoney(weMoneys.get(0));
								WeMoneyRecord weMoneyRecord=new WeMoneyRecord();
								weMoneyRecord.setID(SmBaseUtil.CreateNewID());
								weMoneyRecord.setWeMoney(Double.parseDouble(pirce));
								weMoneyRecord.setUserID(user.getID());
								weMoneyRecord.setType(4);//消费微米
								weMoneyRecord.setBelong(Long.parseLong(aid));
								weMoneyRecord.setReson("活动");
								weMoneyRecord.setStatus(1);
								WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
							}
						}
					}else{
						id=Long.parseLong(orderID);
						
					}
					WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(WeChatInfoServices, wid);
					MoneyRecord MoneyRecord=new MoneyRecord();
					MoneyRecord.setID(id);
					MoneyRecord.setMoney(pirce);
					MoneyRecord.setActivityID(Long.parseLong(aid));
					MoneyRecord.setStudentID(user.getID());
					MoneyRecord.setType(SmBaseGlobal.MoneyType.Pay.getid());
					MoneyRecord.setBusinessID(weChatInfo.getID());
					MoneyRecordService.addMoneyRecord(MoneyRecord);
				}
				ActivityPart activitypart = new ActivityPart();
				activitypart.setActivityID(Long.parseLong(aid));
				activitypart.setID(SmBaseUtil.CreateNewID());
				activitypart.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				activitypart.setStudentID(user.getID());
				ActivityPartService.addActivityPart(activitypart);
				ActivityService.ApplyActivity(Long.parseLong(aid));
			}
			responseMap.put("ErrorMessage", Message);
			if (Message.isEmpty()) {
				responseMap.put("Status", 1);
			} else {
				responseMap.put("Status", -1);
			}

		}
		return responseMap;
	}

	private Map<String, Object> MakeQueryParam(HttpServletRequest req, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user = (Students) session.getAttribute("StudentName");
		String Type = req.getParameter("Type");
		if (user != null) {
			responseMap.put("AreaID", user.getAreaID());
			List<WeChatPublic> lsWeChat = ReadWeChatPublicService.getWeChatPublicList(responseMap);
			responseMap = new HashMap<String, Object>();
			if (lsWeChat.size() > 0) {
				responseMap.put("WeChatPublicID", lsWeChat.get(0).getID());
			} else {
				responseMap.put("WeChatPublicID", 0);
			}
		}

		responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		if (Type == null) {
			Type = "1";
		}
		if (Type.equals("1")) {// 当前活动
			responseMap.put("IsEffect", 1);
		} else if (Type.equals("2")) {// 往期回顾
			responseMap.put("StudentApplyed", user.getID());
			responseMap.put("IsTimeOut", SmBaseGlobal.CheckStatus.Effective.getid());
		} else if (Type.equals("3")) {// 我的进行中的活动
			responseMap.put("IsEffect", 1);// 进行中的活动
			responseMap.put("StudentApplyed", user.getID());
		}
		return responseMap;
	}

	@RequestMapping(value = "/getActivityPartList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getActivityPartList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String aid = request.getParameter("aid");
		String type = request.getParameter("type");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("ActivityID", aid);
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
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
		if (type == null || type.isEmpty() || type.equals("1")) {
			checkParammap.put("Content", 0);
		} else {
			checkParammap.put("Content", 1);
		}
		List<ActivityPart> lswe = ReadActivityPartService.getActivityPartPartList(checkParammap);
		int count = ReadActivityPartService.getActivityPartCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}

	/**
	 * 保存前台传过来的图片,多图片
	 * 
	 * @param imageIDs
	 * @param DelImages
	 * @param notices
	 * @return
	 */
	private Activity SavePicture(String imageIDs, String DelImages, Activity notices) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] imgIDs = imageIDs.split(",");

		for (String imgID : imgIDs) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("ID", imgID);
			List<ProdPictures> lspic = ProdPicturesService.getPicturesList(resultMap);
			if (lspic.size() > 0) {
				if (notices.getImageID() <= 0) {
					notices.setImageID(lspic.get(0).getID());
				}
				lspic.get(0).setProductID(notices.getID());
				lspic.get(0).setWeChatID(notices.getWeChatID());
				ProdPicturesService.updatePictures(lspic.get(0));

			}
		}
		/**
		 * 删除关联的图片
		 */
		if (DelImages != null && DelImages.length() > 0) {
			imgIDs = DelImages.split(",");

			for (String imgID : imgIDs) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("ID", imgID);
				List<ProdPictures> lspic = ProdPicturesService.getPicturesList(resultMap);
				if (lspic.size() > 0) {
					lspic.get(0).setProductID(0);
					ProdPicturesService.updatePictures(lspic.get(0));
				}

			}

		}
		return notices;
	}
	@RequestMapping(value = "/getMoneyRecordList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getMoneyRecordList(HttpServletRequest request, HttpServletResponse response, HttpSession session)  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String aid = request.getParameter("aid");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("ActivityID", aid);
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
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
		String type=request.getParameter("type");
		if (Integer.parseInt(type)!=1) {
			checkParammap.put("Type", type);
		}
		List<MoneyRecord> lswe = MoneyRecordService.getMoneyRecordList(checkParammap);
		int count = MoneyRecordService.getMoneyRecordCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", count);
		return responseMap;
	}
	
	@RequestMapping(value = "/phoneSinaWeMoneyRecharge", method = RequestMethod.GET)
	public ModelAndView phoneSinaWeMoneyRecharge(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		String timeStamp = System.currentTimeMillis() + "";
		timeStamp = timeStamp.substring(0, 10);// 微信只要精确到秒
		String nonceStr = SmBaseUtil.getRandomString(32);
		model.addAttribute("timestamp",timeStamp);
		model.addAttribute("nonceStr", nonceStr);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath+"phoneSinaWeMoneyRecharge");
	}
	
	
	@RequestMapping(value = "/phoneSummerCampApply", method = RequestMethod.GET)
	public String phoneSummerCampApply(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException, ParseException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		String isNewStudent = null;
		try{
			isNewStudent = (String)request.getSession().getAttribute("isNewStudent");
		}catch (Exception e) {
			isNewStudent = "1";
		}
		if(user==null){
			user = new Students();
			user.setCreateTime(sdf.format(new Date()));
		}
		/*int result=SmBaseUtil.compareDate(SmBaseGlobal.sdfDateTime.parse(user.getCreateTime().substring(0, 19)), SmBaseGlobal.sdfDateTime.parse("2017-06-01 00:00:00"));
		if((result==-1) && (user.getLevel()!=null &&  user.getParentName()!=null && !user.getParentName().isEmpty() && user.getPhone()!=null && !user.getPhone().isEmpty())){
			 response.sendRedirect(request.getContextPath() + "/Activity/phoneSummerCampApply_1?cid=842041063417974785");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply";
		}*/
		String cid=request.getParameter("cid");
		String buid=request.getParameter("buid");
		String WID=request.getParameter("WID");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply";
		}
		
		 
		responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", user.getID());
		responseMap.put("Sina", SmBaseUtil.Random());
		List<WeMoney>weMoneys=ReadWeMoneyService.getWeMoneyByIDList(responseMap);
		if(weMoneys.size()>0){
			Double wemoney=weMoneys.get(0).getWeMoney();
			if(weMoneys.get(0).getWeMoney()>5000 ){
				wemoney = 5000d;
			}
			model.addAttribute("MyWeMoney",weMoneys.get(0).getWeMoney());
			model.addAttribute("WeMoney",wemoney);
			model.addAttribute("WeMoneySub",(wemoney/10d));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", SmBaseGlobal.SummerCamp.OptionalCourse.getid());
		List<BaseInfo> baseInfos=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("OptionalCourse",baseInfos);
		model.addAttribute("Competition",competitions.get(0));
		if(buid==null || buid.equals("0") || buid.equals(user.getID())){
			buid="0";
		}
		model.addAttribute("buid",buid);
		
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", 1);
			 if(user.getID()!=0 && !buid.equals(String.valueOf(user.getID()))){
				 if(WID!=null && !WID.isEmpty()){
					 WID = "&WID="+WID;
				 }else{
					 WID="";
				 }
				 response.sendRedirect(request.getContextPath() + "/Activity/phoneSummerCampApply?cid="+cid + "&buid="+user.getID()+WID);
				 return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply";
			 }
		}
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply";
	}
	
	@RequestMapping(value = "/phoneSummerCampApply_1", method = RequestMethod.GET)
	public String phoneSummerCampApply_1(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException, ParseException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		String isNewStudent = (String)request.getSession().getAttribute("isNewStudent");
		if(user==null){
			user = new Students();
			user.setCreateTime(sdf.format(new Date()));
		}
		int result=SmBaseUtil.compareDate(SmBaseGlobal.sdfDateTime.parse(user.getCreateTime().substring(0, 19)), SmBaseGlobal.sdfDateTime.parse("2017-06-01 00:00:00"));
		if(true){
			
			 response.sendRedirect(request.getContextPath() + "/Activity/phoneSummerCampApply?cid=842041063417974784");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply";
		}
		String cid=request.getParameter("cid");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_1";
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("UserID", user.getID());
		responseMap.put("Sina", SmBaseUtil.Random());
		List<WeMoney>weMoneys=ReadWeMoneyService.getWeMoneyByIDList(responseMap);
		if(weMoneys.size()>0){
			Double wemoney=weMoneys.get(0).getWeMoney();
			if(weMoneys.get(0).getWeMoney()>5000 ){
				wemoney = 5000d;
			}
			model.addAttribute("MyWeMoney",weMoneys.get(0).getWeMoney());
			model.addAttribute("WeMoney",wemoney);
			model.addAttribute("WeMoneySub",(wemoney/10d));
		}
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", 1);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", SmBaseGlobal.SummerCamp.OptionalCourse.getid());
		List<BaseInfo> baseInfos=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("OptionalCourse",baseInfos);
		model.addAttribute("Competition",competitions.get(0));
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_1";
	}
	
	
	@RequestMapping(value = "/phoneSummerCampApply_2", method = RequestMethod.GET)
	public String phoneSummerCampApply_2(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user = new Students();
		}
		String cid=request.getParameter("cid");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_2";
		}
		
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		List<CompetitionApply> competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionList(responseMap);
		if (competitionAppliesFlag.size()>0) {
			 model.addAttribute("isApply", 1);
			 if(competitionAppliesFlag.get(0).getStringParamF()==null || competitionAppliesFlag.get(0).getStringParamF().isEmpty()){
				 competitionAppliesFlag.get(0).setStringParamF(String.valueOf(SmBaseUtil.Random4()));
				 CompetitionApplyService.updateCompetition(competitionAppliesFlag.get(0));
			 }
			 model.addAttribute("Apply", competitionAppliesFlag.get(0).getStringParamF());
			 responseMap=new HashMap<>();
			 responseMap.put("Type", cid);
			 responseMap.put("Sina", SmBaseUtil.Random());
			 responseMap.put("StringParamF", competitionAppliesFlag.get(0).getStringParamF());
			 int applyCount=ReadCompetitionApplyService.getCompetitionCount(responseMap);
			 model.addAttribute("applyCount", applyCount);
			 
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 105);//游泳课程
		List<BaseInfo> Swimcourse=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		responseMap.put("ParentID", 109);//班级时间
		List<BaseInfo> coursetime=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		responseMap.put("ParentID", 118);//班级时间
		List<BaseInfo> period=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("Swimcourse",Swimcourse);
		model.addAttribute("coursetime",coursetime);
		model.addAttribute("period",period);
		model.addAttribute("Competition",competitions.get(0));
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_2";
	}
	
	
	
	@RequestMapping(value = "/phoneSummerCampApply_3", method = RequestMethod.GET)
	public String phoneSummerCampApply_3(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user = new Students();
		}
		String cid=request.getParameter("cid");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_3";
		}
		
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", 1);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 122);//游泳课程
		List<BaseInfo> Swimcourse=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("Swimcourse",Swimcourse);
		model.addAttribute("Competition",competitions.get(0));
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_3";
	}
	
	@RequestMapping(value = "/phoneSummerCampApply_4", method = RequestMethod.GET)
	public String phoneSummerCampApply_4(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user = new Students();
		}
		String cid=request.getParameter("cid");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_4";
		}
		
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", 1);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 132);//游泳课程
		List<BaseInfo> Swimcourse=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("Swimcourse",Swimcourse);
		model.addAttribute("Competition",competitions.get(0));
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_4";
	}
	
	
	@RequestMapping(value = "/phoneSummerCampApply_5", method = RequestMethod.GET)
	public String phoneSummerCampApply_5(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user = new Students();
		}
		String cid=request.getParameter("cid");
		String WID=request.getParameter("WID");
		String buid=request.getParameter("buid");
		if(cid==null || cid.isEmpty()){
			cid="0";
		}
		if(buid==null || buid.equals("0")){
			buid="0";
		}
		model.addAttribute("buid",buid);
		responseMap.put("ID", cid);
		List<wtb.core.model.Competition> competitions=ReadCompetitionService.getCompetitionList(responseMap);
		if(competitions.size()<=0){
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_5";
		}
		if(user.getID()!=0 && buid.equals("0")){
			 if(WID!=null && !WID.isEmpty()){
				 WID = "&WID="+WID;
			 }else{
				 WID="";
			 }
			 model.addAttribute("Competition",competitions.get(0));
			 response.sendRedirect(request.getContextPath() + "/Activity/phoneSummerCampApply_5?cid="+cid + "&buid="+user.getID()+WID);
			 return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_5";
		 }
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", competitionAppliesFlag);
			 
		}else{
			model.addAttribute("isApply", 0);
		}
	
		model.addAttribute("Competition",competitions.get(0));
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_5";
	}
	@RequestMapping(value = "/phoneSummerCampApply_7", method = RequestMethod.GET)
	public String phoneSummerCampApply_7(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException, ParseException  {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user = new Students();
			user.setCreateTime(sdf.format(new Date()));
		}
		
		String cid="842041063417974783";
		
		
		responseMap=new HashMap<>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", cid);
		int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if (competitionAppliesFlag>0) {
			 model.addAttribute("isApply", 1);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 146);
		List<BaseInfo> baseInfos=ReadBaseInfoService.getBaseInfoListByID(responseMap);
		model.addAttribute("OptionalCourse",baseInfos);
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_7";
	}
	
	@RequestMapping(value = "/phoneSummerCampApply_6", method = RequestMethod.GET)
	public String phoneSummerCampApply_6(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneSummerCampApply_6";
	}
	
	@RequestMapping(value = "/phoneGame_1", method = RequestMethod.GET)
	public String phoneGame_1(HttpServletResponse response ,HttpServletRequest request,Model model) throws IOException  {
		String type = request.getParameter("type");
		if(type==null || type.isEmpty()){
			type="0";
		}
		model.addAttribute("type", type);
		return SmBaseGlobal.WeNewsMobileViewPath + "phoneGame_1";
	}
	
	/**
	 * 增值服务--作文批改
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/phoneTeacherReview", method = RequestMethod.GET)
	public ModelAndView phoneTeacherReview(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String PID = request.getParameter("aid");
		String NID = request.getParameter("nid");
		int isApply=0;
		responseMap.put("NID", NID);
		responseMap.put("Type", SmBaseGlobal.DealInfoType.CorrectList.getid());
		List<ApplyList>ApplyList = ReadApplyListService.getApplyListList(responseMap);
		if(ApplyList.size()>0){
			model.addAttribute("ApplyList", ApplyList.get(0));
			isApply=1;
			if(ApplyList.get(0).getStatus()==SmBaseGlobal.CheckStatus.Effective.getid()){
				isApply=2;
			}
		}
		model.addAttribute("isApply", isApply);
		responseMap = new HashMap<String, Object>();
		responseMap.put("Type", SmBaseGlobal.DealInfoType.CorrectList.getid());
		Notices Notices = ReadNoticesService.getNoticesByID(Long.parseLong(NID));
		if(Notices!=null){
			model.addAttribute("Notices", Notices);
			model.addAttribute("StudentName", Notices.getStudent());
			
		}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneTeacherReviews");
	}
	
}