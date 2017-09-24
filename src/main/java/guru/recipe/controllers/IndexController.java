package guru.recipe.controllers;

import guru.recipe.domain.Category;
import guru.recipe.domain.UnitOfMeasure;
import guru.recipe.repositories.CategoryRepository;
import guru.recipe.repositories.RecipeRepository;
import guru.recipe.repositories.UnitOfMeasureRepository;
import guru.recipe.servicies.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Nikolay Horushko
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","index"})
    public String getIndexPage(Model model){
        model.addAttribute("recipies", recipeService.getRecipes());
        return "index";
    }
}
