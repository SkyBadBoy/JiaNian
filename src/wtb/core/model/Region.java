package wtb.core.model;

import java.util.*;
/**
 * wtb_region
 */
@SuppressWarnings("serial")
public class Region implements java.io.Serializable{
	/**
	 * REGION_ID
	 */
	public String ID;
	public String text;
	/**
	 * REGION_CODE
	 */
	public String Code;
	/**
	 * REGION_NAME
	 */
	public String Name;
	/**
	 * PARENT_ID
	 */
	public String ParentID;
	/**
	 * REGION_LEVEL
	 */
	public int Level;
	/**
	 * REGION_ORDER
	 */
	public int Order;
	/**
	 * REGION_NAME_EN
	 */
	public String Name_En;
	/**
	 * REGION_SHORTNAME_EN
	 */
	public String ShortName_En;
	
	public List<Region> Childs;

	public Region() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getParentID() {
		return ParentID;
	}

	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public int getOrder() {
		return Order;
	}

	public void setOrder(int order) {
		Order = order;
	}

	public String getName_En() {
		return Name_En;
	}

	public void setName_En(String name_En) {
		Name_En = name_En;
	}

	public String getShortName_En() {
		return ShortName_En;
	}

	public void setShortName_En(String shortName_En) {
		ShortName_En = shortName_En;
	}

	public List<Region> getChilds() {
		return Childs;
	}

	public void setChilds(List<Region> childs) {
		Childs = childs;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	
	
	
}
