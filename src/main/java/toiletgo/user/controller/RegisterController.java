package toiletgo.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toiletgo.user.entity.User;
import toiletgo.user.dto.UserDto;
import toiletgo.user.repository.UserRepository;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

/**
 * <h3>Register Controller</h3>
 *
 * <li><b>/login/register :</b> 입력한 회원 정보를 받아서 최종 회원 등록 요청 </li>
 * <li><b>/login/register/verify-user :</b> 중복 유저 확인</li>
 */

@AllArgsConstructor
@RestController
public class RegisterController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * <li><b>/login/register :</b> 입력한 회원 정보를 받아서 최종 회원 등록 요청 </li>
     * @param user : User instance를 json 객체로 받아서 To Entity  <br>
     *  <b>userId, username, password : </b> 사용자가 입력한 값으로 초기화 <br>
     *  <b>user point: 회원 가입 시 0으로 초기화</b><br>
     *  <b>user trust(신뢰 점수): default 값으로 초기화</b><br><br>
     *             
     *  <li><b>/login/register/verify-user :</b> 중복 유저 확인 절차 거쳐야 함</li>
     * @return
     *
     *
     */


    @PostMapping("/login/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            user.setUserPoint(0);
            user.setUserTrust(8);
            user.setUserProfileImg(null);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);

            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            e.printStackTrace();

            //400 Bad Request
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("회원가입 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


}



