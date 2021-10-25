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

class RookTest {

    static Rook uat1;
    static Rook uat2;
    static Rook uat3;
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
        uat1 = new Rook(board.getField(0, 0), board, PieceType.BLACK);
        board.getField(0, 0).setPiece(uat1);
        uat2 = new Rook(board.getField(3, 5), board, PieceType.WHITE);
        board.getField(3, 5).setPiece(uat2);
        uat3 = new Rook(board.getField(6, 7), board, PieceType.BLACK);
        board.getField(6, 7).setPiece(uat3);
    }

    @Test
    void possibleFieldsInitialPosition() {
        final List<Field> actual = uat1.getAllPossibleFields();

        assertEquals(0, actual.size());
        assertEquals("r", uat1.toString());
    }

    @Test
    void possibleFieldsMiddleFieldEnemyAttackAndFriendlyAttack() {
        final List<Field> actual = uat2.getAllPossibleFields();

        assertEquals(11, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 5),
                board.getField(1, 5),
                board.getField(4, 5),
                board.getField(5, 5),
                board.getField(3, 6),
                board.getField(3, 7),
                board.getField(3, 4),
                board.getField(3, 3),
                board.getField(3, 2),
                board.getField(3, 1),
                board.getField(3, 0))));
        assertEquals("R", uat2.toString());
    }

    @Test
    void possibleFieldsAgainstInitialPositionUnderAttack() {
        final List<Field> actual = uat3.getAllPossibleFields();

        assertEquals(6, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(7, 7),
                board.getField(6, 6),
                board.getField(5, 7),
                board.getField(4, 7),
                board.getField(3, 7),
                board.getField(2, 7))));
        assertEquals("r", uat3.toString());
    }


}