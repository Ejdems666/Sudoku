package board;

import java.util.List;

/**
 * Created by adam on 10/27/2017.
 */
public class Board {
    private List<Column> columns;
    private List<Row> rows;
    private List<Square> squares;

    public Board(List<Column> columns, List<Row> rows, List<Square> squares) {
        this.columns = columns;
        this.rows = rows;
        this.squares = squares;
    }

    public Column getColumn(int x) {
        return columns.get(x);
    }

    public Row getRow(int y) {
        return rows.get(y);
    }

    public Square getSquare(int index) {
        return squares.get(index);
    }

    @Override
    public String toString() {
        String result = "";
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.isEmpty()) {
                    result += "|.";
                } else {
                    result += "|"+field.getValue();
                }
            }
            result += "\n";
        }
        return result;
    }
}
