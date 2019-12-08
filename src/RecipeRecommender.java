import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.text.similarity.CosineDistance;

/**
 * class that recommends recipes based on ingredients
 */

public class RecipeRecommender {

	/**
	 * based on user input ingredients, it returns recipes with the most similar ingredients.  Similarity is calculated using cosine similarity.
	 * Additionally, non noun words are removed from each ingredient.
	 * @param recipes arraylist of recipes to choose from
	 * @param inputIngredients a string containing space separated ingredients to use to find a similar recipe
	 * @param numRecipes integer representing how many recipes should be returned
	 * @param minSimilarity the similarity score threshold a recipe must exceed to be included
	 * @return Arraylist of numRecipes recipes that are most similar to inputIngredients
	 */
	public static ArrayList<Recipe> returnRecipe(ArrayList<Recipe> recipes, String inputIngredients, int numRecipes, double minSimilarity) {
		DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
		ArrayList<Double> cosSimilarity = new ArrayList<Double>();
		// get cosine similarity for each recipe
		for (Recipe r : recipes) {
			String currRecipeIngredients = dataPrep.makeContinuousString(r.getIngredients());
			try {
				cosSimilarity.add(1 - new CosineDistance().apply(currRecipeIngredients.toLowerCase(), inputIngredients));
			} catch(Exception e) {
				cosSimilarity.add(-2.0);
			}
		}

		ArrayList<Recipe> topRecipes = new ArrayList<Recipe>();
		ArrayList<Double> topSimilarity = new ArrayList<Double>();
		double cutoffScore;

		for (int i = 0; i < recipes.size(); i++) {
			// if a recipe doesn't exceed threshold skip it
			if (cosSimilarity.get(i) < minSimilarity) {
				continue;
			}
			// automatically add a recipe if we haven't added any yet
			else if (topRecipes.isEmpty()) {
				topRecipes.add(recipes.get(i));
				topSimilarity.add(cosSimilarity.get(i));
			} else {
				// if we havn't gone through enough recipes yet set the
				// cutoff score to -1.0 so any recipe will make it into the list
				if (topRecipes.size() < numRecipes) {
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
					if (topRecipes.size() >= numRecipes) {
						topRecipes.remove(numRecipes-1);
						topSimilarity.remove(numRecipes-1);
					}
				}
			}
		}

		return topRecipes;
	}
	
	/**
	 * Returns a random recipe
	 * @parm recipes arrayList of recipes to just a random one from
	 * @return a random recipe
	 */
	public static ArrayList<Recipe> randomRecipe(ArrayList<Recipe> recipes) {
		Random random = new Random();
		
		ArrayList<Recipe> returnList = new ArrayList<Recipe>();
		returnList.add(recipes.get(random.nextInt(recipes.size())));
		return returnList;
	}

}
