package hedge.util;

import hedge.entity.Garden;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author andrew
 */
public class TextFileParser {
    public Garden parseTextFile(String path) throws IOException, InconsistentTreeFileException {
        Path currentRelativePath = Paths.get(path);
        List<String> lines = Files.readAllLines(currentRelativePath);
        
        Garden garden = new Garden();
        String[] gardenSize = lines.get(0).split(" ");
        garden.setxTerritoryMetres(Integer.parseInt(gardenSize[0]));
        garden.setyTerritoryMetres(Integer.parseInt(gardenSize[1]));
        lines.remove(0);
        
        List<List<Integer>> trees = lines.stream().map((String p) -> {
            return Arrays.asList(p.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
        }).collect(Collectors.toList());

        garden.setTrees(trees);
        validateGarden(garden);
        return garden;
    }
    
    private void validateGarden(Garden garden) throws InconsistentTreeFileException {
        int xSize = garden.getxTerritoryMetres();
        int ySize = garden.getyTerritoryMetres();
        List<List<Integer>> trees = garden.getTrees();
        
        if(trees.size() != ySize) throw new InconsistentTreeFileException("Wrong number of columns in tree");
        if(!trees.stream().allMatch((List<Integer> c) -> c.size() == xSize)) {
            throw new InconsistentTreeFileException("Wrong number of rows in tree");
        }
    }
}
