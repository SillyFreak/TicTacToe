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
import at.pria.koza.polybuf.PolybufInput;
import at.pria.koza.polybuf.PolybufOutput;
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
            Engine eng1 = new Engine(), eng2 = new Engine();
            PolybufConfig conf1 = config(eng1), conf2 = config(eng2);
            
            
            TTTGame game1, game2;
            {
                NewGameAction action1 = new NewGameAction(eng1);
                game1 = execute(action1).getGame();
                
                Obj obj = new PolybufOutput(conf1).writeObject(eng1.getHead());
                
                State s = (State) new PolybufInput(conf2).readObject(obj);
                eng2.setHead(s);
                game2 = ((NewGameAction) s.getAction()).getGame();
            }
            while(game2.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                Action action1 = new PlacePieceAction(eng1, game1, game1.getNextPlayer(), x, y);
                execute(action1);
                
                Obj obj = new PolybufOutput(conf1).writeObject(eng1.getHead());
                
                State s = (State) new PolybufInput(conf2).readObject(obj);
                eng2.setHead(s);
                
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
    
    private static PolybufConfig config(Engine engine) {
        PolybufConfig config = new PolybufConfig();
        
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
        State.configure(config, engine);
        
        return config;
    }
    
    private static <T extends Action> T execute(T action) {
        Engine engine = action.getEngine();
        State state = new State(engine.getHead(), action);
        engine.setHead(state);
        return action;
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
