package wtb.core.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.Comment;
import wtb.core.model.GameQuestion;
import wtb.core.model.Notices;
import wtb.core.model.QuestionLimit;
import wtb.core.model.QuestionRecord;
import wtb.core.model.QuestionStat;
import wtb.core.model.SerchParam;
import wtb.core.model.Students;
import wtb.core.model.Version;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.WeMoneyClassify;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;

@Controller
@RequestMapping("Version")
public class VersionController extends BaseController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/addVersion", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addGameQuestion(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = request.getParameter("pid");
		String content =  request.getParameter("content");// 评论内容
		String updateType =  request.getParameter("updateType");// 评论内容
		String number =  request.getParameter("number");
		String filePath = request.getParameter("filePath");
		String clientType = request.getParameter("clientType");
		boolean isnew=false;
		// 0为游客
		Version Version = new Version();
		Long InID = SmBaseUtil.CreateNewID();
		if(content==null){
			content="";
		}
		if(pid!=null && !pid.isEmpty() && !pid.equals("0")){
			responseMap = new HashMap<String, Object>();
			responseMap.put("ID", pid);
			List<Version> temp=ReadVersionService.getVersionList(responseMap);
			if(temp.size()>0){
				Version=temp.get(0);
			}else{
				pid="0";
			}
		}
		if(pid==null || pid.isEmpty()|| pid.equals("0")){
			isnew=true;
			
			Version.setID(InID);
			Version.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			responseMap.put("ClientType", clientType);
			int maxNumber=ReadVersionService.getVersionMaxNumber(responseMap);
			Version.setBuild(String.valueOf(maxNumber+1));
			
		}
		Version.setClientType(Integer.parseInt(clientType));
		Version.setContent(SmBaseUtil.URLDecoderString(content));
		Version.setDeleted(1);
		Version.setFilePath(filePath);
		Version.setNumber(number);
		Version.setReleaseTime(SmBaseGlobal.sdfDateTime.format(new Date()));
		Version.setUpdateType(Integer.parseInt(updateType));
		int i =0;
		if(isnew){
			i=VersionService.addVersion(Version);
		}else{
			i=VersionService.updateVersion(Version);
		}
		if (i == 1) {
			
			responseMap.put("status", true);
			responseMap.put("Message", "添加成功");
		} else {
			responseMap.put("status", false);
			responseMap.put("Message", "添加失败");
		}
		return responseMap;
	}



	@RequestMapping(value = "/deleteVersion", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		
		String[] wids = pid.split(",");
		int result = 0;
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID",  Long.parseLong(id));
				int count=VersionService.deleteVersion(responseMap);
				result++;
			}
		}

		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	
	@RequestMapping(value = "/getVersionList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getVersionList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException,
			ParseException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		String Type = request.getParameter("Type");
		if (Type!=null && Type!="") {
			responseMap.put("ClientType", Type);
		}
		responseMap.put("Sina", SmBaseUtil.Random());
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<Version> Version = ReadVersionService.getVersionList(responseMap);
		for (int i = 0; i < Version.size(); i++) {
			if(Version.get(i).getClientType()==SmBaseGlobal.ClientType.Android.getid()){
				Version.get(i).setClientTypeMemo("安卓");
			}else{
				Version.get(i).setClientTypeMemo("苹果");
			}
			if(Version.get(i).getUpdateType()==SmBaseGlobal.UpdateType.NoUpdate.getid()){
				Version.get(i).setUpdateTypeMemo("不升级");
			}else if(Version.get(i).getUpdateType()==SmBaseGlobal.UpdateType.Update.getid()){
				Version.get(i).setUpdateTypeMemo("普通升级");
			}else{
				Version.get(i).setUpdateTypeMemo("强制升级");
			}
		}
		int Prodcount = ReadVersionService.getVersionCount(responseMap);
		
		responseMap = new HashMap<String, Object>();
		responseMap.put("Data", Version);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	
	@RequestMapping(value = "/VersionList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "VersionList");
	}
	

}
