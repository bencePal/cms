package cms.controller;

import cms.model.Post;
import cms.service.CategoryService;
import cms.service.PageService;
import cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    private PostService postService;
    private CategoryService categoryService;
    private PageService pageService;

    @Autowired
    public HomeController(PostService postService, CategoryService categoryService, PageService pageService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.pageService = pageService;
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("posts", postService.getAllPost());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("pages", pageService.getAllPage());
        return "home";
    }
}
