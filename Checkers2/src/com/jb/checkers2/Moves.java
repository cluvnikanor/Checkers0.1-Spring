package com.jb.checkers2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Moves {
	protected Board board;
	protected boolean eated = false;
	protected List<Point> movesList = new ArrayList<Point>();
	protected List<Point> eatedList = new ArrayList<Point>();

	protected Moves(Board board) {
		this.board = board;
	}

	protected void eat(Point p) {
		board.set(p, Piece.NONE);
		eatedList.add(p);
		this.eated = true;
	}

	protected void eat(int x, int y) {
		board.set(y, x, Piece.NONE);
		eatedList.add(new Point(x, y));
		this.eated = true;
	}

	protected Point getStart() {
		System.out.println("start: ");
		return scanPoint();
	}

	protected Point getEnd() {
		System.out.println("end: ");
		return scanPoint();
	}

	private Point scanPoint() {
		Integer x = null;
		Integer y = null;
		Scanner scanner = new Scanner(System.in);
		while (x == null || x < 0 || x > 7) {
			System.out.print(" x ");
			try {
				x = scanner.nextInt();
			} catch (Exception e) {
			}
		}
		while (y == null || y < 0 || y > 7) {
			System.out.print(" y ");
			try {
				y = scanner.nextInt();
			} catch (Exception e) {
			}
		}
		Point p = new Point(x, y);
		System.out.println(p);
		return p;
	}

}
