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
    private int                    id = 0;
    
    public ProtoOutput() {
        objects = new IdentityHashMap<>();
    }
    
    public Obj writeObject(Object o) throws ProtoSerException {
        if(!(o instanceof ProtoSerializable)) throw new ProtoSerException("not serializable: " + o);
        
        ProtoSerializable object = (ProtoSerializable) o;
        Builder builder = Obj.newBuilder();
        
        //create a reference for future writeObject calls
        builder.setId(++id);
        objects.put(o, builder.build());
        
        //parse the actual object. allow objects to overwrite the type
        builder.setType(object.getTypeId());
        object.serialize(this, builder); //TODO make optional
        return builder.build();
    }
}
