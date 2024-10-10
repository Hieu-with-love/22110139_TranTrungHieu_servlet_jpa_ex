package devzeus.com.demojpa.daos.ipml;

import devzeus.com.demojpa.configs.JpaConfig;
import devzeus.com.demojpa.daos.IVideoDao;
import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDao implements IVideoDao {
    @Override
    public void insert(Video video) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.persist(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.merge(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int videoId) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            Video video = enma.find(Video.class, videoId);
            enma.persist(video);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public Video findById(int videoId) {
        EntityManager enma = JpaConfig.getEntityManager();
        return enma.find(Video.class, videoId);
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JpaConfig.getEntityManager();
        return enma.createNamedQuery("Video.findAll", Video.class).getResultList();
    }

    @Override
    public List<Video> findByVideoName(String videoName) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :videoName";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("videoName", "%" + videoName + "%");
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        EntityManager enma = JpaConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
