package a01b.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    
    private int count=0;
    private final int mines;
    private final int size;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Set<Pair<Integer,Integer>> minesSet = new HashSet<>();

    public GUI(int size, int mines) {
        this.size=size;
        this.mines=mines;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        for (int i=0;i<size; i++){
            for (int j=0; j<size; j++) {
                final JButton jb = new JButton();
                jb.addActionListener(this);
                buttons.put(jb, new Pair<>(i, j));
                panel.add(jb);
            }
        }
        putMines();
        this.setVisible(true);
    }

    private void putMines() {
        for(int i=0; i<this.mines; i++) {
            var random =  Math.random() * 4;
            int row = (int) random;
            random = Math.random() * 4;
            int column = (int) random;
            var pair = new Pair<>(row, column);
            if(!minesSet.contains(pair)) {
                minesSet.add(pair);
            } 
            else {
                i--;
            }
        }
    }
    private int neighbours(Pair<Integer, Integer> p) {
        int counter=0;
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if(minesSet.contains(new Pair<>(p.getX()+i, p.getY()+j))) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton bt = (JButton)e.getSource();
        bt.setEnabled(false);
        if(minesSet.contains(buttons.get(bt))) {
            System.out.println("YOU LOSE");
            System.exit(0);
        } else {
            this.count++;
            bt.setText(String.valueOf(neighbours(buttons.get(bt))));
            if(this.count == (size*size-mines)) {
                System.out.println("YOU WIN");
                System.exit(0);
            }
        }
        
        
        
    }
    
}
