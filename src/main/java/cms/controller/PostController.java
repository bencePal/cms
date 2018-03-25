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
public class PostController {

    private PostService postService;
    private PageService pageService;
    private CategoryService categoryService;

    @Autowired
    public PostController(PostService postService, PageService pageService, CategoryService categoryService) {
        this.postService = postService;
        this.pageService = pageService;
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/post/{postId}", method = RequestMethod.GET)
    public String postPage(@PathVariable Long postId, Model model)  {
        model.addAttribute("post", postService.findPostById(postId));
        model.addAttribute("menu", pageService.getAllPage());
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "post";
    }


}
