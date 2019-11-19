package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Location location;
    private Planet planet;

    @BeforeMethod
    public void beforeTest() {
        location = new Location(new Point(21, 13), Direction.NORTH);
        Point max = new Point(50, 50);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(ship.getLocation(), location);
    }

    public void whenMoveForwardThenForward() {
        Location expected = location.copy();
        expected.forward(planet.getMax(), planet.getObstacles());
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = location.copy();
        expected.backward(planet.getMax(), planet.getObstacles());
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

    public void whenReceiveCommandsFThenForward() {
        Location expected = location.copy();
        expected.forward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location expected = location.copy();
        expected.backward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsLThenTurnLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsRThenTurnRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenAllAreExecuted() {
        Location expected = location.copy();
        expected.turnRight();
        expected.forward(planet.getMax(), planet.getObstacles());
        expected.turnLeft();
        expected.backward(planet.getMax(), planet.getObstacles());
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(ship.getPlanet(), planet);
    }

    public void givenDirectionEAndXEqualsMaxXWhenReceiveCommandsFThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(planet.getMax().getX());
        ship.receiveCommands("f");
        assertEquals(location.getX(), 1);
    }

    public void givenDirectionEAndXEquals1WhenReceiveCommandsBThenWrap() {
        location.setDirection(Direction.EAST);
        location.getPoint().setX(1);
        ship.receiveCommands("b");
        assertEquals(location.getX(), planet.getMax().getX());
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(location.getX() + 1, location.getY()));
        ship.getPlanet().setObstacles(obstacles);
        Location expected = location.copy();
        expected.turnRight();
        // Moving forward would encounter an obstacle
        // expected.forward(new Point(0, 0), new ArrayList<Point>());
        expected.turnLeft();
        expected.backward(new Point(0, 0), new ArrayList<>());
        ship.receiveCommands("rflb");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsThenOForOkAndXForObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(location.getX() + 1, location.getY()));
        ship.getPlanet().setObstacles(obstacles);
        String status = ship.receiveCommands("rflbrflfb");
        assertEquals(status, "OXOOOOOXO");
    }
}
