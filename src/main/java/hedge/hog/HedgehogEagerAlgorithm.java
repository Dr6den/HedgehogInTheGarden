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

public class HedgehogEagerAlgorithm extends Hedgehog {
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
            while (x != xBorder || y != yBorder) {
                numberOfApples = numberOfApples + garden.getApplesFromDefinedPoint(x, y);
                boolean goToTheRight = whetherGoToTheRightOrGoToTheDown(garden, x, y, xBorder, yBorder);
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
}
