import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/*
 * Tests for the Recipe.java class
 */

class RecipeTest {

	// Test the equals method in Recipe.java for 2 equal recipes
	@Test
	void testEqualsAreEqual() {
		ArrayList<String> recipe1Tags = new ArrayList<String>() {
			{
				add("tag1");
				add("tag2");
				add("tag3");
			}	
		};
		ArrayList<String> recipe1Nutrition = new ArrayList<String>() {
			{
				add("1");
				add("1");
				add("1");
				add("1");
			}	
		};
		ArrayList<String> recipe1Steps = new ArrayList<String>() {
			{
				add("step1");
				add("step2");
				add("step3");
			}	
		};
		ArrayList<String> recipe1Ingredients = new ArrayList<String>() {
			{
				add("ing1");
				add("ing2");
				add("ing3");
			}	
		};
		Recipe recipe1 = new Recipe("recipe name", 1234, 20, 3219, "2016-04-12", recipe1Tags, recipe1Nutrition,
				10, recipe1Steps, "thisisadescription", recipe1Ingredients);

		ArrayList<String> recipe2Tags = new ArrayList<String>() {
			{
				add("tag1");
				add("tag2");
				add("tag3");
			}	
		};
		ArrayList<String> recipe2Nutrition = new ArrayList<String>() {
			{
				add("1");
				add("1");
				add("1");
				add("1");
			}	
		};
		ArrayList<String> recipe2Steps = new ArrayList<String>() {
			{
				add("step1");
				add("step2");
				add("step3");
			}	
		};
		ArrayList<String> recipe2Ingredients = new ArrayList<String>() {
			{
				add("ing1");
				add("ing2");
				add("ing3");
			}	
		};
		Recipe recipe2 = new Recipe("recipe name", 1234, 20, 3219, "2016-04-12", recipe2Tags, recipe2Nutrition,
				10, recipe2Steps, "thisisadescription", recipe2Ingredients);

		assertEquals(recipe1.equals(recipe2), true);
	}

	// Test the equals method in Recipe.java for 2 not equal recipes
	@Test
	void testEqualsNotEqual() {
		ArrayList<String> recipe1Tags = new ArrayList<String>() {
			{
				add("tag1");
				add("tag2");
				add("tag3");
			}	
		};
		ArrayList<String> recipe1Nutrition = new ArrayList<String>() {
			{
				add("1");
				add("1");
				add("1");
				add("1");
			}	
		};
		ArrayList<String> recipe1Steps = new ArrayList<String>() {
			{
				add("step1");
				add("step2");
				add("step3");
			}	
		};
		ArrayList<String> recipe1Ingredients = new ArrayList<String>() {
			{
				add("ing1");
				add("ing2");
				add("ing3");
			}	
		};
		Recipe recipe1 = new Recipe("recipe name", 1234, 20, 3219, "2016-04-12", recipe1Tags, recipe1Nutrition,
				10, recipe1Steps, "thisisadescription", recipe1Ingredients);

		ArrayList<String> recipe2Tags = new ArrayList<String>() {
			{
				add("tag1");
				add("tag2");
				add("tag3");
			}	
		};
		ArrayList<String> recipe2Nutrition = new ArrayList<String>() {
			{
				add("1");
				add("1");
				add("1");
				add("1");
			}	
		};
		ArrayList<String> recipe2Steps = new ArrayList<String>() {
			{
				add("step1");
				add("step2");
				add("step3");
			}	
		};
		ArrayList<String> recipe2Ingredients = new ArrayList<String>() {
			{
				add("ing1");
				add("ing2");
				add("ing3");
			}	
		};
		Recipe recipe2 = new Recipe("different recipe name", 1234, 20, 3219, "2016-04-12", recipe2Tags, recipe2Nutrition,
				10, recipe2Steps, "thisisadescription", recipe2Ingredients);

		assertEquals(recipe1.equals(recipe2), false);
	}
}
