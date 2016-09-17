package com.bx.service;

import java.util.List;
import java.util.Map;

import com.bx.entity.Diary;

/**
 * @date 2016年4月7日 DiaryDao.java
 * @author CZP
 * @parameter
 */
public interface DiaryService {

	public List<Diary> find(Map<String, Object> map);

	public int getListCount(Map<String, Object> map);

	public List<Diary> findByRelaseDate();

	public Diary getDiaryById(int id);

	public int update(Diary diary);

	public int add(Diary diary);
	public int delete(int id);
}
