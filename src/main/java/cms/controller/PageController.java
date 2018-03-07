package cms.controller;

import cms.model.Page;
import cms.model.Post;
import cms.service.PageService;
import cms.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    private PageService pageService;

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    @RequestMapping(path = "/page/{pageId}", method = RequestMethod.GET)
    public String pageDetails(@PathVariable Long pageId, Model model)  {
        Page page = pageService.findPageById(pageId);
        model.addAttribute("page", page);
        return "page";
    }


}
