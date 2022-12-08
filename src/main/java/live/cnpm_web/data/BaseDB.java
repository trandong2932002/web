package live.cnpm_web.data;

import live.cnpm_web.util.DBUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class BaseDB {

    protected static <T, V> T selectByParameter(String parameterName, V parameterValue, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM " + klass.getSimpleName() + " u"
                + " WHERE u." + parameterName + " = :" + parameterName;
        TypedQuery<T> q = em.createQuery(qString, klass);
        q.setParameter(parameterName, parameterValue);
        try {
            T t = q.getSingleResult();
            return t;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static <T> T selectById(Long id, Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        try {
            T entity = em.find(klass, id);
            return entity;
        } finally {
            em.close();
        }
    }

    public static <T> List<T> selectAll(Class<T> klass) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        String qString = "SELECT u FROM " + klass.getSimpleName() + " u";
        TypedQuery<T> q = em.createQuery(qString, klass);
        try {
            List<T> list = q.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static <T> void insert(T entity) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(entity);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static <T> void update(T entity) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    public static <T> void delete(T entity) {
        EntityManager em = DBUtil.getEMFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(entity));
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
        } finally {
            em.close();
        }
    }
}
