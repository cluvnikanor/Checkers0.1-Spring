package com.jb.checkers2;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		Board board = new Board();
		board.printBoard();
		WhiteMoves whiteMoves = new WhiteMoves(board);
		BlackMoves blackMoves = new BlackMoves(board);
		while (true) {
			whiteMoves.whiteTurn();
			board.printBoard();
			blackMoves.blackTurn();
			board.printBoard();
		}

	}

}
