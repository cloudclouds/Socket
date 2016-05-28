package com.imooc.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.imooc.entity.User;

public class UserServiceTest {

	@Test
	public void testIsUserExist() {
		String userName="aaa";
		String password="123";
		System.out.println(new UserService().isUserExist(userName, password));
	}
    
	@Test
	public void testAddUser() 
	{
		User u=new User();
		u.setUsername("cy");
		u.setPassword("123");
		System.out.println(new UserService().addUser(u));
	}
	
}
