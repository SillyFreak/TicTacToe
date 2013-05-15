/**
 * TTTBoard.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


/**
 * <p>
 * The class TTTBoard.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTBoard {
    private final TTTPiece[][] board;
    
    public TTTBoard() {
        board = new TTTPiece[3][3];
    }
    
    public TTTPiece getPiece(int x, int y) {
        return board[x][y];
    }
    
    public void setPiece(int x, int y, TTTPiece piece) {
        board[x][y] = piece;
    }
}
