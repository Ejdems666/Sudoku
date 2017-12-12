package board;

/**
 * Created by adam on 10/30/2017.
 */
public abstract class StraightBoardPiece extends BoardPiece {
    @Override
    public boolean canHaveValuePlacedIn(Field targetField, int value) {
        if (containsFieldWithValue(value)) return false;
        for (int s = 0; s <= 2; s++) {
            int firstIndexInSquareS = s * 3;
            Square square = fields.get(firstIndexInSquareS).getSquare();
            if (square.equals(targetField.getSquare())) {
                continue;
            }
            int countOfSamePossibleValuesTouchingSquareS = getCountOfSamePossibleValuesTouchingSquareS(value, firstIndexInSquareS);
            if (countOfSamePossibleValuesTouchingSquareS > 1) {
                int countOfPossibleValuesOfSquareS = square.getFieldsWithSamePossibleValues(value).size();
                if (countOfPossibleValuesOfSquareS == countOfSamePossibleValuesTouchingSquareS) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getCountOfSamePossibleValuesTouchingSquareS(int value, int firstIndexInSquareS) {
        int count = 0;
        for (int i = firstIndexInSquareS; i < firstIndexInSquareS + 3; i++) {
            if (fields.get(i).hasPossibleValue(value)) {
                count++;
            }
        }
        return count;
    }
}
