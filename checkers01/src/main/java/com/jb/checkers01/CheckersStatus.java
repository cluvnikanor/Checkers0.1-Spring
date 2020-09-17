package com.jb.checkers01;

public class CheckersStatus {
	public String[][] boardStrings;
	public Boolean isWhiteTurn;
	
	public CheckersStatus(String[][] boardStrings, Boolean isWhiteTurn) {
		this.boardStrings = boardStrings;
		this.isWhiteTurn = isWhiteTurn;
	}
	
	
}
