package guru.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nikolay Horushko
 */
@Controller
public class IndexController {

    @RequestMapping({"","/","index"})
    public String getIndexPage(){
        return "index";
    }
}
