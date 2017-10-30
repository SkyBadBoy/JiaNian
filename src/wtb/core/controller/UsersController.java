package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
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

import com.alibaba.fastjson.JSON;

import wtb.core.model.BaseInfo;
import wtb.core.model.ChangePassWord;
import wtb.core.model.Permissions;
import wtb.core.model.Pictures;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.VerifyRecord;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatPublicSimple;
import wtb.sessions.MySessionContext;
import wtb.smUtil.BaseUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.ResultUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.VerifyCode;

/**
 * wtb_users
 */
@Controller
@RequestMapping("Users")
public class UsersController extends BaseController {
	
	
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView login(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		Users user = null;
		user = (Users) req.getSession().getAttribute("UserName");
		if (user != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("To", user.getID());
			params.put("IsRead", 0);
			int messageCount = MessagesService.getMessagesCount(params);
			model.addAttribute("MesageCount", messageCount);
		}

		return new ModelAndView(SmBaseGlobal.BaseViewPath + "index");
	}


	@RequestMapping(value = "/UserList", method = RequestMethod.GET)
	public ModelAndView UserList(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpServletRequest request,
			HttpSession session, Model model) {
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, "");
		model.addAttribute("QueryAdminLevel", getQueryAdminLevel(session));
		return new ModelAndView(SmBaseGlobal.WebViewPath + "UserList");

	}

	@RequestMapping(value = "/phoneaccountsecurity", method = RequestMethod.GET)
	public ModelAndView phoneaccountsecurity(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		model.addAttribute("user", user);
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneaccountsecurity");

	}

	@RequestMapping(value = "/phoneuserinfo", method = RequestMethod.GET)
	public ModelAndView phoneuserinfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		WeChatPublicSimple lswp = ReadWeChatPublicService.getSimpleWeChatPublicList(user.getWeChatID());

		model.addAttribute("Company", (lswp == null) ? "" : lswp.getCompany());
		model.addAttribute("User", user.getName());
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneuserinfo");
	}

	@RequestMapping(value = "/phoneuserinfoResult", method = RequestMethod.GET)
	public ModelAndView phoneuserinfoResult(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Users user = (Users) session.getAttribute("StudentName");
		String Name = request.getParameter("Name");
		String Company = request.getParameter("Company");
		if (!Name.isEmpty()) {
			Name = SmBaseUtil.URLDecoderString(Name);
		}
		if (!Company.isEmpty()) {
			Company = SmBaseUtil.URLDecoderString(Company);
		}

		user.setName(Name);
		UsersService.updateUsers(user);
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ID", user.getWeChatID());
		List<WeChatPublic> lswp = ReadWeChatPublicService.getWeChatPublicList(responseMap);

		if (lswp.size() > 0) {
			lswp.get(0).setCompany(Company);
			WeChatPublicService.updateWeChatPublic(lswp.get(0));
			model.addAttribute("WeChatPublic", lswp.get(0));
			model.addAttribute("WeChatID", user.getWeChatID());
		}
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneIndex");
	}

	/* 获取普通管理员 */
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getUserList(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, Model model) throws UnsupportedEncodingException {
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String Name = request.getParameter("Name");
		System.out.println(Name + "姓名");
		String state = request.getParameter("Status");
		String LevelID = request.getParameter("LevelID");

		String AreaAreaID = request.getParameter("AreaID");
		String AreaID = request.getParameter("UnitAreaID");
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));

		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		} else {
			checkParammap.put("Status", state);
		}
		if (Name != null && !Name.isEmpty()) {
			Name = SmBaseUtil.URLDecoderString(Name);
			checkParammap.put("Name", Name);
		}
		if (LevelID != null && !LevelID.isEmpty()) {
			if (LevelID.equals(SmBaseGlobal.LevelStatus.AreaManage.getid())) {
				AreaID = AreaAreaID;
			}
		}
		if (AreaID != null && !AreaID.isEmpty() && Integer.parseInt(AreaID) > 0) {
			Boolean iscon = true;
			if (LevelID != null && !LevelID.isEmpty() && LevelID.equals(SmBaseGlobal.LevelStatus.Manage.getid())
					&& LevelID.equals(SmBaseGlobal.LevelStatus.SuperManage.getid())) {
				iscon = false;
			}
			if (iscon) {
				if (Integer.parseInt(LevelID) == 4) {

				} else {
					checkParammap.put("AreaID", AreaID);
				}

			}

		}
		if (LevelID != null && !LevelID.isEmpty()) {
			checkParammap.put("Level", LevelID);
		}
		checkParammap.put("NoQueryLevel", 1);
		return getUserListByCurrentUser(checkParammap, session);

	}

	/**
	 * 用户添加可以选择的级别
	 * 
	 * @param session
	 * @return
	 */
	private List<BaseInfo> getAdminLevel(HttpSession session) {
		List<BaseInfo> bases = new ArrayList<BaseInfo>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		if (user != null) {
			if (user.getLevel() == SmBaseGlobal.LevelStatus.SuperManage.getid()) {
				// 添加管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.Manage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加区域管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.AreaManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.Manage.getid()) {// 普通管理员
																					// 只查询地区管理员和商家

				// 添加区域管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.AreaManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {

				// 添加教师管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.TeacherManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
			}
		}
		return bases;
	}

	/**
	 * 用户查询可以选择的级别
	 * 
	 * @param session
	 * @return
	 */
	private List<BaseInfo> getQueryAdminLevel(HttpSession session) {
		List<BaseInfo> bases = new ArrayList<BaseInfo>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		if (user != null) {
			if (user.getLevel() == SmBaseGlobal.LevelStatus.SuperManage.getid()) {
				// 添加管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.Manage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加区域管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.AreaManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加教师管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.TeacherManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.Manage.getid()) {// 普通管理员
																					// 只查询地区管理员和商家

				// 添加区域管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.AreaManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加教师管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.TeacherManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
				// 添加校级管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.ParsonManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
				// 添加教师管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.TeacherManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));

			} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {

				// 添加教师管理员
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", SmBaseGlobal.LevelStatus.TeacherManage.getid());
				bases.addAll(BaseInfoService.getBaseInfoListByID(responseMap));
			}
		}
		return bases;
	}

	/*
	 * 根据当前的登录用户判断需要查询的用户列表信息
	 */
	public Map<String, Object> getUserListByCurrentUser(Map<String, Object> responseMap, HttpSession session) {
		Users user = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		user = (Users) session.getAttribute("UserName");
		// 超级管理员 不查询自己
		if (user.getLevel() == SmBaseGlobal.LevelStatus.SuperManage.getid()) {
			responseMap.put("NoEqLevel", SmBaseGlobal.LevelStatus.SuperManage.getid());
		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.Manage.getid()) {// 普通管理员
																				// 只查询地区管理员和商家
			responseMap.put("NoEqLevel", SmBaseGlobal.LevelStatus.Manage.getid());
			responseMap.put("NoQueryLevel", SmBaseGlobal.LevelStatus.SuperManage.getid());

		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			responseMap.put("Level", SmBaseGlobal.LevelStatus.ParsonManage.getid());
			responseMap.put("AreaID", user.getAreaID());
		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
			responseMap.put("Level", SmBaseGlobal.LevelStatus.TeacherManage.getid());
			responseMap.put("AreaID", user.getAreaID());
		}
		resultMap = new HashMap<String, Object>();
		resultMap.put("data", ReadUsersService.getHomeUsersList(responseMap));
		resultMap.put("total", ReadUsersService.getUsersCount(responseMap));
		resultMap.put("Status", 1);
		return resultMap;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute("usersForm") Users users, HttpServletRequest request, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model) {
		List<Users> userls = new ArrayList<Users>();

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		model.addAttribute("type", type);
		String AreaID = "";
		int isEdit = 0;

		if (uid != null && !uid.isEmpty()) {
			checkParammap = new HashMap<String, Object>();
			// checkParammap.put("Status", 1);
			checkParammap.put("ID", uid);
			userls = ReadUsersService.getUsersList(checkParammap);
			if (userls.size() > 0) {
				model.addAttribute("usersForm", userls.get(0));
				model.addAttribute("Level", userls.get(0).getLevel());
				if (userls.get(0).getAreaID() > 0) {
					AreaID = String.valueOf(userls.get(0).getAreaID());
					checkParammap = new HashMap<String, Object>();
					checkParammap.put("ID", userls.get(0).getWeChatID());
					List<WeChatPublic> wgls = ReadWeChatPublicService.getWeChatPublicList(checkParammap);
					if (wgls.size() > 0) {
						model.addAttribute("WeChatName", wgls.get(0).getWeChat());
					}
				}
			}
			isEdit = 1;
		}

		model = RegionController.getRegionParams(request, ReadRegionService, model, session, AreaID);
		model.addAttribute("isEdit", isEdit);
		model.addAttribute("AdminLevelList", getAdminLevel(session));

		if (userls.size() > 0 && userls.get(0).getStatus() == SmBaseGlobal.CheckStatus.Disabled.getid()) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewUser");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addUser");
		}
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUserResult(@ModelAttribute("usersForm") Users users, @RequestParam("CityAreaID") String AreaID,
			@RequestParam("AreaID") String AreaAreaID, @RequestParam("LevelID") String Level, HttpServletRequest request, BindingResult result,
			HttpServletResponse response, HttpSession session, Model model) {
		AreaID = request.getParameter("CityAreaID");

		if (Integer.parseInt(Level) == 4) {
			AreaID = "0";
		}
		boolean isNew = false;
		String Password = users.getPassWord();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		long UserID = 0;
		if (users.getID() > 0) {
			UserID = users.getID();
		} else {
			UserID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		users.setAreaID(Long.parseLong(AreaID));
		List<String[]> lsError = AddOrEnabledUserCheck(users);
		for (String[] strings : lsError) {
			result.rejectValue(strings[0], "misFormat", strings[1]);
		}

		if (!result.hasErrors()) {
			if (isNew) {
				Users user = (Users) session.getAttribute("UserName");
				if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
					users.setWeChatID(user.getWeChatID());
					AreaID = String.valueOf(user.getAreaID());
				}
				if (users.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
					AreaID = AreaAreaID;
				}
				users.setPassWord(SmBaseUtil.MD5(Password));
				users.setCreateTime(new Date());
				users.setModifyTime(new Date());
				users.setStatus(1);
				users.setID(new IdWorker(1, 0).nextId());
				users.setImageID(0);
				users.setLevel(Integer.parseInt(Level));
				users.setAreaID(Long.parseLong(AreaID));
				int addresult = UsersService.addUsers(users);
				if (addresult <= 0) {
					result.rejectValue("LoginName", "misFormat", "保存失败,请重试");
				}
			} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", UserID);
				List<Users> lsuser = ReadUsersService.getUsersList(responseMap);
				if (lsuser.size() > 0) {
					Users user = lsuser.get(0);
					if (!Password.isEmpty()) {
						user.setPassWord(SmBaseUtil.MD5(Password));
					}
					if(user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid()){
						AreaID = request.getParameter("UnitID");
					}
					user.setName(users.getName());
					user.setPhone(users.getPhone());
					user.setModifyTime(new Date());
					user.setAreaID(Long.parseLong(AreaID));
					user.setLevel(Integer.parseInt(Level));

					int addresult = UsersService.updateUsers(user);
					if (addresult <= 0) {
						result.rejectValue("LoginName", "misFormat", "保存失败,请重试");
					}
				}
			}

		}
		model = RegionController.getRegionParams(request, ReadRegionService, model, session, AreaID);
		model.addAttribute("AdminLevelList", getAdminLevel(session));
		model.addAttribute("AdminLevel", users.getLevel());
		if (result.hasErrors()) {
			model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addUser");
		}
		if (!result.hasErrors()) {
			model.addAttribute("type", "2");
			model.addAttribute("value", users.getName());
			Users user = (Users) session.getAttribute("UserName");
			if (users.getID() == user.getID()) {
				model.addAttribute("title", "返回");
				model.addAttribute("content", request.getContextPath() + "/Users/addUser?type=self&uid=" + users.getID());
			} else {
				model.addAttribute("content", request.getContextPath() + "/Users/UserList");
			}
			return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addUser");

	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpServletRequest request,
			HttpSession session, Model model) {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		List<Users> lsUser;
		if (users.getLoginName().isEmpty()) {
			result.rejectValue("LoginName", "misFormat", "用户名不能为空");
		} else {
			responseMap.put("LoginName", users.getLoginName());
		}
		if (users.getPassWord().isEmpty()) {
			result.rejectValue("PassWord", "misFormat", "密码不能为空");
		} else {
			responseMap.put("PassWord", SmBaseUtil.MD5(users.getPassWord()));
		}
		if (responseMap.size() >= 2) {
			lsUser = ReadUsersService.getUsersList(responseMap);
			if (lsUser.size() <= 0) {
				result.rejectValue("PassWord", "misFormat", "用户名或者密码错误");
			} else {
				if (lsUser.get(0).getStatus() == SmBaseGlobal.CheckStatus.Disabled.getid()) {
					result.rejectValue("PassWord", "misFormat", "用户已停用,请联系管理员");
				}
				if (!result.hasErrors()) {
					users = lsUser.get(0);
				}
			}

		}

		if (result.hasErrors()) {
			model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.BaseViewPath + "login");
		} else {
			session = request.getSession();
			MySessionContext myc = MySessionContext.getInstance();
			myc.AddSession(session);
			session.setAttribute("UserName", users);
		}
		Users user = (Users) request.getSession().getAttribute("UserName");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("To", user.getID());
		params.put("IsRead", 0);
		int messageCount = MessagesService.getMessagesCount(params);
		model.addAttribute("MesageCount", messageCount);
		model.addAttribute("UserLevel", users.getLevel());

		return new ModelAndView(SmBaseGlobal.BaseViewPath + "index");

	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView Checklogin(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session,
			Model model) {

		return new ModelAndView(SmBaseGlobal.BaseViewPath + "login");
	}

	@RequestMapping(value = "/phonechangepassword", method = RequestMethod.GET)
	public ModelAndView phonechangepassword(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session,
			Model model) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonechangepassword");
	}

	@RequestMapping(value = "/phonechangepassword", method = RequestMethod.POST)
	public ModelAndView phonechangepasswordResult(@ModelAttribute("usersForm") Users users, HttpServletRequest req, @RequestParam("AuthCode") String AuthCode,
			BindingResult result, HttpServletResponse response, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String Type = req.getParameter("Type");

		if (Type.equals("1")) {
			responseMap.put("LoginName", users.getLoginName());
			List<Users> user = ReadUsersService.getUsersList(responseMap);
			if (user.size() > 0) {
				model.addAttribute("usersForm", user.get(0));// 把accountVo对象返回到页面，这样不至于表单被清空了
				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonechangepassword_2");
			} else {
				result.rejectValue("LoginName", "misFormat", "该帐号(微信号)不存在");
				model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonechangepassword");
			}

		}
		String Message = SmBaseUtil.CheckAuthCode(session, AuthCode);
		if (Message != null) {
			result.rejectValue("ID", "misFormat", Message);
		} else {
			responseMap.put("ID", users.getWeChatID());
			List<Users> Users = new ArrayList<Users>();
			responseMap = new HashMap<String, Object>();
			responseMap.put("WeChatID", users.getWeChatID());
			Users = ReadUsersService.getUsersList(responseMap);

			Users user = new Users();
			if (Users.size() > 0) {
				user = Users.get(0);
			}
			user.setPassWord(SmBaseUtil.MD5(users.getPassWord()));
			int proResult = UsersService.updateUsers(user);
			if (proResult > 0 && !result.hasErrors()) {

				model.addAttribute("area", user.getAreaID());
				
				return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "modifysuccess");
			} else {
				result.rejectValue("ID", "misFormat", "验证失败,请重试");
			}
		}

		model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonelogin");
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/AuthenticationUser", method = RequestMethod.POST)
	public ModelAndView phoneloginResult(@ModelAttribute("usersForm") Users users, @RequestParam("AuthCode") String AuthCode, BindingResult result,
			HttpServletResponse response, HttpSession session, HttpServletRequest req, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String AuthType = req.getParameter("AuthType");
		model.addAttribute("AuthType", AuthType);
		String passwd = users.getPassWord();

		String Message = SmBaseUtil.CheckAuthCode(session, AuthCode);
		if (Message != null) {
			result.rejectValue("ID", "misFormat", Message);
		} else {
			int proResult = 0;

			List<Users> Users = new ArrayList<Users>();
			responseMap = new HashMap<String, Object>();
			responseMap.put("LoginName", users.getPhone());
			Users = ReadUsersService.getUsersList(responseMap);

			responseMap = new HashMap<String, Object>();
			responseMap.put("AreaID", users.getAreaID());
			List<WeChatPublic> lswechat = ReadWeChatPublicService.getWeChatPublicList(responseMap);

			Users user = new Users();
			if (Users.size() > 0) {
				user = Users.get(0);
			}
			user.setWeChatID(lswechat.size() > 0 ? lswechat.get(0).getID() : 0);
			user.setPassWord(SmBaseUtil.MD5(passwd));
			user.setPhone(users.getPhone());
			user.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			proResult = UsersService.updateUsers(user);

			if (proResult > 0 && !result.hasErrors()) {
				if (AuthType != null && AuthType.equals("Student")) {
					model.addAttribute("SuccessMessage", "您已成功注册,请进行登录");
					return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonelogin");
				} else {
					model.addAttribute("HomeURL",
							"weChatGroup/phoneWeNewsHome?_wechat_=" + String.valueOf(users.getWeChatID()) + "&&_area_=" + String.valueOf(users.getAreaID()));
					model.addAttribute("LoginURL", req.getContextPath() + "/Users/login");
					model.addAttribute("SuccessType", "ParsonManage");
					return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "applysuccess");
				}
			}

			if (proResult <= 0) {
				result.rejectValue("ID", "misFormat", "验证失败,请重试");
			}
		}

		model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "AuthenticationUser");
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return String
	 * @throws IOException
	 */
	@RequestMapping(value = "/phonelogin", method = RequestMethod.GET)
	public ModelAndView phonelogin(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session,
			Model model) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonelogin");
	}

	@RequestMapping(value = "/phonehelp", method = RequestMethod.GET)
	public ModelAndView phonehelp(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonehelp");
	}

	@RequestMapping(value = "/ServerRole", method = RequestMethod.GET)
	public ModelAndView ServerRole(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response, HttpSession session,
			Model model) {
		return new ModelAndView(SmBaseGlobal.BaseViewPath + "ServerRole");
	}

	@RequestMapping(value = "/phonelogin", method = RequestMethod.POST)
	public ModelAndView phoneloginResult(@ModelAttribute("usersForm") Users users, BindingResult result, HttpServletResponse response,
			HttpServletRequest request, HttpSession session, Model model) throws ServletException, IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		List<Users> lsUser;
		if (users.getLoginName().isEmpty()) {
			result.rejectValue("LoginName", "misFormat", "用户名不能为空");
		} else {
			responseMap.put("LoginName", users.getLoginName());
		}
		if (users.getPassWord().isEmpty()) {
			result.rejectValue("PassWord", "misFormat", "密码不能为空");

		} else {
			responseMap.put("PassWord", SmBaseUtil.MD5(users.getPassWord()));
		}

		responseMap.put("NoEqLevel", SmBaseGlobal.LevelStatus.Manage.getid());
		responseMap.put("NoEqLevel", SmBaseGlobal.LevelStatus.Manage.getid());
		if (!result.hasErrors() && responseMap.size() >= 2) {

			lsUser = ReadUsersService.getUsersList(responseMap);

			if (lsUser.size() <= 0) {
				result.rejectValue("PassWord", "misFormat", "用户名或者密码错误");
			} else {
				if (lsUser.get(0).getStatus() == SmBaseGlobal.CheckStatus.Disabled.getid()) {
					result.rejectValue("PassWord", "misFormat", "用户已停用,请联系管理员");
				}
				users = lsUser.get(0);
			}

		}

		if (result.hasErrors()) {
			model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView("phonelogin");
		} else {
			session = request.getSession();
			MySessionContext myc = MySessionContext.getInstance();
			myc.AddSession(session);
			session.setAttribute("StudentName", users);
		
		}

		WeChatPublicSimple lswp = ReadWeChatPublicService.getSimpleWeChatPublicList(users.getWeChatID());
		if (lswp != null) {
			model.addAttribute("WeChatPublic", lswp);
			model.addAttribute("WeChatID", users.getWeChatID());
			model.addAttribute("AreaID", users.getAreaID());
		}
		return new ModelAndView("phoneIndex");

	}

	@RequestMapping(value = "/phoneIndex", method = RequestMethod.GET)
	public ModelAndView phoneIndex(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) {

		Users user = (Users) session.getAttribute("StudentName");

		WeChatPublicSimple lswp = ReadWeChatPublicService.getSimpleWeChatPublicList(user.getWeChatID());
		if (lswp != null) {
			model.addAttribute("WeChatPublic", lswp);
			model.addAttribute("AreaID", user.getAreaID());
		}
		model.addAttribute("StudentName", user);
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phoneIndex");
	}

	@RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAuthCode(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> queryMap=new HashMap<>();
		String type=request.getParameter("type");//1 注册   2忘记密码    3通用性验证
		String Phone = request.getParameter("Phone");
		if(!SmBaseUtil.isNumeric(Phone)){
			result.put("Status", false);
			result.put("Message", "手机号为空,请重新输入！");
			return result;
		}
		if(!SmBaseUtil.isNumeric(type)){
			type="3";
		}
		if (type!=null) {
			if (Integer.parseInt(type)==1) {//注册的时候要检查该手机号码是不是已经与其他用户绑定啦，绑定的话要提示用户请用微信登录，或者联系客服
				if (Phone!=null) {
					Students stu = (Students) request.getSession().getAttribute("StudentName");
					queryMap=new HashMap<>();
					queryMap.put("Phone", Phone);
					int xbFlag=ReadStudentsService.getStudentsCount(queryMap);
					if (xbFlag>0) {
						result.put("Status", false);
						result.put("Message", "该手机号码已被注册！");
					}else{
						String checkNum = amBaseUtil.getCheckNu(6);
						session.setAttribute("AuthenCode", checkNum);
						session.setAttribute("AuthenPhone", Phone);
						System.err.println(checkNum);
						//自己测试，所以注释掉
	
							VerifyCode verifycode=new VerifyCode();
							verifycode.sendCheckNumber(Phone, checkNum,request.getServletContext());
							System.err.println(checkNum);
						result.put("Status", true);
						result.put("Message", "验证码已发送至您的手机，请注意查收");
					}
				}else{
					result.put("Status", false);
					result.put("Message", "手机号为空,请重新输入！");
				}
			}else if(Integer.parseInt(type)==2){
				queryMap.put("TPhone", Phone);
				int regitFlag=ReadStudentsService.getStudentsCount(queryMap);
				if (regitFlag==0) {
					result.put("Status", false);
					result.put("Message", "该手机号码还未注册！");
				}else{
					String checkNum = amBaseUtil.getCheckNu(6);
					session.setAttribute("AuthenCode", checkNum);
					session.setAttribute("AuthenPhone", Phone);
					//自己测试，所以注释掉
		
						VerifyCode verifycode=new VerifyCode();
						verifycode.sendCheckNumber(Phone, checkNum,request.getServletContext());
						System.err.println(checkNum);
					
					
					result.put("Status", true);
					result.put("Message", "验证码已发送至您的手机，请注意查收");
				}
			}else if(Integer.parseInt(type)==3){
				String checkNum = amBaseUtil.getCheckNu(6);
				session.setAttribute("AuthenCode", checkNum);
				session.setAttribute("AuthenPhone", Phone);
				session.setAttribute("auth", "1");
				
				//自己测试，所以注释掉
		
					VerifyCode verifycode=new VerifyCode();
					verifycode.sendCheckNumber(Phone, checkNum,request.getServletContext());
					System.err.println(checkNum);
			
				
				result.put("Status", true);
				result.put("Message", "验证码已发送至您的手机，请注意查收");
				
			}
		}else{
			result.put("Status", false);
			result.put("Message", "类型不能为空");
		}
		return result;
	}
	
	/**
	 * 
	 * @Author 作者：马健
	 * @Phone  联系qq：1039510196
	 * @CreateTime 创建时间：2017年7月12日 下午5:19:04
	 * @Details 授权SAAS服务所用
	 */
	@RequestMapping(value = "/getStudentData", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getStudentData(HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) {
		String Phone = request.getParameter("Phone");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> map=new HashMap<>();
		map.put("TPhone", Phone);
		map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		map.put("Rand", SmBaseUtil.Random());
		List<Students> students=ReadStudentsService.getStudentsList(map);
		if (students.size()>0) {
			result.put("status", true);
			result.put("Data", students.get(0));
			result.put("message", "获取该学生成功");
		}else{
			result.put("status", false);
			result.put("message", "认证失败，未找到这个学生！");
		}
			
		return result;
	}
	
	
	
	
	
	
	
//    @RequestMapping(value = "SendCheckCode", method = RequestMethod.GET)
//    public @ResponseBody Map<String, Object> SendCheckCode(@RequestParam(required=false) String mobile) {
//    	Map<String, Object> result = new HashMap<String, Object>();
//
//        if (mobile == null || mobile.equals("")) {
//        	result.put("Status", false);
//			result.put("Message", "手机号码不能为空");
//        	
//        } else {
//            if (!VerifyCode.sharedInstance().checkNumberVerify(mobile)) {
//               	result.put("Status", false);
//    			result.put("Message", "号码格式不正确");
//            } else {
//               // String checkNum = amBaseUtil.getCheckNu(4);
//                // 内测版固定验证码
//                String number = "1234";
//
//                Map<String, Object> verifyRecordQuery = new HashMap<String, Object>();
//                verifyRecordQuery.put(VerifyRecord.attributeVerifyRecordPhone, mobile);
//
//                List<VerifyRecord> verifyRecordList = VerifyRecordService.queryVerifyRecord(verifyRecordQuery);
//                boolean isFindVerifyRecord = false;
//                VerifyRecord verifyRecord;
//
//                if (verifyRecordList != null && verifyRecordList.size() > 0) { //  返回最后一个登陆验证的记录
//                    isFindVerifyRecord = true; // 标记数据库已存在该手机号码的验证码记录
//                    verifyRecord = verifyRecordList.get(0);
//
//                    if (verifyRecord.getStatus() == BaseUtil.UserStatus.Disabled.getCode()) { // 手机号被禁用了
//                        Date nowDate = new Date();
//
//                        if (nowDate.compareTo(verifyRecord.getEnableTime()) <= 0) { // 还处于禁用时间内
//                        	result.put("Status", false);
//                			result.put("Message", "禁用期间不能登录");
//                            return result;
//                        } else { // 已经不在禁用时间内登录了
//                            verifyRecord.setStatus(BaseUtil.UserStatus.Enable.getCode());
//                            verifyRecord.setCheckNumber(number);
//                        }
//                    } else if (verifyRecord.getStatus() == BaseUtil.UserStatus.Invalid.getCode()) { // 封号
//                    	result.put("Status", false);
//            			result.put("Message", "手机已经被封号未能通过");
//                        return result;
//                    } else {    //  账号可正常使用, 判断两次获取短信验证码的时间间隔
//                        Date lastDate = verifyRecord.getModifyTime();
//                        long stamp = ((new Date().getTime()) - lastDate.getTime()) / 1000; // 单位是毫秒, 除以 1000 精确到秒
//
//                        //  ToDo 正式环境一分钟内不能重复发, 测试环境为 5 秒
//                        if (Integer.parseInt(Long.toString(stamp), 10) < 60) { // 10 表示十进制 一分钟内不能重复发送
//                        	result.put("Status", false);
//                			result.put("Message", "操作次数过于频繁");
//                            return result;
//                        } else {
//                            verifyRecord.setCheckNumber(number);
//                        }
//                    }
//                } else {    //  用户首次获取验证码, 需在数据库保存获取验证码记录
//                    verifyRecord = new VerifyRecord();
//                    verifyRecord.setID(SmBaseUtil.CreateNewID());//  避免使用 0 , 0 在系统中有特殊使命
//                    verifyRecord.setPhone(mobile);
//                    verifyRecord.setCheckNumber(number);
//                    verifyRecord.setStatus(BaseUtil.UserStatus.Enable.getCode());
//                }
//
//                boolean isSendResult = true;//code.sendCheckNumber(mobile, number);
//
//                if (isSendResult) { // 发送成功
//                    int verifyRecordFlag = 0;
//
//                    if (isFindVerifyRecord) { // 数据库已经存在更新记录
//                        verifyRecordFlag = VerifyRecordService.changeVerifyRecord(verifyRecord);
//                    } else { // 数据库不存在则插入记录
//                        verifyRecordFlag = VerifyRecordService.addVerifyRecord(verifyRecord);
//                    }
//                    if (verifyRecordFlag > 0) {
//        	            result.put("Status",true);
//            			result.put("Message", "验证码已发送,请在半小时内完成输入！");
//                    } else {
//                        result.put("Status", false);
//            			result.put("Message", "获取验证码失败");
//                    }
//                } else { // 发送失败
//                    result.put("Status", false);
//        			result.put("Message", "获取验证码失败");
//                }
//            }
//        }
//        return result;
//    }
    
    
    
	@RequestMapping(value = "/loginOut", method = RequestMethod.GET)
	public ModelAndView loginOut(@ModelAttribute("usersForm") Users users, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		Users user = null;
		session.setAttribute("UserName", user);

		session.removeAttribute("UserName");

		Cookie ck = new Cookie("JSESSIONID", System.currentTimeMillis() + "");
		ck.setMaxAge(-3600);
		response.addCookie(ck);
		return new ModelAndView(SmBaseGlobal.BaseViewPath + "login");
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public ModelAndView changepassword(@ModelAttribute("usersForm") ChangePassWord users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "changepassword");
	}

	@RequestMapping(value = "/phonepasswordedit", method = RequestMethod.GET)
	public ModelAndView phonepasswordedit(@ModelAttribute("usersForm") ChangePassWord users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {

		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonepasswordedit");
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public ModelAndView changepasswordResult(@ModelAttribute("usersForm") ChangePassWord users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {
		String submittype = null;
		submittype = request.getParameter("submittype");
		String OldPassword = users.getOldPassWord();
		String NewPassword = users.getNewPassWord();
		String Confirm_NewPassword = users.getConfirm_NewPassWord();
		Users user = (Users) session.getAttribute("UserName");
		if (!NewPassword.equals(Confirm_NewPassword)) {
			result.rejectValue("Confirm_NewPassword", "misFormat", "两次密码不一致");
		}
		if (user == null) {
			response.sendRedirect("login");
		} else {
			Map<String, Object> checkParammap = new HashMap<String, Object>();
			String MD5Password = SmBaseUtil.MD5(OldPassword);
			String MD5NewPassword = SmBaseUtil.MD5(NewPassword);

			if (MD5Password.equals(user.getPassWord()) && !result.hasErrors()) {
				checkParammap.put("LoginName", user.getLoginName());
				checkParammap.put("OldPassWord", MD5Password);
				checkParammap.put("NewPassWord", MD5NewPassword);
				int proResult = UsersService.ChangePassword(checkParammap);
				if (proResult > 0) {
					user.setPassWord(MD5NewPassword);
					session.setAttribute("UserName", user);

					if (submittype != null && submittype.equals("phone")) {
						model.addAttribute("content", request.getContextPath() + "/Users/phoneIndex");
						return new ModelAndView(SmBaseGlobal.MobileViewPath + "modifysuccess");
					} else {
						model.addAttribute("content", request.getContextPath() + "/home");
						model.addAttribute("title", "返回首页");
						return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
					}
				} else {
					result.rejectValue("LoginName", "misFormat", "保存失败,请重试");
				}
			} else {
				result.rejectValue("OldPassWord", "misFormat", "老密码不正确");
			}

		}
		if (result.hasErrors()) {
			model.addAttribute("usersForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
			if (submittype != null && submittype.equals("phone")) {
				return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonepasswordedit");
			} else {
				return new ModelAndView(SmBaseGlobal.WebViewPath + "changepassword");
			}
		}
		if (submittype != null && submittype.equals("phone")) {
			return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonepasswordedit");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "changepassword");
		}
	}

	@RequestMapping(value = "/ModifyMyInfo", method = RequestMethod.GET)
	public ModelAndView ModifyMyInfo(@ModelAttribute("usersForm") Users users, HttpServletRequest request, HttpServletResponse response, BindingResult result,
			Model model, HttpSession session) throws Exception {
		Users user = null;

		user = (Users) session.getAttribute("UserName");
		model.addAttribute("usersForm", user);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ModifyMyInfo");
	}

	@RequestMapping(value = "/ModifyMyInfo", method = RequestMethod.POST)
	public ModelAndView ModifyMyInfoResult(@ModelAttribute("usersForm") Users users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {
		Users user = null;

		user = (Users) session.getAttribute("UserName");

		if (users.getName().length() > 20) {
			result.rejectValue("LoginName", "misFormat", "用户名不能超过20个字符");
		} else {
			user.setName(users.getName());
			session.setAttribute("UserName", user);
			int proResult = UsersService.updateUsers(user);
			if (proResult > 0) {
				model.addAttribute("type", "2");
				model.addAttribute("value", users.getName());
				model.addAttribute("content", request.getContextPath() + "/Users/ModifyMyInfo");
				model.addAttribute("title", "返回首页");
				return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
			} else {
				result.rejectValue("LoginName", "misFormat", "保存失败,请重试");
			}
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ModifyMyInfo");
	}

	@RequestMapping(value = "/modifyheadimg", method = RequestMethod.GET)
	public ModelAndView modifyheadimgEdit(@ModelAttribute("usersForm") Users users, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		checkParammap.put("Status", 1);
		if (user != null) {
			checkParammap.put("LoginName", user.getLoginName());
			List<Users> userls = ReadUsersService.getUsersList(checkParammap);
			if (userls.size() <= 0) {
				result.rejectValue("LoginName", "misFormat", "用户已停用,请联系管理员");
			} else {

				checkParammap = new HashMap<String, Object>();
				checkParammap.put("usersForm", userls.get(0));
				if(userls.get(0).getImage().getUrl()!=null){
					model.addAttribute("ImageUrl", userls.get(0).getImage().getUrl().split(",")[0]);
				}
				
			}
		} else {
			response.sendRedirect("login");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "modifyheadimg");
	}

	@RequestMapping(value = "/modifyheadimg", method = RequestMethod.POST)
	public ModelAndView modifyheadimg(@ModelAttribute("usersForm") Users users, HttpServletRequest request, HttpServletResponse response, BindingResult result,
			@RequestParam("file") MultipartFile file, @RequestParam(value = "Img_X", required = false) String Img_X, @RequestParam(value = "Img_Y",
					required = false) String Img_Y, @RequestParam(value = "Img_W", required = false) String Img_W, @RequestParam(value = "Img_H",
					required = false) String Img_H, Model model, HttpSession session) throws Exception {
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("Status", 1);
		checkParammap.put("ID", user.getID());
		long iamgeid = users.getImageID();
		List<Users> userls = ReadUsersService.getUsersList(checkParammap);
		if (userls.size() <= 0) {
			result.rejectValue("LoginName", "misFormat", "用户已停用,请联系管理员");
		} else {
			users = userls.get(0);
		}

		if (!result.hasErrors()) {
			checkParammap = new HashMap<String, Object>();
			checkParammap.put("ID", iamgeid);
			List<Pictures> picture = PicturesService.getPictureEffList(checkParammap);
			users.setImage(picture.get(0));
			users.setImageID(picture.get(0).getID());
			/* 保存文件 end */
			users.setModifyTime(new Date());

			int proResult = UsersService.updateUsers(users);
			if (proResult > 0) {
				session.setAttribute("UserName", users);
				model.addAttribute("type", "1");
				model.addAttribute("value", picture.get(0).getUrl().split(",")[2]);
				model.addAttribute("content", request.getContextPath() + "/Users/modifyheadimg");
				return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
			} else {
				result.rejectValue("ID", "misFormat", "保存失败,请重试");
			}
		}
		if (result.hasErrors()) {
			model.addAttribute("WeChatBannerForm", users);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath + "modifyheadimg");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "modifyheadimg");

	}

	/**
	 * 检测用户是否存在session
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/checkUserState", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> checkUserState(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String session_id = request.getParameter("SessionID");
		MySessionContext myc = MySessionContext.getInstance();
		HttpSession session = myc.getSession(session_id);

		if (session != null) {
			responseMap.put("status", true);
		} else {
			responseMap.put("status", false);
			responseMap.put("msg", "session不存在");
		}
		return responseMap;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = null;

		String WeChat = request.getParameter("uid");
		String state = request.getParameter("state");
		String[] wids = WeChat.split(",");
		String ErrorMessage = "";

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				List<Users> lsuser = ReadUsersService.getUsersList(responseMap);

				if (lsuser.size() > 0) {
					responseMap.put("ModifyTime", new Date());
					if (state.equals("1")) {

						List<String[]> lsError = AddOrEnabledUserCheck(lsuser.get(0));
						for (String[] strings : lsError) {
							ErrorMessage = strings[1];
						}
						if (ErrorMessage.isEmpty()) {
							UsersService.enabledUsers(responseMap);
						}
					} else {
						UsersService.deleteUsers(responseMap);
					}

				} else {
					ErrorMessage = "未找到该用户,请刷新页面重新尝试";
				}
			}
		}
		responseMap = new HashMap<String, Object>();
		if (ErrorMessage.isEmpty()) {
			responseMap.put("Status", 1);
		} else {
			responseMap.put("Status", -1);
			responseMap.put("ErrorMsg", ErrorMessage);
		}
		return responseMap;
	}

	/**
	 * 检测用户是否可以添加或启用
	 * 
	 * @param user
	 * @return
	 */
	private List<String[]> AddOrEnabledUserCheck(Users users) {

		List<String[]> ErrorMessage = new ArrayList<String[]>();

		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("LoginName", users.getLoginName());
		responseMap.put("NoQueryStatus", "88");
		int lsusercount = ReadUsersService.getUsersCount(responseMap);

		if (lsusercount > 0 && users.getID() <= 0) {
			ErrorMessage.add(new String[] { "LoginName", "该用户名已经存在" });
		}

		if (users.getLoginName() == null || users.getLoginName().isEmpty()) {
			ErrorMessage.add(new String[] { "LoginName", "用户名不能为空" });
		}
		if ((users.getPassWord() == null || users.getPassWord().isEmpty()) && users.getID() <= 0) {
			ErrorMessage.add(new String[] { "PassWord", "密码不能为空" });
		}
		if (users.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid() && users.getWeChatID() <= 0) {
			ErrorMessage.add(new String[] { "WeChatID", "商家必须关联微信公众号" });
		}
		if (users.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			if (users.getAreaID() == 0) {
				ErrorMessage.add(new String[] { "AreaID", "区域管理员地区所属的地区不能为空" });
			} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("NoQueryID", users.getID());
				responseMap.put("AreaID", users.getAreaID());
				responseMap.put("Level", SmBaseGlobal.LevelStatus.AreaManage.getid());
				responseMap.put("NoQueryStatus", SmBaseGlobal.CheckStatus.Disabled.getid());
				int lsusercount1 = ReadUsersService.getUsersCount(responseMap);
				if (lsusercount1 > 0) {
					ErrorMessage.add(new String[] { "LoginName", "该区域已经存在管理员" });
				}
			}
		}
		return ErrorMessage;
	}

}