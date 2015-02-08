/**
 * TTTBoard.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;

import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;

/**
 * <p>
 * The class TTTBoard.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
@SerialVersionUID(840070188013305420L)
class TTTBoard(implicit val engine: Engine) extends Entity {
  private val board = new Array[TTTPiece](3 * 3)

  def apply(x: Int, y: Int): TTTPiece =
    board(x + y * 3)

  def update(x: Int, y: Int, piece: TTTPiece): Unit =
    board(x + y * 3) = piece;
}
