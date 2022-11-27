package live.cnpm_web.data.account;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.data.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountDB extends BaseDB {

    public static <T> boolean authenticate(String phoneNumber, String password, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM " + klass.getSimpleName() + " u"
                + " WHERE u.phone_number = :phone_number"
                + " AND u.password = :password";
        TypedQuery<T> q = em.createQuery(qString, klass);
        q.setParameter("phone_number", phoneNumber);
        q.setParameter("password", password);
        try {
            T account = q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    public static <T> boolean checkPhoneNumberExist(String phoneNumber, Class<T> klass) {
        if (selecctByParameter("phone_number", phoneNumber, klass) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> boolean checkEmailExist(String email, Class<T> klass) {
        if (selecctByParameter("email", email, klass) != null) {
            return true;
        } else {
            return false;
        }
    }
}
