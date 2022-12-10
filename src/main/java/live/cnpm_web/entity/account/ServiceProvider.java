package live.cnpm_web.entity.account;

import live.cnpm_web.entity.account.account.Customer;

import javax.persistence.*;

@Entity
@Table(name = "ServiceProvider")
public class ServiceProvider {
    public enum ServiceType {
        ELECTRICITY, WATER, INTERNET
    }
    @Id
    @SequenceGenerator(name = "service_provider_seq", sequenceName = "service_provider_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_provider_seq")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private ServiceType type;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id", unique = true, nullable = false)
    private Customer provider;

    public ServiceProvider() {
    }

    public Long getId() {
        return id;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getProvider() {
        return provider;
    }

    public void setProvider(Customer provider) {
        this.provider = provider;
    }
}
