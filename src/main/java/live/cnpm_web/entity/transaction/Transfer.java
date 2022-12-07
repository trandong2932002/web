package live.cnpm_web.entity.transaction;

import live.cnpm_web.entity.account.TransactionAccount;

import javax.persistence.*;

@Entity
@Table(name = "Transfer")
public class Transfer extends BaseTransaction {

    public enum TransferStatus {
        SUCCESS, FAILURE
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private TransferStatus status;

    @Column(name = "content")
    private String content;


    public Transfer() {
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }
    
    public Transfer(TransactionAccount src, TransactionAccount dest, double amount, String content) {
        super();
        setName("transfer");
        setStatus(TransferStatus.SUCCESS);
        setAmount(amount);
        this.content = content;

        setTransactionAccountSource(src);
        setTransactionAccountDestination(dest);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
