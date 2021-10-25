package com.whitehatgaming.chess;

import com.whitehatgaming.UserInputFile;
import com.whitehatgaming.chess.board.Board;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class Game {

    public static void main(String[] args) throws IOException {
        log.info("Game started");
        Board chessBoard = new Board();
        log.info("Chessboard " + chessBoard);
        UserInputFile uif = new UserInputFile("data/checkmate.txt");

        int[] move = uif.nextMove();
        while (move != null) {
            System.in.read();
            log.info("Next move: " + Arrays.toString(move));
            chessBoard.playMove(move);
            chessBoard.testCheckCondition();
            log.info(chessBoard.toString());
            move = uif.nextMove();
        }
        log.info("Game finished!!!");
    }
}

///todo documentation
///todo tests
//TRY TO USE FUNCTIONAL