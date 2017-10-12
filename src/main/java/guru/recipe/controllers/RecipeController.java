package guru.recipe.controllers;

import guru.recipe.domain.Recipe;
import guru.recipe.servicies.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nikolay Horushko
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String getRecipeById(@PathVariable Long id, Model model){

        Recipe recipe = recipeService.getRecipeById(id);

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }
}
