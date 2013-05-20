/**
 * ProtoConfig.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * A {@code ProtoConfig} encapsulates {@link ProtoIO}s for different object types, each identified by an
 * {@code int} that corresponds to the protobuf extension field label associated with that type (see
 * {@link ProtoSerializable}). Note that this class does not have a notion of what message type a{@code ProtoIO}
 * corresponds to, and thus does not enforce anything of the sort. Implementors are free to design {@code ProtoIO}
 * classes that work for any number and any type of messages. The user simply has to register that {@code ProtoIO}
 * with all the messages he wants.
 * </p>
 * 
 * @version V1.0 18.05.2013
 * @author SillyFreak
 */
public class ProtoConfig {
    private final Map<Integer, ProtoIO<?>> config;
    
    public ProtoConfig() {
        config = new HashMap<>();
    }
    
    public void put(int type, ProtoIO<?> io) {
        config.put(type, io);
    }
    
    public void remove(int type) {
        config.remove(type);
    }
    
    public ProtoIO<?> get(int type) {
        return config.get(type);
    }
}
