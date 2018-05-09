package cms.controller;

import cms.service.CategoryService;
import cms.service.MenuService;
import cms.service.PageService;
import cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private PostService postService;
    private MenuService menuService;
    private CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, MenuService menuService, CategoryService categoryService) {
        this.postService = postService;
        this.menuService = menuService;
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/post/{postId}", method = RequestMethod.GET)
    public String postPage(@PathVariable Long postId, Model model)  {
        model.addAttribute("post", postService.findPostById(postId));
        model.addAttribute("menu", menuService.getAllMenuItems());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "post";
    }

}
