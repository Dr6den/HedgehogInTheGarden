package hedge.hog;

import hedge.entity.Garden;
import hedge.util.InconsistentTreeFileException;
import hedge.util.TextFileParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HedgehogLooseCorellationTest {
    
    @Test
    public void goTroughTheMostPollutedByApplesWayWithLoseCalculation() throws InconsistentTreeFileException {
        try {
            Path resultPath = Paths.get("src/test/resources/outputWithCorrelation.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/test/resources/input.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogLooseCorellationAlgorythm hog = new HedgehogLooseCorellationAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/test/resources/logPathCorrelation.txt"));
            Files.write(resultPath, output);
            System.out.println("Loose Corellation Algorythm result:" + 
                    output.parallelStream().map((String r) -> {
                        return Integer.parseInt(r);
                    }).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 44);
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    *Tests for manyApples tree
    */
    
    @Test
    public void goOnBigCell() throws InconsistentTreeFileException {
        try {
            Path resultPath = Paths.get("src/test/resources/outputWithCorrelationMANYAPPLES.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/test/resources/inputFewApples.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogLooseCorellationAlgorythm hog = new HedgehogLooseCorellationAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/test/resources/logPathCorrelation_MANYAPPLES.txt"));
            Files.write(resultPath, output);
            System.out.println("Loose Corellation Algorythm_MANYAPPLES result:" + 
                    output.parallelStream().map((String r) -> {
                        return Integer.parseInt(r);
                    }).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 1840);
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
