package com.bx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bx.entity.DiaryType;
import com.bx.service.DiaryTypeService;
import com.bx.util.StringUtil;

/**
 * @date 2016年4月7日 DiaryTypeController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/diaryType")
public class DiaryTypeController {

	@Resource
	private DiaryTypeService diaryTypeService;

	@RequestMapping("/preSave")
	public ModelAndView preSave(String diaryTypeId, String typeName) {
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isNotEmpty(diaryTypeId)) {
			DiaryType diaryType = diaryTypeService.getDiaryTypeById(Integer.parseInt(diaryTypeId));
			modelAndView.addObject("diaryType", diaryType);
		}
		modelAndView.addObject("mainPage", "diaryType/diaryTypeAve.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

	@RequestMapping("/save")
	public ModelAndView save(String diaryTypeId, String typeName) {
		DiaryType diaryType = new DiaryType();
		diaryType.setTypeName(typeName);
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isNotEmpty(diaryTypeId)) {
			diaryType.setDiaryTypeId(Integer.parseInt(diaryTypeId));
			diaryTypeService.update(diaryType);
		} else {
			diaryTypeService.add(diaryType);
		}
		modelAndView.setViewName("redirect:/main/all.do");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(String diaryTypeId) {
		ModelAndView modelAndView = new ModelAndView();
		diaryTypeService.delete(Integer.parseInt(diaryTypeId));
		modelAndView.setViewName("redirect:/main/all.do");
		return modelAndView;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		List<DiaryType> diaryTypeList = new ArrayList<>();
		diaryTypeList = diaryTypeService.find();
		modelAndView.addObject("diaryTypeList", diaryTypeList);
		modelAndView.addObject("mainPage", "/diaryType/diaryTypeList.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

}
