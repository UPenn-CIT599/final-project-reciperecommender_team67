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
	
	// testing make makeContiniousString with array input
	@Test
	void testMakeContiniousStringArray() {
		String[] testArray = {"chicken", "stock"};
		assertEquals(dataPrep.makeContiniousString(testArray), "chicken stock");
	}
	
	// testing make makeContiniousString with arrayList input
	@Test
	void testMakeContiniousStringArrayList() {
		ArrayList<String> testArrayList = new ArrayList<String>();
		testArrayList.add("chicken");
		testArrayList.add("stock");
		assertEquals(dataPrep.makeContiniousString(testArrayList), "chicken stock");
	}
	
	// testing removeSpaces
	@Test
	void testRemoveSpaces() {
		String testString = "chicken    stock";
		assertEquals(dataPrep.removeSpaces(testString), "chickenstock");
	}
}
