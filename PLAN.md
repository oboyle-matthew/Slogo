# Introduction
The purpose of making this SLOGO project is to create a logo-style program which lets users to enter command to control the movement of a turtle object on the screen. In Python, there is a similar library called “turtle graphic”, which allows users to draw different kinds of graphs by moving a turtle on the screen. Generally speaking, we plan to divide the whole project into two parts: front end and back end. Front end is mainly responsible for creating objects displaying on the screen, which includes buttons, canvas, turtle and a command-input text area. For the back end, we should figure out how to transfer the text in the text area to the command we could use and finally move the turtle on the screen according to different commands. So, after users enter commands in the text area, We plan for the back end API to take a string command, parse the string, validate it, and iterate through a command tree to ultimately call the appropriate method from the front end API to reflect the changes on the front end.

# Overview
For our SlOGO program, we will be using the (MVC)Model-View-Contoller model, foregoing the controller. The back-end (Model) will have the "Model", "Parser", and "Commands" classes with subclasses extending Commands. The front-end (View and Controller) will have the "Main", “Screen”, and "Turtle" classes. “Screen” class(controller) will act as points of contact,  communicate between the front and back end, transmitting string from text box to the parser and retrieving the command of turtles from the back end.

# User Interface
For the user interface, our program will have two screens: splash screen and the main screen. The splash screen includes a the title of our game and a drop-down menu which allows users to choose the language of the help tab. 
After hitting the GO button besides the drop-down menu, the programs switches to the main screen. 

The main screen consists of 5 major components:
*Toolbar: Users will interact with the toolbar to access a command help page as well as color picker box. Once you click the help page button, the screen will pop out a new window including the basic command of turtle movements.

*Command Input TextBox: this multiline text area would allow users to input commands. There would also be two buttons in this box, Run Button and Reset Button. Reset Button will clear the text in the command line. Run Button would pass the string in the TextBox to the backend and thus cause the turtle move on the screen.

*Command History: Past commands will be displayed in the command history panel. These past commands can also be executed again by clicking them. Additionally, current variables will also be displayed in the command history panel.

*Canvas: The turtle and the trail of movement will be displayed on the canvas. 

*Properties Box: A scroll textfield would display the properties of the current objects on the canvas, including x Coordinate, y Coordinate, number of turtles and etc.



# API Details

One API that we were going to create is the PreviousCommands API. This API would be created in order to show the user the previous commands that they typed into the text box. We may also make it possible for the user to load these commands again simply by clicking on them, making this an external API (it interacts with both the front-end and the back-end). We feel that this would be a very good addition, because it allows the user to save time typing in the same commands over and over again as it should be very common that the user wants to re-create a command that they typed in. This API should also separate the front and the back end of the project so that it follows the black-box model of hiding how things are actually implemented on the inside. Therefore, the front-end doesn’t even know why the words will be displayed on the screen, all it does is display them in a certain box if the back-end tells it to. 

There will also be another API that allows the user to input their commands. This string will be passed to the back-end through the StringParser class in order to decide what command should be implemented. The front-end will then be passed back a command saying where it should move, and using its current conditions (Eg. The orientation of the turtle, whether the pen is up or down etc.) it will execute this. The back-end will pass the front-end an old location and a new location, the front-end takes this in and moves the turtle to the new location, and draws a line between the two locations only if the pendown variable is true.

The Turtle class in our program will have instance variables such as PenDown, xLocation, yLocation, Orientation, myImage. This class will then have constructors in order to move the turtle, draw a line between two points, change the rotation of the turtle, and toggle the pen on and off. Because the image of the turtle is an instance variable, we can actually make it possible that the user can change the image of the turtle if they want to at any point. 

#Example:
The “fd 50” string is input to the view, which then sends the string to the controller, where a Parser class takes the input and creates the appropriate command classes (in this case something that handles moving the turtle). This command is executed by a CommandExecuter class that uses Turtle and front end API (drawLine, moveForward, addPrevMove) to do the appropriate action.

Simran uses cases: For the turtle, any movements involving turtle’s will be handled by code I create. Continuing with the example above, the CommandExecuter will call turtle.moveForward(50), which will set the position of the turtle to 50 more than the current one. Since the turtle will have an imageview that is already added to the view holding the game, the turtle will automatically move as I change the position inside my turtle class. 

Since I will also help with parsing, any command typed may use my code. The rough implementation will include splitting up the string into different elements and their corresponding inputs, then creating the corresponding command classes.

#Design Considerations:
Commands Execution and Creation: We think that the best way to handle this is by having each command be it’s own class and having a way to execute these command classes by either a class that like CommandExecution or a method within the class itself that is called to execute. This is opposed to having a large class that has different public methods called in response to the variety of different available commands. Creating a lot of classes from commands will introduce a large variety of interfaces created for special cases (potentially like math commands vs turtle commands vs UI commands, etc.) This type of code structure allows for more closed classes and makes it so that to create a new command, a new class must be made as opposed to editing a large file that handles many different command cases.
Overall Structure (MVC): We believe the best way to go about structuring our project with MVC architecture. Our main scene that contains all the graphics the views and handles a lot of the communication between model and view will be the controller. The model will include the parsing, and turtle movement, while the view includes the buttons, sliders, text boxes, etc. that are populated from information created by the model and given by the controller. This structure inherently fosters modularity as it creates fine distinctions between the roles of different classes.

#Responsibilities
View: Matthew with creating the appropriate views, scenes, boxes
Controller: Heyao focusing on creating controller that interacts with backend and view
Model: Simran with turtle and helping with parsing, Walker with commands/executions and parsing

