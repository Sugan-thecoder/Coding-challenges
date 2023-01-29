package com.simulation.robot.mapper;

import com.simulation.robot.domain.Robot;
import com.simulation.robot.exception.RobotExceptionHandler;
import com.simulation.robot.domain.RobotMovement;
import com.simulation.robot.service.UpdateRobotPosition;

import java.util.ArrayList;

/**
 * This class is used to maintain the all robot details, find active robot and maps the values while placing/moving
 * the robot
 */
public class MappingRobot extends Robot {
    public ArrayList<Robot> addRobotDetails(RobotMovement robotMovement, ArrayList<Robot> robotArrayList) throws RobotExceptionHandler {
        Robot currentRobot = new Robot();
        currentRobot.setRobotId(robotMovement.getRobotId());
        currentRobot.setXAxis(robotMovement.getXAxis());
        currentRobot.setYAxis(robotMovement.getYAxis());
        currentRobot.setStatus(robotMovement.getStatus());
        currentRobot.setCurrentPosition(robotMovement.getCurrentDirection());
        robotArrayList.add(currentRobot);
        return robotArrayList;
    }

    public void getActiveRobot(RobotMovement robotMovement, ArrayList<Robot> robotArrayList) {
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getStatus().equals("ACTIVE")) {
                robotMovement.setStatus(getRobot.getStatus());
                robotMovement.setRobotId(getRobot.getRobotId());
                robotMovement.setCurrentDirection(getRobot.getCurrentPosition());
                robotMovement.setXAxis(getRobot.getXAxis());
                robotMovement.setYAxis(getRobot.getYAxis());
            }
        }
    }

    public void setActiveRobot(RobotMovement robotMovement, ArrayList<Robot> robotArrayList, UpdateRobotPosition updateRobotPosition) {
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getStatus().equals("ACTIVE")) {
                robotMovement.setStatus(getRobot.getStatus());
                robotMovement.setRobotId(getRobot.getRobotId());
                robotMovement.setCurrentDirection(getRobot.getCurrentPosition());
                robotMovement.setXAxis(getRobot.getXAxis());
                robotMovement.setYAxis(getRobot.getYAxis());

                updateRobotPosition.setNewPosition(robotMovement);
            }
        }
    }

    public ArrayList<Robot> updateRobotDetails(RobotMovement robotMovement, ArrayList<Robot> robotArrayList) throws RobotExceptionHandler {

        int index = robotMovement.getRobotId();
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getRobotId() == index) {
                getRobot.setXAxis(robotMovement.getXAxis());
                getRobot.setCurrentPosition(robotMovement.getCurrentDirection());
                getRobot.setYAxis(robotMovement.getYAxis());
                robotArrayList.get(index - 1).setYAxis(getRobot.getYAxis());
                robotArrayList.get(index - 1).setXAxis(getRobot.getXAxis());
                robotArrayList.get(index - 1).setCurrentPosition(getRobot.getCurrentPosition());
            }
        }
        return robotArrayList;
    }

    public ArrayList<Robot> updateRobotDirection(RobotMovement robotMovement, ArrayList<Robot> robotArrayList) throws RobotExceptionHandler {

        int index = robotMovement.getRobotId();
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getRobotId() == index) {
                robotArrayList.get(index - 1).setCurrentPosition(robotMovement.getCurrentDirection());
            }
        }
        return robotArrayList;
    }

    public ArrayList<Robot> activateRobot(int findRobortId, ArrayList<Robot> robotArrayList) {
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getStatus().equals("ACTIVE")) {
                if (findRobortId != getRobot.getRobotId()) {
                    robotArrayList.get(getRobot.getRobotId() - 1).setStatus("INACTIVE");
                    robotArrayList.get(findRobortId - 1).setStatus("ACTIVE");
                }

            }
        }
        return robotArrayList;
    }

    public String IsAnyRobotAlreadyPresent(int xAxis,int yAxis,ArrayList<Robot> robotArrayList) {
        String isAlreadyExists="false";
        for (Robot getRobot : robotArrayList) {
            if (getRobot.getXAxis() == xAxis && getRobot.getYAxis() == yAxis) {
                isAlreadyExists="true";
                break;
            }
        }
         return isAlreadyExists;
    }

}
