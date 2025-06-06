package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Mission;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByUser_UserId(String userId);
    Mission findByUser_UserIdAndMissionList_MissionId(String userId, Long missionId);
}
