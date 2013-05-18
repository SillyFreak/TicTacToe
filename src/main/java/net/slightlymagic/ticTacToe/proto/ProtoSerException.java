/**
 * ProtoSerException.java
 * 
 * Created on 18.05.2013
 */

package net.slightlymagic.ticTacToe.proto;


import java.io.IOException;


/**
 * <p>
 * The class ProtoSerException.
 * </p>
 * 
 * @version V0.0 18.05.2013
 * @author SillyFreak
 */
public class ProtoSerException extends IOException {
    private static final long serialVersionUID = -2257893361939230762L;
    
    public ProtoSerException() {}
    
    public ProtoSerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ProtoSerException(String message) {
        super(message);
    }
    
    public ProtoSerException(Throwable cause) {
        super(cause);
    }
}
