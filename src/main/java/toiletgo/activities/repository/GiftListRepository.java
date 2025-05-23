package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Gift;

@Repository
public interface GiftListRepository extends JpaRepository<Gift, Long> {

}
