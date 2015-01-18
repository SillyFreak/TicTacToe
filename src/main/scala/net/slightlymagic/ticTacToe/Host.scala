/**
 * Host.scala
 *
 * Created on 02.08.2013
 */

package net.slightlymagic.ticTacToe

import at.pria.koza.harmonic.BranchManager
import at.pria.koza.harmonic.BranchManager._
import at.pria.koza.harmonic.Engine
import at.pria.koza.harmonic.jGroups.JGroupsBranchAdapter

import net.slightlymagic.ticTacToe.action.NewGameAction

import org.jgroups.JChannel

/**
 * <p>
 * {@code Host}
 * </p>
 *
 * @version V0.0 02.08.2013
 * @author SillyFreak
 */
class Host(cluster: String) {
  val mgr: BranchManager = new BranchManager()
  private val ch = new JChannel()
  private val adapter: JGroupsBranchAdapter = new JGroupsBranchAdapter(ch, mgr)
  ch.setDiscardOwnMessages(true)
  ch.setReceiver(adapter)
  ch.connect(cluster)

  def engine: Engine = mgr.engine
  def game: TTTGame = engine.entity(0).asInstanceOf[TTTGame]

  def newGame(): Unit = {
    mgr.branchTip(BRANCH_DEFAULT, mgr.engine.state(0l))
    mgr.execute(new NewGameAction(mgr.engine))
  }

  def publish(other: Int, branch: String): Unit =
    adapter.sendUpdate(null, other, branch)
}