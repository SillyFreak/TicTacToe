/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.NewGameAction;
import net.slightlymagic.ticTacToe.action.PlacePieceAction;

import org.jgroups.JChannel;

import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.State;
import at.pria.koza.harmonic.jGroups.JGroupsBranchAdapter;
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
            Host host1, host2;
            {
                JChannel ch = new JChannel();
                ch.setDiscardOwnMessages(true);
                host1 = new Host(ch);
                ch.connect("ticTacToe");
            }
            {
                JChannel ch = new JChannel();
                ch.setDiscardOwnMessages(true);
                host2 = new Host(ch);
                ch.connect("ticTacToe");
            }
            config(host1);
            config(host2);
            
            host1.newGame();
            host1.publish(host2.getEngine().getId(), BranchManager.BRANCH_DEFAULT);
            Thread.sleep(500);
            host2.connectToGame();
            
            for(int i = 0;; i++) {
                if(i % 2 == 0) {
                    makeMove(host1, sc);
                    host1.publish(host2.getEngine().getId(), BranchManager.BRANCH_DEFAULT);
                    Thread.sleep(500);
                    
                    if(!host2.getGame().isGameRunning()) {
                        print(host2.getGame());
                        System.out.println("===# " + host2.getGame().getWinner().getPlayerId());
                        break;
                    }
                } else {
                    makeMove(host2, sc);
                    host2.publish(host1.getEngine().getId(), BranchManager.BRANCH_DEFAULT);
                    Thread.sleep(500);
                    
                    if(!host1.getGame().isGameRunning()) {
                        print(host1.getGame());
                        System.out.println("===# " + host1.getGame().getWinner().getPlayerId());
                        break;
                    }
                }
            }
            
            System.out.println(host1.getEngine());
            for(State s = host1.getEngine().getHead(); s != null; s = s.getParent())
                System.out.println(s);
            
            System.out.println(host2.getEngine());
            for(State s = host2.getEngine().getHead(); s != null; s = s.getParent())
                System.out.println(s);
        }
    }
    
    private static void makeMove(Host current, Scanner sc) {
        TTTGame game = current.getGame();
        print(game);
        
        int x = sc.nextInt(), y = sc.nextInt();
        
        Action action = new PlacePieceAction(current.getEngine(), game, game.getNextPlayer(), x, y);
        current.getBranchManager().execute(action);
    }
    
    private static void print(TTTGame game) {
        System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                p(game, 0, 0), p(game, 1, 0), p(game, 2, 0), //
                p(game, 0, 1), p(game, 1, 1), p(game, 2, 1), //
                p(game, 0, 2), p(game, 1, 2), p(game, 2, 2));
    }
    
    private static void config(Host host) {
        JGroupsBranchAdapter adapter = host.getAdapter();
        BranchManager mgr = host.getBranchManager();
        Engine engine = mgr.getEngine();
        PolybufConfig config = engine.getConfig();
        
        mgr.configure(config);
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
        
        adapter.register(PlacePieceAction.EXTENSION);
        adapter.register(NewGameAction.EXTENSION);
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
