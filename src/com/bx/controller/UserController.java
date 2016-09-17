package com.bx.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bx.entity.User;
import com.bx.service.UserService;
import com.bx.util.MD5Util;
import com.bx.util.StringUtil;

/**
 * @date 2016年4月7日 UserController.java
 * @author CZP
 * @parameter
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * modelAndView 和 String 都可以实现 重定向到另一个请求时都要写 redirect:XXXXXX 
	 * public String login(User user,HttpServletRequest request) throws Exception{ HttpSession
	 * session=request.getSession(); Map<String,Object> map=new HashMap<>();
	 * map.put("userName", user.getUserName()); map.put("password",
	 * MD5Util.EncoderPwdByMd5(user.getPassword())); User
	 * result=userService.login(map); if(result==null){
	 * request.setAttribute("error", "用户名或密码错误"); request.setAttribute("user",
	 * user); return "login"; }else{ session.setAttribute("currentUser",
	 * result); return "redirect:/main/all.do"; } }
	 */
	@RequestMapping("/login")
	public ModelAndView login(User user, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession session = request.getSession();
		Map<String, Object> map = new HashMap<>();
		map.put("userName", user.getUserName());
		map.put("password", MD5Util.EncoderPwdByMd5(user.getPassword()));
		User result = userService.login(map);
		if (result == null) {
			request.setAttribute("error", "用户名或密码错误");
			request.setAttribute("user", user);
			modelAndView.setViewName("login");
			return modelAndView;
		} else {
			session.setAttribute("currentUser", result);
			modelAndView.setViewName("redirect:/main/all.do");
			return modelAndView;
		}
	}

	@RequestMapping("/preSave")
	public ModelAndView preSave() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mainPage", "/user/userAve.jsp");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

	// 上传文件的实例代码
	// @RequestMapping("/upload")
	// public String uploadFile(@RequestParam("file1") MultipartFile file1,
	// HttpServletRequest request) throws Exception {
	// 获取根目录
	// String filePath = request.getServletContext().getRealPath("/");
	// System.out.println(filePath);
	// 注意有 斜杠 getOriginalFilename() 获取 原文件名 
	// file1.transferTo(new File(filePath + "upload/" +
	// file1.getOriginalFilename()));
	// return "redirect:success.html";
	// }

	/**
	 * 一定要 指明 methed！！！！！ method = RequestMethod.POST
	 * 
	 * @param imagePath
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public ModelAndView upload(@RequestParam(value = "imagePath", required = false) MultipartFile file,
			HttpServletRequest request, User user) throws Exception {
		// 添加Commons-fileupload.jar
		// if (!file.isEmpty()) {
		if (file != null) {
			// 上传的目录
			String realPath = request.getServletContext().getRealPath("/userImages");
			System.out.println("realPath:" + realPath);
			// 上传的文件名
			String fileName = file.getOriginalFilename();
			System.out.println("fileName:" + fileName);
			String imageName = StringUtil.getCurrentDateToString() + "." + fileName.split("\\.")[1];
			System.out.println("imageName ：" + imageName);
			user.setImageName(imageName);
			File saveFile = new File(realPath, imageName);
			// if (!saveFile.exists()) {
			// saveFile.mkdirs();
			// }
			file.transferTo(saveFile);
		} else {
			user.setImageName("");
		}
		userService.update(user);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

}
