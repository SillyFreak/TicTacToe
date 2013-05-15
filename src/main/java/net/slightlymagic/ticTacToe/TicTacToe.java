/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import java.util.Scanner;

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
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            Engine eng = new Engine();
            
            
            TTTGame game = new TTTGame(eng);
            while(game.isGameRunning()) {
                int x = sc.nextInt(), y = sc.nextInt();
                
                game.placePiece(game.getNextPlayer(), x, y);
                
                System.out.printf("%s%s%s|%n%s%s%s|%n%s%s%s|%n", //
                        p(game, 0, 0), p(game, 1, 0), p(game, 2, 0), //
                        p(game, 0, 1), p(game, 1, 1), p(game, 2, 1), //
                        p(game, 0, 2), p(game, 1, 2), p(game, 2, 2));
            }
            System.out.println("===# " + game.getWinner().getId());
        }
    }
    
    private static String p(TTTGame game, int x, int y) {
        TTTPiece piece = game.getPiece(x, y);
        return piece == null? " ":"" + piece.getOwner().getId();
    }
}
