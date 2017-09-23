package guru.recipe.repositories;

import guru.recipe.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nikolay Horushko
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
