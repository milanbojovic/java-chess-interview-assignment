package com.whitehatgaming.chess.board;

import com.whitehatgaming.chess.figures.Bishop;
import com.whitehatgaming.chess.figures.King;
import com.whitehatgaming.chess.figures.Knight;
import com.whitehatgaming.chess.figures.Pawn;
import com.whitehatgaming.chess.figures.Piece;
import com.whitehatgaming.chess.figures.Queen;
import com.whitehatgaming.chess.figures.Rook;
import com.whitehatgaming.chess.util.enumerations.PieceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Board {
    @Getter(AccessLevel.NONE)
    Field[][] fields;
    PieceType turn;
    PieceType checkEvent;

    public Board() {
        fields = new Field[8][8];
        turn = PieceType.WHITE;
        log.info("Chessboard created.");

        initEmptyBoard(fields);
        assemblePieces(PieceType.WHITE);
        assemblePieces(PieceType.BLACK);
    }

    private void initEmptyBoard(Field[][] fields) {
        log.info("Initialising empty board.");
        for (var i = 0; i <= 7; i++) {
            for (var j = 0; j <= 7; j++) {
                fields[i][j] = new Field(i, j, this);
            }
        }
    }

    public void testCheckCondition() {
        log.debug("Testing check condition");
        for (var i = 0; i <= 7; i++) {
            for (var j = 0; j <= 7; j++) {
                final Piece piece = getField(i, j).getPiece();
                if (piece == null || piece instanceof King) continue;
                PieceType pieceType = piece.getPieceType();
                final boolean checkCondition = piece.getAllPossibleFields().stream()
                        .map(Field::getPiece)
                        .anyMatch(pi -> pi instanceof King && pi.getPieceType() != pieceType);
                if (checkCondition) {
                    log.info("Check found!");
                    checkEvent = pieceType == PieceType.WHITE ? PieceType.BLACK : PieceType.WHITE;
                    return;
                }
            }
        }
        checkEvent = null;
    }

    private void assemblePieces(PieceType pieceType) {
        log.info("Assembling " + pieceType.toString() + " pieces.");
        if (pieceType == PieceType.BLACK) {
            assembleCastleLine(pieceType, 0, 1);
        } else {
            assembleCastleLine(pieceType, 7, 6);
        }
    }

    private void assembleCastleLine(PieceType pieceType, int kingdomRow, int pownsRow) {
        Rook rook = new Rook(fields[kingdomRow][0], this, pieceType);
        fields[kingdomRow][0].setPiece(rook);
        rook = new Rook(fields[kingdomRow][7], this, pieceType);
        fields[kingdomRow][7].setPiece(rook);

        Knight knight = new Knight(fields[kingdomRow][1], this, pieceType);
        fields[kingdomRow][1].setPiece(knight);
        knight = new Knight(fields[kingdomRow][6], this, pieceType);
        fields[kingdomRow][6].setPiece(knight);

        Bishop bishop = new Bishop(fields[kingdomRow][2], this, pieceType);
        fields[kingdomRow][2].setPiece(bishop);
        bishop = new Bishop(fields[kingdomRow][5], this, pieceType);
        fields[kingdomRow][5].setPiece(bishop);

        King king = new King(fields[kingdomRow][4], this, pieceType);
        fields[kingdomRow][4].setPiece(king);

        Queen queen = new Queen(fields[kingdomRow][3], this, pieceType);
        fields[kingdomRow][3].setPiece(queen);

        for (var j = 0; j <= 7; j++) {
            var pawn = new Pawn(fields[pownsRow][j], this, pieceType);
            fields[pownsRow][j].setPiece(pawn);
        }
    }

    public void playMove(int[] command) {
        final Field src = getField(command[1], command[0]);
        final Field dst = getField(command[3], command[2]);

        if (!src.isEmpty() && isCorrectTurn(src)) {
            if (src.getPiece().move(dst)) invertTurn();
        }
    }

    private void invertTurn() {
        if (turn == PieceType.WHITE) {
            turn = PieceType.BLACK;
        } else turn = PieceType.WHITE;
    }

    private boolean isCorrectTurn(Field field) {
        return turn == field.getPiece().getPieceType();
    }

    public Field getField(int rank, int file) {
        if (rank >= 0 && rank <= 7 && file >= 0 && file <= 7) return fields[rank][file];
        return null;
    }

    @Override
    public String toString() {
        final var sb = new StringBuilder();
        sb.append("\n   a b c d e f g h");
        if (checkEvent != null) sb.append("   " +
                "!!!WARNING!!! CHECK for player ").append(checkEvent);
        sb.append("\n __________________\n");
        for (int i = 0; i <= 7; i++) {
            sb.append(8 - i).append("|");
            for (int j = 0; j <= 7; j++) {
                sb.append(" ").append(fields[i][j]);
            }
            sb.append("|").append(8 - i).append("\n");
        }
        sb.append(" ------------------\n");
        sb.append("   a b c d e f g h\n\n");
        sb.append("Press ENTER to play the next move!\n");
        return sb.toString();
    }
}
