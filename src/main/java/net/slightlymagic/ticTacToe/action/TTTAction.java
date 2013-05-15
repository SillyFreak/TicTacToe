/**
 * TTTAction.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action;


import java.io.Serializable;


/**
 * <p>
 * The class TTTAction.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public interface TTTAction extends Serializable {
    public void apply();
}
