import java.util.ArrayList;

/**
 * 
 * @author Sylvain
 * Create a class called Recipe that will store the information for each recipe in the dataset 
 * A recipe should have the following instance variables:
 *
 */

public class Recipe {
	
	private String name;
	private int ID;
	private int minutes;
	private int contributorID;
	private String dateSubmitted;
	private ArrayList<String> tags;
	private ArrayList<String> nutrition;
	private int numSteps;
	private ArrayList<String> steps;
	private String description;
	private ArrayList<String> ingredients;

	public Recipe(String name, int ID, int minutes, int contributorID, String dateSubmitted, ArrayList<String> tags, 
			ArrayList<String> nutrition, int numSteps, ArrayList<String> steps, String description, ArrayList<String> ingredients) {
		
		this.name = name;
		this.ID = ID;
		this.minutes = minutes;
		this.contributorID = contributorID;
		this.dateSubmitted = dateSubmitted;
		this.tags = tags;
		this.nutrition = nutrition;
		this.numSteps = numSteps;
		this.steps = steps;
		this.description = description;
		this.ingredients = ingredients;

	}

	@Override
	public String toString() {
		return "Recipe [name=" + name + ", ID=" + ID + ", minutes=" + minutes + ", contributorID=" + contributorID
				+ ", dateSubmitted=" + dateSubmitted + ", tags=" + tags + ", nutrition=" + nutrition + ", numSteps="
				+ numSteps + ", steps=" + steps + ", description=" + description + ", ingredients=" + ingredients
				+ "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the contributorID
	 */
	public int getContributorID() {
		return contributorID;
	}

	/**
	 * @param contributorID the contributorID to set
	 */
	public void setContributorID(int contributorID) {
		this.contributorID = contributorID;
	}

	/**
	 * @return the dateSubmitted
	 */
	public String getDateSubmitted() {
		return dateSubmitted;
	}

	/**
	 * @param dateSubmitted the dateSubmitted to set
	 */
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	/**
	 * @return the tags
	 */
	public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the nutrition
	 */
	public ArrayList<String> getNutrition() {
		return nutrition;
	}

	/**
	 * @param nutrition the nutrition to set
	 */
	public void setNutrition(ArrayList<String> nutrition) {
		this.nutrition = nutrition;
	}

	/**
	 * @return the numSteps
	 */
	public int getNumSteps() {
		return numSteps;
	}

	/**
	 * @param numSteps the numSteps to set
	 */
	public void setNumSteps(int numSteps) {
		this.numSteps = numSteps;
	}

	/**
	 * @return the steps
	 */
	public ArrayList<String> getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(ArrayList<String> steps) {
		this.steps = steps;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	@Override
	public boolean equals(Object obj) {
		Recipe compRecipe = (Recipe) obj;
		if (compRecipe.getName().contentEquals(this.name) && compRecipe.getID() == this.ID &&
				compRecipe.getMinutes() == this.minutes && compRecipe.getContributorID() == this.contributorID &&
				compRecipe.getDateSubmitted().contentEquals(this.dateSubmitted) && compRecipe.getTags().equals(this.tags) &&
				compRecipe.getNutrition().equals(this.nutrition) && compRecipe.getNumSteps() == this.numSteps &&
				compRecipe.getSteps().equals(this.steps) && compRecipe.getDescription().contentEquals(this.description) &&
				compRecipe.getIngredients().equals(this.ingredients)) 
		{
			return true;
		} else {
			return false;
		}
	}
	
}


