package a01a.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{
    
    private int hitCounter = 0;
    private int missCounter = 0;
    private Map<JButton, Integer> buttons = new HashMap<>();
    private int pos;
    private int boat;
    private static final int MAX_MISS=5; 

    public GUI(int size, int boat) {        
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        if(boat>size) {
            System.out.println("Boat must be smaller or equal at field size");
            System.exit(1);
        }
        pos = boatPosition(size, boat);
        this.boat = boat;

        for (int i=0;i<size*size;i++){
            final JButton jb = new JButton();
            buttons.put(jb, i);
            jb.addActionListener(this);
            panel.add(jb);
        }
        this.setVisible(true);
    }

    public int boatPosition(int size, int boat) {
        double doubleRow = Math.random() * size;
        int row = (int)doubleRow;
        double doubleCols = Math.random() * (size-boat);
        int cols = (int)doubleCols;
        int position = row * size + cols;
        return position;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton)e.getSource();
        if(buttons.get(pressed)>=pos && buttons.get(pressed)<(pos+boat)) {
            System.out.println("Hit");
            pressed.setText("X");
            hitCounter++;
        } else {
            missCounter++;
            System.out.println("Miss");
            pressed.setText("0");
        }
        pressed.setEnabled(false);
        computeResult();
    }

    private void computeResult() {
        if(missCounter == MAX_MISS) {
            System.out.println("YOU LOSE");
            System.exit(0);
        }
        if(hitCounter == this.boat) {
            System.out.println("YOU WIN");
            System.exit(0);
        }
    } 

    public static void main(String[] args){
        new GUI(5,2);
    }
}
