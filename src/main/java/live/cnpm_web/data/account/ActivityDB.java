package live.cnpm_web.data.account;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.util.DBUtil;
import live.cnpm_web.entity.account.Activity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ActivityDB extends BaseDB {

    public static Activity selectBySessionId(String sessionId) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM Activity u " +
                "WHERE u.createdTime = (SELECT MAX(x.createdTime) " +
                                        "FROM Activity x " +
                                        "WHERE x.sessionId = :sessionId) " +
                "AND u.sessionId = :sessionId ";
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
}
