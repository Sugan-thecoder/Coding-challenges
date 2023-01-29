package com.simulation.robot.domain;

import com.simulation.robot.constants.Instruction;
import com.simulation.robot.constants.MovingDirection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInput {
    Instruction instruction;
    int tableWidth;
    int tableHeight;

    int robotId;

    MovingDirection movingDirection;

    int activateRobot;

    public UserInput(Instruction instruction) {
        this.instruction = instruction;
    }

    public UserInput(Instruction instruction, int robotId) {
        this.instruction = instruction;
        this.robotId = robotId;
    }

    public UserInput(Instruction instruction, int x, int y, MovingDirection direction) {
        this.instruction = instruction;
        this.tableWidth = x;
        this.tableHeight = y;
        this.movingDirection = direction;

    }

}
