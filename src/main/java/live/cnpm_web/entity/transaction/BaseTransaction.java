package live.cnpm_web.entity.transaction;

import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.verification.Verification;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseTransaction implements Serializable {

    @Id
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "amount", precision = 20, scale = 0)
    private Double amount;

    @Column(name = "created_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdTime;

    @OneToOne
    @JoinColumn(name = "verification_id", unique = true)
    private Verification verification;

    @ManyToOne
    @JoinColumn(name = "transaction_account_source_id")
    private TransactionAccount transactionAccountSource;

    @ManyToOne
    @JoinColumn(name = "transaction_account_destination_id")
    private TransactionAccount transactionAccountDestination;

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

    public TransactionAccount getTransactionAccountSource() {
        return transactionAccountSource;
    }

    public void setTransactionAccountSource(TransactionAccount transactionAccount) {
        this.transactionAccountSource = transactionAccount;
    }

    public TransactionAccount getTransactionAccountDestination() {
        return transactionAccountDestination;
    }

    public void setTransactionAccountDestination(TransactionAccount transactionAccountDestination) {
        this.transactionAccountDestination = transactionAccountDestination;
    }
}
