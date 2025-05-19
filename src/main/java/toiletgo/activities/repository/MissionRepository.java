package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
