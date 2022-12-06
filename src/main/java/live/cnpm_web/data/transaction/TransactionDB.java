package live.cnpm_web.data.transaction;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionDB extends BaseDB {
    public static <T> List<T> selectAllByTransactionAccountId(Long id, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u from " + klass.getSimpleName() + " u"
                + " WHERE u.transaction_account_id = :transaction_account_id";
        TypedQuery<T> q = em.createQuery(qString, klass);
        q.setParameter("transaction_account_id", id);
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
