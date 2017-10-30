package wtb.core.model;

import java.util.Date;
import java.util.List;


public class TreeModel {
	public TreeModel() {
		super();
	}
	private String ID;
	private String ParentID;
	private String Text;
	private String href;
	private List<TreeModel>nodes;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getParentID() {
		return ParentID;
	}
	public void setParentID(String parentID) {
		ParentID = parentID;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public List<TreeModel> getNodes() {
		return nodes;
	}
	public void setNodes(List<TreeModel> nodes) {
		this.nodes = nodes;
	}
	
	
	
}
