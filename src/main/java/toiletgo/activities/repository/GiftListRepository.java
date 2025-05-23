package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.GiftList;

@Repository
public interface GiftListRepository extends JpaRepository<GiftList, Long> {

}
