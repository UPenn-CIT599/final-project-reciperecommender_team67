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
	 * to be returned
	 * @param ingredients
	 * @return
	 */
	public ArrayList<Recipe> returnRecipe(ArrayList<String> ingredients) {
		ArrayList<Recipe> potentialRecipes = new ArrayList<>();
		RecipeReader rr = new RecipeReader("RAW_recipes_cleaned.csv");
		DataPreparation prep = new DataPreparation(rr.getAllRecipes());
		ArrayList<Recipe> recipeBook = prep.getAllRecipes();
		System.out.println(recipeBook.get(1).getIngredients());

		for (Recipe r : recipeBook) {
			ArrayList<String> ing = r.getIngredients();
			int counter = 0;
			for (int i = 0; i < ing.size(); i++){
				for (int j = 0; j < ingredients.size(); j++) {
					if (ingredients.get(j).equals(ing.get(i))) {
						counter++;
					}
				}
			}
			if (counter > 3) { //hardcode
				potentialRecipes.add(r);
			}
		}
	System.out.println(potentialRecipes.size());
	return potentialRecipes;
	}
	
	public static void main(String[] args) {
		RecipeRecommender rr = new RecipeRecommender();

		ArrayList<String> example = new ArrayList();
		example.add("prepared pizza crust");
		example.add("sausage patty");
		example.add("eggs");

		rr.returnRecipe(example);
		System.out.println(example);
	}
	
}
