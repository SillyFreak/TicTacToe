/**
 * Action.java
 * 
 * Created on 16.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * <p>
 * The class Action.
 * </p>
 * 
 * @version V0.0 16.05.2013
 * @author SillyFreak
 */
public abstract class Action implements Serializable {
    private static final long            serialVersionUID = 5358251818076675520L;
    
    private transient Engine             engine;
    private transient List<Modification> modifications;
    
    public Action(Engine engine) {
        init(engine);
    }
    
    void init(Engine engine) {
        this.engine = engine;
        modifications = new LinkedList<Modification>();
    }
    
    public Engine getEngine() {
        return engine;
    }
    
    public void apply() {
        apply0();
    }
    
    public void revert() {
        for(ListIterator<Modification> it = modifications.listIterator(modifications.size()); it.hasPrevious();) {
            it.previous().revert();
            it.remove();
        }
    }
    
    protected abstract void apply0();
}
