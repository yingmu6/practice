package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author : chensy
 * Date : 2020-03-09 21:09
 */
@Controller
public class HelloController {

    @RequestMapping("/toIndex")
    public String toIndex(ModelMap model) { //
        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "index";
    }
}
