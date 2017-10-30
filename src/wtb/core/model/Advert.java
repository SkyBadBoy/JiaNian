package wtb.core.model;

import java.util.Date;
import java.util.List;

import wtb.smUtil.SmBaseGlobal;
import wtb.smUtil.SmBaseGlobal.AdvertType;
import wtb.smUtil.SmBaseUtil;


public class Advert {
	public Advert() {
		super();
	}
	public static final String AdvertClass = "advert";
    public static final String attributeID = "id";
    public static final String attributeTitle = "title";
    public static final String attributeCreateTime = "createtime";
    public static final String attributeModifyTime = "modifytime";
    public static final String attributeBeginTime = "starttime";
    public static final String attributeEndTime = "endtime";
    public static final String attributeStatus = "status";
    public static final String attributeMemo = "memo";
    public static final String attributeUrl = "url";
    public static final String attributeType = "type";
    public static final String attributeIndex = "index";
    public static final String attributeImageUrl = "imageurl";
	private long id;
	private String pkid;
	private String title;
	private String memo;
	private int status;
	private Date create_time;
	private Date modify_time;
	private int index;
	private int type;
	private String url;
	private String begin_time;
	private String end_time;
	private String imageurl;
	
	/**
	 * 说明性字段 不参与业务逻辑处理和数据库增加
	 */
	private String status_name;
	private String type_name;
	private String imageurl_name;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the pkid
	 */
	public String getPkid() {
		return pkid;
	}
	/**
	 * @param pkid the pkid to set
	 */
	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the modify_time
	 */
	public Date getModify_time() {
		return modify_time;
	}
	/**
	 * @param modify_time the modify_time to set
	 */
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the begin_time
	 */
	public String getBegin_time() {
		return begin_time;
	}
	/**
	 * @param begin_time the begin_time to set
	 */
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	/**
	 * @return the typename
	 */
	public String getType_name() {
		if (type==SmBaseGlobal.AdvertType.AppLoading.getid()) {
			return SmBaseGlobal.AdvertType.AppLoading.getName();
		}else if (type==SmBaseGlobal.AdvertType.APPHome.getid()) {
			return SmBaseGlobal.AdvertType.APPHome.getName();
		}else if (type==SmBaseGlobal.AdvertType.NewDetail.getid()) {
			return SmBaseGlobal.AdvertType.NewDetail.getName();
		}else if (type==SmBaseGlobal.AdvertType.PCBannerLeft.getid()) {
			return SmBaseGlobal.AdvertType.PCBannerLeft.getName();
		}else if (type==SmBaseGlobal.AdvertType.PCBannerQR.getid()) {
			return SmBaseGlobal.AdvertType.PCBannerQR.getName();
		}else if (type==SmBaseGlobal.AdvertType.PCBannerRight.getid()) {
			return SmBaseGlobal.AdvertType.PCBannerRight.getName();
		}
		return type_name;
	}
	/**
	 * @param typename the typename to set
	 */
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	/**
	 * @return the imageurl
	 */
	public String getImageurl() {
		return imageurl;
	}
	/**
	 * @param imageurl the imageurl to set
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	/**
	 * @return the imageurl_a
	 */
	public String getImageurl_a() {
		return  SmBaseUtil.getClickImageHtml(imageurl) ;
	}
	/**
	 * @param imageurl_a the imageurl_a to set
	 */
	public void setImageurl_a(String imageurl_a) {
		this.imageurl_name = imageurl_a;
	}
	
	public static Advert Advert(String imageUrl){
		Advert Advert=new Advert();
		Advert.setId(SmBaseUtil.CreateNewID());
		Advert.setStatus(SmBaseGlobal.CheckStatus.Apply.getid());
		Advert.setImageurl(imageUrl);
		return Advert;
	}
	/**
	 * @return the status_name
	 */
	public String getStatus_name() {
		if (status==SmBaseGlobal.CheckStatus.Effective.getid()) {
			return "已发布";
		}else if (status==SmBaseGlobal.CheckStatus.Apply.getid()) {
			return "待发布";
		}else if (status==SmBaseGlobal.CheckStatus.Disabled.getid()) {
			return "删除";
		}
		return status_name;
	}
	/**
	 * @param status_name the status_name to set
	 */
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
	
	
	
	
}
