package com.jb.checkers01;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Board {
	private Piece[][] pieces = {
			{ Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE },
			{ Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE },
			{ Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE, Piece.NONE, Piece.WHITE },
			{ Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE },
			{ Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE, Piece.NONE },
			{ Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE },
			{ Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK },
			{ Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE, Piece.BLACK, Piece.NONE } };

	public void set(Point point, Piece piece) {
		pieces[point.y][point.x] = piece;
	}

	public void set(int y, int x, Piece piece) {
		pieces[y][x] = piece;
	}

	public Piece get(Point point) {
		return pieces[point.y][point.x];
	}

	public Piece get(int y, int x) {
		return pieces[y][x];
	}

	public String[][] getBoardShortString() {
		String[][] boardShortString = new String[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				switch (pieces[i][j]) {
				case BLACK:
					boardShortString[i][j] = "B";
					break;
				case WHITE:
					boardShortString[i][j] = "W";
					break;
				case NONE:
					boardShortString[i][j] = "N";
					break;
				case BLACK_KING:
					boardShortString[i][j] = "BK";
					break;
				case WHITE_KING:
					boardShortString[i][j] = "WK";
					break;
				}
			}
		}
		return boardShortString;
	}

	public void printBoardWithIndexes() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(pieces[i][j] + " ");
				if (pieces[i][j] == Piece.NONE)
					System.out.print(" ");
			}
			System.out.println(i);
		}
		for (int i = 0; i < 8; i++) {
			System.out.print("  " + i + "   ");
		}
		System.out.println();
	}

}
