package com.simulation.robot.service;

import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;

public interface RobotPosition {
    public void validateInstruction(final String instruction) throws RobotExceptionHandler;

    public void setRobotPosition(final RobotMovement nextPosition) throws RobotExceptionHandler;

    public RobotMovement getRobotPosition(UserInput userInput) throws RobotExceptionHandler;

    public Boolean placeRobot(UserInput userInput) throws RobotExceptionHandler;

    public void findActiveRobot();

    public RobotMovement getNextPosition() throws RobotExceptionHandler;

    public Boolean updateRobotPosition(final RobotMovement robotPosition) throws RobotExceptionHandler;

    public String getCurrentPosition() throws RobotExceptionHandler;

    public Boolean moveRobotLeft() throws RobotExceptionHandler;

    public Boolean moveRobotRight() throws RobotExceptionHandler;

    public Boolean activateRobot(UserInput userInput) throws RobotExceptionHandler;


}
