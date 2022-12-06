package live.cnpm_web.entity.account;

import live.cnpm_web.entity.account.account.BaseAccount;
import live.cnpm_web.entity.verification.Verification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "Activity")
public class Activity implements Serializable {
    @Id
    @SequenceGenerator(name = "activity_seq", sequenceName = "activity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @Column(name = "expired_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BaseAccount account;

    @OneToOne
    @JoinColumn(name = "verification_id", unique = true)
    private Verification verification;

    public Activity(BaseAccount account, Verification verification, String sessionId) {
        createdTime = LocalDateTime.now();
        // session expired after 15 minutes
        expiredTime = createdTime.plus(100, ChronoUnit.MINUTES);

        this.account = account;
        this.verification = verification;
        this.sessionId = sessionId;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BaseAccount getAccount() {
        return account;
    }

    public void setAccount(BaseAccount account) {
        this.account = account;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
