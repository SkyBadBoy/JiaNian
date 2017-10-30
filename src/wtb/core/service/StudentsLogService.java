package wtb.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wtb.core.data.StudentsLogMapper;
import wtb.core.model.StudentsLog;

 @Service
public class StudentsLogService 
{
	 @Autowired
	 private StudentsLogMapper mapper;
	 

	 @Transactional
	 public int updateStudentsLog(StudentsLog StudentsLog)
	 {
		 return mapper.updateStudentsLog(StudentsLog);
	 }
	 @Transactional
	 public int deleteStudentsLog(Map<String,Object> params)
	 {
		 return mapper.deleteStudentsLog(params);
	 }
	 @Transactional
	 public int addStudentsLog(StudentsLog obj)
	 {
		 return mapper.addStudentsLog(obj);
	 }
	
	
}
