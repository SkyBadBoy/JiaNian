package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import wtb.core.model.ApplyList;
import wtb.core.model.HonorRecord;
import wtb.core.model.Integration;
import wtb.core.model.KeyWord;
import wtb.core.model.Notices;
import wtb.core.model.NoticesContent;
import wtb.core.model.NoticesTemp;
import wtb.core.model.LikeRecord;
import wtb.core.model.PayRecord;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.StudentsLog;
import wtb.core.model.Users;
import wtb.core.model.Vote;
import wtb.core.model.VoteRecords;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.core.service.IntegrationService;
import wtb.core.service.NoticesContentService;
import wtb.core.service.StudentsService;
import wtb.core.service.WeMoneyService;
import wtb.smUtil.EmojiUtil;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.Similarity;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseGlobal.CheckStatus;
import wtb.smUtil.SmBaseGlobal.HonerType;
import wtb.smUtil.SmBaseGlobal.IntegrationType;
import wtb.smUtil.SmBaseGlobal.KeywordType;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.VerifyCode;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Notices")
public class NoticesController extends BaseController {

	@RequestMapping(value = "/addNotices", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("NoticesForm") Notices Notices, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		String preView = null;
		String PID = req.getParameter("pid");
		preView = req.getParameter("preView");
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Notices> lsps = ReadNoticesService.getReadNoticesList(responseMap);
			if (lsps.size() > 0) {
				Notices = lsps.get(0);

				model.addAttribute("Content", Notices.getContent());
				Status = 1;
			} else {
				Status = 404;
			}
		}
		String isNew = req.getParameter("isNew");
		model.addAttribute("isNew", isNew);
		model.addAttribute("NoticesForm", Notices);
		model.addAttribute("Status", Status);
		if (preView != null && preView.equals("1")) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewNotices");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addNotices");
		}
	}

	@RequestMapping(value = "/addNews", method = RequestMethod.GET)
	public ModelAndView addNews(@ModelAttribute("NoticesForm") Notices Notices, BindingResult result, HttpServletResponse response, HttpServletRequest req,
			HttpSession session, Model model) throws IOException {
		String preView = null;
		Long ProdID = new IdWorker(1, 0).nextId();
		int Status = 0;
		String PID = req.getParameter("pid");
		preView = req.getParameter("preView");
		String AuthType = req.getParameter("AuthType");
		long areaid = 0;
		model.addAttribute("AuthType", AuthType);
		String isNew = req.getParameter("isNew");
		model.addAttribute("isNew", isNew);
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Notices> lsps = ReadNoticesService.getReadNoticesList(responseMap);

			if (lsps.size() > 0) {
				Notices = lsps.get(0);
				ProdPictures lsimg = Notices.getImage();
				if (lsimg != null) {

					model.addAttribute("Image", Notices.getImage().getUrl());

				} else {
					model.addAttribute("Image", "#");
				}

				model.addAttribute("id", Notices.getID());
				model.addAttribute("Content", Notices.getContent());
				model.addAttribute("Pid", Notices.getPKID());
				areaid = Notices.getAreaID();
				Status = 1;

			} else {
				Status = 404;
			}
		}

		model.addAttribute("NoticesForm", Notices);
		model.addAttribute("Status", Status);
		model.addAttribute("AreaID", areaid);
		req.setAttribute("id", ProdID);
		if (preView != null && preView.equals("1")) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewNotices");
		} else {

			return new ModelAndView(SmBaseGlobal.WebViewPath + "addNews");
		}
	}

	@RequestMapping(value = "/addNotices", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("NoticesForm") Notices Notices, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) {
		Users user = (Users) session.getAttribute("UserName");
		String AuthType = req.getParameter("AuthType");
		long ProdID = 0;
		if (req.getParameter("ID") != null && !req.getParameter("ID").isEmpty() && !req.getParameter("ID").equals("0")) {
			ProdID = Long.parseLong(req.getParameter("ID"));
		} else {
			ProdID = SmBaseUtil.CreateNewID();
		}
		String Content = req.getParameter("Content");
		String Author = req.getParameter("Author");
		String Title = req.getParameter("Title");
		String isNew = req.getParameter("isNew");
		String imageIDs = req.getParameter("Images");
		String DelImages = req.getParameter("DelImages");
		if (user.getLevel() == SmBaseGlobal.LevelStatus.StudentManage.getid()) {
			AuthType = "Students";
		}
		model.addAttribute("AuthType", AuthType);

		if (Notices.getID() > 0) {
			ProdID = Notices.getID();
		} else {
			Notices.setID(ProdID);
		}

		if (Notices.getTitle() == null || Notices.getTitle().isEmpty()) {
			result.rejectValue("Title", "misFormat", "标题不能为空");
		}
		if (Notices.getContent().isEmpty()) {
			result.rejectValue("Content", "misFormat", "内容不能为空");
		} else {
			model.addAttribute("Content", Notices.getContent());
		}
		if (!Notices.getAuthor().isEmpty() && Notices.getAuthor().length() >= 50) {
			result.rejectValue("Author", "misFormat", "作者不能大于50个字符");
		}
		if (isNew != null && Notices.getType() == 2 && !isNew.isEmpty() && (imageIDs == null || imageIDs.isEmpty())) {
			result.rejectValue("Image", "misFormat", "请选择至少一张封面图片");
		}
		if (imageIDs != null && imageIDs.length() > 0) {
			Notices = SavePicture(imageIDs, DelImages, Notices);
		}
		Notices.setAreaID(user.getAreaID());
		if (!result.hasErrors()) {
			/**
			 * isNew有值：说明时新用户
			 */
			if (isNew != null && !isNew.isEmpty())
		if (isNew != null && !isNew.isEmpty())

			Notices.setPublishUser(String.valueOf(user.getID()));
			Notices.setID(ProdID);
			Notices.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			Notices.setAreaID(user.getAreaID());
			Notices.setModifyTime(sdf.format(new Date()));
			Notices.setCreateTime(sdf.format(new Date()));
			Notices.setContent(Content);
			Notices.setAuthor(Author);
			Notices.setTitle(Title);
			Notices.setClickCount(0);
			Notices.setRegion(0);
			Notices.setIsCity(0);
			Notices.setIsDel(1);
			Notices.setIsPro(0);
			NoticesService.addNotices(Notices);
			return new ModelAndView(SmBaseGlobal.WebViewPath + "success2");
		} else {

			Map<String, Object> map = new HashMap<>();
			map.put("ID", Notices.getID());
			List<Notices> mList = ReadNoticesService.getReadNoticesList(map);
			mList.get(0).setContent(Content);
			mList.get(0).setAuthor(Author);
			mList.get(0).setTitle(Title);
			mList.get(0).setImageID(Notices.getImageID());
			mList.get(0).setModifyTime(sdf.format(new Date()));
			NoticesService.updateNotices(mList.get(0));
			if (Notices.getType() == 1) {
				model.addAttribute("content", req.getContextPath() + "/Notices/NoticesList");
			} else {
				model.addAttribute("content", req.getContextPath() + "/Notices/NewsList");
			}
		}
		model.addAttribute("NoticesForm", Notices);// 把accountVo对象返回到页面，这样不至于表单被清空"

		if (Notices.getType() == 1) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addNotices");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addNews");
		}

	}

	@RequestMapping(value = "/NoticesList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {

		return new ModelAndView(SmBaseGlobal.WebViewPath + "NoticesList");
	}

	@RequestMapping(value = "/NewsList", method = RequestMethod.GET)
	public ModelAndView NewsList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = null;
		user = (Users) session.getAttribute("UserName");
		String PID = request.getParameter("pid");
		String Type = request.getParameter("Type");
		String Status = request.getParameter("Status");
		String shang = request.getParameter("shang");
		if (shang != null) {
			model.addAttribute("shang", shang);
		}

		if (shang != null) {
			model.addAttribute("shang", shang);
			if (Integer.parseInt(shang) == 2) {
				String key = request.getParameter("rules");
				key = SmBaseUtil.URLDecoderString(key);
				model.addAttribute("key", key);
			}
		}
		if(Status==null || Status.equals("")){
			Status="1";
		}
		model.addAttribute("Type", Type);
		model.addAttribute("Status", Status);
		model.addAttribute("NewsID", PID);
		responseMap.put("SrcID", PID);
		responseMap.put("UserID", user.getID());
		MessagesService.ReadedMessages(responseMap);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "NewsList");
	}

	@RequestMapping(value = "/getMyNoticesList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getMyNoticesList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException, SocketException, UnknownHostException, ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		// responseMap.putAll(MakeQueryParam(request,session));
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		String userid=SmBaseUtil.getIpAddr(request);
		if (user!=null) {
			userid=String.valueOf(user.getID());
		}
		String uid=request.getParameter("uid");
		if(uid!=null&&uid!=""){
			responseMap.put("UsersID", uid);
		}else{
			if(user==null){
				responseMap.put("UsersID", 0);
			}else{
				responseMap.put("UsersID", user.getID());
			}
			
		}
		// responseMap.put("AreaID", AreaID);
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Notices> mList = ReadNoticesService.getReadNoticesList(responseMap);
		for (int i = 0; i < mList.size(); i++) {
			mList.set(i, SmBaseUtil.getPCWeNewsData(request, mList.get(i), ReadLikeRecordService, VoteRecordsService)) ;
			mList.get(i).setContent(SmBaseUtil.StripHT(mList.get(i).getContent()));
			
			
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", mList);
		responseMap.put("Status", 1);
		responseMap.put("_page_", request.getParameter("pageNumber"));
		return responseMap;
	}


	@RequestMapping(value = "/getNoticesList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getNoticesList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		String UnitAreaID = request.getParameter("UnitAreaID");
		String isOpen = request.getParameter("isOpen");
		String title = request.getParameter("title");
		String pid = request.getParameter("pid");
		if (pid != null && pid != "undefined" && pid != "") {
			checkParammap.put("ID", pid);
		}
		List<Notices> lswe = null;
		int Prodcount = 0;
		if (isOpen != null) {
			if (Integer.parseInt(isOpen) == 1 && UnitAreaID!=null && !UnitAreaID.isEmpty()) {
				checkParammap.put("AreaID", UnitAreaID);
			}
		}
		if (title != null && !title.isEmpty()) {
			title = SmBaseUtil.URLDecoderString(title);
			checkParammap.put("Title", title);
			System.out.println(title);
		}
		checkParammap.put("Rand", SmBaseUtil.Random());
		checkParammap.putAll(makeQueryParam(request, session));
		lswe = ReadNoticesService.getReadNoticesList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String produrl = SmBaseUtil.getProjectBaseUrl(request);
			String baseUrl=SmBaseUtil.getProjectBaseUrl(request);
			if (lswe.get(i).getImage() == null || lswe.get(i).getImage().getUrl() == null ||lswe.get(i).getImage().getUrl().isEmpty() ) {
				produrl = baseUrl + SmBaseGlobal.ErrorPicture;
				lswe.get(i).setImage(new ProdPictures());
			} else {
				produrl = lswe.get(i).getImage().getUrl().split(",")[0];
			}
			String url = SmBaseUtil.getClickImageHtml(produrl);
			lswe.get(i).getImage().setPUrl(url);
			lswe.get(i).setContent(SmBaseUtil.StripHT(lswe.get(i).getContent()));
		}

		Prodcount = ReadNoticesService.getNoticesCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	
	@RequestMapping(value = "/getphoneNoticesList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPhoneActivityList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String uid=request.getParameter("uid");
		responseMap.put("UsersID", uid);
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Notices> lswe = ReadNoticesService.getReadNoticesList(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("_page_", request.getParameter("pageNumber"));
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		return responseMap;
	}
	
	@RequestMapping(value = "/deleteNotices", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;
		Users user = null;
		String WeChat = request.getParameter("pid");
		String state = request.getParameter("state");
		user = (Users) request.getSession().getAttribute("UserName");
		int Level=3;
		if (request.getSession().getAttribute("UserName")==null) {
			Level=Integer.parseInt(request.getParameter("Level"));
		}else {
			Level = user.getLevel();
		}
		String[] wids = WeChat.split(",");
		int result = 0;
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", id);
			responseMap.put("Sina", SmBaseUtil.Random());
			List<Notices>list=ReadNoticesService.getReadNoticesList(responseMap);
			if(list.size()>0){
				responseMap = new HashMap<String, Object>();
				if (!id.isEmpty()) {
					responseMap.put("ID", Long.parseLong(id));
					responseMap.put("ModifyTime", new Date());
					responseMap.put("Status", state);
					if (state.equals(String.valueOf(SmBaseGlobal.CheckStatus.Effective.getid()))) {
						checkIsActicity(list.get(0),ReadVoteService,KeyWordService);
						NoticesService.enabledNotices(responseMap);
						StudentsService.UpNoticeCount(Long.parseLong(list.get(0).getPublishUser()));
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", list.get(0).getID());
						
						//SmBaseUtil.SendNoticesPublisMessage(list.get(0).getStudent().getPhone(),request.getServletContext());
						
						addLevel(Long.parseLong(list.get(0).getPublishUser()), "新闻通过审核", list.get(0), 5, IntegrationService, StudentsService, WeMoneyService, NoticesService, WeMoneyRecordService, ReadNoticesService, ReadStudentsService, ReadWeMoneyService,ReadVoteService);
					} else if (state.equals(String.valueOf(SmBaseGlobal.CheckStatus.Disabled.getid()))) {
						if (Level == 3) {
							NoticesService.deleteNotices(responseMap);
						} else {
							NoticesService.deleteNoticesisDel(responseMap);
						}
						StudentsService.CancelNoticeCount(Long.parseLong(list.get(0).getPublishUser()));
						addLevel(Long.parseLong(list.get(0).getPublishUser()), "管理员删除新闻", list.get(0), -5, IntegrationService, StudentsService, WeMoneyService, NoticesService, WeMoneyRecordService, ReadNoticesService, ReadStudentsService, ReadWeMoneyService,ReadVoteService);
					} else if (state.equals(String.valueOf(SmBaseGlobal.CheckStatus.NotPass.getid()))) {
						NoticesService.NoPassNotices(responseMap);
					}else if (state.equals(String.valueOf(SmBaseGlobal.CheckStatus.Up.getid()))) {
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", list.get(0).getID());
						responseMap.put("IsTop",1 );
						NoticesService.UpdateTop(responseMap);
					}else if (state.equals(String.valueOf(SmBaseGlobal.CheckStatus.Down.getid()))) {
						responseMap = new HashMap<String, Object>();
						responseMap.put("ID", list.get(0).getID());
						responseMap.put("IsTop",0);
						NoticesService.UpdateTop(responseMap);
					}
					result++;
				}
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	@RequestMapping(value = "/deleteMyCaoGao", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteMyCaoGao(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<>();
		Students user = (Students) request.getSession().getAttribute("StudentName");
		responseMap.put("ID", user.getID());
		int i=NoticesTempService.deleteNotices(responseMap);
		if (i>0) {
			responseMap = new HashMap<>();
			responseMap.put("status", true);
		}else{
			responseMap.put("status", false);
			responseMap.put("Message", "删除失败");
		}
		session.removeAttribute("NoticesTemp");
		return responseMap;
	}
	//上头条
	@RequestMapping(value = "/HitHeadlines", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> HitHeadlines(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<>();
		String nid=request.getParameter("nid");
		if(nid==null || nid.isEmpty()){
			nid="0";
		}
		String istop=request.getParameter("nid");
		if(istop==null || istop.isEmpty() || !SmBaseUtil.isNumeric(istop)){
			istop="0";
		}
		responseMap.put("ID", nid);
		responseMap.put("IsTop", istop);
		int i=NoticesService.UpdateTop(responseMap);
		if (i>0) {
			responseMap = new HashMap<>();
			responseMap.put("status", true);
		}else{
			responseMap.put("status", false);
			responseMap.put("Message", "操作成功");
		}
		return responseMap;
	}
	
	@RequestMapping(value = "/deleteMyNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteMyNotices(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<>();

		String WeChat = request.getParameter("pid");
		responseMap.put("ID", WeChat);
		try{
			int i =0;
			try{
				i = NoticesService.deleteNotices(responseMap);
			}catch (Exception e) {
				i = NoticesService.deleteNotices(responseMap);
			}
			if (i == 1) {
				responseMap.put("result", 1);
				Students user = (Students) session.getAttribute("StudentName");
				StudentsService.CancelNoticeCount(user.getID());
			} else {
				responseMap.put("result", -1);
			}
		}catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			responseMap.put("result", -1);
			responseMap.put("Message", "服务器繁忙,请稍后再试");
		}
		return responseMap;
	}

	@RequestMapping(value = "/phoneaddnotices", method = RequestMethod.GET)
	public ModelAndView phoneaddactivity(HttpServletResponse response, HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Students user = (Students) req.getSession().getAttribute("StudentName");
		if (user==null || user.getPhone() == null || user.getPhone().isEmpty() 
				|| user.getAreaID() == null || user.getAreaID().isEmpty()
				|| user.getParentName() == null || user.getParentName().isEmpty()) {
			response.sendRedirect(SmBaseGlobal.XBLoginOrRegister);
			return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaActivityAdd");
		}
		String notice=req.getParameter("Notice");
		NoticesTemp noticesTemp=new NoticesTemp();
		if (notice!=null) { 
			Map<String, Object> map=new HashMap<>();
			Notices notices=ReadNoticesService.getNoticesByID(Long.parseLong(notice));
			noticesTemp.setTitle(notices.getTitle());
			noticesTemp.setContent(notices.getContent());
			map.put("ProductID", notice);
			String imageIDList="";
			String imageUrl="";
			String imgLiId="";
			String imgLiSrc="";
			List<ProdPictures> pictures=ReadProdPicturesService.getPictureList(map);
			for (int i = 0; i < pictures.size(); i++) {
				if (i==pictures.size()-1) {
					imageIDList+=pictures.get(i).getID();
					imgLiId+=pictures.get(i).getID();
					imageUrl+=pictures.get(i).getUrl().split(",")[0];
					imgLiSrc+=pictures.get(i).getUrl().split(",")[0];
				}else{
					imageIDList=pictures.get(i).getID()+",";
					imgLiId+=pictures.get(i).getID()+",";
					imageUrl+=pictures.get(i).getUrl().split(",")[0]+",";
					imgLiSrc+=pictures.get(i).getUrl().split(",")[0]+",";
				}
			}
			noticesTemp.setImageIDList(imageIDList);
			noticesTemp.setImageUrl(imageUrl);
			noticesTemp.setImgLiId(imgLiId);
			noticesTemp.setImgLiSrc(imgLiSrc);
			model.addAttribute("NoticeID", notice);
			model.addAttribute("Tempdata", 0);
		}else{
			Map<String , Object> map=new HashMap<>();
			try{
				Object object=req.getSession().getAttribute("NoticesTemp");
				if (object!=null) {
					noticesTemp=(NoticesTemp) object;
					if (noticesTemp.getImageIDList()!=null) {
						String imageUrl="";
						String[] id=noticesTemp.getImageIDList().split(",");
						for (int i = 0; i < id.length; i++) {
							 map=new HashMap<>();
							 map.put("ID", id[i]);
							 List<ProdPictures> pictures=ReadProdPicturesService.getPictureList(map);
							 if (pictures.size()>0) {
								if (i==id.length-1) {
									imageUrl+=pictures.get(0).getUrl().split(",")[0];
								}else{
									imageUrl+=pictures.get(0).getUrl().split(",")[0]+",";
								}
							}
						}
						noticesTemp.setImageUrl(imageUrl);
						noticesTemp.setImgLiSrc(imageUrl);
					}
					
				}else{
					map.put("ID", user.getID());
					map.put("status", SmBaseGlobal.CheckStatus.Effective.getid());
					List<NoticesTemp> noticesTemps=NoticesTempService.getNoticesList(map);
					if (noticesTemps.size()>0) {
						noticesTemp=noticesTemps.get(0);
						if (noticesTemp.getImageIDList()!=null) {
							String imageUrl="";
							String[] id=noticesTemp.getImageIDList().split(",");
							for (int i = 0; i < id.length; i++) {
								 map=new HashMap<>();
								 map.put("ID", id[i]);
								 List<ProdPictures> pictures=ReadProdPicturesService.getPictureList(map);
								 if (pictures.size()>0) {
									if (i==id.length-1) {
										imageUrl+=pictures.get(0).getUrl().split(",")[0];
									}else{
										imageUrl+=pictures.get(0).getUrl().split(",")[0]+",";
									}
								}
							}
							noticesTemp.setImageUrl(imageUrl);
							noticesTemp.setImgLiSrc(imageUrl);
						}
					}
					
				}
				if(noticesTemp!=null && ((noticesTemp.getTitle()!=null && !noticesTemp.getTitle().isEmpty())
						 || (noticesTemp.getContent()!=null && !noticesTemp.getContent().isEmpty())
						 || (noticesTemp.getImageIDList()!=null && !noticesTemp.getImageIDList().isEmpty()))){
					model.addAttribute("Tempdata", 1);//表示是否是草稿数据
				}
			}catch (Exception e) {
				ErrorUtil.HandleError(null, "wtb.core.controller.NoticesController.phoneaddnotices", e);
			}
			
		}
		model.addAttribute("data", noticesTemp);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath + "phoneSinaActivityAdd");
	}

	@RequestMapping(value = "/addphoneNotices", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addPhoneNotices(final HttpServletResponse response, HttpSession session, final HttpServletRequest req, String title, String content,
			String imageIDList) throws ParseException {
		System.err.println(title+"-------"+content+"---------"+imageIDList);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String ErrorMessage = "";
		Students user = (Students) session.getAttribute("StudentName");
		SmBaseGlobal.CheckStatus checkStatus = SmBaseGlobal.CheckStatus.Effective;
		String ImageIDList = req.getParameter("imageIDList");
		String DeviceType=req.getParameter("DeviceType");//设备的类型
		if(DeviceType==null || DeviceType.isEmpty()){
			DeviceType = String.valueOf(SmBaseGlobal.DriveType.Inteface.getid());
		}
		Integer proResult = 0;
		Notices Notices = null ;
		String noticeID=req.getParameter("NoticeID");
		long ProdID = SmBaseUtil.CreateNewID();
		if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
			ProdID=Long.parseLong(noticeID);
			Notices=ReadNoticesService.getNoticesByID(Long.parseLong(noticeID));
		}else{
			Notices=new Notices();
		}
		if (user == null) {
			/**为了做统一的请求， 新闻发稿从这边过*/
			String studentID=req.getParameter("studentID");
			if (studentID!=null&&!studentID.isEmpty()&&!studentID.equals("")&&studentID!="") {
				Map<String, Object> map=new HashMap<>();
				map.put("ID", studentID);
				List<Students> students=ReadStudentsService.getStudentsList(map);
				if (students.size()>0) {
					user=students.get(0);
				}else{
					ErrorMessage = "长时间未操作，请刷新再试";
				}
			}else{
				ErrorMessage = "长时间未操作，请刷新再试";
			}
		}
		if (title == null || title.isEmpty()||title.equals("null")) {
			ErrorMessage = "标题不能为空";
		}else if (title.length()>20) {
			ErrorMessage = "标题不能超过20个字符";
		}
		if (content == null || content.isEmpty()||content.equals("null")) {
			ErrorMessage = "内容不能为空";
		}
		if (ErrorMessage != null && !ErrorMessage.isEmpty()) {
			resultMap.put("Status", "false");
			resultMap.put("Message", ErrorMessage);
			return resultMap;
		}
		Notices.setAuthor(user.getName());
		Notices.setTitle(title);
		Notices.setContent(content);
		Notices.setWhoSend(Integer.parseInt(DeviceType));
		String pictures = "";
		if (ErrorMessage.isEmpty()) {

			Notices.setPublishUser(String.valueOf(user.getID()));
			Notices.setID(ProdID);
			Notices.setStatus(checkStatus.getid());
			Notices.setAreaID(Long.parseLong((user.getAreaID() == null || user.getAreaID().isEmpty()) ? "0" : user.getAreaID()));
			Notices.setType(SmBaseGlobal.NewType.News.getid());
			Notices.setContentType(SmBaseGlobal.NoticesContentType.Default.getid());//设置默认的方式
			Notices.setContent(EmojiUtil.resolveToEmojiFromByte(Notices.getContent()));
			// 更新图片信息
			if (ImageIDList != null && ImageIDList.length() > 0&&ImageIDList!=null) {
				if (ImageIDList.substring(0, 1).equals(",")) {
					ImageIDList = ImageIDList.substring(1);
				}
				String[] imgIDs = ImageIDList.split(",");
				for (String imgID : imgIDs) {
					resultMap = new HashMap<String, Object>();
					resultMap.put("ID", imgID);
					List<ProdPictures> lspic = ReadProdPicturesService.getPictureList(resultMap);
					if (lspic.size() > 0) {
						if (Notices.getImageID() == null || Notices.getImageID() <= 0) {
							Notices.setImageID(lspic.get(0).getID());
						}
						lspic.get(0).setWeChatID(user.getID());
						lspic.get(0).setProductID(Notices.getID());
						lspic.get(0).setType(1);
						ProdPicturesService.UpdateSimplePicture(lspic.get(0));
						pictures += ",\"" + lspic.get(0).getUrl().split(",")[0] + "\"";
					}

				}
			}
			try{
				try{
					if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
						proResult = NoticesService.updateNotices(Notices);
					} else {
						Notices = CheckAccordWithAutoRewardWeMoney(Notices,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,VoteService);
						proResult = NoticesService.addNotices(Notices);
						
					}
				}catch (Exception e) {
					//服务器未响应的情况下 进行第二次的递交
					if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
						proResult = NoticesService.updateNotices(Notices);
					} else {
						Notices = CheckAccordWithAutoRewardWeMoney(Notices,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,VoteService);
						proResult = NoticesService.addNotices(Notices);
						
					}
				}
				if (proResult > 0) {
					final Students tempuser = user;
					final Notices tempnotices = Notices;
					final String pics = pictures;
					Thread t = new Thread() {
						public void run() {
							// 添加同步日志,暂停同步
							//addStudentLog(tempuser, tempnotices, pics);
							// 检测重复的稿件
							boolean isrepeat= checkRepeatNotices(tempuser, tempnotices,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,req,VoteService);
							if(!isrepeat){//如果通过审核
								// 进行排名重排
								phoneSortNotices(String.valueOf(tempnotices.getAreaID()));
								checkIsActicity(tempnotices,ReadVoteService,KeyWordService);
								if(Integer.parseInt(tempuser.getDiamondLevel())>0){
									//钻级小编结算工资
									WagesSettlement(tempuser,PayRecordService,ReadPayRecordService,ReadNoticesService,req,response,WeChatInfoServices,ReadStudentsService);
								}
								//发送通知短信
								SmBaseUtil.SendNoticesPublisMessage(tempuser.getPhone(),req.getServletContext());
								
							}
						}
					};
					t.start();
					resultMap.put("Status", "true");
					resultMap.put("Message", "发布成功");
					Map<String , Object> map=new HashMap<>();
					map.put("ID",user.getID());
					session.removeAttribute("NoticesTemp");//发布之后就不用session也就被清空啦
					NoticesTempService.deleteNotices(map);
					return resultMap;
				} else {
					Notices.setID(0);
					ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
				}
			}catch (UncategorizedSQLException Uncategorized) {
				ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
			} catch (Exception e) {
				ErrorUtil.HandleError(null, "wtb.core.controller.addphoneNotices", e);
				ErrorMessage = "服务器繁忙,请稍后再试";
			}

		}
		resultMap.put("Status", "false");
		if (ErrorMessage == null || ErrorMessage.isEmpty()) {
			ErrorMessage = "保存失败,请稍后再试";
		}
		resultMap.put("Message", ErrorMessage);
		return resultMap;
	}
	
	
	/** 127.0.0.1/WeNewsAgency/Notices/addphoneNewNotices?title="123456"&content="asdfq4@::@5614qdasdfa@::@sdfqfasdfasdfwqefa@::@sdfxsadfqwreasdf"&type=0&studentID=367*///首次保存
	/** 127.0.0.1/WeNewsAgency/Notices/addphoneNewNotices?title="123456"&content="asdfq4@::@5614qdasdfa@::@sdfqfasdfasdfwqefa@::@sdfxsadfqwreasdf"&type=0&studentID=367&NoticeID=864313087052746752&contentID="864313127995314176,864313140685574144,864313146097405952,864313151593123840"*///第二次保存
	/**
	 * {
		Status: "true",
		Message: "保存成功",
		data: {
		NoticeID: 864313087052746800,
		ContentID: "864313127995314176,864313140685574144,864313146097405952,864313151593123840"
		}
		}
	 */
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @Author 作者：马健
	 * @Phone  联系qq：1039510196
	 * @CreateTime 创建时间：2017年5月15日 下午5:33:36
	 * @Details title: 标题  content ：内容 contentID 内容ID  type: 类型       1 发布       0 草稿    2 编辑
	 */
	@RequestMapping(value = "/addphoneNewNotices", method = {RequestMethod.POST,RequestMethod.GET})
	public @ResponseBody
	Map<String, Object> addphoneNewNotices(final HttpServletResponse response, HttpSession session, final HttpServletRequest req, String title, String content,
			String contentID,String imageIDList,int Type,int DeviceType) throws ParseException, UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String ErrorMessage = "";
		Students user = (Students) session.getAttribute("StudentName");
		
		Integer proResult = 0;
		Notices Notices = null ;
		String noticeID=req.getParameter("NoticeID");
		boolean isNews=false;
		long ProdID = SmBaseUtil.CreateNewID();
		if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
			ProdID=Long.parseLong(noticeID);
			Notices=ReadNoticesService.getNoticesHtmlByID(Long.parseLong(noticeID));
			if (Notices!=null && Notices.getStatus()==12&&Type==1) {//草稿
				isNews=true;
			}
			
		}else{
			Notices=new Notices();
		}
		if (user == null) {
			/**为了做统一的请求， 新闻发稿从这边过*/
			String studentID=req.getParameter("studentID");
			if (studentID!=null&&!studentID.isEmpty()&&!studentID.equals("")&&studentID!="") {
				Map<String, Object> map=new HashMap<>();
				map.put("ID", studentID);
				List<Students> students=ReadStudentsService.getStudentsList(map);
				if (students.size()>0) {
					user=students.get(0);
				}else{
					ErrorMessage = "长时间未操作，请刷新再试";
				}
			}else{
				ErrorMessage = "长时间未操作，请刷新再试";
			}
		}
		if (title == null || title.isEmpty()||title.equals("null")) {
			ErrorMessage = "标题不能为空";
		}else if (title.length()>20) {
			ErrorMessage = "标题不能超过20个字符";
		}
		if (content == null || content.isEmpty()||content.equals("null")) {
			ErrorMessage = "内容不能为空";
		}
		if (ErrorMessage != null && !ErrorMessage.isEmpty()) {
			resultMap.put("Status", "false");
			resultMap.put("Message", ErrorMessage);
			return resultMap;
		}
		Notices.setAuthor(user.getName());
		Notices.setTitle(title);
		Notices.setWhoSend(DeviceType);
		String pictures = "";
		if (ErrorMessage.isEmpty()) {

			Notices.setPublishUser(String.valueOf(user.getID()));
			Notices.setID(ProdID);
			
			if (Type==1) {
				Notices.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			}else{
				Notices.setStatus(SmBaseGlobal.CheckStatus.Draft.getid());
			}
			Notices.setAreaID(Long.parseLong((user.getAreaID() == null || user.getAreaID().isEmpty()) ? "0" : user.getAreaID()));
			Notices.setType(SmBaseGlobal.NewType.News.getid());
			Notices.setContentType(SmBaseGlobal.NoticesContentType.Html.getid());//设置HTML方式
			
			
			//Notices.setContent(EmojiUtil.resolveToEmojiFromByte(Notices.getContent()));
			// 更新内容信息
			
				String[] contents = content.split("@::@");
				String[] contentIDs=null;
				if (contentID!=null&&!contentID.equals("null")) {
					contentIDs=contentID.split(",");
				}
				String resultContentID="";
				NoticesContentService.DeleteAllContentBySrcID(ProdID);//删除所有的
				for (int i = 0; i < contents.length; i++) {
					NoticesContent noticesContent=new NoticesContent();
					long id=0;
					noticesContent.setNum(i);
					noticesContent.setSrcID(ProdID);
					noticesContent.setStatus(1);
					noticesContent.setContent(EmojiUtil.resolveToEmojiFromByte(contents[i]));
					if (contentIDs!=null&&i<contentIDs.length&&!contentIDs.equals("null")) {//大于0说明存在的
						if (contentIDs[i]!=null&&contentIDs[i]!="") {
							id=Long.parseLong(contentIDs[i]);
							noticesContent.setID(id);
							NoticesContentService.UpdateContent(noticesContent);
						}
					}else{
						id=SmBaseUtil.CreateNewID();
						noticesContent.setID(id);
						NoticesContentService.AddContent(noticesContent);
					}
					
					if (i==contents.length-1) {
						resultContentID=resultContentID+id;
					}else{
						resultContentID=resultContentID+id+",";
					}
				}
				Notices.setContent(EmojiUtil.resolveToEmojiFromByte(content.replace("@::@", "<p>")));//换行
				
				ProdPicturesService.DeleteAllPicByProductID(ProdID);//首先删除所有的图片
				
				if (imageIDList != null && imageIDList.length() > 0&&imageIDList!=null) {
					if (imageIDList.substring(0, 1).equals(",")) {
						imageIDList = imageIDList.substring(1);
					}
					String[] imgIDs = imageIDList.split(",");
					for (String imgID : imgIDs) {
						resultMap = new HashMap<String, Object>();
						resultMap.put("ID", imgID);
						List<ProdPictures> lspic = ReadProdPicturesService.getPictureList(resultMap);
						if (lspic.size() > 0) {
							if (Notices.getImageID() == null || Notices.getImageID() <= 0) {
								Notices.setImageID(lspic.get(0).getID());
							}
							lspic.get(0).setWeChatID(user.getID());
							lspic.get(0).setProductID(Notices.getID());
							lspic.get(0).setType(1);
							ProdPicturesService.UpdateSimplePicture(lspic.get(0));
							pictures += ",\"" + lspic.get(0).getUrl().split(",")[0] + "\"";
						}

					}
				}
			try{
				if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
					if (isNews) {//从来没有发布过的时候 要变为发布的时候 把创建时间改成现在的
						proResult = NoticesService.updateNoticesDraft(Notices);
					}else{
						proResult = NoticesService.updateNotices(Notices);
					}
				} else {
					if (Notices.getStatus()==SmBaseGlobal.CheckStatus.Effective.getid()) {
						Notices = CheckAccordWithAutoRewardWeMoney(Notices,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,VoteService);
					}
					proResult = NoticesService.addNotices(Notices);
				}
				if (proResult > 0) {
					final Students tempuser = user;
					final Notices tempnotices = Notices;
					final String pics = pictures;
					String message="发布成功";
					if (Type==1) {
						if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")&&isNews==false) {
							message="修改成功";
						}else{
							Thread t = new Thread() {
								public void run() {
									// 添加同步日志,暂停同步
									//addStudentLog(tempuser, tempnotices, pics);
									// 检测重复的稿件
									boolean isrepeat= checkRepeatNotices(tempuser, tempnotices,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,req,VoteService);
									if(!isrepeat){//如果通过审核
										// 进行排名重排
										phoneSortNotices(String.valueOf(tempnotices.getAreaID()));
										checkIsActicity(tempnotices,ReadVoteService,KeyWordService);
										if(Integer.parseInt(tempuser.getDiamondLevel())>0){
											//钻级小编结算工资
											WagesSettlement(tempuser,PayRecordService,ReadPayRecordService,ReadNoticesService,req,response,WeChatInfoServices,ReadStudentsService);
										}
										//发送通知短信
										SmBaseUtil.SendNoticesPublisMessage(tempuser.getPhone(),req.getServletContext());
										
									}
								}
							};
							t.start();
						}
					}else{
						message="保存草稿成功";
					}
					Map<String , Object> map=new HashMap<>();
					resultMap=new HashMap<>();
					map.put("NoticeID",ProdID );
					map.put("ContentID", resultContentID);
					resultMap.put("Status", "true");
					resultMap.put("Message", message);
					resultMap.put("data", map);
					return resultMap;
				} else {
					Notices.setID(0);
					ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
				}
			}catch (UncategorizedSQLException Uncategorized) {
				ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
			} catch (Exception e) {
				ErrorUtil.HandleError(null, "wtb.core.controller.addphoneNotices", e);
				ErrorMessage = "服务器繁忙,请稍后再试";
			}

		}
		resultMap.put("Status", "false");
		if (ErrorMessage == null || ErrorMessage.isEmpty()) {
			ErrorMessage = "保存失败,请稍后再试";
		}
		resultMap.put("Message", ErrorMessage);
		
		return resultMap;
	}
	
	
	
	/**
	 * 
	 * @Author 作者：马健
	 * @Phone  联系qq：1039510196
	 * @CreateTime 创建时间：2017年5月15日 下午5:33:36
	 * @Details title: 标题  content ：内容 contentID 内容ID  type: 类型       1 发布       0 草稿    2 编辑
	 */
