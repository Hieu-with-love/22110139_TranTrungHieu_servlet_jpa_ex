package devzeus.com.demojpa.daos;

import devzeus.com.demojpa.entities.Category;

import java.util.List;

public interface ICategoryDao {
    void insert(Category category);

    void update(Category category);

    void delete(int cateId);

    Category findById(int cateId);

    List<Category> findAll();

    List<Category> findByCategoryName(String cateName);

    List<Category> findAll(int page, int pageSize);
}
