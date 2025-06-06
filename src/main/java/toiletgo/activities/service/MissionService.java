package toiletgo.activities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.repository.MissionRepository;
import toiletgo.activities.repository.ReviewRepository;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {
    @Autowired
    private MissionRepository missionRepository;
    @Autowired
    private ReviewService reviewService;

    public List<MissionListDto> getMissions(UserDto userDto){
        List<Mission> missions =  missionRepository.findRandomMissionsByUserId(userDto.getUserId());

        if (missions.isEmpty()) {
            return null;
        }

        List<MissionListDto> missionListDtoList = missions.stream()
                .map(mission -> mission.toDto())
                .collect(Collectors.toList());
        return missionListDtoList;
    }

    //mission 1 해결 처리
    public void updateMission1Progress(User user){
        int reviewCount = reviewService.countReviewsByUserId(user.getUserId());
        int reviewScore = reviewCount * 20;

        Mission mission = missionRepository.findByUser_UserIdAndMissionList_MissionId(user.getUserId(), 1L);
        if(mission == null || mission.getIsCompleted()){
            return;
        }

        mission.setProgress(Math.min(reviewScore,100));
        if(reviewScore == 100 && !mission.getIsCompleted()){
            mission.setIsCompleted(true);
            mission.setCompletedAt(LocalDateTime.now());
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
        }

        missionRepository.save(mission);
    }


}
