package hedge.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 *
 * @author andrew
 */
@RunWith(Parameterized.class)
public class GardenTest {
    List<List<Integer>> trees;
    Garden garden;
    private int x;
    private int y;
    private int res;
    enum Type {SUM_X, SUM_Y, GET_POINT};   
    private Type type;
    
    public GardenTest(Type type, int x, int y, int res) {
        this.x = x;
        this.y = y;
        this.res = res;
        this.type = type;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {Type.SUM_X, 5, 5, 5},
            {Type.SUM_X, 1, 3, 15},
            {Type.SUM_X, 4, 1, 17},
            {Type.SUM_X, 1, 1, 18},
            {Type.SUM_X, 4, 1, 17},
            {Type.SUM_Y, 6, 5, 6},
            {Type.SUM_Y, 4, 5, 15},
            {Type.SUM_Y, 4, 1, 15},
            {Type.SUM_Y, 1, 1, 21},
            {Type.SUM_Y, 5, 1, 11},
            {Type.GET_POINT, 6, 5, 6},
            {Type.GET_POINT, 4, 5, 4},
            {Type.GET_POINT, 1, 1, 1}
        });
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
        Assume.assumeTrue(type == Type.SUM_X);
        assertEquals(garden.sumOfAplesAcrossYline(x, y), res);
    }
    
    @Test
    public void sumOfAplesAcrossXlineTest() {
        Assume.assumeTrue(type == Type.SUM_Y);
        assertEquals(garden.sumOfAplesAcrossXline(x, y), res);
    }
    
    @Test
    public void getApplesFromDefinedPointTest() {
        Assume.assumeTrue(type == Type.GET_POINT);
        assertEquals(garden.getApplesFromDefinedPoint(x, y), res);
    }
}
