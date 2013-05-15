/**
 * EntityRef.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.Serializable;


/**
 * <p>
 * The class EntityRef.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
final class EntityRef implements Serializable {
    private static final long serialVersionUID = -3760344165945528588L;
    
    public final int          id;
    
    public EntityRef(Entity entity) {
        this(entity.getId());
    }
    
    public EntityRef(int id) {
        this.id = id;
    }
}
