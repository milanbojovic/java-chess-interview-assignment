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

class KingTest {
    static Board board;

    @BeforeAll
    static void setUp() {
        board = new Board();
    }

    @Test
    void possibleFieldsInitialPosition() {
        King sut = new King(board.getField(0, 4), board, PieceType.BLACK);
        board.getField(0, 4).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(0, actual.size());
        assertEquals("k", sut.toString());
    }

    @Test
    void possibleFieldsCastleDefenseEdge() {
        King sut = new King(board.getField(1, 0), board, PieceType.BLACK);
        board.getField(1, 0).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(2, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 0),
                board.getField(2, 1))));
        assertEquals("k", sut.toString());
    }

    @Test
    void possibleFieldsCastleDefenseMiddle() {
        King sut = new King(board.getField(1, 3), board, PieceType.BLACK);
        board.getField(1, 3).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(3, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 2),
                board.getField(2, 3),
                board.getField(2, 4))));
        assertEquals("k", sut.toString());
    }

    @Test
    void possibleFieldsAttackOpponent1() {
        King sut = new King(board.getField(1, 6), board, PieceType.WHITE);
        board.getField(1, 6).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(8, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(0, 5),
                board.getField(0, 6),
                board.getField(0, 7),
                board.getField(1, 5),
                board.getField(1, 7),
                board.getField(2, 5),
                board.getField(2, 6),
                board.getField(2, 7))));
        assertEquals("K", sut.toString());
    }

    //          0 1 2 3 4 5 6 7
//              a b c d e f g h
//              ________________
//            0|R K B Q K B N R|0   0,4  -->                              black
//            1|k P P k P Q K b|1   1,0  --> 20 21                        black
//            2|               |2   13   --> 22 23 24                     black
//            3|      K     Q  |3   16   --> 05 06 07 15 17 25 26 27      white
//            4|               |4   33   --> 22 23 24 32 34 42 43 44      white
//            5|              K|5   57   --> 46 47 56                     white
//            6|P P P P P P P P|6
//            7|R K B Q K B K R|7
//             ------------------
//              a b c d e f g h
//              0 1 2 3 4 5 6 7

    @Test
    void possibleFieldsBoardCenter() {
        King sut = new King(board.getField(3, 3), board, PieceType.WHITE);
        board.getField(3, 3).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(8, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(2, 2),
                board.getField(2, 3),
                board.getField(2, 4),
                board.getField(3, 2),
                board.getField(3, 4),
                board.getField(4, 2),
                board.getField(4, 3),
                board.getField(4, 4))));
        assertEquals("K", sut.toString());
    }

    @Test
    void possibleFieldsCastleDefenseEdge2() {
        King sut = new King(board.getField(5, 7), board, PieceType.WHITE);
        board.getField(5, 7).setPiece(sut);

        final List<Field> actual = sut.getAllPossibleFields();
        assertEquals(3, actual.size());
        assertTrue(actual.containsAll(Arrays.asList(
                board.getField(4, 6),
                board.getField(4, 7),
                board.getField(5, 6))));
        assertEquals("K", sut.toString());
    }
}