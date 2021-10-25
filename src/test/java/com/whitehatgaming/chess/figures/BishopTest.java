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

class BishopTest {

    static Bishop sut1;
    static Bishop sut2;
    static Bishop sut3;
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
        sut1 = new Bishop(board.getField(0, 3), board, PieceType.BLACK);
        board.getField(0, 3).setPiece(sut1);
        sut2 = new Bishop(board.getField(3, 5), board, PieceType.BLACK);
        board.getField(3, 5).setPiece(sut2);
        sut3 = new Bishop(board.getField(4, 6), board, PieceType.WHITE);
        board.getField(4, 6).setPiece(sut3);
    }

    @Test
    void possibleFieldsInitialPosition() {
        final List<Field> actual = sut1.getAllPossibleFields();

        assertEquals(0, actual.size());
        assertEquals("b", sut1.toString());
    }

    @Test
    void possibleFieldsMiddleFieldEnemyAttackAndFriendlyAttack() {
        final List<Field> actual = sut2.getAllPossibleFields();

        assertEquals(6, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 6),
                board.getField(4, 4),
                board.getField(5, 3),
                board.getField(6, 2),
                board.getField(4, 6),
                board.getField(2, 4))));
        assertEquals("b", sut2.toString());
    }

    @Test
    void possibleFieldsAgainstInitialPositionUnderAttack() {
        final List<Field> actual = sut3.getAllPossibleFields();

        assertEquals(4, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(3, 5),
                board.getField(3, 7),
                board.getField(5, 7),
                board.getField(5, 5))));
        assertEquals("B", sut3.toString());
    }
}