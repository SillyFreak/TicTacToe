/**
 * ProtoOutput.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import java.util.IdentityHashMap;
import java.util.Map;

import net.slightlymagic.ticTacToe.proto.Objects.Obj;
import net.slightlymagic.ticTacToe.proto.Objects.Obj.Builder;


/**
 * <p>
 * The class ProtoOutput.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public class ProtoOutput {
    private final Map<Object, Obj> objects;
    private final ProtoConfig      config;
    private int                    id = 0;
    
    public ProtoOutput(ProtoConfig config) {
        objects = new IdentityHashMap<>();
        this.config = config;
    }
    
    @SuppressWarnings("unchecked")
    public Obj writeObject(Object o) throws ProtoSerException {
        if(!(o instanceof ProtoSerializable)) throw new ProtoSerException("not serializable: " + o);
        
        ProtoSerializable object = (ProtoSerializable) o;
        ProtoIO<ProtoSerializable> io = (ProtoIO<ProtoSerializable>) config.get(object.getTypeId());
        if(io == null) throw new ProtoSerException("No IO for type: " + object.getTypeId());
        
        Builder obj = Obj.newBuilder();
        
        //create a reference for future writeObject calls
        obj.setId(++id);
        objects.put(o, obj.build());
        
        //parse the actual object. allow objects to overwrite the type
        obj.setType(object.getTypeId());
        io.serialize(this, object, obj);
        return obj.build();
    }
}
