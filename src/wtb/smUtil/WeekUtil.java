package wtb.smUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeekUtil {
	public static String  getNowWeek(int i){
		Date dt=new Date();
		int this_day=dt.getDay();
		int step_s2=-this_day+1;
		if (this_day==0) {
			step_s2=-6;//如果今天是星期一
		}
		long thisTime=System.currentTimeMillis();
		
		Date day=new Date(thisTime+(long) (step_s2+i)*24*3600*1000);//10本周第一天 1第二天 2第三天
		return getDataTostring(day,"d");
	}
	
	public static String getDataTostring(Date date,String formate){
		SimpleDateFormat dateFormat=new SimpleDateFormat(formate);
		return dateFormat.format(date);
	}
	
	public static void main(String[] args) {
		System.out.println(getNowWeek(0));
		System.out.println(getNowWeek(1));
		System.out.println(getNowWeek(2));
		System.out.println(getNowWeek(3));
		System.out.println(getNowWeek(4));
		System.out.println(getNowWeek(5));
		System.out.println(getNowWeek(6));
	}

}
