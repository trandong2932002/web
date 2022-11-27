package live.cnpm_web.entity.transaction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Transfer")
public class Transfer extends BaseTransaction {
    @Column(name = "content")
    private String content;

    public Transfer() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
