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

class QueenTest {
    static Queen uat1;
    static Queen uat2;
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
        uat1 = new Queen(board.getField(7, 3), board, PieceType.WHITE);
        board.getField(7, 3).setPiece(uat1);
        uat2 = new Queen(board.getField(3, 6), board, PieceType.BLACK);
        board.getField(3, 6).setPiece(uat2);
    }

    @Test
    void possibleFieldsInitialPosition() {
        final List<Field> actual = uat1.getAllPossibleFields();

        assertEquals(0, actual.size());
        assertEquals("Q", uat1.toString());
    }

    @Test
    void possibleFieldsMiddleFieldEnemyAttackAndFriendlyAttack() {
        final List<Field> actual = uat2.getAllPossibleFields();

        assertEquals(17, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 6),
                board.getField(4, 6),
                board.getField(5, 6),
                board.getField(6, 6),
                board.getField(3, 7),
                board.getField(3, 5),
                board.getField(3, 4),
                board.getField(3, 3),
                board.getField(3, 2),
                board.getField(3, 1),
                board.getField(3, 0),
                board.getField(2, 7),
                board.getField(4, 7),
                board.getField(2, 5),
                board.getField(4, 5),
                board.getField(5, 4),
                board.getField(6, 3))));
        assertEquals("q", uat2.toString());
    }
}