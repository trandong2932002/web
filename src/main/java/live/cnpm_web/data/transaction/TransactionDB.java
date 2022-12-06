package live.cnpm_web.data.transaction;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.account.TransactionAccount;
import live.cnpm_web.servlet.Savings;
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
}
