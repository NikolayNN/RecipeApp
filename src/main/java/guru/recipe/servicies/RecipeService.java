package guru.recipe.servicies;

import guru.recipe.command.RecipeCommand;
import guru.recipe.domain.Recipe;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public interface RecipeService {
    Set<Recipe> getRecipes ();

    Recipe findRecipeById(long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findRecipeCommandById(Long id);

    void deleteRecipeById(Long id);
}
