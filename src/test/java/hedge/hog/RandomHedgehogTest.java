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
import org.junit.Test;

/**
 *
 * @author andrew
 */
public class RandomHedgehogTest {
   
    @Test
    public void randomWayTest() throws InconsistentTreeFileException {
        try {
            Path resultPath = Paths.get("src/test/resources/outputRandom.txt");
            Files.deleteIfExists(resultPath);           
            Files.createFile(resultPath);
            
            TextFileParser parser = new TextFileParser();
            Garden garden = parser.parseTextFile("src/test/resources/input.txt");
            
            List<String> output = new ArrayList<>();
            RandomHedgehog hog = new RandomHedgehog();
            //for (int i = 1; i < 100000; i++) {
                output.add("" + hog.go(garden, 1, 1, null));
            //}
            Files.write(resultPath, output);
            System.out.println("randomWayTest Result:" +
                    output.parallelStream().map((String r) -> {
                        return Integer.parseInt(r);
                    }).max(Integer::compare).get());
        } catch (IOException ex) {
            Logger.getLogger(RandomHedgehogTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
