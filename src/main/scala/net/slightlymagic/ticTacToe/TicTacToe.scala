/**
 * TicTacToe.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe

import javax.swing.JFrame

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

    val host = createHost()

    val jf = new JFrame("%08X".format(host.engine.id))
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    jf.add(new TicTacToePanel(host))

    jf.pack()
    jf.setVisible(true)
  }
}
