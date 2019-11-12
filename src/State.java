/**
 * enum that's used to represent current state of program
 */
public enum State {
	RECEIVING_INPUT("receiving"), DISPLAYING_OUTPUT("displaying");
	
	private String text;
	
	State(String text) {
		this.text = text;
	}
	
	/**
	 * gets the text representing the current state
	 * @return String containing the current state of the program
	 */
	public String getText() {
		return text;
	}

}
