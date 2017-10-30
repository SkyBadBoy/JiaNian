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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.core.model.AccessActive;

@Controller
@RequestMapping("AccessActivity")
public class AccessActivityController extends BaseController {


	@RequestMapping(value = "/AccessActive", method = RequestMethod.GET)
	public ModelAndView AccessActiveList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "AccessActiveList");
	}

	@RequestMapping(value = "/getAccessActive", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAccessActiveList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<AccessActive> lswe = ReadAccessActiveService.getAccessActiveList(checkParammap);
		
		long Prodcount = ReadAccessActiveService.getAccessActiveCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/sendAccessActive", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String requestUrl = request.getParameter("url");
		SmBaseUtil.addAccessActivity(request, requestUrl, AccessActiveService);
		return responseMap;
	}

	@RequestMapping(value = "/deleteAccessActive", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteAccessActive(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String[] wids = pid.split(",");
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				AccessActiveService.deleteAccessActive(responseMap);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
}
