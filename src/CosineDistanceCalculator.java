import org.apache.commons.text.similarity.CosineDistance;

/**
 * 
 * @author Sylvain
 *
 * Source:
 * Apache Commons Text library
 * https://commons.apache.org/proper/commons-text/
 *
 * Dependency:
 * Apache Commons Lang library
 * https://commons.apache.org/proper/commons-lang/
 *
 * Cosine similarity/distance
 * Algorithm:
 * Similarity is checked by words in both inputs. Word counts are put in the cosine similarity formula XYZ to get similarity.
 * Algorithm is case sensitive so if words are in different case, they count as different.
 * Sequence of words doesn’t matter. Two sentences with same words in different order count as exact same.
 * Result:
 * Algorithm gives value between 0 to 1. Then multiplied by 100 to get a percentage.
 * Similarity = 1 – Distance (one minus distance)
 * Usage:
 * Cosine similarity algorithms are used to find out the similarity between documents regardless of their size.
 * The formula for the cosine similarity doesn’t depend on the magnitude of the inputs.
 *
 */

public class CosineDistanceCalculator {

	private static String s1 = "Amor omnibus idem";
	private static String s2 = "Omnia vincit amor";
	private static String s3 = "In vino veritas";
	private static String s4 = "Vincit Omnia Veritas";

	public static void main(String[] args) {

		String[][] testStrings = new String[][] {
		
				{"winter squash mexican seasoning mixed spice honey butter olive oil salt", "sugar unsalted butter bananas eggs fresh lemon juice orange rind cake flour baking soda salt"},
				
				{"milk frozen juice concentrate plain yogurt", "apple juice strawberry banana non-fat vanilla frozen yogurt ice"},
				
				{"ground beef white rice beef gravy onion eggs", "ground beef green peas cream of mushroom soup milk onion salt pepper"},
				
				{"bacon pepper", "cocoa sugar water"},
				
				{"flour sugar cocoa baking powder salt milk vegetable oil vanilla brown sugar hot water", "flour sugar cocoa baking soda salt vegetable oil vinegar vanilla water"}
		
		};

		for (String[] input : testStrings) {

			// How different words are between both strings
			double cosineDistance = new CosineDistance().apply(input[0], input[1]);
			double cosineDistancePercentage = Math.round(cosineDistance * 100);
			double cosineSimilarityPercentage = Math.round((1 - cosineDistance) * 100);

			System.out.println("cosine distance of:\n" + input[0] + "\nand\n" + input[1] + "\nwords in recipes are "
					+ cosineDistancePercentage + "% different or " + cosineSimilarityPercentage + "% similar\n");
		}

		// other test data
		double cosineDistanceS1S2 = new CosineDistance().apply(s1.toLowerCase(), s2.toLowerCase());
		System.out.println("provided strings:\n" + s1 + "\nand\n" + s2 + "\nare "
				+ Math.round((1 - cosineDistanceS1S2) * 100) + "% similar\n");
		double cosineDistanceS2S4 = new CosineDistance().apply(s2.toLowerCase(), s4.toLowerCase());
		System.out.println("provided strings:\n" + s2 + "\nand\n" + s4 + "\nare "
				+ Math.round((1 - cosineDistanceS2S4) * 100) + "% similar\n");
		double cosineDistanceS3S4 = new CosineDistance().apply(s3.toLowerCase(), s4.toLowerCase());
		System.out.println("provided strings:\n" + s3 + "\nand\n" + s4 + "\nare "
				+ Math.round((1 - cosineDistanceS3S4) * 100) + "% similar\n");
	}
}
