import java.util.*;

import org.apache.commons.text.similarity.CosineDistance;

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
	public static ArrayList<Recipe> returnRecipe(ArrayList<Recipe> recipes, String inputIngredients, int numRecipes) {
		DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
		ArrayList<Double> cosSimilarity = new ArrayList<Double>();
		// get cosine similarity for each recipe
		int errorCounter = 0;
		for (Recipe r : recipes) {
			String currRecipeIngredients = dataPrep.makeContiniousString(r.getIngredients());
			try {
				cosSimilarity.add(1 - new CosineDistance().apply(currRecipeIngredients.toLowerCase(), inputIngredients));
			} catch(Exception e) {
				cosSimilarity.add(-2.0);
				errorCounter++;
			}
		}
		System.out.println(errorCounter);
		
		ArrayList<Recipe> topRecipes = new ArrayList<Recipe>();
		ArrayList<Double> topSimilarity = new ArrayList<Double>();
		double cutoffScore;
		
		for (int i = 0; i < recipes.size(); i++) {
			// automatically add the first recipe
			if (i==0) {
				topRecipes.add(recipes.get(i));
				topSimilarity.add(cosSimilarity.get(i));
			} else {
				// if we havn't gone through enough recipes yet set the
				// cutoff score to -1.0 so any recipe will make it into the list
				if (i < numRecipes) {
					cutoffScore = -1.0;
				}
				// if we have gone through enough recipe set the cutoff
				// score to the lowest topSimilarity
				else {
					cutoffScore = topSimilarity.get(numRecipes - 1);
				}
				// if score of this recipe is lower than cutoff score skip this recipe
				if (cosSimilarity.get(i) <= cutoffScore) {
					continue;
				} else {
					for (int j = 0; j < topRecipes.size(); j++) {
						// see where the current recipe falls in the top recipes by comparing it to each top
						// recipe starting with the one with the lowest score
						if (cosSimilarity.get(i) < topSimilarity.get(topRecipes.size() - j - 1)) {
							topRecipes.add(topRecipes.size() - j, recipes.get(i));
							topSimilarity.add(topSimilarity.size() - j, cosSimilarity.get(i));
							break;
						}
						// if it has not had a lower score up to this point, it has the newest highest
						// score so place it at index 0
						else if (j == topRecipes.size() - 1) {
							topRecipes.add(0, recipes.get(i));
							topSimilarity.add(0, cosSimilarity.get(i));
							break;
						}
					}
					// remove the excess recipe if necessary
					if (i >= numRecipes) {
						topRecipes.remove(numRecipes);
						topSimilarity.remove(numRecipes);
					}
				}
			}
		}
		
		return topRecipes;
	}
	
	
	// Using this main method for testing purposes DELETE EVENTUALLY
	/*
	 * public static void main(String[] args) { RecipeRecommender rr = new
	 * RecipeRecommender();
	 * 
	 * String[] example = new String[3]; example[0] = "prepared pizza crust";
	 * example[1] = "sausage patty"; example[2] = "eggs";
	 * 
	 * rr.returnRecipe(example); System.out.println(example); }
	 */
	
}
