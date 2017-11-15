package botX;

import battlecode.common.*;

import java.util.*;

import static botX.Robot.randomDirection;
import static botX.Robot.tryMove;


public class Soldier extends Robot

{
    @Override
    public void OnUpdate() {
        System.out.println("I'm an soldier!");
        enemy = robotController.getTeam().opponent();

        boolean settled = false;
        Direction soldierDir = null;

        while (true)

        {

            try {
                Direction direction = randomDirection();
                if (soldierDir == null) {
                    soldierDir = direction;
                }
                //If the soldier can move
                if (robotController.canMove(soldierDir)) {
                    robotController.move(soldierDir);
                }


                // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
                try {
                    MapLocation myLocation = robotController.getLocation();

                    // See if there are any nearby enemy robots
                    RobotInfo[] robots = robotController.senseNearbyRobots(-1, enemy);

                    // If there are some...
                    if (robots.length > 0) {
                        // And we have enough bullets, and haven't attacked yet this turn...
                        if (robotController.canFireSingleShot()) {
                            // ...Then fire a bullet in the direction of the enemy.
                            robotController.fireSingleShot(robotController.getLocation().directionTo(robots[0].location));
                        }
                    }

                    // Move randomly
                    tryMove(randomDirection());

                    // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                    Clock.yield();

                } catch (Exception e) {
                    System.out.println("Soldier Exception");
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Soldier Exception");
                e.printStackTrace();
            }
        }
    }
}