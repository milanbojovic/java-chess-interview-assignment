package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public abstract class Piece {

    protected PieceType pieceType;
    private Field field;
    private Board board;
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private boolean isOnInitialPosition;

    public boolean move(Field dst) {
        if (validateMovePossible(dst)) {
            final Piece srcPiece = field.getPiece();
            field.clear();
            dst.setPiece(srcPiece);
            srcPiece.setField(dst);
            isOnInitialPosition = false;
            return true;
        } else {
            return false;
        }
    }

    protected boolean assemblePossibleFields(List<Field> possibleFields, Field field) {
        if (field == null) return false;
        if (field.isEmpty()) {
            possibleFields.add(field);
        } else {
            if (field.getPiece().pieceType != pieceType) possibleFields.add(field);
            return true;
        }
        return false;
    }

    protected boolean validateMovePossible(Field pos) {
        return getAllPossibleFields().contains(pos);
    }

    public abstract List<Field> getAllPossibleFields();
}
