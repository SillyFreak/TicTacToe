/**
 * TTTGame.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;

import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;
import at.pria.koza.harmonic.Modification;

/**
 * <p>
 * The class TTTGame.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
@SerialVersionUID(-8190897474450467676L)
class TTTGame(engine: Engine) extends Entity {
  init(engine)

  private val board = new TTTBoard(engine)
  private val players = List(new TTTPlayer(engine, 0), new TTTPlayer(engine, 1))

  private var next: Int = 0
  //-3: not calculated
  //-2: calculated, running
  //-1: draw
  //0+: winner
  private var winner: Int = -3

  def piece(x: Int, y: Int): TTTPiece = board(x, y)

  def nextPlayer: TTTPlayer = players(next)

  def gameRunning: Boolean = {
    if (winner == -3) {
      winner = -1
      if (check() && winner == -3) winner = -2
    }
    winner == -2
  }

  private[this] def check(): Boolean = {
    for (i <- 0 to 2)
      if (!(check(piece(i, 0), piece(i, 1), piece(i, 2)) &&
        check(piece(0, i), piece(1, i), piece(2, i)))) return false
    if (!(check(piece(0, 0), piece(1, 1), piece(2, 2)) &&
      check(piece(0, 2), piece(1, 1), piece(2, 0)))) return false
    return true
  }

  private[this] def check(a: TTTPiece, b: TTTPiece, c: TTTPiece): Boolean = {
    if (a == null || b == null || c == null) {
      winner = -3
      return true
    }
    if (a.owner != b.owner || b.owner != c.owner) return true

    winner = a.owner.playerId
    return false
  }

  //TODO Option
  def getWinner: TTTPlayer = {
    if (gameRunning) null
    else if (winner < 0) null
    else players(winner)
  }

  def placePiece(player: TTTPlayer, x: Int, y: Int): Unit =
    new PlacePieceModification(player, x, y)()

  private final class PlacePieceModification(player: TTTPlayer, x: Int, y: Int) extends Modification {

    protected[this] override def apply0(): Unit = {
      if (player.playerId != next) {
        throw new IllegalArgumentException()
      }
      if (x < 0 || x >= 3 || y < 0 || y >= 3) {
        throw new IllegalArgumentException()
      }
      if (piece(x, y) != null) {
        throw new IllegalArgumentException()
      }

      //previous piece was null; newPiece() uses modifications
      board(x, y) = player.newPiece()
      //previous next can be computed
      next = (next + 1) % players.length
      //recomputing the winner again does not change the state
      winner = -3
    }

    override def revert(): Unit = {
      board(x, y) = null
      next = (next + players.length - 1) % players.length
      winner = -3
    }
  }
}
