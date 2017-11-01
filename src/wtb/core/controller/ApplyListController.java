package wtb.core.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xml.internal.security.Init;

import wtb.core.model.ApplyList;
import wtb.core.model.DealInfo;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.Users;
import wtb.smUtil.IdWorker;
import wtb.smUtil.ResultUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.VerifyCode;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/ApplyList")
public class ApplyListController extends BaseController {

	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年10月30日 下午9:15:34 
		 * @Details 主界面报名套餐
	 */
	@RequestMapping(value = "/applyCombo", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	Map<String, Object> applyCombo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		String UserName = request.getParameter("UserName");
		String UserPhone=request.getParameter("UserPhone");
		String Course=request.getParameter("Course");
		String Content=request.getParameter("Content");
		String ApplyType=request.getParameter("ApplyType");
		ApplyList applyList=new ApplyList();
		if (!SmBaseUtil.CheckIsNull(UserName)) {
			return ResultUtil.otherError("请输入你的名字~");
		}else {
			applyList.setUserName(UserName);
		}
		
		if (!SmBaseUtil.CheckIsNull(UserPhone)) {
			return ResultUtil.otherError("请输入你的手机号~");
		}else {
			Map<String , Object> map=new HashMap<>();
			map.put("UserPhone", UserPhone);
			map.put("Rand", SmBaseUtil.Random());
			List<ApplyList> applyLists= ReadApplyListService.getApplyListList(map);
			if (applyLists.size()>0) {
				return ResultUtil.otherError("你已报名，无需重复报名");
			}else {
				applyList.setUserPhone(UserPhone);
			}
			
		}
		
		if (SmBaseUtil.CheckIsNull(Content)) {
			applyList.setContent(Content);
		}else {
			applyList.setContent("");
		}
		if (Integer.parseInt(Course)==0) {
			return ResultUtil.otherError("请选择报班课程~");
		}
		applyList.setApplyType(Integer.parseInt(ApplyType));
		applyList.setID(SmBaseUtil.CreateNewID());
		applyList.setCourse(Course);
		applyList.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		applyList.setType(SmBaseGlobal.ApplyType.NoDispose.getid());
		applyList.setFeedback("");
		int i=ApplyListService.addApplyList(applyList);
		if (i>0) {
			return ResultUtil.resultMap(0, "报名成功，稍后我们将会联系你", null);
		}else {
			return ResultUtil.otherError("网络异常，请稍后重试~");
		}
	}
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年10月31日 上午10:01:42 
		 * @Details 报名申请列表
	 */
	@RequestMapping(value = "/ApplyListList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ApplyList");
	}
	
	@RequestMapping(value = "/phoneAreaManageApply", method = RequestMethod.GET)
	public ModelAndView phoneAreaManageApply(HttpServletResponse response, @ModelAttribute("ApplyListForm") ApplyList ApplyList, BindingResult result,
			HttpServletRequest req, HttpSession session, Model model) {
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, null);
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneAreaManageApply");

	}

	@RequestMapping(value = "/phoneAreaManageApply", method = RequestMethod.POST)
	public ModelAndView phoneAreaManageApplyResult(@ModelAttribute("ApplyListForm") ApplyList ApplyList, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {

		Integer proResult = 0;
		long ProdID = new IdWorker(1, 0).nextId();
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, null);

		if (ApplyList.getUserName() == null || ApplyList.getUserName().isEmpty()) {
			result.rejectValue("UserName", "misFormat", "您的姓名不能为空");
		}
		if (ApplyList.getUserPhone() == null || ApplyList.getUserPhone().isEmpty()) {
			result.rejectValue("UserPhone", "misFormat", "您的手机号码不能为空");
		}
		if (ApplyList.getContent().isEmpty()) {
			result.rejectValue("Content", "misFormat", "申请内容不能为空");
		}
		if (!result.hasErrors()) {
			/* 保存文件 begin */
			ApplyList.setStatus(SmBaseGlobal.CheckStatus.UnActivate.getid());
			ApplyList.setID(ProdID);
			proResult = ApplyListService.addApplyList(ApplyList);

			if (proResult > 0) {
				model.addAttribute("ApplyListForm", ApplyList);
				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneApplyAuthorize");
			} else {
				ApplyList.setID(0);
				result.rejectValue("UserName", "misFormat", "申请失败,请重试");
			}

		}

		if (result.hasErrors()) {
			model.addAttribute("ApplyListForm", ApplyList);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneAreaManageApply");
		}
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneAreaManageApply");
	}

