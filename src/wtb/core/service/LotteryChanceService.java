package wtb.core.service;

import wtb.core.data.LotteryChanceMapper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.LotteryChance;

@Service
public class LotteryChanceService {
	
	 @Autowired
	 private LotteryChanceMapper mapper;
	 
	 @Transactional
	 public int AddNewMessage(LotteryChance record) {
		 return mapper.AddNewMessage(record);
	 }
	 
	 @Transactional
	 public int AddChanceCount(Map<String, Object> map) {
		 return mapper.AddChanceCount(map);
	 }

	
}