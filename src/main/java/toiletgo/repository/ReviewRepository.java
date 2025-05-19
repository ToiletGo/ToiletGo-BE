package toiletgo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
