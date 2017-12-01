package botX;

import battlecode.common.*;
import scala.tools.nsc.doc.model.Public;

public class Lumberjack extends Robot {
    @SuppressWarnings("InfiniteLoopStatement")

    public void OnUpdate() throws GameActionException {
        System.out.println("I'm a lumberjack!");
        Team enemy = robotController.getTeam().opponent();

        // The code you want your robot to perform every round should be in this loop
        while (true) {
            // Try/catch blocks stop unhandled exceptions, which cause your robot to explode
            try {
            if (robotController.canShake()){
                //then Shake
                robotController.shake(spawnLocation);
            }
            else {tryMove(randomDirection());}


                // See if there are any enemy robots within striking range (distance 1 from lumberjack's radius)
                RobotInfo[] robots = robotController.senseNearbyRobots(RobotType.LUMBERJACK.bodyRadius+ GameConstants.LUMBERJACK_STRIKE_RADIUS, enemy);

                if(robots.length > 0 && !robotController.hasAttacked()) {
                    // Use strike() to hit all nearby robots!
                    robotController.strike();
                } else {
                    // No close robots, so search for robots within sight radius
                    robots = robotController.senseNearbyRobots(-1,enemy);

                    // If there is a robot, move towards it
                    if(robots.length > 0) {
                        MapLocation myLocation = robotController.getLocation();
                        MapLocation enemyLocation = robots[0].getLocation();
                        Direction toEnemy = myLocation.directionTo(enemyLocation);

                        tryMove(toEnemy);
                    } else {
                        // Move Randomly
                        tryMove(randomDirection());
                    }
                }



            } catch (Exception e) {
                System.out.println("Lumberjack Exception");
                e.printStackTrace();
            }
            // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
            Clock.yield();
        }
    }

}
