package com.jb.checkers2;

public class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean inBoard() {
		return this.x > -1 && this.x < 8 && this.y > -1 && this.y < 8;
	}

	public Point addPoint(Point otherPoint) {
		return new Point(this.x + otherPoint.x, this.y + otherPoint.y);
	}

	public Point subPoint(Point otherPoint) {
		return new Point(this.x - otherPoint.x, this.y - otherPoint.y);
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
