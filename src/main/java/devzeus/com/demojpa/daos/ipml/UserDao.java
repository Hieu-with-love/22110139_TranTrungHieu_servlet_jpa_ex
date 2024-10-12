package devzeus.com.demojpa.daos.ipml;

import devzeus.com.demojpa.configs.JpaConfig;
import devzeus.com.demojpa.daos.IUserDao;
import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public boolean register(User user) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.persist(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        EntityManager enma = JpaConfig.getEntityManager();
        return enma.find(User.class, username);
    }

    @Override
    public User findByEmail(String email) {
        EntityManager enma = JpaConfig.getEntityManager();
        return enma.find(User.class, email);
    }

    @Override
    public List<User> findAll() {
        EntityManager enma = JpaConfig.getEntityManager();
        TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    @Override
    public boolean update(User user) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.merge(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
        return false;
    }

    @Override
    public void delete(String username) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            User user = enma.find(User.class, username);
            if (user != null) {
                enma.remove(user);
            }else{
                throw new Exception("Not found");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }
}
