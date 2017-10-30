package wtb.core.service;

import wtb.core.data.NoticesContentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wtb.core.model.NoticesContent;

 @Service
public class NoticesContentService implements NoticesContentMapper {
	/**
	 * 查询全部
	 */
	 @Autowired
	private NoticesContentMapper mapper;

	/* 
	 * @see wtb.core.data.NoticesContentMapper#UpdateContent(wtb.core.model.NoticesContent)
	 */
	@Override
	@Transactional
	public int UpdateContent(NoticesContent NoticesContent) {
		return mapper.UpdateContent(NoticesContent);
	}

	/* 
	 * @see wtb.core.data.NoticesContentMapper#DeleteAllContentBySrcID(long)
	 */
	@Override
	@Transactional
	public int DeleteAllContentBySrcID(long SrcID) {
		return mapper.DeleteAllContentBySrcID(SrcID);
	}

	/* 
	 * @see wtb.core.data.NoticesContentMapper#AddContent(wtb.core.model.NoticesContent)
	 */
	@Override
	@Transactional
	public int AddContent(NoticesContent Notices) {
		return mapper.AddContent(Notices);
	}



}