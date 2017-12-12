package generator;

import board.Board;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created by adam on 10/27/2017.
 */
public class GeneratorTest {
    private static Board board;
    private static File mapFile;

    @BeforeClass
    public static void setUp() throws Exception {
        mapFile = new File("resources/easy.txt");
        Scanner scanner = new Scanner(mapFile);
        Generator generator = new Generator();
        board = generator.run(scanner);
    }

    @Test
    public void testColumns() throws Exception {
        Scanner scanner = new Scanner(mapFile);
        int y = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            for (int x = 0; x < line.length; x++) {
                Assert.assertTrue(board.getColumn(x).getField(x,y).getValue() == Integer.parseInt(line[x]));
            }
            y++;
        }
    }

    @Test
    public void testRows() throws Exception {
        Scanner scanner = new Scanner(mapFile);
        int y = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            for (int x = 0; x < line.length; x++) {
                Assert.assertTrue(board.getRow(y).getField(x,y).getValue() == Integer.parseInt(line[x]));
            }
            y++;
        }
    }

    @Test
    public void testSquare() throws Exception {
        Scanner scanner = new Scanner(mapFile);
        int y = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            for (int squareRowI = 0; squareRowI < 3; squareRowI++) {
                int firstXInCurrentSquare = squareRowI * 3;
                int squareIndex = (int) (squareRowI + 3 * Math.floor(y / 3));
                for (int x = firstXInCurrentSquare; x < firstXInCurrentSquare + 3; x++) {
                    Assert.assertTrue(board.getSquare(squareIndex).getField(x,y).getValue() == Integer.parseInt(line[x]));
                }
            }
            y++;
        }
    }
}