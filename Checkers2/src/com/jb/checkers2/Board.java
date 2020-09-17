package com.jb.checkers2;

public class Board {
	public static Piece[][] pieces = {
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

	public void printBoard() {
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
