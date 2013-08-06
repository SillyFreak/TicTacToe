/**
 * Host.java
 * 
 * Created on 02.08.2013
 */

package net.slightlymagic.ticTacToe;


import net.slightlymagic.ticTacToe.action.NewGameAction;

import org.jgroups.JChannel;

import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.jGroups.JGroupsBranchAdapter;
import at.pria.koza.polybuf.PolybufConfig;


/**
 * <p>
 * {@code Host}
 * </p>
 * 
 * @version V0.0 02.08.2013
 * @author SillyFreak
 */
public class Host {
    private final BranchManager        mgr;
    private final JGroupsBranchAdapter adapter;
    
    private TTTGame                    game;
    
    public Host(JChannel ch) {
        mgr = new BranchManager();
        adapter = new JGroupsBranchAdapter(ch, mgr);
        ch.setReceiver(adapter);
    }
    
    public void newGame() {
        NewGameAction action = new NewGameAction(mgr.getEngine());
        mgr.execute(action);
        initGame();
    }
    
    public void connectToGame() {
//        new LocalSyncCallback(other.mgr, this.mgr).sendUpdate(TAG_NEW_GAME);
        initGame();
    }
    
    public void publish(int other, String branch) {
        adapter.sendUpdate(null, other, branch);
    }
    
    private void initGame() {
        game = (TTTGame) mgr.getEngine().getEntity(0);
    }
    
    public BranchManager getBranchManager() {
        return mgr;
    }
    
    public Engine getEngine() {
        return mgr.getEngine();
    }
    
    public PolybufConfig getConfig() {
        return mgr.getEngine().getConfig();
    }
    
    public TTTGame getGame() {
        return game;
    }
    
    public JGroupsBranchAdapter getAdapter() {
        return adapter;
    }
    
//    private static class LocalSyncCallback implements SyncCallback {
//        private final BranchManager sender, receiver;
//        
//        private LocalSyncCallback(BranchManager sender, BranchManager receiver) {
//            this.sender = sender;
//            this.receiver = receiver;
//        }
//        
//        public void sendUpdate(String branch) {
//            sender.sendUpdate(receiver.getEngine().getId(), branch, this);
//        }
//        
//        @Override
//        public void sendUpdateCallback(int engine, String branch, Obj state, long... ancestors) {
//            receiver.receiveUpdate(engine, branch, state, ancestors, this);
//        }
//        
//        @Override
//        public void receiveUpdateCallback(int engine, String branch, long ancestor) {
//            sender.sendMissing(receiver.getEngine().getId(), branch, ancestor, this);
//        }
//        
//        @Override
//        public void sendMissingCallback(int engine, String branch, long state, Obj... ancestors) {
//            receiver.receiveMissing(engine, branch, state, ancestors);
//        }
//    }
}
