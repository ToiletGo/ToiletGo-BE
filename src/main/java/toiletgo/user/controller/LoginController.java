package toiletgo.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toiletgo.user.auth.UserCredentials;
import toiletgo.user.service.JwtService;

/**
 * <h3>Login Controller</h3>
 *
 * <li><b> /login :</b> user id, pw를 받아서 로그인 요청을 하고 header에 jwt 반환 </li>
 */

@AllArgsConstructor
@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * <h3> Post : /login </h3>
     * @param credentials
     * @return
     *  <b>ok: 200</b> <br>
     *  <b>fail: 401</b>
     */
    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody UserCredentials credentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.userid(),credentials.password());
        Authentication auth = authenticationManager.authenticate(creds);

        // token 생성
        String jwts = jwtService.getToken(auth.getName());

        // 생성된 token으로 응답 빌드
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();

    }

    /**
     * <h3>Post : /logout</h3>
     * @param request
     * @return ResponseEntity.status(HttpStatus. )
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        System.out.println("/logout");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        logger.info("== authHeader : {}", authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("token not found");
        }

        String token = authHeader.substring(7);
        logger.info("== token : {}", token);
        logger.info("== token before blacklistToken == ");
        jwtService.blacklistToken(token);
        logger.info("== token after blacklistToken == ");

        return ResponseEntity.ok("로그아웃 처리 완료");
    }


}

