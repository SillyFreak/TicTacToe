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
class TTTBoard(engine: Engine) extends Entity {
  init(engine)

  private val board = new Array[TTTPiece](3 * 3)

  //    public TTTBoard(Engine engine) {

  def apply(x: Int, y: Int): TTTPiece =
    board(x + y * 3)

  def update(x: Int, y: Int, piece: TTTPiece): Unit =
    board(x + y * 3) = piece;
}