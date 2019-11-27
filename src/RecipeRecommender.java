import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Build all the necessary objects (RecipeReader, DataPreparation, DataAnalysis ***TBD***) in a Main class that reads in the
 *  file and calls the methods to create ArrayLists of all the recipes.
 *  Data preparation for data analysis is processed from DataPreparation instances to DataAnalysis ***TBD*** instances.
 */

public class RecipeRecommender {
	private static RecipeReader rr = new RecipeReader("RAW_recipes_short.csv");
	private static ArrayList<Recipe> recipeBook = rr.getAllRecipes();

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
		System.out.println("The number of matched recipes is "+ potentialRecipes.size()); // delete eventually
		return potentialRecipes;
	}

	/**
	 * Takes in a recipe and outputs a 'recommended' ingredient that could be added into the recipe.
	 * This ingredient is not already included in the list of ingredients. On a functional level,
	 * this method calls commonPairing. It takes the output of commonPairing, which is a List<String>
	 * outputs the first ingredient recommended. Will continue to add to this method as there are likely
	 * very common ingredients that need to be put on a 'do not recommend list'. (IE: sugar, salt).
	 * @param r
	 * @return
	 */
	public static String recommendedAdditionalIngredient(Recipe r) {
		List<String> commonPairingsList = new ArrayList<>();
		commonPairingsList = commonPairing(r);

		if (commonPairingsList.toString().equals("[]")) {
			return "No items are recommended to be added.";
		}
		else {
			Object[] array = commonPairingsList.toArray();
			String str = String.valueOf(array[0]);
			str = str.substring(0, str.indexOf("|"));
			return str;
		}
	}

	/**
	 * This method takes in a recipe, and outputs a List that dictates ingredients by frequency
	 * that appear other recipes that have the same ingredients as the input recipe.
	 * @param recipe
	 * @return
	 */
	public static List<String> commonPairing(Recipe recipe) {
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

	public static ArrayList<Recipe> randomRecipe() {
		ArrayList<Recipe> randomRecipe = new ArrayList<>();
		Random random = new Random();
		int randomNum = random.nextInt(recipeBook.size());
		randomRecipe.add(recipeBook.get(randomNum));
		return randomRecipe;
	}

	public static ArrayList<Recipe> getRecipeBook() {
		return recipeBook;
	}

    public static void main(String[] args) {
        RecipeRecommender rr = new RecipeRecommender();
        int counter = 0;

		for (int i = 0; i < rr.getRecipeBook().size(); i++) {
			recommendedAdditionalIngredient(rr.getRecipeBook().get(i));
			counter++;
		}



    }
}

