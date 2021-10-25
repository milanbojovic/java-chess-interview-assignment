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

    static Rook sut1;
    static Rook sut2;
    static Rook sut3;
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
        sut1 = new Rook(board.getField(0, 0), board, PieceType.BLACK);
        board.getField(0, 0).setPiece(sut1);
        sut2 = new Rook(board.getField(3, 5), board, PieceType.WHITE);
        board.getField(3, 5).setPiece(sut2);
        sut3 = new Rook(board.getField(6, 7), board, PieceType.BLACK);
        board.getField(6, 7).setPiece(sut3);
    }

    @Test
    void possibleFieldsInitialPosition() {
        final List<Field> actual = sut1.getAllPossibleFields();

        assertEquals(0, actual.size());
        assertEquals("r", sut1.toString());
    }

    @Test
    void possibleFieldsMiddleFieldEnemyAttackAndFriendlyAttack() {
        final List<Field> actual = sut2.getAllPossibleFields();

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
        assertEquals("R", sut2.toString());
    }

    @Test
    void possibleFieldsAgainstInitialPositionUnderAttack() {
        final List<Field> actual = sut3.getAllPossibleFields();

        assertEquals(6, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(7, 7),
                board.getField(6, 6),
                board.getField(5, 7),
                board.getField(4, 7),
                board.getField(3, 7),
                board.getField(2, 7))));
        assertEquals("r", sut3.toString());
    }


}