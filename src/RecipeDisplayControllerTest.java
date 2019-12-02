import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;


/**
 * JUnit tests for RecipeDisplayController class
 *
 */
class RecipeDisplayControllerTest {
	
	// tests the populateRecipes method for situations where the list of output recipes was previously empty
	@Test
	void testPopulateRecipesPreviousEmpty() {
		StateModel model = new StateModel();
		RecipeDisplayView outputView = new RecipeDisplayView();
		RecipeDisplayController outputController = new RecipeDisplayController(model, outputView);
		
		ArrayList<Recipe> outputRecipes = new ArrayList<Recipe>();
		
		Recipe newRecipe = new Recipe("chicken rice", 0, 0, 0, null, null, null, 0, null, null, null);
		outputRecipes.add(newRecipe);
		
		newRecipe = new Recipe("roasted chicken", 0, 0, 0, null, null, null, 0, null, null, null);
		outputRecipes.add(newRecipe);
		
		model.setOutputRecipes(outputRecipes);
		
		outputController.populateRecipes();
		
		assertEquals(outputView.getRecipesModel().elementAt(1),  "roasted chicken");
	}
	
	// tests the populateRecipes method for situations where the list of output recipes was not previously empty
	@Test
	void testPopulateRecipesNotPreviousEmpty() {
		StateModel model = new StateModel();
		RecipeDisplayView outputView = new RecipeDisplayView();
		RecipeDisplayController outputController = new RecipeDisplayController(model, outputView);
		
		outputView.getRecipesModel().addElement("This should not be here at completion");
		
		ArrayList<Recipe> outputRecipes = new ArrayList<Recipe>();

		Recipe newRecipe = new Recipe("chicken rice", 0, 0, 0, null, null, null, 0, null, null, null);
		outputRecipes.add(newRecipe);

		newRecipe = new Recipe("roasted chicken", 0, 0, 0, null, null, null, 0, null, null, null);
		outputRecipes.add(newRecipe);

		model.setOutputRecipes(outputRecipes);

		outputController.populateRecipes();
		
		boolean isPresent = false;
		for (int i=0; i<outputView.getRecipesModel().size(); i++) {
			String currEle = outputView.getRecipesModel().elementAt(i);
			if (currEle.contentEquals("This should not be here at completion")) {
				isPresent = true;
				break;
			}
		}

		assertEquals(false,  isPresent);
	}

}
