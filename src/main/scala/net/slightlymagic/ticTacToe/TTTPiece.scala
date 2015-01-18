/**
 * TTTPiece.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;

import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;

/**
 * <p>
 * The class TTTPiece.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
@SerialVersionUID(3506767468042989192L)
class TTTPiece(engine: Engine, val owner: TTTPlayer) extends Entity {
  init(engine)
}
