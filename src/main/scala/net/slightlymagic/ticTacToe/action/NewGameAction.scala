/**
 * NewGameAction.scala
 *
 * Created on 28.07.2013
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

import com.google.protobuf.GeneratedMessage.GeneratedExtension

import net.slightlymagic.ticTacToe.TTTGame
import net.slightlymagic.ticTacToe.proto.TTTP.NewGameActionP

/**
 * <p>
 * {@code NewGameAction}
 * </p>
 *
 * @version V0.0 28.07.2013
 * @author SillyFreak
 */
object NewGameAction extends IOFactory[NewGameAction] {
  val FIELD = NewGameActionP.NEW_GAME_ACTION_FIELD_NUMBER
  val EXTENSION = NewGameActionP.newGameAction

  def getIO(implicit engine: Engine): PolybufIO[NewGameAction] = new IO()

  private class IO(implicit engine: Engine) extends PolybufIO[NewGameAction] {
    override def extension: GeneratedExtension[Obj, NewGameActionP] = EXTENSION

    @throws[PolybufException]
    override def serialize(out: PolybufOutput, instance: NewGameAction, obj: Obj.Builder): Unit = {
      obj.setExtension(extension, NewGameActionP.newBuilder().build())
    }

    @throws[PolybufException]
    override def initialize(in: PolybufInput, obj: Obj): NewGameAction = {
      //val p = obj.getExtension(extension)
      return new NewGameAction()
    }
  }
}

class NewGameAction(implicit engine: Engine) extends Action with PolybufSerializable {
  //PolybufSerializable
  def typeId: Int = NewGameAction.FIELD

  private var _game: TTTGame = _
  def game: TTTGame = _game

  override protected[this] def apply0(): Unit =
    _game = new TTTGame()
}
