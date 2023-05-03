package hedge.hog;

import hedge.entity.Garden;
import java.util.List;

public class HedgehogEagerAlgorithm extends Hedgehog {
    public int go(Garden garden, int startPointX, int startPointY, List<String> logPathList) {
        int numberOfApples = 0;
        int x = startPointX;
        int y = startPointY;
        int xBorder = garden.getxTerritoryMetres();
        int yBorder = garden.getyTerritoryMetres();
        while (x != xBorder || y != yBorder) {
            numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);
            boolean goToTheRight = whetherGoToTheRightOrGoToTheDown(garden, x, y, xBorder, yBorder);
            if (goToTheRight && x < xBorder) {
                x++;
            } else if (y < yBorder) {
                y++;
            }
            logPathList.add(x + " " + y);
        }
        return numberOfApples;
    }
}
