package guru.recipe.servicies;

import guru.recipe.command.IngredientCommand;
import guru.recipe.converters.IngredientToIngredientCommand;
import guru.recipe.domain.Ingredient;
import guru.recipe.domain.Recipe;
import guru.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            //todo implement error handling
            log.error("recipe id not found. Id {}", recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            //todo implement error handling
            log.error("Ingredient id not found. Id: {}", ingredientId);
        }

        return ingredientCommandOptional.get();
    }
}
