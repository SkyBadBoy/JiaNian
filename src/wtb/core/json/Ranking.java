package wtb.core.json;

/**
 * 排行榜接口
 * @author huban
 *
 */
public class Ranking {
	
	private String NoticeName;
	private String NoticeID;
	
	private String StudentID;
	private String StudentName;
	private String StudentHead;
	private String School;
	private String Count;
	
	private String PositionIcon;
	private String LevelIcon;
	
	public String getNoticeID() {
		return NoticeID;
	}
	public void setNoticeID(String noticeID) {
		NoticeID = noticeID;
	}
	public String getStudentID() {
		return StudentID;
	}
	public void setStudentID(String studentID) {
		StudentID = studentID;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentHead() {
		return StudentHead;
	}
	public void setStudentHead(String studentHead) {
		StudentHead = studentHead;
	}
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	public String getCount() {
		return Count;
	}
	public void setCount(String count) {
		Count = count;
	}
	public String getNoticeName() {
		return NoticeName;
	}
	public void setNoticeName(String noticeName) {
		NoticeName = noticeName;
	}
	public String getPositionIcon() {
		return PositionIcon;
	}
	public void setPositionIcon(String positionIcon) {
		PositionIcon = positionIcon;
	}
	public String getLevelIcon() {
		return LevelIcon;
	}
	public void setLevelIcon(String levelIcon) {
		LevelIcon = levelIcon;
	}
	
}
