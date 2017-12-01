package botX;

import battlecode.common.*;

@SuppressWarnings("ALL")
public class Gardener extends Robot {

    public Gardener() {
        super();
    }

    @Override


    @SuppressWarnings("InfiniteLoopStatement")
    public void OnUpdate()
    {
        System.out.println("my Garner should be doing something");
      boolean settled = false;
        Direction gardnerDir = null;

        while (true) {
            try {
                Direction direction = randomDirection();
                if (gardnerDir == null) {
                    gardnerDir = direction;
                }
                if (robotController.isBuildReady()) {
                    // Randomly attempt to build a soldier or lumberjack in this direction
                    if (robotController.canBuildRobot(RobotType.SOLDIER, direction) && Math.random() < .01) {
                        robotController.buildRobot(RobotType.SOLDIER, direction);
                    }
                    else if (robotController.canBuildRobot(RobotType.LUMBERJACK, direction) && Math.random() < .01 && robotController.isBuildReady()) {
                        robotController.buildRobot(RobotType.LUMBERJACK, direction);
                    }

                }
                if ((robotController.isCircleOccupiedExceptByThisRobot(robotController.getLocation(), robotController.getType().bodyRadius * 4.0f))) {
                    settled = true;

                    if (robotController.canPlantTree(direction)) {
                        {
                            robotController.plantTree(direction);
                        }
                    }
                    if (!settled) {
                        if (robotController.canPlantTree(direction)) {
                            robotController.plantTree(direction);
                        }
                    }
                }
                TreeInfo[] trees = robotController.senseNearbyTrees(robotController.getType().bodyRadius * 2, robotController.getTeam());
                TreeInfo minHealthTree = null;
                for (TreeInfo tree : trees) {
                    if (tree.health < 70)  {
                        if (minHealthTree == null || tree.health < minHealthTree.health) {
                            minHealthTree = tree;
                        }
                    }
                    if (minHealthTree != null) {

                    }
                    robotController.water(minHealthTree.ID);
                }

                if (settled) {
                    if (tryMove(gardnerDir)) {
                        System.out.println("moved");
                    } else {
                        gardnerDir = randomDirection();
                        tryMove(gardnerDir);
                    }
                }



                // Move randomly
                tryMove(randomDirection());



            } catch (Exception e) {
                System.out.println("This is a Gardner Team Exception");
                e.printStackTrace();
            }

            Clock.yield();
        }
    }
}
