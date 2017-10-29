package guru.recipe.servicies;

import guru.recipe.domain.Recipe;
import guru.recipe.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Nikolay Horushko
 */
public class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFile() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        ArgumentCaptor<Recipe> recipeCaptor = ArgumentCaptor.forClass(Recipe.class);
        MultipartFile multipartFile = new MockMultipartFile("imageFile", "testeing.txt", "text/plain", "image".getBytes());

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        //when
        imageService.saveImageFile(1L, multipartFile);

        verify(recipeRepository).findById(1L);
        verify(recipeRepository).save(recipeCaptor.capture());
        verifyNoMoreInteractions(recipeRepository);
        Recipe savedRecipe = recipeCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length);
    }

}