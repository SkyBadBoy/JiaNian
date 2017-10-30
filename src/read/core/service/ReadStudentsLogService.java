package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadStudentsLogMapper;

import wtb.core.data.StudentsLogMapper;
import wtb.core.model.Students;
import wtb.core.model.StudentsLog;

 @Service
public class ReadStudentsLogService 
{
	 @Autowired
	 private ReadStudentsLogMapper mapper;
	 
	 @Transactional(readOnly = true)
	 public List<StudentsLog> getStudentsLogList(Map<String, Object> params)
	 {
		 return mapper.getStudentsLogList(params);
	 }

	
	 @Transactional
	 public int getStudentsLogCount(Map<String,Object> params)
	 {
		 return mapper.getStudentsLogCount(params);
	 }
}
