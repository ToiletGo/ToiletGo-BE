package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.GiftList;
import toiletgo.activities.entity.Toilet;

import java.util.List;

@Repository
public interface GiftListRepository extends JpaRepository<GiftList, Long> {
    List<GiftList> findByIsAssignedFalse();
}
