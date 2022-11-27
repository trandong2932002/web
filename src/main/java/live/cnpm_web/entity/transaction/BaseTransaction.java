package live.cnpm_web.entity.transaction;

import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.verification.Verification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseTransaction implements Serializable {

    public enum TransactionStatus {
        SUCCESS, FAILURE
    }

    @Id
    @SequenceGenerator(name = "default_gen", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "amount", precision = 20, scale = 0)
    private Double amount;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @OneToOne
    @JoinColumn(name = "verification_id", unique = true)
    private Verification verification;

    @ManyToOne
    @JoinColumn(name = "transaction_account_id")
    private TransactionAccount transactionAccount;

    public BaseTransaction() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public TransactionAccount getTransactionAccount() {
        return transactionAccount;
    }

    public void setTransactionAccount(TransactionAccount transactionAccount) {
        this.transactionAccount = transactionAccount;
    }
}
