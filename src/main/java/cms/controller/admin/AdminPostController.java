package cms.controller.admin;

import cms.model.Post;
import cms.service.CategoryService;
import cms.service.CurrentTimeService;
import cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminPostController {

    private PostService postService;
    private CurrentTimeService currentTimeService;
    private CategoryService categoryService;

    @Autowired
    public AdminPostController(PostService postService, CurrentTimeService currentTimeService, CategoryService categoryService) {
        this.postService = postService;
        this.currentTimeService = currentTimeService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/admin/posts", method = RequestMethod.GET)
    public String postList(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("activePostNav", true);
        model.addAttribute("posts", posts);
        return "admin/post-list";
    }

    @RequestMapping(value = "/admin/posts/create-post", method = RequestMethod.GET)
    public String createPost(Model model) {
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("activePostNav", true);
        model.addAttribute("post", new Post());
        return "admin/create-post";
    }

    @RequestMapping(value = "/admin/posts/create-post", method = RequestMethod.POST)
    public String savePost(Post post) {
        post.setPosted(currentTimeService.getCurrentTime());
        postService.savePost(post);
        return "redirect:/admin/posts";
    }

    @RequestMapping(value = "/admin/posts/{postId}/delete", method = RequestMethod.POST)
    public String deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
        return "redirect:/admin/posts";
    }

    @RequestMapping(value = "/admin/posts/{postId}/update", method = RequestMethod.GET)
    public String updatePost(@PathVariable Long postId, Model model) {
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("post", postService.findPostById(postId));
        return "admin/edit-post";
    }

    @RequestMapping(value = "/admin/posts/update", method = RequestMethod.POST)
    public String updatePostEnd(Post post) {
        post.setPosted(currentTimeService.getCurrentTime());
        postService.savePost(post);
        return "redirect:/admin/posts/";
    }
}
