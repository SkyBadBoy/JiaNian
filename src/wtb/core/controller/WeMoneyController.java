package wtb.core.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONException;
import org.activiti.engine.impl.util.json.JSONML;
import org.apache.http.ParseException;
import org.jdom2.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;
import wtb.core.model.ActivityPart;
import wtb.core.model.ApplyList;
import wtb.core.model.Competition;
import wtb.core.model.CompetitionApply;
import wtb.core.model.MoneyRecord;
import wtb.core.model.Notices;
import wtb.core.model.PayRecord;
import wtb.core.model.Students;
import wtb.core.model.WeChatInfo;
import wtb.core.model.WeMoney;
import wtb.core.model.WeMoneyRecord;
import wtb.smUtil.ErrorUtil;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;
import wtb.smUtil.UnifiedorderServlet;
import wtb.smUtil.WeChatMenuUtil;
import wtb.smUtil.WeixinUtil;
import wtb.smUtil.SmBaseGlobal.CheckStatus;
import wtb.smUtil.SmBaseGlobal.WeMoneyType;
import wtb.smUtil.alipay.config.AlipayConfig;
import wtb.smUtil.alipay.util.AlipayNotify;
import wtb.smUtil.alipay.util.AlipaySubmit;
import wtb.smUtil.tenpay.WXPayUtil;
import wtb.smUtil.tenpay.util.XMLUtil;

@Controller
@RequestMapping("WeMoney")
public class WeMoneyController extends BaseController  {
	
