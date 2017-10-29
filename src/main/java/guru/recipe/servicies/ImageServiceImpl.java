package guru.recipe.servicies;

import guru.recipe.domain.Recipe;
import guru.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author Nikolay Horushko
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile imageFile) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("recipe not found id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        try {
            byte[] bytes = imageFile.getBytes();
            Byte[] byteObject = new Byte[bytes.length];
            Arrays.setAll(byteObject, n -> bytes[n]);

            recipe.setImage(byteObject);
            recipeRepository.save(recipe);

        } catch (IOException e) {
            e.printStackTrace();
            log.debug(e.getMessage());
        }
    }
}
