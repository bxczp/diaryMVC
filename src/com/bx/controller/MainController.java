package com.bx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bx.entity.Diary;
import com.bx.entity.PageBean;
import com.bx.service.DiaryService;
import com.bx.service.DiaryTypeService;
import com.bx.util.PageUtil;
import com.bx.util.PropertiesUtil;
import com.bx.util.StringUtil;

/**
 * @date 2016年4月7日 MainController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/main")
public class MainController {

	@Resource
	private DiaryTypeService diaryTypeService;
	@Resource
	private DiaryService diaryService;

	@RequestMapping("/all")
	public ModelAndView all(String page, String all, String s_title, String diary_typeId, String diary_releaseDateStr,
			HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		if (StringUtil.isNotEmpty(diary_releaseDateStr)) {
			diary_releaseDateStr = diary_releaseDateStr.split("年")[0] + diary_releaseDateStr.split("年")[1].split("月")[0];
			diary_releaseDateStr=StringUtil.formatLike(diary_releaseDateStr);
		}
		if(StringUtil.isNotEmpty(s_title)){
			s_title=StringUtil.formatLike(s_title);
		}
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		if (StringUtil.isNotEmpty(all)) {
			if (StringUtil.isNotEmpty(s_title)) {
				session.setAttribute("s_title", s_title);
				map.put("s_title", s_title);
			}
			session.removeAttribute("diary_typeId");
			session.removeAttribute("diary_releaseDateStr");
		} else {
			if (StringUtil.isNotEmpty(diary_releaseDateStr)) {
				session.setAttribute("diary_releaseDateStr", diary_releaseDateStr);
				session.removeAttribute("s_title");
				session.removeAttribute("diary_typeId");
				map.put("s_releaseDateStr", diary_releaseDateStr);
			}
			if (StringUtil.isNotEmpty(diary_typeId)) {
				session.setAttribute("diary_typeId", diary_typeId);
				session.removeAttribute("s_title");
				session.removeAttribute("diary_releaseDateStr");
				map.put("s_typeId", diary_typeId);
			}
			if (StringUtil.isEmpty(s_title) && session.getAttribute("s_title") != null) {
				s_title = (String) session.getAttribute("s_title");
				map.put("s_title", s_title);
			}
			if (StringUtil.isEmpty(diary_typeId) && session.getAttribute("diary_typeId") != null) {
				diary_typeId = (String) session.getAttribute("diary_typeId");
				map.put("s_typeId", diary_typeId);
			}
			if (StringUtil.isEmpty(diary_releaseDateStr) && session.getAttribute("diary_releaseDateStr") != null) {
				diary_releaseDateStr = (String) session.getAttribute("diary_releaseDateStr");
				map.put("s_releaseDateStr", diary_releaseDateStr);
			}
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		map.put("size", pageBean.getPageSize());
		map.put("start", pageBean.getStart());
		modelAndView.addObject("diaryList", diaryService.find(map));
		modelAndView.addObject("diaryCountList", diaryService.findByRelaseDate());
		modelAndView.addObject("diaryTypeCountList", diaryTypeService.find());
		session.setAttribute("diaryCountList", diaryService.findByRelaseDate());
		session.setAttribute("diaryTypeCountList", diaryTypeService.find());
		int totalNum = diaryService.getListCount(map);
		modelAndView.addObject("pageCode", PageUtil.genPagation(totalNum, Integer.parseInt(page),
				Integer.parseInt(PropertiesUtil.getValue("pageSize")), request));
		modelAndView.addObject("mainPage", "/diary/diaryList.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

}
