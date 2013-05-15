/**
 * Entity.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.Serializable;


/**
 * <p>
 * The class Entity.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public interface Entity extends Serializable {
    public Engine getEngine();
    
    public int getId();
}
