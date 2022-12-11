package live.cnpm_web.entity.verification;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "VerificationCode")
public class VerificationCode {

    @Id
    @SequenceGenerator(name = "verification_code_id_seq", sequenceName = "verification_code_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_code_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @Column(name = "expired_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredTime;

    @Column(name = "code")
    private String code;

    @ManyToOne
    private Verification verification;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VerificationCode verificationCode = (VerificationCode) obj;
        return Objects.equals(code, verificationCode.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    public VerificationCode() {
        createdTime = LocalDateTime.now();
        // expired after 60s
        expiredTime = createdTime.plus(60, ChronoUnit.SECONDS);
        code = "";
        for (int x : new Random().ints(0, 9).distinct().limit(6).toArray()) {
            code += x;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }
}
