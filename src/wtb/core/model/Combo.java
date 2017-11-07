/*
 * 2017/10/31 hubantech Creating
 *
 * (c) Copyright hubantech Inc. All rights reserved.
 */
package wtb.core.model;


/**
 * <p> Entity Class</p>
 *
 * @author DX
 * @version 1.00
 */
public class Combo {

    /**  */
    public static final String COLUMN_combo_id = "comboId";

    /**  */
    public static final String COLUMN_combo_title = "comboTitle";
    /**  */
    public static final String COLUMN_combo_price = "comboPrice";

    /**  */
    public static final String COLUMN_combo_createtime = "comboCreatetime";

    /**  */
    public static final String COLUMN_combo_modifytime = "comboModifytime";

    /**  */
    public static final String COLUMN_combo_status = "comboStatus";

    /**  */
    public static final String COLUMN_combo_weight = "comboWeight";

    /**  */
    public static final String COLUMN_combo_best = "comboBest";

    /**  */
    public static final String COLUMN_combo_parameter1 = "comboParameter1";

    /**  */
    public static final String COLUMN_combo_parameter2 = "comboParameter2";

    /**  */
    public static final String COLUMN_combo_parameter3 = "comboParameter3";

    /**  */
    public static final String COLUMN_combo_parameter4 = "comboParameter4";

    /**  */
    public static final String COLUMN_combo_parameter5 = "comboParameter5";

    /**  */
    public static final String COLUMN_combo_parameter6 = "comboParameter6";

    /**  */
    public static final String COLUMN_combo_parameter7 = "comboParameter7";

    /**  */
    public static final String COLUMN_combo_parameter8 = "comboParameter8";

    /**  */
    public static final String COLUMN_combo_parameter9 = "comboParameter9";

    /**  */
    public static final String COLUMN_combo_parameter10 = "comboParameter10";

    /**  */
    private long comboId;
    
    private String pkid;

    /**  */
    private String comboPrice;
    
    private String comboTitle;

    /**  */
    private String comboCreatetime;

    /**  */
    private String comboModifytime;

    /**  */
    private int comboStatus;

    /**  */
    private int comboWeight;

    /**  */
    private int comboBest;

    /**  */
    private int comboParameter1;

    /**  */
    private int comboParameter2;

    /**  */
    private int comboParameter3;

    /**  */
    private int comboParameter4;

    /**  */
    private int comboParameter5;

    /**  */
    private int comboParameter6;

    /**  */
    private int comboParameter7;

    /**  */
    private int comboParameter8;

    /**  */
    private int comboParameter9;

    /**  */
    private int comboParameter10;
    
    private String Rand;
    
    
    /**  */
    private String comboParameter1Str;

    /**  */
    private String comboParameter2Str;

    /**  */
    private String comboParameter3Str;

    /**  */
    private String comboParameter4Str;

    /**  */
    private String comboParameter5Str;

    /**  */
    private String comboParameter6Str;

    /**  */
    private String comboParameter7Str;

    /**  */
    private String comboParameter8Str;

    /**  */
    private String comboParameter9Str;

