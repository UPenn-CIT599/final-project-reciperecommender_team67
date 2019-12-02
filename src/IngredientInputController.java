import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JLabel;


/**
 * Controller for the ingredient input view
 */
public class IngredientInputController {
	private StateModel stateModel;
	private IngredientInputView view;
	DataPreparation dataPrep;
	ArrayList<Recipe> recipes;
	
	public IngredientInputController(StateModel model, IngredientInputView view) {
		this.stateModel = model;
		this.view = view;
		this.dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
	}
	
	/**
	 * Loads all of the recipes into the recipes arrayList
	 */
	public void populateRecipes() {
		RecipeReader recipeReader = new RecipeReader("data/RAW_recipes_cleaned.csv");
		this.recipes = recipeReader.readRecipes();
	}
	
	/**
	 * Gives the buttons on the view their functionality
	 */
	public void setupButtons() {
		
		// When user clicks button to add an ingredient, add the ingredient
		view.getAddButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!view.getIngredientInput().getText().contentEquals("")) {
					view.getIngredientsModel().addElement(view.getIngredientInput().getText());
					view.getIngredientInput().setText("");
				}
			}
		});
		
		// When user clicks button to remove the selected ingredient, remove it
		view.getRemoveSelected().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getIngredients().getSelectedIndex() != -1) {
					view.getIngredientsModel().remove(view.getIngredients().getSelectedIndex());
				}
			}
		});
		
		// When user clicks button to remove all ingredients, remove them
		view.getRemoveAll().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getIngredientsModel().removeAllElements();
			}
		});
		
		// When user wants to find recipes, show them recipes by changing the state of the program
		view.getSubmitButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Make sure the user has entered some ingredients
				if (view.getIngredientsModel().size() > 0) {
					String[] inputIngredientArray = new String[view.getIngredientsModel().size()];
					for (int i=0; i<inputIngredientArray.length; i++) {
						inputIngredientArray[i] = view.getIngredientsModel().elementAt(i);
					}
					
					// remove non nouns from user input ingredients
					for (int i=0; i<inputIngredientArray.length; i++) {
						inputIngredientArray[i] = dataPrep.removeNonNouns(inputIngredientArray[i]);
						inputIngredientArray[i] = dataPrep.removeSpaces(inputIngredientArray[i]);
					}
					
					String inputIngredientString = dataPrep.makeContiniousString(inputIngredientArray).toLowerCase();
					
					ArrayList<Recipe> outputRecipes = RecipeRecommender.returnRecipe(recipes, inputIngredientString, 10);
					
					if (outputRecipes.size()==0) {
						displayErrorDialog("We could not find any recipes that are a good match. Please edit your ingredients.");
					}
					else {
						//DELETE
						System.out.println(inputIngredientString);
						for (Recipe r : outputRecipes) {
							System.out.println(dataPrep.makeContiniousString(r.getIngredients()));
						}
						
						stateModel.setOutputRecipes(outputRecipes);
						stateModel.setState(State.DISPLAYING_OUTPUT);
					}
				}
				// If they have not entered any ingredients, then prompt them to.
				else {
					displayErrorDialog("Please enter some ingredients before searching for recipes!");
				}
			}
		});

		view.getFeelingLuckyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Recipe> randomizedRecipe = RecipeRecommender.randomRecipe();
				stateModel.setOutputRecipes(randomizedRecipe);
				stateModel.setState(State.DISPLAYING_OUTPUT);
			}
		});
		
	}
	
	/**
	 * creates a dialog box with an error message for the user
	 * @param message String containing the error message you want the user to see
	 */
	public void displayErrorDialog(String message) {
		JDialog errorDialog = new JDialog();
		JLabel errorMessage = new JLabel(message);
		errorDialog.add(errorMessage);
		errorDialog.pack();
		errorDialog.setLocationRelativeTo(null);
		errorDialog.setVisible(true);
	}
	
}
