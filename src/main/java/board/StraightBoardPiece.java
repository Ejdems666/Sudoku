package board;

/**
 * Created by adam on 10/30/2017.
 */
public abstract class StraightBoardPiece extends BoardPiece {
    @Override
    public boolean canHaveValuePlacedIn(Field targetField, int value) {
        if (containsFieldWithValue(value)) return false;
        if (checkMultiplePossibleValuesInSameTouchedSquare(targetField, value)) return false;
        return true;
    }

    private boolean checkMultiplePossibleValuesInSameTouchedSquare(Field targetField, int value) {
        for (int s = 0; s <= 2; s++) {
            int firstIndexTouchingSquareS = s * 3;
            Square touchedSquare = fields.get(firstIndexTouchingSquareS).getSquare();
            if (touchedSquare.equals(targetField.getSquare())) {
                continue;
            }
            int countOfSamePossibleValuesTouchingSquareS = getCountOfSamePossibleValuesTouchingSquareS(value, firstIndexTouchingSquareS);
            if (countOfSamePossibleValuesTouchingSquareS > 1) {
                int countOfPossibleValuesOfSquareS = touchedSquare.getFieldsWithSamePossibleValues(value).size();
                if (countOfPossibleValuesOfSquareS == countOfSamePossibleValuesTouchingSquareS) {
                    return true;
                }
            }
        }
        return false;
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
