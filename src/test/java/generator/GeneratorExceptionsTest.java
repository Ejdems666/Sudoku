package generator;

import org.testng.annotations.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created by adam on 10/27/2017.
 */
public class GeneratorExceptionsTest {
    @Test(expectedExceptions = {WrongFileFormatException.class})
    public void testWrongColumn() throws Exception {
        File mapFile = new File("src/test/resources/mapWrongColumn.txt");
        Scanner scanner = new Scanner(mapFile);
        Generator generator = new Generator();
        generator.run(scanner);
    }

    @Test(expectedExceptions = {WrongFileFormatException.class})
    public void testWrongRow() throws Exception {
        File mapFile = new File("src/test/resources/mapWrongRow.txt");
        Scanner scanner = new Scanner(mapFile);
        Generator generator = new Generator();
        generator.run(scanner);
    }
}
