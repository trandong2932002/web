package live.cnpm_web.entity.account.account;

import live.cnpm_web.entity.account.TransactionAccount;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Customer")
public class Customer extends BaseAccount {
    @Column(name = "address")
    private String address;

    @Column(name = "dob", columnDefinition = "DATE")
    private LocalDate dob;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private TransactionAccount transactionAccount;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public TransactionAccount getTransactionAccount() {
        return transactionAccount;
    }

    public void addTransactionAccount(TransactionAccount transactionAccount) {
        transactionAccount.setAccount(this);
        this.transactionAccount = transactionAccount;
    }

    public void removeTransactionAcocunt()
    {
        if (transactionAccount != null) {
            transactionAccount.setAccount(null);
            this.transactionAccount = null;
        }
    }

    public Customer() {
    }

    public Customer(String firstname, String lastname, String phoneNumber, String email, String password, String address, LocalDate dob) {
        super(firstname, lastname, phoneNumber, email, password);
        this.address = address;
        this.dob = dob;
    }
}
