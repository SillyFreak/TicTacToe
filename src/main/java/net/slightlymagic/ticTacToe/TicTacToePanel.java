/**
 * TicTacToePanel.java
 * 
 * Created on 08.08.2013
 */

package net.slightlymagic.ticTacToe;


import static at.pria.koza.harmonic.BranchManager.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

import net.slightlymagic.ticTacToe.action.PlacePieceAction;
import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.HeadListener;
import at.pria.koza.harmonic.State;


/**
 * <p>
 * {@code TicTacToePanel}
 * </p>
 * 
 * @version V1.0 08.08.2013
 * @author SillyFreak
 */
public class TicTacToePanel extends JPanel {
    private static final long serialVersionUID = -4160262603196922197L;
    
    private final Host        host;
    
    private final JButton     start, undo;
    private final JButton[][] buttons;
    
    public TicTacToePanel(Host host) {
        super(new BorderLayout());
        
        this.host = host;
        buttons = new JButton[3][3];
        
        JPanel field = new JPanel(new GridLayout(3, 3));
        Dimension dim = new Dimension(60, 60);
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
                JButton b = new JButton(new PlaceAction(i, j));
                b.setBorder(((CompoundBorder) b.getBorder()).getOutsideBorder());
                b.setPreferredSize(dim);
                buttons[i][j] = b;
                field.add(b);
            }
        }
        add(field);
        
        start = new JButton(new StartAction());
        add(start, BorderLayout.NORTH);
        
        undo = new JButton(new UndoAction());
        add(undo, BorderLayout.SOUTH);
        
        update();
        host.getEngine().addHeadListener(new UpdateListener());
    }
    
    public void update() {
        TTTGame game = host.getGame();
        start.setEnabled(game == null || !game.isGameRunning());
        undo.setEnabled(game != null);
        
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
                updateButton(game, i, j);
            }
        }
    }
    
    private void updateButton(TTTGame game, int i, int j) {
        JButton b = buttons[i][j];
        if(game == null) {
            b.setEnabled(false);
            b.setText("");
        } else {
            TTTPiece p = game.getPiece(i, j);
            b.setEnabled(p == null && game.isGameRunning());
            switch(p == null? -1:p.getOwner().getPlayerId()) {
                case -1:
                    b.setText("");
                break;
                case 0:
                    b.setText("X");
                break;
                case 1:
                    b.setText("O");
                break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    private final class PlaceAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        private int               i, j;
        
        public PlaceAction(int i, int j) {
            this.i = i;
            this.j = j;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            TTTGame game = host.getGame();
            Action action = new PlacePieceAction(host.getEngine(), game, game.getNextPlayer(), i, j);
            host.getBranchManager().execute(action);
            host.publish(0, BranchManager.BRANCH_DEFAULT);
        }
    }
    
    private final class StartAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public StartAction() {
            super("New Game");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            host.newGame();
            host.publish(0, BRANCH_DEFAULT);
        }
    }
    
    private final class UndoAction extends AbstractAction {
        private static final long serialVersionUID = 1L;
        
        public UndoAction() {
            super("Undo");
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            BranchManager mgr = host.getBranchManager();
            mgr.setBranchTip(BRANCH_DEFAULT, mgr.getBranchTip(BRANCH_DEFAULT).getParent());
            host.publish(0, BRANCH_DEFAULT);
        }
    }
    
    private final class UpdateListener implements HeadListener {
        @Override
        public void headMoved(State prevHead, State newHead) {
            update();
        }
    }
}
