package wtb.core.data;
import java.util.*;

import wtb.core.model.Students;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatPublic;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_WeChatPublic
 */
public interface StudentsMapper {

	 
	 /**
	 * 修改记录
	 */
	 public int updateStudents(Students Students);
	 /**
	 * 删除记录
	 */
	 public int deleteStudents(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addStudents(Students Students);
	 /**
	 * 恢复记录
	 */
	 public int enabledStudents(Map<String,Object> params);


	 public int ChangePassword(Students obj);
	 
	 public int updateStudentsForOpenID(Students Students);
	 public int updateLevelStudents(Students Students);
	 
	 public int UpNoticeCount(long Sid);
	 public int CancelNoticeCount(long Sid);
	 
	 public int UpdateOfficial(long Sid);
	 public int CancelOfficial(long Sid);
	 
	 public int UpdateChief(Map<String,Object> params);
	 
	 public int UpHonorCount(long Sid);
	 public int CancelHonorCount(long Sid);
	 
	 public int UpSendCount(long Sid);
	 
	 public int UupdateBirthDayRemind(long Sid);
	 
}