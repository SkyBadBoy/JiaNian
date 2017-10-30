package read.core.service;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadLogFileMapper;
import wtb.core.model.LogFile;

/**
 * wtb_vitaes
 */
 @Service
public class ReadLogFileService implements ReadLogFileMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadLogFileMapper mapper;

	@Override
	@Transactional(readOnly = true)
	public List<LogFile> getLogFileList(Map<String,Object> params) {
		return mapper.getLogFileList(params);
	}
	
	@Override
	@Transactional
	public int getLogFileCount(Map<String,Object> params){
		return mapper.getLogFileCount(params);
	}

	
}