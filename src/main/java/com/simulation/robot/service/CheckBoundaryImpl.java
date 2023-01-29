package com.simulation.robot.service;

import com.simulation.robot.domain.RobotMovement;

public class CheckBoundaryImpl implements CheckBoundary {
    int tableWidth;
    int tableHeight;

    public CheckBoundaryImpl(int width, int depth) {
        this.tableWidth = width;
        this.tableHeight = depth;
    }

    /**
     * Overridden interface method to validate whether or not the robot is within the boundary
     *
     * @param position
     * @return boolean
     */
    @Override
    public boolean isRobotInsideBoundary(RobotMovement position) {
        return (position.getXAxis() <= this.tableWidth && position.getXAxis() >= 0) && (position.getYAxis() <= this.tableHeight && position.getYAxis() >= 0);
    }

}
