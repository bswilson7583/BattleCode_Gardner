package botX;

import battlecode.common.*;
import java.util.*;

@SuppressWarnings("ALL")
class Archon extends Robot {

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void OnUpdate()
    {
        int numberOfGardeners = 0;
        MapLocation combatLocation = robotController.getLocation();
        while (true) {
            try {
                System.out.println(System.getProperty("bc.testing.seed", "0").hashCode() + 1);
                Direction dir = randomDirection();
                if (robotController.canHireGardener(dir) && Math.random() < .01 && numberOfGardeners < 16) {
                    robotController.hireGardener(dir);
                    System.out.println("number of gardeners " + numberOfGardeners);
                    numberOfGardeners++;
                }

                if (robotController.getTeamBullets() > 1000) {
                    robotController.donate(robotController.getTeamBullets() - 1000);
                }
                //Move in Random Direction
                tryMove(dir);

                //Brodcast archon's location for other robopts on the team to know

                robotController.broadcast(0,(int)combatLocation.x);
                robotController.broadcast(1,(int)combatLocation.y);



                Clock.yield();

            } catch (Exception e) {
                System.out.println("Archon Exception for master");
                e.printStackTrace();
            }
        }
    }
}
