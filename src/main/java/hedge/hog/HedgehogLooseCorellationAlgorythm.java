package hedge.hog;

import hedge.entity.Garden;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HedgehogLooseCorellationAlgorythm extends Hedgehog {
    public int go(Garden garden, int startPointX, int startPointY, String logPathFileLocation) {
        int numberOfApples = 0;
        try {
            Path path = Paths.get(logPathFileLocation);
            Files.deleteIfExists(path);
            Files.createFile(path);
            List<String> lines = new ArrayList<>();
            
            int x = startPointX;
            int y = startPointY;
            int xBorder = garden.getxTerritoryMetres();
            int yBorder = garden.getyTerritoryMetres();
            List<List<Integer>> trees = garden.getTrees();
            
            boolean goToTheRight = false;
            while (x != xBorder || y != yBorder) {
                numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);
                byte advice = adviceAccordingToTheLosePossibility(x, y, xBorder, yBorder, trees);
                switch (advice) {
                    case 0:  goToTheRight = whetherGoToTheRightOrGoToTheDown(garden, x, y, xBorder, yBorder);
                    break;
                    case 1:  goToTheRight = true;
                    break;
                    case -1: goToTheRight = false;
                }
                if (goToTheRight && x < xBorder) {
                    x++;
                } else if (y < yBorder) {
                    y++;
                }
                lines.add(x + " " + y);
            }
            Files.write(path, lines);
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfApples;
    }
    
    public int probabilityLoseApplesAfterDefinedStepsRight(int x, int y, int borderX, List<List<Integer>> trees) {
        final int steps;
        if (borderX > 12) {
            steps = (int) Math.floor((borderX - x) / 4);
        } else {
            steps = 1;
        }
        
        if (borderX - x < steps) {
            return 0;
        }
        
        List<List<Integer>> sublist = trees.stream().skip(y - 1).map((List<Integer> l) -> l.subList(x - 1, borderX))
                .collect(Collectors.toList());
        
        List<List<Integer>> sublistWithStepAdded = trees.stream().skip(y - 1)
                .map((List<Integer> l) -> l.subList(x + steps - 1, borderX))
                .collect(Collectors.toList());
        
        int currentSum = IntStream.range(0, sublist.get(0).size())
                .mapToObj(i -> sublist.stream().mapToInt(l -> l.get(i))
                .sum()).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
        
        int sumAfterStepsToTheRight = IntStream.range(0, sublistWithStepAdded.get(0).size())
                .mapToObj(i -> sublistWithStepAdded.stream().mapToInt(l -> l.get(i))
                .sum()).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
        return currentSum - sumAfterStepsToTheRight;
    }
    
    public int probabilityLoseApplesAfterDefinedStepsDown(int x, int y, int borderY, List<List<Integer>> trees) {
        final int steps;
        if (borderY > 12) {
            steps = (int) Math.floor((borderY - y) / 4);
        } else {
            steps = 1;
        }
        
        if (borderY - y < steps) {
            return 0;
        }
        
        List<List<Integer>> sublist = trees.stream().skip(y - 1).limit(borderY)
                .map((List<Integer> l) -> l.subList(x - 1, l.size()))
                .collect(Collectors.toList());
        
        List<List<Integer>> sublistWithStepAdded = trees.stream().skip(y + steps - 1)
                .map((List<Integer> l) -> l.subList(x - 1, l.size()))
                .collect(Collectors.toList());
        
        int currentSum = IntStream.range(0, sublist.get(0).size())
                .mapToObj(i -> sublist.stream().mapToInt(l -> l.get(i))
                .sum()).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
        
        int sumAfterStepsToTheRight = IntStream.range(0, sublistWithStepAdded.get(0).size())
                .mapToObj(i -> sublistWithStepAdded.stream().mapToInt(l -> l.get(i))
                .sum()).collect(Collectors.toList())
                .stream().reduce(0, Integer::sum);
        return currentSum - sumAfterStepsToTheRight;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param borderX
     * @param borderY
     * @return advice: 0 - no advice, 1 advice to the right, -1 advice to the down
     */
    public byte adviceAccordingToTheLosePossibility(int x, int y, int borderX, int borderY, List<List<Integer>> trees) {
        int losesByX = probabilityLoseApplesAfterDefinedStepsRight(x, y, borderX, trees);
        int losesByY = probabilityLoseApplesAfterDefinedStepsDown(x, y, borderY, trees);
        if (losesByY == 0) return 0;

        if (losesByX > losesByY) {
            return 1;
        } else if (losesByX < losesByY) {
            return -1;
        } else {
            return 0;
        }
    }
}
