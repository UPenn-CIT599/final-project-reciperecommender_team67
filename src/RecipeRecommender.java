import java.io.*;
import java.util.*;

/**
 *  Build all the necessary objects (RecipeReader, DataPreparation, DataAnalysis ***TBD***) in a Main class that reads in the file and calls the methods to create ArrayLists of all the recipes.
 *  Data preparation for data analysis is processed from DataPreparation instances to DataAnalysis ***TBD*** instances.
 */

public class RecipeRecommender {
	
	public static void main(String[] args) {
		
		RecipeReader rr = new RecipeReader("RAW_recipes.csv");
		
		DataPreparation prep = new DataPreparation(rr.getAllRecipes());
						
	}
	
}
