import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 *  Build all the necessary objects (RecipeReader, DataPreparation, DataAnalysis ***TBD***) in a Main class that reads in the
 *  file and calls the methods to create ArrayLists of all the recipes.
 *  Data preparation for data analysis is processed from DataPreparation instances to DataAnalysis ***TBD*** instances.
 */

public class RecipeRecommender {

	/**
	 * Takes in the ingredients listed by the user in the GUI and compares them to the ingredients in the recipe book.
	 * If the number of recipes is greater than 3 (this is just a hardcode for now), then include it in the list of recipes
	 * to be returned (this is just an initial implementation of finding a recipe based on directly matching ingredients, we
	 * will be implementing more advanced methods in future).
	 * @param ingredients array containing ingredients entered by the user
	 * @return ArrayList of potential applicable recipes
	 */
	public static ArrayList<Recipe> returnRecipe(String[] ingredients) {
		ArrayList<Recipe> potentialRecipes = new ArrayList<>();
		RecipeReader rr = new RecipeReader("RAW_recipes_short.csv");
		ArrayList<Recipe> recipeBook = rr.getAllRecipes();

		for (Recipe r : recipeBook) {
			ArrayList<String> ing = r.getIngredients();
			double ingLimit = (double) ingredients.length / (double) 2; //set at 50% of ingredients listed
			int counter = 0;
			for (int i = 0; i < ing.size(); i++){
				for (int j = 0; j < ingredients.length; j++) {
					if (ingredients[j].trim().equals(ing.get(i))) {
						counter++;
					}
				}
			}
			if (counter >= ingLimit) {
				potentialRecipes.add(r);
			}
		}
		
		// printing for testing purposes DELETE EVENTUALLY
		for (Recipe r : potentialRecipes) {
			System.out.println(r.getName());
		}
		System.out.println(potentialRecipes.size());
		return potentialRecipes;
	}

	//public static String additionalIngredient(Recipe) ;


    public static void main(String[] args) {


        String[] testrecipe = {"sausage patty", "milk","ginger", "honey"};
        RecipeRecommender rr = new RecipeRecommender();
        rr.returnRecipe(testrecipe);

    }
}

