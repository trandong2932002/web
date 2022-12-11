package live.cnpm_web.entity.transaction.savings;

import live.cnpm_web.data.account.AccountDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.BaseTransaction;
import live.cnpm_web.util.SavingsInterestUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

        // load term - interest - penalty interest
        List<SavingsInterest> interestList = SavingsInterestUtil.getSavingsInterestList();

        for (SavingsInterest temp : interestList) {
            Term i_term = temp.getTerm();
            double interest = temp.getInterest();
            double penaltyInterest = temp.getPenaltyInterest();

            if (Term.getValue(term) == i_term) {
                this.interest = interest;
                this.penaltyInterest = penaltyInterest;
                break;
            }
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