//	@RequestMapping(value = "/addphoneNewNotices", method = {RequestMethod.POST,RequestMethod.GET})
//	public @ResponseBody
//	Map<String, Object> addphoneNewNotices(final HttpServletResponse response, HttpSession session, final HttpServletRequest req, String title, String content,
//			String contentID,int type,String imageIDList) throws ParseException {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		String ErrorMessage = "";
//		Students user = (Students) session.getAttribute("StudentName");
//		
//		Integer proResult = 0;
//		int Effective=0;
//		Notices Notices = null ;
//		String noticeID=req.getParameter("NoticeID");
//		long ProdID = SmBaseUtil.CreateNewID();
//		if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
//			ProdID=Long.parseLong(noticeID);
//			Notices=ReadNoticesService.getNoticesHtmlByID(Long.parseLong(noticeID));
//		}else{
//			Notices=new Notices();
//		}
//		if (user == null) {
//			/**为了做统一的请求， 新闻发稿从这边过*/
//			String studentID=req.getParameter("studentID");
//			if (studentID!=null&&!studentID.isEmpty()&&!studentID.equals("")&&studentID!="") {
//				Map<String, Object> map=new HashMap<>();
//				map.put("ID", studentID);
//				List<Students> students=ReadStudentsService.getStudentsList(map);
//				if (students.size()>0) {
//					user=students.get(0);
//				}else{
//					ErrorMessage = "长时间未操作，请刷新再试";
//				}
//			}else{
//				ErrorMessage = "长时间未操作，请刷新再试";
//			}
//		}
//		if (title == null || title.isEmpty()||title.equals("null")) {
//			ErrorMessage = "标题不能为空";
//		}
//		if (ErrorMessage != null && !ErrorMessage.isEmpty()) {
//			resultMap.put("Status", "false");
//			resultMap.put("Message", ErrorMessage);
//			return resultMap;
//		}
//		Notices.setAuthor(user.getName());
//		Notices.setTitle(title);
//		String pictures = "";
//		if (ErrorMessage.isEmpty()) {
//
//			Notices.setPublishUser(String.valueOf(user.getID()));
//			Notices.setID(ProdID);
//			
//			if (type==0) {//草稿
//				Notices.setStatus(SmBaseGlobal.CheckStatus.Draft.getid());
//			}else if(type==1){//发布
//				Notices.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
//				Effective=1;
//			}else if(type==2){//编辑
//				Notices.setStatus(SmBaseGlobal.CheckStatus.Edit.getid());
//			}
//			
//			
//			Notices.setAreaID(Long.parseLong((user.getAreaID() == null || user.getAreaID().isEmpty()) ? "0" : user.getAreaID()));
//			Notices.setType(SmBaseGlobal.NewType.News.getid());
//			Notices.setContentType(SmBaseGlobal.NoticesContentType.Html.getid());//设置HTML方式
//			
//			
//			//Notices.setContent(EmojiUtil.resolveToEmojiFromByte(Notices.getContent()));
//			// 更新内容信息
//			
////				String[] contents = content.split("@::@");
////				String[] contentIDs=null;
////				if (contentID!=null&&!contentID.equals("null")) {
////					contentIDs=contentID.split(",");
////				}
////				String resultContentID="";
////				NoticesContentService.DeleteAllContentBySrcID(ProdID);//删除所有的
////				for (int i = 0; i < contents.length; i++) {
////					NoticesContent noticesContent=new NoticesContent();
////					long id=0;
////					noticesContent.setNum(i);
////					noticesContent.setSrcID(ProdID);
////					noticesContent.setStatus(1);
////					noticesContent.setContent(EmojiUtil.resolveToEmojiFromByte(contents[i]));
////					if (contentIDs!=null&&i<contentIDs.length&&!contentIDs.equals("null")) {//大于0说明存在的
////						if (contentIDs[i]!=null&&contentIDs[i]!="") {
////							id=Long.parseLong(contentIDs[i]);
////							noticesContent.setID(id);
////							NoticesContentService.UpdateContent(noticesContent);
////						}
////					}else{
////						id=SmBaseUtil.CreateNewID();
////						noticesContent.setID(id);
////						NoticesContentService.AddContent(noticesContent);
////					}
////					
////					if (i==contents.length-1) {
////						resultContentID=resultContentID+id;
////					}else{
////						resultContentID=resultContentID+id+",";
////					}
////				}
////				Notices.setContent(EmojiUtil.resolveToEmojiFromByte(content.replace("@::@", "<p>")));//换行
//				
//				
////				if (imageIDList != null && imageIDList.length() > 0&&imageIDList!=null) {
////					if (imageIDList.substring(0, 1).equals(",")) {
////						imageIDList = imageIDList.substring(1);
////					}
////					String[] imgIDs = imageIDList.split(",");
////					for (String imgID : imgIDs) {
////						resultMap = new HashMap<String, Object>();
////						resultMap.put("ID", imgID);
////						List<ProdPictures> lspic = ReadProdPicturesService.getPictureList(resultMap);
////						if (lspic.size() > 0) {
////							if (Notices.getImageID() == null || Notices.getImageID() <= 0) {
////								Notices.setImageID(lspic.get(0).getID());
////							}
////							lspic.get(0).setWeChatID(user.getID());
////							lspic.get(0).setProductID(Notices.getID());
////							lspic.get(0).setType(1);
////							ProdPicturesService.UpdateSimplePicture(lspic.get(0));
////							pictures += ",\"" + lspic.get(0).getUrl().split(",")[0] + "\"";
////						}
////
////					}
////				}
//				
//				
//				
//			try{
//				
//				if (noticeID!=null&&noticeID!=""&&!noticeID.equals("null")) {
//					proResult = NoticesService.updateNotices(Notices);
//				} else {
//					if (Notices.getStatus()==SmBaseGlobal.CheckStatus.Effective.getid()) {
//						Notices = CheckAccordWithAutoRewardWeMoney(Notices,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,VoteService);
//					}
//					proResult = NoticesService.addNotices(Notices);
//				}
//				if (proResult > 0) {
//					final Students tempuser = user;
//					final Notices tempnotices = Notices;
//					final String pics = pictures;
//					String message="发布成功";
//					if (Effective==1) {
//						Thread t = new Thread() {
//							public void run() {
//								// 添加同步日志,暂停同步
//								//addStudentLog(tempuser, tempnotices, pics);
//								// 检测重复的稿件
//								boolean isrepeat= checkRepeatNotices(tempuser, tempnotices,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,req,VoteService);
//								if(!isrepeat){//如果通过审核
//									// 进行排名重排
//									phoneSortNotices(String.valueOf(tempnotices.getAreaID()));
//									checkIsActicity(tempnotices,VoteService);
//									if(Integer.parseInt(tempuser.getDiamondLevel())>0){
//										//钻级小编结算工资
//										WagesSettlement(tempuser,PayRecordService,ReadPayRecordService,ReadNoticesService,req,response,WeChatInfoServices,ReadStudentsService);
//									}
//									//发送通知短信
//									SmBaseUtil.SendNoticesPublisMessage(tempuser.getPhone(),req.getServletContext());
//									
//								}
//							}
//						};
//						t.start();
//					}else{
//						message="保存成功";
//					}
//					Map<String , Object> map=new HashMap<>();
//					map.put("NoticeID",ProdID );
//					resultMap.put("Status", "true");
//					resultMap.put("Message", message);
//					resultMap.put("data", map);
//					return resultMap;
//				} else {
//					Notices.setID(0);
//					ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
//				}
//			}catch (UncategorizedSQLException Uncategorized) {
//				ErrorMessage = "保存失败,请检测是否包含表情字符目前暂不支持表情字符";
//			} catch (Exception e) {
//				ErrorUtil.HandleError(null, "wtb.core.controller.addphoneNotices", e);
//				ErrorMessage = "服务器繁忙,请稍后再试";
//			}
//
//		}
//		resultMap.put("Status", "false");
//		if (ErrorMessage == null || ErrorMessage.isEmpty()) {
//			ErrorMessage = "保存失败,请稍后再试";
//		}
//		resultMap.put("Message", ErrorMessage);
//		
//		return resultMap;
//	}
	
	
	
	
	//钻级小编结算工资
	public void WagesSettlement(Students stu,wtb.core.service.PayRecordService PayRecordService,read.core.service.ReadPayRecordService ReadPayRecordService,
			read.core.service.ReadNoticesService ReadNoticesService,HttpServletRequest request,HttpServletResponse response,wtb.core.service.WeChatInfoServices WeChatInfoServices ,read.core.service.ReadStudentsService ReadStudentsService){
		Map<String ,Object > result=new HashMap<>();
		Calendar value=Calendar.getInstance();
		value.setTime(new Date());
		result.put("Year",value.get(Calendar.YEAR ) );
		result.put("Month", value.get(Calendar.MONTH)+1);
		result.put("UsersID", stu.getID());
		result.put("Sina", SmBaseUtil.Random());
		int noticesCount=ReadNoticesService.getNoticesCount(result);
		result=new HashMap<>();
		result.put("Year",value.get(Calendar.YEAR ) );
		result.put("Month", value.get(Calendar.MONTH)+1);
		result.put("UserID", stu.getID());
		result.put("Sina", SmBaseUtil.Random());
		int retult=ReadPayRecordService.getPayRecordCount(result);
		if(retult<=0){
			int money=0;
			if(stu.getDiamondLevel().equals("1") && noticesCount>=30){
				money=3;
			}else if(stu.getDiamondLevel().equals("2") && noticesCount>=40){
				money=5;
			}else if(stu.getDiamondLevel().equals("3") && noticesCount>=50){
				money=10;
			}else if(stu.getDiamondLevel().equals("4") && noticesCount>=70){
				money=50;
			}else if(stu.getDiamondLevel().equals("5") && noticesCount>=100){
				money=100;
			}
			if(money>0){
				WeMoneyController buid=new WeMoneyController();
				result = buid.withdrawMethod(String.valueOf(stu.getID()),(float)money,request,response,WeChatInfoServices,ReadStudentsService,null);
				try{
					PayRecord payRecord=new PayRecord();
					payRecord.setID(SmBaseUtil.CreateNewID());
					payRecord.setOrderID(String.valueOf(SmBaseUtil.CreateNewID()));
					payRecord.setPayMethod("企业付款");
					payRecord.setPayReason("钻级小编工资发放");
					payRecord.setType(1);
					payRecord.setUserID(String.valueOf(stu.getID()));
					payRecord.setVersion("1");
					payRecord.setBeLongID(String.valueOf(stu.getID()));
					payRecord.setMoney(String.valueOf(money));
					payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					PayRecordService.addPayRecord(payRecord);
				}catch (Exception e) {
					e.printStackTrace();
					ErrorUtil.HandleError(null, "WeMoneyController.phoneshareAddIntegral", e);
				}
				
			}
		}
		
	}
	@RequestMapping(value = "/saveCookie", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> saveCookie(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Students user = (Students) session.getAttribute("StudentName");
		if(user==null){
			user=new Students();
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content"); 
		String imageIDList = request.getParameter("imageIDList");
		String imageUrl = request.getParameter("imageUrl");
		String imgLiId = request.getParameter("imgLiId");
		String imgLiSrc = request.getParameter("imgLiSrc");
		String noticeID=request.getParameter("noticeID");
		Map<String ,Object > result=new HashMap<>();
		if((title==null || title.isEmpty()) && (content==null || content.isEmpty()) && (imageIDList==null || imageIDList.isEmpty())){
			result.put("Status", false);
			result.put("Message", "保存失败");
			return result;
		}
		NoticesTemp noticesTemp = new NoticesTemp();
		noticesTemp.setTitle(title);
		noticesTemp.setContent(content);
		noticesTemp.setImageIDList(imageIDList);
	//	noticesTemp.setImageUrl(imageUrl);
		noticesTemp.setImgLiId(imgLiId);
	//	noticesTemp.setImgLiSrc(imgLiSrc);
		noticesTemp.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		try {
			if(noticeID!=null&&noticeID!=""&&noticeID!=null){
				noticesTemp.setNoticeSrcID(Long.parseLong(noticeID));
				Map<String ,Object > map=new HashMap<>();
				map.put("ID", noticeID);
				//map.put("Status", SmBaseGlobal.CheckStatus.Edit.getid());
				List<Notices> notices=ReadNoticesService.getReadNoticesList(map);
				if (notices.size()>0) {
					if(notices.get(0).getStatus()==SmBaseGlobal.CheckStatus.Effective.getid()){
						notices.get(0).setStatus(SmBaseGlobal.CheckStatus.Edit.getid());
						NoticesService.updateNotices(notices.get(0));
					}
				}
			}
			session.setAttribute("NoticesTemp", noticesTemp);
			long userid=(user==null)?0:user.getID();
			if(userid!=0){
				noticesTemp.setID(userid);
				if(NoticesTempService!=null){
					Map<String , Object> map=new HashMap<>();
					map.put("ID", userid);
					List<NoticesTemp> noticesTemps=NoticesTempService.getNoticesList(map);
					if (noticesTemps!=null && noticesTemps.size()>0) {
						NoticesTempService.updateNotices(noticesTemp);
					}else{
						NoticesTempService.addNotices(noticesTemp);
					}
				}
				
				result.put("Status", true);
				result.put("Message", "保存成功");
			}else{
				result.put("Status", false);
				result.put("Message", "保存失败,请刷新页面重试");
			}
		} catch (UncategorizedSQLException e) {
			result.put("Status", false);
			result.put("Message", "保存失败,请检测是否包含表情字符目前暂不支持表情字符");
		}
		return result;
	}

	@RequestMapping(value = "/urlNotices", method = RequestMethod.GET)
	public @ResponseBody
	void urlNotices(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ID", id);
		List<StudentsLog> mList = ReadStudentsLogService.getStudentsLogList(map);
		if (mList.size() > 0) {
			JSONObject js = SmBaseUtil.SendGetRequestURL(mList.get(0).getURL());
			if (js.getString("errmsg").isEmpty()) {
				mList.get(0).setRequest("同步成功");
			} else {
				mList.get(0).setRequest(js.getString("errmsg"));
			}

			StudentsLogService.updateStudentsLog(mList.get(0));

		}

	}
	
	/**
	 * AddLevel  app 调用使用
	 * @param request
	 * @param response
	 * @param session
	 * 
	 * 3个参数 studentID title noticeID
	 * 
	 *noticeID=852730024721911808&num=1&title=成功哈哈  例子
	 */
	@RequestMapping(value = "/AddLevel", method = {RequestMethod.GET,RequestMethod.POST} )
	public @ResponseBody void AddLevel (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String title=request.getParameter("title");
		String noticeID=request.getParameter("noticeID");
		String num=request.getParameter("num");
		if(noticeID!=null){
			Notices notices=ReadNoticesService.getNoticesByID(Long.parseLong(noticeID));
			if (title!=null&&notices!=null&&num!=null) {
				addLevel(Long.parseLong(notices.getPublishUser()), title, notices, Integer.parseInt(num), IntegrationService, StudentsService, WeMoneyService, NoticesService, WeMoneyRecordService, ReadNoticesService, ReadStudentsService, ReadWeMoneyService,ReadVoteService);
			}
		}
	}
	
	/**
	 * 排序
	 * 
	 * @param request
	 * @param response
	 * @param session
	 */

	public void phoneSortNotices(String AreaId) {
		/**
		 * 方法�? 1.先重置该区域所上推的区,市省的稿�?
		 */

		Map<String, Object> map = new HashMap<>();
		map.put("school", 1);
		map.put("AreaID", AreaId);
		NoticesService.updateNoticeSortByAreaId(map);// 更新该校�?notice_region
														// iscity ispro�?

		map = new HashMap<>();
		map.put("school", 2);
		map.put("AreaID", AreaId);
		NoticesService.updateNoticeSortByAreaId(map);// 更新该校上传的该区域的排�?

		map = new HashMap<>();
		map.put("school", 2);
		map.put("AreaID", AreaId);
		NoticesService.updateNoticeSortByAreaId(map);// 更新该校上传的该区域的排�?

		map = new HashMap<>();
		map.put("school", 3);
		map.put("AreaID", AreaId);
		NoticesService.updateNoticeSortByAreaId(map);// 更新该校上传的该市级的排�?

		map = new HashMap<>();
		map.put("school", 4);
		map.put("AreaID", AreaId);
		NoticesService.updateNoticeSortByAreaId(map);// 更新该校上传的该省级的排�?

	}

	@RequestMapping(value = "/phonenoticeslist", method = RequestMethod.GET)
	public ModelAndView phoneactivitylist(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.putAll(makeQueryParam(request, session));
		if (responseMap.get("AreaID") != null) {
			model.addAttribute("AreaID", responseMap.get("AreaID"));
		}
		List<Notices> lswe = ReadNoticesService.getReadNoticesList(responseMap);
		model.addAttribute("data", lswe);
		return new ModelAndView(SmBaseGlobal.MobileViewPath + "phonenoticeslist");
	}

	/**
	 * 添加积分记录
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/AddBallotIntegration", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> AddBallotIntegration(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String XBUserID = request.getParameter("uid");
		String voter = request.getParameter("voter");
		String newsid = request.getParameter("newsid");
		if (voter == null) {
			voter = "";
		}
		if (newsid == null) {
			newsid = "";
		}
		if (XBUserID != null && !XBUserID.isEmpty()) {
			int result = 0;
			// 更新积分
			Notices notices=new Notices();
			try {
				notices=ReadNoticesService.getNoticesByID(Long.parseLong(newsid));
				String title = "您的新闻" + notices.getTitle() + "】被投票";
				
				
				addLevel(Long.parseLong(XBUserID), title, notices, 1, IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
				
				result = 1;
			} catch (Exception e) {
				Integration integration = new Integration();
				integration.setID(SmBaseUtil.CreateNewID());
				integration.setCreateTime(sdf.format(new Date()));
				integration.setStatus(1);
				integration.setReason(voter + "为" + newsid + "新闻投票");
				integration.setNum(1);
				integration.setStudentID(Long.parseLong(XBUserID));
				integration.setSrcID(Long.parseLong(newsid));
				integration.setType(1);
				result = IntegrationService.addIntegration(integration);
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}

			responseMap.put("Status", result);
			responseMap.put("Message", "success");
		} else {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", -1);
			responseMap.put("Message", "uid cannot be empty");
		}
		return responseMap;
	}

	/**
	 * 添加积分记录(增加分享)
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/shareAddIntegral", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> shareAddIntegral(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String sid = request.getParameter("sid");
		String newsid = request.getParameter("newsid");
		String title = "您的新闻【新闻ID" + newsid + "】被分享";
		Notices notices=new Notices();
		if ((sid == null || sid.isEmpty()) && newsid != null && !newsid.isEmpty()) {
			notices = ReadNoticesService.getNoticesByID(Long.parseLong(newsid));
			if (notices != null) {
				title="您的新闻" + notices.getTitle() + "】被分享";
				sid = String.valueOf(notices.getPublishUser());
			} else {
				sid = "0";
			}
		}
		if (sid != null && !sid.isEmpty() && newsid != null && !newsid.isEmpty()) {
			NoticesService.UpShareCount(Long.parseLong(newsid));
			addLevel(Long.parseLong(sid), title, notices, 2, IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
		}

		return responseMap;
	}

	/**
	 * 添加积分记录
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/likeNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> likeNotices(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String nid = request.getParameter("nid");
		String type = request.getParameter("type");// 默认0 0表示喜欢 1表示取消
		if (type == null || type.isEmpty()) {
			type = "0";
		}
		if (nid == null || nid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", -1);
			responseMap.put("Message", "uid不能为空");
		} else {
			Students user = (Students) request.getSession().getAttribute("StudentName");
			responseMap = new HashMap<String, Object>();
			responseMap.put("NoticesID", nid);
			if (user != null) {
				responseMap.put("UserID", user.getID());
			} else {
				responseMap.put("UserID", SmBaseUtil.getIpAddr(request));
			}
			List<LikeRecord> likecount = LikeRecordService.getLikeRecordList(responseMap);
			if (likecount.size() <= 0) {
				try{
					NoticesService.UpDateLikeCount(Long.parseLong(nid));
				}catch (Exception e) {
					NoticesService.UpDateLikeCount(Long.parseLong(nid));
				}
				LikeRecord likeRecord = new LikeRecord();
				likeRecord.setID(SmBaseUtil.CreateNewID());
				likeRecord.setCreateTime(new Date());
				likeRecord.setModifyTime(new Date());
				likeRecord.setNoticesID(Long.parseLong(nid));
				likeRecord.setStatus(1);
				if (user != null) {
					likeRecord.setUserID(String.valueOf(user.getID()));
				} else {
					user=new Students();
					user.setID(0);
					likeRecord.setUserID(SmBaseUtil.getIpAddr(request));
				}
				LikeRecordService.addLikeRecord(likeRecord);
				Notices notices = ReadNoticesService.getNoticesByID(Long.parseLong(nid));
				if(CheckIsOutAutoRewardWeMoney(WeMoneyRecordService,NoticesService,notices,ReadNoticesService)){
					SmBaseUtil.AddAutoRewardWeMoneyForRule(WeMoneyClassify.Like, WeMoneyRecordService, WeMoneyService, ReadWeMoneyService, ReadVoteService, ReadCommentService, notices, NoticesService, user, ReadWeMoneyRecordService);
				}
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status", "true");
				responseMap.put("Message", "点赞成功");
			} else if (likecount.size() > 0) {
				try{
					NoticesService.CancelLikeCount(Long.parseLong(nid));
					LikeRecordService.deleteLikeRecord(likecount.get(0).getID());
				}catch (Exception e) {
					NoticesService.CancelLikeCount(Long.parseLong(nid));
					LikeRecordService.deleteLikeRecord(likecount.get(0).getID());
				}
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status", "true");
				responseMap.put("Message", "取消点赞");
			}

			
		}
		return responseMap;
	}
	
	/*
	 * 添加自动打赏
	 */
	public void  addAutoRewardWeMoney(Notices nid,long uid,long wemoneyNum ,wtb.core.service.WeMoneyRecordService WeMoneyRecordService,
			WeMoneyService WeMoneyService,read.core.service.ReadWeMoneyService ReadWeMoneyService,read.core.service.ReadVoteService ReadVoteService,
			WeMoneyClassify classify,int rewardlevel) {
		/*long temp=wemoneyNum;
			try{
				
				if(nid.getVoteID()>0 && nid.getIsActivity()==1){//如果在活动期间，活动新闻稿奖励3倍
					Map<String, Object> map=new HashMap<>();
					map.put("ID", nid.getVoteID());
					map.put(Vote.attributeVoteStatus, SmBaseGlobal.CheckStatus.Effective.getid());
					List<Vote> votes=ReadVoteService.getVoteList(map);
					if (votes.size()>0) {
						boolean isAct=SmBaseUtil.compareDateForNow(votes.get(0).getStartDate(), votes.get(0).getEndDate());
						if (isAct) {
							int rewardTimes=1;
							if(votes.get(0).getRewardTimes()>0){
								rewardTimes = votes.get(0).getRewardTimes();
							}
							wemoneyNum=wemoneyNum*rewardTimes;
						}
					}
				}else{
					if(SmBaseUtil.isWeedEnd()){
						wemoneyNum=wemoneyNum*2;//周末微米自动奖励翻倍
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				ErrorUtil.HandleError(null, "wtb.core.control.NoticesContral.addAutoRewardWeMoney", e);
				wemoneyNum=temp;
			}*/
			if(uid!=0){
				AddWeMoneyRecord(WeMoneyService, WeMoneyRecordService, ReadWeMoneyService, uid, wemoneyNum, nid, WeMoneyType.AutoReward, "系统奖励",classify,rewardlevel);
			}
			
			
		
	}
	/**
	 * 对WeMoney操作的
	 * @param uid
	 * @param WeMoneyService
	 * @param ReadWeMoneyService
	 * @return
	 */
	public List<WeMoney> getDefaultWeMoneyInfo(long uid,WeMoneyService WeMoneyService,read.core.service.ReadWeMoneyService ReadWeMoneyService){
		
		Map<String, Object> map = new HashMap<>();
		map.put("UserID",uid);
		map.put("Sina", SmBaseUtil.Random());
		List<WeMoney> weMoneys = ReadWeMoneyService.getWeMoneyByIDList(map);
		if (weMoneys.size() <= 0) {
			weMoneys = new ArrayList<WeMoney>();
			WeMoney weMoney = new WeMoney();
			weMoney.setUserID(uid);
			weMoney.setWeMoney(SmBaseGlobal.DefaultWeMoney);
			weMoney.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			weMoney.setID(SmBaseUtil.CreateNewID());
			try{
			WeMoneyService.addWeMoney(weMoney);
			}catch (Exception e) {
				WeMoneyService.updateWeMoney(weMoney);
			}
			weMoneys.add(weMoney);
			
		}
		return weMoneys;
	}
	/**
	 * 
	 * @param WeMoneyService
	 * @param WeMoneyRecordService
	 * @param ReadWeMoneyService
	 * @param uid 学生ID
	 * @param wemoneyNum 要增加的微米数量
	 * @param nid  新闻ID
	 * @param weMoneyType 微米类型
	 * @param reson  增加理由
	 */
	public void AddWeMoneyRecord(wtb.core.service.WeMoneyService WeMoneyService,wtb.core.service.WeMoneyRecordService WeMoneyRecordService,
			read.core.service.ReadWeMoneyService ReadWeMoneyService,long uid,long wemoneyNum,Notices nid, WeMoneyType weMoneyType,String reson,WeMoneyClassify classify ,int rewardLevel){
		List<WeMoney> weMoneys =getDefaultWeMoneyInfo(uid,WeMoneyService,ReadWeMoneyService);
		weMoneys.get(0).setWeMoney(weMoneys.get(0).getWeMoney() + wemoneyNum);
		WeMoneyService.updateWeMoney(weMoneys.get(0));
		WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
		weMoneyRecord.setBelong(nid.getID());
		weMoneyRecord.setReson(reson);//"系统奖励"
		weMoneyRecord.setWeMoney((double)wemoneyNum);
		weMoneyRecord.setFromUserID((long) 0);
		weMoneyRecord.setUserID(uid);
		weMoneyRecord.setType(weMoneyType.getid());
		weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		weMoneyRecord.setID(SmBaseUtil.CreateNewID());
		weMoneyRecord.setClassify(classify.getid());
		weMoneyRecord.setRewardLevel(rewardLevel);
		WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
	}
	
	@RequestMapping(value = "/voteNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> voteNotices(HttpServletResponse response, final HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException, SocketException, UnknownHostException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		final String nid = request.getParameter("nid");
		final String type = request.getParameter("type");
		

		if (nid == null || nid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", false);
			responseMap.put("Message", "投票失败，新闻编号不能为空");
		} else {
				final Students user = (Students) request.getSession().getAttribute("StudentName");
				Map<String, Object> check=new HashMap<>();
				check.put("NoticeID", nid);
				if (user != null) {
					check.put("UnionID", user.getUnionID());
				} else {
					check.put("UnionID",  SmBaseUtil.getLocalMac(request));
				}
				check.put("Rand", SmBaseUtil.Random());
				int j=VoteRecordsService.getVoteRecordsCount(check);//判断是否存在，检测是否刷
				if (j==0) {
					Thread t = new Thread() {
						public void run() {
							 synchronized (this) {
								VoteRecords VoteRecord=new VoteRecords();
								if (user != null) {
									VoteRecord.setUnionID(user.getUnionID());
								} else {
									try {
										VoteRecord.setUnionID(SmBaseUtil.getLocalMac(request));
									} catch (SocketException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (UnknownHostException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								VoteRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
								VoteRecord.setID(SmBaseUtil.CreateNewID());
								VoteRecord.setNoticeID(Long.parseLong(nid));
								int i=VoteRecordsService.addVoteRecords(VoteRecord);
								
							
									Map<String, Object> params=new HashMap<>();
									params.put("ID", nid);
									params.put("Rand", SmBaseUtil.Random());
									int count=0;
									try{
										if(type!=null && type.equals("3")){
											count=VideoService.UpVoteCount(Long.parseLong(nid));
										}else{
											count=NoticesService.UpVoteCount(params);
										}
									}catch (Exception e) {
										if(type!=null && type.equals("3")){
											count=VideoService.UpVoteCount(Long.parseLong(nid));
										}else{
											count=NoticesService.UpVoteCount(params);
										}
									}
							}	
								
					}};
					t.start();
					responseMap = new HashMap<String, Object>();
					responseMap.put("status", true);
					responseMap.put("Message", "投票成功");
					
					
				} else {
					responseMap.put("status", false);
					responseMap.put("Message", "您已投票！");
				}	
		}
		return responseMap;
	}
	
	@RequestMapping(value = "/checkIslikeNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> checkIslikeNotices(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String nid = request.getParameter("nid");

		if (nid == null || nid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", -1);
			responseMap.put("Message", "uid不能为空");
		} else {
			Students user = (Students) request.getSession().getAttribute("StudentName");
			responseMap = new HashMap<String, Object>();
			responseMap.put("NoticesID", nid);
			if (user != null) {
				responseMap.put("UserID", user.getID());
			} else {
				responseMap.put("UserID", SmBaseUtil.getIpAddr(request));
			}
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			int likecount = ReadLikeRecordService.getLikeRecordCount(responseMap);
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Data", likecount);
		}
		return responseMap;
	}
	@RequestMapping(value = "/checkVoteNotices", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> checkVoteNotices(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException, SocketException, UnknownHostException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String nid = request.getParameter("nid");

		if (nid == null || nid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", false);
			responseMap.put("Message", "uid不能为空");
		} else {
			Students user = (Students) request.getSession().getAttribute("StudentName");
			responseMap = new HashMap<String, Object>();
			responseMap.put("NoticeID", nid);
			int VoteCount = VoteRecordsService.getVoteRecordsCount(responseMap);
			if (user != null) {
				responseMap.put("UnionID", user.getUnionID());
			} else {
				responseMap.put("UnionID", SmBaseUtil.getLocalMac(request));
			}
			responseMap.put("Rand", SmBaseUtil.Random());
			int VoteRecordsCount = VoteRecordsService.getVoteRecordsCount(responseMap);
			responseMap = new HashMap<String, Object>();
			if (VoteRecordsCount>0) {
				responseMap.put("status", true);
				responseMap.put("Message", "已投票");
			}else{
				responseMap.put("status", false);
				responseMap.put("Message", "未投票");
			}
			responseMap.put("VoteCount",VoteCount);
			
		}
		return responseMap;
	}
	@RequestMapping(value = "/phoneVerfiyNews", produces = "text/html;charset=UTF-8", method = RequestMethod.GET)
	public @ResponseBody
	String phoneVerfiyNews(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		String nid = request.getParameter("nid");
		String type = request.getParameter("type");// 1:表示审核成功,0:表示审核不通过,3表示上首页,
													// 默认通过审核
		if (type == null || type.isEmpty()) {
			type = "1";
		}
		if (nid == null || nid.isEmpty()) {
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "false");
			responseMap.put("Message", "新闻ID不能为空");
			json.putAll(responseMap);
			return json.toString();
		} else {
			Notices notices = ReadNoticesService.getNoticesByID(Long.parseLong(nid));
			if (notices == null) {
				new HashMap<String, Object>();
				responseMap.put("Status", "false");
				responseMap.put("Message", "该新闻不存在");
				json.putAll(responseMap);
				return json.toString();
			}
			if (type.equals("1")) {
				if (notices.getStatus() != CheckStatus.Effective.getid()) {
					notices.setStatus(CheckStatus.Effective.getid());
					new HashMap<String, Object>();
					responseMap.put("ID", notices.getID());
					NoticesService.enabledNotices(responseMap);
				}
			} else if (type.equals("0")) {
				if (notices.getStatus() != CheckStatus.Disabled.getid()) {
					notices.setStatus(CheckStatus.Disabled.getid());
					new HashMap<String, Object>();
					responseMap.put("ID", notices.getID());
					NoticesService.deleteNotices(responseMap);
				}
			}
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Message", "处理成功！");
		}
		json.putAll(responseMap);
		return json.toString();
	}

	/**
	 * @throws ParseException
	 *             大排"
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/BigSortNotices")
	public @ResponseBody
	void BigSortNotices() throws ParseException {

		SimpleDateFormat f = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String nowTime = f.format(new Date());
		System.out.println(nowTime);

		Date date0 = null;
		Date date2 = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		int number1 = 0;

		Map<String, Object> params = new HashMap<String, Object>();
		List<Notices> region = ReadNoticesService.getAreaIDList(params);

		params = new HashMap<String, Object>();
		List<Notices> Time = ReadNoticesService.getNoticesTimeList(params);// 找出一共有多少个时"

		for (int i = 0; i < Time.size(); i++) {
			if (Time.get(i) != null) {

				date0 = df.parse(df.format(new Date()));
				date2 = df2.parse(Time.get(i).getCreateTime());

				number1 = date2.compareTo(date0);
				System.out.println(number1);
				if (number1 != 0) {
					params = new HashMap<String, Object>();
					params.put("Time", Time.get(i).getCreateTime());
					for (int j = 0; j < region.size(); j++) {
						params.put("AreaID", region.get(j).getAreaID());
						params.put("start", 0);
						params.put("limit", 10);
						List<Notices> notices = ReadNoticesService.getReadNoticesList(params);
						if (notices.size() > 0) {
							for (int k = 0; k < notices.size(); k++) {
								Notices temp = notices.get(k);
								temp.setIsSchool(2);
								temp.setRegion(2);
								NoticesService.UpdateForAreaLevel(temp);// 跟新数据"代表过期"
							}
						}
					}
				}
			}

		}
		for (int j = 0; j < region.size(); j++) {
			params = new HashMap<String, Object>();
			params.put("ID", region.get(j).getAreaID());
			params.put("ParentID2", 1);
			List<Region> regions = ReadRegionService.getRegionList(params);// 得到校上一级的地址"
			if (regions.size() > 0) {
				for (int i = 0; i < regions.size(); i++) {
					params = new HashMap<String, Object>();
					params.put("ID", regions.get(i).getParentID());
					List<Region> regions2 = ReadRegionService.getRegionList(params);// 得到区里面所有学校的地址1301

					params = new HashMap<String, Object>();
					List<Region> regions3 = new ArrayList<Region>();

					params.put("ParentID", regions2.get(i).getParentID());
					regions3 = RegionService.getRegionList(params);// 获得"301的市的所有区

					for (int k = 0; k < regions3.size(); k++) {
						params = new HashMap<String, Object>();
						params.put("EndShi", regions3.get(k).getID());
						params.put("start", 0);
						params.put("limit", 10);

						for (int k2 = 0; k2 < Time.size(); k2++) {
							if (Time.get(k2) != null) {
								params.put("Time", Time.get(k2).getCreateTime());
								List<Notices> notices = ReadNoticesService.getReadNoticesList(params);
								for (int l = 0; l < notices.size(); l++) {

									date0 = df.parse(df.format(new Date()));
									date2 = df2.parse(notices.get(l).getCreateTime());

									number1 = date2.compareTo(date0);

									if (number1 != 0) {
										Notices notices2 = notices.get(l);
										notices2.setIsCity(2);
										NoticesService.UpdateForAreaLevel(notices2);
									}

								}
							}

						}

					}
					params = new HashMap<String, Object>();
					List<Region> regions4 = new ArrayList<Region>();

					params.put("ID", regions2.get(i).getParentID());
					regions4 = ReadRegionService.getRegionList(params);

					for (int k = 0; k < regions4.size(); k++) {
						params = new HashMap<String, Object>();
						params.put("ParentID", regions4.get(k).getParentID());
						List<Region> regions5 = ReadRegionService.getRegionList(params);// 所得所有市
						for (int l = 0; l < regions5.size(); l++) {
							params = new HashMap<String, Object>();
							params.put("EndSheng", regions5.get(l).getID());
							params.put("start", 0);
							params.put("limit", 10);
							for (int k2 = 0; k2 < Time.size(); k2++) {
								if (Time.get(k2) != null) {
									params.put("Time", Time.get(k2).getCreateTime());
									List<Notices> notices = ReadNoticesService.getReadNoticesList(params);

									for (int l2 = 0; l2 < notices.size(); l2++) {

										date0 = df.parse(df.format(new Date()));
										date2 = df2.parse(notices.get(l2).getCreateTime());

										number1 = date2.compareTo(date0);
										if (number1 != 0) {
											Notices notices2 = notices.get(l2);
											notices2.setIsPro(2);
											NoticesService.UpdateForAreaLevel(notices2);
										}
									}
								}

							}
						}

					}

				}

			}
		}

	}
/**
 * 社长点评
 * @param request
 * @param response
 * @param session
 * @return
 */
	@RequestMapping(value = "/addCaptainComment", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addCaptainComment(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<>();
		String CaptainComment = request.getParameter("CaptainComment"); 
		String id = request.getParameter("nid"); 
		responseMap.put("ID", id);
		responseMap.put("CaptainComment", SmBaseUtil.URLDecoderString(CaptainComment));
		int i=NoticesService.UpdateCaptainComment(responseMap);
		if (i>0) {
			Map<String, Object> param = new HashMap<>();
			param.put("ID", id);
			List<Notices>lsn=ReadNoticesService.getReadNoticesList(param);
			if(lsn.size()>0 && lsn.get(0).getStudent()!=null){
				param = new HashMap<>();
				param.put("Type", 3);//Type3表示简报短信提醒
				param.put("NewID", lsn.get(0).getID());
				param.put("StudentID",  lsn.get(0).getStudent().getID());
				param.put("Rand", SmBaseUtil.Random());
				int count=ReadStudentsLogService.getStudentsLogCount(param);
				if(count<=0){
					VerifyCode.sharedInstance().sendWeNewPaperBeChosenMessage(lsn.get(0).getStudent().getPhone(), String.valueOf(lsn.get(0).getID()), lsn.get(0).getTitle(), request.getServletContext());
					AddWeMoneyRecord(WeMoneyService, WeMoneyRecordService, ReadWeMoneyService, lsn.get(0).getStudent().getID(), 100l, lsn.get(0), WeMoneyType.AutoReward , "社长点评奖励",WeMoneyClassify.None,0);
					/* 添加短信发送记录 */
					StudentsLog log=new StudentsLog();
					log.setID(SmBaseUtil.CreateNewID());
					log.setNewID(lsn.get(0).getID() );
					log.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					log.setStudentsID(lsn.get(0).getStudent().getID());
					log.setType("3");
					StudentsLogService.addStudentsLog(log);
					/* 设置新闻达人勋章 */
					int isExist=ReadHonorRecordService.getHonorRecordCount(param);
					if(isExist>0){
						responseMap = new HashMap<String, Object>();
						responseMap.put("Status", false);
						return responseMap; 
					}
					Map<String, String> weekday= SmBaseUtil.getWeekDay();
					HonorRecord honorRecord=new HonorRecord();
					honorRecord.setID(SmBaseUtil.CreateNewID());
					honorRecord.setEndTime(weekday.get("endTime"));
					honorRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					honorRecord.setStudentID(lsn.get(0).getStudent().getID());
					honorRecord.setType(HonerType.Excellent.getid());
					int result=HonorRecordService.addHonorRecord(honorRecord);
					if(result>0){
						StudentsService.UpHonorCount(lsn.get(0).getStudent().getID());
					}
				}
			}
			responseMap = new HashMap<>();
			responseMap.put("status", true);
		}else{
			responseMap.put("status", false);
			responseMap.put("Message", "添加失败");
		}
		return responseMap;
	}
	
	
	/**
	 * 组织查询参数
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	private Map<String, Object> makeQueryParam(HttpServletRequest request, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = (Users) session.getAttribute("UserName");
		String Title = request.getParameter("Title");
		String state = request.getParameter("Status");
		String IsTop = request.getParameter("IsTop");
		String type = request.getParameter("type");
		String PID = request.getParameter("pid");
		String AreaID = request.getParameter("AreaID");
		if (AreaID == null || AreaID.isEmpty()) {
			AreaID = String.valueOf(user.getAreaID());
		}

		if (user.getLevel() == SmBaseGlobal.LevelStatus.StudentManage.getid() || user.getLevel() == SmBaseGlobal.LevelStatus.TeacherManage.getid()) {
			responseMap.put("UsersID", user.getID());
		} else if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid() || user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			responseMap.put("AreaID", Long.parseLong(AreaID));
		}

		if (Title != null && !Title.isEmpty()) {
			responseMap.put("Title", Title);
		}
		if (type != null && !type.isEmpty()) {
			responseMap.put("Type", type);

		}
		if (IsTop != null && !IsTop.isEmpty() && IsTop.equals("1")) {
			responseMap.put("IsTop", 1);

		}else if (IsTop != null && !IsTop.isEmpty() && IsTop.equals("0")) {
			responseMap.put("IsTop", 0);

		}
		if (PID != null && !PID.isEmpty()) {
			responseMap.put("ID", PID);
		}
		if (state != null && !state.isEmpty()) {
			responseMap.put("Status", state);
		} else {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		}
		
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		return responseMap;
	}

	/**
	 * 检测活动稿"
	 * @param notices
	 */
	public void checkIsActicity(Notices notices,read.core.service.ReadVoteService ReadVoteService,wtb.core.service.KeyWordService  KeyWordService){
		
		Map<String, Object> maps = new HashMap<>();
		maps.put(KeyWord.attributeKeyWordTypeID, KeywordType.ActivityChar.getCode());
		maps.put("Effective", KeywordType.ActivityChar.getCode());
		maps.put("status", SmBaseGlobal.CheckStatus.Effective.getid());
		maps.put("Sina", SmBaseUtil.Random());
		List<KeyWord> keyWords = KeyWordService.queryKeyWord(maps);
		for (KeyWord item : keyWords) {
			if (notices.getTitle().contains(item.getKeyWord())) {
				notices.setIsActivity(SmBaseGlobal.CheckStatus.Effective.getid());
				notices.setVoteID(item.getVoteID());
				break;
			}
		}
		if(notices.getIsActivity()>0){
			maps = new HashMap<>();
			maps.put(Vote.attributeVoteID, notices.getVoteID());
			maps.put(Vote.attributeVoteStatus, SmBaseGlobal.CheckStatus.Effective.getid());
			maps.put(Vote.attributeAreaID, notices.getAreaID());
			List<Vote>list=ReadVoteService.getVoteList(maps);
			if(list.size()>0){
				NoticesService.updateNoticesActivity(notices);
			}
			
		}
	}
	private boolean checkRepeatNotices(Students student, Notices notic,read.core.service.ReadNoticesService ReadNoticesService,
			read.core.service.ReadStudentsService ReadStudentsService,read.core.service.ReadWeMoneyService ReadWeMoneyService,HttpServletRequest request,wtb.core.service.VoteService VoteService) {
		boolean isrepeat = false;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if(notic.getContent()!=null && notic.getContent().length()>30){//100字一下的新闻都需要通过审核
			responseMap.put("later", 60);// 一个小时之内的重复文章都不通过验证
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			responseMap.put("UsersID", student.getID());
			responseMap.put("Sina", SmBaseUtil.Random());
			List<Notices> lsNoti = ReadNoticesService.getReadNoticesList(responseMap);
			for (Notices notices : lsNoti) {
				if (notices.getID() != notic.getID()) {
					// 相似80%以上的认为是相同的文"
					if (Similarity.SimilarDegree(notices.getContent(), notic.getContent()) >= 0.8) {
						isrepeat = true;
					}
					
				}
			}
		}else{
			isrepeat=true;
		}

		if (isrepeat) {
			Long alsID = SmBaseUtil.CreateNewID();
			ApplyList als = new ApplyList();
//			als.setAreaID(String.valueOf(student.getAreaID()!=null?student.getAreaID():0));
			als.setUserPhone(student.getPhone());
			als.setUserName(student.getName());
			als.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			als.setContent("申请发布新闻");
			als.setCreateTime(sdf.format(new Date()));
			als.setPKID(String.valueOf(alsID));
			als.setID(alsID);
			ApplyListService.addApplyList(als);
			String title = "【" + als.getUserName() + "】申请发布新闻【" + notic.getTitle() + "】！";
			MessageController mc = new MessageController();
			mc.SendMessageToAdminWaitWork(ReadUsersService, MessagesService, als, title, SmBaseGlobal.NewType.News.getid());
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", notic.getID());// 如果为审核不通过则修改为未审"
			NoticesService.ApplyNotices(responseMap);
			return isrepeat;
		} else {
			String title = "发表【" + notic.getTitle() + "】新闻";
			// 更新积分
			addLevel(student.getID(), title, notic, 5, IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
			StudentsService.UpNoticeCount(student.getID());
			
		}
		return false;

	}

	public void addStudentLog(Students student, Notices notices, String pictures) {
		String pics = "&pics=[";
		if (!pictures.isEmpty()) {
			pics += pictures.substring(1);
		}
		pics += "]";

		String weboURL="";
		int isActivity = 0;
		Map<String, Object> map = new HashMap<>();
		map.put(KeyWord.attributeKeyWordTypeID, KeywordType.ActivityChar.getCode());
		List<KeyWord> keyWords = KeyWordService.queryKeyWord(map);
		for (KeyWord item : keyWords) {
			if (notices.getTitle().contains(item.getKeyWord())) {
				isActivity = 1;
				break;
			}
		}
		try {

			weboURL = SmBaseGlobal.XBUrl + "index.php/Admin/Essay/unionCreate?action_type=" + isActivity + "&nid&=" + notices.getID() + "&uid=" + student.getID()
					+ "&text=" + URLEncoder.encode(notices.getContent(), "UTF-8") + pics;
		} catch (UnsupportedEncodingException e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		System.err.println(weboURL);
		Long SLGID = SmBaseUtil.CreateNewID();
		final StudentsLog studentsLog = new StudentsLog();
		studentsLog.setID(SLGID);
		studentsLog.setCreateTime(sdf.format(new Date()));
		studentsLog.setNewID(notices.getID());
		studentsLog.setStudentsID(student.getID());
		studentsLog.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		studentsLog.setURL(weboURL);
		studentsLog.setType("1");
		String message="";
		try{
			JSONObject js = SmBaseUtil.SendGetRequestURL(weboURL);
			if (js==null || (js!=null && !js.containsKey("errmsg")) ||  (js!=null && js.containsKey("errmsg") &&js.getString("errmsg").isEmpty())) {
				message="同步成功";
			} else {
				message=js.getString("errmsg");
			}
		}catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			message="同步失败";
		}
		studentsLog.setRequest(message);
		StudentsLogService.addStudentsLog(studentsLog);
	}

	/**
	 * 更新积分 更新级别
	 * @param type
	 * 
	 * @param Sid
	 *            用户ID
	 * @param title
	 *            积分添加说明
	 * @param noticeID
	 *            新闻ID
	 * @param num
	 *            添加的积分数
	 * @param IntegrationService
	 *            积分的服务接"
	 * @param StudentsService
	 *            学生的服务接"
	 */
	public void addLevel(Long Sid, String title, Notices noticeID, int num, IntegrationService IntegrationService, StudentsService StudentsService,
			WeMoneyService WeMoneyService,wtb.core.service.NoticesService NoticesService,wtb.core.service.WeMoneyRecordService WeMoneyRecordService
			,read.core.service.ReadNoticesService ReadNoticesService,read.core.service.ReadStudentsService ReadStudentsService,
			read.core.service.ReadWeMoneyService ReadWeMoneyService,read.core.service.ReadVoteService ReadVoteService) {

		List<WeMoney> weMoneys = getDefaultWeMoneyInfo(Sid,WeMoneyService,ReadWeMoneyService);
		// 增加100积分兑换一个微米这里的剩余积分和实际积分分"这里只是计算增加的积分不算减少积"
		weMoneys.get(0).setResidualIntegral(weMoneys.get(0).getResidualIntegral() + num);
		if (weMoneys.get(0).getResidualIntegral() >= 100) {
			weMoneys.get(0).setResidualIntegral(weMoneys.get(0).getResidualIntegral() - 100);
			int wemoneyNum=1;
			weMoneys.get(0).setWeMoney(weMoneys.get(0).getWeMoney() + wemoneyNum);
			WeMoneyService.updateWeMoney(weMoneys.get(0));
			addAutoRewardWeMoney(noticeID,weMoneys.get(0).getUserID(),wemoneyNum,WeMoneyRecordService,WeMoneyService,ReadWeMoneyService,ReadVoteService,WeMoneyClassify.None,0);
		}
		
		Long InID = SmBaseUtil.CreateNewID();
		Integration integration = new Integration();
		integration.setID(InID);
		integration.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		integration.setReason(title);
		integration.setNum(num);
		integration.setStudentID(Sid);
		integration.setSrcID(noticeID.getID());
		integration.setType(IntegrationType.Added.getid());
		IntegrationService.addIntegration(integration);
		//

		updateIntegration(Sid, StudentsService, num,ReadStudentsService);
	}
	
	
	/**
	 * 检测是否超出系统自动打赏的额度
	 * @param weMoney
	 * @param noticeID
	 * @return
	 */
	public  Boolean CheckIsOutAutoRewardWeMoney(wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.NoticesService NoticesService,Notices noticeID,read.core.service.ReadNoticesService ReadNoticesService){
		Map<String, Object> map = new HashMap<>();
	
		if(noticeID!=null){//参与活动的文章才能自动打"
			map.put("ID", noticeID.getID());
			map.put("Sina", SmBaseUtil.Random());
			List<Notices> list  = ReadNoticesService.getReadNoticesList(map);
			if(list.size()>0){
				noticeID=list.get(0);
			}
			if(noticeID.getClickCount()>=100|| noticeID.getLike()>=50 || noticeID.getCommentCount()>=10){
				map = new HashMap<>();
				map.put("Type", SmBaseGlobal.WeMoneyType.AutoReward.getid());
				map.put("Belong", noticeID.getID());
				long count=WeMoneyRecordService.getWeMoneyRecordSum(map);
				if(count<=500){//打赏超过100微米即停止自动打"
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 检测是否符合自动打赏的规则
	 * @param weMoney
	 * @param noticeID
	 * @return
	 * @throws ParseException 
	 */
	public  Notices CheckAccordWithAutoRewardWeMoney(Notices notices,read.core.service.ReadWeMoneyService ReadWeMoneyService,WeMoneyService WeMoneyService,wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.VoteService VoteService) throws ParseException{
		String today=sdfShort.format(new Date());
		Map<String, Object> map = new HashMap<>();
		map.put("UsersID", notices.getPublishUser());
		map.put("CreateTime", today);
		map.put("Today", today);
		int noticesCount=ReadNoticesService.getNoticesCount(map);
		if(noticesCount<2){//每天首稿奖励5微米
			map = new HashMap<>();
			map.put("UserID", notices.getPublishUser());
			int wemoneyNum=5;
			addAutoRewardWeMoney(notices,Long.parseLong(notices.getPublishUser()), wemoneyNum, WeMoneyRecordService, WeMoneyService, ReadWeMoneyService,ReadVoteService,WeMoneyClassify.None,0);
		}
		return notices;
	}
	/***
	 * 只对学生有关系
	 * @param Sid
	 * @param StudentsService
	 * @param num
	 * @param ReadStudentsService
	 */
	public void updateIntegration(long Sid, StudentsService StudentsService, int num,read.core.service.ReadStudentsService ReadStudentsService) {

		Map<String, Object> map = new HashMap<>();
		map.put("ID", Sid);
		List<Students> students = ReadStudentsService.getStudentsList(map);
		if (students.size() > 0) {
			Students students2 = students.get(0);
			students2.setIntegration(students2.getIntegration() + num);
			if (students2.getIntegration() >= 0 && students2.getIntegration() <= 100) {
				students2.setLevel("1");
			}
			if (students2.getIntegration() >= 101 && students2.getIntegration() <= 500) {
				students2.setLevel("2");
			}
			if (students2.getIntegration() >= 501 && students2.getIntegration() <= 2000) {
				students2.setLevel("3");
			}
			if (students2.getIntegration() >= 2001 && students2.getIntegration() <= 5000) {
				students2.setLevel("4");
			}
			if (students2.getIntegration() >= 5001) {
				students2.setLevel("5");
			}
			//星级达到3星以及以上则自动晋升为正式社"
			if(Integer.parseInt(students2.getLevel())>=3 && students2.getOfficial()==0){
				students2.setOfficial(1);
			}
			StudentsService.updateLevelStudents(students2);
		}
	}

	/**
	 * 保存前台传过来的图片,多图"
	 * 
	 * @param imageIDs
	 * @param DelImages
	 * @param notices
	 * @return
	 */
	private Notices SavePicture(String imageIDs, String DelImages, Notices notices) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String[] imgIDs = imageIDs.split(",");
		int CurrentLen = 0;
		for (String imgID : imgIDs) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("ID", imgID);
			List<ProdPictures> lspic = ReadProdPicturesService.getPictureList(resultMap);
			if (lspic.size() > 0) {
				if (notices.getImageID() == null || notices.getImageID() <= 0) {
					notices.setImageID(lspic.get(0).getID());
				}

				lspic.get(0).setProductID(notices.getID());
				lspic.get(0).setType(1);
				boolean isNew = false;
				for (int index = 0; index < CurrentLen; index++) {
					if (imgIDs[index].equals(imgIDs[index])) {
						isNew = true;
						lspic.get(0).setID(new IdWorker(1, 0).nextId());
						break;
					}
				}
				if (isNew) {
					ProdPicturesService.addPictures(lspic.get(0));
				} else {
					ProdPicturesService.updatePictures(lspic.get(0));
				}
			}
			CurrentLen++;

		}
		/**
		 * 删除关联的图"
		 */
		if (DelImages != null && DelImages.length() > 0) {
			DelImages = DelImages.substring(1);
			imgIDs = DelImages.split(",");

			for (String imgID : imgIDs) {
				resultMap = new HashMap<String, Object>();
				resultMap.put("ID", imgID);
				List<ProdPictures> lspic = ReadProdPicturesService.getPictureList(resultMap);
				if (lspic.size() > 0) {
					lspic.get(0).setProductID(0);
					ProdPicturesService.updatePictures(lspic.get(0));
				}

			}

		}
		return notices;
	}
	

	
	
}