# SLOGO

A development environment that helps users write SLogo programs.

# Names
 - Michael Zhang
 - Carrie Hunner
 - Christina Chen
 - Feroze Mohideen
# Timeline
date you started, date you finished, and an estimate of the number of hours worked on the project
Date Started: February 14
Date Finished: March 8

Michael's Hours: 50
Christina's Hours: 
Feroze's Hours: ~50
Carrie's Hours: 64

# Individual Roles
each person's role in developing the project
* **Michael:** I was on the back-end and designed the commands for the parser. I also dealt with the turtle interaction.
* **Christina:** I worked mainly on the parser and created models that didn’t pertain to the turtles (UserDefinedCommandsModel, VariablesModel, CurrentStateFileModel, HistoryModel). I also helped with some of the commands classes, such as Ask, AskWith, and UserDefinedCommands.
* **Feroze:** I was part of the front-end team and was responsible for turtle movement and constructing the framework of the GUI. 
* **Carrie:** I was on Front-End where I was responsible for the Console/Terminal display as well as most of the display View panes (Variables, User-Defined Commands, etc.). Additionally, I worked with Feroze on making Main (where the program is run).
# Resources Used
any books, papers, online, or human resources that you used in developing the project
* **Michael:**
    * Professor Duvall
    * Piazza
    * TAs
* **Christina:** 
    * TAs
    * Stack Overflow
* **Feroze:** 
    * JavaFX man pages
    * Stack Overflow
