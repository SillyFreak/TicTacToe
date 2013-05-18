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
    private final Map<Integer, Object>           objects;
    private final Map<Integer, ProtoConstructor> constructors;
    
    public ProtoInput() {
        objects = new HashMap<>();
        constructors = new HashMap<>();
    }
    
    public void putConstructor(int type, ProtoConstructor c) {
        constructors.put(type, c);
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
        
        Object object = createInstance(obj);
        ((ProtoSerializable) object).deserialize(this, obj); //TODO make optional
        
        return object;
    }
    
    protected Object createInstance(Obj obj) throws ProtoSerException {
        ProtoConstructor pc = constructors.get(obj.getType());
        if(pc == null) throw new ProtoSerException("No constructor for proto: " + obj);
        return pc.construct(obj);
    }
}
