/**
 * EntityInputStream.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.sync;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;


/**
 * <p>
 * The class EntityInputStream.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class EntityInputStream extends ObjectInputStream {
    private final Engine engine;
    
    public EntityInputStream(Engine engine) throws IOException, SecurityException {
        super();
        this.engine = engine;
        enableResolveObject(true);
    }
    
    public EntityInputStream(Engine engine, InputStream in) throws IOException {
        super(in);
        this.engine = engine;
        enableResolveObject(true);
    }
    
    @Override
    protected Object resolveObject(Object obj) throws IOException {
        if(obj instanceof EntityRef) {
            Entity entity = engine.get(((EntityRef) obj).id);
            if(entity == null) throw new IOException("unknown entity");
            return entity;
        }
        return super.resolveObject(obj);
    }
}
