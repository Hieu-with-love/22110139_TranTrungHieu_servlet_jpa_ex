package devzeus.com.demojpa.services.impl;

import devzeus.com.demojpa.daos.ICategoryDao;
import devzeus.com.demojpa.daos.ipml.CategoryDao;
import devzeus.com.demojpa.entities.Category;
import devzeus.com.demojpa.services.ICategoryService;

import java.util.List;

public class CategoryService implements ICategoryService {
    ICategoryDao categoryDao = new CategoryDao();

    public CategoryService() {
    }

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(int cateId) {
        categoryDao.delete(cateId);
    }

    @Override
    public Category findById(int cateId) {
        return categoryDao.findById(cateId);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category> findByCategoryName(String cateName) {
        return categoryDao.findByCategoryName(cateName);
    }

    @Override
    public List<Category> findAll(int page, int pageSize) {
        return categoryDao.findAll(page, pageSize);
    }
}
