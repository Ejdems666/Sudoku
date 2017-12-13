package board;

/**
 * Created by adam on 10/30/2017.
 */
public abstract class StraightBoardPiece extends BoardPiece {
    @Override
    public boolean canHaveValuePlacedIn(Field targetField, int possibleValue) {
        if (containsFieldWithValue(possibleValue)) return false;
        if (positionIsLockedByMultiplePossibleValuesInSeriesInAnotherSquare(targetField, possibleValue)) return false;
        return true;
    }

    /**
     * When there are 2 or 3 possible Values on 2 or 3 fields only in a single row or column inside a different square
     * Then the possible value can't be placed in the target field
     */
    private boolean positionIsLockedByMultiplePossibleValuesInSeriesInAnotherSquare(Field targetField, int possibleValue) {
        for (int s = 0; s <= 2; s++) {
            int firstIndexInSquare = s * 3;
            Square overlappingSquare = fields.get(firstIndexInSquare).getSquare();
            if (overlappingSquare.equals(targetField.getSquare())) {
                continue;
            }
            int countOfSamePossibleValuesTouchingSquareS = getCountOfSamePossibleValuesInOverlappingSquare(possibleValue, firstIndexInSquare);
            if (countOfSamePossibleValuesTouchingSquareS > 1) {
                int countOfPossibleValuesOfSquareS = overlappingSquare.getFieldsWithSamePossibleValues(possibleValue).size();
                if (countOfPossibleValuesOfSquareS == countOfSamePossibleValuesTouchingSquareS) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getCountOfSamePossibleValuesInOverlappingSquare(int value, int firstIndexInSquare) {
        int count = 0;
        for (int i = firstIndexInSquare; i < firstIndexInSquare + 3; i++) {
            if (fields.get(i).hasPossibleValue(value)) {
                count++;
            }
        }
        return count;
    }
}
