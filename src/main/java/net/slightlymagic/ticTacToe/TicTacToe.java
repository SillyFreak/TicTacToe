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
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.BranchManager.SyncCallback;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.State;
import at.pria.koza.polybuf.PolybufConfig;
import at.pria.koza.polybuf.proto.Polybuf.Obj;


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
            BranchManager mgr1 = new BranchManager(), mgr2 = new BranchManager();
            Engine eng1 = mgr1.getEngine(), eng2 = mgr2.getEngine();
            config(mgr1);
            config(mgr2);
            
            
            TTTGame game1, game2;
            {
                NewGameAction action1 = new NewGameAction(eng1);
                game1 = mgr1.execute(action1).getGame();
                
                NewGameAction action2 = update(mgr1, mgr2, "default");
                game2 = action2.getGame();
            }
            while(game2.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                Action action1 = new PlacePieceAction(eng1, game1, game1.getNextPlayer(), x, y);
                mgr1.execute(action1);
                
                update(mgr1, mgr2, "default");
                
                System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                        p(game2, 0, 0), p(game2, 1, 0), p(game2, 2, 0), //
                        p(game2, 0, 1), p(game2, 1, 1), p(game2, 2, 1), //
                        p(game2, 0, 2), p(game2, 1, 2), p(game2, 2, 2));
            }
            System.out.println("===# " + game2.getWinner().getPlayerId());
            
            System.out.println(eng1);
            for(State s = eng1.getHead(); s != null; s = s.getParent())
                System.out.println(s);
            
            System.out.println(eng2);
            for(State s = eng2.getHead(); s != null; s = s.getParent())
                System.out.println(s);
        }
    }
    
    private static void config(BranchManager mgr) {
        Engine engine = mgr.getEngine();
        PolybufConfig config = mgr.getConfig();
        
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends Action> T update(final BranchManager sender, final BranchManager receiver, final String branch) {
        SyncCallback callback = new SyncCallback() {
            @Override
            public void sendUpdateCallback(int engine, String branch, Obj state, long... ancestors) {
                long result = receiver.receiveUpdate(engine, branch, state, ancestors);
                sender.sendMissing(receiver.getEngine().getId(), branch, result, this);
            }
            
            @Override
            public void sendMissingCallback(int engine, String branch, long state, Obj... ancestors) {
                receiver.receiveMissing(engine, branch, state, ancestors);
            }
        };
        
        sender.sendUpdate(receiver.getEngine().getId(), branch, callback);
        return (T) receiver.getEngine().getHead().getAction();
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
