package cms.controller.admin;

import cms.model.Category;
import cms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminCategoryController {

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public String categoryList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/category-list";
    }

    @RequestMapping(value = "/admin/categories/create-category", method = RequestMethod.GET)
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/create-category";
    }

    @RequestMapping(value = "/admin/categories/create-category", method = RequestMethod.POST)
    public String saveCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/categories/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/categories/{categoryId}/update", method = RequestMethod.GET)
    public String updateCategory(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("category", category);
        return "admin/edit-category";
    }

    @RequestMapping(value = "/admin/categories/update", method = RequestMethod.POST)
    public String updateCategory(Category category) {
        categoryService.saveCategory(category);
        return "redirect:/admin/categories/";
    }
}
