/**
 * TicTacToe.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.ticTacToe;


import static java.lang.String.*;

import javax.swing.JFrame;

import net.slightlymagic.ticTacToe.action.NewGameAction;
import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.polybuf.PolybufConfig;


/**
 * <p>
 * The class TicTacToe.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class TicTacToe {
    public static void main(String[] args) throws Exception {
        Host host = config("ticTacToe");
        
        JFrame jf = new JFrame(format("%08X", host.getEngine().getId()));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jf.add(new TicTacToePanel(host));
        
        jf.pack();
        jf.setVisible(true);
    }
    
    private static Host config(String cluster) throws Exception {
        final Host host = new Host(cluster);
        
        BranchManager mgr = host.getBranchManager();
        Engine engine = mgr.getEngine();
        PolybufConfig config = engine.getConfig();
        
        mgr.configure(config);
        PlacePieceAction.configure(config, engine);
        NewGameAction.configure(config, engine);
        
        return host;
    }
}
