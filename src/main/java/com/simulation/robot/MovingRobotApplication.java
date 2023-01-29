package com.simulation.robot;

import com.simulation.robot.constants.Constants;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.service.CheckBoundaryImpl;
import com.simulation.robot.service.RobotInstruction;
import com.simulation.robot.service.RobotPositionImpl;
import com.simulation.robot.service.UpdateRobotPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import static com.simulation.robot.constants.Constants.*;

public class MovingRobotApplication {
    public static String width;
    public static String height;

    static {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");) {
            Properties properties = new Properties();
            properties.load(inputStream);
            width = properties.getProperty("table.width");
            height = properties.getProperty("table.height");
        } catch (Exception e) {
            throw new RuntimeException(Constants.PROPERTY_FILE_NOT_FOUND + "\n" + e);
        }
    }

    public static void main(String[] args) throws IOException, RobotExceptionHandler {

        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        //Object Initialization for all the classes
        CheckBoundaryImpl checkBoundaryImpl = new CheckBoundaryImpl(Integer.valueOf(width), Integer.valueOf(height));
        UpdateRobotPosition updateRobotPosition = new UpdateRobotPosition();
        RobotPositionImpl robotPosition = new RobotPositionImpl(checkBoundaryImpl, updateRobotPosition);
        RobotInstruction robotInstruction = new RobotInstruction(robotPosition);
        // RobotPositionImpl robotPosition=new RobotPositionImpl(checkBoundaryImpl,updateRobotPosition);

        System.out.println("*******  Welcome to ROBO world ********");
        System.out.println("My Instructions are ");
        System.out.println("\t1 - PLACE X,Y,FACING_DIRECTION (NORTH|SOUTH|EAST|WEST) (To place me on table).");
        System.out.println("\t2 - MOVE, LEFT, RIGHT (To move me on the table)");
        System.out.println("\t3 - REPORT (To get my current location)");
        System.out.println("\t4 - EXIT (To exit the program)");
        System.out.println("Hello From Robo!");

        boolean isRobotMoving = true;
        while (isRobotMoving) {
            String userInput = String.valueOf(userInputReader.readLine());
            if (Constants.INSTRUCTION_EXIT.equalsIgnoreCase(userInput)) {
                isRobotMoving = false;
            } else {
                try {
                    String isInstructionSucess = robotInstruction.instructionExecution(userInput);
                    if (isInstructionSucess.equals("true")) {
                        System.out.println(ROBO_ACKNOWLEDGEMENT);
                    } else if (isInstructionSucess.equals("false")) {

                        System.out.println(INVALID_COORDINATES);
                    } else {

                        System.out.println(OUTPUT + ": " + isInstructionSucess);
                    }

                } catch (RobotExceptionHandler robotExceptionHandler) {
                    System.out.println(robotExceptionHandler.getMessage());
                }

            }
        }
    }

}
