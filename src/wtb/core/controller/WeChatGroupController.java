package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.alibaba.fastjson.JSON;
import wtb.core.model.Activity;
import wtb.core.model.DealInfo;
import wtb.core.model.DisplayPhoneData;
import wtb.core.model.DisplayPhoneProdData;
import wtb.core.model.DisplayWeNewsPhoneData;
import wtb.core.model.Notices;
import wtb.core.model.PaidListing;
import wtb.core.model.Permissions;
import wtb.core.model.ProdPictures;
import wtb.core.model.Product;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.StudentsLog;
import wtb.core.model.Video;
import wtb.core.model.VideoClass;
import wtb.core.model.WeChatBanner;
import wtb.core.model.WeChatGroup;
import wtb.core.model.WeChatGroupPart;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatUser;
import wtb.core.model.WeMoney;
import wtb.smUtil.FKSequenceGenerator;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.ErrorUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * wtb_users
 */
@Controller
@RequestMapping("weChatGroup")
public class WeChatGroupController extends BaseController {

	FKSequenceGenerator FKS = new FKSequenceGenerator();
	Calendar ca = Calendar.getInstance();

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Date date0 = null;
	Date date2 = null;
	int number1 = 0;
	int sum = 0;
	/**
	 * 添加信息
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/addWeChatGroup", method = RequestMethod.GET)
	public ModelAndView addWeChatGroup(@ModelAttribute("WeChatGroupForm") WeChatGroup weChatgroup, HttpServletRequest req, BindingResult result,
			HttpServletResponse response, HttpSession session, Model model) {
		String wechatID = req.getParameter("wid");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Status = "1";

		if (wechatID != null && !wechatID.isEmpty()) {

			responseMap.put("ID", Long.parseLong(wechatID));
			weChatgroup = WeChatGroupService.getWeChatGroupList(responseMap).get(0);
			model.addAttribute("WeChatGroupForm", weChatgroup);
			model.addAttribute("wechatID", wechatID);
			if (weChatgroup.getStatus() == SmBaseGlobal.CheckStatus.Disabled.getid()) {
				Status = "0";
			}
			responseMap = new HashMap<String, Object>();
			responseMap.put("AreaID", weChatgroup.getAreaID());
			responseMap.put("Status", 1);
			List<Permissions> lspm = PermissionsService.getPermissionsList(responseMap);
			responseMap = new HashMap<String, Object>();
			if (lspm.size() > 0) {
				model.addAttribute("PowerCode", lspm.get(0).getPowerCode());
			} else {
				UUID uuid = UUID.randomUUID();
				Permissions pm = new Permissions();
				pm.setID(new IdWorker(0, 0).nextId());
				pm.setModifyTime(new Date());
				pm.setCreateTime(new Date());
				pm.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				pm.setPowerCode(uuid.randomUUID().toString());
				pm.setWeChatGroupID(Long.parseLong(wechatID));
				pm.setAreaID(weChatgroup.getAreaID());
				PermissionsService.addPermissions(pm);
				model.addAttribute("PowerCode", pm.getPowerCode());

			}
		}
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatgroup.getAreaID()));
		model.addAttribute("Status", Status);
		responseMap = new HashMap<String, Object>();
		responseMap.put("ParentID", 1);
		model.addAttribute("Province", RegionService.getRegionList(responseMap));
		return new ModelAndView("addWeChatGroup");
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView gotosuccess(@ModelAttribute("WeChatGroupForm") WeChatGroup weChatgroup,

	BindingResult result, HttpServletResponse response, HttpSession session, Model model) {

		return new ModelAndView("success");
	}

	@RequestMapping(value = "/phoneIntroduce", method = RequestMethod.GET)
	public ModelAndView phoneIntroduce(HttpServletResponse response, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneIntroduce");
	}

	@RequestMapping(value = "/addWeChatGroup", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("WeChatGroupForm") WeChatGroup weChatgroup, HttpServletRequest req, @RequestParam(value = "wids",
			required = true) String wids, BindingResult result, HttpServletResponse response, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Integer proResult = 0;
		String AreaID = "0";
		String ProvinceID = "0";
		String CityID = "0";
		String Status = "1";
		if (weChatgroup.getStatus() == SmBaseGlobal.CheckStatus.Disabled.getid()) {
			Status = "0";
		}
		model.addAttribute("Status", Status);
		if (weChatgroup.getTitle().isEmpty()) {
			result.rejectValue("Title", "misFormat", "社区名称不能为空");
		}
		if (weChatgroup.getIndustry().isEmpty()) {
			result.rejectValue("Industry", "misFormat", "社区所属行业不能为空");
		}
		if (weChatgroup.getIndustry().isEmpty()) {
			result.rejectValue("Industry", "misFormat", "社区所属行业不能为空");
		}
		if (Long.parseLong(weChatgroup.getAreaID()) <= 0) {
			result.rejectValue("AreaID", "misFormat", "社区所属区域不能为空");
		} else {

			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", weChatgroup.getAreaID());
			if (responseMap.size() > 0) {
				List<Region> lswechat = RegionService.getRegionList(responseMap);
				if (lswechat.size() <= 0) {
					result.rejectValue("AreaID", "misFormat", "所选择的社区地区不存在");
				} else {
					weChatgroup.setAreaID(lswechat.get(0).getID());
				}
			}

		}
		try {

			String Code = "";
			long idw = 0;
			if (weChatgroup.getID() > 0) {
				responseMap.put("ID", weChatgroup.getID());
				responseMap.put("Status", 1);
				List<WeChatGroup> lswg = WeChatGroupService.getWeChatGroupList(responseMap);
				if (lswg.size() > 0) {
					WeChatGroup wg = lswg.get(0);
					wg.setTitle(weChatgroup.getTitle());
					wg.setIndustry(weChatgroup.getIndustry());
					wg.setModifyTime(new Date());
					wg.setAreaID(weChatgroup.getAreaID());
					proResult = WeChatGroupService.updateWeChatGroup(wg);
					weChatgroup = wg;
					Code = wg.getCode();
					idw = wg.getID();
				} else {
					result.rejectValue("Title", "社区" + weChatgroup.getTitle() + "未找到,或已被禁用 ");
				}

			} else {
				UUID uuid = UUID.randomUUID();
				Code = uuid.randomUUID().toString();
				WeChatGroup wg = new WeChatGroup();
				wg.setAreaID(weChatgroup.getAreaID());
				wg.setIndustry(weChatgroup.getIndustry());
				wg.setTitle(weChatgroup.getTitle());
				wg.setCode(Code);
				wg.setStatus(1);
				wg.setModifyTime(new Date());
				wg.setCreateTime(new Date());
				idw = new IdWorker(0, 0).nextId();
				wg.setID(idw);
				WeChatGroupService.addWeChatGroup(wg);
				responseMap = new HashMap<String, Object>();
				responseMap.put("Code", Code);
				weChatgroup.setID(idw);
				// 添加社区的访问权限
				Permissions pm = new Permissions();
				pm.setID(new IdWorker(0, 0).nextId());
				pm.setModifyTime(new Date());
				pm.setCreateTime(new Date());
				pm.setStatus(1);
				pm.setAreaID(weChatgroup.getAreaID());
				pm.setPowerCode(uuid.randomUUID().toString());
				pm.setWeChatGroupID(idw);
				PermissionsService.addPermissions(pm);
			}

			if (wids != null && !wids.isEmpty()) {
				String[] strids = wids.split(",");
				for (String id : strids) {
					if (!id.isEmpty()) {
						long intID = Long.parseLong(id);
						WeChatGroupPart wpp = new WeChatGroupPart();
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", intID);
						List<WeChatGroupPart> lswps = WeChatGroupPartService.getWeChatGroupPartList(responseMap);
						responseMap = new HashMap<String, Object>();
						if (lswps.size() > 0) {
							wpp = lswps.get(0);
							responseMap.put("ID", wpp.getWeChatID());
						} else {
							wpp.setGroupID(idw);
							wpp.setCreateTime(new Date());
							wpp.setModifyTime(new Date());
							wpp.setStatus(1);
							wpp.setWeChatID(intID);
							wpp.setID(new IdWorker(1, 0).nextId());
							proResult = WeChatGroupPartService.addWeChatGroupPart(wpp);
							responseMap.put("ID", intID);
						}
						WeChatPublic wp = ReadWeChatPublicService.getWeChatPublicList(responseMap).get(0);
						// 更新为已经添加联盟
						wp.setStatus(2);
						proResult = WeChatPublicService.updateWeChatPublic(wp);
					}
				}
			}

		} catch (Exception e) {
			result.rejectValue("Title", "微信公众号添加失败=> " + e.getMessage());
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		model = RegionController.getRegionParams(req, ReadRegionService, model, session, String.valueOf(weChatgroup.getAreaID()));
		if (!result.hasErrors()) {
			model.addAttribute("content", req.getContextPath() + "/weChatGroup/weChatGroupList?AreaID=" + weChatgroup.getAreaID() + "&ProvinceID=" + ProvinceID
					+ "&CityID=" + CityID);
			return new ModelAndView("success");
		}
		if (result.hasErrors()) {
			model.addAttribute("WeChatGroupForm", weChatgroup);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView("addWeChatGroup");
		}
		return new ModelAndView("addWeChatGroup");
	}

	@RequestMapping(value = "/weChatGroupList", method = RequestMethod.GET)
	public ModelAndView weChatGroupList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		Map<String, List<?>> hashMap = new HashMap<String, List<?>>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String Title = req.getParameter("Title");
		String AreaID = req.getParameter("AreaID");

		if (Title != null && !Title.isEmpty()) {
			checkParammap.put("Title", Title);
		}
		checkParammap.put("Status", 1);
		List<WeChatGroup> lswg = WeChatGroupService.getWeChatGroupList(checkParammap);

		model = RegionController.getRegionParams(req, ReadRegionService, model, session, "");
		hashMap.put("list", lswg);// userlist是个Arraylist之类的
		model.addAttribute("AreaID", AreaID);
		return new ModelAndView("weChatGroupList", hashMap);
	}

	@RequestMapping(value = "/getRegionPowerCode", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getRegionPowerCode(HttpServletResponse response, HttpServletRequest req) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		String AreaID = req.getParameter("AreaID");
		if (AreaID != null && !AreaID.isEmpty()) {
			responseMap.put("AreaID", AreaID);
			responseMap.put("Status", 1);
			List<Permissions> lspm = PermissionsService.getPermissionsList(responseMap);
			if (lspm.size() > 0) {
				result.put("Data", lspm.get(0).getPowerCode());

			} else {
				UUID uuid = UUID.randomUUID();
				Permissions pm = new Permissions();
				pm.setID(new IdWorker(0, 0).nextId());
				pm.setModifyTime(new Date());
				pm.setCreateTime(new Date());
				pm.setStatus(1);
				pm.setPowerCode(uuid.randomUUID().toString());
				pm.setWeChatGroupID(0);
				pm.setAreaID(AreaID);
				PermissionsService.addPermissions(pm);
				result.put("Data", pm.getPowerCode());
			}
		}
		return result;
	}

	/**
	 * 首页页面
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/phoneweChatGroupDetailList", method = RequestMethod.GET)
	public ModelAndView phoneweChatGroupDetailList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap;

		List<WeChatGroup> wgp;
		List<Region> Areas;
		List<WeChatBanner> wcb;
		String Area = req.getParameter("_area_");
		String pw = req.getParameter("_pc_");// 验证powercode
		String page = req.getParameter("_page_");
		try {
			if (pw != null && !pw.isEmpty() && Area != null && !Area.isEmpty()) {
				hashMap = new HashMap<String, Object>();
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("ID", Area);
				Areas = ReadRegionService.getRegionList(checkParammap);// userlist是个Arraylist之类的
				hashMap = new HashMap<String, Object>();
				if (Areas.size() > 0) {
					hashMap.put("AreaID", Area);
				}
				hashMap.put("Status", 1);

				checkParammap.put("ID", Area);
				checkParammap.put("start", 0);
				checkParammap.put("limit", 12);
				wgp = WeChatGroupService.getWeChatGroupList(hashMap);

				checkParammap = new HashMap<String, Object>();

				checkParammap.put("Status", 1);
				checkParammap.put("Year", ca.get(Calendar.YEAR));
				checkParammap.put("Month", ca.get(Calendar.MONTH) + 1);
				wcb = WeChatBannerService.getWeChatBannerList(checkParammap);// userlist是个Arraylist之类的

				/**
				 * 获取首页主页要显示的数据
				 */
				List<DisplayPhoneData> DispData = getDispDataList(Area, page, "");

