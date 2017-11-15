package botX;

import battlecode.common.*;

public class Gardener extends Robot
{

    public void onUpdate()
    {
        boolean settled = false;
        Direction gardnerDir = null;

        while (true)
        {
            try
            {
                Direction direction = randomDirection();
                if (gardnerDir == null)
                {
                    gardnerDir = direction;
                }
                if (!(robotController.isCircleOccupiedExceptByThisRobot(robotController.getLocation(), robotController.getType().bodyRadius * 4.0f))) {
                    settled = true;
                    if (robotController.canPlantTree(gardnerDir))
                    {
                        robotController.plantTree(gardnerDir);
                    }
                }
                if (settled)
                {
                    if (robotController.canPlantTree(direction))
                    {
                        //Action for making Soldiers
                        if (robotController.canBuildRobot(RobotType.SOLDIER,gardnerDir) && Math.random() < .01);
                        {
                            robotController.buildRobot(RobotType.SOLDIER, gardnerDir);
                    }
                    }

                }

                TreeInfo[] trees = robotController.senseNearbyTrees(robotController.getType().bodyRadius * 2, robotController.getTeam());
                TreeInfo minHealthTree = null;
                for (TreeInfo tree : trees)
                {
                    if (tree.health < 70)
                    {
                        if (minHealthTree == null || tree.health < minHealthTree.health)
                        {
                            minHealthTree = tree;
                    }
                }
                if (minHealthTree != null)
                {

                }                robotController.water(minHealthTree.ID);
                }

                if (!settled)
                {
                    if (tryMove(gardnerDir))
                    {
                        System.out.println("moved");
                    }
                    else
                    {
                        gardnerDir = randomDirection();
                        tryMove(gardnerDir);
                    }
                }




                Clock.yield();
            } catch (Exception e)
            {
                System.out.println("This is a Gardner Team Exception");
                e.printStackTrace();
            }
        }
    }
}
