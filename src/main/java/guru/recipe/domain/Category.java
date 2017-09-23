package guru.recipe.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Nikolay Horushko
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dexcription;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDexcription() {
        return dexcription;
    }

    public void setDexcription(String dexcription) {
        this.dexcription = dexcription;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
