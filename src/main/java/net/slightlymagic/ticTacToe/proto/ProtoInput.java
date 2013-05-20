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
    
    @SuppressWarnings("unchecked")
    public Object readObject(Obj obj) throws ProtoSerException {
        int typeId = obj.getTypeId();
        if(typeId == 0) {
            int id = obj.getId();
            if(id == 0) {
                return null;
            } else {
                Object object = objects.get(id);
                if(object == null) {
                    throw new ProtoSerException("Unknown object: " + obj.getClass().getName() + ":" + id);
                }
                return object;
            }
        }
        
        ProtoIO<ProtoSerializable> io = (ProtoIO<ProtoSerializable>) config.get(typeId);
        if(io == null) throw new ProtoSerException("No IO for type: " + typeId);
        ProtoSerializable object = io.initialize(this, obj);
        objects.put(obj.getId(), object);
        io.deserialize(this, obj, object);
        
        return object;
    }
}
