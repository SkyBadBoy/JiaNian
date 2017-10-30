package wtb.core.data;

import wtb.core.model.NoticesContent;

public interface NoticesContentMapper {
	 /**
	 * 修改记录
	 */
	 public int UpdateContent(NoticesContent NoticesContent);
	 /**
	 * 删除记录
	 */
	 public int DeleteAllContentBySrcID(long SrcID);
	 /**
	 * 添加记录
	 */
	 public int AddContent(NoticesContent Notices);
}