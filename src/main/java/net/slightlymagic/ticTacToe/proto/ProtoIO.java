/**
 * ProtoIO.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import net.slightlymagic.ticTacToe.proto.Objects.Obj;


/**
 * <p>
 * The {@code ProtoIO} interface is used by {@link ProtoOutput} and {@link ProtoInput} to implement conversion
 * between {@link ProtoSerializable} and {@link Obj} instances. See the
 * {@link #serialize(ProtoOutput, ProtoSerializable, net.slightlymagic.ticTacToe.proto.Objects.Obj.Builder)
 * serialize}, {@link #initialize(ProtoInput, Obj) initialize} and
 * {@link #deserialize(ProtoInput, Obj, ProtoSerializable) deserialize} methods for details.
 * </p>
 * 
 * @version V1.0 20.05.2013
 * @author SillyFreak
 */
public interface ProtoIO<T extends ProtoSerializable> {
    /**
     * <p>
     * Serializes {@code object} into the given {@link Obj}. Usually, this will create one or more messages that
     * are stored as extensions in the {@code Obj}. Multiple extensions usually mean that the object has superclass
     * data that is also serialized.
     * </p>
     * 
     * @param out The {@link ProtoOutput} doing serialization
     * @param object The object to serialize
     * @param obj The {@link Obj} to serialize to
     * @throws ProtoSerException if anything goes wrong
     */
    public void serialize(ProtoOutput out, T object, Obj.Builder obj) throws ProtoSerException;
    
    /**
     * <p>
     * Returns an object as the result of deserialization. This method should not call
     * {@link ProtoInput#readObject(Obj) readObject}, because at the time {@code initialize} is called, the
     * resulting object can't be referenced by other objects to be deserialized. If {@code readObject} is called,
     * this method must be sure that any objects to be recursively deserialized don't reference this one.
     * </p>
     * 
     * @param in The {@link ProtoInput} doing deserialization
     * @param obj The {@link Obj} to deserialize from
     * @return A possibly unfinished instance that is the result of deserialization
     * @throws ProtoSerException if anything goes wrong
     */
    public T initialize(ProtoInput in, Obj obj) throws ProtoSerException;
    
    /**
     * <p>
     * Finishes deserialization into the given object. When this method is called, the instance will already be
     * stored in the {@link ProtoInput}, so {@link ProtoInput#readObject(Obj) readObject} may be called freely.
     * </p>
     * 
     * @param in The {@link ProtoInput} doing deserialization
     * @param obj The {@link Obj} to deserialize from
     * @param object The object to serialize to
     * @return A possibly unfinished instance that is the result of deserialization
     * @throws ProtoSerException if anything goes wrong
     */
    public void deserialize(ProtoInput in, Obj obj, T object) throws ProtoSerException;
}
