package live.cnpm_web.entity.message;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MessageDetail")
public class MessageDetail implements Serializable {
    @Id
    @SequenceGenerator(name = "message_detail_id_seq", sequenceName = "message_detail_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_detail_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "time", columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    @Column(name = "content")
    private String content;

    @ManyToOne
    private Message message;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageDetail messageDetail = (MessageDetail) obj;
        return Objects.equals(content, messageDetail.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content);
    }

    public MessageDetail() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
