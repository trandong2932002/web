package live.cnpm_web.entity.verification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Verification")
public class Verification implements Serializable {

    @Id
    @SequenceGenerator(name = "verification_id_seq", sequenceName = "verification_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @Column(name = "expired_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredTime;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "verification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VerificationCode> verificationCodeList = new ArrayList<>();

    public void addVerificationCode(VerificationCode verificationCode) {
        verificationCodeList.add(verificationCode);
        verificationCode.setVerification(this);
    }

    public Verification() {
        createdTime = LocalDateTime.now();
        // expired after 5 minutes
        expiredTime = createdTime.plus(5, ChronoUnit.MINUTES);
        // create the first verification code
        VerificationCode verificationCode = new VerificationCode();
        addVerificationCode(verificationCode);

        status = false;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<VerificationCode> getVerificationCodeList() {
        return verificationCodeList;
    }

}
