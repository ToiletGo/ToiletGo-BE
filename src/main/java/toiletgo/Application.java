package toiletgo;

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
		User user2 = new User("user2","username2", "$2a$12$j8b7ZPi/44Otq2khAgfAQOeqDH.Y3sUIWIGNrk9Tf2j6jj2zU8udO");
		Toilet toilet1 = new Toilet("강남구 테헤란로 1", new BigDecimal("127.00000001"), new BigDecimal("36.00000001"));
		Toilet toilet2 = new Toilet("서초구 신반포로 12", new BigDecimal("126.99999929"), new BigDecimal("36.00000001"));


		MissionList missionList1 = new MissionList("화장실 3개에 리뷰 남기기", "리뷰 작성-1", 10);
		MissionList missionList2 = new MissionList("화장실 10개에 리뷰 남기기", "리뷰 작성-2", 10);
		Mission mission1 = new Mission().missionConstructor(missionList2, user1, 8, false, LocalDateTime.now());
		Mission mission2 = new Mission().missionConstructor(missionList1, user2, 6, false, LocalDateTime.now());

		GiftList giftList1 = new GiftList("Moms Touch", "https://www.google.com/imgres?q=%EC%8B%B8%EC%9D%B4%EB%B2%84%EA%B1%B0%20%EA%B8%B0%ED%94%84%ED%8B%B0%EC%BD%98%20%EC%82%AC%EC%A7%84&imgurl=https%3A%2F%2Fshop2.daumcdn.net%2Fthumb%2FR500x500%2F%3Ffname%3Dhttp%253A%252F%252Fshop2.daumcdn.net%252Fshophow%252Fp%252FM29422699655.jpg%253Fut%253D20241025063306&imgrefurl=https%3A%2F%2Fm.shoppinghow.kakao.com%2Fm%2Fsearch%2Fq%2F%25EC%258B%25B8%25EC%259D%25B4%25EB%25B2%2584%25EA%25B1%25B0%2520%25EA%25B8%25B0%25ED%2594%2584%25ED%258B%25B0%25EC%25BD%2598&docid=W8CkUUwd9hxEkM&tbnid=pPSoWb0mnbQtPM&vet=12ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA..i&w=500&h=500&hcb=2&ved=2ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA"
				, 200, LocalDate.of(2025, 6, 1), false);
		GiftList giftList2 = new GiftList("Moms Touch", "https://www.google.com/imgres?q=%EC%8B%B8%EC%9D%B4%EB%B2%84%EA%B1%B0%20%EA%B8%B0%ED%94%84%ED%8B%B0%EC%BD%98%20%EC%82%AC%EC%A7%84&imgurl=https%3A%2F%2Fshop2.daumcdn.net%2Fthumb%2FR500x500%2F%3Ffname%3Dhttp%253A%252F%252Fshop2.daumcdn.net%252Fshophow%252Fp%252FM29422699655.jpg%253Fut%253D20241025063306&imgrefurl=https%3A%2F%2Fm.shoppinghow.kakao.com%2Fm%2Fsearch%2Fq%2F%25EC%258B%25B8%25EC%259D%25B4%25EB%25B2%2584%25EA%25B1%25B0%2520%25EA%25B8%25B0%25ED%2594%2584%25ED%258B%25B0%25EC%25BD%2598&docid=W8CkUUwd9hxEkM&tbnid=pPSoWb0mnbQtPM&vet=12ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA..i&w=500&h=500&hcb=2&ved=2ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA"
				, 250, LocalDate.of(2025, 6, 1), false);
		GiftList giftList3 = new GiftList("Moms Touch", "https://www.google.com/imgres?q=%EC%8B%B8%EC%9D%B4%EB%B2%84%EA%B1%B0%20%EA%B8%B0%ED%94%84%ED%8B%B0%EC%BD%98%20%EC%82%AC%EC%A7%84&imgurl=https%3A%2F%2Fshop2.daumcdn.net%2Fthumb%2FR500x500%2F%3Ffname%3Dhttp%253A%252F%252Fshop2.daumcdn.net%252Fshophow%252Fp%252FM29422699655.jpg%253Fut%253D20241025063306&imgrefurl=https%3A%2F%2Fm.shoppinghow.kakao.com%2Fm%2Fsearch%2Fq%2F%25EC%258B%25B8%25EC%259D%25B4%25EB%25B2%2584%25EA%25B1%25B0%2520%25EA%25B8%25B0%25ED%2594%2584%25ED%258B%25B0%25EC%25BD%2598&docid=W8CkUUwd9hxEkM&tbnid=pPSoWb0mnbQtPM&vet=12ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA..i&w=500&h=500&hcb=2&ved=2ahUKEwiC-8vfxLmNAxXooK8BHae_H9EQM3oECBoQAA"
				, 300, LocalDate.of(2025, 6, 1), false);

		Review review1 = new Review(user2, toilet1, 5, "최고예요!", LocalDateTime.now());
		Review review2 = new Review(user1, toilet2, 5, "최고예요!", LocalDateTime.now());
		Report report1 = new Report(user2, review1, null, "사실과 다른 정보", "비데 없음", false, LocalDateTime.now());


		Gift gift1 = new Gift(giftList3, user1, false, false);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(new User("admin_id", "admin", "$2a$12$T05/pakINgU7nUagCdInRe8rC6xPK1sHuhxlUuSIQPENfAogqeGpG"));

		toiletRepository.save(toilet1);
		toiletRepository.save(toilet2);

		reviewRepository.save(review1);
		reviewRepository.save(review2);
		reportRepository.save(report1);

		missionListRepository.save(missionList1);
		missionListRepository.save(missionList2);
		missionRepository.save(mission1);
		missionRepository.save(mission2);

		giftListRepository.save(giftList1);
		giftListRepository.save(giftList2);
		giftListRepository.save(giftList3);

		giftRepository.save(gift1);




	}
	// test - bash
}