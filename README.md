# final-project-reciperecommender_team67
final-project-reciperecommender_team67 created by GitHub Classroom

# inal-project-reciperecommender_team67

> This is the final project for Fall 2019 591 for Dan, Sylvain, and Jeanine.
> We are building a recipe recommender to users based on ingredients that they
> input into the GUI. 

#Classes


#Data Manipulation and Cleaning

- Recipe
- RecipeDataCleaner
- RecipeReader
- DataPreparation (calls RecipeReader) - SP
- CosineDistanceCalculator - Word2Vec - SP

#Recipe Recommendation Algorithms (calls Word2Vec model)

- Ingredients --> Vector --> Looking for Neighbors --> Retrieve 
from original file
- RecipeRecommender (calls RecipeReader, DataPreparation) - JC
- AdditionalIngredientRecommender - JC  
- IngredientSwap - JC 

#User Interface

- GUIHelpers
- GUIRunner
- IngredientInputController
- IngredientInputView 
- RecipeDisplayController
- RecipeDiplayView
- StateModel


