package guru.recipe.controllers;

import guru.recipe.command.RecipeCommand;
import guru.recipe.domain.Recipe;
import guru.recipe.exceptions.NotFoundException;
import guru.recipe.servicies.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Nikolay Horushko
 */
@Controller
@Slf4j
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable Long id, Model model){

        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id);

        model.addAttribute("recipe", recipeCommand);

        return "recipe/show";
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));

        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteRecipeById(id);

        return "redirect:/";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute RecipeCommand command, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));
            return RECIPE_RECIPEFORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundErrorPage(Exception exception){
        log.error("Handling not Found exception");
        ModelAndView model = new ModelAndView();
        model.addObject("exception", exception);
        model.setViewName("404error");
        return model;
    }
}
