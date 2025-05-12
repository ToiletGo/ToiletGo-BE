package toiletgo.activities.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.user.entity.Toilet;

import java.util.List;

@Repository
public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    List<Toilet> findActiveToilets();
}