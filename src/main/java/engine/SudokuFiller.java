package engine;

import board.Board;
import board.Field;
import board.Square;
import generator.Generator;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adam on 10/30/2017.
 */
public class SudokuFiller {
    public static void main(String[] args) throws Exception {
        File mapFile = new File("resources/medium.txt");
        Scanner scanner = new Scanner(mapFile);
        Generator generator = new Generator();
        Board board = generator.run(scanner);
        SudokuFiller filler = new SudokuFiller();
        filler.fill(board);
    }

    public void fill(Board board) {
        int safety = 0;
        while (!board.isFilled()) {
            for (int i = 0; i < 9; i++) {
                board.getRow(i).setLastMissingValue();
                board.getColumn(i).setLastMissingValue();
                board.getSquare(i).setLastMissingValue();
            }
            fillBoardUsingSquaresAndPossibleValues(board);
            safety++;
            if (safety == 100) {
                System.out.println("fail!");
                break;
            }
        }
        BoardChecker boardChecker = new BoardChecker();
        System.out.println("Iterations: " + safety);
        System.out.println("Is correct: "+boardChecker.check(board));
        System.out.println(board);
    }

    private void fillBoardUsingSquaresAndPossibleValues(Board board) {
        for (int value = 1; value <= 9; value++) {
            for (int s = 0; s < 9; s++) {
                Square square = board.getSquare(s);
                for (Field field : square.getFields()) {
                    if (field.isEmpty() && square.canHaveValuePlacedIn(field, value)) {
                        field.addPossibleValue(value);
                    }
                }
                List<Field> fieldsWithSamePossibleValues = square.getFieldsWithSamePossibleValues(value);
                if (fieldsWithSamePossibleValues.size() == 1) {
                    fieldsWithSamePossibleValues.get(0).setValueAndNotify(value);
                }
            }
        }
    }
}
