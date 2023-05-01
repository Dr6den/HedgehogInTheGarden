package hedge.entity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author andrew
 */
public class Garden {
    private int xTerritoryMetres;
    private int yTerritoryMetres;
    private List<List<Integer>> trees;

    public int getxTerritoryMetres() {
        return xTerritoryMetres;
    }

    public void setxTerritoryMetres(int xTerritoryMetres) {
        this.xTerritoryMetres = xTerritoryMetres;
    }

    public int getyTerritoryMetres() {
        return yTerritoryMetres;
    }

    public void setyTerritoryMetres(int yTerritoryMetres) {
        this.yTerritoryMetres = yTerritoryMetres;
    }

    public List<List<Integer>> getTrees() {
        return trees;
    }

    public void setTrees(List<List<Integer>> trees) {
        this.trees = trees;
    }
    
    public int sumOfAplesAcrossYline(int x, int startYPoint) {
        return this.trees.stream().skip(startYPoint - 1).collect(Collectors.summingInt((List<Integer> l) -> l.get(x - 1)));
    }
    
    public int sumOfAplesAcrossXline(int startXPoint, int y) {
        return this.trees.get(y - 1).stream().skip(startXPoint - 1).reduce(0, Integer::sum);
    }
    
    public int getApplesFromDefinedPoint(int x, int y) {
        return this.trees.get(y - 1).get( x - 1);
    }
}
