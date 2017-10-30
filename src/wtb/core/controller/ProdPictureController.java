package wtb.core.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.baidu.yun.push.model.AddDevicesToTagResponse;

import sun.misc.BASE64Decoder;
import wtb.core.model.Advert;
import wtb.core.model.PictureParam;
import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;
import wtb.core.model.Users;
import wtb.smUtil.BASE64DecodedMultipartFile;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.OperateImage;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/ProdPicture")
public class ProdPictureController extends BaseController {

	/**
	 * 添加图片
	 * 
	 * @param Path
	 *            储存地址
	 * @param RealName
	 *            实际地址
	 * @param weChatID
	 *            微信ID
	 * @return
	 */
	public ProdPictures addPicture(String Path, String RealName, long ProdID, long WeChatID) {
		System.out.println("addPicture-------addPicture");
		long PicID = new IdWorker(1, 0).nextId();
		ProdPictures pic = new ProdPictures();
		pic.setCreateTime(new Date());
		pic.setID(PicID);
		pic.setPKID(String.valueOf(PicID));
		pic.setModifyTime(new Date());
		pic.setRealUrl(RealName);
		pic.setStatus(1);
		pic.setUrl(Path);
		pic.setProductID(ProdID);
		pic.setWeChatID(WeChatID);
		return pic;
	}

