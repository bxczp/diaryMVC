package com.bx.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bx.entity.Diary;
import com.bx.service.DiaryService;
import com.bx.service.DiaryTypeService;
import com.bx.util.StringUtil;

/**
 * @date 2016年4月7日 DiaryController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/diary")
public class DiaryController {

	@Resource
	private DiaryService diaryService;
	@Resource
	private DiaryTypeService diaryTypeService;

	@RequestMapping("/show")
	public ModelAndView show(String diaryId) {
		ModelAndView modelAndView = new ModelAndView();
		Diary diary = diaryService.getDiaryById(Integer.parseInt(diaryId));
		diary.setTypeName(diaryTypeService.getTypeNameById(diary.getTypeId()));
		modelAndView.addObject("diary", diary);
		modelAndView.addObject("mainPage", "/diary/diaryShow.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

	@RequestMapping("/preSave")
	public ModelAndView preSave(String diaryId) {
		ModelAndView modelAndView = new ModelAndView();
		if (StringUtil.isNotEmpty(diaryId)) {
			modelAndView.addObject("diary", diaryService.getDiaryById(Integer.parseInt(diaryId)));
		}
		modelAndView.addObject("diaryTypeCountList", diaryTypeService.find());
		modelAndView.addObject("mainPage", "/diary/diaryAve.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(String diaryId) {
		ModelAndView modelAndView = new ModelAndView();
		diaryService.delete(Integer.parseInt(diaryId));
		modelAndView.setViewName("redirect:/main/all.do");
		return modelAndView;
	}

	@RequestMapping("/save")
	public ModelAndView save(String title, String content, String typeId, String diaryId) {
		Diary diary = new Diary();
		diary.setContent(content);
		diary.setTitle(title);
		diary.setTypeId(Integer.parseInt(typeId));
		if (StringUtil.isNotEmpty(diaryId)) {
			diary.setDiaryId(Integer.parseInt(diaryId));
		}
		ModelAndView modelAndView = new ModelAndView();
		if (diary.getDiaryId() != 0) {
			diaryService.update(diary);
		} else {
			diaryService.add(diary);
		}
		modelAndView.setViewName("redirect:/main/all.do");
		return modelAndView;
	}

}
