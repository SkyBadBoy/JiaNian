package read.core.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadStudentStatMapper;
import wtb.core.model.StudentStat;

/**
 * wtb_vitaes
 */
 @Service
public class ReadStudentStatService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private ReadStudentStatMapper mapper;

	@Transactional(readOnly = true)
	public List<StudentStat> getStudentStatList(Map<String,Object> params) {
		return mapper.getStudentStatList(params);
	}
	
	@Transactional(readOnly = true)
	public long getStudentStatCount(Map<String,Object> params) {
		return mapper.getStudentStatCount(params);
	}
	@Transactional
	public StudentStat getStudentStatListByID(long params){
		return mapper.getStudentStatListByID(params);
	}
}