package com.bx.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.DiaryDao;
import com.bx.entity.Diary;
import com.bx.service.DiaryService;

/**
 *@date 2016年4月7日
 * DiaryServiceImpl.java
 *@author CZP
 *@parameter
 */
@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {
	
	@Resource
	private DiaryDao diaryDao;

	@Override
	public List<Diary> find(Map<String, Object> map) {
		return diaryDao.find(map);
	}

	@Override
	public int getListCount(Map<String, Object> map) {
		return diaryDao.getListCount(map);
	}

	@Override
	public List<Diary> findByRelaseDate() {
		return diaryDao.findByRelaseDate();
	}

	@Override
	public Diary getDiaryById(int id) {
		return diaryDao.getDiaryById(id);
	}

	@Override
	public int update(Diary diary) {
		return diaryDao.update(diary);
	}

	@Override
	public int add(Diary diary) {
		return diaryDao.add(diary);
	}

	@Override
	public int delete(int id) {
		return diaryDao.delete(id);
	}

}
