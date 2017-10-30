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

import org.bouncycastle.jcajce.provider.symmetric.ARC4.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import wtb.core.model.BaseInfo;
import wtb.core.model.Comment;
import wtb.core.model.Students;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;

@Controller
@RequestMapping("Setting")
public class SettingController extends BaseController {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/addSetting", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> addComment(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String pid = request.getParameter("pid");
		String name =  request.getParameter("name");// 名称
		String memo =  request.getParameter("memo");// 备注
		String parentID =  request.getParameter("parentID");// 类型
		long BaseID=0;

		if(pid==null || pid.isEmpty()){
			BaseID=0;
		}else{
			BaseID=Long.parseLong(pid);
		}
		if(memo==null){
			memo="";
		}
		if(name==null){
			name="";
		}
		if(parentID==null){
			parentID="0";
		}
		int result=0;
		BaseInfo baseinfo=ReadBaseInfoService.getBaseInfoList(BaseID);
		if(baseinfo==null || (baseinfo!=null && baseinfo.getID()<=0)){
			baseinfo = new BaseInfo();
			baseinfo.setMemo(SmBaseUtil.URLDecoderString(memo));
			baseinfo.setName(SmBaseUtil.URLDecoderString(name));
			baseinfo.setParentID(Long.parseLong(parentID));
			baseinfo.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			result = BaseInfoService.addBaseInfo(baseinfo);
		}else{
			baseinfo.setMemo(SmBaseUtil.URLDecoderString(memo));
			baseinfo.setName(SmBaseUtil.URLDecoderString(name));
			result = BaseInfoService.updateBaseInfo(baseinfo);
		}
		
		responseMap.put("status", result);
		return responseMap;
	}

	@RequestMapping(value = "/getSettingList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ParseException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String parentID =  request.getParameter("parentID");// 类型
		String sina =  request.getParameter("sina");// 类型
		
		if(parentID==null || parentID.isEmpty()){
			parentID="0";
		}
		responseMap.put("ParentID", parentID);
		responseMap.put("sina", sina);
		List<BaseInfo> baseinfos = ReadBaseInfoService.getBaseInfoListByID(responseMap);
		int baseInfoCount=ReadBaseInfoService.getBaseInfoCount(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", baseinfos);
		responseMap.put("total", baseInfoCount);
		responseMap.put("Status", 1);
		return responseMap;
	}

	
	@RequestMapping(value = "/deleteSetting", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteWeChatPublic(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> responseMap = null;

		String pid = request.getParameter("pid");
		String[] wids = pid.split(",");
		int result = 0;
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				responseMap.put("ID", id);
				BaseInfoService.deleteBaseInfo(responseMap);
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);
		return responseMap;
	}

	@RequestMapping(value = "/SettingList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		String parentID = request.getParameter("parentID");
		if(parentID==null || parentID.isEmpty()){
			model.addAttribute("parentID",0);
		}else{
			model.addAttribute("parentID",parentID);
		}
		return new ModelAndView(SmBaseGlobal.WebViewPath + "SettingList");
	}

}
