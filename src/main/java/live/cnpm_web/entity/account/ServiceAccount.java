package live.cnpm_web.entity.account;

import live.cnpm_web.entity.account.account.Customer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Random;

@Entity
@Table(name = "ServiceAccount")
public class ServiceAccount implements Serializable {

    public enum ServiceAccountStatus {
        ACTIVE, STOPPED
    }
    @Id
    @SequenceGenerator(name = "service_account_seq", sequenceName = "service_account_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_account_seq")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id", unique = true, nullable = false)
    private ServiceProvider provider;

    // this code must be provided by the provider
    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "debt", precision = 20, scale = 0)
    private Double debt;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private ServiceAccountStatus status;

    public ServiceAccount() {
    }

    public ServiceAccount(ServiceProvider provider) {
        this.provider = provider;

        Random rand = new Random(System.currentTimeMillis());
        customerCode = String.valueOf(rand.nextInt(9999999) + 10000000);
        debt = 0D;
        status = ServiceAccountStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public ServiceProvider getProvider() {
        return provider;
    }

    public void setProvider(ServiceProvider provider) {
        this.provider = provider;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public ServiceAccountStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceAccountStatus status) {
        this.status = status;
    }
}
