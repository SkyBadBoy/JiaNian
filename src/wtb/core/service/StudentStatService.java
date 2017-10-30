package wtb.core.service;
import java.util.*;

import wtb.core.data.StudentStatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.StudentStat;

/**
 * wtb_vitaes
 */
 @Service
public class StudentStatService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private StudentStatMapper mapper;

	@Transactional
	public int addStudentStat(StudentStat obj) {
		return mapper.addStudentStat(obj);
	}

	@Transactional
	public int updateStudentStat(StudentStat obj) {
		return mapper.updateStudentStat(obj);
	}

	@Transactional
	public int deleteStudentStat(Map<String,Object> params) {
		return mapper.deleteStudentStat(params);
	}
}