package live.cnpm_web.data.account;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.data.DBUtil;
import live.cnpm_web.entity.account.Activity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ActivityDB extends BaseDB {
    public static Activity selectLastest(Long id) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM Activity u"
                + " ORDER BY u.createdTime DESC";
        TypedQuery<Activity> q = em.createQuery(qString, Activity.class);
        try {
            Activity activity = q.getSingleResult();
            return activity;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
