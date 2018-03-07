package cms.controller;

import cms.model.Category;
import cms.service.CategoryService;
import cms.service.PageService;
import cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CategoryController {
    private PostService postService;
    private CategoryService categoryService;
    private PageService pageService;

    @Autowired
    public CategoryController(PostService postService, CategoryService categoryService, PageService pageService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.pageService = pageService;
    }


    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public String postsAndPagesByCategory(Model model, @PathVariable Long categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        model.addAttribute("posts", postService.getAllPostByCategoryId(category));
        model.addAttribute("pages", pageService.getAllPageByCategoryId(category));
        return "page-post-by-category";
    }
}
