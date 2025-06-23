package toiletgo.activities.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.GiftDto;
import toiletgo.activities.dto.GiftListDto;
import toiletgo.activities.dto.GiftPurchaseDto;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.GiftList;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.exception.GiftAlreadyUsedException;
import toiletgo.activities.repository.GiftListRepository;
import toiletgo.activities.repository.GiftRepository;
import toiletgo.activities.service.GiftService;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;
import toiletgo.user.service.JwtService;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@RestController
public class GiftController {

    private final GiftService giftService;

    /**
     * GET /api/store/show
     * 상점의 선물을 모두 조회
     */
    @GetMapping("/api/store/show")
    public ResponseEntity<List<GiftListDto>> getGifts() {
        List<GiftListDto> giftListDtos = giftService.getAvailableGifts();
        if (giftListDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(giftListDtos);
    }

    /**
     * GET /api/gifts
     * 본인 ID의 선물을 모두 조회
     */
    @GetMapping("/api/gifts")
    public ResponseEntity<List<GiftDto>> getUserGifts(HttpServletRequest request) {
        try {
            List<GiftDto> giftDtos = giftService.getUserGifts(request);
            if (giftDtos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(giftDtos);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * PATCH /api/gifts/purchase
     * 선물 구입
     */
    @PatchMapping("/api/gifts/purchase")
    public ResponseEntity<String> buyGift(@RequestBody GiftPurchaseDto giftPurchaseDto,HttpServletRequest request) {
        try {
            giftService.buyGift(giftPurchaseDto,request);
            return ResponseEntity.ok("구매가 완료되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (GiftAlreadyUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("기프티콘 구매중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * PATCH /api/gifts/expired
     * 선물 기한 만료
     */
    @PatchMapping("/api/gifts/expired")
    public ResponseEntity<String> expiredGift(@RequestBody GiftDto giftDto) {
        try {
            giftService.expireGift(giftDto.getGiftNo());
            return ResponseEntity.ok("기한이 만료되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("선물 관리 중 오류 발생: " + e.getMessage());
        }
    }
}
