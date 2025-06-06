package toiletgo.user.controller;

import org.springframework.http.HttpHeaders;
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

@RestController
public class LoginController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginController(final JwtService jwtService, final AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

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


}

