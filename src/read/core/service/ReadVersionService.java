package read.core.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import read.core.data.ReadVersionMapper;
import wtb.core.model.Version;

@Service
public class ReadVersionService implements ReadVersionMapper{

	@Autowired
	private ReadVersionMapper mapper;

	@Override
	public List<Version> getVersionList(Map<String, Object> params) {
		return mapper.getVersionList(params);
	}

	@Override
	public int getVersionCount(Map<String, Object> params) {
		return mapper.getVersionCount(params);
	}

	@Override
	public List<Version> getVersionByIDList(Map<String, Object> params) {
		return mapper.getVersionByIDList(params);
	}
	@Override
	public int getVersionMaxNumber(Map<String, Object> params) {
		return mapper.getVersionMaxNumber(params);
	}


}
