package live.cnpm_web.entity.verification;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Verification")
public class Verification implements Serializable {

    public enum VerificationStatus {
        SUCCESS, FAILURE
    }

    @Id
    @SequenceGenerator(name = "verification_id_seq", sequenceName = "verification_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @Column(name = "expired_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private VerificationStatus status;

    @OneToMany(mappedBy = "verification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VerificationCode> verificationCodeList = new ArrayList<>();

    public void addVerificationCode(VerificationCode verificationCode) {
        verificationCodeList.add(verificationCode);
        verificationCode.setVerification(this);
    }

    public Verification() {
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

    public VerificationStatus getStatus() {
        return status;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }
}
