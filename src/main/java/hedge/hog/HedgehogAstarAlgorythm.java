package hedge.hog;

import hedge.entity.Garden;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HedgehogAstarAlgorythm extends Hedgehog {
    @Override
    public int go(Garden garden, int startPointX, int startPointY, List<String> logPathList) {
        int numberOfApples = 0;
        int x = startPointX;
        int y = startPointY;
        int xBorder = garden.getxTerritoryMetres();
        int yBorder = garden.getyTerritoryMetres();
        List<List<Integer>> trees = garden.getTrees();

        boolean goToTheRight = false;
        while (x != xBorder || y != yBorder) {
            numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);
            byte advice = adviceAccordingToTheEuristicFunction(x, y, xBorder, yBorder, trees);
            switch (advice) {
                case 1:
                    goToTheRight = true;
                    break;
                case -1:
                    goToTheRight = false;
            }
            if (!goToTheRight && y < yBorder) {
                y++;
            } else if (x < xBorder) {
                x++;
            }
            logPathList.add(x + " " + y);
        }
        return numberOfApples;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param borderX
     * @param borderY
     * @return advice: 1 advice to the right, -1 advice to the down
     */
    public byte adviceAccordingToTheEuristicFunction(int x, int y, int borderX, int borderY, List<List<Integer>> trees) {
        int loosesByX = probabilityLoseApplesAfterDefinedStepsRight(x, y, borderX, trees);
        int loosesByY = probabilityLoseApplesAfterDefinedStepsDown(x, y, borderY, trees);
        if (loosesByY == 0) return 0;

        double correlationLooses = (double) loosesByX/loosesByY;
        if (correlationLooses > 0.95 && correlationLooses <= 1.09) {
            if(trees.get(y - 1).get(x) > trees.get(y).get(x - 1)) {
                return 1;
            } else {
                return -1;
            }
        } else if (correlationLooses > 1.09) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public int probabilityLoseApplesAfterDefinedStepsRight(int x, int y, int borderX, List<List<Integer>> trees) {        
        List<List<Integer>> sublist = trees.stream().skip(y - 1).map((List<Integer> l) -> {
                    return l.subList(x, borderX);
                }).collect(Collectors.toList());
        
        return IntStream.range(0, sublist.get(0).size())
                .mapToObj(i -> {
                    return sublist.stream().mapToInt(l -> {
                        return l.get(i);
                    }).sum();
                }).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
    }
    
    public int probabilityLoseApplesAfterDefinedStepsDown(int x, int y, int borderY, List<List<Integer>> trees) {
        List<List<Integer>> sublist = trees.stream().skip(y).map((List<Integer> l) -> {
                return l.subList(x - 1, l.size());
            }).collect(Collectors.toList());
        
        if(sublist.size() == 0) return 0;
        
        return IntStream.range(0, sublist.get(0).size())
                .mapToObj(i -> {
                    return sublist.stream().mapToInt(l -> {
                        return l.get(i);
                    }).sum();
                }).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
    }
}
