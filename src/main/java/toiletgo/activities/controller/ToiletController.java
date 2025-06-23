package toiletgo.activities.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ToiletDto;
import toiletgo.activities.dto.ToiletSearchFilterDto;
import toiletgo.activities.service.MissionService;
import toiletgo.activities.service.ToiletService;
import toiletgo.user.service.JwtService;

import java.util.List;


/**
 * <h3>ToiletController</h3>
 * <li><b>GET /api/toilets</b> 모든 화장실 조회 </li>
 * <li><b>POST /api/toilets/filter</b> 필터 조건에 맞는 화장실 조회 </li>
 * <li><b>POST /api/toilet/get</b> 단일 화장실 조회 (마커 클릭)</li>
 * <li><b>POST /api/toilet</b> 새로운 화장실 등록 </li>
 * <li><b>POST /api/admin/toilet/delete</b> 관리자용: 특정 화장실 삭제 </li>
 */
@RestController
@AllArgsConstructor
@Slf4j
public class ToiletController {

    private final ToiletService toiletService;
    private final MissionService missionService;
    private final JwtService jwtService;

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
    public ResponseEntity<String> createToilet(@RequestBody ToiletDto toiletDto, HttpServletRequest request) {
        try {
            String userId = jwtService.getAuthUser(request);
            log.info("추출된 userId: {}", userId);
            toiletDto.setUserId(userId);
            toiletService.createToilet(toiletDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("화장실이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("등록 중 오류가 발생했습니다.");
        }
    }

    /**
     * POST /api/admin/toilet/delete
     * 관리자용: 특정 화장실 삭제
     */
    @DeleteMapping("/api/admin/toilet/delete")
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