package wtb.smUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class WeNewsListen implements ServletContextListener,HttpSessionListener {
	private Timer timer = null; 
	private static int activeSessions = 0;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//timer.cancel(); 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			
			
		Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DAY_OF_MONTH);//每天

	        //定制每天的00:17:40执行，

		    calendar.set(year, month, day, 19, 40, 00);
			timer = new Timer(true); 
			List<String> configList=SmBaseUtil.initServersList(arg0.getServletContext());
			if(configList.size()>=4 && configList.get(3)!=null && configList.get(3).equals("true")){
				WeNewsNoitces wns=new WeNewsNoitces();
				wns.setServletContextEvent(arg0);
		        //每天执行的任务
		        timer.schedule(wns, calendar.getTime(),1000*60*60*24);
		        //每天小时统计一次
		        timer.schedule(new OnLineStatistics(), new Date(),1000*60);
			}
	  
		} catch (Exception e) {
			ErrorUtil.HandleError(null, "wtb.smUtil.MyListen.contextInitialized", e);
		}
 
	}
	
	 
    //session创建时执行
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.err.println("创建session,当前为:"+activeSessions);
    }
    //session销毁时执行
    public void sessionDestroyed(HttpSessionEvent se) {
        if (activeSessions > 0)
            activeSessions--;
        System.err.println("销毁session,当前为:"+activeSessions);
    }
    //获取活动的session个数(在线人数)
    public static int getActiveSessions() {
        return activeSessions;
    }

}
