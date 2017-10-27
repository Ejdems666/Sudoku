package generator;

import board.*;

import java.util.Scanner;

/**
 * Created by adam on 10/27/2017.
 */
public class Generator {
    private final BoardPieceBuffer<Column> columns = new BoardPieceBuffer<>(Column.class);
    private final BoardPieceBuffer<Row> rows = new BoardPieceBuffer<>(Row.class);
    private final BoardPieceBuffer<Square> squares = new BoardPieceBuffer<>(Square.class);

    public Board run(Scanner scanner) {
        int y = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            for (int squareRowI = 0; squareRowI < 3; squareRowI++) {
                int firstXInCurrentSquare = squareRowI * 3;
                for (int x = firstXInCurrentSquare; x < firstXInCurrentSquare+3; x++) {
                    Field field = new Field(x,y,Integer.parseInt(line[x]));
                    squares.addField(getIndexOfSquare(squareRowI,y),field);
                }
            }
            fillRowYOfAllColumns(y, line);
            fillRow(y, line);
            y++;
        }
        return new Board(columns.getBoardPieces(),rows.getBoardPieces(),squares.getBoardPieces());
    }

    private int getIndexOfSquare(int rowIndex, int y) {
        return (int) (rowIndex + 3*Math.floor(y/3));
    }

    private void fillRowYOfAllColumns(int y, String[] line) {
        for (int x = 0; x < line.length; x++) {
            int value = Integer.parseInt(line[x]);
            Field field = new Field(x,y, value);
            columns.addField(x,field);
        }
    }

    private void fillRow(int y, String[] line) {
        for (int x = 0; x < line.length; x++) {
            int value = Integer.parseInt(line[x]);
            Field field = new Field(x,y, value);
            rows.addField(y,field);
        }
    }

}
