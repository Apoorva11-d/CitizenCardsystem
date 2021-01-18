package com.userlogin.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.userlogin.demo.dao.UserDAO;
import com.userlogin.demo.exceptionhandler.UserNotFoundException;
import com.userlogin.demo.model.User;
import com.userlogin.demo.service.IUserService;
import com.userlogin.demo.service.UserServiceImpl;

@RunWith(SpringRunner.class)


@SpringBootTest

class ServiceTest {
	@Autowired(required=true)
	public UserServiceImpl repo;

	
	@Autowired
	public UserDAO repository;
	
	/*@Test
	void testsavedUser() {
User savedUser = repo.saveUser(new User("xyz@gmail.com", "password"));
		when(repository.save(savedUser)).thenReturn(savedUser);
		assertEquals(savedUser,repo.saveUser(savedUser));
	}*/

	@Test
	void testgetUser()throws UserNotFoundException {
String s= repo.getUser("xyz@gmail.com", "password");
		
		assertEquals("Logged in successfully", s);
	}
	
	@Test
	public void testUpdatePassword() {
		User user=repository.findById("abc@gmail.com").get();
		
		//User u = repo.updateUserPassword(user.getUemailid(), new User("abc@gmail.com", "changedpass"));
		user.setUpass("changedpass");
		
		User updated=repository.findById("abc@gmail.com").get();
		
		assertThat(updated.getUpass().equals("changedpass"));
	}
}
