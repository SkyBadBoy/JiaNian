package wtb.core.controller;

import java.util.ArrayList;
import java.util.Calendar;
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

import wtb.core.model.PaidListing;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/PaidListing")
public class PaidListingController extends BaseController {
	
	@RequestMapping(value = "/addPaidListing", method = RequestMethod.GET)
	public ModelAndView WeChatPublic(@ModelAttribute("PaidListingForm") PaidListing PaidListing, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {
		Calendar cd = Calendar.getInstance();
		String Month = req.getParameter("Month");
		String Year = req.getParameter("Year");
		if (Month == null || Month.isEmpty()) {
			Month = String.valueOf(cd.get(Calendar.MONTH) + 1);
		}
		if (Year == null || Year.isEmpty()) {
			Year = String.valueOf(cd.get(Calendar.YEAR));
		}
		model.addAttribute("Year", Year);
		model.addAttribute("Month", Month);
		model.addAttribute("AreaID", 0);
		Calendar now = Calendar.getInstance();
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(PaidListing.getAreaID()));
		List<Integer> lsYear = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lsYear.add(now.get(Calendar.YEAR) + i);
		}
		model.addAttribute("Years", lsYear);
		lsYear = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			lsYear.add(i);
		}
		model.addAttribute("Months", lsYear);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPaidListing");
	}

	@RequestMapping(value = "/addPaidListing", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("PaidListingForm") PaidListing PaidListing, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {
		Integer proResult = 0;
		boolean isNew = false;
		long ProdID = 0;
		if (PaidListing.getID() > 0) {
			ProdID = PaidListing.getID();
		} else {
			ProdID = new IdWorker(1, 0).nextId();
			isNew = true;
		}

		if (PaidListing.getYear() <= 0) {
			result.rejectValue("Year", "misFormat", "年份不能为空");
		}
		if (PaidListing.getMonth() <= 0) {
			result.rejectValue("Month", "misFormat", "月份不能为空");
		}
		if (Long.parseLong(PaidListing.getAreaID()) <= 0) {
			result.rejectValue("AreaID", "misFormat", "地区能为空");
		}
		if (PaidListing.getWeChatID() <= 0) {
			result.rejectValue("WeChatID", "misFormat", "公众号不能为空");
		}

		if (!result.hasErrors()) {
			/* 保存文件 begin */
			/* 保存文件 end */
			PaidListing.setStatus(1);
			PaidListing.setModifyTime(new Date());

			if (isNew) {
				PaidListing.setID(ProdID);
				PaidListing.setCreateTime(new Date());
				proResult = PaidListingService.addPaidListing(PaidListing);
			} else {
				proResult = PaidListingService.updatePaidListing(PaidListing);
			}
			if (proResult > 0) {
				model.addAttribute("content", req.getContextPath() + "/PaidListing/PaidListingList?WeChatID=" + String.valueOf(PaidListing.getWeChatID()));
				return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
			} else {
				result.rejectValue("Title", "misFormat", "保存失败,请重试");
			}

		}
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(PaidListing.getAreaID()));
		if (result.hasErrors()) {
			model.addAttribute("PaidListingForm", PaidListing);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addPaidListing");
		}

		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPaidListing");
	}

	@RequestMapping(value = "/PaidListingList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		Calendar cd = Calendar.getInstance();
		model.addAttribute("Year", cd.get(Calendar.YEAR));
		model.addAttribute("Month", cd.get(Calendar.MONTH) + 1);
		Calendar now = Calendar.getInstance();
		List<Integer> lsYear = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lsYear.add(now.get(Calendar.YEAR) + i);
		}
		model.addAttribute("Years", lsYear);
		lsYear = new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			lsYear.add(i);
		}
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, "");
		model.addAttribute("Months", lsYear);

		return new ModelAndView(SmBaseGlobal.WebViewPath + "PaidListingList");
	}

	@RequestMapping(value = "/getPaidListingList", method = RequestMethod.GET)
	public @ResponseBody
	List<PaidListing> getPaidListingList(HttpServletRequest request, HttpServletResponse response) {
		List<PaidListing> lswe = null;

		String Year = request.getParameter("Year");
		String Month = request.getParameter("Month");
		String AreaID = request.getParameter("AreaID");
		String state = request.getParameter("Status");
		String WeChatID = request.getParameter("WeChatID");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
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
		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		} else {
			checkParammap.put("Status", state);
		}
		Calendar cd = Calendar.getInstance();
		if (Year != null && !Year.isEmpty()) {
			checkParammap.put("Year", Year);
		} else {
			checkParammap.put("Year", cd.get(Calendar.YEAR));
		}
		if (Month != null && !Month.isEmpty()) {
			checkParammap.put("Month", Month);
		} else {
			checkParammap.put("Month", cd.get(Calendar.MONTH) + 1);
		}
		if (AreaID != null && !AreaID.isEmpty()) {
			checkParammap.put("AreaID", AreaID);
		}
		if (WeChatID != null && !WeChatID.isEmpty()) {
			checkParammap.put("WeChatID", WeChatID);
		}
		lswe = PaidListingService.getPaidListingList(checkParammap);
		responseMap.put("data", lswe);
		return lswe;
	}

	@RequestMapping(value = "/deletePaidListing", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String state = request.getParameter("state");
		String[] pids = pid.split(",");
		int result = 0;

		for (String id : pids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				if (state.equals("1")) {
					PaidListingService.enabledPaidListing(responseMap);
				} else {
					PaidListingService.deletePaidListing(responseMap);
				}

				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/levelPaidListing", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> levelPaidListing(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String pid = request.getParameter("pid");
		String amount = request.getParameter("amount");
		int result = 0;

		if (!pid.isEmpty()) {
			responseMap.put("ID", Long.parseLong(pid));
			responseMap.put("ModifyTime", new Date());
			responseMap.put("Amount", Double.parseDouble(amount));
			PaidListingService.UpLevevProduct(responseMap);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/setTopPaidListing", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> setTopPaidListing(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String pid = request.getParameter("pid");
		String state = request.getParameter("state");
		int result = 0;

		if (!pid.isEmpty()) {
			responseMap.put("ID", Long.parseLong(pid));
			responseMap.put("ModifyTime", new Date());
			if (state.equals("1")) {
				responseMap.put("IsTop", 1);
			} else {
				responseMap.put("IsTop", 0);
			}
			PaidListingService.TopLevevProduct(responseMap);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
}