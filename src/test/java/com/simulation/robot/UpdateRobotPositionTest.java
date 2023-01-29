package com.simulation.robot;

import com.simulation.robot.constants.Instruction;
import com.simulation.robot.constants.MovingDirection;
import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.domain.UserInput;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.service.*;
import org.junit.Assert;
import org.junit.Test;

public class UpdateRobotPositionTest {

    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;
   @Test
    public void testRobotMoveCommand() throws RobotExceptionHandler {
       UserInput userInput=new UserInput(Instruction.PLACE,1,3,MovingDirection.WEST);

       RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
               new UpdateRobotPosition());
       robotPosition.placeRobot(userInput);
       robotPosition.updateRobotPosition(new RobotMovement(2,2, MovingDirection.WEST,1, "ACTIVE"));
       Assert.assertEquals("ROBOT  1,ACTIVE,2,2,WEST\n", robotPosition.getCurrentPosition());
   }

   @Test
    public void testRobotLeftCommand() throws RobotExceptionHandler {
       UserInput userInput=new UserInput(Instruction.PLACE,1,3,MovingDirection.WEST);
       RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
               new UpdateRobotPosition());
       robotPosition.placeRobot(userInput);
       robotPosition.updateRobotPosition(new RobotMovement(2,2, MovingDirection.WEST,1, "ACTIVE"));
       robotPosition.moveRobotLeft();
        Assert.assertEquals("ROBOT  1,ACTIVE,2,2,SOUTH\n", robotPosition.getCurrentPosition());
    }

    @Test
    public void testRobotRightCommand() throws RobotExceptionHandler {
        UserInput userInput=new UserInput(Instruction.PLACE,1,3,MovingDirection.WEST);
        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        robotPosition.placeRobot(userInput);
        robotPosition.updateRobotPosition(new RobotMovement(2,2, MovingDirection.WEST,1, "ACTIVE"));
        robotPosition.moveRobotRight();
        Assert.assertEquals("ROBOT  1,ACTIVE,2,2,NORTH\n", robotPosition.getCurrentPosition());
    }
}
