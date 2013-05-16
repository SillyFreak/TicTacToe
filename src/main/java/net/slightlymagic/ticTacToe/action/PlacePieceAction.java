/**
 * PlacePieceAction.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action;


import net.slightlymagic.ticTacToe.TTTGame;
import net.slightlymagic.ticTacToe.TTTPlayer;
import net.slightlymagic.ticTacToe.sync.Action;
import net.slightlymagic.ticTacToe.sync.Engine;


/**
 * <p>
 * The class PlacePieceAction.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class PlacePieceAction extends Action {
    private static final long serialVersionUID = 8511021883726165526L;
    
    private final TTTGame     game;
    private final TTTPlayer   player;
    private final int         x, y;
    
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
}
