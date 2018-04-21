package cms.controller.admin;

import cms.model.Page;
import cms.service.CategoryService;
import cms.service.CurrentTimeService;
import cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminPageController {

    private PageService pageService;
    private CurrentTimeService currentTimeService;
    private CategoryService categoryService;

    @Autowired
    public AdminPageController(PageService pageService, CurrentTimeService currentTimeService, CategoryService categoryService) {
        this.pageService = pageService;
        this.currentTimeService = currentTimeService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/admin/pages", method = RequestMethod.GET)
    public String pageList(Model model) {
        List<Page> pages = pageService.getAllPage();
        model.addAttribute("activePageNav", true);
        model.addAttribute("pages", pages);
        return "admin/page-list";
    }

    @RequestMapping(value = "/admin/pages/create-page", method = RequestMethod.GET)
    public String createPage(Model model) {
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("activePageNav", true);
        model.addAttribute("page", new Page());
        return "admin/create-page";
    }

    @RequestMapping(value = "/admin/pages/create-page", method = RequestMethod.POST)
    public String savePage(Page page) {
        page.setPosted(currentTimeService.getCurrentTime());
        pageService.savePage(page);
        return "redirect:/admin/pages";
    }

    @RequestMapping(value = "/admin/pages/{pageId}/delete", method = RequestMethod.POST)
    public String deletePage(@PathVariable Long pageId) {
        pageService.deletePageById(pageId);
        return "redirect:/admin/pages";
    }

    @RequestMapping(value = "/admin/pages/{pageId}/update", method = RequestMethod.GET)
    public String updatePage(@PathVariable Long pageId, Model model) {
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("page", pageService.findPageById(pageId));
        return "admin/edit-page";
    }

    @RequestMapping(value = "/admin/pages/update", method = RequestMethod.POST)
    public String updatePageEnd(Page page) {
        page.setPosted(currentTimeService.getCurrentTime());
        pageService.savePage(page);
        return "redirect:/admin/pages/";
    }

}
