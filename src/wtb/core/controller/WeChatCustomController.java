package wtb.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.yun.core.json.ParseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import wtb.core.model.News;
import wtb.core.model.ProdPictures;
import wtb.core.model.Users;
import wtb.core.model.WeChatContent;
import wtb.core.model.WeChatCustom;
import wtb.core.model.WeChatInfo;
import wtb.smUtil.CheckUtil;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.HttpClientConnectionManager;
import wtb.smUtil.IdWorker;
import wtb.smUtil.MessageUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.WeChatMenuUtil;
import wtb.smUtil.WeixinUtil;

@Controller
@RequestMapping("WeChatCustom")
public class WeChatCustomController  extends BaseController   {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static DefaultHttpClient httpclient;
	static {
		httpclient = new DefaultHttpClient();
		httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); // 接受任何证书的浏览器客户端
	}

	/**
	 * 微信公众号菜单表
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	@RequestMapping(value = "/WeChatCustomList", method = RequestMethod.GET)
	public ModelAndView WeChatCustomList(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {

			String appid = req.getParameter("appid");
			String appsecret = req.getParameter("appsecret");

			String menu1 = WeChatMenuUtil.getMenuInfo(WeChatMenuUtil.getAccessToken(appid, appsecret));
			String errcode = "0";
			if (JSONObject.fromObject(menu1).get("errcode") != null) {
				errcode = JSONObject.fromObject(menu1).get("errcode").toString();
			}

			if (Integer.parseInt(errcode) == 0) {
				menu1 = menu1.substring(18, menu1.length() - 2);
				System.out.println(menu1);
				String id = jsonToSql(menu1);
				sqlToWeChat(id);
				req.setAttribute("DataWeChat1", sqlToJson(id).toString());
				model.addAttribute("mGroup", id);
				req.setAttribute("mGroup1", "'" + id + "'");
			} else {
				System.out.println("没有数据");
				req.setAttribute("DataWeChat1", "");
				model.addAttribute("mGroup", "");
				req.setAttribute("mGroup1", "");
			}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "WeChatCustomList");
	}
	
	/**
	 *获得微信预览数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value="/getWeChatJson",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWeChatJson(HttpServletRequest request,   
            HttpServletResponse response,Model model) throws ParseException, IOException, Exception{
		Map<String, Object> maps;
		JSONArray jsonArray = null;
		String key=request.getParameter("key");
		String wid=request.getParameter("wid");
		if (key.trim() != null) {
			key = str(key);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
		Map<String , Object> map=new HashMap<>();
		List<Map<String, Object>> mList=new ArrayList<>();
		map.put("Key", key);
		map.put("WID", wid);
		List<WeChatContent> weChatContents=WeChatContentService.getWeChatContentList(map);
		if (weChatContents.size()==1) {
			map=new HashMap<>();
			map.put("title", weChatContents.get(0).getTitle());
			map.put("imgurl", weChatContents.get(0).getPicUrl());
			map.put("time", sdf.format(new Date()));
			map.put("description",weChatContents.get(0).getDescription());
			mList.add(map);
		}else
		{
			for (int i = 0; i < weChatContents.size(); i++) {
				map=new HashMap<>();
				map.put("title", weChatContents.get(i).getTitle());
				map.put("imgurl", weChatContents.get(i).getPicUrl());
				mList.add(map);
			}
		}
			jsonArray = JSONArray.fromObject(mList);
			maps=new HashMap<>();
			maps.put("Status", 1);
			maps.put("data", jsonArray.toString());
			maps.put("size", jsonArray.size());
			maps.put("title", weChatContents.get(0).getTitle());
			return maps;
	
	}
	

	/**
	 * 更新列表数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/getJson", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getJson1(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, Exception {
		Map<String, Object> map=null;
		String message="";
			JSONArray jsonArray = null;
			String appid = request.getParameter("appid");
			String appsecret = request.getParameter("appsecret");
			String menu1 = WeChatMenuUtil.getMenuInfo(WeChatMenuUtil.getAccessToken(appid, appsecret));
			String errcode = "0";
			if (JSONObject.fromObject(menu1).get("errcode") != null) {
				errcode = JSONObject.fromObject(menu1).get("errcode").toString();
			}

			if (Integer.parseInt(errcode) == 0) {
				menu1 = menu1.substring(18, menu1.length() - 2);
				System.out.println(menu1);
				String id = jsonToSql(menu1);
				jsonArray = JSONArray.fromObject(sqlToJson(id).toString().replace("\"am", "").replace("naij\"", ""));
				map=new HashMap<>();
				map.put("ID", id);
				List<WeChatCustom> mList=WeChatCustomService.getWeChatCustomList(map);
				map=new HashMap<>();
				map.put("menu", jsonArray.toString());
				map.put("mGroup", mList.get(0).getmGroup().toString());
				map.put("Status", 1);
				
			} else {
				message+="没有数据";
				map=new HashMap<>();
				map.put("menu", message);
				map.put("Status", -1);
			}
			return map;
		
	}
	
	/**
	 * 公众号管理页面
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/WeChatCustomHome", method = RequestMethod.GET)
	public ModelAndView WeChatCustomHome(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		model.addAttribute("flag", req.getParameter("flag"));
		return new ModelAndView(SmBaseGlobal.WebViewPath + "WeChatInfoList");

	}

	
	/**
	 * 获取主页数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getWeChatInfoHomeList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWeChatInfoHomeList(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(SmBaseUtil.AddPageParam(request));
			user = (Users) request.getSession().getAttribute("UserName");
			if (user.getLevel() != 3) {
				params.put("AreaIDForQU", user.getAreaID());
			}
			String check = request.getParameter("check");
			if (check != null) {
				check = SmBaseUtil.URLDecoderString(check);
				params.put("check", check);
			}

			List<WeChatInfo> weChatInfos = WeChatInfoServices.getWeChatInfoHome(params);
			int count = WeChatInfoServices.getWeChatInfoHomeCount(params);
			responseMap.put("total", count);
			responseMap.put("Data", weChatInfos);
			responseMap.put("Status", 1);
		return responseMap;
	}
	
	/**
	 * 获得关键字管理的数据
	 * @param request
	 * @param response 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/getWeChatContentList",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWeChatContentList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(SmBaseUtil.AddPageParam(request));
			params.put("IsKey", 1);// 不允许Key为空
			params.put("Type", 4);
			params.put("Status", 1);
			// params.remove("check");
			String check = request.getParameter("check");
			if (check != null) {
				check = SmBaseUtil.URLDecoderString(check);
				params.put("Check", check);
			}

			List<WeChatContent> WeChatContents = WeChatContentService.getWeChatContentList(params);
			int count = WeChatContentService.getWeChatContentCount(params);
			responseMap.put("total", count);
			responseMap.put("Data", WeChatContents);
			responseMap.put("Status", 1);
		    return responseMap;
	 }
	
	/**
	 * 获得关键字管理的数据
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/getWeChatContentListForGroup",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWeChatContentListForGroup(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.putAll(SmBaseUtil.AddPageParam(request));
			params.put("Type", 5);
			params.put("Status", 1);
			String check = request.getParameter("check");

			List<WeChatContent> WeChatContents = WeChatContentService.getWeChatContentList(params);
			int count = WeChatContentService.getWeChatContentCount(params);
			responseMap.put("total", count);
			responseMap.put("Data", WeChatContents);
			responseMap.put("Status", 1);
		return responseMap;
	 }
	
	@RequestMapping(value = "/updataWeChatInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> updataWeChatInfo(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = null;
			String name = SmBaseUtil.URLDecoderString(request.getParameter("name"));
			String appid = request.getParameter("appID");
			String appsecret = request.getParameter("appsecret");
			String wid = request.getParameter("wid");
			String id=request.getParameter("id");
			Map<String, Object> map = new HashMap<>();
			map.put("ID", id);
			List<WeChatInfo> weChatInfos=WeChatInfoServices.getWeChatInfo(map);
			WeChatInfo weChatInfo=weChatInfos.get(0);
			weChatInfo.setAppID(appid);
			weChatInfo.setAppsecret(appsecret);
			weChatInfo.setName(name);
			weChatInfo.setWID(wid);
			int i=WeChatInfoServices.updateWeChatInfo(weChatInfo);
			responseMap=new HashMap<>();
			if(i==1){
				responseMap.put("Status", 1);
			}else{
				responseMap.put("Status", -1);
			}
		return responseMap;
	}

	/**
	 * 创建菜单
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/WeChatCustomList", method = RequestMethod.POST)
	public void WeChatCustomListPost(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {

			String json_code = req.getParameter("json_code");
			String appid = req.getParameter("appid");
			String appsecret = req.getParameter("appsecret");
			// System.out.println("前台返回的数据" + json_code);
			System.out.println(WeChatMenuUtil.createMenu(WeChatMenuUtil.getAccessToken(appid, appsecret), json_code));
	}
	
	/**
	 * 公众号详情页
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/WeChatInfoDetail", method = RequestMethod.GET)
	public ModelAndView WeChatInfoDetail(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ParseException, IOException, Exception {
		Users user = null;
			user = (Users) req.getSession().getAttribute("UserName");
			String id = req.getParameter("id");
			String wid = req.getParameter("wid");
			String name = req.getParameter("name");
			String appid = req.getParameter("appid");
			String appsecret = req.getParameter("appsecret");
			String area = req.getParameter("area");
			String areaid = req.getParameter("areaid");
			if (user.getAreaID() == Long.parseLong(areaid)) {
				model.addAttribute("IsSame", 1);
			} else {
				model.addAttribute("IsSame", -1);
			}
			model.addAttribute("id", id.toString());
			if (name != null && !name.isEmpty()) {
				name = SmBaseUtil.URLDecoderString(name);
				model.addAttribute("name", name);
			}
			model.addAttribute("appid", appid);
			model.addAttribute("appsecret", appsecret);
			if (area != null && !area.isEmpty()) {
				area = SmBaseUtil.URLDecoderString(area);
				model.addAttribute("area", area);
			}

			String AccessToken = WeChatMenuUtil.getAccessToken(appid, appsecret);

			if (AccessToken != null) {
				String menu1 = WeChatMenuUtil.getMenuInfo(AccessToken);
				if (menu1.equals("-1")) {
					System.out.println("没有数据");
					req.setAttribute("DataWeChat1", "");
					model.addAttribute("mGroup", "");
					req.setAttribute("mGroup1", "");
				} else {
					menu1 = menu1.substring(18, menu1.length() - 2);
					System.out.println(menu1);
					String id2 = jsonToSql(menu1);
					req.setAttribute("DataWeChat1", sqlToJson(id2).toString().replace("\"am", "").replace("naij\"", ""));
					model.addAttribute("mGroup", id2);
					model.addAttribute("wid", wid);
					req.setAttribute("mGroup1", "'" + id2 + "'");
				}

			} else {
				System.out.println("没有数据");
				req.setAttribute("DataWeChat1", "");
				model.addAttribute("mGroup", "");
				req.setAttribute("mGroup1", "");
			}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "WeChatInfoDetail");
	}
	
	/**
	 * 添加微信公众号
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	
	@RequestMapping(value = "/addWeChatInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> addRegion(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = null;
		Users user = null;
			user = (Users) request.getSession().getAttribute("UserName");
			String name = SmBaseUtil.URLDecoderString(request.getParameter("name"));
			String appid = request.getParameter("appID");
			String appsecret = request.getParameter("appsecret");
			String wid = request.getParameter("wid");

			String mch=request.getParameter("mch");
			String apikey=request.getParameter("apikey");
			String Weight=request.getParameter("Weight");
			String type=request.getParameter("type");
			
			String id=request.getParameter("id");
			
			responseMap = new HashMap<String, Object>();
			responseMap.put("Weight", Weight);
			List<WeChatInfo> WeightFlag=WeChatInfoServices.getWeChatInfo(responseMap);
			if (WeightFlag.size()>0) {
					if(Long.parseLong(id)!=WeightFlag.get(0).getID()){
					responseMap = new HashMap<String, Object>();
					responseMap.put("Status", -1);
					responseMap.put("ErrorMsg", "当前权值已经存在，请修改权值");
					return responseMap;
				}
			}
			
			
			String message = "";// 错误信息
//			responseMap = new HashMap<String, Object>();
//			responseMap.put("Name", name);
//			int count = WeChatInfoServices.getWeChatInfoHomeCount(responseMap);
//			if (count > 0) {
//				message += "微信公众号名字已存在！添加失败";
//			}
//			responseMap = new HashMap<String, Object>();
//			responseMap.put("AppID", appid);
//			int countid = WeChatInfoServices.getWeChatInfoHomeCount(responseMap);
//			if (countid > 0) {
//				message += "微信公众号appID已存在！添加失败";
//			}

//			if (count == 0 && countid == 0) {
				long currentTime = System.currentTimeMillis() - 130 * 60 * 1000;

				WeChatInfo weChatInfo =null;
				if (Long.parseLong(id)==0) {
					weChatInfo = new WeChatInfo();
					Long iD = new IdWorker(1, 0).nextId();
					weChatInfo.setID(iD);
					weChatInfo.setCreateTime(sdf.format(new Date()));
					weChatInfo.setStatus("1");
					weChatInfo.setUID(user.getID());
					weChatInfo.setATTime(sdf.format(new Date(currentTime)));
				}else{
					responseMap = new HashMap<String, Object>();
					responseMap.put("ID", id);
					List<WeChatInfo> weChatInfos=WeChatInfoServices.getWeChatInfo(responseMap);
					if (weChatInfos.size()>0) {
						weChatInfo=weChatInfos.get(0);
					}
					
				}
				
				weChatInfo.setName(name);
				weChatInfo.setAppID(appid);
				weChatInfo.setWID(wid);
				weChatInfo.setAppsecret(appsecret);
				weChatInfo.setWeight(Integer.parseInt(Weight));
				weChatInfo.setMchID(Long.parseLong(mch));
				weChatInfo.setApiKey(apikey);
				weChatInfo.setIsPay(Integer.parseInt(type));
				
				if (Integer.parseInt(type)==1) {//默认只能有一个
					responseMap = new HashMap<String, Object>();
					responseMap.put("IsPay", type);
					List<WeChatInfo> weChatInfos=WeChatInfoServices.getWeChatInfo(responseMap);
					if (weChatInfos.size()>0) {
						weChatInfos.get(0).setIsPay(0);
						WeChatInfoServices.updateWeChatInfo(weChatInfos.get(0));
					}
				}
				
				
				if (Long.parseLong(id)==0) {
					if (user.getLevel() == SmBaseGlobal.LevelStatus.SuperManage.getid()) {
						System.out.println("你是超级管理员不给你地址哈哈哈");
						WeChatInfoServices.addWeChatInfo(weChatInfo);
					} else {
						responseMap = new HashMap<String, Object>();
						responseMap.put("AreaID", user.getAreaID());
						List<WeChatInfo> weChatInfos = WeChatInfoServices.getWeChatInfo(responseMap);
						if (weChatInfos.size() <= 0) {
							weChatInfo.setAreaID(user.getAreaID().toString());
							WeChatInfoServices.addWeChatInfo(weChatInfo);
						} else {
							message += "当前地区已有微信公众号";
						}
					}
				}else{
					WeChatInfoServices.updateWeChatInfo(weChatInfo);
				}
				

//			}

			responseMap = new HashMap<String, Object>();
			if (!message.isEmpty()) {
				responseMap.put("Status", -1);
				responseMap.put("ErrorMsg", message);
			} else {
				responseMap.put("Status", 1);
				responseMap.put("ErrorMsg", "添加成功");
			}
		return responseMap;
	}

	/**
	 * 关注界面
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/Subscribe", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> Subscribe(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			String content = req.getParameter("content").trim();
			String AppID = req.getParameter("AppID");
			String wid = req.getParameter("wid");

			if (content != null && !content.isEmpty()) {
				content = SmBaseUtil.URLDecoderString(content);
				WeChatContent weChatContent = new WeChatContent();
				Long iD = new IdWorker(1, 0).nextId();
				weChatContent.setID(iD);
				weChatContent.setContent(content);
				weChatContent.setType(3);
				weChatContent.setWID(wid);
				weChatContent.setCreateTime(sdf.format(new Date()));
				weChatContent.setAppID(AppID);
				WeChatContentService.addWeChatContent(weChatContent);
				responseMap.put("Status", 1);
			} else {
				responseMap.put("Status", -1);
			}
		return responseMap;

	}

	/**
	 * 前台传过来的值存到数据库中
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addWeChatContent", method = RequestMethod.GET)
	public void addWeChatContent(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {
			String content = req.getParameter("content");
			String key = req.getParameter("key");
			String active = req.getParameter("active");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String picUrl = req.getParameter("picUrl");
			String url = req.getParameter("url");
			String wid = req.getParameter("wid");
			if(title==null){
				title= "";
			}
			if (content != null && !content.isEmpty()) {
				content = SmBaseUtil.URLDecoderString(content);
			}
			if (title != null && !title.isEmpty()) {
				title = SmBaseUtil.URLDecoderString(title);
			}
			if (description != null && !description.isEmpty()) {
				description = SmBaseUtil.URLDecoderString(description);
			}
			boolean a = false;
			try {
				a = title.equals("undefined") && description.equals("undefined");
			} catch (Exception e) {
				System.out.println("Title,Description为空" + e);
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}

			if (a) {
				System.out.println("Title,Description为空");
			} else {
				Long iD = new IdWorker(1, 0).nextId();
				WeChatContent weChatContent = new WeChatContent();
				weChatContent.setID(iD);
				weChatContent.setKey(key);
				weChatContent.setWID(wid);
				weChatContent.setCreateTime(sdf.format(new Date()));
				if (Integer.parseInt(active) == 1) {
					weChatContent.setContent(content);
					weChatContent.setType(1);
				}
				if (Integer.parseInt(active) == 2) {
					weChatContent.setType(2);
					weChatContent.setTitle(title);
					weChatContent.setDescription(description);
					weChatContent.setPicUrl(picUrl);
					weChatContent.setUrl(url);
				}
				WeChatContentService.addWeChatContent(weChatContent);
				if (Integer.parseInt(active) == 1) {
					System.out.println("内容" + content + "key" + key + "active" + active);
				} else {
					System.out.println("title" + title + "description" + description + "picUrl" + picUrl + "url" + url);
				}

			}

	}

	@RequestMapping(value = "/deleteWeChatInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteWeChatInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();
			String id = request.getParameter("ID");
			map.put("ID", id);
			List<WeChatInfo> weChatInfos = WeChatInfoServices.getWeChatInfo(map);
			WeChatInfoServices.deleteWeChatInfo(weChatInfos.get(0));
		return map;
	}

	/**
	 * 保存
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/saveWeChatInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> saveWeChatInfo(HttpServletResponse response, HttpServletRequest req, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> map = null;

			req.setCharacterEncoding("UTF-8");
			String type = req.getParameter("type");
			String name = req.getParameter("name");
			String id = req.getParameter("id");
			String key = req.getParameter("key");
			String mgroup = req.getParameter("mgroup");
			int i = 0;
			if (name != null) {
				name = SmBaseUtil.URLDecoderString(name);
			}

			if (id == null && mgroup == null) {
				WeChatCustom weChatCustom = new WeChatCustom();
				Long wid = new IdWorker(1, 0).nextId();
				weChatCustom.setID(wid);
				weChatCustom.setName(name);
				weChatCustom.setType(type);
				if (type.equals("view")) {
					weChatCustom.setURL(key);
				} else {
					weChatCustom.setKey(key);
				}
				weChatCustom.setParentID(1);
				weChatCustom.setmGroup(wid);
				weChatCustom.setStatus(1);
				weChatCustom.setCreateTime(sdf.format(new Date()));
				i = WeChatCustomService.addWeChatCustom(weChatCustom);
			} else {

				if (mgroup != null && !(mgroup.trim().isEmpty())) {
					map = new HashMap<>();
					map.put("mGroup", mgroup);
					int size = WeChatCustomService.getWeChatCustomCount(map);
					WeChatCustom weChatCustom = new WeChatCustom();
					Long iD = new IdWorker(1, 0).nextId();
					weChatCustom.setName(name);
					weChatCustom.setID(iD);
					weChatCustom.setParentID(size + 1);
					weChatCustom.setmGroup(Long.parseLong(mgroup));
					weChatCustom.setCreateTime(sdf.format(new Date()));
					weChatCustom.setStatus(1);
					if (type == null || type.equals("0")) {

					} else {
						weChatCustom.setType(type);
						if (type.equals("view")) {
							weChatCustom.setURL(key);
						}

						if (type.equals("click")) {
							weChatCustom.setKey(key);
						}
					}
					i = WeChatCustomService.addWeChatCustom(weChatCustom);

				} else {

					List<WeChatCustom> mChatCustoms = null;
					map = new HashMap<>();
					if (id != null) {
						map.put("ID", Long.parseLong(id));
						mChatCustoms = WeChatCustomService.getWeChatCustomList(map);
					} else {
						System.err.println("id为空");
					}

					if (mChatCustoms!=null && mChatCustoms.size() > 0) {
						WeChatCustom weChatCustom = mChatCustoms.get(0);
						if (type == null || type.equals("0")) {
							// id name
							weChatCustom.setName(name);
							weChatCustom.setKey(null);
							weChatCustom.setURL(null);
							weChatCustom.setType(null);

						} else {
							if (type.equals("click")) {
								// id name type=click key=key
								weChatCustom.setName(name);
								weChatCustom.setType(type);
								weChatCustom.setKey(key);
								weChatCustom.setURL(null);
							}

							if (type.equals("view")) {
								// id name type=view url=key
								weChatCustom.setName(name);
								weChatCustom.setType(type);
								weChatCustom.setKey(null);
								weChatCustom.setURL(key);
							}

							map = new HashMap<>();
							map.put("ParentID", id);// 装载到子ID，判断是否有子ID，有的话说明他是主ID；
							List<WeChatCustom> mChatCustoms1 = WeChatCustomService.getWeChatCustomList(map);
							if (mChatCustoms1.size() > 0) {
								for (int z = 0; z < mChatCustoms1.size(); z++) {
									WeChatCustom weChatCustom2 = mChatCustoms1.get(z);
									map = new HashMap<>();
									map.put("ID", weChatCustom2.getID());
									i = WeChatCustomService.deleteWeChatCustom(map);
								}
							}
						}
						i = WeChatCustomService.updateWeChatCustom(weChatCustom);
					}
				}
			}
			map = new HashMap<>();
			if (i == 1) {
				map.put("time", 1);
				map.put("start", 0);
				map.put("limit", 1);
				map.put("del", 1);
				List<WeChatCustom> mChatCustoms = WeChatCustomService.getWeChatCustomList(map);
				if(mChatCustoms.size()>0 && mChatCustoms.get(0).getmGroup()!=null){
					map = new HashMap<>();
					map.put("Stauts", 1);
					map.put("mGroup", mChatCustoms.get(0).getmGroup().toString());
				}
			} else {
				map.put("Stauts", -1);
			}
		return map;

	}

	/**
	 * 发布
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @throws ParseException
	 * @throws IOException
	 * @throws java.text.ParseException
	 */
	@RequestMapping(value = "/sendWeChatInfo", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> sendWeChatInfo(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ParseException, IOException, java.text.ParseException {
		Map<String, Object> map = null;
			String mGroup = req.getParameter("mGroup");
			String id = req.getParameter("id");
			String appid = req.getParameter("appid");
			String appsecret = req.getParameter("appsecret");
			String json = null;
			int i;
			if (mGroup != null && !mGroup.isEmpty() && !mGroup.equals("")) {
				json = sqlToWeChat(mGroup);
			} else {
				// 99.9%的可能这里不会操作
				map = new HashMap<>();
				map.put("time", 1);
				map.put("start", 0);
				map.put("limit", 1);
				map.put("del", 1);
				List<WeChatCustom> mChatCustoms = WeChatCustomService.getWeChatCustomList(map);
				json = sqlToWeChat(mChatCustoms.get(0).getmGroup().toString());
			}
			i = WeChatMenuUtil.createMenu(WeChatMenuUtil.getAccessToken(appid, appsecret), json);
			map = new HashMap<>();
			if (i == 0) {
				map.put("Status", 1);
			} else {
				map.put("Status", -1);
				map.put("error", i);
			}
		return map;
	}

	/**
	 * 删除
	 * 
	 * @param response
	 * @param req
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteWeChat", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteWeChat(HttpServletResponse response, HttpServletRequest req, HttpSession session)
			throws ParseException, IOException {

		Map<String, Object> map2 = new HashMap<>();
			String id = req.getParameter("id");
			Map<String, Object> map = new HashMap<>();
			map.put("ParentID", id);// 装载到子ID，判断是否有子ID，有的话说明他是主ID；
			List<WeChatCustom> mChatCustoms = WeChatCustomService.getWeChatCustomList(map);
			if (mChatCustoms.size() > 0) {
				for (int i = 0; i < mChatCustoms.size(); i++) {
					WeChatCustom weChatCustom = mChatCustoms.get(i);
					map = new HashMap<>();
					map.put("ID", weChatCustom.getID());
					WeChatCustomService.deleteWeChatCustom(map);
				}
				map = new HashMap<>();
				map.put("ID", id);
				map2.put("Status", 1);
				WeChatCustomService.deleteWeChatCustom(map);
			} else {
				map = new HashMap<>();
				map.put("ID", id);
				List<WeChatCustom> mChatCustom = WeChatCustomService.getWeChatCustomList(map);
				map2 = new HashMap<>();
				if (mChatCustom.get(0).getmGroup() == null) {
					map = new HashMap<>();
					map.put("ParentID", mChatCustom.get(0).getParentID());
					List<WeChatCustom> mChatCustoms1 = WeChatCustomService.getWeChatCustomList(map);
					if (mChatCustoms1.size() > 1) {
						map = new HashMap<>();
						map.put("ID", id);
						WeChatCustomService.deleteWeChatCustom(map);
					} else {
						map = new HashMap<>();
						map.put("ID", mChatCustom.get(0).getParentID());
						List<WeChatCustom> mChatCustoms2 = WeChatCustomService.getWeChatCustomList(map);
						WeChatCustom weChatCustom = mChatCustoms2.get(0);
						weChatCustom.setType("view");
						weChatCustom.setURL("http://www.whohelp.cc/");
						WeChatCustomService.updateWeChatCustom(weChatCustom);
						map = new HashMap<>();
						map.put("ID", id);
						WeChatCustomService.deleteWeChatCustom(map);
					}
					map2.put("message", "子菜单");
					map2.put("Status", -1);
					map2.put("SunSize", mChatCustoms1.size() - 1);
				} else {

					map = new HashMap<>();
					map.put("ID", id);
					WeChatCustomService.deleteWeChatCustom(map);
					map2.put("Status", 1);
				}
				;

			}
		return map2;
	}

	/**
	 * 删除所有
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @throws java.text.ParseException
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/deleteWeChatALL", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> deleteWeChatALL(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException, java.text.ParseException, Exception {
		Map<String, Object> map = new HashMap<>();
			String appid = request.getParameter("appid");
			String appsecret = request.getParameter("appsecret");
			String id = request.getParameter("id");

			map.put("ParentID", id);// 装载到子ID，判断是否有子ID，有的话说明他是主ID；
			List<WeChatCustom> mChatCustoms = WeChatCustomService.getWeChatCustomList(map);
			if (mChatCustoms.size() > 0) {
				for (int i = 0; i < mChatCustoms.size(); i++) {
					WeChatCustom weChatCustom = mChatCustoms.get(i);
					map = new HashMap<>();
					map.put("ID", weChatCustom.getID());
					WeChatCustomService.deleteWeChatCustom(map);
				}
				map = new HashMap<>();
				map.put("ID", id);
				WeChatCustomService.deleteWeChatCustom(map);
			} else {
				map = new HashMap<>();
				map.put("ID", id);
				WeChatCustomService.deleteWeChatCustom(map);
			}
			map = new HashMap<>();
			String errmsg = WeChatMenuUtil.deleteMenuInfo(WeChatMenuUtil.getAccessToken(appid, appsecret));
			if (errmsg.equals("ok")) {
				map.put("Status", 1);
				map.put("errmsg", "删除成功");
			} else {
				map.put("Status", -1);
			}
		return map;
	}

	/**
	 * 群发界面
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/GroupSend", method = RequestMethod.GET)
	public ModelAndView GroupSend(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "GroupSend");
	}

	@RequestMapping(value = "/GroupSend", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> GroupSendPost(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, ClientProtocolException, IOException, ParseException,
			java.text.ParseException {
		Map<String, Object> map = null;
			String content = req.getParameter("content");// 文本的内容
			String type = req.getParameter("type");// 类型 这里讲存入Content_Rules
			String id = req.getParameter("id");
			String key = req.getParameter("numb");// 用于区别的条数
			String author = req.getParameter("author");
			String title = req.getParameter("title");
			String content_source_url = req.getParameter("content_source_url");
			String tucontent = req.getParameter("tucontent");
			String digest = req.getParameter("digest");
			String rootPath = getClass().getResource("/").getFile().toString();
			rootPath = rootPath.replace("classes/", "");
			rootPath = rootPath.replace("WEB-INF/", "");
			String path = req.getContextPath();
			String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
			String pic = req.getParameter("pic");
			String appid = req.getParameter("appid");
			String appsecret = req.getParameter("appsecret");
			String wid = req.getParameter("wid");
			// content=new String(content.getBytes("UTF-8"), "ISO8859_1");
			String message = "";
			int errcode = 0;//
			WeChatContent weChatContent = new WeChatContent();
			Long iD = new IdWorker(1, 0).nextId();
			weChatContent.setType(5);
			weChatContent.setRules(type);
			weChatContent.setWID(wid);
			weChatContent.setCreateTime(sdf.format(new Date()));
			weChatContent.setAppID(appid);
			weChatContent.setStatus("1");
			weChatContent.setID(iD);
			if (type.equals("text")) {
				if (content != null) {
					weChatContent.setContent(content);
					errcode = WeixinUtil.GroupSend(WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), type, content, null);
					if (errcode != 0) {
						message += "错误码:" + errcode;
					}
				} else {
					message += "内容为空";
				}

			}

			if (type.equals("mpnews")) {

				// 设置作者
				if (id != null && title != null && tucontent != null && digest != null && content_source_url != null) {
					Map<String, Object> map2 = new HashMap<>();
					map2.put("ProductID", id);
					List<ProdPictures> pictures = ProdPicturesService.getPicturesList(map2);
					String picurl;// 用于上传文件的地址
					String pichttp;// 用于预览的地址
					if (pictures.size() > 0) {
						picurl = rootPath + pictures.get(0).getUrl();
						pichttp = basePath + pictures.get(0).getUrl();
					} else {
						picurl = rootPath + "upload/errorpic.jpg";
						pichttp = basePath + "upload/errorpic.jpg";
					}
					weChatContent.setPicUrl(pichttp);
					weChatContent.setKey(key);
					weChatContent.setTitle(title);
					weChatContent.setContent(tucontent);
					weChatContent.setDescription(digest);
					weChatContent.setUrl(content_source_url);
					weChatContent.setAuthor(author);
					String mid = WeixinUtil.upload(picurl, WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), "thumb");
					if (mid.equals("-1")) {
						mid = WeixinUtil.upload(rootPath + "upload/errorpic.jpg", WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), "thumb");
					}
					weChatContent.setMediaId(mid);// 群发功能发他当着MID存贮

				} else {
					message += "内容为空";
				}

			}
			if (type.equals("image")) {
				if (pic != null) {
					weChatContent.setPicUrl(pic);
					String mid = WeixinUtil.uploadimg(pic, WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), type);
					errcode = WeixinUtil.GroupSend(WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), type, null, mid);
					if (errcode != 0) {
						message += "错误码:" + errcode;
					}
				} else {
					message += "请检查图片是否为存在";
				}

			}
			map = new HashMap<String, Object>();
			if (message == "" && errcode == 0) {
				WeChatContentService.addWeChatContent(weChatContent);
				map.put("Status", 1);
				if (type.equals("mpnews")) {
					map.put("ErrorMsg", "上传成功");
				} else {
					map.put("ErrorMsg", "发送成功");
				}

			} else {
				map.put("Status", -1);
				map.put("ErrorMsg", message);
			}
		return map;

	}

	@RequestMapping(value = "/SendGroupSend")
	public @ResponseBody Map<String, Object> SendGroupSend(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException, java.text.ParseException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		Map<String, Object> map = new HashMap<>();
		Users user = null;
			user = (Users) request.getSession().getAttribute("UserName");
			String key = request.getParameter("numb");
			String appid = request.getParameter("appid");
			String appsecret = request.getParameter("appsecret");
			String message = null;
			map.put("Key", key);
			List<WeChatContent> weChatContents = WeChatContentService.getWeChatContentList(map);
			if (weChatContents.size() > 0) {
				List<Map<String, Object>> mList = new ArrayList<>();
				for (int i = 0; i < weChatContents.size(); i++) {
					map = new HashMap<>();
					map.put("thumb_media_id", weChatContents.get(i).getMediaId());
					map.put("author", weChatContents.get(i).getAuthor());
					map.put("title", weChatContents.get(i).getTitle());
					map.put("content", weChatContents.get(i).getContent());
					map.put("digest", weChatContents.get(i).getDescription());
					map.put("show_cover_pic", 1);
					mList.add(map);
				}
				JSONArray jsonArray = JSONArray.fromObject(mList);
				String tuwenmid = WeixinUtil.uploadPermanentMedia(WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), jsonArray);
				int errcode = WeixinUtil.GroupSend(WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), "mpnews", null, tuwenmid);
				if (errcode != 0) {
					message += "群发失败！错误码：" + errcode;
				}
			} else {
				message += "你未选择数据为空";
			}
			map = new HashMap<>();
			if (message == null) {
				map.put("Status", 1);
			} else {
				map.put("Status", -1);
				map.put("message", message);
			}
		return map;
	}

	/**
	 * 添加
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSun", method = RequestMethod.GET)
	public void addSun(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {
			req.setCharacterEncoding("UTF-8");
			String type = req.getParameter("type");
			String name = req.getParameter("name");
			String key = req.getParameter("key");
			String ParentID = req.getParameter("ParentID");
			if (name != null) {
				name = SmBaseUtil.URLDecoderString(name);
			}
			Long iD = new IdWorker(1, 0).nextId();
			WeChatCustom weChatCustom = new WeChatCustom();
			if (type.equals("click")) {
				weChatCustom.setID(iD);
				weChatCustom.setName(name);
				weChatCustom.setType(type);
				weChatCustom.setKey(key);
				weChatCustom.setURL(null);
				weChatCustom.setCreateTime(sdf.format(new Date()));
				weChatCustom.setParentID(Long.parseLong(ParentID));
				weChatCustom.setStatus(1);
			}

			if (type.equals("view")) {
				// id name type=view url=key
				weChatCustom.setID(iD);
				weChatCustom.setName(name);
				weChatCustom.setType(type);
				weChatCustom.setKey(null);
				weChatCustom.setURL(key);
				weChatCustom.setCreateTime(sdf.format(new Date()));
				weChatCustom.setParentID(Long.parseLong(ParentID));
				weChatCustom.setStatus(1);
			}
			int i = WeChatCustomService.addWeChatCustom(weChatCustom);
			if (i == 1) {
				Map<String, Object> map = new HashMap<>();
				map.put("ID", ParentID);
				List<WeChatCustom> weChatCustoms = WeChatCustomService.getWeChatCustomList(map);
				if (weChatCustoms.size() == 1) {
					WeChatCustom weChatCustom2 = weChatCustoms.get(0);
					weChatCustom2.setKey(null);
					weChatCustom2.setURL(null);
					weChatCustom2.setType(null);
					WeChatCustomService.updateWeChatCustom(weChatCustom2);
				}

			}

	}

	@RequestMapping(value = "/delWeChatContent", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> delWeChatContent(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {

		Map<String, Object> map = new HashMap<>();
			String id = req.getParameter("id");
			map.put("ID", id);
			int i = WeChatContentService.deleteWeChatContent(map);
			map = new HashMap<>();
			if (i == 1) {
				map.put("Status", 1);
			} else {
				map.put("Status", -1);
			}
		return map;
	}

	/**
	 * 规定
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */

	@RequestMapping(value = "/Rules", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> Rules(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {
		Map<String, Object> map = null;
			String content = req.getParameter("content");
			String key = req.getParameter("key");// 关键字
			String rules = req.getParameter("rules");// 规则
			String title = req.getParameter("title");// 标题
			String description = req.getParameter("description");// 详情
			String picurl = req.getParameter("picurl");// 图片地址
			String url = req.getParameter("url");// 内容地址
			String pic = req.getParameter("pic");// 图片
			String appid = req.getParameter("appid");// appid
			String appsecret = req.getParameter("appsecret");
			String wid = req.getParameter("wid");
			String message = "";
			if (key.trim() != null) {
				key = str(key);
			}
			if (title != null) {
				title = str(title);
			}
			if (description != null) {
				description = str(description);
			}

			WeChatContent weChatContent = new WeChatContent();
			Long iD = new IdWorker(1, 0).nextId();
			weChatContent.setID(iD);
			weChatContent.setType(4);
			weChatContent.setRules(rules);
			weChatContent.setWID(wid);
			weChatContent.setCreateTime(sdf.format(new Date()));
			weChatContent.setAppID(appid);
			weChatContent.setStatus("1");
			if (rules.equals("text")) {
				map = new HashMap<>();
				map.put("Key", key);
				int count = WeChatContentService.getWeChatContentCount(map);
				if (count == 0) {
					weChatContent.setKey(key);
				} else {
					message += "当前关键字已存在";
				}
				if (content != null) {
					content = str(content);
					weChatContent.setContent(content);
				} else {
					message += " 内容不能为空";
				}

			}
			if (rules.equals("mpnews")) {
				weChatContent.setKey(key);
				weChatContent.setTitle(title);
				weChatContent.setDescription(description);
				weChatContent.setPicUrl(picurl);
				weChatContent.setUrl(url);
			}
			if (rules.equals("image")) {
				map = new HashMap<>();
				map.put("Key", key);
				int count = WeChatContentService.getWeChatContentCount(map);
				if (count == 0) {
					weChatContent.setKey(key);
				} else {
					message += "当前关键字已存在，请换其他关键字！添加失败！";
				}
				String mid = WeixinUtil.upload(pic, WeChatMenuUtil.getAccessToken(appid, appsecret).toString(), rules);
				weChatContent.setPicUrl(mid);
			}

			map = new HashMap<String, Object>();
			if (message == "") {
				WeChatContentService.addWeChatContent(weChatContent);
				map.put("Status", 1);
				map.put("ErrorMsg", "添加成功");
			} else {
				map.put("Status", -1);
				map.put("ErrorMsg", message);
			}
		return map;

	}

	public static JSONObject doGetStr(String url) throws ParseException, IOException {
		JSONObject jsonObject = null;
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);

			HttpResponse httpResponse = client.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		return jsonObject;
	}

	/**
	 * 图文存到数据库
	 * 
	 * @param response
	 * @param req
	 * @param session
	 * @param model
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Mpnews", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> Mpnews(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model)
			throws UnsupportedEncodingException {

		Map<String, Object> map = new HashMap<>();
			String key = req.getParameter("key");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			String picurl = req.getParameter("picurl");
			String url = req.getParameter("url");
			String appid = req.getParameter("appid");// appid
			String id = req.getParameter("id");

			String wid = req.getParameter("wid");
			String path = req.getContextPath();
			String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";

			String message = "";
			if (key != null && title != null && description != null) {
				key = str(key);
				title = str(title);
				description = str(description);
			} else {
				message += "值为空";
			}
			Map<String, Object> map2 = new HashMap<>();
			map2.put("ProductID", id);
			List<ProdPictures> pictures = ProdPicturesService.getPicturesList(map2);
			WeChatContent weChatContent = new WeChatContent();
			Long iD = new IdWorker(1, 0).nextId();
			weChatContent.setID(iD);
			weChatContent.setKey(key);
			weChatContent.setType(4);
			weChatContent.setWID(wid);
			weChatContent.setRules("mpnews");
			weChatContent.setCreateTime(sdf.format(new Date()));
			weChatContent.setTitle(title);
			weChatContent.setDescription(description);
			if (pictures.size() > 0) {
				weChatContent.setPicUrl(basePath + pictures.get(0).getUrl());
			} else {
				weChatContent.setPicUrl(basePath + "upload/errorpic.jpg");
			}

			weChatContent.setUrl(url);
			weChatContent.setStatus("1");
			weChatContent.setAppID(appid);
			if (message == "") {
				map.put("Status", 1);
				map.put("ErrorMsg", "添加成功");
				WeChatContentService.addWeChatContent(weChatContent);
			} else {
				map.put("Status", -1);
				map.put("ErrorMsg", message);
			}
		return map;

	}

	@RequestMapping(value = "/MessageRequest", method = RequestMethod.POST)
	public void MessageRequest(HttpServletResponse resp, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {

			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();

				Map<String, String> map = MessageUtil.xmlToMap(req);
				String fromUserName = map.get("FromUserName");//
				String toUserName = map.get("ToUserName");//
				String content = map.get("Content");//
				String eventType = map.get("Event");//
				String eventKey = map.get("EventKey");//

				String path = req.getContextPath();
				String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
				// http://localhost:8080/WeNewsAgency/WeChatCustom/getWeChatMessage
				String url = "http://localhost:8080/WeNewsAgency/WeChatCustom/getWeChatMessage?fromUserName=FromUserName&toUserName=ToUserName&content=Content&eventType=EventType&eventKey=EventKey";
				if (eventType == null) {
					eventType = "";
				}
				if (eventKey == null) {
					eventKey = "";
				}
				if (content == null) {
					content = "";
				} else {
					content = URLEncoder.encode(content, "GBK");
					content = URLEncoder.encode(content, "GBK");
				}
				try {
					url = url.replace("FromUserName", fromUserName).replace("ToUserName", toUserName).replace("Content", content)
							.replace("EventType", eventType).replace("EventKey", eventKey);
				} catch (Exception e) {
					System.out.println(e);
					String clazz = this.getClass().getName();
					String method = Thread.currentThread().getStackTrace()[1].getMethodName();
					ErrorUtil.HandleError(null, clazz + "." + method, e);
				}
				JSONObject jsonObject = doGetStr(url);
				String Status = jsonObject.get("Status").toString();
				String message = "";
				if (Integer.parseInt(Status) == 1) {
					message = jsonObject.get("Message").toString();
				}
	}

	/**
	 * 接口类型获取AccessToken
	 * 
	 * @param resp
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	@RequestMapping(value = "/getAccessToken", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAccessToken(HttpServletResponse resp, HttpServletRequest req)
			throws IOException, ParseException, java.text.ParseException {
		Map<String, Object> map = new HashMap<>();

			String appid = req.getParameter("AppID");
			String appsecret = req.getParameter("Appsecret");
			String AccessToken = null;
			String message = "";
			if (appid != null && appsecret != null) {
				AccessToken = WeChatMenuUtil.getAccessToken(appid, appsecret);
				if (AccessToken == null) {
					message += "AppID或者Appsecret有误";
				}
			} else {
				message += "AppID或者Appsecret为空";
			}
			if (message == "") {
				map.put("Status", 1);
				map.put("AccessToken", AccessToken);
			} else {
				map.put("Status", -1);
				map.put("message", message);
			}
		return map;
	}

	/**
	 * 获得XML数据
	 * 
	 * @param resp
	 * @param req
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getWeChatMessage", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWeChatMessage(HttpServletResponse resp, HttpServletRequest req) throws IOException {
		Map<String, Object> map = null;

			String fromUserName = req.getParameter("fromUserName");// 从谁那里发过来
			String toUserName = req.getParameter("toUserName");// 微信公众号ID
			String msgType = req.getParameter("msgType");// 得到信息的类型
			String content = req.getParameter("content");// 用户输入的值
			String eventType = req.getParameter("eventType");// 获得操作的类型
			String eventKey = req.getParameter("eventKey");// 操作类型的值
			String message = null;
			String erroe = null;
			// String msgType=null;
			if (content != null && !content.equals("")) {
				content = java.net.URLDecoder.decode(content, "gb2312");
				msgType = "text";
			}
			if (eventKey != null && !eventKey.equals("")) {
				msgType = "event";
			}
			if ((content != null && !content.equals("")) && (eventKey != null && !eventKey.equals(""))) {
				msgType = "event";// 这是关注
			}

			if (fromUserName != null && toUserName != null && msgType != null) {
				if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
					Map<String, Object> map1 = new HashMap<>();
					map1.put("WID", toUserName);
					map1.put("Key", content);
					map1.put("Type", 4);
					map1.put("Status", 1);
					List<WeChatContent> mList = WeChatContentService.getWeChatContentList(map1);
					if (mList.size() <= 0) {
						message = MessageUtil.initText(toUserName, fromUserName, "哦，我脑袋炸了，不好使！");
					} else {
						if (mList.size() == 1 && mList.get(0).getRules().equals("text")) {
							message = MessageUtil.initText(toUserName, fromUserName, mList.get(0).getContent());
						}
						if (mList.size() >= 1 && mList.get(0).getRules().equals("mpnews")) {
							List<News> newsList = new ArrayList<>();
							for (int i = 0; i < mList.size(); i++) {
								News news = new News();
								news.setTitle(mList.get(i).getTitle());
								news.setDescription(mList.get(i).getDescription());
								news.setPicUrl(mList.get(i).getPicUrl());
								news.setUrl(mList.get(i).getUrl());
								newsList.add(news);
							}
							message = MessageUtil.initNewsMessage(toUserName, fromUserName, newsList);
						}
						if (mList.size() == 1 && mList.get(0).getRules().equals("image")) {
							message = MessageUtil.initImageMessage(toUserName, fromUserName, mList.get(0).getPicUrl());
						}
					}
				} else if (MessageUtil.MESSAGE_EVNET.equals(msgType)) {
					if (eventType != null) {
						if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {// 关注
							map = new HashMap<>();
							System.out.println("-------------------");
							System.out.println(fromUserName + "--------------------" + toUserName);
							map.put("WID", toUserName);
							map.put("Type", 3);
							map.put("start", 0);
							map.put("limit", 1);
							List<WeChatContent> mChatContents = WeChatContentService.getWeChatContentList(map);
							if (mChatContents.size() > 0) {
								message = MessageUtil.initText(toUserName, fromUserName, mChatContents.get(0).getContent());
							} else {
								message = MessageUtil.initText(toUserName, fromUserName, "欢迎关注我！");
							}

						} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
							Map<String, Object> params = new HashMap<>();
							params.put("Key", eventKey);
							List<WeChatContent> weChatContents = WeChatContentService.getWeChatContentList(params);
							if (weChatContents.size() > 0) {
								int type = weChatContents.get(0).getType();
								switch (type) {
								case 1:
									message = MessageUtil.initText(toUserName, fromUserName, weChatContents.get(0).getContent());
									break;
								case 4:
									List<News> newsList = new ArrayList<News>();
									for (int i = 0; i < weChatContents.size(); i++) {
										News news = new News();
										news.setTitle(weChatContents.get(i).getTitle());
										news.setDescription(weChatContents.get(i).getDescription());
										news.setPicUrl(weChatContents.get(i).getPicUrl());
										news.setUrl(weChatContents.get(i).getUrl());
										newsList.add(news);
									}
									message = MessageUtil.initNewsMessage(toUserName, fromUserName, newsList);
									break;
								default:
									break;
								}
							}
						}
					} else {
						erroe += "eventType为空";
					}
				}
			} else {
				erroe += "请检查fromUserName，toUserName，msgType是否为空";
			}
			map = new HashMap<>();
			if (erroe == null) {
				map.put("Status", 1);
				map.put("Message", message);
			} else {
				map.put("Stauts", -1);
				map.put("erroe", erroe);
			}
		return map;
	}
	//

	@RequestMapping(value = "/MessageRequest", method = RequestMethod.GET)
	public void MessageRequestGet(HttpServletResponse resp, HttpServletRequest req, HttpSession session, Model model)
			throws ClientProtocolException, IOException, Exception {
			String signature = req.getParameter("signature");
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String echostr = req.getParameter("echostr");
			PrintWriter out = resp.getWriter();
			if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}

	}

	private String SubscribeStr() {
		List<WeChatContent> mChatContents = null;
			Map<String, Object> map = new HashMap<>();
			map.put("Type", 3);
			map.put("start", 0);
			map.put("limit", 1);
			mChatContents = WeChatContentService.getWeChatContentList(map);
			System.out.println(mChatContents.get(0).getCreateTime());

		return mChatContents.get(0).getContent();

	}

	@SuppressWarnings("null")
	private String str(String str) throws UnsupportedEncodingException {
			if (str != null || !str.isEmpty()) {
				str = SmBaseUtil.URLDecoderString(str);
			}

		return str;
	}


	public static Object[] getJsonToArray(String str) {
		JSONArray jsonArray = null;
			jsonArray = JSONArray.fromObject(str);

		return jsonArray.toArray();
	}

	/**
	 * 把数据存到数据库，并获得这组的ID号；
	 * 
	 * @param json
	 */
	@SuppressWarnings("rawtypes")
	private String jsonToSql(String json) {
		Long iD = new IdWorker(1, 0).nextId();// 父ID
			Map<String, Object> map = new HashMap<>();
			
			JSONArray jsonArray = JSONArray.fromObject(json);
			Object[] obj = getJsonToArray(jsonArray.toString());

			for (int i = 0; i < obj.length; i++) {

				JSONObject jsonObject = (JSONObject) obj[i];
				map = new HashMap<>();
				map.put("name", jsonObject.getString("name"));
				if (jsonObject.getString("sub_button").equals("[]")) {
					// System.out.println("第"+(i+1)+"个按钮");
					
					Iterator iterator = jsonObject.keys();
					map = new HashMap<>();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						String value = jsonObject.getString(key);
						// System.out.println(key+"----"+value);
						map.put(key, value);
					}

					WeChatCustom weChatCustom = new WeChatCustom();
					if (i == 0) {
						weChatCustom.setID(iD);
					} else {
						Long iD2 = new IdWorker(1, 0).nextId();

						weChatCustom.setID(iD2);
					}
					weChatCustom.setName((String) map.get("name"));
					weChatCustom.setType((String) map.get("type"));
					if (map.get("type").equals("view")) {
						weChatCustom.setURL((String) map.get("url"));
					}
					if (map.get("type").equals("click")) {
						weChatCustom.setKey((String) map.get("key"));
					}
					weChatCustom.setParentID(i + 1);
					weChatCustom.setStatus(1);
					weChatCustom.setCreateTime(sdf.format(new Date()));
					weChatCustom.setmGroup(iD);
					WeChatCustomService.addWeChatCustom(weChatCustom);

				} else {
					Long iD2 = new IdWorker(1, 0).nextId();
					String name = jsonObject.getString("name");
					WeChatCustom weChatCustom = new WeChatCustom();
					if (i == 0) {
						weChatCustom.setID(iD);
					} else {
						weChatCustom.setID(iD2);
					}
					weChatCustom.setName(name);
					weChatCustom.setStatus(1);
					weChatCustom.setmGroup(iD);
					weChatCustom.setParentID(i + 1);
					weChatCustom.setCreateTime(sdf.format(new Date()));
					WeChatCustomService.addWeChatCustom(weChatCustom);

					// System.out.println(name);
					// System.out.println("第"+(i+1)+"个按钮");
					String sub_button = jsonObject.getString("sub_button");
					JSONArray jsonArray2 = JSONArray.fromObject(sub_button);
					Object[] obj2 = getJsonToArray(jsonArray2.toString());
					for (int j = 0; j < obj2.length; j++) {
						// System.out.println("第"+(i+1)+"个按钮第"+(j+1)+"个子菜单");
						JSONObject jsonObject2 = (JSONObject) obj2[j];
						Iterator iterator = jsonObject2.keys();
						map = new HashMap<>();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							String value = jsonObject2.getString(key);
							// System.out.println(key+"----"+value);
							map.put(key, value);
						}
						WeChatCustom weChatCustom2 = new WeChatCustom();
						Long iD3 = new IdWorker(1, 0).nextId();
						weChatCustom2.setID(iD3);
						weChatCustom2.setName((String) map.get("name"));
						weChatCustom2.setType((String) map.get("type"));
						if (map.get("type").equals("view")) {
							weChatCustom2.setURL((String) map.get("url"));
						}
						if (map.get("type").equals("click")) {
							weChatCustom2.setKey((String) map.get("key"));
						}
						weChatCustom2.setParentID(weChatCustom.getID());
						weChatCustom2.setStatus(1);
						weChatCustom2.setCreateTime(sdf.format(new Date()));
						WeChatCustomService.addWeChatCustom(weChatCustom2);
					}

				}

			}
		return iD.toString();
	}

	private JSONArray sqlToJson(String id) {
		JSONArray jsonArray = null;
			List<Map<String, Object>> list = new ArrayList<>();

			Map<String, Object> map = new HashMap<>();// 次MAP用作数据库的条件
			map.put("mGroup", id);
			List<WeChatCustom> mList = WeChatCustomService.getWeChatCustomList(map);
			if (mList.size() > 0) {
				for (int i = 0; i < mList.size(); i++) {
					if (mList.get(i).getType() == null) {
						Map<String, Object> json = new HashMap<>();
						json.put("id", String.valueOf(mList.get(i).getID()));
						json.put("name", mList.get(i).getName());
						json.put("mGroup", mList.get(i).getmGroup().toString());
						map = new HashMap<>();
						map.put("ParentID", String.valueOf(mList.get(i).getID()));
						List<WeChatCustom> mList2 = WeChatCustomService.getWeChatCustomList(map);
						List<Map<String, Object>> mMaps = new ArrayList<>();
						if (mList2.size() > 0) {

							for (int j = 0; j < mList2.size(); j++) {
								Map<String, Object> map2 = new HashMap<>();
								map2.put("id", String.valueOf(mList2.get(j).getID()));
								map2.put("name", mList2.get(j).getName());
								map2.put("type", mList2.get(j).getType());

								if (mList2.get(j).getType().equals("view")) {
									map2.put("url", mList2.get(j).getURL());
								}
								if (mList2.get(j).getType().equals("click")) {
									map2.put("key", mList2.get(j).getKey());
								}
								// System.out.println("子"+JSONObject.fromObject(map2));
								mMaps.add(map2);
							}
							// System.out.println("总"+JSONArray.fromObject(mMaps));
						}
						json.put("children", mMaps);
						list.add(json);
					} else {
						// 没有子菜单
						Map<String, Object> map2 = new HashMap<>();
						map2.put("id", String.valueOf(mList.get(i).getID()));
						map2.put("name", mList.get(i).getName());
						map2.put("type", mList.get(i).getType());
						map2.put("mGroup", mList.get(i).getmGroup().toString());
						if (mList.get(i).getType().equals("view")) {
							map2.put("url", mList.get(i).getURL());
						}
						if (mList.get(i).getType().equals("click")) {
							map2.put("key", mList.get(i).getKey());
						}
						map2.put("children", "am[]naij");

						list.add(map2);
					}
				}
			}
			jsonArray = JSONArray.fromObject(list);
			System.out.println("数据库获得的JSON" + jsonArray);
		return jsonArray;
	}

	private String sqlToWeChat(String id) {
		String jsontowechat = null;

			List<Map<String, Object>> list = new ArrayList<>();

			Map<String, Object> map = new HashMap<>();// 次MAP用作数据库的条件
			map.put("mGroup", id);
			List<WeChatCustom> mList = WeChatCustomService.getWeChatCustomList(map);
			if (mList.size() > 0) {
				for (int i = 0; i < mList.size(); i++) {
					if (mList.get(i).getType() == null) {
						Map<String, Object> json = new HashMap<>();
						json.put("name", mList.get(i).getName());
						map = new HashMap<>();
						map.put("ParentID", mList.get(i).getID());
						List<WeChatCustom> mList2 = WeChatCustomService.getWeChatCustomList(map);
						List<Map<String, Object>> mMaps = new ArrayList<>();
						if (mList2.size() > 0) {

							for (int j = 0; j < mList2.size(); j++) {
								Map<String, Object> map2 = new HashMap<>();
								map2.put("name", mList2.get(j).getName());
								map2.put("type", mList2.get(j).getType());
								if (mList2.get(j).getType().equals("view")) {
									map2.put("url", mList2.get(j).getURL());
								}
								if (mList2.get(j).getType().equals("click")) {
									map2.put("key", mList2.get(j).getKey());
								}
								// System.out.println("子"+JSONObject.fromObject(map2));
								mMaps.add(map2);
							}
							// System.out.println("总"+JSONArray.fromObject(mMaps));
						}
						json.put("sub_button", mMaps);
						list.add(json);
					} else {
						// 没有子菜单
						Map<String, Object> map2 = new HashMap<>();
						map2.put("name", mList.get(i).getName());
						map2.put("type", mList.get(i).getType());
						if (mList.get(i).getType().equals("view")) {
							map2.put("url", mList.get(i).getURL());
						}
						if (mList.get(i).getType().equals("click")) {
							map2.put("key", mList.get(i).getKey());
						}
						list.add(map2);
					}
				}
			}
			jsontowechat = "{\"button\":" + JSONArray.fromObject(list).toString() + "}";
		return jsontowechat;
	}
	
}
