package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toiletgo.activities.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
