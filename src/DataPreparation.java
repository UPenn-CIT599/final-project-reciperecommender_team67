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
 * Create a DataPreparation class that contains an ArrayList of Recipe objects as an instance variable
 * and create methods to pre-process the recipe entries for analysis in DataAnalysis.
 */
public class DataPreparation {
	
	InputStream tokenModelIn;
	InputStream posModelIn;
	TokenizerModel tokenModel;
	Tokenizer tokenizer;
	POSModel posModel;
	POSTaggerME posTagger;
		
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
				returnString = " " + returnString; 
			}
		}
		
		return returnString;
	}
	

}

