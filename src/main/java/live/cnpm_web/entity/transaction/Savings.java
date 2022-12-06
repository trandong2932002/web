package live.cnpm_web.entity.transaction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Savings")
public class Savings extends BaseTransaction {

//    public enum TermInterest

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDateTime createdDate;

    @Column(name = "maturity_date", columnDefinition = "DATE")
    private LocalDateTime maturityDate;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Column(name = "term")
    private Integer term;

    @Column(name = "interest", precision = 4, scale = 0)
    private Double interest;

    @Column(name = "penalty_interest", precision = 4, scale = 0)
    private Double penaltyInterest;

    @Column(name = "rolled_over")
    private Boolean rolledOver;

    public Savings() {
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDateTime maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getPenaltyInterest() {
        return penaltyInterest;
    }

    public void setPenaltyInterest(Double penaltyInterest) {
        this.penaltyInterest = penaltyInterest;
    }

    public Boolean getRolledOver() {
        return rolledOver;
    }

    public void setRolledOver(Boolean rolledOver) {
        this.rolledOver = rolledOver;
    }
}
