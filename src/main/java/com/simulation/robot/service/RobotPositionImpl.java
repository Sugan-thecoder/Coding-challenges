package com.simulation.robot.service;

import com.simulation.robot.constants.Instruction;
import com.simulation.robot.constants.MovingDirection;
import com.simulation.robot.domain.Robot;
import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.mapper.MappingRobot;

import java.util.ArrayList;

import static com.simulation.robot.constants.Constants.*;

public class RobotPositionImpl implements RobotPosition {
    CheckBoundaryImpl checkBoundaryImpl;
    UpdateRobotPosition updateRobotPosition;
    RobotMovement robotPosition = null;
    MappingRobot mappingRobot = new MappingRobot();
    ArrayList<Robot> robotArrayList = new ArrayList<>();
    int roboCount = 1;

    public RobotPositionImpl(CheckBoundaryImpl checkBoundaryImpl, UpdateRobotPosition updateRobotPosition) {
        this.checkBoundaryImpl = checkBoundaryImpl;
        this.updateRobotPosition = updateRobotPosition;
    }

    public RobotPositionImpl() {

    }

    @Override
    public void validateInstruction(final String instruction) throws RobotExceptionHandler {
        try {
            Instruction.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            throw new RobotExceptionHandler(UNRECOGNIZED_INSTRUCTION);
        }
    }

    @Override
    public void setRobotPosition(final RobotMovement nextPosition) throws RobotExceptionHandler {

        if (!checkBoundaryImpl.isRobotInsideBoundary(nextPosition)) {
            throw new RobotExceptionHandler(INVALID_COORDINATES);
        }

        updateRobotPosition.move(nextPosition);
    }

    @Override
    public RobotMovement getRobotPosition(UserInput userInput) throws RobotExceptionHandler {
        int xAxis = 0;
        int yAxis = 0;
        if (userInput.getInstruction() == Instruction.PLACE) {
            try {
                xAxis = userInput.getTableWidth();
                yAxis = userInput.getTableHeight();
                MovingDirection direction = userInput.getMovingDirection();
                robotPosition = new RobotMovement(xAxis, yAxis, direction);
            } catch (Exception e) {
                throw new RobotExceptionHandler(UNRECOGNIZED_INSTRUCTION);
            }
        }
        return robotPosition;
    }

    /**
     * Places the Robot on the table after validating the instructions from user
     *
     * @param userInput
     * @return
     * @throws RobotExceptionHandler
     */
    public Boolean placeRobot(UserInput userInput) throws RobotExceptionHandler {
        RobotMovement robotMovement = getRobotPosition(userInput);
        if (robotArrayList.size() != 0) {
            robotPosition.setStatus(INACTIVE);
        } else {
            robotPosition.setStatus(ACTIVE);
        }
        robotPosition.setRobotId(roboCount);
        robotArrayList = mappingRobot.addRobotDetails(robotPosition, robotArrayList);
        if (!checkBoundaryImpl.isRobotInsideBoundary(robotPosition)) {
            throw new RobotExceptionHandler(INVALID_COORDINATES);
        }
        updateRobotPosition.setNewPosition(robotPosition);
        roboCount++;
        return true;
    }

    @Override
    public void findActiveRobot() {
        mappingRobot.getActiveRobot(robotPosition, robotArrayList);
    }

    public RobotMovement getNextPosition() throws RobotExceptionHandler {
        findActiveRobot();
        RobotMovement nextPosition = new RobotMovement(updateRobotPosition.getCurrentPosition().getXAxis(), updateRobotPosition.getCurrentPosition().getYAxis(), updateRobotPosition.getCurrentPosition().getCurrentDirection(), updateRobotPosition.getCurrentPosition().getRobotId(), updateRobotPosition.getCurrentPosition().getStatus());
        switch (updateRobotPosition.getCurrentPosition().getCurrentDirection()) {
            case NORTH:
                nextPosition.newCoordinates(0, 1);
                break;
            case EAST:
                nextPosition.newCoordinates(1, 0);
                break;
            case SOUTH:
                nextPosition.newCoordinates(0, -1);
                break;
            case WEST:
                nextPosition.newCoordinates(-1, 0);
                break;
        }

        return nextPosition;
    }

    @Override
    public Boolean updateRobotPosition(final RobotMovement robotPosition) throws RobotExceptionHandler {

        if (checkBoundaryImpl.isRobotInsideBoundary(robotPosition)) {
            updateRobotPosition.movePosition(robotPosition);
            mappingRobot.updateRobotDetails(robotPosition, robotArrayList);
        }
        return true;
    }

    @Override
    public String getCurrentPosition() throws RobotExceptionHandler {
        return updateRobotPosition.reportPosition(robotArrayList);
    }

    /**
     * Moves the Robot Left to the direction where it is facing when placed
     * or previously located
     *
     * @return
     * @throws RobotExceptionHandler
     */
    @Override
    public Boolean moveRobotLeft() throws RobotExceptionHandler {
        findActiveRobot();
        updateRobotPosition.left();
        robotPosition.setCurrentDirection(updateRobotPosition.getCurrentPosition().getCurrentDirection());
        mappingRobot.updateRobotDirection(robotPosition, robotArrayList);
        return true;

    }

    /**
     * Moves the Robot Right to the direction where it is facing when placed
     * or previously located
     *
     * @return
     * @throws RobotExceptionHandler
     */
    @Override
    public Boolean moveRobotRight() throws RobotExceptionHandler {
        findActiveRobot();
        updateRobotPosition.right();
        robotPosition.setCurrentDirection(updateRobotPosition.getCurrentPosition().getCurrentDirection());
        mappingRobot.updateRobotDirection(robotPosition, robotArrayList);
        return true;
    }

    /**
     * If there are multiple Robots available then activate
     * the robot which the user passes in the instruction Ex. ROBOT <#NUMBER>
     *
     * @return
     * @throws RobotExceptionHandler
     */
    @Override
    public Boolean activateRobot(UserInput userInput) throws RobotExceptionHandler {
        int findRobortId = userInput.getRobotId();
        robotArrayList = mappingRobot.activateRobot(findRobortId, robotArrayList);
        mappingRobot.setActiveRobot(robotPosition, robotArrayList,updateRobotPosition);
        return true;
    }
}
