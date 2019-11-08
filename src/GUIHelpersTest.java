import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GUIHelpersTest {
	
	
	//testing createRecipeURL for string with no back to back spaces
	@Test
	void testCreateRecipeURL() {
		String recipeName = "boiled chicken";
		String recipeId = "5689";
		assertEquals(GUIHelpers.createRecipeURL(recipeName, recipeId), "https://www.food.com/recipe/boiled-chicken-5689");
	}
	
	//testing createRecipeURL for string with back to back spaces
	@Test
	void testCreateRecipeURLSpaces() {
		String recipeName = "boiled   chicken soup";
		String recipeId = "5689";
		assertEquals(GUIHelpers.createRecipeURL(recipeName, recipeId), "https://www.food.com/recipe/boiled-chicken-soup-5689");
	}
}
