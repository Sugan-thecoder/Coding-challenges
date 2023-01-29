package com.simulation.robot.domain;

import com.simulation.robot.constants.MovingDirection;
import com.simulation.robot.exception.RobotExceptionHandler;
import lombok.Getter;
import lombok.Setter;

public class RobotMovement {

    @Setter
    @Getter
    public int xAxis;
    @Setter
    @Getter
    public int yAxis;

    @Setter
    @Getter
    public String status;
    public MovingDirection currentPosition;
    @Setter
    @Getter
    public int robotId;

    public RobotMovement(final int xAxis, final int yAxis, final MovingDirection currentPosition) {
        this.currentPosition = currentPosition;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public RobotMovement(final int xAxis, final int yAxis, final MovingDirection currentPosition, int robotId, String status) {
        this.currentPosition = currentPosition;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.robotId = robotId;
        this.status = status;
    }

    public MovingDirection getCurrentDirection() throws RobotExceptionHandler {
        if (null == this.currentPosition) {
            throw new RobotExceptionHandler("Robot is not active!");
        }
        return currentPosition;
    }

    public void setCurrentDirection(MovingDirection currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void newCoordinates(final int x, final int y) {
        this.xAxis += x;
        this.yAxis += y;
    }


}