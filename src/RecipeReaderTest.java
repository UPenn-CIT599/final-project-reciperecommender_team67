import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the RecipeReader functions
 *
 */
class RecipeReaderTest {
	
	RecipeReader rr = new RecipeReader("test.csv");
	
	// Tests the parseRecipe function
	@Test
	void testParseRecipe() {
		String testLine = "recipe name, 1234, 20, 3219, 2016-04-12, \"['tag1', 'tag2', 'tag3']\",\"[1, 1, 1, 1]\",10,\"['step1', 's"
				+ "tep2', 'step3']\",\"this is a description\",\"['ing1', 'ing2', 'ing3']\",1";
		Recipe newRecipe = rr.parseRecipe(testLine);
		ArrayList<String> compRecipeTags = new ArrayList<String>() {
			{
				add("tag1");
				add("tag2");
				add("tag3");
			}	
		};
		ArrayList<String> compRecipeNutrition = new ArrayList<String>() {
			{
				add("1");
				add("1");
				add("1");
				add("1");
			}	
		};
		ArrayList<String> compRecipeSteps = new ArrayList<String>() {
			{
				add("step1");
				add("step2");
				add("step3");
			}	
		};
		ArrayList<String> compRecipeIngredients = new ArrayList<String>() {
			{
				add("ing1");
				add("ing2");
				add("ing3");
			}	
		};
		Recipe compRecipe = new Recipe("recipe name", 1234, 20, 3219, "2016-04-12", compRecipeTags, compRecipeNutrition,
				10, compRecipeSteps, "thisisadescription", compRecipeIngredients);
		
		assertEquals(newRecipe, compRecipe);
	}
 
}
