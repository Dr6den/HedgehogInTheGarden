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

public class HedgehogAstarAlgorythmTest {
    
    @Test
    public void go() throws InconsistentTreeFileException {
        try {
            Path resultPath = Paths.get("src/main/resources/outputWithCorrelation.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/main/resources/input.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogAstarAlgorythm hog = new HedgehogAstarAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/main/resources/logPathAstar.txt"));
            Files.write(resultPath, output);
            System.out.println("A star Algorythm result:" + 
                    output.parallelStream().map((String r) -> Integer.parseInt(r)).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 128);
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
            Path resultPath = Paths.get("src/main/resources/outputWithCorrelationMANYAPPLES.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/main/resources/inputFewApples.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogAstarAlgorythm hog = new HedgehogAstarAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/main/resources/logPathAstar_MANYAPPLES.txt"));
            Files.write(resultPath, output);
            System.out.println("A star Algorythm result:" + 
                    output.parallelStream().map((String r) -> Integer.parseInt(r)).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 2235);
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
