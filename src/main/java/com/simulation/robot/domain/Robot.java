package com.simulation.robot.domain;

import com.simulation.robot.constants.MovingDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Robot {
    public int xAxis;
    public int yAxis;
    public String status;
    public MovingDirection currentPosition;
    public int robotId;

}
