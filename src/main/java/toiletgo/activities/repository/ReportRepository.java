package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
