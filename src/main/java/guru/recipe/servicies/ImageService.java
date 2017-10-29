package guru.recipe.servicies;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Nikolay Horushko
 */
public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile imageFile);
}
