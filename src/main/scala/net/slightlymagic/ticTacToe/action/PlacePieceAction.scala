/**
 * PlacePieceAction.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe.action

import at.pria.koza.harmonic.Action
import at.pria.koza.harmonic.Engine
import at.pria.koza.harmonic.IOFactory
import at.pria.koza.polybuf.PolybufException
import at.pria.koza.polybuf.PolybufIO
import at.pria.koza.polybuf.PolybufInput
import at.pria.koza.polybuf.PolybufOutput
import at.pria.koza.polybuf.PolybufSerializable
import at.pria.koza.polybuf.proto.Polybuf.Obj

import com.google.protobuf.GeneratedMessage.GeneratedExtension;

import net.slightlymagic.ticTacToe.TTTGame
import net.slightlymagic.ticTacToe.TTTPlayer
import net.slightlymagic.ticTacToe.proto.TTTP.PlacePieceActionP

/**
 * <p>
 * The class PlacePieceAction.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
object PlacePieceAction extends IOFactory[PlacePieceAction] {
  val FIELD = PlacePieceActionP.PLACE_PIECE_ACTION_FIELD_NUMBER
  val EXTENSION = PlacePieceActionP.placePieceAction

  def getIO(implicit engine: Engine): PolybufIO[PlacePieceAction] = new IO()

  private class IO(implicit engine: Engine) extends PolybufIO[PlacePieceAction] {
    override def extension: GeneratedExtension[Obj, PlacePieceActionP] = EXTENSION

    @throws[PolybufException]
    override def serialize(out: PolybufOutput, instance: PlacePieceAction, obj: Obj.Builder): Unit = {
      val b = PlacePieceActionP.newBuilder()
      b.setGame(instance.game.id)
      b.setPlayer(instance.player.id)
      b.setX(instance.x)
      b.setY(instance.y)

      obj.setExtension(extension, b.build())
    }

    @throws[PolybufException]
    override def initialize(in: PolybufInput, obj: Obj): PlacePieceAction = {
      val p = obj.getExtension(extension)
      val game = engine.Entities(p.getGame()).asInstanceOf[TTTGame]
      val player = engine.Entities(p.getPlayer()).asInstanceOf[TTTPlayer]
      val x = p.getX()
      val y = p.getY()

      return new PlacePieceAction(game, player, x, y)
    }
  }
}

class PlacePieceAction(val game: TTTGame, val player: TTTPlayer, val x: Int, val y: Int) extends Action with PolybufSerializable {
  //PolybufSerializable
  def typeId: Int = PlacePieceAction.FIELD

  override protected[this] def apply0(): Unit =
    game.placePiece(player, x, y)
}
