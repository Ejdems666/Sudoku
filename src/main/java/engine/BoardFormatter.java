package engine;

import board.Board;
import board.BoardPiece;
import board.Field;

import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

public class BoardFormatter {
    public static boolean DEBUG = false;

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

    public String formatFullBoardInDebugView(Board board) {
        if (!DEBUG) {
            return "";
        }
        String result = "";
        int rowCount = 1;
        for (BoardPiece row : board.getRows()) {
            for (Field field : row.getFields()) {
                if (field.isEmpty()) {
                    result += ansi().eraseScreen().fg(RED).a("|.");
                    for (Integer possibleValue : field.getPossibleValues()) {
                        result += possibleValue;
                    }
                    int numberOfSpaces = 9 - field.getPossibleValues().size();
                    for (int i = 0; i < numberOfSpaces; i++) {
                        result += " ";
                    }
                    result += ansi().reset();
                } else {
                    result += "|"+field.getValue()+"         ";
                }
            }
            result += "\n";
            if ((rowCount++)%3 == 0) {
                result += ansi().eraseScreen().fg(GREEN)
                        .a("------------------------------------------------------------------------------------------------------\n")
                        .reset();
            }
        }
        result += "\n\n";
        return result;
    }
}
