package guru.recipe.servicies;

import guru.recipe.command.IngredientCommand;
import guru.recipe.command.RecipeCommand;
import guru.recipe.command.UnitOfMeasureCommand;
import guru.recipe.converters.IngredientCommandToIngredient;
import guru.recipe.converters.IngredientToIngredientCommand;
import guru.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.recipe.domain.Ingredient;
import guru.recipe.domain.Recipe;
import guru.recipe.domain.UnitOfMeasure;
import guru.recipe.repositories.RecipeRepository;
import guru.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Nikolay Horushko
 */
public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient,
                recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredient(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testCreateRecipeCommand() throws Exception {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(2L);
        command.setDescription("test ingredient");

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        Ingredient ingredient = savedRecipe.getIngredients().iterator().next();
        ingredient.setId(3L);
        ingredient.setDescription("test ingredient");

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredient(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteIngredient() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(2L);
        ingredient1.setDescription("test ingredient");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(3L);
        ingredient2.setDescription("ingredient for delete");
        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(new Recipe());

        ingredientService.deleteIngredientByRecipeIdAndIngredientId(1L, 3L);

        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, times(1)).save(recipeCaptor.capture());
        verifyNoMoreInteractions(recipeRepository);

        assertEquals(1, recipeCaptor.getValue().getIngredients().size());
        assertEquals("test ingredient", recipeCaptor.getValue().getIngredients().iterator().next().getDescription());
    }
}