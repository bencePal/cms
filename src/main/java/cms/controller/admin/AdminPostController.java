package cms.controller.admin;

import cms.model.Post;
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

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setCurrentTimeService(CurrentTimeService currentTimeService) {
        this.currentTimeService = currentTimeService;
    }


    @RequestMapping(value = "/admin/posts", method = RequestMethod.GET)
    public String postPage(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "admin/post-list";
    }

    @RequestMapping(value = "/admin/posts/create-post", method = RequestMethod.GET)
    public String createPostPage(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
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
    public String updatePostPage(@PathVariable Long postId, Model model) {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "admin/edit-post";
    }

    @RequestMapping(value = "/admin/posts/update", method = RequestMethod.POST)
    public String updatePost(Post post) {
        post.setPosted(currentTimeService.getCurrentTime());
        postService.savePost(post);
        return "redirect:/admin/posts/";
    }
}
