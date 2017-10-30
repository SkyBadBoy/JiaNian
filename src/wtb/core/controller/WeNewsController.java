package wtb.core.controller;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jna.platform.win32.WinDef.LONG;
import com.tencent.mm.sdk.modelbiz.AddCardToWXCardPackage.Req;

import wtb.core.model.Advert;
import wtb.core.model.JsonModel;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Region;
import wtb.core.model.Students;
import wtb.core.model.Video;
import wtb.core.model.VideoClass;
import wtb.core.model.Vote;
import wtb.core.model.WeChatGroup;
import wtb.core.model.WeChatPublic;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;


/**
 * 所有PC端页面请求都从这边过
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/WeNews")
public class WeNewsController extends BaseController {

	@Autowired
	private RegionController RegionController;
	/**个人中心 用户信息 
	 * @throws IOException */
	@RequestMapping(value = "/UserInfo", method = RequestMethod.GET)
	public String UserInfo(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if(user==null){
			response.sendRedirect(SmBaseUtil.getProjectBaseUrl(request)+"WeNews/Register");
			return SmBaseGlobal.WeNewsPCViewPath + "WeNewsHome";
		}
		if(user.getAreaID()==null || user.getAreaID().isEmpty() || user.getAreaID().equals("0")){
			response.sendRedirect(SmBaseUtil.getProjectBaseUrl(request)+"WeNews/UserEdit");
			return SmBaseGlobal.WeNewsPCViewPath + "WeNewsUserEdit";
		}
		Map<String,Object> map=new HashMap<>();
		map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		if (user!=null) {
			map.put("UsersID", user.getID());
		}
		List<Notices> notices=ReadNoticesService.getReadNoticesList(map);
		for (int i = 0; i < notices.size(); i++) {
			notices.get(i).setContent(SmBaseUtil.StripHT(notices.get(i).getContent()));
			map=new HashMap<>();
			map.put("UserID", user.getID());
			map.put("NoticesID", notices.get(i).getID());
			int islikeflag=ReadLikeRecordService.getLikeRecordCount(map);
			if (islikeflag>0) {
				//点赞接口0喜欢     1取消    所以这边刚刚要相反
				notices.get(i).setIsLike(islikeflag);
			}
			if(user.getUnionID()!=null){
				map=new HashMap<>();
				map.put("NoticeID", notices.get(i).getID());
				map.put("UnionID", user.getUnionID());
				int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
				if(voteFlag>0){
					notices.get(i).setIsVote(1);
				}else{
					notices.get(i).setIsVote(0);
				}
			}else{
				map=new HashMap<>();
				map.put("NoticeID", notices.get(i).getID());
				map.put("UnionID",SmBaseUtil.getLocalMac(request));
				int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
				if(voteFlag>0){
					notices.get(i).setIsVote(1);
				}else{
					notices.get(i).setIsVote(0);
				}
			}
		}
		model.addAttribute("NewsData", notices);
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsUserInfo";
	}
	
	/**最新投稿 
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/News", method = RequestMethod.GET)
	public String News(Model model,HttpServletRequest request) throws SocketException, UnknownHostException {

		/**标记*/
		model.addAttribute("flag", "News");
		
		/**返回的是首页的数据*/
		WeNewsPCModel(model,request);

		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsNews";
	}
	
	/**最新投稿 
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/VideoNews", method = RequestMethod.GET)
	public String VideoNews(Model model,HttpServletRequest request) throws SocketException, UnknownHostException {
		String parentID=request.getParameter("parentID");
		if(parentID==null || parentID.isEmpty()){
			parentID = "0";
		}
		/**标记*/
		model.addAttribute("flag", "VideoNews");
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
				if(video.getImage()!=null && video.getImage().getUrl()!=null){
					vc.setImageUrl(video.getImage().getUrl().split(",")[0]);
					lswe.add(vc);
				}
			}
		
		model.addAttribute("ImageCount", lswe.size());
		model.addAttribute("ImageList", lswe);

		model.addAttribute("ImageCount", lswe.size());
		model.addAttribute("ImageList", lswe);
		model.addAttribute("parentID", parentID);
		/**返回的是首页的数据*/
		VideoWeNewsPCModel(model,request);

		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsVideoNews";
	}
	
	/**回顾新闻 
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/ReviewNews", method = RequestMethod.GET)
	public String ReviewNews(Model model,HttpServletRequest request) throws SocketException, UnknownHostException {
		model.addAttribute("flag", "ReviewNews");
		Map<String, Object> map=null;
		/**返回的是首页的数据*/
		WeNewsPCModel(model,request);
		
		
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsReviewNews";
	}
	
	/**投票专题
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/WeNewsSeminar", method = RequestMethod.GET)
	public String WeNewsSeminar(Model model,HttpServletRequest request) throws SocketException, UnknownHostException {
		model.addAttribute("flag", "WeNewsSeminar");
		String vote = request.getParameter("vote");
		/**返回的是首页的数据*/
		WeNewsPCModelWithActivity(model,request,vote);
		model.addAttribute("VoteID",vote);
		
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsSeminar";
	}
	
	
	/**学生空间 */
	@RequestMapping(value = "/Student", method = RequestMethod.GET)
	public String Student(Model model) {
		model.addAttribute("flag", "Student");
		
		Map<String, Object> map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.put("isStu", 1);
		map.put("OrderNoticesCount", 1);
		List<Students> students=ReadStudentsService.getStudentsList(map);
		for (int i = 0; i < students.size(); i++) {
			String habit="该同学还没有留下脚印哦~~";
    		if(students.get(i).getHabit()==null || students.get(i).getHabit().isEmpty()){
    			students.get(i).setHabit(habit); 
    		}
    		
    		String school="该同学还没有设置学校哦~~";
    		if(students.get(i).getSchool()==null || students.get(i).getSchool().isEmpty()){
    			students.get(i).setSchool(school); 
    		}
		}
		model.addAttribute("StudentData", students);
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsStudent";
	}
	
	/**学校空间 */
	@RequestMapping(value = "/School", method = RequestMethod.GET)
	public String School(Model model) {
		
		model.addAttribute("flag", "School");
		
		Map<String, Object> map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		//map.put("Bind", 1);//已嫁接
		//map.put("ID",796996461031067647L);
		map.put("Rand", SmBaseUtil.Random());//已嫁接
		List<WeChatPublic> weChatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
		for (int i = 0; i < weChatPublics.size(); i++) {
			String Addres=weChatPublics.get(i).getAddress()==null?"":weChatPublics.get(i).getAddress();
			weChatPublics.get(i).setAddress(SmBaseUtil.Address(weChatPublics.get(i).getAreaID(),ReadRegionService)+Addres);
		}
		model.addAttribute("SchoolData", weChatPublics);
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsSchool";
	}
	
	/**光荣榜 */
	@RequestMapping(value = "/Glory", method = RequestMethod.GET)
	public String Glory(Model model) {
		model.addAttribute("flag", "Glory");
		
		Map<String, Object> map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		List<Notices> notices = ReadNoticesService.getNoticesRankingList(map);
		for (int i = 0; i < notices.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("ID", notices.get(i).getPublishUser());
			List<Students> students = ReadStudentsService.getStudentsList(map);
			if (students.size() > 0) {
				notices.get(i).setName(students.get(0).getName());
			} else {
				notices.remove(notices.get(i));
			}
		}
		model.addAttribute("NoticeCountData", notices);
		
		map = new HashMap<String, Object>();
		map.put("limit", 10);
		map.put("start", 0);
		List<Notices> School = ReadNoticesService.getNoticesSchoolRankingList(map);
		model.addAttribute("SchoolData", School);
		
		
		map = new HashMap<String, Object>();
		map.put("limit", 10);
		map.put("start", 0);
		map.put("startTime", SmBaseUtil.getFirstDay());
		map.put("endTime", SmBaseUtil.getLastDay());
		map.put("LikeOrder", 1);
		List<Notices> LikeNotices = ReadNoticesService.getReadNoticesList(map);
		model.addAttribute("LikeData", LikeNotices);
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsGlory";
	}

	
	/**光荣榜List */
	@RequestMapping(value = "/GloryList", method = RequestMethod.GET)
	public String GloryList(Model model,HttpServletRequest request) {
		model.addAttribute("flag", "Glory");
		String type=request.getParameter("type");//0本周阅读量  1点赞排行榜 
		String rank=request.getParameter("rank");//0本周阅读量  1点赞排行榜 
		String voteID= request.getParameter("voteid");
		model.addAttribute("type", type);
		model.addAttribute("rank", rank);
		model.addAttribute("voteid", voteID);
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsGloryList";
	}
	/**支社*/
	@RequestMapping(value = "/Office", method = RequestMethod.GET)
	public String Office(Model model) {
		model.addAttribute("flag", "Office");
		
		Map<String, Object> map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		//map.put("Bind", 1);//已嫁接
		//map.put("ID",796996461031067647L);
		map.put("Rand", SmBaseUtil.Random());//已嫁接
		List<WeChatPublic> weChatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
		for (int i = 0; i < weChatPublics.size(); i++) {
			String Addres=weChatPublics.get(i).getAddress()==null?"":weChatPublics.get(i).getAddress();
			weChatPublics.get(i).setAddress(SmBaseUtil.Address(weChatPublics.get(i).getAreaID(),ReadRegionService)+Addres);
		}
		model.addAttribute("SchoolData", weChatPublics);
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsOffice";
	}
	/**注册*/
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String Register() {
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsRegister";
	}
	/**找回密码*/
	@RequestMapping(value = "/FundPwd", method = RequestMethod.GET)
	public String FundPwd() {
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsFundPWD";
	}
	
	/**找回密码*/
	@RequestMapping(value = "/EditPwd", method = RequestMethod.GET)
	public String EditPwd(Model model,HttpServletRequest request) {
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if (user.getPhone()!=null) {
			model.addAttribute("Phone", user.getPhone());
		}
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsEditPWD";
	}
	
	/**用户
	 * @throws IOException */
	@RequestMapping(value = "/User", method = RequestMethod.GET)
	public ModelAndView User(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
		String uid=request.getParameter("Uid");
		if(uid==null || uid.isEmpty()){
			uid = "0";
		}
		Map<String,Object> query=new HashMap<>();
		query.put("ID", uid);
		List<Students> students= ReadStudentsService.getStudentsList(query);
		if(students.size()<=0){
			model.addAttribute("Student", new Students());
			response.sendRedirect(request.getContextPath() + "/include/404.html");
			return new ModelAndView(SmBaseGlobal.WeNewsPCViewPath + "WeNewsUser");
		}
		model.addAttribute("Student", students.get(0));
		Map<String,Object> map=new HashMap<>();
		
		map.put("limit", 3);
		map.put("start", 0);
		map.put("ClickCountOrder", 1);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Notices> noticeLike=ReadNoticesService.getReadNoticesList(map);
		map.put("UsersID", uid);
		List<Notices> noticeLike2=ReadNoticesService.getReadNoticesList(map);
		if(noticeLike.size()<=0){
			map=new HashMap<>();
			map.put("limit", 3);
			map.put("start", 0);
			map.put("ClickCountOrder", 1);
			noticeLike=ReadNoticesService.getReadNoticesList(map);
		}
		if(noticeLike2.size()<=0){
			map=new HashMap<>();
			map.put("limit", 3);
			map.put("start", 0);
			map.put("ClickCountOrder", 1);
			map.put("UsersID", uid);
			noticeLike2=ReadNoticesService.getReadNoticesList(map);
		}
		
		model.addAttribute("Notice1", noticeLike);
		model.addAttribute("Notice2", noticeLike2);
		model.addAttribute("Notice1Count", noticeLike.size() );
		model.addAttribute("Notice2Count", noticeLike2.size());
		model.addAttribute("uid", uid);
		return new ModelAndView(SmBaseGlobal.WeNewsPCViewPath + "WeNewsUser");
	}
	
	/**用户
	 * @throws IOException */
	@RequestMapping(value = "/UserEdit", method = RequestMethod.GET)
	public String UserEdit(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) throws IOException {
		Students user =(Students)request.getSession().getAttribute("StudentName");
		if(user==null){
			user=new Students();
			response.sendRedirect(SmBaseUtil.getProjectBaseUrl(request)+"WeNews/Register");
		}
		model.addAttribute("Name", user.getName());
		model.addAttribute("ImageUrl", user.getImageUrl());
		model.addAttribute("Age", user.getAge());
		model.addAttribute("Sex", user.getSex());
		model.addAttribute("Phone", user.getPhone());
		model.addAttribute("ParentName", user.getParentName());
		model.addAttribute("Grade", user.getGrade());
		model.addAttribute("Habit", user.getHabit());
		model.addAttribute("Email", user.getEmail());
		
		if(user.getAreaID()!=null && !user.getAreaID().isEmpty() && !user.getAreaID().equals("null") && !user.getAreaID().equals("0") ){
			model=RegionController.getRegionParams(request,ReadRegionService,model,session,user.getAreaID());
		}
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsUserEdit";
	}
	
	/**用户
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 * @throws ParseException */
	@RequestMapping(value = "/NewsDetails", method = RequestMethod.GET)
	public String NewsDetails(HttpServletRequest request,Model model) throws SocketException, UnknownHostException, ParseException {
		String NoticeID=request.getParameter("NoticeID");
		Map<String, Object> map=new HashMap<>();
		map.put("Rand", SmBaseUtil.Random());
		map.put("ID", NoticeID);
		List<Notices> Notices=ReadNoticesService.getReadNoticesList(map);
		if (Notices.size()>0) {
			Notices notices=SmBaseUtil.getPCWeNewsData(request, Notices.get(0), ReadLikeRecordService, VoteRecordsService) ;
			Notices.get(0).setClickCount(Notices.get(0).getClickCount()+1);
			ProductController controller=new ProductController();
			controller.addNoticesClickCount(request, String.valueOf(Notices.get(0).getID()), Notices.get(0),ReadNoticesService,ReadStudentsService,ClickListService,IntegrationService,StudentsService,WeMoneyService,NoticesService,WeMoneyRecordService,ReadWeMoneyService,VoteService,ReadWeMoneyRecordService,ReadCommentService,ReadVoteService);
			model.addAttribute("Notice", notices);
			model.addAttribute("NoticeCount", 1);
			if(Notices.get(0).getContentType()!=1){
				Notices.get(0).setContent(SmBaseUtil.huanhang(Notices.get(0).getContent()));
			}
			model.addAttribute("Content", Notices.get(0).getContent());
			
			

		}else{
			model.addAttribute("NoticeCount", 0);
		}
		WeNewsPCModel(model,request);
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsDetails";
	}
	
	/**视频详情
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 * @throws ParseException */
	@RequestMapping(value = "/VideoDetails", method = RequestMethod.GET)
	public String VideoDetails(HttpServletRequest request,Model model) throws SocketException, UnknownHostException, ParseException {
		String VideoID=request.getParameter("VideoID");
		Map<String, Object> map=new HashMap<>();
		map.put("Rand", SmBaseUtil.Random());
		map.put("ID", VideoID);
		List<Video> videos=ReadVideoService.getVideoList(map);
		if (videos.size()>0) {
			String basePath=SmBaseUtil.getProjectBaseUrl(request);
			Video video=videos.get(0);
			String name="";
			String headImageUrl="";
			String ClickImageUrl="";
			String SchoolUrl="";
			String SchoolName="";
			/**超级管理员*/
			if (video.getUserType()==SmBaseGlobal.UserType.Admin.getid()) {
				name="校播科技";
				headImageUrl=SmBaseUtil.getProjectBaseUrl(request)+"images/logo.png";
				ClickImageUrl="#";//管理员的就不用掉链接
				SchoolUrl="#";//管理员的就不用掉链接
				SchoolName=SmBaseGlobal.CompanyName;
			}
			/**学校*/
			if (video.getUserType()==SmBaseGlobal.UserType.School.getid()) {
				name=video.getAreaName();
				SchoolName=name;
				ClickImageUrl=basePath+"WeNews/SchoolInfo?SchoolID="+video.getAreaID();
				SchoolUrl=basePath+"WeNews/SchoolInfo?SchoolID="+video.getAreaID();
				map=new HashMap<>();
				map.put("AreaID",video.getAreaID());
				List<WeChatPublic> weChatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
				if (weChatPublics.size()>0) {
					WeChatPublic weChatPublic=weChatPublics.get(0);
					if (weChatPublic.getLogo()!=null) {
						headImageUrl=weChatPublic.getLogo().getUrl();
					}else{
						headImageUrl=basePath+SmBaseGlobal.SchoolDefaultImageUrl;
					}
				}else{
					headImageUrl=basePath+SmBaseGlobal.SchoolDefaultImageUrl;
				}
			}
			/**学生*/
			if (video.getUserType()==SmBaseGlobal.UserType.Student.getid()) {
				ClickImageUrl=basePath+"WeNews/User?Uid="+video.getUserID();
				SchoolUrl=basePath+"WeNews/SchoolInfo?SchoolID="+video.getAreaID();
				SchoolName=video.getAreaName();
				map=new HashMap<>();
				map.put("ID",video.getUserID() );
				List<Students> students=ReadStudentsService.getStudentsList(map);
				if (students.size()>0) {
					name=students.get(0).getName();
					if (students.get(0).getImageUrl()!=null) {
						headImageUrl=students.get(0).getImageUrl().split(",")[0];
					}else{
						headImageUrl=basePath+SmBaseGlobal.UserDefaultImageUrl;
					}
				}else{
					name="游客";
					headImageUrl=basePath+SmBaseGlobal.UserDefaultImageUrl;
				}
			}
			
			Students user = (Students) request.getSession().getAttribute("StudentName"); 
			String userid=SmBaseUtil.getIpAddr(request);;
			if(user!=null){
				userid=String.valueOf(user.getID());
			}	
			map=new HashMap<>();
			map.put("UserID", userid);
			map.put("NoticesID",String.valueOf(video.getID()));
			int islikeflag=ReadLikeRecordService.getLikeRecordCount(map);
			int isLike=0;
			if (islikeflag>0) {
				//点赞接口0喜欢     1取消    所以这边刚刚要相反
				isLike=1;
			}
			int IsVote=0;
			if(user!=null){
				if(user.getUnionID()!=null){
					map=new HashMap<>();
					map.put("NoticeID", video.getID());
					map.put("UnionID", user.getUnionID());
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						IsVote=1;
					}else{
						IsVote=0;
					}
				}else{
					map=new HashMap<>();
					map.put("NoticeID", video.getID());
					map.put("UnionID",SmBaseUtil.getLocalMac(request));
					int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
					if(voteFlag>0){
						IsVote=1;
					}else{
						IsVote=0;
					}
				}
			}else{
				map=new HashMap<>();
				map.put("NoticeID", video.getID());
				map.put("UnionID",SmBaseUtil.getLocalMac(request));
				int voteFlag=VoteRecordsService.getVoteRecordsCount(map);
				if(voteFlag>0){
					IsVote=1;
				}else{
					IsVote=0;
				}
			}
			
			int Clickresult = SmBaseUtil.getClickInfo(request, String.valueOf(videos.get(0).getID()), ClickListService);
			if (Clickresult>0) {
				map=new HashMap<>();
				map.put("ID", videos.get(0).getID());
				VideoService.UpClickCount(map);
			}
			//model.addAttribute("", );
			model.addAttribute("name", name);//姓名
			model.addAttribute("headImageUrl", headImageUrl);//头像
			model.addAttribute("ClickImageUrl", ClickImageUrl);//头像点击的链接
			model.addAttribute("SchoolUrl", SchoolUrl);
			model.addAttribute("SchoolName", SchoolName);
			model.addAttribute("isLike", isLike);
			model.addAttribute("IsVote", IsVote);
			model.addAttribute("video", video);
			model.addAttribute("VideoCount", 1);
			model.addAttribute("VideoType", video.getType());
			model.addAttribute("Content", SmBaseUtil.huanhang(video.getContent()));
			if(video.getType()!=0){
				model.addAttribute("VID", video.getUrl());
			}else{
				model.addAttribute("VID", video.getVID());
			}
		}else{
			model.addAttribute("VideoCount", 0);
		}
		WeNewsPCModel(model,request);
		return SmBaseGlobal.WeNewsPCViewPath + "WeVideoDetails";
	}
	
	
	/**搜索
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/SearchKeyWord", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> SearchKeyWord(HttpServletRequest request,Model model) throws SocketException, UnknownHostException {
		List<JsonModel> result=new ArrayList<JsonModel>();
		Map<String, Object> map=new HashMap<>();
		String Search=SmBaseUtil.URLDecoderString(request.getParameter("query"));
		if(Search!=null){
			Search = Search.trim();
		}
		
		if(Search==null || Search.isEmpty()){
			map.put("Status", false);
			map.put("data", result);
			return map;
		}
		//Title
		map=new HashMap<>();
		map.put("Title", Search);
		map.put("start", 0);
		map.put("limit", 3);
		List<Notices> notices=ReadNoticesService.getReadNoticesList(map);
		for (int i = 0; i < notices.size(); i++) {
			String title=notices.get(i).getTitle();
			String content=notices.get(i).getAuthor();
			if(content==null){
				content="";
			}
			if(title!=null && title.length()>10){
				title=title.substring(0,10)+"...";
			}
			if(content!=null && content.length()>10){
				content=content.substring(0,10)+"...";
			}
			
			JsonModel jsonModel=new JsonModel();
			jsonModel.setName("【新闻】"+title);
			jsonModel.setValue2(content);
			result.add(jsonModel);
		}
		map=new HashMap<>();
		map.put("check", Search);
		map.put("start", 0);
		map.put("limit", 3);
		List<Students> Students=ReadStudentsService.getStudentsList(map);
		boolean isexist=false;
		for(Notices notices2 :notices){
			 isexist=false;
			for(Students students2:Students){
				if(notices2.getPublishUser().equals(String.valueOf(students2.getID()))){
					isexist=true;;
				}
			}
			if(!isexist){
				map=new HashMap<>();
				map.put("ID", notices2.getPublishUser());
				map.put("start", 0);
				map.put("limit", 1);
				Students.addAll(ReadStudentsService.getStudentsList(map));
			}
		}
		for (int i1 = 0; i1 < Students.size(); i1++) {
			String title=Students.get(i1).getName();
			String content=Students.get(i1).getSchool();
			if(content==null){
				content="";
			}
			if(title!=null && title.length()>10){
				title=title.substring(0,10)+"...";
			}
			if(content!=null && content.length()>10){
				content=content.substring(0,10)+"...";
			}
			JsonModel jsonModel=new JsonModel();
			jsonModel.setName("【小编】"+title);
			jsonModel.setValue2(content);
			result.add(jsonModel);
		}
		
		
		map=new HashMap<>();
		map.put("Company", Search);
		map.put("start", 0);
		map.put("limit", 3);
		List<WeChatPublic> WeChatPublic=ReadWeChatPublicService.getWeChatPublicList(map);
		for(Notices notices2 :notices){
			 isexist=false;
			 for(WeChatPublic students2:WeChatPublic){
					if(notices2.getAreaID()==Long.parseLong(students2.getAreaID())){
						isexist=true;;
					}
				}
				if(!isexist){
					map=new HashMap<>();
					map.put("AreaID", notices2.getAreaID());
					map.put("start", 0);
					map.put("limit", 1);
					WeChatPublic.addAll(ReadWeChatPublicService.getWeChatPublicList(map));
				}
		}
		for (int i2 = 0; i2 < WeChatPublic.size(); i2++) {
			String title=WeChatPublic.get(i2).getCompany();
			String content="";
			if(title!=null && title.length()>10){
				title=title.substring(0,10)+"...";
			}
			if(content!=null && content.length()>10){
				content=content.substring(0,10)+"...";
			}
			
			JsonModel jsonModel=new JsonModel();
			jsonModel.setName("【学校】"+title);
			jsonModel.setValue2(content);
			result.add(jsonModel);
		}
		map=new HashMap<>();
		map.put("Status", true);
		map.put("data", result);
		return map;
	}
	/**搜索
	 * @throws UnknownHostException 
	 * @throws SocketException */
	@RequestMapping(value = "/Search", method = RequestMethod.GET)
	public String Search(HttpServletRequest request,Model model) throws SocketException, UnknownHostException {
		
		String Search=SmBaseUtil.URLDecoderString(request.getParameter("query"));
		if(Search!=null){
			Search = Search.trim();
		}
		//Title
		Map<String, Object> map=new HashMap<>();
		map.put("Title", Search);
		map.put("start", 0);
		map.put("limit", 10);
		List<Notices> notices=ReadNoticesService.getReadNoticesList(map);
		for (int i = 0; i < notices.size(); i++) {
			notices.set(i, notices.get(i));
			map=new HashMap<>();
		}
		map=new HashMap<>();
		map.put("check", Search);
		map.put("start", 0);
		map.put("limit", 10);
		List<Students> Students=ReadStudentsService.getStudentsList(map);
		boolean isexist=false;
		for(Notices notices2 :notices){
			 isexist=false;
			for(Students students2:Students){
				if(notices2.getPublishUser().equals(String.valueOf(students2.getID()))){
					isexist=true;;
				}
			}
			if(!isexist){
				map=new HashMap<>();
				map.put("ID", notices2.getPublishUser());
				map.put("start", 0);
				map.put("limit", 1);
				Students.addAll(ReadStudentsService.getStudentsList(map));
			}
		}
		
		
		map=new HashMap<>();
		map.put("Company", Search);
		map.put("start", 0);
		map.put("limit", 10);
		List<WeChatPublic> WeChatPublic=ReadWeChatPublicService.getWeChatPublicList(map);
		for(Notices notices2 :notices){
			 isexist=false;
			 for(WeChatPublic students2:WeChatPublic){
					if(notices2.getAreaID()==Long.parseLong(students2.getAreaID())){
						isexist=true;;
					}
				}
				if(!isexist){
					map=new HashMap<>();
					map.put("AreaID", notices2.getAreaID());
					map.put("start", 0);
					map.put("limit", 1);
					WeChatPublic.addAll(ReadWeChatPublicService.getWeChatPublicList(map));
				}
		}
		model.addAttribute("NewsDataCount", notices.size());
		if(notices.size()<=0){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			map.putAll(SmBaseUtil.getWeekDay());
			map.put("ClickCountOrder", 1);
			notices = ReadNoticesService.getReadNoticesList(map);
			if(notices.size()<=0){
				map = new HashMap<>();
				map.put("limit", 10);
				map.put("start", 0);
				map.put("ClickCountOrder", 1);
				notices = ReadNoticesService.getReadNoticesList(map);
			}
			
		}
		model.addAttribute("NewsData", notices);
		
		model.addAttribute("Student", Students);
		model.addAttribute("School", WeChatPublic);
		model.addAttribute("StudentCount", Students.size());
		model.addAttribute("SchoolCount", WeChatPublic.size());
		model.addAttribute("Search", Search);
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsSearch";
	}
	
	/**学校信息*/
	@RequestMapping(value = "/SchoolInfo", method = RequestMethod.GET)
	public String SchoolInfo(HttpServletRequest request,Model model) {
		model.addAttribute("flag", "School");
		String SchoolID=request.getParameter("SchoolID");
		Map<String, Object > map=new HashMap<>();
		map.put("AreaID", SchoolID);
		List<WeChatPublic> weChatPublics= ReadWeChatPublicService.getWeChatPublicList(map);
		if(weChatPublics.size()<=0){
			List<Region> parent= RegionService.getRegionByIDList(SchoolID);
			if(parent.size()>0){
				WeChatPublic weChatPublic=new WeChatPublic();
				weChatPublic.setID(SmBaseUtil.CreateNewID());
				weChatPublic.setWeChat(parent.get(0).getCode());
				weChatPublic.setCompany(parent.get(0).getName());
				weChatPublic.setAreaID(parent.get(0).getID());
				weChatPublic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weChatPublic.setBind(1);
				WeChatPublicService.addWeChatPublic(weChatPublic);
				weChatPublics.add(weChatPublic);
			}
		}
		for (int i = 0; i < weChatPublics.size(); i++) {
			String Addres=weChatPublics.get(i).getAddress()==null?"":weChatPublics.get(i).getAddress();
			weChatPublics.get(i).setAddressStr(SmBaseUtil.Address(weChatPublics.get(i).getAreaID(),ReadRegionService)+Addres);
		}
		if(weChatPublics.size()>0){
			model.addAttribute("School", weChatPublics.get(0));
		}
		
		
		 map=new HashMap<>();
		 map.put("AreaID",SchoolID);
		 map.put("start", 0);
		 map.put("limit", 20);
		 map.put("orderNoticeCount", 1);
		 List<Students> students= ReadStudentsService.getStudentsList(map);
		 if (students.size()>0) {
			 model.addAttribute("Students", students);
		}
		
		
		 map=new HashMap<>();
		 map.put("AreaID", SchoolID);
		 map.put("start", 0);
		 map.put("limit", 20);
		 map.put("ClickCountOrder", 1);
		 List<Notices> Notices= ReadNoticesService.getReadNoticesList(map);
		 if(Notices.size()>0){
			 model.addAttribute("Notices", Notices);
		 }
	
		
		
		return SmBaseGlobal.WeNewsPCViewPath + "WeNewsSchoolInfo";
	}
	
	/**学校信息*/
	@RequestMapping(value = "/getSchoolNews", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getSchoolNews(HttpServletRequest request,Model model) {
		
		String SchoolID=request.getParameter("SchoolID");
		Map<String, Object > map=new HashMap<>();
		 map.put("AreaID", SchoolID);
		 map.putAll(SmBaseUtil.AddPageParam(request));
		 map.put("ClickCountOrder", 1);
		 List<Notices> Notices= ReadNoticesService.getReadNoticesList(map);
		 map = new HashMap<String, Object>();
			map.put("data", Notices);
			map.put("Status", 1);
		
		
		return map;
	}
	
	
	
	public Model WeNewsPCModel(Model model,HttpServletRequest request){
		Map<String, Object> map=null;
		String schoolType="week";
		String readType="week";
		String noticeType="week";
		request.setAttribute("DefaultSchoolImageUrl", SmBaseUtil.getProjectBaseUrl(request)+"images/wenewspc/school.png");
		/** 前3个学校*/
		map = new HashMap<>();
		map.put("limit", 3);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Notices> notices = ReadNoticesService.getNoticesSchoolRankingList(map);
		if(notices.size()<=0){
			map = new HashMap<>();
			map.put("limit", 3);
			map.put("start", 0);
			notices = ReadNoticesService.getNoticesSchoolRankingList(map);
			schoolType = "all";
		}
		String imageurl="";
		if(notices.size()>0){
			map = new HashMap<>();
			map.put("AreaID", notices.get(0).getAreaID());
			List<WeChatPublic>chatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
			if(chatPublics.size()>0){
				if(chatPublics.get(0).getBanner()!=null && chatPublics.get(0).getBanner().getUrl()!=null && !chatPublics.get(0).getBanner().getUrl().isEmpty()){
					imageurl=chatPublics.get(0).getBanner().getUrl().split(",")[0];
					
				}
			}
		}
		
		/**新闻阅读量*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		map.put("ClickCountOrder", 1);
		List<Notices> Notices = ReadNoticesService.getNoticesReadRankingList(map);
		if(Notices.size()<=0 ){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			map.put("ClickCountOrder", 1);
			Notices = ReadNoticesService.getNoticesReadRankingList(map);
			readType="all";
		}
		//model.addAttribute("ReadNotices", Notices);
		
		/**本周投稿排行榜*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Notices> NoticesCount = ReadNoticesService.getNoticesRankingList(map);
		if(NoticesCount.size()<=0){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			NoticesCount = ReadNoticesService.getNoticesRankingList(map);
			noticeType="all";
		}
		//model.addAttribute("LikeNotices", LikeNotices);
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if(user!=null){
			map = new HashMap<>();
			map.put("RankUsersID", user.getID());
			map.putAll(SmBaseUtil.getWeekDay());
			List<Notices> NoticesForMy = ReadNoticesService.getNoticesRankingList(map);
			if(NoticesForMy.size()>0){
				model.addAttribute("MyStudent", NoticesForMy.get(0).getStudent());
				if(NoticesForMy.get(0).getCount()>0){
					model.addAttribute("Mylevel", NoticesForMy.get(0).getLevel());
				}else{
					model.addAttribute("Mylevel", "未入榜");
				}
				model.addAttribute("Mycount", NoticesForMy.get(0).getCount());
			}else{
				model.addAttribute("MyStudent", user);
				model.addAttribute("Mylevel", "未入榜");
				model.addAttribute("Mycount",0);
			}
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCSchoolBanner.getid());
		List<Advert> Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("School_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCHomeLogo.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("HomeLogo_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCBannerLeft.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("PCBannerLeft_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCBannerRight.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("PCBannerRight_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCBannerQR.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("PCBannerQR_Adverts", Adverts.get(0));
		}
		map = new HashMap<>();
		map.put("NoticesCount", NoticesCount);
		map.put("ReadNotices", Notices);
		map.put("SchoolRank", notices);
		map.put("SchoolImageUrl", imageurl);
		map.put("schoolType", schoolType);
		map.put("readType", readType);
		map.put("noticeType", noticeType);
		return model.addAllAttributes(map);
	}
	
	
	
	public Model WeNewsPCModelWithActivity(Model model,HttpServletRequest request,String VoteID){
		Map<String, Object> map=null;
		String schoolType="week";
		String readType="week";
		String noticeType="week";
		request.setAttribute("DefaultSchoolImageUrl", SmBaseUtil.getProjectBaseUrl(request)+"images/wenewspc/school.png");
		/** 前3个学校*/
		map = new HashMap<>();
		map.put("limit", 3);
		map.put("start", 0);
		map.put("VoteID", VoteID);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Notices> notices = ReadNoticesService.getNoticesSchoolRankingList(map);
		if(notices.size()<=0){
			map = new HashMap<>();
			map.put("limit", 3);
			map.put("start", 0);
			map.put("VoteID", VoteID);
			notices = ReadNoticesService.getNoticesSchoolRankingList(map);
			schoolType = "all";
		}
		String imageurl="";
		if(notices.size()>0){
			map = new HashMap<>();
			map.put("AreaID", notices.get(0).getAreaID());
			List<WeChatPublic>chatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
			if(chatPublics.size()>0){
				if(chatPublics.get(0).getBanner()!=null && chatPublics.get(0).getBanner().getUrl()!=null && !chatPublics.get(0).getBanner().getUrl().isEmpty()){
					imageurl=chatPublics.get(0).getBanner().getUrl().split(",")[0];
					
				}
			}
		}
		
		/**新闻阅读量*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.put("VoteID", VoteID);
		map.putAll(SmBaseUtil.getWeekDay());
		map.put("ClickCountOrder", 1);
		List<Notices> Notices = ReadNoticesService.getNoticesReadRankingList(map);
		if(Notices.size()<=0 ){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			map.put("ClickCountOrder", 1);
			map.put("VoteID", VoteID);
			Notices = ReadNoticesService.getNoticesReadRankingList(map);
			readType="all";
		}
		//model.addAttribute("ReadNotices", Notices);
		
		/**本周投稿排行榜*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.put("VoteID", VoteID);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Notices> NoticesCount = ReadNoticesService.getNoticesRankingList(map);
		if(NoticesCount.size()<=0){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			map.put("VoteID", VoteID);
			NoticesCount = ReadNoticesService.getNoticesRankingList(map);
			noticeType="all";
		}
		//model.addAttribute("LikeNotices", LikeNotices);
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if(user!=null){
			map = new HashMap<>();
			map.put("RankUsersID", user.getID());
			map.put("VoteID", VoteID);
			map.putAll(SmBaseUtil.getWeekDay());
			List<Notices> NoticesForMy = ReadNoticesService.getNoticesRankingList(map);
			if(NoticesForMy.size()>0){
				model.addAttribute("MyStudent", NoticesForMy.get(0).getStudent());
				if(NoticesForMy.get(0).getCount()>0){
					model.addAttribute("Mylevel", NoticesForMy.get(0).getLevel());
				}else{
					model.addAttribute("Mylevel", "未入榜");
				}
				model.addAttribute("Mycount", NoticesForMy.get(0).getCount());
			}else{
				model.addAttribute("MyStudent", user);
				model.addAttribute("Mylevel", "未入榜");
				model.addAttribute("Mycount",0);
			}
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCSchoolBanner.getid());
		List<Advert> Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("School_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCHomeLogo.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("HomeLogo_Adverts", Adverts.get(0));
		}
		map = new HashMap<>();
		map.put("NoticesCount", NoticesCount);
		map.put("ReadNotices", Notices);
		map.put("SchoolRank", notices);
		map.put("SchoolImageUrl", imageurl);
		map.put("schoolType", schoolType);
		map.put("readType", readType);
		map.put("noticeType", noticeType);
		return model.addAllAttributes(map);
	}
	
	public Model VideoWeNewsPCModel(Model model,HttpServletRequest request){
		Map<String, Object> map=null;
		String schoolType="week";
		String readType="week";
		String noticeType="week";
		request.setAttribute("DefaultSchoolImageUrl", SmBaseUtil.getProjectBaseUrl(request)+"images/wenewspc/school.png");
		/** 前3个学校*/
		map = new HashMap<>();
		map.put("limit", 3);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Video> notices = ReadVideoService.getVideoSchoolRankingList(map);
		if(notices.size()<=0){
			map = new HashMap<>();
			map.put("limit", 3);
			map.put("start", 0);
			notices = ReadVideoService.getVideoSchoolRankingList(map);
			schoolType = "all";
		}
		String imageurl="";
		if(notices.size()>0){
			map = new HashMap<>();
			map.put("AreaID", notices.get(0).getAreaID());
			List<WeChatPublic>chatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
			if(chatPublics.size()>0){
				if(chatPublics.get(0).getBanner()!=null && chatPublics.get(0).getBanner().getUrl()!=null && !chatPublics.get(0).getBanner().getUrl().isEmpty()){
					imageurl=chatPublics.get(0).getBanner().getUrl().split(",")[0];
					
				}
			}
		}
		
		/**新闻阅读量*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		map.put("ClickCountOrder", 1);
		List<Video> Notices = ReadVideoService.getVideoReadRankingList(map);
		if(Notices.size()<=0 ){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			map.put("ClickCountOrder", 1);
			Notices = ReadVideoService.getVideoReadRankingList(map);
			readType="all";
		}
		//model.addAttribute("ReadNotices", Notices);
		
		/**本周投稿排行榜*/
		map = new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		map.putAll(SmBaseUtil.getWeekDay());
		List<Video> NoticesCount = ReadVideoService.getVideoRankingList(map);
		if(NoticesCount.size()<=0){
			map = new HashMap<>();
			map.put("limit", 10);
			map.put("start", 0);
			NoticesCount = ReadVideoService.getVideoRankingList(map);
			noticeType="all";
		}
		//model.addAttribute("LikeNotices", LikeNotices);
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if(user!=null){
			map = new HashMap<>();
			map.put("RankUsersID", user.getID());
			map.putAll(SmBaseUtil.getWeekDay());
			List<Video> NoticesForMy = ReadVideoService.getVideoRankingList(map);
			if(NoticesForMy.size()>0){
				model.addAttribute("MyStudent", NoticesForMy.get(0).getStudent());
				if(NoticesForMy.get(0).getCount()>0){
					model.addAttribute("Mylevel", NoticesForMy.get(0).getLevel());
				}else{
					model.addAttribute("Mylevel", "未入榜");
				}
				model.addAttribute("Mycount", NoticesForMy.get(0).getCount());
			}else{
				model.addAttribute("MyStudent", user);
				model.addAttribute("Mylevel", "未入榜");
				model.addAttribute("Mycount",0);
			}
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCSchoolBanner.getid());
		List<Advert> Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("School_Adverts", Adverts.get(0));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("IsEffect", 1);
		responseMap.put(Advert.attributeStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		responseMap.put(Advert.attributeType, SmBaseGlobal.AdvertType.PCHomeLogo.getid());
		Adverts = ReadAdvertService.getAdvertList(responseMap);
		if (Adverts.size() > 0) {
			model.addAttribute("HomeLogo_Adverts", Adverts.get(0));
		}
		
		map = new HashMap<>();
		map.put("NoticesCount", NoticesCount);
		map.put("ReadNotices", Notices);
		map.put("SchoolRank", notices);
		map.put("SchoolImageUrl", imageurl);
		map.put("schoolType", schoolType);
		map.put("readType", readType);
		map.put("noticeType", noticeType);
		return model.addAllAttributes(map);
	}

	
	
	
}
