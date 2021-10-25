package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PawnTest {

    static Pawn sut;
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
    }

    @Test
    void possibleFieldsAgainstInitialPosition() {
        sut = new Pawn(board.getField(1, 7), board, PieceType.BLACK);
        board.getField(2, 0).setPiece(sut);
        final List<Field> actual = sut.getAllPossibleFields();

        assertEquals(2, actual.size());
        assertEquals(board.getField(2, 7), actual.get(0));
        assertEquals(board.getField(3, 7), actual.get(1));
        assertEquals("p", sut.toString());
    }

    @Test
    void possibleFieldsAgainstFullEnemyDefense() {
        Pawn sut = new Pawn(board.getField(2, 0), board, PieceType.WHITE);
        sut.setOnInitialPosition(false);
        board.getField(1, 7).setPiece(sut);
        final List<Field> actual = sut.getAllPossibleFields();

        assertEquals(1, actual.size());
        assertEquals(board.getField(1, 1), actual.get(0));
        assertEquals("P", sut.toString());
    }

    @Test
    void possibleFieldsAgainstInitialPositionUnderAttack() {
        sut = new Pawn(board.getField(1, 1), board, PieceType.BLACK);
        board.getField(1, 1).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(2, actual.size());
        assertEquals(board.getField(2, 1), actual.get(0));
        assertEquals(board.getField(3, 1), actual.get(1));
    }

    @Test
    void possibleFieldsAgainstPieceAttacking() {
        sut = new Pawn(board.getField(6, 2), board, PieceType.BLACK);
        sut.setOnInitialPosition(false);
        board.getField(6, 2).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(2, actual.size());
        assertEquals(board.getField(7, 1), actual.get(0));
        assertEquals(board.getField(7, 3), actual.get(1));
    }

    @Test
    void possibleFieldsAgainstMiddleBoardAlonePiece() {
        sut = new Pawn(board.getField(5, 5), board, PieceType.WHITE);
        sut.setOnInitialPosition(false);
        board.getField(5, 5).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(1, actual.size());
        assertEquals(board.getField(4, 5), actual.get(0));
    }

    @Test
    void possibleFieldsAgainstPieceAttackingEdge() {
        sut = new Pawn(board.getField(5, 7), board, PieceType.BLACK);
        sut.setOnInitialPosition(false);
        board.getField(5, 7).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(1, actual.size());
        assertEquals(board.getField(6, 6), actual.get(0));
    }
}