package com.example.springbootmybatis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootmybatis.entity.User;
import com.example.springbootmybatis.service.UserService;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	private static final String SUCCESS = "success";
	@Autowired
	private UserService userService;
	
	@ModelAttribute
	public void getUserById(@RequestParam(value="id", required=false) Integer id, Model model){
		if(id!=null){
			User user = userService.getUserById(id);
			model.addAttribute("user", user);
		}
	}

	@RequestMapping("/userManage")
	public ModelAndView userManage(){
		ModelAndView modelAndView = new ModelAndView("user/userManage");
		// modelAndView.addObject("message", "你好！");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public User getUserById(@PathVariable Integer id){
		return userService.getUserById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String save(User user){
		userService.save(user);
		return SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value="/user", method=RequestMethod.PUT)
	public String update(@ModelAttribute("user") User user){
		userService.update(user);
		return SUCCESS;
	}
	
	@ResponseBody
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id){
		userService.delete(id);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getUsers")
	@SuppressWarnings("serial")
	public Map<String, Object> getUsers(HttpServletRequest request){
		int pageNO = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		Map<String, Object> params = new HashMap<>();
		params.put("pageNO", (pageNO-1)*pageSize);
		params.put("pageSize", pageSize);
		List<User> users = userService.getUsers(params);
		long count = userService.getUsersCount(params);
		return new HashMap<String, Object>(){
			{
				put("rows", users);
				put("total", count);
			}
		};
	}
	
}
