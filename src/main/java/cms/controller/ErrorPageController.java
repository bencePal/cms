package cms.controller;

import cms.service.CategoryService;
import cms.service.MenuService;
import cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ErrorPageController implements ErrorController {

    public static final String ERR_PATH = "/error";

    private CategoryService categoryService;
    private ErrorAttributes errorAttributes;
    private MenuService menuService;

    @Autowired
    public ErrorPageController(CategoryService categoryService, ErrorAttributes errorAttributes, MenuService menuService) {
        this.categoryService = categoryService;
        this.errorAttributes = errorAttributes;
        this.menuService = menuService;
    }

    @RequestMapping(ERR_PATH)
    public String error(Model model, HttpServletRequest request) {
        RequestAttributes requestA = new ServletRequestAttributes(request);
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(requestA, true);



        model.addAttribute("timestamp", error.get("timestamp"));
        model.addAttribute("error", error.get("error"));
        model.addAttribute("message", error.get("message"));
        model.addAttribute("path", error.get("path"));
        model.addAttribute("status", error.get("status"));
        model.addAttribute("menu", menuService.getAllMenuItems());
        model.addAttribute("allCategories", categoryService.getAllCategories());

        if(error.get("error").equals("Not Found")) {
            return "404";
        }

        return "error";
    }


    @Override
    public String getErrorPath() {
        return ERR_PATH;
    }
}
