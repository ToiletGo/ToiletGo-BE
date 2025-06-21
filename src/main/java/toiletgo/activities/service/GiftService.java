package toiletgo.activities.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import toiletgo.activities.dto.GiftDto;
import toiletgo.activities.dto.GiftListDto;
import toiletgo.activities.dto.GiftPurchaseDto;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.GiftList;
import toiletgo.activities.exception.GiftAlreadyUsedException;
import toiletgo.activities.repository.GiftListRepository;
import toiletgo.activities.repository.GiftRepository;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;
import toiletgo.user.service.JwtService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftService {

    private final GiftListRepository giftListRepository;
    private final GiftRepository giftRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * 상점에 아직 할당되지 않은 모든 선물을 조회
     */
    public List<GiftListDto> getAvailableGifts() {
        List<GiftList> gifts = giftListRepository.findByIsAssignedFalse();
        return gifts.stream()
                .map(GiftList::toDto)
                .collect(Collectors.toList());
    }

    /**
     * JWT 토큰에 담긴 유저 ID를 이용해, 해당 유저가 보유한 모든 선물을 조회
     */
    public List<GiftDto> getUserGifts(HttpServletRequest request) {
        String userId = jwtService.getAuthUser(request);
        List<Gift> gifts = giftRepository.findByUser_UserId(userId);
        return gifts.stream()
                .map(Gift::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 선물 구입 처리
     */
    public ResponseEntity<?> buyGift(GiftPurchaseDto giftPurchaseDto) {
        GiftList giftListEntity = giftListRepository
                .findById(giftPurchaseDto.getGiftId())
                .orElseThrow(() -> new EntityNotFoundException("해당 선물이 존재하지 않습니다."));

        giftListEntity.setIsAssigned(true);
        giftListEntity.setExpiration(LocalDate.now().plusMonths(1));
        // giftListRepository.save(giftListEntity); // Optional: 변경 감지(Dirty Checking)로 자동 반영됩니다.

        User user = userRepository
                .findById(giftPurchaseDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        if(giftListEntity.getIsAssigned() == true){
            throw new GiftAlreadyUsedException("이미 사용된 기프티콘입니다.");
        }
        giftListEntity.setIsAssigned(true);
        // giftListRepository.save(giftListEntity); // Optional: 변경 감지(Dirty Checking)로 자동 반영됩니다.



        Gift newGift = new Gift(giftListEntity, user, false, false);
        giftRepository.save(newGift);
        return ResponseEntity.ok("구매가 완료되었습니다.");
    }

    /**
     * 선물 기한 만료 처리
     */
    public void expireGift(Long giftNo) {
        Gift giftEntity = giftRepository
                .findById(giftNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 선물이 존재하지 않습니다."));

        giftEntity.setIsExpired(true);
        giftRepository.save(giftEntity);
    }
}