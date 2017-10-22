package guru.recipe.controllers;

import guru.recipe.servicies.IngredientService;
import guru.recipe.servicies.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nikolay Horushko
 */
@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngridient(@PathVariable String id, Model model) {
        log.debug("Getting ingredient list for recipe id: {}", id);
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        log.debug("Getting ingredient id: {} from recipe id {} ", ingredientId, recipeId);
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
}
