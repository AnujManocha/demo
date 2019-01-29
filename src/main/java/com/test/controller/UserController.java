package com.test.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.util.HibernateConfig;


@RestController
public class UserController {

	@Autowired
	HibernateConfig hibernateconfig;
	SessionFactory factory = hibernateconfig.getSessionFactory();

	@GetMapping(value = "/test")
	public ResponseEntity<Object> getUser() {
		System.out.println("Welcome callled ");

		System.out.println(factory != null);
		Session session = factory.openSession();
		session.beginTransaction();
		User user = new User();

//		user.setId("100");
		user.setName("Anuj");

		session.save(user);
		session.getTransaction().commit();
		return new ResponseEntity<Object>("hello user", HttpStatus.OK);
	}
}
