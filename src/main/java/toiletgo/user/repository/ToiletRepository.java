package toiletgo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import toiletgo.user.entity.Toilet;

import java.util.List;

public interface ToiletRepository extends JpaRepository<Toilet, Long> {
    @Query("SELECT t FROM Toilet t WHERE " +
            "t.latitude BETWEEN :minLat AND :maxLat AND " +
            "t.longitude BETWEEN :minLng AND :maxLng AND " +
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
