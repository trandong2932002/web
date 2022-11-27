package live.cnpm_web.entity.account;

import live.cnpm_web.entity.account.account.Customer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TransactionAccount")
public class TransactionAccount implements Serializable {

    @Id
    @SequenceGenerator(name = "transaction_account_seq", sequenceName = "transaction_account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_account_seq")
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", unique = true, nullable = false)
    private Customer customer;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "balance", precision = 20, scale = 0)
    private Double balance;

    // transaction account should have list of transaction
    // but I want to keep it lazy so this entity do not have to fetch a lot of data when it has been loaded
    // so I set @ManyToOne relation: transaction -> transaction account
    // method that transaction account load transaction is in transactiondb

    public TransactionAccount() {
    }

    public Long getId() {
        return id;
    }

    public Customer getAccount() {
        return customer;
    }

    public void setAccount(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
