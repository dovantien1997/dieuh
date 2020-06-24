//package com.minhtien.app.controller;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.minhtien.app.model.User;
//import com.minhtien.app.model.service.UserService;
//
//@Controller
//@RequestMapping("admin")
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//	
//	@RequestMapping("")
//	public String loginUser(HttpSession session) {
//		return "admin/index";
//	}
//	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(){
//    	return "admin/user/login";
//    }
//	
//	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
//	public String signup(Model model) {
//		model.addAttribute("user", new User());
//		return "admin/user/signup";
//	}
//	
//	@RequestMapping(value = "/signup",method = RequestMethod.POST)
//	public String newUser(@Valid User user,BindingResult bindingResult) {
//		User userEx = userService.findByUserName(user.getUsername());
//		ModelAndView model = new ModelAndView();
//		if(userEx != null) {
//			bindingResult.rejectValue("username", "error.username","This username already exists!");
//		}if(bindingResult.hasErrors()) {
//    		return "admin/user/signup";
//    	}else {
//			userService.saveOrUpdate(user);
//			model.addObject("msg", "User has been registered successfully!");
//			model.addObject("user", new User());
//		}
//		return "redirect:/";
//		
//	}
//	
//	
//	
//}
