package wtb.core.data;
import java.util.*;

import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;

/**
 * wtb_Picture
 */
public interface ProdPictureMapper {
	/**
	 * 查询全部
	 */
	 public List<ProdPictures> getPictureList(Map<String, Object> params);
	 /**
	 * 查询列表,不做关联的
	 */
	 public List<ProdPictures> getPictureForList(Map<String, Object> params);
	 
	 /**
	 * 修改记录
	 */
	 public int updatePicture(ProdPictures Picture);
	 /**
	  * 用于更新一些简单的几个字段
	  * @param Picture
	  * @return
	  */
	 public int UpdateSimplePicture(ProdPictures Picture);
	 
	 /**
	 * 停用
	 */
	 public int deletePicture(Map<String,Object> params);
	 /**
	 * 添加记录
	 */
	 public int addPicture(ProdPictures Picture);
	 
	 /**
	  * 启用
	  * @param params
	  * @return
	  */
	 public int enabledPicture(Map<String,Object> params);

	 public int getPictureCount(Map<String,Object> params);
	 
	 public List<ProdPictures> getPictureEffList(Map<String, Object> params);
	 
	 public int getPictureEffCount(Map<String,Object> params);
	 
	 public int DeleteAllPicByProductID(long NoticeID);
	 
}