package com.bx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bx.dao.DiaryTypeDao;
import com.bx.entity.DiaryType;
import com.bx.service.DiaryTypeService;

/**
 *@date 2016年4月7日
 * DiaryTypeServiceImpl.java
 *@author CZP
 *@parameter
 */
@Service("diaryTypeService")
public class DiaryTypeServiceImpl implements DiaryTypeService {
	
	@Resource
	private DiaryTypeDao diaryTypeDao;

	@Override
	public List<DiaryType> find() {
		return diaryTypeDao.find();
	}

	@Override
	public String getTypeNameById(int id) {
		return diaryTypeDao.getTypeNameById(id);
	}

	@Override
	public int update(DiaryType diaryType) {
		return diaryTypeDao.update(diaryType);
	}

	@Override
	public int add(DiaryType diaryType) {
		return diaryTypeDao.add(diaryType);
	}

	@Override
	public int delete(int id) {
		return diaryTypeDao.delete(id);
	}

	@Override
	public DiaryType getDiaryTypeById(int id) {
		return diaryTypeDao.getDiaryTypeById(id);
	}

}
