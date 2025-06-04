package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Mission;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query(value = "SELECT * FROM mission WHERE user_id = :userId ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<Mission> findRandomMissionsByUserId(String userId);
    Mission findByUser_UserIdAndMissionList_MissionId(String userId, Long missionId);
}
