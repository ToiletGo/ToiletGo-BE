package toiletgo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.user.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
