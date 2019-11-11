
public class SplitArrayString {

    public static void main(String[] args) {
        String line = "foo,bar,c;qual=\"baz,blurb\",d;junk=\"quux,syzygy\"";
        
        String line2 = "['60-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'side-dishes', 'vegetables', 'mexican', 'easy', 'fall', 'holiday-event', 'vegetarian', 'winter', 'dietary', 'christmas', 'seasonal', 'squash']";
        
//        String line3 = "a bit different  breakfast pizza,31490,30,26278,2002-06-17,"['30-minutes-or-less', 'time-to-make', 'course', 'main-ingredient', 'cuisine', 'preparation', 'occasion', 'north-american', 'breakfast', 'main-dish', 'pork', 'american', 'oven', 'easy', 'kid-friendly', 'pizza', 'dietary', 'northeastern-united-states', 'meat', 'equipment']","[173.4, 18.0, 0.0, 17.0, 22.0, 35.0, 1.0]",9,"['preheat oven to 425 degrees f', 'press dough into the bottom and sides of a 12 inch pizza pan', 'bake for 5 minutes until set but not browned', 'cut sausage into small pieces', 'whisk eggs and milk in a bowl until frothy', 'spoon sausage over baked crust and sprinkle with cheese', 'pour egg mixture slowly over sausage and cheese', 's& p to taste', 'bake 15-20 minutes or until eggs are set and crust is brown']",this recipe calls for the crust to be prebaked a bit before adding ingredients. feel free to change sausage to ham or bacon. this warms well in the microwave for those late risers.,"['prepared pizza crust', 'sausage patty', 'eggs', 'milk', 'salt and pepper', 'cheese']",6";
        
        String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for(String t : tokens) {
            System.out.println("> "+t);
        }
        
        String[] tokens2 = line2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for(String t : tokens2) {
            System.out.println("> "+t);
        }
        
//        String[] tokens3 = line3.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//        for(String t : tokens3) {
//            System.out.println("> "+t);
//        }
        
//        str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        
    }
}