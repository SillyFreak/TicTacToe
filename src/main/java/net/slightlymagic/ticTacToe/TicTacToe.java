/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.NewGameAction;
import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.HeadListener;
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
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in);) {
            Host host = config("ticTacToe");
            
            System.out.printf("I am %08X%n", host.getEngine().getId());
            do {
                System.out.println("enter 'start' to start a game, or press enter when your partner has started the game");
                String line = sc.nextLine();
                if("start".equalsIgnoreCase(line)) {
                    host.newGame();
                    host.publish(0, BranchManager.BRANCH_DEFAULT);
                }
            } while(host.getGame() == null);
            
            for(;;) {
                makeMove(host, sc);
                host.publish(0, BranchManager.BRANCH_DEFAULT);
            }
        }
    }
    
    private static void makeMove(Host host, Scanner sc) {
        TTTGame game = host.getGame();
        int x = sc.nextInt(), y = sc.nextInt();
        
        Action action = new PlacePieceAction(host.getEngine(), game, game.getNextPlayer(), x, y);
        host.getBranchManager().execute(action);
    }
    
    private static Host config(String cluster) throws Exception {
        final Host host = new Host(cluster);
        
        BranchManager mgr = host.getBranchManager();
        Engine engine = mgr.getEngine();
        PolybufConfig config = engine.getConfig();
        
        mgr.configure(config);
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
        
        engine.addHeadListener(new HeadListener() {
            @Override
            public void headMoved(State prevHead, State newHead) {
                print(host.getGame());
                if(!host.getGame().isGameRunning()) {
                    System.out.println("===# " + host.getGame().getWinner().getPlayerId());
                }
            }
        });
        return host;
    }
    
    private static void print(TTTGame game) {
        System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                p(game, 0, 0), p(game, 1, 0), p(game, 2, 0), //
                p(game, 0, 1), p(game, 1, 1), p(game, 2, 1), //
                p(game, 0, 2), p(game, 1, 2), p(game, 2, 2));
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
