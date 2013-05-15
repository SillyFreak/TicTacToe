/**
 * TTTBoard.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import net.slightlymagic.ticTacToe.sync.AEntity;
import net.slightlymagic.ticTacToe.sync.Engine;


/**
 * <p>
 * The class TTTBoard.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTBoard extends AEntity {
    private static final long  serialVersionUID = 840070188013305420L;
    
    private final TTTPiece[][] board;
    
    public TTTBoard(Engine engine) {
        super(engine);
        board = new TTTPiece[3][3];
    }
    
    public TTTPiece getPiece(int x, int y) {
        return board[x][y];
    }
    
    public void setPiece(int x, int y, TTTPiece piece) {
        board[x][y] = piece;
    }
}