    /**  */
    private String comboParameter10Str;

    
	public String getPkid() {
		return String.valueOf(comboId);
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public long getComboId() {
		return comboId;
	}

	public void setComboId(long comboId) {
		this.comboId = comboId;
	}

	public String getComboPrice() {
		return comboPrice;
	}

	public void setComboPrice(String comboPrice) {
		this.comboPrice = comboPrice;
	}

	public String getComboTitle() {
		return comboTitle;
	}

	public void setComboTitle(String comboTitle) {
		this.comboTitle = comboTitle;
	}

	public String getComboCreatetime() {
		return comboCreatetime;
	}

	public void setComboCreatetime(String comboCreatetime) {
		this.comboCreatetime = comboCreatetime;
	}

	public String getComboModifytime() {
		return comboModifytime;
	}

	public void setComboModifytime(String comboModifytime) {
		this.comboModifytime = comboModifytime;
	}

	public int getComboStatus() {
		return comboStatus;
	}

	public void setComboStatus(int comboStatus) {
		this.comboStatus = comboStatus;
	}

	public int getComboWeight() {
		return comboWeight;
	}

	public void setComboWeight(int comboWeight) {
		this.comboWeight = comboWeight;
	}

	public int getComboBest() {
		return comboBest;
	}

	public void setComboBest(int comboBest) {
		this.comboBest = comboBest;
	}

	public int getComboParameter1() {
		return comboParameter1;
	}

	public void setComboParameter1(int comboParameter1) {
		this.comboParameter1 = comboParameter1;
	}

	public int getComboParameter2() {
		return comboParameter2;
	}

	public void setComboParameter2(int comboParameter2) {
		this.comboParameter2 = comboParameter2;
	}

	public int getComboParameter3() {
		return comboParameter3;
	}

	public void setComboParameter3(int comboParameter3) {
		this.comboParameter3 = comboParameter3;
	}

	public int getComboParameter4() {
		return comboParameter4;
	}

	public void setComboParameter4(int comboParameter4) {
		this.comboParameter4 = comboParameter4;
	}

	public int getComboParameter5() {
		return comboParameter5;
	}

	public void setComboParameter5(int comboParameter5) {
		this.comboParameter5 = comboParameter5;
	}

	public int getComboParameter6() {
		return comboParameter6;
	}

	public void setComboParameter6(int comboParameter6) {
		this.comboParameter6 = comboParameter6;
	}

	public int getComboParameter7() {
		return comboParameter7;
	}

	public void setComboParameter7(int comboParameter7) {
		this.comboParameter7 = comboParameter7;
	}

	public int getComboParameter8() {
		return comboParameter8;
	}

	public void setComboParameter8(int comboParameter8) {
		this.comboParameter8 = comboParameter8;
	}

	public int getComboParameter9() {
		return comboParameter9;
	}

	public void setComboParameter9(int comboParameter9) {
		this.comboParameter9 = comboParameter9;
	}

	public int getComboParameter10() {
		return comboParameter10;
	}

	public void setComboParameter10(int comboParameter10) {
		this.comboParameter10 = comboParameter10;
	}

	public String getComboParameter1Str() {
		if (comboParameter1==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter1Str(String comboParameter1Str) {
		this.comboParameter1Str = comboParameter1Str;
	}

	public String getComboParameter2Str() {
		if (comboParameter2==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter2Str(String comboParameter2Str) {
		this.comboParameter2Str = comboParameter2Str;
	}

	public String getComboParameter3Str() {
		if (comboParameter3==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter3Str(String comboParameter3Str) {
		this.comboParameter3Str = comboParameter3Str;
	}

	public String getComboParameter4Str() {
		if (comboParameter4==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter4Str(String comboParameter4Str) {
		this.comboParameter4Str = comboParameter4Str;
	}

	public String getComboParameter5Str() {
		if (comboParameter5==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter5Str(String comboParameter5Str) {
		this.comboParameter5Str = comboParameter5Str;
	}

	public String getComboParameter6Str() {
		if (comboParameter6==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter6Str(String comboParameter6Str) {
		this.comboParameter6Str = comboParameter6Str;
	}

	public String getComboParameter7Str() {
		if (comboParameter7==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter7Str(String comboParameter7Str) {
		this.comboParameter7Str = comboParameter7Str;
	}

	public String getComboParameter8Str() {
		if (comboParameter8==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter8Str(String comboParameter8Str) {
		this.comboParameter8Str = comboParameter8Str;
	}

	public String getComboParameter9Str() {
		if (comboParameter9==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter9Str(String comboParameter9Str) {
		this.comboParameter9Str = comboParameter9Str;
	}

	public String getComboParameter10Str() {
		if (comboParameter10==1) {
			return "有";
		}else {
			return "没有";
		}
	}

	public void setComboParameter10Str(String comboParameter10Str) {
		this.comboParameter10Str = comboParameter10Str;
	}

	public String getRand() {
		return Rand;
	}

	public void setRand(String rand) {
		Rand = rand;
	}
    

   
    
    

}
