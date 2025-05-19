package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
