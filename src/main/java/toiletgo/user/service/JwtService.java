package toiletgo.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <h3> class : JWTService </h3>
 *
 */
@Data
@Component
public class JwtService {
    static final long EXPIRATION_TIME = 3600000L;
    static final String PREFIX = "Bearer";
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // blacklist token
    private static final ConcurrentHashMap<String, Long> blacklist = new ConcurrentHashMap<>();


    public String getToken(String username){
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
        return token;
    }

    // 요청된 Authorization 헤더에서 token을 가져온 뒤 확인하고 사용자 이름을 가져옴
    public String getAuthUser(HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token != null ){
            String user = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();

            if(user != null){
                return user;
            }
        }
        return null;
    }


    public void blacklistToken(String token) {
        logger.info(">>> blacklistToken() invoked");

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Date expiration = claims.getExpiration();
            long ttl = expiration.getTime() - System.currentTimeMillis();

            if (ttl > 0) {
                blacklist.put(token, expiration.getTime());
            }

            logger.info("=== blacklist size ===" + blacklist.size());
            logger.info("================== blacklist token list ==================");
            blacklist.forEach((key, exp) -> System.out.println("key: " + key + ", exp: " + exp));
            logger.info("====================================================");

        } catch (Exception e) {
            System.out.println(">>> Exception ");
            e.printStackTrace();
        }
    }

    public boolean isBlacklisted(String token) {
        Long expiry = blacklist.get(token);
        if (expiry == null) return false;

        // 만료 시간 지난 블랙리스트 항목 제거
        if (expiry < System.currentTimeMillis()) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }




}
