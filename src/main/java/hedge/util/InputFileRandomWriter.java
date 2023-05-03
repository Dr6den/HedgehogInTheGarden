package hedge.util;

import java.util.List;
import java.util.Random;

/**
 * uses for generating of input files
 * @author andrew
 */
public class InputFileRandomWriter {   
    public static List<String> fillGardenAreaWithRandomApples(List<String> lines,
            int maxX, int maxY, int maxNumApplesUnderTheTree) {
        int startValue = 1;
        
        Random random = new Random();
        int x = random.nextInt(maxX);
        int y = random.nextInt(maxY);
        lines.add(x + " " + y);

        for (int i = 0; i < y; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < x; j++) {                
                line.append(startValue+ random.nextInt(maxNumApplesUnderTheTree));
                line.append(" ");                
            }
            lines.add(line.toString());
        }
        return lines;
    }
}
