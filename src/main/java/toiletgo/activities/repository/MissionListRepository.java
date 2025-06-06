package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.MissionList;

import java.util.List;

@Repository
public interface MissionListRepository extends JpaRepository<MissionList, Long> {
    List<MissionList> findAll();
}
