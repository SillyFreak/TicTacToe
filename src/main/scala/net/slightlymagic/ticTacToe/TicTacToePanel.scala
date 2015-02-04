/**
 * TicTacToePanel.scala
 *
 * Created on 08.08.2013
 */

package net.slightlymagic.ticTacToe

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent

import javax.swing.AbstractAction
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.border.CompoundBorder

import net.slightlymagic.ticTacToe.action.PlacePieceAction

import at.pria.koza.harmonic.Action
import at.pria.koza.harmonic.Engine
import at.pria.koza.harmonic.Engine._
import at.pria.koza.harmonic.HeadListener
import at.pria.koza.harmonic.State

/**
 * <p>
 * {@code TicTacToePanel}
 * </p>
 *
 * @version V1.0 08.08.2013
 * @author SillyFreak
 */
@SerialVersionUID(-4160262603196922197L)
class TicTacToePanel(host: Host) extends JPanel(new BorderLayout()) {
  private implicit val engine = host.engine

  val buttons = new Array[JButton](9)

  {
    val field = new JPanel(new GridLayout(3, 3));
    val dim = new Dimension(60, 60);
    for (i <- 0 to buttons.length - 1) {
      val b = new JButton(new PlaceAction(i % 3, i / 3))
      b.setBorder(b.getBorder().asInstanceOf[CompoundBorder].getOutsideBorder())
      b.setPreferredSize(dim)
      buttons(i) = b
      field.add(b)
    }
    add(field)
  }

  val start = new JButton(StartAction)
  add(start, BorderLayout.NORTH)

  val undo = new JButton(UndoAction)
  add(undo, BorderLayout.SOUTH)

  update()
  host.engine.Head.addListener(UpdateListener)

  def update(): Unit = {
    host.game match {
      case Some(game) =>
        start.setEnabled(!game.gameRunning)
        undo.setEnabled(true)
        for (i <- 0 to buttons.length - 1)
          updateButton(game, i)
      case None =>
        undo.setEnabled(false)
        start.setEnabled(true)
        for (i <- 0 to buttons.length - 1)
          updateButton(null, i)
    }
  }

  private[this] def updateButton(game: TTTGame, i: Int): Unit = {
    val b = buttons(i)
    if (game == null) {
      b.setEnabled(false)
      b.setText("")
    } else {
      val p = game.board(i % 3, i / 3)
      b.setEnabled(p == null && game.gameRunning)
      b.setText(
        if (p == null) ""
        else p.owner.playerId match {
          case 0 => "X"
          case 1 => "O"
          case _ => throw new AssertionError()
        })
    }
  }

  @SerialVersionUID(1L)
  private final class PlaceAction(i: Int, j: Int) extends AbstractAction {
    override def actionPerformed(e: ActionEvent): Unit = {
      val game = host.game.get
      val action = new PlacePieceAction(game, game.nextPlayer, i, j)
      engine.execute(action)
      host.publish(0)
    }
  }

  @SerialVersionUID(1L)
  private object StartAction extends AbstractAction("New Game") {
    override def actionPerformed(e: ActionEvent): Unit = {
      host.newGame()
      host.publish(0)
    }
  }

  @SerialVersionUID(1L)
  private object UndoAction extends AbstractAction("Undo") {
    override def actionPerformed(e: ActionEvent): Unit = {
      engine.head = engine.head.parent
      host.publish(0)
    }
  }

  private object UpdateListener extends HeadListener {
    override def headMoved(prevHead: State, newHead: State): Unit =
      update()
  }
}
