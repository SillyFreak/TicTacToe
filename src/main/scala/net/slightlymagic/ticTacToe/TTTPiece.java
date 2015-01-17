/**
 * TTTPiece.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * The class TTTPiece.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTPiece implements Entity {
    private static final long serialVersionUID = 3506767468042989192L;
    
    private final TTTPlayer   owner;
    
    public TTTPiece(Engine engine, TTTPlayer owner) {
        this.owner = owner;
    }
    
    public TTTPlayer getOwner() {
        return owner;
    }
}
