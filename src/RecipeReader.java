import java.io.*;
import java.util.*;

/**
 *  Create a new class called RecipeReader that can read the file with the 
 *  data and parse information from the csv file to create a list of Recipe objects.
 */

public class RecipeReader {
	ArrayList<Recipe> recipes = new ArrayList<>();
	ArrayList<String> columns = new ArrayList<>();
	
	public RecipeReader(String filename) {
		
		try {
			Scanner in = new Scanner(new FileReader(filename));			
			
			while (in.hasNextLine()) {

				// Read column headers
				if (columns.size() == 0) {
					String nextLine = in.nextLine();
					for (String str : nextLine.split(",")) {
						columns.add(str);
						System.out.println(str);
					}
				} else { // Read recipe data
					String[] newRowArr = in.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

					System.out.println(Arrays.toString(newRowArr));
					
					

//					recipes.add(parseRecipe(in.nextLine()));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses a row into a Recipe object
	 * @param row
	 * @return a recipe
	 */
	public Recipe parseRecipe(String row) { 
				
//		String[] newRowArr = row.split(",");
		
		String[] newRowArr = row.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

		System.out.println(newRowArr);
		
		String name = newRowArr[0];
		int ID = Integer.parseInt(newRowArr[1]);
		int minutes = Integer.parseInt(newRowArr[2]);
		int contributorID = Integer.parseInt(newRowArr[3]);
		String dateSubmitted = newRowArr[4];
		ArrayList<String> tags = new ArrayList<String>(Arrays.asList(newRowArr[5].split(" , ")));
		ArrayList<String> nutrition = new ArrayList<String>(Arrays.asList(newRowArr[6].split(" , ")));
		int numSteps = Integer.parseInt(newRowArr[7]);
		ArrayList<String> steps = new ArrayList<String>(Arrays.asList(newRowArr[8].split(" , ")));
		String description = newRowArr[9];
		ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList(newRowArr[10].split(" , ")));
		int numIngredients = Integer.parseInt(newRowArr[11]);
						
		Recipe recipe = new Recipe(name, ID, minutes, contributorID, dateSubmitted, tags, nutrition, numSteps, steps, description, ingredients, numIngredients);
				
		return recipe;
	}	
	
	public ArrayList<Recipe> getAllRecipes() {
		return recipes;
	}
	
	public static void main(String[] args) {
		
		RecipeReader rr = new RecipeReader("RAW_recipes.csv");
		
		
	}
	
}
