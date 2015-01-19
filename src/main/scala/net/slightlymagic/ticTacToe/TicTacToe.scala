/**
 * TicTacToe.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe

import java.lang.String._

import javax.swing.JFrame

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
    val host = config("ticTacToe")

    val jf = new JFrame("%08X".format(host.engine.id))
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    jf.add(new TicTacToePanel(host))

    jf.pack()
    jf.setVisible(true)
  }

  @throws[Exception]
  def config(cluster: String): Host = {
    val host = new Host(cluster)

    val mgr = host.mgr
    val engine = mgr.engine

    engine.addIO(mgr)
    engine.addIO(PlacePieceAction)
    engine.addIO(NewGameAction)

    host
  }
}
