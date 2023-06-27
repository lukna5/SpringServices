package AuthService.Entitys;

import AuthService.Enums.Role;
import AuthService.Service.AuthService;
import AuthService.Utils.Validation;
import io.jsonwebtoken.*;
import jakarta.persistence.*;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Session> sessions = new ArrayList<>();

    public User() {}

    public User(String firstName, String lastName, String login, String password, String state, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.state = state;
        this.role = role;
    }

    public Long getId(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return id;
    }

    public String getFirstName(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Role getRole(String login, Role role, String token) {
        if (!checkAccess(login, role, token)){
            return null;
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
        session.setUser(this);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
        session.setUser(null);
    }

    private boolean checkAccess(String login, Role role, String token){
        return (login.equals(this.login) || role == Role.ADMIN) && Validation.validateToken(token);
    }

}