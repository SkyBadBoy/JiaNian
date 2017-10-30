package wtb.core.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.PictureParam;
import wtb.core.model.Pictures;
import wtb.core.model.Region;
import wtb.core.model.WeChatBanner;
import wtb.core.model.WeChatPublic;
import wtb.smUtil.IdWorker;
import wtb.smUtil.OperateImage;
import wtb.smUtil.SmBaseGlobal;
/**
 * wtb_users
 */
@Controller
@RequestMapping("weChatbanner")
public class WeChatBannerController  extends BaseController  {


	/**
	 * 添加信息
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/addweChatbanner", method = RequestMethod.GET)
	public ModelAndView addweChatbanner(@ModelAttribute("WeChatBannerForm") WeChatBanner weChatBanner, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		String wechatID = req.getParameter("wid");
		if (wechatID != null && !wechatID.isEmpty()) {

			responseMap.put("ID", Long.parseLong(wechatID));
			List<WeChatBanner> lswb = WeChatBannerService.getWeChatBannerList(responseMap);

			if (lswb.size() > 0) {
				weChatBanner = lswb.get(0);
				model.addAttribute("WeChatGroupTitle", weChatBanner.getTitle());
				model.addAttribute("WeChatName", weChatBanner.getWeChat().getCompany());
				model.addAttribute("file", weChatBanner.getQRCodeURL().getUrl());
				model.addAttribute("Month", weChatBanner.getMonth());
				model.addAttribute("Year", weChatBanner.getYear());
				model.addAttribute("WeChatBannerForm", weChatBanner);

				
			} 
		}
		model=RegionController.getRegionParams(req,ReadRegionService,model,session,String.valueOf(weChatBanner.getAreaID()));
		Calendar now = Calendar.getInstance();  
		List<Integer> lsYear=new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lsYear.add(now.get(Calendar.YEAR)+i);
		}
		model.addAttribute("Years", lsYear);
		lsYear=new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			lsYear.add(i);
		}
		model.addAttribute("Months", lsYear);
		return new ModelAndView(SmBaseGlobal.WebViewPath+ "addweChatbanner");
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView gotosuccess(BindingResult result, HttpServletResponse response, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath+"success");
	}

	@RequestMapping(value = "/addweChatbanner", method = RequestMethod.POST)
	public ModelAndView addweChatbanner(@ModelAttribute("WeChatBannerForm") WeChatBanner weChatBanner, 
			@RequestParam("file") MultipartFile file, 
			@RequestParam(value = "Img_X", required = false) String Img_X, 
			@RequestParam(value = "Img_Y", required = false) String Img_Y, 
			@RequestParam(value = "Img_W", required = false) String Img_W, 
			@RequestParam(value = "Img_H", required = false) String Img_H, 
			@RequestParam(value = "CityAreaID", required = false) String CityAreaID, 
			BindingResult result, HttpServletRequest req, 
			HttpServletResponse response, HttpSession session, Model model)
			throws Exception {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Integer proResult = 0;
		long WeChatID = 0;
		boolean isNew = false;
		if (weChatBanner.getID() > 0) {
			WeChatID = weChatBanner.getID();
		} else {
			WeChatID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		if(CityAreaID==null || CityAreaID.isEmpty()){
			CityAreaID="0";
		}
		weChatBanner.setAreaID(CityAreaID);
		if (Long.parseLong(weChatBanner.getAreaID()) <= 0) {
			result.rejectValue("AreaID", "misFormat", "社区地区不能为空");
		} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", weChatBanner.getAreaID());
				if (responseMap.size() > 0) {
					List<Region> lswechat = RegionService.getRegionList(responseMap);
					if (lswechat.size() <= 0) {
						result.rejectValue("AreaID", "misFormat", "所选择的社区地区不存在");
					}else{
						model.addAttribute("AreaID", lswechat.get(0).getParentID());						
					}
				}
		}
		if (weChatBanner.getWeChatID() <= 0) {
			result.rejectValue("WeChatID", "misFormat", "微信公众号不能为空");
		} else {
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", weChatBanner.getWeChatID());
			if (responseMap.size() > 0) {
				List<WeChatPublic> lswechatlist = ReadWeChatPublicService.getWeChatPublicList(responseMap);

				if (lswechatlist.size() <= 0) {
					result.rejectValue("WeChatID", "misFormat", "所选择的微信公众号不存在");
				} else {
					model.addAttribute("WeChatName", lswechatlist.get(0).getCompany());
				}
			}
		}
		if (weChatBanner.getTitle().isEmpty()) {
			result.rejectValue("title", "misFormat", "标题不能为空");
		}
		if (weChatBanner.getYear() <= 0 || weChatBanner.getMonth()<=0) {
			result.rejectValue("Year", "misFormat", "过期日期不能为空");
		} else {
			responseMap = new HashMap<String, Object>();
			responseMap.put("AreaID",weChatBanner.getAreaID());
			responseMap.put("Year",weChatBanner.getYear());
			responseMap.put("Month",weChatBanner.getMonth());
			responseMap.put("Status",SmBaseGlobal.CheckStatus.Effective.getid());
			model.addAttribute("Year", weChatBanner.getYear());
			model.addAttribute("Month", weChatBanner.getMonth());
			List<WeChatBanner> lsChatBanner=WeChatBannerService.getWeChatBannerList(responseMap);
			if(lsChatBanner.size()>0){
				if(isNew){
					result.rejectValue("Year", "misFormat", "当前期间的广告已经存在");
				}
			}else{
				weChatBanner.setYear(weChatBanner.getYear());
				weChatBanner.setMonth(weChatBanner.getMonth());
				
			}
		}
		Calendar now = Calendar.getInstance();  
		if (weChatBanner.getYear()<now.get(Calendar.YEAR)) {
			result.rejectValue("Year", "misFormat", "过期日期不能小于当前日期");
		}else{
			if (weChatBanner.getMonth()<=now.get(Calendar.MONTH)) {
				result.rejectValue("Month", "misFormat", "过期日期不能小于当前日期");
			}
		}
		
		model=RegionController.getRegionParams(req,ReadRegionService,model,session,String.valueOf(weChatBanner.getAreaID()));
		List<Integer> lsYear=new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lsYear.add(now.get(Calendar.YEAR)+i);
		}
		model.addAttribute("Years", lsYear);
		lsYear=new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			lsYear.add(i);
		}
		model.addAttribute("Months", lsYear);
		PictureParam picParam=new PictureParam();
		OperateImage image = new OperateImage();
		//检测图片是否符合格式
		if (!file.isEmpty() || weChatBanner.getQRCodeURLID()>0) {
				picParam=image.getPicutreBytes(file,req.getSession().getServletContext().getRealPath("/"),null,new String[]{Img_X,Img_Y,Img_W,Img_H});
				model.addAttribute("inputImage", picParam.getFileName());
				if(picParam.getErrorMessage()!=null && !picParam.getErrorMessage().isEmpty()){
					result.rejectValue("QRCodeURL", "misFormat",picParam.getErrorMessage());
				}
		} else {
			if(weChatBanner.getQRCodeURLID()<=0){
				weChatBanner.setQRCodeURLID(0);
				result.rejectValue("QRCodeURL", "misFormat", "图片文件不能为空.");
			}
		}
		if (!result.hasErrors()) {
			/* 保存文件 begin */
				picParam.setSavefileName(image.SavePicture(req, picParam.getPrefix(),picParam.getBytes(), new String[] { Img_X, Img_Y, Img_W, Img_H }));


