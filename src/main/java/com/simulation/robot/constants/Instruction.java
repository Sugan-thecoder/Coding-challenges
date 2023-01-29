package com.simulation.robot.constants;

/**
 * Instructions to the robot
 * PLACE: Position the toy robot with the provided (X,Y) coordinates and F-Facing
 * MOVE: Moves by 1 position in the specified direction
 * RIGHT/LEFT: Rotates robot by left/right WRT the direction facing
 * REPORT:Returns current position(X,Y,F) of the robot
 * ROBOT: Allows to select the robot and proceed with instructions
 * EXIT: Exits the program
 */
public enum Instruction {
    PLACE, MOVE, RIGHT, LEFT, REPORT, ROBOT, EXIT;
}
