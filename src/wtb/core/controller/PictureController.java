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
import wtb.core.model.Users;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.OperateImage;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Picture")
public class PictureController extends BaseController {

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
	public Pictures addPicture(String Path, String RealName, long weChatID) {
		long PicID = new IdWorker(1, 0).nextId();
		Pictures pic = new Pictures();
		pic.setCreateTime(new Date());
		pic.setID(PicID);
		pic.setModifyTime(new Date());
		pic.setRealUrl(RealName);
		pic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		pic.setUrl(Path);
		pic.setWeChatID(weChatID);
		return pic;
	}

	@RequestMapping(value = "/addPicture", method = RequestMethod.GET)
	public ModelAndView addPictures(@ModelAttribute("PictureForm") Pictures picture, HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");
	}

	@RequestMapping(value = "/addPicture", method = RequestMethod.POST)
	public ModelAndView addPicturePost(@ModelAttribute("PictureForm") Pictures picture, HttpServletRequest request, HttpServletResponse response,
			BindingResult result, @RequestParam("file") MultipartFile file, @RequestParam(value = "Img_X", required = false) String Img_X, @RequestParam(
					value = "Img_Y", required = false) String Img_Y, @RequestParam(value = "Img_W", required = false) String Img_W, @RequestParam(
					value = "Img_H", required = false) String Img_H, Model model, HttpSession session) throws Exception {
		Users user = null;
		PictureParam picParam = new PictureParam();
		OperateImage image = new OperateImage();
		// 检测图片是否符合格式
		if (!file.isEmpty()) {
			try {
				picParam = image.getPicutreBytes(file, request.getSession().getServletContext().getRealPath("/"), null, new String[] { Img_X, Img_Y, Img_W,
						Img_H });
				model.addAttribute("inputImage", picParam.getFileName());
				if (picParam.getErrorMessage() != null && !picParam.getErrorMessage().isEmpty()) {
					result.rejectValue("ID", "misFormat", picParam.getErrorMessage());
				}
			} catch (Exception e) {
				String clazz = this.getClass().getName();
				String method = Thread.currentThread().getStackTrace()[1].getMethodName();
				ErrorUtil.HandleError(null, clazz + "." + method, e);
				result.rejectValue("ID", "misFormat", "图片文件 " + file.getOriginalFilename() + " 上传失败=> " + e.getMessage());
			}
		} else {
			result.rejectValue("ID", "misFormat", "图片文件不能为空.");
		}
		if (!result.hasErrors()) {
			picParam.setSavefileName(image.SavePicture(request, picParam.getPrefix(), picParam.getBytes(), new String[] { Img_X, Img_Y, Img_W, Img_H }));
			PictureController pc = new PictureController();
			user = (Users) session.getAttribute("UserName");
			Pictures Pic = pc.addPicture("upload/" + picParam.getSavefileName(), picParam.getFileName(), user.getWeChatID());

			int proResult = PicturesService.addPictures(Pic);
			if (proResult > 0) {
				model.addAttribute("content", request.getContextPath() + "/Picture/PictureList?WeChatID=" + user.getWeChatID());
				return new ModelAndView(SmBaseGlobal.WebViewPath + "success");
			} else {
				result.rejectValue("ID", "misFormat", "保存失败,请重试");
			}
		}
		if (result.hasErrors()) {
			model.addAttribute("PictureForm", picture);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addPicture");

	}

	@RequestMapping(value = "/PictureList", method = RequestMethod.GET)
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

	@RequestMapping(value = "/getPictureList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPictureList(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session)
			throws UnsupportedEncodingException {
		Users user = null;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		user = (Users) request.getSession().getAttribute("UserName");
		String searchText = request.getParameter("searchText");
		if (searchText != null && !searchText.isEmpty()) {
			searchText = new String(searchText.getBytes("ISO-8859-1"), "UTF-8");
		}

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
		if (user.getLevel() == SmBaseGlobal.LevelStatus.ParsonManage.getid()) {
			checkParammap.put("WeChatID", user.getWeChatID());
		}

		checkParammap.put("Status", 1);
		List<Pictures> lswe = PicturesService.getPictureEffList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String url = SmBaseUtil.getClickImageHtml(lswe.get(i).getUrl());
			lswe.get(i).setImageURL(url);
			System.out.println(lswe.get(i).getID() + "---------------" + i);
		}
		int lsweCount = PicturesService.getPictureEffCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", lsweCount);
		return responseMap;
	}

	@RequestMapping(value = "/getPictureByIDList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getPictureByIDList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		String id = request.getParameter("id");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();

		if (id != null) {
			checkParammap.put("ID", id);
		}

		checkParammap.put("Status", 1);
		List<Pictures> lswe = PicturesService.getPictureEffList(checkParammap);
		for (int i = 0; i < lswe.size(); i++) {
			String url = SmBaseUtil.getClickImageHtml(lswe.get(i).getUrl());
			lswe.get(i).setImageURL(url);
			System.out.println(lswe.get(i).getID() + "---------------" + i);
		}
		int lsweCount = PicturesService.getPictureEffCount(checkParammap);
		responseMap.put("data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", lsweCount);
		return responseMap;
	}

	@RequestMapping(value = "/deletePicture", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		int result = 0;

		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				PicturesService.deletePictures(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

}