package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.GiftDto;
import toiletgo.activities.dto.GiftListDto;
import toiletgo.activities.dto.MissionListDto;
import toiletgo.activities.entity.Gift;
import toiletgo.activities.entity.GiftList;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.repository.GiftListRepository;
import toiletgo.activities.repository.GiftRepository;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GiftController {

    @Autowired
    GiftListRepository giftListRepository;
    @Autowired
    GiftRepository giftRepository;

    @Autowired
    UserRepository userRepository;

    // 상점 조회
    @GetMapping("/api/store/show")
    public ResponseEntity<List<GiftListDto>> getGifts(){
        List<GiftList> gifts = giftListRepository.findByIsAssignedFalse();
        if (gifts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<GiftListDto> giftListDtos = gifts.stream()
                .map(giftList -> giftList.toDto())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(giftListDtos);
    }

    // 특정 사용자 선물함 조회
    @GetMapping("/api/gifts")
    public ResponseEntity<List<GiftDto>> getUserGifts(@RequestBody UserDto userDto){
        try {
            List<Gift> gifts = giftRepository.findByUser_UserId(userDto.getUserId());
            if (gifts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            List<GiftDto> giftDtos = gifts.stream()
                    .map(gift -> gift.toDto())
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(giftDtos);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    //선물 구입
    @PatchMapping("/api/gifts/purchase")
    public ResponseEntity<String> buyGift(@RequestBody GiftPurchaseDto giftPurchaseDto){
        try{
            GiftList gift = giftListRepository.findById(giftPurchaseDto.getGiftId()).orElse(null);
            if(gift == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 선물이 존재하지 않습니다.");
            }
            gift.setIsAssigned(true);

            User user = userRepository.findById(giftPurchaseDto.getUserId()).orElse(null);
            Gift myGift = new Gift(gift,user,false,false);
            giftRepository.save(myGift);

            return ResponseEntity.status(HttpStatus.OK).body("구매가 완료되었습니다.");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("기프티콘 구매중 오류 발생: " + e.getMessage());
        }
    }

    //선물 기한만료

    @PatchMapping("/api/gifts/expired")
    public ResponseEntity<String> expiredGift(@RequestBody GiftDto giftDto){
        try{
            Gift gift = giftRepository.findById(giftDto.getGiftNo()).orElse(null);
            if(gift == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 선물이 존재하지 않습니다.");
            }
            gift.setIsExpired(true);
            giftRepository.save(gift);

            return ResponseEntity.status(HttpStatus.OK).body("기한이 만료되었습니다.");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("선물 관리 중 오류 발생: " + e.getMessage());
        }

    }


}