* **Carrie:**
    * [https://stackoverflow.com/](https://)
    * [https://www.geeksforgeeks.org/javafx-tabpane-class/](https://)
    * [https://docs.oracle.com/javase/7/docs/api/](https://)

# Files Used to Start the Project
files used to start the project (the class(es) containing main)

To start the application, run `Main.java` within the View Module.

# Files Used to Test and Expected Errors
files used to test the project and errors you expect your program to handle without crashing

We used the .logo files within `data/examples` to test our handling of various commands. We account for errors in the following ways:
* The console will report if a command is entered incorrectly.
* Users can only select from a drop-down array of images, so we don't have to worry about them uploading their own.
* Users can only enter valid RGB values and a unique ID. If they do not, 0 is populated as the offending RGB value(s) and the ID is randomly generated.

# Data/Resource Files Required
any data or resource files required by the project (including format of non-standard files)
* **View Module**
    * `ViewResources` folder: includes the necessary images and property files to run the front end
* **Back-End Module**
    * `languages` in the `resources` folder contains command information in different languages
    * `resources.Commands` allows us to group commands to parse them in their respective ways
* **Controller Module**
    * `Resources` folder: includes a property file to name the tabs and the stage

# Using the Program
any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
* **Console**
    * **Command Window:** Area to enter commands/code to be run
    * **Help Button:** This opens a Reference page that contains tabs and dropdown windows with command information.
    * **Upload File Button:** This allows a .txt or a .logo file to be uploaded. When done, the text will be written from the file and displayed in the Command Window.
    * **Run Button:** When clicked, it will read in and read the code in the Command Window
    * **Language Dropdown:** This allows for the language to be set for the commands such that the parser knows which language to expect. It can be changed anytime when using the program.
    * **Save State:**
        * Text Field: Allows the user to enter the desired name for the state they wish to save
        * Save State Button: This saves the user's defined variables and commands to a state file, named from the text field, which will be displayed and be accessible in the Saved State View on the main Display window.
    * **Error Pane:** The bottom pane spanning the wisth of the Console is reserved for displaying errors. If an invalid command is entered, it will turn red and display text to alert the user.


* **Display:** 
    * **Views:**
        * **History View:** This displays the past commands the user has run. The text will either be green, meaning it is a vlaid command and ran correctly, or red, meaning the command was invalid and did not run. The user can click on any of the commands in the HistoryView and they will be run again.
        * **Palette View:** This view allows the user to view the colors available to the user as well as add a new color. One has the ability to change the background color as well as pen color to this new color.
        * **Turtle Info View:** This view tracks the status of all active turtles and gives information on their position and pen characteristics. Users can also change the image associated with a turtle in this view, and add a turtle if desired.
        * **Pen View:** This view enables the user to interact with the pen. The slider changes the thickness of the pen, while the buttons may be used to either pick the pen up or set it down.
        * **Saved State View:** As mentioned in the section on the Console, the user is able to save and name the current state, ie the defined variables and commands. When this is done, the state will appear on this view. States are saved to files and thus can be accessed later even if the program is closed and reopened. By clicking on the desired name, the state will be reloaded to the current display.
        * **Turtle Movement View:** Buttons are in place to allow the user to control the active turtle graphically. Each arrow moves the turtle in its respective direction by 50 pixels. The other blocks will rotate the turtle in its respective direction by 45 degrees.
        * **User-Defined Commands View** Commands defined by the user will appear here/
        * **Defined Variables:** Defined variables will appear here. The variable value can be edited by the user.
    * **Tabs:** On the top of the display, there is a tab with a "+". When selected, this will create a new  Display tab. The same console can be used for all the tabs and the console will run programs only on Display being viewed.

# Decisions/Assumptions/Simplifications
any decisions, assumptions, or simplifications you made to handle vague, ambiguous, or conflicting requirements

- For the command `tell [ 100 ]`, we decided to only create one turtle with `id` of 100 instead of creating 100 turtles with ids `1, 2, 3, 4, etc.`. 
- For the command `tell [ 1 10 100 ] fd id`, we have our three turtles with ids `1`, `10`, and `100` each move the length of their respective ids.
- In AskWith, when evaluating the conditions to decide which turtles to move, we are assuming that these conditions do not do actions on the turtle. That is, conditions are of the sort: `greater? id 4` and not `fd 100`. When we evaluate commands, we always execute, so the latter command would mean that we move the turtle regardless of it meeting the condition given. In sum, while evaluating the condition, we must execute the command, as our code does not support the evaluation of a command without executing it.
- For the parser, the resource files required are the languages properties files that include the Command name mapped to the spelling of possible inputs for that command in each language. The parser also requires the files within the Commands resource file which include files for commands that take in a parameter in the format of a variable (such as MakeVariable which requires the name of the variable), file for commands that take in a string as a parameter (MakeCommand which requires the name of the new command), the file of all turtle commands which is needed for the multiple turtles functionality, and the file of all the functions that allow grouping.
- For parameter grouping, we chose to implement it based on which commands could be cumulative. This includes sum, difference, quotient, and other basic math commands. It also includes and, or, less than, greater than, which can be cumulative from left to right. The whole list of commands that we chose to group like this are in the ******** data file. The ones that aren’t able to be grouped, if they are entered with grouped parameters, will just execute every n parameters in order, where n is how many parameters the command needs.
- We also made the assumption that all of the saved state files that can be uploaded can only be from the ones that the program itself saves. Therefore, the user can not upload XML files from other sources into our program. This makes sense because the way that our program formats the XMLs are particular and no user should have access to the actual contents of the file so as to replicate it.
- For the command `askwith`, if no turtles meet the condition, then zero is returned.

# Known Bugs
any known bugs, crashes, or problems with the project's functionality

- AskWith is throwing a null pointer error

# Extra Features
any extra features included in the project
* Uploading a .txt or .logo file: Users can navigate to any folder on their computer and upload a file to the console where it can then be run.

# Impressions
your impressions of the assignment to help improve it in the future
* **Michael:** I found this assignment rewarding. But I wish we had more help with setting up modules and working with interfaces. We felt like we were getting conflicting information about how we should approach design using interfaces and module exports.
* **Christina:** 
* **Feroze:** Although we all agree it was rocky in the beginning, I thought that I gained more insight into object-oriented programming through this one assignment than with any other CS assignment I've had here at Duke. I never even really knew what an interface was before starting this project but now I feel like I have a solid understanding of abstracting as well as other advanced Java tools like Reflection, Functional Interfaces, and Generics. Overall, I wish there was a little more guidance in this assignment  - especially during sprint 2 - but I very happy with how hard my team worked and what we were eventually able to accomplish.
* **Carrie:** The initial start was extremely rough. We had a lot of struggles getting modules up, running, and communicating and I felt unprepared to be working with them. TA office hours were not particularly helpful either, as the TAs had not heard of modules until we arrived. In the future, perhaps a more in depth explanation and demo, as well as more resources or examples, would be helpful if modules are to remain a part of this project.
