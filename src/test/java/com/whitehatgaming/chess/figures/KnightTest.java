package com.whitehatgaming.chess.figures;

import com.whitehatgaming.chess.board.Board;
import com.whitehatgaming.chess.board.Field;
import com.whitehatgaming.chess.util.enumerations.PieceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
    }

    @Test
    void possibleFieldsInitialPosition() {
        Knight sut = new Knight(board.getField(0, 6), board, PieceType.BLACK);
        board.getField(0, 6).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(2, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 5),
                board.getField(2, 7))));
        assertEquals("n", sut.toString());
    }

    @Test
    void possibleFieldsCastleDefense() {
        Knight sut = new Knight(board.getField(1, 3), board, PieceType.BLACK);
        board.getField(1, 3).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(4, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 1),
                board.getField(2, 5),
                board.getField(3, 2),
                board.getField(3, 4))));
        assertEquals("n", sut.toString());
    }

    @Test
    void possibleFieldsOpponentAttack() {
        Knight sut = new Knight(board.getField(6, 3), board, PieceType.BLACK);
        board.getField(6, 3).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(6, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(7, 1),
                board.getField(7, 5),
                board.getField(5, 1),
                board.getField(5, 5),
                board.getField(4, 2),
                board.getField(4, 4))));
        assertEquals("n", sut.toString());
    }

    @Test
    void possibleFieldsCastleDefenseEdge() {
        Knight sut = new Knight(board.getField(7, 7), board, PieceType.WHITE);
        board.getField(7, 7).setPiece(sut);


        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(1, actual.size());
        assertTrue(actual.contains(board.getField(5, 6)));
        assertEquals("N", sut.toString());
    }
}