package generator;

import board.BoardPiece;
import board.Field;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adam on 10/27/2017.
 */
public class BoardPieceBuffer<K extends BoardPiece> {
    private List<K> boardPieces = new ArrayList<>();

    public BoardPieceBuffer(Class<K> boardPieceClass) {
        for (int i = 0; i < 9; i++) {
            boardPieces.add(createBoardPiece(boardPieceClass));
        }
    }

    public K addField(int boardPieceIndex, Field field) throws WrongFileFormatException {
        try {
            K boardPiece = boardPieces.get(boardPieceIndex);
            boardPiece.addField(field);
            return boardPiece;
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFileFormatException();
        }
    }

    public List<K> getBoardPieces() {
        return boardPieces;
    }

    private K createBoardPiece(Class<K> boardPieceClass) {
        try {
            Constructor<?> ctor = boardPieceClass.getConstructor();
            return (K) ctor.newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("BoardPieceBuffer instance creation is broken");
    }
}
