package wtb.core.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.Comment;
import wtb.core.model.Notices;
import wtb.core.model.Students;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;

@Controller
@RequestMapping("Comment")
public class CommentController extends BaseController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/addComment", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addComment(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = request.getParameter("pid");
		String content =  request.getParameter("content");// 评论内容
		String type =  request.getParameter("type");// 评论内容
		String name = null;
		String img = null;
		String basePath = SmBaseUtil.getProjectBaseUrl(request);
		// 0为游客
		Comment comment = new Comment();
		Date date = new Date();
		Long InID = SmBaseUtil.CreateNewID();
		if(pid==null || pid.isEmpty()){
			pid="0";
		}
		if(content==null){
			content="";
		}
		comment.setID(InID);
		comment.setNoticesID(Long.parseLong(pid));
		comment.setCreateTime(sdf.format(date));
		comment.setContent(SmBaseUtil.URLDecoderString(content));
		comment.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		if(type!=null && type.equals("1")){
			comment.setType(SmBaseGlobal.CommentType.NewComment.getid());
		}else if(type!=null && type.equals("2")){
			comment.setType(SmBaseGlobal.CommentType.VideoComment.getid());
		}
		Students user = (Students) request.getSession().getAttribute("StudentName");
		if (user != null) {
			comment.setUserID(String.valueOf(user.getID()));
			name = user.getName();
			if (user.getImage() != null) {
				img = user.getImage().getUrl().split(",")[0];
			} else {
				if (user.getImageUrl() != null) {
					img = user.getImageUrl();
				} else {
					img = basePath + SmBaseGlobal.UserDefaultImageUrl;
				}
			}
		} else {
			user = new Students();
			user.setID(0);
			comment.setUserID("0");
			name = "游客";
			img = basePath + SmBaseGlobal.UserDefaultImageUrl;
		}
		int i=0;
		try{
			 i = CommentService.addComment(comment);
		}catch (Exception e) {
			i = CommentService.addComment(comment);
		}
		if (i == 1) {
			if(type!=null && type.equals("1")){
				NoticesService.UpDateCommentCount(Long.parseLong(pid));
				NoticesController notices=new NoticesController();
				Notices noticesObj = ReadNoticesService.getNoticesByID(Long.parseLong(pid));
				if(notices.CheckIsOutAutoRewardWeMoney(WeMoneyRecordService,NoticesService,noticesObj,ReadNoticesService)){
					SmBaseUtil.AddAutoRewardWeMoneyForRule(WeMoneyClassify.Comment, WeMoneyRecordService, WeMoneyService, ReadWeMoneyService, ReadVoteService, ReadCommentService, noticesObj, NoticesService, user, ReadWeMoneyRecordService);
				}
			}else if(type!=null && type.equals("2")){
				VideoService.UpCommentCount(Long.parseLong(pid));
			}
			responseMap.put("status", true);
			responseMap.put("name", name);
			responseMap.put("img", img);
			responseMap.put("time", SmBaseUtil.format(date));
			responseMap.put("Message", "评论成功");
		} else {
			responseMap.put("status", false);
			responseMap.put("Message", "服务器繁忙,请稍后再试");
		}
		return responseMap;
	}

	@RequestMapping(value = "/getCommentList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String nid = request.getParameter("nid");
		if(nid==null || nid.isEmpty()){
			nid="0";
		}
		responseMap.put("NoticesID", nid);
		// responseMap.put("AreaID", AreaID);
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Comment> comments = ReadCommentService.getCommentList(responseMap);
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if (Long.parseLong(comment.getUserID()) == 0) {
				comment.setUserName("游客");
				comment.setUserImage(basePath + "img/tx_default.jpg");
			} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", comment.getUserID());
				List<Students> students = ReadStudentsService.getStudentsList(responseMap);
				Students user = new Students();
				if (students.size() > 0) {
					user = students.get(0);
				} else {
					user.setName("游客");
				}
				String img;
				comment.setUserName(user.getName());
				if (user.getImage() != null) {
					img = user.getImage().getUrl().split(",")[0];
				} else {
					if (user.getImageUrl() != null) {
						img = user.getImageUrl();
					} else {
						img = basePath + "img/tx_default.jpg";
					}
				}
				comment.setUserImage(img);
			}
			comment.setFromtTime(SmBaseUtil.format(sdf.parse(comment.getCreateTime())));
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("CommentData", comments);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/getCommentPCList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentPCList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException,
			ParseException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		String basePath = SmBaseUtil.getProjectBaseUrl(request);
		String nid = request.getParameter("nid");
		if (nid!=null&&nid!="") {
			responseMap.put("NoticesID", nid);
		}
		// responseMap.put("AreaID", AreaID);
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Comment> comments = ReadCommentService.getCommentList(responseMap);
		int Prodcount = ReadCommentService.getCommentCount(responseMap);
		for (int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			if (Long.parseLong(comment.getUserID()) == 0) {
				comment.setUserName("游客");
				comment.setUserImage(basePath + SmBaseGlobal.UserDefaultImageUrl);
			} else {
				responseMap = new HashMap<String, Object>();
				responseMap.put("ID", comment.getUserID());
				List<Students> students = ReadStudentsService.getStudentsList(responseMap);
				if(students.size()>0){
					Students user = students.get(0);
					comment.setUserName(user.getName());
				}else{
					comment.setUserName("游客");
					comment.setUserImage(basePath + SmBaseGlobal.UserDefaultImageUrl);
				}
				
			}
			comment.setFromtTime(SmBaseUtil.format(sdf.parse(comment.getCreateTime())));
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("Data", comments);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	@RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String type = request.getParameter("type");
		
		String[] wids = pid.split(",");
		String[] types = type.split(",");
		int result = 0;
		int step=0;
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				int count=CommentService.deleteComment(responseMap);
				if(count>0){
					if(types.length>=step && types[step].equals("1")){
						NoticesService.CancelCommentCount(Long.parseLong(id));
					}else if(types.length>=step && types[step].equals("2")){
						VideoService.CancelCommentCount(Long.parseLong(id));
					}
				}
				result++;
			}
			step++;
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/CommentList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "CommentList");
	}

}
