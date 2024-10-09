package devzeus.com.demojpa.services;

import devzeus.com.demojpa.entities.Category;

import java.util.List;

public interface ICategoryService {
    void insert(Category category);

    void update(Category category);

    void delete(int cateId);

    Category findById(int cateId);

    List<Category> findAll();

    List<Category> findByCategoryName(String cateName);

    List<Category> findAll(int page, int pageSize);
}
