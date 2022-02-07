package com.SpringBoot.Application.Controller.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ExceptionFinder {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handler(HttpServletRequest req,Exception ex)
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("exception", ex.getLocalizedMessage());
		mv.addObject("url", req.getRequestURL());
		
		mv.setViewName("error");
		return mv;
		
	}
}
