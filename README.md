# Robot Challenge
The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.
There are no other obstructions on the table surface.
The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement that would result in the robot falling from the table must be prevented, however further valid movement commands must still be allowed.

The application can read in commands of the following form

PLACE X,Y,F | MOVE | LEFT | RIGHT | REPORT

## Description

    
    PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST. The origin (0,0)
    can be considered to be the SOUTH WEST most corner.

    It is required that the first command to the robot is a PLACE command, after that, any sequence of commands may 
    be issued, in any order, including another PLACE command. The
    application should discard all commands in the sequence until a valid PLACE command has been executed.
    MOVE will move the toy robot one unit forward in the direction it is currently facing.
    LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.
    REPORT will announce the X,Y and F of the robot. This can be in any form, but standard output is sufficient.
    A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.
    Input can be from a file, or from standard input, as the developer chooses.
    Provide test data to exercise the application.
    It is not required to provide any graphical output showing the movement of the toy robot.
    The application should handle error states appropriately and be robust to user input.

## Constraints
  The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot. Any
  move that would cause the robot to fall must be ignored.

## Example Input and Output:
a)

    PLACE 0,0,NORTH
    MOVE
    REPORT
    Output: ROBOT 1,ACTIVE,0,1,NORTH


b)

    PLACE 0,0,NORTH
    LEFT
    REPORT
    Output: ROBOT 1,ACTIVE,0,0,WEST
c)

    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT
    Output: ROBOT 1,ACTIVE,3,3,NORTH

## Usage Instructions
Compiling the application
- `mvn clean compile` Clean up the artifacts clearing the target folder

Testing the application
- `mvn test` Runs the unit tests of the program

Build and package
- `mvn clean package` clean up all the generated artifacts and package a fresh copy of the jar file

Executing Application from Command Line
- java -jar project_robot-1.0-SNAPSHOT.jar

## Extensions

Multiple robots will operate on the table

The existing system above should continue to work as-is. REPORT will also now report on how many robots are present and which robot is active (see the ROBOT command later).

PLACE will add a new robot to the table with incrementing number identifier, i.e. the first placed robot will be 'Robot 1', then the next placed robot will be 'Robot 2', then 'Robot 3', etc.

A ROBOT <number> command will make the robot identified by active i.e. subsequent commands will affect that robot's position/direction. Any command that affects position/direction (e.g. MOVE, LEFT, RIGHT...) will affect only the active robot.

By default the first robot placed will become the active robot.

## Example Input and Output:
a)

    PLACE 0,0,NORTH
    MOVE
    PLACE 1,2,EAST
    
    REPORT
    Output: ROBOT 1,ACTIVE,0,1,NORTH
    ROBOT 2,INACTIVE,1,2,EAST
b)

    PLACE 0,0,NORTH
    LEFT
    PLACE 1,2,EAST   
    ROBOT 2
    MOVE 
    REPORT
    Output: ROBOT 1,INACTIVE,0,0,WEST    
    ROBOT 2,ACTIVE,2,2,EAST    