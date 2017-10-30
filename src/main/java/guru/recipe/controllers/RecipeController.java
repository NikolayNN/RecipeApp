package guru.recipe.controllers;

import guru.recipe.command.RecipeCommand;
import guru.recipe.domain.Recipe;
import guru.recipe.servicies.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nikolay Horushko
 */
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show/")
    public String getRecipeById(@PathVariable Long id, Model model){

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id);

        model.addAttribute("recipe", recipeCommand);

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/recipeform";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);

        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show/";
    }
}
