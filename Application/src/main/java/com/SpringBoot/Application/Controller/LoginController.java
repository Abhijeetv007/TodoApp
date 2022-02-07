package com.SpringBoot.Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SpringBoot.Application.Service.LoginService;


@Controller
//@SessionAttributes("name")
public class LoginController {
	//I have to think view resolver because dispatcher servlet getting "/login" resquest
	//View Resolver
	//And tomcat jasper dependencies need for redirect to jsp and view the content on the chrome
	
	@Autowired
	LoginService service; //Bean //We are not using Login service any more due to Spring security
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String hello(ModelMap model )
	{
		model.put("name", getUserLogged());
		return "welcome";
	}
	
	public String getUserLogged()
	{
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails)
		{
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

	//	@RequestMapping(value="/login", method = RequestMethod.POST)
	//	public String pageLogin(ModelMap model, @RequestParam String name,@RequestParam String password)
	//	{
	//		boolean check=service.validate(name,password);
	//		if(check==false)
	//		{
	//			model.put("errorMsg", "Wrong Credential");
	//			return "login";
	//		}
	//		model.put("name", name);
	//		
	//		return "welcome";
	//		
	//	}
	
}
