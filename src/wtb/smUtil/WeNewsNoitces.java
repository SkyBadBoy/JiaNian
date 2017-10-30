package wtb.smUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import read.core.service.ReadHonorRecordService;
import read.core.service.ReadStudentsService;
import read.core.service.ReadNoticesService;
import wtb.core.model.HonorRecord;
import wtb.core.model.Notices;
import wtb.core.model.StudentStat;
import wtb.core.model.Students;
import wtb.core.service.HonorRecordService;
import wtb.core.service.StudentStatService;
import wtb.core.service.StudentsService;
/**
 * 
 * @author John
 *
 */
@Component
public class WeNewsNoitces extends TimerTask {

	private static WeNewsNoitces weNewsNoitces;
	@Autowired
	private ReadStudentsService ReadStudentsService;
	@Autowired
	private StudentsService StudentsService;
	@Autowired
	private ReadNoticesService ReadNoticesService;
	@Autowired
	private HonorRecordService HonorRecordService;
	@Autowired
	private ReadHonorRecordService ReadHonorRecordService;
	@Autowired
	private StudentStatService StudentStatService;
	@Autowired
	private read.core.service.ReadStudentStatService ReadStudentStatService;
	
	private  ServletContextEvent ServletContextEvent;
	
	
	
	public  ServletContextEvent getServletContextEvent() {
		return ServletContextEvent;
	}
	public  void setServletContextEvent(ServletContextEvent servletContextEvent) {
		ServletContextEvent = servletContextEvent;
	}
	public static void setErrorUtil(WeNewsNoitces weNewsNoitces) {
		WeNewsNoitces.weNewsNoitces = weNewsNoitces;
	}
	@PostConstruct
	public void Init() {
		weNewsNoitces = this;
		weNewsNoitces.ReadStudentsService = this.ReadStudentsService;
		weNewsNoitces.StudentsService = this.StudentsService;
		weNewsNoitces.ReadNoticesService = this.ReadNoticesService;
		weNewsNoitces.HonorRecordService = this.HonorRecordService;
		weNewsNoitces.ReadHonorRecordService = this.ReadHonorRecordService;
		weNewsNoitces.StudentStatService = this.StudentStatService;
		weNewsNoitces.ReadStudentStatService = this.ReadStudentStatService;
	}
	

