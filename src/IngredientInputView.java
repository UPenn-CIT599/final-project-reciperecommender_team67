import java.awt.*;
import javax.swing.*;

/**
 * view the user sees when entering ingredients
 * 
 * @author dan
 *
 */
public class IngredientInputView {

	// main panel that will hold everything
	private JPanel mainPanel = new JPanel();

	// Ingredient text field
	private JTextField ingredientInput = new JTextField("Enter ingredient");

	// Buttons
	private JButton addButton = new JButton("Add ingredient");
	private JButton removeSelected = new JButton("Remove selected");
	private JButton removeAll = new JButton("Remove all");
	private JButton submitButton = new JButton("Find Recipes!");

	// List of ingredients user has entered
	private DefaultListModel<String> ingredientsModel = new DefaultListModel<>();
	private JList<String> ingredients = new JList<>(ingredientsModel);

	IngredientInputView() {

		// Set up the display grid
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		mainPanel.add(ingredientInput, c);

		c.gridy = 1;
		mainPanel.add(addButton, c);

		c.gridy = 5;
		mainPanel.add(removeSelected, c);

		c.gridy = 6;
		mainPanel.add(removeAll, c);

		c.gridy = 7;
		mainPanel.add(submitButton, c);

		c.gridy = 2;
		c.gridheight = 3;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		mainPanel.add(new JScrollPane(ingredients), c);

	}
	
	/**
	 * gets the mainPanel for the view
	 * @return JPanel containing the main panel
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * gets the textfield where user enters ingredients
	 * @return JTextField that user users to enter ingredients
	 */
	public JTextField getIngredientInput() {
		return ingredientInput;
	}
	
	/**
	 * gets the add ingredient button
	 * @return JButton the user clicks to add ingredients
	 */
	public JButton getAddButton() {
		return addButton;
	}
	
	/**
	 * gets the removeSelected button
	 * @return JButton the user clicks to remove selected ingredient
	 */
	public JButton getRemoveSelected() {
		return removeSelected;
	}
	
	/**
	 * gets the removeAll button
	 * @return JButton the user clicks to remove all ingredients
	 */
	public JButton getRemoveAll() {
		return removeAll;
	}
	
	/**
	 * gets the submitButton
	 * @return JButton the user clicks to find a recipe 
	 */
	public JButton getSubmitButton() {
		return submitButton;
	}
	
	/**
	 * gets the Model holding the current ingredients
	 * @return DefaultListModel holding the ingredients
	 */
	public DefaultListModel<String> getIngredientsModel() {
		return ingredientsModel;
	}
	
	/**
	 * gets the JList of ingredients
	 * @return JList containing the ingredients
	 */
	public JList<String> getIngredients() {
		return ingredients;
	}
}
