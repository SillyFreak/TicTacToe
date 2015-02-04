/**
 * TicTacToe.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe

import at.pria.koza.harmonic.viewer.HarmonicViewer

import java.awt.BorderLayout
import java.awt.Dimension

import javax.swing.JFrame
import javax.swing.JPanel

import at.pria.koza.harmonic.StateNode

import net.slightlymagic.ticTacToe.action.NewGameAction
import net.slightlymagic.ticTacToe.action.PlacePieceAction

/**
 * <p>
 * The class TicTacToe.
 * </p>
 *
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
object TicTacToe {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    def createHost(): Host = {
      val host = new Host()
      val engine = host.engine
      engine.addIO(StateNode)
      engine.addIO(PlacePieceAction)
      engine.addIO(NewGameAction)
      host
    }

    def createPanel(host: Host) = {
      val jp = new JPanel(new BorderLayout())
      jp.add({
        new TicTacToePanel(host)
      }, BorderLayout.WEST)
      jp.add {
        val viewer = new HarmonicViewer()
        viewer.setMinimumSize(new Dimension(300, 0))
        viewer.setPreferredSize(new Dimension(300, 0))
        viewer.listenTo(host.engine)
        viewer
      }
      jp
    }

    val host = createHost()

    val jf = new JFrame("%08X".format(host.engine.id))
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    jf.add(createPanel(host))

    jf.pack()
    jf.setVisible(true)
  }
}
