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
 * The class ProtoConfig.
 * </p>
 * 
 * @version V0.0 18.05.2013
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
