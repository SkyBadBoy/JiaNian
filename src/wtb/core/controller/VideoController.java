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

import org.apache.http.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
import wtb.core.model.KeyWord;
import wtb.core.model.LikeRecord;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.Video;
import wtb.core.model.Users;
import wtb.smUtil.IdWorker;
import wtb.smUtil.NetUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.KeywordType;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Video")
public class VideoController  extends BaseController {

	@RequestMapping(value = "/addVideo", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(@ModelAttribute("VideoForm") Video Video, BindingResult result, HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		int Status = 0;
		Users user=(Users)session.getAttribute("UserName");
		String PID = req.getParameter("pid");
		String WeChatID = req.getParameter("WeChatID");
		if(WeChatID!=null){
			model.addAttribute("WeChatID", WeChatID);
		}
		if (PID != null && !PID.isEmpty()) {
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("ID", Long.parseLong(PID));
			List<Video> lsps = ReadVideoService.getVideoList(responseMap);
			if (lsps.size() > 0) {
				Video = lsps.get(0);
				ProdPictures lsimg=Video.getImage();
				if(lsimg!=null){
					model.addAttribute("Image", Video.getImage().getUrl());
				}else{
					model.addAttribute("Image", "#");
				}
				model.addAttribute("Content", Video.getContent());
				model.addAttribute("UserType", Video.getUserType());
				//model.addAttribute("WeChat", Video.getWeChat().getCompany());
				if (Video.getWeChat()!=null) {
					model.addAttribute("WeChatID", Video.getWeChat().getID());
				}
				Status = 1;
			} else {
				Status = 404;
			}
		}

		model.addAttribute("VideoForm", Video);
		model.addAttribute("Status", Status);
		if(user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid() && Video.getID()>0){
			return new ModelAndView(SmBaseGlobal.WebViewPath+ "viewVideo");
		}else{
			return new ModelAndView(SmBaseGlobal.WebViewPath+ "addVideo");
		}
	}

	@RequestMapping(value = "/addVideo", method = RequestMethod.POST)
	public ModelAndView WeChatPublicResult(@ModelAttribute("VideoForm") Video Video, 
			BindingResult result, HttpServletResponse response,
			HttpSession session, Model model ,HttpServletRequest req) throws ParseException, IOException {
		Integer proResult = 0;
		Users user=(Users)session.getAttribute("UserName");
		String image=req.getParameter("Imageurl");
		boolean isNew = false;
		long ProdID = 0;
		if (Video.getID() > 0) {
			ProdID = Video.getID();
		} else {
			ProdID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		/**
		 * 接受后台的视频文件
		 */
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;    
		MultipartFile file = multipartRequest.getFile("file");
		if(file.isEmpty()){
			String VID=req.getParameter("VID");
			if(VID==null || VID.isEmpty()){
				result.rejectValue("Url", "misFormat", "请输入正确的视频地址,当前只支持腾讯视频和新浪视频");
			}
		}else{
			//{"Status":true,"Data":{"url":"http://wenews.oss-cn-hangzhou.aliyuncs.com/Video/2017-07-07/7e675925-0e02-4690-8d26-7b8ca4ce7b04.mp4","fileTime":2,"fileSize":45209.0}}
			com.alibaba.fastjson.JSONObject jsonObject=NetUtil.doPostFile(SmBaseUtil.getProjectBaseUrlNoProject(req)+SmBaseGlobal.VideoService, null, file);
			boolean status=jsonObject.getBoolean("Status");
			if (status) {
				com.alibaba.fastjson.JSONObject data=jsonObject.getJSONObject("Data");
				String url=data.getString("url");
				long fileTime=data.getLong("fileTime");
				double fileSize=data.getDouble("fileSize");
				Video.setUrl(url);
				Video.setFileTime(fileTime);
				Video.setFileSize(fileSize);
			}
		}
		
		if (Video.getTitle().isEmpty()) {
			result.rejectValue("Title", "misFormat", "视频标题不能为空");
		}
		if (Video.getContent().isEmpty()) {
			result.rejectValue("Content", "misFormat", "视频简介不能为空");
		}else{
			model.addAttribute("Content", Video.getContent());
		}
		if(Video.getWeChatID()>0){
			model.addAttribute("WeChatID", Video.getWeChatID());
		}
		if (!result.hasErrors()) {
				String UserType=req.getParameter("UserType");
				Video.setUserType(Integer.parseInt(UserType));
				if (Integer.parseInt(UserType)==SmBaseGlobal.UserType.Admin.getid()) {
					Video.setAreaID(0);
					Video.setAreaName("微新闻社");
					Video.setUserID(0);
					Video.setVID(Video.getUrl());
				}
				String UserID=req.getParameter("UserID");//学生的id或者是学校的ID
				if (Integer.parseInt(UserType)==SmBaseGlobal.UserType.School.getid()) {
					List<Region> regions=RegionService.getRegionByIDList(UserID);
					if (regions.size()>0) {
							Video.setUserID(Long.parseLong(UserID));
							Video.setAreaID(Long.parseLong(UserID));
							Video.setAreaName(regions.get(0).getName());
					}
				}
			
				if (Integer.parseInt(UserType)==SmBaseGlobal.UserType.Student.getid()) {
					Map<String, Object> map=new HashMap<>();
					map.put("ID", UserID);
					List<Students> students=ReadStudentsService.getStudentsList(map);
					if (students.size()>0) {
							Video.setUserID(Long.parseLong(UserID));
							if (students.get(0).getAreaID()!=null) {
								Video.setAreaID(Long.parseLong(students.get(0).getAreaID()));
								Video.setAreaName(students.get(0).getArea().getName());
							}else{
								Video.setAreaID(0);
								Video.setAreaName("微新闻社");
							}
					}
				}
				
				Video.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				Video.setWeChatID(user.getWeChatID());
				if(Video.getImageID()<=0){
					if(image ==null || image.isEmpty()){
						image=SmBaseUtil.getProjectBaseUrl(req)+SmBaseGlobal.DefaultVideoPicture;
					}
					ProdPictures prod=new ProdPictures();
					prod.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					prod.setID(SmBaseUtil.CreateNewID());
					prod.setCreateTime(new Date());
					prod.setProductID(ProdID);
					prod.setRealUrl(image);
					prod.setUrl(image);
					prod.setType(1);
					ProdPicturesService.addPictures(prod);
					Video.setImageID(prod.getID());
				}
				Video=checkIsActicity(Video);
				if (isNew) {
					Video.setID(ProdID);
					proResult = VideoService.addVideo(Video);
				} else {
					proResult = VideoService.updateVideo(Video);
				}
				if (proResult > 0) {
					model.addAttribute("content", req.getContextPath()+ "/Video/VideoList?WeChatID="+String.valueOf(Video.getWeChatID()));
					return new ModelAndView(SmBaseGlobal.WebViewPath+ "success");
				} else {
					Video.setID(0);
					result.rejectValue("Title", "misFormat", "保存失败,请重试");
				}

		}

		if (result.hasErrors()) {
			model.addAttribute("VideoForm", Video);// 把accountVo对象返回到页面，这样不至于表单被清空了
			return new ModelAndView(SmBaseGlobal.WebViewPath+ "addVideo");
		}

		return new ModelAndView(SmBaseGlobal.WebViewPath+ "addVideo");
	}
	/**
	 * 检测活动稿件
	 * @param notices
	 */
	public Video checkIsActicity(Video video){
		
		Map<String, Object> maps = new HashMap<>();
		maps.put(KeyWord.attributeKeyWordTypeID, KeywordType.ActivityChar.getCode());
		maps.put("status", SmBaseGlobal.CheckStatus.Effective.getid());
		List<KeyWord> keyWords = KeyWordService.queryKeyWord(maps);
		for (KeyWord item : keyWords) {
			if (video.getTitle().contains(item.getKeyWord())) {
				video.setIsActivity(SmBaseGlobal.CheckStatus.Effective.getid());
				video.setVoteID(item.getVoteID());
				break;
			}
		}
		return video;
	}
	@RequestMapping(value = "/VideoList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Users user=null;
		user=(Users)session.getAttribute("UserName");
		String WeChatID = request.getParameter("WeChatID");
		
		if(WeChatID==null ||WeChatID.isEmpty()){
			WeChatID=String.valueOf(user.getWeChatID());
		}
		model.addAttribute("WeChatID",WeChatID);
		return new ModelAndView(SmBaseGlobal.WebViewPath+ "VideoList");
	}

	@RequestMapping(value = "/getVideoList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getVideoList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Users user=null;
		user=(Users)session.getAttribute("UserName");
		String Name = request.getParameter("searchText");
		String state = request.getParameter("state");
		String WeChatID = request.getParameter("WeChatID");
		
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if (state != null) {
			checkParammap.put("Status", state);
		}else{
			checkParammap.put("Status", 1);
		}
		if (Name != null && !Name.isEmpty()) {
			checkParammap.put("Title", Name);
		}
		if(user.getLevel()==SmBaseGlobal.LevelStatus.TeacherManage.getid() || user.getLevel()==SmBaseGlobal.LevelStatus.ParsonManage.getid()){
			checkParammap.put("WeChatID",Long.parseLong(WeChatID));
		}else if(user.getLevel()==SmBaseGlobal.LevelStatus.AreaManage.getid()){
			checkParammap.put("AreaID",user.getAreaID());
		}
		
		checkParammap.put("HotSoft", 1);
		checkParammap.put("Sina",SmBaseUtil.Random());
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		List<Video> lswe = ReadVideoService.getVideoList(checkParammap);
		for (int i=0;i<lswe.size();i++) {
			if(lswe.get(i).getUserType()==2){
				lswe.get(i).setTempUrl("<a target='_blank' href='"+ lswe.get(i).getUrl() +"'>"+ lswe.get(i).getUrl() +"</a>");
			}else{
				lswe.get(i).setTempUrl(lswe.get(i).getUrl());
			}
		}
		int count=ReadVideoService.getVideoCount(checkParammap);
		responseMap=new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", count);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/deleteVideo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap=null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		int result = 0;
	
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				responseMap.put("ModifyTime", new Date());
				VideoService.deleteVideo(responseMap);
				

				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	@RequestMapping(value = "/HotVideo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> HotVideo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap=null;
		String WeChat = request.getParameter("pid");
		String type = request.getParameter("type");
		String[] wids = WeChat.split(",");
		int result = 0;
	
		for (String id : wids) {
			if (!id.isEmpty()) {
				if(type!=null && type.equals("1")){
					VideoService.SetHotVideo(Long.parseLong(id));
				}else{
					VideoService.CancelHotVideo(Long.parseLong(id));
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
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
	@RequestMapping(value = "/likeVideo", method = RequestMethod.GET)
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
			if (likecount.size() <= 0 && type.equals("0")) {
				VideoService.UpLikeCount(Long.parseLong(nid));
				LikeRecord likeRecord = new LikeRecord();
				likeRecord.setID(SmBaseUtil.CreateNewID());
				likeRecord.setCreateTime(new Date());
				likeRecord.setModifyTime(new Date());
				likeRecord.setNoticesID(Long.parseLong(nid));
				likeRecord.setStatus(1);
				if (user != null) {
					likeRecord.setUserID(String.valueOf(user.getID()));
				} else {
					likeRecord.setUserID(SmBaseUtil.getIpAddr(request));
				}
				LikeRecordService.addLikeRecord(likeRecord);
			} else if (type.equals("1") && likecount.size() > 0) {
				VideoService.CancelLikeCount(Long.parseLong(nid));
				LikeRecordService.deleteLikeRecord(likecount.get(0).getID());
			}

			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", "true");
			responseMap.put("Message", "处理成功");
		}
		return responseMap;
	}
	

	
	
}