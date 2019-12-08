import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * class which controls the recipeDisplay view
 */
public class RecipeDisplayController {
	private StateModel stateModel;
	private RecipeDisplayView view;

	public RecipeDisplayController(StateModel model, RecipeDisplayView view) {
		this.stateModel = model;
		// Listen for when the user submits the ingredient list and update when necessary
		this.stateModel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// if the state change is the one we're interested in...
				if (evt.getPropertyName().equals("outputRecipes")) {
					populateRecipes();
				}
			}
		});
		this.view = view;
	}
	
	/**
	 * give the buttons their activity
	 */
	public void setupButtons() {
		
		// Allow user to enter more ingredients when they click on this button
		view.getBackButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// change state of the program
				stateModel.setState(State.RECEIVING_INPUT);
			}
		});
		
		// When user clicks on "open recipe" button, open a link to the recipe
		view.getOpenRecipe().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (view.getRecipes().getSelectedIndex() != -1) {
					String currRecipeName = view.getRecipesModel().elementAt(view.getRecipes().getSelectedIndex());
					currRecipeName = currRecipeName.substring(0, currRecipeName.indexOf("|"));
					String currID = Integer.toString(stateModel.getOutputRecipes().get(view.getRecipes().getSelectedIndex()).getID());
					String url = GUIHelpers.createRecipeURL(currRecipeName, currID);
					Desktop d = Desktop.getDesktop();
					try {
						d.browse(new URI(url));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * Update the recipes to be displayed
	 */
	public void populateRecipes() {
		if (view.getRecipesModel().size() != 0) {
			view.getRecipesModel().removeAllElements();
		}
		for (Recipe r : stateModel.getOutputRecipes()) {
			String additionalIngredient = RecipeRecommender.recommendedAdditionalIngredient(r, IngredientInputController.recipes);
			view.getRecipesModel().addElement(GUIHelpers.removeExcessSpaces(r.getName()) + additionalIngredient);

		}
	}
}
