package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
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


import wtb.core.model.Activity;
import wtb.core.model.ActivityPart;
import wtb.core.model.KeyWord;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.Vote;
import wtb.core.model.WeChatPublic;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/Vote")
public class VoteController extends BaseController {

	
	@RequestMapping(value = "/VoteList", method = RequestMethod.GET)
	public ModelAndView VoteList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "VoteList");
	}
	
	@RequestMapping(value = "/getVoteList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getVoteList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String searchText = request.getParameter("searchText");
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		if (searchText != null && !searchText.isEmpty()) {
			searchText = SmBaseUtil.URLDecoderString(searchText);
			checkParammap.put(Vote.attributeVoteTitle, searchText);
		}
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.put(Vote.attributeVoteStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		if (searchText != null && !searchText.isEmpty()) {
			checkParammap.put("Title", searchText);
		}
		List<Vote> lswe = ReadVoteService.getVoteList(checkParammap);

		int Prodcount = ReadVoteService.getVoteCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}
	
	
	@RequestMapping(value = "/addVote", method = RequestMethod.GET)
	public ModelAndView addWeChatPublic(HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		
		if (req.getParameter("IsNew").equals("true")) {
			long VoteID=SmBaseUtil.CreateNewID();
			model.addAttribute("VoteID", String.valueOf(VoteID));
			model.addAttribute("IsNew",true);
		}else{
			String voteID=req.getParameter("pid");//voteID
			Map<String, Object> map=new HashMap<>();
			map.put("ID", voteID);
			List<Vote> votes=ReadVoteService.getVoteList(map);
			model.addAttribute(Vote.attributeVoteTitle, votes.get(0).getTitle());
			model.addAttribute(Vote.attributeVoteStartTime, votes.get(0).getStartTime().substring(0,10));
			model.addAttribute(Vote.attributeVoteEndTime, votes.get(0).getEndTime().substring(0, 10));
			model.addAttribute(Vote.attributeHeadUrl, votes.get(0).getHeadUrl());
			model.addAttribute(Vote.attributeFootUrl, votes.get(0).getFootUrl());
			model.addAttribute(Vote.attributeMiddleUrl, votes.get(0).getMiddleUrl());
			model.addAttribute("VoteID", voteID);
			model.addAttribute("IsNew",false);
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "addVote");
	}
	
	
	@RequestMapping(value = "/addVote", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> addVotePost(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String IsNew=request.getParameter("IsNew");//true 表示新增，false表示修改
		String title=request.getParameter("title");
		String StartTime=request.getParameter("StartTime");
		String EndTime=request.getParameter("EndTime");
		String HeadUrl=request.getParameter("HeadUrl");
		String MiddleUrl=request.getParameter("MiddleUrl");
		String FootUrl=request.getParameter("FootUrl");
		String ErrorMessage="";
		if(title==null||title==""||title=="null"||title=="undefined")
		{
			ErrorMessage="标题不能为空";
		}
		if(StartTime==null||StartTime==""||StartTime=="null"||StartTime=="undefined")
		{
			ErrorMessage="开始时间不能为空";
		}
		if(StartTime==null||StartTime==""||StartTime=="null"||StartTime=="undefined")
		{
			ErrorMessage="结束时间不能为空";
		}
		if (ErrorMessage=="") {
			Vote vote=new Vote();
			vote.setID(Long.parseLong(request.getParameter("VoteID")));
			vote.setTitle(SmBaseUtil.URLDecoderString(title));
			vote.setStartTime(StartTime);
			vote.setEndTime(EndTime);
			if(HeadUrl!=null && !HeadUrl.isEmpty()){
				vote.setHeadUrl(HeadUrl);
			}
			if(MiddleUrl!=null && !MiddleUrl.isEmpty()){
				vote.setMiddleUrl(MiddleUrl);
			}
			if(FootUrl!=null && !FootUrl.isEmpty()){
				vote.setFootUrl(FootUrl);
			}
			vote.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			int i=0;
			String Message="";
			if (IsNew.equals("true")) {
				i=VoteService.addVote(vote);
				Message="添加成功";
			}else{
				i=VoteService.updateVote(vote);
				Message="修改成功";
			}
			if (i==1) {
				responseMap.put("status", true);
				responseMap.put("Message", Message);
			}else{
				responseMap.put("status", false);
				responseMap.put("Message", Message);
			}
		}else{
			responseMap.put("status", false);
			responseMap.put("Message", ErrorMessage);
		}

		return responseMap;
	}
	

	@RequestMapping(value = "/addKeyWord", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addKeyWord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String title=request.getParameter("title");
		String VoteID=request.getParameter("VoteID");
		if (title==null||title=="null"||title=="undefined") {
			responseMap.put("status", false);
			responseMap.put("Message", "规则不允许为空，请输入规则！");
		}else{
			KeyWord keyWord=new KeyWord();
			long id=SmBaseUtil.CreateNewID();
			keyWord.setID(id);
			keyWord.setTypeID(SmBaseGlobal.KeywordType.ActivityChar.getCode());
			keyWord.setKeyWord(SmBaseUtil.URLDecoderString(title));
			keyWord.setVoteID(Long.parseLong(VoteID));
			keyWord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			int i=KeyWordService.addKeyWord(keyWord);
			if (i>0) {
				responseMap.put("status", true);
				responseMap.put("Message", "规则添加成功");
				responseMap.put("keyWordID", String.valueOf(id));
			}else{
				responseMap.put("status", false);
				responseMap.put("Message", "规则添加失败");
			}
		}
		return responseMap;
	}
	
	
	@RequestMapping(value = "/getKeyWordList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getKeyWord(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {

		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put(KeyWord.attributeKeyWordVoteID, request.getParameter("VoteID"));
		checkParammap.put(KeyWord.attributeKeyWordTypeID, SmBaseGlobal.KeywordType.ActivityChar.getCode());
		checkParammap.put(KeyWord.attributeKeyWordStatus, SmBaseGlobal.CheckStatus.Effective.getid());
		List<KeyWord> lswe = KeyWordService.queryKeyWord(checkParammap);
		int Prodcount = KeyWordService.queryKeyWordCount(checkParammap);
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

	
	@RequestMapping(value = "/deleteVote", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteVote(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int result = 0;
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				VoteService.deleteVote(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
	
	@RequestMapping(value = "/deleteKeyWord", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteKeyWord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int result = 0;
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id));
				KeyWordService.deleteKeyWord(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}
}