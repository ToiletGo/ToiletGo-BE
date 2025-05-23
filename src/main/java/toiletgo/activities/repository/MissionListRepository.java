package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Mission;

@Repository
public interface MissionListRepository extends JpaRepository<Mission, Long> {

}
