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
	 * 
	 * @param r a recipe to find additional ingredients to add to
	 * @param recipeBook the list of recipes to use as data to find additional ingredients
	 * @return A string containing the additional ingredient to reccomend
	 */
	public static String recommendedAdditionalIngredient(Recipe r, ArrayList<Recipe> recipeBook) {
		//build the most common ingredients in the whole recipeBook.
		
		List<String> commonPairingsList = new ArrayList<>();
		
		commonPairingsList = commonPairing(r, recipeBook);

		if (commonPairingsList.toString().equals("[]")) {
			return "No items are recommended to be added.";
		}
		else {
			int iterator = 0;
			Object[] array = commonPairingsList.toArray();
			String ing = String.valueOf(array[iterator]);
			ing = ing.substring(0, ing.indexOf("|"));
			
			if (IngredientInputController.commonIngredients.contains(ing)) {
				while(IngredientInputController.commonIngredients.contains(ing)) {
					iterator++;
					ing = String.valueOf(array[iterator]);
					ing = ing.substring(0, ing.indexOf("|"));
				}	
			}
			ing = "| Potential additional ingredient: " + ing;
			return ing;
		}
	}
	
	/**
	 * Creates the list of mostCommonFoods in all recipes.
	 * @param recipeBook ArrayList of recipes to find the most common ingredients
	 * @return ArrayList containing the most common ingredients across all recipes
	 */
	
	public static ArrayList<String> mostCommonFoods(ArrayList<Recipe> recipeBook) {
		
		HashMap<String, Integer> commonFoods = new HashMap<>();
		ArrayList<String> allIngredientsInRecipeBook = new ArrayList<>();
		
		for (Recipe r : recipeBook) {
			allIngredientsInRecipeBook.addAll(r.getIngredients());
		}
		
		for (String ing : allIngredientsInRecipeBook) {
			if (ing == null) { }
			else if (!commonFoods.containsKey(ing)) {
				commonFoods.put(ing, 1);
			}
			else {
				commonFoods.put(ing, commonFoods.get(ing)+1);
			}
		}
		
		//sort the list 
		
		Stream<Map.Entry<String,Integer>> sorted = commonFoods.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
		Stream<String> flatMap = sorted.flatMap(x -> Stream.of(x.getKey()+ "|" + x.getValue()));

		//Convert Stream to flattened List for re-export
		List sortedList = flatMap.collect(Collectors.toList());
		
		Object[] sortedArray = sortedList.toArray();
		
		ArrayList<String> sortedArrayList = new ArrayList<String>();
		
		for (int i = 0; i < 25; i++) {
			String adj = (String) sortedArray[i];
			adj = adj.substring(0, adj.indexOf("|"));
			sortedArrayList.add(adj);
		}
		return sortedArrayList;
		
	}
		
	
	/**
	 * Finds commonly paired ingredients between recipes
	 * @param recipe The Recipe you want to find commonly paired ingredients to
	 * @param recipeBook The list of recipes you want to use to find common pairings
	 * @return a List of ingredients that are commonly paired with ingredients in Recipe
	 */
	public static List<String> commonPairing(Recipe recipe, ArrayList<Recipe> recipeBook) {
		ArrayList<String> allIngredientsInRecipes = new ArrayList<>();
		HashMap<String, Integer> pairings = new HashMap<>();

		//parse out recipes that include the ingredient. Add ALL ingredients
		//in those recipes to a long list.
		for (String recipeIngredient : recipe.getIngredients()) {
			for (Recipe r : recipeBook) {
				if (r.getIngredients().contains(recipeIngredient)) {
					allIngredientsInRecipes.addAll(r.getIngredients());
				}
			}
		}
			//for each ingredient in the long list, add to a hashmap that shows
			//frequency of appearance. Disregard the original input ingredient.

		for (String ing : allIngredientsInRecipes) {
			if (ing == null) { }
			else if (recipe.getIngredients().contains(ing)) { }
			else if (!pairings.containsKey(ing)) {
				pairings.put(ing, 1);
			}
			else {
				pairings.put(ing, pairings.get(ing)+1);
			}
		}

		//convert HashMap to Stream for sorting purposes
		Stream<Map.Entry<String,Integer>> sorted = pairings.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
		Stream<String> flatMap = sorted.flatMap(x -> Stream.of(x.getKey()+ "|" + x.getValue()));

		//Convert Stream to flattened List for re-export
		List sortedList = flatMap.collect(Collectors.toList());
		return sortedList;
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
	
	/**
	 * Finds all recipes of a certain cuisine
	 * @param recipes ArrayList of recipes to search through
	 * @param cuisine String representing the cuisine of interest
	 * @return ArrayList of Recipes that are of the cuisine of interest
	 */
	public static ArrayList<Recipe> returnCuisine(ArrayList<Recipe> recipes, String cuisine) {
		ArrayList<Recipe> cuisineRecipes = new ArrayList<Recipe>();
		ArrayList<Recipe> tooManyRecipes = new ArrayList<Recipe>();
		for (Recipe r : recipes) {
			if (r.getTags().contains(cuisine)) {
				cuisineRecipes.add(r);
			}
		}
		if (cuisineRecipes.size() > 250) {
			return tooManyRecipes;
		}
		
		return cuisineRecipes;
	}
	

}
