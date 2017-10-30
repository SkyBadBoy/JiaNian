package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import read.core.data.ReadErrorStatMapper;
import wtb.core.data.ErrorStatMapper;
import wtb.core.model.ErrorStat;

@Service
public class ReadErrorStatService {
	@Autowired
	private ReadErrorStatMapper mapper;
	@Transactional(readOnly = true)
	public List<ErrorStat> getErrorStatList(Map<String, Object> map)
	{
		return mapper.getErrorStatList(map);
	}
	@Transactional
	public int getErrorStatCount(Map<String, Object> map){
		return mapper.getErrorStatCount(map);
	}
	
}
