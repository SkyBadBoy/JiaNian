package wtb.core.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.alipay.api.internal.util.codec.Encoder;
import com.baidu.yun.core.json.ParseException;

import net.sf.json.JSONObject;
import wtb.core.model.Activity;
import wtb.core.model.Advert;
import wtb.core.model.ApplyList;
import wtb.core.model.Comment;
import wtb.core.model.Integration;
import wtb.core.model.Notices;
import wtb.core.model.PictureParam;
import wtb.core.model.ProdPictures;
import wtb.core.model.Product;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.Video;
import wtb.core.model.Vote;
import wtb.core.model.WeChatInfo;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.NetUtil;
import wtb.smUtil.OperateImage;
import wtb.smUtil.SessionUtils;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Product")
public class ProductController extends BaseController {

	SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("ProductForm") Product product, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		Users user = null;

		String PID = req.getParameter("pid");
		String WeChatID = req.getParameter("WeChatID");
		if (WeChatID != null) {
			model.addAttribute("WeChatID", WeChatID);
		}
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Product> lsps = ProductService.getProductList(responseMap);
			if (lsps.size() > 0) {
				product = lsps.get(0);
				ProdPictures lsimg = product.getImage();
				if (lsimg != null) {
					model.addAttribute("Image", product.getImage().getUrl());
				} else {
					model.addAttribute("Image", "#");
				}
				model.addAttribute("WeChat", product.getWeChat().getCompany());
				model.addAttribute("WeChatID", product.getWeChat().getID());
				model.addAttribute("Content", product.getContent());
				model.addAttribute("ImageID", product.getImageID());
				Status = 1;
			} else {
				Status = 404;
			}
		}

		model.addAttribute("ProductForm", product);
		model.addAttribute("Status", Status);
		user = (Users) session.getAttribute("UserName");

		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid() && product.getID() > 0) {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "viewProduct");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addProduct");
		}
	}

	@RequestMapping(value = "/phoneaddgood", method = RequestMethod.GET)
	public ModelAndView phoneaddgood(@ModelAttribute("ProductForm") Product product, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		String PID = req.getParameter("pid");
		String WeChatID = req.getParameter("WeChatID");
		if (WeChatID != null) {
			model.addAttribute("WeChatID", WeChatID);
		}
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Product> lsps = ProductService.getProductList(responseMap);
			if (lsps.size() > 0) {
				product = lsps.get(0);
				ProdPictures lsimg = product.getImage();
				if (lsimg != null) {
					model.addAttribute("Image", product.getImage().getUrl());
				} else {
					model.addAttribute("Image", "");
				}
				model.addAttribute("WeChat", product.getWeChat().getCompany());
				model.addAttribute("WeChatID", product.getWeChat().getID());
				model.addAttribute("Content", product.getContent());
				model.addAttribute("ImageID", product.getImageID());
				Status = 1;
			} else {
				Status = 404;
				model.addAttribute("WeChatID", 0);
				model.addAttribute("ImageID", 0);
			}
		}
		if (Status == 404 || Status == 0) {
			model.addAttribute("WeChatID", 0);
			model.addAttribute("ImageID", 0);
		}
		model.addAttribute("ProductForm", product);
		model.addAttribute("Status", Status);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneaddgood");

	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("ProductForm") Product product, @RequestParam("fileImage") MultipartFile file, @RequestParam(
			value = "Img_X", required = false) String Img_X, @RequestParam(value = "Img_Y", required = false) String Img_Y, @RequestParam(value = "Img_W",
			required = false) String Img_W, @RequestParam(value = "Img_H", required = false) String Img_H, BindingResult result, HttpServletResponse response,
			HttpSession session, Model model, HttpServletRequest req) throws Exception {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String submittype = req.getParameter("submittype");
		Integer proResult = 0;
		boolean isNew = false;
		long ProdID = 0;
		if (product.getID() > 0) {
			ProdID = product.getID();
		} else {
			ProdID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		if (product.getName().isEmpty()) {
			result.rejectValue("Name", "misFormat", "产品名称不能为空");
		}

		model.addAttribute("Content", product.getContent());
		model.addAttribute("WeChatID", product.getWeChatID());
		PictureParam picParam = new PictureParam();
		OperateImage image = new OperateImage();
		if (!file.isEmpty() && product.getImageID() == 0) {
			picParam = image.getPicutreBytes(file, req.getSession().getServletContext().getRealPath("/"), null, new String[] { Img_X, Img_Y, Img_W, Img_H });
			model.addAttribute("inputImage", picParam.getFileName());
			if (picParam.getErrorMessage() != null && !picParam.getErrorMessage().isEmpty()) {
				result.rejectValue("QRCodeURL", "misFormat", picParam.getErrorMessage());
			}

		} else {
			if (file.isEmpty() && product.getImageID() == 0) {
				result.rejectValue("Image", "misFormat", "图片文件不能为空.");
			}
		}

		if (!result.hasErrors()) {
			/* 保存文件 begin */

			responseMap = new HashMap<String, Object>();
			responseMap.put("ProductID", product.getImageID());

			List<ProdPictures> lsprod = ProdPicturesService.getPicturesList(responseMap);
			for (int i = 0; i < lsprod.size(); i++) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", lsprod.get(i).getID());
				ProdPicturesService.deletePictures(responseMap);
			}
			if (!file.isEmpty() && product.getImageID() == 0) {
				image = new OperateImage();
				picParam.setSavefileName(image.SavePicture(req, picParam.getPrefix(), picParam.getBytes(), new String[] { Img_X, Img_Y, Img_W, Img_H }));
				ProdPictureController pc = new ProdPictureController();
				Users user = (Users) session.getAttribute("UserName");
				ProdPictures Pic = pc.addPicture("upload/" + picParam.getSavefileName(), picParam.getFileName(), ProdID, user.getWeChatID());
				Pic.setType(2);
				product.setImageID(Pic.getID());
				ProdPicturesService.addPictures(Pic);
				model.addAttribute("Image", "upload/" + picParam.getSavefileName());
			}
			/* 保存文件 end */
			product.setStatus(1);
			product.setModifyTime(new Date());
			if (isNew) {
				product.setID(ProdID);
				product.setCreateTime(sdf.format(new Date()));
				proResult = ProductService.addProduct(product);
			} else {
				proResult = ProductService.updateProduct(product);
			}
			if (proResult > 0) {

				if (submittype != null && submittype.equals("phone")) {
					model.addAttribute("content", req.getContextPath() + "/Product/phonegoodlist?WeChatID=" + String.valueOf(product.getWeChatID()));
					return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "modifysuccess");
				} else {
					model.addAttribute("content", req.getContextPath() + "/Product/ProductList?WeChatID=" + String.valueOf(product.getWeChatID()));
					return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
				}
			} else {
				result.rejectValue("Name", "misFormat", "保存失败,请重试");
			}

		}

		if (result.hasErrors()) {
			model.addAttribute("ProductForm", product);// 把accountVo对象返回到页面，这样不至于表单被清空了
			if (submittype != null && submittype.equals("phone")) {
				return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneaddgood");
			} else {
				return new ModelAndView(SmBaseGlobal.WebViewPath + "addProduct");
			}

		}

		if (submittype != null && submittype.equals("phone")) {
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneaddgood");
		} else {
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addProduct");
		}
	}

	@RequestMapping(value = "/phonegoodlist", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phonegoodlist(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user = null;

		user = (Users) session.getAttribute("UserName");
		String WeChatID = request.getParameter("WeChatID");
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
		if (WeChatID == null || WeChatID.isEmpty()) {
			WeChatID = String.valueOf(user.getWeChatID());
		}
		model.addAttribute("WeChatID", WeChatID);

		responseMap.put("WeChatID", Long.parseLong(WeChatID));

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
		List<Product> lswe = ProductService.getProductList(responseMap);
		model.addAttribute("data", lswe);
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phonegoodlist");
	}

	@RequestMapping(value = "/ProductList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Users user = null;

		String WeChatID = request.getParameter("WeChatID");
		user = (Users) session.getAttribute("UserName");
		if (WeChatID == null || WeChatID.isEmpty()) {
			WeChatID = String.valueOf(user.getWeChatID());
		}
		model.addAttribute("WeChatID", WeChatID);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ProductList");
	}

	@RequestMapping(value = "/getProductList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getProductList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String Name = request.getParameter("Name");
		String state = request.getParameter("state");
		String WeChatID = request.getParameter("WeChatID");
		String pageSize = request.getParameter("pageSize");
		String pageNumber = request.getParameter("pageNumber");
		String PC = request.getParameter("PC");

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if (state != null && state.equals("1")) {
			checkParammap.put("Status", 1);
		}
		if (Name != null && !Name.isEmpty()) {
			checkParammap.put("Name", Name);
		}
		if (pageSize != null && !pageSize.isEmpty()) {
			checkParammap.put("limit", Integer.parseInt(pageNumber) * Integer.parseInt(pageSize));
			if (PC != null) {
				checkParammap.put("limit", pageSize);
			}
		} else {
			checkParammap.put("limit", 10);
		}
		if (pageNumber != null && !pageNumber.isEmpty()) {
			checkParammap.put("start", Integer.parseInt(pageNumber) * Integer.parseInt(pageSize) - Integer.parseInt(pageNumber));
			if (PC != null) {
				checkParammap.put("start", pageNumber);
			}
		} else {
			checkParammap.put("start", 0);
		}
		checkParammap.put("WeChatID", Long.parseLong(WeChatID));
		List<Product> lswe = ProductService.getProductList(checkParammap);
		int Prodcount = ProductService.getProductCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);

		return responseMap;
	}

	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = null;

		String WeChat = request.getParameter("pid");
		String state = request.getParameter("state");
		String[] wids = WeChat.split(",");
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				if (state.equals("1")) {
					ProductService.enabledProduct(responseMap);
				} else {
					ProductService.deleteProduct(responseMap);
				}

				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/levelProduct", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> levelProduct(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String ProdiD = request.getParameter("pid");
		String WeChat = request.getParameter("wid");
		String[] ProdiDs = ProdiD.split(",");
		int result = 0;

		for (String id : ProdiDs) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("WeChatID", WeChat);
				int MaxWeight = ProductService.getProductByMaxList(responseMap) + 1;

				responseMap = new HashMap<String, Object>();
				responseMap.put("ModifyTime", new Date());
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("Weight", MaxWeight);
				ProductService.TopLevevProduct(responseMap);

				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
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
	 * @throws java.text.ParseException
	 */
	@RequestMapping(value = "/phoneweChatPordDetail", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phoneweChatGroupDetail(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) throws IOException,
			java.text.ParseException {
		String[] splitstr = {};
		System.err.println("进入phoneweChatPordDetail");
		if (request.getQueryString() != null) {
			splitstr = request.getQueryString().replace("&amp;", "&").split("&");

		}
		Map<String, Object> mapParamp = new HashMap<String, Object>();
		for (int i = 0; i < splitstr.length; i++) {
			mapParamp.put(splitstr[i].split("=")[0], splitstr[i].split("=").length > 1 ? splitstr[i].split("=")[1] : "");
		}
		// 为了解决Url中老是会出现&amp;的情况
		String PID = request.getParameter("_pid_") == null ? (String) mapParamp.get("_pid_") : request.getParameter("_pid_");
		String PCCode = request.getParameter("_pc_") == null ? (String) mapParamp.get("_pc_") : request.getParameter("_pc_");
		String area = request.getParameter("_area_") == null ? (String) mapParamp.get("_area_") : request.getParameter("_area_");
		String type = request.getParameter("_type_") == null ? (String) mapParamp.get("_type_") : request.getParameter("_type_");
		String wgid = request.getParameter("_wgid_") == null ? (String) mapParamp.get("_wgid_") : request.getParameter("_wgid_");
		String status = request.getParameter("_status_") == null ? (String) mapParamp.get("_status_") : request.getParameter("_status_");
		String ispre = request.getParameter("_ispre_") == null ? (String) mapParamp.get("_ispre_") : request.getParameter("_ispre_");
		if (PID == null || PID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail");
		}
		List<Product> lswp = new ArrayList<Product>();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		long voteID=0;
		List<ProdPictures> lspicture = new ArrayList<ProdPictures>();// 照片列表

		if (status != null && status.equals("7")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Apply.getid());
		} else if (status != null && status.equals("8")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.NotPass.getid());
		} else if (status != null && status.equals("12")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Draft.getid());
		}else  {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		}
		if (PID == null || PID.isEmpty() || PID.equals("null")) {
			PID = "0";
		}
		if (!SmBaseUtil.isNumeric(PID)) {
			PID = "0";
		}
		responseMap.put("ID", Long.parseLong(PID));
		model.addAttribute("NID", Long.parseLong(PID));
		if ((type == null || type.isEmpty()) || !type.matches("[0-9]+")) {
			type = "2";
		}
		if (type.equals("1")) {// 活动
			List<Activity> lsact = ReadActivityService.getActivityList(responseMap);
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setImage(lsact.get(0).getImage());
				prod.setName(lsact.get(0).getTitle());
				prod.setWeChat(lsact.get(0).getWeChat());
				wgid = String.valueOf(lsact.get(0).getWeChatID());
				lswp.add(prod);
			}
		} else if (type.equals("2")) {// 新闻
			List<Notices> lsact = ReadNoticesService.getReadNoticesList(responseMap);//我不影响读的情况下，改成NoticesService ,这里需要一些关联的信息
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setImage(lsact.get(0).getImage());
				prod.setName(lsact.get(0).getTitle());
				prod.setPKID(lsact.get(0).getPKID());
				prod.setClickCount(lsact.get(0).getClickCount());
				prod.setCreateTime(lsact.get(0).getCreateTime());
				prod.setAuthor(lsact.get(0).getAuthor());
				prod.setLikeCount(lsact.get(0).getLike());
				prod.setMemo(lsact.get(0).getCaptainComment());//社长点评
				prod.setContentType(lsact.get(0).getContentType());
				model.addAttribute("PublishUserID", lsact.get(0).getPublishUser());
				final Notices notices = lsact.get(0);
				if (ispre == null || ispre.isEmpty() || !ispre.equals("1")) {
					lsact.get(0).setClickCount(lsact.get(0).getClickCount()+1);
					addNoticesClickCount(request, String.valueOf(lsact.get(0).getID()), lsact.get(0),ReadNoticesService,ReadStudentsService,ClickListService,IntegrationService,StudentsService,WeMoneyService,NoticesService,WeMoneyRecordService,ReadWeMoneyService,VoteService,ReadWeMoneyRecordService,ReadCommentService,ReadVoteService);
				}
				// NoticesService.updateNotices(notices);
				lswp.add(prod);
				responseMap = new HashMap<String, Object>();
				responseMap.put("ProductID", lsact.get(0).getID());
				
				responseMap.put("Status", 1);
				lspicture = ReadProdPicturesService.getPictureList(responseMap);
				if (notices.getLike() <= 0) {
					notices.setLike(0);
				}
				if(lsact.get(0).getVoteID()>0){
					model.addAttribute("NoticesVoteID", 1);
				}else{
					model.addAttribute("NoticesVoteID", 0);
				}
				voteID=notices.getVoteID();
				model.addAttribute("DataContent",SmBaseUtil.StripHT(notices.getContent()));
				model.addAttribute("LikeCount", notices.getLike());
				model.addAttribute("VoteCount", notices.getVoteCount());
				model.addAttribute("PositionIcon", SmBaseUtil.getPositionIcon(request,lsact.get(0).getStudent()));
				model.addAttribute("LevelIcon", SmBaseUtil.getLevelIcon(request,lsact.get(0).getStudent(),ReadStudentStatService));
				model.addAttribute("HonourIcon", SmBaseUtil.getHonourIcon(request,lsact.get(0).getStudent(),ReadHonorRecordService));
				if (notices.getVoteID()>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", notices.getVoteID());
					map.put(Vote.attributeVoteStatus, SmBaseGlobal.CheckStatus.Effective.getid());
					List<Vote> votes=ReadVoteService.getVoteList(map);
					if (votes.size()>0) {
						boolean isAct=compareDateForNow(votes.get(0).getStartDate(), votes.get(0).getEndDate());
						if (isAct) {
							notices.setIsActivity(1);
							//是在这个时间段
							model.addAttribute("IsActivity", true);
						}else{
							//不是在这个时间段 则把他更新啦，防止下次在扫描到稿件
							notices.setIsActivity(0);
							model.addAttribute("IsActivity", false);
						}
						model.addAttribute("VoteHeadUrl", votes.get(0).getHeadUrl());
						model.addAttribute("VoteMiddleUrl", votes.get(0).getMiddleUrl());
						model.addAttribute("VoteFootUrl", votes.get(0).getFootUrl());
					}else{
							notices.setIsActivity(0);
							model.addAttribute("IsActivity", false);
					}
					try{
						NoticesService.updateNoticesActivity(notices);
					}catch (Exception e) {
					}
					
				}
				
				Students student=SessionUtils.getStudentSession(request);
				if(student!=null){
					String param=URLEncoder.encode("{\"StudentID\":\""+ student.getID() +"\",\"NoticesID\":\""+ lsact.get(0).getID() +"\"}","utf-8");
					JSONObject obj=NetUtil.doGet(SmBaseUtil.getProjectBaseUrlNoProject(request)+SmBaseGlobal.WeNewsInterfaceService+"/Notices/Interface/V2/JudgeShare?param="+param);
					if(obj!=null && obj.containsKey("chance") && obj.get("chance")!=null){
						model.addAttribute("ischance", true);
					}
				}
				
				responseMap = new HashMap<String, Object>();
				responseMap.put("NID", lsact.get(0).getID());
				responseMap.put("Type", SmBaseGlobal.DealInfoType.CorrectList.getid());//待批改列表
				responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				List<ApplyList> applyList = ReadApplyListService.getApplyListList(responseMap);
				if(applyList.size()>0){
					model.addAttribute("applyListID", applyList.get(0).getID());
				}
			}
		} else if (type.equals("3")) {// 视频
			List<Video> lsact = ReadVideoService.getVideoList(responseMap);
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setURL((lsact.get(0).getVID()==null || lsact.get(0).getVID().isEmpty())?lsact.get(0).getUrl():lsact.get(0).getVID());// 视频播放ID
				prod.setName(lsact.get(0).getTitle());
				prod.setClickCount(lsact.get(0).getClickCount());
				prod.setCreateTime(lsact.get(0).getCreateTime());
				prod.setAuthor(lsact.get(0).getAuthor());
				prod.setLikeCount(lsact.get(0).getLikeCount());
				prod.setPKID(String.valueOf(lsact.get(0).getID()));
				prod.setClickCount(lsact.get(0).getClickCount());
				prod.setContentType(1);
				wgid = String.valueOf(lsact.get(0).getWeChatID());
				lswp.add(prod);
				model.addAttribute("LikeCount", lsact.get(0).getLikeCount());
				model.addAttribute("VoteCount", lsact.get(0).getVoteCount());
				model.addAttribute("VideoType", lsact.get(0).getType());
				model.addAttribute("DataContent",SmBaseUtil.StripHT(prod.getContent()));
				
				if (lsact.get(0).getVoteID()>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", lsact.get(0).getVoteID());
					map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					List<Vote> votes=ReadVoteService.getVoteList(map);
					if (votes.size()>0) {
						boolean isAct=compareDate(votes.get(0).getStartDate(), votes.get(0).getEndDate(), lsact.get(0).getCreateDate());
						if (isAct) {
							//是在这个时间段
							model.addAttribute("IsActivity", true);
						}else{
							//不是在这个时间段 则把他更新啦，防止下次在扫描到稿件
							lsact.get(0).setIsActivity(0);
							VideoService.updateVideo(lsact.get(0));
							model.addAttribute("IsActivity", false);
						}
					}else{
							lsact.get(0).setIsActivity(0);
							VideoService.updateVideo(lsact.get(0));
							model.addAttribute("IsActivity", false);
					}
					
				}
				model.addAttribute("UserType", lsact.get(0).getUserType());
				if (lsact.get(0).getUserType()==SmBaseGlobal.UserType.Student.getid()) {
					model.addAttribute("UserID", lsact.get(0).getUserID());
				}
				
				int result = SmBaseUtil.getClickInfo(request, String.valueOf(lsact.get(0).getID()), ClickListService);
				if (result>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", lsact.get(0).getID());
					VideoService.UpClickCount(map);
				}
				
			}
		} else {
			lswp = ReadProductService.getProductList(responseMap);
			if (lswp.size() > 0) {
				wgid = String.valueOf(lswp.get(0).getWeChatID());
			}
		}

		if (lswp.size() > 0) {
			// addUserClickCount(request, PID);
			model.addAttribute("Data", lswp.get(0));

			model.addAttribute("Title", lswp.get(0).getName());

			if(lswp.get(0).getContentType()==0){
				model.addAttribute("Content", huanhang(lswp.get(0).getContent()));
			}else{
				model.addAttribute("Content", lswp.get(0).getContent());//分开不然格式错乱
			}
			
			
			
			
		} else {
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail");
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		map.put("NoticesID", PID);
		Students user = (Students)session.getAttribute("StudentName");
		if (user!=null) {
			map.put("UserID", user.getID());
			if(user.getImageUrl()!=null && !user.getImageUrl().isEmpty()){
				model.addAttribute("CommentImageUrl", user.getImageUrl().split(",")[0]);
			}else{
				model.addAttribute("CommentImageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			}
		}else{
			model.addAttribute("CommentImageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			map.put("UserID", SmBaseUtil.getIpAddr(request));
		}
		
		int j=ReadLikeRecordService.getLikeRecordCount(map);
		if (j>0) {
			model.addAttribute("likecount",1);//判断是否点赞
		} else {
			model.addAttribute("likecount",0);//判断是否点赞

		}
		model.addAttribute("ImageCount", lspicture.size());
		model.addAttribute("ImageList", lspicture);
		model.addAttribute("_pc_", PCCode);
		model.addAttribute("_wgid_", wgid);
		model.addAttribute("_area_", area);
		model.addAttribute("Type", type);

		
		if (ispre == null || ispre.isEmpty()) {
			model.addAttribute("ispre", 0);
		} else {
			model.addAttribute("ispre", ispre);
		}

		// 评价模块
		String basePath = SmBaseUtil.getProjectBaseUrl(request);
		String defaultImage=basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
		responseMap = new HashMap<String, Object>();
		responseMap.put("NoticesID", PID);
		responseMap.put("start", 0);
		responseMap.put("limit", 10);
		List<Comment> comments = ReadCommentService.getCommentList(responseMap);
		int commentCount = ReadCommentService.getCommentCount(responseMap);
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if(comment.getUserID()==null || comment.getUserID().isEmpty()){
				comment.setUserID("0");
			}
			if (Long.parseLong(comment.getUserID()) == 0) {
				comment.setUserName("游客");
				comment.setUserImage(defaultImage);
			} else {
				if (comment.getUserImage()==null ||  comment.getUserImage().isEmpty()) {
					comment.setUserImage(defaultImage);
				}
			}
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				comment.setFromtTime(SmBaseUtil.format(sdf.parse(comment.getCreateTime())));
			}catch (Exception e) {
				ErrorUtil.HandleError(String.valueOf(comment.getID()), "wtb.core.controller.ProductController.phoneweChatPordDetail", e);
				comment.setFromtTime(sdf.format(new Date()));
			}
		}

		model.addAttribute("CommentData", comments);
		model.addAttribute("CommentCount", commentCount);
		System.err.println("离开phoneweChatPordDetail");
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.NewDetail.getid());
		List<Advert> Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("Banner_Adverts", RandomData(Adverts));
		}
		if(type.equals("3")){
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatVedioDetail");
		}
		if(type.equals("2") && String.valueOf(voteID).equals("879990030779944960")){
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail_activity");
		}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail");
	}
	
	
	/**
	 * 计算出到底返回哪一条数据
	 * @param adverts
	 * @return
	 */
	private Advert RandomData(List<Advert> adverts) {
		Advert adver=null;
		if(adverts.size()==1) {
			adver=adverts.get(0);
		}else {
			int count=0;
			int tempCount=0;
			int index=0;
			for (int i = 0; i < adverts.size(); i++) {
				count+=adverts.get(i).getIndex();
			}
			int rand=1+(int)(Math.random()*count);
			System.err.println("随机值："+rand);
			for (int i = 0; i < adverts.size(); i++) {
				tempCount+=adverts.get(i).getIndex();
				if(tempCount>=rand) {
					index=i;
					break;
				}else {
					System.err.println("不符合");
				}
			}
			adver=adverts.get(index);
			
		}
		return adver;
	}
	@RequestMapping(value = "/phoneweChatPordDetail_activity", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView phoneweChatPordDetail_activity(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) throws IOException,
			java.text.ParseException {
		String[] splitstr = {};
		System.err.println("进入phoneweChatPordDetail");
		if (request.getQueryString() != null) {
			splitstr = request.getQueryString().replace("&amp;", "&").split("&");

		}
		Map<String, Object> mapParamp = new HashMap<String, Object>();
		for (int i = 0; i < splitstr.length; i++) {
			mapParamp.put(splitstr[i].split("=")[0], splitstr[i].split("=").length > 1 ? splitstr[i].split("=")[1] : "");
		}
		// 为了解决Url中老是会出现&amp;的情况
		String PID = request.getParameter("_pid_") == null ? (String) mapParamp.get("_pid_") : request.getParameter("_pid_");
		String PCCode = request.getParameter("_pc_") == null ? (String) mapParamp.get("_pc_") : request.getParameter("_pc_");
		String area = request.getParameter("_area_") == null ? (String) mapParamp.get("_area_") : request.getParameter("_area_");
		String type = request.getParameter("_type_") == null ? (String) mapParamp.get("_type_") : request.getParameter("_type_");
		String wgid = request.getParameter("_wgid_") == null ? (String) mapParamp.get("_wgid_") : request.getParameter("_wgid_");
		String status = request.getParameter("_status_") == null ? (String) mapParamp.get("_status_") : request.getParameter("_status_");
		String ispre = request.getParameter("_ispre_") == null ? (String) mapParamp.get("_ispre_") : request.getParameter("_ispre_");
		if (PID == null || PID.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail");
		}
		List<Product> lswp = new ArrayList<Product>();
		Map<String, Object> responseMap = new HashMap<String, Object>();

		List<ProdPictures> lspicture = new ArrayList<ProdPictures>();// 照片列表

		if (status != null && status.equals("7")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Apply.getid());
		} else if (status != null && status.equals("8")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.NotPass.getid());
		} else if (status != null && status.equals("12")) {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Draft.getid());
		}else  {
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		}
		if (PID == null || PID.isEmpty() || PID.equals("null")) {
			PID = "0";
		}
		if (!SmBaseUtil.isNumeric(PID)) {
			PID = "0";
		}
		responseMap.put("ID", Long.parseLong(PID));
		model.addAttribute("NID", Long.parseLong(PID));
		if ((type == null || type.isEmpty()) || !type.matches("[0-9]+")) {
			type = "2";
		}
		if (type.equals("1")) {// 活动
			List<Activity> lsact = ReadActivityService.getActivityList(responseMap);
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setImage(lsact.get(0).getImage());
				prod.setName(lsact.get(0).getTitle());
				prod.setWeChat(lsact.get(0).getWeChat());
				wgid = String.valueOf(lsact.get(0).getWeChatID());
				lswp.add(prod);
			}
		} else if (type.equals("2")) {// 新闻
			List<Notices> lsact = ReadNoticesService.getReadNoticesList(responseMap);//我不影响读的情况下，改成NoticesService ,这里需要一些关联的信息
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setImage(lsact.get(0).getImage());
				prod.setName(lsact.get(0).getTitle());
				prod.setPKID(lsact.get(0).getPKID());
				prod.setClickCount(lsact.get(0).getClickCount());
				prod.setCreateTime(lsact.get(0).getCreateTime());
				prod.setAuthor(lsact.get(0).getAuthor());
				prod.setLikeCount(lsact.get(0).getLike());
				prod.setMemo(lsact.get(0).getCaptainComment());//社长点评
				prod.setContentType(lsact.get(0).getContentType());
				model.addAttribute("PublishUserID", lsact.get(0).getPublishUser());
				final Notices notices = lsact.get(0);
				if (ispre == null || ispre.isEmpty() || !ispre.equals("1")) {
					lsact.get(0).setClickCount(lsact.get(0).getClickCount()+1);
					addNoticesClickCount(request, String.valueOf(lsact.get(0).getID()), lsact.get(0),ReadNoticesService,ReadStudentsService,ClickListService,IntegrationService,StudentsService,WeMoneyService,NoticesService,WeMoneyRecordService,ReadWeMoneyService,VoteService,ReadWeMoneyRecordService,ReadCommentService,ReadVoteService);
				}
				// NoticesService.updateNotices(notices);
				lswp.add(prod);
				responseMap = new HashMap<String, Object>();
				responseMap.put("ProductID", lsact.get(0).getID());
				
				responseMap.put("Status", 1);
				lspicture = ReadProdPicturesService.getPictureList(responseMap);
				if (notices.getLike() <= 0) {
					notices.setLike(0);
				}
				if(lsact.get(0).getVoteID()>0){
					model.addAttribute("NoticesVoteID", 1);
				}else{
					model.addAttribute("NoticesVoteID", 0);
				}
				model.addAttribute("DataContent",SmBaseUtil.StripHT(notices.getContent()));
				model.addAttribute("LikeCount", notices.getLike());
				model.addAttribute("VoteCount", notices.getVoteCount());
				model.addAttribute("PositionIcon", SmBaseUtil.getPositionIcon(request,lsact.get(0).getStudent()));
				model.addAttribute("LevelIcon", SmBaseUtil.getLevelIcon(request,lsact.get(0).getStudent(),ReadStudentStatService));
				model.addAttribute("HonourIcon", SmBaseUtil.getHonourIcon(request,lsact.get(0).getStudent(),ReadHonorRecordService));
				if (notices.getVoteID()>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", notices.getVoteID());
					map.put(Vote.attributeVoteStatus, SmBaseGlobal.CheckStatus.Effective.getid());
					List<Vote> votes=ReadVoteService.getVoteList(map);
					if (votes.size()>0) {
						boolean isAct=compareDateForNow(votes.get(0).getStartDate(), votes.get(0).getEndDate());
						if (isAct) {
							notices.setIsActivity(1);
							//是在这个时间段
							model.addAttribute("IsActivity", true);
						}else{
							//不是在这个时间段 则把他更新啦，防止下次在扫描到稿件
							notices.setIsActivity(0);
							model.addAttribute("IsActivity", false);
						}
						model.addAttribute("VoteHeadUrl", votes.get(0).getHeadUrl());
						model.addAttribute("VoteMiddleUrl", votes.get(0).getMiddleUrl());
						model.addAttribute("VoteFootUrl", votes.get(0).getFootUrl());
					}else{
							notices.setIsActivity(0);
							model.addAttribute("IsActivity", false);
					}
					try{
						NoticesService.updateNotices(notices);
					}catch (Exception e) {
					}
					
				}
				
				
			}
		} else if (type.equals("3")) {// 视频
			List<Video> lsact = ReadVideoService.getVideoList(responseMap);
			if (lsact.size() > 0) {
				Product prod = new Product();
				prod.setContent(lsact.get(0).getContent());
				prod.setURL((lsact.get(0).getVID()==null || lsact.get(0).getVID().isEmpty())?lsact.get(0).getUrl():lsact.get(0).getVID());// 视频播放ID
				prod.setName(lsact.get(0).getTitle());
				prod.setClickCount(lsact.get(0).getClickCount());
				prod.setCreateTime(lsact.get(0).getCreateTime());
				prod.setAuthor(lsact.get(0).getAuthor());
				prod.setLikeCount(lsact.get(0).getLikeCount());
				prod.setPKID(String.valueOf(lsact.get(0).getID()));
				prod.setClickCount(lsact.get(0).getClickCount());
				wgid = String.valueOf(lsact.get(0).getWeChatID());
				lswp.add(prod);
				model.addAttribute("LikeCount", lsact.get(0).getLikeCount());
				model.addAttribute("VoteCount", lsact.get(0).getVoteCount());
				model.addAttribute("VideoType", lsact.get(0).getType());
				model.addAttribute("DataContent",SmBaseUtil.StripHT(prod.getContent()));
				
				if (lsact.get(0).getVoteID()>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", lsact.get(0).getVoteID());
					map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
					List<Vote> votes=ReadVoteService.getVoteList(map);
					if (votes.size()>0) {
						boolean isAct=compareDate(votes.get(0).getStartDate(), votes.get(0).getEndDate(), lsact.get(0).getCreateDate());
						if (isAct) {
							//是在这个时间段
							model.addAttribute("IsActivity", true);
						}else{
							//不是在这个时间段 则把他更新啦，防止下次在扫描到稿件
							lsact.get(0).setIsActivity(0);
							VideoService.updateVideo(lsact.get(0));
							model.addAttribute("IsActivity", false);
						}
					}else{
							lsact.get(0).setIsActivity(0);
							VideoService.updateVideo(lsact.get(0));
							model.addAttribute("IsActivity", false);
					}
					
				}
				model.addAttribute("UserType", lsact.get(0).getUserType());
				if (lsact.get(0).getUserType()==SmBaseGlobal.UserType.Student.getid()) {
					model.addAttribute("UserID", lsact.get(0).getUserID());
				}
				
				int result = SmBaseUtil.getClickInfo(request, String.valueOf(lsact.get(0).getID()), ClickListService);
				if (result>0) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", lsact.get(0).getID());
					VideoService.UpClickCount(map);
				}
				
			}
		} else {
			lswp = ReadProductService.getProductList(responseMap);
			if (lswp.size() > 0) {
				wgid = String.valueOf(lswp.get(0).getWeChatID());
			}
		}

		if (lswp.size() > 0) {
			// addUserClickCount(request, PID);
			model.addAttribute("Data", lswp.get(0));

			model.addAttribute("Title", lswp.get(0).getName());

			if(lswp.get(0).getContentType()==0){
				model.addAttribute("Content", huanhang(lswp.get(0).getContent()));
			}else{
				model.addAttribute("Content", lswp.get(0).getContent());//分开不然格式错乱
			}
			
			
			
			
		} else {
			response.sendRedirect(request.getContextPath() + "/include/notfond.html");
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail");
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
		map.put("NoticesID", PID);
		Students user = (Students)session.getAttribute("StudentName");
		if (user!=null) {
			map.put("UserID", user.getID());
			if(user.getImageUrl()!=null && !user.getImageUrl().isEmpty()){
				model.addAttribute("CommentImageUrl", user.getImageUrl());
			}else{
				model.addAttribute("CommentImageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			}
		}else{
			model.addAttribute("CommentImageUrl", SmBaseUtil.getProjectBaseUrl(request)+SmBaseGlobal.UserDefaultImageUrl);
			map.put("UserID", SmBaseUtil.getIpAddr(request));
		}
		
		int j=ReadLikeRecordService.getLikeRecordCount(map);
		if (j>0) {
			model.addAttribute("likecount",1);//判断是否点赞
		} else {
			model.addAttribute("likecount",0);//判断是否点赞

		}
		model.addAttribute("ImageCount", lspicture.size());
		model.addAttribute("ImageList", lspicture);
		model.addAttribute("_pc_", PCCode);
		model.addAttribute("_wgid_", wgid);
		model.addAttribute("_area_", area);
		model.addAttribute("Type", type);

		
		if (ispre == null || ispre.isEmpty()) {
			model.addAttribute("ispre", 0);
		} else {
			model.addAttribute("ispre", ispre);
		}

		// 评价模块
		String basePath = SmBaseUtil.getProjectBaseUrl(request);
		String defaultImage=basePath+SmBaseGlobal.PhoneUserDefaultImageUrl;
		responseMap = new HashMap<String, Object>();
		responseMap.put("NoticesID", PID);
		responseMap.put("start", 0);
		responseMap.put("limit", 10);
		List<Comment> comments = ReadCommentService.getCommentList(responseMap);
		int commentCount = ReadCommentService.getCommentCount(responseMap);
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if(comment.getUserID()==null || comment.getUserID().isEmpty()){
				comment.setUserID("0");
			}
			if (Long.parseLong(comment.getUserID()) == 0) {
				comment.setUserName("游客");
				comment.setUserImage(defaultImage);
			} else {
				if (comment.getUserImage()==null ||  comment.getUserImage().isEmpty()) {
					comment.setUserImage(defaultImage);
				}
			}
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try{
				comment.setFromtTime(SmBaseUtil.format(sdf.parse(comment.getCreateTime())));
			}catch (Exception e) {
				ErrorUtil.HandleError(String.valueOf(comment.getID()), "wtb.core.controller.ProductController.phoneweChatPordDetail", e);
				comment.setFromtTime(sdf.format(new Date()));
			}
		}

		model.addAttribute("CommentData", comments);
		model.addAttribute("CommentCount", commentCount);
		System.err.println("离开phoneweChatPordDetail");
		if(type.equals("3")){
			return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatVedioDetail");
		}
		return new ModelAndView(SmBaseGlobal.WeNewsMobileViewPath + "phoneweChatPordDetail_activity");
	}

	/**
	 * 获取签名
	 * 
	 * @throws java.text.ParseException
	 * @throws IOException
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */

	@RequestMapping(value = "/getSignature", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSignature(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException, java.text.ParseException,
			NoSuchAlgorithmException {
		String wid=request.getParameter("WID");
		
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String access_token = null;
		String ticket = null;
		WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(WeChatInfoServices, wid);
		if (weChatInfo!=null) {
			long currentTime = System.currentTimeMillis() - 115 * 60 * 1000;// 115分钟改变一次，不取120是有原因的就怕，刚刚2小时，差一秒就报错啦
			long currentTime2 = System.currentTimeMillis() - 300 * 60 * 1000;// 防止出错
			Date date = new Date(currentTime);
			Date date2 = null;
			if (weChatInfo.getTKTime() != null) {
				date2 = sdf.parse(weChatInfo.getTKTime());
				// date2 = new Date(currentTime2);
			} else {
				date2 = new Date(currentTime2);
			}

			if (date.getTime() <= date2.getTime()) {
				access_token = weChatInfo.getAccessToken();
				ticket =weChatInfo.getTicket();
			} else {
				String AppSecret = weChatInfo.getAppsecret();
				String url = null;
				url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + weChatInfo.getAppID() + "&secret=" + AppSecret;
				net.sf.json.JSONObject jsonObject1 = NetUtil.doGet(url);
				if (jsonObject1.containsKey("access_token") && jsonObject1.getString("access_token") != null) {
					access_token = jsonObject1.getString("access_token");
					String jsapi = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
					net.sf.json.JSONObject japiObject = NetUtil.doGet(jsapi);
					ticket = japiObject.getString("ticket");
					WeChatInfo weChatInf = weChatInfo;
					weChatInf.setAccessToken(access_token);
					weChatInf.setTicket(ticket);
					weChatInf.setTKTime(sdf.format(new Date()));
					weChatInf.setATTime(sdf.format(new Date()));
					WeChatInfoServices.updateWeChatInfo(weChatInf);
				}
			}
		}

		// 微信分享内容
		String nonceStr = getRandomString(32);
		String timeStamp = (String.valueOf(System.currentTimeMillis())).substring(0, 10);// 微信只要精确到秒
		String urlW = request.getParameter("URL");
		String Signature = getSignature(ticket, timeStamp, nonceStr, urlW);
		map = new HashMap<String, Object>();
		if (Signature != null) {
			map.put("status", 1);
			map.put("signature", Signature);
			map.put("AppID", weChatInfo.getAppID());
			map.put("nonceStr", nonceStr);
			map.put("timeStamp", timeStamp);
		} else {
			map.put("status", -1);
		}
		return map;
	}

	
	/**
	 * AddNoticesClickCount  app 调用使用 新闻阅读
	 * @param request
	 * @param response
	 * @param session
	 */
	@RequestMapping(value = "/AddNoticesClickCount", method = {RequestMethod.GET,RequestMethod.POST} )
	public @ResponseBody void AddNoticesClickCount (HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String noticeID=request.getParameter("noticeID");
		System.err.println("======================================");
		System.err.println(noticeID);
		System.err.println("======================================");
		if (noticeID!=null) {
			Notices notices=ReadNoticesService.getNoticesByID(Long.parseLong(noticeID));
			if (notices!=null) {
				addNoticesClickCount(request, noticeID, notices, ReadNoticesService, ReadStudentsService, ClickListService, IntegrationService, StudentsService, WeMoneyService, NoticesService, WeMoneyRecordService, ReadWeMoneyService,VoteService,ReadWeMoneyRecordService,ReadCommentService,ReadVoteService);
			}
		}
	}
	
	
	/**
	 * 添加访问量
	 * 
	 * @param req
	 * @param type
	 * @param EntryID
	 */
	public void addNoticesClickCount(final HttpServletRequest req, final String EntryID, final Notices act,final read.core.service.ReadNoticesService ReadNoticesService,final read.core.service.ReadStudentsService ReadStudentsService,final wtb.core.service.ClickListService ClickListService
			,final wtb.core.service.IntegrationService IntegrationService,final wtb.core.service.StudentsService StudentsService,
			final wtb.core.service.WeMoneyService WeMoneyService,final wtb.core.service.NoticesService NoticesService ,final wtb.core.service.WeMoneyRecordService WeMoneyRecordService,
			final read.core.service.ReadWeMoneyService ReadWeMoneyService,final wtb.core.service.VoteService VoteService,final read.core.service.ReadWeMoneyRecordService ReadWeMoneyRecordService,final read.core.service.ReadCommentService ReadCommentService,
			final read.core.service.ReadVoteService ReadVoteService) {
		Thread t = new Thread() {
			public void run() {
				int result = SmBaseUtil.getClickInfo(req, EntryID, ClickListService);
				if (result > 0) {
					Map<String, Object> responseMap = new HashMap<String, Object>();
					responseMap.put("ID", EntryID);
					// 添加新闻点击量
					NoticesService.UpDateClickCount(responseMap);
					NoticesController notices=new NoticesController();
					if(notices.CheckIsOutAutoRewardWeMoney(WeMoneyRecordService,NoticesService,act,ReadNoticesService)){
						SmBaseUtil.AddAutoRewardWeMoneyForRule(WeMoneyClassify.Read, WeMoneyRecordService, WeMoneyService, ReadWeMoneyService, ReadVoteService, ReadCommentService, act, NoticesService, act.getStudent(), ReadWeMoneyRecordService);
					}
					NoticesController noticon = new NoticesController();
					// 更新积分
					String title = "您的新闻【" + act.getTitle() + "】被浏览";
					noticon.addLevel(Long.parseLong(act.getPublishUser()), title, act, 1, IntegrationService, StudentsService, WeMoneyService,NoticesService,WeMoneyRecordService,ReadNoticesService,ReadStudentsService,ReadWeMoneyService,ReadVoteService);
				}
			}
		};
		t.start();
	}

	
	public int addUserClickCount2(HttpServletRequest req, String EntryID) {
		int result = 0;

		result = SmBaseUtil.getClickInfo(req, EntryID, ClickListService);
		if (result > 0) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", EntryID);
			// 添加产品点击量
			NoticesService.UpClickCount(responseMap);
			return 1;
		}
		return 0;
	}

	private static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 获得分享链接的签名。
	 * 
	 * @param ticket
	 * @param nonceStr
	 * @param timeStamp
	 * @param url
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	// 获得js signature
	public static String getSignature(String jsapi_ticket, String timestamp, String nonce, String jsurl) throws IOException, NoSuchAlgorithmException {
		/****
		 * 对 jsapi_ticket、 timestamp 和 nonce 按字典排序 对所有待签名参数按照字段名的 ASCII
		 * 码从小到大排序（字典序）后，使用 URL 键值对的格式（即key1=value1&key2=value2…）拼接成字符串
		 * string1。这里需要注意的是所有参数名均为小写字符。 接下来对 string1 作 sha1 加密，字段名和字段值都采用原始值，不进行
		 * URL 转义。即 signature=sha1(string1)。
		 * **如果没有按照生成的key1=value&key2=value拼接的话会报错
		 */
		String[] paramArr = new String[] { "jsapi_ticket=" + jsapi_ticket, "noncestr=" + nonce, "timestamp=" + timestamp, "url=" + jsurl };
		Arrays.sort(paramArr);
		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat("&" + paramArr[1]).concat("&" + paramArr[2]).concat("&" + paramArr[3]);
		System.out.println("拼接之后的content为:" + content);
		String gensignature = null;
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		// 对拼接后的字符串进行 sha1 加密
		byte[] digest = md.digest(content.toString().getBytes());
		gensignature = byteToStr(digest);

		// 将 sha1 加密后的字符串与 signature 进行对比
		if (gensignature != null) {
			gensignature = gensignature.toLowerCase();
			return gensignature;// 返回signature
		} else {
			return "false";
		}
		// return (String) (ciphertext != null ? ciphertext: false);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	private static String huanhang(String myString) {
		if(myString!=null){
			Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
			Matcher m = CRLF.matcher(myString);
			String tempm = "";
			if (m.find()) {
				if (tempm.isEmpty()) {
					tempm = "<p>";
				} else {
					if (tempm.equals("<p>")) {
						tempm = "</p><p>";
					} else {
						tempm = "<p>";
					}
				}
				myString = m.replaceAll(tempm);
			}
			Pattern CRLFnbsp = Pattern.compile("(\40)");
			Matcher mnbsp = CRLFnbsp.matcher(myString);
			if (mnbsp.find()) {
				myString = mnbsp.replaceAll("&nbsp");
			}
			return myString;
		}else{
			return "";
		}
	}
	public boolean compareDate(Date voteStart,Date voteEnd,Date noticeTime){
		if (noticeTime.getTime()>voteStart.getTime()&&noticeTime.getTime()<voteEnd.getTime()) {
			return true;
		}else{
			return false;
		}
	}
	public boolean compareDateForNow(Date voteStart,Date voteEnd){
		Date noticeTime=new Date();
		if (noticeTime.getTime()>voteStart.getTime()&&noticeTime.getTime()<voteEnd.getTime()) {
			return true;
		}else{
			return false;
		}
	}
		
}