	@RequestMapping(value = "/getProdPictureList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getProdPictureList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Users user = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		user = (Users) session.getAttribute("UserName");
		String searchText = request.getParameter("searchText");
		String type = request.getParameter("type");
		String TypeNormal = request.getParameter("TypeNormal");
		String UnEqType = request.getParameter("UnEqType");
		String soft = request.getParameter("soft");
		String sina = request.getParameter("sina");
		if (searchText != null && !searchText.isEmpty()) {
			searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		}

		Map<String, Object> checkParammap = new HashMap<String, Object>();

		checkParammap.putAll(SmBaseUtil.AddPageParam(request));

		/**
		 * 区域管理员按地区查询
		 */
		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			checkParammap.put("AreaID", user.getAreaID());
		}
		/*
		 * 商家按照ID查询
		 */
		if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
			checkParammap.put("WeChatID", user.getWeChatID());
		}

		if (searchText != null && searchText.length() > 0) {
			checkParammap.put("searchText", searchText);
		}
		/**
		 * 区域管理员按地区查询
		 */
		if (user.getLevel() == SmBaseGlobal.LevelStatus.AreaManage.getid()) {
			checkParammap.put("AreaID", user.getAreaID());
		}
		/*
		 * 商家按照ID查询
		 */
		if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid() || user.getLevel() == SmBaseGlobal.LevelStatus.TeacherManage.getid()) {
			checkParammap.put("WeChatID", user.getWeChatID());
		}

		checkParammap.put("Status", 1);
		if (UnEqType != null && !UnEqType.isEmpty()) {
			checkParammap.put("UnEqType", UnEqType);
		}
		if (type != null && !type.isEmpty()) {
			checkParammap.put("Type", type);
		}
		if (TypeNormal != null && !TypeNormal.isEmpty()) {
			checkParammap.put("TypeNormal", TypeNormal);
		}
		
		if(soft!=null && soft.equals("time")){
			checkParammap.put("time", 1);
		}
		if (sina != null && !sina.isEmpty()) {
			checkParammap.put("Sina", SmBaseUtil.Random());
		}
		
		List<ProdPictures> lswe = ReadProdPicturesService.getPictureList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			if (lswe.get(i).getUrl() != null && !lswe.get(i).getUrl().isEmpty()) {
				String url = SmBaseUtil.getClickImageHtml(lswe.get(i).getUrl());
				lswe.get(i).setTempUrl(url);
			}
		}

		int lsweCount = ReadProdPicturesService.getPictureCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", lsweCount);
		return responseMap;
	}

	/**
	 * 根据 prodpicture_id查询
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/getProdPictureByPPIDList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getProdPictureByPPIDList(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String ppid = request.getParameter("ppid");

		Map<String, Object> checkParammap = new HashMap<String, Object>();

		checkParammap.putAll(SmBaseUtil.AddPageParam(request));

		checkParammap.put("Status", 1);
		if (ppid != null) {
			checkParammap.put("ProductID", Long.parseLong(ppid));
		}

		List<ProdPictures> lswe = ReadProdPicturesService.getPictureList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String url = SmBaseUtil.getClickImageHtml(lswe.get(i).getUrl());
			lswe.get(i).setTempUrl(url);
		}

		int lsweCount = ReadProdPicturesService.getPictureCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", lsweCount);
		return responseMap;
	}

	@RequestMapping(value = "/UploadAction", method = RequestMethod.GET)
	public ModelAndView UploadAction(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/addProdPicture", method = RequestMethod.GET)
	public ModelAndView addPictures(@ModelAttribute("PictureForm") Pictures picture, HttpServletRequest request, Model model, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type != null || !type.isEmpty()) {
			model.addAttribute("type", type);
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");
	}

	@RequestMapping(value = "/addPicture", method = RequestMethod.POST)
	public ModelAndView addPicturePost(@ModelAttribute("PictureForm") ProdPictures picture, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, Model model, HttpSession session) throws Exception {
		Users user = (Users) session.getAttribute("UserName");

		String type = request.getParameter("type");
		model.addAttribute("type", type);
		long ProdIDD = 0;
		String ProdID = null;
		try {
			ProdID = request.getParameter("ID");
			if (ProdID != null) {
				ProdIDD = Long.parseLong(ProdID);
			} else {
				ProdIDD = 0;
			}
		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			System.out.println("ProdID值为空");
		}

		if (!result.hasErrors()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", ProdIDD);
			List<ProdPictures> lsweCount = ProdPicturesService.getPicturesList(responseMap);
			if (lsweCount.size() > 0) {
				lsweCount.get(0).setWeChatID(user.getWeChatID());
				if (type.equals(String.valueOf(SmBaseGlobal.PictureType.Banner.getid()))) {
					lsweCount.get(0).setType(SmBaseGlobal.PictureType.Banner.getid());
				}else if(type.equals(String.valueOf(SmBaseGlobal.PictureType.Advert.getid()))){
					lsweCount.get(0).setType(SmBaseGlobal.PictureType.Advert.getid());
					//添加广告图片
					Advert advert=Advert.Advert(lsweCount.get(0).getUrl().split(",")[0]);
					AdvertService.addAdvert(advert);
				}
				ProdPicturesService.updatePictures(lsweCount.get(0));
			} else {
				result.rejectValue("ID", "misFormat", "图片上传失败,请重新选择上传");
			}
		}
		if (!result.hasErrors()) {
			if (type.equals(String.valueOf(SmBaseGlobal.PictureType.Material.getid()))) {
				model.addAttribute("content", request.getContextPath() + "/ProdPicture/ProdPictureList?AreaID=" + user.getAreaID());
			} else if(type.equals(String.valueOf(SmBaseGlobal.PictureType.Advert.getid()))) {
				model.addAttribute("content", request.getContextPath() + "/Advert/AdvertList");
			}else {
				model.addAttribute("content", request.getContextPath() + "/ProdPicture/ProdPictureHeadList?AreaID=" + user.getAreaID());
			}
			return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
		}

		model.addAttribute("PictureForm", picture);// 把accountVo对象返回到页面，这样不至于表单被清空了
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");

	}

	/**
	 * 添加新闻页 添加图片
	 * 
	 * @param picture
	 * @param request
	 * @param response
	 * @param result
	 * @param file
	 * @param Img_X
	 * @param Img_Y
	 * @param Img_W
	 * @param Img_H
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addNewPicture", method = RequestMethod.POST)
	public ModelAndView addNewPicture(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "Img_X", required = false) String Img_X,
			@RequestParam(value = "Img_Y", required = false) String Img_Y, @RequestParam(value = "Img_W", required = false) String Img_W, @RequestParam(
					value = "Img_H", required = false) String Img_H, Model model, HttpSession session) throws Exception {
		String file = request.getParameter("file");
		String[] b = file.split(",");
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes = decoder.decodeBuffer(b[1]);
		String filePath = request.getParameter("filePath");
		BASE64DecodedMultipartFile base = new BASE64DecodedMultipartFile(bytes);
		base.setOriginalFilename(filePath);

		PictureParam picParam = new PictureParam();

		OperateImage image = new OperateImage();
		long ProdIDD = 0;
		String ProdID = null;
		try {
			ProdID = request.getParameter("ProdID");
			if (ProdID != null) {
				ProdIDD = Long.parseLong(ProdID);
			} else {
				ProdIDD = 0;
			}
		} catch (Exception e) {
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
			System.out.println("ProdID值为空");
		}

		if (!file.isEmpty()) {
			try {
				picParam = image.getPicutreBytes(base, filePath, null, new String[] { Img_X, Img_Y, Img_W, Img_H });
				model.addAttribute("inputImage", picParam.getFileName());
			} catch (Exception e) {
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
			}
		}

		picParam.setSavefileName(image.SavePicture(request, picParam.getPrefix(), picParam.getBytes(), new String[] { Img_X, Img_Y, Img_W, Img_H }));

		ProdPictures Pic = addPicture("upload/" + picParam.getSavefileName(), picParam.getFileName(), ProdIDD, 0);

		ProdPicturesService.addPictures(Pic);

		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");

	}

	@RequestMapping(value = "/ProdPictureList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView PictureList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Users user = null;

		String WeChatID = request.getParameter("WeChatID");
		user = (Users) session.getAttribute("UserName");
		if (WeChatID == null || WeChatID.isEmpty()) {
			WeChatID = String.valueOf(user.getWeChatID());
		}
		model.addAttribute("WeChatID", WeChatID);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "PictureList");
	}

	@RequestMapping(value = "/deleteProdPictures", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteProdPictures(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				ProdPicturesService.deletePictures(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/deleteProdPrivate", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteProdPrivate(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String WeChat = request.getParameter("pid");

		String[] wids = WeChat.split(",");
		int result = 0;
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				ProdPicturesService.deletePictures(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	/**
	 * 启用和禁用
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/enableProdPrivate", method = RequestMethod.GET)
	public @ResponseBody
	void enableProdPrivate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String pid = request.getParameter("pid");
		String pUrl = request.getParameter("PUrl");
		String Title=request.getParameter("title");
		int enable = Integer.parseInt(request.getParameter("enable"));
		
		Map<String, Object> map = new HashMap<>();
		map.put("ID", pid);
		List<ProdPictures> pictures = ReadProdPicturesService.getPictureList(map);
		ProdPictures pictures2 = pictures.get(0);
		pictures2.setIsEnable(enable);
		pictures2.setType(2);
		if (pUrl != null) {
			pictures2.setPUrl(pUrl);
		}
		if (Title!=null) {
			pictures2.setTitle(Title);
		}
		ProdPicturesService.updatePictures(pictures2);
	}

	@RequestMapping("/uploadpic")
	@ResponseBody
	public Map<String, Object> webPhotoUpload(HttpServletRequest request, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		String ErrMessage = "";
		String Img_X = request.getParameter("Img_X");
		String Img_Y = request.getParameter("Img_Y");
		String Img_W = request.getParameter("Img_W");
		String Img_H = request.getParameter("Img_H");
		String Img_SrcW = request.getParameter("Img_SrcW");
		String Img_SrcH = request.getParameter("Img_SrcH");

		ProdPictures Pic = new ProdPictures();
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名

			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				// 数据封装操作 MultipartFile reqeust
				// 取得当前上传文件的文 件名称

				PictureParam picParam = new PictureParam();
				OperateImage image = new OperateImage();
				// 检测图片是否符合格式
				if (!file.isEmpty()) {
					try {
						picParam = image.getPicutreBytes(file, request.getSession().getServletContext().getRealPath("/"), null, new String[] { Img_X, Img_Y,
								Img_W, Img_H, Img_SrcW, Img_SrcH });
						if (picParam.getErrorMessage() == null || picParam.getErrorMessage().isEmpty()) {
							picParam.setSavefileName(image.SavePicture(request, picParam.getPrefix(), picParam.getBytes(), new String[] { Img_X, Img_Y, Img_W,
									Img_H, Img_SrcW, Img_SrcH }));
						}

					} catch (Exception e) {
						e.printStackTrace();
						String clazz = this.getClass().getName();
						String method = Thread.currentThread().getStackTrace()[1].getMethodName();
						ErrorUtil.HandleError(null, clazz + "." + method, e);
					}
					if (picParam.getErrorMessage() == null || picParam.getErrorMessage().isEmpty()) {
						Users user = (Users) session.getAttribute("UserName");
						Pic = addPicture("upload/" + picParam.getSavefileName(), picParam.getFileName(), (long) 0, ((user != null) ? user.getWeChatID()
								: (long) 0));
						ProdPicturesService.addPictures(Pic);
					}
					ErrMessage = picParam.getErrorMessage();
				}
			}

		}

		if (ErrMessage == null || ErrMessage.isEmpty()) {
			responseMap.put("Status", Pic.getPKID());
		} else {
			responseMap.put("Status", -1);
		}
		responseMap.put("ErrorMsg", ErrMessage);
		return responseMap;
	}

	/**
	 * 列表页
	 */
	@RequestMapping(value = "/ProdPictureHeadList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView ProdPictureHeadList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "PictureHeadList");
	}
}