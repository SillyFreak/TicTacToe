/**
 * NewGameAction.java
 * 
 * Created on 28.07.2013
 */

package net.slightlymagic.ticTacToe.action;


import net.slightlymagic.ticTacToe.TTTGame;
import net.slightlymagic.ticTacToe.proto.TTTP.NewGameActionP;
import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.polybuf.PolybufConfig;
import at.pria.koza.polybuf.PolybufException;
import at.pria.koza.polybuf.PolybufIO;
import at.pria.koza.polybuf.PolybufInput;
import at.pria.koza.polybuf.PolybufOutput;
import at.pria.koza.polybuf.PolybufSerializable;
import at.pria.koza.polybuf.proto.Polybuf.Obj;

import com.google.protobuf.GeneratedMessage.GeneratedExtension;


/**
 * <p>
 * {@code NewGameAction}
 * </p>
 * 
 * @version V0.0 28.07.2013
 * @author SillyFreak
 */
public class NewGameAction extends Action implements PolybufSerializable {
    public static final int                                     FIELD     = NewGameActionP.NEW_GAME_ACTION_FIELD_NUMBER;
    public static final GeneratedExtension<Obj, NewGameActionP> EXTENSION = NewGameActionP.newGameAction;
    
    public static PolybufIO<NewGameAction> getIO(Engine engine) {
        return new IO(engine);
    }
    
    public static void configure(PolybufConfig config, Engine engine) {
        config.put(FIELD, getIO(engine));
    }
    
    private TTTGame game;
    
    public NewGameAction(Engine engine) {
        super(engine);
    }
    
    @Override
    protected void apply0() {
        game = new TTTGame(getEngine());
    }
    
    public TTTGame getGame() {
        return game;
    }
    
    @Override
    public int getTypeId() {
        return FIELD;
    }
    
    private static class IO implements PolybufIO<NewGameAction> {
        private final Engine engine;
        
        public IO(Engine engine) {
            this.engine = engine;
        }
        
        @Override
        public void serialize(PolybufOutput out, NewGameAction object, Obj.Builder obj) throws PolybufException {
            NewGameActionP.Builder b = NewGameActionP.newBuilder();
            
            obj.setExtension(EXTENSION, b.build());
        }
        
        @Override
        public NewGameAction initialize(PolybufInput in, Obj obj) throws PolybufException {
//            NewGameActionP p = obj.getExtension(EXTENSION);
            
            return new NewGameAction(engine);
        }
        
        @Override
        public void deserialize(PolybufInput in, Obj obj, NewGameAction object) throws PolybufException {}
    }
}
