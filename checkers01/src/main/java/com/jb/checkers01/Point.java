package com.jb.checkers01;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point overPoint) {
		this.x = overPoint.x;
		this.y = overPoint.y;
	}

	public boolean inBoard() {
		return this.x > -1 && this.x < 8 && this.y > -1 && this.y < 8;
	}

	@Override
	public boolean equals(Object otherPoint) {
		return this.getClass() == otherPoint.getClass() && this.x == ((Point) otherPoint).x
				&& this.y == ((Point) otherPoint).y;
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + ")";
	}

}
