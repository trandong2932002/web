package live.cnpm_web.entity.transaction.savings;

import javax.persistence.*;

@Entity
@Table(name = "SavingsInterest")
public class SavingsInterest {
    @Id
    @SequenceGenerator(name = "savings_interest_seq", sequenceName = "savings_interest_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "savings_interest_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "term")
    private Savings.Term term;
    @Column(name = "interest")
    private Double interest;
    @Column(name = "penaltyInterest")
    private Double penaltyInterest;

    public SavingsInterest() {
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

    public Savings.Term getTerm() {
        return term;
    }

    public void setTerm(Savings.Term term) {
        this.term = term;
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
}
