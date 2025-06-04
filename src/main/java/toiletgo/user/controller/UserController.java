package toiletgo.user.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.user.dto.UserDto;
import toiletgo.user.service.UserService;


@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * POST /api/profile
     * 사용자 프로필 조회
     */
    @PostMapping("/api/profile")
    public ResponseEntity<UserDto> showProfile(@RequestBody UserDto userDto) {
        try {
            UserDto profile = userService.getProfile(userDto.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * POST /api/user/id/check
     * ID 중복 체크
     */
    @PostMapping("/api/user/id/check")
    public boolean checkDuplicateId(@RequestBody UserDto userDto) {
        return userService.checkDuplicateId(userDto.getUserId());
    }

    /**
     * POST /api/user/username/check
     * username 중복 체크
     */
    @PostMapping("/api/user/username/check")
    public boolean checkDuplicateName(@RequestBody UserDto userDto) {
        return userService.checkDuplicateName(userDto.getUserName());
    }

    /**
     * PATCH /api/edit/user
     * 유저 정보 수정
     */
    @PatchMapping("/api/edit/user")
    public ResponseEntity<String> modifyUser(@RequestBody UserDto userDto) {
        try {
            userService.modifyUser(userDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("유저 정보가 성공적으로 수정되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("수정 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    /**
     * DELETE /api/admin/delete/user
     * 관리자용: 신고가 들어온 유저 삭제
     */
    @DeleteMapping("/api/admin/delete/user")
    public ResponseEntity<String> deleteUser(@RequestBody ReportDto reportDto) {
        try {
            userService.deleteUser(reportDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("삭제 완료 처리되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}