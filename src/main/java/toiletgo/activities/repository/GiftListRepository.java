package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import toiletgo.activities.entity.Gift;

public interface GiftListRepository extends JpaRepository<Gift, Long> {

}
