package wtb.core.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import wtb.smUtil.SmBaseUtil;

/**
 * Created by MeetLucky on 16/5/17.
 */
@SuppressWarnings("unchecked")
public class KeyWord { // 关键词
    //  属性名称
    public static final String keyWordClass = "keyWord";
    public static final String attributeKeyWordID = "iD";
    public static final String attributeKeyWordTypeID = "typeID";
    public static final String attributeKeyWordKeyWord = "keyWord";
    public static final String attributeKeyWordCreateTime = "createTime";
    public static final String attributeKeyWordModifyTime = "modifyTime";
    public static final String attributeKeyWordVersion = "version";
    public static final String attributeKeyWordStatus = "status";
    public static final String attributeKeyWordDeleted = "deleted";
    public static final String attributeKeyWordVoteID = "VoteID";
    //  关键信息
    private long iD;//键词编号
    private String pkid;
    private long typeID;//键词类型
    private String keyWord;//关键词语
    private Date createTime;//创建时间
    private Date modifyTime;//修改时间
    private long VoteID;

    //  结构信息
    @JSONField(serialize=false)
    private int version;//修改次数

    private int status;//状态信息
    @JSONField(serialize=false)
    private int deleted;//是否删除
    private long count;//是否删除


    public KeyWord() {
        super();
        this.iD= SmBaseUtil.CreateNewID();
    }

    public KeyWord(long TypeID) {
        super();
        this.typeID = TypeID;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid;
    }

    public long getID() {
        return iD;
    }

    public void setID(long iD) {
        this.iD = iD;
    }

    public long getTypeID() {
        return typeID;
    }

    public void setTypeID(long typeID) {
        this.typeID = typeID;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

	public long getVoteID() {
		return VoteID;
	}

	public void setVoteID(long voteID) {
		VoteID = voteID;
	}
    
}
