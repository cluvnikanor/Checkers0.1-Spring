package com.jb.checkers01;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Moves {
	public Board board = new Board();
	private boolean eated = false;
	private List<Point> movesList = new ArrayList<Point>();
	private List<Point> eatedList = new ArrayList<Point>();
	private boolean isWhiteTurn = true;
	final private Point overBoardPoint = new Point(8, 8);

	public void move(Point start, Point end) {
		if (isWhiteTurn) {
			whiteTurn(start, end);
		} else {
			blackTurn(start, end);
		}
		System.out.println("move: " + start + "," + end + " isWhiteTurn = " + isWhiteTurn);
	}

	private void eat(Point p) {
		eat(p.x, p.y);
	}

	private void eat(int x, int y) {
		board.set(y, x, Piece.NONE);
		eatedList.add(new Point(x, y));
		this.eated = true;
	}

	private void moveWhite(Point start, Point end) {
		System.out.println("moveWhite: " + start + "," + end);
		board.set(start, Piece.NONE);
		if (end.y == 7) {
			board.set(end, Piece.WHITE_KING);
		} else {
			board.set(end, Piece.WHITE);
		}
		movesList.add(start);
		movesList.add(end);
	}

	private void moveWhiteKing(Point start, Point end) {
		board.set(start, Piece.NONE);
		board.set(end, Piece.WHITE_KING);
		movesList.add(start);
		movesList.add(end);
	}

	private Boolean canWhitePieceEat(Point p) {
		return board.get(p) == Piece.WHITE && p.y < 6 && ((p.x > 1
				&& (board.get(p.y + 1, p.x - 1) == Piece.BLACK || board.get(p.y + 1, p.x - 1) == Piece.BLACK_KING)
				&& board.get(p.y + 2, p.x - 2) == Piece.NONE)
				|| (p.x < 6
						&& (board.get(p.y + 1, p.x + 1) == Piece.BLACK
								|| board.get(p.y + 1, p.x + 1) == Piece.BLACK_KING)
						&& board.get(p.y + 2, p.x + 2) == Piece.NONE));
	}

	private boolean canWhiteKingEat(Point p) {
		if (board.get(p) == Piece.WHITE_KING) {
			for (int i = 0, xDirection = 1; i < 2; i++, xDirection = -xDirection) {
				for (int j = 0, yDirection = 1; j < 2; j++, yDirection = -yDirection) {
					for (Point q = new Point(p); q.inBoard(); q.x += xDirection, q.y += yDirection) {
						if (board.get(q) == Piece.WHITE || board.get(q) == Piece.WHITE_KING) {
							break;
						}
						if (board.get(q) == Piece.NONE && (board.get(q.y - yDirection, q.x - xDirection) == Piece.BLACK
								|| board.get(q.y - yDirection, q.x - xDirection) == Piece.BLACK_KING)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	private List<Point> whitesThatCanEat() {
		List<Point> whitesThatCanEatList = new ArrayList<Point>();
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				if ((board.get(y, x) == Piece.WHITE && canWhitePieceEat(new Point(x, y)))
						|| (board.get(y, x) == Piece.WHITE_KING && canWhiteKingEat(new Point(x, y))))
					whitesThatCanEatList.add(new Point(x, y));
			}
		}
		return whitesThatCanEatList;
	}

	private boolean whiteMove(Point start, Point end) {
		if (start.inBoard() && end.inBoard() && board.get(end) == Piece.NONE) {
			if (board.get(start) == Piece.WHITE) {
				if (end.y - start.y == 1 && (end.x - start.x == -1 || end.x - start.x == 1)) {/* move */
					moveWhite(start, end);
					return true;
				}
				if (end.y - start.y == 2 && Math.abs(end.x - start.x) == 2
						&& board.get(start.y + 1, start.x + (end.x - start.x) / 2) == Piece.BLACK) {/* eat */
					eat(start.x + (end.x - start.x) / 2, start.y + 1);
					moveWhite(start, end);
					return true;
				}
			}
			if (board.get(start) == Piece.WHITE_KING) {
				Point black = overBoardPoint;
				int blacks = 0;
				for (Point p = new Point(start); !p.equals(end); p.x += (end.x - start.x)
						/ Math.abs(end.x - start.x), p.y += (end.y - start.y) / Math.abs(end.y - start.y)) {
					if (board.get(p) == Piece.WHITE)
						return false;
					if (board.get(p) == Piece.BLACK || board.get(p) == Piece.BLACK_KING) {
						if (blacks == 0) {
							black = p;
						}
						blacks++;
					}
				}
				if (blacks < 2) {
					if (blacks == 1) {
						eat(black);
					}
					moveWhiteKing(start, end);
					return true;
				}
			}
		}
		return false;
	}

	private void whiteTurn(Point start, Point end) {
		eated = false;
		List<Point> whitesThatMustEat = whitesThatCanEat();
		whiteMove(start, end);
		if (!eated && !whitesThatMustEat.isEmpty()) {
			if (board.get(whitesThatMustEat.get(0)) == Piece.NONE) {
				eat(movesList.get(movesList.size() - 1));
			} else {
				eat(whitesThatMustEat.get(0)); /* Who decides which of this pieces to 'burn'? */
			}
		}

		// add eat again

		isWhiteTurn = false;
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
					eat(start.x + (end.x - start.x) / 2, start.y - 1);
					moveBlack(start, end);
					return true;
				}
			}
			if (board.get(start) == Piece.BLACK_KING) {
				Point white = overBoardPoint;
				int whites = 0;
				for (Point p = new Point(start); !p.equals(end); p.x += (end.x - start.x)
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

	private void blackTurn(Point start, Point end) {
		eated = false;
		List<Point> blacksThatMustEat = blacksThatCanEat();
		blackMove(start, end);
		if (!eated && !blacksThatMustEat.isEmpty()) {
			if (board.get(blacksThatMustEat.get(0)) == Piece.NONE) {
				eat(movesList.get(movesList.size() - 1));
			} else {
				eat(blacksThatMustEat.get(0)); /* Who decides which of this pieces to 'burn'? */
			}
		}

		/* add eat again */

		isWhiteTurn = true;
	}

	public boolean isWhiteTurn() {
		return isWhiteTurn;
	}

//	public void move2(Point point) {
//		// TODO Auto-generated method stub
//
//	}

}