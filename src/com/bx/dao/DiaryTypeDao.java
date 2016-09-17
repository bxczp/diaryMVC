package com.bx.dao;

import java.util.List;

import com.bx.entity.DiaryType;

/**
 *@date 2016年4月7日
 * DiaryTypeDao.java
 *@author CZP
 *@parameter
 */
public interface DiaryTypeDao {
	
	public List<DiaryType> find();
	
	public String getTypeNameById(int id);
	
	public DiaryType getDiaryTypeById(int id);
	
	public int update(DiaryType diaryType);
	
	public int add(DiaryType diaryType);
	
	public int delete(int id);

}
