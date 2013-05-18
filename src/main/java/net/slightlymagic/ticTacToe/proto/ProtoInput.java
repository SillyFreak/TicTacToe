/**
 * ProtoInput.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import java.util.HashMap;
import java.util.Map;

import net.slightlymagic.ticTacToe.proto.Objects.Obj;


/**
 * <p>
 * The class ProtoInput.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public class ProtoInput {
    private final Map<Integer, Object> objects;
    private final ProtoConfig          config;
    
    public ProtoInput(ProtoConfig config) {
        objects = new HashMap<>();
        this.config = config;
    }
    
    public Object readObject(Obj obj) throws ProtoSerException {
        if(obj.getType() == 0) {
            if(obj.getId() == 0) {
                return null;
            } else {
                Object object = objects.get(obj.getId());
                if(object == null) {
                    throw new ProtoSerException("Unknown object: " + obj.getClass().getName() + ":" + obj.getId());
                }
                return object;
            }
        }
        
        ProtoIO<?> io = config.get(obj.getType());
        if(io == null) throw new ProtoSerException("No IO for type: " + obj.getType());
        Object object = io.deserialize(this, obj);
        
        return object;
    }
}
