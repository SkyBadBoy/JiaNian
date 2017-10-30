package wtb.core.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.Permissions;
import wtb.core.model.PictureParam;
import wtb.core.model.Pictures;
import wtb.core.model.Users;
import wtb.core.model.WeChatPublic;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.OperateImage;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("weChatPublic")
public class WeChatPublicController extends BaseController {

	/**
	 * 添加信息
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/addWeChatPublic", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("WeChatPublicForm") WeChatPublic weChatPublic, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String wechatID = req.getParameter("wid");
		if (wechatID != null && !wechatID.isEmpty()) {

			responseMap.put("ID", Long.parseLong(wechatID));
			List<WeChatPublic> lswp = ReadWeChatPublicService.getWeChatPublicList(responseMap);
			if (lswp.size() > 0) {
				weChatPublic = lswp.get(0);
				if (weChatPublic.getQRCodeURL() != null && weChatPublic.getQRCodeURL().getUrl()!=null) {
					model.addAttribute("QRCodeURL", weChatPublic.getQRCodeURL().getUrl().split(",")[0]);
				}
				if (weChatPublic.getBanner() != null &&  weChatPublic.getBanner().getUrl()!=null) {
					model.addAttribute("Banner", weChatPublic.getBanner().getUrl().split(",")[0]);
				}
				if (weChatPublic.getLogo() != null && weChatPublic.getLogo().getUrl()!=null) {
					model.addAttribute("Logo", weChatPublic.getLogo().getUrl().split(",")[0]);
				}
				if (weChatPublic.getLat() != null && !weChatPublic.getLat().isEmpty() && weChatPublic.getLng() != null && !weChatPublic.getLng().isEmpty()) {
					model.addAttribute("Latlng", "经度：" + weChatPublic.getLat() + "，纬度：" + weChatPublic.getLng());
				}
				Status = lswp.get(0).getStatus();
			} else {
				Status = 404;
			}
		}
		if (Status == 404) {
			weChatPublic = new WeChatPublic();
			weChatPublic.setPublicType(1);
			weChatPublic.setRegisterSubject(3);
		}
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatPublic.getAreaID()));
		model.addAttribute("WeChatPublicForm", weChatPublic);
		model.addAttribute("Status", Status);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addWeChatPublic");
	}

	@RequestMapping(value = "/PhoneBusinessApply", method = RequestMethod.GET)
	public ModelAndView PhoneBusinessApply(@ModelAttribute("WeChatPublicForm") WeChatPublic weChatPublic, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatPublic.getAreaID()));
		model.addAttribute("WeChatPublicForm", weChatPublic);
		model.addAttribute("Status", Status);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "PhoneBusinessApply");
	}

	@RequestMapping(value = "/PhoneBusinessApply", method = RequestMethod.POST)
	public ModelAndView PhoneBusinessApplyPost(@ModelAttribute("WeChatPublicForm") WeChatPublic weChatPublic,
			@RequestParam("QRCodeURLImage") MultipartFile QRCodeURLImgfile, @RequestParam("UnitAreaID") String AreaID, BindingResult result,
			HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatPublic.getAreaID()));
		Integer proResult = 0;
		long WeChatID = 0;
		Users UnActivityUser = null;
		boolean isNew = true;
		WeChatID = new IdWorker(1, 0).nextId();
		String name = "";
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 1);
		model.addAttribute("Province", ReadRegionService.getRegionList(responseMap));
		if (weChatPublic.getWeChat().isEmpty()) {
			result.rejectValue("WeChat", "misFormat", "微信公众号不能为空");
			result.rejectValue("ID", "misFormat", "微信公众号不能为空");

		}
		if (weChatPublic.getCompany().isEmpty()) {
			result.rejectValue("Company", "misFormat", "公司名称不能为空");
			result.rejectValue("ID", "misFormat", "公司名称不能为空");
		} else {
			name = weChatPublic.getCompany();
		}

		if (weChatPublic.getWebSiteURL() == null || weChatPublic.getWebSiteURL().isEmpty()) {
			weChatPublic.setWebSiteURL("#");
		}
		if(AreaID!=null && !AreaID.isEmpty()){
			weChatPublic.setAreaID(AreaID);
		}
		responseMap.put("WeChat", weChatPublic.getWeChat());
		if (responseMap.size() > 0) {
			List<WeChatPublic> lswechat = ReadWeChatPublicService.getWeChatPublicList(responseMap);

			if (lswechat.size() > 0) {

				responseMap = new HashMap<String, Object>();
				responseMap.put("WeChatID", lswechat.get(0).getID());
				responseMap.put("NoQueryStatus", SmBaseGlobal.CheckStatus.Disabled.getid());
				List<Users> lsUser = ReadUsersService.getUsersList(responseMap);
				if (lsUser.size() > 0 && lsUser.get(0).getStatus() == SmBaseGlobal.CheckStatus.Effective.getid()) {
					result.rejectValue("WeChat", "misFormat", "微信公众号已经存在");
					result.rejectValue("ID", "misFormat", "微信公众号已经存在");
				} else if (lsUser.size() > 0 && lsUser.get(0).getStatus() == SmBaseGlobal.CheckStatus.UnActivate.getid()) {
					UnActivityUser = lsUser.get(0);
				}
				if (!result.hasErrors()) {
					weChatPublic = lswechat.get(0);
				}
				isNew = false;
			}
		}

		PictureParam picParam = new PictureParam();
		OperateImage image = new OperateImage();
		if (!QRCodeURLImgfile.isEmpty() && weChatPublic.getQRCodeURLID() == 0) {
			try {

				picParam = image.getPicutreBytes(QRCodeURLImgfile, req.getSession().getServletContext().getRealPath("/"), null, new String[] {});
				model.addAttribute("QRCodeURL", picParam.getFileName());
				if (picParam.getErrorMessage() != null && !picParam.getErrorMessage().isEmpty()) {
					result.rejectValue("QRCodeURL", "misFormat", picParam.getErrorMessage());
					result.rejectValue("ID", "misFormat", picParam.getErrorMessage());
				}

			} catch (Exception e) {
				result.rejectValue("QRCodeURL", "misFormat", "二维码图片文件 " + name + " 上传失败=> " + e.getMessage());
				result.rejectValue("ID", "misFormat", "二维码图片文件 " + name + " 上传失败=> " + e.getMessage());
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}
		} else {
			if (QRCodeURLImgfile.isEmpty() && weChatPublic.getQRCodeURLID() == 0) {
				result.rejectValue("QRCodeURL", "misFormat", "二维码图片不能为空.");
				result.rejectValue("ID", "misFormat", "二维码图片不能为空.");
			}
		}

		if (weChatPublic.getLat() != null && !weChatPublic.getLat().isEmpty() && weChatPublic.getLng() != null && !weChatPublic.getLng().isEmpty()) {
			model.addAttribute("Latlng", "经度：" + weChatPublic.getLat() + "，纬度：" + weChatPublic.getLng());
		}

		if (!result.hasErrors()) {
			/* 保存文件 begin */
			try {
				if (weChatPublic.getQRCodeURLID() == 0) {
					// 二维码
					Pictures PicQrCode = new Pictures();
					PicQrCode = SavePicture(picParam.getSavefileName(), picParam.getPrefix(), picParam.getFileName(), req, WeChatID, picParam.getBytes(), null);
					if (PicQrCode != null) {
						weChatPublic.setQRCodeURLID(PicQrCode.getID());
						model.addAttribute("QRCodeURL", PicQrCode.getUrl());

					}
				}

				/* 保存文件 end */
				if (isNew) {
					weChatPublic.setID(WeChatID);
					weChatPublic.setStatus(1);
					weChatPublic.setAreaID(AreaID);
					proResult = WeChatPublicService.addWeChatPublic(weChatPublic);
				} else {
					proResult = WeChatPublicService.updateWeChatPublic(weChatPublic);
				}
				if (UnActivityUser == null) {
					Users users = new Users();
					users.setLoginName(weChatPublic.getWeChat());
					users.setPassWord(SmBaseUtil.MD5("123456"));
					users.setCreateTime(new Date());
					users.setModifyTime(new Date());
					users.setStatus(SmBaseGlobal.CheckStatus.UnActivate.getid());
					users.setID(new IdWorker(1, 0).nextId());
					users.setImageID(0);
					users.setLevel(SmBaseGlobal.LevelStatus.ParsonManage.getid());
					users.setPhone(weChatPublic.getContactPhone());
					users.setPerson(weChatPublic.getContactPerson());
					users.setWeChatID(weChatPublic.getID());
					users.setAreaID(Long.parseLong(weChatPublic.getAreaID()));
					UsersService.addUsers(users);
				}
				if (proResult > 0) {
					Users user = new Users();
					user.setAreaID(Long.parseLong(weChatPublic.getAreaID()));
					user.setWeChatID(weChatPublic.getID());
					user.setPhone(weChatPublic.getContactPhone());
					model.addAttribute("usersForm", user);
					return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "AuthenticationUser");

				} else {
					result.rejectValue("ID", "misFormat", "申请失败,请重试");
				}
			} catch (Exception e) {
				result.rejectValue("QRCodeURL", "misFormat", "申请失败 " + name + "=> " + e.getMessage());
				result.rejectValue("ID", "misFormat", "申请失败 " + name + "=> " + e.getMessage());
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("WeChatPublicForm", weChatPublic);// 把accountVo对象返回到页面，这样不至于表单被清空了
		}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "PhoneBusinessApply");
	}

	@RequestMapping(value = "/AuthenticationUser", method = RequestMethod.GET)
	public ModelAndView AuthenticationUser(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session,
			Model model, HttpServletRequest req) {
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "AuthenticationUser");
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView gotosuccess(BindingResult result, HttpServletResponse response, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
	}

	@RequestMapping(value = "/applysuccess", method = RequestMethod.GET)
	public ModelAndView gotoapplysuccess(BindingResult result, HttpServletResponse response, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "applysuccess");
	}

	@RequestMapping(value = "/addWeChatPublic", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("WeChatPublicForm") WeChatPublic weChatPublic, @RequestParam("UnitAreaID") String AreaID, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Integer proResult = 0;
		long WeChatID = 0;
		boolean isNew = false;
		if (weChatPublic.getID() > 0) {
			WeChatID = weChatPublic.getID();
		} else {
			WeChatID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		String name = "";

		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 1);
		model.addAttribute("Province", ReadRegionService.getRegionList(responseMap));
		if (weChatPublic.getWeChat().isEmpty()) {
			result.rejectValue("WeChat", "misFormat", "微信公众号不能为空");

		}
		if (weChatPublic.getCompany().isEmpty()) {
			result.rejectValue("Company", "misFormat", "公司名称不能为空");

		} else {
			name = weChatPublic.getCompany();
		}

		if (isNew) {
			responseMap.put("WeChat", weChatPublic.getWeChat());
			if (responseMap.size() > 0) {
				List<WeChatPublic> lswechat = ReadWeChatPublicService.getWeChatPublicList(responseMap);
				if (lswechat.size() > 0) {
					result.rejectValue("WeChat", "misFormat", "微信公众号已经存在");
				}
			}
		}

		if (weChatPublic.getLat() != null && !weChatPublic.getLat().isEmpty() && weChatPublic.getLng() != null && !weChatPublic.getLng().isEmpty()) {
			model.addAttribute("Latlng", "经度：" + weChatPublic.getLat() + "，纬度：" + weChatPublic.getLng());
		}
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatPublic.getAreaID()));
		if (!result.hasErrors()) {
			/* 保存文件 begin */
			try {
				/* 保存文件 end */
				model.addAttribute("Latlng", "经度：" + weChatPublic.getLat() + "，纬度：" + weChatPublic.getLng());
				weChatPublic.setAreaID(AreaID);
				if (isNew) {
					weChatPublic.setID(WeChatID);
					weChatPublic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					proResult = WeChatPublicService.addWeChatPublic(weChatPublic);
				} else {
					proResult = WeChatPublicService.updateWeChatPublic(weChatPublic);
				}
				if (proResult > 0) {
					model.addAttribute("content", req.getContextPath() + "/weChatPublic/weChatPublicList");
					return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
				} else {
					result.rejectValue("ID", "misFormat", "保存失败,请重试");
				}
			} catch (Exception e) {
				result.rejectValue("QRCodeURL", "misFormat", "保存失败 " + name + "=> " + e.getMessage());

				e.printStackTrace();
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}
		}

		if (result.hasErrors()) {
			model.addAttribute("WeChatPublicForm", weChatPublic);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addWeChatPublic");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addWeChatPublic");
	}

	private Pictures SavePicture(String SavefileName, String prefix, String fileName, HttpServletRequest req, long WeChatID, byte[] bytes, String Image_option)
			throws Exception {
		OperateImage image = new OperateImage();
		SavefileName = image.SavePicture(req, prefix, bytes, Image_option == null ? new String[] {} : Image_option.split(","));
		PictureController pc = new PictureController();
		Pictures Pic = pc.addPicture("upload/" + SavefileName, fileName, WeChatID);
		PicturesService.addPictures(Pic);
		return Pic;

	}

	@RequestMapping(value = "/weChatPublicList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		Map<String, List<?>> hashMap = new HashMap<String, List<?>>();
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, "");
		return new ModelAndView(SmBaseGlobal.WebViewPath + "weChatPublicList", hashMap);
	}

	@RequestMapping(value = "/getWeChatPublicList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatPublicList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Company = request.getParameter("Company");
		String isOpen = request.getParameter("isOpen");
		Users user = (Users) session.getAttribute("UserName");
		if (Company != null && !Company.isEmpty()) {
			Company = SmBaseUtil.URLDecoderString(Company);
			System.out.println("company" + Company);
		}

		String state = request.getParameter("Status");

		String AreaID = request.getParameter("AreaID");
		String UnitAreaID = request.getParameter("UnitAreaID");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
		String bind = request.getParameter("bind");
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
		if (Company != null && !Company.isEmpty()) {
			checkParammap.put("Company", Company);
		}

		if (bind != null && !bind.isEmpty()) {
			bind = SmBaseUtil.URLDecoderString(bind);
			checkParammap.put("Bind", bind);
			System.out.println(bind);
		}
		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		} else {
			checkParammap.put("Status", state);
		}
		if (isOpen != null) {
			if (Integer.parseInt(isOpen) == 1) {

				if (AreaID != null && !AreaID.isEmpty() && Long.parseLong(AreaID) > 0) {
					checkParammap.put("AreaID", AreaID);
				}
				if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
					responseMap.put("AreaID", user.getAreaID());
				}
				if (UnitAreaID != null && !UnitAreaID.isEmpty() && Long.parseLong(UnitAreaID) > 0) {
					checkParammap.put("UnitAreaID", UnitAreaID);
				}

			}
		}
		List<WeChatPublic> lswe = ReadWeChatPublicService.getWeChatPublicList(checkParammap);
		// lswe.get(0).setContactPerson("<img alt='image' style='width:70px;height:70px;' class='img-circle' id='UserInfoImg' src='http://localhost:8080/WeNewsAgency/img/profile_small.png'>");
		int WeChatCount = ReadWeChatPublicService.getWeChatPublicNormalCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", WeChatCount);
		responseMap.put("Status", 1);
		return responseMap;
	}

	/**
	 * 手机详细页
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/phoneWeChatDetail", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phoneweChatGroupDetail(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
		String WeChat = request.getParameter("_wc_");
		String PCCode = request.getParameter("_pc_");
		String area = request.getParameter("_area_");
		List<WeChatPublic> lswp = null;
		List<Permissions> lspc = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("NoEqStatus", SmBaseGlobal.CheckStatus.Disabled.getid());
		responseMap.put("ID", Long.parseLong(WeChat));
		lswp = ReadWeChatPublicService.getWeChatPublicList(responseMap);
		WeChatPublic wc = lswp.get(0);
		if (area == null || area.isEmpty()) {
			area = String.valueOf(wc.getAreaID());
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put("AreaID", area);
		lspc = PermissionsService.getPermissionsList(responseMap);
		if (lswp.size() <= 0 || lspc.size() <= 0) {
			response.sendRedirect(request.getContextPath() + "/include/mobile404.html");
		}
		addUserClickCount(request, WeChat);

		wc.setWeChat(SmBaseUtil.substring(wc.getWeChat(), 30));
		wc.setAddress(SmBaseUtil.substring(wc.getAddress(), 30));
		wc.setCompany(SmBaseUtil.substring(wc.getCompany(), 30));
		model.addAttribute("WeChat", wc);
		model.addAttribute("WeChatGroupID", lspc.get(0).getWeChatGroupID());
		model.addAttribute("_pc_", PCCode);
		model.addAttribute("_area_", lspc.get(0).getPowerCode());
		model.addAttribute("Title", lswp.get(0).getCompany());
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneWeChatDetail");
	}

	/**
	 * 地图
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/PhoneDetailNavMap", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView NavMap(HttpServletRequest request, Model model, HttpServletResponse response) {
		String WeChat = request.getParameter("_wc_");
		String area = request.getParameter("_area_");
		List<WeChatPublic> lswp = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("NoEqStatus", SmBaseGlobal.CheckStatus.Disabled.getid());
		responseMap.put("ID", Long.parseLong(WeChat));
		lswp = ReadWeChatPublicService.getWeChatPublicList(responseMap);
		model.addAttribute("WeChat", lswp.get(0));
		model.addAttribute("AreaID", area);
		model.addAttribute("Title", lswp.get(0).getCompany() + " -- 地图导航");
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "PhoneDetailNavMap");
	}

	@RequestMapping(value = "/getReferWeChatPublicList", method = RequestMethod.GET)
	public @ResponseBody
	List<WeChatPublic> getReferWeChatPublicList(HttpServletRequest request, HttpServletResponse response) {
		String WeChat = request.getParameter("WeChat");
		String AreaID = request.getParameter("AreaID");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String Status = request.getParameter("Status");
		if (Status == null || Status.isEmpty()) {
			checkParammap.put("Status", 1);
		} else {
			checkParammap.put("Status", Status);
		}

		if (WeChat != null && !WeChat.isEmpty()) {
			checkParammap.put("WeChat", WeChat);
		}
		if (AreaID != null && !AreaID.isEmpty()) {
			checkParammap.put("AreaID", AreaID);
		}
		List<WeChatPublic> lswe = ReadWeChatPublicService.getWeChatPublicList(checkParammap);
		responseMap.put("data", lswe);
		return lswe;
	}

	@RequestMapping(value = "/getWeChatPublicForEffList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatPublicForEffList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String AreaID = request.getParameter("UnitAreaID");
		String Company = request.getParameter("Company");
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
		if (pageSize != null && !pageSize.isEmpty()) {
			responseMap.put("limit", pageSize);
		} else {
			responseMap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
			responseMap.put("start", pageNumber);
		} else {
			responseMap.put("start", 0);
		}
		if (Company != null && !Company.isEmpty()) {
			responseMap.put("Company", Company);
		}
		if (AreaID != null && !AreaID.isEmpty() && !AreaID.equals("0")) {
			responseMap.put("AreaID", AreaID);
		}
		List<WeChatPublic> lswe = ReadWeChatPublicService.getWeChatPublicForEffList(responseMap);
		responseMap.put("Status", 1);
		int wecount = ReadWeChatPublicService.getWeChatCount(responseMap);
		responseMap.put("data", lswe);
		responseMap.put("status", 1);
		responseMap.put("total", wecount);
		return responseMap;
	}

	@RequestMapping(value = "/deleteWeChatPublic", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;
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
					WeChatPublicService.enabledWeChatPublic(responseMap);
				} else {
					WeChatPublicService.deleteWeChatPublic(responseMap);
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	/**
	 * 添加访问量
	 * 
	 * @param req
	 * @param type
	 * @param EntryID
	 */
	public void addUserClickCount(HttpServletRequest req, String EntryID) {
		int result = SmBaseUtil.getClickInfo(req, EntryID, ClickListService);
		if (result > 0) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", EntryID);
			// 添加产品点击量
			WeChatPublicService.UpClickCount(responseMap);
		}
	}
}