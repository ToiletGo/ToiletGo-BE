package toiletgo.activities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.MissionList;
import toiletgo.activities.repository.MissionRepository;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;
import toiletgo.user.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final MissionListService missionListService;

    // 여기는 생성자 주입만
    @Autowired
    public MissionService(MissionRepository missionRepository,
                          MissionListService missionListService) {
        this.missionRepository = missionRepository;
        this.missionListService = missionListService;
    }

    // 순환 고리인 UserService, ReviewService 는 지연된 세터 주입으로만!
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public void setUserService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setReviewService(@Lazy ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    public List<MissionListDto> getMissions(UserDto userDto) {
        List<Mission> missions = missionRepository.findByUser_UserId(userDto.getUserId());
        if (missions.isEmpty()) return null;
        return missions.stream()
                .map(Mission::toDto)
                .collect(Collectors.toList());
    }

    public void assignAllMissionsToUser(User user) {
        List<MissionList> missionLists = missionListService.getAllMissionLists();
        for (MissionList ml : missionLists) {
            Mission m = Mission.builder()
                    .user(user)
                    .missionList(ml)
                    .isCompleted(false)
                    .progress(0)
                    .build();
            missionRepository.save(m);
        }
    }

    public void updateMission1Progress(User user) {
        int score = reviewService.countReviewsByUserId(user.getUserId()) * 20;
        Mission mission = missionRepository
                .findByUser_UserIdAndMissionList_MissionId(user.getUserId(), 1L);
        if (mission == null || mission.getIsCompleted()) return;

        mission.setProgress(Math.min(score, 100));
        if (score == 100) {
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
            userService.givePoint(user.getUserId(), mission.getMissionList().getPoint());
        }
        missionRepository.save(mission);
    }

    //mission 2 해결 처리
    public void completeMission2(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 2L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }
        mission.setProgress(100);
        mission.setIsCompleted(true);
        mission.setCompletedAt(LocalDateTime.now());

        userService.givePoint(userId,mission.getMissionList().getPoint());
        missionRepository.save(mission);
    }
    //mission 3 해결 처리
    public void updateMission3Progress(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 3L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }

        int missionProgress = mission.getProgress()+20;
        mission.setProgress(Math.min(missionProgress, 100));
        if(missionProgress == 100 && !mission.getIsCompleted()){
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
            userService.givePoint(userId,mission.getMissionList().getPoint());
        }

        missionRepository.save(mission);
    }

    //mission 4 해결 처리
    public void updateMission4Progress(User user){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(user.getUserId(), 4L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }

        int missionProgress = mission.getProgress()+33;
        mission.setProgress(Math.min(missionProgress, 99));
        if(missionProgress == 99 && !mission.getIsCompleted()){
            mission.setProgress(100);
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
            userService.givePoint(user.getUserId(),mission.getMissionList().getPoint());
        }

        missionRepository.save(mission);
    }

    //mission 5 해결 처리
    public void updateMission5Progress(User user){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(user.getUserId(), 5L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }

        int missionProgress = mission.getProgress()+33;
        mission.setProgress(Math.min(missionProgress, 99));
        if(missionProgress == 99 && !mission.getIsCompleted()){
            mission.setProgress(100);
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
            userService.givePoint(user.getUserId(),mission.getMissionList().getPoint());
        }

        missionRepository.save(mission);
    }
    //mission 6 해결 처리
    public void completeMission6(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 6L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }
        mission.setProgress(100);
        mission.setIsCompleted(true);
        mission.setCompletedAt(LocalDateTime.now());
        userService.givePoint(userId,mission.getMissionList().getPoint());

        missionRepository.save(mission);
    }
    //mission 7 해결 처리
    public void completeMission7(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 7L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }
        mission.setProgress(100);
        mission.setIsCompleted(true);
        mission.setCompletedAt(LocalDateTime.now());
        userService.givePoint(userId,mission.getMissionList().getPoint());

        missionRepository.save(mission);
    }
    //mission 8 해결 처리
    public void completeMission8(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 8L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }
        mission.setProgress(100);
        mission.setIsCompleted(true);
        mission.setCompletedAt(LocalDateTime.now());
        userService.givePoint(userId,mission.getMissionList().getPoint());

        missionRepository.save(mission);
    }
    //mission 9 해결 처리
    public void updateMission9Progress(User user) {
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(user.getUserId(), 9L);
        if (mission == null || mission.getIsCompleted()) {
            return;
        }

        int missionProgress = mission.getProgress() + 33;
        mission.setProgress(Math.min(missionProgress, 100));
        if (missionProgress == 99 && !mission.getIsCompleted()) {
            mission.setProgress(100);
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
            userService.givePoint(user.getUserId(), mission.getMissionList().getPoint());
        }
    }
    //mission 10 해결 처리
    public void completeMission10(String userId){
        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(userId, 10L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }
        mission.setProgress(100);
        mission.setIsCompleted(true);
        mission.setCompletedAt(LocalDateTime.now());
        userService.givePoint(userId,mission.getMissionList().getPoint());

        missionRepository.save(mission);
    }

}