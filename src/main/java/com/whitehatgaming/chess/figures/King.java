package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        final int rank = getField().getRank();
        final int file = getField().getFile();
        final List<Field> possibleFields = new ArrayList<>();

        for (var i = rank - 1; i <= rank + 1; i++) {
            for (var j = file - 1; j <= file + 1; j++) {
                if (i == rank && j == file) continue;

                var field = getBoard().getField(i, j);
                if (assemblePossibleFields(possibleFields, field)) break;
            }
        }
        return possibleFields;
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "k" : "K";
    }
}
