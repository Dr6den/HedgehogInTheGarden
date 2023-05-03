package hedge.hog;

import hedge.entity.Garden;
import java.util.List;
import java.util.Random;

/**
 *
 * @author andrew
 */
public class RandomHedgehog extends Hedgehog {   
    public int go(Garden garden, int startPointX, int startPointY, List<String> logPathList) {
        int numberOfApples = 0;
        int x = startPointX;
        int y = startPointY;
        Random rand = new Random();
        while (x < garden.getxTerritoryMetres() || y < garden.getyTerritoryMetres()) {
            numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);
            boolean goToTheRight = rand.nextBoolean();
            if (goToTheRight && x < garden.getxTerritoryMetres()) {
                x++;
            } else if (y < garden.getyTerritoryMetres()) {
                y++;
            }
        }
        return numberOfApples;
    }
}
