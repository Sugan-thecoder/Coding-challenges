package com.simulation.robot;

import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.service.CheckBoundaryImpl;
import com.simulation.robot.service.RobotInstruction;
import com.simulation.robot.service.RobotPositionImpl;
import com.simulation.robot.service.UpdateRobotPosition;
import org.junit.Assert;
import org.junit.Test;

public class RobotInstructionsTest {

    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;


    @Test
    public void testMoveCommand() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);

        instruction.instructionExecution("PLACE 0,2,NORTH");
        instruction.instructionExecution("MOVE");

        Assert.assertEquals("ROBOT  1,ACTIVE,0,3,NORTH" + "\n", instruction.instructionExecution("REPORT"));
    }

    @Test
    public void testBoundaryLimit() throws RobotExceptionHandler {
        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);

        instruction.instructionExecution("PLACE 0,4,NORTH");
        instruction.instructionExecution("MOVE");

        Assert.assertEquals("ROBOT  1,ACTIVE,0,4,NORTH" + "\n", instruction.instructionExecution("REPORT"));

    }

    @Test
    public void testLeftCommand() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);

        instruction.instructionExecution("PLACE 0,4,NORTH");
        instruction.instructionExecution("LEFT");

        Assert.assertEquals("ROBOT  1,ACTIVE,0,4,WEST" +"\n", instruction.instructionExecution("REPORT"));
        instruction.instructionExecution("MOVE");
        Assert.assertEquals("ROBOT  1,ACTIVE,0,4,WEST\n", instruction.instructionExecution("REPORT"));

    }

    @Test
    public void testRightCommand() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 4,0,EAST");
        instruction.instructionExecution("RIGHT");

        Assert.assertEquals("ROBOT  1,ACTIVE,4,0,SOUTH\n", instruction.instructionExecution("REPORT"));

        instruction.instructionExecution("MOVE");
        Assert.assertEquals("ROBOT  1,ACTIVE,4,0,SOUTH\n", instruction.instructionExecution("REPORT"));
    }

    @Test
    public void testActiveAndInactiveRobot() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 4,0,EAST");
        instruction.instructionExecution("PLACE 3,2,NORTH");

        Assert.assertEquals("ROBOT  1,ACTIVE,4,0,EAST\nROBOT  2,INACTIVE,3,2,NORTH\n", instruction.instructionExecution("REPORT"));

    }

    @Test
    public void testRobotCommand() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 4,0,EAST");
        instruction.instructionExecution("PLACE 3,2,NORTH");
        instruction.instructionExecution("ROBOT 2");

        Assert.assertEquals("ROBOT  1,INACTIVE,4,0,EAST\nROBOT  2,ACTIVE,3,2,NORTH\n", instruction.instructionExecution("REPORT"));

    }

    @Test
    public void testMultiRobot() throws RobotExceptionHandler {

        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 4,0,EAST");
        instruction.instructionExecution("PLACE 3,2,NORTH");
        instruction.instructionExecution("ROBOT 2");
        instruction.instructionExecution("MOVE");

        Assert.assertEquals("ROBOT  1,INACTIVE,4,0,EAST\nROBOT  2,ACTIVE,3,3,NORTH\n", instruction.instructionExecution("REPORT"));

    }



    @Test (expected = RobotExceptionHandler.class)
    public void testInvalidPlacement() throws RobotExceptionHandler {
        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 5,0,EAST");

    }

    @Test (expected = RobotExceptionHandler.class)
    public void testInvalidCommand() throws RobotExceptionHandler {
        RobotPositionImpl robotPosition=new RobotPositionImpl(new CheckBoundaryImpl(WIDTH,HEIGHT),
                new UpdateRobotPosition());
        RobotInstruction instruction = new RobotInstruction(
                robotPosition);
        instruction.instructionExecution("PLACE 4,0,EAST");
        instruction.instructionExecution("ADFDF");

    }
}
