package board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 10/27/2017.
 */
public class Square extends BoardPiece {
    private List<StraightBoardPiece> rows = new ArrayList<>();
    private List<StraightBoardPiece> columns = new ArrayList<>();

    public void addRow(StraightBoardPiece row) {
        rows.add(row);
    }

    public void addColumn(StraightBoardPiece column) {
        columns.add(column);
    }

    @Override
    public boolean canHaveValuePlacedIn(Field targetField, int value) {
        if (containsFieldWithValue(value)) return false;
        return targetField.getColumn().canHaveValuePlacedIn(targetField, value) && targetField.getRow().canHaveValuePlacedIn(targetField, value);
    }

    public List<Field> getFieldsWithSamePossibleValues(int value) {
        List<Field> fieldsWithSamePValue = new ArrayList<>();
        int count = 0;
        for (Field field : fields) {
            if (field.hasPossibleValue(value)) {
                fieldsWithSamePValue.add(field);
            }
        }
        return fieldsWithSamePValue;
    }
}
