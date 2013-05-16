/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import net.slightlymagic.ticTacToe.sync.Action;
import net.slightlymagic.ticTacToe.sync.Engine;
import net.slightlymagic.ticTacToe.sync.EntityInputStream;
import net.slightlymagic.ticTacToe.sync.EntityOutputStream;


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
                
                byte[] buf;
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream oos = new EntityOutputStream(eng1, baos);) {
                    
                    oos.writeObject(action1);
                    oos.flush();
                    buf = baos.toByteArray();
                }
                
                try (ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                        ObjectInputStream ois = new EntityInputStream(eng2, bais)) {
                    
                    Action action2 = (Action) ois.readObject();
                    action2.apply();
                }
                
                
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
