package com.SpringBoot.Application.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.SpringBoot.Application.Model.Todo;
import com.SpringBoot.Application.Service.TodoService;


@Controller
//@SessionAttributes("name")
public class TodosController {
	
	@Autowired
	TodoService service; //Bean
	
	@InitBinder
	public void dateChanger(WebDataBinder binder)
	{
		SimpleDateFormat df=new SimpleDateFormat("dd/mm/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				df, false));
	}
	
	@RequestMapping(value="/todo-list", method = RequestMethod.GET)
	public String hello(ModelMap model )
	{
		String name=getLoggedInUserName( model);
		model.put("todos", service.retrieveTodos(name));
		return "todo-list";
	}

	private String getLoggedInUserName(ModelMap model) 
	{
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	
	//	private String extracted(ModelMap model) {
	//		return (String)model.get("name");
	//	}
	//	
	
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String addValue(ModelMap model)
	{
		model.addAttribute("todo", new Todo(0, getLoggedInUserName( model), "Default Desc",
				new Date(), false));
		return "/add-list";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String nameAdd(ModelMap model ,@Valid Todo todo,BindingResult res) //Hibernate validator
	{
		if(res.hasErrors())
		{
			return "/add-list";
		}
		service.addTodo(getLoggedInUserName( model),todo.getDesc(), todo.getTargetDate(), false);
		// model.put("added", " Name Successfully Added ");
		return "redirect:/todo-list";
	}
	
	//	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	//	public String deleteValue(ModelMap model )
	//	{
	//
	//		return "delete";
	//	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int number)
	{
		if(number==1)
			throw new RuntimeException();
		
		service.deleteTodo(number);
		return "redirect:/todo-list";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String updateTodo(@RequestParam int number,ModelMap model)
	{    
		Todo todo=service.retrieveTodo(number);
		model.put("todo", todo);
		return "add-list";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodoList(ModelMap model ,@Valid Todo todo,BindingResult res)
	{
		if(res.hasErrors())
		{
			return "/add-list";
		}
		todo.setUser(getLoggedInUserName( model));
		service.updateTodo(todo);
		
		return "redirect:/todo-list";
	}
}
