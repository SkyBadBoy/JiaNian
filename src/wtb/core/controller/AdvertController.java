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

import read.core.service.ReadAdvertService;
import wtb.smUtil.ResultUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.core.model.Advert;

@Controller
@RequestMapping("Advert")
public class AdvertController extends BaseController {


	@RequestMapping(value = "/AdvertList", method = RequestMethod.GET)
	public ModelAndView AdvertList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "AdvertList");
	}

	@RequestMapping(value = "/getAdvert", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getAdvertList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		String searchText = request.getParameter("searchText");
		
		if(searchText!=null && !searchText.isEmpty()){
			checkParammap.put(Advert.attributeTitle, searchText);
		}
		checkParammap.put("Sina", SmBaseUtil.Random());
		List<Advert> lswe = ReadAdvertService.getAdvertList(checkParammap);
		
		long Prodcount = ReadAdvertService.getAdvertCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/addAdvert", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pkid = request.getParameter("pid");
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		String title = request.getParameter("title");
		String type = request.getParameter("type");
		String url = request.getParameter("url");
		String index = request.getParameter("index");
		if(!SmBaseUtil.CheckIsNull(index)){
			index = "0";
		}
//		if(!SmBaseUtil.CheckIsNull(title)){
//			return ResultUtil.sharedInstance().otherError("广告标题不能为空");
//		}
//		if(!SmBaseUtil.CheckIsNull(pkid)){
//			return ResultUtil.sharedInstance().otherError("请选择要修改的记录");
//		}
//		if(!SmBaseUtil.CheckIsNull(begin_time)){
//			return ResultUtil.sharedInstance().otherError("开始时间不能为空");
//		}
//		if(!SmBaseUtil.CheckIsNull(end_time)){
//			return ResultUtil.sharedInstance().otherError("结束时间不能为空");
//		}
		
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put(Advert.attributeID, pkid);
		List<Advert> lswe = ReadAdvertService.getAdvertList(checkParammap);
		if(lswe.size()>0){
			lswe.get(0).setBegin_time(begin_time);
			lswe.get(0).setEnd_time(end_time);
			lswe.get(0).setUrl(url);
			lswe.get(0).setIndex(Integer.parseInt(index));
			lswe.get(0).setTitle(title);
			lswe.get(0).setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			lswe.get(0).setType(Integer.parseInt(type));
			int result=AdvertService.updateAdvert(lswe.get(0));
			//checkParammap = ResultUtil.sharedInstance().handleCorrect(result);
		}else{
			//checkParammap = ResultUtil.sharedInstance().otherError("更新失败，请刷新页面后重试");
		}
		
		//SmBaseUtil.addAdvert(request, requestUrl, AdvertService);
		return checkParammap;
	}

	@RequestMapping(value = "/deleteAdvert", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteAdvert(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String[] wids = pid.split(",");
		String status = request.getParameter("status");
		if(status==null || status.isEmpty()){
			status = String.valueOf(SmBaseGlobal.CheckStatus.Apply.getid());
		}
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put(Advert.attributeID, Long.parseLong(id));
				responseMap.put(Advert.attributeStatus, status);
				AdvertService.deleteAdvert(responseMap);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
}
