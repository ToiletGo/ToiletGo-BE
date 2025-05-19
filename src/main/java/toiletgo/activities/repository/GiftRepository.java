package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.Gift;

public interface GiftRepository extends JpaRepository<Gift, Long> {
}
