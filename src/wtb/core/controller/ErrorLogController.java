package wtb.core.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.xpath.internal.operations.Bool;

import net.sf.json.JSONArray;
import wtb.core.model.ErrorLog;
import wtb.core.model.ErrorSend;
import wtb.core.model.LogFile;
import wtb.smUtil.IdWorker;
import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseUtil;

@Controller
@RequestMapping("ErrorLog")
public class ErrorLogController extends BaseController {

	/**
	 * 错误日志页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ErrorLogList")
	public ModelAndView ErrorLogList(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		Map<String, Object> map = null;
		map = new HashMap<>();
		
		return new ModelAndView(SmBaseGlobal.WebViewPath + "ErrorLogList");
	}

	
	@RequestMapping(value = "/getErrorLogStatList")
	public @ResponseBody
	Map<String, Object> getErrorLogStatList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> map = null;
		List<ErrorLog> errorLogAll = ErrorLogService.getViewData(map);// 获得所有数据
		List<Map<String, Object>> mListAll = new ArrayList<>();
		String nameALL = "[";
		JSONArray dataAll = null;
		if (errorLogAll.size() > 0) {
			for (int i = 0; i < errorLogAll.size(); i++) {
				map = new HashMap<>();
				map.put("value", errorLogAll.get(i).getCount());
				String[] classname = errorLogAll.get(i).getClassName().toString().split("\\.");
				map.put("name", classname[classname.length - 1]);
				if (i != errorLogAll.size() - 1) {
					nameALL += "\"" + classname[classname.length - 1] + "\",";
				} else {
					nameALL += "\"" + classname[classname.length - 1] + "\"]";
				}
				mListAll.add(map);
			}
			dataAll = JSONArray.fromObject(mListAll);
		}

		map = new HashMap<>();
		map.put("data", 1);
		List<ErrorLog> errorLogDay = ErrorLogService.getViewData(map);// 获得所有数据
		List<Map<String, Object>> mListDay = new ArrayList<>();
		String nameDay = "[";
		JSONArray dataDay = null;
		if (errorLogDay.size() > 0) {
			for (int i = 0; i < errorLogDay.size(); i++) {
				map = new HashMap<>();
				map.put("value", errorLogDay.get(i).getCount());
				String[] classname = errorLogDay.get(i).getClassName().toString().split("\\.");
				map.put("name", classname[classname.length - 1]);
				if (i != errorLogDay.size() - 1) {
					nameDay += "\"" + classname[classname.length - 1] + "\",";
				} else {
					nameDay += "\"" + classname[classname.length - 1] + "\"]";
				}
				mListDay.add(map);
			}
			dataDay = JSONArray.fromObject(mListDay);
		}
		if (nameDay.equals("[")) {
			nameDay = "[]";
		}
		responseMap.put("dataAll", dataAll);
		responseMap.put("nameAll", nameALL);
		responseMap.put("dataDay", dataDay);
		responseMap.put("nameDay", nameDay);
		return responseMap;
	}
	
	
	@RequestMapping(value = "/getErrorLogList")
	public @ResponseBody
	Map<String, Object> getErrorLogList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.putAll(SmBaseUtil.AddPageParam(request));
		List<ErrorLog> errorLogs = ErrorLogService.getErrorLogList(params);
		int count = ErrorLogService.getErrorLogCount(params);
		responseMap.put("total", count);
		responseMap.put("Data", errorLogs);
		responseMap.put("Status", 1);

		return responseMap;
	}

	@RequestMapping(value = "/deleteErrorLog")
	public @ResponseBody
	Map<String, Object> deleteErrorLog(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String id = request.getParameter("id");
		String[] ids = id.split(",");
		int result = 0;
		Map<String, Object> responseMap;
		for (String id1 : ids) {
			responseMap = new HashMap<String, Object>();
			if (!id1.isEmpty()) {
				responseMap.put("ID", Long.parseLong(id1));
				ErrorLogService.deleteErrorLog(responseMap);
				result++;
			}
		}
		if (result != 0) {
			map.put("Status", 1);
			map.put("count", result);
		} else {
			map.put("Status", -1);
		}
		return map;
	}
	
	@RequestMapping(value = "/getErrorLogFileList")
	public @ResponseBody
	Map<String, Object> getErrorLogFileList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		String DriveType = request.getParameter("DriveType");
		if(DriveType!=null && !DriveType.isEmpty()){
			params.put("DeviceType", DriveType);
		}
		params.put("Sina", SmBaseUtil.Random());
		params.putAll(SmBaseUtil.AddPageParam(request));
		List<LogFile> ErrorSends = ReadLogFileService.getLogFileList(params);
		for (int i = 0; i < ErrorSends.size(); i++) {
			if(ErrorSends.get(i).getDeviceType()==SmBaseGlobal.ClientType.Android.getid()){
				ErrorSends.get(i).setDeviceTypeMemo("安卓");
			}else{
				ErrorSends.get(i).setDeviceTypeMemo("苹果");
			}
			if(ErrorSends.get(i).getUrl()!=null && !ErrorSends.get(i).getUrl().isEmpty()){
				ErrorSends.get(i).setDownUrl("<a target='_blank' download href='"+ ErrorSends.get(i).getUrl() +"'>点击下载</a>");
			}else{
				ErrorSends.get(i).setDownUrl("无法下载");
			}
			
			
		}
		int count = ErrorSendService.getErrorSendCount(params);
		responseMap.put("total", count);
		responseMap.put("Data", ErrorSends);
		responseMap.put("Status", 1);
		return responseMap;
	}
	@RequestMapping(value = "/getErrorSendList")
	public @ResponseBody
	Map<String, Object> getErrorSendList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.putAll(SmBaseUtil.AddPageParam(request));
		List<ErrorSend> ErrorSends = ErrorSendService.getErrorSendList(params);
		for (int i = 0; i < ErrorSends.size(); i++) {
			if(ErrorSends.get(i).getType()==SmBaseGlobal.DriveType.IOS.getid()){
				ErrorSends.get(i).setTypeMemo("IOS开发");
			}else if(ErrorSends.get(i).getType()==SmBaseGlobal.DriveType.Android.getid()){
				ErrorSends.get(i).setTypeMemo("Android开发");
			}else if(ErrorSends.get(i).getType()==SmBaseGlobal.DriveType.Inteface.getid()){
				ErrorSends.get(i).setTypeMemo("接口开发");
			}else if(ErrorSends.get(i).getType()==SmBaseGlobal.DriveType.Service.getid()){
				ErrorSends.get(i).setTypeMemo("后台开发");
			}else{
				ErrorSends.get(i).setTypeMemo("其他开发");
			}
			
			
		}
		int count = ErrorSendService.getErrorSendCount(params);
		responseMap.put("total", count);
		responseMap.put("Data", ErrorSends);
		responseMap.put("Status", 1);
		return responseMap;
	}

	@RequestMapping(value = "/addErrorSendList")
	public @ResponseBody
	Map<String, Object> addErrorSendList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = new HashMap<String, Object>();
		String ID = request.getParameter("ID");
		String Account = request.getParameter("Account");
		String Message = request.getParameter("Message");
		String Phone = request.getParameter("Phone");
		String type = request.getParameter("Type");
		String Count = request.getParameter("Count");
		Message = SmBaseUtil.URLDecoderString(Message);
		ErrorSend errorSend = new ErrorSend();
		Boolean isNew=false;
		if(ID==null || ID.isEmpty() || ID.equals("0")){
			isNew=true;
			errorSend.setID(SmBaseUtil.CreateNewID());
			errorSend.setStatus(String.valueOf(SmBaseGlobal.CheckStatus.Default.getid()));
		}else{
			responseMap.put("ID", ID);
			List<ErrorSend> ErrorSendS = ErrorSendService.getErrorSendList(responseMap);
			if(ErrorSendS.size()>0){
				errorSend=ErrorSendS.get(0);
			}
		}
		errorSend.setAccount(Account);
		errorSend.setPhone(Phone);
		errorSend.setMessage(Message);
		errorSend.setCount(Count);
		errorSend.setType(Integer.parseInt(type));
		int i = 0;
		if(isNew){
			i = ErrorSendService.addErrorSend(errorSend);
		}else{
			i = ErrorSendService.updateErrorSend(errorSend);
		}
		if (i == 1) {
			responseMap.put("Status", 1);
		} else {
			responseMap.put("Status", -1);
		}
		return responseMap;
	}

	@RequestMapping(value = "/delectErrorSendList")
	public @ResponseBody
	Map<String, Object> delectErrorSendList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = null;
		String ID = request.getParameter("ID");
		Map<String, Object> map = new HashMap<>();
		map.put("ID", ID);
		ErrorSendService.deleteErrorSend(map);
		return responseMap;
	}


	@RequestMapping(value = "/updateOpenErrorSendList")
	public @ResponseBody
	Map<String, Object> updateOpenErrorSendList(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Map<String, Object> responseMap = null;
		responseMap = new HashMap<>();
		String ID = request.getParameter("ID");
		String Status = request.getParameter("Status");
		responseMap.put("ID", ID);
		List<ErrorSend> ErrorSendS = ErrorSendService.getErrorSendList(responseMap);
		ErrorSend errorSend = ErrorSendS.get(0);
		errorSend.setStatus(Status);
		int i = ErrorSendService.updateErrorSend(errorSend);
		responseMap = new HashMap<>();
		if (i == 1) {
			responseMap.put("Status", 1);
		} else {
			responseMap.put("Status", -1);
		}
		return responseMap;
	}

	@RequestMapping(value = "/getChatLine")
	public @ResponseBody
	Map<String, Object> getChatLine(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
		String STime = request.getParameter("STime");
		String ETime = request.getParameter("ETime");
		if(STime==null || STime.isEmpty()){
			String Monday = getCurrentMonday();
			String Sunday = getPreviousSunday();
			STime=Monday+" 00:00:00";
			ETime=Sunday + " 23:59:59";
		}
		Map<String, Object> map = new HashMap<>();
		map = new HashMap<>();
		String dataCount = "";
		String data = "";
		map = new HashMap<>();
		map.put("StartTime", STime);
		map.put("EndTime", ETime);
		List<ErrorLog> getDateDayAll = ErrorLogService.getChartLineStat(map);
		for (int k = 0; k < getDateDayAll.size(); k++) {
			// System.err.println(getDateDayAll.get(k).getClassName()+"------------"+getDateDayAll.get(k).getDateDayHour()+"--------"+getDateDayAll.get(k).getCount());
			int Count = getDateDayAll.get(k).getCount();
			String[] classname = getDateDayAll.get(k).getClassName().toString().split("\\.");
			String classn = classname[classname.length - 1];
			String time = getDateDayAll.get(k).getYear()+getDateDayAll.get(k).getMonth()+getDateDayAll.get(k).getDay();
			dataCount += Count + ",";
			data += time + " " + getDateDayAll.get(k).getClassName().replaceAll("\"", "'") + ",";
		}
			
		

		dataCount = dataCount.substring(0, dataCount.length() - 1);
		data = data.substring(0, data.length() - 1);
		System.err.println(dataCount);
		System.err.println(data);

		String[] dataCountArr = dataCount.split(",");
		int[] dataCountint = StringToInt(dataCountArr);
		int max = getMax(dataCountint);

		map = new HashMap<>();
		map.put("dataCount", dataCount);
		map.put("data", data);
		map.put("max", max);
		return map;
	}

	private String getCurrentMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得当前周- 周日 的日期
	private String getPreviousSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	// 获得当前日期与本周一相差的天数
	private int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}

	/**
	 * 取出数组中的最大值
	 * 
	 * @param arr
	 * @return
	 */
	public static int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	public int[] StringToInt(String[] arrs) {
		int[] ints = new int[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			ints[i] = Integer.parseInt(arrs[i]);
		}
		return ints;
	}
}
