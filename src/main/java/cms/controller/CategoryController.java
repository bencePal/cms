package cms.controller;

import cms.service.CategoryService;
import cms.service.MenuService;
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
    private MenuService menuService;
    private PageService pageService;

    @Autowired
    public CategoryController(PostService postService, CategoryService categoryService, MenuService menuService, PageService pageService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.menuService = menuService;
        this.pageService = pageService;
    }


    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public String postsAndPagesByCategory(Model model, @PathVariable Long categoryId) {
        model.addAttribute("posts", postService.getAllPostByCategoryId(categoryId));
        model.addAttribute("pages", pageService.getAllPageByCategoryId(categoryId));
        model.addAttribute("menu", menuService.getAllMenuItems());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("categoryName", categoryService.getCategoryName(categoryId));
        return "page-post-by-category";
    }
}
