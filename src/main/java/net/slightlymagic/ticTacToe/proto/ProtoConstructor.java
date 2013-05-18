/**
 * ProtoConstructor.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import net.slightlymagic.ticTacToe.proto.Objects.Obj;


/**
 * <p>
 * The class ProtoConstructor.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public interface ProtoConstructor {
    public Object construct(Obj obj) throws ProtoSerException;
}
