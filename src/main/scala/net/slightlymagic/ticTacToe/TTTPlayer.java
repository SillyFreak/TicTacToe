/**
 * TTTPlayer.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * The class TTTPlayer.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTPlayer implements Entity {
    private static final long serialVersionUID = -3201819660675581004L;
    
    private final int         id;
    
    public TTTPlayer(Engine engine, int id) {
        this.id = id;
    }
    
    public int getPlayerId() {
        return id;
    }
    
    public TTTPiece newPiece() {
        return new TTTPiece(getEngine(), this);
    }
}
