/**
 * TTTPlayer.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


/**
 * <p>
 * The class TTTPlayer.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTPlayer {
    private final int id;
    
    public TTTPlayer(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public TTTPiece newPiece() {
        return new TTTPiece(this);
    }
}
