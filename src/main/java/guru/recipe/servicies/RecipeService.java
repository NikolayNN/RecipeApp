package guru.recipe.servicies;

import guru.recipe.domain.Recipe;

import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public interface RecipeService {
    Set<Recipe> getRecipes ();
}
