package hedge.entity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author andrew
 */
public class GardenTest {
    List<List<Integer>> trees;
    Garden garden;
    
    public GardenTest() {
    }
    
    @Before
    public void setUp() {
        trees = new ArrayList<>();
        List<Integer> treeLine1 = new ArrayList<Integer>() {{add(1); add(2); add(3); add(4); add(5); add(6);}};
        List<Integer> treeLine2 = new ArrayList<Integer>() {{add(2); add(1); add(1); add(7); add(1); add(4);}};
        List<Integer> treeLine3 = new ArrayList<Integer>() {{add(8); add(1); add(3); add(2); add(1); add(1);}};
        List<Integer> treeLine4 = new ArrayList<Integer>() {{add(6); add(5); add(1); add(0); add(0); add(0);}};
        List<Integer> treeLine5 = new ArrayList<Integer>() {{add(1); add(2); add(3); add(4); add(5); add(6);}};
        garden = new Garden();
        trees.add(treeLine1);
        trees.add(treeLine2);
        trees.add(treeLine3);
        trees.add(treeLine4);
        trees.add(treeLine5);
        garden.setTrees(trees);
    }
    
    @Test
    public void sumOfAplesAcrossYlineTest() {
        assertEquals(garden.sumOfAplesAcrossYline(5, 5), 5);
        assertEquals(garden.sumOfAplesAcrossYline(1, 3), 15);
        assertEquals(garden.sumOfAplesAcrossYline(4, 1), 17);
        assertEquals(garden.sumOfAplesAcrossYline(1, 1), 18);
        assertEquals(garden.sumOfAplesAcrossYline(4, 1), 17);
    }
    
    @Test
    public void sumOfAplesAcrossXlineTest() {
        assertEquals(garden.sumOfAplesAcrossXline(6, 5), 6);
        assertEquals(garden.sumOfAplesAcrossXline(4, 5), 15);
        assertEquals(garden.sumOfAplesAcrossXline(4, 1), 15);
        assertEquals(garden.sumOfAplesAcrossXline(1, 1), 21);
        assertEquals(garden.sumOfAplesAcrossXline(5, 1), 11);
    }
    
    @Test
    public void getApplesFromDefinedPointTest() {
        assertEquals(garden.getApplesFromDefinedPoint(6, 5), 6);
        assertEquals(garden.getApplesFromDefinedPoint(4, 5), 4);
        assertEquals(garden.getApplesFromDefinedPoint(1, 1), 1);
    }
}