	@RequestMapping(value = "/phoneWeMoney", method = RequestMethod.GET)
	public ModelAndView phoneWeMoney(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Students user =(Students)request.getSession().getAttribute("StudentName");
		Map<String , Object> map=new HashMap<>();
		map.put("UserID", user.getID()); 
		map.put("Sina", SmBaseUtil.Random()); 
		
		List<WeMoney> mList =ReadWeMoneyService.getWeMoneyList(map);
		model.addAttribute("WeMoney", mList.get(0).getWeMoney());
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath+"phoneSinaWeMoney");
	}
	
	
	@RequestMapping(value = "/phoneSinaWeMoneyPay", method = RequestMethod.GET)
	public ModelAndView phoneSinaWeMoneyPay(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws UnsupportedEncodingException {
		String money=request.getParameter("money");
		String uid=request.getParameter("uid");
		if(money==null || money.isEmpty()){
			money = "";
		}
		Map<String , Object> map=new HashMap<>();
		if(uid!=null && !uid.isEmpty()){
			map=new HashMap<>();
			map.put("ID", uid);
			List<Students> stus=ReadStudentsService.getStudentsList(map);
			if(stus.size()>0){
				model.addAttribute("Student",stus.get(0).getName());
			}
		}
		model.addAttribute("money",   money);
		model.addAttribute("uid",   uid);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath+"phoneSinaWeMoneyPay");
	}
	
	@RequestMapping(value = "/phoneSinaWeMoneyConfirm", method = RequestMethod.GET)
	public ModelAndView payWeMoneyConfirm(HttpServletRequest request, HttpServletResponse response,HttpSession session,Model model) throws UnsupportedEncodingException {
		Students user =(Students)request.getSession().getAttribute("StudentName");
		String money=request.getParameter("money");
		String uid=request.getParameter("uid");
		if(money==null || money.isEmpty() || money.equals("0")){
			money = "";
		}
		Map<String , Object> map=new HashMap<>();
		map.put("UserID", user.getID());
		List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyList(map);
		if (weMoneys.size()>0)
		{
			model.addAttribute("WeMoney", weMoneys.get(0).getWeMoney());
		}else{
			model.addAttribute("WeMoney", 0);
		}
		model.addAttribute("money", money);
		if(uid!=null && !uid.isEmpty()){
			map=new HashMap<>();
			map.put("ID", uid);
			List<Students> stus=ReadStudentsService.getStudentsList(map);
			if(stus.size()>0){
				model.addAttribute("Student",stus.get(0));
			}
		}
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath+"phoneSinaWeMoneyConfirm");
	}
	
	
	@RequestMapping(value = "/WeMoneyList", method = RequestMethod.GET)
	public ModelAndView WeMoneyList(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		String id=request.getParameter("id");
		Map<String , Object> map=new HashMap<>();
		map.put("UserID", id);
		List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyList(map);
		if (weMoneys.size()>0)
		{
			model.addAttribute("WeMoney", weMoneys.get(0).getWeMoney());
		}else{
			model.addAttribute("WeMoney", 0);
		}
		model.addAttribute("id", id);
		return new ModelAndView(SmBaseGlobal.WebViewPath+"WeMoneyList");
	}
	
	@RequestMapping(value = "/getWeMoneyRecordPCList", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> getWeMoneyRecordPCList(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws UnsupportedEncodingException {
		
		Map<String, Object> responseMap = new HashMap<String, Object>();
			
			responseMap.put("UserID", request.getParameter("id"));
			responseMap.putAll(SmBaseUtil.AddPageParam(request));
			List<WeMoneyRecord> weMoneyRecords =ReadWeMoneyRecordService.getWeMoneyRecordList(responseMap);
			int i=ReadWeMoneyRecordService.getWeMoneyRecordCount(responseMap);
			responseMap=new HashMap<String, Object>();
			responseMap.put("Data", weMoneyRecords);
			responseMap.put("Status", 1);
			responseMap.put("total", i);
		return responseMap;
	}
	
	
	@RequestMapping(value = "/phoneWeMoneyDetail", method = RequestMethod.GET)
	public ModelAndView phoneSinaWeMoneyDetail(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Students user =(Students)request.getSession().getAttribute("StudentName");
		responseMap.put("UserID", user.getID());
		responseMap.put("limit", 20);
		responseMap.put("start", 0);
		List<WeMoneyRecord> weMoneyRecords =ReadWeMoneyRecordService.getWeMoneyRecordList(responseMap);
		int i=ReadWeMoneyRecordService.getWeMoneyRecordCount(responseMap);
		model.addAttribute("WeMoneyRecord", weMoneyRecords);
		model.addAttribute("WeMoneyRecordCount", i);
		return new ModelAndView(SmBaseGlobal.SinaMobileViewPath+"phoneSinaWeMoneyDetail");
	}
	
	
	@RequestMapping(value = "/getWeMoneyRecordList", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCommentList(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			Students user =(Students)request.getSession().getAttribute("StudentName");
			responseMap.put("UserID", user.getID());
			responseMap.putAll(SmBaseUtil.AddPageParam(request));
			List<WeMoneyRecord> weMoneyRecords =ReadWeMoneyRecordService.getWeMoneyRecordList(responseMap);
			responseMap.put("data", weMoneyRecords);
			responseMap.put("Status", 1);
		return responseMap;
	}
	
	@RequestMapping(value = "/addWeMoney", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> addWeMoney(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			Students user =(Students)request.getSession().getAttribute("StudentName");
			String pirce =request.getParameter("pirce");
			responseMap.put("UserID", user.getID());
			List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyList(responseMap);
			if (weMoneys.size()>0) {
				WeMoney weMoney=weMoneys.get(0);
				weMoney.setWeMoney(weMoney.getWeMoney()+Long.parseLong(pirce)*10);
				int i=WeMoneyService.updateWeMoney(weMoney);
				if (i==1) {
					Long ID=new IdWorker(1, 0).nextId();
					WeMoneyRecord weMoneyRecord=new WeMoneyRecord();
					weMoneyRecord.setID(ID);
					weMoneyRecord.setWeMoney(Double.parseDouble(pirce)*10);
					weMoneyRecord.setUserID(user.getID());
					weMoneyRecord.setType(SmBaseGlobal.WeMoneyType.Added.getid());
					weMoneyRecord.setBelong(0l);
					weMoneyRecord.setReson("充值");
					weMoneyRecord.setFromUserID(user.getID());
					weMoneyRecord.setStatus(1);
					weMoneyRecord.setDeleted(0);
					i=WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
					responseMap = new HashMap<String, Object>();
					responseMap.put("Status",1);
				}else{
					responseMap = new HashMap<String, Object>();
					responseMap.put("Status",-1);
				}
				
			}else{
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status",-1);
			}
		return responseMap;
	}
	
	@RequestMapping(value = "/phonePayWeMoneyResult", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> payWeMoneyResult(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
			Students user =(Students)request.getSession().getAttribute("StudentName");
			String pirce =request.getParameter("pirce");
			String uid =request.getParameter("uid");
			if(uid==null || uid.isEmpty()){
				uid="0";
			}
			responseMap.put("UserID", user.getID());
			List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyList(responseMap);
			if (weMoneys.size()>0) {
				
				if (weMoneys.get(0).getWeMoney() - Long.parseLong(pirce) < 0) {
					responseMap.put("Status", false);
					responseMap.put("Message", "微米数量不足");
					return responseMap;
				}
					
				WeMoney weMoney=weMoneys.get(0);
				weMoney.setWeMoney(weMoney.getWeMoney()-Long.parseLong(pirce));
				int i=WeMoneyService.updateWeMoney(weMoney);
				if (i==1) {
					Long ID=new IdWorker(1, 0).nextId();
					WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
					weMoneyRecord.setID(ID);
					weMoneyRecord.setBelong(user.getID());
					weMoneyRecord.setFromUserID(Long.parseLong(uid));
					if(uid==null || uid.isEmpty() || uid.equals("0")){
						weMoneyRecord.setReson("通过二维码消费");
					}else{
						weMoneyRecord.setReson("微米转账");
					}
					
					weMoneyRecord.setUserID(user.getID());
					weMoneyRecord.setType(WeMoneyType.Consume.getid());
					weMoneyRecord.setStatus(CheckStatus.Effective.getid());
					weMoneyRecord.setWeMoney(Double.parseDouble(pirce));
					i=WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
					if(uid !=null && !uid.isEmpty() && !uid.equals("0")){
						weMoneyRecord = new WeMoneyRecord();
						weMoneyRecord.setID(SmBaseUtil.CreateNewID());
						weMoneyRecord.setBelong(Long.parseLong(uid));
						weMoneyRecord.setFromUserID(user.getID());
						weMoneyRecord.setUserID(Long.parseLong(uid));
						weMoneyRecord.setReson("微米收款");
						weMoneyRecord.setType(WeMoneyType.Added.getid());
						weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						weMoneyRecord.setWeMoney(Double.parseDouble(pirce));
						WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
						
						 responseMap = new HashMap<String, Object>();
						responseMap.put("UserID", uid);
						weMoneys=ReadWeMoneyService.getWeMoneyList(responseMap);
						if (weMoneys.size()>0) {
							weMoney=weMoneys.get(0);
							weMoney.setWeMoney(weMoney.getWeMoney()+Long.parseLong(pirce));
							WeMoneyService.updateWeMoney(weMoney);
						}
					}
					responseMap = new HashMap<String, Object>();
					responseMap.put("Status",true);
				}else{
					responseMap = new HashMap<String, Object>();
					responseMap.put("Status",false);
				}
				
			}else{
				responseMap = new HashMap<String, Object>();
				responseMap.put("Status",false);
			}
			
		return responseMap;
	}
	
	
	
	
	
	@RequestMapping(value = "/payWeChat", method = RequestMethod.GET)
	public @ResponseBody Map<String , Object> payWeChat(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException, ParseException, java.text.ParseException {
		Map<String , Object> map=new HashMap<String, Object>();
			response.setContentType("text/html; charset=utf-8");
			Students user =(Students)request.getSession().getAttribute("StudentName");
			String timestamp=request.getParameter("timestamp");
			String nonceStr=request.getParameter("nonceStr");
			String attach=request.getParameter("attach");
			long orderID=SmBaseUtil.CreateNewID() ;
			String pirce=request.getParameter("pirce");
			String ip=request.getParameter("ip");
			String wid=request.getParameter("WID");
			if(attach!=null && !attach.isEmpty()){
				com.alibaba.fastjson.JSONObject jsonobj= com.alibaba.fastjson.JSONObject.parseObject(attach);
				jsonobj.put("pir", pirce);
				attach = jsonobj.toJSONString();
			}
			
			if (pirce!=null&&ip!=null) {
				JSONObject jso = new JSONObject();
				String openId=user.getTempOpenID();
				if(openId==null || openId.isEmpty()){
					openId=user.getOpenID();
				}
				/*String strUserInfo=SmBaseUtil.getWeChat_token(openId);
				System.err.println(strUserInfo);
        		JSONObject userInfo=SmBaseUtil.PaseJsonToJsonObject(strUserInfo);
        		if(userInfo.containsKey("openid")){
        			openId = userInfo.getString("openid");
        		}*/
				//String openId="oDDzewhgiFgHtmMs-WF6feQAhaaE";//我自己的测试可用的
				WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(WeChatInfoServices, wid);
				String WeChatAPI_UsrInfo="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+ WeChatMenuUtil.getAccessToken(weChatInfo.getAppID(),weChatInfo.getAppsecret()) +"&openid="+ openId +"&lang=zh_CN";
				JSONObject userInfo=SmBaseUtil.SendGetRequestURL(WeChatAPI_UsrInfo);
				if(userInfo.containsKey("openid")){
					openId=userInfo.getString("openid");
				}
				String jsonObject=UnifiedorderServlet.execute(nonceStr,timestamp,openId,orderID+"", "微新闻社-"+orderID, Double.parseDouble(pirce), ip,request, jso,weChatInfo,attach).toString();
				map.put("status", 1);
				map.put("data", jsonObject);
				map.put("orderID", String.valueOf(orderID));
			}else{
				map.put("status", -1);
				map.put("data", "orderID"+orderID+";pirce"+pirce+";ip"+ip+";这里其中有一个值为空");
			}
		return map;
	} 
	
	/**
	 * 处理微信回调
	 * 
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/payCallBack",method ={RequestMethod.POST,RequestMethod.GET})  
	public @ResponseBody String payCallBack(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("微信服务号支付回调");
		String inputLine;
		StringBuilder notityXml = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = request.getReader();
			while ((inputLine = bufferedReader.readLine()) != null)
				notityXml.append(inputLine);
			if (bufferedReader != null)
				bufferedReader.close();
			if (notityXml.length() < 10) {
				return "fail";
			}
			String total_fee = "", bank_type = "" ;
			String out_trade_no = "",  result_code = "",appid="";
			String attach = "";
			JSONArray result = JSONML.toJSONObject(notityXml.toString()).getJSONArray("childNodes");
			int len = result.length();
			for (int i = 0; i < len; i++) {
				org.activiti.engine.impl.util.json.JSONObject js = result.getJSONObject(i);
				Object tagName = js.get("tagName");
				if (tagName.equals("bank_type")) {
					bank_type = js.getJSONArray("childNodes").getString(0);
				}  else if (tagName.equals("out_trade_no")) {
					out_trade_no = js.getJSONArray("childNodes").getString(0);
				} else if (tagName.equals("result_code")) {
					result_code = js.getJSONArray("childNodes").getString(0);
				} else  if (tagName.equals("attach")) {
					attach = js.getJSONArray("childNodes").getString(0);
				}else  if (tagName.equals("total_fee")) {
					total_fee = js.getJSONArray("childNodes").getString(0);
				}else  if (tagName.equals("appid")) {
					appid = js.getJSONArray("childNodes").getString(0);
				}
			}
			

			if (result_code.equals("SUCCESS") &&result_code.equals("SUCCESS")) {

				PrecessPayCallBackResult(total_fee, attach, out_trade_no,appid, bank_type, request, response, ReadWeMoneyService, WeMoneyService, WeMoneyRecordService, ActivityPartService, ActivityService, ReadCompetitionApplyService, CompetitionApplyService, WeChatInfoServices, ReadStudentsService, 
						ReadCompetitionService, PayRecordService,ReadActivityPartService,ReadApplyListService,ApplyListService);
				return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";//成功后给微信返回数据
			} else {
				
				return "fail";
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		} catch (IOException e) {
			e.printStackTrace();
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		return "fail";
	}
	
	private void PrecessPayCallBackResult(String total_fee,String attach,String out_trade_no,String appid,String bank_type,
			HttpServletRequest request,HttpServletResponse response,read.core.service.ReadWeMoneyService ReadWeMoneyService,wtb.core.service.WeMoneyService WeMoneyService,wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.ActivityPartService ActivityPartService,
			wtb.core.service.ActivityService ActivityService,read.core.service.ReadCompetitionApplyService ReadCompetitionApplyService
			,wtb.core.service.CompetitionApplyService CompetitionApplyService,wtb.core.service.WeChatInfoServices WeChatInfoServices,read.core.service.ReadStudentsService ReadStudentsService
			,read.core.service.ReadCompetitionService ReadCompetitionService,wtb.core.service.PayRecordService PayRecordService,read.core.service.ReadActivityPartService ReadActivityPartService,
			read.core.service.ReadApplyListService ReadApplyListService,wtb.core.service.ApplyListService ApplyListService){
		com.alibaba.fastjson.JSONObject jsonobj=com.alibaba.fastjson.JSONObject.parseObject(attach);
		total_fee = new java.text.DecimalFormat("#0.00").format(Double.parseDouble(total_fee) / 100);// 支付金额以分为单位
		PayRecord payRecord=new PayRecord();
		payRecord.setBeLongID((jsonobj!=null&&jsonobj.containsKey("bid"))?jsonobj.getString("bid"):"0");
		payRecord.setDeleted(0);
		payRecord.setID(SmBaseUtil.CreateNewID());
		payRecord.setMoney(total_fee);
		payRecord.setOrderID(out_trade_no);
		payRecord.setPayMethod(bank_type);
		payRecord.setTarde(appid);//交易类型变为appid
		String type=jsonobj.getString("type");
		if(type.equals(String.valueOf(SmBaseGlobal.PayUse.Recharge.getid()))){
			payRecord.setPayReason("微米充值");
		}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.ActivityPay.getid()))){
			payRecord.setPayReason("活动付款");
		}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.MatchPay.getid()))){
			payRecord.setPayReason("比赛付款");
		}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.ServicePay.getid()))){
			payRecord.setPayReason("增值服务");
		}else{
			payRecord.setPayReason("微新闻社付款");
		}
		payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		payRecord.setUserID((jsonobj!=null&&jsonobj.containsKey("uid"))?jsonobj.getString("uid"):"0");
		PayRecordService.addPayRecord(payRecord);
		if(attach!=null && !attach.isEmpty()){
			ProcessWeChatPayResult(request,response,jsonobj,appid,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,ActivityPartService,ActivityService,ReadCompetitionApplyService,CompetitionApplyService,WeChatInfoServices,ReadStudentsService,ReadCompetitionService,PayRecordService,ReadActivityPartService,ReadApplyListService,ApplyListService);
		}
	}
	
	@RequestMapping(value = "/alipayapi", method = RequestMethod.GET)
	public  ModelAndView payAli(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		
		return new ModelAndView(SmBaseGlobal.WebApyApiPath+"alipayapi");
	} 
	@RequestMapping(value = "/alipayapiResult", method = RequestMethod.GET)
	public  ModelAndView alipayapiResult(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
		
		response.sendRedirect(request.getContextPath() + "/include/paysuccess.html");
	
		return new ModelAndView(SmBaseGlobal.WebApyApiPath+"alipayapiResult");
	} 
	/**
	 * 处理微信回调
	 * 
	 * @param request
	 * @return
	 * @throws AlipayApiException 
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/aliPayCallBack",method ={RequestMethod.POST,RequestMethod.GET})  
	public @ResponseBody String aliPayCallBack(HttpServletRequest request,HttpServletResponse response) throws AlipayApiException, IOException {
		System.out.println("支付宝支付回调 begin");
		BufferedReader in = null;
		PrintWriter out = null;
		String result="";
		InputStreamReader InputStreamReader = new InputStreamReader(request.getInputStream(), "UTF-8");
		in = new BufferedReader(InputStreamReader);
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		System.out.println(result);
		System.out.println("支付宝支付回调 end url");
		//获取支付宝POST过来反馈信息
		Map<String,String> paramsMap = new HashMap<String,String>();
		try{
		
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			System.err.println("------------------");
			System.err.println(name);
			System.err.println(valueStr);
			System.err.println("------------------");
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			paramsMap.put(name, valueStr);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		//交易状态
		String trade_status = request.getParameter("trade_status");
		//金额
		String total_fee =request.getParameter("buyer_pay_amount");
		String app_id =request.getParameter("app_id");
		
		String attach = request.getParameter("passback_params");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		System.err.println("------------------");
		System.err.println(out_trade_no);
		System.err.println(trade_status);
		System.err.println(total_fee);
		System.err.println(attach);
		System.err.println("------------------");
		try{
			boolean signVerified = com.alipay.api.internal.util.AlipaySignature.rsaCheckV1(paramsMap, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.input_charset, AlipayConfig.sign_type); //调用SDK验证签名
			if(signVerified){
				PrecessPayCallBackResult(total_fee, attach, out_trade_no,app_id, "alipay", request, response, ReadWeMoneyService, WeMoneyService, WeMoneyRecordService, ActivityPartService, ActivityService, ReadCompetitionApplyService, CompetitionApplyService, WeChatInfoServices, ReadStudentsService, ReadCompetitionService, PayRecordService,ReadActivityPartService,ReadApplyListService,ApplyListService);
				return "success";	//请不要修改或删除
			}
			return "fail";
		}catch (Exception e) {
			e.printStackTrace();
			if(trade_status!=null && trade_status.equals("TRADE_SUCCESS") && app_id!=null && app_id.equals(AlipayConfig.app_id)){
				PrecessPayCallBackResult(total_fee, attach, out_trade_no,app_id, "alipay", request, response, ReadWeMoneyService, WeMoneyService, WeMoneyRecordService, ActivityPartService, ActivityService, ReadCompetitionApplyService, CompetitionApplyService, WeChatInfoServices, ReadStudentsService, ReadCompetitionService, PayRecordService,ReadActivityPartService,ReadApplyListService,ApplyListService);
				return "success";	//请不要修改或删除
			}
			return "fail";
		}
		
	}
	public static void ProcessWeChatPayResult(HttpServletRequest request,HttpServletResponse response, com.alibaba.fastjson.JSONObject attach,String appid,
			read.core.service.ReadWeMoneyService ReadWeMoneyService,wtb.core.service.WeMoneyService WeMoneyService,
			wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.ActivityPartService ActivityPartService,wtb.core.service.ActivityService ActivityService,
			read.core.service.ReadCompetitionApplyService ReadCompetitionApplyService,
			wtb.core.service.CompetitionApplyService CompetitionApplyService,wtb.core.service.WeChatInfoServices WeChatInfoServices,read.core.service.ReadStudentsService ReadStudentsService
			,read.core.service.ReadCompetitionService ReadCompetitionService,wtb.core.service.PayRecordService PayRecordService,read.core.service.ReadActivityPartService ReadActivityPartService,
			read.core.service.ReadApplyListService ReadApplyListService,wtb.core.service.ApplyListService ApplyListService){
		if(attach!=null){
			if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.Recharge.getid()){
				String userid=attach.getString("uid");
				String pirce =attach.getString("pir");
				String type =attach.getString("type");
				String text="";
				if(type.equals(String.valueOf(SmBaseGlobal.PayUse.Recharge.getid()))){
					text="微米充值";
				}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.ActivityPay.getid()))){
					text="活动付款";
				}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.MatchPay.getid()))){
					text="比赛付款";
				}else if(type.equals(String.valueOf(SmBaseGlobal.PayUse.ServicePay.getid()))){
					text="增值服务";
				}else{
					text="微新闻社付款";
				}
				PhoneaddWeMoney(pirce,userid,text,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService);
			}else if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.ActivityPay.getid()){
				PhoneApplyActivity(attach.getString("bid"),attach.getString("uid"),ActivityPartService,ReadActivityPartService,ActivityService);
			}else if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.MatchPay.getid()){
				phonePutSummerCambe(attach,request,response,ReadCompetitionApplyService,CompetitionApplyService,WeChatInfoServices,ReadStudentsService,
						ReadCompetitionService,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,PayRecordService,appid);
			}else if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.OtherPay.getid()){
				phonePutOtherCambe(attach,request,response,ReadCompetitionApplyService,CompetitionApplyService,WeChatInfoServices,ReadStudentsService,
						ReadCompetitionService,ReadWeMoneyService,WeMoneyService,WeMoneyRecordService,PayRecordService,appid);
			}else if(attach.containsKey("type") && attach.getInteger("type")==SmBaseGlobal.PayUse.ServicePay.getid()){
				phonePutServiceResult(attach,request,response,ReadApplyListService,ApplyListService,WeChatInfoServices,ReadStudentsService,
						PayRecordService,appid);
			}
		}
	}
	public static void PhoneaddWeMoney(String pirce,String userid,String text,read.core.service.ReadWeMoneyService ReadWeMoneyService,wtb.core.service.WeMoneyService WeMoneyService,wtb.core.service.WeMoneyRecordService WeMoneyRecordService) {
		Map<String, Object> responseMap = new HashMap<String, Object>();

		responseMap.put("UserID", userid);
		List<WeMoney> weMoneys=ReadWeMoneyService.getWeMoneyList(responseMap);
		if (weMoneys.size()>0) {
			WeMoney weMoney=weMoneys.get(0);
			weMoney.setWeMoney(weMoney.getWeMoney()+Long.parseLong(pirce)*10);
			int i=WeMoneyService.updateWeMoney(weMoney);
			if (i==1) {
				Long ID=SmBaseUtil.CreateNewID();
				WeMoneyRecord weMoneyRecord=new WeMoneyRecord();
				weMoneyRecord.setID(ID);
				weMoneyRecord.setWeMoney(Double.parseDouble(pirce)*10);
				weMoneyRecord.setUserID(Long.parseLong(userid));
				weMoneyRecord.setType(SmBaseGlobal.WeMoneyType.Added.getid());
				weMoneyRecord.setBelong(0l);
				weMoneyRecord.setReson(text);
				weMoneyRecord.setFromUserID(Long.parseLong(userid));
				weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weMoneyRecord.setDeleted(0);
				i=WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
			}
			
		}
	}
	//比赛申请
	public static void PhoneApplyActivity(String aid,String uid,wtb.core.service.ActivityPartService ActivityPartService,read.core.service.ReadActivityPartService ReadActivityPartService,wtb.core.service.ActivityService ActivityService) {
		

		Map<String, Object> checkParammap = new HashMap<String, Object>();
		checkParammap.put("Status", 1);
		if (aid != null && !aid.isEmpty()) {
			checkParammap.put("ActivityID", aid);
		}
		checkParammap.put("StudentID",uid);
		checkParammap.put("Sina",SmBaseUtil.Random());
		int Prodcount = ReadActivityPartService.getActivityPartCount(checkParammap);
		if (Prodcount <=0) {
			ActivityPart activitypart = new ActivityPart();
			activitypart.setActivityID(Long.parseLong(aid));
			activitypart.setID(SmBaseUtil.CreateNewID());
			activitypart.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
			activitypart.setStudentID(Long.parseLong(uid));
			ActivityPartService.addActivityPart(activitypart);
			ActivityService.ApplyActivity(Long.parseLong(aid));
		}
	}
	/**
	 * 添加积分记录(增加分享)
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/phoneshareAddIntegral", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> shareAddIntegral(HttpServletResponse response, HttpServletRequest request, HttpSession session, Model model)
			throws UnsupportedEncodingException {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String sid = request.getParameter("sid");
		String newsid = request.getParameter("newsid");
		String type = request.getParameter("type");
		if (sid != null && !sid.isEmpty() && newsid != null && !newsid.isEmpty()) {
			responseMap.put("StudentID", sid);
			responseMap.put("Type", newsid);
			responseMap.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			List<CompetitionApply>applies=ReadCompetitionApplyService.getCompetitionList(responseMap);
			if(applies.size()>0 && applies.get(0).getIntParamA()!=1){//IntParamA这里表示石佛已经分享到朋友圈了 一人一次返利
				if(type!=null && type.equals("3")){//不参与返利,只支持优惠减免
					String intParamB = request.getParameter("intParamB");
					if(intParamB==null || intParamB.isEmpty()){
						intParamB = "0";
					}
					applies.get(0).setIntParamA(1);
					applies.get(0).setIntParamB(Integer.parseInt(intParamB));
					CompetitionApplyService.updateCompetition(applies.get(0));
				}else{
					responseMap = new HashMap<String, Object>();
					responseMap.put("UserID", sid);
					responseMap.put("BeLongID", newsid);
					responseMap.put("Sina", SmBaseUtil.Random());
					List<PayRecord> list=ReadPayRecordService.getPayRecordList(responseMap);
					String appid=null;
					if(list.size()>0){
						appid = list.get(0).getTarde();
					}
					responseMap = withdrawMethod(sid,(float)100,request,response,WeChatInfoServices,ReadStudentsService,appid);
					if(responseMap!=null && responseMap.containsKey("Status") && responseMap.get("Status").equals(true)){
						applies.get(0).setIntParamA(1);
						CompetitionApplyService.updateCompetition(applies.get(0));
						try{
							PayRecord payRecord=new PayRecord();
							payRecord.setBeLongID(newsid);
							payRecord.setDeleted(0);
							payRecord.setID(SmBaseUtil.CreateNewID());
							payRecord.setMoney(String.valueOf(100));
							payRecord.setOrderID(newsid);
							payRecord.setPayMethod("企业转账");;
							payRecord.setPayReason("微新闻社返利成功");
							payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
							payRecord.setUserID(sid);
							PayRecordService.addPayRecord(payRecord);
						}catch (Exception e) {
							e.printStackTrace();
							ErrorUtil.HandleError(null, "WeMoneyController.phoneshareAddIntegral", e);
						}
					}
				}
				
			}
			
			return responseMap;
		}


		return responseMap;
	}
//  返利
	public static Map<String, Object> withdrawMethod(String userid, float price, HttpServletRequest request, HttpServletResponse response,wtb.core.service.WeChatInfoServices WeChatInfoServices,
			read.core.service.ReadStudentsService ReadStudentsService,String appid) {
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> queryUser = new HashMap<String, Object>();
		queryUser.put("ID", userid);

		List<Students> findUserFlag = ReadStudentsService.getStudentsList(queryUser);

		if (findUserFlag.size() == 0) {
			result.put("Status", false);
			result.put("Message", "用户不存在");
			return result;
		} else {
			if (findUserFlag.get(0).getOpenID() == null || findUserFlag.get(0).getOpenID().isEmpty()) {
				result.put("Status", false);
				result.put("Message", "未绑定微信,请先进行绑定在返利");
				return result;
			} else {
				if (price <= 0) {
					result.put("Status", false);
					result.put("Message", "返利金额必须大于零");
					return result;
				}
				/* 处理提现,金额每两小时提现一次 */
				try {
					String orderNo = String.valueOf(SmBaseUtil.CreateNewID());
					WeChatInfo weChatInfo=SmBaseUtil.getDefaultWeChatInfo(WeChatInfoServices, appid);
					String respcontent = WXPayUtil.pushClient(request, response, findUserFlag.get(0),(int)price*100, orderNo,weChatInfo,appid);// 提现金额写死1元钱
																													// 正式发布后修改
					Map<String, Object> mapContent = XMLUtil.doXMLParse(respcontent);
					if(mapContent!=null){
						if (mapContent.get("return_code").equals("SUCCESS") && mapContent.get("result_code").equals("SUCCESS")) {
							
							result.put("Status", true);
							result.put("Message", "返利成功");
							return result;
						} else {
							result.put("Status", false);
							result.put("Message", (String) mapContent.get("return_msg"));
							return result;
						}
					}else{
						result.put("Status", false);
						result.put("Message", "返利失败");
						return result;
					}
				}

				catch (Exception e) {
					e.printStackTrace();
					result.put("Status", false);
					result.put("Message", e.getMessage());
					return result;
				}

			}

		}

	}
	
	public static void main(String[] args) throws JDOMException, IOException {
		String a="<xml>\n" + 
				"<return_code><![CDATA[SUCCESS]]></return_code>\n" + 
				"<return_msg><![CDATA[]]></return_msg>\n" + 
				"<nonce_str><![CDATA[08b255a5d42b89b0585260b6f2360bdd]]></nonce_str>\n" + 
				"<result_code><![CDATA[SUCCESS]]></result_code>\n" + 
				"<partner_trade_no><![CDATA[908861816616259584]]></partner_trade_no>\n" + 
				"<payment_no><![CDATA[1000018301201709169894982713]]></payment_no>\n" + 
				"<payment_time><![CDATA[2017-09-16 09:15:18]]></payment_time>\n" + 
				"</xml>";
		Map<String, Object> ab=XMLUtil.doXMLParse(a);
//		System.err.println(ab.get("return_code").equals("SUCCESS") && ab.get("result_code").equals("SUCCESS"));
//		System.err.println(ab.get("return_code"));
//		System.err.println(ab.get("result_code"));
		ab.put("Status", true);
		String flag=ab.get("Status").toString();
		System.out.print(ab.get("Status").toString());
		System.out.print(flag.equals("true"));
		
		
	}
	
	public static void phonePutSummerCambe(com.alibaba.fastjson.JSONObject json, HttpServletRequest request ,HttpServletResponse response,read.core.service.ReadCompetitionApplyService ReadCompetitionApplyService,
			wtb.core.service.CompetitionApplyService CompetitionApplyService,wtb.core.service.WeChatInfoServices WeChatInfoServices,
			read.core.service.ReadStudentsService ReadStudentsService,read.core.service.ReadCompetitionService ReadCompetitionService,
			read.core.service.ReadWeMoneyService ReadWeMoneyService ,wtb.core.service.WeMoneyService WeMoneyService,
			wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.PayRecordService PayRecordService,String appid){
		
		
		String type=json.getString("bid");
		String userid=json.getString("uid");
		String text=json.getString("c");
		com.alibaba.fastjson.JSONObject jsonobj=com.alibaba.fastjson.JSONObject.parseObject(text);
		String wemoney=jsonobj.getString("w");//微米折扣数量
		String buid=jsonobj.getString("b");
		String cousr=jsonobj.getString("c");
		
		if(wemoney==null || wemoney.isEmpty()){
			wemoney="0";
		}
		if(buid==null || buid.isEmpty()){
			buid = "0";
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("ID", userid);
		List<Students>users=ReadStudentsService.getStudentsList(map);
		Students user=new Students();
		if(users.size()>0){
			user = users.get(0);
		}else{
			return ;
		}
		String[] strCousr=cousr.split("-");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("StudentID", user.getID());
		responseMap.put("Type", type);
		responseMap.put("Sina", SmBaseUtil.Random());
		int resultCount=ReadCompetitionApplyService.getCompetitionCount(responseMap);
		if(resultCount>0){
			return;
		}
		CompetitionApply competitionApply=new CompetitionApply();
		competitionApply.setID(SmBaseUtil.CreateNewID());
		competitionApply.setName(user.getName());
		competitionApply.setPhone(user.getPhone());
		competitionApply.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Effective.getid()));
		competitionApply.setStudentID(String.valueOf(user.getID()));
		competitionApply.setType(Long.parseLong(type));
		if(strCousr.length>=1){
			competitionApply.setStringParamC(strCousr[0]);//报名的选修课;
		}else{
			competitionApply.setStringParamC(cousr);//报名的选修课;
		}
		if(strCousr.length>=2){
			competitionApply.setStringParamD(strCousr[1]);//是否需要影集 1为需要
		}else{
			competitionApply.setStringParamD("0");//是否需要影集 1为需要
		}
		if(strCousr.length>=3){
			competitionApply.setStringParamE(strCousr[2]);//夏令营的期次
		}else{
			competitionApply.setStringParamE("1");//夏令营的期次
		}
		competitionApply.setStringParamA(buid);//这个buid表示邀请人
		competitionApply.setStringParamB(wemoney);//微米则口数量
		if(!wemoney.equals("0")){
			competitionApply.setStringParamB(String.valueOf((Float.parseFloat(wemoney)/10)));//微米折扣金额
		}
		int competitionFlag=CompetitionApplyService.addCompetition(competitionApply);
		if(competitionFlag>0){
			if(user!=null && Double.parseDouble(wemoney)>0){				
				map=new HashMap<>();
				map.put("ID", type);
				List<Competition> lsCom=ReadCompetitionService.getCompetitionList(map);
				String reson="夏令营活动报名";
				if(lsCom.size()>0){
					reson="夏令营"+ lsCom.get(0).getTitle() +"活动报名";
					SmBaseUtil.sendActiveApplyMessage(user.getPhone(), user.getName(), lsCom.get(0).getTitle(),request.getServletContext());
				}
				responseMap = new HashMap<String, Object>();
				responseMap.put("UserID", user.getID());
				responseMap.put("Sina", SmBaseUtil.Random());
				List<WeMoney> weMoney = ReadWeMoneyService.getWeMoneyByIDList(responseMap);
				weMoney.get(0).setWeMoney(weMoney.get(0).getWeMoney() - Double.parseDouble(wemoney));
				WeMoneyService.updateWeMoney(weMoney.get(0));
				WeMoneyRecord weMoneyRecord = new WeMoneyRecord();
				weMoneyRecord.setBelong(competitionApply.getID());
				weMoneyRecord.setReson(reson);
				weMoneyRecord.setWeMoney(Double.parseDouble(wemoney));
				weMoneyRecord.setFromUserID((long) 0);
				weMoneyRecord.setUserID(user.getID());
				weMoneyRecord.setType(WeMoneyType.Consume.getid());
				weMoneyRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				weMoneyRecord.setID(SmBaseUtil.CreateNewID());
				WeMoneyRecordService.addWeMoneyRecord(weMoneyRecord);
			}
			
			if(!buid.equals("0")){
				map=new HashMap<>();
				map.put("StringParamA", buid);
				map.put("Type", type);
				int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(map);
				if(competitionAppliesFlag<5){
					map = withdrawMethod(buid,(float)100,request,response,WeChatInfoServices,ReadStudentsService,appid);
					try{
						PayRecord payRecord=new PayRecord();
						payRecord.setBeLongID(type);
						payRecord.setDeleted(0);
						payRecord.setID(SmBaseUtil.CreateNewID());
						payRecord.setMoney( String.valueOf(100));
						payRecord.setOrderID(type);
						payRecord.setPayMethod("企业转账");;
						payRecord.setPayReason("微新闻社返利成功");
						payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						payRecord.setUserID(String.valueOf(user.getID()));
						PayRecordService.addPayRecord(payRecord);
					}catch (Exception e) {
						e.printStackTrace();
						ErrorUtil.HandleError(null, "WeMoneyController.phoneshareAddIntegral", e);
					}
				}
			}
		}
		
	}
	
	
	public static void phonePutServiceResult(com.alibaba.fastjson.JSONObject json, HttpServletRequest request ,HttpServletResponse response,read.core.service.ReadApplyListService ReadApplyListService,
			wtb.core.service.ApplyListService ApplyListService,wtb.core.service.WeChatInfoServices WeChatInfoServices,
			read.core.service.ReadStudentsService ReadStudentsService,wtb.core.service.PayRecordService PayRecordService,String appid){
		
		
		String type=json.getString("bid");
		String userid=json.getString("uid");
		String text=json.getString("c");
		
		
		
		Map<String, Object> map=new HashMap<>();
		map.put("ID", userid);
		List<Students>users=ReadStudentsService.getStudentsList(map);
		Students user=new Students();
		if(users.size()>0){
			user = users.get(0);
		}else{
			return ;
		}
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("NID", type);
		responseMap.put("Type", SmBaseGlobal.DealInfoType.CorrectList.getid());
		responseMap.put("Sina", SmBaseUtil.Random());
		List<ApplyList> resultCount=ReadApplyListService.getApplyListList(responseMap);
		if(resultCount.size()<=0){
			ApplyList applylist=new ApplyList();
			applylist.setID(SmBaseUtil.CreateNewID());
//			applylist.setNID(type);
			applylist.setStatus(SmBaseGlobal.CheckStatus.Default.getid());
			applylist.setStudentID(userid);
			applylist.setType(SmBaseGlobal.DealInfoType.CorrectList.getid());
			applylist.setUserName(users.get(0).getName());
			applylist.setUserPhone(users.get(0).getPhone());
			ApplyListService.addApplyList(applylist);
		}
	}
	
	public static void phonePutOtherCambe(com.alibaba.fastjson.JSONObject json, HttpServletRequest request ,HttpServletResponse response,read.core.service.ReadCompetitionApplyService ReadCompetitionApplyService,
			wtb.core.service.CompetitionApplyService CompetitionApplyService,wtb.core.service.WeChatInfoServices WeChatInfoServices,
			read.core.service.ReadStudentsService ReadStudentsService,read.core.service.ReadCompetitionService ReadCompetitionService,
			read.core.service.ReadWeMoneyService ReadWeMoneyService ,wtb.core.service.WeMoneyService WeMoneyService,
			wtb.core.service.WeMoneyRecordService WeMoneyRecordService,wtb.core.service.PayRecordService PayRecordService,String appid){
		
		
		String type=json.getString("bid");
		String userid=json.getString("uid");
		String text=json.getString("c");
		com.alibaba.fastjson.JSONObject jsonobj=com.alibaba.fastjson.JSONObject.parseObject(text);
		String wemoney=jsonobj.getString("w");//微米折扣数量
		String buid=jsonobj.getString("b");
		String cousr=jsonobj.getString("c");
		
		if(wemoney==null || wemoney.isEmpty()){
			wemoney="0";
		}
		if(buid==null || buid.isEmpty()){
			buid = "0";
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("ID", userid);
		List<Students>users=ReadStudentsService.getStudentsList(map);
		Students user=new Students();
		if(users.size()>0){
			user = users.get(0);
		}else{
			return ;
		}
		String[] strCousr=cousr.split("-");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("ID", wemoney);
		responseMap.put("Sina", SmBaseUtil.Random());
		List<CompetitionApply> resultCount=ReadCompetitionApplyService.getCompetitionList(responseMap);
		CompetitionApply competitionApply=new CompetitionApply();
		competitionApply.setID(SmBaseUtil.CreateNewID());
		if(resultCount.size()<=0){
			return ;
		}
		competitionApply=resultCount.get(0);
		competitionApply.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Effective.getid()));
		int competitionFlag=0;
		if(resultCount.size()>0){
			competitionFlag=CompetitionApplyService.updateCompetition(competitionApply);
		}
		if(competitionFlag>0){
			if(!competitionApply.getStringParamA().equals("0") && !competitionApply.getStringParamA().equals(competitionApply.getStudentID())){
				map=new HashMap<>();
				map.put("ID", type);
				List<Competition> competitions= ReadCompetitionService.getCompetitionList(map);
				if(competitions.size()>0){
					map=new HashMap<>();
					map.put("StringParamA", competitionApply.getStringParamA());
					map.put("Type", type);
					int competitionAppliesFlag=ReadCompetitionApplyService.getCompetitionCount(map);
					if(competitionAppliesFlag<10){
						int money=competitions.get(0).getIntParamA();
						money = money*competitionApply.getIntParamA();
						appid="wxab5e4ee5a20faf3f";
						map = withdrawMethod(competitionApply.getStringParamA(),(float)money,request,response,WeChatInfoServices,ReadStudentsService,appid);
						try{
							if(map!=null && map.containsKey("Message")){
								System.err.println(map.get("Message"));
								System.err.println("111111111111111");
							}
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						try{
							PayRecord payRecord=new PayRecord();
							payRecord.setBeLongID(type);
							payRecord.setDeleted(0);
							payRecord.setID(SmBaseUtil.CreateNewID());
							payRecord.setMoney( String.valueOf(money));
							payRecord.setOrderID(type);
							payRecord.setPayMethod("企业转账");;
							payRecord.setTarde(appid);
							payRecord.setPayReason(competitions.get(0).getTitle()+"返利成功");
							payRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
							payRecord.setUserID(String.valueOf(user.getID()));
							PayRecordService.addPayRecord(payRecord);
						}catch (Exception e) {
							e.printStackTrace();
							ErrorUtil.HandleError(null, "WeMoneyController.phoneshareAddIntegral", e);
						}
					}
				}
			}
		}
		
	}
	public String MD5Purity(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes("utf-8"));
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			plainText = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			String clazz = this.getClass().getName();
			String method = Thread.currentThread().getStackTrace()[1].getMethodName();
			ErrorUtil.HandleError(null, clazz + "." + method, e);
		}
		return plainText.toUpperCase();
	}
}
