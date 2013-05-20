/**
 * PlacePieceAction.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action;


import net.slightlymagic.ticTacToe.TTTGame;
import net.slightlymagic.ticTacToe.TTTPlayer;
import net.slightlymagic.ticTacToe.proto.Objects.Obj;
import net.slightlymagic.ticTacToe.proto.Objects.Obj.Builder;
import net.slightlymagic.ticTacToe.proto.ProtoConfig;
import net.slightlymagic.ticTacToe.proto.ProtoIO;
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
    
    public static ProtoIO<PlacePieceAction> getIO(Engine engine) {
        return new IO(engine);
    }
    
    public static void configure(ProtoConfig config, Engine engine) {
        config.put(FIELD, getIO(engine));
    }
    
    private final TTTGame   game;
    private final TTTPlayer player;
    private final int       x, y;
    
    public PlacePieceAction(TTTGame game, TTTPlayer player, int x, int y) {
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
    
    private static class IO implements ProtoIO<PlacePieceAction> {
        private final Engine engine;
        
        public IO(Engine engine) {
            this.engine = engine;
        }
        
        @Override
        public void serialize(ProtoOutput out, PlacePieceAction object, Builder obj) throws ProtoSerException {
            PlacePieceActionP.Builder b = PlacePieceActionP.newBuilder();
            b.setGame(object.game.getId());
            b.setPlayer(object.player.getId());
            b.setX(object.x);
            b.setY(object.y);
            
            obj.setExtension(EXTENSION, b.build());
        }
        
        @Override
        public PlacePieceAction initialize(ProtoInput in, Obj obj) throws ProtoSerException {
            PlacePieceActionP p = obj.getExtension(EXTENSION);
            TTTGame game = (TTTGame) engine.get(p.getGame());
            TTTPlayer player = (TTTPlayer) engine.get(p.getPlayer());
            int x = p.getX(), y = p.getY();
            
            return new PlacePieceAction(game, player, x, y);
        }
        
        @Override
        public void deserialize(ProtoInput in, Obj obj, PlacePieceAction object) throws ProtoSerException {}
    }
}
