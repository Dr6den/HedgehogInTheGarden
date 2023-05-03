package hedge.hog;

import hedge.entity.Garden;
import java.util.LinkedList;
import java.util.List;

public class HedgehogDeikstraBackwardAlgorythm extends Hedgehog {
    private LinkedList<Coordinates> disiredPathChange = new LinkedList<>();
    
    @Override
    public int go(Garden garden, int startPointX, int startPointY, List<String> logPathList) {
        LinkedList<DeikstraStep> stepsRightDown = new LinkedList<>();
        LinkedList<DeikstraStep> stepsLeftUp = new LinkedList<>();

        int result = 0;
        int x = startPointX;
        int y = startPointY;
        int xBorder = garden.getxTerritoryMetres();
        int yBorder = garden.getyTerritoryMetres();
        List<List<Integer>> trees = garden.getTrees();

        int numberOfApples = 0;
        int backwardNumberOfApples = 0;
        boolean goToTheRight = false;
        logPathList.add("Downward");

        while (numberOfApples <= backwardNumberOfApples) {
            numberOfApples = 0;

            while (x != xBorder || y != yBorder) {
                DeikstraStep downwardStep = new DeikstraStep(x, y, numberOfApples);
                numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);

                if (x == xBorder) {
                    goToTheRight = false;
                } else if (y == yBorder) {
                    goToTheRight = true;
                } else {
                    short lastAdvice = changePathAccoridingDisiredTail(x, y);
                    if (lastAdvice != 0) {
                        if (lastAdvice == 1) {
                            goToTheRight = true;
                        } else {
                            goToTheRight = false;
                        }
                    } else if (trees.get(y - 1).get(x) > trees.get(y).get(x - 1)) {
                        goToTheRight = true;
                    } else {
                        goToTheRight = false;
                    }
                }
                if (!goToTheRight && y < yBorder) {
                    y++;
                } else if (x < xBorder) {
                    x++;
                }
                logPathList.add(x + " " + y);
                downwardStep.setNextX(x);
                downwardStep.setNextY(y);
                downwardStep.setStepCost(garden.getApplesFromDefinedPoint(x, y));
                stepsRightDown.addLast(downwardStep);
            }

            result = numberOfApples;
            backwardNumberOfApples = 0;
            boolean goToTheLeft = false;
            logPathList.add("Backward");

            while (x != 1 || y != 1) {
                DeikstraStep backwardStep = new DeikstraStep(x, y, numberOfApples - backwardNumberOfApples);
                backwardNumberOfApples = backwardNumberOfApples + garden.getApplesFromDefinedPoint(x, y);

                if (x == 1) {
                    goToTheLeft = false;
                } else if (y == 1) {
                    goToTheLeft = true;
                } else {
                    if (trees.get(y - 1).get(x - 2) > trees.get(y - 2).get(x - 1)) {
                        goToTheLeft = true;
                    } else {
                        goToTheLeft = false;
                    }
                }
                if (!goToTheLeft && y > 0) {
                    y--;
                } else if (x > 0) {
                    x--;
                }
                logPathList.add(x + " " + y);
                backwardStep.setNextX(x);
                backwardStep.setNextY(y);
                backwardStep.setStepCost(garden.getApplesFromDefinedPoint(x, y));
                stepsLeftUp.addLast(backwardStep);
            }
            getMostValuedTail(stepsRightDown, stepsLeftUp);
        }
        return result;
    }
    
    private void getMostValuedTail(LinkedList<DeikstraStep> stepsRightDown, LinkedList<DeikstraStep> stepsLeftUp) {
        DeikstraStep downwardStep = stepsRightDown.pollLast();
        DeikstraStep backwardStep = stepsLeftUp.pollFirst();
        int downwardTailValue = downwardStep.getWayCost();
        int backwardTailValue = backwardStep.getWayCost();
        while(downwardTailValue < backwardTailValue && downwardTailValue > 0) {
            downwardStep = stepsRightDown.pollLast();
            backwardStep = stepsLeftUp.pollFirst();
            downwardTailValue = downwardStep.getWayCost();
            backwardTailValue = backwardStep.getWayCost();
        }
        disiredPathChange.add(new Coordinates(backwardStep.getNextX(), backwardStep.getNextY()));
    }
    
    /**
     * if returns 1, then go to the right, if return -1, then go to the down, if returns 0, no advice
     * @param x
     * @param y
     * @return 
     */
    private short changePathAccoridingDisiredTail(int x, int y) {
        short result = 0;
        if (disiredPathChange.isEmpty()) return result;
        Coordinates desiredCoordinates = disiredPathChange.pollLast();
        if(x == desiredCoordinates.getX()) {
            return 1;
        } else if (y == desiredCoordinates.getY()) {
            return -1;
        } else {
            return 0;
        }
    }
}