//	@RequestMapping(value = "/phoneApplyAuthorize", method = RequestMethod.POST)
//	public ModelAndView phoneApplyAuthorize(@ModelAttribute("ApplyListForm") ApplyList ApplyList, @RequestParam("AuthCode") String AuthCode,
//			BindingResult result, HttpServletResponse response, HttpSession session, Model model) {
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		String sessionAuthCode = session.getAttribute("AuthenCode").toString();
//		model.addAttribute("ApplyPhone", ApplyList.getUserPhone());
//		model.addAttribute("ApplyID", ApplyList.getID());
//		if (!sessionAuthCode.equals(AuthCode)) {
//			result.rejectValue("ID", "misFormat", "验证码不正确，请重新输入验证码！");
//		} else {
//			responseMap.put("ID", ApplyList.getID());
//			List<ApplyList> lsapply = ReadApplyListService.getApplyListList(responseMap);
//			if (lsapply.size() > 0) {
//				ApplyList applyInfo = lsapply.get(0);
//				/* 获取详细省市 */
//				String Address = "";
//				responseMap = new HashMap<String, Object>();
//				responseMap.put("ID", applyInfo.getAreaID());
//				List<Region> lswechat = RegionService.getRegionList(responseMap);
//				Address += lswechat.get(0).getName();
//				// 获取城市
//				responseMap = new HashMap<String, Object>();
//				responseMap.put("ID", lswechat.get(0).getParentID());
//				List<Region> lsRegion = RegionService.getRegionList(responseMap);
//				Address = lsRegion.get(0).getName() + Address;
//				// 获取省
//				responseMap = new HashMap<String, Object>();
//				responseMap.put("ID", lsRegion.get(0).getParentID());
//				lsRegion = RegionService.getRegionList(responseMap);
//				Address = lsRegion.get(0).getName() + Address;
//
//				applyInfo.setArea(Address);
//				applyInfo.setStatus(SmBaseGlobal.CheckStatus.Apply.getid());
//				int proResult = ApplyListService.updateApplyList(applyInfo);
//
//				MessageController mesage = new MessageController();
//				String Title = "【" + applyInfo.getUserName() + "】申请成为【" + applyInfo.getArea() + "】区域代理！";
//				mesage.SendMessageToAdminWaitWork(ReadUsersService, MessagesService, applyInfo, Title, SmBaseGlobal.NewType.Applys.getid());
//				if (proResult > 0) {
//					model.addAttribute("SuccessType", "AreaManage");
//					return new ModelAndView(SmBaseGlobal.MobileViewPath + "applysuccess");
//				} else {
//					result.rejectValue("ID", "misFormat", "验证失败,请重试");
//				}
//			}
//		}
//		model.addAttribute("ApplyListForm", ApplyList);
//		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneApplyAuthorize");
//	}



	@RequestMapping(value = "/getApplyListList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getApplyListList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String Title = request.getParameter("UserName");
		if (Title != null && !Title.isEmpty()) {
			Title =SmBaseUtil.URLDecoderString(Title);
		}
		String state = request.getParameter("Status");
		String ApplyID = request.getParameter("ApplyID");
		String Type = request.getParameter("Type");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		if (state == null) {
			checkParammap.put("NoEqStatus", 1);
		} else {
			checkParammap.put("Status", state);
		}
		if (Title != null && !Title.isEmpty()) {
			checkParammap.put("check", Title);
		}
		if (Type != null && !Type.isEmpty()) {
			checkParammap.put("Type", Type);
		}

			checkParammap.put("Rand", SmBaseUtil.Random());
		
		
		if (ApplyID != null && !ApplyID.isEmpty()) {
			checkParammap.put("ID", ApplyID);
		} // 消息需要添加是否已读
		responseMap = new HashMap<String, Object>();
		int Prodcount = ReadApplyListService.getApplyListCount(checkParammap);
		List<ApplyList> lswe = ReadApplyListService.getApplyListList(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	
	@RequestMapping(value = "/ModifyApplyResult", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> ModifyApplyResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users userID=(Users) session.getAttribute("UserName");
		String WeChat = request.getParameter("pid");
		String type=request.getParameter("Type");
		String Content =  SmBaseUtil.URLDecoderString(request.getParameter("Content"));
		int result = 0;
		if (!WeChat.isEmpty()) {
			responseMap.put("ID", WeChat);
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			List<ApplyList> applys = ReadApplyListService.getApplyListList(responseMap);
			if (applys.size() > 0) {
				ApplyList apply = applys.get(0);
				apply.setType(Integer.parseInt(type));
				apply.setFeedback(Content);
				apply.setUserID(userID.getID());
				result = ApplyListService.updateApplyList(apply);
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", result);
		return responseMap;
	}
	
	
	@RequestMapping(value = "/ComfrimApplyResult", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> ComfrimApplyResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String WeChat = request.getParameter("pid");
	
		int result = 0;
		
		if (!WeChat.isEmpty()) {
			String[] pids=WeChat.split(",");
			for (String string : pids) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", string);
				result += ApplyListService.enabledApplyList(responseMap);
				ApplyList applyLists=ReadApplyListService.getApplyListByID(Long.parseLong(string));
				if(applyLists!=null){
					//Notices Notices=ReadNoticesService.getNoticesByID(Long.parseLong(applyLists.getNID()));
//					if(Notices!=null){
//						VerifyCode.sharedInstance().sendWeNewCorrectingMessage(applyLists.getUserPhone(), applyLists.getUserName(), "【"+ Notices.getTitle() +"】", request.getServletContext());
//					}
					
				}
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", result);
		return responseMap;
	}
	
	
	
	@RequestMapping(value = "/ApplyResult", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> ApplyResult(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		String WeChat = request.getParameter("pid");
		String Content = request.getParameter("Content");
		if (Content != null && !Content.isEmpty()) {
			Content = SmBaseUtil.URLDecoderString(Content);
		}
		String Status = request.getParameter("Status");// 1为通过 -1为不通过
		int result = 0;
		if (!WeChat.isEmpty()) {
			responseMap.put("ID", WeChat);
			List<ApplyList> applys = ReadApplyListService.getApplyListList(responseMap);
			if (applys.size() > 0) {
				ApplyList apply = applys.get(0);
//				apply.setCheckerID(user.getID());
//				apply.setCheckResult(Content);
				apply.setModifyTime(sdf.format(new Date()));
				if (Status != null && Status.equals("1")) {
					apply.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				} else {
					apply.setStatus(SmBaseGlobal.CheckStatus.NotPass.getid());
				}
				result = ApplyListService.updateApplyList(apply);

				DealInfo dealinfo = new DealInfo();
				if (Status != null && Status.equals("1")) {
					dealinfo.setResultStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				} else {
					dealinfo.setResultStatus(SmBaseGlobal.CheckStatus.NotPass.getid());
				}
				dealinfo.setCheckerID(user.getID());
				dealinfo.setCreateTime(sdf.format(new Date()));
				dealinfo.setID(new IdWorker(1, 0).nextId());
				dealinfo.setParentID(apply.getID());
				dealinfo.setResult(Content);
				dealinfo.setType(SmBaseGlobal.DealInfoType.ApplyList.getid());
				result = DealInfoService.addDealInfo(dealinfo);
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", result);
		return responseMap;
	}
	
	
}