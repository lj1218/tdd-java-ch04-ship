package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;

    @BeforeMethod
    public void beforeTest() {
        location = new Location(new Point(21, 13), Direction.NORTH);
        ship = new Ship(location);
    }

    public void whenInstantiatedThenLocationIsSet() {
//        Location location = new Location(new Point(21, 13), Direction.NORTH);
//        Ship ship = new Ship(location);
        assertEquals(ship.getLocation(), location);
    }

//    public void givenNorthWhenMoveForwardThenYDecreases() {
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getY(), 12);
//    }
//
//    public void givenEastWhenMoveForwardThenXIncreases() {
//        ship.getLocation().setDirection(Direction.EAST);
//        ship.moveForward();
//        assertEquals(ship.getLocation().getPoint().getX(), 22);
//    }

    public void whenMoveForwardThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

}
