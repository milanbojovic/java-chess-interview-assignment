package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.List;

public class Queen extends Piece {

    public Queen(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        var r = new Rook(getField(), getBoard(), getPieceType());
        var b = new Bishop(getField(), getBoard(), getPieceType());
        final List<Field> queenPossibleFields = r.getAllPossibleFields();

        queenPossibleFields.addAll(b.getAllPossibleFields());
        return queenPossibleFields;
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "q" : "Q";
    }
}
