package live.cnpm_web.entity.transaction;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.TransactionAccount;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Savings")
public class Savings extends BaseTransaction {

    public enum SavingsStatus {
        FINISH, INPROGRESS, BREAK
    }

    public enum Term {
        ONEMONTH(1), THREEMONTHS(3), SIXMONTHS(6),
        TWELVEMONTHS(12), TWENTYFOURMONTHS(24), THIRTYSIXMONTHS(36);

        private int num;

        private Term(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public static Term getValue(int num) {
            return Term.values()[num];
        }
    }

    public enum RolledOver {
        NO(0), RENEWALSBASE(1), RENEWALSBASEANDINTEREST(2);
        private int num;

        RolledOver(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public static RolledOver getValue(int num) {
            for (RolledOver e : RolledOver.values()) {
                if (e.num == num) {
                    return e;
                }
            }
            return null;
        }
    }

    @Column(name = "created_date", columnDefinition = "DATE")
    private LocalDate createdDate;

    @Column(name = "maturity_date", columnDefinition = "DATE")
    private LocalDate maturityDate;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "term")
    private Term term;

    @Column(name = "interest", precision = 4, scale = 0)
    private Double interest;

    @Column(name = "penalty_interest", precision = 4, scale = 0)
    private Double penaltyInterest;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rolled_over")
    private RolledOver rolledOver;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private SavingsStatus status;

    public Savings() {
    }

    public Savings(TransactionAccount src, String name, Double amount, int term, int rolledOver) {
        super();
        setName(name);
        setAmount(amount);
        this.term = Term.getValue(term);
        this.rolledOver = RolledOver.getValue(rolledOver);
        this.status = SavingsStatus.INPROGRESS;
        switch (term) {
            case 0:
            case 1:
                this.interest = 0.05;
                this.penaltyInterest = 0.008;
                break;
            case 2:
            case 3:
                this.interest = 0.06;
                this.penaltyInterest = 0.008;
                break;
            case 4:
            case 5:
                this.interest = 0.07;
                this.penaltyInterest = 0.008;
                break;
        }

        setTransactionAccountSource(src);
        setTransactionAccountDestination(AccountDB.selectById(0L, TransactionAccount.class));
    }

    public SavingsStatus getStatus() {
        return status;
    }

    public void setStatus(SavingsStatus status) {
        this.status = status;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate() {
        this.maturityDate = createdDate.plusMonths(term.getNum());
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

    public RolledOver getRolledOver() {
        return rolledOver;
    }

    public void setRolledOver(RolledOver rolledOver) {
        this.rolledOver = rolledOver;
    }
}
