import java.awt.event.*;

/**
 * Controller for the ingredient input view
 * @author dan
 *
 */
public class IngredientInputController {
	private StateModel stateModel;
	private IngredientInputView view;
	
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
				String[] temp = {"Hello", "World"};
				stateModel.setOutputRecipes(temp);
				stateModel.setState(State.DISPLAYING_OUTPUT);
			}
		});
		
	}
	
}
