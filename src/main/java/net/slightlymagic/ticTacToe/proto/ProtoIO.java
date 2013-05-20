/**
 * ProtoIO.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import net.slightlymagic.ticTacToe.proto.Objects.Obj;


/**
 * <p>
 * The class ProtoIO.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public interface ProtoIO<T extends ProtoSerializable> {
    public void serialize(ProtoOutput out, T object, Obj.Builder obj) throws ProtoSerException;
    
    public T initialize(ProtoInput in, Obj obj) throws ProtoSerException;
    
    public void deserialize(ProtoInput in, Obj obj, T object) throws ProtoSerException;
}
