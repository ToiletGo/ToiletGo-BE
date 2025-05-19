package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.MissionList;

public interface MissionListRepository extends JpaRepository<MissionList, Long> {
}
