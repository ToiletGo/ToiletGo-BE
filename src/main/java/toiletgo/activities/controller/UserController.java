package toiletgo.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toiletgo.activities.entity.Mission;
import toiletgo.activities.entity.Report;
import toiletgo.user.dto.UserDto;
import toiletgo.user.entity.User;
import toiletgo.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/profile/{userId}")
    public ResponseEntity<UserDto> showProfile(@PathVariable String userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            UserDto userDto = user.toDto();
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
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
    @DeleteMapping("/api/admin/delete/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("해당 유저가 존재하지 않습니다.");
            }

            userRepository.delete(optionalUser.get());
            return ResponseEntity.ok("삭제 완료 처리되었습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("유저 삭제 중 오류 발생: " + e.getMessage());
        }
    }
}
