package live.cnpm_web.data.transaction;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.entity.transaction.BaseTransaction;
import live.cnpm_web.entity.transaction.Savings;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
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
        q.setParameter("status", live.cnpm_web.entity.transaction.Savings.SavingsStatus.INPROGRESS);
        try {
            List<Savings> savingsList = q.getResultList();
            return savingsList;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static BaseTransaction selectByVerification(Verification verification) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
//        String qString1 = "SELECT u from Savings u " +
//                "WHERE u.verification = :verification";
//        String qString2 = "SELECT u from Transfer u " +
//                "WHERE u.verification = :verification";
//        TypedQuery<Savings> q = em.createQuery(qString1, Savings.class);
//        TypedQuery<Savings> q = em.createQuery(qString1, Savings.class);

        String qString = "select u from BaseTransaction u " +
                "where u.verification = :verification";
        TypedQuery<BaseTransaction> q = em.createQuery(qString, BaseTransaction.class);
        q.setParameter("verification", verification);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
