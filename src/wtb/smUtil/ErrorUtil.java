package wtb.smUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import read.core.service.ReadErrorStatService;
import wtb.core.model.ErrorLog;
import wtb.core.model.ErrorSend;
import wtb.core.model.ErrorStat;
import wtb.core.model.Students;
import wtb.core.service.ErrorLogService;
import wtb.core.service.ErrorSendService;
import wtb.core.service.ErrorStatService;

@Component
public class ErrorUtil {
	private static ErrorUtil errorUtil;
	@Autowired
	private ErrorLogService errorLogService;
	@Autowired
	private ErrorSendService errorSendService;
	@Autowired
	private ErrorStatService errorStatService;
	@Autowired
	private ReadErrorStatService readErrorStatService;

	public static void setErrorUtil(ErrorUtil errorUtil) {
		ErrorUtil.errorUtil = errorUtil;
	}

	@PostConstruct
	public void Init() {
		errorUtil = this;
		errorUtil.errorLogService = this.errorLogService;
		errorUtil.errorSendService = this.errorSendService;
		errorUtil.errorStatService = this.errorStatService;
		errorUtil.readErrorStatService = this.readErrorStatService;
	}

	public static void HandleError(String UserID, String ClassMethod, Exception e)  {
		try{
		 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        e.printStackTrace(new PrintStream(baos));
	        String exception = baos.toString();
			String ClassName = e.getClass().toString().replace("class ", "");
			ErrorLog errorLog = InsertErrorLog(UserID, ClassMethod, exception, ClassName);
			
			ErrorLogStat(ClassMethod,errorLog);
			
			
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void HandleError2(String UserID, String ClassMethod,Exception e, String exception)  {
		try{
			e.printStackTrace();
			String ClassName = e.getClass().toString().replace("class ", "");
			ErrorLog errorLog = InsertErrorLog(UserID, ClassMethod, exception, ClassName);
			ErrorLogStat(ClassName,errorLog);
			
			e.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static void HandleErrorTestPoint(HttpSession session,  String ClassMethod, String exception)  {
		try{
			Students user = (Students) session.getAttribute("StudentName");
			String UserID = "0";
			if (user != null) {
				UserID = String.valueOf(user.getID());
			}
			ErrorLog errorLog = InsertErrorLog(UserID, ClassMethod, exception, ClassMethod);
			ErrorLogStat(ClassMethod,errorLog);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void ErrorLogStat(String ClassName,ErrorLog errorLog){
		String ExtMess="org.apache.catalina.connector.ClientAbortException";
		String ExtMess1="wtb.smUtil.SmBaseUtil.sendMail";
		if(!ClassName.contains(ExtMess) && !ClassName.equals(ExtMess) && !ClassName.contains(ExtMess1) && !ClassName.equals(ExtMess1)){
			Map<String, Object> map = new HashMap<>(); 
			map.put("ClassName", ClassName);
			map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			List<ErrorStat> count = errorUtil.readErrorStatService.getErrorStatList(map);
			if(count.size()<=0){
				ErrorStat errorStat=new ErrorStat();
				errorStat.setID(SmBaseUtil.CreateNewID());
				errorStat.setClassName(ClassName);
				errorStat.setCount(1);
				errorStat.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
				errorStat.setType(0);
				errorStat.setTotalCount(1);
				errorUtil.errorStatService.addErrorStat(errorStat);
				count.add(errorStat);
			}else{
				errorUtil.errorStatService.updateErrorStatByClassName(count.get(0).getID());
			}
			map=new HashMap<>();
			map.put("Status", SmBaseGlobal.CheckStatus.Effective.getid());
			map.put("Type", SmBaseGlobal.DriveType.Service.getid());
			List<ErrorSend> errorSends=errorUtil.errorSendService.getErrorSendList(map);
			SmBaseUtil smBaseUtil=new SmBaseUtil();
			for (int i = 0; i < errorSends.size(); i++) {
				if (count.get(0).getCount()>=Integer.parseInt(errorSends.get(i).getCount())) {
					if (isEmail(errorSends.get(i).getAccount())) {
						smBaseUtil.sendMail(errorSends.get(i).getAccount(), errorLog.getMessage());
						VerifyCode.sharedInstance().sendReportMessageResult(errorSends.get(i).getPhone(), errorSends.get(i).getAccount());
						count.get(0).setCount(0);
						count.get(0).setSendTime(SmBaseGlobal.sdfDateTime.format(new Date()));
						errorUtil.errorStatService.updateErrorStat(count.get(0));
					}
					
				}
				
			}
		}
	
	}
	private static ErrorLog InsertErrorLog(String UserID,String ClassMethod ,String Message,String ClassName) throws UnknownHostException{
		
		ErrorLog errorLog = new ErrorLog();
		errorLog.setID(SmBaseUtil.CreateNewID());
		if (UserID != null) {
			errorLog.setUserID(UserID);
		}
		errorLog.setName("【后台】"+InetAddress.getLocalHost().getHostAddress()+"："+SmBaseUtil.getSystemInfo());
		errorLog.setClassName(ClassName);
		try{
			errorLog.setMessage(ClassMethod+"："+Message);}
		catch (Exception e2) {
			errorLog.setMessage(ClassMethod+"："+Message);
		}
		errorLog.setStatus(7);
		errorUtil.errorLogService.addErrorLog(errorLog);// 设置为未通知状态
		return errorLog;
	}
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
		}

}
