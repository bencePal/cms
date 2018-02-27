package cms.controller;

import cms.model.Post;
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

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/post/{postId}", method = RequestMethod.GET)
    public String postPage(@PathVariable Long postId, Model model)  {
        Post post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "post";
    }


}
