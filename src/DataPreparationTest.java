import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class DataPreparationTest {
	
	DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
	
	// testing removeNonNouns
	@Test
	void testRemoveNonNouns() {
		String testString = "organic chicken breast";
		assertEquals(dataPrep.removeNonNouns(testString), "chicken breast");
	}
	
	// testing make makeContinuousString with array input
	@Test
	void testMakeContinuousStringArray() {
		String[] testArray = {"chicken", "stock"};
		assertEquals(dataPrep.makeContinuousString(testArray), "chicken stock");
	}
	
	// testing make makeContinuousString with arrayList input
	@Test
	void testMakeContinuousStringArrayList() {
		ArrayList<String> testArrayList = new ArrayList<String>();
		testArrayList.add("chicken");
		testArrayList.add("stock");
		assertEquals(dataPrep.makeContinuousString(testArrayList), "chicken stock");
	}
	
	// testing removeSpaces
	@Test
	void testRemoveSpaces() {
		String testString = "chicken    stock";
		assertEquals(dataPrep.removeSpaces(testString), "chickenstock");
	}
}
