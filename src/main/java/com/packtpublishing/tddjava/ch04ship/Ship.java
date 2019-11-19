package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    private Location location;
    private Planet planet;

    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public Location getLocation() {
        return location;
    }

    public Planet getPlanet() {
        return planet;
    }

    public boolean moveForward() {
        return location.forward(planet.getMax(), planet.getObstacles());
    }

    public boolean moveBackward() {
        return location.backward(planet.getMax(), planet.getObstacles());
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public String receiveCommands(String commands) {
        StringBuilder sb = new StringBuilder();
        for (char command : commands.toCharArray()) {
            boolean rc = true;
            switch (command) {
                case 'f':
                    rc = moveForward();
                    break;
                case 'b':
                    rc = moveBackward();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
                default:
                    throw new RuntimeException("Error command: " + command);
            }
            sb.append(rc ? 'O' : 'X');
        }
        return sb.toString();
    }
}
