package live.cnpm_web.data.transaction;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.savings.Savings;
import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class TransactionDB extends BaseDB {
    public static <T> List<T> selectAllByTransactionAccount(TransactionAccount src, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u from " + klass.getSimpleName() + " u " +
                "WHERE u.transactionAccountSource = :transactionAccountSource";
        TypedQuery<T> q = em.createQuery(qString, klass);
        q.setParameter("transactionAccountSource", src);
        try {
            List<T> transactions = q.getResultList();
            return transactions;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<Savings> selectAllSavingsByTransactionAcocunt(TransactionAccount src) {
        return selectAllByTransactionAccount(src, Savings.class);
    }

    public static List<Savings> selectAllSavingsInProgress() {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u from Savings u " +
                "WHERE u.status = :status";
        TypedQuery<Savings> q = em.createQuery(qString, Savings.class);
        q.setParameter("status", Savings.SavingsStatus.INPROGRESS);
        try {
            List<Savings> savingsList = q.getResultList();
            return savingsList;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    // use for transaction history and statement
    public static <T> List<T> selectByCondition(TransactionAccount src, Class<T> type, String period, String amount) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u from " + type.getSimpleName() + " u " +
                "WHERE u.transactionAccountSource = :transactionAccountSource ";

        int iperiod = 0;
        if (period.equals("0")) iperiod = 1;
        else if (period.equals("1")) iperiod = 3;
        else if (period.equals("2")) iperiod = 7;
        String qStringOfPeriod = "AND u.createdTime > '" + LocalDate.now().minusDays(iperiod).toString() + "' ";
        String qStringOfAmount = "";
        if (amount.equals("0")) qStringOfAmount = "";
        else if (amount.equals("1")) qStringOfAmount = "AND u.amount <= 10000";
        else if (amount.equals("2")) qStringOfAmount = "AND u.amount > 10000 AND u.amount <= 100000";
        else if (amount.equals("3")) qStringOfAmount = "AND u.amount > 100000 AND u.amount <= 1000000";
        else if (amount.equals("4")) qStringOfAmount = "AND u.amount > 1000000 ";

        qString += qStringOfPeriod + qStringOfAmount;

        TypedQuery<T> q = em.createQuery(qString, type);
        q.setParameter("transactionAccountSource", src);
        try {
            List<T> transactions = q.getResultList();
            return transactions;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static <T> List<T> selectAllByCondition(Class<T> type, String period, String amount) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u from " + type.getSimpleName() + " u ";

        int iperiod = 0;
        if (period.equals("0")) iperiod = 1;
        else if (period.equals("1")) iperiod = 3;
        else if (period.equals("2")) iperiod = 7;
        String qStringOfPeriod = "WHERE u.createdTime > '" + LocalDate.now().minusDays(iperiod).toString() + "' ";

        if (iperiod != 0) qString += qStringOfPeriod;

        String qStringOfAmount = "";
        if (amount.equals("0")) ;
        else if (amount.equals("1")) qStringOfAmount = "u.amount <= 10000";
        else if (amount.equals("2")) qStringOfAmount = "u.amount > 10000 AND u.amount <= 100000";
        else if (amount.equals("3")) qStringOfAmount = "u.amount > 100000 AND u.amount <= 1000000";
        else if (amount.equals("4")) qStringOfAmount = "u.amount > 1000000 ";

        if (!qStringOfAmount.equals(""))
            if (iperiod == 0) qString += "WHERE " + qStringOfAmount;
            else qString += "AND " + qStringOfAmount;

        System.out.println(qString);

        TypedQuery<T> q = em.createQuery(qString, type);
        try {
            List<T> transactions = q.getResultList();
            return transactions;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
