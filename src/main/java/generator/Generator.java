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

    public Board run(Scanner scanner) throws Exception {
        int y = 0;
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            if (line.length != 9) {
                throw new WrongFileFormatException();
            }
            fillRowYOfSquaresOnRowY(y, line);
            fillRowYOfAllColumns(y, line);
            fillRow(y, line);
            y++;
        }
        return new Board(columns.getBoardPieces(), rows.getBoardPieces(), squares.getBoardPieces());
    }

    private void fillRowYOfSquaresOnRowY(int y, String[] line) throws WrongFileFormatException {
        for (int squareRowI = 0; squareRowI < 3; squareRowI++) {
            int firstXInCurrentSquare = squareRowI * 3;
            int squareIndex = (int) (squareRowI + 3 * Math.floor(y / 3));
            for (int x = firstXInCurrentSquare; x < firstXInCurrentSquare + 3; x++) {
                Field field = new Field(x, y, Integer.parseInt(line[x]));
                squares.addField(squareIndex, field);
            }
        }
    }

    private void fillRowYOfAllColumns(int y, String[] line) throws WrongFileFormatException {
        for (int x = 0; x < line.length; x++) {
            int value = Integer.parseInt(line[x]);
            Field field = new Field(x,y, value);
            columns.addField(x,field);
        }
    }

    private void fillRow(int y, String[] line) throws WrongFileFormatException {
        for (int x = 0; x < line.length; x++) {
            int value = Integer.parseInt(line[x]);
            Field field = new Field(x,y, value);
            rows.addField(y,field);
        }
    }

}
