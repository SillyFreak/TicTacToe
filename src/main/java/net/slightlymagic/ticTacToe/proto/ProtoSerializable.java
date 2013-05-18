/**
 * ProtoSerializable.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import net.slightlymagic.ticTacToe.proto.Objects.Obj;


/**
 * <p>
 * The class ProtoSerializable.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public interface ProtoSerializable {
    public int getTypeId();
    
    public void serialize(ProtoOutput out, Obj.Builder obj) throws ProtoSerException;
    
    public void deserialize(ProtoInput in, Obj obj) throws ProtoSerException;
}
