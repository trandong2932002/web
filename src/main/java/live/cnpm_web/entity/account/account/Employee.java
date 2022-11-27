package live.cnpm_web.entity.account.account;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Employee")
public class Employee extends BaseAccount {
    public Employee() {
    }

    public Employee(String firstname, String lastname, String phoneNumber, String email, String password) {
        super(firstname, lastname, phoneNumber, email, password);
    }
}
