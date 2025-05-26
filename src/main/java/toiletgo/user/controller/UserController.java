package toiletgo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.dto.ReportDto;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/profile")
    public ResponseEntity<UserDto> showProfile(@RequestBody UserDto userDto){
        User user = userRepository.findById(userDto.getUserId()).orElse(null);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user.toDto());
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PatchMapping("/api/edit/user")
    public ResponseEntity<String> modifyUser(@RequestBody UserDto userDto) {
        try {
            User user = userRepository.findById(userDto.getUserId()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 유저가 존재하지 않습니다.");
            }
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setUserProfileImg(userDto.getUserProfileImg());

            return ResponseEntity.status(HttpStatus.OK).body("미션 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("미션 완료 처리 중 오류 발생: " + e.getMessage());
        }

    }

    //admin
    @DeleteMapping("/api/admin/delete")
    public ResponseEntity<String> deleteUser(@RequestBody ReportDto reportDto) {
        try {
            User user = userRepository.findById(reportDto.getUserId()).orElse(null);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 유저가 존재하지 않습니다.");
            }
            userRepository.delete(user);

            return ResponseEntity.status(HttpStatus.OK).body("삭제 완료 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("삭제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
