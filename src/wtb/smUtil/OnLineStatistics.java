package wtb.smUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import read.core.service.ReadAccessActiveService;
import read.core.service.ReadStudentsService;
import wtb.core.model.OnlineCount;
import wtb.core.service.OnLineService;
import wtb.core.service.OnlineCountService;
/**
 * 
 * @author John
 *
 */
@Component
public class OnLineStatistics extends TimerTask {
	private static OnLineStatistics onLineStatistics;

	@Autowired
	private ReadStudentsService ReadStudentsService;
	@Autowired
	private OnlineCountService OnlineCountService;
	@Autowired
	private ReadAccessActiveService ReadAccessActiveService;
	@Autowired
	private OnLineService OnLineService;
	
	public static void setErrorUtil(OnLineStatistics onLineStatistics) {
		OnLineStatistics.onLineStatistics = onLineStatistics;
		
	}

	@PostConstruct
	public void Init() {
		onLineStatistics = this;
		onLineStatistics.ReadStudentsService = this.ReadStudentsService;
		onLineStatistics.OnlineCountService = this.OnlineCountService;
		onLineStatistics.ReadAccessActiveService = this.ReadAccessActiveService;
		onLineStatistics.OnLineService = this.OnLineService;
		
	}
	@Override
	public void run()
	{
		//开启定时功能
		System.err.println("OnLineStatistics");
		OnlineCount count=new OnlineCount();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("Type", SmBaseGlobal.ProjectType.WeNewsService.getid());
		long currentSession=onLineStatistics.ReadAccessActiveService.getAccessActiveCountForMinute(param);
		 param = new HashMap<String, Object>();
		param.put("CurNewUser", 1);
		int NewUserCount=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		param = new HashMap<String, Object>();
		param.put("CurAccessUser", 1);
		int accessUserCount=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		count.setID(String.valueOf(SmBaseUtil.CreateNewID()));
		count.setNewUserCount(NewUserCount);
		count.setAccessUserCount(accessUserCount);
		count.setCount(currentSession);
		count.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		count.setType(SmBaseGlobal.ProjectType.WeNewsService.getid());//1表示微新闻社项目
		onLineStatistics.OnlineCountService.addOnlineCount(count);
		
		/* app活跃度 */
		count=new OnlineCount();
		param = new HashMap<String, Object>();
		param.put("Type", SmBaseGlobal.ProjectType.WeNewsInteface.getid());
		currentSession=onLineStatistics.ReadAccessActiveService.getAccessActiveCountForMinute(param);
		param = new HashMap<String, Object>();
		param.put("CurNewUser", 1);
		NewUserCount=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		param = new HashMap<String, Object>();
		param.put("CurAccessUser", 1);
		accessUserCount=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		count.setID(String.valueOf(SmBaseUtil.CreateNewID()));
		count.setNewUserCount(NewUserCount);
		count.setAccessUserCount(accessUserCount);
		count.setCount(currentSession);
		count.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		count.setType(SmBaseGlobal.ProjectType.WeNewsInteface.getid());//1表示微新闻社项目
		onLineStatistics.OnlineCountService.addOnlineCount(count);
		
		/* 后台日活跃度统计 */
		param = new HashMap<String, Object>();
		param.put("Today", 1);
		int NewStu=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		param = new HashMap<String, Object>();
		param.put("TodayActive", 1);
		int ActivityStu=onLineStatistics.ReadStudentsService.getStudentsCount(param);
		param = new HashMap<String, Object>();
		param.put("toDay", 1);
		param.put("Type", SmBaseGlobal.ProjectType.WeNewsService.getid());
		long toDay=onLineStatistics.ReadAccessActiveService.getAccessActiveCount(param);
		count=new OnlineCount();
		count.setID(String.valueOf(SmBaseUtil.CreateNewID()));
		count.setNewUserCount(NewStu);
		count.setAccessUserCount(ActivityStu);
		count.setCount(toDay);
		count.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		count.setType(SmBaseGlobal.ProjectType.WeNewsServiceDay.getid());//1表示微新闻社项目
		int result=onLineStatistics.OnlineCountService.updateOnlineCountByToDay(count);
		if(result<=0){
			onLineStatistics.OnlineCountService.addOnlineCount(count);
		}
		
		/* App日活跃度统计 */
		param = new HashMap<String, Object>();
		param.put("toDayNew", 1);
		NewStu=onLineStatistics.OnLineService.findOnLine(param);
		param = new HashMap<String, Object>();
		param.put("toDay", 1);
		ActivityStu=onLineStatistics.OnLineService.findOnLine(param);
		param = new HashMap<String, Object>();
		param.put("toDay", 1);
		param.put("Type", SmBaseGlobal.ProjectType.WeNewsInteface.getid());
		toDay=onLineStatistics.ReadAccessActiveService.getAccessActiveCount(param);
		count=new OnlineCount();
		count.setID(String.valueOf(SmBaseUtil.CreateNewID()));
		count.setNewUserCount(NewStu);
		count.setAccessUserCount(ActivityStu);
		count.setCount(toDay);
		count.setStatus(SmBaseGlobal.CheckStatus.Effective.getid());
		count.setType(SmBaseGlobal.ProjectType.WeNewsIntefaceDay.getid());//1表示微新闻社项目
		int AppResult=onLineStatistics.OnlineCountService.updateOnlineCountByToDay(count);
		if(AppResult<=0){
			onLineStatistics.OnlineCountService.addOnlineCount(count);
		}
		
		
	}

	
	
}
