import java.beans.*;
import java.util.*;

/**
 * class to contain information about the current state of the program
 */
public class StateModel {
	private State state = State.RECEIVING_INPUT;
	private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);
	private ArrayList<Recipe> outputRecipes;
	
	/**
	 * sets the state of the program and notifies all listeners
	 * @param newState the new state of the program
	 */
	public void setState(State newState) {
		State oldState = state;
		state = newState;
		pcSupport.firePropertyChange("State", oldState, newState);
	}
	
	/**
	 * sets the list of output recipes and notifies all listeners
	 * @param newRecipes the new list of output recipes
	 */
	public void setOutputRecipes(ArrayList<Recipe> newRecipes) {
		ArrayList<Recipe> oldRecipes = outputRecipes;
		outputRecipes = newRecipes;
		pcSupport.firePropertyChange("outputRecipes", oldRecipes, newRecipes);
	}
	
	/**
	 * adds a property change listener to this model
	 * @param listener the listener you want to add
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcSupport.addPropertyChangeListener(listener);
	}
	
	/**
	 * gets the state of the program
	 * @return State indicating the state of the program
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * gets the output recipes
	 * @return ArrayList of recipes containing the recipes to be shown to the user
	 */
	public ArrayList<Recipe> getOutputRecipes() {
		return outputRecipes;
	}
	
	

}
