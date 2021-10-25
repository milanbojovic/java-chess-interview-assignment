package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        final int rank = getField().getRank();
        final int file = getField().getFile();
        final List<Field> possibleFields = new ArrayList<>();

        //right
        for (var i = file + 1; i <= 7; i++) {
            final var field = getBoard().getField(rank, i);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //left
        for (var i = file - 1; i >= 0; i--) {
            final var field = getBoard().getField(rank, i);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //down
        for (var i = rank + 1; i <= 7; i++) {
            final var field = getBoard().getField(i, file);
            if (assemblePossibleFields(possibleFields, field)) break;
        }

        //up
        for (var i = rank - 1; i >= 0; i--) {
            final var field = getBoard().getField(i, file);
            if (assemblePossibleFields(possibleFields, field)) break;
        }
        return possibleFields;
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "r" : "R";
    }
}
