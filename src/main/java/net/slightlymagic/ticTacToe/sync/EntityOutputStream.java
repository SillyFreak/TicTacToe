/**
 * EntityOutputStream.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * <p>
 * The class EntityOutputStream.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class EntityOutputStream extends ObjectOutputStream {
    private final Engine engine;
    
    public EntityOutputStream(Engine engine) throws IOException, SecurityException {
        super();
        this.engine = engine;
        enableReplaceObject(true);
    }
    
    public EntityOutputStream(Engine engine, OutputStream out) throws IOException {
        super(out);
        this.engine = engine;
        enableReplaceObject(true);
    }
    
    @Override
    protected Object replaceObject(Object obj) throws IOException {
        if(obj instanceof Engine) {
            if(obj != engine) {
                throw new IOException("Foreign engine: " + obj);
            }
            return SerialRef.ref(engine);
        } else if(obj instanceof Entity) {
            Entity entity = (Entity) obj;
            if(entity.getEngine() != engine) {
                throw new IOException("Foreign entity: " + entity + "@" + entity.getEngine());
            }
            return SerialRef.ref(entity);
        }
        return obj;
    }
}
