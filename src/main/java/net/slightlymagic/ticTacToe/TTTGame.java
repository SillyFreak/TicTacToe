/**
 * TTTGame.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


/**
 * <p>
 * The class TTTGame.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TTTGame {
    private final TTTBoard    board;
    private final TTTPlayer[] players;
    
    //-3: not calculated
    //-2: calculated, running
    //-1: draw
    //0+: winner
    private int               next, winner;
    
    public TTTGame() {
        board = new TTTBoard();
        players = new TTTPlayer[] {new TTTPlayer(0), new TTTPlayer(1)};
        next = 0;
        winner = -3;
    }
    
    public TTTPiece getPiece(int x, int y) {
        return board.getPiece(x, y);
    }
    
    public TTTPlayer getNextPlayer() {
        return players[next];
    }
    
    public boolean isGameRunning() {
        if(winner == -3) {
            winner = -1;
            if(check() && winner == -3) winner = -2;
        }
        return winner == -2;
    }
    
    private boolean check() {
        for(int i = 0; i < 3; i++)
            if(!(check(getPiece(i, 0), getPiece(i, 1), getPiece(i, 2)) && check(getPiece(0, i), getPiece(1, i),
                    getPiece(2, i)))) return false;
        if(!(check(getPiece(0, 0), getPiece(1, 1), getPiece(2, 2)) && check(getPiece(0, 2), getPiece(1, 1),
                getPiece(2, 0)))) return false;
        return true;
    }
    
    private boolean check(TTTPiece a, TTTPiece b, TTTPiece c) {
        if(a == null || b == null || c == null) {
            winner = -3;
            return true;
        }
        if(a.getOwner() != b.getOwner() || b.getOwner() != c.getOwner()) return true;
        
        winner = a.getOwner().getId();
        return false;
    }
    
    public TTTPlayer getWinner() {
        if(isGameRunning()) return null;
        return winner < 0? null:players[winner];
    }
    
    public void placePiece(TTTPlayer player, int x, int y) {
        if(player.getId() != next) {
            throw new IllegalArgumentException();
        }
        if(x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException();
        }
        if(getPiece(x, y) != null) {
            throw new IllegalArgumentException();
        }
        
        board.setPiece(x, y, player.newPiece());
        next = (next + 1) % players.length;
        winner = -3;
    }
}
