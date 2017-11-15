package read.core.data;
import java.util.*;

import wtb.core.model.Pictures;
import wtb.core.model.ProdPictures;

/**
 * wtb_Picture
 */
public interface ReadProdPictureMapper {
	 /**
	 * 查询列表,不做关联的
	 */
	 public List<ProdPictures> getPictureByIDList(Map<String, Object> params);
	 public List<ProdPictures> getPictureList(Map<String, Object> params);
	 
	 public List<ProdPictures> getPicturePhoneList();
	 
	 public int getPictureCount(Map<String, Object> params);
}