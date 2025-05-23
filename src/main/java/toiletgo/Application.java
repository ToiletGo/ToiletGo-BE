package toiletgo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;


@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private final UserRepository userRepository;

	public Application(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.save(new User("user_id","user", "$2a$12$j8b7ZPi/44Otq2khAgfAQOeqDH.Y3sUIWIGNrk9Tf2j6jj2zU8udO"));
		userRepository.save(new User("admin_id", "admin", "$2a$12$T05/pakINgU7nUagCdInRe8rC6xPK1sHuhxlUuSIQPENfAogqeGpG"));
	}

	// test - bash

}
