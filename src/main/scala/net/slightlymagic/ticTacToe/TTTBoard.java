/**
 * TTTBoard.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * The class TTTBoard.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTBoard implements Entity {
    private static final long  serialVersionUID = 840070188013305420L;
    
    private final TTTPiece[][] board;
    
    public TTTBoard(Engine engine) {
        board = new TTTPiece[3][3];
    }
    
    public TTTPiece getPiece(int x, int y) {
        return board[x][y];
    }
    
    public void setPiece(int x, int y, TTTPiece piece) {
        board[x][y] = piece;
    }
}
