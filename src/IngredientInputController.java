import java.awt.event.*;
import java.util.*;

/**
 * Controller for the ingredient input view
 */
public class IngredientInputController {
	private StateModel stateModel;
	private IngredientInputView view;
	DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
	
	public IngredientInputController(StateModel model, IngredientInputView view) {
		this.stateModel = model;
		this.view = view;
	}
	
	/**
	 * Gives the buttons on the view their functionality
	 */
	public void setupButtons() {
		
		// When user clicks button to add an ingredient, add the ingredient
		view.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!view.getIngredientInput().getText().contentEquals("")) {
					view.getIngredientsModel().addElement(view.getIngredientInput().getText());
					view.getIngredientInput().setText("");
				}
			}
		});
		
		// When user clicks button to remove the selected ingredient, remove it
		view.getRemoveSelected().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view.getIngredients().getSelectedIndex() != -1) {
					view.getIngredientsModel().remove(view.getIngredients().getSelectedIndex());
				}
			}
		});
		
		// When user clicks button to remove all ingredients, remove them
		view.getRemoveAll().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.getIngredientsModel().removeAllElements();
			}
		});
		
		// When user wants to find recipes, show them recipes by changing the state of the program
		view.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] inputIngredientArray = new String[view.getIngredientsModel().size()];
				for (int i=0; i<inputIngredientArray.length; i++) {
					inputIngredientArray[i] = view.getIngredientsModel().elementAt(i);
				}
				
				// remove non nouns from user input ingredients
				for (int i=0; i<inputIngredientArray.length; i++) {
					inputIngredientArray[i] = dataPrep.removeNonNouns(inputIngredientArray[i]);
				}
				
				ArrayList<Recipe> outputRecipes = RecipeRecommender.returnRecipe(inputIngredientArray);
				stateModel.setOutputRecipes(outputRecipes);
				stateModel.setState(State.DISPLAYING_OUTPUT);
			}
		});
		
	}
	
}
