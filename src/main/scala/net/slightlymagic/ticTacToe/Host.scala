/**
 * Host.scala
 *
 * Created on 02.08.2013
 */

package net.slightlymagic.ticTacToe

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
  val engine: Engine = new Engine()
  private val ch = new JChannel()
  private val adapter: JGroupsBranchAdapter = new JGroupsBranchAdapter(ch, engine)
  ch.setDiscardOwnMessages(true)
  ch.setReceiver(adapter)
  ch.connect(cluster)

  def game: Option[TTTGame] = engine.Entities.get(0).map { _.asInstanceOf[TTTGame] }

  def newGame(): Unit = {
    implicit def engine = this.engine
    engine.Branches.currentBranch.head = engine.states(0l)
    engine.execute(new NewGameAction())
  }

  def publish(other: Int, branch: String): Unit = ()
  //adapter.sendUpdate(null, other, branch)
}
