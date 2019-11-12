import java.awt.*;
import javax.swing.*;

/**
 * view the user sees when being shown recipes
 */
public class RecipeDisplayView {
	
	// Main panel for the view
	private JPanel mainPanel = new JPanel();

	// Buttons
	private JButton openRecipe = new JButton("Open Selected Recipe!");
	private JButton backButton = new JButton("Change Ingredients");

	// List of suggested recipes 
	private DefaultListModel<String> recipesModel = new DefaultListModel<>();
	private JList<String> recipes = new JList<>(recipesModel);

	RecipeDisplayView() {
		
		// Set up the display grid
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		mainPanel.add(new JScrollPane(recipes), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.gridy = 6;
		mainPanel.add(openRecipe, c);

		c.gridy = 7;
		mainPanel.add(backButton, c);
	}
	
	/**
	 * gets the mainPanel for the view
	 * @return JPanel containing the main panel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * gets the button the user clicks to open link to recipe
	 * @return JButton the user clicks to open recipe
	 */
	public JButton getOpenRecipe() {
		return openRecipe;
	}
	
	/**
	 * gets the button the user clicks to enter more ingredients
	 * @return JButton the user clicks to enter more ingredients
	 */
	public JButton getBackButton() {
		return backButton;
	}
	
	/**
	 * gets Model containing the recipes to be displayed
	 * @return DefaultListModel containing recipes to be displayed
	 */
	public DefaultListModel<String> getRecipesModel() {
		return recipesModel;
	}
	
	/**
	 * gets JList showing the recommended recipes
	 * @return JList showing the recommended recipes
	 */
	public JList<String> getRecipes() {
		return recipes;
	}

}
