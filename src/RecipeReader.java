import java.io.*;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

/**
 *  Create a new class called RecipeReader that can read the file with the 
 *  data and parse information from the csv file to create a list of Recipe objects.
 */

public class RecipeReader {
	ArrayList<Recipe> recipes = new ArrayList<>();
	ArrayList<String> columns = new ArrayList<>();
	DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");

	public RecipeReader(String filename) {

		try {
			Scanner in = new Scanner(new FileReader(filename));		
			while (in.hasNextLine()) {
				
				// Read column headers
				if (columns.size() == 0) {
					String nextLine = in.nextLine();
					for (String str : nextLine.split(",")) {
						columns.add(str);
					}
				} else { // Read recipe data
					String currLine = in.nextLine();
					try {
						Recipe newRecipe = parseRecipe(currLine);
						recipes.add(newRecipe);
					} catch(Exception e) {
						continue;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Parses a line in the csv file into a Recipe object
	 * @param currLine the row in the csv file you are parsing
	 * @return a recipe object filled with information from currLine
	 */
	public Recipe parseRecipe(String currLine) { 
		
		// get the name of the recipe
		String[] temp = currLine.split(",",2);
		String name = temp[0];
		currLine = temp[1];
		
		// get the ID of the recipe
		temp = currLine.split(",",2);
		int ID = Integer.parseInt(temp[0]);
		currLine = temp[1];
		
		// get the amount of time the recipe takes to make 
		temp = currLine.split(",",2);
		int minutes = Integer.parseInt(temp[0]);
		currLine = temp[1];
		
		// get the id of the creator of the recipe
		temp = currLine.split(",",2);
		int contributorID = Integer.parseInt(temp[0]);
		currLine = temp[1];
		
		// get what date the recipe was submitted
		temp = currLine.split(",",2);
		String dateSubmitted = temp[0];
		currLine = temp[1];
		
		// get the tags for the recipe
		temp = currLine.split("]", 2);
		String currList = temp[0];
		currList = StringUtils.substringAfter(currList, "[");
		String[] tagsArray = currList.split(",");
		ArrayList<String> tags = new ArrayList<String>();
		for (String s : tagsArray) {
			tags.add(StringUtils.substringBetween(s, "'", "'"));
		}
		currLine = temp[1];
		
		// get the nutrition data for the recipe
		temp = currLine.split("]", 2);
		currList = temp[0];
		currList = StringUtils.substringAfter(currList, "[");
		String[] nutritionArray = currList.split(",");
		ArrayList<String> nutrition = new ArrayList<String>();
		for (String s : nutritionArray) {
			nutrition.add(s.replaceAll("\\s", ""));
		}
		currLine = temp[1];
		
		// get the number of steps for the recipe
		temp = currLine.split(",", 3);
		int numSteps = Integer.parseInt(temp[1]);
		currLine = temp[2];
		
		// get the steps for the recipe
		temp = currLine.split("]", 2);
		currList = temp[0];
		currList = StringUtils.substringAfter(currList, "[");
		String[] stepsArray = currList.split(",");
		ArrayList<String> steps = new ArrayList<String>();
		for (String s : stepsArray) {
			steps.add(StringUtils.substringBetween(s, "'", "'"));
		}
		currLine = temp[1];
		
		// get the description for the recipe
		temp = currLine.split("\\[", 2);
		String description = temp[0];
		description = description.replaceAll("[^\\p{IsDigit}\\p{IsAlphabetic}]", "");
		currLine = temp[1];
		
		// get the ingredients for the recipe
		temp = currLine.split("]", 2);
		currList = temp[0];
		String[] ingredientsArray = currList.split(",");
		ArrayList<String> ingredients = new ArrayList<String>();
		for (String s : ingredientsArray) {
			String cleanIngredient = StringUtils.substringBetween(s, "'", "'");
			if (StringUtils.isEmpty(cleanIngredient)) {
				continue;
			}
			String onlyNounIngredient = dataPrep.removeNonNouns(cleanIngredient);
			ingredients.add(dataPrep.removeSpaces(onlyNounIngredient));
		}
		currLine = temp[1];
		
		return new Recipe(name, ID, minutes, contributorID, dateSubmitted, tags, nutrition, numSteps, steps, description, ingredients);
	}	
	
	/**
	 * gets all of the recipes read in by this recipe reader
	 * @return ArrayList of Recipes. 
	 */
	public ArrayList<Recipe> getAllRecipes() {
		return recipes;
	}
	
	// Method for testing purposes 
	public static void main(String[] args) {

		RecipeReader rr = new RecipeReader("data/RAW_recipes_cleaned.csv");


	}

}
