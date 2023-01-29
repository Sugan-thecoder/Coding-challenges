package com.simulation.robot.service;

import com.simulation.robot.constants.Instruction;
import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.validator.InputValidator;

import java.util.Objects;

import static com.simulation.robot.constants.Constants.PLACE_THE_ROBOT;
import static com.simulation.robot.constants.Constants.UNRECOGNIZED_INSTRUCTION;


public class RobotInstruction {

    InputValidator inputValidator = new InputValidator();
    RobotPositionImpl robotPosition;

    public RobotInstruction(RobotPositionImpl robotPosition) {
        this.robotPosition = robotPosition;
    }

    /**
     * This method executes the instruction provided by user
     *
     * @param instrutionString
     * @return
     * @throws RobotExceptionHandler
     */
    public String instructionExecution(String instrutionString) throws RobotExceptionHandler {
        String resultingPosition = null;
        if (Objects.nonNull(instrutionString)) {
            UserInput userInput = inputValidator.evaluateInput(instrutionString);
            Instruction instruction = userInput.getInstruction();
            robotPosition.validateInstruction(instruction.toString());

            try {
                switch (instruction) {
                    case PLACE:
                        resultingPosition = String.valueOf(robotPosition.placeRobot(userInput));
                        break;

                    case MOVE:
                        RobotMovement nextMovement = robotPosition.getNextPosition();
                        resultingPosition = String.valueOf(robotPosition.updateRobotPosition(nextMovement));
                        break;

                    case LEFT:
                        resultingPosition = String.valueOf(robotPosition.moveRobotLeft());
                        break;

                    case RIGHT:
                        resultingPosition = String.valueOf(robotPosition.moveRobotRight());
                        break;

                    case REPORT:
                        resultingPosition = robotPosition.getCurrentPosition();
                        break;

                    case ROBOT:
                        resultingPosition = String.valueOf(robotPosition.activateRobot(userInput));
                        break;

                    default:
                        throw new RobotExceptionHandler(UNRECOGNIZED_INSTRUCTION);
                }
            } catch (Exception exception) {

                throw new RobotExceptionHandler(exception.getMessage());
            }
            return resultingPosition;
        }
        return null;
    }
}
