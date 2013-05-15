/**
 * PlacePieceAction.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action;


import net.slightlymagic.ticTacToe.TTTGame;
import net.slightlymagic.ticTacToe.TTTPlayer;


/**
 * <p>
 * The class PlacePieceAction.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class PlacePieceAction implements TTTAction {
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
    public void apply() {
        game.placePiece(player, x, y);
    }
}
