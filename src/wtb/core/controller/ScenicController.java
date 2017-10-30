package wtb.core.controller;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import wtb.core.model.Scenic;
import wtb.core.model.Students;
import wtb.core.model.Users;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.VerifyCode;

@Controller
@RequestMapping("/Scenic")
public class ScenicController extends BaseController{
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年9月18日 下午10:25:54 
		 * @Details 商户申请列表
	 */
	@RequestMapping(value = "/ScenicList", method = RequestMethod.GET)
	public ModelAndView WeChatPublicList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ApplyScenicList");
	}
	
	/**
	 * 
		 * 
		 * @Author 作者：马健
		 * @Phone  联系qq：1039510196
		 * @CreateTime 创建时间：2017年9月18日 下午11:19:38 
		 * @Details 获取申请列表
	 */
	@RequestMapping(value = "/getScenicList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getScenicList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		SmBaseUtil amBaseUtil = SmBaseUtil.getInstance();
		checkParammap.putAll(amBaseUtil.getParamsMap(request));
		checkParammap.putAll(SmBaseUtil.AddPageParam(request));
		checkParammap.put("Rand", SmBaseUtil.Random());
		List<Scenic> lswe = ReadScenicService.getScenicList(checkParammap);
		int Count = ReadScenicService.getScenicCount(checkParammap);
		responseMap = new HashMap<String, Object>();
		responseMap.put("data", lswe);
		responseMap.put("total", Count);
		responseMap.put("Status", 1);
		return responseMap;
	}
	
	@RequestMapping(value = "/makeScenic", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> makeScenic(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> checkParammap = new HashMap<String, Object>();
		String state=request.getParameter("state");
		String pid=request.getParameter("pid");
		String message="";
		//只允许一个一个弄
//		String[] pids=pid.split(",");
//		int count=0;
//		for (String id : pids) {
//			checkParammap.put(Scenic.attributeScenicID, id);
//			checkParammap.put(Scenic.attributeScenicRand, SmBaseUtil.Random());
//			List<Scenic> scenics=ReadScenicService.getScenicList(checkParammap);
//			if (scenics.size()>0) {
//				 scenics.get(0).setStatus(Integer.parseInt(state));
//				 count++;
//			}
//		}
//		responseMap.put("count", 1);
		checkParammap.put(Scenic.attributeScenicID, pid);
		checkParammap.put(Scenic.attributeScenicRand, SmBaseUtil.Random());
		List<Scenic> scenics=ReadScenicService.getScenicList(checkParammap);
		if (scenics.size()>0) {
			scenics.get(0).setStatus(Integer.parseInt(state));
			int updataFlag=ScenicService.updateScenic(scenics.get(0));
			if (updataFlag>0) {
				responseMap.put("Status", true);
				responseMap.put("Message", "操作成功");
				if (Integer.parseInt(state)==SmBaseGlobal.CheckStatus.Effective.getid()) {//申请的  重新编辑的     和从未编辑的
					if (scenics.get(0).getUserid()==0) {//第一次申请的，所以没有分配账号
						//发送短信，告知用户申请成功了
						VerifyCode code = VerifyCode.sharedInstance();
						//String Password=code.createCheckNumber(6);
						String Password=code.createCheckNumber(123456);
						Users users=new Users();
						long id=new IdWorker(1, 0).nextId();
						users.setLoginName(scenics.get(0).getPhone());
						users.setPhone(scenics.get(0).getPhone());
						users.setName(scenics.get(0).getName());
						users.setPassWord(SmBaseUtil.MD5(Password));
						users.setCreateTime(new Date());
						users.setModifyTime(new Date());
						users.setStatus(1);
						users.setID(id);
						users.setImageID(0);
						users.setLevel(3);
						users.setAreaID(Long.parseLong(scenics.get(0).getAreaid()));
						int addresult = UsersService.addUsers(users);
						scenics.get(0).setUserid(id);
						ScenicService.updateScenic(scenics.get(0));
						//您好审核通过，您的账号是177645，您的密码是123456， 短信发送
						responseMap.put("password", Password);
						responseMap.put("Status", true);
						responseMap.put("Message", "操作成功");
						
					}else {//编辑的
						//发送短信，告知用户修改成功了
						
						//您好您修改的加盟信息已成功
						
						responseMap.put("Status", true);
						responseMap.put("Message", "操作成功");
						
					}
				}else {//被拒绝了
					if (scenics.get(0).getUserid()==0) {//第一次申请的，所以没有分配账号
						//发送短信，告知用户申请失败了
						
						//您好您未审核通过
					}else {//编辑的
						//发送短信，告知用户修改失败了

						//您好您修改未通过审核
					}
					
					responseMap.put("Status", true);
					responseMap.put("Message", "操作成功");
				}
			}else {
				responseMap.put("Status", false);
				responseMap.put("Message", "操作失败");
			}
		}else {
			responseMap.put("Status", false);
			responseMap.put("Message", "该申请不存在");
		}
		return responseMap;
	}
	
	

}
