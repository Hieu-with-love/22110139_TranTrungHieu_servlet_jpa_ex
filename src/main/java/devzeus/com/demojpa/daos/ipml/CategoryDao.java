package devzeus.com.demojpa.daos.ipml;

import devzeus.com.demojpa.configs.JpaConfig;
import devzeus.com.demojpa.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CategoryDao implements devzeus.com.demojpa.daos.ICategoryDao {
    @Override
    public void insert(Category category) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.persist(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Category category) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            enma.merge(category);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int cateId) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction transaction = enma.getTransaction();
        try {
            transaction.begin();
            // Your code here
            Category category = enma.find(Category.class, cateId);
            if (category != null) {
                enma.remove(category);
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

    @Override
    public Category findById(int cateId){
        EntityManager enma = JpaConfig.getEntityManager();
        Category category = enma.find(Category.class, cateId);
        return category;
    }

    @Override
    public List<Category> findAll(){
        EntityManager enma = JpaConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findByCategoryName(String cateName){
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE LOWER(c.name) LIKE :cateName";
        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("cateName", "%"+cateName+"%");
        return query.getResultList();
    }

    @Override
    public List<Category> findAll(int page, int pageSize){
        EntityManager enma = JpaConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
