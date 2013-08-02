/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.io.IOException;
import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.NewGameAction;
import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.State;
import at.pria.koza.polybuf.PolybufConfig;


/**
 * <p>
 * The class TicTacToe.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TicTacToe {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (Scanner sc = new Scanner(System.in);) {
            Host host1 = new Host(), host2 = new Host();
            config(host1);
            config(host2);
            
            host1.newGame();
            host2.connectToGame(host1);
            TTTGame game1 = host1.getGame(), game2 = host2.getGame();
            
            while(game2.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                Action action = new PlacePieceAction(host1.getEngine(), game1, game1.getNextPlayer(), x, y);
                host1.getBranchManager().execute(action);
                
                host2.synchronize(host1);
                
                System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                        p(game2, 0, 0), p(game2, 1, 0), p(game2, 2, 0), //
                        p(game2, 0, 1), p(game2, 1, 1), p(game2, 2, 1), //
                        p(game2, 0, 2), p(game2, 1, 2), p(game2, 2, 2));
            }
            System.out.println("===# " + game2.getWinner().getPlayerId());
            
            System.out.println(host1.getEngine());
            for(State s = host1.getEngine().getHead(); s != null; s = s.getParent())
                System.out.println(s);
            
            System.out.println(host2.getEngine());
            for(State s = host2.getEngine().getHead(); s != null; s = s.getParent())
                System.out.println(s);
        }
    }
    
    private static void config(Host host) {
        Engine engine = host.getEngine();
        PolybufConfig config = host.getConfig();
        
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
