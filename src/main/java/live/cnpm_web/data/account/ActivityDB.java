package live.cnpm_web.data.account;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.util.DBUtil;
import live.cnpm_web.entity.account.Activity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActivityDB extends BaseDB {

    public static Activity selectBySessionId(String sessionId) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
//        String qString = "select u from Activity u " +
//                "where u.sessionId = :sessionId " +
//                "and u.createdTime = " +
//                "(select max(v.createdTime) " +
//                "from Activity v " +
//                "where v.sessionId = u.sessionId)";
        String qString = "select u from Activity  u " +
                "where u.sessionId = :sessionId";
        TypedQuery<Activity> q = em.createQuery(qString, Activity.class);
        q.setParameter("sessionId", sessionId);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static Activity selectByVerification(Verification verification) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "select u from Activity  u " +
                "where u.verification = :verification";
        TypedQuery<Activity> q = em.createQuery(qString, Activity.class);
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
