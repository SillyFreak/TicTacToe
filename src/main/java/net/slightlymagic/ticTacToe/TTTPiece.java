/**
 * TTTPiece.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


/**
 * <p>
 * The class TTTPiece.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTPiece {
    private final TTTPlayer   owner;
    
    public TTTPiece(TTTPlayer owner) {
        this.owner = owner;
    }
    
    public TTTPlayer getOwner() {
        return owner;
    }
}
