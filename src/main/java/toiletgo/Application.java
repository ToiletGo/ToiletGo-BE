package toiletgo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import toiletgo.activities.entity.*;
import toiletgo.user.entity.*;
import toiletgo.user.entity.User;
import toiletgo.user.repository.*;
import toiletgo.activities.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * <h3>Application</h3>
 * <b>Elastic IP : 15.164.220.91 </b>
 *
 *
 */
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Sample로 이동
	}
	// test - bash
}