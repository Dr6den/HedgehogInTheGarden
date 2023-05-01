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

public class HedgehogDeikstraBackwardAlgorythmTest {
    
    @Test
    public void go() throws InconsistentTreeFileException {
        try {
            Path resultPath = Paths.get("src/main/resources/output.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/main/resources/input.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogDeikstraBackwardAlgorythm hog = new HedgehogDeikstraBackwardAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/main/resources/logPathDeikstra.txt"));
            Files.write(resultPath, output);
            System.out.println("Deikstra Backward Algorythm result:" + 
                    output.parallelStream().map((String r) -> Integer.parseInt(r)).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 135);
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
            Path resultPath = Paths.get("src/main/resources/outputMANYAPPLES.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/main/resources/inputFewApples.txt");
            
            List<String> output = new ArrayList<>();
            HedgehogDeikstraBackwardAlgorythm hog = new HedgehogDeikstraBackwardAlgorythm();            
            output.add("" + hog.go(garden, 1, 1,"src/main/resources/logPathDeikstra_MANYAPPLES.txt"));
            Files.write(resultPath, output);
            System.out.println("Deikstra Backward Algorythm_MANYAPPLES result:" + 
                    output.parallelStream().map((String r) -> Integer.parseInt(r)).max(Integer::compare).get());
            
            assertEquals(Integer.parseInt(output.get(0)), 2214);
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
