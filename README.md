# final-project-reciperecommender_team67

> This is the final project for Fall 2019 591 for Dan, Sylvain, and Jeanine.
> We are building a recipe recommender to users based on ingredients that they
> input into the GUI. 

# Instructions for use
To use the program first download the repository and unzip the folder. Next, follow this link https://drive.google.com/open?id=1Ucd9A2pbtpmiZIGlkuToAWYLjyo8zwtX to download a CSV file containing our recipes.  If the CSV file isn't already named "RAW_recipes_cleaned.csv" change its name to that and place it into the unzipped repository folder that you downloaded.  

Open up Eclipse and click "File"->"Open Projects from File System"->"Directory...", then navigate to where the unzipped repository is saved on your computer.  Click finish once you have done this.

Make sure to update your project path if the dependencies in the library files (included in the subfolder /lib) are not recognized by your Java class files, using the "configure build path" option in Eclipse.

To run the program, run the GUIRunner.java class.  You'll be prompted with a message asking you to wait for 30-60 seconds as we read in all of the recipes. Once, this completes a user interface will appear that allows you enter in as many ingredients as you'd like.  When you are ready to search for recipes with ingredients most similar to the ones you've entered, click on "Find Recipes!".  If we find similar recipes, you'll be provided with their names and you can navigate to a website with the full recipe by clicking "Open Selected Recipe!".
