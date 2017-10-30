package engine;

import board.Board;
import board.BoardPiece;
import board.Field;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by adam on 10/30/2017.
 */
public class BoardChecker {
    public boolean check(Board board) {
        return checkBoardPieceType(board.getRows()) && checkBoardPieceType(board.getColumns()) && checkBoardPieceType(board.getSquares());
    }

    private boolean checkBoardPieceType(List<BoardPiece> boardPieceList) {
        for (BoardPiece boardPiece : boardPieceList) {
            Set<Integer> possibleValues = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
            for (Field field : boardPiece.getFields()) {
                possibleValues.remove(field.getValue());
            }
            if (!possibleValues.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
