package board;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by adam on 10/27/2017.
 */
public class Field {
    public final int x;
    public final int y;
    private Set<Integer> possibleValues = new HashSet<>();
    private Column column;
    private Row row;
    private Square square;

    private int value;

    public Field(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public void addPossibleValue(int value) {
        possibleValues.add(value);
    }

    public int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}
