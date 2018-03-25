package cms.controller;

import cms.service.CategoryService;
import cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    private PageService pageService;
    private CategoryService categoryService;

    @Autowired
    public PageController(PageService pageService, CategoryService categoryService) {
        this.pageService = pageService;
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/page/{pageId}", method = RequestMethod.GET)
    public String pageDetails(@PathVariable Long pageId, Model model)  {
        model.addAttribute("menu", pageService.getAllPage());
        model.addAttribute("page", pageService.findPageById(pageId));
        model.addAttribute("allCategories", categoryService.getAllCategories());
        return "page";
    }
}
