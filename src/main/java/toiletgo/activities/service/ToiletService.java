package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.ToiletDto;
import toiletgo.activities.dto.ToiletSearchFilterDto;
import toiletgo.activities.entity.Toilet;
import toiletgo.activities.repository.ToiletRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToiletService {

    private final ToiletRepository toiletRepository;
    private final MissionService missionService;

    /**
     * 모든 화장실 조회
     */
    public List<ToiletDto> getAllToilets() {
        List<Toilet> toilets = toiletRepository.findAll();
        return toilets.stream()
                .map(Toilet::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 필터 조건에 맞는 화장실 조회
     */
    public List<ToiletDto> getFilteredToilets(ToiletSearchFilterDto filterDto) {
        List<Toilet> toilets = toiletRepository.findFilteredToilets(
                filterDto.getMinLatitude(),
                filterDto.getMaxLatitude(),
                filterDto.getMinLongitude(),
                filterDto.getMaxLongitude(),
                filterDto.getHasDiaperTable(),
                filterDto.getHasHandicapAccess(),
                filterDto.getHasBidet(),
                filterDto.getHasTissue()
        );
        return toilets.stream()
                .map(Toilet::toDto)
                .collect(Collectors.toList());
    }



    /**
     * 단일 화장실 조회 (DTO 반환)
     */
    public ToiletDto getToiletDtoById(Long toiletId) {
        if (toiletId == null) {
            throw new IllegalArgumentException("화장실 ID가 유효하지 않습니다.");
        }
        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("해당 화장실이 존재하지 않습니다."));
        return toilet.toDto();
    }

    /**
     * (추가) 엔티티 자체가 필요할 때 사용하는 메소드
     */
    public Toilet getToiletEntity(Long toiletId) {
        return toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("해당 화장실이 존재하지 않습니다."));
    }

    /**
     * 새로운 화장실 등록
     */
    public void createToilet(ToiletDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("등록할 화장실 정보가 없습니다.");
        }
        Toilet toilet = dto.toEntity();
        toiletRepository.save(toilet);
        missionService.completeMission2(dto.getUserId());
        missionService.updateMission3Progress(dto.getUserId());
        if(toilet.getHasHandicapAccess()==true){
            missionService.completeMission6(dto.getUserId());
        }
        if(toilet.getHasDiaperTable()==true){
            missionService.completeMission7(dto.getUserId());
        }
        if(toilet.getHasTissue()==true){
            missionService.completeMission8(dto.getUserId());
        }

    }

    /**
     * 화장실 삭제 (관리자용)
     */
    public void deleteToilet(Long toiletId) {
        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("해당 화장실이 존재하지 않습니다."));
        toiletRepository.delete(toilet);
    }

    /**
     * (추가) 특정 화장실의 평점(rating)과 리뷰 개수(reviewCount)를 업데이트
     * ReviewService 같은 곳에서 직접 toiletRepository를 호출하는 대신,
     * 이 메소드를 통해 업데이트하도록 위임한다.
     *
     * @param toiletId   업데이트할 화장실 ID
     * @param average    계산된 평균 평점 (BigDecimal)
     * @param reviewCount 해당 화장실에 달린 총 리뷰 개수
     */
    public void updateRatingAndCount(Long toiletId, BigDecimal average, int reviewCount) {
        Toilet toilet = toiletRepository.findById(toiletId)
                .orElseThrow(() -> new EntityNotFoundException("해당 화장실이 존재하지 않습니다."));
        toilet.setRating(average);
        toilet.setReviewCount(reviewCount);
        toiletRepository.save(toilet);
    }


}



    /*
    {
    "minLatitude":127,
    "maxLatitude":128,
    "minLongitude":37,
    "maxLongitude":38,


    "hasDiaperTable": false,

    "hasHandicapAccess": false,
    "hasBidet":false,
    "hasTissue":false

}

     */