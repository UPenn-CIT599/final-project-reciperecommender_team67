import java.util.ArrayList;

/**
 * Create a DataPreparation class that contains an ArrayList of Recipe objects as an instance variable
 * and create methods to pre-process the recipe entries for analysis in DataAnalysis.
 */
public class DataPreparation {
	
	ArrayList<Recipe> recipes;
		
	public DataPreparation(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	/**
	 * Returns an ArrayList of recipes that occurred 
	 * @return
	 */
	public ArrayList<Recipe> getAllRecipes() {
		ArrayList<Recipe> allRecipes = new ArrayList<>();
		
		for (Recipe r : recipes) {
			allRecipes.add(r);
		}
		return allRecipes;		
	}
	
}

