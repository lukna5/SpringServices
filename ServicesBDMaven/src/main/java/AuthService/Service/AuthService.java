package AuthService.Service;

import AuthService.Entitys.Session;
import AuthService.Entitys.User;
import AuthService.Enums.Role;
import AuthService.Utils.Validation;
import io.jsonwebtoken.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class AuthService {
    private static final long TOKEN_EXPIRATION_TIME = 60 * 60 * 1000;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public User registerUser(String firstName, String lastName, String login, String password, String state, Role role) {
        if (userRepository.findByLogin(login) != null) {
            throw new IllegalArgumentException("User with this login already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(firstName, lastName, login, encodedPassword, state, role);
        return userRepository.save(user);
    }

    public String authenticateUser(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("login", login);
        String token = generateToken(claims);
        Session session = new Session(user, token);
        user.addSession(session);
        userRepository.save(user);

        return session.getToken();
    }

    public String generateToken(Map<String, Object> claims) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] secretKeyBytes = Validation.SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8);
        Key key = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, key);

        return builder.compact();
    }

}
