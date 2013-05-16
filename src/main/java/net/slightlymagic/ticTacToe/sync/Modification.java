/**
 * Modification.java
 * 
 * Created on 16.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


/**
 * <p>
 * The class Modification.
 * </p>
 * 
 * @version V0.0 16.05.2013
 * @author SillyFreak
 */
public interface Modification {
    public abstract void apply();
    
    public abstract void revert();
}
