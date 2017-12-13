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
        return checkBoardPieces(board.getRows()) && checkBoardPieces(board.getColumns()) && checkBoardPieces(board.getSquares());
    }

    private boolean checkBoardPieces(List<BoardPiece> boardPieces) {
        for (BoardPiece boardPiece : boardPieces) {
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

    public int countFilledNumbers(Board board) {
        int numbers = 0;
        for (BoardPiece boardPiece : board.getRows()) {
            for (Field field : boardPiece.getFields()) {
                if (!field.isEmpty()) {
                    numbers++;
                }
            }
        }
        return numbers;
    }
}
