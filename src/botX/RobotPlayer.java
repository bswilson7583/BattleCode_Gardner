package botX;

import battlecode.common.*;

@SuppressWarnings("ALL")
public strictfp class RobotPlayer
{
    static RobotController robotController;

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
     **/
    @SuppressWarnings({"unused", "InfiniteLoopStatement"})
   public static void run(RobotController robotController) throws GameActionException {

        Robot robot = null;
        try
        {
            Robot.init(robotController);

            switch (robotController.getType())
            {
                case ARCHON:
                    robot = new Archon();
                    break;
                case GARDENER:
                    robot = new Gardener();
                    break;
                case LUMBERJACK:
                    robot = new Lumberjack();
                    break;
                case SOLDIER:
                    robot = new Soldier();
                    break;

            }
        } catch (Exception e)
        {
            System.out.println("Exception in " + robotController.getType());
            e.printStackTrace();
        }
        while (true)
        {
            try
            {
                while (true)
                {
                    robot.OnUpdate();
                }

            } catch (Exception e)
            {
                System.out.println("Exception in " + robotController.getType());
                e.printStackTrace();
            }
        }
    }

}