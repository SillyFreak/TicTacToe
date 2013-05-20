/**
 * ProtoSerializable.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


/**
 * <p>
 * {@code ProtoSerializable} marks a class as serializable by the {@link ProtoOutput} and {@link ProtoInput}
 * classes. A {@code ProtoSerializable} class provides a {@linkplain #getTypeId() type id} that corresponds to the
 * protobuf extension field id. For example, consider this message:
 * </p>
 * 
 * <pre>
 * message Example {
 *     extend Obj {
 *         optional Example example = 100;
 *     }
 *     
 *     optional string value = 1;
 * }
 * </pre>
 * 
 * <p>
 * The {@code Example} message declares the {@code Obj}-extension {@code example = 100}, thus its ID is 100.
 * Protobuf requires that extension field labels are unique, thus the number 100 unambiguously refers to the
 * {@code Example} message.
 * </p>
 * <p>
 * This example shows how a certain {@code ProtoSerializable}'s state will be serialized: the {@code Example} java
 * class that has a {@code value} field will be converted to an {@code Example} message by its corresponding
 * {@link ProtoIO}. That message will then be stored in {@code Obj}'s {@code example} extension field. A subclass
 * of {@code Example} will declare its own message type that contains only those fields declared in the class, not
 * any inherited fields. The serialized form will have two extensions set, and the {@link ProtoIO} code for
 * {@code Example} can be reused by the subclass' {@link ProtoIO}.
 * </p>
 * 
 * @version V1.0 18.05.2013
 * @author SillyFreak
 */
public interface ProtoSerializable {
    /**
     * <p>
     * </p>
     * 
     * @return
     */
    public int getTypeId();
}
