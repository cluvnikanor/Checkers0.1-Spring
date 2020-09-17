package com.jb.checkers2;

import java.util.ArrayList;
import java.util.List;

public class BlackMoves extends Moves {

	public BlackMoves(Board board) {
		super(board);
	}

	private void moveBlack(Point start, Point end) {
		board.set(start, Piece.NONE);
		if (end.y == 0) {
			board.set(end, Piece.BLACK_KING);
		} else {
			board.set(end, Piece.BLACK);
		}
		movesList.add(start);
		movesList.add(end);
	}

	private void moveBlackKing(Point start, Point end) {
		board.set(start, Piece.NONE);
		board.set(end, Piece.BLACK_KING);
		movesList.add(start);
		movesList.add(end);
	}

	private Boolean canBlackPieceEat(Point p) {
		return board.get(p) == Piece.BLACK && p.y > 1 && ((p.x > 1
				&& (board.get(p.y - 1, p.x - 1) == Piece.WHITE || board.get(p.y - 1, p.x - 1) == Piece.WHITE_KING)
				&& board.get(p.y - 2, p.x - 2) == Piece.NONE)
				|| (p.x < 6
						&& (board.get(p.y - 1, p.x + 1) == Piece.WHITE
								|| board.get(p.y - 1, p.x + 1) == Piece.WHITE_KING)
						&& board.get(p.y - 2, p.x + 2) == Piece.NONE));
	}

	private boolean canBlackKingEat(Point p) {
		if (board.get(p) == Piece.BLACK_KING) {
			for (int i = 0, xDirection = 1; i < 2; i++, xDirection = -xDirection) {
				for (int j = 0, yDirection = 1; j < 2; j++, yDirection = -yDirection) {
					for (Point q = p; q.inBoard(); q.x += xDirection, q.y += yDirection) {
						if (board.get(q) == Piece.BLACK || board.get(q) == Piece.BLACK_KING) {
							return false;
						}
						if (board.get(q) == Piece.NONE && (board.get(q.y - yDirection, q.x - xDirection) == Piece.WHITE
								|| board.get(q.y - yDirection, q.x - xDirection) == Piece.WHITE_KING)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private List<Point> blacksThatCanEat() {
		List<Point> blacksThatCanEatList = new ArrayList<Point>();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if ((board.get(y, x) == Piece.BLACK && canBlackPieceEat(new Point(x, y)))
						|| (board.get(y, x) == Piece.BLACK_KING && canBlackKingEat(new Point(x, y))))
					blacksThatCanEatList.add(new Point(x, y));
			}
		}
		return blacksThatCanEatList;
	}

	private boolean blackMove(Point start, Point end) {
		if (start.inBoard() && end.inBoard() && board.get(end) == Piece.NONE) {
			if (board.get(start) == Piece.BLACK) {
				if (end.y - start.y == -1 && (end.x - start.x == -1 || end.x - start.x == 1)) {/* move */
					moveBlack(start, end);
					return true;
				}
				if (end.y - start.y == -2 && Math.abs(end.x - start.x) == 2
						&& board.get(start.y - 1, start.x + (end.x - start.x) / 2) == Piece.WHITE) {/* eat */
					eat(start.x + (end.x - start.x) / 2, start.y + 1);
					moveBlack(start, end);
					return true;
				}
			}
			if (board.get(start) == Piece.BLACK_KING) {
				Point white = null;
				int whites = 0;
				for (Point p = start; !p.equals(end); p.x += (end.x - start.x)
						/ Math.abs(end.x - start.x), p.y += (end.y - start.y) / Math.abs(end.y - start.y)) {
					if (board.get(p) == Piece.BLACK)
						return false;
					if (board.get(p) == Piece.WHITE || board.get(p) == Piece.WHITE_KING) {
						if (whites == 0) {
							white = p;
						}
						whites++;
					}
				}
				if (whites < 2) {
					if (whites == 1) {
						eat(white);
					}
					moveBlackKing(start, end);
					return true;
				}
			}
		}
		return false;
	}

	public void blackTurn() {
		eated = false;
		List<Point> blacksThatMustEat = blacksThatCanEat();
		while (!blackMove(getStart(), getEnd())) {
		}
		if (eated == true && !(blacksThatMustEat == null)) {
			if (board.get(blacksThatMustEat.get(0)) == Piece.NONE) {
				eat(movesList.get(movesList.size() - 1));
			} else {
				eat(blacksThatMustEat.get(0)); /* Who decides which of this pieces to 'burn'? */
			}
		}

		// add eat again

	}

}
