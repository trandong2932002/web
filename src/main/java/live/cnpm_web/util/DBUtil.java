package live.cnpm_web.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    public static EntityManagerFactory getEMFactory() {
        return emf;
    }
}
