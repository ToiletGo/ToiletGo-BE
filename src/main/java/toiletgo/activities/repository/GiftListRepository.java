package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.GiftList;

public interface GiftListRepository extends JpaRepository<GiftList, Integer> {

}
