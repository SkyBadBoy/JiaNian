package wtb.core.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
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

import com.alibaba.fastjson.JSONObject;

import wtb.core.model.ActivityPart;
import wtb.core.model.BaseInfo;
import wtb.core.model.HonorRecord;
import wtb.core.model.MoneyRecord;
import wtb.core.model.Notices;
import wtb.core.model.ProdPictures;
import wtb.core.model.Activity;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.core.model.WeChatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.core.service.MoneyRecordService;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.UnifiedorderServlet;

/**
 * wtb_users
 */
@Controller
@RequestMapping("/HonorRecord")
public class HonorRecordController extends BaseController {

	@RequestMapping(value = "/addHonorRecord", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object>  addHonorRecord(HttpServletResponse response,
			HttpServletRequest req, HttpSession session, Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String type=req.getParameter("type");
		String endTime=req.getParameter("endTime");
		String uid=req.getParameter("uid");
		if(uid==null || uid.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", -1);
			responseMap.put("message", "学生不存在");
			return responseMap;
		}
		if(endTime==null || endTime.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", -1);
			responseMap.put("message", "有效期不能为空");
			return responseMap;
		}
		if(type==null || type.isEmpty()){
			responseMap = new HashMap<String, Object>();
			responseMap.put("status", -1);
			responseMap.put("message", "勋章类型不能为空");
			return responseMap;
		}
		endTime +=" 23:59:59"; 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("StudentID", uid);
		param.put("Type", type);
		param.put("CustomerTime", endTime);
		param.put("Sina", SmBaseUtil.Random());
		int isExist=ReadHonorRecordService.getHonorRecordCount(param);
		if(isExist>0){
			responseMap = new HashMap<String, Object>();
			responseMap.put("Status", false);
			return responseMap; 
		}
		HonorRecord honorRecord=new HonorRecord();
		honorRecord.setID(SmBaseUtil.CreateNewID());
		honorRecord.setEndTime(endTime);
		honorRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		honorRecord.setStudentID(Long.parseLong(uid));
		honorRecord.setType(Integer.parseInt(type));
		int result=HonorRecordService.addHonorRecord(honorRecord);
		if(result>0){
			StudentsService.UpHonorCount(Long.parseLong(uid));
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("Status", result);
		
		return responseMap;
	}

	@RequestMapping(value = "/honorRecordList", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView StudentsUserList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		String StudentID=request.getParameter("StudentID");
		if(StudentID==null || StudentID.isEmpty()){
			StudentID="0";
		}
		model.addAttribute("StudentID", StudentID);
		return new ModelAndView(SmBaseGlobal.WebViewPath + "honorRecordList");
	}

	
	@RequestMapping(value = "/deleteHonorRecord", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> deleteHonorRecord(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int result = 0;
		Map<String, Object> responseMap = null;
		String WeChat = request.getParameter("pid");
		String[] wids = WeChat.split(",");
		for (String id : wids) {
			responseMap = new HashMap<String, Object>();
			if (!id.isEmpty()) {
				HonorRecord honorRecord=ReadHonorRecordService.getHonorRecordListByID(Long.parseLong(id));
				responseMap.put("ID", Long.parseLong(id));
				HonorRecordService.deleteHonorRecord(responseMap);
				if(honorRecord!=null){
					StudentsService.CancelHonorCount(honorRecord.getStudentID());
				}
				result++;
			}
		}
		responseMap = new HashMap<String, Object>();
		responseMap.put("result", result);

		return responseMap;
	}
	
	@RequestMapping(value = "/getHonorRecordList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getHonorRecordList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.putAll(SmBaseUtil.getInstance().getParamsMap(request));
		responseMap.putAll(SmBaseUtil.AddPageParam(request));
		List<HonorRecord> lswe = ReadHonorRecordService.getHonorRecordList(responseMap);

		int Prodcount = ReadHonorRecordService.getHonorRecordCount(responseMap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("Data", lswe);
		responseMap.put("Status", 1);
		responseMap.put("total", Prodcount);
		return responseMap;
	}

}