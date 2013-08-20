package com.tukhvatullin.chess4j.pieces;

import com.tukhvatullin.chess4j.game.Game;
import com.tukhvatullin.chess4j.game.Move;
import com.tukhvatullin.chess4j.game.response.*;

/**
 * Date: 4/1/13
 * Author: Marat Tukhvatullin pokmeptb@gmail.com
 */
public class Rook extends Piece {


  @Override
  protected char _code() {
    return 'r';
  }

  @Override
  public MoveResponse canMove(Move move, Game game, Piece pieceTo) {
    int drow = move.getRowTo() - move.getRowFrom();
    int dcol = move.getColTo() - move.getColFrom();
    if (drow * dcol != 0) {
      return new CantMoveResponse();
    }
    if (drow != 0) {
      int rStep = drow < 0 ? -1 : 1;
      for (int i = 1; i < Math.abs(drow); i++) {
        if (!game.getBoard().isEmpty(move.getColFrom(),
            move.getRowFrom() + i * rStep)) {
          return new CantMoveResponse();
        }
      }
    }
    else if (dcol != 0) {
      int cStep = dcol < 0 ? -1 : 1;
      for (int i = 1; i < Math.abs(dcol); i++) {
        if (!game.getBoard().isEmpty((char) (move.getColFrom() + i * cStep),
            move.getRowFrom())) {
          return new CantMoveResponse();
        }
      }
    }
    if (pieceTo == null) {
      return new MovenmentResponse(new Action(move));
    }
    else {
      return new AttackResponse(new Action(move));
    }
  }
}
