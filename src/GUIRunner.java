import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.*;
import javax.swing.*;

/**
 * Class to run the GUI
 */
public class GUIRunner {
	/**
	 * Makes and displays the GUI
	 */
	
	public static void createAndDisplay() {
		
		
		JDialog loadingDialog = new JDialog();
		JLabel loadingMessage = new JLabel("We are reading in all of the recipes.  This takes between 30 and 60 seconds.");
		loadingDialog.add(loadingMessage);
		loadingDialog.pack();
		loadingDialog.setLocationRelativeTo(null);
		loadingDialog.setVisible(true);
		
		// Do all of the main work in the background so that the wait message 
		// functions properly
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			public Void doInBackground() {
				// Model to keep track of the state of the program 
				StateModel model = new StateModel();
				// View the user sees when entering ingredients
				IngredientInputView inputView = new IngredientInputView();
				// View the user sees when being shown recipes
				RecipeDisplayView outputView = new RecipeDisplayView();
				// Controller for ingredient input view
				IngredientInputController inputController = new IngredientInputController(model, inputView);
				// Load in all of the recipes
				inputController.populateRecipes();
				// Once this done, remove the loading dialog
				loadingDialog.setVisible(false);
				// Controller for the display view
				RecipeDisplayController outputController = new RecipeDisplayController(model, outputView);

				// Give the buttons their activity
				inputController.setupButtons();
				outputController.setupButtons();

				// Create the main frame
				JFrame frame = new JFrame("RecipeRecommender");

				// Set the layout of the main panel of the frame
				JPanel mainPanel = new JPanel(new CardLayout());

				// Add the two views (input and display) to the main panel 
				mainPanel.add(inputView.getMainPanel());
				mainPanel.add(outputView.getMainPanel());

				// Set the input view to be the first view
				CardLayout cl = (CardLayout) (mainPanel.getLayout());
				cl.first(mainPanel);
				frame.add(mainPanel);

				// Set features of the main frame
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

				// Listen for changes in the state of the programming (going from input to output or vice versa)
				model.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						// if the change is the one we're interested in...
						if (evt.getPropertyName().equals("State")) {
							// change the display when appropriate
							cl.next(mainPanel);
						}
					}
				});
				return null;
			}
		};
		worker.execute();
	}

	// call Swing code in a thread-safe manner like tutorials
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndDisplay();
			}
		});
	}

}
