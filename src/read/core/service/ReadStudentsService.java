package read.core.service;
import java.util.*;

import wtb.core.data.StudentsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadStudentsMapper;
import wtb.core.model.Students;
import wtb.core.model.WeChatLastMonthStatInfo;
import wtb.core.model.WeChatStatInfo;

/**
 * wtb_vitaes
 */
 @Service
public class ReadStudentsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadStudentsMapper mapper;

	@Transactional(readOnly = true)
	public List<Students> getStudentsList(Map<String,Object> params) {
		return mapper.getStudentsList(params);
	}
	
	@Transactional
	public int getStudentsCount(Map<String,Object> params){
		return mapper.getStudentsCount(params);
	}
	
	@Transactional(readOnly = true)
	public WeChatStatInfo getStudentsByDaysList(Map<String,Object> params) {
		return mapper.getStudentsByDaysList(params);
	}
	
	@Transactional(readOnly = true)
	public List<WeChatLastMonthStatInfo> getStudentsMonthStat(Map<String,Object> params) {
		return mapper.getStudentsMonthStat(params);
	}

	@Transactional(readOnly = true)
	public List<Students> getStudentsAllNoticesList(Map<String,Object> params) {
		return mapper.getStudentsAllNoticesList(params);
	}
	
	@Transactional(readOnly = true)
	public List<WeChatLastMonthStatInfo> getStudentsForDayStat(Map<String,Object> params) {
		return mapper.getStudentsForDayStat(params);
	}
	
}