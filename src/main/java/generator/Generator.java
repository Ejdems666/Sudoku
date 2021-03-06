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
            for (int x = 0; x < line.length; x++) {
                int value = Integer.parseInt(line[x]);
                Field field = new Field(x, y, value);
                Column column = addFieldToAccordingColumn(x, field);
                Row row = addFieldToAccordingRow(y, field);
                Square square = addFieldToAccordingSquare(y, x, field);
                square.addColumn(column);
                square.addRow(row);
            }
            y++;
        }
        return new Board(columns.getBoardPieces(), rows.getBoardPieces(), squares.getBoardPieces());
    }

    private Row addFieldToAccordingRow(int y, Field field) throws WrongFileFormatException {
        Row row = rows.addField(y, field);
        field.setRow(row);
        return row;
    }

    private Column addFieldToAccordingColumn(int x, Field field) throws WrongFileFormatException {
        Column column = columns.addField(x, field);
        field.setColumn(column);
        return column;
    }

    private Square addFieldToAccordingSquare(int y, int x, Field field) throws WrongFileFormatException {
        double squareColumn = Math.floor(x / 3);
        double squareRow = Math.floor(y / 3);
        int squareIndex = (int) (squareColumn + 3 * squareRow);
        Square square = squares.addField(squareIndex, field);
        field.setSquare(square);
        return square;
    }
}
