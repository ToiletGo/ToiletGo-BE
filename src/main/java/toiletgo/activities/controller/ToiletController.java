package toiletgo.activities.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ToiletDto;
import toiletgo.activities.dto.ToiletSearchFilterDto;
import toiletgo.activities.service.MissionService;
import toiletgo.activities.service.ToiletService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ToiletController {

    private final ToiletService toiletService;
    private final MissionService missionService;

    /**
     * GET /api/toilets
     * 모든 화장실 조회
     */
    @GetMapping("/api/toilets")
    public ResponseEntity<List<ToiletDto>> getToilets() {
        List<ToiletDto> toilets = toiletService.getAllToilets();
        if (toilets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(toilets);
    }

    /**
     * POST /api/toilets/filter
     * 필터 조건에 맞는 화장실 조회
     */
    @PostMapping("/api/toilets/filter")
    public ResponseEntity<List<ToiletDto>> getFilteredToilets(
            @RequestBody ToiletSearchFilterDto filterDto) {
        List<ToiletDto> toilets = toiletService.getFilteredToilets(filterDto);
        if (toilets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(toilets);
    }

    /**
     * POST /api/toilet/get
     * 단일 화장실 조회
     */
    @PostMapping("/api/toilet/get")
    public ResponseEntity<ToiletDto> getToilet(@RequestBody ToiletDto toiletDto) {
        try {
            ToiletDto dto = toiletService.getToiletDtoById(toiletDto.getToiletId());
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * POST /api/toilet
     * 새로운 화장실 등록
     */
    @PostMapping("/api/toilet")
    public ResponseEntity<String> createToilet(@RequestBody ToiletDto toiletDto) {
        try {
            toiletService.createToilet(toiletDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("화장실이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("등록 중 오류가 발생했습니다.");
        }
    }

    /**
     * POST /api/admin/toilet/delete
     * 관리자용: 특정 화장실 삭제
     */
    @PostMapping("/api/admin/toilet/delete")
    public ResponseEntity<String> deleteToilet(@RequestBody ToiletDto toiletDto) {
        try {
            toiletService.deleteToilet(toiletDto.getToiletId());
            return ResponseEntity.ok("삭제 완료 처리되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}