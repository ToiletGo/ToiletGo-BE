package toiletgo.activities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toiletgo.activities.entity.MissionList;
import toiletgo.activities.repository.MissionListRepository;

import java.util.List;

@Service
public class MissionListService {

    @Autowired
    private MissionListRepository missionListRepository;

    // 전체 미션 목록 반환
    public List<MissionList> getAllMissionLists() {
        return missionListRepository.findAll();
    }
}