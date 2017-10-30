package wtb.core.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;
import wtb.core.model.Notices;
import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;
import wtb.core.model.Students;
import wtb.core.model.Video;
import wtb.core.model.WeChatPublic;
import wtb.smUtil.NetUtil;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * 所有PC端请求的数据
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/WeNews")
public class WeNewsNoticeController extends BaseController {

	

	@RequestMapping(value = "/getWeNewNoticeList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeNewNoticeList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException, SocketException, UnknownHostException, ParseException {
		Map<String, Object> map=new HashMap<>();
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		String Search=request.getParameter("Search");
		String VoteID=request.getParameter("VoteID");
		String type = request.getParameter("type");
		map=new HashMap<>();
		map.putAll(SmBaseUtil.AddPageParam(request));
		if (user!=null) {
			map.put("AreaID", user.getAreaID());
		}
		if(type==null || type.isEmpty()){
			type = "0";
		}
		if (Integer.parseInt(type)==0) {
			map.put("ModifyTime", 1);
		}else{
			map.put("ClickCountOrder", 1);
		}
		if (VoteID!=null&&VoteID!="") {
			map.put("VoteID", VoteID);
		}
		if (Search!=null&&Search!="") {
			map.put("Title", Search);
		}
		map.put("IsTop", 1);
		List<Notices> notices=ReadNoticesService.getReadNoticesList(map);

		for (int i = 0; i < notices.size(); i++) {
			notices.set(i, SmBaseUtil.getPCWeNewsData(request, notices.get(i) , ReadLikeRecordService, VoteRecordsService));
			notices.get(i).setContent(SmBaseUtil.StripHT(notices.get(i).getContent()));
		}
		

		map = new HashMap<String, Object>();
		map.put("data", notices);
		map.put("Status", 1);
		return map;
	}
	
	@RequestMapping(value = "/getGloryNoticeList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getGloryNoticeList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException, SocketException, UnknownHostException {
		Map<String, Object> map=new HashMap<>();
		String type=request.getParameter("type");
		map=new HashMap<>();
		map.putAll(SmBaseUtil.AddPageParam(request));
		map.put("startTime", SmBaseUtil.getWeek(0) + " 00:00:00");
		map.put("endTime", SmBaseUtil.getWeek(1)+ " 23:59:59");
		if(type!=null){
			if(Integer.parseInt(type)==0){
				map.put("ClickCountOrder", 1);
			}else{
				map.put("LikeOrder", 1);
			}
		}
		List<Notices> notices=ReadNoticesService.getReadNoticesList(map);
		map = new HashMap<String, Object>();
		map.put("data", notices);
		map.put("Status", 1);
		return map;
	}

	@RequestMapping(value = "/getWeNewStudentList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeNewStudentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map=new HashMap<>();
		map=new HashMap<>();
		map.putAll(SmBaseUtil.AddPageParam(request));
		String areaID=request.getParameter("AreaID");
		if (areaID!=null&&areaID!=""&&areaID!="0") {
			map.put("AreaID", areaID);
		}
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
		map = new HashMap<String, Object>();
		map.put("data", students);
		map.put("Status", 1);
		return map;
	}
	
	@RequestMapping(value = "/phoneGetToupiaoQr", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> phoneGetToupiaoQr(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map=new HashMap<>();
		String pkid=request.getParameter("pkid");
		String type=request.getParameter("type");
		String url="http://wenews.top/WeChatAPI/wechat.do?getQRCode={\"id\":\""+ pkid +"\";\"type\":\""+ type +"\"}";
		JSONObject json = NetUtil.doGet(url);
		map = new HashMap<String, Object>();
		map.put("data", json.getString("data"));
		map.put("Status", 1);
		return map;
	}
	
	@RequestMapping(value = "/getWeNewWeChatPublicList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeNewWeChatPublicList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> map=new HashMap<>();
		map.put("limit", 10);
		map.put("start", 0);
		//map.put("Bind", 1);//已嫁接
		map.putAll(SmBaseUtil.AddPageParam(request));
		map.put("Rand", SmBaseUtil.Random());//已嫁接
		String AreaID=request.getParameter("AreaID");
		if (AreaID!=null&&AreaID!="") {
			map.put("query", AreaID);
		}
		List<WeChatPublic> weChatPublics=ReadWeChatPublicService.getWeChatPublicList(map);
		for (int i = 0; i < weChatPublics.size(); i++) {
			String Addres=weChatPublics.get(i).getAddress()==null?"":weChatPublics.get(i).getAddress();
			try {
				weChatPublics.get(i).setAddress(SmBaseUtil.Address(weChatPublics.get(i).getAreaID(),ReadRegionService)+Addres);
			} catch (Exception e) {
				System.err.println(i);
				System.err.println(weChatPublics.get(i).getCompany());
				System.err.println("-------------------------------------------");
				weChatPublics.get(i).setAddress(weChatPublics.get(i).getCompany());
			}
			
		}
		map = new HashMap<String, Object>();
		map.put("data", weChatPublics);
		map.put("Status", 1);
		return map;
	}

	@RequestMapping(value = "/putUPloadPic", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> putUPloadPic(MultipartFile file,HttpServletRequest request,HttpSession session) throws IOException {
		Map<String, Object> map=new HashMap<>();
		String type=request.getParameter("Type");//类型上传的1是头像还是新闻的照片
		String name=file.getOriginalFilename();
		String prefix=name.substring(name.lastIndexOf(".")+1);
		String path = SmBaseGlobal.UploadAliYunFileService(file.getInputStream(), prefix, "Images");
		Long id=SmBaseUtil.CreateNewID();
		Students user = (Students) request.getSession().getAttribute("StudentName"); 
		if (type==null||type=="") {
			type="0";
		}
		if (Integer.parseInt(type)==1) {
			Pictures pic=new Pictures();
		 	pic.setID(id);
			pic.setCreateTime(new Date());
			pic.setPKID(String.valueOf(pic.getID()));
			pic.setModifyTime(new Date());
			pic.setRealUrl(name);
			pic.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			pic.setUrl(path);
			pic.setWeChatID(user.getID());
			
			PicturesService.addPictures(pic);
			user.setImageUrl(path);
			user.setImageID(id);
			StudentsService.updateStudents(user);
		}else{
			ProdPictures pic = new ProdPictures();
			pic.setCreateTime(new Date());
			pic.setID(id);
			pic.setPKID(String.valueOf(pic.getID()));
			pic.setModifyTime(new Date());
			pic.setRealUrl(name);
			pic.setStatus(1);
			pic.setUrl(path);
			pic.setProductID(0);
			pic.setWeChatID(user.getID());
			ProdPicturesService.addPictures(pic);
			String PicID=(String) session.getAttribute("PicID");
			if (PicID!=null) {
				PicID+=","+id;
			}else{
				PicID=","+id;
			}
			session.removeAttribute("PicID");
			request.getSession().setAttribute("PicID", PicID);
			System.out.println("PicID"+PicID);
		}
		map.put("ID", id);
		map.put("ImageUrl", path);
		return map;
	}


	
}
