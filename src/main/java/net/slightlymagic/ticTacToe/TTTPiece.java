/**
 * TTTPiece.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import net.slightlymagic.ticTacToe.sync.Engine;
import net.slightlymagic.ticTacToe.sync.Entity;


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
    
    private final Engine      engine;
    private final int         id;
    private final TTTPlayer   owner;
    
    public TTTPiece(Engine engine, TTTPlayer owner) {
        this.engine = engine;
        id = engine.newId();
        engine.put(this);
        
        this.owner = owner;
    }
    
    public Engine getEngine() {
        return engine;
    }
    
    public int getId() {
        return id;
    }
    
    public TTTPlayer getOwner() {
        return owner;
    }
}
