package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Field field, Board board, PieceType pieceType) {
        super(pieceType, field, board, true);
    }

    public List<Field> getAllPossibleFields() {
        final int rank = getField().getRank();
        final int file = getField().getFile();
        final List<Field> possibleFields = new ArrayList<>();

        var field = getBoard().getField(ternByPieceType(rank, 1), file);
        if (field != null && field.isEmpty()) possibleFields.add(field);

        field = getBoard().getField(ternByPieceType(rank, 2), file);
        if (isOnInitialPosition() && field != null && field.isEmpty()) {
            possibleFields.add(field);
        }

        field = getBoard().getField(ternByPieceType(rank, 1), file - 1);
        canTakeOpponentsPiece(possibleFields, field);
        field = getBoard().getField(ternByPieceType(rank, 1), file + 1);
        canTakeOpponentsPiece(possibleFields, field);

        return possibleFields;
    }

    private int ternByPieceType(int a, int b) {
        return getPieceType() == PieceType.WHITE ? a - b : a + b;
    }

    private void canTakeOpponentsPiece(List<Field> possibleFields, Field field) {
        if (field != null && !field.isEmpty() && field.getPiece().getPieceType() != getPieceType()) {
            possibleFields.add(field);
        }
    }

    @Override
    public String toString() {
        return getPieceType() == PieceType.BLACK ? "p" : "P";
    }

    @Override
    protected boolean assemblePossibleFields(List<Field> possibleFields, Field field) {
        if (field.isEmpty()) {
            possibleFields.add(field);
        } else {
            return field.getPiece().pieceType == pieceType;
        }
        return false;
    }
}
