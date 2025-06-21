package toiletgo.activities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import toiletgo.activities.entity.Toilet;

import java.util.List;

@Repository
public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    @Query("SELECT t FROM Toilet t WHERE " +
            "(:minLat IS NULL OR :maxLat IS NULL OR t.latitude BETWEEN :minLat AND :maxLat OR t.latitude IS NULL) AND " +
            "(:minLng IS NULL OR :maxLng IS NULL OR t.longitude BETWEEN :minLng AND :maxLng OR t.longitude IS NULL) AND " +
            "(:hasDiaperTable IS NULL OR t.hasDiaperTable = :hasDiaperTable) AND " +
            "(:hasHandicapAccess IS NULL OR t.hasHandicapAccess = :hasHandicapAccess) AND " +
            "(:hasBidet IS NULL OR t.hasBidet = :hasBidet) AND " +
            "(:hasTissue IS NULL OR t.hasTissue = :hasTissue)")
    List<Toilet> findFilteredToilets(
            @Param("minLat") Double minLat,
            @Param("maxLat") Double maxLat,
            @Param("minLng") Double minLng,
            @Param("maxLng") Double maxLng,
            @Param("hasDiaperTable") Boolean hasDiaperTable,
            @Param("hasHandicapAccess") Boolean hasHandicapAccess,
            @Param("hasBidet") Boolean hasBidet,
            @Param("hasTissue") Boolean hasTissue
    );
}
