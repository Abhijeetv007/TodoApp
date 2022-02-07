package com.SpringBoot.Application.Service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validate(String name, String password) {
		// TODO Auto-generated method stub
		
		return name.equalsIgnoreCase("Abhi") && password.equals("abhi");
		
	}
	
}
