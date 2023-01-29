package com.simulation.robot.validator;

import com.simulation.robot.constants.Constants;
import com.simulation.robot.constants.Instruction;
import com.simulation.robot.constants.MovingDirection;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;

import static com.simulation.robot.constants.Constants.UNRECOGNIZED_INSTRUCTION;

public class InputValidator {

    public UserInput evaluateInput(String inputString) throws RobotExceptionHandler {

        Instruction instruction;
        String[] args = inputString.split(" ");

        try {
            instruction = Instruction.valueOf(args[0]);
        } catch (Exception exception) {

            throw new RobotExceptionHandler(UNRECOGNIZED_INSTRUCTION);
        }

        if (instruction == Instruction.PLACE && args.length < 2) {

            throw new RobotExceptionHandler(Constants.INVALID_INSTRUCTION);
        }

        if (instruction == Instruction.ROBOT && args.length < 2) {

            throw new RobotExceptionHandler(Constants.INVALID_INSTRUCTION);
        }
        // validate PLACE params
        UserInput userInput = null;

        if (instruction.equals(Instruction.PLACE)) {
            String[] params;
            int x = 0;
            int y = 0;
            MovingDirection movingDirection = null;

            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                movingDirection = MovingDirection.valueOf(params[2]);
                userInput = new UserInput(instruction, x, y, movingDirection);

            } catch (Exception exception) {
                throw new RobotExceptionHandler(Constants.INVALID_INSTRUCTION);
            }
        } else {
            if (instruction.equals(Instruction.ROBOT)) {
                String param = args[1];
                userInput = new UserInput(instruction, Integer.parseInt(param));
            } else {
                userInput = new UserInput(instruction);
            }
        }
        return userInput;

    }
}