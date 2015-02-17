SLogo Plan
=====

###Introduction (Emre)
The problem our team is trying to solve is creating a logo-style program with a front end and back end component that together allow users to enter commands that control a “turtle” on the screen. We plan for the back end API to take a string command, parse the string, validate it, and iterate through a command tree to ultimately call the appropriate method from the front end API to reflect the changes on the front end.

Our project is most flexible in the back end and, more specifically, in implementing new commands. As each command has a series of nodes in the command tree, adding a command simply requires the insertion of additional methods into this tree. Our project is inherently less flexible on the front end. This is because while adding directions for the execution of a command is straight forward to implement adding features to the GUI (such as multiple turtles, impediments for the turtle) require more extensive modification that simply interpreting a command and translating it to directions on the back end.

We have designed our project so that all of the methods we implement in the back end during the first sprint are closed (including language capabilities and existing methods). We have designed our project so that new movement can be implemented by adding methods rather than modifying existing ones. Since the GUI will always have the capability to be modified to accommodate new features or sprint requirements we plan on the GUI being open.

###Overview (Bojia):
For our Slogo program, we will be using the Model-Viewer model, foregoing the controller. The back-end (Model) will have the "Model", "Parser", and "Commands" classes with subclasses extending Commands. The front-end (Viewer) will have the "Main", "Manager", "View", and "Turtle" classes. "Manager" and "Model" will act as points of contact, meaning communications between the front and back end will occur only between those two objects. 

The Manager object contains a method that accepts the user input, methods for all the simple operations on the turtle, and instances of all the views and turtles. Each View object contains the map that the turtle(s) move on, command text box, command history, and any other objects for users to interface with the IDE. Each Turtle object contains information about the turtle such as location, direction, and any other state details necessary to satisfy the design requirements. The Model object has a method for accepting the user input string from Manager and a map to translate from commands as strings to methods that are needed to be called in Manager to satisfy each command. The Parser contains all the helper methods needed to be able to intake a sequence of commands, call the appropriate methods in Commands objects, and return a list of return values. It also needs a map that maps commands to specific methods in the Commands (sub)classes. Finally, each Commands class (or subclass) contains a method (and helper methods if needed) for each command that it needs to support. These methods will perform the operations requested by their corresponding commands (e.g. arithmetic, telling model to call specific methods in Manager, processing loops, learning new commands).

The program starts in Main, which creates instances of Manager and Model and passes each object the instance of the other. Manager creates the first instances of View and Turtle and adds them to the user interface. After the user enters a command into the command text field, that string is passed to Manager, which calls on the back-end to process the command by passing the string to Model. Model accepts the user input string from Manager, splits it, places all the elements in a stack, and passes the stack to a Parser. The Parser pops the first element off the stack, looks up the method corresponding to that command and calls it while also passing the stack and model object. The method performs the operations needed to satisfy that command and returns a value to the parser. The parser then returns that value to Model, which then returns that value to the Manager.

###User Interface:
The User Interface will consist of 5 primary components: toolbar, display, control panel, command line, and command history.
 
Toolbar: Users will interact with the toolbar to access a help page as well as change the language. Changing the language will change the properties file accessed in the help tab, namely helping to redefine the html command reference in a different language.
 
Command Line: Users will then input SLOGO commands into the command line, pressing enter to execute. Invalid commands will not be executed in the display. Error checking will show up in the command history panel below the erroneous command.
 
Command History: Past commands will be displayed in the command history panel. These past commands can also be executed again by clicking them. Additionally, current variables will also be displayed in the command history panel.
 
The control panel can also control the turtle, specifying fields such as pen color, line width, turtle image, and certain preset commands. 

###Design Details:
Main: This class will simply initialize the manager class
Manager: the manager class is the primary point of contact between the front end and the back end. This class is responsible for communicating with the parsing elements of the back end whenever commands are executed on the front end. In doing so, the manager necessarily contains references to the view and model. But the manager class is not just a middle communicator between view and model, it can also be extended in the sense that it can actively create new instances of view that it can manage as well.

View: This view will contain several hboxes that represent the toolbar, display, command line, command history, and control panel. This will contain references to the manager which will parse the commands that are entered in the panel.
Model: Keeps track of currently defined environment, in terms of newly defined variables and functions. If need be, it could be extended to keep track of turtles as well. Provides access to this information to the view so that it can display it. It is the main conduit of information between commands and the View, and so it also contains a map of strings to functions in the view, which can be accessed by commands in order to make changes in the view. 

Parser: Takes a stack of tokens and recursively parses through each high-level command, calling and getting the return value of the run method for the corresponding Command object and returns a list of return values to be printed. If an invalid function is specified in the token, an invalidCommandException will be thrown. This class will be largely closed to modification.
Commands:  Defines a class of objects that are used to specify a command’s name, number of parameters and their types. Each command object processes the stack of commands until either the stack is empty or it has filled up all its parameter fields. It will throw an invalidParameterException if it runs out of parameters in stack or the parameters are of invalid type. New command subclasses can be added here, and so provides flexibility for extension to added commands in the future.

###API Example Code (Janan)
The View class contains a command line text box that will call the model's public processCode method on the "fd 50" string contained in the box once the user hits enter. The model will take the string and break it down into a stack of forward and 50. It then runs the Parser class's parseCommand method on the stack. It will then call the runCommand method on the forward Command. The forward Command recognizes that it has the single argument, which is all it needs and so it passes a string representing the forward 50 command as an argument to the executeCommand method in model. The model looks up the forward command in a Map and sees that it corresponds to moveTurtle method in the Manager class in the view and so it calls that method with 50 as an argument in the view. The moveTurtle method moves the turtle forward 50 units in the JavaFX screen draws a trail behind it.

###Design Considerations:

We had to consider how exactly interfacing between the GUI elements and the parsing elements in the project would work. Originally, we thought that the view would simply call parsing methods on the parser. But, in order to more clearly define the responsibilities of the view, we decided that it would be better to have a controller-class that will contain one or more views as well as handle the communications between the view and model. This has the benefit of reducing the clutter of the view to only contain the GUI elements and defining a single class that exclusively interfaces with the models.


###Team Responsibilities

Emre and Michael: Front End: View, Manager, Turtle

####Back End: 
Bojia - Model, Commands
Janan- Parser