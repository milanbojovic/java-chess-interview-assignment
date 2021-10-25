package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        final int rank = getField().getRank();
        final int file = getField().getFile();
        final List<Field> possibleFields = new ArrayList<>();

        validateField(rank + 1, file + 2, possibleFields);
        validateField(rank + 2, file + 1, possibleFields);
        validateField(rank - 1, file - 2, possibleFields);
        validateField(rank - 2, file - 1, possibleFields);
        validateField(rank - 1, file + 2, possibleFields);
        validateField(rank - 2, file + 1, possibleFields);
        validateField(rank + 2, file - 1, possibleFields);
        validateField(rank + 1, file - 2, possibleFields);

        return possibleFields;
    }

    private void validateField(int rank, int file, List<Field> possibleFields) {
        if (rank >= 0 && rank <= 7 && file >= 0 && file <= 7) {
            addIfEmptyOrOpponnentsPiece(possibleFields, getBoard().getField(rank, file));
        }
    }

    private void addIfEmptyOrOpponnentsPiece(List<Field> possibleFields, Field field) {
        if (field.isEmpty()) {
            possibleFields.add(field);
        } else {
            if (field.getPiece().pieceType != pieceType) possibleFields.add(field);
        }
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "n" : "N";
    }
}