				PictureController pc = new PictureController();
				Pictures Pic = pc.addPicture("upload/" + picParam.getSavefileName(), picParam.getFileName(), WeChatID);
				weChatBanner.setQRCodeURLID(Pic.getID());
				PicturesService.addPictures(Pic);
				model.addAttribute("file", "upload/" + picParam.getSavefileName());
				/* 保存文件 end */

				weChatBanner.setStatus(1);
				weChatBanner.setModifyTime(new Date());
				if (isNew) {
					weChatBanner.setID(WeChatID);
					weChatBanner.setCreateTime(new Date());
					proResult = WeChatBannerService.addWeChatBanner(weChatBanner);
				} else {
					proResult = WeChatBannerService.updateWeChatBanner(weChatBanner);
				}
				if (proResult > 0) {
					model.addAttribute("content",req.getContextPath()+ "/weChatbanner/weChatbannerList");
					return new ModelAndView(SmBaseGlobal.WebViewPath+"success");
				} else {
					weChatBanner.setQRCodeURLID(0);
					result.rejectValue("AreaID", "misFormat", "保存失败,请重试");
				}

		}

		if (result.hasErrors()) {
			model.addAttribute("WeChatBannerForm", weChatBanner);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath+"addweChatbanner");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath+"addweChatbanner");
	}

	@RequestMapping(value = "/weChatbannerList", method = RequestMethod.GET)
	public ModelAndView weChatGroupList(HttpServletRequest req, HttpServletResponse response, HttpSession session, Model model) {
		Map<String, List<?>> hashMap = new HashMap<String, List<?>>();
		
		Calendar cd = Calendar.getInstance();
		model.addAttribute("Year", cd.get(Calendar.YEAR));
		model.addAttribute("Month", cd.get(Calendar.MONTH) + 1);
		Calendar now = Calendar.getInstance();  
		model=RegionController.getRegionParams(req,ReadRegionService,model,session,"");
		List<Integer> lsYear=new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			lsYear.add(now.get(Calendar.YEAR)+i);
		}
		model.addAttribute("Years", lsYear);
		lsYear=new ArrayList<Integer>();
		for (int i = 1; i < 13; i++) {
			lsYear.add(i);
		}
		model.addAttribute("Months", lsYear);
		return new ModelAndView(SmBaseGlobal.WebViewPath+"weChatbannerList", hashMap);
	}

	@RequestMapping(value = "/getweChatbannerList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatPublicList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String WeChat = request.getParameter("title");
		String Year = request.getParameter("Year");
		String Month = request.getParameter("Month");
		String AreaID = request.getParameter("AreaID");
		String state = request.getParameter("Status");
		String WeChatID = request.getParameter("WeChatID");
		
		
		String pageSize=request.getParameter("pageSize");
		String pageNumber=request.getParameter("pageNumber");
		if (pageSize != null && !pageSize.isEmpty()) {
			checkParammap.put("limit", pageSize);
		}else{
			checkParammap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
			checkParammap.put("start", pageNumber);
		}else{
			checkParammap.put("start", 0);
		}
		if (WeChat != null && !WeChat.isEmpty()) {
			checkParammap.put("title", WeChat);
		}
		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		}else{
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
		List<WeChatBanner> lswe = WeChatBannerService.getWeChatBannerList(checkParammap);
		int lswecount = WeChatBannerService.getWeChatBannerCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("total", lswe);
		responseMap.put("Status", lswecount);
		return responseMap;
	}

	@RequestMapping(value = "/deleteweChatbanner", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatGroup(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> responseMap=null;
		String WeChat = request.getParameter("wid");
		String state = request.getParameter("state");
		String[] wids = WeChat.split(",");
		int result = 0;
		
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				if (state.equals("1")) {
					WeChatBannerService.enabledWeChatBanner(responseMap);
				} else {
					WeChatBannerService.deleteWeChatBanner(responseMap);
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
}