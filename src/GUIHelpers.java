/**
 * Class with helper functions for the GUI
 */
public class GUIHelpers {
	
	/**
	 * generates a url to see a recipe
	 * @param recipeName the name of recipe
	 * @param recipeID the ID of the recipe
	 * @return a String containing a link to the recipe
	 */
	public static String createRecipeURL(String recipeName, String recipeID) {
		String urlBeginning = "https://www.food.com/recipe/";
		char[] nameFormatted = recipeName.toCharArray();
		
		// Count the number of spaces the come after spaces so we can remove them
		int spaceAfterSpaceCount = 0;
		boolean previousWasSpace = false;
		boolean currIsSpace = false;
		for (int i=0; i<nameFormatted.length; i++) {
			if (nameFormatted[i] == ' ') {
				currIsSpace = true;
			} else {
				currIsSpace = false;
			}
			if (currIsSpace && previousWasSpace) {
				spaceAfterSpaceCount++;
			}
			previousWasSpace=currIsSpace;
		}
		
		// Copy the name except remove back to back spaces and replace other spaces with '-'
		char[] nameFormattedNew = new char[nameFormatted.length-spaceAfterSpaceCount];
		int loc = 0;
		previousWasSpace = false;
		for (int i=0; i<nameFormatted.length; i++) {
			if (nameFormatted[i] == ' ' && !previousWasSpace) {
				nameFormattedNew[loc] = '-';
				loc++;
				previousWasSpace = true;
			} 
			else if (nameFormatted[i] == ' ' && previousWasSpace) {
				previousWasSpace = true;
				continue;
			} else {
				nameFormattedNew[loc] = nameFormatted[i];
				loc++;
				previousWasSpace = false;
			}
		}
		String urlEnd = new String(nameFormattedNew)+"-"+recipeID;
		return urlBeginning + urlEnd;
	}
	
	/**
	 * removes excess spaces from a string such that there is only one space between each word
	 * @param s the string you want to remove excess spaces from
	 * @return A string containing s with no spaces in it 
	 */
	public static String removeExcessSpaces(String s) {	
		return s.trim().replaceAll(" +", " ");
	}
}
