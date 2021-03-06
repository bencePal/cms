package cms.service;

import cms.model.Category;
import cms.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category findCategoryById(Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    public String getCategoryName(Long categoryId) {
        return findCategoryById(categoryId).getName();
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository.delete(categoryId);
    }

}
