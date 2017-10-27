package board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 10/27/2017.
 */
public abstract class BoardPiece implements Notifyable{
    protected List<Field> fields = new ArrayList<>(9);

    List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public Field getField(int x, int y) {
        for (Field field : fields) {
            if (field.x == x && field.y == y) {
                return field;
            }
        }
        throw new RuntimeException("x ("+x+") or y ("+y+") out of bounds, or "+Square.class+" wasn't properly initialized!");
    }

    public Field getField(int value) {
        for (Field field : fields) {
            if (field.getValue() == value) {
                return field;
            }
        }
        return null; // maybe thrown an exception here
    }
}
