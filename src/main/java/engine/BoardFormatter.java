package engine;

import board.Board;
import board.BoardPiece;
import board.Field;

public class BoardFormatter {
    public String formatBoardWithValuesOnly(Board board) {
        String result = "";
        for (BoardPiece row : board.getRows()) {
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
