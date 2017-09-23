package guru.recipe.repositories;

import guru.recipe.domain.Category;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nikolay Horushko
 */
public interface UnitOfMeasureRepository extends CrudRepository<Category, Long> {
}
