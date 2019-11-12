import java.util.*;
import java.io.*;

/**
 * class to clean the recipe data to ensure that one entry is on each line
 */
public class RecipeDataCleaner {

	/**
	 * writes a new file called RAW_recipes_cleaned.CSV which will have 1 recipe on each line
	 * @param filename the name of the recipe file you want to clean
	 */
	public void cleanRecipeData(String filename) {
		try {
			// read in the file
			Scanner in = new Scanner(new FileReader(filename));
			FileWriter fw;
			try {
				// write to this file
				fw = new FileWriter("RAW_recipes_cleaned.csv", false);
				PrintWriter pw = new PrintWriter(fw);
				// print the headers of the columns on the file
				pw.println(in.nextLine());
				String currWriteLine = "";
				while(in.hasNextLine()) {
					String currReadLine = in.nextLine();
					if (currReadLine.length() == 0) {
						continue;
					}
					// if the last character is a digit, end the line and write it to the new file
					else if (Character.isDigit(currReadLine.charAt(currReadLine.length()-1))) {
						currWriteLine = currWriteLine + currReadLine;
						pw.println(currWriteLine);
						currWriteLine = "";
						// if the last character is not a digit, add to the current line
					} else {
						currWriteLine = currWriteLine + currReadLine;
					}
				}
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** 
	 * makes a smaller version of the cleaned recipe data
	 * @param filename name of the recipe data file you want to clean and make smaller
	 */
	public void makeShortCleanRecipeData(String filename) {
		try {
			// read in the file
			Scanner in = new Scanner(new FileReader(filename));
			FileWriter fw;
			try {
				// write to this file
				fw = new FileWriter("RAW_recipes_short.csv", false);
				PrintWriter pw = new PrintWriter(fw);
				// print the headers of the columns on the file
				pw.println(in.nextLine());
				String currWriteLine = "";
				int counter = 0;
				while(counter < 1000) {
					String currReadLine = in.nextLine();
					if (currReadLine.length() == 0) {
						continue;
					}
					// if the last character is a digit, end the line and write it to the new file
					else if (Character.isDigit(currReadLine.charAt(currReadLine.length()-1))) {
						currWriteLine = currWriteLine + currReadLine;
						pw.println(currWriteLine);
						currWriteLine = "";
						// if the last character is not a digit, add to the current line
					} else {
						currWriteLine = currWriteLine + currReadLine;
					}
					counter++;
				}
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RecipeDataCleaner rcp = new RecipeDataCleaner();
		rcp.cleanRecipeData("Raw_recipes.CSV");
		rcp.makeShortCleanRecipeData("Raw_recipes.CSV");
	}

}
