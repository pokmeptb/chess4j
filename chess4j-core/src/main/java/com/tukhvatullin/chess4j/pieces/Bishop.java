package com.tukhvatullin.chess4j.pieces;

import com.tukhvatullin.chess4j.game.Game;
import com.tukhvatullin.chess4j.game.Move;

/**
 * Date: 4/1/13
 * Author: Marat Tukhvatullin pokmeptb@gmail.com
 */
public class Bishop extends Piece {
    @Override
    protected char _code() {
        return 'b';
    }

    @Override
    public Move.Type canMove(Move move, Game game, Piece pieceTo) {
        int drow = move.getRowTo() - move.getRowFrom();
        int dcol = move.getColTo() - move.getColFrom();
        if (Math.abs(dcol) != Math.abs(drow)) {
            return Move.Type.CANTMOVE;
        }
        //check path
        int rStep = drow < 0 ? -1 : 1;
        int cStep = dcol < 0 ? -1 : 1;
        for (int i = 1; i < Math.abs(drow); i++) {
            if (!game.getBoard().isEmpty((char) (move.getColFrom() + i * cStep),
                    move.getRowFrom() + i * rStep)) {
                return Move.Type.CANTMOVE;
            }
        }
        if (pieceTo == null) {
            return Move.Type.MOVENMENT;
        } else {
            return Move.Type.ATTACK;
        }
    }
}
