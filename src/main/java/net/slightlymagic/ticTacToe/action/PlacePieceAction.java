/**
 * PlacePieceAction.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action;


import net.slightlymagic.ticTacToe.TTTGame;
import net.slightlymagic.ticTacToe.TTTPlayer;
import net.slightlymagic.ticTacToe.proto.Objects.Obj;
import net.slightlymagic.ticTacToe.proto.ProtoConstructor;
import net.slightlymagic.ticTacToe.proto.ProtoInput;
import net.slightlymagic.ticTacToe.proto.ProtoOutput;
import net.slightlymagic.ticTacToe.proto.ProtoSerException;
import net.slightlymagic.ticTacToe.proto.ProtoSerializable;
import net.slightlymagic.ticTacToe.proto.TTTP.PlacePieceActionP;
import net.slightlymagic.ticTacToe.sync.Action;
import net.slightlymagic.ticTacToe.sync.Engine;

import com.google.protobuf.GeneratedMessage.GeneratedExtension;


/**
 * <p>
 * The class PlacePieceAction.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class PlacePieceAction extends Action implements ProtoSerializable {
    public static final int                                        FIELD     = PlacePieceActionP.PLACE_PIECE_ACTION_FIELD_NUMBER;
    public static final GeneratedExtension<Obj, PlacePieceActionP> EXTENSION = PlacePieceActionP.placePieceAction;
    
    public static ProtoConstructor getConstructor(Engine engine) {
        return new PConstructor(engine);
    }
    
    private final TTTGame   game;
    private final TTTPlayer player;
    private final int       x, y;
    
    public PlacePieceAction(Engine engine, TTTGame game, TTTPlayer player, int x, int y) {
        super(engine);
        this.game = game;
        this.player = player;
        this.x = x;
        this.y = y;
    }
    
    @Override
    protected void apply0() {
        game.placePiece(player, x, y);
    }
    
    @Override
    public int getTypeId() {
        return FIELD;
    }
    
    @Override
    public void serialize(ProtoOutput out, Obj.Builder obj) throws ProtoSerException {
        PlacePieceActionP.Builder b = PlacePieceActionP.newBuilder();
        b.setGame(game.getId());
        b.setPlayer(player.getId());
        b.setX(x);
        b.setY(y);
        
        obj.setExtension(EXTENSION, b.build());
    }
    
    @Override
    public void deserialize(ProtoInput in, Obj obj) throws ProtoSerException {}
    
    private static class PConstructor implements ProtoConstructor {
        private final Engine engine;
        
        public PConstructor(Engine engine) {
            this.engine = engine;
        }
        
        @Override
        public Object construct(Obj obj) throws ProtoSerException {
            PlacePieceActionP p = obj.getExtension(EXTENSION);
            TTTGame game = (TTTGame) engine.get(p.getGame());
            TTTPlayer player = (TTTPlayer) engine.get(p.getPlayer());
            int x = p.getX(), y = p.getY();
            
            return new PlacePieceAction(engine, game, player, x, y);
        }
    }
}
