package toiletgo.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ReportDto;
import toiletgo.activities.exception.DuplicateUserNameException;
import toiletgo.activities.service.MissionService;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 순환 고리인 MissionService 만 @Lazy 세터 주입
    private MissionService missionService;

    @Autowired
    public void setMissionService(@Lazy MissionService missionService) {
        this.missionService = missionService;
    }

    public String registerUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("중복 id 입니다.");
        }

        user.setUserPoint(0);
        user.setUserTrust(20);
        user.setUserProfileImg(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // 유저에게 초기 미션 할당
        missionService.assignAllMissionsToUser(user);

        return "회원 가입이 완료되었습니다.";
    }

    public boolean isUserIdDuplicate(String userId) {
        return userRepository.findById(userId).isPresent();
    }
    /**
     * (추가) User 엔티티 자체가 필요할 때 사용하는 메소드
     * @param userId 조회할 유저 ID
     * @return User 엔티티
     * @throws EntityNotFoundException 해당 유저가 없을 때
     */
    public User getUserEntity(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));
    }

    /**
     * (기존) getProfile: UserDto 반환
     */
    public UserDto getProfile(String userId) {
        User user = getUserEntity(userId);
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .userPoint(user.getUserPoint())
                .userTrust(user.getUserTrust())
                .userProfileImg(user.getUserProfileImg())
                .build();
    }

    /**
     * (기존) ID 중복 체크
     */
    public boolean checkDuplicateId(String userId) {
        return !userRepository.findById(userId).isPresent();
    }

    /**
     * (기존) username 중복 체크
     */
    public boolean checkDuplicateName(String username) {
        return !userRepository.findByUsername(username).isPresent();
    }

    /**
     * (기존) 유저 정보 수정
     */
    public void modifyUser(UserDto dto) throws DuplicateUserNameException {
        User user = getUserEntity(dto.getUserId());
        if(!checkDuplicateName(dto.getUserName())){
            throw new DuplicateUserNameException("중복된 유저입니다");
        }
        user.setUsername(dto.getUserName());
        user.setUserProfileImg(dto.getUserProfileImg());
        userRepository.save(user);
    }

    /**
     * (기존) 관리자용: 유저 삭제
     */
    public void deleteUser(ReportDto reportDto) {
        User user = getUserEntity(reportDto.getUserId());
        userRepository.delete(user);
    }

    //유저 포인트 증가
    public void givePoint(String userId, int point){
        User user = getUserEntity(userId);
        user.setUserPoint(user.getUserPoint()+point);
    }

    public void minusUserPoint(String userId) {
        User target = userRepository.findById(userId).orElse(null);
        target.setUserTrust(target.getUserTrust()-10);
        userRepository.save(target);
    }
}