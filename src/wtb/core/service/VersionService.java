package wtb.core.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wtb.core.data.VersionMapper;
import wtb.core.model.Version;

@Service
public class VersionService implements VersionMapper{

	@Autowired
	private VersionMapper mapper;
		
	@Override
	public int updateVersion(Version version) {
		return mapper.updateVersion(version);
	}

	@Override
	public int deleteVersion(Map<String, Object> params) {
		return mapper.deleteVersion(params);
	}

	@Override
	public int addVersion(Version version) {
		return mapper.addVersion(version);
	}

	@Override
	public int enabledVersion(Version version) {
		return mapper.enabledVersion(version);
	}

}
