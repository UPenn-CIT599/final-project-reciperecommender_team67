import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IngredientInputControllerTest {
	
	StateModel model = new StateModel();
	IngredientInputView inputView = new IngredientInputView();
	
	// Tests that the add ingredient button works properly
	@Test
	void testAddIngredientButton() {
		IngredientInputController inputController = new IngredientInputController(model, inputView);
		inputController.setupButtons();
		inputController.getView().getIngredientInput().setText("test ingredient");
		inputController.getView().getAddButton().doClick(200);
		String lastElement = inputController.getView().getIngredientsModel().lastElement();
		assertEquals(lastElement, "test ingredient");
	}
	
	// Tests that the remove selected button works properly when one item is selected
	@Test
	void testRemoveSelectedButtonOneSelected() {
		IngredientInputController inputController = new IngredientInputController(model, inputView);
		inputController.setupButtons();
		inputController.getView().getIngredientInput().setText("Ing1");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing2");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing3");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredients().setSelectedIndex(1);
		inputController.getView().getRemoveSelected().doClick(200);
		assertEquals(inputController.getView().getIngredientsModel().firstElement(), "Ing1");
		assertEquals(inputController.getView().getIngredientsModel().lastElement(), "Ing3");
	}
	
	// Tests that the remove selected button works properly when multiple items are selected
	@Test
	void testRemoveSelectedButtonMultSelected() {
		IngredientInputController inputController = new IngredientInputController(model, inputView);
		inputController.setupButtons();
		inputController.getView().getIngredientInput().setText("Ing1");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing2");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing3");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing4");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredients().setSelectedIndices(new int[] {0, 2});
		inputController.getView().getRemoveSelected().doClick(200);
		assertEquals(inputController.getView().getIngredientsModel().firstElement(), "Ing2");
		assertEquals(inputController.getView().getIngredientsModel().lastElement(), "Ing4");
	}
	
	// Tests that the remove selected button works properly when an item is not selected
	@Test
	void testRemoveSelectedButtonNoSelected() {
		IngredientInputController inputController = new IngredientInputController(model, inputView);
		inputController.setupButtons();
		inputController.getView().getIngredientInput().setText("Ing1");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing2");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing3");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getRemoveSelected().doClick(200);
		assertEquals(inputController.getView().getIngredientsModel().firstElement(), "Ing1");
		assertEquals(inputController.getView().getIngredientsModel().elementAt(1), "Ing2");
		assertEquals(inputController.getView().getIngredientsModel().lastElement(), "Ing3");
	}
	
	// Tests that the remove all button works properly
	@Test
	void testRemoveAllButton() {
		IngredientInputController inputController = new IngredientInputController(model, inputView);
		inputController.setupButtons();
		inputController.getView().getIngredientInput().setText("Ing1");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing2");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getIngredientInput().setText("Ing3");
		inputController.getView().getAddButton().doClick(200);
		inputController.getView().getRemoveAll().doClick(200);
		assertEquals(inputController.getView().getIngredientsModel().getSize(), 0);
	}
}
