/**
 * Action.java
 * 
 * Created on 16.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.Serializable;


/**
 * <p>
 * The class Action.
 * </p>
 * 
 * @version V0.0 16.05.2013
 * @author SillyFreak
 */
public abstract class Action implements Serializable {
    private static final long serialVersionUID = 5358251818076675520L;
    
    private transient Engine  engine;
    
    public Action(Engine engine) {
        setEngine(engine);
    }
    
    void setEngine(Engine engine) {
        this.engine = engine;
    }
    
    public Engine getEngine() {
        return engine;
    }
    
    public void apply() {
        
    }
    
    protected abstract void apply0();
}
