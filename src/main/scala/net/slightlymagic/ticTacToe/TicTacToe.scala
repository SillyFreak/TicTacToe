/**
 * TicTacToe.scala
 *
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe

import at.pria.koza.harmonic.BranchListener
import at.pria.koza.harmonic.Engine
import at.pria.koza.harmonic.RemoteEngine
import at.pria.koza.harmonic.RemoteEngineListener
import at.pria.koza.harmonic.State
import at.pria.koza.harmonic.StateNode
import at.pria.koza.harmonic.local.LocalEngine
import at.pria.koza.harmonic.viewer.HarmonicViewer

import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel

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

    //configures host 1 to update with host 2
    def connect(host1: Host, host2: Host) = {
      //remote connecting to engine 2
      val remote = new LocalEngine(host2.engine)
      val remoteBranch = host2.branch.name

      //track branch
      val branch = host1.branch
      branch.tracking = (remote, remoteBranch)

      //action when branch is updated
      remote.addListener(new RemoteEngineListener() {
        override def headsUpdated(remote: RemoteEngine, oldHeads: Map[String, Long], newHeads: Map[String, Long]): Unit =
          if (oldHeads.get(remoteBranch) != newHeads.get(remoteBranch)) {
            remote.download(remoteBranch)(host1.engine)
            branch.update()
          }
      })

      //make sure engine 1 pulls when branch changes
      //TODO use a push mechanism
      host2.engine.Branches.addListener(new BranchListener() {
        override def branchMoved(engine: Engine, branch: String, prevTip: State, newTip: State): Unit =
          remote.fetch()
      })
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

    val host1 = createHost()
    val host2 = createHost()
    connect(host1, host2)
    connect(host2, host1)

    val jf = new JFrame("%08X - %08X".format(host1.engine.id, host2.engine.id))
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    jf.add {
      val jp = new JPanel(new GridLayout(0, 1))
      jp.add(createPanel(host1))
      jp.add(createPanel(host2))
      jp
    }

    jf.pack()
    jf.setVisible(true)
  }
}
