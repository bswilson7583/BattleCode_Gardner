package botX;


 import battlecode.common.*;

import java.util.*;


 import static botX.Robot.tryMove;


 public class Soldier extends Robot{
     public Soldier() {
         super();
     }
 @Override
public void OnUpdate() throws GameActionException {

     System.out.println("I'm an soldier!");
     enemy = robotController.getTeam().opponent();
     boolean settled = false;
     Direction soldierDir = null;

     while (true) {
         try {
             Direction combatZone = randomDirection();
             if (soldierDir == null){
                 soldierDir = combatZone;
         }
     // if the soldier can move
             if (robotController.canMove(combatZone)){
                 robotController.move(combatZone);
             }

            // See if there are any nearby enemy robots
           RobotInfo[] robots = robotController.senseNearbyRobots(-1, enemy);
           // If there are some...
             if (robots.length > 0) {
                 // And we have enough bullets, and haven't attacked yet this turn...
                 if (robotController.canFirePentadShot()) {
                     // ...Then fire a bullet in the direction of the enemy.
                     robotController.firePentadShot(robotController.getLocation().directionTo(robots[0].location));
                 }
             }

             // Move randomly
             tryMove(randomDirection());



         } catch (Exception e) {
             System.out.println("Soldier Exception");
             e.printStackTrace();
         }
            // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                     Clock.yield();

         }
     }
 }
