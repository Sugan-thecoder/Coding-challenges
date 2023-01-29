package com.simulation.robot.enums;

import com.simulation.robot.constants.MovingDirection;
import org.junit.Assert;
import org.junit.Test;

public class MovingDirectionTest {

   @Test
    public void testDirection() {
       MovingDirection direction = MovingDirection.EAST;

       Assert.assertEquals(direction.getLeft(), MovingDirection.NORTH.name());
       Assert.assertEquals(direction.getRight(), MovingDirection.SOUTH.name());

       direction = MovingDirection.NORTH;

       Assert.assertEquals(direction.getLeft(), MovingDirection.WEST.name());
       Assert.assertEquals(direction.getRight(), MovingDirection.EAST.name());

       direction = MovingDirection.WEST;

       Assert.assertEquals(direction.getLeft(), MovingDirection.SOUTH.name());
       Assert.assertEquals(direction.getRight(), MovingDirection.NORTH.name());

       direction = MovingDirection.SOUTH;

       Assert.assertEquals(direction.getLeft(), MovingDirection.EAST.name());
       Assert.assertEquals(direction.getRight(), MovingDirection.WEST.name());
   }
}
