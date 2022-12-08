package live.cnpm_web.data.account;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.account.account.Customer;
import live.cnpm_web.entity.account.account.Employee;
import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AccountDB extends BaseDB {

    public static <T> T authenticate(String phoneNumber, String password, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM " + klass.getSimpleName() + " u"
                + " WHERE u.phoneNumber = :phoneNumber"
                + " AND u.password = :password";
        TypedQuery<T> q = em.createQuery(qString, klass);
        q.setParameter("phoneNumber", phoneNumber);
        q.setParameter("password", password);
        try {
            T account = q.getSingleResult();
            return account;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static <T> boolean checkPhoneNumberExist(String phoneNumber, Class<T> klass) {
        return selectByParameter("phoneNumber", phoneNumber, klass) != null;
    }

    public static <T> boolean checkEmailExist(String email, Class<T> klass) {
        return selectByParameter("email", email, klass) != null;
    }

    public static boolean checkTransactionAccountNumberExist(String accountNumber) {
        return selectByParameter("accountNumber", accountNumber, TransactionAccount.class) != null;
    }

    public static TransactionAccount selectByAccountNumber(String accountNumber) {
        return selectByParameter("accountNumber", accountNumber, TransactionAccount.class);
    }

    // Employee
    public static Customer selectBySSN(String ssn) {
        return selectByParameter("ssn", ssn, Customer.class);
    }

    public static boolean authenticateEmployee(String phoneNumber, String password) {
        return authenticate(phoneNumber, password, Employee.class) == null;
    }

    public static Customer selectByPhoneNumber(String phoneNumber) {
        return selectByParameter("phoneNumber", phoneNumber, Customer.class);
    }
}
