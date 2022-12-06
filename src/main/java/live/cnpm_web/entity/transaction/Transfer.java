package live.cnpm_web.entity.transaction;

import live.cnpm_web.entity.account.TransactionAccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transfer")
public class Transfer extends BaseTransaction {
    @Column(name = "content")
    private String content;

    public Transfer() {
    }

    public Transfer(TransactionAccount src, TransactionAccount dest, double amount, String content) {
        super();
        setName("transfer");
        setStatus(TransactionStatus.SUCCESS);
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
