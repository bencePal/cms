package cms.controller;

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
    private PageService pageService;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(PostService postService, PageService pageService, CategoryService categoryService) {
        this.postService = postService;
        this.pageService = pageService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public String postsAndPagesByCategory(Model model, @PathVariable Long categoryId) {
        model.addAttribute("posts", postService.getAllPostByCategoryId(categoryId));
        model.addAttribute("pages", pageService.getAllPageByCategoryId(categoryId));
        model.addAttribute("menu", pageService.getAllPage());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("categoryName", categoryService.getCategoryName(categoryId));
        return "page-post-by-category";
    }
}
