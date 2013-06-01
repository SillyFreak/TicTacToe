/**
 * TTTPlayer.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import at.pria.koza.harmonic.AEntity;
import at.pria.koza.harmonic.Engine;


/**
 * <p>
 * The class TTTPlayer.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTPlayer extends AEntity {
    private static final long serialVersionUID = -3201819660675581004L;
    
    private final int         id;
    
    public TTTPlayer(Engine engine, int id) {
        super(engine);
        this.id = id;
    }
    
    public int getPlayerId() {
        return id;
    }
    
    public TTTPiece newPiece() {
        return new TTTPiece(getEngine(), this);
    }
}
