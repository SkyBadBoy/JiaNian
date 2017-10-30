package wtb.smUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;









import wtb.core.model.Notices;
import wtb.core.model.Region;
import wtb.core.service.NoticesService;
import wtb.core.service.RegionService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import read.core.service.ReadNoticesService;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTask extends TimerTask {

	@Autowired
	private RegionService regionService;
	@Autowired
	private NoticesService noticesService;
	@Autowired
	private ReadNoticesService ReadnoticesService;
	@Override
	public void run()
	{
		//开启定时功能
		 DBConnection dbsource = null;
		try {
			dbsource = new DBConnection();
		} catch (ClassNotFoundException | IOException e2) {
			ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.run", e2);
		}
		 Connection conn = null;
		try {
			conn = (Connection) dbsource.getConnection();
		} catch (SQLException e2) {
			ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.run", e2);
		}
		 Statement stmt = null;
		 String query1="UPDATE `wtb_Notices` SET `Notices_Region`=2 WHERE `Notices_Region`=1 and to_days(now())-to_days(`Notices_CreateTime`) =1";
		 String query2="UPDATE `wtb_Notices` SET `Notices_IsCity`=2 WHERE `Notices_IsCity`=1 and to_days(now())-to_days(`Notices_CreateTime`) =1";
		 String query3="UPDATE `wtb_Notices` SET `Notices_IsPro`=2 WHERE `Notices_IsPro`=1 and to_days(now())-to_days(`Notices_CreateTime`) =1";
		 
		    try {
		    	stmt = (Statement) conn.createStatement();  
		    	conn.setAutoCommit(false);
		    	stmt.addBatch(query1);
		    	stmt.addBatch(query2);
		    	stmt.addBatch(query3);
		    	stmt.executeBatch(); 
		    	System.out.println("插入成功!");   
		    	conn.commit();
		    	System.out.println("提交成功!"); 
		    	conn.setAutoCommit(true);
 
		    } catch (SQLException e) {
		    	ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.run", e);
		        System.out.println("出错啦");
		        try {
					if (!conn.isClosed()) {
						conn.rollback();
						System.out.println("回滚");
						conn.setAutoCommit(true);
					}
				} catch (SQLException e1) {
					ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.run", e1);
				}
		    }finally
		    {
		    	try {
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.run", e);
					
				}
		    }
		
	}

	private void init() {
		/**
		 * 0是未查询 1待推送的新闻  2过期新闻
		 */
try{
		Map<String, Object> checkParammap1 = new HashMap<String, Object>();
		checkParammap1.put("orde", 1);//找出一共有多少类
		List<Notices> lsWc = ReadnoticesService.getReadNoticesList(checkParammap1);
		
		int sum=0;
		int citysum=0;
		int prosum=0;
		int number1=0; 
		Date date0 = null;
		Date date2=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<lsWc.size();i++)
		{
			Map<String, Object> checkParammap2 = new HashMap<String, Object>();
			checkParammap2.put("AreaID", lsWc.get(i).getAreaID());
			List<Notices> lsWc2 = ReadnoticesService.getReadNoticesList(checkParammap2);//按AreaID地区找出多少条
			
			for(int x=0;x<lsWc2.size();x++)
			{
				try {
					date0 = df.parse(df.format(new Date()));
					date2 = df2.parse(lsWc2.get(x).getCreateTime());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				number1=date2.compareTo(date0);
				if(lsWc2.get(x).getRegion()==1 && number1==-1 && lsWc2.get(x).getIsDel()==0)
				{
					Notices notices=lsWc2.get(x);
					notices.setRegion(2);
					noticesService.updateNotices(notices);
				}
				if((number1==1||number1==0) && lsWc2.get(x).getIsDel()==0 && sum<10)
				{
					sum++;
					Notices notices=lsWc2.get(x);
					notices.setRegion(1);
					noticesService.updateNotices(notices);
				}
			}
		}
		
		/**
		 * 找出这个学校的区域 市
		 */
		for(int i=0;i<lsWc.size();i++)
		{
			Map<String, Object> checkParammap2 = new HashMap<String, Object>();
			checkParammap2.put("ID", lsWc.get(i).getAreaID());//区
			checkParammap2.put("ParentID2", 1);
			List<Region> regions=regionService.getRegionList(checkParammap2);//得到区上一级的地址
			//SELECT * FROM wtb_Region WHERE REGION_ID in(SELECT Notices_AreaID FROM `wtb_Notices` GROUP BY `Notices_AreaID` HAVING(COUNT(`Notices_AreaID`)>0)) GROUP BY `PARENT_ID` HAVING(COUNT(`PARENT_ID`)>0)
			if(regions.size()>0)
			{
				for (int j = 0; j < regions.size(); j++)
				{
					Map<String, Object> checkParammap3 = new HashMap<String, Object>();
					checkParammap3.put("AreaID", regions.get(j).getID());
					
					List<Notices> notices=ReadnoticesService.getReadNoticesList(checkParammap3);
					if(notices.size()>0)
					{
						for (int k = 0; k < notices.size(); k++) 
						{
							System.out.println(notices.size());
							if(notices.get(k).getRegion()==1||notices.get(k).getRegion()==2)
							{
								try {
									date0 = df.parse(df.format(new Date()));
									date2 = df2.parse(notices.get(k).getCreateTime());
								} catch (ParseException e) {
									e.printStackTrace();
								}
								number1=date2.compareTo(date0);
								
								if(number1==-1 && notices.get(k).getIsDel()==0)
								{
									Notices notices2=notices.get(k);
									notices2.setIsCity(2);
									noticesService.updateNotices(notices2);
								}
								
								if((number1==1||number1==0) && notices.get(k).getIsDel()==0 && citysum<=10)
								{
									citysum++;
									Notices notices2=notices.get(k);
									notices2.setIsCity(1);
									noticesService.updateNotices(notices2);
								}
							}
						}
					}
				}
			}
		}
		
		
		
		for (int i = 0; i < lsWc.size(); i++) {
			Map<String, Object> checkParammap2 = new HashMap<String, Object>();
			checkParammap2.put("ID", lsWc.get(i).getAreaID());// 区
			checkParammap2.put("ParentID2", 1);
			List<Region> regions = regionService.getRegionList(checkParammap2);// 得到区上一级的地址
			if (regions.size() > 0) {
				for (int j = 0; j < regions.size(); j++) {
					System.out.println("aaaaaaaaaaaaa" + regions.get(j).getParentID());
					Map<String, Object> checkParammapMap = new HashMap<String, Object>();
					checkParammapMap.put("ID", regions.get(j).getParentID());// 区
					List<Region> regions2 = regionService.getRegionList(checkParammapMap);// 得到区上一级的地址
					if (regions2.size() > 0) {
						for (int k = 0; k < regions2.size(); k++) {
							System.out.println("bbbbbbbbbbb" + regions2.get(k).getParentID());

							Map<String, Object> checkParammapMap2 = new HashMap<String, Object>();
							checkParammapMap2.put("ID", regions2.get(j).getParentID());// 区
							List<Region> regions3 = regionService.getRegionList(checkParammapMap2);
							if (regions3.size() > 0) {
								for (int l = 0; l < regions3.size(); l++) {
									System.out.println(regions3.get(l).getParentID());
									Map<String, Object> checkParammap = new HashMap<String, Object>();
									checkParammap.put("ParentID", regions3.get(l).getParentID());
									List<Region> Areas3 = regionService.getRegionList(checkParammap);// 获取啦所有关于该省的市
									for (int m = 0; m < Areas3.size(); m++) {
										Map<String, Object> checkParammapMap3 = new HashMap<String, Object>();
										checkParammapMap3.put("ParentID", Areas3.get(m).getID());
										List<Region> Areas2 = regionService.getRegionList(checkParammapMap3);// 获取啦所有关于该杭州市的地区
										for (int n = 0; n < Areas2.size(); n++) {

											Map<String, Object> checkParammapMap4 = new HashMap<String, Object>();
											checkParammapMap4.put("ParentID", Areas2.get(n).getID());
											List<Region> Areas = regionService.getRegionList(checkParammapMap4);// 获取啦所有关于该地区的学校
											if (Areas.size() > 0) {
												for (int o = 0; o < Areas.size(); o++) {
													System.out.println(Areas.get(o).getID() + "----------" + Areas.get(o).getParentID());
													Map<String, Object> checkParammapMap5 = new HashMap<String, Object>();
													checkParammapMap5.put("AreaID", Areas.get(o).getID());
													List<Notices> notices = ReadnoticesService.getReadNoticesList(checkParammapMap5);
													if (notices.size() > 0) {
														for (int a = 0; a < notices.size(); a++) {
															System.out.println(notices.size());
															if (notices.get(a).getIsCity() == 1 || notices.get(a).getIsCity() == 2) {
																try {
																	date0 = df.parse(df.format(new Date()));
																	date2 = df2.parse(notices.get(a).getCreateTime());
																} catch (ParseException e) {
																	e.printStackTrace();
																}
																number1 = date2.compareTo(date0);

																if (number1 == -1 && notices.get(a).getIsDel() == 0) {
																	Notices notices2 = notices.get(a);
																	notices2.setIsPro(2);
																	noticesService.updateNotices(notices2);
																}

																if ((number1 == 1 || number1 == 0) && notices.get(a).getIsDel() == 0 && prosum <= 10) {
																	prosum++;
																	Notices notices2 = notices.get(a);
																	notices2.setIsPro(1);
																	noticesService.updateNotices(notices2);
																}
															}
														}
													}
												}

											}
										}
									}

								}

							}
						}
					}
				}
			}
		}
		
} catch (Exception e) {
	ErrorUtil.HandleError(null, "wtb.smUtil.MyTask.init", e);
	
}
	}

}
