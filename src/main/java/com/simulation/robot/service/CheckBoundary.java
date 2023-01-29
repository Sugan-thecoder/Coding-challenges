package com.simulation.robot.service;

import com.simulation.robot.domain.RobotMovement;

public interface CheckBoundary {
    public boolean isRobotInsideBoundary(RobotMovement position);
}
