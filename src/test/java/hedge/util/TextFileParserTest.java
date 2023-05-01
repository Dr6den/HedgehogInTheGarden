package hedge.util;

import hedge.entity.Garden;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author andrew
 */
public class TextFileParserTest {
    List<String> lines;
    
    public TextFileParserTest() {
    }
    
    @Before
    public void setUp() {
        lines = new ArrayList();
        lines.add("15 10");
        lines.add("1 2 3");
        lines.add("4 5 6");
        lines.add("10 11 12");
    }
    
    @Test
    public void parseCorrectTextFileTest() throws IOException, InconsistentTreeFileException {
        TextFileParser parser = new TextFileParser();
        Garden garden = parser.parseTextFile("src/main/resources/fileparser/correct.txt");
        
        assertNotNull(garden);
        assertEquals(garden.getxTerritoryMetres(), 7);
        assertEquals(garden.getyTerritoryMetres(), 6);
        
        List<List<Integer>> coord = garden.getTrees();
        
        assertNotNull(coord);
        assertEquals(coord.get(0).get(0).intValue(), 5);
        assertEquals(coord.get(0).get(1).intValue(), 10);
        assertEquals(coord.get(0).get(2).intValue(), 3);
        assertEquals(coord.get(0).get(3).intValue(), 4);
        assertEquals(coord.get(0).get(4).intValue(), 5);
        assertEquals(coord.get(0).get(5).intValue(), 3);
        assertEquals(coord.get(0).get(6).intValue(), 5);
        assertEquals(coord.get(3).get(2).intValue(), 10);
        assertEquals(coord.get(3).get(3).intValue(), 1);
        assertEquals(coord.get(3).get(4).intValue(), 10);
        assertEquals(coord.get(3).get(6).intValue(), 3);
        assertEquals(coord.get(5).get(0).intValue(), 5);
        assertEquals(coord.get(5).get(1).intValue(), 5);
        assertEquals(coord.get(5).get(2).intValue(), 9);
        assertEquals(coord.get(5).get(3).intValue(), 6);
        assertEquals(coord.get(5).get(6).intValue(), 1);
    }
    
    @Test(expected = InconsistentTreeFileException.class)
    public void parseWrongCellNumbersTextFileTest() throws IOException, InconsistentTreeFileException {
        TextFileParser parser = new TextFileParser();
        parser.parseTextFile("src/main/resources/fileparser/wrongCellNumbers.txt");
    }
    
    @Test(expected = InconsistentTreeFileException.class)
    public void parseWrongColumnNumbersTextFileTest() throws IOException, InconsistentTreeFileException {
        TextFileParser parser = new TextFileParser();
        parser.parseTextFile("src/main/resources/fileparser/wrongColumnNumbers.txt");
    }
    
    @Test(expected = InconsistentTreeFileException.class)
    public void parseHeaderAndCellIncoinsidenessTest() throws IOException, InconsistentTreeFileException {
        TextFileParser parser = new TextFileParser();
        parser.parseTextFile("src/main/resources/fileparser/headerAndCellInconsideness.txt");
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void parseWrongHeaderTextFileTest() throws IOException, InconsistentTreeFileException {
        TextFileParser parser = new TextFileParser();
        parser.parseTextFile("src/main/resources/fileparser/wrongHeader.txt");
    }
}
