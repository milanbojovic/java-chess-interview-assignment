package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        final int rank = getField().getRank();
        final int file = getField().getFile();
        final List<Field> possibleFields = new ArrayList<>();

        //inc i, inc j;
        for (int i = rank + 1, j = file + 1; i <= 7 && j <= 7; i++, j++) {
            final Field field = getBoard().getField(i, j);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //dec i, dec j
        for (int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
            final Field field = getBoard().getField(i, j);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //inc i, dec j
        for (int i = rank + 1, j = file - 1; i <= 7 && j >= 0; i++, j--) {
            final Field field = getBoard().getField(i, j);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //dec i, inc j
        for (int i = rank - 1, j = file + 1; i >= 0 && j <= 7; i--, j++) {
            final Field field = getBoard().getField(i, j);
            if (assemblePossibleFields(possibleFields, field)) break;
        }
        return possibleFields;
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "b" : "B";
    }
}
