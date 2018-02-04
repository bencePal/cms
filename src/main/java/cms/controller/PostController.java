package cms.controller;

import cms.DAO.QueryHandler;
import cms.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    QueryHandler queryHandler;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminHome() {
        return "admin/home";
    }


    @RequestMapping(value = "/admin/create-post", method = RequestMethod.GET)
    public String createPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "admin/create-post";
    }

    @RequestMapping(value = "/admin/create-post", method = RequestMethod.POST)
    public String savePost(Post post) {
        queryHandler.savePost(post);
        return "admin/create-post";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        List<Post> allPost = queryHandler.getAllPost();
        model.addAttribute("posts", allPost);
        return "home";
    }
}
