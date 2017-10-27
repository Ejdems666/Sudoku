package generator;

import board.Board;

import java.io.File;
import java.util.Scanner;

/**
 * Created by adam on 10/27/2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        File mapFile = new File(getFileName(args));
        Scanner scanner = new Scanner(mapFile);
        Generator generator = new Generator();
        Board board = generator.run(scanner);
        System.out.println(board);
    }

    private static String getFileName(String[] args) {
        if (args.length >= 1) {
            return args[0];
        } else {
            return "resources/map1.txt";
        }
    }
}
