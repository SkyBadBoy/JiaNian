package wtb.core.service;
import java.util.*;

import wtb.core.data.StudentsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.Students;

/**
 * wtb_vitaes
 */
 @Service
public class StudentsService {
	/**
	 * 查询全部
	 */
	 @Autowired
	private StudentsMapper mapper;


	@Transactional
	public int addStudents(Students obj) {
		return mapper.addStudents(obj);
	}

	@Transactional
	public int updateStudents(Students obj) {
		return mapper.updateStudents(obj);
	}

	@Transactional
	public int deleteStudents(Map<String,Object> params) {
		return mapper.deleteStudents(params);
	}
	
	@Transactional
	public int enabledStudents(Map<String,Object> params) {
		return mapper.enabledStudents(params);
	}
	@Transactional
	public int ChangePassword(Students obj) {
		return mapper.ChangePassword(obj);
	}

	@Transactional
	public int updateStudentsForOpenID(Students Students){
		return mapper.updateStudentsForOpenID(Students);
	}
	@Transactional
	public int updateLevelStudents(Students Students){
		return mapper.updateLevelStudents(Students);
	}
	@Transactional
	public int UpNoticeCount(long Sid){
		return mapper.UpNoticeCount(Sid);
	}
	
	@Transactional
	public int CancelNoticeCount(long Sid){
		return mapper.CancelNoticeCount(Sid);
	}
	
	@Transactional
	public int UpdateOfficial(long Sid){
		return mapper.UpdateOfficial(Sid);
	}
	
	@Transactional
	public int CancelOfficial(long Sid){
		return mapper.CancelOfficial(Sid);
	}
	
	@Transactional
	public int UpdateChief(Map<String,Object> params) {
		return mapper.UpdateChief(params);
	}
	 
	@Transactional
	public int UpHonorCount(long Sid){
		return mapper.UpHonorCount(Sid);
	}
	
	@Transactional
	public int CancelHonorCount(long Sid){
		return mapper.CancelHonorCount(Sid);
	}
	
	@Transactional
	public int UpSendCount(long Sid){
		return mapper.UpSendCount(Sid);
	}
	
	@Transactional
	public int UupdateBirthDayRemind(long Sid){
		return mapper.UupdateBirthDayRemind(Sid);
	}
	
}