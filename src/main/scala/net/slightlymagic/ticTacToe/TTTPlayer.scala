/**
 * TTTPlayer.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe

import at.pria.koza.harmonic.Engine
import at.pria.koza.harmonic.Entity

/**
 * <p>
 * The class TTTPlayer.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
@SerialVersionUID(-3201819660675581004L)
class TTTPlayer(engine: Engine, val playerId: Int) extends Entity {
  init(engine)

  def newPiece(): TTTPiece =
    new TTTPiece(engine, this)
}
