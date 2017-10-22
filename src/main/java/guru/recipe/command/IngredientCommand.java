package guru.recipe.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Nikolay Horushko
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand unitOfMeasure;
}