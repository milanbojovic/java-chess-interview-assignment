package com.whitehatgaming.chess.board;

import com.whitehatgaming.chess.figures.Piece;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Field {

    int rank;
    int file;

    @EqualsAndHashCode.Exclude
    Piece piece;
    @EqualsAndHashCode.Exclude
    Board board;

    public Field(int rank, int file, Board board) {
        this.rank = rank;
        this.file = file;
        this.board = board;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public void clear() {
        piece = null;
    }

    @Override
    public String toString() {
        return isEmpty() ? " " : piece.toString();
    }
}
