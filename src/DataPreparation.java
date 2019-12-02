import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
 
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**
 * Data preparation class has methods that prepare the data for analysis
 */
public class DataPreparation {
	
	InputStream tokenModelIn;
	InputStream posModelIn;
	TokenizerModel tokenModel;
	Tokenizer tokenizer;
	POSModel posModel;
	POSTaggerME posTagger;
	
	/**
	 * Contructor
	 * @param tokenModelFileName name of the model file that will break ingredients up into tokens
	 * @param posModelFileName name of the model file that will identify part of speech of the ingredients
	 */
	public DataPreparation(String tokenModelFileName, String posModelFileName) {
		try {
			this.tokenModelIn = new FileInputStream(tokenModelFileName);
			this.posModelIn = new FileInputStream(posModelFileName);
			this.tokenModel = new TokenizerModel(tokenModelIn);
			this.tokenizer = new TokenizerME(tokenModel);
			this.posModel = new POSModel(posModelIn);
			this.posTagger = new POSTaggerME(posModel);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
            if (tokenModelIn != null) {
                try {
                    tokenModelIn.close();
                }
                catch (IOException e) {
                }
            }
            if (posModelIn != null) {
                try {
                    posModelIn.close();
                }
                catch (IOException e) {
                }
            }
        }		
	}
	
	/**
	 * removes all non nouns from an ingredient
	 * @param ingredient String representing the ingredient to remove non nouns from
	 * @return an ingredient with only nouns
	 */
	public String removeNonNouns(String ingredient) {
		String tokens[] = tokenizer.tokenize(ingredient);
		List<String> tokensList = new ArrayList<String>();
		Collections.addAll(tokensList, tokens);
		String tags[] = posTagger.tag(tokens);
		int counter = 0;
		for (String t : tags) {
			if (t.charAt(0) != 'N') {
				tokensList.remove(counter);
			} else {
				counter++;
			}	
		}
		String returnString = "";
		boolean isFirst = true;
		for (String s : tokensList) {
			if (isFirst) {
				returnString = s;
				isFirst = false;
			} else {
				returnString = returnString + " " + s; 
			}
		}
		
		return returnString;
	}
	
	/**
	 * turns an array of strings into one long string with spaces between
	 * @param ingredients array of strings with ingredients in each cell
	 * @return long string of all ingredients
	 */
	public String makeContiniousString(String[] ingredients) {
		String returnString = "";
		boolean isFirst = true;
		for (String s : ingredients) {
			if (isFirst) {
				returnString = s;
				isFirst = false;
			}
			else {
				returnString = returnString + " " + s;
			}
		}
		return returnString;
	}
	
	/**
	 * turns an arraylist of strings into one long string with spaces between
	 * @param ingredients array list of strings with ingredients in each cell
	 * @return long string of all ingredients
	 */
	public String makeContiniousString(ArrayList<String> ingredients) {
		String returnString = "";
		boolean isFirst = true;
		for (String s : ingredients) {
			if (isFirst) {
				returnString = s;
				isFirst = false;
			}
			else {
				returnString = returnString + " " + s;
			}
		}
		return returnString;
	}

	public void getSimililarity(ArrayList<Recipe> allRecipes) {
		//return
	}
	
	/**
	 * removes the spaces from a string
	 * @param inputString a string you want to remove the spaces from
	 * @return string with no spaces
	 */
	public String removeSpaces(String inputString) {
		return inputString.replaceAll("\\s", "");
	}
	
	// This method is for testing purposes DELETE EVENTUALLY
	public static void main(String[] args) {
		DataPreparation dataPrep = new DataPreparation("en-token.bin", "en-pos-maxent.bin");
		String temp = dataPrep.removeNonNouns("whole kernel corn");
		System.out.println(temp);
		temp = dataPrep.removeSpaces(temp);
		System.out.println(temp);
	}
}