				if (DispData.size() > 0 && Areas.size() > 0) {
					model.addAttribute("Title", Areas.get(0).getName() + "社区");
					model.addAttribute("PartList", DispData);
					model.addAttribute("BannerList", wcb.size() > 0 ? wcb.get(0) : wcb);
					model.addAttribute("BannerListCount", wcb.size());
					model.addAttribute("GroupList", wgp);

					model.addAttribute("_area_", Area);
					model.addAttribute("_page_", 2);
					model.addAttribute("_pc_", pw);
				} else {
					response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
					return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneweChatGroupDetailList");
				}
			}
		} catch (Exception e) {
			response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneweChatGroupDetailList");
	}

	/**
	 * 首页页面
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/phoneWeNewsHome", method = RequestMethod.GET)
	public ModelAndView phoneWeNewsHome(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException {
		String title = "";
		System.err.println("进入phoneWeNewsHome");
		try {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			Map<String, Object> checkParammap = new HashMap<String, Object>();
			List<Region> Areas=null;
			String pw = req.getParameter("_pc_");// 验证powercode
			String page = req.getParameter("_page_");
			String Area = req.getParameter("_area_");
			String isStu = req.getParameter("_isStu_");// 0是校 1是区 2是市 3是省
			String ParentID = req.getParameter("_paid_");//
			if (isStu != null && (isStu.equals("0_and__paid_=100047") || isStu.contains("0_and__paid_=100047"))) {
				isStu = "0";
				ParentID = "100047";
			}

			//if ((ParentID==null || ParentID.isEmpty()) && session.getAttribute("Area") != null) {
				//Area = (String) session.getAttribute("Area");
			//}
			
				
			
			if (!SmBaseUtil.isNumeric(isStu)) {
				isStu = "0";
			}
			if (Area == null || Area.isEmpty() || Area.equals("null") || Area.equals("0")) {
				if(ParentID!=null){
					Area =ParentID;
				}else{
					Area = "0";
				}
				
			}
			//String islogin = checkIsLogin(req, session, response);
			if (isStu != null) {
				// 判断是否已经登录
					try {
						if (Area != null && !Area.isEmpty()) {
							if(!Area.equals("0")){
								hashMap = new HashMap<String, Object>();
								checkParammap = new HashMap<String, Object>();
								checkParammap.put("ID", Area);
								Areas = ReadRegionService.getRegionList(checkParammap);// userlist是个Arraylist之类的
								hashMap = new HashMap<String, Object>();
								if (Areas.size() > 0) {
									hashMap.put("AreaID", Area);
									if(Areas.get(0).getLevel()==1){
										isStu="3";
									}else if(Areas.get(0).getLevel()==2){
										isStu="2";
									}else if(Areas.get(0).getLevel()==3){
										isStu="1";
									}else {
										isStu="0";
									}
								} else {
									response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
									return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneWeNewsHome");
								}
							}

							/**
							 * 获取首页主页要显示的数据
							 */
							List<DisplayWeNewsPhoneData> DispData = getWeNewsDispDataList(Area, page, "", "2", "",isStu, null, req);
							checkParammap = new HashMap<String, Object>();
							checkParammap.put("Hot", 1);
							checkParammap.put("Status", 1);
							List<Activity> lswe = ReadActivityService.getActivityList(checkParammap);
							if (lswe.size() > 0) {
								model.addAttribute("BannerListCount", lswe.size());
								model.addAttribute("BannerList", lswe);
							}
							// if (DispData.size() > 0 && Areas.size() > 0 &&) {
							model.addAttribute("PartList", DispData);
							model.addAttribute("_area_", Area);
							model.addAttribute("_page_", 2);
							model.addAttribute("_pc_", pw);
							model.addAttribute("_isStu_", isStu);
							if(Areas!=null && Areas.size()>0){
								title = Areas.get(0).getName();
							}
							// }
						}

					} catch (Exception e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
						response.sendRedirect(req.getContextPath() + "/include/mobile404.html");
						return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneWeNewsHome");
					}
				}
			
			if (isStu == null || isStu.isEmpty() || isStu.equals("0")) {
				model.addAttribute("Title", title + "支社_新浪微新闻社");
			} else if (isStu.equals("1") ||isStu.equals("2")) {
				model.addAttribute("Title", title + "教育分社_新浪微新闻社");
			} else {
				model.addAttribute("Title", title + "总社_新浪微新闻社");
			}
			if (Area != null && Area.equals("0")) {
				model.addAttribute("Title", "新浪微新闻社");
			}
			if (isStu != null) {
				session.setAttribute("isStu", isStu);
			} else {
				session.setAttribute("isStu", 0);
			}
			if (Area != null && !Area.isEmpty()) {
				session.setAttribute("Area", Area);
			}

			Map<String, Object> map = new HashMap<>();
			map.put("Type", 2);
			map.put("IsEnable", 1);
			List<ProdPictures> pictures = ReadProdPicturesService.getPictureList(map);
			model.addAttribute("ImageCount", pictures.size());
			model.addAttribute("ImageList", pictures);
			model.addAttribute("_page_", 2);
		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		System.err.println("离开phoneWeNewsHome");
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneWeNewsHome");
	}

	private String checkIsLogin(HttpServletRequest req, HttpSession session, HttpServletResponse resp) {
		String islogin = "";
		try {
			Students user = (Students) req.getSession().getAttribute("StudentName");
			String uid = req.getParameter("userInfo");
			Map<String, Object> checkParammap = new HashMap<String, Object>();

			String basePath = SmBaseUtil.getCurrentWebUrl(req) + "/weChatGroup/phoneWeNewsHome";

			if (user == null && uid == null) {
				islogin = SmBaseUtil.getWeChatLoginUrl("snsapi_userinfo", basePath,req);
			} else {
				List<Students> lsStu;
				if (uid != null && !uid.isEmpty() && user == null) {
					uid = SmBaseUtil.URLDecoderString(uid);
					JSONObject jsonObj = null;
					try {
						jsonObj = SmBaseUtil.PaseJsonToJsonObject(uid);
					} catch (Exception e) {
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
						e.printStackTrace();
					}
					checkParammap.put("OpenID", jsonObj.getString("openid"));
					checkParammap.put("Sina", SmBaseUtil.Random());
					lsStu = ReadStudentsService.getStudentsList(checkParammap);
					Students stu = null;
					// 判断是否有地区的信息
					if (lsStu.size() <= 0) {
						// fastJson转换json字符串为对象
						if (SmBaseGlobal.IOpen == true) {
							WeChatUser xbuser = JSON.parseObject(uid, WeChatUser.class);
							stu = SmBaseUtil.parseXBUserForStudent(xbuser, stu, RegionService);
						}
						try {
							StudentsService.addStudents(stu);
							checkParammap = new HashMap<String, Object>();
							checkParammap.put("UserID", stu.getID());
							List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyByIDList(checkParammap);
							if (weMoney.size() > 0) {
								if (weMoney.get(0).getUserID() != stu.getID()) {
									weMoney.get(0).setUserID(stu.getID());
									WeMoneyService.updateWeMoney(weMoney.get(0));
								}
							} else {
								WeMoney weMoneyTemp = new WeMoney();
								weMoneyTemp.setID(SmBaseUtil.CreateNewID());
								weMoneyTemp.setUserID(stu.getID());
								weMoneyTemp.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
								weMoneyTemp.setWeMoney(SmBaseGlobal.DefaultWeMoney);// 新用户奖!"0微米
								WeMoneyService.addWeMoney(weMoneyTemp);
							}
						} catch (Exception e) {
							e.printStackTrace();
							String clazz = this.getClass().getName();
							String method = Thread.currentThread().getStackTrace()[1].getMethodName();
							ErrorUtil.HandleError(null, clazz + "." + method, e);
						}
					} else {
						stu = lsStu.get(0);
					}

					SmBaseUtil.CreateSession("StudentName", stu, req, session, resp);
				}
			}
		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		return islogin;
	}

	/**
	 * 查询首页的信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getWeNewsPaJsonrtList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeNewsPaJsonrtList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException, SocketException, UnknownHostException, ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String areaid = request.getParameter("_area_");
		String pw = request.getParameter("_pc_");// 验证powercode
		String page = request.getParameter("_page_");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String isStu = request.getParameter("_isStu_");

		if (session.getAttribute("Area") != null) {
			areaid = (String) session.getAttribute("Area");
		}
			
		if (isStu == null || isStu.isEmpty()) {
			isStu = "0";
		}
		int NextPage = 1;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}
		if (areaid != null && !areaid.isEmpty() && pw != null) {
			List<DisplayWeNewsPhoneData> DispData;
			if (name != null && !name.isEmpty()) {
				name = new String(name.getBytes("iso-8859-1"), "utf-8");
				DispData = getWeNewsDispDataList(areaid, page, "", type, "", "0", name, request);
			} else {
				DispData = getWeNewsDispDataList(areaid, page, "", type, "", isStu, null, request);
			}

			if (DispData.size() <= 0) {
				responseMap.put("Status", -1);
			} else {
				responseMap.put("Status", 1);
				responseMap.put("Data", DispData);
			}
		}
		responseMap.put("_page_", NextPage);
		return responseMap;
	}

	/**
	 * 微新闻社获取首页数据信息
	 * 
	 * @param areaid
	 * @param page
	 * @param GroupID
	 * @param type
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 * @throws ParseException
	 */
	public List<DisplayWeNewsPhoneData> getWeNewsDispDataList(String areaid, String page, String GroupID, String type, final String paid, String difference,
			String name, HttpServletRequest req) throws SocketException, UnknownHostException, ParseException {
		List<DisplayWeNewsPhoneData> DispData = new ArrayList<DisplayWeNewsPhoneData>();
		CheckSchool(areaid);
		Map<String, Object> checkParammap = null;
		int NextPage = 1;
		int PageCount = 12;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}

		// if(areaid.isEmpty())
		// {

		/**
		 * 获取区级信息 SELECT * FROM `wtb_Notices` WHERE `Notices_AreaID`IN(SELECT
		 * `REGION_ID` FROM wtb_Region WHERE parent_id=1301) and Notices_Region
		 * in(1,2)
		 */
		if (difference != null && difference.equals("1")) {
			/**
			 * 获取区级信息
			 * 
			 * SELECT `REGION_ID` FROM `wtb_Region` WHERE `PARENT_ID`=122
			 * http://192.168.31.98:8080/WeNewsAgency/weChatGroup/
			 * phoneWeNewsHome?_isStu_=1&_paid_=1301 SELECT `Notices_AreaID`
			 * FROM `wtb_Notices` GROUP BY `Notices_AreaID`
			 * HAVING(COUNT(`Notices_AreaID`)>1)
			 */
			// getMes(new HashMap<String,Object>(), paid, DispData);
			/**
			 * SELECT `PARENT_ID` FROM `wtb_Region` WHERE `REGION_ID` in(SELECT
			 * `Notices_AreaID` FROM `wtb_Notices` GROUP BY `Notices_AreaID`
			 * HAVING(COUNT(`Notices_AreaID`)>1)) GROUP BY `PARENT_ID`
			 * HAVING(COUNT(`PARENT_ID`)>0) 查询新闻内父地区的号PARENT_ID
			 */

			// 还要好好理过

			checkParammap = new HashMap<String, Object>();
			checkParammap.put("AreaIDisQu", 1);
			if (areaid != null && !areaid.isEmpty() && !areaid.equals("0")) {
				checkParammap.put("AreaID", areaid);
			}
			checkParammap.put("IsSchool", "1");
			if (name != null && !name.isEmpty()) {
				checkParammap.put("Author", name);
			}
			checkParammap.put("start", PageCount * NextPage - PageCount);
			checkParammap.put("limit", PageCount);
			checkParammap.put("Rand", SmBaseUtil.Random());
			List<Notices> mList = ReadNoticesService.getReadNoticesList(checkParammap);
			for (int i = 0; i < mList.size(); i++) {
				DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
				dp.setTitle(mList.get(i).getTitle());
				dp.setNoticesList(mList.get(i));
				dp.setPKID(String.valueOf(mList.get(i).getID()));
				dp.setCreateTime(mList.get(i).getCreateTime());
				// dp.setClickCount(mList.get(i).getClickCount());
				dp.setType(2);
				DispData.add(dp);
			}
		}

		if (difference.equals("2")) {
			/**
			 * 查询所有市 http://192.168.31.98:8080/WeNewsAgency/weChatGroup/
			 * phoneWeNewsHome?_isStu_=2&_paid_=122 SELECT * FROM `wtb_Notices`
			 * WHERE `Notices_AreaID`IN(SELECT `REGION_ID` FROM wtb_Region WHERE
			 * parent_id in (SELECT `REGION_ID` FROM `wtb_Region` WHERE
			 * `PARENT_ID`=122)) AND `Notices_Region`=2 SELECT `REGION_ID` FROM
			 * `wtb_Region` WHERE `PARENT_ID`=12
			 */

			Map<String, Object> checkParammapMap = new HashMap<String, Object>();
			checkParammapMap.put("AreaIDCity", areaid);
			if (name != null && !name.isEmpty()) {
				checkParammapMap.put("Author", name);
			}
			checkParammapMap.put("start", PageCount * NextPage - PageCount);
			checkParammapMap.put("limit", PageCount);
			checkParammapMap.put("Rand", SmBaseUtil.Random());
			List<Notices> qlsns = ReadNoticesService.getReadNoticesList(checkParammapMap);
			for (int z = 0; z < qlsns.size(); z++) {
				DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
				dp.setTitle(qlsns.get(z).getTitle());
				dp.setNoticesList(qlsns.get(z));
				dp.setPKID(String.valueOf(qlsns.get(z).getID()));
				dp.setCreateTime(qlsns.get(z).getCreateTime());
				// dp.setClickCount(qlsns.get(z).getClickCount());
				dp.setType(2);
				DispData.add(dp);
			}
		}

		if (difference.equals("3")) {
			/**
			 * 查询省级 http://192.168.31.98:8080/WeNewsAgency/weChatGroup/
			 * phoneWeNewsHome?_isStu_=3&_paid_=12
			 */
			Map<String, Object> checkParammapMap = new HashMap<String, Object>();
			checkParammapMap.put("AreaIDPro", areaid);
			if (name != null && !name.isEmpty()) {
				checkParammapMap.put("Author", name);
			}
			checkParammapMap.put("start", PageCount * NextPage - PageCount);
			checkParammapMap.put("limit", PageCount);
			checkParammapMap.put("Rand", SmBaseUtil.Random());
			List<Notices> qlsns = ReadNoticesService.getReadNoticesList(checkParammapMap);
			for (int z = 0; z < qlsns.size(); z++) {
				DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
				dp.setTitle(qlsns.get(z).getTitle());
				dp.setNoticesList(qlsns.get(z));
				dp.setPKID(String.valueOf(qlsns.get(z).getID()));
				dp.setCreateTime(qlsns.get(z).getCreateTime());
				// dp.setClickCount(qlsns.get(z).getClickCount());
				dp.setType(2);
				DispData.add(dp);
			}
		}

		if (areaid != null && !areaid.isEmpty() && difference.equals("0")) {

			if (type == null || type.isEmpty() || type.equals("2")) {
				String top=req.getParameter("top");
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				checkParammap.put("NoEqStatus", 0);
				checkParammap.put("start", PageCount * NextPage - PageCount);
				checkParammap.put("limit", PageCount);
				if (areaid != null && !areaid.isEmpty() && !areaid.equals("0")) {
					checkParammap.put("AreaID", areaid);
				}
				if (name != null && !name.isEmpty()) {
					checkParammap.put("Author", name);
				}
				if(top!=null && top.equals("1")){
					checkParammap.put("IsTop",1);
				}
				checkParammap.put("Rand", SmBaseUtil.Random());
				List<Notices> lsWc = ReadNoticesService.getReadNoticesList(checkParammap);
				String PC = req.getParameter("_pc_");
				for (Notices weChatPublic : lsWc) {
					DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
					dp.setTitle(weChatPublic.getTitle());
					if (PC != null && PC.equals("PC")) {// 表示这个是PC的请求,PC的请求需要获取下面的数据,移动端的不需要
						dp.setNoticesList(SmBaseUtil.getPCWeNewsData(req, weChatPublic, ReadLikeRecordService, VoteRecordsService));
						dp.getNoticesList().setContent(SmBaseUtil.StripHT(dp.getNoticesList().getContent()));
					} else {
						dp.setNoticesList(weChatPublic);
					}
					// dp.setNoticesList(SmBaseUtil.getPCWeNewsData(req,
					// weChatPublic, ReadLikeRecordService, VoteRecordsService)
					// );
					dp.setPKID(String.valueOf(weChatPublic.getID()));
					dp.setCreateTime(weChatPublic.getCreateTime());
					dp.setClickCount(weChatPublic.getClickCount());
					dp.setType(2);
					DispData.add(dp);

				}

			}
			if (type == null || type.isEmpty() || type.equals("1")) {
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				// checkParammap.put("AreaID", areaid);
				checkParammap.put("start", PageCount * NextPage - PageCount);
				checkParammap.put("limit", PageCount);
				checkParammap.put("IsEffect", 1);
				List<Activity> lsActivity = ReadActivityService.getActivityList(checkParammap);
				for (Activity weChatPublic : lsActivity) {
					DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
					dp.setTitle(weChatPublic.getTitle());
					dp.setActivity(weChatPublic);
					dp.setPKID(String.valueOf(weChatPublic.getID()));
					dp.setCreateTime(sdf.format(weChatPublic.getCreateTime() == null ? new Date() : weChatPublic.getCreateTime()));
					dp.setType(1);
					DispData.add(dp);

				}
			}
			if (type == null || type.isEmpty() || type.equals("3")) {
				String videoType = req.getParameter("videoType");
				if(videoType!=null && !videoType.isEmpty() && !videoType.equals("0")){
					checkParammap = new HashMap<String, Object>();
					checkParammap.put("ParentID", videoType);
					checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					List<VideoClass> lsvideoclass = ReadVideoClassService.getVideoClassList(checkParammap);
					if(lsvideoclass.size()>0 && lsvideoclass.get(0).getIsEnd()==1){
						videoType = lsvideoclass.get(0).getPKID();	
					}
				}

				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				checkParammap.put("start", PageCount * NextPage - PageCount);
				checkParammap.put("limit", PageCount);
				if(videoType!=null && !videoType.isEmpty() && !videoType.equals("0")){
					checkParammap.put("BelongID", videoType);
				}
				List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
				String PC = req.getParameter("_pc_");
				for (Video weChatPublic : lsvideo) {
					DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
					dp.setTitle(weChatPublic.getTitle());
					dp.setPKID(String.valueOf(weChatPublic.getID()));
					dp.setCreateTime((weChatPublic.getCreateTime() == null) ? sdf.format(new Date()) : weChatPublic.getCreateTime());
					dp.setType(3);
					if (PC != null && PC.equals("PC")) {// 表示这个是PC的请求,PC的请求需要获取下面的数据,移动端的不需要
						dp.setVideo(SmBaseUtil.getPCVideoWeNewsData(req, weChatPublic, ReadLikeRecordService, VoteRecordsService));
						dp.getVideo().setContent(SmBaseUtil.StripHT(dp.getVideo().getContent()));
					} else {
						dp.setVideo(weChatPublic);
					}
					DispData.add(dp);

				}
				
				
			}
		}
		return DispData;
	}

	/**
	 * 检查学校是否入驻 没有入驻就自动创建
	 */
	public void CheckSchool(String AreaID) {
		RegionController regionController=new RegionController();
		regionController.checkIsBind(AreaID, ReadRegionService, ReadWeChatPublicService, WeChatPublicService, StudentsLogService);
	}

	@RequestMapping(value = "/getWeChatGroupList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatGroupList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Title = request.getParameter("Title");
		if (Title != null && !Title.isEmpty()) {
			Title = new String(Title.getBytes("ISO-8859-1"), "UTF-8");
		}
		String state = request.getParameter("Status");
		String AreaID = request.getParameter("AreaID");

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
		if (Title != null && !Title.isEmpty()) {
			checkParammap.put("Title", Title);
		}
		if (AreaID != null && !AreaID.isEmpty()) {
			checkParammap.put("AreaID", AreaID);
		}

		List<WeChatGroup> lswe = WeChatGroupService.getWeChatGroupList(checkParammap);
		int WeCount = WeChatGroupService.getWeChatGroupCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", WeCount);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/getWeChatPublicList", method = RequestMethod.GET)
	public @ResponseBody
	List<WeChatGroupPart> getWeChatPublicList(HttpServletRequest request, HttpServletResponse response) {
		List<WeChatGroupPart> lswe = null;
		String wid = request.getParameter("wid");
		String searchText = request.getParameter("searchText");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("Status", 1);
		if (wid != null && !wid.isEmpty()) {
			checkParammap.put("GroupID", Long.parseLong(wid));
		} else {
			checkParammap.put("GroupID", (long) -1);
		}
		checkParammap.put("Company", searchText);

		lswe = WeChatGroupPartService.getWeChatGroupPartList(checkParammap);

		responseMap.put("data", lswe);
		return lswe;
	}

	/**
	 * 地区的社区列表
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/phoneweChatGroupList", method = RequestMethod.GET)
	public ModelAndView phoneweChatGroupList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException,
			ParseException {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap;
		List<WeChatGroup> wg;
		List<Region> Areas;
		String GroupID = req.getParameter("_groupid_");
		String videoType = req.getParameter("videoType");
		String Area = req.getParameter("_area_");
		String page = req.getParameter("_page_");
		String _pc_ = req.getParameter("_pc_");
		String type = req.getParameter("type");
		String isStu = req.getParameter("_isStu_");
		String name = req.getParameter("_sname_");
		model.addAttribute("parentID", videoType);
		
		if ((Area==null  ||  Area.isEmpty() )&& session.getAttribute("Area") != null) {
			Area = (String) session.getAttribute("Area");
		}
		
		if (name != null && !name.isEmpty()) {
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			model.addAttribute("name", name);
		}
		if (isStu == null || isStu.isEmpty()) {
			isStu = "0";
		}
		if (Area == null || Area.isEmpty() || Area.equals("null")) {
			Area = "0";
		}
		if(type==null || type.isEmpty()|| type.equals("null")){
			type = "2";
		}
		if (Area != null && !Area.isEmpty() && !Area.equals("null")) {

			checkParammap = new HashMap<String, Object>();
			checkParammap.put("ID", Area);
			Areas = ReadRegionService.getRegionList(checkParammap);// userlist是个Arraylist之类的
			hashMap = new HashMap<String, Object>();
			if (Areas.size() > 0) {
				hashMap.put("AreaID", Area);
				if(Areas.get(0).getLevel()==1){
					isStu="3";
				}else if(Areas.get(0).getLevel()==2){
					isStu="2";
				}else if(Areas.get(0).getLevel()==3){
					isStu="1";
				}else {
					isStu="0";
				}
			}

			hashMap = new HashMap<String, Object>();
			List<DisplayWeNewsPhoneData> DispData;
			if (Long.parseLong(isStu) == 0) {
				DispData = getWeNewsDispDataList(Area, page, "", type, "", "0", name, req);
			} else {
				DispData = getWeNewsDispDataList(Area, page, "", type, Area, isStu, name, req);
			}

			hashMap = new HashMap<String, Object>();

			if (DispData.size() > 0 && Areas.size() > 0) {
				if(type!=null && !type.equals("1") && !type.equals("3")){
					model.addAttribute("Title", Areas.get(0).getName() + "支社_新浪微新闻社");
				}else{
					model.addAttribute("Title","新浪微新闻社");
				}
				model.addAttribute("PartList", DispData);
				model.addAttribute("_area_", Area);
				model.addAttribute("type", type);
				model.addAttribute("_pc_", _pc_);
				model.addAttribute("_page_", 2);

			} else {
				model.addAttribute("PartListCount", 0);
			}
			if(session.getAttribute("Area")==null){
				session.setAttribute("isStu", isStu);
				session.setAttribute("Area", Area);
			}
		}
		if (!model.containsAttribute("Title")) {
			model.addAttribute("Title", "新浪微新闻社");
		}
		if (type.equals("3")) {
			Map<String, Object> responseMap = new HashMap<String, Object>();

			List<VideoClass> lswe=new ArrayList<VideoClass>();
			if(videoType==null || videoType.isEmpty()){
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Sina", SmBaseUtil.Random());
				checkParammap.put(VideoClass.attributeVideoClassType, 1);
				checkParammap.put(VideoClass.attributeVideoClassStatus, SmBaseGlobal.CheckStatus.Effective.getid());
				lswe = ReadVideoClassService.getVideoClassList(checkParammap);
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Sina", SmBaseUtil.Random());
				checkParammap.put("Hot", 1);
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
				for (Video video : lsvideo) {
					VideoClass vc=new VideoClass();
					vc.setID(video.getID());
					vc.setPKID(video.getPKID());
					vc.setLevel(-1);//-1表示视频
					vc.setTitle(video.getTitle());
					vc.setType(0);
					if(video.getImage()!=null && video.getImage().getUrl()!=null){
						vc.setImageUrl(video.getImage().getUrl().split(",")[0]);
						lswe.add(vc);
					}
				}
			}else{
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("ID", videoType);
				List<VideoClass> lsvideo = ReadVideoClassService.getVideoClassList(checkParammap);
				if(lsvideo.size()>0){
					model.addAttribute("Title", "视频分类 - "+lsvideo.get(0).getTitle());
				}
				checkParammap = new HashMap<String, Object>();
				checkParammap.put(VideoClass.attributeVideoClassParentID, videoType);
				checkParammap.put(VideoClass.attributeVideoClassStatus, SmBaseGlobal.CheckStatus.Effective.getid());
				lsvideo = ReadVideoClassService.getVideoClassList(checkParammap);
				model.addAttribute("VideoClassList", lsvideo);
				model.addAttribute("VideoClassCount", lsvideo.size());
			}
			model.addAttribute("ImageCount", lswe.size());
			model.addAttribute("ImageList", lswe);
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneVideoList");
		} else {
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatGroupList");
		}
	}

	/**
	 * 查询分类的商家的信息,产品和活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getphoneweChatGroupList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getphoneweChatGroupList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String GroupID = request.getParameter("_groupid_");
		String Area = request.getParameter("_area_");
		String page = request.getParameter("_page_");
		int NextPage = 1;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}

		List<DisplayPhoneData> lsProdList = getDisplayGroupPhoneData(GroupID, page);

		if (GroupID != null && !GroupID.isEmpty()) {
			responseMap.put("Data", lsProdList);
			responseMap.put("Status", 1);

		}
		responseMap.put("_page_", NextPage + 1);
		return responseMap;
	}

	public List<DisplayPhoneData> getDisplayGroupPhoneData(String GroupID, String page) {
		Map<String, Object> checkParammap;
		List<DisplayPhoneData> DispData = new ArrayList<DisplayPhoneData>();
		int NextPage = 1;
		int PageCount = 6;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("GroupID", GroupID);
		checkParammap.put("Status", 1);
		checkParammap.put("start", PageCount * NextPage - PageCount);
		checkParammap.put("limit", PageCount * NextPage);
		List<WeChatGroupPart> wgp = WeChatGroupPartService.getWeChatGroupPartList(checkParammap);

		for (WeChatGroupPart WeChatGroupPart : wgp) {
			/* 查询前台所需要的数据 */
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", 1);
			checkParammap.put("Year", ca.get(Calendar.YEAR));
			checkParammap.put("Month", ca.get(Calendar.MONTH) + 1);
			checkParammap.put("WeChatID", WeChatGroupPart.getWeChatID());
			List<PaidListing> paidlist = PaidListingService.getPaidListingList(checkParammap);
			DisplayPhoneData dp = new DisplayPhoneData();
			dp.setTitle(WeChatGroupPart.getWeChat().getCompany());
			dp.setWeChatID(WeChatGroupPart.getWeChatID());
			dp.setPKID(String.valueOf(WeChatGroupPart.getWeChatID()));
			if (paidlist.size() > 0) {
				dp.setWeight(paidlist.get(0).getAmount());
			} else {
				dp.setWeight(WeChatGroupPart.getWeChat().getWeight());
			}
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", 1);
			checkParammap.put("WeChatID", WeChatGroupPart.getWeChatID());
			checkParammap.put("start", 0);
			checkParammap.put("limit", 1);
			checkParammap.put("IsEffect", 1);
			List<Activity> lsActivity = ReadActivityService.getActivityList(checkParammap);
			if (lsActivity.size() > 0) {
				dp.setActivity(lsActivity.get(0));
				dp.setType(1);
			}

			checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", 1);
			checkParammap.put("start", 0);
			checkParammap.put("limit", 1);
			checkParammap.put("WeChatID", WeChatGroupPart.getWeChatID());
			List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
			if (lsvideo.size() > 0) {
				dp.setVideo(lsvideo.get(0));
				dp.setType(1);
			}

			checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", 1);
			checkParammap.put("WeChatID", WeChatGroupPart.getWeChatID());
			checkParammap.put("start", 0);
			checkParammap.put("limit", 2);
			List<Product> lsProd = ProductService.getProductList(checkParammap);
			if (lsProd.size() > 0) {
				dp.setType(0);
				dp.setProductList(lsProd);
			}
			DispData.add(dp);
		}
		Collections.sort(DispData, new Comparator<DisplayPhoneData>() {
			public int compare(DisplayPhoneData arg0, DisplayPhoneData arg1) {
				return arg0.getWeight() > arg1.getWeight() ? 1 : 0;
			}
		});
		return DispData;
	}

	/**
	 * 查询商家的信息,产品和活动
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/phoneweChatGroupProdList", method = RequestMethod.GET)
	public ModelAndView phoneweChatGroupProdList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException {
		String wid = req.getParameter("_wgid_");
		String page = req.getParameter("_page_");
		String area = req.getParameter("_area_");
		String _pc_ = req.getParameter("_pc_");
		int NextPage = 1;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}

		DisplayPhoneProdData lsProdList = getWeChatProdInfo(wid, NextPage);

		if (wid != null && !wid.isEmpty()) {
			model.addAttribute("Data", lsProdList);

			model.addAttribute("Title", SmBaseUtil.substring(lsProdList.getWeChat().getCompany(), 10) + " -- 产品列表");
		}
		model.addAttribute("_page_", NextPage + 1);
		model.addAttribute("_wgid_", wid);
		model.addAttribute("_pc_", _pc_);
		model.addAttribute("_area_", area);

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneweChatGroupProdList");
	}

	/**
	 * 查询商家的信息,产品和活动
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getWeChatPublicProdList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatPublicProdList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String wid = request.getParameter("_wgid_");
		String page = request.getParameter("_page_");
		int NextPage = 1;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}

		DisplayPhoneProdData lsProdList = getWeChatProdInfo(wid, NextPage);

		if (wid != null && !wid.isEmpty()) {
			responseMap.put("Data", lsProdList);
			responseMap.put("Status", 1);
			responseMap.put("_page_", NextPage + 1);
			addUserClickCount(request, wid);

		}
		return responseMap;
	}

	/**
	 * 获取商品和活动信息
	 * 
	 * @param wid
	 * @param NextPage
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public DisplayPhoneProdData getWeChatProdInfo(String wid, int NextPage) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		DisplayPhoneProdData lsProdList = new DisplayPhoneProdData();

		int PageCount = 6;

		checkParammap.put("Status", 1);
		List<Activity> lsActiList = new ArrayList<Activity>();
		checkParammap = new HashMap<String, Object>();
		checkParammap.put("ID", wid);
		List<WeChatPublic> wp = ReadWeChatPublicService.getWeChatPublicList(checkParammap);
		if (wp.size() > 0) {
			WeChatPublic wpl = wp.get(0);
			wpl.setCompany(SmBaseUtil.substring(wpl.getCompany(), 20));
			wpl.setWeChat(SmBaseUtil.substring(wpl.getWeChat(), 40));
			lsProdList.setWeChat(wpl);
		}
		checkParammap = new HashMap<String, Object>();
		checkParammap.put("WeChatID", wp.get(0).getID());
		checkParammap.put("IsEffect", 1);
		lsActiList = ReadActivityService.getActivityList(checkParammap);
		lsProdList.setActivitys(lsActiList);

		checkParammap = new HashMap<String, Object>();
		checkParammap.put("Status", 1);
		checkParammap.put("start", PageCount * NextPage - PageCount);
		checkParammap.put("limit", PageCount * NextPage);
		checkParammap.put("WeChatID", wp.get(0).getID());
		List<Product> lsprod = ProductService.getProductList(checkParammap);
		lsProdList.setProductList(lsprod);
		return lsProdList;

	}

	/**
	 * 作废公众号
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteWeChatGroup", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatGroup(HttpServletRequest request, HttpServletResponse response) {
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
					// 启用
					WeChatGroupService.enabledWeChatGroup(responseMap);
					responseMap = new HashMap<String, Object>();
					responseMap.put("GroupID", Long.parseLong(id));
					responseMap.put("Status", SmBaseGlobal.CheckStatus.Disabled.getid());
					List<WeChatGroupPart> lswp = WeChatGroupPartService.getWeChatGroupPartList(responseMap);
					for (WeChatGroupPart lswpitem : lswp) {

						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", lswpitem.getWeChatID());
						responseMap.put("Status", SmBaseGlobal.CheckStatus.Disabled.getid());
						int resultwp = WeChatPublicService.JoinWeChatPublic(responseMap);
						if (resultwp > 0) {
							responseMap = new HashMap<String, Object>();
							responseMap.put("GroupID", lswpitem.getID());
							responseMap.put("Status", SmBaseGlobal.CheckStatus.Disabled.getid());
							WeChatGroupPartService.enabledWeChatGroupPart(responseMap);
						}
					}
				} else {
					// 删除
					WeChatGroupService.deleteWeChatGroup(responseMap);
					responseMap = new HashMap<String, Object>();
					responseMap.put("GroupID", Long.parseLong(id));
					responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					List<WeChatGroupPart> lswp = WeChatGroupPartService.getWeChatGroupPartList(responseMap);
					for (WeChatGroupPart lswpitem : lswp) {
						responseMap = new HashMap<String, Object>();
						responseMap.put("GroupID", lswpitem.getID());
						responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
						WeChatGroupPartService.deleteWeChatGroupPart(responseMap);
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", lswpitem.getWeChatID());
						WeChatPublicService.enabledWeChatPublic(responseMap);
					}
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	/**
	 * 查询首页的信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getWeChatGroupPaJsonrtList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeChatGroupPaJsonrtList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String areaid = request.getParameter("_area_");
		String pw = request.getParameter("_pc_");// 验证powercode
		String page = request.getParameter("_page_");
		String name = request.getParameter("name");
		int NextPage = 1;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}
		if (areaid != null && !areaid.isEmpty() && pw != null && !pw.isEmpty()) {
			List<DisplayPhoneData> DispData = getDispDataList(areaid, page, "");
			if (DispData.size() <= 0) {
				responseMap.put("Status", -1);
			} else {

				responseMap.put("Status", 1);
				responseMap.put("Data", DispData);
			}
		}
		responseMap.put("_page_", NextPage + 1);
		return responseMap;
	}

	/**
	 * 查询首页的信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDealInfoList", method = RequestMethod.GET)
	public @ResponseBody
	List<DealInfo> getDealInfoList(HttpServletRequest request, HttpServletResponse response) {
		List<DealInfo> DispData = new ArrayList<DealInfo>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = request.getParameter("pid");
		responseMap.put("ParentID", pid);

		if (pid != null && !pid.isEmpty()) {
			DispData = DealInfoService.getDealInfoList(responseMap);
		}
		return DispData;
	}

	/**
	 * 根据推荐的数据来获取首页的数据
	 * 
	 * @param areaid
	 * @param pw
	 * @param page
	 * @return
	 */
	public List<DisplayPhoneData> getDispDataList(String areaid, String page, String GroupID) {
		List<DisplayPhoneData> DispData = new ArrayList<DisplayPhoneData>();
		Map<String, Object> checkParammap;

		int NextPage = 1;
		int PageCount = 6;
		if (page == null || page.isEmpty()) {
			NextPage = 1;
		} else {
			NextPage = Integer.parseInt(page);
		}
		if (areaid != null && !areaid.isEmpty()) {
			/* 查询前台所需要的数据 */
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			checkParammap.put("start", PageCount * NextPage - PageCount);
			checkParammap.put("limit", PageCount * NextPage);
			checkParammap.put("Year", ca.get(Calendar.YEAR));
			checkParammap.put("Month", ca.get(Calendar.MONTH) + 1);
			checkParammap.put("AreaID", areaid);
			List<PaidListing> paidlist = PaidListingService.getPaidListingList(checkParammap);
			for (PaidListing paidListing : paidlist) {
				DisplayPhoneData dp = new DisplayPhoneData();
				dp.setTitle(paidListing.getWeChat().getCompany());
				dp.setWeChatID(paidListing.getWeChatID());
				dp.setWeChat(paidListing.getWeChat());
				dp.setPKID(String.valueOf(paidListing.getWeChatID()));
				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				checkParammap.put("WeChatID", paidListing.getWeChatID());
				checkParammap.put("start", 0);
				checkParammap.put("limit", 1);
				checkParammap.put("IsEffect", 1);
				List<Activity> lsActivity = ReadActivityService.getActivityList(checkParammap);
				if (lsActivity.size() > 0) {
					dp.setActivity(lsActivity.get(0));
					dp.setType(1);
				}

				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				checkParammap.put("WeChatID", paidListing.getWeChatID());
				checkParammap.put("start", 0);
				checkParammap.put("limit", 2);
				List<Product> lsProd = ProductService.getProductList(checkParammap);
				if (lsProd.size() > 0) {
					dp.setType(0);
					dp.setProductList(lsProd);
				}

				checkParammap = new HashMap<String, Object>();
				checkParammap.put("Status", 1);
				checkParammap.put("start", 0);
				checkParammap.put("limit", 1);
				checkParammap.put("WeChatID", paidListing.getWeChatID());
				List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
				if (lsvideo.size() > 0) {
					dp.setVideo(lsvideo.get(0));
					dp.setType(1);
				}

				DispData.add(dp);
			}

			if (DispData.size() < PageCount) {

				checkParammap = new HashMap<String, Object>();
				checkParammap.put("NoEqStatus", 0);
				checkParammap.put("start", PageCount * NextPage - PageCount);
				checkParammap.put("limit", (PageCount * NextPage - DispData.size()));
				checkParammap.put("AreaID", areaid);
				List<WeChatPublic> lsWc = ReadWeChatPublicService.getWeChatPublicList(checkParammap);
				for (WeChatPublic weChatPublic : lsWc) {
					DisplayPhoneData dp = new DisplayPhoneData();
					dp.setTitle(weChatPublic.getCompany());
					dp.setWeChatID(weChatPublic.getID());
					dp.setWeChat(weChatPublic);
					dp.setPKID(String.valueOf(weChatPublic.getID()));

					checkParammap = new HashMap<String, Object>();
					checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					checkParammap.put("WeChatID", weChatPublic.getID());
					checkParammap.put("start", 0);
					checkParammap.put("limit", 1);
					checkParammap.put("IsEffect", 1);
					List<Activity> lsActivity = ReadActivityService.getActivityList(checkParammap);

					if (lsActivity.size() > 0) {

						dp.setActivity(lsActivity.get(0));
						dp.setType(1);
					}
					checkParammap = new HashMap<String, Object>();
					checkParammap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					checkParammap.put("WeChatID", weChatPublic.getID());
					checkParammap.put("start", 0);
					checkParammap.put("limit", 2);
					List<Product> lsProd = ProductService.getProductList(checkParammap);
					if (lsWc.size() > 0) {
						dp.setType(0);
						dp.setProductList(lsProd);
					}

					checkParammap = new HashMap<String, Object>();
					checkParammap.put("Status", 1);
					checkParammap.put("start", 0);
					checkParammap.put("limit", 1);
					checkParammap.put("WeChatID", weChatPublic.getID());
					List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
					if (lsvideo.size() > 0) {
						dp.setVideo(lsvideo.get(0));
						dp.setType(1);
					}
					DispData.add(dp);

				}
			}
		}
		return DispData;
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

	/**
	 * 获得区，市，省的数据
	 * 
	 * @param checkParammap
	 * @param paid
	 * @param DispData
	 */
	private void getMes(Map<String, Object> checkParammap, String paid, List<DisplayWeNewsPhoneData> DispData,
			read.core.service.ReadNoticesService ReadNoticesService) {
		checkParammap.put("ParentID", paid);
		List<Region> Areas = RegionService.getRegionList(checkParammap);// 获取啦所有关于该地区的学校
		for (int i = 0; i < Areas.size(); i++) {
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("AreaID", Areas.get(i).getID());
			List<Notices> qlsns = ReadNoticesService.getReadNoticesList(checkParammap);

			if (qlsns.size() > 0) {
				for (int j = 0; j < qlsns.size(); j++) {
					try {
						date0 = df.parse(df.format(new Date()));
						date2 = df2.parse(qlsns.get(j).getCreateTime());
					} catch (ParseException e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
					number1 = date2.compareTo(date0);
					if ((number1 == 0 || number1 == 1) && qlsns.get(j).getIsDel() == 0) {
						sum++;
						DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
						dp.setTitle(qlsns.get(j).getTitle());
						dp.setNoticesList(qlsns.get(j));
						dp.setPKID(String.valueOf(qlsns.get(j).getID()));
						dp.setCreateTime(qlsns.get(j).getCreateTime());
						dp.setType(2);
						DispData.add(dp);
					}
					if (sum == 10) {
						break;// 控制新的条数
					}
				}
			}
		}

		for (int i = 0; i < Areas.size(); i++) {
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("AreaID", Areas.get(i).getID());
			List<Notices> qlsns = ReadNoticesService.getReadNoticesList(checkParammap);

			if (qlsns.size() > 0) {
				for (int j = 0; j < qlsns.size(); j++) {
					try {
						date0 = df.parse(df.format(new Date()));
						date2 = df2.parse(qlsns.get(j).getCreateTime());
					} catch (ParseException e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
					number1 = date2.compareTo(date0);
					if ((number1 == -1) && qlsns.get(j).getIsDel() == 0) {
						DisplayWeNewsPhoneData dp = new DisplayWeNewsPhoneData();
						dp.setTitle(qlsns.get(j).getTitle());
						dp.setNoticesList(qlsns.get(j));
						dp.setPKID(String.valueOf(qlsns.get(j).getID()));
						dp.setCreateTime(qlsns.get(j).getCreateTime());
						dp.setType(2);
						DispData.add(dp);
					}
				}
			}
		}

	}

	@RequestMapping(value = "/phoneSinaBallotList", method = RequestMethod.GET)
	private ModelAndView phoneSinaBallotList(HttpServletRequest req, HttpServletResponse response, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneSinaBallotList");

	}

	/**
	 * 获取新浪小编排名信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getSinaBallotList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSinaBallotList(String school_level, int start_page, String keywords) throws UnsupportedEncodingException {
		Map<String, Object> outMap = new HashMap<String, Object>();
		int page = 20;
		start_page = start_page * page;

		String url = "http://sina.ztnet.com.cn/index.php/Admin/Api/?school_level=" + school_level + "&start_page=" + start_page + "&pages=" + page
				+ "&keywords=" + keywords;
		JSONObject json = SmBaseUtil.SendGetRequestURL(url);
		json = JSONObject.fromObject(json.get("Data"));
		if(json!=null){
			Iterator<String> nameItr = json.keys();
			String name;
	
			while (nameItr.hasNext()) {
				name = nameItr.next();
				outMap.put(name, json.getString(name));
			}
		}
		return outMap;
	}

}