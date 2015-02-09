/**
 * TTTGame.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;

import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;
import at.pria.koza.harmonic.Modification._;

/**
 * <p>
 * The class TTTGame.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
@SerialVersionUID(-8190897474450467676L)
class TTTGame(implicit val engine: Engine) extends Entity {
  private val _board = new TTTBoard()
  private val players = List(new TTTPlayer(0), new TTTPlayer(1))

  private var next: Int = 0
  //-3: not calculated
  //-2: calculated, running
  //-1: draw
  //0+: winner
  private var _winner: Int = -3

  def board(x: Int, y: Int): TTTPiece = _board(x, y)

  def nextPlayer: TTTPlayer = players(next)

  def gameRunning: Boolean = {
    if (_winner == -3) {
      _winner = -1
      if (check() && _winner == -3) _winner = -2
    }
    _winner == -2
  }

  private[this] def check(): Boolean = {
    for (i <- 0 to 2)
      if (!(check(_board(i, 0), _board(i, 1), _board(i, 2)) &&
        check(_board(0, i), _board(1, i), _board(2, i)))) return false
    if (!(check(_board(0, 0), _board(1, 1), _board(2, 2)) &&
      check(_board(0, 2), _board(1, 1), _board(2, 0)))) return false
    return true
  }

  private[this] def check(a: TTTPiece, b: TTTPiece, c: TTTPiece): Boolean = {
    if (a == null || b == null || c == null) {
      _winner = -3
      return true
    }
    if (a.owner != b.owner || b.owner != c.owner) return true

    _winner = a.owner.playerId
    return false
  }

  def winner: Option[TTTPlayer] = {
    if (gameRunning || _winner < 0) None
    else Some(players(_winner))
  }

  def placePiece(player: TTTPlayer, x: Int, y: Int): Unit = {
    if (player.playerId != next) {
      throw new IllegalArgumentException()
    }
    if (x < 0 || x >= 3 || y < 0 || y >= 3) {
      throw new IllegalArgumentException()
    }
    if (_board(x, y) != null) {
      throw new IllegalArgumentException()
    }

    //previous piece was null; newPiece() uses modifications
    _board(x, y) = player.newPiece()
    //previous next can be computed
    next = (next + 1) % players.length
    //recomputing the winner again does not change the state
    _winner = -3

    modification {
      _board(x, y) = null
      next = (next + players.length - 1) % players.length
      _winner = -3
    }
  }
}
