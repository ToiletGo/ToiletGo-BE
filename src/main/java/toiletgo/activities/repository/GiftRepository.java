package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.Gift;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findByUser_UserId(String userId);
}
