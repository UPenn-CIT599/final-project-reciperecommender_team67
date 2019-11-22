import java.util.*;

import org.apache.commons.text.similarity.CosineDistance;

/**
 * class that reccommends recipes based on ingredients
 */

public class RecipeRecommender {
	
	/**
	 * based on user inputted ingredients, it returns recipes with the most similar ingredients.  Similarity is calculated using cosine similarity.
	 * Additionally, non noun words are removed from each ingredient.
	 * @param recipes arraylist of recipes to choose from
	 * @param inputIngredients a string containing space separated ingredients to use to find a similar recipe
	 * @param numRecipes integer representing how many recipes should be returned
	 * @return Arraylist of numRecipes recipes that are most similar to inputIngredients
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
		
		//DELETE
		for (Double d : topSimilarity) {
			System.out.println(d);
		}
		
		return topRecipes;
	}
	
	
//	 Using this main method for testing purposes DELETE EVENTUALLY
	  public static void main(String[] args) { 
//		  RecipeRecommender rr = new
//		  RecipeRecommender();
//		  
//		  String[] example = new String[3]; example[0] = "prepared pizza crust";
//		  example[1] = "sausage patty"; example[2] = "eggs";
//		  
//		  rr.returnRecipe(example); System.out.println(example); 
		  String string1 = "chicken potato rice zucchini carrot";
		  String string2 = "zucchini";
		  
		  System.out.println((1 - new CosineDistance().apply(string2, string1)));
	  }
	 
	
}
