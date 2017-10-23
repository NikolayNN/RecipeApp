package guru.recipe.servicies;

import guru.recipe.command.UnitOfMeasureCommand;
import guru.recipe.domain.UnitOfMeasure;

import java.util.Set;

/**
 * @author Nikolay Horushko
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUnitOfMeasure();
}
