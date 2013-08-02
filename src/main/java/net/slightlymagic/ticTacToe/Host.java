/**
 * Host.java
 * 
 * Created on 02.08.2013
 */

package net.slightlymagic.ticTacToe;


import net.slightlymagic.ticTacToe.action.NewGameAction;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.BranchManager.SyncCallback;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.polybuf.proto.Polybuf.Obj;


/**
 * <p>
 * {@code Host}
 * </p>
 * 
 * @version V0.0 02.08.2013
 * @author SillyFreak
 */
public class Host {
    private static final String TAG_NEW_GAME = "tag-newGame";
    
    private final Engine        eng;
    private final BranchManager mgr;
    
    private TTTGame             game;
    
    public Host() {
        mgr = new BranchManager();
        eng = mgr.getEngine();
    }
    
    public void newGame() {
        NewGameAction action = new NewGameAction(eng);
        mgr.execute(action);
        mgr.createBranchHere(TAG_NEW_GAME);
        initGame();
    }
    
    public void connectToGame(final Host other) {
        new LocalSyncCallback(other.mgr, this.mgr).sendUpdate(TAG_NEW_GAME);
        initGame();
    }
    
    public void synchronize(final Host other) {
        new LocalSyncCallback(other.mgr, this.mgr).sendUpdate("default");
    }
    
    private void initGame() {
        game = ((NewGameAction) mgr.getBranchTip(TAG_NEW_GAME).getAction()).getGame();
    }
    
    public TTTGame getGame() {
        return game;
    }
    
    private static class LocalSyncCallback implements SyncCallback {
        private final BranchManager sender, receiver;
        
        private LocalSyncCallback(BranchManager sender, BranchManager receiver) {
            this.sender = sender;
            this.receiver = receiver;
        }
        
        public void sendUpdate(String branch) {
            sender.sendUpdate(receiver.getEngine().getId(), branch, this);
        }
        
        @Override
        public void sendUpdateCallback(int engine, String branch, Obj state, long... ancestors) {
            long result = receiver.receiveUpdate(engine, branch, state, ancestors);
            sender.sendMissing(receiver.getEngine().getId(), branch, result, this);
        }
        
        @Override
        public void sendMissingCallback(int engine, String branch, long state, Obj... ancestors) {
            receiver.receiveMissing(engine, branch, state, ancestors);
        }
    }
}
