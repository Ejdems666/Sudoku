package board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by adam on 10/27/2017.
 */
public abstract class BoardPiece implements Notifyable{
    protected List<Field> fields = new ArrayList<>(9);

    public List<Field> getFields() {
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

    public Field getField(int index) {
        return fields.get(index);
    }

    public abstract boolean canHaveValuePlacedIn(Field targetField, int value);

    public void setLastMissingValue() {
        Field onlyEmptyField = null;
        TreeSet<Integer> possibleMissingValues = new TreeSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        for (Field field : fields) {
            if (field.isEmpty()) {
                if (onlyEmptyField != null) {
                    return;
                } else {
                    onlyEmptyField = field;
                }
            } else {
                possibleMissingValues.remove(field.getValue());
            }
        }
        if (possibleMissingValues.size() == 1) {
            onlyEmptyField.setValueAndNotify(possibleMissingValues.first());
        }
    }

    @Override
    public void onValueWrittenIn(int value) {
        for (Field field : fields) {
            field.removePossibleValue(value);
        }
    }

    protected boolean containsFieldWithValue(int value) {
        for (Field field : fields) {
            if (field.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
