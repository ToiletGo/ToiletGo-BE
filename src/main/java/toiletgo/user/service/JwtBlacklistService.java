package toiletgo.user.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 임시로 두는 클래스입니다 삭제 x
 */
@Service
public class JwtBlacklistService {

    // <문자열, 만료 시간>
    private final Map<String, Long> blacklist = new ConcurrentHashMap<>();

    public void blacklist(String token, long expirationMillis) {
        long expiryTime = System.currentTimeMillis() + expirationMillis;
        blacklist.put(token, expiryTime);
    }

    public boolean isBlacklisted(String token) {
        Long expiry = blacklist.get(token);
        if (expiry == null) return false;
        if (System.currentTimeMillis() > expiry) {
            blacklist.remove(token);
            return false;
        }
        return true;
    }
}