package com.simulation.robot.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * All Args constructor is used to initialize the parameterized constructor for the enum class
 *
 * @Getter - Getter method to get left and right value
 */
@AllArgsConstructor
public enum MovingDirection {
    NORTH("WEST", "EAST"),
    EAST("NORTH", "SOUTH"),
    SOUTH("EAST", "WEST"),
    WEST("SOUTH", "NORTH");
    @Getter
    private String left;
    @Getter
    private String right;
}
