package toiletgo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.MissionList;
import toiletgo.activities.entity.Review;
import toiletgo.user.entity.*;
import toiletgo.user.entity.User;
import toiletgo.user.repository.*;
import toiletgo.activities.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private final UserRepository userRepository;
	private final ToiletRepository toiletRepository;
	private final GiftRepository giftRepository;
	private final GiftListRepository giftListRepository;
	private final MissionRepository missionRepository;
	private final MissionListRepository missionListRepository;
	private final ReportRepository reportRepository;
	private final ReviewRepository reviewRepository;

	public Application(UserRepository userRepository, ToiletRepository toiletRepository,
					   GiftRepository giftRepository, GiftListRepository giftListRepository,
					   MissionRepository missionRepository, MissionListRepository missionListRepository,
					   ReportRepository reportRepository, ReviewRepository reviewRepository) {
		this.userRepository = userRepository;
		this.toiletRepository = toiletRepository;
		this.giftRepository = giftRepository;
		this.giftListRepository = giftListRepository;
		this.missionRepository = missionRepository;
		this.missionListRepository = missionListRepository;
		this.reportRepository = reportRepository;
		this.reviewRepository = reviewRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("user_id","user", "$2a$12$j8b7ZPi/44Otq2khAgfAQOeqDH.Y3sUIWIGNrk9Tf2j6jj2zU8udO");
		Toilet toilet1 = new Toilet("강남구 테헤란로 1", new BigDecimal("127.00000001"), new BigDecimal("36.00000001"));
		Toilet toilet2 = new Toilet("서초구 신반포로 12", new BigDecimal("126.99999929"), new BigDecimal("36.00000001"));


		Mission mission1 = new Mission(user1, 8, false, false, LocalDateTime.now());
		MissionList missionList1 = new MissionList(mission1, "화장실 10개에 리뷰 남기기", "리뷰 작성-2", "사용", 10);

		userRepository.save(user1);
		userRepository.save(new User("admin_id", "admin", "$2a$12$T05/pakINgU7nUagCdInRe8rC6xPK1sHuhxlUuSIQPENfAogqeGpG"));

		toiletRepository.save(toilet1);
		toiletRepository.save(toilet2);

		reviewRepository.save(new Review(user1, toilet2, 5, "최고예요!", LocalDateTime.now()));


	}

	// test - bash
}

public MissionList(Mission mission, String description, String missionName, String missionType, Integer point){
	this.mission = mission;
	this.description = description;
	this.missionName = missionName;
	this.missionType = Boolean.parseBoolean(missionType);
	this.point = point;
}

public Mission(User user, Integer progress, Boolean isCompleted, LocalDateTime completedAt) {
	this.user = user;
	this.progress = progress;
	this.isCompleted = isCompleted;
	this.completedAt = completedAt;
}
