# slogo
A development environment that helps users write SLogo programs.

##### To-Do's
* Incorporate Display.java with GUI (to visualize) - Michael
* debug Display.java - Emre
* binding - Michael
* help menu - Michael/Emre

##### Public Methods for Turtle Movement API (frontend, in Display.java)

*NOTE: All methods except the constructor for Display take x and y coordinates with the origin (0,0) at the center of the screen.*
* Display(int xDimension, int yDimension,Canvas myCanvas)
* clearScreen() (not yet completed)
* setPen(Turtle t, boolean leaveTrail) (used for Pen Up and Pen Down)
* showTurtle(Turtle t, boolean showTurtle) (not yet completed) (used for Show and Hide Turtle)
* rotateLeft(Turtle t,double degrees)
* rotateRight(Turtle t,double degrees)
* setHeading(Turtle t, double degrees)
* setXY(Turtle t, int x, int y)
* moveForward(Turtle t, int pixels)
* moveBackward(Turtle t, int pixels)
* moveTowards(Turtle t,int x, int y)
* moveHome(Turtle t) (heading not yet implemented)

 
