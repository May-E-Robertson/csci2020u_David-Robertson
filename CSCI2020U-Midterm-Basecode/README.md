Project Information: To create this program I used javafx-sdk-15.0.1 and openjdk-15, version 15.0.2

To run this program, take the necessary steps to point to your local javafx files, then compile and run the program. When initially running this program a UI appears with three buttons. These buttons will open 3 different scenes, each with different functions and a button to return to the main menu.
The three different functions, as outlined below are, animation, 2d graphics, and about.

ANIMATION: The animation scene displays an animation of a duck, which was taken from a sprite sheet, located in the resources folder in the project directory. The duck will appear after 4 seconds. Code was written to print a square at specific coordinates on the sprite sheet. This square is the size of one duck. The square is then moved over the sheet to print different frames of the animation. There are 3 columns of animation for each duck, and 8 rows. An if statement is used to check if all 3 columns have been displayed and once they have the square resets to the first row and moves to the next column. A new animation frame is displayed once every 4000/3 milliseconds, so the next row will be moved to after 4 seconds.

2D GRAPHICS: The 2D Graphics scene prints several shapes to display my initials in an artistic way. Several javafx shapes, such as arcs, rectangles, and circles, are used to print the initials DR. There is also a label printed at the top of the window that reads "D.R." so that the user will know what the initials are, in case the shapes are not clear.

ABOUT: The about function displays information about me and the program. It includes information such as my student number, name, and email. It also shows a short description of the program. This information is read in from an xml file, located in the resources folder in the project directory.

How to run:  To run our application download the zip file containing the project files.  Open the project in Intellij and run the program.  A dialog box will appear with the three buttons that allow you to choose from the above features.


NOTE: The project does not need to be run in IntelliJ, however that is the safest/easiest way to run it.
