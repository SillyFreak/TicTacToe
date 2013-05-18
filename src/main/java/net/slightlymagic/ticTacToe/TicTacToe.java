/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.io.IOException;
import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import net.slightlymagic.ticTacToe.proto.Objects.Obj;
import net.slightlymagic.ticTacToe.proto.ProtoInput;
import net.slightlymagic.ticTacToe.proto.ProtoOutput;
import net.slightlymagic.ticTacToe.sync.Action;
import net.slightlymagic.ticTacToe.sync.Engine;


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
            Engine eng1 = new Engine();
            Engine eng2 = new Engine();
            
            
            TTTGame game1 = new TTTGame(eng1);
            TTTGame game2 = new TTTGame(eng2);
            while(game2.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                Action action1 = new PlacePieceAction(eng1, game1, game1.getNextPlayer(), x, y);
                action1.apply();
                
                Obj obj = new ProtoOutput().writeObject(action1);
                
                ProtoInput in = new ProtoInput();
                in.putConstructor(PlacePieceAction.FIELD, PlacePieceAction.getConstructor(eng2));
                Action action2 = (Action) in.readObject(obj);
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
