package wtb.core.service;
import java.util.*;

import wtb.core.data.ActivityMapper;
import wtb.core.data.LogFileMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Activity;
import wtb.core.model.LogFile;

/**
 * wtb_vitaes
 */
 @Service
public class LogFileService implements LogFileMapper{
	/**
	 * 查询全部
	 */
	 @Autowired
	private LogFileMapper mapper;

	/* 
	 * @see wtb.core.data.LogFileMapper#updateLogFile(wtb.core.model.LogFile)
	 */
	@Override
	@Transactional
	public int updateLogFile(LogFile LogFile) {
		return mapper.updateLogFile(LogFile);
	}

	/* 
	 * @see wtb.core.data.LogFileMapper#deleteLogFile(java.util.Map)
	 */
	@Override
	@Transactional
	public int deleteLogFile(Map<String, Object> params) {
		return mapper.deleteLogFile(params);
	}

	/* 
	 * @see wtb.core.data.LogFileMapper#addLogFile(wtb.core.model.LogFile)
	 */
	@Override
	@Transactional
	public int addLogFile(LogFile LogFile) {
		return mapper.addLogFile(LogFile);
	}


	 
}