	@Override
	public void run()
	{
		Map<String, Object> responseMap = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		//获取今日生日信息
		System.err.println("WeNewsNoitces");
		Calendar calendar = Calendar.getInstance();
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
		responseMap.put("AgeMonth", month);
		responseMap.put("AgeDay", day);
		responseMap.put("isStu", 1);
		responseMap.put("Sina", SmBaseUtil.Random());
		List<Students> stus=weNewsNoitces.ReadStudentsService.getStudentsList(responseMap);
		SmBaseUtil.SendBirthDayblessing(stus,weNewsNoitces.StudentsService,ServletContextEvent.getServletContext());
		//每周一进行统计达人类信息,以及统计长久未使用的小编
		if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.MONDAY){
			responseMap = new HashMap<String, Object>();
			responseMap.putAll(SmBaseUtil.getLastWeekDay());
			List<Notices> Notices = weNewsNoitces.ReadNoticesService.getNoticesSchoolRankingList(responseMap);
			//发放传播达人勋章
			for (Notices notices2 : Notices) {
				responseMap = new HashMap<String, Object>();
				responseMap.putAll(SmBaseUtil.getLastWeekDay());
				responseMap.put("AreaID", notices2.getAreaID());
				responseMap.put("start", 0);
				responseMap.put("limit", 10);
				responseMap.put("ClickCountOrder", 1);
				responseMap.put("Sina", SmBaseUtil.Random());
				List<Notices> StudentsNotices = weNewsNoitces.ReadNoticesService.getNoticesReadRankingList(responseMap);
				Date beginDate = new Date();
				Calendar date = Calendar.getInstance();
				date.setTime(beginDate);
				date.set(Calendar.DATE, date.get(Calendar.DATE) +7);
				String endDate = SmBaseGlobal.sdfDate.format(date.getTime());
				//传播达人
				if(StudentsNotices.size()>0){
					param.put("StudentID", StudentsNotices.get(0).getPublishUser());
					param.put("Type", SmBaseGlobal.HonerType.Spread.getid());
					param.put("EndTime", 1);
					param.put("Sina", SmBaseUtil.Random());
					int isExist=weNewsNoitces.ReadHonorRecordService.getHonorRecordCount(param);
					if(isExist<=0){
						HonorRecord honorRecord=new HonorRecord();
						honorRecord.setID(SmBaseUtil.CreateNewID());
						honorRecord.setEndTime(endDate);
						honorRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						honorRecord.setStudentID(Long.parseLong(StudentsNotices.get(0).getPublishUser()));
						honorRecord.setType(SmBaseGlobal.HonerType.Spread.getid());
						int result=weNewsNoitces.HonorRecordService.addHonorRecord(honorRecord);
						if(result>0){
							weNewsNoitces.StudentsService.UpHonorCount(Long.parseLong(StudentsNotices.get(0).getPublishUser()));
						}
					}
				}
				responseMap = new HashMap<String, Object>();
				responseMap.putAll(SmBaseUtil.getLastWeekDay());
				responseMap.put("AreaID", notices2.getAreaID());
				responseMap.put("start", 0);
				responseMap.put("limit", 10);
				responseMap.put("orderNoticeCount", 1);
				responseMap.put("Sina", SmBaseUtil.Random());
				StudentsNotices = weNewsNoitces.ReadNoticesService.getNoticesRankingList(responseMap);
				//写作达人
				if(StudentsNotices.size()>0){
					param.put("StudentID", StudentsNotices.get(0).getPublishUser());
					param.put("Type", SmBaseGlobal.HonerType.Write.getid());
					param.put("EndTime", 1);
					param.put("Sina",SmBaseUtil.Random());
					int isExist=weNewsNoitces.ReadHonorRecordService.getHonorRecordCount(param);
					if(isExist<=0){
						HonorRecord honorRecord=new HonorRecord();
						honorRecord.setID(SmBaseUtil.CreateNewID());
						honorRecord.setEndTime(endDate);
						honorRecord.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
						honorRecord.setStudentID(Long.parseLong(StudentsNotices.get(0).getPublishUser()));
						honorRecord.setType(SmBaseGlobal.HonerType.Write.getid());//写作达人
						int result=weNewsNoitces.HonorRecordService.addHonorRecord(honorRecord);
						if(result>0){
							weNewsNoitces.StudentsService.UpHonorCount(Long.parseLong(StudentsNotices.get(0).getPublishUser()));
						}
					}
				}
			}
			
			//获取超过1个月未使用了微新闻社的新浪小编100名
			responseMap = new HashMap<String, Object>();
			responseMap.put("start", 0);
			responseMap.put("limit", 100);
			responseMap.put("NotUsedDay", 30);
			responseMap.put("orderSendCount", 1);
			responseMap.put("Sina", SmBaseUtil.Random());
			stus=weNewsNoitces.ReadStudentsService.getStudentsList(responseMap);
			//发送短信
			SmBaseUtil.SendLongTimeUnUseNotices(stus, weNewsNoitces.StudentsService,ServletContextEvent.getServletContext());
			
		}
		if(calendar.get(Calendar.DAY_OF_MONTH)==1){
			calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			responseMap = new HashMap<String, Object>();
			responseMap.put("Month", calendar.get(Calendar.MONTH) + 1);
			responseMap.put("Year", calendar.get(Calendar.YEAR));
			responseMap.put("Sina", SmBaseUtil.Random());
			List<Notices> StudentsNotices = weNewsNoitces.ReadNoticesService.getReadNoticesList(responseMap);
			for (Notices notices : StudentsNotices) {
				responseMap = new HashMap<String, Object>();
				responseMap.put("StatMonth", calendar.get(Calendar.MONTH) + 1);
				responseMap.put("StatYear", calendar.get(Calendar.YEAR));
				responseMap.put("Sina", SmBaseUtil.Random());
				responseMap.put("StudentID", notices.getPublishUser());
				responseMap.put("Sina", SmBaseUtil.Random());
				long result=weNewsNoitces.ReadStudentStatService.getStudentStatCount(responseMap);
				if(result<=0){
					responseMap = new HashMap<String, Object>();
					responseMap.put("UsersID", notices.getPublishUser());
					responseMap.put("Month", calendar.get(Calendar.MONTH) + 1);
					responseMap.put("Year", calendar.get(Calendar.YEAR));
					responseMap.put("Sina", SmBaseUtil.Random());
					long glamour=weNewsNoitces.ReadNoticesService.getNoticesGlamourCount(responseMap);
					StudentStat stat=new  StudentStat();
					stat.setID(String.valueOf(SmBaseUtil.CreateNewID()));
					stat.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
					stat.setStudentID(notices.getPublishUser());
					stat.setGlamour(String.valueOf(glamour));
					stat.setYear(calendar.get(Calendar.YEAR));
					stat.setMonth((calendar.get(Calendar.MONTH) + 1));
					weNewsNoitces.StudentStatService.addStudentStat(stat);
				}
				
			}
			
		}
	}

	
	
}
