package com.simulation.robot.enums;

import com.simulation.robot.constants.Instruction;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.validator.InputValidator;
import org.junit.Assert;
import org.junit.Test;

public class InstructionTest {

    public  static InputValidator inputValidator = new InputValidator();
    @Test
  public void testDirection() throws RobotExceptionHandler {

        Instruction command = Instruction.PLACE;
        Assert.assertEquals(new UserInput(command).getInstruction(), inputValidator.evaluateInput("PLACE 1,2,NORTH").getInstruction());

        command = Instruction.MOVE;
        Assert.assertEquals(new UserInput(command).getInstruction(), inputValidator.evaluateInput("MOVE").getInstruction());

        command = Instruction.EXIT;
        Assert.assertEquals(new UserInput(command).getInstruction(), inputValidator.evaluateInput("EXIT").getInstruction());
    }

    @Test (expected = RobotExceptionHandler.class)
    public void testInvalidCommand() throws RobotExceptionHandler {
        inputValidator.evaluateInput("ABCD");
    }
}
