/**
 * Engine.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * The class Engine.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class Engine {
    private int                  nextId   = 0;
    private Map<Integer, Entity> entities = new HashMap<>();
    
    int newId() {
        return nextId++;
    }
    
    void put(Entity entity) {
        entities.put(entity.getId(), entity);
    }
    
    public Entity get(int id) {
        return entities.get(id);
    }
}
