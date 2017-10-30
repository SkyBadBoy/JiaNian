package read.core.service;
import java.util.*;

import read.core.data.ReadNoticesContentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.NoticesContent;

/**
 * wtb_vitaes
 */
 @Service
public class ReadNoticesContentService implements ReadNoticesContentMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadNoticesContentMapper mapper;

	/* 
	 * @see read.core.data.ReadNoticesContentMapper#getReadContentList(java.util.Map)
	 */
	@Override
	@Transactional
	public List<NoticesContent> getReadContentList(Map<String, Object> params) {
		return mapper.getReadContentList(params);
	}

	/* 
	 * @see read.core.data.ReadNoticesContentMapper#getContentByID(long)
	 */
	@Override
	@Transactional
	public NoticesContent getContentByID(long ContentID) {
		return mapper.getContentByID(ContentID);
	}
	
}