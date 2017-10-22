package guru.recipe.servicies;

import guru.recipe.command.IngredientCommand;

/**
 * @author Nikolay Horushko
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
}
