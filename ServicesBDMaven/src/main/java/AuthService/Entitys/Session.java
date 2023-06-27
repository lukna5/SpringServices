package AuthService.Entitys;

import AuthService.Entitys.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date duration;

    private boolean active;

    @Column(unique = true)
    private String token;

    public Session() {}

    public Session(User user, String token) {
        this.user = user;
        this.creationDate = new Date();
        this.duration = new Date();
        this.active = true;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getDuration() {
        return duration;
    }

    public boolean isActive() {
        return active;
    }

    public String getToken() {
        return token;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @PrePersist
    public void setDuration() {
        this.duration = new Date(new Date().getTime() - this.creationDate.getTime());
    }
}