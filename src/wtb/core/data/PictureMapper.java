package wtb.core.data;
import java.util.*;

import wtb.core.model.Pictures;

/**
 * wtb_Picture
 */
public interface PictureMapper {
	/**
	 * 查询全部
	 */
	 public List<Pictures> getPictureList(Map<String, Object> params);
	 /**
	 * 修改记录
	 */
	 public int updatePicture(Pictures Picture);
	 /**
	 * 停用
	 */
	 public int deletePicture(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addPicture(Pictures Picture);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledPicture(Map<String,Object> params);
	 
	 public List<Pictures> getPictureEffList(Map<String, Object> params);
	 
	 public int getPictureEffCount(Map<String,Object> params);

}