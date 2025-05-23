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

    @GetMapping("/api/gifts")
    public ResponseEntity<List<GiftListDto>> getGifts(){
        List<GiftList> gifts = giftListRepository.findAll();
        if (gifts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<GiftListDto> missionListDtoList = gifts.stream()
                .map(giftList -> giftList.toDto())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(missionListDtoList);
    }

    @GetMapping("/api/gifts/{userId}")
    public ResponseEntity<List<GiftDto>> getUserGifts(@PathVariable String userId){
        try {
            List<Gift> gifts = giftRepository.findByUser_UserId(userId);
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
    @PatchMapping("/api/gifts/purchase/{userId}/{giftId}")
    public ResponseEntity<String> buyGift(@PathVariable String userId, @PathVariable Long giftId){
        try{
            GiftList gift = giftListRepository.findById(giftId).orElse(null);
            if(gift == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 선물이 존재하지 않습니다.");
            }
            gift.setIsAssigned(true);

            User user = userRepository.findById(userId).orElse(null);
            Gift myGift = new Gift(gift,user,false,false);
            giftRepository.save(myGift);

            return ResponseEntity.status(HttpStatus.OK).body("구매가 완료되었습니다.");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("기프티콘 구매중 오류 발생: " + e.getMessage());
        }
    }

    //선물 기한만료

    @PatchMapping("/api/gifts/{giftId}/use")
    public ResponseEntity<String> (@PathVariable Long no){
        try{
            Gift gift = giftRepository.findById(no).orElse(null);
            if(gift == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 미션이 존재하지 않습니다.");
            }
            gift.setIsUsed(true);
            giftRepository.save(gift);

            return ResponseEntity.status(HttpStatus.OK).body("사용완료 처리되었습니다.");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("미션 완료 처리 중 오류 발생: " + e.getMessage());
        }

    }


}
