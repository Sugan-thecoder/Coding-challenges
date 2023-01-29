package com.simulation.robot.service;

import com.simulation.robot.constants.MovingDirection;
import com.simulation.robot.domain.Robot;
import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.exception.RobotExceptionHandler;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;

import static com.simulation.robot.constants.Constants.PLACE_THE_ROBOT;

@NoArgsConstructor
public class UpdateRobotPosition {

    public static final String ROBOT_UNAVAILABLE = "Robot is not available!";
    private RobotMovement currentPosition;

    public RobotMovement getCurrentPosition() throws RobotExceptionHandler {

        if (null == this.currentPosition) {
            throw new RobotExceptionHandler(ROBOT_UNAVAILABLE);
        }
        return currentPosition;
    }

    public void setNewPosition(final RobotMovement position) {
        this.currentPosition = position;
    }

    public void movePosition(final RobotMovement position) {
        this.currentPosition = position;
    }

    public void move(final RobotMovement nextPosition) {
        this.currentPosition = nextPosition;
    }

    /**
     * This instruction will rotate the robot to the Left from the facing direction
     *
     * @throws RobotExceptionHandler
     */
    public void left() throws RobotExceptionHandler {
        if (null == this.currentPosition) {
            throw new RobotExceptionHandler(ROBOT_UNAVAILABLE);
        }
        this.currentPosition.setCurrentDirection(MovingDirection.valueOf(this.currentPosition.getCurrentDirection().getLeft()));
    }

    /**
     * This instruction will rotate the robot to the Right from the facing direction
     *
     * @throws RobotExceptionHandler
     */
    public void right() throws RobotExceptionHandler {
        if (null == this.currentPosition) {
            throw new RobotExceptionHandler(ROBOT_UNAVAILABLE);
        }
        this.currentPosition.setCurrentDirection(MovingDirection.valueOf(this.currentPosition.getCurrentDirection().getRight()));
    }

    /**
     * This instruction will display the Robot current place on the table
     * along the Robot details
     * ROBOT  1,INACTIVE,4,0,EAST
     *
     * @return
     * @throws RobotExceptionHandler
     */
    public String reportPosition(ArrayList<Robot> robotArrayList) {
        StringBuilder report = new StringBuilder();
        for (Robot robot : robotArrayList) {
            report.append("ROBOT ").append(" ").append(robot.getRobotId()).append(",").append(robot.getStatus()).append(",").append(robot.getXAxis()).append(",").append(robot.getYAxis()).append(",").append(robot.getCurrentPosition()).append("\n");
        }
        String reportRobot=report.toString();
        if(!reportRobot.isEmpty())
        {
            return reportRobot;
        }
        else
        {
            return PLACE_THE_ROBOT;
        }
    }
}
