package live.cnpm_web.data.verification;

import live.cnpm_web.data.BaseDB;
import live.cnpm_web.entity.verification.Verification;
import live.cnpm_web.entity.verification.VerificationCode;
import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class VerificationDB extends BaseDB {
    public static Long selectVerificationCodeIdByCode(Verification verification, String code) {
        Class<VerificationCode> klass = VerificationCode.class;
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM " + klass.getSimpleName() + " u " +
                "WHERE u.createdTime = (SELECT MAX(x.createdTime) " +
                                        "FROM " + klass.getSimpleName() + " x " +
                                        "WHERE x.verification = :verification " +
                                        "AND x.code = :code) " +
                "AND u.verification = :verification " +
                "AND u.code = :code ";
        TypedQuery<VerificationCode> q = em.createQuery(qString, klass);
        q.setParameter("verification", verification);
        q.setParameter("code", code);
        try {
            VerificationCode t = q.getSingleResult();
            return t.getId();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
