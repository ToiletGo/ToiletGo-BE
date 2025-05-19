package toiletgo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.user.entity.Toilet;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {

}
