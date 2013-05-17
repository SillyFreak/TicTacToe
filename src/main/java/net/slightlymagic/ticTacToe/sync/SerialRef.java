/**
 * SerialRef.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.Serializable;


/**
 * <p>
 * The class SerialRef.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
final class SerialRef implements Serializable {
    private static final long serialVersionUID = -3760344165945528588L;
    
    public static final int   ENGINE           = 0, ENTITY = 1, STATE = 2;
    
    public final int          type, id;
    
    private SerialRef(int type, int id) {
        this.type = type;
        this.id = id;
    }
    
    public static SerialRef ref(Engine engine) {
        return new SerialRef(ENGINE, -1);
    }
    
    public static SerialRef ref(Entity entity) {
        return new SerialRef(ENTITY, entity.getId());
    }
    
    public static SerialRef ref(State state) {
        return new SerialRef(STATE, state.getId());
    }
}
