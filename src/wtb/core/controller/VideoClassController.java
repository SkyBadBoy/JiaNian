package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.KeyWord;
import wtb.core.model.LikeRecord;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.Video;
import wtb.core.model.VideoClass;
import wtb.core.service.VideoClassService;
import wtb.core.model.Users;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.KeywordType;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/VideoClass")
public class VideoClassController  extends BaseController {

	

	@RequestMapping(value = "/addVideoClass", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object>  WeChatPublicResult(HttpServletResponse response,
			HttpSession session, Model model ,HttpServletRequest req) {
		Integer proResult = 0;
		Map<String, Object> responseMap = new HashMap<String, Object>();
		VideoClass VideoClass=new VideoClass();
		String id=req.getParameter("id");
		String title= SmBaseUtil.URLDecoderString(req.getParameter("title"));
		String level="0";
		String memo=SmBaseUtil.URLDecoderString(req.getParameter("memo"));
		String parentID=req.getParameter("parentID");
		String type=req.getParameter("type");
		boolean isNew = false;
		long ProdID = 0;
		if (id!=null && !id.isEmpty() && Long.parseLong(id) > 0) {
			ProdID = Long.parseLong(id);
		} else {
			ProdID = new IdWorker(1, 0).nextId();
			isNew = true;
		}
		
	
		if(title==null || title.isEmpty()){
			responseMap.put("status", false);
			responseMap.put("message", "分类名称不能为空");
			return responseMap;
		}
		if(parentID==null || parentID.isEmpty()){
			parentID="0";
		}
		if(type==null || type.isEmpty()){
			type="0";
		}
		if(parentID.equals("0")){
			level = "0";
		}else{
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", parentID);
			List<VideoClass> lstemp=ReadVideoClassService.getVideoClassList(responseMap);
			if(lstemp.size()>0){
				level =String.valueOf(lstemp.get(0).getLevel()+1);
			}else{
				responseMap.put("status", false);
				responseMap.put("message", "父节点不存在");
				return responseMap;
			}
		}
		if (isNew) {
			VideoClass.setID(ProdID);
			VideoClass.setIsEnd(0);
			VideoClass.setTitle(title);
			VideoClass.setMemo(memo);
			VideoClass.setLevel(Integer.parseInt(level));
			VideoClass.setParentID(Long.parseLong(parentID));
			VideoClass.setType(Integer.parseInt(type));
			VideoClass.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			proResult = VideoClassService.addVideoClass(VideoClass);
		} else {
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", ProdID);
			List<VideoClass> lstemp=ReadVideoClassService.getVideoClassList(responseMap);
			if(lstemp.size()>0){
				lstemp.get(0).setTitle(title);
				lstemp.get(0).setMemo(memo);
				proResult = VideoClassService.updateVideoClass(lstemp.get(0));
			}
			
		}
		responseMap = new HashMap<String, Object>();
		if (proResult > 0) {
			responseMap.put("status", true);
			responseMap.put("message", "添加成功");
			responseMap.put("parentID", parentID);
			
		} else {
			responseMap.put("status", false);
			responseMap.put("message", "添加失败");
		}

		return responseMap;
	}
	
	@RequestMapping(value = "/VideoClassList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath+ "VideoClassList");
	}

	@RequestMapping(value = "/getVideoClassList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getVideoClassList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Name = request.getParameter("searchText");
		String state = request.getParameter("state");
		String parentID = request.getParameter("parentID");
		String isend = request.getParameter("isend");
		
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if (state != null) {
			checkParammap.put(VideoClass.attributeVideoClassStatus, state);
		}else{
			checkParammap.put(VideoClass.attributeVideoClassStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		}
		if (Name != null && !Name.isEmpty()) {
			checkParammap.put(VideoClass.attributeVideoClassTitle, Name);
		}
		if (parentID == null || parentID.isEmpty()) {
			parentID="0";
		}
		checkParammap.put(VideoClass.attributeVideoClassParentID, parentID);
		checkParammap.put("Sina", SmBaseUtil.Random());
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		int count=0;
		List<VideoClass> lswe=new ArrayList<VideoClass>();
		if(isend!=null && !isend.isEmpty() && isend.equals("1")){
			checkParammap.put("BelongID", parentID);
			List<Video> lsvideo = ReadVideoService.getVideoList(checkParammap);
			count=ReadVideoService.getVideoCount(checkParammap);
			for (Video video : lsvideo) {
				VideoClass vc=new VideoClass();
				vc.setID(video.getID());
				vc.setPKID(video.getPKID());
				vc.setLevel(-1);//-1表示视频
				vc.setParentID( Long.parseLong(parentID));
				vc.setTitle(video.getTitle());
				lswe.add(vc);
			}
		}else{
			lswe = ReadVideoClassService.getVideoClassList(checkParammap);
			count=ReadVideoClassService.getVideoClassCount(checkParammap);
		}
		responseMap=new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", count);
		responseMap.put("Status", 1);
		return responseMap;
	}
	
	
	
	@RequestMapping(value = "/getHotVideoBannerList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getHotVideoBannerList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		List<VideoClass> lswe=new ArrayList<VideoClass>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		
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
			lswe.add(vc);
		}
		
		responseMap=new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("count", lswe.size());
		return responseMap;
	}

	@RequestMapping(value = "/deleteVideoClass", method = RequestMethod.GET)
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
				VideoClassService.deleteVideoClass(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	
	/**
	 * 检测该类别是否有子元素,没有子元素可以进行子分类增加 有子元素只能增加视频
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/CheckVideoClassExistChild", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> CheckVideoClassExistChild(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap=null;
		String belongID = request.getParameter("belongID");
		String type = request.getParameter("type");//1表示 检测是否存在子分类  0表示检测是否存在视频
		responseMap = new HashMap<String, Object>();
		int count=0;
		if(type!=null && !type.isEmpty() && type.equals("1")){
			responseMap.put(VideoClass.attributeVideoClassParentID, belongID);
			responseMap.put(VideoClass.attributeVideoClassStatus, SmBaseGlobal.CheckStatus.Effective.getid());
			count = ReadVideoClassService.getVideoClassCount(responseMap);
		}else{
			responseMap.put("BelongID", belongID);
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			count = ReadVideoService.getVideoCount(responseMap);
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", count);
		return responseMap;
	}
	
	@RequestMapping(value = "/AddVideoClassVideo", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> HotVideoClass(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap=null;
		String pid = request.getParameter("pid");
		String belongID = request.getParameter("belongID");
		String type = request.getParameter("type");//1表示设置0表示取消
		String[] wids = pid.split(",");
		int result = 0;
	
		for (String id : wids) {
			if (!id.isEmpty()) {
				responseMap = new HashMap<String, Object>();
				
				responseMap.put("ID", id);
				if(type!=null && type.equals("1")){
					responseMap.put("BelongID", belongID);
				}else{
					responseMap.put("BelongID", 0);
				}
				VideoService.UpdateParentID(responseMap);
				result++;
			}
		}
		if(result>0){
			if(type.equals(1)){
				VideoClassService.UpdateToEnd(Long.parseLong(belongID));
			}else{
				responseMap = new HashMap<String, Object>();
				responseMap.put("BelongID", belongID);
				responseMap.put("Sina", SmBaseUtil.Random());
				responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
				int count = ReadVideoService.getVideoCount(responseMap);
				if(count<=0){
					VideoClassService.CancelToEnd(Long.parseLong(belongID));
				}else{
					VideoClassService.UpdateToEnd(Long.parseLong(belongID));
				}
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	@RequestMapping(value = "/HotVideoClass", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> HotVideo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap=null;
		String WeChat = request.getParameter("id");
		String type = request.getParameter("type");
		String imageUrl = request.getParameter("imageUrl");
		responseMap = new HashMap<String, Object>();
		responseMap.put("ID", WeChat);
		responseMap.put("Type", type);
		responseMap.put("ImageUrl", imageUrl);
		int result=VideoClassService.UpdateToType(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	
}