import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * class which controls the recipeDisplay view
 * @author dan
 *
 */
public class RecipeDisplayController {
	private StateModel stateModel;
	private RecipeDisplayView view;

	public RecipeDisplayController(StateModel model, RecipeDisplayView view) {
		this.stateModel = model;
		// Listen for when the user submits the ingredient list and update when necessary
		this.stateModel.addPropertyChangeListener(new PropertyChangeListener() {
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
			public void actionPerformed(ActionEvent e) {
				// change state of the program
				stateModel.setState(State.RECEIVING_INPUT);
			}
		});
		
		// When user clicks on "open recipe" button, open a link to the recipe
		view.getOpenRecipe().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (view.getRecipes().getSelectedIndex() != -1) {
					String currRecipe = view.getRecipesModel().elementAt(view.getRecipes().getSelectedIndex());
					String currID = "38382";
					String url = GUIHelpers.createRecipeURL(currRecipe, currID);
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
		view.getRecipesModel().removeAllElements();
		for (String s : stateModel.getOutputRecipes()) {
			view.getRecipesModel().addElement(s);
		}
	}
}
