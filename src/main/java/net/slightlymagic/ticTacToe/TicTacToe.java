/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.io.IOException;
import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import net.slightlymagic.ticTacToe.sync.Action;
import net.slightlymagic.ticTacToe.sync.Engine;
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
    private static PolybufConfig config(Engine engine) {
        PolybufConfig config = new PolybufConfig();
        
        PlacePieceAction.configure(config, engine);
        
        return config;
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (Scanner sc = new Scanner(System.in);) {
            Engine eng1 = new Engine(), eng2 = new Engine();
            PolybufConfig conf1 = config(eng1), conf2 = config(eng2);
            
            
            TTTGame game1 = new TTTGame(eng1);
            TTTGame game2 = new TTTGame(eng2);
            while(game2.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                Action action1 = new PlacePieceAction(game1, game1.getNextPlayer(), x, y);
                action1.apply();
                
                Obj obj = new PolybufOutput(conf1).writeObject(action1);
                
                Action action2 = (Action) new PolybufInput(conf2).readObject(obj);
                action2.apply();
                
                
                System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                        p(game2, 0, 0), p(game2, 1, 0), p(game2, 2, 0), //
                        p(game2, 0, 1), p(game2, 1, 1), p(game2, 2, 1), //
                        p(game2, 0, 2), p(game2, 1, 2), p(game2, 2, 2));
            }
            System.out.println("===# " + game2.getWinner().getPlayerId());
        }
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getPlayerId();
    }
}
