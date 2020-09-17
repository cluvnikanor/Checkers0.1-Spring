package com.jb.checkers01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
@RequestMapping("/checkers")
public class CheckersController {
	@Autowired
	Moves moves;

//	@GetMapping("/click")
//	public ResponseEntity<String[][]> click2(@RequestParam(name = "y") int y, @RequestParam(name = "x") int x) {
//		moves.move2(new Point(x, y));
//		return new ResponseEntity<String[][]>(moves.board.getBoardShortString(), HttpStatus.OK);
//	}

//	@GetMapping("/move")
//	public ResponseEntity<CheckersStatus> move2(@RequestParam(name = "y") int startY,
//			@RequestParam(name = "x") int startX, @RequestParam(name = "y") int endY,
//			@RequestParam(name = "x") int endX) {
//		moves.move(new Point(startX, startY), new Point(endX, endY));
//		CheckersStatus status = new CheckersStatus(moves.board.getBoardShortString(), moves.isWhiteTurn);
//		return new ResponseEntity<CheckersStatus>(status, HttpStatus.OK);
//	}

	@GetMapping("/move")
	public ResponseEntity<String[][]> move(@RequestParam(name = "y0") int startY, @RequestParam(name = "x0") int startX,
			@RequestParam(name = "y1") int endY, @RequestParam(name = "x1") int endX) {
		moves.move(new Point(startX, startY), new Point(endX, endY));
		return new ResponseEntity<String[][]>(moves.board.getBoardShortString(), HttpStatus.OK);
	}

	@GetMapping("/isWhiteTurn")
	public ResponseEntity<Boolean> isWhiteTurn() {
		return new ResponseEntity<Boolean>(moves.isWhiteTurn(), HttpStatus.OK);
	}

	@GetMapping("/restart")
	public ResponseEntity<String[][]> restart() {
		moves = new Moves();
		return new ResponseEntity<String[][]>(moves.board.getBoardShortString(), HttpStatus.OK);
	}

//	@GetMapping("/restart")
//	public ResponseEntity<CheckersStatus> restart2() {
//		moves = new Moves();
//		CheckersStatus status = new CheckersStatus(moves.board.getBoardShortString(), moves.isWhiteTurn);
//		return new ResponseEntity<CheckersStatus>(status, HttpStatus.OK);
//	}

}
