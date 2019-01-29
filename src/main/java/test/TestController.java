package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	
	@Autowired
	HibernateConfig hibernateconfig;
	SessionFactory factory = hibernateconfig.getSessionFactory();
	@GetMapping(value = "/welcome")
	public String welcome() {// Welcome page, non-rest
		System.out.println("Welcome callled ");

		
		System.out.println(factory!=null);
		Session session = factory.openSession();
		session.beginTransaction();
		DbUser user = new DbUser();

		user.setId("100");
		user.setName("Anuj");

		session.save(user);
		session.getTransaction().commit();
		return "Welcome to RestTemplate Example.";
	}

}
