package com.whitehatgaming.chess.board;

import com.whitehatgaming.chess.figures.Knight;
import com.whitehatgaming.chess.util.enumerations.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BoardTest {

    static Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testBoardInitializationFieldsAndPieceCounts() {
        int whiteCnt = 0;
        int blackCnt = 0;
        for (var i = 0; i <= 7; i++) {
            for (var j = 0; j <= 7; j++) {
                final Field field = board.getField(i, j);
                assertNotNull(field);

                if (!field.isEmpty()) {
                    if (field.getPiece().getPieceType() == PieceType.WHITE) whiteCnt++;
                    else blackCnt++;
                }
            }
        }
        assertEquals(16, whiteCnt);
        assertEquals(16, blackCnt);
    }

    @Test
    void testBoardInitializationPiecePositions() {
        assertBlackPiecesLocations();
        assertWhitePiecesLocations();
    }

    private void assertWhitePiecesLocations() {
        assertEquals("R", getPieceForField(7, 0));
        assertEquals("N", getPieceForField(7, 1));
        assertEquals("B", getPieceForField(7, 2));
        assertEquals("Q", getPieceForField(7, 3));
        assertEquals("K", getPieceForField(7, 4));
        assertEquals("B", getPieceForField(7, 5));
        assertEquals("N", getPieceForField(7, 6));
        assertEquals("R", getPieceForField(7, 7));
        assertEquals("P", getPieceForField(6, 0));
        assertEquals("P", getPieceForField(6, 1));
        assertEquals("P", getPieceForField(6, 2));
        assertEquals("P", getPieceForField(6, 3));
        assertEquals("P", getPieceForField(6, 4));
        assertEquals("P", getPieceForField(6, 5));
        assertEquals("P", getPieceForField(6, 6));
        assertEquals("P", getPieceForField(6, 7));
    }

    private void assertBlackPiecesLocations() {
        assertEquals("r", getPieceForField(0, 0));
        assertEquals("n", getPieceForField(0, 1));
        assertEquals("b", getPieceForField(0, 2));
        assertEquals("q", getPieceForField(0, 3));
        assertEquals("k", getPieceForField(0, 4));
        assertEquals("b", getPieceForField(0, 5));
        assertEquals("n", getPieceForField(0, 6));
        assertEquals("r", getPieceForField(0, 7));
        assertEquals("p", getPieceForField(1, 0));
        assertEquals("p", getPieceForField(1, 1));
        assertEquals("p", getPieceForField(1, 2));
        assertEquals("p", getPieceForField(1, 3));
        assertEquals("p", getPieceForField(1, 4));
        assertEquals("p", getPieceForField(1, 5));
        assertEquals("p", getPieceForField(1, 6));
        assertEquals("p", getPieceForField(1, 7));
    }

    @Test
    void testTurnChange() {
        //White plays first
        assertEquals(PieceType.WHITE, board.getTurn());

        //Valid move
        board.playMove(new int[]{5, 6, 5, 5});
        assertEquals(PieceType.BLACK, board.getTurn());

        //Invalid move
        board.playMove(new int[]{0, 1, 1, 1});
        assertEquals(PieceType.BLACK, board.getTurn());
    }

    @Test
    void testCheckCondition() {
        Knight knight = new Knight(board.getField(1, 6), board, PieceType.WHITE);
        board.getField(1, 6).setPiece(knight);

        assertNull(board.getCheckEvent());
        board.testCheckCondition();
        assertEquals(PieceType.BLACK, board.getCheckEvent());
    }

    private String getPieceForField(int i, int i2) {
        return board.getField(i, i2).getPiece().toString();
    }
}