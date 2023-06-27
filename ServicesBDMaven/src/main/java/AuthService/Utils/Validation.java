package AuthService.Utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class Validation {
    public static final String SECRET_KEY_STRING = "g2kR6z8G9J$M!aD*";

    public static boolean validateToken(String token) {
        try {
            byte[] secretKeyBytes = SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8);
            Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            Date expiration = claims.getBody().getExpiration();
            return expiration != null && !expiration.before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
